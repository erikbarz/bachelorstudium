package Notenverwaltung;

import java.sql.*;

public class Noten_Durchschnitt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Fehler abfangen
		try {
			//Datenstruktur für die Verbindung mit der Datenbank
			Connection Verbindung;
			
			//Datenstruktur für Aufnahme der SQL Anweisung
			Statement Abfrage;
			
			//Variable für Datenquelle
			String db = "jdbc:mysql://localhost/mydb";
			
			//Username und Passwort
			String user = "root";
			String pw = "ch2007CH1";
			
			//SQL-Anweisung
			String SQLString = 
				"SELECT AVG(note) "+
				"FROM studierender_klausur ";
			
			//Treiber laden
			Class.forName("com.mysql.jdbc.Driver");
			
			// Aufbau der DB-Verbindung
			Verbindung = DriverManager.getConnection(db, user, pw); // hier in () User, pw hinzunehmen, wenn Passwortgeschützt
			
			//Statement anlegen
			Abfrage = Verbindung.createStatement();
			
			//Abfrage ausführen
			ResultSet rs = Abfrage.executeQuery(SQLString); // hier nur 1 Eintrag, da nur 1 berechnete Durchschnittsnote
			
			//Datensätze holen
			while (rs.next()) { // Schleife eigentlich nciht notwendig, da nur 1 Ergebnissatz
				double schnitt = rs.getDouble(1);
				System.out.printf("%.2f\n", schnitt); // syso printF wegen Formatierter Ausgabe
			}
			
			//Verbindung trennen
			Verbindung.close();
			
		} catch (Exception Fehler) {
			System.out.println("Fehler im Programm: ");
			System.out.println(Fehler.toString());
			System.exit(0);
			
		}

	}

}