// Kyle Geib - CSS434 - Dr Fukuda - Program 01 - October 16th, 2012
// Connection.java

import java.net.*;
import java.io.*;

public class Connection {
	public String name;
	public Boolean alive = false;
	
	private Socket clientSocket;	// Socket connection server has to client
	private InputStream rawIn;		// an input stream from the server
	private DataInputStream in;		// a filtered input stream from the server
	private DataOutputStream out;	// a filtered output stream to the server
	
	
	
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
		
		// Successful connection in reading the new user's name.
		alive = true;
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	/** readMessage -----------------------------------------------------------
	 * Attempts receiving a new message from this Client Socket. If an error
	 * occurs
	 * @return		
	 */
	public String readMessage() {
		String message = null;
		
		try { 
			if (rawIn.available() > 0)
				message = in.readUTF(); 
		} catch (IOException e) { alive = false; }
		
		return message;
	}
	
	/** writeMessage ----------------------------------------------------------
	 * Attempts to send a message to this client
	 * @param message	String of the message we want to send to the client.
	 * @return			Returns whether or not it was successful.
	 */
	public boolean writeMessage(String message) {
		
		try {
			out.writeUTF(message);
		} catch (IOException e) {
			alive = false;	// The connection may be dead
			return false;
		}
		alive = true;
		return true;
	}

	/** equals ----------------------------------------------------------------
	 * Small function that allows Vector.contains() to check for a match
	 * @param match		The socket to be compared
	 * @return			The result of using equals() on the Socket object
	 */
	public boolean equals(Socket match) {
		return clientSocket.equals(match);
	}
	
	/** close -----------------------------------------------------------------
	 * Small method that closes the client socket.
	 * @return			The success of the attempt at closing the Socket.
	 */
	public boolean close() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		// Able to close without issue.
		return true;
	}
}
