package SAMDESK;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class atview extends JInternalFrame {
	private static final String Null = null;
	private JTable datab;
	private JTextField batchtxt;
	public DefaultTableModel databmodel = new DefaultTableModel();
	private JButton button;
	private JDateChooser cdate;
	String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	   String DB_URL = "jdbc:mysql://localhost:3306/ams?useTimezone=true&serverTimezone=UTC";
	   String USER="root";
	   String PASS="";
	   private JComboBox deptbox;
	   private JComboBox ayearbox;
	   private JButton button_1;
	   private JButton button_2;
	   private JButton button_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					atview frame = new atview();
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
	public atview() {
		getContentPane().setBackground(new Color(0, 255, 0));
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 1065, 79);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setBounds(10, 11, 575, 61);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("Department");
		label_1.setBounds(0, 22, 71, 15);
		panel_1.add(label_1);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		deptbox = new JComboBox();
		deptbox.setBounds(81, 11, 71, 35);
		panel_1.add(deptbox);
		deptbox.setModel(new DefaultComboBoxModel(new String[] {"CE", "CM", "ME", "ET", "EE"}));
		deptbox.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		
		JLabel label_2 = new JLabel("AC-YEAR");
		label_2.setBounds(162, 22, 71, 15);
		panel_1.add(label_2);
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		ayearbox = new JComboBox();
		ayearbox.setBounds(243, 11, 71, 35);
		panel_1.add(ayearbox);
		ayearbox.setModel(new DefaultComboBoxModel(new String[] {"FY", "SY", "TY"}));
		ayearbox.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		
		JLabel label = new JLabel("Batch");
		label.setBounds(399, 19, 101, 15);
		panel_1.add(label);
		label.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		batchtxt = new JTextField();
		batchtxt.setBounds(445, 11, 55, 35);
		panel_1.add(batchtxt);
		batchtxt.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}
		});
		batchtxt.setColumns(10);
		
		button_1 = new JButton("");
		button_1.setBounds(510, 8, 55, 35);
		panel_1.add(button_1);
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				searstudbybatch();
			}
		});
		button_1.setIcon(new ImageIcon(atview.class.getResource("/rec/search small.png")));
		button_1.setBackground(Color.LIGHT_GRAY);
		
		button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				searstudall();
			}
		});
		button_2.setIcon(new ImageIcon(atview.class.getResource("/rec/search small.png")));
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setBounds(324, 11, 55, 35);
		panel_1.add(button_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 215, 0));
		panel_2.setBounds(595, 11, 460, 61);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_3 = new JLabel("Date");
		label_3.setBounds(10, 16, 36, 30);
		panel_2.add(label_3);
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		cdate = new JDateChooser();
		cdate.setBounds(44, 11, 133, 35);
		panel_2.add(cdate);
		cdate.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		cdate.setDateFormatString("yyyy-MM-dd");
		
		JButton btnPublish = new JButton("");
		btnPublish.setIcon(new ImageIcon(atview.class.getResource("/rec/printers.png")));
		btnPublish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				create_attendancepdf();			
			}
		});
		btnPublish.setBounds(253, 11, 55, 39);
		panel_2.add(btnPublish);
		
		JButton btnPrint = new JButton("");
		btnPrint.setIcon(new ImageIcon(atview.class.getResource("/rec/excel-2-512.png")));
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				export_paidstatus();
			}
		});
		btnPrint.setBounds(318, 11, 49, 39);
		panel_2.add(btnPrint);
		
		button = new JButton("");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loopchecker();
			}
		});
		button.setBounds(188, 11, 55, 35);
		panel_2.add(button);
		button.setBackground(Color.LIGHT_GRAY);
		button.setIcon(new ImageIcon(atview.class.getResource("/rec/search small.png")));
		
		button_3 = new JButton("");
		button_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				  Desktop.attview_form=0;
					atview.this.setVisible(false);	
			}
		});
		button_3.setIcon(new ImageIcon(atview.class.getResource("/rec/Stop.png")));
		button_3.setBounds(377, 11, 49, 39);
		panel_2.add(button_3);
		databmodel.addColumn("RID");
		databmodel.addColumn("STUDENT_NAME");
		databmodel.addColumn("STUDENT_ID");
		databmodel.addColumn("ENROLLMENT_NO");
		databmodel.addColumn("DEPARTMENT");
		databmodel.addColumn("BATCH");
		databmodel.addColumn("AYEAR");
		databmodel.addColumn("INTIME");
		databmodel.addColumn("OUTTIME");
		databmodel.addColumn("STATUS");
	
         JScrollPane scrollPane = new JScrollPane();
         scrollPane.setBounds(10, 101, 1065, 412);
         getContentPane().add(scrollPane);
         
         datab = new JTable();
     	datab.getTableHeader().setBackground(Color.ORANGE);
		datab.setRowHeight(30);
         scrollPane.setViewportView(datab);
         datab.setModel(databmodel);
		setBounds(100, 100, 1101, 554);

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
		    databmodel.setRowCount(0);
		    
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
						          databmodel.addRow(row);	
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
		    databmodel.setRowCount(0);
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
						    	 row.add(rs.getString("DEPARTMENT"));
						    	 row.add(rs.getString("BATCH"));
						    	 row.add(rs.getString("AYEAR"));
						    	 row.add("A");
						    	 row.add("A");
						    	 row.add("A");
						    	databmodel.addRow(row);	
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
	 public void searstudall()
	  {
		    databmodel.setRowCount(0);
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
			      String query = "select * from STUDEN"
			      		+ "T_INFORMATION where DEPARTMENT='"+sdept+"' and AYEAR='"+sayear+"'";
			     ResultSet rs = stmt.executeQuery(query);
			     while(rs.next()){
			    	             Vector row= new Vector();		   			     	
			  	 				 row.add(rs.getString("RID"));
						    	 row.add(rs.getString("STUDENT_NAME"));
						    	 row.add(rs.getString("STUDENT_ID")); 	
						    	 row.add(rs.getString("ENROLLMENT_NO"));
						    	 row.add(rs.getString("DEPARTMENT"));
						    	 row.add(rs.getString("BATCH"));
						    	 row.add(rs.getString("AYEAR"));
						    	 row.add(rs.getString("INTIME"));
						    	 row.add(rs.getString("OUTTIME"));
						    	 row.add(rs.getString("STATUS"));
						    	databmodel.addRow(row);	
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
	 public void loopchecker()
	 {
		int ctr = datab.getRowCount();
		String trid;
	for(int i=0;i<ctr;i++)
	{
		trid = datab.getValueAt(i,0).toString();
		setpara(trid,i);
	} 
	
	 }
	 
	 public void setpara(String rid,int row)
	 {
		
		 /*
		  * databmodel.addColumn("RID");
		databmodel.addColumn("STUDENT_NAME");
		databmodel.addColumn("STUDENT_ID");
		databmodel.addColumn("ENROLLMENT_NO");
		databmodel.addColumn("DEPARTMENT");
		databmodel.addColumn("BATCH");
		databmodel.addColumn("AYEAR");
		databmodel.addColumn("INTIME");
		databmodel.addColumn("OUTTIME");
		databmodel.addColumn("STATUS");
		  */
		      String sedate = ((JTextField)cdate.getDateEditor().getUiComponent()).getText();
			  Connection conn = null;
			   Statement stmt = null;		  
	    	
			  try
			  {
			     Class.forName("com.mysql.cj.jdbc.Driver");
			     conn=DriverManager.getConnection(DB_URL,USER,PASS);
			     stmt = conn.createStatement();
			      String query = "select * from STUDENT_DAILY where EDATE='"+sedate+"' and RID="+rid;
			     ResultSet rs = stmt.executeQuery(query);
			     while(rs.next()){
			    
			    	 String it;
			    	
			    	 if(rs.getString("INTIME") != null)
			    	 {
			    		 it = rs.getString("INTIME");
			    	 }
			    	 else
			    	 {
			    		 it = "A";
			    	 }
			    	 String ot;
			    
				    	
			    	 if(rs.getString("OUTTIME") != null)
			    	 {
			    		 ot = rs.getString("OUTTIME");
			    	 }
			    	 else
			    	 {
			    		 ot = "A";
			    	 }
			    	 
			    	
			    	 String stat;
			    	 if(rs.getString("STATUS") != null)
			    	 {
			    		 stat = rs.getString("STATUS");
			    	 }
			    	 else
			    	 {
			    		 stat = "A";
			    	 }
			    	 
			       	 datab.setValueAt(it, row, 7);
			    	 datab.setValueAt(ot, row, 8);
			    	 datab.setValueAt(stat, row, 9);
			  	 				
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
	 public void export_paidstatus()
	 {
	 JFileChooser fileChooser = new JFileChooser();
	 	fileChooser.setDialogTitle("Specify a file to save"); 
	 	File tfile=null;
	 	int userSelection = fileChooser.showSaveDialog(null);
	 	 
	 	if (userSelection == JFileChooser.APPROVE_OPTION) {
	 	    File fileToSave = fileChooser.getSelectedFile();
	 	   tfile=new File(fileToSave.getAbsolutePath().toString()+".xls");
	 	}
	 	int cc=datab.getColumnCount();
	 	int cr=datab.getRowCount();
	 	try{
	 		  FileWriter excel = new FileWriter(tfile);
	 		  for(int h=0;h<cc;h++)
	 		  {
	 			  excel.write(datab.getColumnName(h).toString()+"\t");
	 		  }
	 		  excel.write("\n");
	 		  for(int x=0;x<cr;x++)
	 			{
	 			 
	 				for(int v=0;v<cc;v++)
	 				{
	 				try {				
	 				 excel.write(datab.getValueAt(x, v).toString()+"\t");	
	 				}catch(Exception nm)
	 				{
	 					
	 				}
	 				 //	JOptionPane.showMessageDialog(null, inst_tab.getValueAt(x, v).toString());
	 				}
	 				excel.write("\n");
	 			}
	 		excel.close();
	 	}catch(IOException e){ System.out.println(e); }
	 	
	 }
	 public void create_attendancepdf()
	 {
	 	String Filename=null;
	 	JFileChooser fileChooser = new JFileChooser();
	 	fileChooser.setDialogTitle("Specify a file to save");  
	 	int userSelection = fileChooser.showSaveDialog(null);
	 	
	 	if (userSelection == JFileChooser.APPROVE_OPTION) {
	 	    File fileToSave = fileChooser.getSelectedFile();
	 	    Filename=fileToSave.getAbsolutePath().toString()+".pdf";
	 	} 
	 	 
	 	Document document = null ;
	 	
	 	
	 		document= new Document(PageSize.A4);	
	 	
	     try {
	       PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(Filename));
	        document.open();
	        PdfPTable header = new PdfPTable(2);
	 	      header.setTotalWidth(527);
	  header.setLockedWidth(true);
	  header.getDefaultCell().setFixedHeight(20);
	  header.getDefaultCell().setBorder(Rectangle.BOTTOM);
	  header.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
	  header.setWidths(new int[]{2, 24});
	  // add image
	
	  PdfPCell lcell = new PdfPCell();
	

	  // add text
	  PdfPCell text = new PdfPCell();
	  text.setPaddingBottom(15);
	  text.setPaddingLeft(10);
	  text.setBorder(Rectangle.BOTTOM);
	  text.setBorderColor(BaseColor.LIGHT_GRAY);
	  
	 
	  header.addCell(text);
	 	 document.add(header);
	 	  Paragraph paragraphOne = new Paragraph("Attendance Sheet:",FontFactory.getFont(FontFactory.TIMES_ROMAN, 18f));
	 	  paragraphOne.setAlignment(Element.ALIGN_CENTER);
	 	  String sdept = deptbox.getItemAt(deptbox.getSelectedIndex()).toString();
			String sayear = ayearbox.getItemAt(ayearbox.getSelectedIndex()).toString();	
			String sbatch = batchtxt.getText().toString();
			
	 	  Paragraph paragraphtwo = new Paragraph("Department:- "+sdept+" YEAR:- "+sayear,FontFactory.getFont(FontFactory.TIMES_ROMAN, 18f));
		  paragraphtwo.setAlignment(Element.ALIGN_CENTER);
	 	  document.add(paragraphOne);
	 	  document.add(paragraphtwo);
	 		  document.add( Chunk.NEWLINE );
	       PdfPTable pdtable = new PdfPTable(6); // 3 columns.
	       pdtable.setWidthPercentage(100);
	       pdtable.getDefaultCell().setBackgroundColor(GrayColor.GRAYWHITE);
	       pdtable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	       pdtable.addCell("NAME");
	       pdtable.addCell("ID");
	       pdtable.addCell("ENROLLMENT");
	       pdtable.addCell("IN TIME");
	       pdtable.addCell("OUT TIME");
	       pdtable.addCell("STATUS");
	       int fcount=datab.getRowCount();
	 		Boolean flag=false;
	 		for(int j=0;j<fcount;j++)
	 		{
	 			
	 				  pdtable.addCell(datab.getValueAt(j,1).toString());
	 			      pdtable.addCell(datab.getValueAt(j,2).toString());
	 			      pdtable.addCell(datab.getValueAt(j,3).toString());
	 			      pdtable.addCell(datab.getValueAt(j,7).toString());
	 			      pdtable.addCell(datab.getValueAt(j,8).toString());
	 			      pdtable.addCell(datab.getValueAt(j,9).toString());
	 			   
	 		}
	 		
	 		

	          document.add(pdtable);
	           Paragraph mysign = new Paragraph("Authorised Signature",FontFactory.getFont(FontFactory.COURIER_BOLD, 20f));
	           document.add( Chunk.NEWLINE );
	 	      document.add( Chunk.NEWLINE );
	           document.add(mysign);
	           document.close();
	           writer.close();
	     } catch (Exception e) {
	       System.err.println(e.getMessage());
	     }
	     document.close(); 	

	 	
	 }
}
