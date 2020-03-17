package model.cards.minions;

import model.cards.Card;
import model.cards.Rarity;

public class Minion extends Card {
	private int attack ;
	private int maxHP ;
	private int currentHP ;
	private boolean taunt ;
	private boolean divine ;
	private boolean sleeping ;
	private boolean attacked ;
	
	public Minion() {}
	
	public Minion(String name,int manaCost,Rarity rarity, int attack,int maxHP,boolean
			taunt,boolean divine,boolean charge) {
		super(name,manaCost,rarity);
		this.attack = attack;
		currentHP=maxHP;
		this.maxHP = maxHP;
		this.taunt = taunt ;
		this.divine = divine ;
		this.sleeping =! charge ;
		
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = Math.max(attack,0);
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		if (currentHP>=0)
			this.currentHP = Math.min(currentHP,this.maxHP);
		else {
			this.currentHP = currentHP; 
		}
	}

	public boolean isTaunt() {
		return taunt;
	}

	public void setTaunt(boolean taunt) {
		this.taunt = taunt;
	}

	public boolean isDivine() {
		return divine;
	}

	public void setDivine(boolean divine) {
		this.divine = divine;
	}

	public boolean isSleeping() {
		return sleeping;
	}

	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
	}

	

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	
}
