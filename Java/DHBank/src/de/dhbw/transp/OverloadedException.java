package de.dhbw.transp;


public class OverloadedException extends RuntimeException {
	
	private final double maxLoad;
	private final double currentLoad;

	public OverloadedException(double maxLoad, double currentLoad) {
		this.maxLoad = maxLoad;
		this.currentLoad = currentLoad;
	}
	
	@Override
	public String getMessage() {
		return "Load <"+ currentLoad+ "> exceeds maximum load <"+maxLoad+">";
	}
}
