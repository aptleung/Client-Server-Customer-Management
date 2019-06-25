package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import Model.Client;
import Model.Packet;

/**
 * This class contains methods that control how the server reads objects from clients, executes the command, and returns a package to the client. 
 * @author aaron
 *
 */
public class ServerCommsController implements Runnable {

	private ObjectOutputStream toClientOOS;
	private ObjectInputStream fromClientOIS;
	private Socket mySocket;
	private Packet myPacket;
	private DBController myDBControl;
	
	/**
	 * Constructor for Server Communications Controller. Takes a input socket and constructs the object input and output streams. 
	 * @param aSocket The socket that the client connected through
	 * @param dbc The database controller for the server. 
	 */

	public ServerCommsController(Socket aSocket, DBController dbc) {

		mySocket = aSocket;
		myPacket = new Packet();
		myDBControl = dbc;
		try {
			toClientOOS = new ObjectOutputStream(mySocket.getOutputStream());
			fromClientOIS = new ObjectInputStream(mySocket.getInputStream());
		} catch (IOException e) {
			System.out.println("Error Connecting Object Input/Output Streams");
			e.printStackTrace();
		}
	}

	/**
	 * Method that runs the thread. Continuously reads objects from the client and executes certain tasks based on what the input object contains. 
	 * Returns the object with the results once the task is executed then awaits the next command. 
	 */
	@Override
	public void run() {

		int switchID = 0;

		while (switchID != 99) {

			try {
				System.out.println("Reading from Client.");
				myPacket = (Packet) fromClientOIS.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			switchID = myPacket.getSwitchCase();

			switch (switchID) {

			case 1:
				switchID = 1;
				// Search By ID
				int id = Integer.parseInt(myPacket.getQuery());
				Client temp = myDBControl.searchClient(id);
				myPacket.getClientList().clear();
				if (temp != null) {
					myPacket.getClientList().add(temp);
				}
				break;

			case 2:
				switchID = 2;
				// Search By Last Name
				String ln = myPacket.getQuery();
				ArrayList<Client> tempArrLN = myDBControl.searchClientLastName(ln);
				myPacket.getClientList().clear();
				if (!tempArrLN.isEmpty()) {
					for (Client c : tempArrLN) {
						myPacket.getClientList().add(c);
					}
				}
				break;

			case 3:
				switchID = 3;
				// Search by client type
				String type = myPacket.getQuery();
				ArrayList<Client> tempArrType = myDBControl.searchClient(type);
				myPacket.getClientList().clear();
				if (tempArrType.isEmpty() == false) {
					for (Client c : tempArrType) {
						myPacket.getClientList().add(c);
					}
				}
				break;

			case 4:
				switchID = 4;
				// Delete Client
				int id1 = Integer.parseInt(myPacket.getQuery());
				myPacket.getClientList().clear();
				myDBControl.removeClient(id1);
				break;

			case 5:
				switchID = 5;
				// Add New Client
				Client tempClient = myPacket.getClientList().get(0);
				myDBControl.addClient(tempClient);
				break;

			case 6:
				switchID = 6;
				// Save Client
				Client tempModClient = myPacket.getClientList().get(0);
				myDBControl.modifyClient(tempModClient);
				break;
				
			default: 
				System.out.println("Closing Thread.");
				break;
				
			}

			try {
				System.out.println("Sending Back To Client.");
				toClientOOS.writeObject(myPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			toClientOOS.close();
			fromClientOIS.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
