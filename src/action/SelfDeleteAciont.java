
package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SelfRecipeDAO;



public class SelfDeleteAciont {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {		
		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		dao.primDeleteMethod(Integer.parseInt(req.getParameter("recipe_id")));
		dao.selfRecipeDeleteMethod(Integer.parseInt(req.getParameter("recipe_id")));
		dao.irdntDeleteMethod(Integer.parseInt(req.getParameter("recipe_id")));
		dao.stepDeleteMethod(Integer.parseInt(req.getParameter("recipe_id")));
	}
}// end class
