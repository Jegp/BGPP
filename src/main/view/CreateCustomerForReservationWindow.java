package main.view;

import java.awt.*;

import javax.swing.*;

import main.model.Customer;
import main.util.Log;

public class CreateCustomerForReservationWindow extends JFrame {
	
	private final JLabel textFirstName = new JLabel("First name:");
	private final JLabel textLastName  = new JLabel("Last name:");
	private final JLabel textEmail	   = new JLabel("Email:");
	private final JLabel textPhone     = new JLabel("Phone number:");
	private final JLabel textAddress   = new JLabel("Address:");
	private JLabel textHelpTip;
	
	private String firstNameInput;
	private String lastNameInput;
	private String emailInput;
	private String phoneInput;
	private String adressInput;
	
	/**
	 * The field which contains information about the first name.
	 */
	public final JTextField fieldFirstName;
	
	/**
	 * The field which contains information about the last name.
	 */
	public final JTextField fieldLastName;
	
	/**
	 * The field which contains information about the email.
	 */
	public final JTextField fieldEmail;
	
	/**
	 * The field which contains information about the phone.
	 */
	public final JTextField fieldPhone;
	
	/**
	 * The field which contains information about the address.
	 */
	public final JTextArea fieldAddress;
	
	/**
	 * The button which submits the information from the view. Used in both search
	 * and edit mode.
	 */
	public final JButton buttonSubmit;
	
	/**
	 * The button which closes the screen and deletes the information in the window.
	 */
	public final CancelButton buttonCancel;
	
	/**
	 * The id of the customer.
	 */
	public final int customerId;
	
	/**
	 * Create a new window that searches for a given customer.
	 */
	public CreateCustomerForReservationWindow() {
		// TODO Auto-generated constructor stub
		// Set the title
		setTitle("Create a customer");
		
		// Set the elements to empty fields
		fieldFirstName	= new JTextField("");
		fieldLastName	= new JTextField("");
		fieldEmail		= new JTextField("");
		fieldPhone		= new JTextField("");
		fieldAddress	= new JTextArea("");
		customerId		= 0;
		
		// Create buttons
		buttonSubmit = new JButton("Create");
		buttonCancel = new CancelButton(this);
		
		// Generate layout
		generateLayout();
	}
	
	/**
	 * Create a new window that can manipulate the given customer.
	 */
	public CreateCustomerForReservationWindow(Customer customer) {
		// Close the window if the customer doesn't exists or hasn't been stored yet.
		if (customer == null) {
			Log.error("Cannot edit a non-existing customer.");
			dispose();
		} else if (customer.id == 0) {
			Log.error("Cannot edit a customer that does not exist in the database.");
			dispose();
		}
		
		// Set the title
		setTitle("Edit " + customer.firstName + " " + customer.lastName);
		
		// Set the elements to contain data of the customer
		fieldFirstName	= new JTextField(customer.firstName);
		fieldLastName	= new JTextField(customer.lastName);
		fieldEmail		= new JTextField(customer.email);
		fieldPhone		= new JTextField(customer.phone);
		fieldAddress	= new JTextArea(customer.address);
		customerId		= customer.id;
		
		// Create buttons
		buttonSubmit = new JButton("Save");
		buttonCancel = new CancelButton(this);
		
		// Generate the layout
		generateLayout();
	}
	
	public void submit() {
		firstNameInput 	= fieldFirstName.getText();
		lastNameInput	= fieldLastName.getText();
		emailInput		= fieldEmail.getText();
		phoneInput		= fieldPhone.getText();
		adressInput		= fieldAddress.getText();
		
		Customer customer = new Customer(firstNameInput, lastNameInput, emailInput, phoneInput, adressInput);
		Customer.save(customer);
		
		remove(this);
	}
	
	/**
	 * Generate layout with the fields defined in the class.
	 */
	private void generateLayout() {
		// Set layout
		setLayout(new BorderLayout());
		
		// Add spaces
		JLabel space1 = new JLabel("");
		JLabel space2 = new JLabel("");
		JLabel space3 = new JLabel("");
		space1.setPreferredSize(new Dimension(10, 10));
		space2.setPreferredSize(new Dimension(10, 10));
		space3.setPreferredSize(new Dimension(10, 10));
		add(space1, BorderLayout.EAST); 
		add(space2, BorderLayout.WEST);
		add(space3, BorderLayout.NORTH);
		
		// Create center wrapper
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		
		// Create wrapping panels for use inside center
		JPanel northWrapper = new JPanel();
		JPanel southWrapper = new JPanel();
		
		// Set the layout - 3px horizontal and 6 px vertical distance
		northWrapper.setLayout(new GridLayout(5, 2, 3, 6));
		southWrapper.setLayout(new GridLayout(1, 2, 3, 6));

		// Add the elements to the pane
		northWrapper.add(textFirstName);	northWrapper.add(fieldFirstName);
		northWrapper.add(textLastName);		northWrapper.add(fieldLastName);
		northWrapper.add(textEmail);		northWrapper.add(fieldEmail);
		northWrapper.add(textPhone);		northWrapper.add(fieldPhone);
		northWrapper.add(new JLabel());		northWrapper.add(new JLabel()); // Add empty space
		southWrapper.add(textAddress);		southWrapper.add(new JScrollPane(fieldAddress)); // Add a scroll pane for the text area.
		textAddress.setPreferredSize(new Dimension(100, 60));
		
		// Add the wrappers to the center panel
		center.add(northWrapper, BorderLayout.NORTH);
		center.add(southWrapper, BorderLayout.SOUTH);
		
		// Add the center
		add(center, BorderLayout.CENTER);
		
		// Create a south wrapper
		JPanel south = new JPanel();
		south.setLayout(new BorderLayout());
		
		// Create inline wrappers and set their layout
		JPanel southDescription = new JPanel();
		JPanel southButtons     = new JPanel();	
		southDescription.setLayout(new BorderLayout());
		southButtons.setLayout(new GridLayout(1, 2, 3, 6));
		
		// Add the help
		if (textHelpTip != null) {
			southDescription.add(textHelpTip, BorderLayout.CENTER);
		
			// Add space
			JPanel space4 = new JPanel(); space4.setPreferredSize(new Dimension(10, 5));
			JPanel space5 = new JPanel(); space5.setPreferredSize(new Dimension(10, 5));
			southDescription.add(space4, BorderLayout.EAST);
			southDescription.add(space5, BorderLayout.WEST);
		}
		
		// Add buttons
		southButtons.add(buttonSubmit); southButtons.add(buttonCancel);
		
		// Add the wrappers to the south panel
		south.add(southDescription, BorderLayout.NORTH);
		south.add(southButtons, BorderLayout.SOUTH);
		
		// Add the south panel
		add(south, BorderLayout.SOUTH);
		
		// Force it to fix size
		setResizable(false);
		
		// Initialize
		setVisible(true);
		pack();
	}
	
	public JButton getSubmitButton() {
		return buttonSubmit;
	}
}
