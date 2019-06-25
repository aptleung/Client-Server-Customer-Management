package Client;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.Client;

/**
 * This class contains the information used to construct the GUI for the client manager application.
 * @author aaron
 *
 */

public class ClientGUI {

	private JFrame frame;
	private JTextField searchParameterInput;
	private JTextField clientIDinput;
	private JTextField firstNameInput;
	private JTextField lastNameInput;
	private JTextField addressInput;
	private JTextField postalCodeInput;
	private JTextField phoneNumInput;
	private JComboBox clientTypeInput;
	private JSplitPane splitPaneOne;
	private JPanel northPanel;
	private JLabel screenLabel;
	private JPanel bottomleftpanel;
	private JSplitPane leftpanel;
	private JPanel topleftpanel;
	private JLabel lblSearchClients;
	private JLabel lblSelectType;
	private JRadioButton clientIDrdBtn;
	private JRadioButton lastNamerdBtn;
	private JRadioButton clientTyperdBtn;
	private JButton saveBtn;
	private ButtonGroup radioButtons;
	private JButton searchBtn;
	private JButton clearBtn;
	private JButton clearSearchBtn;
	private JScrollPane scrollPane;
	private JButton deleteBtn;
	private DefaultListModel <Client> model;
	private JList resultsList;
	private JPanel lblAddress;

