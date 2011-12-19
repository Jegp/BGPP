package main.view;

import javax.swing.*;

import main.model.Customer;
import main.model.Period;
import main.model.Reservation;
import main.model.Vehicle;

import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
/**
 * This class extends a JFrame and has a GUI function to create reservations of vehicles.
 */
public class CreateReservationView extends JFrame{
	/**
	 * a variable which stores the customer who will be stored in the database. 
	 */
	private Customer customer;
	
	/**
	 * a variable that stores the GUI input as well as in conjunction with endDate creates a period in the database. 
	 */
	private Date startDate;
	
	/**
	 * a variable that stores the GUI input as well as in conjunction with startDate creates a period in the database.
	 */
	private Date endDate;
	
	/**
	 * a variable that stores the period. The period is stored in the database and contributes to create a reservation.
	 */
	private Period period;
	
	/**
	 * a variable that stores the chosen vehicle.
	 */
	private Vehicle vehicle;
	
	/**
	 * the only panel which is defined to a variable.
	 */
	private JPanel centerPanel;
	
	/**
	 * empty label for GUI purposes.
	 */
	private final JLabel emptyLabel2		 = new JLabel("Customer:");
	
	/**
	 * empty label for GUI purposes.
	 */
	private final JLabel emptyLabel1		 = new JLabel("Customer:");
	
	/**
	 * Label for GUI: The customer.
	 */
	private final JLabel customerLabel	 = new JLabel("Customer:");
	
	/**
	 * Label for GUI: Starting date.
	 */
	public final JLabel startDateLabel 	 = new JLabel("Start:");
	
	/**
	 * Label for GUI: Ending date.
	 */
	public final JLabel endDateLabel 	 = new JLabel("End:");
	
	/**
	 * Label for GUI: Chosen vehicle
	 */
	public final JLabel vehicleLabel 	 = new JLabel("Vehicle:");
	
	/**
	 * Text field which allows the user to enter the desired dates.
	 */
	private JTextField startDateTextField;
	
	/**
	 * Text field which allows the user to enter the desired dates.
	 */
	private JTextField endDateTextField;
	
	/**
	 * A JButton which displays "Create". The button is final and cannot be manipulated 
	 */
	public final JButton customerCreateButton 	= new JButton("Create");
	
	/**
	 * A JButton which displays "Update". The button is final and cannot be manipulated
	 */
	public final JButton updatePeriodButton		= new JButton("Update");
	
	/**
	 * A variable that can store a JButton. This button can be manipulated.
	 */
	private JButton saveButton;
	
	/**
	 * A variable that can store a JButton. This button can be manipulated.
	 */
	private JButton saveChangesButton;
	
	/**
	 * A generic cancel button which can be implemented in any JComponent. It has an automated listener and will close
	 * the container which it is created in by activating.
	 */
	public final CancelButton cancelButton 	= new CancelButton(this);
	
	/**
	 * A String variable that can store what the user has written in the text fields.
	 */
	private String startDateInput;
	
	/**
	 * A String variable that can store what the user has written in the text fields.
	 */
	private String endDateInput;
	
	/**
	 * A variable that stores the ID of the reservation that is to be updated.
	 */
	private int oldReservationID;
	
	/**
	 * a variable that can store an array of customers.
	 */
	private Customer[] customers;
	
	/**
	 * a variable that can store an array of vehicles.
	 */
	private Vehicle[] vehicles;
	
	/**
	 * a variable that stores a JComboBox. 
	 */
	private JComboBox customerDropDown;
	
	/**
	 * a variable that stores a JComboBox. 
	 */
	private JComboBox vehicleDropDown;
	
	/**
	 * a variable that stores an array of Strings. It has the purpose of "painting" a JCombobox.
	 */
	private String[] customerComboBox;
	
	/**
	 * a variable that stores an array of Strings. It has the purpose of "painting" a JCombobox.
	 */
	private String[] availableVehicles;
	
	/**
	 * a variable that stores an array of vehicles. It has the purpose of keeping track of the vehicles used in
	 * the variable "availableVehicles".
	 */
	private Vehicle[] availableVehiclesType;

	/**
	 * simply defines a simpleDateFormat, so that the whole class can use it.
	 */
	private SimpleDateFormat dateFormat;
	
	/**
	 * a variable that can hold a Gregorian Calendar.
	 */
	private GregorianCalendar calendar;
	
	/**
	 * A constructor that creates a view where you can create a reservation from scratch.
	 */
	public CreateReservationView() {		
		startDateTextField 	= new JTextField(10);
		endDateTextField	= new JTextField(10);
		
		setCustomerComboBox();
		
		customerDropDown	= new JComboBox(customerComboBox);
		saveButton			= new JButton("Save");
		
		setLayout();
	}
	
	/**
	 * Second constructor that creates a view with data from a reservation
	 * @param customer
	 * @param period
	 * @param vehicle
	 */
	public CreateReservationView(Customer customer, Period period, Vehicle vehicle) {
		this.customer	= customer;
		this.period		= period;
		this.vehicle	= vehicle;
		
		setCustomerComboBox();
		
		customerDropDown					= new JComboBox(customerComboBox);
		saveChangesButton					= new JButton("Save changes");
		saveButton							= saveChangesButton;
		updatePeriodButton.setEnabled(false);
		customerCreateButton.setEnabled(false);
		
		//date -> string
		dateFormat							= new SimpleDateFormat("dd/MM/yyyy");
		String periodStartToString 			= dateFormat.format(period.start);
		String periodEndToString			= dateFormat.format(period.end);
		
		startDateTextField 	= new JTextField(periodStartToString);
		endDateTextField	= new JTextField(periodEndToString);
		
		setLayout();
	}
	
