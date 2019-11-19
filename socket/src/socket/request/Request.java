package socket.request;

import java.util.Optional;

public abstract class Request implements IRequest {
    private RequestType type;
    private IRequest next;

    public Request(RequestType type) {
        this.type = type;
    }

    public void setNext(IRequest request) {
        this.next = request;
    }

    @Override
    public void handleRequest(EncodedRequest request) {
        if (request.getType().isPresent() && type == request.getType().get()) {
            this.handle(request);
        } else if (request.getType().isPresent() && next != null) {
            this.next.handleRequest(request);
        } else {
            System.out.println("Unknown request type");
        }
    }

    public RequestType getType() {
        return this.type;
    }

    public abstract Object handle(EncodedRequest request);

    public abstract Optional<EncodedRequest> encode();

}
