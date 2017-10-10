/**
 * Top Level GUI Class
 */ 
package storemanager;

import java.awt.Frame;
import javax.swing.*;

public class StoreManagerGUI
{
private:
	Jframe frame;
public:
	StoreManagerGUI() {
		frame = new Jframe("StoreManagerGUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label = new JLabel("Store Manager");
        frame.getContentPane().add(label);
	}

	static void displayGUI()
	{
		frame.pack();
		frame.setVisible();
	}
}