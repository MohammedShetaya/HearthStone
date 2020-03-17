package model.heroes;

import java.util.*;

import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.Card;

import java.io.*;
import model.cards.*;
public abstract class Hero {

	private String name ;
	private int currentHP;
	private boolean heroPowerUsed;
	private int totalManaCrystals; 
	private int currentManaCrystals;
	private ArrayList<Card> deck ;
	private ArrayList<Minion> field ;
	

	private ArrayList<Card> hand ;
	private int fatigueDamage ;


	//empty constractor
	public Hero () {}

	//constractor initiates the attributes 
	public Hero(String name) throws IOException {
		this.name = name ; 
		this.currentHP = 30 ; 
		this.heroPowerUsed = false ;
		deck=new ArrayList<Card>();
		hand=new ArrayList<Card>();
		field =new ArrayList<Minion>();
		buildDeck();
	}

	public String getName() {
		return name;
	}

	public int getCurrentHP() {
		return currentHP;
	}
	public void setCurrentHP(int currentHP) {
		this.currentHP = Math.min(30 , currentHP ) ;
	}
	public boolean isHeroPowerUsed() {
		return heroPowerUsed;
	}
	public void setHeroPowerUsed(boolean heroPowerUsed) {
		this.heroPowerUsed = heroPowerUsed;
	}
	public int getTotalManaCrystals() {
		return totalManaCrystals;
	}
	public void setTotalManaCrystals(int totalManaCrystals) {
		if (totalManaCrystals>10) {
		this.totalManaCrystals = 10 ;
		}
		else { 
			this.totalManaCrystals = totalManaCrystals ;
		}
	}
	public int getCurrentManaCrystals() {
		return currentManaCrystals;
	}
	public void setCurrentManaCrystals(int currentManaCrystals) {
		
			if(currentManaCrystals>10)
				this.currentManaCrystals = 10 ; 
			else {
				if (currentManaCrystals<0)
					this.currentManaCrystals = 0 ; 
				else 
				this.currentManaCrystals = currentManaCrystals ; 
			}
		}
	
	public ArrayList<Card> getDeck() {
		return deck;
	}

	public ArrayList<Minion> getField() {
		return field;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	final public static ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException{
		
		String currentLine = "" ;
		FileReader fileReader = new FileReader(filePath) ; 
		BufferedReader br = new BufferedReader(fileReader) ;
		
		//to instatiate a deck of cards for a hero 
		
		ArrayList<Minion> nMinions = new ArrayList<Minion>() ;
		
		while ((currentLine = br.readLine())!=null) {
			String l [] = currentLine.split(",") ; 
			Rarity temp=null;
			
			switch(l[2]) {
			case "b":temp=Rarity.BASIC;break;
			case "r":temp=Rarity.RARE;break;
			case "e":temp=Rarity.EPIC;break;
			case "c" :temp=Rarity.COMMON;break;
			case "l":temp=Rarity.LEGENDARY;break;
			default:break;
			}
			
			Minion m ;
			if (l[0].equals("Icehowl"))
				 m =new Icehowl();
			else
				
			      m = new Minion (l[0] , Integer.parseInt(l[1]), temp,
					               Integer.parseInt(l[3]),Integer.parseInt(l[4]),
					               (l[5].equals("TRUE")?true:false), (l[6].equals("TRUE")?true:false) ,
					              (l[7]).equals("TRUE")?true:false) ;
			nMinions.add(m) ;
		}
		return nMinions ;
	}

	final public static ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions,int count){
	
		ArrayList<Minion> nMinions = new ArrayList<Minion>(); 
	    HashMap<Integer , Integer > lol = new HashMap<>();
	    
	    while (nMinions.size()!=count) {
	    	int rand =(int)(Math.random()*minions.size());
	    	int a =lol.getOrDefault(rand,0);
	    	Minion y = minions.get(rand);
	    	Minion temp =new Minion(y.getName(),y.getManaCost(),y.getRarity(),y.getAttack(),y.getMaxHP(),y.isTaunt(),y.isDivine(),!y.isSleeping());
	    	
	    	if (a<=1) {
	    		if (y.getRarity().equals(Rarity.LEGENDARY) && a==0 ){
	    			
	    				nMinions.add(temp);
	    				lol.put(rand,3);
	    				continue;

	    		}
	    		lol.put(rand,a+1);
	    		nMinions.add(temp);
	    	}
	    }
		return nMinions ; 
	}

	public abstract void buildDeck() throws IOException ;
}
