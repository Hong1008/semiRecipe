package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SelfRecipeDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;

	private SelfRecipeDAO() {
	}

	private static SelfRecipeDAO dao = new SelfRecipeDAO();

	public static SelfRecipeDAO getInstance() {
		return dao;
	}

	public Connection init() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");

		String url = "jdbc:oracle:thin://@192.168.30.72:1521:xe";
		String user = "hong";
		String password = "1234";
		return DriverManager.getConnection(url, user, password);
	}// end init()

	public void exit() throws SQLException {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}// end exit()

	// -------------------------------------------------------------------리스트 출력 메소드 시작

	public List<PrimDTO> primListMethod(PageDTO pdto) {
		List<PrimDTO> prList = new ArrayList<PrimDTO>();
		
		try {
			conn = init();
			String sql = "select recipe_id, recipe_nm_ko, sumry, img_url, ty_nm, cooking_time, calorie, level_nm from primary ";
			sql += "where recipe_type = 's'"; 
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PrimDTO dto = new PrimDTO();
				dto.setRECIPE_ID(rs.getInt("recipe_id"));
				dto.setRECIPE_NM_KO(rs.getString("recipe_nm_ko"));
				dto.setSUMRY(rs.getString("sumry"));
				dto.setIMG_URL(rs.getString("img_url"));
				dto.setTY_NM(rs.getString("ty_nm"));
				dto.setCOOKING_TIME(rs.getString("cooking_time"));
				dto.setCALORIE(rs.getString("calorie"));
				dto.setLEVEL_NM(rs.getString("level_nm"));
				prList.add(dto);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return prList;
	}// end primListMethod()

	public List<SelfRecipeDTO> selfRecipeListMethod(PageDTO pdto) {
		List<SelfRecipeDTO> srList = new ArrayList<SelfRecipeDTO>();
		SelfRecipeDTO dto = new SelfRecipeDTO();
		try {
			conn = init();
			String sql = "select s.recipe_id, user_id, self_date from selfrecipe s, primary p where S.RECIPE_ID=P.RECIPE_ID";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto.setRecipe_id(rs.getInt("recipe_id"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setSelf_date(rs.getDate("self_date"));
				srList.add(dto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return srList;
	}// end selfRecipeListMethod()

	public List<IrdntDTO> irdntListMethod(PageDTO pdto) {
		List<IrdntDTO> irList = new ArrayList<IrdntDTO>();
		IrdntDTO dto = new IrdntDTO();
		try {
			conn = init();
			String sql = "select irdnt_sn, importance, p.recipe_id, irdnt_nm ";
			sql	+= "from irdnt i, primary p where i.recipe_id = p.recipe_id and recipe_type = 's'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setIRDNT_SN(rs.getInt("irdnt_sn"));
				dto.setRECIPE_ID(rs.getInt("recipe_id"));
				dto.setIMPORTANCE(rs.getString("importance"));
				dto.setIRDNT_NM(rs.getString("irdnt_nm"));
				irList.add(dto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return irList;
	}// end irdntListMethod()

	public List<StepDTO> stepListMethod(PageDTO pdto) {
		List<StepDTO> stList = new ArrayList<StepDTO>();
		StepDTO stdto = new StepDTO();
		try {
			conn = init();
			String sql = "select cooking_no, recipe_id  from step where  recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stdto.getRECIPE_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				stdto.setCOOKING_NO(rs.getInt("cooking_no"));
				stdto.setRECIPE_ID(rs.getInt("recipe_id"));
				stList.add(stdto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return stList;
	}// end stepListMethod()
	// -------------------------------------------------------------------리스트 출력 끝
	
	
	// -------------------------------------------------------------------뷰 시작
	
	public PrimDTO primViewMethod(int recipe_id) {
		PrimDTO prdto = new PrimDTO();
		try {
			conn = init();
			String sql = "select recipe_id, recipe_nm_ko, sumry, img_url, ty_nm, cooking_time, calorie, level_nm ";
			sql += "from primary where recipe_id = ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prdto.setRECIPE_ID(rs.getInt("recipe_id"));
				prdto.setRECIPE_NM_KO(rs.getString("recipe_nm_ko"));
				prdto.setSUMRY(rs.getString("sumry"));
				prdto.setIMG_URL(rs.getString("img_url"));
				prdto.setTY_NM(rs.getString("ty_nm"));
				prdto.setCOOKING_TIME(rs.getString("cooking_time"));
				prdto.setCALORIE(rs.getString("calorie"));
				prdto.setLEVEL_NM(rs.getString("level_nm"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return prdto;
	}// end primViewMethod()
	
	public List<IrdntDTO> irdntViewMethod(int recipe_id) {
		List<IrdntDTO> irList = new ArrayList<IrdntDTO>();
		try {
			conn = init();
			String sql = "select irdnt_sn, importance, recipe_id, irdnt_nm, irdnt_ty_nm from irdnt where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				IrdntDTO irdto = new IrdntDTO();
				irdto.setIRDNT_SN(rs.getInt("irdnt_sn"));
				irdto.setRECIPE_ID(rs.getInt("recipe_id"));
				irdto.setIMPORTANCE(rs.getString("importance"));
				irdto.setIRDNT_NM(rs.getString("irdnt_nm"));
				irdto.setIRDNT_TY_NM(rs.getString("irdnt_ty_nm"));
				irList.add(irdto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return irList;
	}// end irdntViewMethod()
	
	public SelfRecipeDTO selfRecipeViewMethod(int recipe_id) {
		SelfRecipeDTO srdto = new SelfRecipeDTO();
		try {
			conn = init();
			String sql = "select recipe_id, user_id, self_date, self_views from selfrecipe where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				srdto.setRecipe_id(rs.getInt("recipe_id"));
				srdto.setUser_id(rs.getString("user_id"));
				srdto.setSelf_date(rs.getDate("self_date"));
				srdto.setSelf_views(rs.getString("self_views"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return srdto;
	}// end selfRecipeViewMethod()
	
	public List<StepDTO> stepViewMethod(int recipe_id) {
		List<StepDTO> stList = new ArrayList<StepDTO>();
		try {
			conn = init();
			String sql = "select cooking_no, recipe_id, step_tip from step where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
		while (rs.next()) {
			StepDTO stdto = new StepDTO();
			stdto.setCOOKING_NO(rs.getInt("cooking_no"));
			stdto.setSTEP_TIP(rs.getString("step_tip"));
			stdto.setRECIPE_ID(rs.getInt("recipe_id"));
			stList.add(stdto);
		}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return stList;
	}// end stepViewMethod()
	
	public List<IrdntTYDTO> irdntTyViewMethod(int recipe_id) {
		List<IrdntTYDTO> irtList = new ArrayList<IrdntTYDTO>();
		try {
			conn = init();
			String sql = "select irdnt_nm from irdnt where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				IrdntTYDTO irtdto = new IrdntTYDTO();
				irtdto.setIrdnt_nm(rs.getString("irdnt_nm"));
				irtList.add(irtdto);
			}			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return irtList;
	}// end indntTyViewMethod()	
	
	// -------------------------------------------------------------------뷰 끝

	
	// -------------------------------------------------------------------검색 시작
	public PrimDTO primSearchMethod(int recipe_id){
		PrimDTO prdto = new PrimDTO();
		try {
			conn = init();
			String sql = "select recipe_id, recipe_nm_ko, sumry, img_url, ty_nm, cooking_time, calorie, level_nm ";
			sql += "from primary where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prdto.setRECIPE_ID(rs.getInt("recipe_id"));
				prdto.setRECIPE_NM_KO(rs.getString("recipe_nm_ko"));
				prdto.setSUMRY(rs.getString("sumry"));
				prdto.setIMG_URL(rs.getString("img_url"));
				prdto.setTY_NM(rs.getString("ty_nm"));
				prdto.setCOOKING_TIME(rs.getString("cooking_time"));
				prdto.setCALORIE(rs.getString("calorie"));
				prdto.setLEVEL_NM(rs.getString("level_nm"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return prdto;
	}// end primSearchMethod()
	
	public SelfRecipeDTO selfSearchMethod(int recipe_id){
		SelfRecipeDTO srdto = new SelfRecipeDTO();
		try {
			conn = init();
			String sql = "select recipe_id, user_id, self_date from selfrecipe where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				srdto.setRecipe_id(rs.getInt("recipe_id"));
				srdto.setUser_id(rs.getString("user_id"));
				srdto.setSelf_date(rs.getDate("self_date"));
				srdto.setSelf_views(rs.getString("self_views"));
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return srdto;
	}// end selfSearchMethod()
	
	public List<IrdntDTO> irdntSearchMethod(int recipe_id){
		List<IrdntDTO> irList = new ArrayList<IrdntDTO>();
		try {
			conn = init();
			String sql ="select irdnt_sn, importance, recipe_id, irdnt_nm, irdnt_ty_nm from irdnt where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				IrdntDTO irdto = new IrdntDTO();
				irdto.setIRDNT_SN(rs.getInt("irdnt_sn"));
				irdto.setIMPORTANCE(rs.getString("importance"));
				irdto.setIRDNT_NM(rs.getString("irdnt_nm"));
				irdto.setIRDNT_TY_NM(rs.getString("irdnt_ty_nm"));
				irList.add(irdto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return irList;
	}// end irdntSearchMethod()
	
	public List<StepDTO> steptSearchMethod(int recipe_id){
		List<StepDTO> stList = new ArrayList<StepDTO>();
		StepDTO stdto = new StepDTO();
		try {
			conn = init();
			String sql = "select cooking_no, recipe_id from step where  recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				stdto.setCOOKING_NO(rs.getInt("cooking_no"));
				stdto.setRECIPE_ID(rs.getInt("recipe_id"));
				stList.add(stdto);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return stList;
	}// end steptSearchMethod()
	// -------------------------------------------------------------------검색 끝
	
	
	// -------------------------------------------------------------------글쓰기 시작
	public void primInsertMethod(PrimDTO prdto) {
		try {
			conn = init();
			String sql = "insert into primary (recipe_id, recipe_nm_ko, sumry, img_url, ty_nm, cooking_time, calorie, level_nm, recipe_type, nation_nm) ";
			sql += "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, prdto.getRECIPE_ID());
			pstmt.setString(2, prdto.getRECIPE_NM_KO());
			pstmt.setString(3, prdto.getSUMRY());
			pstmt.setString(4, prdto.getIMG_URL());
			pstmt.setString(5, prdto.getTY_NM());
			pstmt.setString(6, prdto.getCOOKING_TIME());
			pstmt.setString(7, prdto.getCALORIE());
			pstmt.setString(8, prdto.getLEVEL_NM());
			pstmt.setString(9, "s");
			pstmt.setString(10, prdto.getNATION_NM());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end primInsertMethod()

	public void selfRecipeInsertMethod(SelfRecipeDTO srdto) {
		try {
			conn = init();

			String sql = "insert into selfrecipe (recipe_id, user_id, self_date) ";
			sql += "values(?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, srdto.getRecipe_id());
			pstmt.setString(2, srdto.getUser_id());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end selfRecipeInsertMethod()

	public void irdntInsertMethod(List<IrdntDTO> irdList) {
		try {
			conn = init();
			String sql = "insert into irdnt (irdnt_sn, importance, recipe_id, irdnt_nm) ";
			sql += "values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < irdList.size(); i++) {
			pstmt.setInt(1, irdList.get(i).getIRDNT_SN());
			pstmt.setString(2, irdList.get(i).getIMPORTANCE());
			pstmt.setInt(3, irdList.get(i).getRECIPE_ID());
			pstmt.setString(4, irdList.get(i).getIRDNT_NM());
			pstmt.executeUpdate();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end irdntInsertMethod()

	public void stepInsertMethod(StepDTO stdto) {
		try {
			conn = init();
			int riMax = recipeIdMax();
			String sql = "insert into step (recipe_id, cooking_no, cooking_dc) ";
			sql += "values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stdto.getRECIPE_ID());
			pstmt.setInt(2, stdto.getCOOKING_NO());
			pstmt.setString(3, stdto.getCOOKING_DC());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end stepInsertMethod()

	// -------------------------------------------------------------------글쓰기 끝
	
	// -------------------------------------------------------------------레시피 아이디 큰값시작
	public int recipeIdMax () {	
		int riMax = 0;
		try {
			conn = init();
			String sql = "select max(recipe_id) from primary";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			riMax = rs.getInt(1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return riMax;		
	}
	// -------------------------------------------------------------------레시피 아이디 큰값끝

	
	// -------------------------------------------------------------------수정하기 시작
	public void primUpdateMethod(PrimDTO dto) {
		try {
			conn = init();
			String sql = "update primary set recipe_nm_ko = ?, sumry = ?, img_url = ?, ty_nm = ?, cooking_time = ?, calorie = ?, level_nm = ? ";
			sql += "where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getRECIPE_NM_KO());
			pstmt.setString(2, dto.getSUMRY());
			pstmt.setString(3, dto.getIMG_URL());
			pstmt.setString(4, dto.getTY_NM());
			pstmt.setString(4, dto.getCOOKING_TIME());
			pstmt.setString(6, dto.getCALORIE());
			pstmt.setString(7, dto.getLEVEL_NM());
			pstmt.setInt(8, dto.getRECIPE_ID());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end primUpdateMethod()

	public void selfRecipeUpdateMethod(SelfRecipeDTO dto) {
		try {
			conn = init();
			String sql = "update primary set self_date = sysdate where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getRecipe_id());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end selfRecipeUpdateMethod()
	
	public void irdntUpdateMethod(IrdntDTO dto) {
		try {
			conn = init();
			String sql = "update irdnt set irdnt_sn = ?, importance = ?, irdnt_nm = ? where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getIRDNT_SN());
			pstmt.setString(2, dto.getIMPORTANCE());
			pstmt.setString(3, dto.getIRDNT_NM());
			pstmt.setInt(4, dto.getRECIPE_ID());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end irdntUpdateMethod()
	
	public void stepUpdateMethod(List<StepDTO> stList) {
		try {
			conn = init();
			String sql = "update step set cooking_no = ?, cooking_dc = ? where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < stList.size(); i++) {
				pstmt.setInt(1, stList.get(i).getCOOKING_NO());
				pstmt.setString(2, stList.get(i).getCOOKING_DC());
				pstmt.setInt(3, stList.get(i).getRECIPE_ID());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end stepUpdateMethod()
	// -------------------------------------------------------------------수정하기 끝

	
	// -------------------------------------------------------------------삭제하기 시작
	public void primDeleteMethod(int recipe_id) {
		try {
			conn = init();
			String sql = "delete from primary where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end primDeleteMethod()
	
	public void selfRecipeDeleteMethod(int recipe_id) {
		try {
			conn = init();
			String sql = "delete from selfrecipe where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end selfRecipeDeleteMethod()
	
	public void irdntDeleteMethod(int recipe_id) {
		try {
			conn = init();
			String sql = "delete from irdnt where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end irdntDeleteMethod()
	
	public void stepDeleteMethod(int recipe_id) {
		try {
			conn = init();
			String sql = "delete from step where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end stepDeleteMethod()
	
	// -------------------------------------------------------------------삭제하기 끝
	
	
	// -------------------------------------------------------------------파일 시작
	public String fileMethod(int recipe_id) {
		String fileName = null;
		try {
			conn = init();
			String sql = "select img_url from primary where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				fileName = rs.getString("img_url");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}// end fileMethod()
	// -------------------------------------------------------------------파일 끝
	
	
	// -------------------------------------------------------------------조회수 증가 시작
	public void readCountMethod(int recipe_id) {
		try {
			conn = init();
			String sql = "update board set self_views = self_views + 1 where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, recipe_id);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}// end readCountMethod()
	// -------------------------------------------------------------------조회수 증가 끝

	

	// -------------------------------------------------------------------
	public int rowTotalCount() {
		int cnt = 0;
		try {
			conn = init();
			String sql = "select count(*) from selfrecipe";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(cnt);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}// end rowTotalCount()
	// -------------------------------------------------------------------
}// end class
