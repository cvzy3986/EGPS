
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.management.monitor.StringMonitor;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
public class SearchFrame extends JFrame {
   private JPanel contentPane;
   private JTable tableOut;
   private JScrollPane scrollPaneOut;
   
   public SearchFrame() {
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 780, 688);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      String tag[] = {"상품명", "판매가" , "비고"};
  String goods[][] = {{"사과","1,100원","1개(특대)"},
            {"경북사과","9,800원","4kg 18내외"},
            {"강원 사과즙","10,800원","2kg/팩"},
            {"사과식초","3,880원","15개입 대란"},
            {"농심 사과쥬스","950원","90g"},
            {"말린 사과","3,150원","5입  600g"},
            {"사과 잼","1,100원","1개(특대)"},
            {"애플잼쿠키(사과맛)","5,480원","1000g"},
      };
      DefaultTableModel modelout =  new DefaultTableModel(goods, tag);
      tableOut = new JTable(new DefaultTableModel(
      	new Object[][] {
      		{"\uC0AC\uACFC", "1,100\uC6D0", "1\uAC1C(\uD2B9\uB300)"},
      		{"\uACBD\uBD81\uC0AC\uACFC", "9,800\uC6D0", "4kg 18\uB0B4\uC678"},
      		{"\uAC15\uC6D0 \uC0AC\uACFC\uC999", "10,800\uC6D0", "2kg/\uD329"},
      		{"\uC0AC\uACFC\uC2DD\uCD08", "3,880\uC6D0", "15\uAC1C\uC785 \uB300\uB780"},
      		{"\uB18D\uC2EC \uC0AC\uACFC\uC96C\uC2A4", "950\uC6D0", "90g"},
      		{"\uB9D0\uB9B0 \uC0AC\uACFC", "3,150\uC6D0", "5\uC785  600g"},
      		{"\uC0AC\uACFC \uC7BC", "1,100\uC6D0", "1\uAC1C(\uD2B9\uB300)"},
      		{"\uC560\uD50C\uC7BC\uCFE0\uD0A4(\uC0AC\uACFC\uB9DB)", "5,480\uC6D0", "1000g"},
      	},
      	new String[] {
      		"\uC0C1\uD488\uBA85", "\uD310\uB9E4\uAC00", "\uBE44\uACE0"
      	}
      ));
      tableOut.setColumnSelectionAllowed(true);
      tableOut.setFont(new Font("굴림", Font.PLAIN, 13));
      tableOut.setBounds(81, 337, 660, 215);
      
     
      
      scrollPaneOut = new JScrollPane(tableOut);
      scrollPaneOut.setBounds(22, 40, 700, 600);
      contentPane.add(scrollPaneOut);
      
      JLabel lblNewLabel = new JLabel("검색 기록");
      lblNewLabel.setBounds(12, 10, 69, 20);
      contentPane.add(lblNewLabel);
   }
}
