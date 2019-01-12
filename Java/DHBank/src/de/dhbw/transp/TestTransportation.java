package de.dhbw.transp;


public class TestTransportation {

	public static void main(String[] args) {
		
		Transportation[] transp = new Transportation[] {
				new Boat(9000),
				new Car(600),
				new Plane(3000),
				new Amphibium(1000, false)
		};
		
		System.out.println("\nStarting transportations...");
		for (Transportation t : transp) {
			t.start();
			System.out.println(t);
		}
		
		System.out.println("\nStopping transportations...");
		for (Transportation t : transp) {
			t.stop();
			System.out.println(t);
		}
	}
}
