package Notenverwaltung;

import java.sql.*;

public class Notenverwaltung {

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
			String SQLString = "select nachname from Studierender;";
			
			//Treiber laden
			Class.forName("com.mysql.jdbc.Driver");
			
			// Aufbau der DB-Verbindung
			Verbindung = DriverManager.getConnection(db, user, pw); // hier in () User, pw hinzunehmen, wenn Passwortgeschützt
			
			//Statement anlegen
			Abfrage = Verbindung.createStatement();
			
			//Abfrage ausführen
			ResultSet rs = Abfrage.executeQuery(SQLString);
			
			//Datensätze holen
			while (rs.next()) {
				String nachname = rs.getString(1); // Index 1, weil 1 ID ist und das erste Merkmal im Select Nachname ist
				System.out.println(nachname);
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
