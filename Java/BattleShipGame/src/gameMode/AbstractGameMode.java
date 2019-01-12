package gameMode;

import java.io.Serializable;

import player.Player;
import playingfield.PlayingField;
import ship.Ship;

public abstract class AbstractGameMode implements GameMode, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected GameModeEnum gameModeEnum;

	@Override
	public PlayingField createPlayingField(Player player) {
		player.setPlayingfield(new PlayingField(player));
		return player.getPlayingfield();
	}

	@Override
	public void createShips(Player player) {
		player.loadShips();
	}

	@Override
	public boolean isGameLost(Player player) {
		for (Ship ship : player.getShips()) {
			if (!isShipSunk(ship))
				return false;
		}

		return true;
	}

	@Override
	public boolean isShipSunk(Ship ship) {
		return ship.getSize() <= ship.getNumHits();
	}


	public GameModeEnum getGameModeEnum() {
		return gameModeEnum;
	}

	public void setGameState(GameModeEnum gameModeEnum) {
		this.gameModeEnum = gameModeEnum;
	}

}
