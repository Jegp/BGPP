package main.view;
import javax.swing.*;
import java.awt.event.*;

/**
 * A cancel button for use in the view. The cancel button automatically
 * disposes the parent when pressed.
 */
public class CancelButton extends JButton {
	
	/**
	 * The parent frame.
	 */
	private JFrame cParent;
	
	/**
	 * @param cParent The frame that acts as parent for this button.
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
	
	/**
	 * Dispose the parent.
	 */
	private void cancel(){
		cParent.dispose();
	}

}
