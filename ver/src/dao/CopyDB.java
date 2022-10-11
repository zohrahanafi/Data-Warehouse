package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CopyDB {
	// cette classe nous permet de copier une bdd a une autre bdd ou on vas l'utiliser comme un intermediere
	// cette fontion permet de creer une bdd apres avoir faire la cnx a notre serveur 
	Statement createDataBase(String bdd_target,String user,String mdp) throws SQLException
	{
		String db_name;
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", user,
				mdp);
		Statement statement= con.createStatement();
		statement.executeUpdate("CREATE DATABASE "+bdd_target);
		return statement;
	}
	
	Statement createDbLink(String bdd_target,String user,String mdp) throws SQLException
	{
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_target, user,mdp);
		Statement statement= con.createStatement();
		statement.executeUpdate("create extension dblink");
		return statement;
	}
	// cette fontion nous permet de connecter a notre bdd target qui est une bdd intermediere
	static Connection connexion_db( String db_target,String user,String mdp) throws SQLException
	{
		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+db_target, user,mdp);
		return con;
	}
	// la fontion qui copier la bdd account, commencant par faire une connection a la bdd ensuite on execute la requete, on fait le meme traitement pour les tables sauf la table 
	// order et card qu'on n'a pas besoin
	void _account_table(String db_target,String account,String user,String mdp,String dbsource) throws SQLException, IOException {

		Connection connection = connexion_db(db_target,user,mdp);
		Statement statement= connection.createStatement();
		String query = "create table "+account+" as select * from dblink('host=localhost user="+user+" password="+mdp+" dbname="+dbsource+"','select * from account') as linkable(account_id int ,district_id int,frequency varchar,date date)";		
		statement.executeUpdate(query);
		
	}
	
	void _client_table(String db_name,String client,String user,String mdp,String dbsource) throws SQLException, IOException {

		Connection connection = connexion_db(db_name,user,mdp);
		Statement statement= connection.createStatement();
		String query = "create table "+client+" as select * from dblink('host=localhost user=" +user+" password="+mdp+ " dbname="+dbsource+"','select * from client') as linkable(client_id int, gender varchar, birth_date date, district_id int )";		
		statement.executeUpdate(query);

		
		
	}
	
	
	void _disp_table(String db_name,String disp_name,String user,String mdp,String dbsource) throws SQLException, IOException {

		Connection connection = connexion_db(db_name,user,mdp);
		Statement statement= connection.createStatement();
		String query = "create table "+disp_name+" as select * from dblink('host=localhost user="+user+" password="+mdp+" dbname="+dbsource+"','select * from disp') as linkable(disp_id int, client_id int, account_id int, type varchar)";		
		statement.executeUpdate(query);

	}
	
	void _district_table(String db_name,String district_name,String user,String mdp,String dbsource) throws SQLException, IOException {

		Connection connection = connexion_db(db_name,user,mdp);
		Statement statement= connection.createStatement();
		String query = "create table "+district_name+" as select * from dblink('host=localhost user="+user+" password="+mdp+" dbname="+dbsource+"','select * from district') as linkable(district_id int , A2 varchar, A3 varchar, A4 int, A5 int , A6 int, A7 int, A8 int, A9 int, A10 decimal ,A11 int ,A12 decimal ,A13 decimal , A14 int, A15 int, A16 int)";		
		statement.executeUpdate(query);
	}
	
	
	void _loan_table(String db_name,String loan_name,String user,String mdp,String dbsource) throws SQLException, IOException {

		Connection connection = connexion_db(db_name,user,mdp);
		Statement statement= connection.createStatement();
		String query = "create table "+loan_name+" as select * from dblink('host=localhost user="+user+" password="+mdp+" dbname="+dbsource+"','select * from loan') as linkable(loan_id int, account_id int, date date, amount int, duration int, payments decimal, status varchar)";		
		statement.executeUpdate(query);

	}
			
	void _trans_table(String db_name,String trans_name,String user,String mdp,String dbsource) throws SQLException, IOException {

		Connection connection = connexion_db(db_name,user,mdp);
		Statement statement= connection.createStatement();
		String query = "create table "+trans_name+" as select * from dblink('host=localhost user="+user+" password="+mdp+" dbname="+dbsource+"','select * from transaction') as linkable(trans_id int , account_id int, date date, type varchar ,operation varchar, amount int ,balance int, k_symbol varchar, bank varchar, account int )";		
		statement.executeUpdate(query);

	}
	// la fontion add_pk nous permet d'ajouter les cles primaire a nos table
	static void add_pk(String db_name,String user,String mdp) throws SQLException
	{
		
		String key1 = "ALTER TABLE loan ADD PRIMARY KEY (loan_id);";
		String key2 = "ALTER TABLE transaction ADD PRIMARY KEY (trans_id);";
		String key3 = "ALTER TABLE district ADD PRIMARY KEY (district_id);";
		String key4 = "ALTER TABLE account ADD PRIMARY KEY (account_id);";
		String key5 = "ALTER TABLE client ADD PRIMARY KEY (client_id);";
		String key6 = "ALTER TABLE disp ADD PRIMARY KEY (disp_id);";
	
		Connection con = connexion_db(db_name,user,mdp);
		Statement stm1=con.createStatement();
		Statement stm2=con.createStatement();
		Statement stm3=con.createStatement();
		Statement stm4=con.createStatement();
		Statement stm5=con.createStatement();
		Statement stm6=con.createStatement();

		
		stm1.executeUpdate(key1);
		stm2.executeUpdate(key2);
		stm3.executeUpdate(key3);
		stm4.executeUpdate(key4);
		stm5.executeUpdate(key5);
		stm6.executeUpdate(key6);

		
	}	
	// on fait appelle a toute les fontion creer afin de pouvoir creer notre bdd intermediere
	public static void allTables(String db_name,String uer, String mdp,String dbsource) throws SQLException, IOException
	{
		CopyDB e = new CopyDB();
		e.createDataBase(db_name,uer,mdp);
		e.createDbLink(db_name,uer,mdp);
		String account_table ="account"; 
		String client_table = "client";
		String disp_table ="disp";
		String district_table="district";
		String loan_table ="loan";
		String trans_table = "transaction";
		e._account_table(db_name, account_table,uer,mdp,dbsource);
		e._client_table(db_name, client_table,uer,mdp,dbsource);
		e._disp_table(db_name, disp_table,uer,mdp,dbsource);
		e._district_table(db_name, district_table,uer,mdp,dbsource);
		e._loan_table(db_name, loan_table,uer,mdp,dbsource);
		e._trans_table(db_name, trans_table,uer,mdp,dbsource);
		
		add_pk(db_name,uer,mdp);
		
		
}
}
