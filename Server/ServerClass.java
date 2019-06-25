package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This is the server class that runs the server for the Client Manager application. 
 * @author aaron
 *
 */
public class ServerClass {

	private ServerSocket serverSocket;
	private ExecutorService threadPool;
	private DBController dbControl;

	/**
	 * Constructor that sets up the server socket, the database controller, and a threadpool. 
	 */
	public ServerClass() {
		try {
			serverSocket = new ServerSocket(9898);
			dbControl = new DBController();
			threadPool = Executors.newFixedThreadPool(10);

		} catch (IOException e) {
			System.out.println("Error Creating New Socket..");
			System.out.println(e.getMessage());
		}
		System.out.println("Server Is Running..");
	}

	/**
	 * Runs the server infinitely. Submits new threads to run as clients connect, each running individually. 
	 */
	public void runServer() {
		
//		Uncomment out the createTable() and fillTable() methods if the table does not exist in SQL. Create the DB if needed. 
//		dbControl.createTable();
//		dbControl.fillTable();
		dbControl.printTable();
		
		while (true) {

			try {
				threadPool.submit(new ServerCommsController(serverSocket.accept(), dbControl));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws IOException {
		ServerClass myserver = new ServerClass();
		myserver.runServer();
	}

}
