package socket.client;

import socket.net.Receiver;
import socket.net.Sender;
import socket.net.SocketIO;
import socket.request.EncodedRequest;
import socket.request.message.JpegRequest;
import socket.request.message.TextRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Client {
    private SocketIO socket;
    private BlockingQueue<EncodedRequest> incoming;
    private BlockingQueue<EncodedRequest> outgoing;
    private Thread receiverThread;
    private Thread senderThread;
    private Thread incomigReqService;

    public Client(String ip, int port) {
        this.socket = new SocketIO();
        this.tryToConnectWithServer(ip, port);

        this.incoming = new LinkedBlockingQueue<>();
        this.outgoing = new LinkedBlockingQueue<>();

        this.incomigReqService = new Thread(new IncomingRequestService(this.incoming));
        this.receiverThread = new Thread(new Receiver(this.socket, this.incoming));
        this.senderThread = new Thread(new Sender(this.socket, this.outgoing));
        this.run();
    }

    private void run() {
        this.incomigReqService.start();
        this.receiverThread.start();
        this.senderThread.start();

        TextRequest textRequest = new TextRequest("lolol");
        textRequest.encode().ifPresent(request -> this.outgoing.add(request));

        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        try {
            BufferedImage capture = new Robot().createScreenCapture(screenRect);
            JpegRequest jpegRequest = new JpegRequest(capture);
            jpegRequest.encode().ifPresent(request -> this.outgoing.add(request));
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void tryToConnectWithServer(String ip, int port) {
        boolean running = true;
        while (running) {
            try {
                this.socket.connect(ip, port);
                running = false;
            } catch (IOException e) {
                System.out.println("Error during connecting to server. I'll wait 10s and try again.");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Client("localhost", 3000);
    }
}
