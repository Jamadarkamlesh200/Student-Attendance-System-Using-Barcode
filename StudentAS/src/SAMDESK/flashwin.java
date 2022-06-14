package SAMDESK;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class flashwin extends JFrame {

	private JPanel contentPane;
	Timer tim;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					flashwin frame = new flashwin();
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
	
	public void hidemyself()
	{
	this.setVisible(false);	
	Desktop mywin = new Desktop();
	mywin.setVisible(true);
	}
	ActionListener act = new ActionListener() {
		   @Override
		public void actionPerformed(ActionEvent e) {
		    System.out.println("Swing is powerful!!");
		    tim.stop();
		    hidemyself();
		   }
		  };
	private JLabel regtxt;
	public void waitme()
	{
		
     tim = new Timer(5000,act);
    	tim.start();	  

	}
	
	
	public flashwin() {
		setBackground(Color.WHITE);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 330);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.disabledShadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(flashwin.class.getResource("/rec/logo.png")));
				label.setBounds(64, 63, 79, 188);
		contentPane.add(label);
		
		JLabel lblItrocxyTechnologyPvtlim = new JLabel("V.1.0");
		lblItrocxyTechnologyPvtlim.setForeground(SystemColor.activeCaption);
		lblItrocxyTechnologyPvtlim.setFont(new Font("Traditional Arabic", Font.BOLD, 16));
		lblItrocxyTechnologyPvtlim.setBounds(340, 198, 44, 14);
		contentPane.add(lblItrocxyTechnologyPvtlim);
		
		JLabel lblLibraryManager = new JLabel("Attendance Monitor");
		lblLibraryManager.setFont(new Font("Courier New", Font.PLAIN, 26));
		lblLibraryManager.setForeground(Color.BLUE);
		lblLibraryManager.setBounds(143, 98, 379, 48);
		contentPane.add(lblLibraryManager);
		
		regtxt = new JLabel("Register : Ahinsa Polytechnic,Dondaicha");
		regtxt.setForeground(Color.BLUE);
		regtxt.setFont(new Font("Courier New", Font.PLAIN, 18));
		regtxt.setBounds(153, 139, 359, 48);
		contentPane.add(regtxt);
		waitme();
		starter();
	}
	public void starter()
	{
	regtxt.setText("Ahinsa Polytechnic Dondaicha");	
	}
}
