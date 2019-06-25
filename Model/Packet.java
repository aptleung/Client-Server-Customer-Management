package Model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that contains information that gets sent between the client and server.
 * @author aaron
 *
 */
public class Packet implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList <Client> clientList;
	private int switchCase;
	private String query;
	
	
	/**
	 * A constructor for the packet object. Creates and empty array list of client objects.
	 */
	public Packet () {
		clientList = new ArrayList <Client>();
		query = "";
	}
	
	/**
	 * Getter for the array list of clients.
	 * @return ArrayList of Client objects
	 */
	
	public ArrayList <Client> getClientList (){
		return clientList;
	}
	
	/**
	 * Getter for the switch case. 
	 * @return An integer that represents which action to take. 
	 */
	
	public int getSwitchCase () {
		return switchCase;
	}
	
	/**
	 * Getter for the query
	 * @return A string that represents what is being searched. 
	 */
	
	public String getQuery () {
		return query;
	}
	
	/**
	 * Setter for the switch case.
	 * @param i An integer that represents which action to take. 
	 */
	
	public void setCase (int i) {
		switchCase = i;
	}
	
	/**
	 * Setter for the query.
	 * @param q A string that contains the search parameter. 
	 */
	
	public void setQuery (String q) {
		query = q;
	}
	
}
