import java.sql.Connection;

class Category_Screen {
	Panels panels;

	Category_Screen(Connection conn) {
		panels = new Panels(conn);
	}
}