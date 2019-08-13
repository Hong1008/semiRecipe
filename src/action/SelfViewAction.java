
package action;

import java.sql.SQLException;
import java.util.HashMap;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.SelfRecipeDAO;

public class SelfViewAction {
	public void execute(HttpServletRequest req,  HttpServletResponse resp) {

		String recipe_id = req.getParameter("recipe_id");
		System.out.println(recipe_id);
		System.out.println("레시피 아이디");
		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		HashMap<String, String> pvmap = dao.primViewMethod(recipe_id);

		req.setAttribute("prdto", pvmap);
		req.setAttribute("irList", dao.irdntViewMethod(recipe_id));
		req.setAttribute("stList", dao.stepViewMethod(recipe_id));


		
		int hour = 0;
		int miute = 0;
		String zero = "";
		hour = Integer.parseInt(pvmap.get("cooking_time")) / 60;
		System.out.println(pvmap.get("cooking_time"));
		miute = Integer.parseInt(pvmap.get("cooking_time")) % 60;
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

