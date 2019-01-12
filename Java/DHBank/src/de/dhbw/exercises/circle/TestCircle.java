package de.dhbw.exercises.circle;

import de.dhbw.bank.model.*;

public class TestCircle {
	
	public static void main(String[] args) {

		Circle a = new Circle(3,4,4);
		Circle b = new Circle(3,2,7.4);
		
		System.out.println(a.getArea());
		System.out.println("Has collision: "+a.collidesWith(b));
		
		Circle[] circle = new Circle[4];
		circle[0] = new Circle(1,2,2);
		circle[1] = new Circle(1,2,3);
		circle[2] = new Circle(1,2,2);
		circle[3] = new Circle(1,2,2);
		
		
		double sumArea = 0;
		double sumPerimeter = 0;
		
		for ( int i=0; i<circle.length; i++){
			sumArea += circle[i].getArea();
			sumPerimeter += circle[i].getPerimeter();
		}
		
		System.out.println("Total Area: " + sumArea);
		System.out.println("Total Perimeter: " + sumPerimeter);
		
		
		Clearable cl = new Circle(10, 20, 1000);
		cl.clear();
		double r = ((Circle)cl).getRadius();
		
		
		
		
		
		
		
		
	}
}
