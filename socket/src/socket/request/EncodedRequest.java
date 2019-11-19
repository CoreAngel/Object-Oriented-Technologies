package socket.request;

import java.nio.ByteBuffer;
import java.util.Optional;

public class EncodedRequest {
    public final static int TypeSize = 4;
    public final static int LengthSize = 4;
    public final static int MaxPayloadSize = Integer.MAX_VALUE - EncodedRequest.TypeSize - EncodedRequest.LengthSize;


    private RequestType enumType;
    private int requestSize;
    private byte[] type;
    private byte[] size;
    private byte[] payload;


    public EncodedRequest(byte[] rawRequest) {
        this.requestSize = rawRequest.length;
        this.type = new byte[EncodedRequest.TypeSize];
        this.size = new byte[EncodedRequest.LengthSize];
        int payloadLength = this.requestSize - EncodedRequest.TypeSize - EncodedRequest.LengthSize;
        this.payload = new byte[payloadLength];

        ByteBuffer buffer = ByteBuffer.wrap(rawRequest).rewind();
        buffer.get(this.type, 0, EncodedRequest.TypeSize);
        buffer.get(this.size, 0, EncodedRequest.LengthSize);
        buffer.get(this.payload, 0, payloadLength);

        RequestType.getTypeFromBytes(this.type).ifPresent(type -> this.enumType = type);
    }


    public EncodedRequest(RequestType type, byte[] payload) {
        this.enumType = type;
        this.requestSize = payload.length + EncodedRequest.TypeSize + EncodedRequest.LengthSize;
        this.type = ByteBuffer.allocate(EncodedRequest.TypeSize).put(RequestType.getBytesFromType(type)).array();
        this.size = ByteBuffer.allocate(EncodedRequest.LengthSize).putInt(payload.length).array();
        this.payload = payload;
    }


    public byte[] getRequest() {
        return ByteBuffer.allocate(this.requestSize)
                .put(this.type)
                .put(this.size)
                .put(this.payload)
                .array();
    }

    public Optional<RequestType> getType() {
        return Optional.ofNullable(this.enumType);
    }

    public byte[] getPayload() {
        return this.payload;
    }

    public int getRequestSize() {
        return this.requestSize;
    }


    // @ToDo mozliwe ze te funkcje beda do wywalenia
//    public byte[] longToBytes(long x) {
//        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
//        buffer.putLong(x);
//        return buffer.array();
//    }
//
//
//    public byte[] intToBytes(int x) {
//        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
//        buffer.putInt(x);
//        return buffer.array();
//    }
//
//
//    public long bytesToLong(byte[] bytes) {
//        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
//        buffer.put(bytes);
//        buffer.flip();
//        return buffer.getLong();
//    }
//
//
//    public int bytesToInt(byte[] bytes) {
//        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
//        buffer.put(bytes);
//        buffer.flip();
//        return buffer.getInt();
//    }
}
