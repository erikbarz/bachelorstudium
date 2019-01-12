package game;

import gameMode.ArmadaMode;
import gameMode.DefaultMode;
import gameMode.SuddenDeathMode;

import java.util.Collections;

import playingfield.InvalidCoordinateException;
import saveGame.GamePersistor;
import saveGame.GameState;

public class BattleShip {

	/**
	 * 
	 * @author Erik Barz
	 * @throws InvalidCoordinateException
	 * @throws InterruptedException
	 * 
	 */
	public static void main(String[] args) throws InvalidCoordinateException,
			InterruptedException {

		System.out.println("Willkommen bei Schiffe Versenken!" + "\n" + "\n");
		printLogo();
		while (true) {
			showMenu();
		}

	}

	public static void showMenu() throws InvalidCoordinateException,
			InterruptedException {
		// Menu
		System.out.println("-- BattleShip Hauptmenu --");
		System.out.println("  Bitte wählen:");
		System.out.println("    N - Neues Spiel");
		System.out.println("    L - Spiel laden");
		System.out.println("    H - High Score");
		System.out.println("    A - Autor");
		System.out.println("    B - Beenden" + "\n");

		String a = Game.einlesen();

		// neues Spiel
		if (a.equalsIgnoreCase("N")) {
			Game spiel = new Game();

			System.out.println("  Bitte wählen:");
			System.out.println("    S - selbst gegen PC spielen");
			System.out.println("    P - PC gegen PC" + "\n");

			String b = Game.einlesen();
			if (b.equalsIgnoreCase("S")) {
				System.out.println("Sie haben ein Spiel gegen den PC gewählt");
				spiel.playGame1PC();
			} else {
				spiel.playGame2PC();
			}
			System.out
					.println(".....................................................\n");
		}
		// spiel laden
		if (a.equalsIgnoreCase("L")) {
			GamePersistor g = new GamePersistor();
			System.out.println("  Bitte wählen Sie einen Spielstand aus:");

			g.readFromDisk();
			if (g.getListSize() > 0) {
				System.out.println(g.getOutput());

				String bum = Game.einlesen();
				
				int bam = Integer.parseInt(bum);
				
				if (bam > 0 && bam <= g.getListSize()) {
					Game f=new Game();
					GameState gs = g.loadGame(bam - 1).getGameState();
					GameState.getInstance().setCurrentPlayer(
					gs.getCurrentPlayer());
					GameState.getInstance().setNextPlayer(gs.getNextPlayer());
					GameState.getInstance().setGameMode(gs.getGameMode());
					GameState.getInstance().setNumRounds(gs.getNumRounds());
					
					f.playSavedGame();
				} else {
					System.out.println("Ihre Eingabe war ungültig!");
				}
			}
			System.out
					.println(".....................................................\n");
		}
		// Highscore
		if (a.equalsIgnoreCase("H")) {

			System.out.println("  Bitte wählen:");
			System.out.println("    R - nach Runden sortiert");
			System.out.println("    G - nach Gewinner sortiert");
			System.out.println("    D - nach Datum sortiert" + "\n");
			String sort = Game.einlesen();

			Game.setGameMode(new DefaultMode());
			GameState.getInstance().setGameMode(new DefaultMode());
			
			HighScoreList h = new HighScoreList();
			h.readFromDisk();
			if (sort.equalsIgnoreCase("G"))
				Collections.sort(h.getList(), new WinnersNameComparator());
			if (sort.equalsIgnoreCase("D"))
				Collections.sort(h.getList());
			System.out.println("Highscores aus dem Default-Mode: \n"
					+ h.getOutput());
			System.out
					.println(".....................................................\n");

			Game.setGameMode(new SuddenDeathMode());
			GameState.getInstance().setGameMode(new SuddenDeathMode());
			h = new HighScoreList();
			h.readFromDisk();
			if (sort.equalsIgnoreCase("G"))
				Collections.sort(h.getList(), new WinnersNameComparator());
			if (sort.equalsIgnoreCase("D"))
				Collections.sort(h.getList());
			System.out.println("Highscores aus dem SuddenDeath-Mode: \n"
					+ h.getOutput());
			System.out
					.println(".....................................................\n");

			Game.setGameMode(new ArmadaMode());
			GameState.getInstance().setGameMode(new ArmadaMode());
			h = new HighScoreList();
			h.readFromDisk();
			if (sort.equalsIgnoreCase("G"))
				Collections.sort(h.getList(), new WinnersNameComparator());
			if (sort.equalsIgnoreCase("D"))
				Collections.sort(h.getList());
			System.out.println("Highscores aus dem Armada-Mode: \n"
					+ h.getOutput());
			System.out
					.println(".....................................................\n");

		}
		// Autor
		if (a.equalsIgnoreCase("A")) {
			System.out.println("Autor dieses Spiels ist: Erik Barz" + "\n");
			System.out
					.println(".....................................................\n");
		}
		// beenden
		if (a.equalsIgnoreCase("B")) {
			System.out
					.println("Vielen Dank fürs Spielen, bis zum Nächsten Mal!");
			Thread.sleep(3000);

			System.exit(0);
		}

	}

	public static void printLogo() {
		System.out
				.println("__________         __    __  .__           _________.__    .__        ");
		System.out
				.println("\\ _____   \\ ____ _/  |__/  |_|  |   ____  /   _____/|  |__ |__|_____  ");
		System.out
				.println(" |    |  _/\\ _  \\    _\\    _\\   | _/ __\\ \\ _____ \\  |  | \\ | \\ ____\\  ");
		System.out
				.println(" |    |  \\  / __\\ |  |  |  | |  |_\\  ___/ /       \\ |   Y \\   |  |_> >");
		System.out
				.println(" |______  /(____  /__|  |__| |____/\\ __  >_______  /|___|  /__|   __/ ");
		System.out
				.println("       \\ /     \\ /                    \\ /       \\ /     \\ /   |__|  "
						+ "\n" + "\n");
	}

}
