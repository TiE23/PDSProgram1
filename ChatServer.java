// Kyle Geib - CSS434 - Dr Fukuda - Program 01 - October 16th, 2012
// ChatServer.java

import java.net.*;
import java.io.*;

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
		try {
		// Establish a server socket with given port
		ServerSocket svr = new ServerSocket(port);
		while(true) {
			
			
		}	
		}
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
