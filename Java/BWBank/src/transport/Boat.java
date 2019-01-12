package transport;

public class Boat extends AbstractTransportation {

	private boolean isAnchored;
	
	@Override
	public void park() {
		// TODO Auto-generated method stub
		isAnchored=true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString()+", isAnchored="+isAnchored;
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
		setLoad(1000000);
		setSpeed(30);
		isAnchored=false;
	}
	
	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
		park();
		setLoad(0);
		setSpeed(0);
	}
	
}
