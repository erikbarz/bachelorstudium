package de.dhbw.transp;


public class Plane extends AbstractTransportation {
	private boolean isOnAir;

	public Plane(double maximumLoad) {
		super(maximumLoad);
	}
	
	@Override
	public void start() {
		super.start();
		setOnAir(true);
	}
	
	@Override
	public void stop() {
		setOnAir(false);
		super.stop();
	}
	
	public void setOnAir(boolean isOnAir) {
		this.isOnAir = isOnAir;
	}
	public boolean isOnAir() {
		return isOnAir;
	}
	
	@Override
	public String toString() {
		return super.toString()+", isOnAir="+isOnAir;
	}
}
