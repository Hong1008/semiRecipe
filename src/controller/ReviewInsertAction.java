package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReviewDAO;
import model.ReviewDTO;

public class ReviewInsertAction {

	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		ReviewDAO dao = new ReviewDAO();
		ReviewDTO dto = new ReviewDTO();
		
		dto.setReview_content(req.getParameter("review_content"));
		dto.setUser_id(req.getParameter("user_id"));
		dto.setRecipe_id(Integer.parseInt(req.getParameter("recipe_id").trim()));
		dto.setReview_subject(req.getParameter("review_subject"));
		dto.setUser_nickname(req.getParameter("user_nickname"));
		
		dao.insertMethod(dto);
		
	}

}//end class
