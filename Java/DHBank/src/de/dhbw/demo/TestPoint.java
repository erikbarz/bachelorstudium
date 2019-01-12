package de.dhbw.demo;

import java.util.*;


/**
 * This class ...
 * 
 * @author Rainer Buesch
 */
public class TestPoint {
	public static void main(String[] args) {
		
		Point p1 = new Point(1,1);
		Point p2 = new Point(2,2);
		Point p3 = new Point(3,3);
		
		List<Point> list = new ArrayList();
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		Point p4 = new Point(1,1);
		
		if (p1 == p4) {
			System.out.println("equal by reference");
		}
		if (p1.equals(p4)) {
			System.out.println("equal by equals() method");
		}
		if (p1.equals("Hallo")) {
			System.out.println("equal by equals() method");
		}
		if (list.contains(p4)) {
			System.out.println("List contains point "+p4);
		}
	}

}
