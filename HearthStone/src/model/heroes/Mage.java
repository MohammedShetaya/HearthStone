package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.Flamestrike;
import model.cards.spells.Polymorph;
import model.cards.spells.Pyroblast;
import model.cards.spells.Spell;

public class Mage extends Hero {
	
	
	public Mage () throws IOException {
		super("Jaina Proudmoore") ;
	}
	

	
	public void buildDeck() throws IOException {
		ArrayList<Minion> m = getAllNeutralMinions("neutral-minions.csv") ; 
		ArrayList<Minion> n = getNeutralMinions(m,13) ;
		ArrayList<Card> d = this.getDeck() ;
		for(int i = 0 ; i < n.size() ;  i ++ ) {
			d.add(n.get(i)) ; 
		}
		Spell s1 = new Polymorph();
		d.add(s1);  
		d.add(new Polymorph());
		Spell s2 = new Flamestrike(); 
		d.add(s2);
		d.add(new Flamestrike()) ; 
		Spell s3 = new Pyroblast(); 
		d.add(s3);
		d.add(new Pyroblast()) ; 
		Minion m1 = new Minion("Kalycgos",10,Rarity.LEGENDARY ,4,12 ,false ,false ,false) ;
		d.add(m1) ;
		Collections.shuffle(d);
		
	}
}
