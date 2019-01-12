package de.dhbw.demo;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class Point extends Object {
	
	private int x;
	private int y;
	
	public Point() {
		this(0, 0);
	}
	
	public Point(int x, int y) {
		setX(x);
		setY(y);
	}

	public void scale(double factor) {
		x = (int) (x * factor);
		y = (int) (y * factor);
	}

	public void setX(int x) {
		this.x = x;
	}
		
	public void setY(int y) {
		this.y = y;
	}	
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			return true;
		}
		
		if (obj instanceof Point) {
			Point p = (Point) obj;
			if (x == p.getX() && y == p.getY()) {
				return true;
			}			
		}
		return false;
	}
	
//	@Override
//	public String toString() {
//		return getClass().getName()+": x="+x+", y="+y;
//	}
}










