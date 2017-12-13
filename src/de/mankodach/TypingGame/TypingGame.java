package de.mankodach.TypingGame;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class TypingGame extends BasicGame {

	private Circle circle1;
	private int treffer;
	private Input input;
	private Random random;

	public TypingGame() {
		super("Basic Slick2D Game!");
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new TypingGame());
		container.setDisplayMode(1280, 720, false);
		container.start();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// Spiel verlangsamen
		container.setMinimumLogicUpdateInterval(5);
		container.setMaximumLogicUpdateInterval(5);

		treffer = 0;
		random = new Random();
		// ein Input-Objekt für die Maus / Tastatur, etc.
		input = container.getInput();
		// Neues Circle-Objekt in der Mitte des Spielfelds mit 20Pixel-Radius
		circle1 = new Circle(container.getWidth() / 2, container.getHeight() / 2, 20);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		g.fill(circle1);
		g.drawString("Anzahl Treffer: " + treffer, 100, 100);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();

		// Maus Taste1 abfragen und auf Kollision mit Circle testen
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			if (circle1.contains(mouseX, mouseY)) {
				treffer++;
				circle1.setX(random.nextInt(container.getWidth()));
				circle1.setY(random.nextInt(container.getHeight()));
			}
			// Fenster mit ESC scließen
		} else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}
}
