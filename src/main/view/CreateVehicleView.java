package main.view;
import java.awt.*;
import javax.swing.*;

import main.view.*;

public class CreateVehicleView extends JFrame {
	
	private JButton save;
	private JTextField manufacter;
	private JTextField model;
	private JTextField vehicleClass;
	private CancelButton cancelButton;
	
	
	public CreateVehicleView() {
		super("Add Vehicle");
		setSize(4000, 4000);
		setResizable(false);
		
		manufacter 		= new JTextField();
		model 			= new JTextField();
		vehicleClass 	= new JTextField();
		cancelButton 	= new CancelButton(this);
		save 			= new JButton("Save");
		
		setLayout(new GridLayout(4, 2));
		add(new JLabel("Manufacter"));
		add(manufacter);
		add(new JLabel("Model"));
		add(model);
		add(new JLabel("Class"));
		add(vehicleClass);
		add(save);
		add(cancelButton);
		
		
		pack();
		setVisible(false);
		
		
	}

}
