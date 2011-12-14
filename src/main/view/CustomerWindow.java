package main.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import main.model.Customer;
import main.util.Log;

/**
 * A window that displays information about a given customer
 * as well as methods to manipulate that customer. This window is not meant 
 * for creating customers.
 */
public class CustomerWindow extends JFrame {

	/**
	 * The customer to edit.
	 */
	private Customer customer;
	
	private final JLabel textId 			 = new JLabel("Customer id:");
	private final JLabel textFirstName = new JLabel("First name:");
	private final JLabel textLastName  = new JLabel("Last name:");
	private final JLabel textEmail	   = new JLabel("Email:");
	private final JLabel textPhone     = new JLabel("Phone number:");
	private final JLabel textAddress   = new JLabel("Address:");
	
	public final JLabel fieldId;
	public final JTextField fieldFirstName;
	public final JTextField fieldLastName;
	public final JTextField fieldEmail;
	public final JTextField fieldPhone;
	public final JTextArea fieldAddress;
	
	public final JButton buttonSave;
	public final CancelButton buttonCancel;
	
	/**
	 * Create a new window that can manipulate the given customer.
	 */
	public CustomerWindow(Customer customer) {
		// Set layout
		setLayout(new BorderLayout());
		
		// Create center wrapper
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		
		// Create wrapping panels
		JPanel northWrapper = new JPanel();
		JPanel southWrapper = new JPanel();
		
		// Set the layout - 3px horizontal and 6 px vertical distance
		northWrapper.setLayout(new GridLayout(5, 2, 3, 6));
		southWrapper.setLayout(new GridLayout(1, 2, 3, 6));

		// Throw error if customer is null
		if (customer == null) {
			Log.error("CustomerWindow was initialized without a customer. Throwing exception.");
			throw new NullPointerException("Unable to edit customer if no customer is given.");
		}
		
		// Set the customer
		this.customer = customer;
		
		// Set the title
		setTitle("Edit " + customer.firstName + " " + customer.lastName);
		
		// Set the elements to contain data of the customer
		fieldId 			 = new JLabel(customer.id + "");
		fieldFirstName = new JTextField(customer.firstName);
		fieldLastName  = new JTextField(customer.lastName);
		fieldEmail 		 = new JTextField(customer.email);
		fieldPhone 		 = new JTextField(customer.phone);
		fieldAddress 	 = new JTextArea(customer.address);
		
		// Create buttons
		buttonSave   = new JButton("Save");
		buttonCancel = new CancelButton(this);
		
		// Add the elements to the pane
		northWrapper.add(textId);					northWrapper.add(fieldId); 
		northWrapper.add(textFirstName); 	northWrapper.add(fieldFirstName);
		northWrapper.add(textLastName);  	northWrapper.add(fieldLastName);
		northWrapper.add(textEmail);		  northWrapper.add(fieldEmail);
		northWrapper.add(textPhone);		  northWrapper.add(fieldPhone);
		southWrapper.add(textAddress);	  southWrapper.add(new JScrollPane(fieldAddress)); // Add a scroll pane for the text area.
		textAddress.setPreferredSize(new Dimension(100, 60));
		
		// Add the wrappers to the center panel
		center.add(northWrapper, BorderLayout.NORTH);
		center.add(southWrapper, BorderLayout.SOUTH);
		
		// Add the center
		add(center, BorderLayout.CENTER);
		
		// Add spaces
		JLabel space1 = new JLabel("");
		JLabel space2 = new JLabel("");
		space1.setPreferredSize(new Dimension(10, 10));
		space2.setPreferredSize(new Dimension(10, 10));
		add(space1, BorderLayout.EAST); 
		add(space2, BorderLayout.WEST);
		
		// Create a south wrapper
		JPanel south = new JPanel();
		south.setLayout(new GridLayout(1, 2, 3, 6));
		
		// Add the buttons
		south.add(buttonSave); south.add(buttonCancel);
		
		// Add the south panel
		add(south, BorderLayout.SOUTH);
		
		// Force it to fix size
		setResizable(false);
		
		// Initialize
		setVisible(true);
		pack();
	}
	
}
