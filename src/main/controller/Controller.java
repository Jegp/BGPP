package main.controller;

import java.awt.event.*;

import main.model.Model;
import main.view.View;

/**
 * The Controller for the booking-system.
 */
public class Controller {
	
	//The controller needs to interact with both the view and model
	private Model c_model;
	private View c_view;

	//Constructor
	public Controller(Model model, View view) {
		c_model		= model;
		c_view		= view;
		
		//Add an ActionListener to the a button in the view
		view.AddNewWindowListener(new NewWindowListener());
		
		System.out.println("I'm alive!");
	}
	
	////////////////////////////////////// Inner class PressButtonActionListener
	class NewWindowListener implements ActionListener {
		/**
		 * Build a new window
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			c_view.buildNewWindow();			
		}
		
	}
	
} 
