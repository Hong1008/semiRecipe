package action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberDTO;

public class LoginAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		MemberDTO dto = new MemberDTO();
		dto.setId(req.getParameter("id"));
		dto.setPw(req.getParameter("pw"));
		
		System.out.println(req.getParameter("id"));
		
		MemberDAO dao = MemberDAO.getInstance();
		int cnt = dao.login(dto);

		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.write(String.valueOf(cnt));
		
		HttpSession session = null;
		if(cnt==1) {	// 회원일 때
			session = req.getSession();
			session.setAttribute("logOk", req.getParameter("id")); 	// logOk라는 이름으로 fid 값을 세션에 저장
			session.setMaxInactiveInterval(30*60); 	// 30분
		}
		
		
		
		
	} // end execute()
}
