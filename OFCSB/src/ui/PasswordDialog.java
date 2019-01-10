package ui;

/*
 * Author: Alex Zhou
 * Date: 2018.12.25
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class PasswordDialog extends JDialog implements ActionListener
{
	private final String password = "ofcsb";
	private JPanel passPanel;
	private JPasswordField passField;
	private JLabel label;
	private JButton verifyBtn, cancelBtn;
	private boolean isRight = false;
	
	public PasswordDialog()
	{
		//the image that is needed for the buttons
		ImageIcon buttonImage = new ImageIcon("Images/Button.png");
				
		//the font for buttons
		Font buttonFont = new Font("Arial", Font.PLAIN, 30);
		
		this.setModal(true);
		this.setTitle("Vertification");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(300, 200));
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		
		passPanel = new JPanel();
		passPanel.setLayout(null);
		
		label = new JLabel("Password:");
		label.setBounds(0, 20, 70, 33);
		passPanel.add(label);
		
		passField = new JPasswordField(10);
		passField.setBounds(80, 20, 100, 33);
		passField.setEchoChar('*');
		passPanel.add(passField);
		
		verifyBtn = new JButton(buttonImage);
		verifyBtn.setBounds(20, 100, 70, 33);
		verifyBtn.setText("Verify");
		verifyBtn.setFont(buttonFont);
		verifyBtn.setHorizontalTextPosition(JButton.CENTER);
		verifyBtn.setVerticalTextPosition(JButton.CENTER);
		verifyBtn.setContentAreaFilled(false);
		verifyBtn.setBorderPainted(false);
		verifyBtn.addActionListener(this);
		passPanel.add(verifyBtn);
		
		cancelBtn = new JButton(buttonImage);
		cancelBtn.setBounds(100, 100, 70, 33);
		cancelBtn.setText("Cancel");
		cancelBtn.setFont(buttonFont);
		cancelBtn.setHorizontalTextPosition(JButton.CENTER);
		cancelBtn.setVerticalTextPosition(JButton.CENTER);
		cancelBtn.setContentAreaFilled(false);
		cancelBtn.setBorderPainted(false);
		cancelBtn.addActionListener(this);
		passPanel.add(cancelBtn);
		
		this.add(passPanel);
		this.pack();
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == verifyBtn)
		{
			String pass = String.valueOf(passField.getPassword());
			
			if(pass.compareTo(password) == 0)
			{
				isRight = true;
				this.dispose();
			}
			else
			{
				isRight = false;
				JOptionPane.showMessageDialog(this, "Wrong password!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(e.getSource() == cancelBtn)
		{
			this.dispose();
		}
	}
	
	public boolean getIsRight()
	{
		return isRight;
	}
	
	public void resetRight()
	{
		isRight = false;
	}
	
	public void clear()
	{
		passField.setText("");
	}
}
