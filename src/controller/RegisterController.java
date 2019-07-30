package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberDTO;

@WebServlet("/recipe/register.do")
public class RegisterController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dis = req.getRequestDispatcher("/jsp/register.jsp");
		dis.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String nickname = req.getParameter("nickname");
		
		String year = req.getParameter("yy");
		String month = req.getParameter("mm");
		String day = req.getParameter("dd");
		
		if(Integer.parseInt(req.getParameter("mm"))<10) {
			month = "0"+month;
		}
		
		Date birthday = Date.valueOf(year+"-"+month+"-"+day);

		MemberDTO dto = new MemberDTO();
		MemberDAO dao = MemberDAO.getInstance();
		
		dto.setId(id);
		dto.setPw(pw);
		dto.setNickname(nickname);
		dto.setBirthday(birthday);
		
		int result = dao.registerMethod(dto);
		
		if(result<1) {
			resp.setContentType("text/html; charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print("<script>alert('회원가입에 실패하였습니다.')</script>");
		}else {
			resp.sendRedirect("../home.do");
		}
		
	}

}
