import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Buttons {
	public JButton CategoryButtons[];
	public ArrayList<CategoryData> Categorys;
	public int floorNum;
	
	Buttons(Connection conn){
		HashSet<Integer> floors = new HashSet<>();
		Categorys = new ArrayList<>();
		// Button Open
		try{
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("Select * from category");
			while(rset.next()){
				Categorys.add(new CategoryData(rset.getInt(1),rset.getString(2),rset.getInt(3)));
				floors.add(rset.getInt(3));
			}
		}
		catch(SQLException sqle){
			System.out.println("SQLException : "+sqle);
		}
		int cateSize = Categorys.size();
		
		floorNum = floors.size();
		CategoryButtons = new JButton[cateSize];
		
		PopupMenu popmenus = new PopupMenu(conn);
		for(int i=0; i<cateSize;i++){
			CategoryButtons[i] = new JButton(Categorys.get(i).category);
			CategoryButtons[i].setForeground(Color.YELLOW);
			CategoryButtons[i].setBackground(Color.GRAY);
			CategoryButtons[i].setFont(new Font("±¼¸²", Font.BOLD, 22));
			CategoryButtons[i].addMouseListener(new MouseClickEvent(Categorys.get(i).cid,CategoryButtons[i],popmenus));
		}
	}
}
