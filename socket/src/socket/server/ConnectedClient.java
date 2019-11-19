package socket.server;

import socket.net.Receiver;
import socket.net.Sender;
import socket.net.SocketIO;
import socket.request.EncodedRequest;

import java.util.ArrayList;
import java.util.concurrent.*;

public class ConnectedClient {

    private SocketIO socket;
    private BlockingQueue<EncodedRequest> incoming;
    private BlockingQueue<EncodedRequest> outgoing;
    private Receiver receiver;
    private Sender sender;

    public ConnectedClient(SocketIO socket, BlockingQueue<EncodedRequest> incoming) {
        this.socket = socket;
        this.incoming = incoming;
        this.outgoing = new LinkedBlockingQueue<>();
    }

    public void run(ExecutorService executor) {
        this.receiver = new Receiver(this.socket, this.incoming);
        this.sender = new Sender(this.socket, this.outgoing);
        executor.execute(this.receiver);
        executor.execute(this.sender);
    }

    public void addRequestToSend(EncodedRequest request) {
        this.outgoing.add(request);
    }

    public void closeClient() {
        if (this.receiver != null) {
            this.receiver.stop();
        }
        if (this.sender != null) {
            this.sender.stop();
        }
    }


}
