package main.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.model.Customer;
import main.util.Log;

/**
 * A window that displays information about a given customer
 * as well as methods to manipulate that customer. This window is not meant 
 * for creating customers.
 */
public class CustomerWindow extends JFrame {
	
	private final JLabel textFirstName = new JLabel("First name:");
	private final JLabel textLastName  = new JLabel("Last name:");
	private final JLabel textEmail	   = new JLabel("Email:");
	private final JLabel textPhone     = new JLabel("Phone number:");
	private final JLabel textAddress   = new JLabel("Address:");
	
	public final JTextField fieldFirstName;
	public final JTextField fieldLastName;
	public final JTextField fieldEmail;
	public final JTextField fieldPhone;
	public final JTextArea fieldAddress;
	
	public final JButton buttonDelete;
	public final JButton buttonSubmit;
	public final CancelButton buttonCancel;
	
	/**
	 * Create a new window that searches for a given customer.
	 */
	public CustomerWindow(ActionListener searchListener) {
		// Set the title
		setTitle("Search for customers");
		
		// Set the elements to contain data of the customer
		fieldFirstName = new JTextField("");
		fieldLastName  = new JTextField("");
		fieldEmail 		 = new JTextField("");
		fieldPhone 		 = new JTextField("");
		fieldAddress 	 = new JTextArea("");
		
		// Create buttons
		buttonDelete = new JButton("");
		buttonDelete.setVisible(false);
		buttonSubmit = new JButton("Search");
		buttonCancel = new CancelButton(this);
		
		// Add the save listener
		buttonSubmit.addActionListener(searchListener);
		
		// Generate layout
		generateLayout();
	}
	
	/**
	 * Create a new window that can manipulate the given customer.
	 */
	public CustomerWindow(Customer customer, ActionListener deleteListener, ActionListener saveListener) {
		// Throw error if customer is null
		if (customer == null) {
			Log.error("CustomerWindow was initialized without a customer. Throwing exception.");
			throw new NullPointerException("Unable to edit customer if no customer is given.");
		}
		
		// Set the title
		setTitle("Edit " + customer.firstName + " " + customer.lastName);
		
		// Set the elements to contain data of the customer
		fieldFirstName = new JTextField(customer.firstName);
		fieldLastName  = new JTextField(customer.lastName);
		fieldEmail 		 = new JTextField(customer.email);
		fieldPhone 		 = new JTextField(customer.phone);
		fieldAddress 	 = new JTextArea(customer.address);
		
		// Create buttons
		buttonDelete = new JButton("Delete");
		buttonSubmit = new JButton("Save");
		buttonCancel = new CancelButton(this);
		
		// Add listeners
		buttonDelete.addActionListener(deleteListener);
		buttonSubmit.addActionListener(saveListener);
		
		// Generate the layout
		generateLayout();
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
		northWrapper.add(textFirstName); 	northWrapper.add(fieldFirstName);
		northWrapper.add(textLastName);  	northWrapper.add(fieldLastName);
		northWrapper.add(textEmail);		  northWrapper.add(fieldEmail);
		northWrapper.add(textPhone);		  northWrapper.add(fieldPhone);
		northWrapper.add(new JLabel());	  northWrapper.add(new JLabel()); // Add empty space
		southWrapper.add(textAddress);	  southWrapper.add(new JScrollPane(fieldAddress)); // Add a scroll pane for the text area.
		textAddress.setPreferredSize(new Dimension(100, 60));
		
		// Add the wrappers to the center panel
		center.add(northWrapper, BorderLayout.NORTH);
		center.add(southWrapper, BorderLayout.SOUTH);
		
		// Add the center
		add(center, BorderLayout.CENTER);
		
		// Create a south wrapper
		JPanel south = new JPanel();
		south.setLayout(new GridLayout(2, 3, 3, 6));
		
		// Add space
		south.add(new JLabel()); south.add(new JLabel()); south.add(new JLabel());
		
		// Add the buttons
		south.add(buttonDelete); south.add(buttonSubmit); south.add(buttonCancel);
		
		// Add the south panel
		add(south, BorderLayout.SOUTH);
		
		// Force it to fix size
		setResizable(false);
		
		// Initialize
		setVisible(true);
		pack();
	}
	
}
