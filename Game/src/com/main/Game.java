package com.main;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -668240625892092763L;
	
	public final static int WIDTH = 1000; // window size with 9:16
	public final static int HEIGHT = WIDTH * 9 / 16; 
	
	public boolean gamerunning = true; // true when gamerunning
	public static int human = 0; // 0 = human 1 == AI
	

	private Thread gameThread; // single thread, updated and drawn 
	private double AIrandom = 0; // AI can be between 0 and 1 (random)
	private static double AIafficency = 1; // change difficult level
	
	// property being declared
	private Ball ball;
	private Paddel Paddelone;
	private Paddel Paddeltwo;
	
	private Meny meny;
	
	
	public static void main(String[] args) {
		new Game(); // starts game when you start the application
	}
	
	public Game() {
		canvasSetup(); // run method
		new Window("Tennis", this);
		creat(); // run method
		
		// Add key input to paddel, and value of ai
		this.addKeyListener(new KeyInput(Paddelone, Paddeltwo, human));
		// To the menu add mouse and MotionListener to menu
		this.addMouseListener(meny);
		this.addMouseMotionListener(meny);
		this.setFocusable(true);
		
	}
	
	// AI method
	private void AI() {
		
			int Yball = ball.getY(); // get value of y coordinates
			int Ypaddel = Paddeltwo.getY(); // get value of coordinates
			// AIafficency how often the ai could move
			// Move ai if to ball is higer then paddel move up
			if(Yball > Ypaddel && AIrandom < AIafficency) {
				Paddeltwo.switchdir(1);
			}
			// Move ai if to ball is lower then paddel move down 
			else if(Yball < Ypaddel && AIrandom < AIafficency) { 
				Paddeltwo.switchdir(-1);
			}
			else
			{
				Paddeltwo.stop(); // velocity 0 if the ball is in the middle
			}
		
	}
	// create the object
	private void creat() {
	
		ball = new Ball(); // create the ball
		Paddelone = new Paddel(Color.green, true); // create the paddel one, (color and position of left or right)
		Paddeltwo = new Paddel(Color.red, false); // create the paddel two (true = left, false = right)
		meny = new Meny(this); // create the menu
		
	}
	
	// draw the objects
	private void draw() {
		
		BufferStrategy buffer = this.getBufferStrategy(); // a buffer that we can makes the game smoother, (blank canvas)

		if (buffer == null) { 
			this.createBufferStrategy(2); // 2 diffrents canvas that are used to to improve performance
			return;
		}
		
		Graphics g = buffer.getDrawGraphics(); // get tools to draw from buffer
		
		drawBackground(g); //draw background

		// draw menu
		if(meny.active == true) {
			meny.draw(g);
		}
		// draw ball
		ball.draw(g);
		

		// draw the paddels
		Paddelone.draw(g);
		Paddeltwo.draw(g);

	
		g.dispose();  // draw on screen actully draw and use system resources exampel graphic card
						
		buffer.show(); // show the objects
	}
	
	// draw back ground
	private void drawBackground(Graphics g) {
	
		g.setColor(Color.black); // black background
		g.fillRect(0, 0, WIDTH, HEIGHT);

		
		g.setColor(Color.white);
		Graphics2D g2d = (Graphics2D) g; // more complex graphics
										
		//dashed line in the middle screen
		Stroke line = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 10 }, 0);
		g2d.setStroke(line);
		g.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
	}

	// update game
	private void update() {
		if(meny.active == false) // update not when menu is active
		{
			ball.update(Paddelone,Paddeltwo); // update ball
			Paddelone.update(ball); // update paddel
			Paddeltwo.update(ball); // update paddel
		}
	}

	// setup canvas with the size
	private void canvasSetup() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	public void run(){ // run method
		this.requestFocus();

		// game timer
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0; //set fps the game should run
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (gamerunning) { // loop until gamerunning is false, game loop
			
			if(human == 1 && meny.active == false) { // run ai if human is = 1 and if meny is false
				AIrandom = Math.random(); // between 0 and 1
				AI();
			}
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				update(); // method update
				delta--;
				draw(); // method draw
				frames++;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames); // print fps in consol
				frames = 0;
				
				
				int score1 = Paddelone.getScore(); // get score player1
				int score2 = Paddeltwo.getScore(); // get score player2
				
				// if score is 10 close program
				if(score1 >= 10) { 
					
					System.exit(0);
					
				}
				// if score is 10 close program
				if(score2 >= 10) {
					
					System.exit(0);
					
				}
			}
			
		}

		stop(); // run method stop
	}

	public void start() {
		gameThread = new Thread(this); // (this = game class) and implements runnable interface
		gameThread.start(); // start thread
		
	}
	
	// stop thread and game
	public void stop() {
		try {
			gameThread.join(); // stop thread
			gamerunning = false; // stop game
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	// return 0 or 1 velociry direction when ball reset
	public static int sign(double d) {
		if (d <= 0)
			return -1;

		return 1;
	}

	// value between minimum and max value, if minimum is smaller return minimum
	// if maximum is bigger return maximum
	public static int range(int value, int min, int max) {
		return Math.min(Math.max(value,min), max);
	}
	
	// change to ai or 1v1 from menu class
	public static void human(int ai) {
		 human = ai;
	}
	
	// change ai difficult from menu class
	public static void diff(double level) {
		AIafficency = level;
	}
	
}
