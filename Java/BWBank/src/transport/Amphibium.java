package transport;

public class Amphibium implements Transportation {

	private Car car=new Car(4);
	private Boat boat=new Boat();
	
	private boolean isOnWater;
	
	
	
	public Amphibium(int numWheels, boolean isOnWater) {
		this.car=new Car(numWheels);
		this.isOnWater = isOnWater;
	}

	public Amphibium(boolean isOnWater) {
		
		this.isOnWater = isOnWater;
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		if(isOnWater){
			 boat.start();
		}
		else{
			 car.start();
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		if(isOnWater){
			 boat.stop();
		}
		else{
			 car.stop();
		}
	}

	@Override
	public double getSpeed() {
		if(isOnWater){
			return boat.getSpeed();
		}
		
			return car.getSpeed();
		
	}

	@Override
	public void setSpeed(double speed) {
		// TODO Auto-generated method stub
		if(isOnWater){
			boat.setSpeed(speed);
		}
		else{
			car.setSpeed(speed);
		}
	}

	@Override
	public double getLoad() {
		if(isOnWater){
			return boat.getLoad();
		}
		
			return car.getLoad();
		
	}

	@Override
	public void setLoad(double load) {
		if(isOnWater){
			 boat.setLoad(load);
		}
		else{
			 car.setLoad(load);
		}
	}

	@Override
	public boolean isRunning() {
		if(isOnWater){
			return boat.isRunning();
		}
		
			return car.isRunning();
		
	}

	@Override
	public void setRunning(boolean isRunning) {
		// TODO Auto-generated method stub
		if(isOnWater){
			 boat.setRunning(isRunning);
		}
		else{
			 car.setRunning(isRunning);
		}
	}

	@Override
	public void park() {
		// TODO Auto-generated method stub
		if(isOnWater){
			 boat.park();
		}
		else{
			 car.park();
		}
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		//tertiäre notation von if else
		String str=(isOnWater) ? boat.toString() : car.toString();
		
		return getClass().getName()+" ,is on water="+isOnWater+str;
	}
	

}
