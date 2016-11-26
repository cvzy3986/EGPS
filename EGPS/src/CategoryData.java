/**
 * @brief 카테고리 버튼에 들어갈 데이터
 * @details  카테고리 별 데이터(카테고리 id , 카테고리명, 층)으
 * @author user
 *
 */
public class CategoryData{
	    int cid;
		String category;
	    int floor;
	    CategoryData(int cid,String category,int floor){
	    	this.cid = cid;
	    	this.category=category;
	    	this.floor=floor;
	    }
}