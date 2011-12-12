package main.view;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CancelButton extends JButton {
	
	JFrame cParent;
	/**
	 * @param cParent The frame that acts as parent for this button
	 */
	public CancelButton(JFrame cParent) {
		super("Cancel");
		this.cParent = cParent;
		
		addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				cancel(); 
			}
		});		
	}
	
	private void cancel(){
		cParent.dispose();
	}

}
