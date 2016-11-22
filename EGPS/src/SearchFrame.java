import java.awt.Font;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

class SearchThread extends Thread{
	JTextField textField;
	Connection conn;
	SearchThread(JTextField textField,Connection conn){
		this.textField= textField;
		this.conn = conn;
	}
	public  void run(){
		   JFrame frame = new SearchFrame(textField,conn);
		   frame.setBounds(new Rectangle(1000, 950));
		   frame.setVisible(true);
		   frame.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent e){
				   frame.setVisible(false);
				   frame.dispose();
				   System.exit(0);
			   }
		});
	   }
}

class SearchFrame extends JFrame{
   private JPanel contentPane;
   private Connection conn;
   private JTextField textField;
   public SearchFrame(JTextField textField,Connection conn) {
	    this.textField= textField;
		this.conn = conn;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 688);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		String out[] = { "사진", "상품명", "가격" };
		DefaultTableModel modelout = new DefaultTableModel(out, 10);
		JLabel lblNewLabel = new JLabel("검색 기록");
		lblNewLabel.setBounds(12, 10, 69, 20);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 41, 950, 800);
		getContentPane().add(scrollPane);
		JTable table;
		table = new JTable(modelout);
		table.getTableHeader().setFont(new Font("Dialog", Font.BOLD, 23));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(305);
		table.getColumnModel().getColumn(1).setPreferredWidth(450);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.setRowHeight(100);
		table.setFont(new Font("Dialog", Font.BOLD, 23));
		table.setBounds(0, 0, 1100, 537);
		scrollPane.setViewportView(table);
		
		
		String product  = textField.getText();
		try{
			while(modelout.getRowCount() != 0)
		    	modelout.removeRow(0);
			PreparedStatement query = conn.prepareStatement("Select pname,cost from product where pname like ?");
			query.setString(1, "%"+product+"%");
			System.out.println(query);
			ResultSet rset = query.executeQuery();
			ArrayList<String> row = new ArrayList<>();
			while(rset.next()){
				row.add(rset.getString(1));
				row.add(Integer.toString(rset.getInt(2)));
				modelout.addRow(row.toArray());
				row.clear();
			}
		}
		catch(SQLException sqle){
			System.out.println("SQLException : "+sqle);
		}
   }
}