package game;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HighScore implements Comparable<HighScore> {

	private Date date;
	private String losersName;
	private String winnersName;
	private int rounds;

	public HighScore(String winnersName, String losersName, int rounds) {
		// für anlegen eines neuen Highscore
		this(new Date(System.currentTimeMillis()), winnersName, losersName,
				rounds);

	}

	public HighScore(Date date, String losersName, String winnersName,
			int rounds) {
		// für das anlegen aus datei
		this.date = date;
		this.losersName = losersName;
		this.winnersName = winnersName;
		this.rounds = rounds;
	}

	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
		return getWinnersName() + "\t" + getLosersName() + "\t" + getRounds()
				+ "\t\t" + sdf.format(date);
	}

	public String getOutput() {
		return getWinnersName() + "°" + getLosersName() + "°" + getRounds()
				+ "°" + date.getTime();
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setLosersName(String losersName) {
		this.losersName = losersName;
	}

	public void setWinnersName(String winnersName) {
		this.winnersName = winnersName;
	}

	public void setRounds(int rounds) {
		this.rounds = rounds;
	}

	public Date getDate() {
		return date;
	}

	public String getLosersName() {
		return losersName;
	}

	public String getWinnersName() {
		return winnersName;
	}

	public int getRounds() {
		return rounds;
	}

	@Override
	public int compareTo(HighScore o) {
		return date.compareTo(o.getDate());
	}

}
