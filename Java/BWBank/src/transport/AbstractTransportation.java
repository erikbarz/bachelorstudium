package transport;

public abstract class AbstractTransportation implements Transportation {

	private double speed;
	private double load;
	private boolean isRunning;

	/* (non-Javadoc)
	 * @see transport.Transportation#start()
	 */
	@Override
	public void start() {
		isRunning = true;
	}

	/* (non-Javadoc)
	 * @see transport.Transportation#stop()
	 */
	@Override
	public void stop() {
		isRunning = false;
	}

	/* (non-Javadoc)
	 * @see transport.Transportation#getSpeed()
	 */
	@Override
	public double getSpeed() {
		return speed;
	}

	/* (non-Javadoc)
	 * @see transport.Transportation#setSpeed(double)
	 */
	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/* (non-Javadoc)
	 * @see transport.Transportation#getLoad()
	 */
	@Override
	public double getLoad() {
		return load;
	}

	/* (non-Javadoc)
	 * @see transport.Transportation#setLoad(double)
	 */
	@Override
	public void setLoad(double load) {
		this.load = load;
	}

	/* (non-Javadoc)
	 * @see transport.Transportation#isRunning()
	 */
	@Override
	public boolean isRunning() {
		return isRunning;
	}

	/* (non-Javadoc)
	 * @see transport.Transportation#setRunning(boolean)
	 */
	@Override
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	/* (non-Javadoc)
	 * @see transport.Transportation#park()
	 */
	@Override
	public abstract void park();

	@Override
	public String toString() {
		return getClass().getName() + ": isRunning=" + isRunning + ", speed="
				+ speed + ", load=" + load;
	}

}
