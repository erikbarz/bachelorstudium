package player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import playingfield.InvalidCoordinateException;
import playingfield.PlayingField;
import ship.Ship;
import ship.ShipFactory;

/**
 * 
 * @author Erik Barz
 * 
 */

public abstract class Player  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3300740590076910765L;
	protected String name;
	protected Player enemy;
	protected List<Ship> ships = new ArrayList<Ship>();
	protected PlayingField playingField = new PlayingField(this);

	public Player(String name) {
		setName(name);
	}

	public Player() {

	}

	public Player(String name, Player enemy) {
		this(name);
		this.enemy = enemy;
	}

	public void loadShips() {
			ships.add(ShipFactory.getShip(5));
			ships.add(ShipFactory.getShip(4));
			ships.add(ShipFactory.getShip(3));
			ships.add(ShipFactory.getShip(2));
			ships.add(ShipFactory.getShip(2));
			ships.add(ShipFactory.getShip(1));
			ships.add(ShipFactory.getShip(1));
	}

	public String getName() {
		return name;
	}

	/**
	 * 
	 * @throws IllegalArgumentException
	 */
	public void setName(String name) {
		if (!name.equals("")) {
			this.name = name;
		} else {
			throw new IllegalArgumentException(
					"Bitte geben Sie einen Namen ein");
		}
	}

	public Player getEnemy() {
		return enemy;
	}

	public void setEnemy(Player enemy) {
		this.enemy = enemy;
	}

	@Override
	public abstract String toString();

	/**
	 * Spielzug durchführen
	 */
	public abstract void doTurn() throws InvalidCoordinateException;

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	public PlayingField getPlayingfield() {
		return playingField;
	}

	public void setPlayingfield(PlayingField playingField) {
		this.playingField = playingField;
	}

	public void placeShips() {
		// durch das schiff array
		for (Ship ship : ships) {
			// bis schiff-koordinate gültig ist neue zufallswerte
			do {
				Random rnd = new Random();
				int x = rnd.nextInt(playingField.getWidth()) + 1;
				int y = rnd.nextInt(playingField.getHeight()) + 1;
				boolean b = rnd.nextBoolean();
				ship.setX(x);
				ship.setY(y);
				ship.setHorizontal(b);

			} while (!playingField.isShipLocationValid(ship));

			// schiff setzen
			playingField.placeShip(ship);
		}
	}

}
