package de.mankodach.TypingGame;

import java.util.ArrayList;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class TypingGame extends StateBasedGame {
	public static final String gamename = "Crazy typing Game!";

	public enum States {
		mainMenuState, gameState, settingsState
	}

	public MainMenu mainMenu;
	public Game game;
	public Settings settings;

	public TypingGame() {
		super(gamename);
		mainMenu = new MainMenu(States.mainMenuState.ordinal());
		settings = new Settings(States.settingsState.ordinal());
		game = new Game(Color.red, new ArrayList<String>(), States.gameState.ordinal());
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new TypingGame());
		try 
		{
			container.setDisplayMode(1280, 720, false);
			container.setAlwaysRender(true);
			
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.addState(mainMenu);
		this.addState(game);
		this.addState(settings);
	}
}
