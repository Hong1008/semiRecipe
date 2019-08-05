package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PrimDAO;

public class RecipeListAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {	
		String method = req.getMethod();
		PrimDAO dao = new PrimDAO();
		if(method.equalsIgnoreCase("GET")) {
			req.setAttribute("recList", dao.listView());
		}else if(method.equalsIgnoreCase("POST")) {
			String column = req.getParameter("column");
			String order = req.getParameter("order");
			req.setAttribute("recList", dao.sortView(column, order));
		}
	}
}
