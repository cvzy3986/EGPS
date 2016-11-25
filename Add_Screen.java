import java.awt.BorderLayout;
import java.awt.EventQueue;

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

import java.awt.event.ActionListener;
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
import java.awt.event.ActionEvent;

public class Add_Screen extends JFrame {

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

		JPanel contentPane;
		JTextField pidField;
		JTextField pnameField;
		JTextField floorField;
		JTextField costField;
		JTextField cateField;
		JTextField xField;
		JTextField yField;
		JTextField URLField;
		JLabel lblCid;
		JLabel lblImage;
		JLabel lblX;
		JLabel lblY;
		JLabel lblUrl;
		JButton addButton;
		JButton cancleButton;
		JTextField imageField;
		
		/**
		 * Create the frame.
		 */

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pidField = new JTextField();
		pidField.setEditable(false);
		pidField.setBounds(55, 10, 90, 21);
		contentPane.add(pidField);
		pidField.setColumns(10);

		pnameField = new JTextField();
		pnameField.setBounds(226, 10, 163, 21);
		contentPane.add(pnameField);
		pnameField.setColumns(10);

		floorField = new JTextField();
		floorField.setBounds(55, 41, 90, 21);
		contentPane.add(floorField);
		floorField.setColumns(10);

		costField = new JTextField();
		costField.setBounds(226, 41, 163, 21);
		contentPane.add(costField);
		costField.setColumns(10);

		cateField = new JTextField();
		cateField.setBounds(226, 72, 163, 21);
		contentPane.add(cateField);
		cateField.setColumns(10);

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

		JButton fileButton = new JButton("\uD30C\uC77C \uC120\uD0DD");
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser(); // 파일 선택기를 사용
				File file;
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					file = fc.getSelectedFile(); 
//					imageField.setText(file.toString());
				}
				else {
					// JOptionPane.showMessageDialog(null, "파일을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				InputStream input = null;
				try {
					input = new FileInputStream(file);
//					imageField.setText(input.toString());
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(input);
				
			}
		});
		
		fileButton.setBounds(292, 103, 97, 23);
		contentPane.add(fileButton);

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
		
		imageField = new JTextField();
		imageField.setBounds(55, 104, 225, 21);
		contentPane.add(imageField);
		imageField.setColumns(10);

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
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//추가 버튼 클릭
				ProAdmin_form product = new ProAdmin_form();
				product.setPid(pidField.getText());
				product.setPname(pnameField.getText());
				product.setCost(costField.getText());
				product.setFloor(floorField.getText());
				product.setCategory(cateField.getText());
				product.setCid(cidBox.getActionCommand());
				product.setX(xField.getText());
				product.setY(yField.getText());
				product.setURL(URLField.getText());
				product.setImage(imageField.getText());
			}
		});
		addButton.setBounds(81, 201, 97, 34);
		contentPane.add(addButton);

		cancleButton = new JButton("\uCDE8\uC18C");
		cancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//취소 버튼 클릭
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		cancleButton.setBounds(214, 201, 97, 34);
		contentPane.add(cancleButton);
	}

}
