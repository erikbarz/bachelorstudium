/**
 * 
 */
package de.dhbw.demo;

/**
 * @author Erik
 * 
 */
public class Point {

	/**
	 * @param args
	 */

	private int x;
	private int y;

	public Point() {
		this(0, 0);
	}

	public Point(int x, int y) {
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		checkNotNegative(x);
		this.x = x;

	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		checkNotNegative(y);
		this.y = y;

	}

	private void checkNotNegative(int value) {
		if (value < 0) {
			throw new IllegalArgumentException(value + " must not be negative");
		}
	}

	@Override
	// wurde geerbt und wird nun verändert
	public String toString() {
		// TODO Auto-generated method stub
		return getClass().getName() + "(x=" + x + ",y=" + y + ")";
	}

}
