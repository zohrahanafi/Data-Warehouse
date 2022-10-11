package dao;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Display_dw extends JFrame {

	private JPanel contentPane;
	private JTable client_table;
	private JTextField user_textField;
	private JTextField password_textField;
	private JTextField dw_textfield;
	private JTable date_table;
	private JScrollPane scrollPane_1;
	private JTable fact_table;
	private JScrollPane scrollPane_2;
	private JTable region_table;
	private JScrollPane scrollPane_3;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JButton btnBack;

	public Display_dw() {
		setTitle("Data-warehouse creation");
		 
	    this.setLocation(400, 250);
        initComponents();
    }
	 @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	    private void initComponents() {
		
		setTitle("Display data");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 150, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 81, 750, 125);
		contentPane.add(scrollPane);
		
		client_table = new JTable();
		scrollPane.setViewportView(client_table);
		
		user_textField = new JTextField();
		user_textField.setBounds(80, 15, 100, 25);
		contentPane.add(user_textField);
		user_textField.setColumns(10);
		
		password_textField = new JTextField();
		password_textField.setBounds(300, 15, 100, 25);
		contentPane.add(password_textField);
		password_textField.setColumns(10);
		
		dw_textfield = new JTextField();
		dw_textfield.setBounds(650, 15, 100, 25);
		contentPane.add(dw_textfield);
		dw_textfield.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("User :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(15, 15, 100, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(220, 15, 100, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("data warehouse name :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(480, 15, 200, 25);
		contentPane.add(lblNewLabel_2);
		
		JButton display_btn = new JButton("display");
		display_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		display_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = user_textField.getText();
				String password = password_textField.getText();
				String data = dw_textfield.getText();
				try {
					Connection con = connexion_db(data, user, password);
					String client_query = "select * from clientdim";
					PreparedStatement client_pst = con.prepareStatement(client_query);
					ResultSet client_rs = client_pst.executeQuery();
					client_table.setModel(DbUtils.resultSetToTableModel(client_rs));
					
					String date_query = "select * from datedim";
					PreparedStatement date_pst = con.prepareStatement(date_query);
					ResultSet date_rs = date_pst.executeQuery();
					date_table.setModel(DbUtils.resultSetToTableModel(date_rs));
					
					String fact_query = "select * from presfact";
					PreparedStatement fact_pst = con.prepareStatement(fact_query);
					ResultSet fact_rs = fact_pst.executeQuery();
					fact_table.setModel(DbUtils.resultSetToTableModel(fact_rs));
					
					String region_query = "select * from regiondim";
					PreparedStatement region_pst = con.prepareStatement(region_query);
					ResultSet region_rs = region_pst.executeQuery();
					region_table.setModel(DbUtils.resultSetToTableModel(region_rs));
					
					
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		display_btn.setBounds(372, 715, 97, 25);
		contentPane.add(display_btn);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(15, 243, 750, 125);
		contentPane.add(scrollPane_1);
		
		date_table = new JTable();
		scrollPane_1.setViewportView(date_table);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(15, 409, 750, 125);
		contentPane.add(scrollPane_2);
		
		fact_table = new JTable();
		scrollPane_2.setViewportView(fact_table);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(15, 566, 750, 125);
		contentPane.add(scrollPane_3);
		
		region_table = new JTable();
		scrollPane_3.setViewportView(region_table);
		
		lblNewLabel_3 = new JLabel("region dimension table");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(300, 537, 200, 16);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Fact table");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_4.setBounds(330, 380, 100, 16);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Client dimension table");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setBounds(300, 55, 200, 16);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Date dimension table");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_6.setBounds(300, 214, 200, 16);
		contentPane.add(lblNewLabel_6);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App_body a = new App_body();
				a.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.setBounds(0, 728, 97, 25);
		contentPane.add(btnBack);
	}
	
		static Connection connexion_db( String db_target,String user,String mdp) throws SQLException
		{
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+db_target, user,mdp);
			return con;
		}
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display_dw frame = new Display_dw();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
