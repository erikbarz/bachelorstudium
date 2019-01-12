package saveGame;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GamePersistor {

	private File saves = new File(
			"C:\\Users\\Erik\\Studium\\2. Semester\\Java\\BattleShipGame\\src\\saveGame\\saveGames.txt");
	private List<SaveGame> savedGamesList = new ArrayList<SaveGame>();

	public SaveGame loadGame(int slot) {
		if (savedGamesList.size() > 0) {
			if (slot < savedGamesList.size()) {
				return savedGamesList.get(slot);
			} else {
				throw new IllegalArgumentException("Eingabe ungültig");
			}
		} else {
			System.out.println("keine SaveGames vorhanden");
			return null;
		}
	}

	public void saveGame(SaveGame saveGame) {
		readFromDisk();
		savedGamesList.add(saveGame);
		writeToDisk();
	}

	public String getOutput() {

		if (savedGamesList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < savedGamesList.size(); i++) {
				sb.append("(" + (i + 1) + ") " + savedGamesList.get(i) + "\n");
			}

			return sb.toString();
		} else {
			return "Keine SaveGames vorhanden";
		}
	}

	@SuppressWarnings("unchecked")
	public void readFromDisk() {
		try {
			FileInputStream fileIn = new FileInputStream(saves);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Object o=in.readObject();
			if(o!=null){
				savedGamesList=(List<SaveGame>)o;
			}
			
			fileIn.close();
		} 
		catch(EOFException i2){
			System.out.println("Datei ist leer!");
		}
		catch (IOException i) {
			i.printStackTrace();
			System.out.println(i.getMessage());
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("class not found.");
			c.printStackTrace();
			return;
		}
	}

	public void writeToDisk() {
		try {
			FileOutputStream fileOut = new FileOutputStream(saves);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
				out.writeObject(savedGamesList);
			
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public int getListSize() {
		return savedGamesList.size();
	}
}
