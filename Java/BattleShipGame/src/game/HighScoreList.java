package game;

import gameMode.GameModeEnum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

import saveGame.GameState;

public class HighScoreList {
	private boolean isEmpty = false;

	public boolean isEmpty() {
		return isEmpty;
	}

	private File scores = null;
	private FileReader fileReader;
	private FileWriter fileWriter;

	private BufferedReader reader;
	private BufferedWriter writer;
	private Vector<HighScore> list = new Vector<HighScore>();

	public HighScoreList() {
		if (GameState.getInstance().getGameMode().getGameModeEnum() == GameModeEnum.DEFAULT) {
			scores = new File(
					"C:\\Users\\Erik\\Studium\\2. Semester\\Java\\BattleShipGame\\src\\Game\\scores.txt");
		} else {
			if (GameState.getInstance().getGameMode().getGameModeEnum() == GameModeEnum.SUDDENDEATH) {
				scores = new File(
						"C:\\Users\\Erik\\Studium\\2. Semester\\Java\\BattleShipGame\\src\\Game\\scoresSudden.txt");
			} else {
				scores = new File(
						"C:\\Users\\Erik\\Studium\\2. Semester\\Java\\BattleShipGame\\src\\Game\\scoresArmada.txt");
			}

		}
	}

	public boolean addHighScore(HighScore s) {
		// file lesen
		readFromDisk();
		int a = list.size() - 1;
		// falls zu schlecht false
		if (list.size() > 0 && s.getRounds() > list.get(a).getRounds()
				&& list.size() >= 10) {
			{
				System.out
						.println("Diesmal hat es leider nicht für einen Highscore gereicht. Vielleicht beim nächsten Mal...");
				return false;
			}

		}

		// falls in den highscore geschafft
		else {

			// richtige position im highscore suchen
			for (int i = list.size() - 1; i > 0; i--) {
				if (s.getRounds() < list.get(i).getRounds())
					a--;
			}

			if (a < 0)
				a++;

			// zur liste hinzufügen
			list.add(a, s);

			// elemente, die zu viel sind löschen
			if (list.size() > 10) {
				for (int n = 10; n < list.size(); n++) {
					list.remove(n);
				}
			}

			sortList();
			System.out
					.println("Herzlichen Glückwunsch! Sie haben es auf Platz "
							+ (list.indexOf(s) + 1)
							+ " im Highscore geschafft!!!");

			// file schreiben
			writeToDisk();
			return true;

		}
	}

	public String getOutput() {
		StringBuilder sb = new StringBuilder();
		sb.append("Gewinner" + "\t\tVerlierer" + "\t\tRundenanzahl"
				+ "\tDatum\n");
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i) + "\n");
		}
		if(isEmpty){
			sb.append("Keine Highscores vorhanden");
			return sb.toString();
		}
		return sb.toString();
	}

	public void readFromDisk() {

		// file, filereader, bufferedreader und input init
		try {
			fileReader = new FileReader(scores);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
		reader = new BufferedReader(fileReader);
		String input = null;

		try {
			// erstes einlesen
			input = reader.readLine();
			if (input == null) {
				isEmpty = true;
			}
			while (input != null) {

				// input string zu string array umwandeln und daraus highscore
				// anlegen
				String[] a = new String[4];
				a = input.split("°", 4);
				HighScore h = new HighScore(new Date(Long.parseLong(a[3])),
						a[1], a[0], Integer.parseInt(a[2]));

				// highscore in liste einfügen
				list.add(h);

				// nächsten Satz einlesen
				input = reader.readLine();

			}
			// liste sortieren
			if (list.size() != 0) {
				sortList();
				isEmpty = false;
			}

		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
	}

	private void sortList() {
		for (int i = 0; i < list.size(); i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).getRounds() < list.get(j - 1).getRounds()) {
					// vertauschen
					HighScore temp = list.get(j);
					list.set(j, list.get(j - 1));
					list.set(j - 1, temp);
				}
			}
		}
	}

	public void writeToDisk() {
		// Filewriter und bufferedwriter init
		try {
			fileWriter = new FileWriter(scores);
		} catch (IOException e) {
			System.out.println("IOException");
			e.printStackTrace();
		}
		writer = new BufferedWriter(fileWriter);

		// highscores in file schreiben
		for (int m = 0; m < list.size(); m++) {
			try {
				writer.write(list.get(m).getOutput(), 0, list.get(m)
						.getOutput().length());
				writer.newLine();
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public Vector<HighScore> getList() {
		return list;
	}

}
