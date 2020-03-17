package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.Flamestrike;
import model.cards.spells.LevelUp;
import model.cards.spells.Polymorph;
import model.cards.spells.Pyroblast;
import model.cards.spells.SealOfChampions;
import model.cards.spells.Spell;

public class Paladin extends Hero {
	
	public Paladin() throws IOException {
		super("Uther Lightbringer") ;
	}
	
	public void buildDeck() throws IOException {
		ArrayList<Minion> m = getAllNeutralMinions("neutral-minions.csv") ; 
		ArrayList<Minion> n = getNeutralMinions(m,15) ;
		ArrayList<Card> d = this.getDeck() ;
		for(int i = 0 ; i < n.size() ;  i ++ ) {
			d.add(n.get(i)) ; 
		}
		Spell s1 = new SealOfChampions();
		d.add(s1);
		Spell s2 = new SealOfChampions();
		d.add(s2);
		Spell s3 = new LevelUp(); 
		d.add(s3);
		Spell s4 = new LevelUp();
		d.add(s4) ;
		Minion m1 = new Minion("Tirion Fordring",4,Rarity.LEGENDARY ,6,6,true,true,false) ;
		d.add(m1) ;
		Collections.shuffle(d);
		
	}

}
