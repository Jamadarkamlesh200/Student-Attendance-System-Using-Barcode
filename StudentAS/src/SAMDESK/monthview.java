package SAMDESK;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import com.toedter.calendar.JDateChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
//charting imports
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;


public class monthview extends JInternalFrame {
	public DefaultTableModel matbmodel = new DefaultTableModel();
	private JButton btnGenerate;
	private JDateChooser sdate;
	private JDateChooser etdate;
	 String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
	   String DB_URL = "jdbc:mysql://localhost:3306/ams?useTimezone=true&serverTimezone=UTC";
	   String USER="localhost";
	   String PASS="";
	   private JComboBox deptbox;
	   private JComboBox ayearbox;
	   private JTabbedPane tabbedPane;
	   DefaultListModel dm = new DefaultListModel();
	   DefaultListModel rd = new DefaultListModel();
	   private JList dlist;
	   private JList rdlist;
	   private JTable dwtab;
	   private JSplitPane splitPane;
	   private JButton btnCalculate;
	   private JTextField stat_idtxt;
	   private JTextField stat_ridtxt;
	   private JTextField stat_nametxt;
	   private JTextField stat_enrtxt;
	   private JTextField stat_mobtxt;
	   private JTextField stat_batchtxt;
	   private JTextField stat_classtxt;
	   private JTextField stat_depttxt;
	   private JTextField stat_fdate;
	   private JTextField stat_tdate;
	   private JTextField stat_pdaystxt;
	   private JTextField stat_adaystxt;
	   private JTextField stat_totaldaystxt;
	   private JTextField stat_hdaystxt;
	   private JTextField stat_attpertxt;
	   private JTextField stat_gradetxt;
	   private JTextField stat_remarktxt;
	   private JButton btnGraph;
	   private JPanel mygraph;
	   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					monthview frame = new monthview();
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
	public monthview() {
		setBounds(100, 100, 1185, 536);
		getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(10, 0, 1149, 495);
		getContentPane().add(tabbedPane);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Attendance Sheet", null, panel_4, null);
		panel_4.setLayout(null);
		
		splitPane = new JSplitPane();
		splitPane.setDividerSize(10);
		splitPane.setOneTouchExpandable(true);
		splitPane.setBounds(1, 11, 1133, 445);
		panel_4.add(splitPane);
		
		JPanel panel_2 = new JPanel();
		splitPane.setLeftComponent(panel_2);
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(255, 255, 255));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Attendace Dates", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(10, 11, 279, 95);
		panel_2.add(panel);
		panel.setLayout(null);
		
