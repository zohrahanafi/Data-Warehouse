package dao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CreateDatabase extends JFrame {

	private JPanel contentPane;
	private JTextField username_textfield;
	private JTextField password_textfield;
	private JTextField client_textfield;
	String client_path ="";
	String card_path ="";
	String transaction_path ="";
	String loan_path ="";
	String order_path ="";
	String district_path ="";
	String disp_path ="";
	String account_path ="";
	String file_name;
	PopulateFromCSV pop = new PopulateFromCSV();
	private JTextField database_textfield;
	private JTextField account_textfield;
	private JTextField card_textfield;
	private JTextField loan_textfield;
	private JTextField trans_textfield;
	private JTextField district_textfield;
	private JTextField order_textfield;
	private JTextField disp_textfield;
	private JButton btnNewButton;
	private JButton back_btn;


	public CreateDatabase() {
		setTitle("Database creation");
		
	    this.setLocation(400, 250);
        initComponents();
    }
	 @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	    private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 200, 822, 495); 
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel username_label = new JLabel("User :");
		username_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		username_label.setBounds(15, 15, 80, 25);
		contentPane.add(username_label);
		
		username_textfield = new JTextField();
		username_textfield.setBounds(110, 15, 120, 25);
		contentPane.add(username_textfield);
		username_textfield.setColumns(10);
		
		JLabel password_label = new JLabel("password :");
		password_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		password_label.setBounds(300, 15, 80, 25);
		contentPane.add(password_label);
		
		password_textfield = new JTextField();
		password_textfield.setBounds(395, 15, 120, 25);
		contentPane.add(password_textfield);
		password_textfield.setColumns(10);
		
		JLabel database_label = new JLabel("databas name :");
		database_label.setFont(new Font("Tahoma", Font.BOLD, 13));
		database_label.setBounds(550, 15, 110, 25);
		contentPane.add(database_label);
		
		client_textfield = new JTextField();
		client_textfield.setBounds(15, 110, 400, 25);
		contentPane.add(client_textfield);
		client_textfield.setColumns(10);
		
		
		
		JButton browse_client = new JButton("Browse client");
		browse_client.setFont(new Font("Tahoma", Font.BOLD, 13));
		browse_client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btn_browseClient(e);
			}
		});
		browse_client.setBounds(600, 110, 140, 25);
		contentPane.add(browse_client);
		
		
		database_textfield = new JTextField();
		database_textfield.setBounds(660, 15, 120, 25);
		contentPane.add(database_textfield);
		database_textfield.setColumns(10);
		
		JButton browe_account = new JButton("Browse account");
		browe_account.setFont(new Font("Tahoma", Font.BOLD, 13));
		browe_account.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_browseAccount(e);
			}
		});
		browe_account.setBounds(600, 70, 140, 25);
		contentPane.add(browe_account);
		
		account_textfield = new JTextField();
		account_textfield.setBounds(15, 70, 400, 25);
		contentPane.add(account_textfield);
		account_textfield.setColumns(10);
		
		card_textfield = new JTextField();
		card_textfield.setBounds(15, 150, 400, 25);
		contentPane.add(card_textfield);
		card_textfield.setColumns(10);
		
		loan_textfield = new JTextField();
		loan_textfield.setBounds(15, 190, 400, 25);
		contentPane.add(loan_textfield);
		loan_textfield.setColumns(10);
		
		trans_textfield = new JTextField();
		trans_textfield.setBounds(15, 230, 400, 25);
		contentPane.add(trans_textfield);
		trans_textfield.setColumns(10);
		
		district_textfield = new JTextField();
		district_textfield.setBounds(15, 270, 400, 25);
		contentPane.add(district_textfield);
		district_textfield.setColumns(10);
		
		order_textfield = new JTextField();
		order_textfield.setBounds(15, 310, 400, 25);
		contentPane.add(order_textfield);
		order_textfield.setColumns(10);
		
		disp_textfield = new JTextField();
		disp_textfield.setBounds(15, 350, 400, 25);
		contentPane.add(disp_textfield);
		disp_textfield.setColumns(10);
		
		JButton browse_card = new JButton("Browse card");
		browse_card.setFont(new Font("Tahoma", Font.BOLD, 13));
		browse_card.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_browseCard(e);
			}
		});
		browse_card.setBounds(600, 150, 140, 25);
		contentPane.add(browse_card);
		
		JButton browse_loan = new JButton("Browse loan");
		browse_loan.setFont(new Font("Tahoma", Font.BOLD, 13));
		browse_loan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_browseLoan(e);
			}
		});
		browse_loan.setBounds(600, 190, 140, 25);
		contentPane.add(browse_loan);
		
		JButton browse_trans = new JButton("Browse trans");
		browse_trans.setFont(new Font("Tahoma", Font.BOLD, 13));
		browse_trans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_browseTrans(e);
			}
		});
		browse_trans.setBounds(600, 230, 140, 25);
		contentPane.add(browse_trans);
		
		JButton browse_district = new JButton("Browse district");
		browse_district.setFont(new Font("Tahoma", Font.BOLD, 13));
		browse_district.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_browseDistrict(e);
			}
		});
		browse_district.setBounds(600, 270, 140, 25);
		contentPane.add(browse_district);
		
		JButton browse_order = new JButton("Browse order");
		browse_order.setFont(new Font("Tahoma", Font.BOLD, 13));
		browse_order.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_browseOrder(e);
			}
		});
		browse_order.setBounds(600, 310, 140, 25);
		contentPane.add(browse_order);
		
		JButton browse_disp = new JButton("Browse disp");
		browse_disp.setFont(new Font("Tahoma", Font.BOLD, 13));
		browse_disp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn_browseDisp(e);
			}
		});
		browse_disp.setBounds(600, 350, 140, 25);
		contentPane.add(browse_disp);
		
		btnNewButton = new JButton("Create");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createDB(e);
					ShowMessage();
				} catch (SQLException e1) {
					showErrorMessage();
				} 
			}
		});
		btnNewButton.setBounds(350, 400, 140, 25);
		contentPane.add(btnNewButton);
		
		back_btn = new JButton("Back");
		back_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App_body a = new App_body();
				a.setVisible(true);
				dispose();
			}
		});
		back_btn.setBounds(-2, 423, 97, 25);
		contentPane.add(back_btn);
		
	}

	 private void btn_browseClient(java.awt.event.ActionEvent evt) {
		 JFileChooser fc = new JFileChooser();
		 fc.showOpenDialog(this);
		 try
		 {
			 	File f = fc.getSelectedFile();
	            client_path = f.getAbsolutePath();
	            client_textfield.setText(client_path);
		 }catch(Exception e)
		 {
			 
		 }
	 }
	 
	 private void btn_browseAccount(java.awt.event.ActionEvent evt) {
		 JFileChooser fc = new JFileChooser();
		 fc.showOpenDialog(this);
		 try
		 {
			 	File f = fc.getSelectedFile();
	            account_path = f.getAbsolutePath();
	            account_textfield.setText(account_path);
		 }catch(Exception e)
		 {
			 
		 }
	 }
	 
	 private void btn_browseCard(java.awt.event.ActionEvent evt) {
		 JFileChooser fc = new JFileChooser();
		 fc.showOpenDialog(this);
		 try
		 {
			 	File f = fc.getSelectedFile();
	            card_path = f.getAbsolutePath();
	            card_textfield.setText(card_path);
		 }catch(Exception e)
		 {
			 
		 }
	 }
	 
	 private void btn_browseTrans(java.awt.event.ActionEvent evt) {
		 JFileChooser fc = new JFileChooser();
		 fc.showOpenDialog(this);
		 try
		 {
			 	File f = fc.getSelectedFile();
	            transaction_path = f.getAbsolutePath();
	            trans_textfield.setText(transaction_path);
		 }catch(Exception e)
		 {
			 
		 }
	 }
	 
	 private void btn_browseOrder(java.awt.event.ActionEvent evt) {
		 JFileChooser fc = new JFileChooser();
		 fc.showOpenDialog(this);
		 try
		 {
			 	File f = fc.getSelectedFile();
	            order_path = f.getAbsolutePath();
	            order_textfield.setText(order_path);
		 }catch(Exception e)
		 {
			 
		 }
	 }
	 
	 private void btn_browseDistrict(java.awt.event.ActionEvent evt) {
		 JFileChooser fc = new JFileChooser();
		 fc.showOpenDialog(this);
		 try
		 {
			 	File f = fc.getSelectedFile();
	            district_path = f.getAbsolutePath();
	            district_textfield.setText(district_path);
		 }catch(Exception e)
		 {
			 
		 }
	 }
	 
	 private void btn_browseDisp(java.awt.event.ActionEvent evt) {
		 JFileChooser fc = new JFileChooser();
		 fc.showOpenDialog(this);
		 try
		 {
			 	File f = fc.getSelectedFile();
	            disp_path = f.getAbsolutePath();
	            disp_textfield.setText(disp_path);
		 }catch(Exception e)
		 {
			 
		 }
	 }
	 private void btn_browseLoan(java.awt.event.ActionEvent evt) {
		 JFileChooser fc = new JFileChooser();
		 fc.showOpenDialog(this);
		 try
		 {
			 	File f = fc.getSelectedFile();
	            loan_path = f.getAbsolutePath();
	            loan_textfield.setText(loan_path);
		 }catch(Exception e)
		 {
			 
		 }
	 }
	 private void client_btn(java.awt.event.ActionEvent evt) throws SQLException
	 {
		 String db_name = database_textfield.getText();
		 String username = username_textfield.getText();
		 String password = password_textfield.getText();
	//	 pop.populateTable(path,"account","account_id int PRIMARY KEY,district_id int,frequency varchar,date date",db_name,username, password);
		 
	 }
	 
	 public void createDB(java.awt.event.ActionEvent evt) throws SQLException
	 {
		 String db_name_source = database_textfield.getText();
		 String user = username_textfield.getText();
		 String mdp = password_textfield.getText();
		 try {
		 pop.create_db(db_name_source, user, mdp);
		 	pop.populateTable(account_path,"account","account_id int PRIMARY KEY,district_id int,frequency varchar,date date",db_name_source,user, mdp);
		 	pop.populateTable(card_path,"card","card_id int PRIMARY KEY, disp_id int, type varchar, issued date",db_name_source, user, mdp);
			pop.populateTable(loan_path,"loan","loan_id int PRIMARY KEY, account_id int, date date, amount int, duration int, payments decimal, status varchar",db_name_source, user, mdp);
			pop.populateTable(disp_path,"disp","disp_id int PRIMARY KEY, client_id int, account_id int, type varchar",db_name_source, user, mdp);
			pop.populateTable(client_path,"client","client_id int PRIMARY KEY, gender varchar, birth_date date, district_id int",db_name_source, user, mdp);
			pop.populateTable(district_path,"district","district_id int PRIMARY KEY, A2 varchar, A3 varchar, A4 int, A5 int , A6 int, A7 int, A8 int, A9 int, A10 decimal ,A11 int ,A12 decimal ,A13 decimal , A14 int, A15 int, A16 int",db_name_source, user, mdp);
		    pop.populateTable(transaction_path,"transaction","trans_id int PRIMARY KEY, account_id int, date date, type varchar ,operation varchar, amount int ,balance int, k_symbol varchar, bank varchar, account int ",db_name_source, user, mdp);
		    
		 }
		 catch (Exception e) {
			 e.printStackTrace();
		}

	 
}
	 public void showErrorMessage()
	 {
		 JOptionPane.showMessageDialog(this, "Error");
		 CreateDatabase d = new CreateDatabase();
		 dispose();
		 d.setVisible(true);
	 }
	 public void ShowMessage()
	 {
		 JOptionPane.showMessageDialog(this, "Database created");
	 }
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateDatabase frame = new CreateDatabase();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
