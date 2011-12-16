package main.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import main.model.Vehicle;


public class VehicleContainer extends JPanel	
{
	JButton addVehicleBtn;
	JButton deleteBtn;
	JScrollPane pane;
	
	
	
	public VehicleContainer()
	{
		//Manage layouts
		setLayout(new BorderLayout());
		JPanel south = new JPanel();
		south.setLayout(new GridLayout());
		
		
		//Manage content
		addVehicleBtn = new JButton("Add Vehicle");
		deleteBtn = new JButton("Delete");
		south.add(addVehicleBtn);
		south.add(deleteBtn);
		
		Vehicle[] vehicles = Vehicle.getAll();
		
		
		add(south, BorderLayout.SOUTH);
		
		setVisible(true);		
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
	
	public void addTable(VehicleTable table) {
		pane = new JScrollPane(table);
		add(pane, BorderLayout.CENTER);
	}
	
	public void addDeleteVehicleBtnListener(ActionListener e) {
		deleteBtn.addActionListener(e);
	}
	
}
