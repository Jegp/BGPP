package main.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import main.model.*;

/**
 * The View of the booking-system.
 *
 */
public class View extends JFrame 
{
	
	JButton newWindowBtn;

	public View(Model model) 
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 900);
		
		
		// create the menu
		JMenu menu = new JMenu();
		add(menu);
		
		// create the menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
        
        // create the File menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        
        // create the Help menu
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        
        // add "open" to the File menu
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        
        // add "quit" to the Quit menu
        JMenuItem quitItem = new JMenuItem("Quit");
        fileMenu.add(quitItem);
        
        // create north and south content panel
        JPanel nPanel = new JPanel();
        JPanel sPanel = new JPanel();
        
        // create center center content panel to contain data
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createLineBorder(Color.black));
        contentPane.setBackground(Color.white);
        
        // arrange the panels 
        add(contentPane, "Center");
        add(nPanel, "North");
        add(sPanel, "South");
        
        // create buttons
        newWindowBtn = new JButton("New Window");
        JButton search_Button = new JButton("Search");
        JButton bookAcar_Button = new JButton("Make a reservation");
        JButton backToMenu_Button = new JButton("Back to main menu");
        
        // Top panel
        nPanel.add(search_Button);
        nPanel.add(bookAcar_Button);
        nPanel.add(newWindowBtn);
        
        // Bottom panel
        sPanel.add(backToMenu_Button);
        
        pack();
        setVisible(true);
	}
	
	public void AddNewWindowListener(ActionListener nwl)
	{
		newWindowBtn.addActionListener(nwl);
	}
	
	public void buildNewWindow() {
		//Build a new test window
		JFrame newWindow = new JFrame("oh hai, I'm a new window");
		newWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		newWindow.setSize(200, 200);
		newWindow.pack();
		newWindow.setVisible(true);
	}
}