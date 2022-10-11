package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Load {
	// on commence par la table DateDim :
	//on fait une connection a 2 bdd notre bdd source et le dw
	//on selectionne d'une bdd a l'autre on se basant de la bdd creer dans dans la partie transformation
	//on fait la meme chose pour toute nos tables
	public void LoadDateDim(String bdd_name_ource,String bdd_name_target, String user,String mdp) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_name_ource, user, mdp);
		Connection connection2 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_name_target, user, mdp);
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select date_id, date, day, month, year from date");
					
		String query ="insert into datedim (date_id, date, day, month, year) VALUES (?, ?, ?, ?, ?)";
					 
		while (result.next()) {
				PreparedStatement preparedStmt = connection2.prepareStatement(query);
						
				preparedStmt.setInt(1, result.getInt("date_id"));
				preparedStmt.setDate(2,result.getDate("date"));
				preparedStmt.setInt (3, result.getInt("day"));
				preparedStmt.setInt(4, result.getInt("month"));
				preparedStmt.setInt(5, result.getInt("year"));

				preparedStmt.execute();
					
				}
		result.close();
}
				
				// la table client :
	public void LoadClientDim(String bdd_name_ource,String bdd_name_target, String user,String mdp) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_name_ource, user, mdp);
		Connection connection2 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_name_target, user, mdp);
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select client_id, gender, birth_date, district_id from client");
					
		String query ="insert into clientdim(client_id, gender, birth_date, district_id)VALUES ( ?, ?, ?, ?)";
					 
		while (result.next()) {
			PreparedStatement preparedStmt = connection2.prepareStatement(query);
						
			preparedStmt.setInt(1, result.getInt("client_id"));
			preparedStmt.setString(2,result.getString("gender"));
			preparedStmt.setDate (3, result.getDate("birth_date"));
			preparedStmt.setInt(4, result.getInt("district_id"));

			preparedStmt.execute();
					
			}
	result.close();
		}
				
				// la table region :
	public void LoadRegionDim(String bdd_name_ource,String bdd_name_target, String user,String mdp) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_name_ource, user, mdp);
		Connection connection2 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_name_target, user, mdp);
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select district_id, a2, a3 from district");
		String query ="insert into regiondim( district_id, name, region)VALUES ( ?, ?, ?);";
								 
		while (result.next()) {
				PreparedStatement preparedStmt = connection2.prepareStatement(query);
				preparedStmt.setInt(1, result.getInt("district_id"));
				preparedStmt.setString(2,result.getString("a2"));
				preparedStmt.setString (3, result.getString("a3"));
				preparedStmt.execute();
					}
		result.close();
		}
				// la table region :
	public void LoadCardDim(String bdd_name_ource,String bdd_name_target, String user,String mdp) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_name_ource, user, mdp);
		Connection connection2 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_name_target, user, mdp);	Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select card_id, disp_id, type, issued from card");
		String query ="insert into carddim(card_id, disp_id, type, issued)VALUES ( ?, ?, ?, ?);";
											 
		while (result.next()) {
							PreparedStatement preparedStmt = connection2.prepareStatement(query);
							preparedStmt.setInt(1, result.getInt("card_id"));
							preparedStmt.setInt(2,result.getInt("disp_id"));
							preparedStmt.setString(3,result.getString("type"));
							preparedStmt.setDate (4, result.getDate("issued"));
							// execute the preparedstatement
							preparedStmt.execute();
							}
							result.close();
											
							}		
				// la table FreFact :
	public void LoadFactTable(String bdd_name_ource,String bdd_name_target, String user,String mdp) throws SQLException {
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_name_ource, user, mdp);
		Connection connection2 = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+bdd_name_target, user, mdp);
		Statement statement = connection.createStatement();
		ResultSet result = statement.executeQuery("select d.date_id, tl.client_id, tl.id_district, tl.etat_paid, tl.reste,tl.payed, tl.paid_month " + 
							"from date d, tra_load tl " + 
							" where d.date= tl.date ");
		String query ="insert into presfact(date_id, client_id, district_id, etat_paid, reste, payed, paid_month)VALUES (?, ?, ?, ?, ?, ?, ?);";
														 
		while (result.next()) {
			PreparedStatement preparedStmt = connection2.prepareStatement(query);
			preparedStmt.setInt(1, result.getInt(1));
			preparedStmt.setInt(2,result.getInt(2));
			preparedStmt.setInt(3,result.getInt(3));
			preparedStmt.setInt (4, result.getInt(4));
			preparedStmt.setFloat (5, result.getFloat(5));
			preparedStmt.setFloat (6, result.getFloat(6));
			preparedStmt.setInt (7, result.getInt(7));
			preparedStmt.execute();
				}
		result.close();
	}	
	public void loaddata(String bdd_name_ource,String bdd_name_target, String user,String mdp) throws SQLException {
		LoadDateDim(bdd_name_ource,bdd_name_target,user,mdp);
		LoadClientDim(bdd_name_ource, bdd_name_target, user, mdp);
		LoadRegionDim(bdd_name_ource, bdd_name_target, user, mdp);
		LoadFactTable(bdd_name_ource, bdd_name_target, user, mdp);
	}

}
