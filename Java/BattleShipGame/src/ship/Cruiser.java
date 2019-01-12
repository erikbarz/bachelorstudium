package ship;

import java.io.Serializable;


/**
 * 
 * @author Erik Barz
 *
 */
public class Cruiser extends Ship implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4396800378568763535L;

	public Cruiser( boolean b, int x, int y){
		super(b, 3, x, y);
	}
}
