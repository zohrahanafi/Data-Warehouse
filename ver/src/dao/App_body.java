package dao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class App_body extends JFrame {

	private JPanel contentPane;
	private JDialog dialogue;
	private String data;

	public App_body() {
		setTitle("Data-warehouse creation");
		 
	    this.setLocation(400, 250);
        initComponents();
    }
	 @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	    private void initComponents() {
		setTitle("Creation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(750, 300, 606, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel createdb_tbn = new JLabel("Import data : ");
		createdb_tbn.setFont(new Font("Tahoma", Font.BOLD, 14));
		createdb_tbn.setBounds(80, 70, 190, 35);
		contentPane.add(createdb_tbn);
		
		JLabel createdw_btn = new JLabel("Create a data warehouse : ");
		createdw_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		createdw_btn.setBounds(80, 150, 190, 35);
	
		contentPane.add(createdw_btn);
		
		
		JButton btnDatabase = new JButton("Import");
		btnDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDatabase cd = new CreateDatabase();
				cd.setVisible(true);
				dispose();
			}
		});
		btnDatabase.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDatabase.setBounds(335, 70, 200, 35);
		contentPane.add(btnDatabase);
		
		JButton btnDataWarehouse = new JButton("Create");
		btnDataWarehouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateDataWarehouse dw = new CreateDataWarehouse();
				dw.setVisible(true);
				System.out.println(data);
				dispose();
			}
		});
		btnDataWarehouse.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDataWarehouse.setBounds(335, 150, 200, 35);
		contentPane.add(btnDataWarehouse);
		
		JButton back_btn = new JButton("Back");
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DesktopApp a = new DesktopApp();
				a.setVisible(true);
				dispose();
			}
		});
		back_btn.setFont(new Font("Tahoma", Font.BOLD, 14));
		back_btn.setBounds(0, 328, 97, 25);
		contentPane.add(back_btn);
		
		JLabel lblDisplayData = new JLabel("Display data :");
		lblDisplayData.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDisplayData.setBounds(80, 230, 140, 35);
		contentPane.add(lblDisplayData);
		
		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Display_dw d = new Display_dw();
				d.setVisible(true);
				dispose();
			}
		});
		btnDisplay.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDisplay.setBounds(335, 230, 200, 35);
		contentPane.add(btnDisplay);
	}

	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App_body frame = new App_body();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); 
	
	}
}
