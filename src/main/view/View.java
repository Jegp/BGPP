	package main.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

/**
 * The View of the booking-system. The view is responsible for 
 * defining and displaying the graphical base of the software.
 */
public class View extends JFrame 
{
  private static final long serialVersionUID = -8589019224540130133L;
	private JButton reservationButton;
	private JButton customerButton;
	private JButton vehicleButton;
	
	private JPanel container;
	private JPanel graphicContainer;
	
	/**
	 * Instantiates the view.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    
	    // create north, south, west and east panels
	    JPanel northPanel 	= new JPanel(new FlowLayout());
	    JPanel westPanel 	= new JPanel(new GridLayout(0, 1));
	    JPanel eastPanel 	= new JPanel();
	    JPanel southPanel 	= new JPanel();
	    
	    // create primitive start panel.
	    JPanel startPanel 	= new JPanel(); // perhaps add picture?
	    
	    // set starting panel
	    container = startPanel;
	    
	    // arrange the panels 
	    add(container, BorderLayout.CENTER);
	    add(northPanel, BorderLayout.NORTH);
	    add(westPanel, BorderLayout.WEST);
	    add(eastPanel, BorderLayout.EAST);
	    add(southPanel, BorderLayout.SOUTH);
	        
	    // create buttons
	    reservationButton = new JButton("Reservation"); 
	    customerButton 		= new JButton("Customer");
	    vehicleButton 		= new JButton("Vehicle");
	        
	    // add components to north panel
	    northPanel.add(reservationButton);
	    northPanel.add(customerButton);
	    northPanel.add(vehicleButton);
	    
	    // Add a loading panel to display loading before the container is shown
	    JPanel loadingPanel = new JPanel(new BorderLayout());
	    JLabel loading = new JLabel("Loading...");
	    loadingPanel.add(loading, BorderLayout.NORTH);
	    add(loadingPanel);
	        
	    // Set title
	    setTitle("Bookingsystem");

	    // Display
	    GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    setMaximizedBounds(e.getMaximumWindowBounds());
	    setPreferredSize(e.getMaximumWindowBounds().getSize());
	    
	    // Initialize
	    pack();
	    setVisible(true);
	}
		
	/**
	 * Replace the active container with a new one.
	 */
	public void changeContainer(JPanel newContainer) {
		remove(container);
		container = newContainer;
		add(container, BorderLayout.CENTER);
		pack();
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
