package main.view;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import main.model.*;

/**
 * A window with required fields and buttons for saving a new vehicle
 * @author sunedebel
 *
 */
public class CreateVehicleView extends JFrame {
	
	private JButton saveButton;
	private JTextField description;
	private JTextField manufacter;
	private JTextField model;
	private JComboBox vehicleClass;
	private CancelButton cancelButton;
	private String[] vehicleClasses;
	
	/**
	 * Constructor
	 */
	public CreateVehicleView() {
		//Manage frame
		super("Add Vehicle");
		setLocation(new Point(200, 200));
		setLayout(new GridLayout(5, 2));
		setResizable(false);
		fillVehicleClasses();
		
		//Initialize instance variables
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
		setVisible(true);
		
		
	}
	
	/**
	 * Fill the array used to display a drop down menu of vehicle classes
	 */
	private void fillVehicleClasses() {
		VehicleClass[] vehicleClassesInDb = VehicleClass.getAll();
		ArrayList<String> descriptions = new ArrayList<String>();
		
		for (int i = 0; i < vehicleClassesInDb.length; i++) {
			descriptions.add(vehicleClassesInDb[i].description);
		}
		
		vehicleClasses = new String[descriptions.size()];
		descriptions.toArray(vehicleClasses);
	}
	
	/**
	 * add an actionlistener to the save button
	 * @param svl the actionlistener
	 */
	public void addSaveVehicleListener(ActionListener svl) {
		saveButton.addActionListener(svl);
	}
	
	/**
	 * Get the description entered in the designated text field in the window
	 * @return the text entered by the user
	 */
	public String getNewDescription() {
		return description.getText();
	}
	
	/**
	 * Get the manufacturer entered in the designated text field in the window
	 * @return the text entered by the user
	 */
	public String getNewManufactorer() {
		return manufacter.getText();
	}
	
	/**
	 * Get the model entered in the designated text field in the window
	 * @return the text entered by the user
	 */
	public String getNewModel() {
		return model.getText();
	}
	
	/**
	 * Get the id of the vehicleclass selected in the vehicleclass drop down menu
	 * @return the id of the selectedvehicleclass
	 */
	public int getNewVehicleClassID() {
		HashMap<String, String> identifier = new HashMap<String,String>();
		identifier.put("description", vehicleClasses[vehicleClass.getSelectedIndex()]);
		VehicleClass[] temp = VehicleClass.searchWhere(identifier);
		
		for (VehicleClass vc : temp) {
			if (vc.description.equals(vehicleClasses[vehicleClass.getSelectedIndex()])) {
				return vc.id;
			}
		}
		return 0;
	}
	
	/**
	 * reset the text fields and close the window
	 */
	public void kill() {
		description.setText("");
		manufacter.setText("");
		model.setText("");
		setVisible(false);		
	}

}
