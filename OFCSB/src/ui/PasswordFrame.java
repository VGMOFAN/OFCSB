package ui;

/*
 * Author: Alex Zhou
 * Date: 2018.12.25
 */

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class PasswordFrame extends JFrame
{
	private JFrame passFrame;
	private JPanel passPanel;
	private JPasswordField passField;
	
	public PasswordFrame()
	{
		passFrame = new JFrame("Vertification");
		passFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		passFrame.setPreferredSize(new Dimension(650,500));
		passFrame.setLayout(new BorderLayout());
		passFrame.setResizable(false);
		
		passPanel = new JPanel();
		passPanel.setBorder(null);
		
		passField = new JPasswordField(10);
		passField.setBounds(20, 20, 100, 33);
		passField.setEchoChar('*');
		passPanel.add(passField);
		
		passFrame.add(passPanel);
		passFrame.pack();
		passFrame.setVisible(true);
	}
}
