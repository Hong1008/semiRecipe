package action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PrimDTO;
import model.SelfRecipeDAO;

public class SelfViewAction {
	public void execute(HttpServletRequest req,  HttpServletResponse resp) {
		System.out.println(req.getParameter("recipe_id"));
		int recipe_id = Integer.parseInt(req.getParameter("recipe_id"));
		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		req.setAttribute("prdto", dao.primViewMethod(recipe_id));
		req.setAttribute("irList", dao.irdntViewMethod(recipe_id));
		req.setAttribute("srdto", dao.selfRecipeViewMethod(recipe_id));
		req.setAttribute("stList", dao.stepViewMethod(recipe_id));
		PrimDTO prdto = dao.primViewMethod(recipe_id);
		System.out.println(dao.selfRecipeViewMethod(recipe_id).getSelf_date());
		int hour = 0;
		int miute = 0;
		String zero = "";
		hour = Integer.parseInt(prdto.getCOOKING_TIME()) / 60;
		miute = Integer.parseInt(prdto.getCOOKING_TIME()) % 60;
		if (miute < 10) {
			zero = "0";
		}
		req.setAttribute("hour", hour);
		req.setAttribute("miute", miute);
		try {
			dao.exit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}// end class

