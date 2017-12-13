package de.mankodach.TypingGame;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.state.StateBasedGame;

public class TypingGame extends StateBasedGame{
	public static final String gamename = "Crazy typing Game!";
	public static final int mainMenuState = 0;
	public static final int gameState = 1;
	public static final int settingsState = 2;
	
	public MainMenu mainMenu;
//	public Game game;
//	public Settings settings;

	public TypingGame() {
		super(gamename);
		mainMenu = new MainMenu(mainMenuState);
//		game = new Game();
//		settings = new Settings();
		
		this.addState(mainMenu);
//		this.addState(game);
//		this.addState(Settings);
		System.out.println(this.getStateCount());
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new TypingGame());
		try {
		container.setDisplayMode(1280, 720, false);
		container.start();
		}catch(SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.getState(mainMenuState).init(container, this);
		this.enterState(mainMenuState);
	}
}
