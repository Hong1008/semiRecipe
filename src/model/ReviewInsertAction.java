package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.ReviewDAO;
import model.ReviewDTO;

public class ReviewInsertAction {

	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		ReviewDAO dao = new ReviewDAO();
		ReviewDTO dto = new ReviewDTO();
		
		MultipartRequest multi = null;
		String saveDirectory = "C:\\study\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\semiRecipe\\review\\images";
		
		File file = new File(saveDirectory);
		if (!file.exists()) { // 파일 존재확인 후 생성
			file.mkdir();
		}
		int maxPostSize = 6000000; // 약 5MB(5242880Byte) 파일저장 최대크기
		String encoding = "UTF-8"; // 파일명 한글처리

		try {
			multi = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
			// new DefaultFileRenamePolicy() 동일파일명이 있을때 숫자 붙임
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*-----------------파일 업로드 처리------------------------------------*/
		
		
		Enumeration<String> nms = multi.getParameterNames();
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		while (nms.hasMoreElements()) {
			String param = (String) nms.nextElement();
			String key="";
			if(param.contains("importance")) {
				key = param.substring(param.lastIndexOf("_")+1);
				map.put(Integer.parseInt(key), Integer.parseInt(multi.getParameter(param)));
			}
		}
		System.out.println(multi.getParameter("review_content"));
		dto.setReview_content(multi.getParameter("review_content"));
		dto.setReview_rate(multi.getParameter("review_rate"));
		dto.setUser_id(multi.getParameter("user_id"));
		dto.setRecipe_id(Integer.parseInt(multi.getParameter("recipe_id")));
		dto.setReview_subject(multi.getParameter("review_subject"));
		dto.setUser_nickname(multi.getParameter("user_nickname"));
		dao.insertMethod(dto);
		
		PrimDAO pdao = new PrimDAO();
		pdao.primRating(dto.getRecipe_id());
		pdao.exit();
		
		IrdntDAO idao = new IrdntDAO();
		boolean chk = idao.chkFirst(dto.getRecipe_id());
		if(chk) {
			idao.firstImportance(map, dto.getRecipe_id());
		}else {
			idao.setImportance(map,dto.getRecipe_id());
		}
		idao.exit();		
	}

}//end class
