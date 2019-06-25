package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Model.Packet;
import Model.Client;

/**
 * This class is the client communications controller. It holds all objects required to communicate with the server. 
 * @author aaron
 *
 */

public class ClientCommsController {

	private Socket mySocket;
	private ObjectOutputStream toServerOOS;
	private ObjectInputStream fromServerOIS;
	private Packet myPacket;

	/**
	 * Constructor for the client communications controller. 
	 * @param serverName The name of the server each client tries to connect to 
	 * @param portNum The port number the socket will be located on 
	 */
	public ClientCommsController(String serverName, int portNum) {

		try {
			mySocket = new Socket(serverName, portNum);
			toServerOOS = new ObjectOutputStream(mySocket.getOutputStream());
			fromServerOIS = new ObjectInputStream(mySocket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		myPacket = new Packet();
	}

	/**
	 * This method communicates with the server by writing objects to the server and reading the response from the server. 
	 */
	public void communicate() {

		try {
			toServerOOS.writeObject(myPacket);
			myPacket = (Packet) fromServerOIS.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method closes the object input and output streams when the client disconnects from the server.
	 */
	public void closeStreams () {
		try {
			toServerOOS.close();
			fromServerOIS.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method returns the packet that contains the information of clients, actions, and queries.
	 * @return A packet that contains an array of clients, an action, and a query. 
	 */
	public Packet getPacket() {
		return myPacket;
	}

}
