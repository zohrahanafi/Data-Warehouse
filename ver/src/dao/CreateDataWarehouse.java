package dao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class CreateDataWarehouse extends JFrame {

	private JPanel contentPane;
	private JTextField db_name;
	private JTextField dw_name;
	private JTextField username_textfield;
	private JTextField password_jtextfield;
	Creatdw c=new Creatdw();
	private JTextField target_textfield;
	
	public CreateDataWarehouse() {
		setTitle("Data-warehouse creation");
		
	    this.setLocation(400, 250);
        initComponents();
    }

	 @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	    private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 300, 646, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Database name : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(50, 170, 240, 25);
		contentPane.add(lblNewLabel);
		
		db_name = new JTextField();
		db_name.setBounds(450, 170, 120, 25);
		contentPane.add(db_name);
		db_name.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Data warehouse name : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(50, 120, 240, 25);
		contentPane.add(lblNewLabel_1);
		
		dw_name = new JTextField();
		dw_name.setBounds(450, 120, 120, 25);
		contentPane.add(dw_name);
		dw_name.setColumns(10);
		
		JLabel lblNomDutilisateur = new JLabel("Username :");
		lblNomDutilisateur.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomDutilisateur.setBounds(50, 20, 200, 25);
		contentPane.add(lblNomDutilisateur);
		
		username_textfield = new JTextField();
		username_textfield.setBounds(450, 20, 120, 25);
		contentPane.add(username_textfield);
		username_textfield.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(50, 70, 200, 25);
		contentPane.add(lblNewLabel_2);
		
		JButton submit_btn = new JButton("Create");
		submit_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					createDW(e);
					ShowMessage();
				} catch (SQLException | IOException e1) {
					showErrorMessage();
					//e1.printStackTrace();
				}
			}
		});
		submit_btn.setBounds(230, 270, 130, 25);
		contentPane.add(submit_btn);
		
		password_jtextfield = new JTextField();
		password_jtextfield.setBounds(450, 70, 120, 25);
		contentPane.add(password_jtextfield);
		password_jtextfield.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Database extract name :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(50, 220, 240, 16);
		contentPane.add(lblNewLabel_3);
		
		target_textfield = new JTextField();
		target_textfield.setBounds(450, 220, 120, 25);
		contentPane.add(target_textfield);
		target_textfield.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				App_body a = new App_body();
				a.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.setBounds(0, 300, 100, 25);
		contentPane.add(btnBack);
	}
	 
	 public void createDW(java.awt.event.ActionEvent evt) throws SQLException, IOException
	 {
		 String source = db_name.getText();
		 String dw = dw_name.getText();
		 String user = username_textfield.getText();
		 String mdp = password_jtextfield.getText();
		 String target = target_textfield.getText();
		 
		 	Extraction e= new Extraction();
			e.extract(target, user, mdp,source);
			System.out.println("Extraction done");
			
			//tranformation :
			Transformation t= new Transformation();
			t.tranform(target, user, mdp);
			System.out.println("Tranformation done");
		 
			c.CreatData(dw, user, mdp);
			System.out.println("Data warehouse created");
			
			Load d= new Load();
			d.loaddata(target, dw, user, mdp);
	 }
	
	 public void showErrorMessage()
	 {
		 JOptionPane.showMessageDialog(this, "Error");
		 CreateDataWarehouse d = new CreateDataWarehouse();
		 dispose();
		 d.setVisible(true);
	 }
	 public void ShowMessage()
	 {
		 JOptionPane.showMessageDialog(this, "Data warehouse created");
	 }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					CreateDataWarehouse frame = new CreateDataWarehouse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
