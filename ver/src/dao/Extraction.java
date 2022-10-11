package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
// cette class fait l'extraction de nos donnee apres l'avoir copier a une autre bdd
// au debut, on a extrait les donnee en ve basant sur la class 'PopulateFromCV' 
//apre apres l'avoir creer on a copier la bdd dans une autre bdd 
//apres on supprimer les champs inutile dans la bdd 
public class Extraction {

 void extraction_columns(String db_name,String user,String mdp,String bd_source) throws SQLException, IOException
 {
	 
	 CopyDB tab = new CopyDB();
	 
	 CopyDB.allTables(db_name,user,mdp,bd_source);
	String modifier_account = "ALTER TABLE account DROP COLUMN frequency";
	String modifier_tans = "ALTER TABLE transaction DROP COLUMN operation, DROP COLUMN bank,"
			+ "DROP COLUMN account";
	String modifier_loan = "ALTER TABLE loan DROP COLUMN status";
	String modifier_district = "ALTER TABLE district DROP COLUMN A4 ,"
			+ "DROP COLUMN A5 , DROP COLUMN A6 , DROP COLUMN A7 , DROP COLUMN A8 ,"
			+ "DROP COLUMN A9 , DROP COLUMN A10 , DROP COLUMN A11 , DROP COLUMN A12 ,"
			+ "DROP COLUMN A13 , DROP COLUMN A14 , DROP COLUMN A15 , DROP COLUMN A16";

	
	
	
	Connection connection = tab.connexion_db(db_name,user,mdp);
	Statement statement1= connection.createStatement();
	Statement statement2= connection.createStatement();
	Statement statement3= connection.createStatement();
	Statement statement4= connection.createStatement();
	statement1.executeUpdate(modifier_account);
	statement2.executeUpdate(modifier_tans);
	statement3.executeUpdate(modifier_loan);
	statement4.executeUpdate(modifier_district);
	
	 
 }

 	void extract (String db_name,String user,String mdp,String db_source) throws SQLException, IOException 

 {
 		extraction_columns(db_name,user,mdp,db_source);
 }
}
