package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class Transformation {
	// <!----------------------------Transform Data -------------------------->
	
		//fonction pour extract data from Date:
		//ou on commence par creer la table date, ensuite faire l'extraction (formatage) a jour,mois et annee 
		//apres, on insere dans la bdd cible
		// ensuite, on supprime les dates dubliques
	public void ExtractDataDate(String bdd_name, String user,String mdp) throws SQLException  {
	//ArrayList<DateDim> dt= new ArrayList<DateDim>();
	Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_name, user, mdp);
	Statement statement = connection.createStatement();
	//creation de la table date :
	statement.executeUpdate("CREATE TABLE IF NOT EXISTS  Date ( date_id SERIAL PRIMARY KEY, date date, day int, month int, year int );");
	ResultSet result = statement.executeQuery(" WITH ItemDate as (select date as d from loan) select d, extract (day from d) as jour_d,extract (month from d) as month_d,extract (year from d) as year_d from ItemDate ;");
	String query ="insert into Date ( date_id , date , day , month , year  )"
								+ " values (DEFAULT, ?, ?, ?, ?)";
					
	while (result.next()) {
			PreparedStatement preparedStmt = connection.prepareStatement(query);

			preparedStmt.setDate(1,result.getDate("d"));
			preparedStmt.setInt (2, result.getInt("jour_d"));
			preparedStmt.setInt(3, result.getInt("month_d"));
			preparedStmt.setInt(4, result.getInt("year_d"));

			preparedStmt.execute();
						}
	result.close();
		//la requete suivante permet de supprimer les dates dubliques 		
	PreparedStatement preparedStmt = connection.prepareStatement("DELETE FROM date \r\n" + 
							"  WHERE date IN (SELECT date \r\n" + 
							"  FROM (SELECT row_number() OVER (PARTITION BY date), date \r\n" +
							"  FROM date) x \r\n" +
							"  WHERE x.row_number > 1);");
					
	preparedStmt.executeUpdate();
	connection.close();
	}
	
	
	// la table transaction_load_table pour facilite la recherche de attribut et extraire jute le champ qu'on a beoin 
	public void transaction_load_table(String bdd_name, String user,String mdp) throws SQLException {
	Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_name, user,mdp);;
	Statement statement = connection.createStatement();
	//creation de la table date :
	statement.executeUpdate("CREATE TABLE IF NOT EXISTS  Tra_load ( id_tra_load SERIAL PRIMARY KEY, client_id int, paid_month int, duration int,payed float, reste float, amount float,gender varchar,date date,etat_paid int, id_district int);");
	ResultSet result = statement.executeQuery("SELECT c.client_id, count (k_symbol) as paid_month , l.duration, (count (k_symbol)*l.payments)as payed,l.amount-(count (k_symbol)*l.payments)as reste,l.amount,c.gender,l.date,\r\n" + 
							"(CASE WHEN count (k_symbol)=l.duration  THEN 1\r\n" + 
							"            ELSE 0\r\n" + 
							"       END\r\n" + 
							" ) as etat,c.district_id\r\n" + 
							" \r\n" + 
							"from transaction t, loan l,disp di, client c\r\n" + 
							"where l.account_id = t.account_id and t.k_symbol ='UVER' and l.account_id=di.account_id and c.client_id= di.client_id and di.type='OWNER'\r\n" + 
							"						\r\n" + 
							"group by c.client_id, l.duration,l.payments,l.amount,c.gender,l.date,c.district_id\r\n" + 
							"order by l.date");
					
	String query ="insert into Tra_load (id_tra_load,client_id,paid_month,duration,payed,reste,amount,gender,date,etat_paid,id_district)"
								+ " values (DEFAULT, ?, ?, ?, ?,?,?,?,?,?,?)";
						 
	while (result.next()) {
		PreparedStatement preparedStmt = connection.prepareStatement(query);

			preparedStmt.setInt(1,result.getInt(1));
			preparedStmt.setInt (2, result.getInt(2));
			preparedStmt.setInt(3, result.getInt(3));
			preparedStmt.setFloat(4, result.getFloat(4));
			preparedStmt.setFloat(5,result.getFloat(5));
			preparedStmt.setFloat(6,result.getFloat(6));
			preparedStmt.setString(7, result.getString(7));
			preparedStmt.setDate(8, result.getDate(8));
			preparedStmt.setInt(9, result.getInt(9));
			preparedStmt.setInt(10, result.getInt(10));

					      
			preparedStmt.execute();
					}
	connection.close();
				}
				
	void tranform (String db_name,String user,String mdp) throws SQLException, IOException
 { 		ExtractDataDate(db_name,user,mdp);
 		transaction_load_table(db_name,user,mdp);
				 }
}
