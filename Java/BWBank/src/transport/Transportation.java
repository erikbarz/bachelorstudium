package transport;

public interface Transportation {

	public  void start();

	public  void stop();

	public  double getSpeed();

	public  void setSpeed(double speed);

	public  double getLoad();

	public  void setLoad(double load);

	public  boolean isRunning();

	public  void setRunning(boolean isRunning);

	public  void park();

}