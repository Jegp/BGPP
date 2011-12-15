package main.controller;

import java.awt.event.*;

import main.view.ReservationContainer;

public class ReservationController {
	
	private ReservationContainer container = new ReservationContainer();
	
	public ReservationController(ReservationContainer container) {
		
		this.container = container;
		
		container.setActionListenerToCreateButton(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		
	}

}
