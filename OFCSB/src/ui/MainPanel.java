package ui;

/*
 * Author: Alex Zhou
 * Date: 2018.10.26
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainPanel implements ActionListener
{
	private JLabel titleLabel;
	private JPanel mainMenuPanel;
	private JButton cashBtn, inventoryBtn;
	private ImageIcon buttonImage;
	
	//constructor
	public MainPanel()
	{
		//the image that is needed for the buttons
		buttonImage = new ImageIcon("Image/button.png");
		Font buttonFont = new Font("Arial", Font.PLAIN, 23);
		
		//overall panel
		mainMenuPanel = new JPanel();
		mainMenuPanel.setLayout(null);
		
		//setting for the label on the top of the main frame
		titleLabel = new JLabel("Ottawa Family Cinema Snack Bar");
		titleLabel.setOpaque(true);
		titleLabel.setBounds(0, 0, 650, 100);
		titleLabel.setFont(new Font("Arial", Font.PLAIN, 43));
		titleLabel.setBackground(Color.CYAN);
		mainMenuPanel.add(titleLabel);
		
		//jump to cash system if this button is clicked
		cashBtn = new JButton("Cash System");
		cashBtn.setBounds(225, 220, 200, 65);
		cashBtn.setFont(buttonFont);
		cashBtn.setIcon(buttonImage);
		cashBtn.setHorizontalTextPosition(JButton.CENTER);
		cashBtn.setVerticalTextPosition(JButton.CENTER);
		cashBtn.setContentAreaFilled(false);
		cashBtn.setBorderPainted(false);
		cashBtn.addActionListener(this);
		mainMenuPanel.add(cashBtn);
		
		//jump to inventory system if this button is clicked
		inventoryBtn = new JButton("Inventory System");
		inventoryBtn.setBounds(225, 300, 200, 65);
		inventoryBtn.setFont(buttonFont);
		inventoryBtn.setIcon(buttonImage);
		inventoryBtn.setHorizontalTextPosition(JButton.CENTER);
		inventoryBtn.setVerticalTextPosition(JButton.CENTER);
		inventoryBtn.setContentAreaFilled(false);
		inventoryBtn.setBorderPainted(false);
		inventoryBtn.addActionListener(this);
		mainMenuPanel.add(inventoryBtn);
		
		//add the main panel to the main frame and pack
		MainFrame.overallFrame.add(mainMenuPanel);
		MainFrame.overallFrame.pack();
		MainFrame.overallFrame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == inventoryBtn)
		{
			//remove all the content on the main frame and load the inventory system
			MainFrame.overallFrame.getContentPane().removeAll();
			MainFrame.overallFrame.repaint();
//			try
//			{
				new InventoryPanel();
//			}
//			catch (Exception e1)
//			{
//				JOptionPane.showMessageDialog(MainFrame.overallFrame, "You must select a product", "Error", JOptionPane.ERROR_MESSAGE);
//			}
		}
		
		if(e.getSource() == cashBtn)
		{
			//remove all the content on the main frame and load the cash system
		}
	}
}