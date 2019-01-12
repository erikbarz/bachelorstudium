package ship;

import java.io.Serializable;

public class Destroyer extends Ship  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1429037799732842653L;

	public Destroyer( boolean b, int x, int y){
		super(b, 2, x, y);
	}
	
}
