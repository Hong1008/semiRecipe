package action;

import java.io.IOException;
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
		session.setAttribute("reviewList", aList);
	//	req.setAttribute("aList", aList);
	}
}
