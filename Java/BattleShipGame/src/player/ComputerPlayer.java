package player;

import java.io.Serializable;
import java.util.Random;

import playingfield.InvalidCoordinateException;

/**
 * 
 * @author Erik Barz
 * 
 */
public class ComputerPlayer extends Player  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1644761838508788223L;
	Random rnd = new Random();
	boolean b = rnd.nextBoolean();

	private AI ki=new SimpleAI();
	
	
	public ComputerPlayer(String name, Player enemy, AI ai) {
		super(name, enemy);
		setKi(ai);
	}

	public ComputerPlayer(String name) {
		super(name);
	}

	public void setKi(AI ki) {
		this.ki = ki;
	}

	public AI getKi() {
		return ki;
	}

	public void doTurn() throws InvalidCoordinateException {
		ki.doTurn(this);
	}

	public String toString() {
		return "Player-Computer [name=" + name + ", enemy=" + enemy.getName()
				+ ki + "]";
	}

}
