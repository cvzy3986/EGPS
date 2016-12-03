import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.awt.Font;


//@TODO 카테고리 추가, 삭제
public class Category_admin extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */


    
    public Category_admin(Connection conn,JTabbedPane tabbedPane) {
		// TODO Auto-generated constructor stub
    	initComponents(conn);
    	this.tabbedPane= tabbedPane;
	}


	private void initComponents(Connection conn) {
    	try {
			Statement stmt = conn.createStatement();
			 
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	CateAdmin_form cate = new CateAdmin_form();
    	contentPane = new JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setFont(new Font("굴림", Font.BOLD, 40));
        jLabel2 = new javax.swing.JLabel();
        jLabel2.setFont(new Font("굴림", Font.BOLD, 40));
        jLabel3 = new javax.swing.JLabel();
        jLabel3.setFont(new Font("굴림", Font.BOLD, 40));
        Catedel = new javax.swing.JLabel();
        Catedel.setFont(new Font("굴림", Font.BOLD, 40));
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField3.setFont(new Font("굴림", Font.PLAIN, 25));
        CatedelText = new javax.swing.JTextField();
        CatedelText.setFont(new Font("굴림", Font.PLAIN, 25));
        jButton1 = new javax.swing.JButton();
        jButton1.setFont(new Font("굴림", Font.BOLD, 45));
        Del = new javax.swing.JButton();
        Del.setFont(new Font("굴림", Font.BOLD, 45));
        setContentPane(contentPane);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 950));
        getContentPane().setLayout(null);

        jLabel1.setText("Floor");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(12, 371, 342, 79);

        jLabel2.setText("Category ID");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(12, 79, 352, 62);

        jLabel3.setText("Category name");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(12, 192, 352, 140);
        
        
        jTextField3.setText("Cname");
        getContentPane().add(jTextField3);
        jTextField3.setBounds(479, 671, 242, 73);
        

        JComboBox cidBox = new JComboBox();
        cidBox.setFont(new Font("굴림", Font.PLAIN, 45));
		cidBox.setBounds(479, 93, 242, 79);
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
		
		JComboBox floorbox = new JComboBox();
		floorbox.setFont(new Font("굴림", Font.PLAIN, 45));
		floorbox.setBounds(479, 410, 242, 73);
		contentPane.add(floorbox);
		//	cid를 받아와 콤보박스에 추가
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("Select distinct floor from category");
			while (rset.next()) {
				floorbox.addItem(rset.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        jButton1.setText("추 가");       
        getContentPane().add(jButton1);
        jButton1.setBounds(547, 529, 174, 73);
        jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PreparedStatement query;
				try {
			        cate.setFloor(floorbox.getSelectedItem().toString());
			        cate.setCid(cidBox.getSelectedItem().toString());
			        cate.setCname(jTextField3.getText());
			       
		
					query = conn.prepareStatement("INSERT INTO category(cid, cname, floor) VALUES (?, ?, ?)");
					query.setInt(1, Integer.parseInt(cate.getCid()));
					query.setString(2, cate.getCname());
					query.setInt(3, Integer.parseInt(cate.getFloor()));
					
					System.out.println(query);
					int result = query.executeUpdate();				
					AdminFrame.refreshCate(conn);

					JOptionPane.showMessageDialog(null, "추가 되었습니다.");
				} catch (SQLException sqle){
					JOptionPane.showMessageDialog(null, "카테고리 추가 오류.");
				} catch (NumberFormatException nfe){
					JOptionPane.showMessageDialog(null, "cid  floor 숫자입력.");
				}
			}
		});
        
        Catedel.setText("Category name");
        getContentPane().add(Catedel);
        Catedel.setBounds(12, 650, 352, 109);
        
        
        CatedelText.setText("Cname");
        getContentPane().add(CatedelText);
        CatedelText.setBounds(479, 245, 246, 90);
        
        
        Del.setText("삭 제");       
        getContentPane().add(Del);
        Del.setBounds(547, 789, 174, 63);
        Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PreparedStatement query;
				try {
					query = conn.prepareStatement("Delete From category Where cname = ?");
					query.setString(1, jTextField3.getText());
					query.executeUpdate();
					JOptionPane.showMessageDialog(null, "삭제 되었습니다.");
					

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});

        pack();
    }                   

   
    // Variables declaration - do not modify       
	private JPanel contentPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton Del;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel Catedel;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField CatedelText;
    JTabbedPane tabbedPane;
    // End of variables declaration                   
}

