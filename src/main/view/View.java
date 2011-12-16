	package main.view;

import javax.swing.*;
import main.model.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The View of the booking-system.
 *
 */
public class View extends JFrame 
{
	private JButton reservationButton;
	private JButton customerButton;
	private JButton vehicleButton;
	
	private JPanel container;
	private JPanel graphicContainer;
	

	
	public View(Model model) 
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    
			
	    // create north, south, west and east panels
	    JPanel northPanel = new JPanel(new FlowLayout());
	    JPanel westPanel = new JPanel(new GridLayout(0, 1));
	    JPanel eastPanel = new JPanel();
	    JPanel southPanel = new JPanel();
	        
	    // create content
	    ReservationContainer reservationContainer = new ReservationContainer();
	    
	    container = reservationContainer;
	    
	    // arrange the panels 
	    
	    add(container, BorderLayout.CENTER);
	    add(northPanel, BorderLayout.NORTH);
	    add(westPanel, BorderLayout.WEST);
	    add(eastPanel, BorderLayout.EAST);
	    add(southPanel, BorderLayout.SOUTH);
	        
	    // create buttons
	    reservationButton 	= new JButton("Reservation"); 
	    customerButton 		= new JButton("Customer");
	    vehicleButton 		= new JButton("Vehicle");
	        
	    // add components to north panel
	    northPanel.add(reservationButton);
	    northPanel.add(customerButton);
	    northPanel.add(vehicleButton);
	        
	    // Set title
	    setTitle("Bookingsystem");

	    // Display
	    GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    setMaximizedBounds(e.getMaximumWindowBounds());
	    
	    setPreferredSize(e.getMaximumWindowBounds().getSize());
	    pack();
	    
	    setVisible(true);
	    
	}
		
	public void changeContainer(JPanel newContainer)
	{
		remove(container);
		container = newContainer;
		add(container, BorderLayout.CENTER);
	}

	public void addActionListenerToReservationButton(ActionListener listener)
	{
		reservationButton.addActionListener(listener);
	}
	
	public void addActionListenerToCustomerButton(ActionListener listener)
	{
		customerButton.addActionListener(listener);
	}
	
	public void addActionListenerToVehicleButton(ActionListener listener)
	{
		vehicleButton.addActionListener(listener);
	}
	
	public void addMouseListenerToGraphics(MouseAdapter listener)
	{
		graphicContainer.addMouseListener(listener);
	}
	
	public void maximize() {
		
	}
}
