import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class EGPSAdmin extends JFrame {
	Connection conn;
	private JScrollPane scrollPane;
	public static DefaultTableModel modelout =  new DefaultTableModel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EGPSAdmin frame = new EGPSAdmin();
					frame.setBounds(new Rectangle(1750, 950));
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
	public EGPSAdmin() {
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
		setBounds(100, 100, 1750, 951);
		getContentPane().setLayout(null);

		JTextField searchField = new JTextField();
		searchField.setFont(new Font("±¼¸²", Font.PLAIN, 25));
		searchField.setBounds(21, 753, 437, 50);
		getContentPane().add(searchField);
		searchField.setColumns(10);

		JButton searchButton = new JButton("°Ë »ö");
		searchButton.setBounds(475, 753, 114, 50);
		searchButton.setFont(new Font("±¼¸²", Font.BOLD, 27));
		searchButton.setBackground(new Color(0, 0, 0));
		searchButton.setForeground(UIManager.getColor("Desktop.background"));
		getContentPane().add(searchButton);
		searchButton.addActionListener(new SearchButtonAction(conn,searchField));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(17, 175, 572, 537);
		tabbedPane.setFont(new Font("±¼¸²", Font.BOLD, 37));
		
		Category_Screen screen = new Category_Screen(conn);
		for(int i=0;i<screen.panels.panelArray.length;i++){
			int floor = i+1;
			String floorstr = floor +"Ãþ";
			tabbedPane.add(floorstr,screen.panels.panelArray[i] );
		}
		getContentPane().add(tabbedPane);
		
		scrollPane = new JScrollPane();
		
		scrollPane.setBounds(606, 29, 1100, 537);
		getContentPane().add(scrollPane);
		
		JTable table;
		String out[] = {"pid", "pname" , "cost","floor","category","cid","location","URL"};
		modelout.setColumnIdentifiers(out);
		table = new JTable(modelout);
		table.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 23));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(1).setPreferredWidth(600);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);
		table.getColumnModel().getColumn(7).setPreferredWidth(1000);
		
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setRowHeight(40);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Dialog", Font.BOLD, 23));
		table.setBounds(0, 0, 1100, 537);
		scrollPane.setViewportView(table);
		
		JButton addButton = new JButton("\uCD94\uAC00");
		addButton.setFont(new Font("±¼¸²", Font.PLAIN, 54));
		addButton.setBounds(634, 683, 329, 132);
		getContentPane().add(addButton);
		
		JButton deleteButton = new JButton("\uC0AD\uC81C");
		deleteButton.setFont(new Font("±¼¸²", Font.PLAIN, 54));
		deleteButton.setBounds(1004, 683, 329, 132);
		getContentPane().add(deleteButton);
		
		JButton modifyButton = new JButton("\uC218\uC815");
		modifyButton.setFont(new Font("±¼¸²", Font.PLAIN, 54));
		modifyButton.setBounds(1369, 683, 329, 132);
		getContentPane().add(modifyButton);
	}
}
class SearchButtonAction implements ActionListener{
	Connection conn;
	JTextField searchField;
	SearchButtonAction(Connection conn,JTextField searchField){
		this.conn = conn;
		this.searchField=searchField;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String product  = searchField.getText();
		try{
			while(EGPSAdmin.modelout.getRowCount() != 0)
		    	EGPSAdmin.modelout.removeRow(0);
			PreparedStatement query = conn.prepareStatement("Select * from product where pname like ?");
			query.setString(1, "%"+product+"%");
			System.out.println(query);
			ResultSet rset = query.executeQuery();
			ArrayList<String> row = new ArrayList<>();
			while(rset.next()){
				row.add(Integer.toString(rset.getInt(1)));
				row.add(rset.getString(2));
				row.add(Integer.toString(rset.getInt(3)));
				row.add(Integer.toString(rset.getInt(4)));
				row.add(rset.getString(5));
				row.add(Integer.toString(rset.getInt(6)));
				row.add(rset.getString(7));
				row.add(rset.getString(9));
				EGPSAdmin.modelout.addRow(row.toArray());
				row.clear();
			}
		}
		catch(SQLException sqle){
			System.out.println("SQLException : "+sqle);
		}
		
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
