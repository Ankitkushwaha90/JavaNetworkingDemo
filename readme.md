import { Callout, CodeBlock } from 'next-docs-ui/components';

# Network Programming: Date-Time Server & Client using TCP Sockets

A Java implementation demonstrating fundamental networking concepts

## Overview

This project consists of two Java programs:

- **DateTimeServer.java** - A TCP server that sends the current date and time to connected clients
- **DateTimeClient.java** - A TCP client that connects to the server and retrieves the server's date and time

<Callout type="info">
These programs illustrate:
✔ TCP Socket Communication (Connection-oriented, reliable data transfer)  
✔ Client-Server Architecture (How servers handle multiple clients)  
✔ Java Networking APIs (ServerSocket, Socket, InetAddress)  
✔ Error Handling & Resource Management (Proper stream and socket closure)  
</Callout>

## How It Works

### Server-Side (DateTimeServer.java)
1. Creates a ServerSocket bound to port 4444
2. Accepts client connections using `ss.accept()`
3. Sends the current date/time (`java.util.Date`) to the client
4. Receives the client's IP address and logs it
5. Closes the connection after sending data

### Client-Side (DateTimeClient.java)
1. Connects to the server using `Socket(InetAddress, port)`
2. Reads the server's date/time from the input stream
3. Sends its own IP address back to the server
4. Displays the received date/time on the console

## Key Networking Concepts Explained

### 1. TCP vs. UDP
| Feature        | TCP                          | UDP                          |
|---------------|-----------------------------|-----------------------------|
| Connection    | Connection-oriented         | Connectionless              |
| Reliability   | Guaranteed delivery         | No delivery guarantee       |
| Use Cases     | Date-time exchange, web     | VoIP, video streaming       |

### 2. Socket Programming Basics
- **ServerSocket** → Waits for client connections
- **Socket** → Represents an active connection  
- **InetAddress** → Handles IP addresses

### 3. Streams in Java Networking
- `InputStream`/`OutputStream` → Raw byte streams
- `BufferedReader`/`PrintStream` → Text-based I/O wrappers

## Running the Code

### Server Execution
<CodeBlock lang="bash">
javac DateTimeServer.java
java DateTimeServer
</CodeBlock>

**Output:**
Server started. Press Ctrl+C to quit
Client System's IP address is: 127.0.0.1

### Client Execution
<CodeBlock lang="bash">
javac DateTimeClient.java
java DateTimeClient
</CodeBlock>

**Output:**
The date/time on server is: Wed Apr 25 14:30:45 UTC 2024


## Extending This Project

- **Multi-Threading**: Handle multiple clients simultaneously
- **GUI Version**: Use JavaFX/Swing for visual interface
- **Encryption**: Add SSL/TLS for secure communication
- **Broadcast Server**: Use UDP to send time to multiple clients

## Further Reading

- [Oracle Java Networking Guide](https://docs.oracle.com/javase/tutorial/networking/)
- Books:
  - "TCP/IP Illustrated" by Richard Stevens
  - "Java Network Programming" by Elliotte Rusty Harold

## Conclusion

This project demonstrates core networking concepts in Java:

✅ Basic client-server interaction  
✅ Proper resource management  
✅ Real-world networking principles  

By extending this code, you can build chat apps, file transfer systems, or even HTTP servers!

<Callout type="tip">
🚀 Happy Coding! 🚀
</Callout>
