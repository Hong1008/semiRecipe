package action;

import javax.servlet.http.HttpServletRequest;

import model.PrimDAO;
import model.SelfRecipeDAO;

public class SelfViewAction {
	public void execute(HttpServletRequest req) {
		int self_num = Integer.parseInt(req.getParameter("self_num"));
		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		req.setAttribute("self_num", dao.primViewMethod(self_num));
		req.setAttribute("self_num", dao.irdntViewMethod(self_num));
		req.setAttribute("self_num", dao.selfRecipeViewMethod(self_num));
		req.setAttribute("self_num", dao.stepViewMethod(self_num));
	}
}// end class

