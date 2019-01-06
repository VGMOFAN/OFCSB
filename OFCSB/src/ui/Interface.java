package ui;
/*
 * Description: This is the layout of the Cash System. 
 * Author:Suveatha K.
 * Date: Oct 22 2018
 */
import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import core.Menu;
import dao.ProductDAO;

public class Interface implements ActionListener {

	ProductDAO p;
	List<Menu> allMenuItemsList = new ArrayList<>();
	Menu[] allMenuItems;
	JPanel contentsPanel;
	JLabel border, borderTwo;
	JButton transVoid, deleteInventory, editPrice, staffPrice, totalButton;
	ImageIcon spButton1;
	int colourChange, test, testOne, candi ;
	double price1, price2, price3, price4, price5, total;
	String name;
	JTextArea area2, totalArea;
	List<Menu> smdrinksList = new ArrayList<>();
	List<Menu> lgdrinksList = new ArrayList<>();
	List<Menu> popcornList = new ArrayList<>();
	List<Menu> hotdogList = new ArrayList<>();
	List<Menu> candyList = new ArrayList<>();
	List<Menu> transactionList = new ArrayList<>();
	Menu[] smdrinks, lgdrinks, hotdogs, popcorn, candy;
	private JButton[] smallDrinkButtons, largeDrinkButtons, hotdogButtons,
			popcornButtons, candyButtons;

