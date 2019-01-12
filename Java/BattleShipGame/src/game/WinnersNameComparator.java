package game;

import java.util.Comparator;

public class WinnersNameComparator implements Comparator<HighScore> {

	@Override
	public int compare(HighScore h1, HighScore h2) {
		return h1.getWinnersName().compareTo(h2.getWinnersName());
	}

}
