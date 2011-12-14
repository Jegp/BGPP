package main.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import main.model.Vehicle;


public class VehicleContainer extends JPanel	
{
	JButton addVehicleBtn;
	JButton deleteBtn;
	
	VehicleTable vehicleTable;
	
	public VehicleContainer()
	{
		//Manage layouts
		setLayout(new BorderLayout());
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		south.setLayout(new GridLayout());
		center.setLayout(new GridLayout());
		
		
		//Manage content
		addVehicleBtn = new JButton("Add Vehicle");
		deleteBtn = new JButton("Delete");
		south.add(addVehicleBtn);
		south.add(deleteBtn);
		
		Vehicle[] vehicles = Vehicle.getAll();
		vehicleTable = new VehicleTable(vehicles);
		
		JScrollPane pane = new JScrollPane(vehicleTable);
		center.add(pane);
		
		add(south, BorderLayout.SOUTH);
		add(center, BorderLayout.NORTH);
		
		setVisible(true);		
	}
	
	public void updateTable(Vehicle[] vehicles) {
		vehicleTable.updateTable(vehicles);
	}
	
	public void addVehicleBtnListener(ActionListener vbl) {
		addVehicleBtn.addActionListener(vbl);
	}
	
	public void addDeleteBtnListener(ActionListener dbl) {
		deleteBtn.addActionListener(dbl);
	}

	public void addCreateVehicleBtnListener(ActionListener cvbl) {
		addVehicleBtn.addActionListener(cvbl);
		
	}
}
