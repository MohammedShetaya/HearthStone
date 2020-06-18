package controller;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import engine.Game;
import engine.GameListener;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Mage;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import view.CardView;
import view.CurrentHeroView;
import view.EndTurnButton;
import view.GameView;
import view.OpponentView;
import view.UseHeroPowerButton;

public class controller implements ActionListener , GameListener {


	private Game model ;

	private GameView gameView ;

	private boolean startedGame = false ;
	private boolean chooseHeroPage = false ;

	private Hero firstHero ;
	private Hero secondHero;


	//game buttons 
	private ArrayList<JButton> firstField ; 
	private ArrayList<JButton> firstHand ;
	private JButton currentHero ;

	private ArrayList<JButton> secondField;
	private JButton oppenentHero ; 

	private JButton currentHeroPower ;

	private JButton endTurn ;


	private JButton attacker ; 
	private JButton fieldMinionAttacker ;
	private JButton heroPowerInitialized ;

	//game buttons listeners
	private CurrHeroHandListener currHeroHandListener ;
	private CurrHeroFieldListener 	currHeroFieldListener ; 
	private CurrentHeroListener currentHeroListener ;

	private OpponentListener opponentListener ; 
	private OpponentFieldListener opponentFieldListener ;
	private UseHeroPowerListener useHeroPowerListener ; 
	
	private EndTurnListener endTurnListener ; 

	public controller () {


		gameView = new GameView() ;
		gameView.startPage();

		gameView.getStartGame().addActionListener(this);
		gameView.getExit().addActionListener(this);

		gameView.getHunter().addActionListener(this);
		gameView.getMage().addActionListener(this);
		gameView.getPaladin().addActionListener(this);
		gameView.getPriest().addActionListener(this);
		gameView.getWarlock().addActionListener(this);

		//initialization of instances 
		firstField = new ArrayList<JButton>(); 
		firstHand = new ArrayList<JButton>()  ;

		secondField = new ArrayList<JButton>() ;				


		//listeners initialisations 

		currHeroFieldListener = new CurrHeroFieldListener(this) ; 
		currHeroHandListener = new CurrHeroHandListener(this); 
		currentHeroListener = new  CurrentHeroListener(this) ;

		opponentListener = new OpponentListener(this) ; 
		opponentFieldListener = new OpponentFieldListener(this) ; 

		useHeroPowerListener = new UseHeroPowerListener(this) ;

		endTurnListener = new EndTurnListener(this ) ; 




		gameView.revalidate();
		gameView.repaint();

	}




