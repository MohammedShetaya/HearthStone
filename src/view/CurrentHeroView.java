package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import model.heroes.Hero;

public class CurrentHeroView extends JButton{

	private Hero h ; 

	public CurrentHeroView(Hero h) {
		// TODO Auto-generated constructor stub

		this.h = h ;

		this.setPreferredSize(new Dimension(110,127));
		this.setFont(new Font("Arial", Font.BOLD, 11));
		this.setBorder(BorderFactory.createLineBorder(Color.red));
		this.setText(this.toString());
	}
	
	public Hero getH () {
		return h ;
	}


	public String toString() {
		return "<html><center>" + h.getName() + "<br/>" + "HP: "+ h.getCurrentHP() +"<br/>"+ "Mana: "+h.getCurrentManaCrystals()+"/"+h.getTotalManaCrystals()+"<br/>"+"Deck Cards: "+h.getDeck().size()+"<br/>"+"</center></html>" ;

	}





}
