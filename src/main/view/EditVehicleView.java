package main.view;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import main.model.Vehicle;
import main.model.VehicleClass;

/**
 * A window for editing information about vehicles in the system
 * @author sunedebel
 *
 */
public class EditVehicleView extends JFrame {
	
	private JButton saveButton;
	private JTextField description;
	private JTextField manufacter;
	private JTextField model;
	private JComboBox vehicleClass;
	private CancelButton cancelButton;
	private String[] vehicleClasses;
	
	/**
	 * Constructor
	 * @param the vehicle to edit
	 */
	public EditVehicleView(Vehicle vehicle) {
		
		super("Edit " + vehicle.manufacturer + " " + vehicle.model);
		setSize(9000, 9000);
		setLocation(new Point(200, 200));
		setLayout(new GridLayout(5, 2));
		setResizable(false);
		fillVehicleClasses();
		
		
		saveButton = new JButton("Save");
		cancelButton = new CancelButton(this);
		this.description = new JTextField(vehicle.description);
		this.manufacter = new JTextField(vehicle.manufacturer);
		this.model = new JTextField(vehicle.model);
		this.vehicleClass = new JComboBox(vehicleClasses);
		
		setDropDown(vehicle.vehicleClass.description);
		
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
	
	
	/**
	 * fill the array thats used to display a drop down menu of vehicleclasses
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
	 * set the drop down to the vehicle class of the selected vehicle
	 * @param selectedVehicleClass the name of the vehicleclass
	 */
	private void setDropDown(String selectedVehicleClass) {
		for (int i = 0; i < vehicleClasses.length; i++) {
			if (selectedVehicleClass.equals(vehicleClasses[i])) {
				vehicleClass.setSelectedIndex(i);
			}
		}
	}
	
	/**
	 * add an actionlistener to the save button
	 * @param e the actionlistener
	 */
	public void addSaveActionListener(ActionListener e) {
		
		saveButton.addActionListener(e);
	}
	
	/**
	 * Get the description entered in the designated text field in the window
	 * @return the text entered by the user
	 */
	public String getNewDescription() {
		return description.getText();
	}
	
	/**
	 * Get the model entered in the designated text field in the window
	 * @return the text entered by the user
	 */
	public String getNewModel() {
		return model.getText();
	}
	
	/**
	 * Get the manufacturer entered in the designated text field in the window
	 * @return the text entered by the user
	 */
	public String getNewManufactorer() {
		return manufacter.getText();
	}
	
	/**
	 * Get the id of the selected vehicleclass
	 * @return the id of the selected vehicle class
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
	 * reset text fields and close the window
	 */
	public void kill() {
		description.setText("");
		manufacter.setText("");
		model.setText("");
		setVisible(false);		
	}

}
