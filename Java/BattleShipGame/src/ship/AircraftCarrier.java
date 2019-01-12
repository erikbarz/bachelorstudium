package ship;

import java.io.Serializable;


/**
 * 
 * @author Erik Barz
 *
 */
public class AircraftCarrier extends Ship implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1725965230584129910L;

	public AircraftCarrier( boolean b, int x, int y){
		super(b, 5, x, y);
	}
}
