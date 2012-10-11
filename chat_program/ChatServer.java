// Kyle Geib - CSS434 - Dr Fukuda - Program 01 - October 16th, 2012
// ChatServer.java

package chat_program;

import java.net.*;
import java.io.*;
import java.util.Vector;

public class ChatServer {
	//private Socket socket;			// a socket connection to a chat server
	//private InputStream rawIn;		// an input stream from the server
	private DataInputStream in;		// a filtered input stream from the server
	private DataOutputStream out;	// a filtered output stream to the server
	//private BufferedReader stdin;	// the standard input
	
	
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
				connectionVector.add(clientConn);	// Add socket to list
				
				
			}
			} catch (SocketTimeoutException e) {}

		}	
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
