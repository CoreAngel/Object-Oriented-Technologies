### Socket

Client, server program with functionality to rebroadcasts messages from client to all clients connected to server.
Synchronization between threads provide blockingQueue. Messages are bytes array with structure:
- type (4 bytes)
- payload size (4 bytes)
- payload

#### Design patterns:
- Chain of responsibility
