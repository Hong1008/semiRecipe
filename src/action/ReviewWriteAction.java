package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PrimDAO;
import model.PrimDTO;

public class ReviewWriteAction {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrimDAO dao = new PrimDAO();
		List<PrimDTO> aList = dao.listView();
		req.setAttribute("recipes", aList);
	}
}//end class