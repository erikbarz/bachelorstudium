package gameMode;

import java.io.Serializable;

public class DefaultMode extends AbstractGameMode  implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8718496717897571977L;

	public DefaultMode(){
		gameModeEnum=GameModeEnum.DEFAULT;
	}
}
