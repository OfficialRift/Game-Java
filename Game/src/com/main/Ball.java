package com.main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	public static final int SIZE = 20;
	
	public int x, y; // posttion of ball
	public int xVEL, yVEL; // 1 or -1
	public int speed = 5; // speed of ball
	
	public Ball() {
		rest(); // reset method
	}

	// reset the possion and randomize the way the ball starts bounding
	private void rest() {
		
		x = Game.WIDTH / 2 - SIZE /2; // place in mindel of x cordninats
		y = Game.HEIGHT / 2 - SIZE /2; // place in midel of y cordinats
		
		xVEL = Game.sign(Math.random() * 2.0 - 1); // random vel when reset
		yVEL = Game.sign(Math.random() * 2.0 - 1); // radom vel when reset
	
	}
	// getters x cordninat
	public int getX() {
		return x;
	}
	// getters y cordinats
	public int getY() {
		return y;
	}
	// draw ball
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, SIZE, SIZE);
	}
	// method for change xdir
	public void changeXDir() {
		xVEL *= -1;
	}

	// method for change ydir
	public void changeYDir() {
		yVEL *= -1;
	}

	public void update(Paddel paddelone, Paddel paddeltwo) {
		x += xVEL * speed; // v*t = s 
		y += yVEL * speed;
		
		// bounds when ball touch ceiling and floor
		if(y + SIZE >= Game.HEIGHT || y <= 0) {
			changeYDir();
		}
		
		if(x + SIZE >= Game.WIDTH) { // right wall
			paddelone.addPoints();
			rest();
		}
		
		if(x <= 0) { // left wall
			paddeltwo.addPoints();
			rest();
		}
	}
	
	
}
