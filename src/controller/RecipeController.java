package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.IrdntDAO;
import model.IrdntDTO;
import model.IrdntTYDAO;

@WebServlet("/recipe/*")
public class RecipeController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		execute(req, resp);
	}
	
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getRequestURI();
		String next = "";
		System.out.println(path);

		path = path.substring(path.indexOf("/", 2)+1);
		System.out.println(path);
		if(path.equals("recipe")) {
			IrdntTYDAO dao = new IrdntTYDAO();
			req.setAttribute("aList", dao.list());
			req.getRequestDispatcher("/home/home.jsp").forward(req, resp);
		}
	}
}
