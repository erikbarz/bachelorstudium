package ship;

import java.io.Serializable;


/**
 * 
 * @author Erik Barz
 *
 */
public class Battleship extends Ship implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7567989542756873638L;

	public Battleship( boolean b, int x, int y){
		super(b, 4, x, y);
	}
}
