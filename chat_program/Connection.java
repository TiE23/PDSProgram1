// Kyle Geib - CSS434 - Dr Fukuda - Program 01 - October 16th, 2012
// Connection.java

package chat_program;

import java.net.*;
import java.io.*;

public class Connection {
	public String name;
	public Boolean alive = true;
	private Socket clientSocket;
	
	public Connection(Socket incoming) {
		
		
	}
	
	public String readMessage() {
		
		return "";
	}
	
	public Boolean writeMessage(String message) {
		
		return false;
	}
}
