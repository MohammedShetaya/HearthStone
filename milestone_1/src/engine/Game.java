package engine;

import model.heroes.Hero;
import java.util.*; 

public class Game {
	private Hero firstHero ;
	private Hero secondHero ;
	private Hero currentHero  ; 
	private Hero opponent ; 
	public Game (Hero p1 , Hero p2 ) {
		this.firstHero = p1 ; 
		this.secondHero = p2 ; 
		Random e = new Random () ; 
		int i = e.nextInt(2) ;
		
		if (i == 0 ) {
			this.currentHero = firstHero ; 
			this.opponent = secondHero ; 
		}
		else {
			this.currentHero = secondHero ; 
			this.opponent = firstHero ; 
			
		}
		
		this.currentHero.setCurrentManaCrystals(1) ;
	}

	
	public Hero getCurrentHero() {
		return currentHero;
	}
	
	public Hero getOpponent() {
		return opponent;
	}
	
}
