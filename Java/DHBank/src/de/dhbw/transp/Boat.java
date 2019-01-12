package de.dhbw.transp;


public class Boat extends AbstractTransportation {
	private boolean isAnchored;

	public Boat(double maximumLoad) {
		super(maximumLoad);
	}
	
	@Override
	public void start() {
		setAnchored(false);
		super.start();
	}
	
	@Override
	public void stop() {
		super.stop();
		setAnchored(true);
	}
	
	public void setAnchored(boolean isAnchored) {
		this.isAnchored = isAnchored;
	}
	public boolean isAnchored() {
		return isAnchored;
	}
	
	@Override
	public String toString() {
		return super.toString()+", isAnchored="+isAnchored;
	}
}
