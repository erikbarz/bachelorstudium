package saveGame;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveGame implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6159257196571717863L;
	public Date date;
	public String description;
	public GameState gameState;

	
	
	public SaveGame(String description, GameState gameState) {
		date=new Date();
		this.description = description;
		this.gameState = gameState;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
		
		return description+"\t"+sdf.format(date);
	}

}
