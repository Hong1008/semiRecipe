package action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.IrdntDTO;
import model.PrimDTO;
import model.SelfRecipeDAO;
import model.SelfRecipeDTO;
import model.StepDTO;

public class SelfUpdateAction extends HttpServlet {
	
	public MultipartRequest executeMulti(HttpServletRequest req) {

		MultipartRequest multi = null;
		String saveDirectory = "/img_self";
		File file = new File(saveDirectory);

		int maxPostSize = 6000000; // 약 5MB(5242880Byte) 파일저장 최대크기
		String encoding = "UTF-8"; // 파일명 한글처리

		try {
			multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
			// new DefaultFileRenamePolicy() 동일파일명이 있을때 숫자 붙임
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		PrimDTO prdto = new PrimDTO();				
		int recipe_id = Integer.parseInt(multi.getParameter("recipe_id"));
		String fileName = dao.fileMethod(recipe_id);
		
		if (multi.getFilesystemName("img_url") != null) {
			if(fileName != null) {
				File oldFile = new File(saveDirectory, fileName);
				oldFile.delete();
			}
			prdto.setIMG_URL(multi.getFilesystemName("img_url"));
		} else {
			if(fileName != null) {
				prdto.setIMG_URL(fileName);
			}
		}
		prdto.setRECIPE_NM_KO(multi.getParameter("recipe_nm_ko"));
		prdto.setSUMRY(multi.getParameter("sumry"));
		prdto.setTY_NM(multi.getParameter("ty_nm"));
		prdto.setLEVEL_NM(multi.getParameter("levele_nm"));
		prdto.setCOOKING_TIME(multi.getParameter("cooking_time"));
		prdto.setCALORIE(multi.getParameter("calorie"));
		
		SelfRecipeDTO srdto = new SelfRecipeDTO();
		
		
		IrdntDTO irdto = new IrdntDTO();
		
		
		StepDTO stdto = new StepDTO();
		
		
		
		
		
		return multi;
	}// end executeMulti()
}// end class
