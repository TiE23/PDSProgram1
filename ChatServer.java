// Kyle Geib - CSS434 - Dr Fukuda - Program 01 - October 16th, 2012
// ChatServer.java

import java.net.*;
import java.io.*;
import java.util.Vector;

public class ChatServer {
	
	/**     
	 * Creates a socket and creates a list of client connections that is
	 * empty at the beginning. Thereafter, the server goes into a loop
	 * where it repeats operations.
	 * 
     * @param port   a server port
     */
	public ChatServer(int port) {
		// Create a vector of client-socket connections
		Vector<Connection> connectionVector = new Vector<Connection>();
		
		try {
		// Establish a server socket with given port
		ServerSocket svr = new ServerSocket(port);
		
		// Establish a 500ms time-out period
		svr.setSoTimeout(500);
		
		while (true) {	// Main loop
			try { // Try accepting a new connection
			Socket socket = svr.accept();
			
			// Check to see if returned socket is something new
			if (socket != null && !connectionVector.contains(socket)) {
				
				Connection clientConn = new Connection(socket);
				
				// Read the new user's name
				System.out.println("User " + clientConn.name + " joined!");
				
				// Add connection to list
				connectionVector.add(clientConn);
				
			}
			} catch (SocketTimeoutException e) { System.out.print("."); }

			// Go through the connection list
			for (int x = 0; x < connectionVector.size(); x++) {
				Connection temp = connectionVector.get(x);
				
				if (!temp.alive) {	// Clear out a dropped client
					System.out.println("User " + temp.name + " has dropped!");
					temp.close();
					connectionVector.remove(x);	// Remove this dropped client
					break;
				}
				
				// Try reading a new message from this client
				String message = temp.readMessage();
				
				// If the message has content, send it to the other clients
				if (message != null && !message.isEmpty()) {
					message = temp.name + ": " + message;
					
					// Cycle through the clients again
					for (int y = 0; y < connectionVector.size(); y++) {
						
						// Reach those who aren't the sending client
						if (y != x) {
							temp = connectionVector.get(y);
							temp.writeMessage(message);
						}
					}
				}
			}
		}	// End of Main loop
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	/**     
	* Usage: java ChatServer <port>
    *
    * @param args Receives a port in args[0].
    */
	public static void main(String args[]) {
		// Check # of args.
		if (args.length != 1) {
			System.err.println("Syntax: java ChatServer <port>");
			System.exit(1);
		}
		// Convert args[0] into an integer that will be used as port.
		int port = Integer.parseInt(args[0]);
		
		// Instantiate the main body of ChatServer application
		new ChatServer(port);
	}
}
