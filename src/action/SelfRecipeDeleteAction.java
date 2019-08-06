package action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import model.SelfRecipeDAO;

public class SelfRecipeDeleteAction {
	public void execute(HttpServletRequest req) {
		String saveDirectory = "/img_self";
		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		int self_num = Integer.parseInt(req.getParameter("self_num"));
		String fileName = dao.fileMethod(self_num);
		if (fileName != null) {
			File file = new File(saveDirectory, fileName);
			file.delete();
		}
		dao.primDeleteMethod(self_num);
		dao.irdntDeleteMethod(self_num);
		dao.selfRecipeDeleteMethod(self_num);
		dao.stepDeleteMethod(self_num);
	}

}// end class
