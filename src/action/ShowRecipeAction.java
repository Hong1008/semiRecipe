package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.IrdntDAO;
import model.PrimDAO;
import model.StepDAO;

public class ShowRecipeAction {

	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		PrimDAO pdao = new PrimDAO();
		StepDAO sdao = new StepDAO();
		IrdntDAO idao = new IrdntDAO();
		int recipe_id = Integer.parseInt(req.getParameter("recipe_id"));
		pdao.primViewsCnt(recipe_id);
		req.setAttribute("prim", pdao.listView(recipe_id));
		req.setAttribute("step", sdao.listView(recipe_id));
		req.setAttribute("irdntMain", idao.mainList(recipe_id));
		req.setAttribute("irdntSub", idao.subList(recipe_id));
		idao.exit();
	}

}
