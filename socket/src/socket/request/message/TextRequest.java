package socket.request.message;

import socket.request.EncodedRequest;
import socket.request.Request;
import socket.request.RequestType;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Optional;

public class TextRequest extends Request {
    private String charset = "UTF-8";
    private String message;

    public TextRequest() {
        super(RequestType.TEXT);
    }

    public TextRequest(String message) {
        super(RequestType.TEXT);
        this.message = message;
    }

    @Override
    public Object handle(EncodedRequest request) {
        byte[] payload = request.getPayload();
        try {
            String message = new String(payload, this.charset);
            System.out.println(message);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<EncodedRequest> encode() {
        if(!this.message.isEmpty()) {
            byte[] encodedPayload = this.message.getBytes(Charset.forName(this.charset));
            return Optional.of(new EncodedRequest(this.getType(), encodedPayload));
        }
        return Optional.empty();
    }

    public void setData(String data) {
        this.message = data;
    }

    Optional<String> getDate() {
        return Optional.ofNullable(this.message);
    }
}
