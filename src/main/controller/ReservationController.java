package main.controller;

import java.awt.event.*;
import java.awt.*;

import javax.swing.JButton;

import main.view.CreateCustomerForReservationWindow;
import main.view.CreateReservationView;
import main.view.ReservationContainer;

public class ReservationController {	
	private CreateReservationView createReservationWindow;
	private ReservationContainer container;
	private CreateCustomerForReservationWindow createCustomerWindow;
	
	public ReservationController(ReservationContainer container) {
		
		this.container = container;
		container.getCreateReservationButton().addActionListener(new ActionListenerToCreateReservationButton());
		container.getSearchButton().addActionListener(new ActionListenerToSearchButton());
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
	
	class ActionListenerToSearchButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ActionListerToSaveButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			createReservationWindow.submit();
		}
	}
	
	class ActionListenerToEditButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("i hit the edit button!!!!");
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
		}
	}
	
	class ActionListenerToTable extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2) {
				int selectedRow = container.getTable().getSelectedRow();
			}
		}
		
	}
}