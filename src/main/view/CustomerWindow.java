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
		// Set the layout - 3px horizontal and 6 px vertical distance
		setLayout(new GridLayout(8, 2, 3, 6));

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
		add(textId);				add(fieldId); 
		add(textFirstName); add(fieldFirstName);
		add(textLastName);  add(fieldLastName);
		add(textEmail);		  add(fieldEmail);
		add(textPhone);		  add(fieldPhone);
		add(textAddress);	  add(new JScrollPane(fieldAddress)); // Add a scroll pane for the text area.
		textAddress.setPreferredSize(new Dimension(200, 80));
		
		// Add space
		add(new JLabel("")); add(new JLabel(""));
		
		// Add the buttons
		add(buttonSave); add(buttonCancel);
		
		// Initialize
		setVisible(true);
		pack();
	}
	
}