	/**
	 * Create the view by calling on method initialize
	 */
	public ClientGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 578);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		northPanel = new JPanel();
		frame.getContentPane().add(northPanel, BorderLayout.NORTH);

		screenLabel = new JLabel("Client Management Screen");
		screenLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		northPanel.add(screenLabel);

		splitPaneOne = new JSplitPane();
		splitPaneOne.setResizeWeight(0.5);
		frame.getContentPane().add(splitPaneOne, BorderLayout.CENTER);

		leftpanel = new JSplitPane();
		leftpanel.setResizeWeight(0.5);
		leftpanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPaneOne.setLeftComponent(leftpanel);

		topleftpanel = new JPanel();
		leftpanel.setLeftComponent(topleftpanel);
		GridBagLayout gbl_topleftpanel = new GridBagLayout();
		gbl_topleftpanel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_topleftpanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_topleftpanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_topleftpanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		topleftpanel.setLayout(gbl_topleftpanel);

		lblSearchClients = new JLabel("Search Clients");
		lblSearchClients.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblSearchClients = new GridBagConstraints();
		gbc_lblSearchClients.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearchClients.gridx = 0;
		gbc_lblSearchClients.gridy = 0;
		topleftpanel.add(lblSearchClients, gbc_lblSearchClients);

		lblSelectType = new JLabel("Select Type of Search to be Performed:");
		lblSelectType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblSelectType = new GridBagConstraints();
		gbc_lblSelectType.anchor = GridBagConstraints.WEST;
		gbc_lblSelectType.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectType.gridx = 0;
		gbc_lblSelectType.gridy = 2;
		topleftpanel.add(lblSelectType, gbc_lblSelectType);

		clientIDrdBtn = new JRadioButton("Client ID");
		GridBagConstraints gbc_clientIDrdBtn = new GridBagConstraints();
		gbc_clientIDrdBtn.anchor = GridBagConstraints.WEST;
		gbc_clientIDrdBtn.insets = new Insets(0, 0, 5, 5);
		gbc_clientIDrdBtn.gridx = 0;
		gbc_clientIDrdBtn.gridy = 3;
		topleftpanel.add(clientIDrdBtn, gbc_clientIDrdBtn);

		lastNamerdBtn = new JRadioButton("Last Name");
		GridBagConstraints gbc_lastNamerdBtn = new GridBagConstraints();
		gbc_lastNamerdBtn.anchor = GridBagConstraints.WEST;
		gbc_lastNamerdBtn.insets = new Insets(0, 0, 5, 5);
		gbc_lastNamerdBtn.gridx = 0;
		gbc_lastNamerdBtn.gridy = 4;
		topleftpanel.add(lastNamerdBtn, gbc_lastNamerdBtn);

		clientTyperdBtn = new JRadioButton("Client Type: Commerical (C) or Residential (R)");
		GridBagConstraints gbc_clientTyperdBtn = new GridBagConstraints();
		gbc_clientTyperdBtn.anchor = GridBagConstraints.WEST;
		gbc_clientTyperdBtn.insets = new Insets(0, 0, 5, 5);
		gbc_clientTyperdBtn.gridx = 0;
		gbc_clientTyperdBtn.gridy = 5;
		topleftpanel.add(clientTyperdBtn, gbc_clientTyperdBtn);

		JLabel lblNewLabel_1 = new JLabel("Enter the Search Parameter below:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 6;
		topleftpanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		searchParameterInput = new JTextField();
		GridBagConstraints gbc_searchParameterInput = new GridBagConstraints();
		gbc_searchParameterInput.insets = new Insets(0, 0, 5, 5);
		gbc_searchParameterInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchParameterInput.gridx = 0;
		gbc_searchParameterInput.gridy = 7;
		topleftpanel.add(searchParameterInput, gbc_searchParameterInput);
		searchParameterInput.setColumns(20);

		searchBtn = new JButton("Search");
		GridBagConstraints gbc_searchBtn = new GridBagConstraints();
		gbc_searchBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchBtn.insets = new Insets(0, 0, 5, 5);
		gbc_searchBtn.gridx = 0;
		gbc_searchBtn.gridy = 8;
		topleftpanel.add(searchBtn, gbc_searchBtn);

		clearSearchBtn = new JButton("Clear Search");
		GridBagConstraints gbc_clearSearchBtn = new GridBagConstraints();
		gbc_clearSearchBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_clearSearchBtn.insets = new Insets(0, 0, 0, 5);
		gbc_clearSearchBtn.gridx = 0;
		gbc_clearSearchBtn.gridy = 9;
		topleftpanel.add(clearSearchBtn, gbc_clearSearchBtn);

		bottomleftpanel = new JPanel();
		leftpanel.setRightComponent(bottomleftpanel);
		GridBagLayout gbl_bottomleftpanel = new GridBagLayout();
		gbl_bottomleftpanel.columnWidths = new int[]{0, 0};
		gbl_bottomleftpanel.rowHeights = new int[]{0, 187, 0};
		gbl_bottomleftpanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_bottomleftpanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		bottomleftpanel.setLayout(gbl_bottomleftpanel);

		JLabel lblSearchResults = new JLabel("Search Results:");
		lblSearchResults.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblSearchResults = new GridBagConstraints();
		gbc_lblSearchResults.anchor = GridBagConstraints.WEST;
		gbc_lblSearchResults.insets = new Insets(0, 0, 5, 0);
		gbc_lblSearchResults.gridx = 0;
		gbc_lblSearchResults.gridy = 0;
		bottomleftpanel.add(lblSearchResults, gbc_lblSearchResults);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		bottomleftpanel.add(scrollPane, gbc_scrollPane);

		model = new DefaultListModel();
		resultsList = new JList ();
		resultsList.setModel(model);
		scrollPane.setViewportView(resultsList);

		JPanel lblAddress = new JPanel();
		splitPaneOne.setRightComponent(lblAddress);
		GridBagLayout gbl_lblAddress = new GridBagLayout();
		gbl_lblAddress.columnWidths = new int[]{0, 0, 54, 0, 0};
		gbl_lblAddress.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_lblAddress.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_lblAddress.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		lblAddress.setLayout(gbl_lblAddress);

		JLabel lblClientInformation = new JLabel("Client Information");
		lblClientInformation.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblClientInformation = new GridBagConstraints();
		gbc_lblClientInformation.gridwidth = 4;
		gbc_lblClientInformation.insets = new Insets(0, 0, 5, 5);
		gbc_lblClientInformation.gridx = 0;
		gbc_lblClientInformation.gridy = 0;
		lblAddress.add(lblClientInformation, gbc_lblClientInformation);

		JLabel lblClientID = new JLabel("Client ID: ");
		GridBagConstraints gbc_lblClientID = new GridBagConstraints();
		gbc_lblClientID.insets = new Insets(0, 0, 5, 5);
		gbc_lblClientID.anchor = GridBagConstraints.EAST;
		gbc_lblClientID.gridx = 0;
		gbc_lblClientID.gridy = 2;
		lblAddress.add(lblClientID, gbc_lblClientID);

		clientIDinput = new JTextField();
		GridBagConstraints gbc_clientIDinput = new GridBagConstraints();
		gbc_clientIDinput.gridwidth = 2;
		gbc_clientIDinput.insets = new Insets(0, 0, 5, 5);
		gbc_clientIDinput.fill = GridBagConstraints.HORIZONTAL;
		gbc_clientIDinput.gridx = 1;
		gbc_clientIDinput.gridy = 2;
		lblAddress.add(clientIDinput, gbc_clientIDinput);
		clientIDinput.setColumns(10);
		clientIDinput.setDocument(new JTextFieldLimit(4));

		JLabel lblFirstName = new JLabel("First Name: ");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.fill = GridBagConstraints.VERTICAL;
		gbc_lblFirstName.anchor = GridBagConstraints.EAST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 4;
		lblAddress.add(lblFirstName, gbc_lblFirstName);

		firstNameInput = new JTextField();
		GridBagConstraints gbc_firstNameInput = new GridBagConstraints();
		gbc_firstNameInput.gridwidth = 2;
		gbc_firstNameInput.insets = new Insets(0, 0, 5, 5);
		gbc_firstNameInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_firstNameInput.gridx = 1;
		gbc_firstNameInput.gridy = 4;
		lblAddress.add(firstNameInput, gbc_firstNameInput);
		firstNameInput.setColumns(20);
		firstNameInput.setDocument(new JTextFieldLimit(20));

		JLabel lblLastName = new JLabel("Last Name: ");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.EAST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 0;
		gbc_lblLastName.gridy = 6;
		lblAddress.add(lblLastName, gbc_lblLastName);

		lastNameInput = new JTextField();
		GridBagConstraints gbc_lastNameInput = new GridBagConstraints();
		gbc_lastNameInput.gridwidth = 2;
		gbc_lastNameInput.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_lastNameInput.gridx = 1;
		gbc_lastNameInput.gridy = 6;
		lblAddress.add(lastNameInput, gbc_lastNameInput);
		lastNameInput.setColumns(20);
		lastNameInput.setDocument(new JTextFieldLimit(20));

		JLabel lblNewLabel_5 = new JLabel("Address: ");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 8;
		lblAddress.add(lblNewLabel_5, gbc_lblNewLabel_5);

		addressInput = new JTextField();
		GridBagConstraints gbc_addressInput = new GridBagConstraints();
		gbc_addressInput.gridwidth = 2;
		gbc_addressInput.insets = new Insets(0, 0, 5, 5);
		gbc_addressInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressInput.gridx = 1;
		gbc_addressInput.gridy = 8;
		lblAddress.add(addressInput, gbc_addressInput);
		addressInput.setColumns(20);
		addressInput.setDocument(new JTextFieldLimit(50));

		JLabel lblPostalCode = new JLabel("Postal Code: ");
		GridBagConstraints gbc_lblPostalCode = new GridBagConstraints();
		gbc_lblPostalCode.anchor = GridBagConstraints.EAST;
		gbc_lblPostalCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostalCode.gridx = 0;
		gbc_lblPostalCode.gridy = 10;
		lblAddress.add(lblPostalCode, gbc_lblPostalCode);

		postalCodeInput = new JTextField();
		GridBagConstraints gbc_postalCodeInput = new GridBagConstraints();
		gbc_postalCodeInput.gridwidth = 2;
		gbc_postalCodeInput.insets = new Insets(0, 0, 5, 5);
		gbc_postalCodeInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_postalCodeInput.gridx = 1;
		gbc_postalCodeInput.gridy = 10;
		lblAddress.add(postalCodeInput, gbc_postalCodeInput);
		postalCodeInput.setColumns(10);
		postalCodeInput.setDocument(new JTextFieldLimit(7));

		JLabel lblPhoneNum = new JLabel("Phone Number: ");
		GridBagConstraints gbc_lblPhoneNum = new GridBagConstraints();
		gbc_lblPhoneNum.anchor = GridBagConstraints.EAST;
		gbc_lblPhoneNum.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhoneNum.gridx = 0;
		gbc_lblPhoneNum.gridy = 12;
		lblAddress.add(lblPhoneNum, gbc_lblPhoneNum);

		phoneNumInput = new JTextField();
		GridBagConstraints gbc_phoneNumInput = new GridBagConstraints();
		gbc_phoneNumInput.gridwidth = 2;
		gbc_phoneNumInput.insets = new Insets(0, 0, 5, 5);
		gbc_phoneNumInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneNumInput.gridx = 1;
		gbc_phoneNumInput.gridy = 12;
		lblAddress.add(phoneNumInput, gbc_phoneNumInput);
		phoneNumInput.setColumns(10);
		phoneNumInput.setDocument(new JTextFieldLimit(12));

		JLabel lblClientType = new JLabel("Client Type: ");
		GridBagConstraints gbc_lblClientType = new GridBagConstraints();
		gbc_lblClientType.anchor = GridBagConstraints.EAST;
		gbc_lblClientType.insets = new Insets(0, 0, 5, 5);
		gbc_lblClientType.gridx = 0;
		gbc_lblClientType.gridy = 14;
		lblAddress.add(lblClientType, gbc_lblClientType);

		clientTypeInput = new JComboBox();
		clientTypeInput.setModel(new DefaultComboBoxModel(new String[] {"", "Residential", "Commercial"}));
		GridBagConstraints gbc_clientTypeInput = new GridBagConstraints();
		gbc_clientTypeInput.insets = new Insets(0, 0, 5, 5);
		gbc_clientTypeInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_clientTypeInput.gridx = 1;
		gbc_clientTypeInput.gridy = 14;
		lblAddress.add(clientTypeInput, gbc_clientTypeInput);

		deleteBtn = new JButton("Delete");
		GridBagConstraints gbc_deleteBtn = new GridBagConstraints();
		gbc_deleteBtn.anchor = GridBagConstraints.EAST;
		gbc_deleteBtn.insets = new Insets(0, 0, 5, 5);
		gbc_deleteBtn.gridx = 0;
		gbc_deleteBtn.gridy = 16;
		lblAddress.add(deleteBtn, gbc_deleteBtn);
		deleteBtn.setEnabled(false);


		saveBtn = new JButton("Save");
		GridBagConstraints gbc_saveBtn = new GridBagConstraints();
		gbc_saveBtn.insets = new Insets(0, 0, 5, 5);
		gbc_saveBtn.gridx = 1;
		gbc_saveBtn.gridy = 16;
		lblAddress.add(saveBtn, gbc_saveBtn);

		clearBtn = new JButton("Clear");
		GridBagConstraints gbc_clearBtn = new GridBagConstraints();
		gbc_clearBtn.insets = new Insets(0, 0, 5, 5);
		gbc_clearBtn.gridx = 2;
		gbc_clearBtn.gridy = 16;
		lblAddress.add(clearBtn, gbc_clearBtn);

		radioButtons = new ButtonGroup();
		radioButtons.add(clientTyperdBtn);
		radioButtons.add(lastNamerdBtn);
		radioButtons.add(clientIDrdBtn);
	}

	//clearBtn, saveBtn, deleteBtn, clearSearchBtn

	/**
	 * Adds an action listener to search button
	 * @param ac Represents an action listener
	 */
	void regSearchListener (ActionListener ac){
		searchBtn.addActionListener(ac);
	}

	/**
	 * Adds an action listener to clear button
	 * @param ac Represents an action listener
	 */
	void regClearListener (ActionListener ac){
		clearBtn.addActionListener(ac);
	}

	/**
	 * Adds an action listener to save button
	 * @param ac Represents an action listener
	 */
	void regSaveListener (ActionListener ac){
		saveBtn.addActionListener(ac);
	}

	/**
	 * Adds an action listener to delete button
	 * @param ac Represents an action listener
	 */
	void regDeleteListener (ActionListener ac){
		deleteBtn.addActionListener(ac);
	}

	/**
	 * Adds an action listener to clear search button
	 * @param ac Represents an action listener
	 */
	void clearSearchListener (ActionListener ac){
		clearSearchBtn.addActionListener(ac);
	}

	/**
	 * Adds an action listener to the list
	 * @param lc Represents a list selection listener
	 */
	void regListListener(ListSelectionListener lc){
		resultsList.addListSelectionListener(lc);

	}

	/**
	 * Adds a window listener to the main window
	 * @param wc Represents a window listener
	 */
	void regWindowListener(WindowListener wc){
		frame.addWindowListener(wc);
	}

	/**
	 * This method clears all client textfields
	 */

	public void clearTextFieldsClientInfo() {
		clientIDinput.setText("");
		firstNameInput.setText("");
		lastNameInput.setText("");
		addressInput.setText("");
		postalCodeInput.setText("");
		phoneNumInput.setText("");
		clientTypeInput.setSelectedIndex(0);
	}

	/**
	 * This method is used to display JOptionPane messages
	 * @param s Represents a string
	 */
	public void jOptionPane (String s){
		JOptionPane.showMessageDialog(null, s);
	}

	/**
	 * This method represents whether the user selected yes or no
	 * @param s Represents a string
	 * @return A boolean that contains user's answer
	 */

	public boolean confirm(String s){
		boolean answer = false;
		int reply = JOptionPane.showConfirmDialog(null, s, "Confirmation", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			answer = true;
		}
		return answer;
	}

	/**
	 * A method to get the frame
	 * @return A JFrame that contains the main frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * A method to get the Search Parameter input
	 * @return A JTextFIeld that contains search parameter input
	 */

	public JTextField getSearchParameterInput() {
		return searchParameterInput;
	}

	/**
	 * A method to get the client ID input
	 * @return A JTextField that contains client ID
	 */
	public JTextField getClientIDinput() {
		return clientIDinput;
	}

	/**
	 * A method to get the first name input
	 * @return A JTextField containing first name
	 */
	public JTextField getFirstNameInput() {
		return firstNameInput;
	}

	/**
	 * A method that gets last name input
	 * @return A JTextField that contains last name
	 */
	public JTextField getLastNameInput() {
		return lastNameInput;
	}

	/**
	 * A method that gets address input
	 * @return A JTextField that contains address input
	 */
	public JTextField getAddressInput() {
		return addressInput;
	}

	/**
	 * A method that gets the postal code input
	 * @return A JTextField that contains postal code
	 */
	public JTextField getPostalCodeInput() {
		return postalCodeInput;
	}

	/**
	 * A method that gets phone nubmer input
	 * @return A JTextField representing phone number
	 */
	public JTextField getPhoneNumInput() {
		return phoneNumInput;
	}

	/**
	 * A method that gets the client type box
	 * @return A JComboBox containing client type
	 */
	public JComboBox getClientTypeInput() {
		return clientTypeInput;
	}

	/**
	 * A method that gets the client ID radio button
	 * @return A JRadioButton containing client ID radio button
	 */
	public JRadioButton getClientIDrdBtn() {
		return clientIDrdBtn;
	}

	/**
	 * A method that gets the last name radio button
	 * @return A JRadioButton containing last name radio button
	 */
	public JRadioButton getLastNamerdBtn() {
		return lastNamerdBtn;
	}

	/**
	 * A method that gets client type radio button
	 * @return A JRadioButton containing client type radio button
	 */
	public JRadioButton getClientTyperdBtn() {
		return clientTyperdBtn;
	}

	/**
	 * A method that gets search button
	 * @return A JButton containing search button
	 */
	public JButton getSearchBtn() {
		return searchBtn;
	}

	/**
	 * A method that gets delete button
	 * @return A JButton containing delete button
	 */

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	/**
	 * A method that gets the client list model
	 * @return A DefaultListModel containing client list model
	 */
	public DefaultListModel<Client> getModel() {
		return model;
	}
	
	
}
