package main.controller;

import java.awt.event.*;
import java.awt.*;

import javax.swing.JButton;

import main.view.CreateReservationView;
import main.view.ReservationContainer;

public class ReservationController {	
	private ReservationContainer container;
	
	public ReservationController(ReservationContainer container) {
		
		this.container = container;
		
		container.getCreateReservationButton().addActionListener(new ActionListenerToCreateReservationButton());
		
		container.getSearchButton().addActionListener(new ActionListenerToSearchButton());
	}
	
	class ActionListenerToCreateReservationButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CreateReservationView window = new CreateReservationView();
		}
	}
	
	class ActionListenerToSearchButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("SearchButton!!!");			
		}
	}
}