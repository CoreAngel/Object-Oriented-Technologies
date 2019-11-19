package socket.server;

import socket.net.SocketIO;
import socket.request.EncodedRequest;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server {
    private static final int MAX_CLINTS = 500;

    private int port;
    private int maxClients;
    private final AtomicBoolean running = new AtomicBoolean(true);

    private ArrayList<ConnectedClient> clients = new ArrayList<>();
    private BlockingQueue<EncodedRequest> incoming = new LinkedBlockingQueue<>();
    private IncomingRequestService incomingRequestService;
    private ExecutorService pool;

    public Server(int port) {
        this(port, MAX_CLINTS);
    }

    public Server(int port, int maxClients) {
        this.port = port;
        this.maxClients = maxClients;
    }


    public void start() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::closeServer));

        this.pool = Executors.newFixedThreadPool(maxClients);
        try {
            ServerSocket listener = new ServerSocket(port);

            this.incomingRequestService = new IncomingRequestService(this.clients, this.incoming);
            this.pool.execute(this.incomingRequestService);

            while(running.get()) {
                SocketIO socket = new SocketIO();
                socket.connect(listener.accept());

                ConnectedClient connectedClient = new ConnectedClient(socket, this.incoming);
                this.clients.add(connectedClient);
                connectedClient.run(this.pool);
            }

        } catch (Exception e) {
            System.out.println("Server exception");
            closeServer();
        }
    }

    private void closeServer() {
        this.running.set(false);

        try {
            if(!this.pool.awaitTermination(5, TimeUnit.SECONDS)) {
                this.pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            this.pool.shutdownNow();
        }


    }

    public static void main(String[] args) {
        new Server(3000).start();
    }
}
