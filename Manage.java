import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Manage extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
//    public Manage(Connection conn) {
//        initComponents(conn);
//    }
    
    public Manage() {
        initComponents();
    }
    
    
    	private void initComponents() {
//    private void initComponents(Connection conn) {
//    	try {
//			Statement stmt = conn.createStatement();
//			 
//			   
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

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
        String floor = jTextField1.getText();

        jTextField2.setText("Cid");      
        getContentPane().add(jTextField2);
        jTextField2.setBounds(130, 30, 190, 21);
        String cid = jTextField2.getText();

        jTextField3.setText("Cname");
        getContentPane().add(jTextField3);
        jTextField3.setBounds(130, 60, 190, 21);
        String cname = jTextField3.getText();

        jButton1.setText("�� ��");       
        getContentPane().add(jButton1);
        jButton1.setBounds(240, 140, 100, 23);
        jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PreparedStatement query = conn.prepareStatement("insert into Category values(?, ?, ?");
				
				query.setString(1, cid);
				query.setString(2, cname);
				query.setString(3, floor);
				query.executeQuery();

				JOptionPane.showMessageDialog(null, "�߰� �Ǿ����ϴ�.");
			}
		});
        
        Catedel.setText("Category name");
        getContentPane().add(Catedel);
        Catedel.setBounds(20, 200, 110, 15);
        
        
        CatedelText.setText("Cname");
        getContentPane().add(CatedelText);
        CatedelText.setBounds(130, 200, 190, 21);
        String cnamedel = CatedelText.getText();
        
        Del.setText("�� ��");       
        getContentPane().add(Del);
        Del.setBounds(240, 250, 100, 23);
        Del.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PreparedStatement query = conn.prepareStatement("Delete From Category Where cname = '?'");
				
				
				query.setString(1, cname);
				query.executeQuery();

				JOptionPane.showMessageDialog(null, "���� �Ǿ����ϴ�.");
			}
		});

        pack();
    }                   

   
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage().setVisible(true);
            }
        });
    }

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
