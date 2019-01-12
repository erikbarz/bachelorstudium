package player;

import playingfield.InvalidCoordinateException;

public interface AI {
	public void doTurn(Player player) throws InvalidCoordinateException;
	
	public void placeShips(Player player);
}
