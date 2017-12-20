package de.mankodach.TypingGame;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Game extends BasicGameState {
	private final int state;

	private Scoreboard score;
	private Player player;
	private Enemy activeEnemy;
	private ArrayList<Enemy> enemies;
	private Color backgroundColor;
	private ArrayList<String> words;

	public Game(Color backgroundColor, ArrayList<String> words, int state) {
		super();
		this.activeEnemy = null;
		this.backgroundColor = backgroundColor;
		this.words = words;
		this.state = state;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		container.setMinimumLogicUpdateInterval(5);
		container.setMaximumLogicUpdateInterval(5);
		
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		
		
	}
	
	@Override 
	public void keyPressed(int key, char c)
	{
		Word activeWord = activeEnemy.getWord();
		if (c > 64 && c < 91 )
		{
			if (activeEnemy == null)
			{
				ArrayList<Enemy> possibleEnemies = new ArrayList<Enemy>();
				for(Enemy enemy : enemies)
				{
					Word word = enemy.getWord();
					if (word.getName().charAt(0) == c)
					{
						 possibleEnemies.add(enemy);
						 
						 for (Enemy possibleEnemy : possibleEnemies)
						 {
							 
						 }
					}
				}
			}
			activeWord.addTypedLetter(c);
			
			if (activeWord.getName() == activeWord.getTyped())
			{
				destroyEnemy(activeEnemy);
				activeEnemy = null;
				//animation einfügen
			}
		}	
	}

	@Override
	public int getID() {
		return this.state;
	}

	public void start() {
		
	}

	public void end() {

	}

	public void pause() {
		

	}

	public void destroyEnemy(int i) {
		enemies.remove(i);
	}

	public void destroyEnemy(Enemy e) {
		enemies.remove(e);
	}

	public void addEnemy(Enemy e) {
		enemies.add(e);
	}

	public Scoreboard getScore() {
		return score;
	}

	public void setScore(Scoreboard score) {
		this.score = score;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Enemy getActiveEnemy() {
		return activeEnemy;
	}

	public void setActiveEnemy(Enemy activeEnemy) {
		this.activeEnemy = activeEnemy;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public ArrayList<String> getWords() {
		return words;
	}

	public void setWords(ArrayList<String> words) {
		this.words = words;
	}

}
