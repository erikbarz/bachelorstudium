package gameMode;

import java.io.Serializable;

public class GodMode extends DefaultMode  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5459224528787775051L;

	public GodMode() {
		gameModeEnum = GameModeEnum.GOD;
	}

}
