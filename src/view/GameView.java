package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.heroes.Hero;

public class GameView  extends  JFrame {

	// page1 
	private JButton startGame ;
	private JButton exit ; 

	//page 2 
	private JLabel chooseHeroLabel ; 


	private JButton hunter;
	private JButton mage;
	private JButton warlock;
	private JButton paladin;
	private JButton priest;

	//page 2

	private JPanel firstHeroPart ;
	private JPanel secondHeroPart ; 

	private JPanel firstHeroFiled ;
	private JPanel secondHeroField ;

	private JPanel firstHeroImage ; 
	private JPanel secondHeroImage ; 

	private JPanel firstHeroHand ; 


	public 	GameView () {

		super () ;

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}


	public void startPage () {

		this.startGame = new JButton("start") ;


		this.exit = new JButton("Exit") ;

		this.setLayout(new BorderLayout());	

		JLabel l1 = new  JLabel("welcome to HearthStone") ;
		JPanel p1 = new JPanel() ; 
		p1.add(l1,BorderLayout.CENTER) ;

		JPanel p2 = new JPanel() ;
		p2.setLayout(new GridLayout(1,0));
		p2.setPreferredSize(new Dimension(p2.getWidth(),200));
		p2.add(startGame) ;
		p2.add(exit); 

		this.add(p1,BorderLayout.NORTH) ;
		this.add(p2,BorderLayout.SOUTH) ;


		this.hunter = new JButton("Hunter") ;
		this.mage = new JButton("Mage") ; 
		this.priest = new JButton("Priesst") ; 
		this.paladin = new JButton("Paladin") ; 
		this.warlock = new JButton("Warlock") ;


		this.setVisible(true);
	}

	public JPanel getFirstHeroPart() {
		return firstHeroPart;
	}


	public JPanel getSecondHeroPart() {
		return secondHeroPart;
	}


	public JPanel getFirstHeroFiled() {
		return firstHeroFiled;
	}


	public JPanel getSecondHeroField() {
		return secondHeroField;
	}


	public JPanel getFirstHeroImage() {
		return firstHeroImage;
	}


	public JPanel getSecondHeroImage() {
		return secondHeroImage;
	}


	public JPanel getFirstHeroHand() {
		return firstHeroHand;
	}




	public void chooseHeroPage () {

		this.setLayout(new GridLayout(0,1));

		chooseHeroLabel = new JLabel("PLEASE CHOOSE FIRST HERO") ; 
		JPanel p1 = new JPanel() ; 
		p1.add(chooseHeroLabel , BorderLayout.CENTER) ;

		this.add(p1) ;

		this.add(hunter) ;
		this.add(mage) ; 
		this.add(priest) ;
		this.add(paladin) ;
		this.add(warlock) ; 

		this.setVisible(true);

	}

	public void GamePage () {

		firstHeroPart = new JPanel() ;
		firstHeroFiled = new JPanel() ;
		firstHeroHand = new JPanel() ;
		firstHeroImage = new JPanel() ;

		secondHeroPart = new JPanel(); 
		secondHeroField = new JPanel() ;
		secondHeroImage = new JPanel() ;

		this.setLayout(new GridLayout(0,1));

		this.secondHeroPart.setLayout(new GridLayout(0,1));
		this.secondHeroPart.add(secondHeroImage); 
		this.secondHeroPart.add(secondHeroField) ;


		this.firstHeroPart.setLayout(new GridLayout(0,1));
		this.firstHeroPart.add(firstHeroFiled) ;
		this.firstHeroPart.add(firstHeroImage) ;
		this.firstHeroPart.add(firstHeroHand) ;

		this.secondHeroImage.setLayout(new FlowLayout());
		this.secondHeroField.setLayout(new FlowLayout());

		this.firstHeroFiled.setLayout(new FlowLayout());
		this.firstHeroImage.setLayout(new FlowLayout());
		this.firstHeroHand.setLayout(new FlowLayout());
		
		this.add(secondHeroPart) ;
        this.add(firstHeroPart) ; 
        this.setVisible(true);

	}
	
	public void gameOver (Hero h)  {
		
		this.getContentPane().removeAll();
		this.repaint();
		
		JLabel j = new JLabel(); 
		j.setText("WINNER >> " + h.getName() );
		this.add(j);
		
		this.setVisible(true);
	}



	public JButton getExit () {
		return this.exit ;
	}


	public JButton getStartGame() {
		return startGame;
	}


	public JButton getHunter() {
		return hunter;
	}


	public JButton getMage() {
		return mage;
	}


	public JButton getWarlock() {
		return warlock;
	}


	public JButton getPaladin() {
		return paladin;
	}


	public JButton getPriest() {
		return priest;
	}


	public JLabel getChooseHeroLabel() {
		return chooseHeroLabel;
	}
}
