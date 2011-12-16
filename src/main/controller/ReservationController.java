package main.controller;

import java.awt.event.*;
import java.awt.*;

import javax.swing.JButton;

import main.view.CreateReservationView;
import main.view.ReservationContainer;

public class ReservationController {	
	private CreateReservationView window;
	private ReservationContainer container;
	
	public ReservationController(ReservationContainer container) {
		
		this.container = container;
		container.getCreateReservationButton().addActionListener(new ActionListenerToCreateReservationButton());
		container.getSearchButton().addActionListener(new ActionListenerToSearchButton());
	}
	
	class ActionListenerToCreateReservationButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CreateReservationView window = new CreateReservationView();
			window.getSaveButton().addActionListener(new ActionListerToSaveButton());
			window.getEditButton().addActionListener(new ActionListenerToEditButton());
			window.getUpdateButton().addActionListener(new ActionListenerToUpdateButton());
		}
	}
	
	class ActionListenerToSearchButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	class ActionListerToSaveButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("save!!!!");
		}
	}
	
	class ActionListenerToEditButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("i hit the edit button!!!!");
		}
	}
	
	class ActionListenerToUpdateButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			window.setInput();
		}
	}
}