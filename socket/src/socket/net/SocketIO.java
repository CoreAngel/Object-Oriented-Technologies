package socket.net;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketIO {

    private String ip = null;
    private Integer port = null;
    private Socket socket = null;

    private BufferedInputStream input = null;
    private BufferedOutputStream output = null;

    public void connect(Socket socket) throws IOException {
        if (!this.socketsAreEqual(socket, this.socket)) {
            this.close();
            this.socket = socket;
            this.setDataFromSocket(this.socket);
        }
    }

    public void connect(String ip, int port) throws IOException {
        if (!ip.equals(this.getIpFromSocket(this.socket)) || !(port == this.port)) {
            this.close();

            this.socket = new Socket(this.getInetAddressFromIp(ip), port);
            this.setDataFromSocket(this.socket);
        }
    }

    public boolean isConnected() {
        if (this.socket == null) {
            return false;
        } else if (this.socket.isConnected()) {
            return true;
        } else {
            try {
                this.socket.close();
            } catch (IOException ignored) {}
            this.socket = null;
            return false;
        }
    }

    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
        if (input != null) {
            input.close();
        }
        if (output != null) {
            output.close();
        }
    }

    public int read(byte[] bytes) throws IOException {
        if (!isConnected()) {
            throw new IOException("Socket not connected");
        }
        return input.read(bytes);

    }

    public int read(byte[] bytes, int i, int remaining) throws IOException {
        if (!isConnected()) {
            throw new IOException("Socket not connected");
        }
        return input.read(bytes, i, remaining);
    }

    public void write(byte[] bytes) throws IOException {
        if (!isConnected()) {
            throw new IOException("Socket not connected");
        }
        output.write(bytes);
    }

    public void flush() throws IOException {
        if (!isConnected()) {
            throw new IOException("Socket not connected");
        }
        output.flush();
    }

    public InetAddress getInetAddressFromIp(String ip) throws UnknownHostException {
        return InetAddress.getByName(ip);
    }

    public BufferedInputStream getInputStream() {
        return input;
    }

    public BufferedOutputStream getOutputStream() {
        return output;
    }

    private void setDataFromSocket(Socket socket) throws IOException {
        this.ip = this.getIpFromSocket(socket);
        this.port = socket.getPort();
        this.input = new BufferedInputStream(socket.getInputStream());
        this.output = new BufferedOutputStream(socket.getOutputStream());
    }

    private String getIpFromSocket(Socket socket) {
        if (socket != null) {
            return (((InetSocketAddress) socket.getRemoteSocketAddress())
                    .getAddress())
                    .toString()
                    .replace("/", "");
        } else {
            return "";
        }
    }

    private boolean socketsAreEqual(Socket firstSocket, Socket secondSocket) {
        return this.getIpFromSocket(firstSocket).equals(this.getIpFromSocket(secondSocket))
                && firstSocket.getPort() == secondSocket.getPort();
    }

}
