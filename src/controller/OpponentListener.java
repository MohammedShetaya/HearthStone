package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.HeroTargetSpell;
import view.CardView;
import view.CurrentHeroView;
import view.OpponentView;

public class OpponentListener implements ActionListener{

	
	controller controller ; 
	public OpponentListener(controller controller ) {
		// TODO Auto-generated constructor stub
		this.controller = controller; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		OpponentView a = (OpponentView) e.getSource() ; 

				
		if (controller.getAttacker() != null) {

			CardView c = (CardView) controller.getAttacker() ; 

			if (c.getC() instanceof HeroTargetSpell ) {
				
				controller.playCard(c.getC(), a.getH());
			}
			else {
				JFrame j = new JFrame() ;
				JOptionPane.showMessageDialog(j, "Invalid Target");
			}
		}
		else if (controller.getFieldMinionAttacker() != null ) {
			
			CardView c = (CardView) controller.getFieldMinionAttacker() ; 

			controller.attackHero((Minion) c.getC() ,a.getH() );
		}
		else if (controller.getHeroPowerInitialized() != null ) {
			
			CurrentHeroView c = (CurrentHeroView) controller.getHeroPowerInitialized() ; 
			controller.heroPower(c.getH() , a.getH());
		}
	}

}
