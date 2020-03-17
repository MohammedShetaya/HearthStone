package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.DivineSpirit;
import model.cards.spells.Flamestrike;
import model.cards.spells.HolyNova;
import model.cards.spells.Polymorph;
import model.cards.spells.Pyroblast;
import model.cards.spells.ShadowWordDeath;
import model.cards.spells.Spell;

public class Priest extends Hero {
	
	public Priest() throws IOException {
		super("Anduin Wrynn") ; 
	}
	
	public void buildDeck() throws IOException {
		ArrayList<Minion> m = getAllNeutralMinions("neutral-minions.csv") ; 
		ArrayList<Minion> n = getNeutralMinions(m,13) ;
		ArrayList<Card> d = this.getDeck() ;
		for(int i = 0 ; i < n.size() ;  i ++ ) {
			d.add(n.get(i)) ; 
		}
		Spell s1 = new DivineSpirit();
		d.add(s1);
		Spell s2 = new DivineSpirit();
		d.add(s2);
		Spell s3 = new HolyNova(); 
		d.add(s3);
		Spell s4 = new HolyNova();
		d.add(s4) ; 
		Spell s5 = new ShadowWordDeath(); 
		d.add(s5);
		Spell s6 = new ShadowWordDeath();
		d.add(s6) ; 
		Minion m1 = new Minion("Prophet Velen",	7,Rarity.LEGENDARY ,7,7,false ,false ,false) ;
		d.add(m1) ;
		Collections.shuffle(d);
	}

}
