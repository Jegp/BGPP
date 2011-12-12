package main.view;

import java.awt.*;

import javax.swing.*;

public class CustomerContainer
	extends JPanel
{
	public CustomerContainer()
	{
		setLayout(new GridLayout(3, 2));
		JTextField textField1 = new JTextField(20);
		JTextField textField2 = new JTextField(20);
		
		add(textField1);
		add(textField2);
		
		setVisible(true);
	}
}
