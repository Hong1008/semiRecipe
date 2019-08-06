package model;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			Date birthday = Date.valueOf(req.getParameter("birthday"));
			dto.setBirthday(birthday);
			dto.setUpdateName("birthday");
		}
		
		HttpSession session = req.getSession();
		
		dto.setId((String) session.getAttribute("logOk"));
		dao.update(dto);
	}
}
