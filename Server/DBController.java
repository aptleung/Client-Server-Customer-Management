package Server;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.Client;

public class DBController {
	
	public Connection jdbc_connection;
	public PreparedStatement statement;
	public String databaseName = "clientdb", tableName = "ClientTable", dataFile = "clients.txt";
	//TA May have to change the login and password for this to work with their own SQL setup
	public String connectionInfo = "jdbc:mysql://localhost:3306/clientdb?verifyServerCertificate=false&useSSL=true",
			  login          = "root",
			  password       = "ENSF619";


	public DBController() {
		try {
			// If this throws an error, make sure you have added the mySQL connector JAR to the project
			Class.forName("com.mysql.cj.jdbc.Driver");

			// If this fails make sure your connectionInfo and login/password are correct
			jdbc_connection = DriverManager.getConnection(connectionInfo, login, password);
			System.out.println("Connected to: " + connectionInfo + "\n");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * Creates an empty database in SQL
     */

	public void createDB() {
		try {
			statement = jdbc_connection.prepareStatement("CREATE DATABASE" + databaseName);
			statement.executeUpdate("CREATE DATABASE " + databaseName);
			System.out.println("Created Database " + databaseName);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * Creates a table within SQL's database
     */

	public void createTable() {
		String sql = "CREATE TABLE " + tableName + "(" + "ID INT(4) NOT NULL AUTO_INCREMENT, "
				+ "FIRSTNAME VARCHAR(20) NOT NULL, " + "LASTNAME VARCHAR(20) NOT NULL, "
				+ "ADDRESS VARCHAR(50) NOT NULL, " + "POSTALCODE CHAR(7) NOT NULL, " + "PHONENUMBER CHAR(12) NOT NULL, "
				+ "CLIENTTYPE CHAR(1) NOT NULL, " + "PRIMARY KEY ( id ))";
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();
			System.out.println("Created Table " + tableName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    /**
     * This method is used to add a client in SQL
     * @param client Represents a client
     */

	public synchronized void addClient(Client client) {

		String sql = "INSERT INTO " + tableName
				+ " (id, firstname, lastname, address, postalCode, phoneNumber, clientType) values (?,?,?,?,?,?,?)";
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, client.getID());
			statement.setString(2, client.getFirstName());
			statement.setString(3, client.getLastName());
			statement.setString(4, client.getAddress());
			statement.setString(5, client.getPostalCode());
			statement.setString(6, client.getPhoneNumber());
			statement.setString(7, client.getClientType());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to initially add clients to the SQL table and prevents duplicate client IDs
	 * @param client Represents a client
	 */
	public void addClientInitial(Client client) {

		String sql = "INSERT INTO " + tableName
				+ " (id, firstname, lastname, address, postalCode, phoneNumber, clientType) values (?,?,?,?,?,?,?)";
		while (true) {
			if (searchClient(client.getID()) == null) {
				try {
					statement = jdbc_connection.prepareStatement(sql);
					statement.setInt(1, client.getID());
					statement.setString(2, client.getFirstName());
					statement.setString(3, client.getLastName());
					statement.setString(4, client.getAddress());
					statement.setString(5, client.getPostalCode());
					statement.setString(6, client.getPhoneNumber());
					statement.setString(7, client.getClientType());
					statement.executeUpdate();
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else{
					client.setID(generateRandomID());
			}
		}
	}

    /**
     * This method populates all clients in a file with a unique random ID
     */

	public void fillTable() {
		// THE FILL TABLE METHOD POPULATES ALL CLIENTS IN FILE WITH A UNIQUE RANDOM ID
		// BETWEEN 0-9999 AS
		// ID WAS NOT PROVIDED. AFTER INITIAL POPULATING, USERS CAN ADD W/E ID THEY WANT
		// AS LONG AS IT IS AVAILABLE.
		try {
			Scanner sc = new Scanner(new FileReader(dataFile));
			while (sc.hasNext()) {
				String clientInfo[] = sc.nextLine().split(";");
				String fName = clientInfo[0];
				String lName = clientInfo[1];
				String address = clientInfo[2];
				String postCode = clientInfo[3];
				String phNum = clientInfo[4];
				String type = clientInfo[5];
				int id = generateRandomID();
				Client temp = new Client(id, fName, lName, address, postCode, phNum, type);
				addClientInitial(temp);

			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("File " + dataFile + " Not Found!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    /**
     * This method prints the contents of the table to the console
     */

	public void printTable() {
		try {
			String sql = "SELECT * FROM " + tableName;
			statement = jdbc_connection.prepareStatement(sql);
			ResultSet clients = statement.executeQuery(sql);
			System.out.println("Clients:");
			while (clients.next()) {
				System.out.println(clients.getInt("id") + " " + clients.getString("firstname") + " "
						+ clients.getString("lastname") + " " + clients.getString("address") + " "
						+ clients.getString("postalCode") + " " + clients.getString("phoneNumber") + " "
						+ clients.getString("clientType"));
			}
			clients.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    /**
     * This method is used to modify client information in SQL
     * @param client Represents a client
     */

	public synchronized void modifyClient(Client client) {

		String sql = "UPDATE " + tableName
				+ " SET FIRSTNAME=?, LASTNAME=?, ADDRESS=?, POSTALCODE=?, PHONENUMBER=?, CLIENTTYPE=?" + " WHERE ID=?";
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setString(1, client.getFirstName());
			statement.setString(2, client.getLastName());
			statement.setString(3, client.getAddress());
			statement.setString(4, client.getPostalCode());
			statement.setString(5, client.getPhoneNumber());
			statement.setString(6, client.getClientType());
			statement.setInt(7, client.getID());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    /**
     * This method is used to search for a client using a specified ID
     * @param id An int representing a client ID
     * @return a null if client does not exist
     */

	public Client searchClient(int id) {
		String sql = "SELECT * FROM " + tableName + " WHERE ID = ?";
		ResultSet clientList;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, id);
			clientList = statement.executeQuery();

			if (clientList.next()) {
				return new Client(clientList.getInt("ID"), clientList.getString("FIRSTNAME"),
						clientList.getString("LASTNAME"), clientList.getString("ADDRESS"),
						clientList.getString("POSTALCODE"), clientList.getString("PHONENUMBER"),
						clientList.getString("CLIENTTYPE"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

    /**
     * This method searches through the SQL database based on client type and
	 * returns a list of all clients matching that type
     * @param type A string representing client type
     * @return An Arraylist of clients
     */

	public ArrayList<Client> searchClient(String type) {
		String sql = "SELECT * FROM " + tableName + " WHERE CLIENTTYPE = ?";
		ResultSet clientList;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setString(1, type);
			clientList = statement.executeQuery();
			ArrayList<Client> tempList = new ArrayList<Client>();
			while (clientList.next()) {
				tempList.add(new Client(clientList.getInt("ID"), clientList.getString("FIRSTNAME"),
						clientList.getString("LASTNAME"), clientList.getString("ADDRESS"),
						clientList.getString("POSTALCODE"), clientList.getString("PHONENUMBER"),
						clientList.getString("CLIENTTYPE")));
			}

			return tempList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * A method that deletes clients based on ID in the SQL database
	 * @param id An int representing client ID
	 */
	public synchronized void removeClient(int id) {

		String sql = "DELETE FROM " + tableName + " WHERE ID = ?";
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * A method that searches SQL database based on client last name and returns a list of clients
	 * @param lastName A string representing a client's last name
	 * @return A list of clients with specified last name
	 */

	public ArrayList<Client> searchClientLastName(String lastName) {
		String sql = "SELECT * FROM " + tableName + " WHERE LASTNAME = ?";
		ResultSet clientList;
		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.setString(1, lastName);
			clientList = statement.executeQuery();
			ArrayList<Client> tempList = new ArrayList<Client>();
			while (clientList.next()) {
				tempList.add(new Client(clientList.getInt("ID"), clientList.getString("FIRSTNAME"),
						clientList.getString("LASTNAME"), clientList.getString("ADDRESS"),
						clientList.getString("POSTALCODE"), clientList.getString("PHONENUMBER"),
						clientList.getString("CLIENTTYPE")));
			}

			return tempList;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * This method removes the table in the SQL database
	 */
	public void removeTable()

	{
		String sql = "DROP TABLE " + tableName;

		try {
			statement = jdbc_connection.prepareStatement(sql);
			statement.executeUpdate();

			System.out.println("Removed Table " + tableName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method generates a random client ID number between 0 and 9999
	 * @return An int representing client ID
	 */
	public int generateRandomID() {
		RandomGen id = new RandomGen();
		int clientID = id.discrete(0, 9999);
		while (searchClient(clientID) != null) {
			clientID = id.discrete(0, 9999);
		}
		return clientID;
	}

}
