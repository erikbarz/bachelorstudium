package saveGame;

import gameMode.GameMode;

import java.io.Serializable;

import player.Player;

public class GameState  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6280809076993713607L;
	private Player nextPlayer;
	private Player currentPlayer;
	private int numRounds=1;
	private GameMode gameMode=null;
	
	private static GameState instance=null;
	
	private GameState(){

	}
	
	public static GameState getInstance(){
		if(instance==null){
			instance=new GameState();
		}
		return instance;
	}

	public Player getCurrentPlayer(){
		return currentPlayer;
	}
	
	public Player getNextPlayer(){
		return nextPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}

	public GameMode getGameMode(){
		return gameMode;
	}
	
	public void setGameMode(GameMode gameMode){
		this.gameMode=gameMode;
	}
	
	public int getNumRounds(){
		return numRounds;
	}
	
	public void setNumRounds(int rounds){
		numRounds=rounds;
	}
	
	public void increaseNumRounds(){
		numRounds++;
	}
	
	
}
