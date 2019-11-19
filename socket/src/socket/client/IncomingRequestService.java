package socket.client;

import socket.request.EncodedRequest;
import socket.request.Request;
import socket.request.RequestFlowFactory;

import java.util.concurrent.BlockingQueue;

public class IncomingRequestService implements Runnable {
    private BlockingQueue<EncodedRequest> queue;
    private Request startFlow;

    public IncomingRequestService(BlockingQueue<EncodedRequest> queue) {
        this.queue = queue;
        new RequestFlowFactory().getFlow().ifPresent(flow -> this.startFlow = flow);
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                EncodedRequest request = this.queue.take();
                startFlow.handleRequest(request);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
