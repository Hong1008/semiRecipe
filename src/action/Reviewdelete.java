package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReviewDAO;

public class Reviewdelete {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		ReviewDAO dao = new ReviewDAO();
		
		System.out.println(req.getParameter("review_num"));
		int review_num = Integer.parseInt(req.getParameter("review_num"));
		dao.deleteMethod(review_num);
	}

}
