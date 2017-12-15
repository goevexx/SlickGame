package de.mankodach.TypingGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import de.mankodach.TypingGame.TypingGame.States;

public class MainMenu extends BasicGameState {
	private Button gameButton;
	private Button settingsButton;
	private Button endButton;
	private Input input;

	private final int state;

	public MainMenu(int state) {
		this.state = state;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		container.setMinimumLogicUpdateInterval(5);
		container.setMaximumLogicUpdateInterval(5);

		gameButton = new Button("Spiel starten", container.getWidth() / 2 - 150, container.getHeight() / 2 - 100, 300,
				50);
		settingsButton = new Button("Einstellungen", container.getWidth() / 2 - 150, container.getHeight() / 2 - 25,
				300, 50);
		endButton = new Button("Beenden", container.getWidth() / 2 - 150, container.getHeight() / 2 + 50, 300, 50);

		input = container.getInput();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Typing Game",  container.getWidth() / 2 - 50, container.getHeight() / 2 - 150);
		gameButton.draw(g);
		settingsButton.draw(g);
		endButton.draw(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (gameButton.contains(mouseX, mouseY)) {
				game.enterState(States.gameState.ordinal());
			}
		} else if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (settingsButton.contains(mouseX, mouseY)) {
				game.enterState(States.settingsState.ordinal());
			}
		} else if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (endButton.contains(mouseX, mouseY)) {
				container.exit();
			}
		} else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	@Override
	public int getID() {
		return this.state;
	}
}
