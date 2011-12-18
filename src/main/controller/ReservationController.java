package main.controller;

import java.awt.event.*;
import java.awt.*;

import javax.swing.JButton;

import main.model.Customer;
import main.model.Period;
import main.model.Reservation;
import main.model.Vehicle;
import main.view.CreateCustomerForReservationWindow;
import main.view.CreateReservationView;
import main.view.ReservationContainer;

public class ReservationController {	
	private CreateReservationView createReservationWindow;
	private ReservationContainer container;
	private CreateReservationView reservation;
	private CreateCustomerForReservationWindow createCustomerWindow;
	
	public ReservationController(ReservationContainer container) {
		
		this.container = container;
		container.getCreateReservationButton().addActionListener(new ActionListenerToCreateReservationButton());
		container.getDeleteButton().addActionListener(new ActionListenerDeleteButton());
		container.getUpdatePeriodButton().addActionListener(new ActionListenerToUpdatePeriodButton());
		container.getTable().addMouseListener(new ActionListenerToTable());
	}
	
	class ActionListenerToCreateReservationButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			createReservationWindow = new CreateReservationView();
			createReservationWindow.getSaveButton().addActionListener(new ActionListerToSaveButton());
			createReservationWindow.getUpdateButton().addActionListener(new ActionListenerToUpdateButton());
			createReservationWindow.getCustomerCreateButton().addActionListener(new ActionListenerToCreateCustomerButton());
		}
	}
	
	class ActionListenerDeleteButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int selectedRow = container.getTable().getSelectedRow();
			
			int id = container.getData().reservations[selectedRow].id;
			
			Reservation.delete("reservation", id);
			
			container.updatePeriod();
			container.addTable();
		}
	}
	
	class ActionListerToSaveButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			createReservationWindow.submit();
			container.updatePeriod();
			container.addTable();
		}
	}
	
	class ActionListenerToUpdateButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			createReservationWindow.setPeriod();
			createReservationWindow.setVehicleComboBox();
		}
	}
	
	class ActionListenerToCreateCustomerButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		createReservationWindow.dispose();
		createCustomerWindow = new CreateCustomerForReservationWindow();
		createCustomerWindow.getSubmitButton().addActionListener(new ActionListenerToSubmitNewCustomerButton());
		}
	}
	
	class ActionListenerToSubmitNewCustomerButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		createCustomerWindow.submit();
		container.updatePeriod();
		container.addTable();
		}
	}
	
	class ActionListenerToTable extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) {
				int selectedRow = container.getTable().getSelectedRow();
				
				Customer customer 			= container.getData().reservations[selectedRow].customer;
				Period period				= container.getData().reservations[selectedRow].period;
				Vehicle vehicle				= container.getData().reservations[selectedRow].vehicle;
				int oldReservationID		= container.getData().reservations[selectedRow].id;
				
				reservation = new CreateReservationView(customer, period, vehicle);
				reservation.getSaveChangesButton().addActionListener(new ActionListenerToSaveChangesButton());
				reservation.setPeriodWithExistingReservation(period);
				reservation.setVehicleComboBox();
				reservation.setOldReservationID(oldReservationID);
			}
		}
		
	}
	
	class ActionListenerToUpdatePeriodButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			container.updatePeriod();
			container.addTable();
		}
	}
	
	class ActionListenerToSaveChangesButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			reservation.submitReservationChanges();
			container.updatePeriod();
			container.addTable();
		}
	}
}