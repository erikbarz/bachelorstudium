package transport;

public class Plane extends AbstractTransportation {

	boolean isFlying;
	boolean isFahrwerkDrinne;
	
	@Override
	public void park() {
		// TODO Auto-generated method stub
		isFahrwerkDrinne=false;
		isFlying=false;
		setLoad(0);
		setSpeed(0);
	}
	
	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
		isFahrwerkDrinne=true;
		isFlying=true;
		setLoad(100000);
		setSpeed(1000);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
		park();
	}

	public boolean isFlying() {
		return isFlying;
	}

	public boolean isFahrwerkDrinne() {
		return isFahrwerkDrinne;
	}
	
	
}
