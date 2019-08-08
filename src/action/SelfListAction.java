package action;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PageDTO;
import model.PrimDTO;
import model.SelfRecipeDAO;

public class SelfListAction extends HttpServlet{
	
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		// 첫 페이지 번호 설정
		String pageNum = req.getParameter("pageNum");
		if ( pageNum == null || pageNum.equals("null")) {
			pageNum = "1";
		}
		
		//int currentPage = Integer.parseInt(pageNum);
		//int cnt = dao.rowTotalCount();
		//if(cnt > 0) {
		//PageDTO pdto = new PageDTO(currentPage, cnt);
		PageDTO pdto = null;
		req.setAttribute("prList", dao.primListMethod(pdto));
		req.setAttribute("srList", dao.selfRecipeListMethod(pdto));
		req.setAttribute("irList", dao.irdntListMethod(pdto));
		req.setAttribute("stList", dao.stepListMethod(pdto));
		req.setAttribute("pdto", pdto);
		//}
		
		
		
		/*
		 * for(int i=0; i<dao.primListMethod(pdto).size(); i++) {
		 * System.out.println(dao.primListMethod(pdto).get(i).getRECIPE_ID()); }
		 */		
		
		
		
		
	}// end exectue()
}// end class
