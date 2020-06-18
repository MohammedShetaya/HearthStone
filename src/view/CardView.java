package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import model.cards.Card;
import model.cards.minions.Minion;

public class CardView  extends JButton {   
	
	private Card c ;
	
	public Card getC() {
		return c;
	}




	public CardView (Card c) {
		
		this.c = c ; 
		this.setPreferredSize(new Dimension(110,127));
		this.setFont(new Font("Arial", Font.BOLD, 11));
	    this.setBorder(BorderFactory.createLineBorder(Color.blue));
	    this.setText(this.toString());

	}
	
	
    public String toString () {
    	return c.toString() ;
    }

	public static void main(String[] args) {
		
	}
	
}
