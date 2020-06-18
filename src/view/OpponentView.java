package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import model.heroes.Hero;


public class OpponentView  extends JButton{
	
	private Hero h ; 

	public OpponentView(Hero h ) {
		
		// TODO Auto-generated constructor stub
		this.h = h ;
		this.setPreferredSize(new Dimension(110,127));
		this.setFont(new Font("Arial", Font.BOLD, 11));
		this.setBorder(BorderFactory.createLineBorder(Color.green));
		this.setText(this.toString());
	}
	
	public Hero getH ()  {
		return h ; 
	}
	
	
	public String toString() {
		return "<html><center>" + h.getName() + "<br/>" + "HP: "+ h.getCurrentHP() +"<br/>"+ "Mana: "+h.getCurrentManaCrystals()+"/"+h.getTotalManaCrystals()+"<br/>"+"Hand Cards: "+h.getHand().size()+"<br/>"+"Deck Cards: "+h.getDeck().size()+"<br/>"+"</center></html>" ;
	}
	
}
