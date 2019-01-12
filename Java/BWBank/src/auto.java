/**
 * 
 */

/**
 * @author Erik
 * 
 */
public class auto {

	/**
	 * @param args
	 */
	private final double maxLoad;
	private final String brand;

	private String sign;
	private double currentLoad;

	private double speed;
	private boolean isMotorRunning;
	private int gear = 1;

	public auto(String brand, double maxLoad) {
		this.brand = brand;
		this.maxLoad = maxLoad;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return getClass().getName() + "(brand=" + brand + ",maxLoad=" + maxLoad
				+ ", isMotorRunning=" + isMotorRunning + ",speed=" + speed
				+ ",gear=" + gear + ")";
	}

	public void start() {
		isMotorRunning = true;
	}

	public void stop() {
		if (speed > 0) {
			throw new IllegalArgumentException("Speed must  be 0");
		}
		isMotorRunning = false;
	}

	public void setSpeedTo(double targetSpeed) {
		if (targetSpeed > speed) {
			while (speed < targetSpeed) {
				speed += 1;
				setGear(speed, gear);
			}
		} else {
			while (speed > targetSpeed) {
				speed -= 1;
				setGear(speed, gear);
			}
		}
	}

	public void setGear(double speed, int gear) {
		if (speed < 50) {
			this.gear = 1;
		} else {
			if (speed < 100) {
				this.gear = 2;
			} else {
				if (speed < 100) {
					this.gear = 3;
				} else {
					if (speed < 150) {
						this.gear = 4;
					} else {
						if (speed < 200) {
							this.gear = 5;
						} else {
							this.gear = 6;
						}
					}
				}
			}
		}
		if (gear<this.gear){
			for(int i=gear; i<this.gear;i++){
				System.out.println("in den " + i + ". Gang geschalten ");
			}
		}
		if(gear>this.gear){
			for(int i=gear; i>this.gear;i--){
				System.out.println("in den " + i + ". Gang geschalten ");
			}
		}
	}
	
	public void setCurrentLoad(double currentLoad) {
		if(currentLoad>=0 && currentLoad<=maxLoad){
			this.currentLoad = currentLoad;
		}
		else {
			throw new IllegalArgumentException("Your load is negative or too high! You chose " 
			+ currentLoad);
		}
	}

}
