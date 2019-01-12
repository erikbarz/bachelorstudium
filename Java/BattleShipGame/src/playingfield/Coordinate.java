package playingfield;

import java.io.Serializable;

public class Coordinate  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5964678263287042450L;
	int x=1;
	int y=1;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Coordinate(){}
	
	public Coordinate(int x, int y) throws InvalidCoordinateException {
		if (x < 1 || y < 1) {
			throw new InvalidCoordinateException("x=" + x + ",y=" + y);
		}
		this.x = x;
		this.y = y;
	}

	public Coordinate(String s) throws InvalidCoordinateException {
		x = zweiteZahl(s);
		y = ersteZahl(s);
		if (x < 1 || y < 1) {
			y = 1;
			x = 1;
			throw new InvalidCoordinateException(s);
		}
	}

	private int ersteZahl(String s) {
		int y;
		y = (int) (s.toUpperCase().charAt(0));
		y = y - 64;
		return y;
	}

	private int zweiteZahl(String s) {
		String hilf;
		if (s.length() == 2) {
			hilf = s.charAt(1) + "";
			return Integer.parseInt(hilf);
		} 
		else{
			return Integer.parseInt(s.substring(1));
		}
		

	}

	@Override
	public String toString() {

		return "coordinate: x=" + x + "; y=" + y;
	}

	public String coordinateToString() {
		char a = (char) (y + 64);
		String hilf = a + "" + x;
		return hilf;
	}

}
