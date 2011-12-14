package main.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import main.model.*;
import main.view.*;

public class CreateVehicleView extends JFrame {
	
	private JButton saveButton;
	private JTextField description;
	private JTextField manufacter;
	private JTextField model;
	private JComboBox vehicleClass;
	private CancelButton cancelButton;
	private String[] vehicleClasses;
	private Model v_model;
	
	
	public CreateVehicleView() {
		//Manange frame
		super("Add Vehicle");
		setSize(9000, 9000);
		setLayout(new GridLayout(5, 2));
		setResizable(false);
		fillVehicleClasses();
		
		//Initialise instance variables
		v_model = Model.getInstance();
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
	
	public void fillVehicleClasses() {
		VehicleClass[] vehicleClassesInDb = VehicleClass.getAll();
		ArrayList<String> descriptions = new ArrayList<String>();
		
		for (int i = 0; i < vehicleClassesInDb.length; i++) {
			descriptions.add(vehicleClassesInDb[i].description);
		}
		
		vehicleClasses = new String[descriptions.size()];
		descriptions.toArray(vehicleClasses);
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
