import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;

public class EGPS extends JFrame {
	public static Product PRODUCT= new  Product();
	public static JTextPane textPname = new JTextPane();
	public static JTextPane textCost = new JTextPane();
	public static ProductDrawImage panel = new ProductDrawImage();
	Connection conn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EGPS frame = new EGPS();
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
	public EGPS() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://165.229.89.148/mart?autoReconnect=true","team8", "password");
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1468, 950);
		getContentPane().setLayout(null);

		GetFloorImage floorImage = new GetFloorImage(); //·Î°í ÀÌ¹ÌÁö »ý¼º

		JLabel floorMap = new JLabel(floorImage.F1);
		floorMap.setBounds(438, 350, 1000, 500);
		getContentPane().add(floorMap);

		JLabel logo = new JLabel(new GetlogoImage().logo);
		logo.setBounds(17, 30, 441, 130);
		getContentPane().add(logo);
		JTextField textField = new JTextField();
		textField.setBounds(21, 753, 246, 50);
		getContentPane().add(textField);
		textField.setColumns(10);

		JButton searchButton = new JButton("°Ë »ö");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		searchButton.setFont(new Font("±¼¸²", Font.BOLD, 27));
		searchButton.setBackground(new Color(0, 0, 0));
		searchButton.setForeground(UIManager.getColor("Desktop.background"));
		searchButton.setBounds(296, 753, 114, 50);
		getContentPane().add(searchButton);
//		searchButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JFrame serchFrame = new SearchFrame();
//				serchFrame.setVisible(true);
//				serchFrame.setSize(1400, 800);
//				serchFrame.addWindowListener(new WindowAdapter() {
//					public void windowClosing(WindowEvent e){
//						serchFrame.dispose();
//					}
//				});
//			}
//		}
		
		
				
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("±¼¸²", Font.BOLD, 37));
		tabbedPane.setBounds(17, 175, 334, 537);
		
		Category_Screen screen = new Category_Screen(conn);
		for(int i=0;i<screen.panels.panelArray.length;i++){
			int floor = i+1;
			String floorstr = floor +"Ãþ";
			tabbedPane.add(floorstr,screen.panels.panelArray[i] );
		}
		getContentPane().add(tabbedPane);
		
		JPanel infoScreen = new JPanel();
		infoScreen.setBackground(Color.ORANGE);
		infoScreen.setBounds(438, 30, 1000, 276);
		getContentPane().add(infoScreen);
		infoScreen.setLayout(null);
		
		
		textPname.setFont(new Font("±¼¸²", Font.PLAIN, 28));
		textPname.setBounds(440, 26, 543, 97);
		infoScreen.add(textPname);
		
		
		textCost.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		textCost.setBounds(667, 201, 300, 61);
		infoScreen.add(textCost);
		
		JLabel lblNewLabel = new JLabel("\uC0C1\uD488 \uC774\uB984: ");
		lblNewLabel.setFont(new Font("±¼¸²",Font.BOLD, 27));
		lblNewLabel.setBounds(303, 26, 165, 61);
		infoScreen.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uAC00\uACA9: ");
		lblNewLabel_1.setFont(new Font("±¼¸²", Font.BOLD, 27));
		lblNewLabel_1.setBounds(595, 200, 144, 61);
		infoScreen.add(lblNewLabel_1);
		
		
		panel.setBounds(0, 0, 300, 276);
		infoScreen.add(panel);
		
		
	}
}

//class SearchButtonActionUser implements ActionListener{
//	Connection conn;
//	JTextField searchField;
//	SearchButtonActionUser(Connection conn,JTextField searchField){
//		this.conn = conn;
//		this.searchField=searchField;
//	}
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		String product  = searchField.getText();
//		try{
////			while(EGPSAdmin.modelout.getRowCount() != 0)
////		    	EGPSAdmin.modelout.removeRow(0);
////			PreparedStatement query = conn.prepareStatement("Select * from product where pname like ?");
////			query.setString(1, "%"+product+"%");
////			System.out.println(query);
////			ResultSet rset = query.executeQuery();
////			ArrayList<String> row = new ArrayList<>();
////			while(rset.next()){
////				row.add(Integer.toString(rset.getInt(1)));
////				row.add(rset.getString(2));
////				row.add(Integer.toString(rset.getInt(3)));
////				row.add(Integer.toString(rset.getInt(4)));
////				row.add(rset.getString(5));
////				row.add(Integer.toString(rset.getInt(6)));
////				row.add(rset.getString(7));
////				row.add(rset.getString(9));
////				EGPSAdmin.modelout.addRow(row.toArray());
////				row.clear();
//			}
//		}
//		catch(SQLException sqle){
//			System.out.println("SQLException : "+sqle);
//		}
//		
//	}
//	
//}
class ProductDrawImage extends JPanel{
	public void paint(Graphics g){
        g.drawImage(EGPS.PRODUCT.pimage,0,0,this);
	}
	ProductDrawImage(){
		addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	        	 java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
	        	 java.net.URI uri;
				try {
					uri = new java.net.URI(EGPS.PRODUCT.URL);
					desktop.browse(uri);
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch (IOException ioe){
					ioe.printStackTrace();
				}
	        }
//	        @Override
//	        public void mouseReleased(MouseEvent e) {
//	        	 System.out.println("¸¶¿ì½º ºüÁú¶§");
//	        }
	    });
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
			conn = DriverManager.getConnection("jdbc:mysql://165.229.89.148/mart?autoReconnect=true","team8", "password");
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
		}
	}
}
class Category_Screen{
	Panels panels;
	Category_Screen(Connection conn){
		panels = new Panels(conn);
	}
}
class Panels{
	JPanel panelArray[];
	Panels(Connection conn){
		setButtonToPanel(conn);
	}
	void setButtonToPanel(Connection conn){
		Buttons Jbuttons= new Buttons(conn);
		panelArray = new JPanel[Jbuttons.floorNum];
		for(int i=0;i<Jbuttons.floorNum;i++){
			panelArray[i]  = new JPanel();
			panelArray[i].setLayout(new GridLayout(10,1));
		}
		for (int j = 0; j < Jbuttons.Categorys.size(); j++) {
			if (Jbuttons.Categorys.get(j).floor == 1) {
				panelArray[0].add(Jbuttons.CategoryButtons[j]);
			} else if (Jbuttons.Categorys.get(j).floor == 2) {
				panelArray[1].add(Jbuttons.CategoryButtons[j]);
			} else if (Jbuttons.Categorys.get(j).floor == 3) {
				panelArray[2].add(Jbuttons.CategoryButtons[j]);
			}
			
		}
	}
	
}
class GetFloorImage {
	ImageIcon F1 = null;

	GetFloorImage() {
		File sourceImage = new File("1F.jpg");
		try {
			Image temp = ImageIO.read(sourceImage);
			Image temp2 = temp.getScaledInstance(1000, 500, Image.SCALE_SMOOTH);
			F1 = new ImageIcon(temp2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
class GetlogoImage {
	ImageIcon logo = null;
	GetlogoImage() {
		File sourceImage = new File("log.png");
		try {
			Image temp = ImageIO.read(sourceImage);
			Image temp2 = temp.getScaledInstance(441, 130, Image.SCALE_SMOOTH);
			logo = new ImageIcon(temp2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


