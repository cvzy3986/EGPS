
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class Add_Screen extends JFrame {
	private JPanel contentPane;
	private JTextField pidField;
	private JTextField pnameField;
	private JTextField costField;
	private JTextField xField;
	private JTextField yField;
	private JTextField URLField;
	private JTextField imageField;
	private JLabel lblCid;
	private JLabel lblImage;
	private JLabel lblX;
	private JLabel lblY;
	private JLabel lblUrl;
	private JButton addButton;
	private JButton cancleButton;
	static String filePath;
	
	public Add_Screen(Connection conn) {
		addComponets(conn);
	}

	private void addComponets(Connection conn) {
		try {
			Statement stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * Create the frame.
		 */
		setBounds(100, 100, 1600, 1600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(219,208,186));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
//		 this.setResizable(false); // Ã¢ÀÇ È®ÀåÀ» ¸·´Â ¸Þ¼­µå
		
		ArrayList<JTextField> fields = new ArrayList<>();
		ArrayList<JComboBox> combos = new ArrayList<>();
		
		pidField = new JTextField();
		pidField.setFont(new Font("±¼¸²", Font.PLAIN, 28));
		pidField.setEditable(false);
		pidField.setBounds(211, 83, 275, 50);
		contentPane.add(pidField);
		pidField.setColumns(10);
		//Ãß°¡ÇÒ ¹°Ç°ÀÇ pid¸¦ ±¸ÇÑ´Ù. pid = max(pid)+1 from product
		try {
				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery("select max(pid)+1 addNum from product");
				
				while (rset.next()) 
					pidField.setText(rset.getString("addNum"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		

		pnameField = new JTextField();
		pnameField.setFont(new Font("±¼¸²", Font.PLAIN, 28));
		pnameField.setBounds(734, 83, 275, 50);
		contentPane.add(pnameField);
		pnameField.setColumns(10);

		costField = new JTextField();
		costField.setFont(new Font("±¼¸²", Font.PLAIN, 28));
		costField.setBounds(734, 200, 275, 50);
		contentPane.add(costField);
		costField.setColumns(10);
		
		imageField = new JTextField();
		imageField.setFont(new Font("±¼¸²", Font.PLAIN, 28));
		imageField.setEditable(false);
		imageField.setBounds(211, 440, 598, 50);
		contentPane.add(imageField);
		imageField.setColumns(10);
		
		xField = new JTextField();
		xField.setFont(new Font("±¼¸²", Font.PLAIN, 28));
		xField.setBounds(211, 568, 275, 50);
		contentPane.add(xField);
		xField.setColumns(10);

		yField = new JTextField();
		yField.setFont(new Font("±¼¸²", Font.PLAIN, 28));
		yField.setBounds(734, 568, 275, 50);
		contentPane.add(yField);
		yField.setColumns(10);

		URLField = new JTextField();
		URLField.setFont(new Font("±¼¸²", Font.PLAIN, 28));
		URLField.setBounds(211, 673, 798, 50);
		contentPane.add(URLField);
		URLField.setColumns(10);
		
		JComboBox cidBox = new JComboBox();
		cidBox.setFont(new Font("±¼¸²", Font.PLAIN, 28));
		cidBox.setBounds(211, 320, 275, 50);
		contentPane.add(cidBox);
		//	cid¸¦ ¹Þ¾Æ¿Í ÄÞº¸¹Ú½º¿¡ Ãß°¡
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("Select cid from category");
			while (rset.next()) {
				cidBox.addItem(rset.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JComboBox cnameBox = new JComboBox();
		cnameBox.setFont(new Font("±¼¸²", Font.PLAIN, 28));
		cnameBox.setBounds(734, 320, 275, 50);
		contentPane.add(cnameBox);
		// cname¸¦ ¹Þ¾Æ¿Í ÄÞº¸¹Ú½º¿¡ Ãß°¡
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("Select cname from category");
			while (rset.next()) {
				cnameBox.addItem(rset.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JComboBox floorBox = new JComboBox();
		floorBox.setFont(new Font("±¼¸²", Font.PLAIN, 28));
		floorBox.setBounds(211, 200, 275, 50);
		contentPane.add(floorBox);
		// floor¸¦ ¹Þ¾Æ¿Í ÄÞº¸¹Ú½º¿¡ Ãß°¡
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("Select distinct(floor) from category");
			while (rset.next()) {
				floorBox.addItem(rset.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton fileButton = new JButton("\uD30C\uC77C \uC120\uD0DD");
		fileButton.setBackground(new Color(85,75,75));
		fileButton.setForeground(new Color(255,255,255));
		fileButton.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		fileButton.addActionListener(new UploadActionListener(imageField));
		fileButton.setBounds(821, 440, 188, 50);
		contentPane.add(fileButton);
		
		//arraylist¿¡ Ãß°¡
		fields.add(pidField); fields.add(pnameField); fields.add(costField);
		fields.add(xField); fields.add(yField); fields.add(URLField);
		fields.add(imageField);

		combos.add(cidBox); combos.add(cnameBox); combos.add(floorBox);

		JLabel lblPid = new JLabel("pid");
		lblPid.setFont(new Font("±¼¸²", Font.PLAIN, 35));
		lblPid.setBounds(71, 65, 114, 69);
		contentPane.add(lblPid);

		JLabel lblPanme = new JLabel("pname");
		lblPanme.setFont(new Font("±¼¸²", Font.PLAIN, 35));
		lblPanme.setBounds(552, 73, 168, 53);
		contentPane.add(lblPanme);

		JLabel lblCost = new JLabel("cost");
		lblCost.setFont(new Font("±¼¸²", Font.PLAIN, 35));
		lblCost.setBounds(552, 196, 96, 41);
		contentPane.add(lblCost);

		JLabel lblFloor = new JLabel("floor");
		lblFloor.setFont(new Font("±¼¸²", Font.PLAIN, 35));
		lblFloor.setBounds(71, 177, 150, 81);
		contentPane.add(lblFloor);

		JLabel lblCategory = new JLabel("category");
		lblCategory.setFont(new Font("±¼¸²", Font.PLAIN, 35));
		lblCategory.setBounds(552, 312, 205, 50);
		contentPane.add(lblCategory);

		lblCid = new JLabel("cid");
		lblCid.setFont(new Font("±¼¸²", Font.PLAIN, 35));
		lblCid.setBounds(71, 307, 114, 60);
		contentPane.add(lblCid);

		lblImage = new JLabel("image");
		lblImage.setFont(new Font("±¼¸²", Font.PLAIN, 35));
		lblImage.setBounds(71, 440, 160, 62);
		contentPane.add(lblImage);

		lblX = new JLabel("x");
		lblX.setFont(new Font("±¼¸²", Font.PLAIN, 35));
		lblX.setBounds(71, 531, 275, 107);
		contentPane.add(lblX);

		lblY = new JLabel("y");
		lblY.setFont(new Font("±¼¸²", Font.PLAIN, 35));
		lblY.setBounds(620, 531, 137, 107);
		contentPane.add(lblY);

		lblUrl = new JLabel("URL");
		lblUrl.setFont(new Font("±¼¸²", Font.PLAIN, 35));
		lblUrl.setBounds(71, 669, 129, 41);
		contentPane.add(lblUrl);

		addButton = new JButton("\uCD94\uAC00");
		addButton.setBackground(new Color(85,75,75));
		addButton.setForeground(new Color(255,255,255));
		addButton.setFont(new Font("±¼¸²", Font.PLAIN, 35));
		addButton.addActionListener(new addProActionListener(fields, combos, conn));
			
		addButton.setBounds(318, 835, 168, 69);
		contentPane.add(addButton);

		cancleButton = new JButton("\uCDE8\uC18C");
		cancleButton.setBackground(new Color(85,75,75));
		cancleButton.setForeground(new Color(255,255,255));
		cancleButton.setFont(new Font("±¼¸²", Font.PLAIN, 35));
		cancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Ãë¼Ò ¹öÆ° Å¬¸¯
				dispose();
			}
		});
		cancleButton.setBounds(552, 835, 170, 69);
		contentPane.add(cancleButton);
	}
}