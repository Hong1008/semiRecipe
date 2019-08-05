package action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberDTO;

public class MyPageAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dis = null;
		HttpSession session = req.getSession();
		
		String sessionChkId = (String) session.getAttribute("logOk");
		MemberDAO dao = MemberDAO.getInstance();
		
		if(sessionChkId!=null) {	// 로그인이 되어 있으면
			MemberDTO dto = dao.myPage(sessionChkId);
			req.setAttribute("dto", dto);
			dis = req.getRequestDispatcher("../jsp/myPage.jsp");
		}else {
			dis = req.getRequestDispatcher("../jsp/register.jsp");
		}
		
		dis.forward(req, resp);
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
	}
}
