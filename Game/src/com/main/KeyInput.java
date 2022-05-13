package com.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Paddel player1; // player 1
	private Paddel player2; // player 2
	private int ishuman; // to turn of player 2
	
	public KeyInput(Paddel paddelone, Paddel paddeltwo, int human) {

		player1 = paddelone;
		player2 = paddeltwo;
		ishuman = human;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); // get what key that was pressed

		if (key == KeyEvent.VK_W) { // when w
			
			// move down
			player1.switchdir(-1);
		}
		if (key == KeyEvent.VK_S) { // when s
			
			// move upp
			player1.switchdir(1);
		}
		
		if(ishuman == 0)  // turn of or on controls
		{
			
			if (key == KeyEvent.VK_UP) { // when arrow up
				
				player2.switchdir(-1); // move up
			}
			if (key == KeyEvent.VK_DOWN) { // when arrow down
				
				
				player2.switchdir(1); // move down
			}
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();  // get what key that was released

		if (key == KeyEvent.VK_W) {
			 player1.stop(); // stop moment
		}
		if (key == KeyEvent.VK_S) {
			player1.stop(); // stop moment
		}
		
		if(ishuman == 0) // turn of or on controls
		{
			if (key == KeyEvent.VK_UP) {
				 player2.stop(); // stop moment
			}
			if (key == KeyEvent.VK_DOWN) {
				player2.stop(); // stop moment
			}
		}
	}
	
}
