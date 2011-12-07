package main.view;

import javax.swing.*;
import main.model.*;
import java.awt.*;

/**
 * The View of the booking-system.
 *
 */
public class View extends JFrame 
{

	public View(Model model) 
	{
<<<<<<< HEAD
=======
		
>>>>>>> branch 'master' of git@github.com:Jegp/BGPP.git
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
        JButton a_Button = new JButton("A Button");
        JButton search_Button = new JButton("Search");
        JButton bookAcar_Button = new JButton("Make a reservation");
        JButton backToMenu_Button = new JButton("Back to main menu");
        
        // Top panel
        nPanel.add(search_Button);
        nPanel.add(bookAcar_Button);
        nPanel.add(a_Button);
        
        // Bottom panel
        sPanel.add(backToMenu_Button);
        
        pack();
<<<<<<< HEAD
=======
        setVisible(true);
>>>>>>> branch 'master' of git@github.com:Jegp/BGPP.git
	}
	
	public void actionlistener()
	{
		System.out.println("trololol");
	}
}
