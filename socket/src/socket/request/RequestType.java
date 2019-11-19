package socket.request;

import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.stream.Stream;

public enum RequestType {
    TEXT(1),
    JPEG(2);

    private int index;

    RequestType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public static Optional<RequestType> getTypeFromBytes(byte[] bytes) {
        int index = ByteBuffer.allocate(bytes.length).put(bytes).rewind().getInt();
        return RequestType.getTypeFromIndex(index);
    }

    public static byte[] getBytesFromType(RequestType type) {
        return ByteBuffer.allocate(4).putInt(type.getIndex()).array();
    }

    private static Optional<RequestType> getTypeFromIndex(int index) {
        return Stream.of(RequestType.values()).filter(i -> i.getIndex() == index).findFirst();
    }
}
