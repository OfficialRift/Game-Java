package com.main;

import javax.swing.JFrame;

public class Window {

	public Window(String title, Game game) {
		JFrame frame = new JFrame(title);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(game); // inherits from canvas, this makes it can be added to JFrame
		frame.pack();
		frame.setLocationRelativeTo(null); // center window
		frame.setVisible(true);
		
		game.start();
	}

}
