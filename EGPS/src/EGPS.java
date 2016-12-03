import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

/**
 * 
 * @author user
 *
 */
public class EGPS extends JFrame {

	public static Product PRODUCT = new Product();
	public static JTextPane textPname = new JTextPane();
	public static JTextPane textCost = new JTextPane();
	public static ProductDrawImage panel = new ProductDrawImage();
	public static boolean isAdmin;
	public static Connection conn;
	public static MapImage mapImage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				/**
				 * @brief 메인 프레임 실행
				 */
				try {
					EGPS frame = new EGPS(true);
					frame.setBounds(new Rectangle(1468, 950));
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
	public EGPS(boolean isDBaccess) {
		getContentPane().setBackground(new Color(219,208,186));
		isAdmin = false;
		IsSearchActive SearchActive = new IsSearchActive();
		IsAdminActive adminActive = new IsAdminActive();
		SearchActive.isActive = false;

		if (isDBaccess) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection("jdbc:mysql://165.229.89.148/mart?autoReconnect=true", "team8",
						"password");
			} catch (SQLException sqex) {
				System.out.println("SQLException: " + sqex.getMessage());
			}
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1468, 950);
		getContentPane().setLayout(null);
		ArrayList<Image> arrFloor = new ArrayList<>();
		getFloorImage(arrFloor, conn);

		mapImage = new MapImage();
		mapImage.setBounds(438, 376, 1000, 490);
		getContentPane().add(mapImage);

		JLabel logo = new JLabel(new GetlogoImage(conn).logo);
		logo.setBounds(17, 30, 441, 130);
		getContentPane().add(logo);

		JTextField textField = new JTextField();
		textField.setBounds(21, 753, 275, 50);
		getContentPane().add(textField);
		textField.setColumns(10);
		// 엔터로 검색
		textField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// TODO Auto-generated method stub
					String product = textField.getText();
					if(product.length() == 0) {	//검색어가 없을 때
						JOptionPane.showMessageDialog(null, "검색어를 입력해주세요.");
						return;
					}
					try {
						while (AdminFrame.modelout.getRowCount() != 0)
						{
							String pid = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(0)).elementAt(0);
							
							AdminFrame.modelout.removeRow(0);
						}
						PreparedStatement query = conn.prepareStatement(
								"Select pid,pname,cost,floor,category,cid,x,y,url from product where pname like ?");
						query.setString(1, "%" + product + "%");
						System.out.println(query);
						ResultSet rset = query.executeQuery();
						if(!rset.next()){	//검색 결과가 없을때
							JOptionPane.showMessageDialog(null, "검색 결과가 존재하지 않습니다.");
							return;
						}
						rset = query.executeQuery();
						ArrayList<String> row = new ArrayList<>();
						while (rset.next()) {
							row.add(rset.getString(1)); // pid
							row.add(rset.getString(2)); // pname
							row.add(rset.getString(3)); // cost
							row.add(rset.getString(4)); // floor
							row.add(rset.getString(5)); // category
							row.add(rset.getString(6)); // cid
							row.add(rset.getString(7)); // x
							row.add(rset.getString(8)); // y
							row.add(rset.getString(9)); // URL
							AdminFrame.modelout.addRow(row.toArray());
							row.clear();
						}
					} catch (SQLException sqle) {
						System.out.println("SQLException : " + sqle);
					}
				}
			}
		});

		JButton searchButton = new JButton("검 색");
		searchButton.setFont(new Font("굴림", Font.BOLD, 27));
		searchButton.setBackground(new Color(0, 0, 0));
		searchButton.setForeground(UIManager.getColor("Desktop.background"));
		searchButton.setBounds(296, 753, 114, 50);
		getContentPane().add(searchButton);
		searchButton.addActionListener(new SearchButtonActionListener(textField, conn, mapImage, SearchActive));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP); // tabbedPane이 바뀔 때마다 지도 바뀜
		tabbedPane.setBackground(new Color(228,211,197));
		tabbedPane.addChangeListener(new TabChangeListener(mapImage, arrFloor));
		tabbedPane.setFont(new Font("HY엽서M", Font.BOLD, 32));
//		tabbedPane.setForeground(new Color(255,255,255));
		tabbedPane.setBounds(17, 175, 334, 537);

		Category_Screen screen = new Category_Screen(conn);
		for (int i = 0; i < screen.panels.panelArray.length; i++) {
			int floor = i + 1;
			String floorstr = floor + "층";
			tabbedPane.add(floorstr, screen.panels.panelArray[i]);
		}
		getContentPane().add(tabbedPane);

		JPanel infoScreen = new JPanel();
		infoScreen.setBorder(new LineBorder(Color.WHITE, 5, true));
		infoScreen.setBackground(new Color(219, 208, 186));
		infoScreen.setBounds(438, 30, 1000, 280);
		getContentPane().add(infoScreen);
		infoScreen.setLayout(null);
		textPname.setBackground(new Color(219,208,186));
		textPname.setEditable(false);

		textPname.setFont(new Font("양재인장체M", Font.BOLD, 28));
		textPname.setBounds(457, 31, 511, 97);
//		textPname.setBorder(new LineBorder(Color.WHITE, 2, true));
		infoScreen.add(textPname);
		textCost.setBackground(new Color(219,208,186));
		textCost.setEditable(false);

		textCost.setFont(new Font("양재인장체M", Font.BOLD, 30));
		textCost.setBounds(668, 200, 300, 61);
//		textCost.setBorder(new LineBorder(Color.WHITE, 2, true));
		infoScreen.add(textCost);

		JLabel lblNewLabel = new JLabel("\uC0C1\uD488 \uC774\uB984: ");
		lblNewLabel.setFont(new Font("양재인장체M", Font.BOLD, 29));
		lblNewLabel.setBounds(303, 26, 165, 61);
		infoScreen.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uAC00\uACA9: ");
		lblNewLabel_1.setFont(new Font("양재인장체M", Font.BOLD, 30));
		lblNewLabel_1.setBounds(595, 200, 144, 61);
		infoScreen.add(lblNewLabel_1);
		panel.setBorder(new LineBorder(Color.WHITE, 5));
		panel.setBackground(new Color(119, 136, 153));

		panel.setBounds(5, 5, 293, 272);
		infoScreen.add(panel);

		JButton adminButton = new JButton("관리자");
		adminButton.setFont(new Font("굴림", Font.PLAIN, 30));
		adminButton.setBackground(new Color(85,75,75));
		adminButton.setForeground(new Color(255,255,255));		
		adminButton.setBounds(17, 818, 393, 62);
		getContentPane().add(adminButton);
		adminButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JPasswordField resultStr = new JPasswordField();
				JOptionPane.showMessageDialog(null, resultStr, "비밀번호", JOptionPane.QUESTION_MESSAGE);
				if (resultStr.getText().equals("123")) {
					EGPS.isAdmin = true;
					Thread adminThread = new AdminThread(conn);
					adminThread.start();
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호 오류", "오류", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public static void getFloorImage(ArrayList<Image> arrFloor, Connection conn) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("Select Mapimage from floor");
			while (rset.next()) {
				arrFloor.add(returnImage(rset.getBlob(1)));
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Image returnImage(Blob data) throws Exception {
		Image temp = ImageIO.read(data.getBinaryStream());
		Image temp2 = temp.getScaledInstance(1000, 490, Image.SCALE_SMOOTH);
		return temp2;
	}
}

class DBConnect {

	static void DBConnectFunc(Connection conn) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://165.229.89.148/mart?autoReconnect=true", "team8",
					"password");
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
		}
	}
}

class IsSearchActive {
	public boolean isActive;
}
