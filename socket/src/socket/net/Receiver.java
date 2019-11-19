package socket.net;

import socket.request.EncodedRequest;

import java.io.IOException;
import java.nio.ByteBuffer;

import java.util.concurrent.BlockingQueue;

public class Receiver implements Runnable {
    private BlockingQueue<EncodedRequest> queue;
    private SocketIO socket;

    public Receiver(SocketIO socket, BlockingQueue<EncodedRequest> queue) {
        this.queue = queue;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                byte[] requestType = new byte[EncodedRequest.TypeSize];
                socket.read(requestType);

                byte[] payloadSize = new byte[EncodedRequest.LengthSize];
                socket.read(payloadSize);
                int payloadLength = ByteBuffer.allocate(EncodedRequest.LengthSize).put(payloadSize).rewind().getInt();

                byte[] payload = this.readPayload(payloadLength);

                EncodedRequest request = this.createEncodedRequest(requestType, payloadSize, payload);
                queue.add(request);
            }
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        Thread.currentThread().interrupt();
    }

    private EncodedRequest createEncodedRequest(byte[] type, byte[] size, byte[] payload) {
        int requestSize = type.length + size.length + payload.length;
        byte[] request = ByteBuffer.allocate(requestSize)
                            .put(type)
                            .put(size)
                            .put(payload)
                            .array();

        return new EncodedRequest(request);
    }

    private byte[] readPayload(int payloadSize) throws IOException {
        byte[] payload = new byte[payloadSize];
        int bytesRemaining = payloadSize;
        int offset = 0;
        while (bytesRemaining > 0) {
            int readCount = this.socket.read(payload, offset, bytesRemaining);
            offset += readCount;
            bytesRemaining -= readCount;
        }
        return payload;
    }
}
