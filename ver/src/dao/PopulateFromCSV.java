package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PopulateFromCSV {
	
	public void create_db(String bdd_name, String user,String mdp) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", user, mdp);
		Statement statement = connection.createStatement();
		statement.executeUpdate("CREATE DATABASE " +bdd_name +";");
		System.out.println("Connected to database");

	}
	void populateTable(String csvFile, String tableName, String tableSchema,String bdd_name, String user,String mdp) throws SQLException {

		// La creation de la bdd et la connection 
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_name, user, mdp);
		Statement statement = connection.createStatement();
		System.out.println("Connected to database");

		// Configuration du fichier CSV
		String line = "";
		String cvsSplitBy = ",";

		// Récupération des types des attributs
		
		String[] att = tableSchema.split(",");
		
		List<String> attributesTypes = new ArrayList<String>();
		List<String> attributesName = new ArrayList<String>();
		for (int i = 0; i < att.length; i++) {
			String[] tmp = att[i].trim().split("\\s+");
			
			if (tmp.length > 1) {
				attributesTypes.add(tmp[1]);
				attributesName.add(tmp[0]);
			}
			else
				System.out.println("Attribute syntax error: " + tmp[1]);
		}
		
		System.out.println(attributesTypes);
		System.out.println(attributesName);
		
		

		// Création de la table dans la BD
		System.out.println("CREATE TABLE IF NOT EXISTS  " + tableName + " (" + tableSchema + ");");
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS  " + tableName + " (" + tableSchema + ");");
		System.out.println("Table " + tableName + " created");

		// Ouverture du fichier CSV
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			// Sauter l'en-tête
			br.readLine();
			
			// Lecture du fichier CSV ligne par ligne
			while ((line = br.readLine()) != null) {
				String query;
				
				// TODO
				// Récupérer les valeurs des attributs présentes 
				// dans "line" et construire une requête "INSERT" 
				// pour les insérer dans la table.
				
				// on commence par replacer quote par 
				line= line.replaceAll("\"", "");
				String value[] = line.split(",",-1);
				List<String> ay_value = new ArrayList<String>();
				ay_value = Arrays.asList(value);
				
				System.out.println(line);
				String my_String;
				 for (int i=0; i<attributesTypes.size(); i++) {
					 my_String=ay_value.get(i);
					
					 if (attributesTypes.get(i).equals("varchar") || attributesTypes.get(i).equals("date")) {
						 
						// System.out.println(my_String);
						 if (attributesTypes.get(i).equals("date")) { 
								//on converte la date en ql format :
							    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
						        java.util.Date date = sdf1.parse(my_String);
						        java.sql.Date date_ql = new java.sql.Date(date.getTime()); 
						        
						        my_String= date_ql.toString();
								my_String="'"+ my_String + "'";
							}
						 else {
							 my_String="'"+ ay_value.get(i) + "'";}
						 
						ay_value.set(i, my_String);
						}
					 else {
						 if ( my_String.length()==0) { 
							 ay_value.set(i, null);
						 }
					}
					 
					
					 
					 
				 }
				
				 //to convert arrayLit to String
				String colomn = String.join(",", attributesName);	
				String values = String.join(",", ay_value); 
				//System.out.println(query);
				 
				
				
				
				query="INSERT INTO "+ tableName +"("+ colomn +")" + 
						"VALUES ("+ values +")";
				query=query.replace(",)", ", )");
				
				System.out.println(query);
				statement.executeUpdate(query);
			}

			System.out.println("Insertion completed");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws SQLException, Exception {

		PopulateFromCSV p1 = new PopulateFromCSV();
	 // p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\account.csv","account","account_id int PRIMARY KEY,district_id int,frequency varchar,date date");
	//	p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\card.csv","card","card_id int PRIMARY KEY, disp_id int, type varchar, issued date");
	//	p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\loan.csv","loan","loan_id int PRIMARY KEY, account_id int, date date, amount int, duration int, payments decimal, status varchar");
	//	p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\disp.csv","disp","disp_id int PRIMARY KEY, client_id int, account_id int, type varchar");
	//	p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\client.csv","client","client_id int PRIMARY KEY, gender varchar, birth_date date, district_id int");
	//	p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\order.csv","_order","order_id int PRIMARY KEY,account_id int,bank_to varchar, account_to int, amount decimal , k_symbol varchar");
	//	p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\district.csv","district","district_id int PRIMARY KEY, A2 varchar, A3 varchar, A4 int, A5 int , A6 int, A7 int, A8 int, A9 int, A10 decimal ,A11 int ,A12 decimal ,A13 decimal , A14 int, A15 int, A16 int");
	//	p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\trans.csv","transaction","trans_id int PRIMARY KEY, account_id int, date date, type varchar ,operation varchar, amount int ,balance int, k_symbol varchar, bank varchar, account int ");
		

    
        
	}

}




/*
 * p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\account.csv","account","account_id int PRIMARY KEY,district_id int,frequency varchar,date date");
	//	p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\card.csv","card","card_id int PRIMARY KEY, disp_id int, type varchar, issued date");
	//	p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\trans.csv","transaction","trans_id int PRIMARY KEY, account_id int, date date, type varchar ,operation varchar, amount int ,balance int, k_symbol varchar, bank varchar, account int ");
	//	p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\loan.csv","loan","loan_id int PRIMARY KEY, account_id int, date date, amount int, duration int, payments decimal, status varchar");
	//	p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\disp.csv","disp","disp_id int PRIMARY KEY, client_id int, account_id int, type varchar");
	//	p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\client.csv","client","client_id int PRIMARY KEY, gender varchar, birth_date date, district_id int");
	//	p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\order.csv","order","order_id int PRIMARY KEY, account_id int, bank_to varchar, account_to int, amount int , k_symbol varchar");
	//	p1.populateTable("C:\\Users\\manel\\OneDrive\\M1\\ua1\\S2\\entrepo de donnees\\Financial\\district.csv","district","district_id int PRIMARY KEY, A2 varchar, A3 varchar, A4 int, A5 int , A6 int, A7 int, A8 int, A9 int, A10 decimal ,A11 int ,A12 decimal ,A13 decimal , A14, A15, A16");
	
 * */
