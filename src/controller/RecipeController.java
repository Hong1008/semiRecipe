package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.ViewAction;
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
		String method = req.getMethod();
		String path = req.getRequestURI();
		String next = "";

		path = path.substring(path.indexOf("/", 2)+1);
		if(path.equals("recipe/home")) {
			IrdntTYDAO dao = new IrdntTYDAO();
			req.setAttribute("aList", dao.list());
			req.setAttribute("tList", dao.tyList());
			dao.exit();
			next = "/jsp/home.jsp";
		}else if(path.equals("recipe/viewResult")) {
			ViewAction act = new ViewAction();
			act.execute(req, resp);
			next = "/ajax/viewResult.jsp";
		}else if(path.equals("recipe/review")) {
			next = "/jsp/reviewBoard.jsp";
		}else if(path.equals("recipe/myRecipe")) {
			next = "/jsp/recipeBoard.jsp";
		}else if(path.equals("recipe/qna")) {
			next = "/jsp/qna.jsp";
		}else if(path.equals("recipe/login")) {
			next = "/jsp/login.jsp";
		}
		
		if(next!="") {
			System.out.println(next);
			req.getRequestDispatcher(next).forward(req, resp);
		}
	}
}
