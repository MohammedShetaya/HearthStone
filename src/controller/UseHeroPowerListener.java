package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Paladin;
import model.heroes.Warlock;
import view.CardView;
import view.CurrentHeroView;
import view.UseHeroPowerButton;

public class UseHeroPowerListener implements ActionListener{
	
	
	controller controller ; 
	public UseHeroPowerListener(controller controller ) {
		// TODO Auto-generated constructor stub
		this.controller = controller ;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		CurrentHeroView a = (CurrentHeroView) controller.getCurrentHero() ;
		
		Hero h = controller.getModel().getCurrentHero() ; 
		
		controller.setHeroPowerInitialized(a);
		
		if (h instanceof Hunter || h instanceof Paladin || h instanceof Warlock) {
			controller.heroPower(h);
		}
		
	}
	

}
