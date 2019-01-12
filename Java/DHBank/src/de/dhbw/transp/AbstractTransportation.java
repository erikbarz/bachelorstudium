package de.dhbw.transp;


public abstract class AbstractTransportation implements Transportation, Clearable {

	private final double maximumLoad;
	private double currentLoad;
	private boolean isRunning;

	public AbstractTransportation(double maximumLoad) {
		this.maximumLoad = maximumLoad;
	}
	
	public double getMaximumLoad() {
		return maximumLoad;
	}
	public double getCurrentLoad() {
		return currentLoad;
	}

	public void setCurrentLoad(double load) throws OverloadedException {
		if (load > maximumLoad) {
			throw new OverloadedException(maximumLoad, load);
		}
		this.currentLoad = load;
	}
	
	public void start() {
		isRunning = true;
	}
	
	public void stop() {
		isRunning = false;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public void clear() {
		setCurrentLoad(0);
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName()+": isRunning="+isRunning;
	}
}
