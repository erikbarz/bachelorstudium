package de.dhbw.transp;


public interface Transportation {
	void start();
	void stop();
	boolean isRunning();
	double getCurrentLoad();
	void setCurrentLoad(double load) throws OverloadedException;
	double getMaximumLoad();
}
