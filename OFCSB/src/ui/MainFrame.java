package ui;

/*
 * Autoher: Alex Zhou
 * Date: 2018.10.26
 */

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame
{
	public static JFrame overallFrame;
	
	//main method
	public static void main(String[] args)
	{
		new MainFrame();
	}
	
	//constructor
	public MainFrame()
	{
		//settings for the overall frame of the system
		overallFrame = new JFrame("Ottawa Family Cinema Snack Bar System");
		overallFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		overallFrame.setPreferredSize(new Dimension(1800,1000));
		overallFrame.setLayout(new BorderLayout());
		overallFrame.setResizable(false);
		
		new MainPanel();
	}
}