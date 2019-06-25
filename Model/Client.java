package Model;
import java.io.Serializable;

/**
 * This class contains all the information about a client in the system. 
 * @author aaron
 *
 */
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String postalCode;
	private String phoneNumber;
	private String clientType;
	
	/**
	 * This is the constructor for a client object. 
	 * @param id An integer containing the client's unique ID number
	 * @param firstName A string representing the client's first name
	 * @param lastName A string representing the client's last name
	 * @param address A string that contains the client's address
	 * @param postalCode A string that contains the client's Postal Code
	 * @param phoneNumber A string the contrains the client's phone number
	 * @param clientType A string that represents the client type (Residential or Commercial)
	 */
	
	public Client (int id,String firstName, String lastName, String address, String postalCode, String phoneNumber, String clientType) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.clientType = clientType; 
	}
	
	/**
	 * Getter for the client ID
	 * @return The client's ID number
	 */
	
	public int getID () {
		return id;
	}
	
	/**
	 * Setter for client ID
	 * @param i The integer representing the client's ID.
	 */
	
	public void setID (int i) {
		id = i;
	}
	
	/**
	 * Getter for the client's first name
	 * @return The client's first name
	 */

	public String getFirstName () {
		return firstName;
	}
	
	/**
	 * Getter for client's last name
	 * @return The client's last name
	 */
	
	public String getLastName () {
		return lastName;
	}
	
	/**
	 * Getter for the client's address.
	 * @return The client's address
	 */
	
	public String getAddress () {
		return address;
	}
	
	/**
	 * Getter for the client's postal code
	 * @return The client's postal code
	 */
	
	public String getPostalCode () {
		return postalCode;
	}
	
	/**
	 * A getter for the client's phone number
	 * @return The client's phone number
	 */
	
	public String getPhoneNumber () {
		return phoneNumber;
	}
	
	/**
	 * A getter for the client type
	 * @return The client type.
	 */
	
	public String getClientType () {
		return clientType;
	}

	/**
	 * A method that displays the client information as a string. 
	 */
	@Override
	public String toString() {
		return id + " " + firstName + " " + lastName + " " + address + " " + postalCode + " " + phoneNumber + " " +
				   clientType;
	}
	
	/**
	 * Setter for client's first name
	 * @param fn A string that contains the client's first name
	 */
	
	public void setFirstName (String fn) {
		firstName = fn;
	}
	
	/**
	 * Setter for client's last name
	 * @param ln A string that contains the client's last name
	 */
	public void setLastName (String ln) {
		lastName = ln;
	}
	
	/**
	 * Setter for the client's address
	 * @param ad A string that contains the client's address
	 */
	
	public void setAddress (String ad) {
		address = ad;
	}
	
	/**
	 * Setter for the client's postal code
	 * @param pc A string that contains the client's postal code
	 */
	
	public void setPostalCode (String pc) {
		postalCode = pc;
	}
	
	/**
	 * Setter for the client's phone number
	 * @param ph A string that contains the client's phone number
	 */
	
	public void setPhoneNumber (String ph) {
		phoneNumber = ph;
	}
	
	/**
	 * Setter for the client's type
	 * @param ct A string that represents the client's type
	 */
	
	public void setClientType (String ct) {
		clientType = ct;
	}
	
}
