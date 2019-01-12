package ship;

import java.io.Serializable;

import playingfield.Coordinate;
import saveGame.GameState;

/**
 * 
 * @author Erik Barz
 * 
 */
public abstract class Ship implements Comparable<Ship>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -888732898713873581L;
	protected boolean horizontal;
	protected int size;
	protected Coordinate c = new Coordinate();
	protected boolean shipSet = false;

	public int numHits = 0;

	public Ship(boolean horizontal, int size, int x, int y) {
		this.horizontal = horizontal;
		this.size = size;
		setX(x);
		setY(y);
	}

	public boolean isShipSet() {
		return shipSet;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	public int getNumHits() {
		return numHits;
	}

	public int getX() {
		return c.getX();
	}

	public int getY() {
		return c.getY();
	}

	public int getSize() {
		return size;
	}

	public void setShipSet(boolean shipSet) {
		this.shipSet = shipSet;
	}

	public void setX(int x) {
		c.setX(x);
	}

	public Coordinate getC() {
		return c;
	}

	public void setC(Coordinate c) {
		this.c = c;
	}

	public void setY(int y) {
		c.setY(y);
	}

	public void increaseNumHits() {
		numHits++;
	}

	@Override
	public String toString() {
		return "Ship [horizontal=" + horizontal + ", size=" + size + ", x="
				+ getX() + ", y=" + getY() + "]";
	}

	@Override
	public int compareTo(Ship s) {
		boolean thisSunk = GameState.getInstance().getGameMode().isShipSunk(this);
		boolean shipSunk = GameState.getInstance().getGameMode().isShipSunk(s);
		Integer thisSize = size;
		Integer shipSize = s.getSize();
		if (thisSunk && !shipSunk)
			return 1;
		if (!thisSunk && shipSunk)
			return -1;
		return -thisSize.compareTo(shipSize);
	}

	public String getOutput() {
		String s ="";
		for (int i = 0; i < size; i++) {
			if (!GameState.getInstance().getGameMode().isShipSunk(this))
				s = s + getClass().getSimpleName().substring(0, 1);
			else
				s = s + "X";
		}

		return s;
	}

}
