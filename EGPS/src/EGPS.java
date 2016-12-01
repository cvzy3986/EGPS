import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JDesktopPane;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
/**
 * 
 * @author user
 *
 */
public class EGPS extends JFrame {
	
	public static Product PRODUCT= new  Product(); 
	public static JTextPane textPname = new JTextPane();
	public static JTextPane textCost = new JTextPane();
	public static ProductDrawImage panel = new ProductDrawImage();
	public static boolean isAdmin;
	public static Connection conn;
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
		isAdmin = false;
		IsSearchActive SearchActive = new IsSearchActive();
		IsAdminActive adminActive = new IsAdminActive();
		SearchActive.isActive = false;
		
		if(isDBaccess){
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
		getFloorImage(arrFloor,conn);

		MapImage mapImage = new MapImage();
		mapImage.setBounds(438, 376, 1000, 490);
		getContentPane().add(mapImage);
		
	

		JLabel logo = new JLabel(new GetlogoImage(conn).logo);
		logo.setBounds(17, 30, 441, 130);
		getContentPane().add(logo);
		
		JTextField textField = new JTextField();
		textField.setBounds(21, 753, 246, 50);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton searchButton = new JButton("검 색");
		searchButton.setFont(new Font("굴림", Font.BOLD, 27));
		searchButton.setBackground(new Color(0, 0, 0));
		searchButton.setForeground(UIManager.getColor("Desktop.background"));
		searchButton.setBounds(296, 753, 114, 50);
		getContentPane().add(searchButton);
		searchButton.addActionListener(new SearchButtonActionListener(textField,conn,mapImage,SearchActive));
		
				
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);  //tabbedPane이 바뀔 때마다 지도 바뀜
		tabbedPane.addChangeListener(new TabChangeListener(mapImage,arrFloor));
		tabbedPane.setFont(new Font("굴림", Font.BOLD, 37));
		tabbedPane.setBounds(17, 175, 334, 537);
		
		
		Category_Screen screen = new Category_Screen(conn);
		for(int i=0;i<screen.panels.panelArray.length;i++){
			int floor = i+1;
			String floorstr = floor +"층";
			tabbedPane.add(floorstr,screen.panels.panelArray[i] );
		}
		getContentPane().add(tabbedPane);
		
		JPanel infoScreen = new JPanel();
		infoScreen.setBackground(Color.ORANGE);
		infoScreen.setBounds(438, 30, 1000, 276);
		getContentPane().add(infoScreen);
		infoScreen.setLayout(null);
		textPname.setEditable(false);
		
		
		textPname.setFont(new Font("굴림", Font.PLAIN, 28));
		textPname.setBounds(440, 26, 543, 97);
		infoScreen.add(textPname);
		textCost.setEditable(false);
		
		
		textCost.setFont(new Font("굴림", Font.PLAIN, 30));
		textCost.setBounds(667, 201, 300, 61);
		infoScreen.add(textCost);
		
		JLabel lblNewLabel = new JLabel("\uC0C1\uD488 \uC774\uB984: ");
		lblNewLabel.setFont(new Font("굴림",Font.BOLD, 27));
		lblNewLabel.setBounds(303, 26, 165, 61);
		infoScreen.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uAC00\uACA9: ");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 27));
		lblNewLabel_1.setBounds(595, 200, 144, 61);
		infoScreen.add(lblNewLabel_1);
		
		
		panel.setBounds(0, 0, 300, 276);
		infoScreen.add(panel);	
		
		JButton adminButton = new JButton("관리자");
		adminButton.setFont(new Font("굴림", Font.PLAIN, 30));
		adminButton.setBounds(17, 818, 393, 62);
		getContentPane().add(adminButton);
		adminButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String resultStr = null;
				resultStr = JOptionPane.showInputDialog("비밀번호 입력하세요.");
				
				adminActive.isActive = true;
				if (resultStr.equals("123")) {
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
	/**
	 * 
	 * @param arrFloor
	 * @param conn
	 */
	public static void getFloorImage(ArrayList<Image> arrFloor,Connection conn){
		try {
			Statement stmt = conn.createStatement(); 
			ResultSet rset = stmt.executeQuery("Select Mapimage from floor");
			while(rset.next()){
				arrFloor.add(returnImage(rset.getBlob(1)));
			}
		} catch (SQLException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	public static Image returnImage(Blob data) throws Exception{
		Image temp = ImageIO.read(data.getBinaryStream());
		Image temp2 = temp.getScaledInstance(1000, 490, Image.SCALE_SMOOTH);
		return temp2;
	}

}


class DBConnect{
	
	static void DBConnectFunc(Connection conn){
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
class IsSearchActive{
	public boolean isActive;
}
class IsAdminActive{
	public boolean isActive;
}