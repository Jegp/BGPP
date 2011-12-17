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

public class CreateReservationView extends JFrame{
	
	private Customer customer;
	private Date startDate;
	private Date endDate;
	private Period period;
	private Vehicle vehicle;
	
	private JPanel centerPanel;
	
	public final JLabel emptyLabel2		 = new JLabel("Customer:");
	public final JLabel emptyLabel1		 = new JLabel("Customer:");
	public final JLabel customerLabel	 = new JLabel("Customer:");
	public final JLabel startDateLabel 	 = new JLabel("Start:");
	public final JLabel endDateLabel 	 = new JLabel("End:");
	public final JLabel vehicleLabel 	 = new JLabel("Vehicle:");
	
	private JTextField startDateTextField;
	private JTextField endDateTextField;
	
	public final JButton customerCreateButton 	= new JButton("Create");
	public final JButton customerSearchButton 	= new JButton("Search");
	public final JButton updatePeriodButton	= new JButton("Update");
	
	public final JButton saveButton			= new JButton("Save"); 
	public final JButton editButton			= new JButton("Edit");
	public final CancelButton cancelButton 	= new CancelButton(this);
	
	private String startDateInput;
	private String endDateInput;
	
	private Customer[] customers;
	private Vehicle[] vehicles;
	
	private JComboBox customerDropDown;
	private JComboBox vehicleDropDown;
	private String[] allCustomers;
	private String[] availableVehicles;
		
	private SimpleDateFormat dateFormat;
	private GregorianCalendar calendar;
	
	public CreateReservationView() {		
		startDateTextField 		= new JTextField(10);
		endDateTextField		= new JTextField(10);

		editButton.setVisible(false);
		
		getAllCustomersInArray();
		
		customerDropDown	= new JComboBox(allCustomers);
		
		setLayout();
	}
	
	public CreateReservationView(Customer customer, Period period, Vehicle vehicle) {
		this.customer	= customer;
		this.period		= period;
		this.vehicle	= vehicle;
		
		//date -> string
		dateFormat							= new SimpleDateFormat("dd/MM/yyyy");
		String periodStartToString 			= dateFormat.format(period.start);
		String periodEndToString			= dateFormat.format(period.end);
		
		startDateTextField 	= new JTextField(periodStartToString);
		endDateTextField	= new JTextField(periodEndToString);
		
		editButton.setVisible(true);
		
		setLayout();
	}
	
	public void setAllVehiclesinArray() {
		vehicles 			= Vehicle.getAll();
		Reservation[] reservations	= Reservation.getFromPeriod(period);
		availableVehicles			= new String[vehicles.length];
		int reservedInThePeriod		= 0;
		
		//iterates through every slot in the drop down box
		for(int k = 0; k < vehicles.length; k++) {
		 //iterates through all vehicles
		 for(int i = 0; i < vehicles.length; i++) {
		  if(reservations != null) {
			  //iterates through reservations to find the reservations vehicle ID's
			  for(int j = 0; j < reservations.length; j++) {
				 if(vehicles[i].id == reservations[j].vehicle.id) {
					for(int n = 0; n < period.getLengthInDays(); n++) {
						if(reservations[j].period.isIncluded(startDate)) {
							reservedInThePeriod++;
						}
						if(n == period.getLengthInDays() - 1 && reservedInThePeriod == 0) {
							availableVehicles[k] = vehicles[i].model;
						}
					calendar.add(Calendar.DAY_OF_MONTH, n);
					}
				 }
				 else {
					 availableVehicles[k] = vehicles[i].model;
				 }
			  availableVehicles[k] = vehicles[i].model;
			  }}
		  else if(reservations == null) {
			  availableVehicles[k] = vehicles[i].model;
		  }
		 }
	    }
		
		vehicleDropDown = new JComboBox(availableVehicles);
		centerPanel.add(vehicleDropDown);
		pack();
		
	}
	
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
	
	public void getAllCustomersInArray() {
		customers				= Customer.getAll();
		allCustomers			= new String[customers.length];
		
		for(int i = 0; i < customers.length; i++) {
			allCustomers[i] = customers[i].firstName + " " + customers[i].lastName;
		}	
	}
	
	public int submit() {
		for(int i = 0; i < customers.length; i++) {
			if(allCustomers[customerDropDown.getSelectedIndex()].equals(customers[i].firstName + "" + customers[i].lastName)) {
				customer = customers[i];
			}
		}
		
		for(int i = 0; i < vehicles.length; i++) {
			if(availableVehicles[vehicleDropDown.getSelectedIndex()].equals(vehicles[i].model)) {
				vehicle = vehicles[i];
			}
		}
		
		Reservation reservation = new Reservation(customer, period, vehicle);
		Reservation.save(reservation);
		
		return 0;
	}
	
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
		withinNorthPanel.add(customerSearchButton);
		
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
		southPanel.add(editButton);
		southPanel.add(cancelButton);
		
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
				
		setResizable(false);
		pack();
		setVisible(true);
	}
	
	public JButton getCustomerCreateButton() {
		return customerCreateButton;
	}

	public JButton getCustomerSearchButton() {
		return customerSearchButton;
	}
	
	public JButton getSaveButton() {
		return saveButton;
	}
	
	public JButton getEditButton() {
		return editButton;
	}
	
	public JButton getUpdateButton() {
		return updatePeriodButton;
	}
}
