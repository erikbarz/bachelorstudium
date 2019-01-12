package player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

import playingfield.Coordinate;
import playingfield.InvalidCoordinateException;

import saveGame.GamePersistor;
import saveGame.GameState;
import saveGame.SaveGame;
import ship.Ship;

/**
 * 
 * @author Erik Barz
 * 
 */
public class HumanPlayer extends Player  implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7993423833558774567L;

	public void doTurn() {

		Coordinate c;

		while (true) {
			System.out
					.println("Bitte geben Sie die Koordinaten für Ihren Schuss oder S für Speichern ein!");
			
			try {
				// koordinate erstellen
				
				String bla="";
				while(bla.length()<2){
					bla=einlesen();
					if(bla.length()<2&&!bla.equalsIgnoreCase("S")){
						System.out.println("Ihre Koordinate ist zu kurz. Richtige Form ist A2");
					}
					if(bla.equalsIgnoreCase("S")){
						GamePersistor gp=new GamePersistor();
						System.out.println("Bitte geben Sie eine kurze Beschreibung des Spiels ein!");
						gp.saveGame(new SaveGame(einlesen(), GameState.getInstance()));
					}
				}
				c = new Coordinate(bla);
				
				// wenn koordinate gültig => drauf schießen
				if (playingField.isCoordinateValid(c)
						&& !enemy.getPlayingfield().isCoordinateShotAt(c)) {
					System.out.println(this.name + " hat geschossen auf "
							+ c.coordinateToString());
					enemy.getPlayingfield().shootAt(c);

					return;
				}
				System.out.println("Ihre Koordinaten waren nicht gültig, versuchen Sie es erneut");

			} catch (InvalidCoordinateException e) {
				System.out
						.println("Ihre Eingabe war leider falsch. Die korrekte Form ist A3. Beachten Sie auch die Spielregeln");
				e.printStackTrace();
			}

		}

	}

	@Override
	public String toString() {
		return "Player-Mensch [name=" + name + ", enemy=" + enemy.getName()
				+ "]";
	}

	public HumanPlayer(String name, Player enemy) {
		super(name, enemy);

	}

	public HumanPlayer(String name) {
		super(name);

	}

	public void placeShipsNotAutomatic() {
		// durch schiff array
		for (Ship ship : ships) {

				// solange schiff noch nicht gesetzt
				while (!ship.isShipSet()) {
					System.out.println("Bitte geben Sie nun die Koordinaten für Ihren "
							+ ship.getClass().getSimpleName() + " ein.");
					try {
						// koordinaten einlesen und prüfen
						String a = einlesen();
						ship.setC(new Coordinate(a));
						
						System.out.println("  Bitte wählen:");
						System.out.println("    H - horizontal");
						System.out.println("    V - vertikal");
						
						//horizontal oder vertikal
						String b = einlesen();
						if (b.equalsIgnoreCase("h"))
							{ship.setHorizontal(true);}
						else
							{ship.setHorizontal(false);}
						
						//schiff setzen und printen oder Meldung
						if (playingField.isShipLocationValid(ship))
							{playingField.placeShip(ship);
							playingField.getOutput();}
						

					} catch (InvalidCoordinateException e) {
						System.out.println("Die korrekte Form ist A2 h");
						e.printStackTrace();
					}
				
			} 
			
		}
	}

	public static String einlesen() {
		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(
					System.in));
			return in.readLine();
		} catch (IOException e) {
			System.out.println("Fehler");
			e.printStackTrace();
			return null;
		}
	}
}
