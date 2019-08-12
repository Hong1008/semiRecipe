package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.CheckIdAction;
import action.CheckNicknameAction;
import action.CheckNowPw;
import action.ComInsertAction;
import action.ComListAction;
import action.IrdntAction;
import action.KakaoLoginAction;
import action.LoginAction;
import action.LogoutAction;
import action.MyPageAction;
import action.RecipeListAction;
import action.RegisterAction;
import action.ReviewListAction;
import action.ReviewViewAction;
import action.ReviewWriteAction;
import action.SelfDeleteAciont;
import action.SelfListAction;
import action.SelfViewAction;
import action.SelfInsertAction;
import action.ShowRecipeAction;
import action.ViewAction;
import model.InfoUpdate;
import model.IrdntTYDAO;
import model.ReviewInsertAction;
import model.SelfRecipeDAO;


@WebServlet("/recipe/*")
public class RecipeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		}else if(path.equals("recipe/showRecipe")) {
			ShowRecipeAction act = new ShowRecipeAction();
			act.execute(req, resp);
			next = "/jsp/showRecipe.jsp";
		}else if(path.equals("recipe/list")) {
			RecipeListAction list = new RecipeListAction();
			list.execute(req, resp);////////////////////////////////////////////////////////////////////
			next = "/jsp/primList.jsp";
			if(method.equalsIgnoreCase("POST")) {
				next = "/ajax/listResult.jsp";
			}
		}else if(path.equals("recipe/review")) {
			ReviewListAction revList = new ReviewListAction();
			revList.execute(req, resp);
			next = "/review/reviewBoard.jsp";
		}else if(path.equals("recipe/reviewwrite")) {
			HttpSession session = req.getSession();
			String loginID = (String) session.getAttribute("loginID");
			
			if(loginID==null) {
				next ="/recipe/loginForm";
			}else {
				ReviewWriteAction revWrite = new ReviewWriteAction();
				revWrite.execute(req, resp);
				next = "/review/write.jsp";
			}
			
		}else if(path.equals("recipe/reviewinsert")) {
			ReviewInsertAction revInsert = new ReviewInsertAction();
			revInsert.execute(req, resp);
			//next="/review/reviewBoard.jsp";
			resp.sendRedirect("review");
		}else if(path.equals("recipe/reviewview")) {
			ReviewViewAction revView = new ReviewViewAction();
			revView.execute(req, resp);
			next="/review/view.jsp";
		}
		else if(path.equals("recipe/irdnt")) {
			IrdntAction irdnt = new IrdntAction();
			irdnt.execute(req, resp);
			next = "/review/irdntList.jsp";
		}else if(path.equals("recipe/comList")) {
			ComListAction comList = new ComListAction();
			comList.execute(req,resp);
			next = "/jsp/comment.jsp";				
		}else if(path.equals("recipe/insertCom")) {
			ComInsertAction insertCom = new ComInsertAction();
			insertCom.execute(req,resp);
		}else if(path.equals("recipe/selfRecipe")) {
			SelfListAction selfList = new SelfListAction();// 셀프 리스트 
			selfList.execute(req, resp);////////////////////////////////////////////////////////////////////////////
			next = "/selfRecipe/selfBoard.jsp";		
			if(method.equalsIgnoreCase("POST")) {
				next = "/ajax/selfList.jsp";
			}
		}else if(path.equals("recipe/selfView")) { //  셀프 뷰
			SelfViewAction viewList = new SelfViewAction();
			viewList.execute(req, resp);
			next = "/selfRecipe/selfView.jsp";	
		}else if(path.equals("recipe/insertSelfRecipe")) { //  셀프 인서트
			if(method.equalsIgnoreCase("get")){
				IrdntTYDAO dao = new IrdntTYDAO();
				req.setAttribute("aList", dao.list());
				req.setAttribute("tList", dao.tyList());
				dao.exit();
				next = "/selfRecipe/insertSelfRecipe.jsp";				
			}else {				
				SelfInsertAction insertList = new SelfInsertAction();
				insertList.executeMulti(req, resp);
				SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
				int riMax = dao.recipeIdMax();
				resp.sendRedirect("selfView?recipe_id="+riMax);
			}
		}else if (path.equals("deleteSelfRecipe")) {
				SelfDeleteAciont del = new SelfDeleteAciont();
				del.execute(req, resp);
				//resp.sendRedirect("selfRecipe?pageNum=" + req.getParameter("pageNum"));
				//삭제 후 마지막 페이지 유지
			
			
		}else if(path.equals("recipe/loginForm")) {
			next = "/jsp/login.jsp";
		}else if(path.equals("recipe/login")) {
			LoginAction login = new LoginAction();
			login.execute(req, resp);
		}else if(path.equals("recipe/kakao_login")) {
			KakaoLoginAction kLogin = new KakaoLoginAction();
			kLogin.execute(req, resp);
		}else if(path.equals("recipe/logout")) {
			LogoutAction logout = new LogoutAction();
			logout.execute(req, resp);
			resp.sendRedirect("home");
		}else if(path.equals("recipe/registerForm")) {
			next = "/jsp/register.jsp";
		}else if(path.equals("recipe/register")) {
			RegisterAction register = new RegisterAction();
			register.execute(req, resp);
			
			HttpSession session = req.getSession();
			session.setAttribute("loginID", req.getParameter("id")); 	// loginID라는 이름으로 id 값을 세션에 저장
			session.setMaxInactiveInterval(30*60); 	// 30분
			
			resp.sendRedirect("home");
		}else if(path.equals("recipe/checkID")) {
			CheckIdAction checkId = new CheckIdAction();
			checkId.execute(req, resp);
		}else if(path.equals("recipe/checkNickname")) {
			CheckNicknameAction checkNickname = new CheckNicknameAction();
			checkNickname.execute(req, resp);
		}else if(path.equals("recipe/checkNowPw")) {
			CheckNowPw checkNowPw = new CheckNowPw();
			checkNowPw.execute(req, resp);
		}else if(path.equals("recipe/myPage")) {
			MyPageAction myPage = new MyPageAction();
			myPage.execute(req, resp);
		}else if(path.indexOf("recipe/infoUpdate/")>-1) {
			InfoUpdate update = new InfoUpdate(); 
			update.execute(req, resp);
		}
		
		if(next!="") {
			System.out.println(next);
			req.getRequestDispatcher(next).forward(req, resp);
		}
	}
}// end class
