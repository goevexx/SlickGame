package de.mankodach.TypingGame;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenu extends BasicGameState{
	private Button gameButton;
	private Button settingsButton;
	private Button endButton;
	
	private final int state;
	
	private Font font;
	
//	private Circle circle1;
//	private int treffer;
//	private Input input;
//	private Random random;
	
	public MainMenu(int state) {
		this.state = state;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		container.setMinimumLogicUpdateInterval(5);
		container.setMaximumLogicUpdateInterval(5);

		gameButton = new Button("Start Game",container.getWidth()/2-150,container.getHeight()/2-25, 300, 50);
//		treffer = 0;
//		random = new Random();
//		// ein Input-Objekt für die Maus / Tastatur, etc.
//		input = container.getInput();
//		// Neues Circle-Objekt in der Mitte des Spielfelds mit 20Pixel-Radius
//		circle1 = new Circle(container.getWidth() / 2, container.getHeight() / 2, 20);
	}
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
//		g.fill(circle1);
//		g.drawString("Anzahl Treffer: " + treffer, 100, 100);
		gameButton.draw(g);
	}
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
//		int mouseX = input.getMouseX();
//		int mouseY = input.getMouseY();
//
//		// Maus Taste1 abfragen und auf Kollision mit Circle testen
//		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
//			if (circle1.contains(mouseX, mouseY)) {
//				treffer++;
//				circle1.setX(random.nextInt(container.getWidth()));
//				circle1.setY(random.nextInt(container.getHeight()));
//			}
//			// Fenster mit ESC scließen
//		} else if (input.isKeyPressed(Input.KEY_ESCAPE)) {
//			container.exit();
//		}
	}

	@Override
	public int getID() {
		return this.state;
	}
}
