package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.Client;
import Model.Packet;

/**
 * This class is the controller that handles the GUI and the communications controller for the client. 
 * @author aaron
 *
 */
public class ClientController {
	
	private ClientGUI myGUI;
	private ClientCommsController myCCC;
	private boolean continueRunning;
	
	/**
	 * This is the constructor for the client controller. It will register all the action listeners for the buttons and instantiate the 
	 * communications controller for a client. 
	 */
	
	public ClientController () {
		
		myCCC = new ClientCommsController ("localhost", 9898);
		
		myGUI = new ClientGUI();
		myGUI.initialize();
        myGUI.regClearListener(new clearListener());
        myGUI.regSaveListener(new saveListener());
        myGUI.regDeleteListener(new deleteListener());
        myGUI.clearSearchListener(new clearSearchListener());
        myGUI.regSearchListener(new searchListener());
        myGUI.regListListener(new listSelectListener());
        myGUI.regWindowListener(new windowListener());
        
        continueRunning = true;
		
		
	}
	
	/**
	 * This method enables the display of the GUI on the client side. It will keep it running until the client exits the application. 
	 */
	
	public void show () {
		myGUI.getFrame().setVisible(true);
		while (continueRunning) {
			continue;
		}
		return;
	}
	
	/**
     * A class that adds an Action Listener for search button
     */

    class searchListener implements ActionListener {
        /**
         * A method that performs an action in response to which radio button is selected
         * @param e An actionEvent which will be a button click
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (myGUI.getClientIDrdBtn().isSelected()) {
                searchByID();
            }

            if (myGUI.getClientTyperdBtn().isSelected()) {
                if (myGUI.getSearchParameterInput().getText().equals("R")) {
                    searchByType();

                } else if (myGUI.getSearchParameterInput().getText().equals("C")) {
                    searchByType();
                } else {
                    myGUI.jOptionPane("Invalid Client Type");
                }
            }

            if (myGUI.getLastNamerdBtn().isSelected()) {
                searchByLastName();
            }

        }
    }

    /**
     * A class that adds an Window Listener for closing the main window frame
     */
    class windowListener implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {
        }

        /**
         * A method that performs an exit procedure when the window frame is closed
         * @param e Represents a windowEvent
         */

        @Override
        public void windowClosing(WindowEvent e){
        	exitProcedure();
        }

        @Override
        public void windowClosed(WindowEvent e) {
        }

        @Override
        public void windowIconified(WindowEvent e) {
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
        }

        @Override
        public void windowActivated(WindowEvent e) {
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
        }
    }

