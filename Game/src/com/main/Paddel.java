package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Paddel {
	private int x, y; // coordinates
	private int velocity = 0; // direction
	private int speed = 10;  // speed
	private int width = 22, height = 85; // widh and heigt of paddel
	private int score = 0;  // score
	private Color color; // color
	private boolean left;
	
	// add score text 
	public Paddel(Color c, boolean dir) {
		color = c; // color of paddel
		this.left = dir; 
		
		if (left) // left = 0
			x = 0; // text at x coordinates
		else
			x = Game.WIDTH - width; // text at x coordinates

			y = Game.HEIGHT / 2 - height / 2; // text at y coordinates
	}
	// add score
	public void addPoints() {
		score++; // increment by 1
	}

	// draw paddel
	public void draw(Graphics g) {
		g.setColor(color); // set color
		g.fillRect(x, y, width, height); // 
		
		// draw scare
		int le; // position of string
		int padding = 25; // space between score and dotted line
		String ScoreText = Integer.toString(score);
		Font font = new Font("Times New Roman", Font.PLAIN, 50); // add font, and size
		
		if (left) {
			int stringWidth = g.getFontMetrics(font).stringWidth(ScoreText); // get the width to center score text
			le = Game.WIDTH / 2 - padding - stringWidth; // left == true, change posstion of text
		} else {
			le = Game.WIDTH / 2 + padding; // left = false, change position of text
		}

		g.setFont(font); // sent front
		g.drawString(ScoreText, le, 50); // draw score
		
	}

	// update position and collection
	public void update(Ball ball) {
		
		// update position
		y = Game.range(y += velocity, 0, Game.HEIGHT - height);
		
		// get cordinets from ball
		int BallX = ball.getX();
		int BallY = ball.getY();
		
		if(left) { // if left == true
			// controll if ball hit 
			if(BallX <= width && BallY + Ball.SIZE >= y && BallY <= y + height) {
				ball.changeXDir(); // call method changexdir
			}
		}
		else
		{
			// controll if ball hit
			if(BallX + Ball.SIZE >= Game.WIDTH - width && BallY + Ball.SIZE >= y && BallY <= y + height) {
				ball.changeXDir(); // call methid cangexdir
			}
			
		}
		
	}
	// getters for x cordinets
	public int getX() {
		return x;
	}
	// getters for y cordinets
	public int getY() {
		return y;
	}
	// getters for score
	public int getScore() {
		return score;
	}

	// method for change direction
	public void switchdir(int direction) {
		velocity = speed * direction; // vel = speed * dir (dir -1 for up 1 for down)
	}
	
	public void stop() {
		velocity = 0; // stop moving paddels
	}
	
	
}
