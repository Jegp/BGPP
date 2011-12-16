package main.view;

import javax.swing.*;

import main.model.Customer;
import main.model.Period;
import main.model.Vehicle;

import java.awt.*;
import java.util.Date;
import java.util.HashMap;

public class CreateReservationView extends JFrame{

	/**
	 * the reservations user.
	 */
	private Customer customer;
	private Date startDate;
	private Date endDate;
	private Vehicle vehicle;
	
	private JTextField customerTextField;
	private JTextField startDateTextField;
	private JTextField endDateTextField;
	private JTextField vehicleTextField;
	
	private JButton saveButton;
	private JButton editButton;
	private CancelButton cancelButton = new CancelButton(this);
	
	public CreateReservationView() {
		
		setLayout(new BorderLayout());
	}
}