    /**
     * A class that that adds a List Selection Listener
     */
    class listSelectListener implements ListSelectionListener {
        /**
         * A method that performs an action based on a list value changing
         * @param e Represents a ListSelectionEvent
         */
        public void valueChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting() == false) {
                if (((JList) e.getSource()).getSelectedIndex() >= 0) {
                    Client myC = (Client) ((JList) e.getSource()).getSelectedValue();
                    populateClientInfoFields(myC);
                    myGUI.getDeleteBtn().setEnabled(true);
                }
            }
        }
    }

    /**
     * A class that adds an Action Listener to the clear button
     */

    class clearListener implements ActionListener {
        /**
         * A method that clears all textfields if clear button is selected
         * @param e Represents an Action Event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            myGUI.clearTextFieldsClientInfo();
        }
    }

    /**
     * A class that adds an Action Listener to the save button
     */

    class saveListener implements ActionListener {
        /**
         * A method that adds or removes client data if save button is clicked
         * @param arg0 Represents an Action Event
         */
        @Override
        public void actionPerformed(ActionEvent arg0) {
			Client myC = createFromClientInformation();
			
			if (myC != null) {
				
				myCCC.getPacket().setCase(1);
		    	myCCC.getPacket().setQuery(String.valueOf(myC.getID()));
		    	myCCC.communicate();
		    	
		    	ArrayList <Client> tempList = myCCC.getPacket().getClientList();
		    	if (tempList.isEmpty()) {
		    		myCCC.getPacket().setCase(5);
		    		myCCC.getPacket().getClientList().clear();
		    		myCCC.getPacket().getClientList().add(myC);
		    		myCCC.communicate();
		    		
		    		myGUI.jOptionPane("Successfully Added");
					myGUI.clearTextFieldsClientInfo();
		    	}
				else {
					String ask = "Client: " + myC.getID() + " already exists. " +
							"Select 'No' to choose another ID. Select 'Yes' to overrwrite this client's information";
					
					if (myGUI.confirm(ask)) {
						myCCC.getPacket().setCase(6);
			    		myCCC.getPacket().getClientList().clear();
			    		myCCC.getPacket().getClientList().add(myC);
			    		myCCC.communicate();
						myGUI.jOptionPane("Successfully Modified");
						myGUI.clearTextFieldsClientInfo();
					}	
				}
			}
		}
    }

    /**
     * A class that adds an action listener to delete button
     */

    class deleteListener implements ActionListener {
        /**
         * A method that calls on remove client class when delete button is clicked
         * @param e Represents an action event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            removeClient();
        }
    }

    /**
     * A class that adds an Action Listener to clear search button
     */

    class clearSearchListener implements ActionListener {
        /**
         * A method that clears textfields when clear search button is clicked
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            myGUI.clearTextFieldsClientInfo();
            myGUI.getSearchParameterInput().setText("");
            myGUI.getModel().removeAllElements();
        }
    }

    //NON-SQL METHODS

    /**
     * A method used to remove clients from the database and changes the view
     */
	public void removeClient() {

		myCCC.getPacket().setCase(1);
		myCCC.getPacket().setQuery(myGUI.getClientIDinput().getText());
		myCCC.communicate();

		ArrayList<Client> tempList = myCCC.getPacket().getClientList();
		if (tempList.isEmpty()) {
			myGUI.jOptionPane("This Client Does Not Exist..");
		} 
		else {
			if (myGUI.confirm("Are you sure you want to delete this client?")) {
				myCCC.getPacket().setCase(4);
				myCCC.getPacket().setQuery(myGUI.getClientIDinput().getText());
				myCCC.communicate();

				myGUI.jOptionPane("Client Removed");
				myGUI.clearTextFieldsClientInfo();
				myGUI.getDeleteBtn().setEnabled(false);
				myGUI.getModel().removeAllElements();
				myGUI.getSearchBtn().doClick();
			}
		}
    }

    /**
     * A method that creates a client based on data in the textfields
     * @return A null or client
     */
    public Client createFromClientInformation () {
    	try {
			int id = Integer.parseInt(myGUI.getClientIDinput().getText());
			String fName = myGUI.getFirstNameInput().getText();
			String lName = myGUI.getLastNameInput().getText();
			String address = myGUI.getAddressInput().getText();
			String pc = myGUI.getPostalCodeInput().getText();
			String phoneNum = myGUI.getPhoneNumInput().getText();
			String type = "X";
			if (myGUI.getClientTypeInput().getSelectedItem().toString().equals("Residential")){
				type = "R";
			}
			else if (myGUI.getClientTypeInput().getSelectedItem().toString().equals("Commercial")) {
				type = "C";
			}
			Client myClient = new Client (id,fName,lName,address,pc,phoneNum,type);
			return myClient;
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Invalid ID. ID's must be a 4 digit numerical value.");
			myGUI.getClientIDinput().setText("");
			return null;
		}
    }

    /**
     * A method that populates all the textfields with client information
     * @param myC Represents a client
     */

    public void populateClientInfoFields (Client myC) {
        myGUI.getClientIDinput().setText(String.valueOf(myC.getID()));
        myGUI.getFirstNameInput().setText(myC.getFirstName());
        myGUI.getLastNameInput().setText(myC.getLastName());
        myGUI.getAddressInput().setText(myC.getAddress());
        myGUI.getPostalCodeInput().setText(myC.getPostalCode());
        myGUI.getPhoneNumInput().setText(myC.getPhoneNumber());
        if (myC.getClientType().equals("R")) {
            myGUI.getClientTypeInput().setSelectedIndex(1);
        } else {
            myGUI.getClientTypeInput().setSelectedIndex(2);
        }
    }

    /**
     * A method that searches for clients based on last names
     */

    public void searchByLastName() { 
    	
    	myCCC.getPacket().setCase(2);
    	
    	if (!myGUI.getSearchParameterInput().getText().equals("")) {
    		myCCC.getPacket().setQuery(myGUI.getSearchParameterInput().getText());
    		myCCC.communicate();
    	}
    	else {
    		myGUI.jOptionPane("Enter A Last Name to Search..");
    		return;
    	}
    
    	ArrayList <Client> tempList = myCCC.getPacket().getClientList();	
    	if (!tempList.isEmpty()) {
    		for (Client c : tempList) {
    			myGUI.getModel().addElement(c);
    		}
    	}
    	else {
    		myGUI.jOptionPane("No Records Found");
    	}
    }

    /**
     * A method that searches for clients based on type
     */

    public void searchByType() {
    	
    	myCCC.getPacket().setCase(3);
    	
    	if (!myGUI.getSearchParameterInput().getText().equals("")) {
    		myCCC.getPacket().setQuery(myGUI.getSearchParameterInput().getText());
    		myCCC.communicate();
    	}
    	else {
    		myGUI.jOptionPane("Invalid Type..");
    		return;
    	}
    	
    	ArrayList <Client> tempList = myCCC.getPacket().getClientList();
    	if (!tempList.isEmpty()) {
    		for (Client c : tempList) {
    			myGUI.getModel().addElement(c);
    		}
    	}
    	else {
    		myGUI.jOptionPane("No Records Found");
    	}
    }
 	
    /**
     * A method that searches by client ID
     */
    public void searchByID () {
    	
    	myCCC.getPacket().setCase(1);
    	
    	if (!myGUI.getSearchParameterInput().getText().equals("")) {
    		try {
    			int id = Integer.parseInt(myGUI.getSearchParameterInput().getText());
    			myCCC.getPacket().setQuery(myGUI.getSearchParameterInput().getText());
    			myCCC.communicate();
    		} catch (NumberFormatException e) {
    			myGUI.jOptionPane("Invalid ID. Must be a number..");
    			return;
    		}
    	}
    	else {
    		myGUI.jOptionPane("Invalid ID");
    		return;
    	}
    	
    	ArrayList <Client> tempList = myCCC.getPacket().getClientList();
    	if (tempList.isEmpty()) {
    		myGUI.jOptionPane("No Record of ID");
    	}
    	else {
    		Client myC = tempList.get(0);
    		myGUI.getModel().addElement(myC);
    	}
    }

    /**
     * A method that runs when the window is closed
     */

    public void exitProcedure() {
        myCCC.getPacket().setCase(99);
        myCCC.communicate();
        myCCC.closeStreams();
        
    	continueRunning = false;
    }
    
    public static void main (String [] args) {
    	ClientController myCVC = new ClientController();
    	myCVC.show();
    }
	
	

}
