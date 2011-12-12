package main.view;

import java.awt.*;
import javax.swing.*;

import javax.swing.JTextField;

public class VehicleContainer
	extends JPanel
{
	
	public VehicleContainer()
	{
		setLayout(new GridLayout(3, 2));
		JTextField textField1 = new JTextField(50);
		
		add(textField1);
		
		setVisible(true);		
	}
}
