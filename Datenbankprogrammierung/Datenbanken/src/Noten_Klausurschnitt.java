package Notenverwaltung;

import java.sql.*;

public class Noten_Klausurschnitt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Fehler abfangen
		try {
			//Datenstruktur f�r die Verbindung mit der Datenbank
			Connection Verbindung;
			
			//Datenstruktur f�r Aufnahme der SQL Anweisung
			Statement Abfrage;
			
			//Variable f�r Datenquelle
			String db = "jdbc:mysql://localhost/mydb";
			
			//Username und Passwort
			String user = "root";
			String pw = "ch2007CH1";
			
			//SQL-Anweisung
			String SQLString = 
				"SELECT AVG(note) "+
				"FROM studierender_klausur "+
				"GROUP BY idklausur; ";
			
			//Treiber laden
			Class.forName("com.mysql.jdbc.Driver");
			
			// Aufbau der DB-Verbindung
			Verbindung = DriverManager.getConnection(db, user, pw); // hier in () User, pw hinzunehmen, wenn Passwortgesch�tzt
			
			//Statement anlegen
			Abfrage = Verbindung.createStatement();
			
			//Abfrage ausf�hren
			ResultSet rs = Abfrage.executeQuery(SQLString);
			
			//Datens�tze holen
			while (rs.next()) {
				double schnitt = rs.getDouble(1); // Index 1, weil 1 ID ist und das erste Merkmal im Select Nachname ist
				System.out.printf("%.2f\n", schnitt);
			}
			
			//Verbindung trennen
			Verbindung.close();
			
		} catch (Exception Fehler) {
			System.out.println("Fehler im Programm: ");
			System.out.println(Fehler.toString());
			System.exit(0);
			
		}

	}

