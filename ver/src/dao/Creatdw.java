package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
// cette class creer un data warehouse
//ou on a commence par creer la bdd et la connecter au serveur
//ensuite, on a passe a la creation de chaque table
public class Creatdw {
	public Statement CreatData(String bdd_name, String user,String mdp) throws SQLException { 
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", user, mdp);
			Statement statement = connection.createStatement();
			statement.executeUpdate("CREATE DATABASE " +bdd_name +";");
			System.out.println("Connected to database");
			connection.close();
			
			 connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_name,  user, mdp);
					
			 statement = connection.createStatement();
			//statement.executeUpdate("CREATE TABLE IF NOT EXISTS  CardDim (card_id int not null PRIMARY KEY, disp_id int, type varchar, issued date);");
	        statement.executeUpdate("CREATE TABLE IF NOT EXISTS  ClientDim (client_id int not null PRIMARY KEY, gender varchar, birth_date date, district_id int);");
	        statement.executeUpdate("CREATE TABLE IF NOT EXISTS  RegionDim ( district_id int not null PRIMARY KEY, name varchar, region varchar );");
	        statement.executeUpdate("CREATE TABLE IF NOT EXISTS  DateDim ( date_id int PRIMARY KEY, date date, day int, month int, year int );");
	        statement.executeUpdate("CREATE TABLE IF NOT EXISTS  PresFact " + 
	        			"( date_id int not null references DateDim(date_id) ," + 
	        			" client_id int not null references ClientDim(client_id) ," + 
	        			" district_id int not null references RegionDim(district_id) ," + 
	        			" etat_paid int," + 
	        			" reste float , " + 
	        			" payed float ," + 
	        			" paid_month  int," + 
	        			" primary  key (date_id,client_id,district_id)" + 
	        				");");
	        							
	        System.out.println("Table Created");
	        
			return statement;
		}
	

	}


