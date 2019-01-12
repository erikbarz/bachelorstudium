package gameMode;

import java.io.Serializable;

import player.Player;
import playingfield.PlayingField;

public class ArmadaMode extends AbstractGameMode  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2076624973532820827L;

	public ArmadaMode(){
		gameModeEnum=GameModeEnum.ARMADA;
	}
	
	@Override
	public void createShips(Player player) {
		for(int i=0;i<9;i++){
			player.loadShips();
		}
		
	}
	
	@Override
	public PlayingField createPlayingField(Player player) {
		player.setPlayingfield(new PlayingField(player,30,30));
		return player.getPlayingfield();
	}

}