	@Override
	public void actionPerformed(ActionEvent e) {

		if (!startedGame) {

			if(e.getSource() == gameView.getExit()) {
				System.exit(0) ; 
			}
			else if(e.getSource() == gameView.getStartGame() ) {

				this.startedGame = true ;
				this.gameView.getContentPane().removeAll(); 
				this.gameView.repaint();  
				this.gameView.chooseHeroPage();
			}
		}


		else if (! chooseHeroPage) {


			try {


				if(firstHero==null) {

					if(e.getSource()==gameView.getHunter()) {	
						firstHero=new Hunter();

					}

					else if(e.getSource()==gameView.getMage()) {

						firstHero=new Mage();
					}

					else if(e.getSource()==gameView.getPaladin()){

						firstHero=new Paladin();

					}

					else if(e.getSource()==gameView.getPriest()) {

						firstHero=new Priest();

					}

					else {

						firstHero=new Warlock();

					}

					gameView.getChooseHeroLabel().setText("PLEASE SELECT THE SECOND HERO");
				}

				else {

					if(e.getSource()==gameView.getHunter()) {
						secondHero=new Hunter();
					}
					else if(e.getSource()==gameView.getMage()) {					

						secondHero=new Mage();

					}
					else if(e.getSource()==gameView.getPaladin()){

						secondHero=new Paladin();

					}
					else if(e.getSource()==gameView.getPriest()) {

						secondHero=new Priest();
					}
					else {

						secondHero=new Warlock();
					}

					try {
						this.model = new Game(firstHero,secondHero) ;
						this.model.setListener(this);

					} catch (FullHandException e1) {
						JFrame j = new JFrame() ;
						JOptionPane.showMessageDialog(j, "Full Hand");
					}

					chooseHeroPage = true ; 

					this.render();
				}


			}
			catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}		


	public void endTurn ()  {


		try {
			model.endTurn();
		} catch (FullHandException | CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Full Hand");
		}

		this.render();


	}


	public void playCard (Card c) {

		try {

			if (c instanceof Minion ) {
				model.getCurrentHero().playMinion((Minion) c );

			}
			else {
				if(c instanceof FieldSpell) {
					model.getCurrentHero().castSpell((FieldSpell)c);

				}
				else if (c instanceof AOESpell ) {
					model.getCurrentHero().castSpell((AOESpell) c ,model.getOpponent().getField());
				}

			}

		}

		catch (NotYourTurnException e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Not Your Turn");
		}
		catch ( NotEnoughManaException e) {
			// TODO: handle exception
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Not Enough Mana");
		}
		catch (FullFieldException e) {
			// TODO: handle exception
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Full Field");
		}

		this.attacker = null ; 

		this.render();

	}


	public void playCard (Card c , Minion m )  {

		try {


			if (c instanceof MinionTargetSpell) {

				model.getCurrentHero().castSpell((MinionTargetSpell) c , m);

			}
			else if (c instanceof LeechingSpell) {

				model.getCurrentHero().castSpell((LeechingSpell) c, m);
			}


		}

		catch (NotYourTurnException e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Not Your Turn");
		}
		catch ( NotEnoughManaException e) {
			// TODO: handle exception
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Not Enough Mana");
		}
		catch (InvalidTargetException e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Invalid Target");
		}

		this.attacker = null ; 

		this.render();

	}


	public void playCard (Card c , Hero m )  {

		try {

			model.getCurrentHero().castSpell((HeroTargetSpell)c,m);

		}
		catch (NotYourTurnException e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Not Your Turn");
		}
		catch ( NotEnoughManaException e) {
			// TODO: handle exception
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Not Enough Mana");
		}

		this.attacker = null ; 

		this.render();

	}

	public void  attackMinion (Minion c , Minion a ) {

		try {
			model.getCurrentHero().attackWithMinion(c, a);
		} 
		catch (CannotAttackException  e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Cannot Attack");
		}
		catch ( NotYourTurnException e) {
			// TODO: handle exception
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Not Your Turn");
		}
		catch (TauntBypassException e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "The Opponent Has A Taunt Card");
		}
		catch (InvalidTargetException e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Invalid Target");
		}
		catch ( NotSummonedException e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "This Card Is In Hand");
		}

		this.fieldMinionAttacker = null ;

