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
		
		
		System.out.println("I'm alive!");
	}
	
	
	
} 
