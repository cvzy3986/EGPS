import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;

public class Add_Screen extends JFrame {

	private JPanel contentPane;
	private JTextField pidField;
	private JTextField pnameField;
	private JTextField floorField;
	private JTextField costField;
	private JTextField cateField;
	private JTextField xField;
	private JTextField yField;
	private JTextField URLField;
	private JLabel lblCid;
	private JLabel lblImage;
	private JLabel lblX;
	private JLabel lblY;
	private JLabel lblUrl;
	private JButton addButton;
	private JButton cancleButton;
	private JTextField imageField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Screen frame = new Add_Screen();
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
	public Add_Screen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 284);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pidField = new JTextField();
		pidField.setEditable(false);
		pidField.setBounds(81, 10, 116, 21);
		contentPane.add(pidField);
		pidField.setColumns(10);
		
		pnameField = new JTextField();
		pnameField.setBounds(273, 10, 116, 21);
		contentPane.add(pnameField);
		pnameField.setColumns(10);
		
		floorField = new JTextField();
		floorField.setBounds(81, 41, 116, 21);
		contentPane.add(floorField);
		floorField.setColumns(10);
		
		costField = new JTextField();
		costField.setBounds(273, 41, 116, 21);
		contentPane.add(costField);
		costField.setColumns(10);
		
		cateField = new JTextField();
		cateField.setBounds(273, 72, 116, 21);
		contentPane.add(cateField);
		cateField.setColumns(10);
		
		JComboBox cidBox = new JComboBox();
		cidBox.setBounds(81, 72, 116, 21);
		contentPane.add(cidBox);
		cidBox.addItem("f");
		cidBox.addItem("란ㄹ");
		cidBox.addItem("gsg");
		cidBox.addItem("1");
		
		JButton fileButton = new JButton("\uD30C\uC77C \uC120\uD0DD");
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();		// 파일 선택기를 사용
				File file;
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
						 file = fc.getSelectedFile();
				else  {
						JOptionPane.showMessageDialog(null, "파일을 선택하세요.", "오류", JOptionPane.ERROR_MESSAGE);
						return;
				}
				InputStream input = null;
				try {
					input = new FileInputStream(file);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(input);
			}
		});
		fileButton.setBounds(273, 103, 97, 23);
		contentPane.add(fileButton);
		
		xField = new JTextField();
		xField.setBounds(81, 133, 90, 21);
		contentPane.add(xField);
		xField.setColumns(10);
		
		yField = new JTextField();
		yField.setBounds(273, 133, 97, 21);
		contentPane.add(yField);
		yField.setColumns(10);
		
		URLField = new JTextField();
		URLField.setBounds(81, 160, 308, 21);
		contentPane.add(URLField);
		URLField.setColumns(10);
		
		JLabel lblPid = new JLabel("pid");
		lblPid.setBounds(12, 13, 57, 15);
		contentPane.add(lblPid);
		
		JLabel lblPanme = new JLabel("pname");
		lblPanme.setBounds(214, 13, 57, 15);
		contentPane.add(lblPanme);
		
		JLabel lblCost = new JLabel("cost");
		lblCost.setBounds(214, 44, 57, 15);
		contentPane.add(lblCost);
		
		JLabel lblFloor = new JLabel("floor");
		lblFloor.setBounds(12, 44, 57, 15);
		contentPane.add(lblFloor);
		
		JLabel lblCategory = new JLabel("category");
		lblCategory.setBounds(209, 75, 57, 15);
		contentPane.add(lblCategory);
		
		lblCid = new JLabel("cid");
		lblCid.setBounds(12, 75, 57, 15);
		contentPane.add(lblCid);
		
		lblImage = new JLabel("image");
		lblImage.setBounds(12, 107, 57, 15);
		contentPane.add(lblImage);
		
		lblX = new JLabel("x");
		lblX.setBounds(12, 132, 57, 15);
		contentPane.add(lblX);
		
		lblY = new JLabel("y");
		lblY.setBounds(214, 136, 57, 15);
		contentPane.add(lblY);
		
		lblUrl = new JLabel("URL");
		lblUrl.setBounds(12, 157, 57, 15);
		contentPane.add(lblUrl);
		
		addButton = new JButton("\uCD94\uAC00");
		addButton.setBounds(81, 201, 97, 34);
		contentPane.add(addButton);
		
		cancleButton = new JButton("\uCDE8\uC18C");
		cancleButton.setBounds(214, 201, 97, 34);
		contentPane.add(cancleButton);
		
		imageField = new JTextField();
		imageField.setEditable(false);
		imageField.setBounds(81, 104, 180, 21);
		contentPane.add(imageField);
		imageField.setColumns(10);
	}
}
