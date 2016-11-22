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
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class EGPSAdmin extends JFrame {
	Connection conn;
	private JScrollPane scrollPane;
	public static DefaultTableModel modelout = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EGPSAdmin frame = new EGPSAdmin();
					frame.setBounds(new Rectangle(970, 570));
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
			conn = DriverManager.getConnection("jdbc:mysql://165.229.89.148/mart?autoReconnect=true", "team8",
					"password");
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 954, 573);
		getContentPane().setLayout(null);

		JButton searchButton = new JButton("검 색");
		searchButton.setBounds(188, 417, 117, 50);
		searchButton.setFont(new Font("굴림", Font.BOLD, 27));
		searchButton.setBackground(new Color(0, 0, 0));
		searchButton.setForeground(UIManager.getColor("Desktop.background"));
		getContentPane().add(searchButton);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 10, 293, 397);
		tabbedPane.setFont(new Font("굴림", Font.BOLD, 37));

		Category_Screen screen = new Category_Screen(conn);
		for (int i = 0; i < screen.panels.panelArray.length; i++) {
			int floor = i + 1;
			String floorstr = floor + "층";
			tabbedPane.add(floorstr, screen.panels.panelArray[i]);
		}
		getContentPane().add(tabbedPane);

		scrollPane = new JScrollPane();

		scrollPane.setBounds(317, 10, 609, 439);
		getContentPane().add(scrollPane);

		JTable table;
		String out[] = { "pid", "pname", "cost", "floor", "category", "cid", "x", "y", "URL" };
		modelout.setColumnIdentifiers(out);
		table = new JTable(modelout);
		table.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 16));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(250);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.getColumnModel().getColumn(7).setPreferredWidth(40);
		table.getColumnModel().getColumn(8).setPreferredWidth(300);

		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		table.setRowHeight(40);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Dialog", Font.PLAIN, 16));
		table.setBounds(0, 0, 1100, 537);
		scrollPane.setViewportView(table);

		JButton addButton = new JButton("\uCD94\uAC00");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 추가 눌렸을 때
				// 추가할 물품의 pid를 구한다. pid = max(pid)+1 from product
				try {
						Statement stmt = conn.createStatement();
						ResultSet rset = stmt.executeQuery("select max(pid)+1 addNum from product");
						while (rset.next()) 
							System.out.println("New pid : "+rset.getInt("addNum"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		});
		addButton.setFont(new Font("굴림", Font.PLAIN, 40));
		addButton.setBounds(317, 459, 135, 41);
		getContentPane().add(addButton);

		JButton deleteButton = new JButton("\uC0AD\uC81C");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 삭제 눌렸을 때
				int index = table.getSelectedRow(); // 선택된 테이블 번호 받아온다.
				System.out.println("selected index : " + index);

				// 테이블에서 선택된 행의 pid를 구한다.
				String pid = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(0);
				System.out.println("selected pid : " + pid);

				try {
					// 리스트 내용 전부 삭제
					EGPSAdmin.modelout.removeRow(index);
					// 삭제 쿼리
					PreparedStatement menuQuery = conn.prepareStatement("DELETE from product where pid = ?");
					menuQuery.setString(1, pid);
					System.out.println(menuQuery);
					menuQuery.executeUpdate();
					PopupMenu.setPopMenuToButton(conn);	//팝업 메뉴 갱신
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		deleteButton.setFont(new Font("굴림", Font.PLAIN, 40));
		deleteButton.setBounds(464, 459, 135, 41);
		getContentPane().add(deleteButton);

		JButton modifyButton = new JButton("\uC218\uC815");
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 수정 눌렸을 때
				int index = table.getSelectedRow(); // 선택된 테이블 번호 받아온다.
				// System.out.println("selected index : "+index);

				// 테이블에서 선택된 행의 정보를 받는다.
				String pid = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(0);
				String pname = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(1);
				String cost = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(2);
				String floor = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(3);
				String category = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(4);
				String cid = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(5);
				String x = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(6);
				String y = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(7);
				String URL = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(index)).elementAt(8);
				
				try {
					PreparedStatement menuQuery = conn.prepareStatement(
							"UPDATE product SET pname = ?, cost = ?, floor = ?, category = ?, cid = ?, x = ?, y = ?, URL = ? where pid = ?");

					menuQuery.setString(1, pname);
					menuQuery.setString(2, cost);
					menuQuery.setString(3, floor);
					menuQuery.setString(4, category);
					menuQuery.setString(5, cid);
					menuQuery.setString(6, pid);
					menuQuery.setString(6, x);
					menuQuery.setString(7, y);
					menuQuery.setString(8, URL);
					menuQuery.setString(9, pid);

					System.out.println(menuQuery);
					menuQuery.executeUpdate(); // 삭제 완료 but 리스트엔 그대로 남아있음 수정필요
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}updateTable(conn);
			}
		});
		modifyButton.setFont(new Font("굴림", Font.PLAIN, 40));
		modifyButton.setBounds(611, 459, 135, 41);
		getContentPane().add(modifyButton);

		JTextField searchField = new JTextField();
		// 엔터 입력으로 검색
		searchField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// TODO Auto-generated method stub
					String product = searchField.getText();
					try {
						while (EGPSAdmin.modelout.getRowCount() != 0)
						{
							String pid = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(0)).elementAt(0);
							
							EGPSAdmin.modelout.removeRow(0);
						}
						PreparedStatement query = conn.prepareStatement(
								"Select pid,pname,cost,floor,category,cid,x,y,url from product where pname like ?");
						query.setString(1, "%" + product + "%");
						System.out.println(query);
						ResultSet rset = query.executeQuery();
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
							EGPSAdmin.modelout.addRow(row.toArray());
							row.clear();
						}
					} catch (SQLException sqle) {
						System.out.println("SQLException : " + sqle);
					}
				}
			}
		});

		searchField.setFont(new Font("굴림", Font.PLAIN, 25));
		searchField.setBounds(12, 417, 176, 50);
		getContentPane().add(searchField);
		searchField.setColumns(10);
		searchButton.addActionListener(new SearchButtonAction(conn, searchField));

		JButton resetButton = new JButton("\uAC31\uC2E0");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 갱신 버튼 클릭 시
				updateTable(conn);
			}
		});
		resetButton.setFont(new Font("굴림", Font.PLAIN, 40));
		resetButton.setBounds(774, 459, 152, 41);
		getContentPane().add(resetButton);
	}
	
	public static void updateTable(Connection conn)
	{
			// table에서 한줄씩 비우고 pid를 큐에 저장 
			Queue update = new LinkedList();	//pid 저장
			while (EGPSAdmin.modelout.getRowCount() != 0)
			{
				String pid = (String) ((Vector) EGPSAdmin.modelout.getDataVector().elementAt(0)).elementAt(0);
				update.offer(pid);
				EGPSAdmin.modelout.removeRow(0);
			}
			//큐가 null이 될때까지 pid를 꺼내서 해당하는 물품 정보를 table에 출력
			while(update.peek() != null)
			{
				try 
				{
					PreparedStatement menuQuery = conn.prepareStatement("Select pid,pname,cost,floor,category,cid,x,y,url from product where pid = ?");
					String pid = (String)update.poll();
					menuQuery.setString(1, pid);
					System.out.println(menuQuery);
					ResultSet rset = menuQuery.executeQuery();
					ArrayList<String> row = new ArrayList<>();
					while(rset.next()){
						row.add(rset.getString(1)); //pid
						row.add(rset.getString(2));	//pname
						row.add(rset.getString(3));	//cost
						row.add(rset.getString(4));	//floor
						row.add(rset.getString(5));	//category
						row.add(rset.getString(6));	//cid
						row.add(rset.getString(7));	//x
						row.add(rset.getString(8));	//y
						row.add(rset.getString(9));	//URL
						EGPSAdmin.modelout.addRow(row.toArray());
						row.clear();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}
}



class SearchButtonAction implements ActionListener {
	Connection conn;
	JTextField searchField;

	SearchButtonAction(Connection conn, JTextField searchField) {
		this.conn = conn;
		this.searchField = searchField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String product = searchField.getText();
		try {
			while (EGPSAdmin.modelout.getRowCount() != 0)
				EGPSAdmin.modelout.removeRow(0);
			PreparedStatement query = conn.prepareStatement(
					"Select pid,pname,cost,floor,category,cid,x,y,url from product where pname like ?");
			query.setString(1, "%" + product + "%");
			System.out.println(query);
			ResultSet rset = query.executeQuery();
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
				EGPSAdmin.modelout.addRow(row.toArray());
				row.clear();
			}
		} catch (SQLException sqle) {
			System.out.println("SQLException : " + sqle);
		}
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

class Category_Screen {
	Panels panels;

	Category_Screen(Connection conn) {
		panels = new Panels(conn);
	}
}

class Panels {
	JPanel panelArray[];

	Panels(Connection conn) {
		setButtonToPanel(conn);
	}

	void setButtonToPanel(Connection conn) {
		Buttons Jbuttons = new Buttons(conn);
		panelArray = new JPanel[Jbuttons.floorNum];
		for (int i = 0; i < Jbuttons.floorNum; i++) {
			panelArray[i] = new JPanel();
			panelArray[i].setLayout(new GridLayout(10, 1));
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
