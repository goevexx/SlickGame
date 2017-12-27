package de.mankodach.TypingGame;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.newdawn.slick.*;

public class TypingGame extends BasicGame {
	private GameContainer container;
	private Sound backgroundSound0;
	private Sound backgroundSound1;
	private Sound backgroundSound2;
	private Sound backgroundSound3;
	private Sound endSound;
	private Sound missSound;
	private Scoreboard scoreboard;
	private Player player;
	private Enemy activeEnemy;
	private Enemy passedEnemy;
	private ArrayList<Enemy> enemies;
	private ArrayList<String> words;
	private Date timestamp_spawn;
	private Date timestamp_move;
	private Date timestamp_shoot;
	private Random random;
	private int currentDifficulty;
	private final float[] difficulties = { 1, 0.75f, 0.675f, 0.6f };

	public static final String gamename = "Crazy typing Game!";
	public Settings settings;

	public TypingGame() {
		super(gamename);
		this.settings = new Settings();
		this.activeEnemy = null;
		this.words = settings.getWords();
		this.random = new Random();
		this.enemies = new ArrayList<Enemy>();
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new TypingGame());
		try {
			container.setDisplayMode(720, 720, false);

			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		container.setMinimumLogicUpdateInterval(5);
		container.setMaximumLogicUpdateInterval(5);

		this.container = container;
		currentDifficulty = 0;
		backgroundSound0 = new Sound("res/backgroundSound0.ogg");
		backgroundSound1 = new Sound("res/backgroundSound1.ogg");
		backgroundSound2 = new Sound("res/backgroundSound2.ogg");
		backgroundSound3 = new Sound("res/backgroundSound3.ogg");
		endSound = new Sound("res/ending.wav");
		missSound = new Sound("res/miss.wav");
		backgroundSound0.loop(1, 0.25f);
		timestamp_spawn = new Date();
		timestamp_move = new Date();
		player = new Player(container.getWidth() / 2, container.getHeight(), Color.white);
		spawnEnemey();
		passedEnemy = null;
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		player.draw(g);
		player.getScore().draw(g, container.getWidth()
				- g.getFont().getWidth(player.getScore().getName() + player.getScore().getScore()) - 5, 5);
		g.drawString("Lifepoints: " + player.getLifepoints(),
				container.getWidth() - g.getFont().getWidth("Lifepoints: " + player.getLifepoints()) - 5,
				container.getHeight() - g.getFont().getHeight("Lifepoints: " + player.getLifepoints()) - 5);
		if (!container.isPaused()) {
			if (player.getShot() != null) {
				if ((new Date().getTime() - timestamp_shoot.getTime()) <= 25) {
					player.getShot().playSound();
					player.getShot().draw(g);
				} else {
					player.setShot(null);
				}
			}
			int incHeight = 0;
			if ((new Date().getTime() - timestamp_move.getTime()) >= 15 * difficulties[currentDifficulty]) {
				incHeight = 1;
				timestamp_move = new Date();
			}
			for (int i = 0; i < enemies.size(); i++) {
				Enemy enemy = enemies.get(i);
				enemy.draw(enemy.getX(), enemy.getY() + incHeight);
				if (enemy.getY() > container.getHeight()) {
					passedEnemy = enemy;
				}
			}
		} else {
			g.drawString("Loose", container.getWidth() / 2 - g.getFont().getWidth("Loose") / 2,
					container.getHeight() / 2 - g.getFont().getWidth("Loose") / 2);
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if (container.isPaused()) {
			backgroundSound0.stop();
			backgroundSound1.stop();
			backgroundSound2.stop();
			backgroundSound3.stop();
			endSound.loop(1, 0.02f);
		} else {
			if (player.getLifepoints() == 0) {
				container.setPaused(true);
			}
			if (passedEnemy != null) {
				player.subLifepoints(1);
				destroyEnemy(passedEnemy);
				passedEnemy = null;
			}
			if ((new Date().getTime() - timestamp_spawn.getTime()) >= 3650 * difficulties[currentDifficulty]) {
				spawnEnemey();
				timestamp_spawn = new Date();
			}
			if (player.getScore().getScore() >= 25 && currentDifficulty == 0) {
				currentDifficulty = 1;
				backgroundSound0.stop();
				backgroundSound1.loop(1, 0.25f);
			} else if (player.getScore().getScore() >= 50 && currentDifficulty == 1) {
				currentDifficulty = 2;
				backgroundSound1.stop();
				backgroundSound2.loop(1, 0.25f);
				System.out.println(currentDifficulty);
			} else if (player.getScore().getScore() >= 100 && currentDifficulty == 2) {
				currentDifficulty = 3;
				backgroundSound2.stop();
				backgroundSound3.loop(1, 0.25f);
			}
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		if (c == 13) {
			if (activeEnemy != null) {
				activeEnemy.getWord().setTyped("");
				activeEnemy = null;
			}
		} else {
			if (activeEnemy == null) {
				ArrayList<Enemy> candidates = new ArrayList<Enemy>();
				for (Enemy enemy : enemies) {
					Word word = enemy.getWord();
					if (word.getName().charAt(0) == c) {
						candidates.add(enemy);
					}
				}
				if (candidates.size() > 0) {
					activeEnemy = candidates.get(0);
					for (Enemy candidate : candidates) {
						if (candidate.getY() > activeEnemy.getY())
							activeEnemy = candidate;
					}
				}
			}
			if (activeEnemy != null) {
				Word activeWord = activeEnemy.getWord();
				if (activeWord.getName().length() > activeWord.getTyped().length()) {
					if (activeWord.getName().charAt(activeWord.getTyped().length()) == c) {
						activeWord.addTypedLetter(c);
						timestamp_shoot = new Date();
						player.setShot(
								new Shoot(player.getX(), player.getY(), activeEnemy.getX() + activeEnemy.getWidth() / 2,
										activeEnemy.getY() + activeEnemy.getHeight()));
					} else {
						missSound.play(1, 0.1f);
					}
				}
				if (activeWord.getName().equals(activeWord.getTyped())) {
					int scoreAdd = ((int) (activeWord.getName().length() / 4) > 1)
							? (int) (activeWord.getName().length() / 4)
							: 1;
					player.getScore().addScore(scoreAdd);
					destroyEnemy(activeEnemy);

				}
			} else {
				missSound.play(1, 0.1f);
			}
		}
	}

	public void destroyEnemy(Enemy e) {
		if (activeEnemy == e)
			activeEnemy = null;
		enemies.remove(e);
	}

	public void spawnEnemey() {
		String enemyWord = words.get(random.nextInt(settings.getWords().size()));
		enemies.add(new Enemy(container.getGraphics(),
				random.nextInt(container.getWidth() - Word.calWidth(this.container.getGraphics(), enemyWord)), 0,
				settings.getEnemyColor(), enemyWord, Word.calWidth(this.container.getGraphics(), enemyWord),
				Word.calHeight(this.container.getGraphics(), enemyWord)));
	}
}
