package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PrimDAO;

public class RecipeListAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		PrimDAO dao = new PrimDAO();
		req.setAttribute("recList", dao.listView());
	}
}
