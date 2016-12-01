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

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


//@TODO 카테고리 추가, 삭제
public class Category_admin extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */


    
    public Category_admin(Connection conn) {
		// TODO Auto-generated constructor stub
    	initComponents(conn);
	}


	private void initComponents(Connection conn) {
    	try {
			Statement stmt = conn.createStatement();
			 
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	CateAdmin_form cate = new CateAdmin_form();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Catedel = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        CatedelText = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        Del = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 380));
        getContentPane().setLayout(null);

        jLabel1.setText("Floor");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 90, 110, 15);

        jLabel2.setText("Category ID");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 30, 110, 15);

        jLabel3.setText("Category name");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 60, 110, 15);

        jTextField1.setText("Floor");
        getContentPane().add(jTextField1);
        jTextField1.setBounds(130, 90, 190, 21);
        
        
        jTextField2.setText("Cid");      
        getContentPane().add(jTextField2);
        jTextField2.setBounds(130, 30, 190, 21);
        
        
        jTextField3.setText("Cname");
        getContentPane().add(jTextField3);
        jTextField3.setBounds(130, 60, 190, 21);
        


        jButton1.setText("추 가");       
        getContentPane().add(jButton1);
        jButton1.setBounds(240, 140, 100, 23);
        jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PreparedStatement query;
				try {
			        cate.setFloor(jTextField1.getText());
			        cate.setCid(jTextField2.getText());
			        cate.setCname(jTextField3.getText());
		
					query = conn.prepareStatement("INSERT INTO category(cid, cname, floor) VALUES (?, ?, ?)");
					query.setInt(1, Integer.parseInt(cate.getCid()));
					query.setString(2, cate.getCname());
					query.setInt(3, Integer.parseInt(cate.getFloor()));
					
					System.out.println(query);
					int result = query.executeUpdate();				
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
        Catedel.setBounds(20, 200, 110, 15);
        
        
        CatedelText.setText("Cname");
        getContentPane().add(CatedelText);
        CatedelText.setBounds(130, 200, 190, 21);
        
        
        Del.setText("삭 제");       
        getContentPane().add(Del);
        Del.setBounds(240, 250, 100, 23);
        Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PreparedStatement query;
				try {
					query = conn.prepareStatement("Delete From category Where cname = ?");
					query.setString(1, CatedelText.getText());
					query.executeUpdate();
					JOptionPane.showMessageDialog(null, "삭제 되었습니다.");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});

      
    }                   

   
//    public static void main(String args[]) {
//       
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//            //@exception 예외처리
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Category_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Category_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Category_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Category_admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//            	Connection conn;
//				try {
//					conn = DriverManager.getConnection("jdbc:mysql://165.229.89.148/mart?autoReconnect=true","team8", "password");
//					new Category_admin(conn).setVisible(true);
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//                
//            }
//        });
//    }

    // Variables declaration - do not modify                     
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
    // End of variables declaration                   
}

