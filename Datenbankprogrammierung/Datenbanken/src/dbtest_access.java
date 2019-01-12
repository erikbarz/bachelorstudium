import java.sql.*;

public class dbtest_access {

	public static void main(String[] args){
		try{
			Connection Verbindung;
			Statement Abfrage;
			String db="jdbc:odbc:Datenbank1";
			String user="";
			String pw="";
			String SQLString="select nachname from student";
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Verbindung=DriverManager.getConnection(db, user, pw);
			Abfrage=Verbindung.createStatement();
			ResultSet rs=Abfrage.executeQuery(SQLString);
			while (rs.next()){
				String nachname = rs.getString(1);
				System.out.println(nachname);
			}
			Verbindung.close();
		}
		catch(Exception fehler){
			System.out.println("Fehler im Programm:");
			System.out.println(fehler.toString());
			System.exit(0);
		}
	}
}
