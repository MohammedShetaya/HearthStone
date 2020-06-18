package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import engine.Game;
import exceptions.FullHandException;
import view.GameView;

public class EndTurnListener implements ActionListener{

	
	controller controller ; 
	
	public EndTurnListener(controller controller) {
		this.controller = controller; 
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
	
		controller.endTurn(); 	
		
	}
	
}