		this.render();

	}


	public void attackHero (Minion c , Hero h ) {
		try {
			model.getCurrentHero().attackWithMinion(c, h);
		} 
		catch (CannotAttackException  e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Cannot Attack");
		}
		catch ( NotYourTurnException e) {
			// TODO: handle exception
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Not Your Turn");
		}
		catch (TauntBypassException e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "The Opponent Has A Taunt Card");
		}
		catch (InvalidTargetException e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Invalid Target");
		}
		catch ( NotSummonedException e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "This Card Is In Hand");
		}

		this.fieldMinionAttacker = null ;
		this.render(); 
	} 



	public void heroPower (Hero h){

		
			try {
				h.useHeroPower();
			}
			
			catch ( NotYourTurnException e) {
				// TODO: handle exception
				JFrame j = new JFrame() ;
				JOptionPane.showMessageDialog(j, "Not Your Turn");
			}
			catch (NotEnoughManaException e) {
				JFrame j = new JFrame() ;
				JOptionPane.showMessageDialog(j, "Not Enough Mana");
			} catch (HeroPowerAlreadyUsedException e) {

				JFrame j = new JFrame() ;
				JOptionPane.showMessageDialog(j, "Hero Power Alredy Used");
			} catch (FullHandException e) {

				JFrame j = new JFrame() ;
				JOptionPane.showMessageDialog(j, "Full Hand");
			} catch (FullFieldException e) {
				JFrame j = new JFrame() ;
				JOptionPane.showMessageDialog(j, "Full Field");
				
			} catch (CloneNotSupportedException e) {
				
			}
		
			this.heroPowerInitialized = null ; 
			this.render();
	}
	
	public void heroPower (Hero h , Minion m) {
		
		try {
		if ( h instanceof Mage ) {
			Mage a = (Mage) h ; 
			
				a.useHeroPower(m);
			
		}
		else if (h instanceof Priest ) {
			Priest a = (Priest) h ;
			a.useHeroPower(m);
 		}
		
		}
		catch ( NotYourTurnException e) {
			// TODO: handle exception
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Not Your Turn");
		}
		catch (NotEnoughManaException e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Not Enough Mana");
		} catch (HeroPowerAlreadyUsedException e) {

			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Hero Power Alredy Used");
		} catch (FullHandException e) {

			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Full Hand");
		} catch (FullFieldException e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Full Field");
			
		} catch (CloneNotSupportedException e) {
			
		}
	

		this.heroPowerInitialized = null ; 
		this.render();  
	}
	

	public void heroPower (Hero h , Hero m) {
		
		try {
			
		if ( h instanceof Mage ) {
			
			Mage a = (Mage) h ; 
			
				a.useHeroPower(m);
			
		}
		else if (h instanceof Priest ) {
			Priest a = (Priest) h ;
			a.useHeroPower(m);
 		}
		
		}
		catch ( NotYourTurnException e) {
			// TODO: handle exception
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Not Your Turn");
		}
		catch (NotEnoughManaException e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Not Enough Mana");
		} catch (HeroPowerAlreadyUsedException e) {

			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Hero Power Alredy Used");
		} catch (FullHandException e) {

			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Full Hand");
		} catch (FullFieldException e) {
			JFrame j = new JFrame() ;
			JOptionPane.showMessageDialog(j, "Full Field");
			
		} catch (CloneNotSupportedException e) {
			
		}
	

		this.heroPowerInitialized = null ; 
		this.render();  
	}
	
	

	@Override
	public void onGameOver() {


		if(model.getOpponent().getCurrentHP() == 0 )
			gameView.gameOver(model.getCurrentHero());
		else
			gameView.gameOver(model.getOpponent());


	}



	public void render() {

		if (model.getCurrentHero().getCurrentHP() >0 && model.getOpponent().getCurrentHP()>0) {

			gameView.getContentPane().removeAll(); 
			gameView.repaint(); 

			gameView.GamePage();

			// reinitializing the curr hero field 

			for (int i = 0; i < model.getCurrentHero().getField().size() ; i++ ) {

				CardView a = new CardView(model.getCurrentHero().getField().get(i)) ;
				a.addActionListener(currHeroFieldListener);
				firstField.add(a) ;
				this.gameView.getFirstHeroFiled().add(a) ;
			}

			//reinitializing the curr hero hand 
			for (int i = 0 ; i< model.getCurrentHero().getHand().size() ; i ++ ) {

				CardView a = new CardView(model.getCurrentHero().getHand().get(i)) ;
				a.addActionListener(currHeroHandListener);
				firstHand.add(a) ;
				this.gameView.getFirstHeroHand().add(a) ;
			}

			//initialization of opponent field 

			for (int i = 0 ; i < model.getOpponent().getField().size() ; i ++ ) {
				CardView a = new CardView(model.getOpponent().getField().get(i)) ;
				a.addActionListener(opponentFieldListener);
				secondField.add(a) ;
				this.gameView.getSecondHeroField().add(a) ;
			}		

			// initialize curr hero button 
			CurrentHeroView d = new CurrentHeroView(model.getCurrentHero()) ; 
			d.addActionListener(currentHeroListener);				
			gameView.getFirstHeroImage().add(d) ;
			this.currentHero = d ;


			//initialize opponent hero button 		
			OpponentView o = new OpponentView(model.getOpponent()) ;
			o.addActionListener(opponentListener);
			gameView.getSecondHeroImage().add(o) ;
			this.oppenentHero = o ; 


			// initialization of use power button 
			UseHeroPowerButton b = new UseHeroPowerButton("Use Power" , model.getCurrentHero()) ;
			gameView.getFirstHeroImage().add(b) ;
			b.addActionListener(useHeroPowerListener);
			this.currentHeroPower = b ; 

			// initialization of end turn button 
			EndTurnButton t = new EndTurnButton() ; 
			gameView.getFirstHeroImage().add(t) ; 
			t.addActionListener(endTurnListener);
			this.endTurn = t ;		


			gameView.setVisible(true);

		}
	}




	public JButton getCurrentHero() {
		return currentHero;
	}




	public void setCurrentHero(JButton currentHero) {
		this.currentHero = currentHero;
	}




	public JButton getAttacker() {
		return attacker;
	}


	public void setAttacker(JButton attacker) {
		this.attacker = attacker;
	}


	public JButton getFieldMinionAttacker() {
		return fieldMinionAttacker;
	}




	public void setFieldMinionAttacker(JButton fieldMinionAttacker) {
		this.fieldMinionAttacker = fieldMinionAttacker;
	}

	public Game getModel () {
		return this.model ; 
	}

	public JButton getHeroPowerInitialized () {
		
		return heroPowerInitialized ;
	}
	
	public void setHeroPowerInitialized (JButton HeroPowerInitialized) {
		this.heroPowerInitialized = HeroPowerInitialized ;
	}

	public static void main(String[]args) {
		new controller();
	}




}








