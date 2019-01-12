package Notenverwaltung;

import java.sql.*;

public class Noten_Verbesserung {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Fehler abfangen
		try {
			
			//Datenstruktur f�r die Verbindung mit der Datenbank
			Connection Verbindung;
			
			//Datenstruktur f�r Aufnahme der SQL-Anweisung
			Statement Update;
			
			//Variable f�r die Datenquelle
			String db = "jdbc:mysql://localhost/mydb";
			
			//Username und Passwort
			String user = "root";
			String pw = "ch2007CH1";
			
			//SQL-Anweisung
			String updateString = "UPDATE Studierender_Klausur " +
			"SET Note = Note + 0.2  " + 
			"WHERE idKlausur = 2; ";
			
			//Treiber laden
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			
			//Aufbau der Datenbankverbindung
			Verbindung = DriverManager.getConnection(db, user, pw);
			
			//Statement anlegen
			Update = Verbindung.createStatement();
			
			//Update ausf�hren
			int rueck = Update.executeUpdate(updateString);
			System.out.println("Datens�tze, die von �nderungen betroffen waren:" + rueck);

			
			//Verbindung von der Datenbank trennen
			Verbindung.close();
			
		} catch (Exception Fehler) {
			System.out.println("Fehler im Programm: ");
			System.out.println(Fehler.toString());
			System.exit(0);
		}

	}


}
