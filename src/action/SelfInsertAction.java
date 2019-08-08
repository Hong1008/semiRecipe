package action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.IrdntDTO;
import model.PrimDTO;
import model.SelfRecipeDAO;
import model.SelfRecipeDTO;
import model.StepDTO;

public class SelfInsertAction extends HttpServlet{
		
	public MultipartRequest executeMulti(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println("2");
		MultipartRequest multi = null;
		String saveDirectory = "C:\\Users\\user1\\git\\semiRecipe\\WebContent\\selfRecipe\\img_self";
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
		
		SelfRecipeDAO dao = SelfRecipeDAO.getInstance();
		int riMax = dao.recipeIdMax() + 1 ;
		
		PrimDTO prdto = new PrimDTO();
		prdto.setRECIPE_ID(riMax);
		prdto.setRECIPE_NM_KO(multi.getParameter("recipe_nm_ko"));
		prdto.setSUMRY(multi.getParameter("sumry"));
		prdto.setIMG_URL(multi.getFilesystemName("img_url")); // 첨부파일은 getFilesystemName()이걸로
		prdto.setNATION_NM(multi.getParameter("natioin_nm"));
		prdto.setCOOKING_TIME(multi.getParameter("cooking_time"));
		prdto.setCALORIE(multi.getParameter("calorie"));
		prdto.setLEVEL_NM(multi.getParameter("level_nm"));
		//prdto.setTY_NM(multi.getParameter("ty_nm"));
		dao.primInsertMethod(prdto);
		
		System.out.println(riMax);
		System.out.println(multi.getParameter("recipe_nm_ko"));
		System.out.println(multi.getParameter("sumry"));
		System.out.println(multi.getFilesystemName("img_url"));
		System.out.println(multi.getParameter("natioin_nm"));
		System.out.println(multi.getParameter("calorie"));
		System.out.println(multi.getParameter("level_nm"));
		System.out.println(multi.getParameter("cooking_time"));
		//SelfRecipeDTO srdto = new SelfRecipeDTO();
		//srdto.setRecipe_id(riMax);
		//srdto.setUser_id(multi.getParameter("user_id"));
		//dao.selfRecipeInsertMethod(srdto);
		
		//List<IrdntDTO> irList = new ArrayList<IrdntDTO>();
		//IrdntDTO irdto = new IrdntDTO();
		//for (int i = 0; i < irList.size(); i++) {
		//	irdto.setRECIPE_ID(riMax);
		//	irdto.setIMPORTANCE(multi.getParameter("importance"));
		//	irdto.setIRDNT_SN(Integer.parseInt(multi.getParameter("irdnt_sn")));
		//	irdto.setIRDNT_NM(multi.getParameter("irdnt_nm"));
		//	irList.add(irdto);
		//}
		
		
		//List<StepDTO> stList = new ArrayList<StepDTO>();
		//StepDTO stdto = new StepDTO();
		//for (int i = 0; i < stList.size(); i++) {
		//	stdto.setCOOKING_NO(Integer.parseInt(multi.getParameter("cooking_no")));
		//	stdto.setCOOKING_DC(multi.getParameter("cooking_dc"));
		//	stList.add(stdto);
		//}
		
		
		// form으로 넘오오기 때문에 parameter으로 받는다.
		System.out.println("3");
		return multi;
	}// end executeMulti()
}// end selfWriteAction()
