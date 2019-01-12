package transport;

public class Car extends AbstractTransportation {

	private final int numWheels;
	private boolean doorsClosed;

	public Car(int numWheels) {
		this.numWheels = numWheels;

	}

	@Override
	public void park() {
		// TODO Auto-generated method stub
		doorsClosed = true;
	}

	public int getNumWheels() {
		return numWheels;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + ", doorsClosed=" + doorsClosed
				+ ", numWheels=" + numWheels;
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		doorsClosed=true;
		setLoad(76);
		setSpeed(80);
		super.start();
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
		setSpeed(0);
		setLoad(0);
		doorsClosed=false;
	}

}
