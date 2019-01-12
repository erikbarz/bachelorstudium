package ship;

import java.io.Serializable;

public class Submarine extends Ship implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8746392120062433206L;

	public Submarine( boolean b, int x, int y){
		super(b, 1, x, y);
	}
}
