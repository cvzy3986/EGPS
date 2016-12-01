import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

class MenuActionListenerAdmin implements  ActionListener{
	Connection conn;
   MenuActionListenerAdmin(Connection conn) {
      this.conn = conn;
      // TODO Auto-generated constructor stub
   }

   public void actionPerformed(ActionEvent e) {
      try {
         int i = AdminFrame.modelout.getRowCount();
         String pstr = e.getActionCommand(), pname = null;
         //table �� ������ ���� �� ���� ����, ���̺� �̹� �ִ� ��ǰ�̸� �߰�x
         while (i-- != 0) {
            pname = (String) ((Vector) AdminFrame.modelout.getDataVector().elementAt(i)).elementAt(1);
            if (pstr.equals(pname))   return;   //�ߺ��̸� �� ����
         }
         
         //���̺� ��ǰ �߰�
         PreparedStatement menuQuery = conn.prepareStatement(
               "Select pid,pname,cost,floor,category,cid,x,y,url from product where pname like ?");
         menuQuery.setString(1, pstr);
         System.out.println(menuQuery);
         ResultSet rset = menuQuery.executeQuery();
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
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
   }
}