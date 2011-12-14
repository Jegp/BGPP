package main.view;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import main.view.*;

public class CreateVehicleView extends JFrame {
	
	private JButton saveButton;
	private JTextField description;
	private JTextField manufacter;
	private JTextField model;
	private JComboBox vehicleClass;
	private CancelButton cancelButton;
	String[] vehicleClasses;
	
	
	public CreateVehicleView() {
		//Manange frame
		super("Add Vehicle");
		setSize(9000, 9000);
		setLayout(new GridLayout(5, 2));
		setResizable(false);
		
		//Array for dropdown
		vehicleClasses = new String[]{"3 Doors", "5 Doors", "Stationcar", "Van"};
		
		//Initialise instance variables
		description		= new JTextField();
		manufacter 		= new JTextField();
		model 			= new JTextField();
		vehicleClass 	= new JComboBox(vehicleClasses);
		cancelButton 	= new CancelButton(this);
		saveButton		= new JButton("Save");
		
		vehicleClass.setSelectedIndex(0);
		
		//Add content
		add(new JLabel("Description:"));
		add(description);
		add(new JLabel("Manufacter:"));
		add(manufacter);
		add(new JLabel("Model:"));
		add(model);
		add(new JLabel("Class:"));
		add(vehicleClass);
		add(saveButton);
		add(cancelButton);
		
		
		pack();
		setVisible(false);
		
		
	}
	
	public void addSaveVehicleListener(ActionListener svl) {
		saveButton.addActionListener(svl);
	}
	
	public String getNewDescription() {
		return description.getText();
	}
	
	public String getNewManufactorer() {
		return manufacter.getText();
	}
	
	public String getModel() {
		return model.getText();
	}
	
	public String getVehicleClass() {
		return vehicleClasses[vehicleClass.getSelectedIndex()];
	}

}
