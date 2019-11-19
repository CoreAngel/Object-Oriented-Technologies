package socket.server;

import socket.request.EncodedRequest;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class IncomingRequestService implements Runnable {

    private ArrayList<ConnectedClient> clients;
    private BlockingQueue<EncodedRequest> incoming;

    public IncomingRequestService(ArrayList<ConnectedClient> clients, BlockingQueue<EncodedRequest> incoming) {
        this.clients = clients;
        this.incoming = incoming;
    }

    @Override
    public void run() {

        while(!Thread.currentThread().isInterrupted()) {
            try {
                EncodedRequest request = this.incoming.take();
                if (request.getType().isPresent()) {
                    for (ConnectedClient client: this.clients) {
                        client.addRequestToSend(request);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
