package de.dhbw.exercises.circle;

import de.dhbw.bank.model.*;
import de.dhbw.demo.*;

public class Circle extends Point implements Clearable {

	private double radius;

	public Circle(int x, int y) {
		this(x, y, 0);
	}

	public Circle(int x, int y, double radius) {
		super(x, y);
		this.radius = radius;
	}
	
	public void clear() {
		radius = 0;
	}

	public boolean collidesWith(Circle c) {
		double distancex = getX() - c.getX();
		double distancey = getY() - c.getY();

		double distance = Math.sqrt(Math.pow(distancex, 2) + Math.pow(distancey, 2));
		if (distance < (this.getRadius() + c.getRadius())) {
			return true;
		}
		return false;
	}

	public double getArea() {
		return Math.PI * radius * radius;
	}

	public double getPerimeter() {
		return Math.PI * radius * 2;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		if (radius < 0) {
			throw new IllegalArgumentException("Radius must not be negative");
		}
		this.radius = radius;
	}

	@Override
	public String toString() {
		return super.toString()+", radius="+radius;
	}
}
















