package socket.request;

import socket.request.message.JpegRequest;
import socket.request.message.TextRequest;

import java.util.ArrayList;
import java.util.Optional;

public class RequestFlowFactory {
    private ArrayList<Request> requests = new ArrayList<>();

    public RequestFlowFactory() {
        this.requests.add(new TextRequest());
        this.requests.add(new JpegRequest());

        this.setFlow();
    }

    private void setFlow() {
        if (requests.size() > 1) {
            for (int i = 1; i < requests.size(); i++) {
                Request firstReq = requests.get(i - 1);
                Request secondReq = requests.get(i);

                firstReq.setNext(secondReq);
            }
        }
    }

    public Optional<Request> getFlow() {
        if (!requests.isEmpty()) {
            return Optional.of(requests.get(0));
        }
        return Optional.empty();
    }
}
