package action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import model.ReviewDAO;
import model.ReviewDTO;

public class ReviewListAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ReviewDAO dao = new ReviewDAO();
		List<ReviewDTO> aList = dao.listMethod();
		
		HttpSession session = req.getSession();
		session.removeAttribute("reviewList");
		session.setAttribute("reviewList", aList);
		
		
		int start = 0; 
		int finish = 0;
		
		List<String> imgList = new ArrayList<String>();
		
		for(int i=0; i<aList.size(); i++) {
			String a = aList.get(i).getReview_content();
			start = a.indexOf("src=\"");
			finish = a.indexOf(" title");
			
			if(start>-1) {
			//	System.out.println(a.substring(start+5,finish-1));
				imgList.add(a.substring(start+5,finish-1));
			}else {
				imgList.add("http://imgnews.naver.net/image/052/2017/12/18/201712181011528976_d_20171218101235511.jpg");
			}
		}
		
		session.setAttribute("imgList", imgList);
				
	//	req.setAttribute("aList", aList);
	}
}
