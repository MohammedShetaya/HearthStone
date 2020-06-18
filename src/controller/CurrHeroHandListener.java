package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import exceptions.FullFieldException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import view.CardView;

public class CurrHeroHandListener implements ActionListener{

	controller controller ;  
	public CurrHeroHandListener(controller controller ) {
		// TODO Auto-generated constructor stub
		this.controller = controller ;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		CardView a = (CardView) e.getSource() ;
		
		controller.setAttacker(a);
		
		if ( (a.getC() instanceof Minion ) ||(a.getC() instanceof FieldSpell ) || (a.getC()  instanceof AOESpell ))  {
			controller.playCard(a.getC());
		}
		
	}

}
