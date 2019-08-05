package action;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.SelfRecipeDAO;
import model.SelfRecipeDTO;

public class SelfWriteAction extends HttpServlet{
	
	
	public MultipartRequest executeMulti(HttpServletRequest req) {
		MultipartRequest multi = null;
		String saveDirectory = "/img_self";
		File file = new File(saveDirectory);
		if(!file.exists()) { // 파일 존재확인 후 생성
			file.mkdir();
		}
		int maxPostSize = 6000000; // 약 5MB(5242880Byte) 파일저장 최대크기
		String encoding = "UTF-8"; // 파일명 한글처리
		
		try {
			multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy()); 
			//new DefaultFileRenamePolicy() 동일파일명이 있을때 숫자 붙임
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*-----------------파일 업로드 처리------------------------------------*/
		/*
		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		SelfRecipeDTO dto = new SelfRecipeDTO();
		dto.setRecipe_nm_ko(multi.getParameter("recipe_nm_ko"));
		dto.setSumry(multi.getParameter("sumry"));
		dto.setImg_url(multi.getParameter("img_url")); // 첨부파일은 getFilesystemName()이걸로
		dto.setTy_nm(multi.getParameter("ty_nm"));
		dto.setCooking_time(multi.getParameter("cooking_time"));
		dto.setCalorie(multi.getParameter("calorie"));
		dto.setLevel_nm(multi.getParameter("level_nm"));
		dto.setImportance(multi.getParameter("importance"));
		dto.setCooking_no(Integer.parseInt(multi.getParameter("cooking_no")));
		// form으로 넘오오기 때문에 parameter으로 받는다.
		dao.insertMethod(dto);
		// 받아온 값을 넘겨준다.

		*/
		return multi;
	}// end executeMulti()
}// end selfWriteAction()
