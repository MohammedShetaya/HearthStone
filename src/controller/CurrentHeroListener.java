package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.cards.spells.HeroTargetSpell;
import view.CardView;
import view.CurrentHeroView;

public class CurrentHeroListener implements ActionListener{
	
	controller controller ; 
	public CurrentHeroListener(controller controller ) {
		// TODO Auto-generated constructor stub
		this.controller = controller ;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 CurrentHeroView a = (CurrentHeroView) e.getSource() ;
		 
		 if (controller.getAttacker() != null ) {
			 CardView c = (CardView) controller.getAttacker() ; 
			 if (c instanceof HeroTargetSpell) {
				 controller.playCard(c.getC(), a.getH() );
			 }
			 else {
					JFrame j = new JFrame() ;
					JOptionPane.showMessageDialog(j, "Invalid Target");
			 }
		 }
		 else if (controller.getHeroPowerInitialized() != null ) {
				
				CurrentHeroView c = (CurrentHeroView) controller.getHeroPowerInitialized() ;
				
				controller.heroPower(c.getH() , a.getH());
			}
		 
	}

}
