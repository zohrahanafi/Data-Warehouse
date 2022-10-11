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
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Color;
import java.awt.Desktop;


public class DesktopApp extends JFrame {

	private JPanel contentPane;
	private JTextField user_textfield;
	private JTextField password_textfield;
	
	
	
	public DesktopApp() {
		setTitle("Data-warehouse creation");
		
	    this.setLocation(400, 250);
        initComponents();
    }

	
	 @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	    private void initComponents() {
		setTitle("Authentification");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 300, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Database user  : ");
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsername.setBounds(50, 90, 200, 25);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Database password :");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPassword.setBounds(50, 150, 200, 25);
		contentPane.add(lblPassword);
		
		user_textfield = new JTextField();
		user_textfield.setForeground(Color.BLACK);
		user_textfield.setFont(new Font("Tahoma", Font.BOLD, 15));
		user_textfield.setBounds(230, 90, 120, 25);
		contentPane.add(user_textfield);
		user_textfield.setColumns(10);
		
		password_textfield = new JTextField();
		password_textfield.setFont(new Font("Tahoma", Font.BOLD, 15));
		password_textfield.setForeground(Color.BLACK);
		password_textfield.setBounds(230, 150, 120, 25);
		contentPane.add(password_textfield);
		password_textfield.setColumns(10);
		
		JButton submit_btn = new JButton("Submit");
		submit_btn.setBackground(Color.LIGHT_GRAY);
		submit_btn.setFont(new Font("Tahoma", Font.BOLD, 15));
		submit_btn.setForeground(Color.BLACK);
		submit_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = user_textfield.getText();
				String password = password_textfield.getText();
			
				try 
				{
					Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", username,
							password);
					java.sql.Statement statement = connection.createStatement();	
					
					App_body app = new App_body();
					app.setVisible(true);	
					dispose();
					
					
				}
				catch (Exception exception) {
					showErrorMessage();
					
                }
				
			}
		});
		submit_btn.setBounds(142, 207, 97, 25);
		contentPane.add(submit_btn);
	}
	 
	 public void showErrorMessage()
	 {
		 JOptionPane.showMessageDialog(this, "User or password incorrect");
		 DesktopApp d = new DesktopApp();
		 dispose();
		 d.setVisible(true);
	 }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DesktopApp frame = new DesktopApp();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
