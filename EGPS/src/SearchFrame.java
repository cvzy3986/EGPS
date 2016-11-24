import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


class SearchFrame extends JFrame{
   private JPanel contentPane;
   private Connection conn;
   private JTextField textField;
   private MapImage mapImage;
   public SearchFrame(JTextField textField,Connection conn,MapImage mapImage) {
	   
	    this.textField= textField;
		this.conn = conn;
		this.mapImage = mapImage;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 688);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    ArrayList<Image> arrFloor = new ArrayList<>();
	    EGPS.getFloorImage(arrFloor,conn);
		
		String out[] = { "상품명", "가격"  };
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
		table.getColumnModel().getColumn(0).setPreferredWidth(805);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		
		table.setRowHeight(100);
		table.setFont(new Font("Dialog", Font.BOLD, 23));
		table.setBounds(0, 0, 1100, 537);
		scrollPane.setViewportView(table);
		
		
		setTableProduct(conn,modelout,textField.getText());
		
		ListSelectionModel model = table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				/**
				 * @
				 */
				// TODO Auto-generated method stub
			    System.out.println(table.getModel().getValueAt(model.getMinSelectionIndex(), 0));
			    try {
					PreparedStatement menuQuery = conn.prepareStatement("Select pname,cost,image,URL,x,y,floor from product where pname = ?");
					menuQuery.setString(1, table.getModel().getValueAt(model.getMinSelectionIndex(), 0).toString());
					System.out.println(menuQuery);
					ResultSet rset = menuQuery.executeQuery();
					while(rset.next()){
						EGPS.PRODUCT.setPname(rset.getString(1));
						EGPS.PRODUCT.setCost(rset.getInt(2));
						EGPS.PRODUCT.setPimage(ReturnProductImage.returnImage(rset.getBlob(3)));
						EGPS.textPname.setText(EGPS.PRODUCT.pname);
						EGPS.textCost.setText(Integer.toString((EGPS.PRODUCT.cost)));
						EGPS.PRODUCT.setURL(rset.getString(4));
					    EGPS.PRODUCT.setX(rset.getInt(5));
					    EGPS.PRODUCT.setY(rset.getInt(6));
						EGPS.panel.repaint();
						int index = rset.getInt(7)-1;
						mapImage.setImage(arrFloor.get(index));
						mapImage.repaint();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
   }
   public static void setTableProduct(Connection conn, DefaultTableModel modelout, String product){
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

