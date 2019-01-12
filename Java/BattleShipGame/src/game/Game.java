package game;

import gameMode.ArmadaMode;
import gameMode.DefaultMode;
import gameMode.GameMode;
import gameMode.GameModeEnum;
import gameMode.SuddenDeathMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import player.AI;
import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import player.SimpleAI;
import player.StrongAI;
import playingfield.InvalidCoordinateException;
import saveGame.GameState;

/**
 * 
 * @author Erik Barz
 * 
 */
public class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7885065253517260799L;
	HighScoreList hList;
	private GameState gameState;
	private static GameMode gameMode;

	public void playGame1PC() throws InvalidCoordinateException {
		// gamemode wählen
		prepareGame();

		// Spieler anlegen
		HumanPlayer player1 = new HumanPlayer("Spieler 1");
		System.out.println("Bitte den ersten Spielernamen eingeben");
		player1.setName(einlesen());
		System.out.println("Hallo " + player1.getName()
				+ "! Viel Spaß beim BattleShip Spielen!\n");

		// Schwierigkeit wählen
		System.out.println("  Bitte Schwierigkeit wählen:");
		System.out.println("    L - leichter PC-Gegner");
		System.out.println("    S - starker PC-Gegner" + "\n");

		AI ai;

		boolean leicht = false;
		if (einlesen().equalsIgnoreCase("L")) {
			ai = new SimpleAI();
			System.out
					.println("Sie haben sich für ein Spiel gegen den leichten PC-Gegner entschieden\n");
			leicht = true;
		} else {
			ai = new StrongAI();
			System.out
					.println("Sie haben sich für ein Spiel gegen den starken PC-Gegner entschieden\n");
			leicht = false;
		}

		ComputerPlayer player2 = new ComputerPlayer("Computer(leicht)",
				player1, ai);
		player1.setEnemy(player2);

		if (!leicht)
			player2.setName("Computer(schwer)");

		GameModeEnum j = gameMode.getGameModeEnum();
		// schiffe selbst oder auto setzen
		if (j == GameModeEnum.ARMADA) {
			gameMode.createPlayingField(player1);
			gameMode.createShips(player1);
			player1.placeShips();
			gameMode.createPlayingField(player2);
		} else {
			System.out
					.println("  Bitte wählen, wie die Schiffe gesetzt werden sollen:");
			System.out.println("    S - selbst die Schiffe setzen");
			System.out.println("    A - automatisch die Schiffe setzen" + "\n");
			String b = einlesen();
			if (b.equalsIgnoreCase("S")) {
				gameMode.createShips(player1);
				player1.placeShipsNotAutomatic();
			} else {
				gameMode.createShips(player1);
				player1.placeShips();
			}
		}

		// pc schiffe setzen
		gameMode.createShips(player2);
		player2.placeShips();

		// spielfeld drucken
		player1.getPlayingfield().getOutput();
		// eigentliches spiel
		gameState.setNumRounds(0);
		do {
			gameState.increaseNumRounds();
			System.out
					.println(".....................................................\n");
			System.out.println("Runde: " + gameState.getNumRounds());

			// zug spieler 1
			gameState.setCurrentPlayer(player1);
			gameState.setNextPlayer(player2);
			gameState.getCurrentPlayer().doTurn();
			gameState.getCurrentPlayer().getPlayingfield().getOutput();
			if (gameMode.isGameLost(gameState.getNextPlayer()))
				break;

			// zug spieler 2
			gameState.setCurrentPlayer(player2);
			gameState.setNextPlayer(player1);
			gameState.getCurrentPlayer().doTurn();

			System.out.println("\n" + "\n");
		} while (!gameMode.isGameLost(player1) && !gameMode.isGameLost(player2));

		// nach dem Spiel
		if (gameMode.isGameLost(player2)) {
			System.out.println("Glückwunsch " + player1.getName()
					+ "! Du hast den Computer in " + gameState.getNumRounds()
					+ " Runden besiegt!" + "\n" + "\n");
			hList.addHighScore(new HighScore(player1.getName(), player2
					.getName(), gameState.getNumRounds()));
		} else {
			System.out
					.println("Da hat wohl jemand gegen den PC verloren....  :'("
							+ "\n" + "\n");
		}

	}

	public void playGame2PC() throws InvalidCoordinateException {

		// gamemode wählen
		prepareGame();

		ComputerPlayer player1 = null;
		ComputerPlayer player2 = null;

		// Schwierigkeit für 1 wählen
		System.out.println("  Bitte Schwierigkeit für Computer1 wählen:");
		System.out.println("    L - leichte PC-Gegner");
		System.out.println("    S - starke PC-Gegner" + "\n");
		String dif = einlesen();
		// Spieler anlegen
		if (dif.equalsIgnoreCase("L")) {
			player1 = new ComputerPlayer("Computer1(leicht)");
			player1.setKi(new SimpleAI());
		} else {
			player1 = new ComputerPlayer("Computer1(schwer)");
			player1.setKi(new StrongAI());
		}

		// Schwierigkeit für 2 wählen
		System.out.println("  Bitte Schwierigkeit für Computer2 wählen:");
		System.out.println("    L - leichte PC-Gegner");
		System.out.println("    S - starke PC-Gegner" + "\n");
		String dif2 = einlesen();
		// Spieler2 anlegen
		if (dif2.equalsIgnoreCase("L")) {
			player2 = new ComputerPlayer("Computer2(leicht)", player1,
					new SimpleAI());
			player1.setEnemy(player2);
		} else {
			player2 = new ComputerPlayer("Computer2(schwer)", player1,
					new StrongAI());
			player1.setEnemy(player2);
		}

		// playingfield und ships setzen
		gameMode.createPlayingField(player1);
		gameMode.createPlayingField(player2);
		gameMode.createShips(player1);
		gameMode.createShips(player2);

		// schiffe platzieren und spielfelder ausgeben
		player1.placeShips();
		player1.getPlayingfield().getOutput();
		player2.placeShips();
		player2.getPlayingfield().getOutput();

		// eigentliches spiel
		gameState.setNumRounds(0);
		do {
			gameState.increaseNumRounds();
			System.out
					.println(".....................................................\n");
			System.out.println("Runde: " + gameState.getNumRounds());

			// zug spieler 1
			gameState.setCurrentPlayer(player1);
			gameState.setNextPlayer(player2);
			gameState.getCurrentPlayer().doTurn();
			gameState.getCurrentPlayer().getPlayingfield().getOutput();
			if (gameMode.isGameLost(gameState.getNextPlayer()))
				break;

			// zug spieler 1
			gameState.setCurrentPlayer(player2);
			gameState.setNextPlayer(player1);
			gameState.getCurrentPlayer().doTurn();
			gameState.getCurrentPlayer().getPlayingfield().getOutput();

			System.out.println("\n" + "\n");

		} while (!gameMode.isGameLost(player1) && !gameMode.isGameLost(player2));

		// nach dem spiel
		hList = new HighScoreList();
		if (gameMode.isGameLost(player1)) {
			System.out.println(player2.getName() + " hat " + player1.getName()
					+ " in " + gameState.getNumRounds() + " Runden besiegt!"
					+ "\n" + "\n");
			hList.addHighScore(new HighScore(player2.getName(), player2
					.getEnemy().getName(), gameState.getNumRounds()));
		} else {
			System.out.println(player1.getName() + " hat " + player2.getName()
					+ " in " + gameState.getNumRounds() + " Runden besiegt!"
					+ "\n" + "\n");
			hList.addHighScore(new HighScore(player1.getName(), player1
					.getEnemy().getName(), gameState.getNumRounds()));
		}
	}

	public void playSavedGame() throws InvalidCoordinateException {
		gameState = GameState.getInstance();
		Player player1=gameState.getCurrentPlayer();
		Player player2= gameState.getNextPlayer();
		
		gameState.getCurrentPlayer().getPlayingfield().getOutput();
		
		do {
			gameState.increaseNumRounds();
			System.out
					.println(".....................................................\n");
			System.out.println("Runde: " + gameState.getNumRounds());

			// zug spieler 1
			gameState.setCurrentPlayer(player1);
			gameState.setNextPlayer(player2);
			gameState.getCurrentPlayer().doTurn();
			gameState.getCurrentPlayer().getPlayingfield().getOutput();
			if (gameState.getGameMode().isGameLost(gameState.getNextPlayer()))
				break;

			// zug spieler 2
			gameState.setCurrentPlayer(player2);
			gameState.setNextPlayer(player1);
			gameState.getCurrentPlayer().doTurn();

			System.out.println("\n" + "\n");
		} while (!gameState.getGameMode().isGameLost(player1) && !gameState.getGameMode().isGameLost(player2));

		// nach dem Spiel
		if (gameState.getGameMode().isGameLost(player2)) {
			System.out.println("Glückwunsch " + player1.getName()
					+ "! Du hast den Computer in " + gameState.getNumRounds()
					+ " Runden besiegt!" + "\n" + "\n");
			hList.addHighScore(new HighScore(player1.getName(), player2
					.getName(), gameState.getNumRounds()));
		} else {
			System.out
					.println("Da hat wohl jemand gegen den PC verloren....  :'("
							+ "\n" + "\n");
		}

	}

	private void prepareGame() {
		selectGameMode();
		gameState = GameState.getInstance();
	}

	private void selectGameMode() {
		// Spielmodus wählen
		System.out.println("  Bitte Spielmodus wählen:");
		System.out
				.println("    D - Default Mode (Spielfeld 10x10,7 Schiffe, Spieler schießen abwechselnd)");
		System.out
				.println("    S - Sudden Death (wie Default, außer: 1 Treffer versenkt das Schiff)");
		System.out
				.println("    A - Armada Mode  (wie Default, außer: Spielfeld 30x30, 63 Schiffe)\n");

		String mode = einlesen();
		if (mode.equalsIgnoreCase("D")) {
			gameMode = new DefaultMode();
			System.out
					.println("Sie haben sich für ein Spiel im Default-Mode entschieden\n");
		} else {
			if (mode.equalsIgnoreCase("S")) {
				gameMode = new SuddenDeathMode();
				System.out
						.println("Sie haben sich für ein Spiel im SuddenDeath-Mode entschieden");
			} else {
				gameMode = new ArmadaMode();
				System.out
						.println("Sie haben sich für ein Spiel im Armada-Mode entschieden\n");
			}

		}
		GameState.getInstance().setGameMode(gameMode);
	}



	public static void setGameMode(GameMode gameMode) {
		Game.gameMode = gameMode;
	}

	public static String einlesen() {
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			return in.readLine();
		} catch (IOException e) {
			System.out.println("Fehler");
			e.printStackTrace();
			return null;
		}
	}

}
