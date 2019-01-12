package player;

import java.io.Serializable;
import java.util.Random;

import playingfield.Coordinate;
import playingfield.InvalidCoordinateException;
import ship.Ship;

public class SimpleAI implements AI, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8203268386168754763L;

	@Override
	public void doTurn(Player player) throws InvalidCoordinateException {
		Coordinate c;

		// endlosschleife
		while (true) {
			// zufalls-koordinate erstellen
			Random r = new Random();
			c = new Coordinate(r.nextInt(player.getPlayingfield().getWidth()) + 1, r.nextInt(player.getPlayingfield().getHeight()) + 1);

			// wenn noch nicht drauf geschossen => schießen und aus der schleife
			// gehen
			if (!player.getEnemy().getPlayingfield().isCoordinateShotAt(c)) {
				System.out.println(player.getName() + " hat geschossen auf "
						+ c.coordinateToString());
				player.getEnemy().getPlayingfield().shootAt(c);

				return;
			}
		}

	}

	@Override
	public void placeShips(Player player) {
		
		// durch das schiff array
		for (Ship ship : player.getShips()) {
			// bis schiff-koordinate gültig ist neue zufallswerte
			do {
				Random rnd = new Random();
				int x = rnd.nextInt(player.getPlayingfield().getWidth()) + 1;
				int y = rnd.nextInt(player.getPlayingfield().getHeight()) + 1;
				boolean b = rnd.nextBoolean();
				ship.setX(x);
				ship.setY(y);
				ship.setHorizontal(b);

			} while (!player.getPlayingfield().isShipLocationValid(ship));

			// schiff setzen
			player.getPlayingfield().placeShip(ship);
		}

	}
	
	@Override
	public String toString() {
		
		return "Schwierigkeitsstufe: einfach";
	}

}
