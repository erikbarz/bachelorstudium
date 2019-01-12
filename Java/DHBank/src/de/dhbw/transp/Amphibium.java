package de.dhbw.transp;

public class Amphibium implements Transportation {

	private final Car car;
	private final Boat boat;

	private boolean isOnWater;

	public Amphibium(double maximumLoad, boolean isOnWater) {
		this.isOnWater = isOnWater;
		car = new Car(maximumLoad);
		boat = new Boat(maximumLoad);
	}

	public boolean isOnWater() {
		return isOnWater;
	}
	public void setOnWater(boolean isOnWater) {
		this.isOnWater = isOnWater;
	}

	public void start() {
		if (isOnWater) {
			boat.start();
		} else {
			car.start();
		}
	}

	public void stop() {
		if (isOnWater) {
			boat.stop();
		} else {
			car.stop();
		}
	}

	public boolean isRunning() {
		return (isOnWater) ? boat.isRunning(): car.isRunning();
	}

	public double getMaximumLoad() {
		return boat.getMaximumLoad();
	}

	public double getCurrentLoad() {
		return (isOnWater) ? boat.getCurrentLoad(): car.getCurrentLoad();
	}

	public void setCurrentLoad(double load) throws OverloadedException {
		if (isOnWater) {
			boat.setCurrentLoad(load);
		} else {
			car.setCurrentLoad(load);
		}
	}
	
	@Override
	public String toString() {
		String str = getClass().getSimpleName()+": isOnWater="+isOnWater+", ";
		if (isOnWater) {
			str += boat;
		} else {
			str += car;
		}
		return str;
	}
}
