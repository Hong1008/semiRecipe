package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.IrdntDAO;
import model.PrimDAO;
import model.PrimDTO;
import model.StepDAO;

public class ShowRecipeAction {

	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		PrimDAO pdao = new PrimDAO();
		StepDAO sdao = new StepDAO();
		IrdntDAO idao = new IrdntDAO();
		int recipe_id = Integer.parseInt(req.getParameter("recipe_id"));
		pdao.primViewsCnt(recipe_id);
		PrimDTO pdto = pdao.listView(recipe_id);
		req.setAttribute("prim", pdto);
		req.setAttribute("step", sdao.listView(recipe_id));
		req.setAttribute("irdntMain", idao.mainList(recipe_id));
		req.setAttribute("irdntSub", idao.subList(recipe_id));
		int hour=0;
		int minute=0;
		String zero="";
		hour = Integer.parseInt(pdto.getCOOKING_TIME())/60;
		minute = Integer.parseInt(pdto.getCOOKING_TIME())%60;
		if(minute<10)
			zero = "0";
		req.setAttribute("hour", hour);
		req.setAttribute("minute", zero+minute);
		
		idao.exit();
	}

}
