
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
		setBounds(100, 100, 419, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 this.setResizable(false); // 창의 확장을 막는 메서드
		
		ArrayList<JTextField> fields = new ArrayList<>();
		ArrayList<JComboBox> combos = new ArrayList<>();
		
		pidField = new JTextField();
		pidField.setEditable(false);
		pidField.setBounds(55, 10, 90, 21);
		contentPane.add(pidField);
		pidField.setColumns(10);
		//추가할 물품의 pid를 구한다. pid = max(pid)+1 from product
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
		pnameField.setBounds(226, 10, 163, 21);
		contentPane.add(pnameField);
		pnameField.setColumns(10);

		costField = new JTextField();
		costField.setBounds(226, 41, 163, 21);
		contentPane.add(costField);
		costField.setColumns(10);
		
		imageField = new JTextField();
		imageField.setEditable(false);
		imageField.setBounds(55, 104, 225, 21);
		contentPane.add(imageField);
		imageField.setColumns(10);
		
		xField = new JTextField();
		xField.setBounds(55, 133, 90, 20);
		contentPane.add(xField);
		xField.setColumns(10);

		yField = new JTextField();
		yField.setBounds(226, 133, 90, 20);
		contentPane.add(yField);
		yField.setColumns(10);

		URLField = new JTextField();
		URLField.setBounds(55, 163, 308, 21);
		contentPane.add(URLField);
		URLField.setColumns(10);
		
		JComboBox cidBox = new JComboBox();
		cidBox.setBounds(55, 72, 90, 21);
		contentPane.add(cidBox);
		//	cid를 받아와 콤보박스에 추가
		
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
		cnameBox.setBounds(226, 72, 163, 21);
		contentPane.add(cnameBox);
		// cname를 받아와 콤보박스에 추가
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
		floorBox.setBounds(55, 41, 90, 21);
		contentPane.add(floorBox);
		// floor를 받아와 콤보박스에 추가
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
		fileButton.addActionListener(new UploadActionListener(imageField));
		fileButton.setBounds(292, 103, 97, 23);
		contentPane.add(fileButton);
		
		//arraylist에 추가
		fields.add(pidField); fields.add(pnameField); fields.add(costField);
		fields.add(xField); fields.add(yField); fields.add(URLField);
		fields.add(imageField);

		combos.add(cidBox); combos.add(cnameBox); combos.add(floorBox);

		JLabel lblPid = new JLabel("pid");
		lblPid.setBounds(12, 13, 57, 15);
		contentPane.add(lblPid);

		JLabel lblPanme = new JLabel("pname");
		lblPanme.setBounds(157, 13, 57, 15);
		contentPane.add(lblPanme);

		JLabel lblCost = new JLabel("cost");
		lblCost.setBounds(157, 44, 57, 15);
		contentPane.add(lblCost);

		JLabel lblFloor = new JLabel("floor");
		lblFloor.setBounds(12, 44, 57, 15);
		contentPane.add(lblFloor);

		JLabel lblCategory = new JLabel("category");
		lblCategory.setBounds(157, 75, 57, 15);
		contentPane.add(lblCategory);

		lblCid = new JLabel("cid");
		lblCid.setBounds(12, 75, 57, 15);
		contentPane.add(lblCid);

		lblImage = new JLabel("image");
		lblImage.setBounds(12, 107, 57, 15);
		contentPane.add(lblImage);

		lblX = new JLabel("x");
		lblX.setBounds(12, 134, 57, 15);
		contentPane.add(lblX);

		lblY = new JLabel("y");
		lblY.setBounds(157, 136, 57, 15);
		contentPane.add(lblY);

		lblUrl = new JLabel("URL");
		lblUrl.setBounds(12, 167, 57, 15);
		contentPane.add(lblUrl);

		addButton = new JButton("\uCD94\uAC00");
		addButton.addActionListener(new addProActionListener(fields, combos, conn));
			
		addButton.setBounds(81, 201, 97, 34);
		contentPane.add(addButton);

		cancleButton = new JButton("\uCDE8\uC18C");
		cancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//취소 버튼 클릭
				dispose();
			}
		});
		cancleButton.setBounds(214, 201, 97, 34);
		contentPane.add(cancleButton);
	}
}