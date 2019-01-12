package gameMode;

import player.Player;
import playingfield.PlayingField;
import ship.Ship;

public interface GameMode {

	PlayingField createPlayingField(Player player);

	void createShips(Player player);

	boolean isGameLost(Player player);

	boolean isShipSunk(Ship ship);
	
	public GameModeEnum getGameModeEnum();
}
