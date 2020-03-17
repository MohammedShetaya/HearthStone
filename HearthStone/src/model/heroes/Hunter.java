package model.heroes;

import java.io.IOException;
import java.util.*;
import model.cards.*;

import model.cards.minions.Minion;
import model.cards.spells.KillCommand;
import model.cards.spells.MultiShot;
import model.cards.spells.Spell;

public class Hunter extends Hero  {

	public Hunter() throws IOException {
		super("Rexxar") ;
	}

	public void buildDeck() throws IOException {
		ArrayList<Minion> m = getAllNeutralMinions("neutral-minions.csv") ; 
		ArrayList<Minion> n = getNeutralMinions(m,15) ;
		ArrayList<Card> d = this.getDeck() ;
		for(int i = 0 ; i < n.size() ;  i ++ ) {
			d.add(n.get(i)) ; 
		}
		Spell s1 = new KillCommand();
		d.add(s1);  
		d.add(new KillCommand());
		Spell s2 = new MultiShot(); 
		d.add(s2);
		d.add(new MultiShot()) ; 
		Minion m1 = new Minion("King Krush",9,Rarity.LEGENDARY , 8,8 ,false ,false ,true) ;
		d.add(m1) ;
		Collections.shuffle(d);
		System.out.println(d.size());
	}

	public static void main(String[] args) throws IOException {
		Hunter h = new Hunter() ;

	}

}

