package SAMDESK;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class Master extends JInternalFrame {
	private JTextField nametxt;
	private JTextField idtxt;
	private JTextField mobtxt;
	private JTextField enrtxt;
	private JTextField altmobtxt;
	private JTextField citytxt;
	private JTextField batchtxt;
	private JTable stab;
	public static Boolean photoflag;
	 String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	   String DB_URL = "jdbc:mysql://localhost:3306/ams?useTimezone=true&serverTimezone=UTC";
	   String USER="root";
	   String PASS="";
	   public byte[] public_photoByte = null;
	public DefaultTableModel stabmodel = new DefaultTableModel();
	private JComboBox ayearbox;
	private JComboBox deptbox;
	private JTextArea addtxt;
	private JLabel photo;
	private JTextField ridtxt;
	  public Image tempfig = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Master frame = new Master();
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
	public Master() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 816, 510);
		getContentPane().setLayout(null);
		
		photo = new JLabel("");
		photo.setIcon(null);
		photo.setForeground(Color.RED);
		photo.setBorder(new LineBorder(new Color(0, 0, 255)));
		photo.setBounds(629, 57, 146, 158);
		getContentPane().add(photo);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblName.setBounds(23, 66, 46, 14);
		getContentPane().add(lblName);
		
		nametxt = new JTextField();
		nametxt.setBounds(128, 57, 292, 35);
		getContentPane().add(nametxt);
		nametxt.setColumns(10);
		
		idtxt = new JTextField();
		idtxt.setColumns(10);
		idtxt.setBounds(128, 11, 85, 35);
		getContentPane().add(idtxt);
		
		mobtxt = new JTextField();
		mobtxt.setColumns(10);
		mobtxt.setBounds(128, 145, 199, 35);
		getContentPane().add(mobtxt);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblId.setBounds(23, 19, 46, 14);
		getContentPane().add(lblId);
		
		JLabel lblCitytown = new JLabel("City/Town");
		lblCitytown.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblCitytown.setBounds(23, 240, 85, 27);
		getContentPane().add(lblCitytown);
		
		JLabel lblEnrollmentNo = new JLabel("Enrollment No");
		lblEnrollmentNo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblEnrollmentNo.setBounds(23, 111, 101, 15);
		getContentPane().add(lblEnrollmentNo);
		
		enrtxt = new JTextField();
		enrtxt.setColumns(10);
		enrtxt.setBounds(128, 99, 199, 35);
		getContentPane().add(enrtxt);
		
		JLabel lblRollNumber = new JLabel("Mobile Number");
		lblRollNumber.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblRollNumber.setBounds(17, 155, 101, 15);
		getContentPane().add(lblRollNumber);
		
		JLabel lblAlternateMobile = new JLabel("Alternate Mobile");
		lblAlternateMobile.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAlternateMobile.setBounds(17, 201, 107, 14);
		getContentPane().add(lblAlternateMobile);
		
		altmobtxt = new JTextField();
		altmobtxt.setColumns(10);
		altmobtxt.setBounds(128, 191, 199, 35);
		getContentPane().add(altmobtxt);
		
		citytxt = new JTextField();
		citytxt.setColumns(10);
		citytxt.setBounds(128, 232, 199, 35);
		getContentPane().add(citytxt);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDepartment.setBounds(430, 31, 71, 15);
		getContentPane().add(lblDepartment);
		
		deptbox = new JComboBox();
		deptbox.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		deptbox.setModel(new DefaultComboBoxModel(new String[] {"CE", "CM", "ME", "ET", "EE"}));
		deptbox.setBounds(430, 55, 71, 35);
		getContentPane().add(deptbox);
		
		JLabel lblBatch = new JLabel("Batch");
		lblBatch.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblBatch.setBounds(349, 168, 71, 29);
		getContentPane().add(lblBatch);
		
		batchtxt = new JTextField();
		batchtxt.setColumns(10);
		batchtxt.setBounds(430, 167, 83, 35);
		getContentPane().add(batchtxt);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAddress.setBounds(349, 129, 71, 15);
		getContentPane().add(lblAddress);
		
		addtxt = new JTextArea();
		addtxt.setBounds(433, 104, 161, 54);
		getContentPane().add(addtxt);
		
		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new 
				 FileNameExtensionFilter("JPEG file", new String[] {"jpg", "jpeg"});
				fc.setFileFilter(filter);
				           
						 BufferedImage bi1;
						  File file = null ;
						  BufferedImage resize;
						  int returnVal = fc.showOpenDialog(null);
						   if (returnVal == JFileChooser.APPROVE_OPTION) {
					            file = fc.getSelectedFile();
					          //######################
					            try {
					                bi1 = ImageIO.read(file);
					                int iw =photo.getWidth();
					                int ih =  photo.getHeight();
					                ImageIcon icon1 = new ImageIcon(bi1);
					                Image scaleImage = icon1.getImage().getScaledInstance(iw,ih, Image.SCALE_DEFAULT);
					                ImageIcon icon2 = new ImageIcon(scaleImage);
					               photo.setIcon(icon2);
					                photoflag=true;
					            } catch (Exception ex) {
					                System.out.println(ex);
					            }
					          //######################
					            
					            
					            
					            
					        } else {
					           
					        }
			}
		});
		btnNewButton.setBounds(616, 232, 101, 35);
		getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 278, 765, 191);
		getContentPane().add(scrollPane);
		
		stab = new JTable();
		stab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		      ridtxt.setText(stab.getValueAt(stab.getSelectedRow(),0).toString());
		      searstudbyrid();	
			}
		});
		scrollPane.setViewportView(stab);
		//STUDENT_NAME,STUDENT_ID,ENROLLMENT_NO,MOBILE,ALTMOB,CITY,DEPARTMENT,BATCH,ADDRESS,AYEAR,PHOTO,RID'
	    stabmodel.addColumn("RID");
		stabmodel.addColumn("STUDENT_NAME");
		stabmodel.addColumn("STUDENT_ID");
		stabmodel.addColumn("ENROLLMENT_NO");
		stabmodel.addColumn("MOBILE");
		stabmodel.addColumn("BATCH");
		
	stab.getTableHeader().setBackground(Color.ORANGE);
	stab.setRowHeight(30);
		stab.setModel(stabmodel);
		
		JButton btnNew = new JButton("NEW");
		btnNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			nametxt.setText("");
			ridtxt.setText("");
			idtxt.setText("");
			enrtxt.setText("");
			 mobtxt.setText("");
			 altmobtxt.setText("");
			citytxt.setText("");				  
		     addtxt.setText("");
		    photo.setIcon(new ImageIcon(Master.class.getResource("/SAMDESK/rec/photo not.jpg")));	
			}
		});
		btnNew.setBounds(511, 232, 71, 35);
		getContentPane().add(btnNew);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(emptycheck()==0)
				{
				/*	String sname = nametxt.getText().toString();
					String sid = idtxt.getText().toString();
					String senr = enrtxt.getText().toString();
					String smob = mobtxt.getText().toString();
					String saltmob = altmobtxt.getText().toString();
					String scity = citytxt.getText().toString();
					String sdept = deptbox.getItemAt(deptbox.getSelectedIndex()).toString();
					String sbatch = batchtxt.getText().toString();
					String sadd = addtxt.getText().toString();
					String sayear = ayearbox.getItemAt(ayearbox.getSelectedIndex()).toString();		*/
					save_data();
					searstud();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "All Fields are maniditory");
				}
			
			/*
			String query = "INSERT INTO STUDENT_INFORMATION (STUDENT_NAME,STUDENT_ID,ENROLLMENT_NO,MOBILE,ALTMOB,CITY,DEPARTMENT,BATCH,ADDRESS,AYEAR) VALUES ('"+sname+"',"+sid+",'"+senr+"','"+smob+"','"+saltmob+"','"+scity+"','"+sdept+"','"+sbatch+"','"+sadd+"','"+sayear+"');";	
			dconnect dc = new dconnect();
			System.out.print(query);
			dc.savedata(query);*/
			
			}
			
		});
		btnSave.setBounds(337, 232, 71, 35);
		getContentPane().add(btnSave);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				update_data();
			}
		});
		btnUpdate.setBounds(418, 232, 83, 35);
		getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(342, 11, 78, 35);
		getContentPane().add(btnDelete);
		
		ayearbox = new JComboBox();
		ayearbox.setModel(new DefaultComboBoxModel(new String[] {"FY", "SY", "TY"}));
		ayearbox.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		ayearbox.setBounds(511, 55, 71, 35);
		getContentPane().add(ayearbox);
		
		JLabel lblAcyear = new JLabel("AC-YEAR");
		lblAcyear.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAcyear.setBounds(511, 31, 71, 15);
		getContentPane().add(lblAcyear);
		
		JButton btnExit = new JButton("");
		btnExit.setIcon(new ImageIcon(Master.class.getResource("/rec/Stop.png")));
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			     Desktop.master_form=0;
				Master.this.setVisible(false);
			}
		});
		btnExit.setBounds(727, 227, 46, 40);
		getContentPane().add(btnExit);
		
		JLabel lblRid = new JLabel("RID");
		lblRid.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblRid.setBounds(223, 19, 26, 14);
		getContentPane().add(lblRid);
		
		ridtxt = new JTextField();
		ridtxt.setEnabled(false);
		ridtxt.setColumns(10);
		ridtxt.setBounds(259, 11, 78, 35);
		getContentPane().add(ridtxt);
		
		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				searstudbybatch();
			}
		});
		btnSearch.setIcon(new ImageIcon(Master.class.getResource("/rec/search small.png")));
		btnSearch.setBounds(523, 167, 55, 35);
		getContentPane().add(btnSearch);
	        stab.getColumnModel().getColumn(0).setPreferredWidth(80);
			stab.getColumnModel().getColumn(1).setPreferredWidth(200);
			stab.getColumnModel().getColumn(2).setPreferredWidth(80);
			//stab.getColumnModel().getColumn(3).setPreferredWidth(scrollPane.getWidth()-300);
			//stab.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(), 20));
				stab.getTableHeader().setForeground(Color.BLUE);
	}
	public void prepare_photo()
	{
		 int iw = photo.getWidth();
         int ih =  photo.getHeight();
         ImageIcon icon = new ImageIcon();
         icon = (ImageIcon) photo.getIcon();
         Image scaleImage = icon.getImage().getScaledInstance(iw,ih, Image.SCALE_DEFAULT);
		 Image scaledImage = scaleImage.getScaledInstance(iw,ih, Image.SCALE_SMOOTH);
       BufferedImage imageBuff = new BufferedImage(iw,ih, BufferedImage.TYPE_INT_RGB);
       Graphics g = imageBuff.createGraphics();
       g.drawImage(scaledImage, 0, 0, new Color(0,0,0), null);
       g.dispose();
       ByteArrayOutputStream baos = new ByteArrayOutputStream();
       try {
			ImageIO.write(imageBuff, "jpg", baos );
			 baos.flush();
		       public_photoByte = baos.toByteArray();
		        baos.close();
		       
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public void save_data()
	{
		prepare_photo();
		String sname = nametxt.getText().toString();
		String sid = idtxt.getText().toString();
		String senr = enrtxt.getText().toString();
		String smob = mobtxt.getText().toString();
		String saltmob = altmobtxt.getText().toString();
		String scity = citytxt.getText().toString();
		String sdept = deptbox.getItemAt(deptbox.getSelectedIndex()).toString();
		String sbatch = batchtxt.getText().toString();
		String sadd = addtxt.getText().toString();
		String sayear = ayearbox.getItemAt(ayearbox.getSelectedIndex()).toString();		
		Connection connection = null;				
		PreparedStatement psmnt = null;	
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(DB_URL, USER,PASS);
			String query="INSERT INTO STUDENT_INFORMATION (STUDENT_NAME,STUDENT_ID,ENROLLMENT_NO,MOBILE,ALTMOB,CITY,DEPARTMENT,BATCH,ADDRESS,AYEAR,PHOTO) " + "values(?,?,?,?,?,?,?,?,?,?,?)";
			System.out.println(query); 
			psmnt =connection.prepareStatement(query);
			System.out.println(query); 
		     psmnt.setString(1,sname);
			 psmnt.setString(2,sid );
			 psmnt.setString(3,senr );
			 psmnt.setString(4,smob);
			 psmnt.setString(5,saltmob);
			 psmnt.setString(6,scity);
			 psmnt.setString(7,sdept);
			 psmnt.setString(8,sbatch);
			 psmnt.setString(9,sadd );
			 psmnt.setString(10,sayear);
			 psmnt.setBytes(11,public_photoByte);
						int s = psmnt.executeUpdate();
			System.out.println("seted");
			if(s>0) {
			JOptionPane.showMessageDialog(null,"Record submitted ");
			photoflag =false;
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
	public void update_data()
	{
		prepare_photo();
		String sname = nametxt.getText().toString();
		String sid = idtxt.getText().toString();
		String senr = enrtxt.getText().toString();
		String smob = mobtxt.getText().toString();
		String saltmob = altmobtxt.getText().toString();
		String scity = citytxt.getText().toString();
		String sdept = deptbox.getItemAt(deptbox.getSelectedIndex()).toString();
		String sbatch = batchtxt.getText().toString();
		String sadd = addtxt.getText().toString();
		String sayear = ayearbox.getItemAt(ayearbox.getSelectedIndex()).toString();		
		Connection connection = null;				
		PreparedStatement psmnt = null;	
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(DB_URL, USER,PASS);
			String query="update STUDENT_INFORMATION  set STUDENT_NAME=?,STUDENT_ID=?,ENROLLMENT_NO=?,MOBILE=?,ALTMOB=?,CITY=?,DEPARTMENT=?,BATCH=?,ADDRESS=?,AYEAR=?,PHOTO=? where RID="+ridtxt.getText();
			System.out.println(query); 
			psmnt =connection.prepareStatement(query);
			System.out.println(query); 
		     psmnt.setString(1,sname);
			 psmnt.setString(2,sid );
			 psmnt.setString(3,senr );
			 psmnt.setString(4,smob);
			 psmnt.setString(5,saltmob);
			 psmnt.setString(6,scity);
			 psmnt.setString(7,sdept);
			 psmnt.setString(8,sbatch);
			 psmnt.setString(9,sadd );
			 psmnt.setString(10,sayear);
			 psmnt.setBytes(11,public_photoByte);
						int s = psmnt.executeUpdate();
			System.out.println("seted");
			if(s>0) {
			JOptionPane.showMessageDialog(null,"Record Updated ");
			photoflag =false;
			}
			else {
				JOptionPane.showMessageDialog(null,"Record not Updated ");
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
	public int emptycheck()
	{
		int ct=0;
		if( nametxt.getText().toString().equals(""))
		{
          nametxt.requestFocus(true);
          ct= ct+1;
          
		}
		if( idtxt.getText().toString().equals(""))
		{
			 idtxt.requestFocus(true);
			  ct= ct+1;
		}
		if( enrtxt.getText().toString().equals(""))
		{
			enrtxt.requestFocus(true);
			  ct= ct+1;
		}
		if( mobtxt.getText().toString().equals(""))
		{
			mobtxt.requestFocus(true);
			  ct= ct+1;
		}
		if( altmobtxt.getText().toString().equals(""))
		{
			altmobtxt.requestFocus(true);
			  ct= ct+1;
		}
		if( citytxt.getText().toString().equals(""))
		{
			citytxt.requestFocus(true);
			  ct= ct+1;
		}
		if( batchtxt.getText().toString().equals(""))
		{
			batchtxt.requestFocus(true);
			  ct= ct+1;
		}
		if( addtxt.getText().toString().equals(""))
		{
			addtxt.requestFocus(true);
			  ct= ct+1;
		}	
	if(ct>0)
	{
		//JOptionPane.showMessageDialog(null, "Record should not be Empty");
		return 1;
	}
	else
	{
		return 0;
	}
}
	 public void searstud()
	  {
		  stabmodel.setRowCount(0);
			String sdept = deptbox.getItemAt(deptbox.getSelectedIndex()).toString();
			String sayear = ayearbox.getItemAt(ayearbox.getSelectedIndex()).toString();	
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
			      String query = "select * from STUDENT_INFORMATION where DEPARTMENT='"+sdept+"' and AYEAR='"+sayear+"'";
			     ResultSet rs = stmt.executeQuery(query);
			     while(rs.next()){
			    	             Vector row= new Vector();		   			     	
			  	 				 row.add(rs.getString("RID"));
						    	 row.add(rs.getString("STUDENT_NAME"));
						    	 row.add(rs.getString("STUDENT_ID"));
						    	 row.add(rs.getString("ENROLLMENT_NO"));
						    	 row.add(rs.getString("MOBILE"));
						    	 row.add(rs.getString("BATCH"));
						          stabmodel.addRow(row);	
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
	 public void searstudbybatch()
	  {
		  stabmodel.setRowCount(0);
			String sdept = deptbox.getItemAt(deptbox.getSelectedIndex()).toString();
			String sayear = ayearbox.getItemAt(ayearbox.getSelectedIndex()).toString();	
			String sbatch = batchtxt.getText().toString();
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
			      String query = "select * from STUDENT_INFORMATION where DEPARTMENT='"+sdept+"' and AYEAR='"+sayear+"' and BATCH='"+sbatch+"'";
			     ResultSet rs = stmt.executeQuery(query);
			     while(rs.next()){
			    	             Vector row= new Vector();		   			     	
			  	 				 row.add(rs.getString("RID"));
						    	 row.add(rs.getString("STUDENT_NAME"));
						    	 row.add(rs.getString("STUDENT_ID"));
						    	 row.add(rs.getString("ENROLLMENT_NO"));
						    	 row.add(rs.getString("MOBILE"));
						    	 row.add(rs.getString("BATCH"));
						          stabmodel.addRow(row);	
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
				mobtxt.setText(rs.getString("MOBILE"));
				batchtxt.setText(rs.getString("BATCH"));
			    altmobtxt.setText(rs.getString("ALTMOB"));		  
			    addtxt.setText(rs.getString("ADDRESS"));
			    deptbox.setSelectedItem(rs.getString("DEPARTMENT"));
			    ayearbox.setSelectedItem(rs.getString("AYEAR"));
			    citytxt.setText(rs.getString("CITY"));
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
			     if(rs.wasNull())
			     {
			    		photo.setIcon(new ImageIcon(Master.class.getResource("/SAMDESK/rec/photo not.jpg")));	 
			     }else
			     {
			    	   tempfig = ImageIO.read(in);
				   	   tempfig = tempfig.getScaledInstance(photo.getWidth(),photo.getHeight(), Image.SCALE_FAST);
				   	  ImageIcon icon = new ImageIcon(tempfig);
				      photo.setIcon(icon);  
			     }
			   	  
			      }
			      rs.close();
			  
			     }
			    catch(Exception e)
			    {     
			    	System.out.println(e.toString());
			    
			    	
			    }	 
		}
	 
}
