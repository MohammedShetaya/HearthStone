package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.cards.minions.Minion;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import view.CardView;
import view.CurrentHeroView;

public class CurrHeroFieldListener implements ActionListener {

	controller controller ;
	public CurrHeroFieldListener(controller controller) {

		this.controller = controller ;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		CardView a = (CardView) e.getSource();

		controller.setFieldMinionAttacker((JButton)e.getSource());

		if (controller.getAttacker() != null ) {

			CardView c = (CardView) controller.getAttacker() ; 

			if (c.getC() instanceof MinionTargetSpell || c.getC() instanceof LeechingSpell ) {					

				controller.playCard(c.getC() , (Minion) a.getC() );
			}
			else {
				JFrame j = new JFrame() ;
				JOptionPane.showMessageDialog(j, "Invalid Target");
			}
		}

		else if (controller.getHeroPowerInitialized() != null ) {

			CurrentHeroView c = (CurrentHeroView) controller.getHeroPowerInitialized() ;

			controller.heroPower(c.getH(),(Minion) a.getC());

		}

	}
}
