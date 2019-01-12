package gameMode;

import java.io.Serializable;

import ship.Ship;

public class SuddenDeathMode extends AbstractGameMode  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6811827439102322800L;

	public SuddenDeathMode() {
		gameModeEnum=GameModeEnum.SUDDENDEATH;
	}

	@Override
	public boolean isShipSunk(Ship ship) {
		return ship.getNumHits()>=1;
	}
	
}