		JLabel lblStart = new JLabel("Start");
		lblStart.setBounds(10, 16, 36, 30);
		panel.add(lblStart);
		lblStart.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		sdate = new JDateChooser();
		sdate.setBounds(44, 16, 87, 30);
		panel.add(sdate);
		sdate.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 8));
		sdate.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		sdate.setDateFormatString("yyyy-MM-dd");
		
		JLabel lblEnd = new JLabel("End");
		lblEnd.setBounds(141, 16, 36, 30);
		panel.add(lblEnd);
		lblEnd.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		etdate = new JDateChooser();
		etdate.setBounds(187, 11, 87, 35);
		panel.add(etdate);
		etdate.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		etdate.setDateFormatString("yyyy-MM-dd");
		
		JButton btnNewButton = new JButton("Display All");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnNewButton.setBounds(44, 53, 87, 30);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Remove ");
		btnNewButton_1.setBounds(194, 53, 80, 30);
		panel.add(btnNewButton_1);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				rd.addElement(dm.getElementAt(dlist.getSelectedIndex()).toString());
				dm.remove(dlist.getSelectedIndex());
				rdlist.setModel(rd);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				  String sedate = ((JTextField)sdate.getDateEditor().getUiComponent()).getText();
				  String edate = ((JTextField)etdate.getDateEditor().getUiComponent()).getText();	
				
				
				try {
					   java.util.LinkedList hitList = searchBetweenDates(
					    new java.text.SimpleDateFormat("yyyy-MM-dd").parse(sedate),
					    new java.text.SimpleDateFormat("yyyy-MM-dd").parse(edate));
					   String[] comboDates = new String[hitList.size()];
				        for(int i=0; i<hitList.size(); i++)
				            comboDates[i] = new java.text.SimpleDateFormat("yyyy-MM-dd - E").format(((java.util.Date)hitList.get(i)));
				 
				        for(int i=0; i<comboDates.length; i++)
				        {
				            System.out.println(comboDates[i]);
				           String tem = comboDates[i].toString();
		                    dm.add(i, tem);
				        }
					   
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 dlist.setModel(dm);
			}
		});
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(130, 135, 144)));
		scrollPane_1.setBounds(10, 105, 139, 329);
		panel_2.add(scrollPane_1);
		
		dlist = new JList();
		scrollPane_1.setViewportView(dlist);
		dlist.setBackground(new Color(255, 255, 255));
		dlist.setForeground(new Color(138, 43, 226));
		dlist.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(161, 105, 128, 329);
		panel_2.add(scrollPane_2);
		
		rdlist = new JList();
		scrollPane_2.setViewportView(rdlist);
		rdlist.setForeground(new Color(138, 43, 226));
		rdlist.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		rdlist.setBackground(new Color(255, 255, 255));
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(255, 255, 255));
		
		JLabel label = new JLabel("Department");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label.setBounds(0, 22, 71, 15);
		panel_1.add(label);
		
		deptbox = new JComboBox();
		deptbox.setModel(new DefaultComboBoxModel(new String[] {"CE", "CM", "ME", "ET", "EE"}));
		deptbox.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		deptbox.setBounds(81, 11, 71, 35);
		panel_1.add(deptbox);
		
		JLabel label_1 = new JLabel("AC-YEAR");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_1.setBounds(162, 22, 71, 15);
		panel_1.add(label_1);
		
		ayearbox = new JComboBox();
		ayearbox.setModel(new DefaultComboBoxModel(new String[] {"FY", "SY", "TY"}));
		ayearbox.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		ayearbox.setBounds(243, 11, 71, 35);
		panel_1.add(ayearbox);
		
		btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
		create_dept_year_model();
			}
		});
		btnGenerate.setBackground(Color.LIGHT_GRAY);
		btnGenerate.setBounds(324, 11, 116, 35);
		panel_1.add(btnGenerate);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setAutoscrolls(true);
		scrollPane_3.setBounds(10, 58, 1092, 374);
		panel_1.add(scrollPane_3);
		
		dwtab = new JTable();
		dwtab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				 stat_ridtxt.setText(dwtab.getValueAt(dwtab.getSelectedRow(),0).toString());
				 stat_nametxt.setText(dwtab.getValueAt(dwtab.getSelectedRow(),1).toString());
				 stat_mobtxt.setText(dwtab.getValueAt(dwtab.getSelectedRow(),2).toString());
				 stat_idtxt.setText(dwtab.getValueAt(dwtab.getSelectedRow(),3).toString());
				 stat_enrtxt.setText(dwtab.getValueAt(dwtab.getSelectedRow(),4).toString());
				 stat_depttxt.setText(dwtab.getValueAt(dwtab.getSelectedRow(),5).toString());
				 stat_batchtxt.setText(dwtab.getValueAt(dwtab.getSelectedRow(),6).toString());
				 stat_classtxt.setText(dwtab.getValueAt(dwtab.getSelectedRow(),7).toString());
				  String sedate = ((JTextField)sdate.getDateEditor().getUiComponent()).getText();
				  String edate = ((JTextField)etdate.getDateEditor().getUiComponent()).getText();	
				  stat_fdate.setText(sedate);
				  stat_tdate.setText(edate);
			      stat_hdaystxt.setText(Integer.toString(rdlist.getModel().getSize()));
			      stat_totaldaystxt.setText(Integer.toString(dlist.getModel().getSize()));
			      int ctr  = dwtab.getColumnCount()-1;
			      stat_pdaystxt.setText(dwtab.getValueAt(dwtab.getSelectedRow(),ctr).toString());
			      int abday = Integer.parseInt(stat_totaldaystxt.getText().toString())-Integer.parseInt(stat_pdaystxt.getText().toString());
			      stat_adaystxt.setText(Integer.toString(abday));
			}
		});
		scrollPane_3.setViewportView(dwtab);
		
		JButton btnTrack = new JButton("Track");
		btnTrack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loopchecker();
			}
		});
		btnTrack.setBackground(Color.LIGHT_GRAY);
		btnTrack.setBounds(442, 11, 116, 35);
		panel_1.add(btnTrack);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				export_paidstatus();
			}
		});
		button_1.setIcon(new ImageIcon(monthview.class.getResource("/rec/excel-2-512.png")));
		button_1.setBounds(689, 7, 49, 39);
		panel_1.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Desktop.attmview_form=0;
			    monthview.this.setVisible(false);	
			}
		});
		button_2.setIcon(new ImageIcon(monthview.class.getResource("/rec/Stop.png")));
		button_2.setBounds(748, 11, 49, 39);
		panel_1.add(button_2);
		
		btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loopcalc();	
			}
		});
		btnCalculate.setBackground(Color.LIGHT_GRAY);
		btnCalculate.setBounds(563, 11, 116, 35);
		panel_1.add(btnCalculate);
		splitPane.setDividerLocation(300);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 250, 250));
		tabbedPane.addTab("Statics", null, panel_3, null);
		panel_3.setLayout(null);
		
		JLabel label_2 = new JLabel("ID");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_2.setBounds(28, 98, 46, 14);
		panel_3.add(label_2);
		
		stat_idtxt = new JTextField();
		stat_idtxt.setColumns(10);
		stat_idtxt.setBounds(133, 90, 85, 35);
		panel_3.add(stat_idtxt);
		
		JLabel label_3 = new JLabel("RID");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_3.setBounds(228, 98, 26, 14);
		panel_3.add(label_3);
		
		stat_ridtxt = new JTextField();
		stat_ridtxt.setEnabled(false);
		stat_ridtxt.setColumns(10);
		stat_ridtxt.setBounds(264, 90, 78, 35);
		panel_3.add(stat_ridtxt);
		
		JLabel label_4 = new JLabel("Name");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_4.setBounds(28, 145, 46, 14);
		panel_3.add(label_4);
		
		stat_nametxt = new JTextField();
		stat_nametxt.setColumns(10);
		stat_nametxt.setBounds(133, 136, 386, 35);
		panel_3.add(stat_nametxt);
		
		JLabel label_5 = new JLabel("Enrollment No");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_5.setBounds(28, 190, 101, 15);
		panel_3.add(label_5);
		
		stat_enrtxt = new JTextField();
		stat_enrtxt.setColumns(10);
		stat_enrtxt.setBounds(133, 178, 199, 35);
		panel_3.add(stat_enrtxt);
		
		JLabel label_6 = new JLabel("Mobile Number");
		label_6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_6.setBounds(22, 234, 101, 15);
		panel_3.add(label_6);
		
		stat_mobtxt = new JTextField();
		stat_mobtxt.setColumns(10);
		stat_mobtxt.setBounds(133, 224, 199, 35);
		panel_3.add(stat_mobtxt);
		
		JLabel label_7 = new JLabel("Batch");
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_7.setBounds(342, 227, 71, 29);
		panel_3.add(label_7);
		
		stat_batchtxt = new JTextField();
		stat_batchtxt.setColumns(10);
		stat_batchtxt.setBounds(413, 226, 106, 35);
		panel_3.add(stat_batchtxt);
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblClass.setBounds(342, 194, 101, 15);
		panel_3.add(lblClass);
		
		stat_classtxt = new JTextField();
		stat_classtxt.setColumns(10);
		stat_classtxt.setBounds(413, 182, 106, 35);
		panel_3.add(stat_classtxt);
		
		JLabel lblDept = new JLabel("Dept");
		lblDept.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDept.setBounds(354, 102, 101, 15);
		panel_3.add(lblDept);
		
		stat_depttxt = new JTextField();
		stat_depttxt.setColumns(10);
		stat_depttxt.setBounds(413, 90, 106, 35);
		panel_3.add(stat_depttxt);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(541, 22, 593, 434);
		panel_3.add(scrollPane);
		
		mygraph = new JPanel();
		scrollPane.setViewportView(mygraph);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(22, 395, 497, 61);
		panel_3.add(panel_6);
		panel_6.setLayout(null);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.setBounds(381, 11, 106, 39);
		panel_6.add(btnPrint);
		
		JLabel lblRemark = new JLabel("Remark");
		lblRemark.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblRemark.setBounds(10, 21, 101, 15);
		panel_6.add(lblRemark);
		
		stat_remarktxt = new JTextField();
		stat_remarktxt.setColumns(10);
		stat_remarktxt.setBounds(80, 13, 171, 35);
		panel_6.add(stat_remarktxt);
		
		btnGraph = new JButton("Graph");
		btnGraph.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				plot_graph();
			}
		});
		btnGraph.setBounds(265, 11, 106, 39);
		panel_6.add(btnGraph);
		
		JLabel lblFromDate = new JLabel("From Date");
		lblFromDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblFromDate.setBounds(28, 45, 101, 15);
		panel_3.add(lblFromDate);
		
		stat_fdate = new JTextField();
		stat_fdate.setColumns(10);
		stat_fdate.setBounds(133, 33, 131, 35);
		panel_3.add(stat_fdate);
		
		JLabel lblToDate = new JLabel("To Date");
		lblToDate.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblToDate.setBounds(286, 45, 89, 15);
		panel_3.add(lblToDate);
		
		stat_tdate = new JTextField();
		stat_tdate.setColumns(10);
		stat_tdate.setBounds(385, 37, 131, 35);
		panel_3.add(stat_tdate);
		
		JLabel lblPresentDays = new JLabel("Present Days");
		lblPresentDays.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPresentDays.setBounds(28, 320, 101, 15);
		panel_3.add(lblPresentDays);
		
		stat_pdaystxt = new JTextField();
		stat_pdaystxt.setColumns(10);
		stat_pdaystxt.setBounds(133, 312, 131, 35);
		panel_3.add(stat_pdaystxt);
		
		JLabel lblAbsentDays = new JLabel("Absent Days");
		lblAbsentDays.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAbsentDays.setBounds(286, 320, 89, 15);
		panel_3.add(lblAbsentDays);
		
		stat_adaystxt = new JTextField();
		stat_adaystxt.setColumns(10);
		stat_adaystxt.setBounds(413, 312, 106, 35);
		panel_3.add(stat_adaystxt);
		
		JLabel lblDays = new JLabel("Total Days");
		lblDays.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblDays.setBounds(31, 282, 101, 15);
		panel_3.add(lblDays);
		
		stat_totaldaystxt = new JTextField();
		stat_totaldaystxt.setColumns(10);
		stat_totaldaystxt.setBounds(136, 270, 131, 35);
		panel_3.add(stat_totaldaystxt);
		
		JLabel lblHolidays = new JLabel("Holidays");
		lblHolidays.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblHolidays.setBounds(289, 282, 89, 15);
		panel_3.add(lblHolidays);
		
		stat_hdaystxt = new JTextField();
		stat_hdaystxt.setColumns(10);
		stat_hdaystxt.setBounds(413, 274, 106, 35);
		panel_3.add(stat_hdaystxt);
		
		JLabel lblAttendance = new JLabel("Attendance %");
		lblAttendance.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblAttendance.setBounds(31, 357, 101, 15);
		panel_3.add(lblAttendance);
		
		stat_attpertxt = new JTextField();
		stat_attpertxt.setColumns(10);
		stat_attpertxt.setBounds(133, 349, 131, 35);
		panel_3.add(stat_attpertxt);
		
		JLabel lblGrade = new JLabel("Grade");
		lblGrade.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblGrade.setBounds(289, 357, 89, 15);
		panel_3.add(lblGrade);
		
		stat_gradetxt = new JTextField();
		stat_gradetxt.setColumns(10);
		stat_gradetxt.setBounds(413, 349, 106, 35);
		panel_3.add(stat_gradetxt);

	}
	public void create_dept_year_model() 
	{
		  matbmodel.setColumnCount(0);
		  matbmodel.setRowCount(0);
		   List<String> mydates = new ArrayList<String>();
		   int mct = dm.size()-1;
			for(int k=0;k<=mct;k++)
			{
			dlist.setSelectedIndex(k);
			String tmp = dlist.getSelectedValue().toString();
			int beginIndex = tmp.length()-5;
			int  endIndex = tmp.length();
			String last = tmp.substring(0, beginIndex );
	          mydates.add(last);
	      
			} 
		   
	/*	   List<Date> dates = new ArrayList<Date>();
		  String sedate = ((JTextField)sdate.getDateEditor().getUiComponent()).getText();
		  String edate = ((JTextField)etdate.getDateEditor().getUiComponent()).getText();
		  DateFormat formatter ; 
		  formatter = new SimpleDateFormat("yyyy-MM-dd");
		  Date startDate = null;
		  try {
		   startDate = (Date)formatter.parse(sedate);
		  } catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		  Date endDate = null;
		  try {
			  endDate = (Date)formatter.parse(edate);
			  } catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		  long interval = 24*1000 * 60 * 60; // 1 hour in millis
		  long endTime =endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
		  long curTime = startDate.getTime();
		  while (curTime <= endTime) {
		      dates.add(new Date(curTime));
		      curTime += interval;
		  }
		  */
		  matbmodel.setRowCount(0);
		  matbmodel.setRowCount(0);
		  matbmodel.addColumn("RID");
		  matbmodel.addColumn("STUDENT_NAME");
		  matbmodel.addColumn("MOBILE");
		  matbmodel.addColumn("STUDENT_ID");
		  matbmodel.addColumn("ENROLLMENT_NO");
		  matbmodel.addColumn("DEPARTMENT");
		  matbmodel.addColumn("BATCH");
		  matbmodel.addColumn("AYEAR");
		   for(int i=0;i<mydates.size();i++){		  
	         matbmodel.addColumn(mydates.get(i));
	       }
		   
		   matbmodel.addColumn("REMARK");
		/*  for(int i=0;i<dates.size();i++){
		      Date lDate =(Date)dates.get(i);
		      String ds = formatter.format(lDate);    
		      matbmodel.addColumn(ds);
		  }
*/

	
dwtab.setModel(matbmodel);	
dwtab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
dwtab.setAutoscrolls(true);
searstudall();
	}
	 public void searstudall()
	  {
		 matbmodel.setRowCount(0);
		 int  cc = dwtab.getColumnCount();
		 int ac = cc-8;
		 JOptionPane.showMessageDialog(null,cc );
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
						    	 row.add(rs.getString("MOBILE")); 	
						    	 row.add(rs.getString("STUDENT_ID")); 	
						    	 row.add(rs.getString("ENROLLMENT_NO"));
						    	 row.add(rs.getString("DEPARTMENT"));
						    	 row.add(rs.getString("BATCH"));
						    	 row.add(rs.getString("AYEAR"));
						    	
						    	for(int i=0;i<=ac-2;i++)
						    	{
						    		 row.add("A");
						    	}
						    	 row.add("NO");
						    	dwtab.getTableHeader().setBackground(Color.ORANGE);
						    	dwtab.setRowHeight(30);
						    	 matbmodel.addRow(row);	
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
	 public static java.util.LinkedList searchBetweenDates(java.util.Date startDate, java.util.Date endDate) 
	    {
	        java.util.Date begin = new Date(startDate.getTime());
	        java.util.LinkedList list = new java.util.LinkedList();
	        list.add(new Date(begin.getTime()));
	 
	        while(begin.compareTo(endDate)<0)
	        {
	            begin = new Date(begin.getTime() + 86400000);
	            list.add(new Date(begin.getTime()));
	        }
	 
	        return list;
	    }
	 public void loopchecker()
	 {
		//JOptionPane.showMessageDialog(null, dwtab.getValueAt(1, 7));
		int ctr = dwtab.getColumnCount()-1;
		int ccr = dwtab.getRowCount();
		String trid;
		
	for(int i=8;i<ctr;i++)
	{
		System.out.println("Date :"+dwtab.getColumnName(i).toString());
		for(int j=0;j<ccr;j++)
		{
			   String tri = dwtab.getValueAt(j,0).toString();
			   String tdate=dwtab.getColumnName(i).toString();
			  //System.out.println("value"+dwtab.getValueAt(j,0));
			 //JOptionPane.showMessageDialog(null, dwtab.getColumnName(i).toString());
			  setpara(tri,j,i,tdate);
		}
		
	} 
	 }
	
	public void loopcalc()
	 {
		//JOptionPane.showMessageDialog(null, dwtab.getValueAt(1, 7));
		int ctr = dwtab.getColumnCount()-1;
		int ccr = dwtab.getRowCount();
		String tri = null;
		String tdate;
		String trid;
		int total =0;
		int rowtotal = 0;
		int coltotal;
		String dta= null;
	/*	for(int j=8;j<ctr;j++)
		{
			for (int i=0;i<ccr;i++)
			{
				
			dta = dwtab.getValueAt(i,j).toString();
			JOptionPane.showMessageDialog(null, dta);
			}
		}*/
		for (int i=0;i<ccr;i++)
		{
			for(int j=8;j<ctr;j++)
			{
				dta = dwtab.getValueAt(i,j).toString();
				if(dta.equalsIgnoreCase("P"))
				{
					rowtotal = rowtotal + 1;
				}
				
			}
		//	JOptionPane.showMessageDialog(null, "Final Row Total " + Integer.toString(rowtotal));
			dwtab.setValueAt(rowtotal, i, ctr);
			rowtotal =0;
		
		}

  
	 }
	 public void setpara(String rid,int row,int col,String chdate)
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
		      String sedate = chdate;
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
			    
			    	
			    	 String stat;
			    	 if(rs.getString("STATUS") != null)
			    	 {
			    		 stat = rs.getString("STATUS");
			    	 }
			    	 else
			    	 {
			    		 stat = "A";
			    	 }
			    	 
			      
			         dwtab.setValueAt(stat, row, col);
			  	 				
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
	 public void setcal(String rid,int row,int col,String chdate)
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
		      String sedate = chdate;
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
			    
			    	
			    	 String stat;
			    	 if(rs.getString("STATUS") != null)
			    	 {
			    		 stat = rs.getString("STATUS");
			    	 }
			    	 else
			    	 {
			    		 stat = "A";
			    	 }
			    	 
			      
			         dwtab.setValueAt(stat, row, col);
			  	 				
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
	 	int cc=dwtab.getColumnCount();
	 	int cr=dwtab.getRowCount();
	 	try{
	 		  FileWriter excel = new FileWriter(tfile);
	 		  for(int h=0;h<cc;h++)
	 		  {
	 			  excel.write(dwtab.getColumnName(h).toString()+"\t");
	 		  }
	 		  excel.write("\n");
	 		  for(int x=0;x<cr;x++)
	 			{
	 			 
	 				for(int v=0;v<cc;v++)
	 				{
	 				try {				
	 				 excel.write(dwtab.getValueAt(x, v).toString()+"\t");	
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
	 public void plot_graph()
	 {
		 DefaultPieDataset dataset = new DefaultPieDataset();
	        dataset.setValue("Present", 30.00);
	        dataset.setValue("Absent",70.00);	      	        
	        JFreeChart chart1 = ChartFactory.createPieChart("Attendance Chart", dataset, false, false, false);
	        ChartPanel chartPanel = new ChartPanel(chart1);
	        chartPanel.setSize(200,200);
	        PiePlot plot1 = (PiePlot) chart1.getPlot();
	        plot1.setCircular(true);	
	        mygraph.add(chartPanel);	     
	         this.repaint();
	 }
}
