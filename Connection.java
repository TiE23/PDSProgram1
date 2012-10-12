// Kyle Geib - CSS434 - Dr Fukuda - Program 01 - October 16th, 2012
// Connection.java

import java.net.*;
import java.io.*;

public class Connection {
	public String name;
	
	private Socket clientSocket;
	private InputStream rawIn;		// an input stream from the server
	private DataInputStream in;		// a filtered input stream from the server
	private DataOutputStream out;	// a filtered output stream to the server
	
	public Boolean alive = false;
	
	// Constructor -----------------------------------------------------------
	/* Takes in the client socket and establishes the user name and the other
	 * various streams.
	 */
	public Connection(Socket incoming) {
		clientSocket = incoming;
		
		try {
		rawIn = clientSocket.getInputStream();
		
		in = new DataInputStream(rawIn);
		out = new DataOutputStream(clientSocket.getOutputStream());
		
		// Receive the name of the user with the 
		name = in.readUTF();
		
		alive = true;
		} catch (Exception e) { e.printStackTrace(); }
		
		System.out.println("User " + name + " joined!");
	}
	
	public String readMessage() {
		
		return "";
	}
	
	public boolean writeMessage(String message) {
		
		return false;
	}

	// equals ----------------------------------------------------------------
	// Small function that allows Vector.contains() to check for a match
	public boolean equals(Socket match) {
		return clientSocket.equals(match);
	}
	
	// close -----------------------------------------------------------------
	// Small method that closes the client socket.
	public boolean close() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		// Able to close without issue.
		alive = false;
		return true;
	}
}