	/**
	 * sets the period to reflect the input of the customer.
	 */
	public void setPeriod() {
		try {
		startDateInput		= startDateTextField.getText();
		endDateInput		= endDateTextField.getText();
		
		dateFormat			= new SimpleDateFormat("dd/MM/yyyy");
		
		startDate			= dateFormat.parse(startDateInput);	
		endDate				= dateFormat.parse(endDateInput);
		
		period				= new Period(startDate, endDate);
		
		calendar			= new GregorianCalendar();
		calendar.setTime(startDate);		
		
		} catch (ParseException e) {
			System.out.println("Invalid calendar input");
			e.printStackTrace();
		}
	}
	
	/**
	 * the method name says it all. This is called when the 2nd constructor is called.
	 * @param period
	 */
	public void setPeriodWithExistingReservation(Period period) {
		startDate			= period.start;
		endDate				= period.end;
		calendar			= new GregorianCalendar();
	}
	
	/**
	 * sets and creates the JComboBox filled with all customer names from the database.
	 */
	public void setCustomerComboBox() {
		customers				= Customer.getAll();
		customerComboBox		= new String[customers.length];
		
		for(int i = 0; i < customers.length; i++) {
			customerComboBox[i] = customers[i].firstName + " " + customers[i].lastName;
		}	
	}
	
	/**
	 * sets and creates the JComboBox filled with the available vehicles in the given period.
	 */
	public void setVehicleComboBox() {
		vehicles 	 				= Vehicle.getAll();
		Reservation[] reservations	= Reservation.getFromPeriod(period);
		availableVehicles			= new String[vehicles.length];
		availableVehiclesType		= new Vehicle[vehicles.length];
		calendar.setTime(startDate);
		
		if(vehicles == null) {
		vehicleDropDown = new JComboBox();
		}
		else {
		int n = 0;
		 for (Vehicle vehicle : vehicles) {
			 boolean isReserved = false;
			 for (int i = 0; i<reservations.length; i++ ) {
				 if (vehicle.id == reservations[i].vehicle.id) {
					 isReserved = true;
				 }
			 }
			 if (!isReserved) {
				 availableVehicles[n] = "Class: " + vehicle.vehicleClass.description + " " + "Model: " + vehicle.model;
				 availableVehiclesType[n] = vehicle;
				 n++;
			 }
		 }
		vehicleDropDown = new JComboBox(availableVehicles);
		}
		centerPanel.add(vehicleDropDown);
		pack();
		
	}
	
	/**
	 * submits a reservation by reading the users input.
	 */
	public void submit() {		

		
		customer 	= customers[customerDropDown.getSelectedIndex()];
		vehicle 	= availableVehiclesType[vehicleDropDown.getSelectedIndex()];
		
		setPeriod();
		
		customer 				= Customer.save(customer);
		period 					= Period.save(period);

		Reservation reservation = new Reservation(customer, period, vehicle);

		Reservation.save(reservation);
		
		dispose();

	}
	
	/**
	 * sets the reservation ID. This method is called when a reservation is to be changed.
	 * @param oldReservationID
	 */
	public void setOldReservationID(int oldReservationID) {
		this.oldReservationID = oldReservationID;
	}
	
	/**
	 * submits an edit in a existing reservation.
	 */
	public void submitReservationChanges() {
		
		customer = customers[customerDropDown.getSelectedIndex()];
		
		vehicle 	= availableVehiclesType[vehicleDropDown.getSelectedIndex()];
		
		setPeriod();
		
		customer 				= Customer.save(customer);
		period 					= Period.save(period);

		Reservation reservation = new Reservation(customer, period, vehicle);

		Reservation.update(reservation, oldReservationID);
		
		dispose();
	}
	
	/**
	 * sets out the GUI preferences.
	 */
	public void setLayout() {
		setTitle("Create Reservation");
		setLayout(new BorderLayout());

		JPanel northPanel		= new JPanel(new GridLayout(1,2,1,3));
		JPanel withinNorthPanel = new JPanel(new GridLayout(1,2,1,3));	
		centerPanel 			= new JPanel(new GridLayout(5,2,1,3));
		JPanel southPanel		= new JPanel(new FlowLayout());
		
		//arrange the north panel with JLabels, JTextFields and JButtons
		emptyLabel1.setVisible(false);
		emptyLabel2.setVisible(false);
		
		northPanel.add(emptyLabel1);
		northPanel.add(withinNorthPanel);
		withinNorthPanel.add(customerCreateButton);
		
		//arrange center panel with JLabels and JTextFIelds
		centerPanel.add(customerLabel);
		centerPanel.add(customerDropDown);
		
		centerPanel.add(startDateLabel);
		centerPanel.add(startDateTextField);
		
		centerPanel.add(endDateLabel);
		centerPanel.add(endDateTextField);
		
		centerPanel.add(emptyLabel2);
		centerPanel.add(updatePeriodButton);
		
		centerPanel.add(vehicleLabel);
		
		//arrange south panel with buttons
		southPanel.add(saveButton);
		southPanel.add(cancelButton);
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
				
		setResizable(false);
		pack();
		setVisible(true);
	}
	
	/**
	 * returns the button which creates a customer
	 * @return JButton
	 */
	public JButton getCustomerCreateButton() {
		return customerCreateButton;
	}
	
	/**
	 * returns the button which saves a customer.
	 * @return JButton
	 */
	public JButton getSaveButton() {
		return saveButton;
	}
	
	/**
	 * returns the button which updates the available vehicles
	 * @return JButton
	 */
	public JButton getUpdateButton() {
		return updatePeriodButton;
	}
	
	/**
	 * return a JButton that submits changes to an existing reservation. (constructor #2).
	 * @return JButton
	 */
	public JButton getSaveChangesButton() {
		return saveChangesButton;
	}
}
