package main.view;

import javax.swing.*;
import java.awt.event.*;

/**
 * A test window
 * @author sunedebel
 *
 */
public class TestWindow extends JFrame {
	
	public TestWindow() {
		//Build a new test window
		super("oh hai, I'm a new window");
		setSize(200, 200);
		
		pack();
		setVisible(true);
	}
	
	
	
	public void addBtn(JButton button) {
		add(button);
	}
}
