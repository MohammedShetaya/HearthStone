package model.heroes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.DivineSpirit;
import model.cards.spells.HolyNova;
import model.cards.spells.ShadowWordDeath;
import model.cards.spells.SiphonSoul;
import model.cards.spells.Spell;
import model.cards.spells.TwistingNether;

public class Warlock extends Hero {
	
	public Warlock () throws IOException {
		super("Gul'dan");
	}
	
	public void buildDeck() throws IOException {
		ArrayList<Minion> m = getAllNeutralMinions("neutral-minions.csv") ; 
		ArrayList<Minion> n = getNeutralMinions(m,13) ;
		ArrayList<Card> d = this.getDeck() ;
		
		for(int i = 0 ; i < n.size() ;  i ++ ) {
			d.add(n.get(i)) ; 
		}
		
		Spell s1 = new CurseOfWeakness();
		d.add(s1);
		Spell s2 = new CurseOfWeakness();
		d.add(s2);
		Spell s3 = new SiphonSoul(); 
		d.add(s3);
		Spell s4 = new SiphonSoul();
		d.add(s4) ; 
		Spell s5 = new TwistingNether(); 
		d.add(s5);
		Spell s6 = new TwistingNether();
		d.add(s6) ; 
		Minion m1 = new Minion("Wilfred Fizzlebang",6,Rarity.LEGENDARY,4,4,false ,false ,false) ;
		d.add(m1) ;
		Collections.shuffle(d);
		
	}

}
