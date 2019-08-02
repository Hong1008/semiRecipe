package controller;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberDTO;

public class InfoUpdate {
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String path = req.getRequestURI();
		path = path.substring(path.lastIndexOf("/"));
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		if(path.equals("/pw")) {
			dto.setPw(req.getParameter("pw"));
			dto.setUpdateName("pw");
		}else if(path.equals("/nickname")) {
			dto.setNickname(req.getParameter("nickname"));
			dto.setUpdateName("nickname");
		}else if(path.equals("/birthday")) {
			String year = req.getParameter("yy");
			String month = req.getParameter("mm");
			String day = req.getParameter("dd");
			
			if(Integer.parseInt(req.getParameter("mm"))<10) {
				month = "0"+month;
			}
			
			Date birthday = Date.valueOf(year+"-"+month+"-"+day);
			
			dto.setBirthday(birthday);
			dto.setUpdateName("birthday");
		}
		
		HttpSession session = req.getSession();
		
		dto.setId((String) session.getAttribute("logOk"));
		dao.update(dto);
	}
}
