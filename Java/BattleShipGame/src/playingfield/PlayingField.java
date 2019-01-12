package playingfield;

import gameMode.GameModeEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import player.Player;
import saveGame.GameState;
import ship.AircraftCarrier;
import ship.Battleship;
import ship.Cruiser;
import ship.Destroyer;
import ship.Ship;
import ship.Submarine;

/**
 * 
 * @author Erik Barz
 * 
 */
public class PlayingField  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1367024691670570654L;
	Field[][] spielfeld;
	int height;
	int width;
	Player player;

	public PlayingField(Player player) {
		this(player, 10, 10);
	}

	public PlayingField(Player player, int height, int width) {
		spielfeld = new Field[width][height];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				spielfeld[j][i] = new Field();
			}
		}
		this.player = player;
		this.width = width;
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Field getFieldAtCoordinate(Coordinate c) {
		Field f = spielfeld[c.getX() - 1][c.getY() - 1];
		return f;
	}

	public Field[][] getFields() {
		return spielfeld;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Ship getShipAtCoordinate(Coordinate c) {
		return getFieldAtCoordinate(c).getShip();
	}

	public boolean isCoordinateValid(Coordinate c) {
		if (c.getX() > 0 && c.getX() <= width && c.getY() > 0
				&& c.getY() <= height) {
			return true;
		} else
			return false;
	}

	public boolean isShipLocationValid(Ship ship) {
		// allgemein
		if (ship.getX() < 1 || ship.getY() < 1 || ship.getX() > width
				|| ship.getY() > height) {
			System.out.println("Achtung: allgemeiner Fehler");
			return false;
		}

		// horizontal
		if (ship.isHorizontal()) {
			if (ship.getX() + ship.getSize() > width + 1) {
				System.out.println("Achtung: Breite des Spielfelds beachten");
				return false;
			}

			for (int i = 0; i < ship.getSize(); i++) {
				// proximity auf schiff
				if (spielfeld[ship.getX() - 1 + i][ship.getY() - 1]
						.getOwningPlayerFieldState() == FieldState.PROXIMITY) {
					System.out.println("Achtung: Beachte Proximity auf Schiff");
					return false;
				}
				// schiff auf schiff
				if (spielfeld[ship.getX() - 1 + i][ship.getY() - 1]
						.getOwningPlayerFieldState() == FieldState.SHIP) {
					System.out.println("Achtung: Beachte Schiff auf Schiff");
					return false;
				}

			}
			return true;

		}

		// vertikal
		else {
			if (ship.getY() + ship.getSize() > height + 1) {
				System.out.println("Achtung: Höhe des Spielfelds beachten");
				return false;
			}

			for (int i = 0; i < ship.getSize(); i++) {
				// proximity auf schiff
				if (spielfeld[ship.getX() - 1][ship.getY() - 1 + i]
						.getOwningPlayerFieldState() == FieldState.PROXIMITY) {
					System.out.println("Achtung: Beachte Proximity auf Schiff");
					return false;
				}
				// schiff auf schiff
				if (spielfeld[ship.getX() - 1][ship.getY() - 1 + i]
						.getOwningPlayerFieldState() == FieldState.SHIP) {
					System.out.println("Achtung: Beachte Schiff auf Schiff");
					return false;
				}

			}
			return true;
		}

	}

	public boolean isCoordinateShotAt(Coordinate c) {
		if (getFieldAtCoordinate(c).getOwningPlayerFieldState() == FieldState.HIT
				|| getFieldAtCoordinate(c).getOwningPlayerFieldState() == FieldState.MISS
				|| getFieldAtCoordinate(c).getOwningPlayerFieldState() == FieldState.SUNK)
			return true;
		else
			return false;
	}

	public void placeShip(Ship ship) {
		if (isShipLocationValid(ship)) {
			ship.setShipSet(true);
			// horizontal
			if (ship.isHorizontal()) {
				for (int i = 0; i < ship.getSize(); i++) {
					// fieldstatus auf ship setzen und variable ship setzen
					spielfeld[ship.getX() - 1 + i][ship.getY() - 1]
							.setFieldState(FieldState.SHIP);
					spielfeld[ship.getX() - 1 + i][ship.getY() - 1]
							.setShip(ship);

					// proximity links
					if (i == 0 && ship.getX() > 1)
						spielfeld[ship.getX() - 2][ship.getY() - 1]
								.setFieldState(FieldState.PROXIMITY);
					// proximity rechts
					if (i == ship.getSize() - 1
							&& ship.getX() + ship.getSize() <= width)
						spielfeld[ship.getX() - 1 + ship.getSize()][ship.getY() - 1]
								.setFieldState(FieldState.PROXIMITY);
					// proximity oben
					if (ship.getY() > 1)
						spielfeld[ship.getX() - 1 + i][ship.getY() - 2]
								.setFieldState(FieldState.PROXIMITY);
					// proximity unten
					if (ship.getY() < height)
						spielfeld[ship.getX() - 1 + i][ship.getY()]
								.setFieldState(FieldState.PROXIMITY);
				}
			}
			// vertikal
			else {
				for (int i = 0; i < ship.getSize(); i++) {
					// fieldstatus auf ship setzen und variable ship setzen
					spielfeld[ship.getX() - 1][ship.getY() - 1 + i]
							.setFieldState(FieldState.SHIP);
					spielfeld[ship.getX() - 1][ship.getY() - 1 + i]
							.setShip(ship);
					// proximity links
					if (ship.getX() > 1)
						spielfeld[ship.getX() - 2][ship.getY() - 1 + i]
								.setFieldState(FieldState.PROXIMITY);
					// proximity rechts
					if (ship.getX() < width)
						spielfeld[ship.getX()][ship.getY() - 1 + i]
								.setFieldState(FieldState.PROXIMITY);
					// proximity oben
					if (i == 0 && ship.getY() > 1)
						spielfeld[ship.getX() - 1][ship.getY() - 2]
								.setFieldState(FieldState.PROXIMITY);
					// proximity unten
					if (i == ship.getSize() - 1
							&& ship.getY() + ship.getSize() <= height)
						spielfeld[ship.getX() - 1][ship.getY() + ship.getSize()
								- 1].setFieldState(FieldState.PROXIMITY);
				}
			}
		}
		// wenn koordinaten nicht korrekt
		else {
			System.out
					.println("Achtung:  Eingegebene Koordinaten nicht korrekt!!!  "
							+ ship);
		}

	}

	public Field[][] getSpielfeld() {
		return spielfeld;
	}

	public void shootAt(Coordinate c) {

		Field f = getFieldAtCoordinate(c);
		f.setPriority(0);

		// wenn schiff getroffen
		if (f.getOwningPlayerFieldState() == FieldState.SHIP) {
			f.getShip().increaseNumHits();

			System.out.println("Treffer");

			// wenn nicht gesunken
			if (!GameState.getInstance().getGameMode().isShipSunk(f.getShip())) {
				f.setFieldState(FieldState.HIT);
				// drüber priorität hoch
				if (c.getY() > 1
						&& getFields()[c.getX() - 1][c.getY() - 2]
								.getPriority() != 0) {
					getFields()[c.getX() - 1][c.getY() - 2].setPriority(4);
				}
				// drunter priorität hoch
				if (c.getY() < height
						&& getFields()[c.getX() - 1][c.getY()].getPriority() != 0) {
					getFields()[c.getX() - 1][c.getY()].setPriority(4);
				}
				// links priorität hoch
				if (c.getX() > 1
						&& getFields()[c.getX() - 2][c.getY() - 1]
								.getPriority() != 0) {
					getFields()[c.getX() - 2][c.getY() - 1].setPriority(4);
				}
				// rechts priorität hoch
				if (c.getX() < width
						&& getFields()[c.getX()][c.getY() - 1].getPriority() != 0) {
					getFields()[c.getX()][c.getY() - 1].setPriority(4);
				}

				// bei 2 Hits nebeneinander, 4er drüber und drunter auf 0
				// links daneben
				if (c.getX() > 1
						&& getFields()[c.getX() - 2][c.getY() - 1]
								.getOwningPlayerFieldState() == FieldState.HIT) {
					if (c.getY() < height) {
						getFields()[c.getX() - 1][c.getY()].setPriority(0);
						getFields()[c.getX() - 2][c.getY()].setPriority(0);
					}
					if (c.getY() > 1) {
						getFields()[c.getX() - 1][c.getY() - 2].setPriority(0);
						getFields()[c.getX() - 2][c.getY() - 2].setPriority(0);
					}
				}
				// rechts daneben
				if (c.getX() < width
						&& getFields()[c.getX()][c.getY() - 1]
								.getOwningPlayerFieldState() == FieldState.HIT) {
					if (c.getY() < height) {
						getFields()[c.getX() - 1][c.getY()].setPriority(0);
						getFields()[c.getX()][c.getY()].setPriority(0);
					}
					if (c.getY() > 1) {
						getFields()[c.getX() - 1][c.getY() - 2].setPriority(0);
						getFields()[c.getX()][c.getY() - 2].setPriority(0);
					}
				}

				// bei 2 Hits übereinander, 4er links und rechts auf 0
				// drüber
				if (c.getY() > 1
						&& getFields()[c.getX() - 1][c.getY() - 2]
								.getOwningPlayerFieldState() == FieldState.HIT) {
					if (c.getX() < width) {
						getFields()[c.getX()][c.getY() - 1].setPriority(0);
						getFields()[c.getX()][c.getY() - 2].setPriority(0);
					}
					if (c.getX() > 1) {
						getFields()[c.getX() - 2][c.getY() - 2].setPriority(0);
						getFields()[c.getX() - 2][c.getY()].setPriority(0);
					}
				}
				// drunter
				if (c.getY() < height
						&& getFields()[c.getX() - 1][c.getY()]
								.getOwningPlayerFieldState() == FieldState.HIT) {
					if (c.getX() < width) {
						getFields()[c.getX()][c.getY() - 1].setPriority(0);
						getFields()[c.getX()][c.getY()].setPriority(0);
					}
					if (c.getX() > 1) {
						getFields()[c.getX() - 2][c.getY() - 1].setPriority(0);
						getFields()[c.getX() - 2][c.getY()].setPriority(0);
					}
				}
			}

			// wenn gesunken
			else {
				System.out.println("Versenkt!");
				for (int i = 0; i < f.getShip().getSize(); i++) {
					if (f.getShip().isHorizontal()) {
						spielfeld[f.getShip().getX() - 1 + i][f.getShip()
								.getY() - 1].setFieldState(FieldState.SUNK);
						spielfeld[f.getShip().getX() - 1 + i][f.getShip()
								.getY() - 1].setPriority(0);

					} else {
						spielfeld[f.getShip().getX() - 1][f.getShip().getY()
								- 1 + i].setFieldState(FieldState.SUNK);
						spielfeld[f.getShip().getX() - 1][f.getShip().getY()
								- 1 + i].setPriority(0);
					}

				}

				// feldprios horizontal
				if (f.getShip().isHorizontal()) {
					for (int i = 0; i < f.getShip().getSize(); i++) {

						// proximity links
						if (i == 0 && f.getShip().getX() > 1)
							spielfeld[f.getShip().getX() - 2][f.getShip()
									.getY() - 1].setPriority(0);
						// proximity rechts
						if (i == f.getShip().getSize() - 1
								&& f.getShip().getX() + f.getShip().getSize() <= width)
							spielfeld[f.getShip().getX() - 1
									+ f.getShip().getSize()][f.getShip().getY() - 1]
									.setPriority(0);
						// proximity oben
						if (f.getShip().getY() > 1)
							spielfeld[f.getShip().getX() - 1 + i][f.getShip()
									.getY() - 2].setPriority(0);
						// proximity unten
						if (f.getShip().getY() < height)
							spielfeld[f.getShip().getX() - 1 + i][f.getShip()
									.getY()].setPriority(0);
					}
				}
				// feldprios vertikal
				else {
					for (int i = 0; i < f.getShip().getSize(); i++) {

						// proximity links
						if (f.getShip().getX() > 1)
							spielfeld[f.getShip().getX() - 2][f.getShip()
									.getY() - 1 + i].setPriority(0);
						// proximity rechts
						if (f.getShip().getX() < width)
							spielfeld[f.getShip().getX()][f.getShip().getY()
									- 1 + i].setPriority(0);
						// proximity oben
						if (i == 0 && f.getShip().getY() > 1)
							spielfeld[f.getShip().getX() - 1][f.getShip()
									.getY() - 2].setPriority(0);
						// proximity unten
						if (i == f.getShip().getSize() - 1
								&& f.getShip().getY() + f.getShip().getSize() <= height)
							spielfeld[f.getShip().getX() - 1][f.getShip()
									.getY() + f.getShip().getSize() - 1]
									.setPriority(0);
					}
				}
			}
		}
		// wenn kein schiff getroffen
		else {
			f.setFieldState(FieldState.MISS);

			System.out.println("Leider daneben geschossen ");
		}
	}

	public void getOutput() {

		List<Ship> sortedShips=new ArrayList<Ship>();
		sortedShips=player.getEnemy().getShips();
		Collections.sort(sortedShips);
		
		
		// Spielernamen printen
		System.out.println("\n    Spielfeld von " + player.getName()
				+ "\t\t   Spielfeld von " + player.getEnemy().getName());

		System.out.print("    ");
		// 2 mal die head-zeile drucken
		for (int n = 0; n < 2; n++) {
			for (int i = 0; i < width; i++) {
				if (i < 9)
					System.out.print("_" + (i + 1) + "_ ");
				if (i >= 9)
					System.out.print("" + (i + 1) + "_ ");

			}
			System.out.print("       ");
		}

		// äußere for schleife
		char a = 'A';
		for (int i = 0; i < height; i++) {

			// linke beschriftung drucken
			System.out.print("\n" + (a) + "  |");

			// innere for-schleife für eigenes feld
			for (int j = 0; j < width; j++) {

				if (spielfeld[j][i].getShip() != null) {
					// wenn getroffen *
					if (spielfeld[j][i].getOwningPlayerFieldState() == FieldState.HIT) {
						System.out.print("_*_|");
					}
					// wenn gesunken X
					else {
						if (spielfeld[j][i].getOwningPlayerFieldState() == FieldState.SUNK) {
							System.out.print("_X_|");
						} else {
							// wenn schiff nicht getroffen => Buchstabe
							if (spielfeld[j][i].getShip() instanceof Submarine)
								System.out.print("_S_|");
							if (spielfeld[j][i].getShip() instanceof AircraftCarrier)
								System.out.print("_A_|");
							if (spielfeld[j][i].getShip() instanceof Cruiser)
								System.out.print("_C_|");
							if (spielfeld[j][i].getShip() instanceof Destroyer)
								System.out.print("_D_|");
							if (spielfeld[j][i].getShip() instanceof Battleship)
								System.out.print("_B_|");
						}
					}
				}
				// wenn kein schiff => ~ oder O
				else {
					if (spielfeld[j][i].getOwningPlayerFieldState() == FieldState.MISS)
						System.out.print("_O_|");
					else
						System.out.print("_~_|");
				}
			}

			// buchstabe für linke beschriftung
			System.out.print("   " + a + "  |");

			// innere for-schleife für gegnerisches feld
			for (int j = 0; j < width; j++) {
				if (player.getEnemy().getPlayingfield().getFields()[j][i]
						.getEnemyPlayerFieldState() == FieldState.HIT) {
					System.out.print("_*_|");
				}
				if (player.getEnemy().getPlayingfield().getFields()[j][i]
						.getEnemyPlayerFieldState() == FieldState.MISS) {
					System.out.print("_O_|");
				}
				if (player.getEnemy().getPlayingfield().getFields()[j][i]
						.getEnemyPlayerFieldState() == FieldState.UNKNOWN) {
					System.out.print("_?_|");
				}
				if (player.getEnemy().getPlayingfield().getFields()[j][i]
						.getEnemyPlayerFieldState() == FieldState.SUNK) {
					System.out.print("_X_|");
				}
			}

			//Ausgabe der gegnerischen Schiffe außer im armada-mode
			if(GameState.getInstance().getGameMode().getGameModeEnum()==GameModeEnum.DEFAULT||GameState.getInstance().getGameMode().getGameModeEnum()==GameModeEnum.SUDDENDEATH)
			{
				if(i<7)System.out.print("\t"+sortedShips.get(i).getOutput());
			}
			
			// // innere for-schleife für gegnerisches feld priorities
			// // buchstabe für linke beschriftung
			// System.out.print("   " + a + "  |");
			// for (int j = 0; j < width; j++) {
			// System.out.print("_"
			// + player.getEnemy().getPlayingfield().getFields()[j][i]
			// .getPriority() + "_|");
			// }

			// Buchstabe für linke beschriftung hochzählen
			a++;
			
			
		}

	}
}
