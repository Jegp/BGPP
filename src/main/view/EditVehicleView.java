package main.view;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.model.Vehicle;
import main.model.VehicleClass;

public class EditVehicleView extends JFrame {
	
	private JButton saveButton;
	private JTextField description;
	private JTextField manufacter;
	private JTextField model;
	private JComboBox vehicleClass;
	private CancelButton cancelButton;
	private String[] vehicleClasses;
	
	public EditVehicleView(Vehicle vehicle) {
		
		super("Edit " + vehicle.manufacturer + " " + vehicle.model);
		setSize(9000, 9000);
		setLayout(new GridLayout(5, 2));
		setResizable(false);
		fillVehicleClasses();
		
		saveButton = new JButton("Save");
		cancelButton = new CancelButton(this);
		this.description = new JTextField(vehicle.description);
		this.manufacter = new JTextField(vehicle.manufacturer);
		this.model = new JTextField(vehicle.model);
		this.vehicleClass = new JComboBox(vehicleClasses);
		
		//Add content
		add(new JLabel("Description:"));
		add(this.description);
		add(new JLabel("Manufacter:"));
		add(manufacter);
		add(new JLabel("Model:"));
		add(this.model);
		add(new JLabel("Class:"));
		add(this.vehicleClass);
		add(saveButton);
		add(cancelButton);
		
		pack();
		setVisible(true);
		
		
	}
	
	private void fillVehicleClasses() {
		VehicleClass[] vehicleClassesInDb = VehicleClass.getAll();
		ArrayList<String> descriptions = new ArrayList<String>();
		
		for (int i = 0; i < vehicleClassesInDb.length; i++) {
			descriptions.add(vehicleClassesInDb[i].description);
		}
		
		vehicleClasses = new String[descriptions.size()];
		descriptions.toArray(vehicleClasses);
	}

}
