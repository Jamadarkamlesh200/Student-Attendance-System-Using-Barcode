package SAMDESK;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTable;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;

public class Attendance extends JInternalFrame {
	private JTextField idtxt;
	private JTextField nametxt;
	private JTextField batchtxt;
	private JTextField enrtxt;
	private JTextField intimetxt;
	private JTextField outtimetxt;
	private JTextField clocktxt;
	 private javax.swing.Timer t;
	 private JTextField statustxt;
	 private JTable atab;
	 private JDateChooser cdate;
	 private JButton button;
	 private JLabel att_photo;
	 String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	   String DB_URL = "jdbc:mysql://localhost:3306/ams?useTimezone=true&serverTimezone=UTC";
	   String USER="root";
	   String PASS="";
	   private JTextField ridtxt;
	   public Image tempfig = null;
	   private JComboBox deptbox;
	   private JComboBox ayearbox;
	   private JLabel enrlabel;
		public DefaultTableModel atabmodel = new DefaultTableModel();
		private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Attendance frame = new Attendance();
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
	public Attendance() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 1085, 570);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBounds(10, 11, 1049, 183);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("ID");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label.setBounds(10, 19, 46, 14);
		panel.add(label);
		
		idtxt = new JTextField();
		idtxt.setColumns(10);
		idtxt.setBounds(115, 11, 138, 35);
		panel.add(idtxt);
		
		JLabel label_1 = new JLabel("RID");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_1.setBounds(336, 111, 26, 14);
		panel.add(label_1);
		
		ridtxt = new JTextField();
		ridtxt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		ridtxt.setEnabled(false);
		ridtxt.setColumns(10);
		ridtxt.setBounds(385, 103, 159, 35);
		panel.add(ridtxt);
		
		nametxt = new JTextField();
		nametxt.setColumns(10);
		nametxt.setBounds(115, 57, 268, 35);
		panel.add(nametxt);
		
		JLabel label_2 = new JLabel("Name");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_2.setBounds(10, 66, 46, 14);
		panel.add(label_2);
		
		JLabel lblBatch = new JLabel("Batch");
		lblBatch.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblBatch.setBounds(10, 111, 101, 15);
		panel.add(lblBatch);
		
		batchtxt = new JTextField();
		batchtxt.setColumns(10);
		batchtxt.setBounds(115, 99, 199, 35);
		panel.add(batchtxt);
		
		JLabel label_4 = new JLabel("Department");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_4.setBounds(556, 65, 71, 15);
		panel.add(label_4);
		
		deptbox = new JComboBox();
		deptbox.setModel(new DefaultComboBoxModel(new String[] {"CE", "CM", "ME", "ET", "EE"}));
		deptbox.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		deptbox.setBounds(637, 54, 71, 35);
		panel.add(deptbox);
		
		JLabel label_5 = new JLabel("AC-YEAR");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_5.setBounds(718, 65, 71, 15);
		panel.add(label_5);
		
		ayearbox = new JComboBox();
		ayearbox.setModel(new DefaultComboBoxModel(new String[] {"FY", "SY", "TY"}));
		ayearbox.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		ayearbox.setBounds(799, 54, 71, 35);
		panel.add(ayearbox);
		
		att_photo = new JLabel("");
		att_photo.setIcon(new ImageIcon(Attendance.class.getResource("/rec/photo not.jpg")));
		att_photo.setForeground(Color.RED);
		att_photo.setBorder(new LineBorder(new Color(0, 0, 255)));
		att_photo.setBounds(894, 11, 145, 164);
		panel.add(att_photo);
		
		JLabel lblEnrollmentNo = new JLabel("Enrollment No");
		lblEnrollmentNo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEnrollmentNo.setBounds(263, 19, 107, 14);
		panel.add(lblEnrollmentNo);
		
		enrtxt = new JTextField();
		enrtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				searstudbyenr();
			}
		});
		enrtxt.setForeground(new Color(30, 144, 255));
		enrtxt.setFont(new Font("Times New Roman", Font.BOLD, 20));
		enrtxt.setColumns(10);
		enrtxt.setBounds(362, 11, 199, 35);
		panel.add(enrtxt);
		
		JLabel lblInTime = new JLabel("In Time");
		lblInTime.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblInTime.setBounds(554, 111, 51, 27);
		panel.add(lblInTime);
		
		intimetxt = new JTextField();
		intimetxt.setColumns(10);
		intimetxt.setBounds(637, 103, 233, 35);
		panel.add(intimetxt);
		
		JLabel lblOutTime = new JLabel("Out Time");
		lblOutTime.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblOutTime.setBounds(548, 152, 58, 23);
		panel.add(lblOutTime);
		
		outtimetxt = new JTextField();
		outtimetxt.setColumns(10);
		outtimetxt.setBounds(637, 145, 233, 35);
		panel.add(outtimetxt);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDate.setBounds(10, 145, 46, 30);
		panel.add(lblDate);
		
		cdate = new JDateChooser();
		cdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cdate.setDateFormatString("yyyy-MM-dd");
		cdate.setBounds(115, 140, 199, 35);
		panel.add(cdate);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTime.setBounds(324, 151, 58, 24);
		panel.add(lblTime);
		
		clocktxt = new JTextField();
		clocktxt.setForeground(new Color(0, 0, 128));
		clocktxt.setFont(new Font("Times New Roman", Font.BOLD, 24));
		clocktxt.setEnabled(false);
		clocktxt.setColumns(10);
		clocktxt.setBounds(385, 144, 159, 35);
		panel.add(clocktxt);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblStatus.setBounds(395, 67, 46, 25);
		panel.add(lblStatus);
		
		
		statustxt = new JTextField();
		statustxt.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		statustxt.setEnabled(false);
		statustxt.setColumns(10);
		statustxt.setBounds(436, 57, 108, 35);
		panel.add(statustxt);
		
		button = new JButton("");
		button.setIcon(new ImageIcon(Attendance.class.getResource("/rec/Stop.png")));
		button.setBackground(new Color(255, 255, 255));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			Desktop.att_form=0;
		Attendance.this.setVisible(false);
			
				
			}
		});
		button.setBounds(799, 11, 71, 35);
		panel.add(button);
		
		enrlabel = new JLabel("");
		enrlabel.setBorder(new LineBorder(new Color(0, 0, 255)));
		enrlabel.setBounds(590, 11, 199, 35);
		panel.add(enrlabel);
		atabmodel.addColumn("RID");
		atabmodel.addColumn("STUDENT_NAME");
		atabmodel.addColumn("STUDENT_ID");
		atabmodel.addColumn("ENROLLMENT_NO");
		atabmodel.addColumn("DEPARTMENT");
		atabmodel.addColumn("BATCH");
		atabmodel.addColumn("AYEAR");
		atabmodel.addColumn("INTIME");
		atabmodel.addColumn("OUTTIME");
		atabmodel.addColumn("STATUS");
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setBounds(10, 205, 1049, 324);
		getContentPane().add(scrollPane);
		atab = new JTable();
		atab.getTableHeader().setBackground(Color.ORANGE);
		atab.setRowHeight(30);
		atab.setModel(atabmodel);
		scrollPane.setViewportView(atab);
		
		t = new javax.swing.Timer(100, new ActionListener() {
		    @Override
			public void actionPerformed(ActionEvent e) {
		   
		    	DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
		        Date date = new Date();
		        String formattedDateTime = dateFormat.format(date);
		         clocktxt.setText(formattedDateTime);
		        
		        
		    }
		 });	
		starter();
	}
	public void starter()
	{
		t.start();
		set_cdate();
	}
	public void set_cdate()
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			String output = df.format(c.getTime());
			((JTextField)cdate.getDateEditor().getUiComponent()).setText(output);
			
	}
	 public void searstudbyrid()
	  {
		
			Connection conn = null;
			  Statement stmt = null;
			  String strs1=null;
	    	 	String strs2="-";
	    	 	String stitle=null;
	    	 Long ct=(long) 0;
			  try
			  {
			     Class.forName("com.mysql.cj.jdbc.Driver");
			     conn=DriverManager.getConnection(DB_URL,USER,PASS);
			     stmt = conn.createStatement();
			      String query = "select * from STUDENT_INFORMATION where RID="+ ridtxt.getText();
			     ResultSet rs = stmt.executeQuery(query);
			     while(rs.next()){
			    	                			     	
			  	nametxt.setText(rs.getString("STUDENT_NAME"));						    	
			  	idtxt.setText(rs.getString("STUDENT_ID"));
				enrtxt.setText(rs.getString("ENROLLMENT_NO"));
		        batchtxt.setText(rs.getString("BATCH"));
			    deptbox.setSelectedItem(rs.getString("DEPARTMENT"));
			    ayearbox.setSelectedItem(rs.getString("AYEAR"));
			 
			    	 		}    	
				      rs.close();
			  
			     }
			    catch(Exception ec)
			    {     
			    	System.out.println(ec.toString());
			    
			    	
			    }	
			  try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			  load_photo();
	  }
	 public void searstudbyenr()
	  {
		
			Connection conn = null;
			  Statement stmt = null;
			  String strs1=null;
	    	 	String strs2="-";
	    	 	String stitle=null;
	    	 Long ct=(long) 0;
			  try
			  {
			     Class.forName("com.mysql.cj.jdbc.Driver");
			     conn=DriverManager.getConnection(DB_URL,USER,PASS);
			     stmt = conn.createStatement();
			      String query = "select * from STUDENT_INFORMATION where ENROLLMENT_NO='"+ enrtxt.getText()+"'";
			     ResultSet rs = stmt.executeQuery(query);
			  
			     while(rs.next()){			                   			     	
			  	nametxt.setText(rs.getString("STUDENT_NAME"));						    	
			  	idtxt.setText(rs.getString("STUDENT_ID"));
				enrtxt.setText(rs.getString("ENROLLMENT_NO"));
				enrlabel.setText(rs.getString("ENROLLMENT_NO"));
		        batchtxt.setText(rs.getString("BATCH"));
			    deptbox.setSelectedItem(rs.getString("DEPARTMENT"));
			    ayearbox.setSelectedItem(rs.getString("AYEAR"));
			    ridtxt.setText(rs.getString("RID"));
			    enrtxt.setText("");
			    load_photo();
			    if(data_counter()==0)
				{
					
					save_data();	
				}
				else
				{
					update_data();	
				}
			    	 		}    	
				      rs.close();
			  
			     }
			    catch(Exception ec)
			    {     
			    	System.out.println(ec.toString());
			    
			    	
			    }	
			  try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			 
	  }
	 public void searstudios()
	  {
		   String sedate = ((JTextField)cdate.getDateEditor().getUiComponent()).getText();
			Connection conn = null;
			  Statement stmt = null;
			  String strs1=null;
	    	 	String strs2="-";
	    	 	String stitle=null;
	    	 Long ct=(long) 0;
			  try
			  {
			     Class.forName("com.mysql.cj.jdbc.Driver");
			     conn=DriverManager.getConnection(DB_URL,USER,PASS);
			     stmt = conn.createStatement();
			     String query = "select * from STUDENT_DAILY where RID="+ridtxt.getText()+" and EDATE='"+sedate+"'";
			     ResultSet rs = stmt.executeQuery(query);
			  
			     while(rs.next()){	
			    	 
			    	 intimetxt.setText(rs.getString("INTIME"));
			    	 outtimetxt.setText(rs.getString("OUTTIME"));
			    	 statustxt.setText(rs.getString("STATUS"));
			  	
			     }
				      rs.close();
			  
			     }
			    catch(Exception ec)
			    {     
			    	System.out.println(ec.toString());
			    
			    	
			    }	
			  try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
				searstudbyedate();	 
	  }
	 public void load_photo()
		{
			
			  InputStream in = null;
			  Connection conn = null;
			  Statement stmt = null;
			  try
			  {
			     Class.forName("com.mysql.cj.jdbc.Driver");
			     conn=DriverManager.getConnection(DB_URL,USER,PASS);
			     stmt = conn.createStatement();
			     String query = "select * from STUDENT_INFORMATION where RID="+ridtxt.getText();
			     ResultSet rs = stmt.executeQuery(query);
			     while(rs.next()){
			    	 in = rs.getBinaryStream("PHOTO");
			   	  tempfig = ImageIO.read(in);
			   	  tempfig = tempfig.getScaledInstance(att_photo.getWidth(),att_photo.getHeight(), Image.SCALE_FAST);
			   	  ImageIcon icon = new ImageIcon(tempfig);
			   	   att_photo.setIcon(icon); 
			      }
			      rs.close();
			  
			     }
			    catch(Exception e)
			    {     
			    	System.out.println(e.toString());
			    
			    	
			    }	 
		}
	 public int data_counter()
	 {
		 String sedate = ((JTextField)cdate.getDateEditor().getUiComponent()).getText();
		 int ctr=0;
		 InputStream in = null;
		  Connection conn = null;
		  Statement stmt = null;
		  try
		  {
		     Class.forName("com.mysql.cj.jdbc.Driver");
		     conn=DriverManager.getConnection(DB_URL,USER,PASS);
		     stmt = conn.createStatement();
		     String query = "select * from STUDENT_DAILY where RID="+ridtxt.getText()+" and EDATE='"+sedate+"'";
		     ResultSet rs = stmt.executeQuery(query);
		     while(rs.next()){
		    	ctr = ctr +1;
		      }
		      rs.close();
		  
		     }
		    catch(Exception e)
		    {     
		    	System.out.println(e.toString());
		    
		    	
		    }	 
		  return ctr;
	 }
	 public void save_data()
	 {
		 //RID,STUDENT_NAME,STUDENT_ID,ENROLLMENT_NO,DEPARTMENT,BATCH,AYEAR,INTIME,OUTTIME,EDATE,STATUS,LID
		 String sname = nametxt.getText().toString();
			String sid = idtxt.getText().toString();
			String srid= ridtxt.getText().toString();
			String senr = enrlabel.getText().toString();
            intimetxt.setText(clocktxt.getText());
			String sintime =intimetxt.getText().toString();
			String souttime = outtimetxt.getText().toString();
			String sedate = ((JTextField)cdate.getDateEditor().getUiComponent()).getText();
			String sdept = deptbox.getItemAt(deptbox.getSelectedIndex()).toString();
			String sbatch = batchtxt.getText().toString();
			 statustxt.setText("U");
			String status = statustxt.getText().toString();
			String sayear = ayearbox.getItemAt(ayearbox.getSelectedIndex()).toString();		
			Connection connection = null;				
			PreparedStatement psmnt = null;	
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				connection = DriverManager.getConnection(DB_URL, USER,PASS);
				String query="INSERT INTO STUDENT_DAILY(RID,STUDENT_NAME,STUDENT_ID,ENROLLMENT_NO,DEPARTMENT,BATCH,AYEAR,INTIME,OUTTIME,EDATE,STATUS) " + "values(?,?,?,?,?,?,?,?,?,?,?)";
				System.out.println(query); 
				psmnt =connection.prepareStatement(query);
				System.out.println(query); 
			     psmnt.setString(1,srid);
				 psmnt.setString(2,sname);
				 psmnt.setString(3,sid );
				 psmnt.setString(4,senr);
				 psmnt.setString(5,sdept);
				 psmnt.setString(6,sbatch);
				 psmnt.setString(7,sayear);
				 psmnt.setString(8,sintime);
				 psmnt.setString(9,souttime );
				 psmnt.setString(10,sedate);
				 psmnt.setString(11,status);
							int s = psmnt.executeUpdate();
				System.out.println("seted");
				if(s>0) {
				//JOptionPane.showMessageDialog(null,"Record submitted ");
				searstudbyedate();
				}
				else {
					JOptionPane.showMessageDialog(null,"Record not Submitted ");
				}
				}
				
				catch (Exception ex) {
				System.out.println("Found some error : "+ex);
				JOptionPane.showMessageDialog(null,"Record not Submitted ");
				}
				finally {
				            try {
				                        connection.close();
				            } catch (SQLException ex) {
				               
				            }
				            try {
				                psmnt.close();
				            } catch (SQLException ex) {
				               
				            }
				}
	 }
	 public void searstudbyedate()
	  {
		 /* 
		atabmodel.addColumn("RID");
		atabmodel.addColumn("STUDENT_NAME");
		atabmodel.addColumn("STUDENT_ID");
		atabmodel.addColumn("ENROLLMENT_NO");
		atabmodel.addColumn("DEPARTMENT");
		atabmodel.addColumn("BATCH");
		atabmodel.addColumn("AYEAR");
		atabmodel.addColumn("INTIME");
		atabmodel.addColumn("OUTTIME");
		atabmodel.addColumn("STATUS");
		*/
		    atabmodel.setRowCount(0);
		    
		    String sedate = ((JTextField)cdate.getDateEditor().getUiComponent()).getText();
			Connection conn = null;
			 Statement stmt = null;
			 String strs1=null;
	    	 String strs2="-";
	    	 String stitle=null;
	    	 Long ct=(long) 0;
			  try
			  {
			     Class.forName("com.mysql.cj.jdbc.Driver");
			     conn=DriverManager.getConnection(DB_URL,USER,PASS);
			     stmt = conn.createStatement();
			      String query = "select * from STUDENT_DAILY where EDATE='"+sedate+"'";
			     System.out.print(query);
			     ResultSet rs = stmt.executeQuery(query);
			     while(rs.next()){
			    	             Vector row= new Vector();		   			     	
			  	 				 row.add(rs.getString("RID"));
						    	 row.add(rs.getString("STUDENT_NAME"));
						    	 row.add(rs.getString("ENROLLMENT_NO"));
						    	 row.add(rs.getString("DEPARTMENT"));
						    	 row.add(rs.getString("BATCH"));
						    	 row.add(rs.getString("EDATE"));
						    	 row.add(rs.getString("AYEAR"));
						    	 row.add(rs.getString("INTIME"));
						    	 row.add(rs.getString("OUTTIME"));
						    	 row.add(rs.getString("STATUS"));
						        atabmodel.addRow(row);	
			    	 		}    	
				      rs.close();
			  
			     }
			    catch(Exception ec)
			    {     
			    	System.out.println(ec.toString());
			    
			    	
			    }	
			  try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			  atab.setModel(atabmodel);
	  }
	 public void update_data()
	 {
		 //RID,STUDENT_NAME,STUDENT_ID,ENROLLMENT_NO,DEPARTMENT,BATCH,AYEAR,INTIME,OUTTIME,EDATE,STATUS,LID
		
		 String sname = nametxt.getText().toString();
			String sid = idtxt.getText().toString();
			String srid= ridtxt.getText().toString();
			String senr = enrlabel.getText().toString();
             outtimetxt.setText(clocktxt.getText());
			String sintime =intimetxt.getText().toString();
			String souttime = outtimetxt.getText().toString();
			String sedate = ((JTextField)cdate.getDateEditor().getUiComponent()).getText();
			String sdept = deptbox.getItemAt(deptbox.getSelectedIndex()).toString();
			String sbatch = batchtxt.getText().toString();
			String status = "P";
			String sayear = ayearbox.getItemAt(ayearbox.getSelectedIndex()).toString();		
			Connection connection = null;				
			PreparedStatement psmnt = null;	
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				connection = DriverManager.getConnection(DB_URL, USER,PASS);
				String query="UPDATE STUDENT_DAILY SET OUTTIME=?,STATUS=? WHERE RID="+srid+" and EDATE='"+sedate+"'";
				System.out.println(query); 
				psmnt =connection.prepareStatement(query);
				System.out.println(query); 
			     psmnt.setString(1,souttime);
				 psmnt.setString(2,status);
				
							int s = psmnt.executeUpdate();
				System.out.println("seted");
				if(s>0) {
				//JOptionPane.showMessageDialog(null,"Record Updated ");
				searstudios();
						
				}
				else {
					JOptionPane.showMessageDialog(null,"Record not Submitted ");
				}
				}
				
				catch (Exception ex) {
				System.out.println("Found some error : "+ex);
				JOptionPane.showMessageDialog(null,"Record not Submitted ");
				}
				finally {
				            try {
				                        connection.close();
				            } catch (SQLException ex) {
				               
				            }
				            try {
				                psmnt.close();
				            } catch (SQLException ex) {
				               
				            }
				}
	 }
}