	public Interface() {
		// Components of JFrame mainMenu

		DecimalFormat df = new DecimalFormat("#.##");
		df.setMaximumFractionDigits(2);
		
		try {
			p = new ProductDAO();
		} catch (Exception el) {
			el.printStackTrace();
		}


		
		Font f = new Font("Arial", Font.BOLD, 14);

		
		try {
			allMenuItemsList = p.getAllMenus();
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		//allMenuItems: all the menu items in an array
		allMenuItems = new Menu[allMenuItemsList.size()];
		allMenuItems = allMenuItemsList.toArray(allMenuItems);


		//This loop categorizes the different menu items by their category type into their coresponding category lists: Small drinks, large drinks, popcorn,hotdog, and candy 
		for (int i = 0; i < allMenuItemsList.size(); i++) 
		{
			//Separates into small drinks category list
			if (allMenuItemsList.get(i).getCategory()
					.compareToIgnoreCase("Small drink") == 0) {
				smdrinksList.add(allMenuItemsList.get(i));

			}
			//Separates into larges drinks category list 
			else if (allMenuItemsList.get(i).getCategory()
					.compareToIgnoreCase("Large drink") == 0) {
				lgdrinksList.add(allMenuItemsList.get(i));

			}
			//Separates into hotdog category list
			else if (allMenuItemsList.get(i).getCategory()
					.compareToIgnoreCase("Hotdog") == 0) {
				hotdogList.add(allMenuItemsList.get(i));

			//Separates into popcorn category list 
			} else if (allMenuItemsList.get(i).getCategory()
					.compareToIgnoreCase("Popcorn") == 0) {
				popcornList.add(allMenuItemsList.get(i));

			//Separates into candy category list
			}else if (allMenuItemsList.get(i).getCategory()
					.compareToIgnoreCase("Candy") == 0) {
				candyList.add(allMenuItemsList.get(i));

			}
			

		}
		//Casts the small drink category list into individual category arrays
		smdrinks = new Menu[smdrinksList.size()];
		smdrinks = smdrinksList.toArray(smdrinks);
		
		//Casts the large drink category list into individual category arrays
		lgdrinks = new Menu[lgdrinksList.size()];
		lgdrinks = lgdrinksList.toArray(lgdrinks);
		
		//Casts the popcorn category list into individual category arrays
		popcorn = new Menu[popcornList.size()];
		popcorn = popcornList.toArray(popcorn);
		
		//Casts the hotdog category list into individual category arrays
		hotdogs = new Menu[hotdogList.size()];
		hotdogs = hotdogList.toArray(hotdogs);
		
		//Casts the candy category list into individual category arrays
		candy = new Menu[candyList.size()];
		candy = candyList.toArray(candy);

		// contentsPanel(JPanel): This is the main panel that has all the panels
		// inside it.
		contentsPanel = new JPanel();
		contentsPanel.setBackground(new Color(224, 224, 224));
		contentsPanel.setBounds(0, 0, 1900, 1000);

		// border (JLabel): This displays the navy blue,black and white panel
		// borders of the display
		ImageIcon icon1 = new ImageIcon("Images/border.png");
		JLabel border = new JLabel();
		border.setIcon(icon1);
		contentsPanel.add(border);

		// editPrice(JButton): This button allows for access to edit the price
		// of a particular product
		editPrice = new JButton(new ImageIcon("Images/editP.png"));
		editPrice.addActionListener(this);
		editPrice.setBounds(950, 600, 300, 100);
		editPrice.setOpaque(false);
		editPrice.setBackground(new Color(150, 0, 0));
		editPrice.setBorder(null);
		border.add(editPrice);

		// deleteInventory(JButton): This allows access to on the spot delete an
		// item from inventory if the product was not sold and was a defect
		deleteInventory = new JButton(new ImageIcon("Images/deleteIn.png"));
		deleteInventory.addActionListener(this);
		deleteInventory.setBounds(950, 700, 300, 100);
		deleteInventory.setOpaque(false);
		deleteInventory.setBackground(new Color(150, 0, 0));
		deleteInventory.setBorder(null);
		border.add(deleteInventory);

		// staffPrice(JButton): This allows access to the menu of the staff
		// prices on products
		ImageIcon spButton1 = new ImageIcon("Images/staffP.png");
		staffPrice = new JButton(spButton1);
		staffPrice.addActionListener(this);
		staffPrice.setBounds(950, 800, 300, 100);
		staffPrice.setOpaque(false);
		staffPrice.setBackground(new Color(150, 0, 0));
		staffPrice.setBorder(null);
		border.add(staffPrice);

		// transVoid(JButton): This allows access to clear the transaction
		transVoid = new JButton(new ImageIcon("Images/transV.png"));
		transVoid.addActionListener(this);
		transVoid.setBounds(1500, 700, 300, 100);
		transVoid.setOpaque(false);
		transVoid.setBackground(new Color(150, 0, 0));
		transVoid.setBorder(null);
		border.add(transVoid);

		// totalButtonButton(JButton): This allows access to totalButton the
		// transaction
		totalButton = new JButton(new ImageIcon("Images/totaL.png"));
		totalButton.addActionListener(this);
		totalButton.setBounds(1500, 800, 300, 100);
		totalButton.setOpaque(false);
		totalButton.setBackground(new Color(150, 0, 0));
		totalButton.setBorder(null);
		border.add(totalButton);

		// ff(Font): Specific font properties for the string (larger)
		Font ff = new Font("Arial", Font.BOLD, 20);
		//fff(Font): Specific font properties for the string (smaller)
		Font fff = new Font("Arial", Font.BOLD, 18);
		
		//menuLabel (JLabel): Specific Label to show the products listed in the transaction.
		JLabel menuLabel = new JLabel("Product");
		menuLabel.setBounds(1320, 28, 100, 100);
		menuLabel.setOpaque(false);
		menuLabel.setFont(fff);
		menuLabel.setBackground(new Color(150, 0, 0));
		menuLabel.setBorder(null);
		border.add(menuLabel);

		//priceLabel(JLabel): Specific Label to show the products listed in the transaction 
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setBounds(1500, 28, 100, 100);
		priceLabel.setOpaque(false);
		priceLabel.setFont(fff);
		priceLabel.setBackground(new Color(150, 0, 0));
		priceLabel.setBorder(null);
		border.add(priceLabel);

		//area2(JTextArea): This is the area where the totaling 
		area2 = new JTextArea();
		area2.setBounds(360, 225, 230, 300);
		area2.setLineWrap(true);
		area2.setWrapStyleWord(true);
		area2.setFont(new Font("Georgia", Font.PLAIN, 15));
		area2.setEditable(false);
		border.add(area2);
		JScrollPane scrollPane = new JScrollPane(area2);
		scrollPane.setBounds(1300, 100, 600, 540);
		border.add(scrollPane);

		totalArea = new JTextArea();
		totalArea.setBounds(1700, 625, 180, 50);
		totalArea.setLineWrap(true);
		totalArea.setWrapStyleWord(true);
		totalArea.setFont(ff);
		totalArea.setEditable(false);
		border.add(totalArea);

		// dummy small drinks

		// buttons for small drinks
		smallDrinkButtons = new JButton[smdrinksList.size()];

		// Adding small drinks buttons to panel
		for (int i = 0; i < smallDrinkButtons.length; i++) // From i is 0 to 15
		{

			smallDrinkButtons[i] = new JButton("<html>" + smdrinks[i].getName()
					+ "<br> $" + smdrinks[i].getPrice());

			smallDrinkButtons[i].setBounds(100, (120 * i) + 100, 20, 20);
			smallDrinkButtons[i].setSize(100, 100);
			int smDrinkInt = i;
			smallDrinkButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					test = smDrinkInt;
					price1 = (smdrinks[test].getPrice());
					name = smdrinks[test].getName();
					
					transactionList.add(smdrinks[test]);
				
					
					total = total + price1;
					area2.append("\n\n      " + name
							+ "                                $ " + price1);

					
					totalArea.setText("\nTotal: $" +  String.format("%,.2f", total));
					
					

				}
			}

			);
			border.add(smallDrinkButtons[i]);

		}

		JLabel smdrinkstitle = new JLabel("Small drinks");
		smdrinkstitle.setFont(f);
		smdrinkstitle.setBounds(110, 50, 100, 50);
		border.add(smdrinkstitle);

		// dummy large drinks
		/*
		 * lgdrinks = new Menu [3]; lgdrinks [0]= new Menu ("root beer",
		 * "small drink",0, 1.75, 1.00, 0); lgdrinks [1]= new Menu ("lemonade",
		 * "small drink",1, 1.75, 1.00, 0); lgdrinks[2]= new Menu ("coke",
		 * "small drink",2, 1.75, 1.00, 0);
		 */

		// buttons for large drinks
		largeDrinkButtons = new JButton[lgdrinksList.size()];

		// Adding large drinks to panel
		for (int i = 0; i < largeDrinkButtons.length; i++) // From i is 0 to 15
		{
			largeDrinkButtons[i] = new JButton("<html>" + lgdrinks[i].getName()
					+ "<br> $" + lgdrinks[i].getPrice());
			largeDrinkButtons[i].setBounds(250, (120 * i) + 100, 20, 20);
			largeDrinkButtons[i].setSize(100, 100);
			int lgDrinkInt = i;
			largeDrinkButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					test = lgDrinkInt;
					price2 = (lgdrinks[test].getPrice());
					
					transactionList.add(lgdrinks[test]);
				
					
					name = lgdrinks[test].getName();
					total = total + price2;
					area2.append("\n\n      " + name
							+ "                                $ " + price2);

					
					totalArea.setText("\nTotal: $" +  String.format("%,.2f", total));

				}
			}

			);
			border.add(largeDrinkButtons[i]);
		}

		JLabel lgdrinkstitle = new JLabel("Large drinks");
		lgdrinkstitle.setFont(f);
		lgdrinkstitle.setBounds(255, 50, 100, 50);
		border.add(lgdrinkstitle);

		// dummy hotdog
		/*
		 * hotdogs = new Menu [3]; hotdogs[0]= new Menu ("jumbo", "hotdog",0,
		 * 1.75, 1.00, 0); hotdogs [1]= new Menu ("regular", "hotdog",1, 1.75,
		 * 1.00, 0);
		 */

		// buttons for hotdog
		hotdogButtons = new JButton[hotdogList.size()];

		// Adding hotdog Buttons
		for (int i = 0; i < hotdogButtons.length; i++) // From i is 0 to 15
		{
			hotdogButtons[i] = new JButton("<html>" + hotdogs[i].getName()
					+ "<br> $" + hotdogs[i].getPrice());
			hotdogButtons[i].setBounds(400, (120 * i) + 100, 20, 20);
			hotdogButtons[i].setSize(100, 100);
			int hotdogInt = i;
			hotdogButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					test = hotdogInt;
					
					transactionList.add(hotdogs[test]);
	
					
					price3 = (hotdogs[test].getPrice());
					name = hotdogs[test].getName();
					total = total + price3;
					area2.append("\n\n      " + name
							+ "                                $ " + price3);

					
					totalArea.setText("\nTotal: $" +  String.format("%,.2f", total));

				}
			}

			);
			border.add(hotdogButtons[i]);
		}

		// this labels the hotdog categorey
		JLabel hotdogtitle = new JLabel("Hotdogs");
		hotdogtitle.setFont(f);
		hotdogtitle.setBounds(423, 50, 100, 50);
		border.add(hotdogtitle);

		// dummy popcorn list
		/*
		 * popcorn= new Menu [3]; popcorn [0]= new Menu ("large",
		 * "popcorn",0,1.75, 0); popcorn [1]= new Menu ("medium", "popcorn",1,
		 * 1.75, 0); popcorn[2]= new Menu ("small", "popcorn",2, 1.75, 1.00, 0);
		 */

		// buttons for popcorn
		popcornButtons = new JButton[popcornList.size()];

		// Adding popcorn to panel
		for (int i = 0; i < popcornButtons.length; i++) // From i is 0 to 15
		{
			popcornButtons[i] = new JButton("<html>" + popcorn[i].getName()
					+ "<br> $" + popcorn[i].getPrice());

			int popcornInt = i;
			popcornButtons[i].setBounds(550, (120 * i) + 100, 20, 20);
			popcornButtons[i].setSize(100, 100);
			popcornButtons[i].addActionListener(new ActionListener()

			{
				public void actionPerformed(ActionEvent e) {
					test = popcornInt;
					
					
					transactionList.add(popcorn[test]);
					
					
					price4 = (popcorn[test].getPrice());
					name = popcorn[test].getName();
					total = total + price4;
					area2.append("\n\n      " + name
							+ "                                $ " + price4);

					
					totalArea.setText("\nTotal: $" +  String.format("%,.2f", total));

				}
			}

			);
			border.add(popcornButtons[i]);
		}

		// Label for popcorn category
		JLabel popcorntitle = new JLabel("Popcorn");
		popcorntitle.setFont(f);
		popcorntitle.setBounds(573, 50, 100, 50);
		border.add(popcorntitle);

		// dummy class of candy
		/*
		 * candy= new Menu [3]; candy [0]= new Menu ("skittles", "candy",0,
		 * 1.75,0, 0); candy [1]= new Menu ("twizzlers", "candy",1, 1.75,1.00,
		 * 0); candy[2]= new Menu ("chocolate", "candy",2, 1.72, 1.00, 0);
		 */

		// buttons for candy
		candyButtons = new JButton[candyList.size()];

		// Adding candy to panel
		for (int i = 0; i < candyButtons.length; i++) // From i is 0 to 15
		{

			candyButtons[i] = new JButton();
			candyButtons[i].setText("<html>" + candy[i].getName() + "<br> $"
					+ candy[i].getPrice());

			candyButtons[i].setBounds(700, (120 * i) + 100, 20, 20);
			candyButtons[i].setSize(100, 100);
			candyButtons[i].addActionListener(this);

			int candi = i;

			candyButtons[i].addActionListener(new ActionListener()

			{
				public void actionPerformed(ActionEvent e) {
					test = candi;
					
					
					transactionList.add(candy[test]);
					
					price5 = (candy[test].getPrice());
					name = candy[test].getName();
					total = total + price5;
					area2.append("\n\n      " + name
							+ "                                $ " + price5);
					
				
			
					
					totalArea.setText("\nTotal: $" +  String.format("%,.2f", total));
				}
			}

			);
			border.add(candyButtons[i]);
		}

		JLabel candytitle = new JLabel("Candy");
		candytitle.setFont(f);
		candytitle.setBounds(730, 50, 100, 50);
		border.add(candytitle);

		MainFrame.overallFrame.add(contentsPanel);
		MainFrame.overallFrame.pack();
	}





	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == transVoid)
		{
			TransactionVoid tv = new TransactionVoid();
			tv.setVisible(true);
			tv.setJTextArea(area2);
			tv.setTransactionListCopyTV(transactionList);
			area2 = tv.getDuplicateArea();
			tv.setJTextArea2(totalArea);
			totalArea = tv.getNewArea();
		if (tv.cleared()==1)
		{
			transactionList =tv.getTransactionListCopyTV(); 
			System.out.print("hi " +transactionList);
			
		}
		
		}

		if (e.getSource() == totalButton) {
			Total t = new Total();
			t.setVisible(true);
			System.out.println(total);
			t.setDebitTotal(total);
			t.setTransactionListCopy(transactionList);
			//t.cleared(t.getClear());
		}

		if (e.getSource() == editPrice) {
			EditPrice ep = null;
			ep = new EditPrice();
			ep.setVisible(true);
		}

		if (e.getSource() == deleteInventory) {
			DeleteFromInventory dfi = new DeleteFromInventory();
			dfi.setVisible(true);
		}

		// spButton2: in menu of staff products (yellow)
		ImageIcon spButton2 = new ImageIcon("Images/staffP2.png");
		// spButton3: not in menu of staff products (red)
		ImageIcon spButton3 = new ImageIcon("Images/staffP.png");

		// Conditional Statement: adds to the numberount of how many times the
		// staff price buttons to variable as it is clicked
		if (e.getSource() == staffPrice) {
			colourChange++;
			// Conditional Statement: Depending on the number of clicks,
			// alternates colours and therefore alternates menus
			if (!(colourChange % 2 == 0)) {
				staffPrice.setIcon(spButton2);
				for (int i = 0; i < candy.length; i++) {
					candyButtons[i].setText("<html>" + candy[i].getName()
							+ "<br> $" + candy[i].getEmployeePrice());
					
				}
				
				for (int i = 0; i < smdrinks.length; i++) {
					smallDrinkButtons[i].setText("<html>" + candy[i].getName()
							+ "<br> $" + candy[i].getEmployeePrice());
					
				}
				
				for (int i = 0; i < lgdrinks.length; i++) {
					largeDrinkButtons[i].setText("<html>" + candy[i].getName()
							+ "<br> $" + candy[i].getEmployeePrice());
					
				}

				for (int i = 0; i < hotdogs.length; i++) {
					hotdogButtons[i].setText("<html>" + hotdogs[i].getName()
							+ "<br> $" + hotdogs[i].getEmployeePrice());
				}

			} else {
				staffPrice.setIcon(spButton3);
				for (int i = 0; i < candy.length; i++) {
					candyButtons[i].setText("<html>" + candy[i].getName()
							+ "<br> $" + candy[i].getPrice());
				}
				for (int i = 0; i < hotdogs.length; i++) {
					hotdogButtons[i].setText("<html>" + hotdogs[i].getName()
							+ "<br> $" + hotdogs[i].getPrice());
				}
				for (int i = 0; i < smdrinks.length; i++) {
					smallDrinkButtons[i].setText("<html>" + candy[i].getName()
							+ "<br> $" + candy[i].getPrice());
					
				}
				
				for (int i = 0; i < lgdrinks.length; i++) {
					largeDrinkButtons[i].setText("<html>" + candy[i].getName()
							+ "<br> $" + candy[i].getPrice());
					
				}
			}

		}

	}

}
