package SAMDESK;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Desktop extends JFrame {
	public static int master_form=0;
	public static int att_form=0;
	public static int attview_form=0;
	public static int attmview_form=0;
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JMenuItem mntmAttendance;
	private JMenuItem mntmMonthwise;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Desktop frame = new Desktop();
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
	public Desktop() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1274, 690);
		 desktopPane = new JDesktopPane();
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
	JMenu mnForms = new JMenu("Windows");
		menuBar.add(mnForms);
		
		JMenuItem mntmStudentsDetails = new JMenuItem("Students Details");
		mntmStudentsDetails.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(master_form==0)
				{
				 Master ird = new Master();
	             desktopPane.add(ird);
	             Dimension desktopSize = desktopPane.getSize();
	             Dimension jInternalFrameSize = ird.getSize();
	             ird.setLocation((desktopSize.width - jInternalFrameSize.width)/2,(desktopSize.height- jInternalFrameSize.height)/2);
	             ird.setVisible(true);
	             master_form=1;
				}
	            
			}
		});
		mnForms.add(mntmStudentsDetails);
		
		mntmAttendance = new JMenuItem("Attendance");
		mntmAttendance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(att_form==0)
				{
				Attendance ird = new Attendance();
	             desktopPane.add(ird);
	             Dimension desktopSize = desktopPane.getSize();
	             Dimension jInternalFrameSize = ird.getSize();
	             ird.setLocation((desktopSize.width - jInternalFrameSize.width)/2,(desktopSize.height- jInternalFrameSize.height)/2);
	             ird.setVisible(true);
	             att_form=1;
				}	
			}
		});
		mnForms.add(mntmAttendance);
		
		JMenuItem mntmView = new JMenuItem("View");
		mntmView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(attview_form==0)
				{
				atview ird = new atview();
	             desktopPane.add(ird);
	             Dimension desktopSize = desktopPane.getSize();
	             Dimension jInternalFrameSize = ird.getSize();
	             ird.setLocation((desktopSize.width - jInternalFrameSize.width)/2,(desktopSize.height- jInternalFrameSize.height)/2);
	             ird.setVisible(true);
	             attview_form=1;
				}	
			}
		});
		mnForms.add(mntmView);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnForms.add(mntmExit);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem mntmDaywise = new JMenuItem("Daywise");
		mntmDaywise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(attview_form==0)
				{
				 atview ird = new atview();
	             desktopPane.add(ird);
	             Dimension desktopSize = desktopPane.getSize();
	             Dimension jInternalFrameSize = ird.getSize();
	             ird.setLocation((desktopSize.width - jInternalFrameSize.width)/2,(desktopSize.height- jInternalFrameSize.height)/2);
	             ird.setVisible(true);
	             attview_form=1;
				}			
				
			}
		});
		mnReports.add(mntmDaywise);
		
		mntmMonthwise = new JMenuItem("Monthwise");
		mntmMonthwise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(attmview_form==0)
				{
				monthview ird = new monthview();
	             desktopPane.add(ird);
	             Dimension desktopSize = desktopPane.getSize();
	             Dimension jInternalFrameSize = ird.getSize();
	             ird.setLocation((desktopSize.width - jInternalFrameSize.width)/2,(desktopSize.height- jInternalFrameSize.height)/2);
	             ird.setVisible(true);
	             attmview_form=1;
				}		
			}
		});
		mnReports.add(mntmMonthwise);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
