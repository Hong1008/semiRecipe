package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReviewDAO;
import model.ReviewDTO;

public class ReviewListAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ReviewDAO dao = new ReviewDAO();
		List<ReviewDTO> aList = dao.listMethod();
		req.setAttribute("aList", aList);
	}
}
