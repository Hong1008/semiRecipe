package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberDTO;
import model.PrimDAO;
import model.PrimDTO;
import model.ReviewDAO;
import model.ReviewDTO;

public class ReviewWriteAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		PrimDAO dao = new PrimDAO();
		List<PrimDTO> aList = dao.listView();
		req.setAttribute("recipes", aList);
		
		RequestDispatcher dis = null;
		HttpSession session = req.getSession();
		
		String sessionChkId = (String) session.getAttribute("loginID");
		MemberDAO mdao = MemberDAO.getInstance();
		
		MemberDTO mdto = mdao.myPage(sessionChkId);
		req.setAttribute("mdto", mdto);
		
	}
}//end class