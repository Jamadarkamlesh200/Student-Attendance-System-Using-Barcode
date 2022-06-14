package SAMDESK;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField unametxt;
	private JPasswordField passtxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setUndecorated(true);
		setResizable(false);
		
		//setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Ahinsa\\graphics\\picon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 171);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblEnterUsername = new JLabel("Enter Username");
		lblEnterUsername.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEnterUsername.setForeground(new Color(0, 0, 128));
		lblEnterUsername.setBounds(10, 20, 126, 23);
		panel.add(lblEnterUsername);
		
		unametxt = new JTextField();
		unametxt.setBounds(116, 13, 193, 39);
		panel.add(unametxt);
		unametxt.setColumns(10);
		
		JLabel lblEnterPassword = new JLabel("Enter Password");
		lblEnterPassword.setForeground(new Color(0, 0, 128));
		lblEnterPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblEnterPassword.setBounds(10, 64, 102, 23);
		panel.add(lblEnterPassword);
		
		passtxt = new JPasswordField();
		passtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
                {
                 passcheck();  
                }	
				
			}
		});
		passtxt.setBounds(116, 57, 193, 39);
		panel.add(passtxt);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			passcheck();	
			}
		});
		btnLogin.setBorder(new LineBorder(new Color(153, 51, 51), 1, true));
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnLogin.setBackground(new Color(255, 255, 0));
		btnLogin.setForeground(Color.BLUE);
		btnLogin.setBounds(119, 121, 78, 29);
		panel.add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
System.exit(0);
			}
		});
		btnExit.setForeground(Color.BLUE);
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnExit.setBorder(new LineBorder(new Color(153, 51, 51), 1, true));
		btnExit.setBackground(new Color(255, 255, 0));
		btnExit.setBounds(231, 121, 78, 29);
		panel.add(btnExit);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
	public void passcheck()
	{
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   String DB_URL = "jdbc:mysql://localhost:3306/ams?useTimezone=true&serverTimezone=UTC";
       String uname = unametxt.getText().toString();
       char[] input = passtxt.getPassword();
       String upass = new String(input);
       String username="Admin";
       String userpass="Admin";
       Boolean pmatch = false;
			   //  Database credentials
       String USER="root";
	   String PASS="";
			    Connection conn = null;
			   Statement stmt = null;
			   try{
			      //STEP 2: Register JDBC driver
			      Class.forName("com.mysql.cj.jdbc.Driver");

			      //STEP 3: Open a connection
			      System.out.println("Connecting to database...");
			      conn = DriverManager.getConnection(DB_URL,USER,PASS);

			      //STEP 4: Execute a query
			      System.out.println("Creating statement...");
			      stmt = conn.createStatement();
			      String sql;
			      sql = "SELECT * from users WHERE USERNAME='"+uname+"'";
  
			      ResultSet rs = stmt.executeQuery(sql);

			      //STEP 5: Extract data from result set
			      while(rs.next()){
			         //Retrieve by column name
			      username = rs.getString("USERNAME");
			      userpass = rs.getString("PASSWORD");
			      if (  username.equals(uname)  && userpass.equals(upass) )
			      {
			    	    flashwin fw = new flashwin();
					 	fw.setVisible(true);
			            hidemyself();
			     				      		        
			       }else
			       {
			    	   JOptionPane.showMessageDialog(null, "Check Password......");
			    	 
			       }			      
			     		      
			      //STEP 6: Clean-up environment
			     
			      }
			      rs.close();
			      stmt.close();
			      conn.close();
			      
			      } catch(SQLException se) {
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception ex){ 
			      //Handle errors for Class.forName
			      ex.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }// nothing we can do
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try			      
			
	}
	public  void hidemyself()
	{
		this.setVisible(false);
	}
}
