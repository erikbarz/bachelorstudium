package player;

import java.io.Serializable;
import java.util.Random;

import playingfield.Coordinate;
import playingfield.InvalidCoordinateException;

import ship.Ship;

public class StrongAI implements AI,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7582727273608478697L;
	private boolean isFieldPrioritySet = false;
	private int maxPrio = 1;

	public void doTurn(Player player) throws InvalidCoordinateException {
		if (!isFieldPrioritySet)
			setFieldPriorities(player);

		Coordinate c = setMaxPrio(player);
		// endlosschleife
		while (true) {
			// wenn noch nicht drauf geschossen => schießen und aus der
			// schleife gehen
			if (!player.getEnemy().getPlayingfield().isCoordinateShotAt(c)) {
				System.out.println(player.getName() + " hat geschossen auf "
						+ c.coordinateToString());
				player.getEnemy().getPlayingfield().shootAt(c);
				maxPrio = 0;
				return;
			} else {
				// nächste Coordinate
				c = setMaxPrio(player);
			}
		}

	}

	public Coordinate setMaxPrio(Player player)
			throws InvalidCoordinateException {
		int max = 0;
		Coordinate c = new Coordinate();
		for (int i = 0; i < player.getPlayingfield().getHeight(); i++) {
			for (int n = 0; n < player.getPlayingfield().getWidth(); n++) {
				if (player.getEnemy().getPlayingfield().getFields()[n][i]
						.getPriority() > max) {
					max = player.getEnemy().getPlayingfield().getFields()[n][i]
							.getPriority();
					c = new Coordinate(n + 1, i + 1);
				}
				if (player.getEnemy().getPlayingfield().getFields()[n][i]
						.getPriority() == 4) {
					maxPrio = max;
					return new Coordinate(n + 1, i + 1);
				}
			}
		}
		return c;
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

	private void setFieldPriorities(Player player) {
		for (int i = 0; i < player.getPlayingfield().getHeight(); i++) {
			for (int n = 0; n < player.getPlayingfield().getWidth(); n++) {
				// schachbrett
				if ((i + n) % 2 == 0) {
					player.getEnemy().getPlayingfield().getFields()[n][i]
							.increasePriority();
				}
				// diagonale
				if (i == n) {
					player.getEnemy().getPlayingfield().getFields()[n][i]
							.increasePriority();
				}
				// 2. diagonale
				if (i == player.getPlayingfield().getWidth() - n) {
					player.getEnemy().getPlayingfield().getFields()[n][i]
							.increasePriority();
				}
				// maxPrio setzen
				if (maxPrio < player.getEnemy().getPlayingfield().getFields()[n][i]
						.getPriority()) {
					maxPrio = player.getEnemy().getPlayingfield().getFields()[n][i]
							.getPriority();
				}

			}
		}
		isFieldPrioritySet = true;

	}

	@Override
	public String toString() {

		return "Schwierigkeitsstufe: schwer";
	}

}
