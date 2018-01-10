package de.mankodach.TypingGame;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.newdawn.slick.*;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

public class TypingGame extends BasicGame {
	private GameContainer container;
	private BackgroundSound backgroundSound0;
	private BackgroundSound backgroundSound1;
	private BackgroundSound backgroundSound2;
	private BackgroundSound backgroundSound3;
	private Sound endSound;
	private Sound missSound;
	private Sound destroyEnemySound;
	private Sound clearActiveSound;
	private Player player;
	private Enemy activeEnemy;
	private boolean noEnemies;
	private Enemy passedEnemy;
	private ArrayList<Enemy> enemies;
	private ArrayList<String> words;
	private Date timestamp_spawn;
	private Date timestamp_move;
	private Date timestamp_shoot;
	private int pointer_timestamps_wpm;
	private ArrayList<Date[]> timestamps_wpm;
	private Random random;
	private IntAttribute currentLevel;
	private ParticleSystem particleSystem;
	private ConfigurableEmitter explosion;
	private Level[] levels;

	public static final String gamename = "Crazy typing Game!";
	public Settings settings;

	public TypingGame() {
		super(gamename);
		this.settings = new Settings();
		this.activeEnemy = null;
		this.words = settings.getWords();
		this.random = new Random();
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer container = new AppGameContainer(new TypingGame());
		try {
			container.setDisplayMode(720, 690, false);
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
		currentLevel = new IntAttribute("Level", 0);
		backgroundSound0 = new BackgroundSound("bg0", "res/backgroundSound0.ogg");
		backgroundSound1 = new BackgroundSound("bg1", "res/backgroundSound1.ogg");
		backgroundSound2 = new BackgroundSound("bg2", "res/backgroundSound2.ogg");
		backgroundSound3 = new BackgroundSound("bg3", "res/backgroundSound3.ogg");
		levels = new Level[] { new Level(backgroundSound0, 1, 25), new Level(backgroundSound0, 0.75f, 250),
				new Level(backgroundSound1, 0.675f, 700), new Level(backgroundSound1, 0.6f, 1500),
				new Level(backgroundSound2, 0.55f, 3000), new Level(backgroundSound2, 0.5f, 6000),
				new Level(backgroundSound3, 0.45f, 12000), new Level(backgroundSound3, 0.4f, Integer.MAX_VALUE), };
		levels[0].getBackgroundSound().loop(1, 0.25f);
		endSound = new Sound("res/ending.wav");
		missSound = new Sound("res/miss.wav");
		destroyEnemySound = new Sound("res/destroyEnemy.wav");
		clearActiveSound = new Sound("res/clearActive.wav");
		timestamp_spawn = new Date();
		timestamp_move = new Date();
		timestamps_wpm = new ArrayList<Date[]>();
		pointer_timestamps_wpm = 0;
		noEnemies = true;
		player = new Player(container.getWidth() / 2, container.getHeight(), Color.white);
		this.enemies = new ArrayList<Enemy>();
		spawnEnemey();
		passedEnemy = null;

		try {
			particleSystem = ParticleIO.loadConfiguredSystem("res/explosion.xml");
			particleSystem.getEmitter(0).setEnabled(false);
			particleSystem.setRemoveCompletedEmitters(true);

			explosion = (ConfigurableEmitter) particleSystem.getEmitter(0);
			explosion.setEnabled(false);
		} catch (Exception e) {
			System.out.println("Error adding explosion\nCheck for explosion.xml");
			System.exit(0);
		}

	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		particleSystem.render();

		player.draw(g);

		player.getScore().draw(g, container.getWidth() - g.getFont().getWidth(player.getScore().createString()) - 5, 5);

		String levelstring = currentLevel.createString(1);
		g.drawString(levelstring, container.getWidth() - g.getFont().getWidth(levelstring) - 5,
				g.getFont().getHeight(player.getScore().getName()) + 10);

		String lifepointsString = player.getLifepoints().createString();
		player.getLifepoints().draw(g, container.getWidth() - g.getFont().getWidth(lifepointsString) - 5,
				container.getHeight() - g.getFont().getHeight(lifepointsString) - 5);

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
			if ((new Date().getTime() - timestamp_move.getTime()) >= 15
					* levels[currentLevel.getInt()].getDifficulty()) {
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
			float lostStrHeight = g.getFont().getHeight("You Lost");
			float destroyedWordsStrHeight = g.getFont().getHeight(player.getDestroyedWords().createString());
			float missedStrHeight = g.getFont().getHeight(player.getMissed().createString());
			g.drawString("You Lost", container.getWidth() / 2 - g.getFont().getWidth("You Lost") / 2,
					container.getHeight() / 2 - lostStrHeight / 2);
			player.getDestroyedWords().draw(g,
					container.getWidth() / 2 - g.getFont().getWidth(player.getDestroyedWords().createString()) / 2,
					container.getHeight() / 2 + lostStrHeight + 5);
			player.getMissed().draw(g,
					container.getWidth() / 2 - g.getFont().getWidth(player.getMissed().createString()) / 2,
					container.getHeight() / 2 + lostStrHeight + destroyedWordsStrHeight + 5);

			player.getWpm().draw(g, container.getWidth() / 2 - g.getFont().getWidth(player.getWpm().createString()) / 2,
					container.getHeight() / 2 + lostStrHeight + destroyedWordsStrHeight + missedStrHeight + 5);
		}
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		particleSystem.update(delta);
		if (container.isPaused()) {
			backgroundSound0.stop();
			backgroundSound1.stop();
			backgroundSound2.stop();
			backgroundSound3.stop();
			endSound.loop(1, 0.02f);
		} else {
			if (player.getLifepoints().getInt() == 0) {
				container.setPaused(true);
				timestamps_wpm.get(timestamps_wpm.size() - 1)[1] = new Date();
				float timeDif = 0;
				for (Date[] wpmTimes : timestamps_wpm) {
					timeDif += wpmTimes[1].getTime() - wpmTimes[0].getTime();
				}
				player.setWpm(new IntAttribute("Words per Minute",
						Math.round(player.getDestroyedWords().getInt() / (timeDif / 1000 / 60))));
			}

			if (passedEnemy != null) {
				player.subLifepoints(1);
				destroyEnemy(passedEnemy);
				passedEnemy = null;
			}

			if (!enemies.isEmpty() && noEnemies) {
				noEnemies = false;
				timestamps_wpm.add(new Date[] { new Date(), null });
			}
			if (enemies.isEmpty() && !noEnemies) {
				noEnemies = true;
				timestamps_wpm.get(pointer_timestamps_wpm)[1] = new Date();
				pointer_timestamps_wpm++;
			}

			if ((new Date().getTime() - timestamp_spawn.getTime()) >= 3650
					* levels[currentLevel.getInt()].getDifficulty()) {
				spawnEnemey();
				timestamp_spawn = new Date();
			}

			if (player.getScore().getInt() >= levels[currentLevel.getInt()].getScoreLimit()) {
				if (!(levels[currentLevel.getInt()].getBackgroundSound().getName()
						.equals(levels[currentLevel.getInt() + 1].getBackgroundSound().getName()))) {
					levels[currentLevel.getInt()].getBackgroundSound().stop();
					levels[currentLevel.getInt() + 1].getBackgroundSound().loop(1, 0.25f);
				}
				currentLevel.setInt(currentLevel.getInt() + 1);
				player.getLifepoints().add(1);
			}
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		if (!container.isPaused()) {
			if (key == 28) {
				if (activeEnemy != null) {
					activeEnemy.getWord().setTyped("");
					activeEnemy = null;
					clearActiveSound.play();
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
							player.setShot(new Shot(player.getX(), player.getY(),
									activeEnemy.getX() + activeEnemy.getWidth() / 2,
									activeEnemy.getY() + activeEnemy.getHeight(), settings.getShotColor()));
						} else {
							if (key != 42) {
								missSound.play(1, 0.1f);
								player.incMissed();
							}
						}
					}
					if (activeWord.getName().equals(activeWord.getTyped())) {
						int scoreAdd = activeWord.getName().length() * (currentLevel.getInt() + 1);
						player.getScore().add(scoreAdd);

						float x = activeEnemy.getX();
						float y = activeEnemy.getY();
						destroyEnemy(activeEnemy);
						player.incDestroyedEnemies();
						destroyEnemySound.play(0.95f, 0.2f);
						addExplosion(x, y);
					}
				} else {
					if (key != 42) {
						missSound.play(1, 0.1f);
						player.incMissed();
					}
				}
			}
		} 
//		else {
//			if (key == 28) {
//				try {
//					endSound.stop();
//					init(container);
//					container.setPaused(false);
//				} catch (SlickException e) {
//					e.printStackTrace();
//				}
//			}
//		} Warum funtioniert der kack nicht?
		if (key == 1) {
			container.exit();
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

	public void addExplosion(float x, float y) {
		ConfigurableEmitter e = explosion.duplicate();
		e.setEnabled(true);
		e.setPosition(x, y);
		particleSystem.addEmitter(e);
	}
}
