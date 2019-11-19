package socket.net;

import socket.request.EncodedRequest;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class Sender implements Runnable {
    private SocketIO socket;
    private BlockingQueue<EncodedRequest> queue;

    public Sender(SocketIO socket, BlockingQueue<EncodedRequest> queue) {
        this.socket = socket;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (socket.isConnected()){
                    EncodedRequest request = queue.take();
                    socket.write(request.getRequest());
                    socket.flush();
                }
            }
        } catch (final IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        Thread.currentThread().interrupt();
    }
}
