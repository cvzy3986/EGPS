
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.sql.Connection;
import javax.swing.border.TitledBorder;

public class AdminFrame extends JFrame {
	private JScrollPane scrollPane;
	public static DefaultTableModel modelout = new DefaultTableModel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	static int select = 1;
	/**
	 * Create the frame.
	 */
	public AdminFrame(Connection conn) {
		setBounds(100, 100, 954, 645);
		getContentPane().setLayout(null);

		IsAdminActive adminActive = new IsAdminActive();
		
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
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setFont(new Font("Dialog", Font.PLAIN, 16));
		table.setBounds(0, 0, 1100, 537);
		scrollPane.setViewportView(table);

		JButton addButton = new JButton("\uCD94\uAC00");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 추가 눌렸을 때
				if(!adminActive.isAdd){
					adminActive.isAdd = true;
					Thread addScreen = new AddScreenThread(conn,adminActive);
					addScreen.start();
				}
				
			}
		});
		addButton.setFont(new Font("굴림", Font.PLAIN, 40));
		addButton.setBounds(628, 525, 135, 41);
		getContentPane().add(addButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uC635\uC158", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(327, 459, 282, 120);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton deleteButton = new JButton("\uC0AD\uC81C");
		deleteButton.setBounds(135, 65, 135, 41);
		panel.add(deleteButton);
		
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 삭제 눌렸을 때
						ProductAdmin obj = null;
						if(select == 1) // 물품 1개
							obj = new OnePro_admin();
						else if(select == 2) // 테이블 전체 물품
							obj = new Multi_admin();
						else if(select == 3) //	선택된 물품
							obj = new Spec_admin();
						
						obj.change(table, conn); // delete
					}
				});
		deleteButton.setFont(new Font("굴림", Font.PLAIN, 40));

		JButton modifyButton = new JButton("\uC218\uC815");
		modifyButton.setBounds(135, 17, 135, 41);
		panel.add(modifyButton);
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 수정 눌렸을 때
				ProductAdmin obj = null;
				
				if(select == 1) // 물품 1개
					obj = new OnePro_admin();
				else if(select == 2) // 테이블 전체 물품
					obj = new Multi_admin();
				else if(select == 3) //	선택된 물품
					obj = new Spec_admin();
				
				obj.change(table, conn, true); // modify
			}
		});
		modifyButton.setFont(new Font("굴림", Font.PLAIN, 40));
		
		JRadioButton oneRadio = new JRadioButton("\uC120\uD0DD\uD55C \uBB3C\uD488");
		oneRadio.setBounds(6, 24, 121, 23);
		panel.add(oneRadio);
		buttonGroup.add(oneRadio);
		oneRadio.setSelected(true);
		
		JRadioButton multiRadio = new JRadioButton("\uD14C\uC774\uBE14 \uC804\uCCB4");
		multiRadio.setBounds(6, 54, 121, 23);
		panel.add(multiRadio);
		buttonGroup.add(multiRadio);
		
		JRadioButton specRadio = new JRadioButton("\uC120\uD0DD\uB41C \uBB3C\uD488\uB4E4");
		specRadio.setBounds(6, 84, 121, 23);
		panel.add(specRadio);
		buttonGroup.add(specRadio);
		specRadio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(specRadio.isSelected())	select = 3;
			}
		});
		multiRadio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(multiRadio.isSelected())	select = 2;
			
			}
		});
		oneRadio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(oneRadio.isSelected())	select = 1;
				
			}
		});
		
		JButton Categoryadd = new JButton("카테고리 추가");
		Categoryadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!adminActive.isCate) {
					adminActive.isCate=true;
					Category_admin mana = new Category_admin(conn);
					mana.setBounds(new Rectangle(400, 300));
					mana.setVisible(true);
					mana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					mana.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e){
								adminActive.isCate=false;
						   }
					});
				}
			}
		});

		Categoryadd.setFont(new Font("굴림", Font.PLAIN, 30));
		Categoryadd.setBounds(12, 477, 293, 50);
		getContentPane().add(Categoryadd);
		

		JTextField searchField = new JTextField();
		// 엔터 입력으로 검색
		searchField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// TODO Auto-generated method stub
					String product = searchField.getText();
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

		searchField.setFont(new Font("굴림", Font.PLAIN, 25));
		searchField.setBounds(12, 417, 176, 50);
		getContentPane().add(searchField);
		searchField.setColumns(10);
		searchButton.addActionListener(new SearchButtonActionAdmin(conn, searchField));

		JButton updateButton = new JButton("\uAC31\uC2E0");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 갱신 버튼 클릭 시
				updateTable(conn);
				PopupMenu.setPopMenuToButton(conn);	//팝업 메뉴 갱신
			}
		});
		updateButton.setFont(new Font("굴림", Font.PLAIN, 40));
		updateButton.setBounds(628, 477, 135, 41);
		getContentPane().add(updateButton);
		
		JButton removeButton = new JButton("\uC9C0\uC6B0\uAE30");
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//지우기 버튼 클릭 시 , 선택한 물품 테이블에서 뺀다
				int index = table.getSelectedRow();
				AdminFrame.modelout.removeRow(index);
			}
		});
		removeButton.setFont(new Font("굴림", Font.PLAIN, 33));
		removeButton.setBounds(791, 477, 135, 41);
		getContentPane().add(removeButton);
		JButton cleanButton = new JButton("\uBE44\uC6B0\uAE30");
		cleanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//비우기 버튼 클릭시 , 테이블은 비운다.
				while (AdminFrame.modelout.getRowCount() != 0)
						AdminFrame.modelout.removeRow(0);
			}
		});
		cleanButton.setFont(new Font("굴림", Font.PLAIN, 33));
		cleanButton.setBounds(791, 525, 135, 41);
		getContentPane().add(cleanButton);
		
		JButton xyButton = new JButton("\uC9C0\uB3C4 \uC88C\uD45C\uD655\uC778");
		xyButton.setFont(new Font("굴림", Font.PLAIN, 30));
		xyButton.setBounds(12, 537, 293, 42);
		getContentPane().add(xyButton);
		xyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Check frame = new Check();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		} );

	}
	
	public static void updateTable(Connection conn)
	{
			// table에서 한줄씩 비우고 pid를 큐에 저장 
			Queue update = new LinkedList();	//pid 저장
			while (AdminFrame.modelout.getRowCount() != 0)
			{
				String pid = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(0)).elementAt(0);
				update.offer(pid);
				AdminFrame.modelout.removeRow(0);
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
						AdminFrame.modelout.addRow(row.toArray());
						row.clear();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}
}
class IsAdminActive{
	public boolean isAdd;
	public boolean isCate;
	IsAdminActive(){
		isAdd=false;
		isCate=false;
	}
}