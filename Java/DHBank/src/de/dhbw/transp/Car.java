package de.dhbw.transp;


public class Car extends AbstractTransportation {
	private final int numWheels;
	private boolean isDoorOpen;

	public Car(double maximumLoad) {
		this(maximumLoad, 4);
	}
	public Car(double maximumLoad, int wheels) {
		super(maximumLoad);
		this.numWheels = wheels;
	}
	
	@Override
	public void start() {
		setDoorOpen(false);
		super.start();
	}
	
	@Override
	public void stop() {
		super.stop();
		setDoorOpen(true);
	}
	
	public int getNumWheels() {
		return numWheels;
	}
	
	public void setDoorOpen(boolean isOpen) {
		this.isDoorOpen = isOpen;
	}
	public boolean isDoorOpen() {
		return isDoorOpen;
	}
	
	@Override
	public String toString() {
		return super.toString()+", numWheels="+numWheels+", isDoorOpen="+isDoorOpen;
	}
}
