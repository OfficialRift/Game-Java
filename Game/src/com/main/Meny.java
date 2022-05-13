package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Meny extends MouseAdapter {

	public boolean active; 


	private Rectangle PlayButton; 
	private String playTxt = "Play";
	private boolean play = false; 
	
	
	private Rectangle ChangeButton; // Quit Button
	private String changeTxt = "1v1";
	private boolean change = false; // true if the mouse hovered over the Quit button

	
	private Rectangle ExitButton; // Quit Button
	private String quitTxt = "Exit";
	private boolean exit = false; // true if the mouse hovered over the Quit button

	private Font font;
	int antal = 0;

	public Meny(Game game) {

		active = true;
		game.start();

		// position and dimensions of each button
		int x, y, w, h;

		w = 150;
		h = 75;

		y = Game.HEIGHT / 2 - h / 2;

		x = Game.WIDTH / 4 - w / 2;
		PlayButton = new Rectangle(x, y, w, h);

		x = Game.WIDTH * 3 / 4 - w / 2;
		ExitButton = new Rectangle(x, y, w, h);
		
		x = Game.WIDTH / 2- w / 2;
		ChangeButton = new Rectangle(x, y, w, h);

		font = new Font("Times New Roman", Font.PLAIN, 35);
	}

	
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setFont(font);

		// draw buttons
		g.setColor(Color.black);
		if (play)
			g.setColor(Color.white);
		g2d.fill(PlayButton);

		g.setColor(Color.black);
		if (exit)
			g.setColor(Color.white);
		g2d.fill(ExitButton);
		
		g.setColor(Color.black);
		if (change)
			g.setColor(Color.white);
		g2d.fill(ChangeButton);

		// draw button borders
		g.setColor(Color.white);
		g2d.draw(PlayButton);
		g2d.draw(ExitButton);
		g2d.draw(ChangeButton);

		// draw text in buttons

		int strWidth, strHeight;

		// Play Button text
		strWidth = g.getFontMetrics(font).stringWidth(playTxt);
		strHeight = g.getFontMetrics(font).getHeight();

		g.setColor(Color.green);
		g.drawString(playTxt, (int) (PlayButton.getX() + PlayButton.getWidth() / 2 - strWidth / 2),
				(int) (PlayButton.getY() + PlayButton.getHeight() / 2 + strHeight / 4));

		// Quit Button text
		strWidth = g.getFontMetrics(font).stringWidth(quitTxt);
		strHeight = g.getFontMetrics(font).getHeight();

		g.setColor(Color.red);
		g.drawString(quitTxt, (int) (ExitButton.getX() + ExitButton.getWidth() / 2 - strWidth / 2),
				(int) (ExitButton.getY() + ExitButton.getHeight() / 2 + strHeight / 4));
		
		strWidth = g.getFontMetrics(font).stringWidth(changeTxt);
		strHeight = g.getFontMetrics(font).getHeight();
		
		g.setColor(Color.orange);
		g.drawString(changeTxt, (int) (ChangeButton.getX() + ChangeButton.getWidth() / 2 - strWidth / 2),
				(int) (ChangeButton.getY() + ChangeButton.getHeight() / 2 + strHeight / 4));

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		Point p = e.getPoint();

		if (PlayButton.contains(p) && active == true) {
			active = false;
		}
		else if (ExitButton.contains(p) && active == true) {
			System.exit(0);
		}
		else if (ChangeButton.contains(p) && active == true) {
				antal ++;
				switch(antal) {
				
				case 1:
					changeTxt= "Easy";
					Game.human(1);
					Game.diff(0.05);
		
				break;
				
				case 2:
					changeTxt= "Normal";
					Game.human(1);
					Game.diff(0.5);
					break;
					
				case 3:
					changeTxt= "Hard";
					Game.human(1);
					Game.diff(0.8);
					break;
					
				case 4: 
					
					changeTxt= "Impossible";
					Game.human(1);
					Game.diff(1);
				break;
				
				default:
					changeTxt= "1v1";
					Game.human(0);
					antal = 0;
					break;
				
				}
			}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		Point p = e.getPoint();

		play = PlayButton.contains(p);
		exit = ExitButton.contains(p);
		change = ChangeButton.contains(p);

	}

}