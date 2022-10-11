package dao;

import java.io.IOException;
import java.sql.SQLException;

//import org.postgresql.util.LruCache.CreateAction;

public class Main_act {

	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
		// on commence par convertir le fichier plat a la bdd ource :
		String db_name_source ="mybdd_ource";
		String user ="postgres";
		String mdp="admin";
		String db_name_target ="db_inter";
		String bdd_dw="datawarehouse";
		PopulateFromCSV p1= new PopulateFromCSV();
		p1.create_db(db_name_source, user, mdp);
		p1.populateTable("C:\\Users\\Dell\\eclipse-workspace\\Data_Warehouse_Projet\\data\\account.csv","account","account_id int PRIMARY KEY,district_id int,frequency varchar,date date",db_name_source,user, mdp);
		p1.populateTable("C:\\Users\\Dell\\eclipse-workspace\\Data_Warehouse_Projet\\data\\card.csv","card","card_id int PRIMARY KEY, disp_id int, type varchar, issued date",db_name_source, user, mdp);
		p1.populateTable("C:\\Users\\Dell\\eclipse-workspace\\Data_Warehouse_Projet\\data\\loan.csv","loan","loan_id int PRIMARY KEY, account_id int, date date, amount int, duration int, payments decimal, status varchar",db_name_source, user, mdp);
		p1.populateTable("C:\\Users\\Dell\\eclipse-workspace\\Data_Warehouse_Projet\\data\\disp.csv","disp","disp_id int PRIMARY KEY, client_id int, account_id int, type varchar",db_name_source, user, mdp);
		p1.populateTable("C:\\Users\\Dell\\eclipse-workspace\\Data_Warehouse_Projet\\data\\client.csv","client","client_id int PRIMARY KEY, gender varchar, birth_date date, district_id int",db_name_source, user, mdp);
		p1.populateTable("C:\\Users\\Dell\\eclipse-workspace\\Data_Warehouse_Projet\\data\\order.csv","_order","order_id int PRIMARY KEY,account_id int,bank_to varchar, account_to int, amount decimal , k_symbol varchar",db_name_source, user,mdp);
		p1.populateTable("C:\\Users\\Dell\\eclipse-workspace\\Data_Warehouse_Projet\\data\\district.csv","district","district_id int PRIMARY KEY, A2 varchar, A3 varchar, A4 int, A5 int , A6 int, A7 int, A8 int, A9 int, A10 decimal ,A11 int ,A12 decimal ,A13 decimal , A14 int, A15 int, A16 int",db_name_source, user, mdp);
		p1.populateTable("C:\\Users\\Dell\\eclipse-workspace\\Data_Warehouse_Projet\\data\\trans.csv","transaction","trans_id int PRIMARY KEY, account_id int, date date, type varchar ,operation varchar, amount int ,balance int, k_symbol varchar, bank varchar, account int ",db_name_source, user, mdp);
			
		System.out.println("Database created and filled");
		
		//Extraction :
		Extraction e= new Extraction();
		e.extract(db_name_target, user, mdp,db_name_source);
		System.out.println("Extraction done");
		
		//tranformation :
		Transformation t= new Transformation();
		t.tranform(db_name_target, user, mdp);
		System.out.println("Tranformation done");
	
		//load data 
		// on creer au debut la bdd de dataareoue :
		Creatdw c=new Creatdw();
		c.CreatData(bdd_dw, user, mdp);
		System.out.println("Data warehouse created");
		Load d= new Load();
		d.loaddata(db_name_target, bdd_dw, user, mdp);
		
	}

}
