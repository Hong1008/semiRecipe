package action;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import model.PageDTO;
import model.SelfRecipeDAO;

public class SelfListAction extends HttpServlet{
	
	public void execute(HttpServletRequest req) {
		
		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		// 첫 페이지 번호 설정
		String pageNum = req.getParameter("pageNum");
		if ( pageNum == null || pageNum.equals("null")) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		int cnt = dao.rowTotalCount();
		if(cnt > 0) {
		PageDTO pdto = new PageDTO(currentPage, cnt);
		req.setAttribute("prList", dao.primListMethod(pdto));
		req.setAttribute("srlist", dao.selfRecipeListMethod(pdto));
		req.setAttribute("irList", dao.irdntListMethod(pdto));
		req.setAttribute("stList", dao.stepListMethod(pdto));
		req.setAttribute("pdto", pdto);

		}
		
		
		
		
		
	}// end exectue()
}// end class
