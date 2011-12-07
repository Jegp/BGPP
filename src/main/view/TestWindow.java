package main.view;

import javax.swing.*;

/**
 * A test window
 * @author sunedebel
 *
 */
public class TestWindow extends JFrame {
	
	public TestWindow() {
		//Build a new test window
		super("oh hai, I'm a new window");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(200, 200);
		pack();
		setVisible(true);
	}
}
