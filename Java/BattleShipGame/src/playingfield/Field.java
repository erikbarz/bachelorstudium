package playingfield;

import java.io.Serializable;

import ship.Ship;

public class Field  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6099815786890183014L;
	private Ship ship=null;
	private int priority=1;
	private FieldState fieldState=FieldState.WATER;

	
	public int getPriority() {
		return priority;
	}

	public void increasePriority(){
		priority++;
	}
	
	public Ship getShip(){
		return ship;
	}
	
	public FieldState getOwningPlayerFieldState(){
		return fieldState;
	}
	
	public FieldState getEnemyPlayerFieldState(){
		if(fieldState==FieldState.HIT||fieldState==FieldState.MISS||fieldState==FieldState.SUNK)
		return fieldState;
		else return FieldState.UNKNOWN;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setFieldState(FieldState fieldState) {
		this.fieldState = fieldState;
	}

	public void setShip(Ship ship){
		this.ship=ship;
	}
	
	
	
}
