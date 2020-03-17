package model.cards;

public abstract class Card {
	private String name ;

	private int manaCost ; 
	private Rarity rarity ;
	public Card() {}
	public Card(String name,int m,Rarity rarity) {
		this.name = name ;
		if(m>10)
			this.manaCost=Math.min(10, m);
		else
			this.manaCost=Math.max(0, m);

		this.rarity = rarity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getManaCost() {
		return manaCost;
	}
	public void setManaCost(int m) {
		if(m>10)
			this.manaCost=Math.min(10, m);
		else
			this.manaCost=Math.max(0, m);
	}

	public Rarity getRarity() {
		return rarity;
	}
}
