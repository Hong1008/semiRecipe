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
		PrimDTO dto = new PrimDTO();
		try {
			conn = init();
			String sql = "select recipe_id, recipe_nm_ko, sumry, img_url, ty_nm, cooking_time, calorie, level_nm from primary ";
			sql += "where recipe_type = 's'"; 
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {;
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
			String sql = "select p.recipe_id, user_id, self_num, self_date ";
			sql	+= "from selfrecipe s, primary p where s.recipe_id = p.recipe_id and recipe_type = 's'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setRecipe_id(rs.getInt("recipe_id"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setSelf_num(rs.getInt("self_num"));
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
		StepDTO dto = new StepDTO();
		try {
			conn = init();
			String sql = "select cooking_no, p.recipe_id, step_tip ";
				sql	+= "from step s, primary p where s.recipe_id = p.recipe_id and recipe_type = 's'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setCOOKING_NO(rs.getInt("cooking_no"));
				dto.setSTEP_TIP(rs.getString("step_tip"));
				dto.setRECIPE_ID(rs.getInt("recipe_id"));
				stList.add(dto);
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
	
	public List<PrimDTO> primViewMethod(int self_num) {
		List<PrimDTO> prList = new ArrayList<PrimDTO>();
		try {
			conn = init();
			String sql = "select recipe_id, recipe_nm_ko, sumry, img_url, ty_nm, cooking_time, calorie, level_nm ";
			sql += "from primary p, selfrecipe s where self_num = '" + self_num + "'"; 
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
	}// end primViewMethod()
	
	public List<IrdntDTO> irdntViewMethod(int self_num) {
		List<IrdntDTO> irList = new ArrayList<IrdntDTO>();
		try {
			conn = init();
			String sql = "select irdnt_sn, importance, p.recipe_id, irdnt_nm ";
			sql	+= "from irdnt i, (select p.recipe_id, self_num from primary p, selfrecipe s where self_num = '" + self_num + "') t";
			sql += "where i.recipe_id = t.recipe_id "; 
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				IrdntDTO dto = new IrdntDTO();
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
	}// end irdntViewMethod()
	
	public List<SelfRecipeDTO> selfRecipeViewMethod(int self_num) {
		List<SelfRecipeDTO> srList = new ArrayList<SelfRecipeDTO>();
		try {
			conn = init();
			String sql = "select p.recipe_id, user_id, self_num, self_date ";
			sql	+= "from selfrecipe where self_num = '" + self_num + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SelfRecipeDTO dto = new SelfRecipeDTO();
				dto.setRecipe_id(rs.getInt("recipe_id"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setSelf_num(rs.getInt("self_num"));
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
	}// end selfRecipeViewMethod()
	
	public List<StepDTO> stepViewMethod(int self_num) {
		List<StepDTO> stList = new ArrayList<StepDTO>();
		try {
			conn = init();
			String sql = "select cooking_no, p.recipe_id, step_tip ";
			sql	+= "from step s, (select p.recipe_id, self_num from primary p, selfrecipe s where self_num = '" + self_num + "') t";
			sql	+= "where s.recipe_id = t.recipe_id";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		while (rs.next()) {
			StepDTO dto = new StepDTO();
			dto.setCOOKING_NO(rs.getInt("cooking_no"));
			dto.setSTEP_TIP(rs.getString("step_tip"));
			dto.setRECIPE_ID(rs.getInt("recipe_id"));
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
	// -------------------------------------------------------------------뷰 끝

	
	// -------------------------------------------------------------------검색 시작
	public List<PrimDTO> primSearchMethod(){
		List<PrimDTO> prList = new ArrayList<PrimDTO>();
		try {
			conn = init();
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
	}
	
	
	// -------------------------------------------------------------------검색 끝
	
	
	// -------------------------------------------------------------------글쓰기 시작
	public void primInsertMethod(PrimDTO dto) {
		try {
			conn = init();
			String sql = "insert into primary (recipe_id, recipe_nm_ko, sumry, img_url, ty_nm, cooking_time, calorie, level_nm) ";
			sql += "values(?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getRECIPE_ID());
			pstmt.setString(2, dto.getRECIPE_NM_KO());
			pstmt.setString(3, dto.getSUMRY());
			pstmt.setString(4, dto.getIMG_URL());
			pstmt.setString(5, dto.getTY_NM());
			pstmt.setString(6, dto.getCOOKING_TIME());
			pstmt.setString(7, dto.getCALORIE());
			pstmt.setString(8, dto.getLEVEL_NM());
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

	public void selfRecipeInsertMethod(SelfRecipeDTO dto) {
		try {
			conn = init();
			String sql = "insert into selfrecipe (recipe_id, user_id, self_num) ";
			sql += "values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getRecipe_id());
			pstmt.setString(2, dto.getUser_id());
			pstmt.setInt(3, dto.getSelf_num());
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

	public void irdntInsertMethod(IrdntDTO dto) {
		try {
			conn = init();
			String sql = "insert into irdnt (irdnt_sn, importance, recipe_id, irdnt_nm) ";
			sql += "values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getIRDNT_SN());
			pstmt.setString(2, dto.getIMPORTANCE());
			pstmt.setInt(3, dto.getRECIPE_ID());
			pstmt.setString(4, dto.getIRDNT_NM());
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
	}// end irdntInsertMethod()

	public void stepInsertMethod(StepDTO dto) {
		try {
			conn = init();
			String sql = "insert into step (recipe_id, cooking_no, cooking_dc, step_tip) ";
			sql += "valuse(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getRECIPE_ID());
			pstmt.setInt(2, dto.getCOOKING_NO());
			pstmt.setString(3, dto.getCOOKING_DC());
			pstmt.setString(4, dto.getSTEP_TIP());
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


		
	// -------------------------------------------------------------------수정하기 시작
	public void primUpdateMethod(PrimDTO dto) {
		try {
			conn = init();
			String sql = "update primary set recipe_nm_ko = ?, sumry = ?, img_url = ?, ty_nm = ?, cooking_time = ?, calorie = ?, level_nm = ? ";
			sql += "where recipe_id = '" + dto.getRECIPE_ID() + "'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getRECIPE_NM_KO());
			pstmt.setString(2, dto.getSUMRY());
			pstmt.setString(3, dto.getIMG_URL());
			pstmt.setString(4, dto.getTY_NM());
			pstmt.setString(4, dto.getCOOKING_TIME());
			pstmt.setString(6, dto.getCALORIE());
			pstmt.setString(6, dto.getLEVEL_NM());
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

	public void updateMethod(SelfRecipeDTO dto) {
		try {
			conn = init();
			String sql = "update primary set user_id = ?, self_num = ? ";
			sql += "where recipe_id = '" + dto.getRecipe_id() + "'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUser_id());
			pstmt.setInt(2, dto.getSelf_num());
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
	}// end updateMethod()
	
	public void irdntUpdateMethod(IrdntDTO dto) {
		try {
			conn = init();
			String sql = "update irdnt set irdnt_sn = ?, importance = ?, irdnt_nm = ?";
			sql += "where recipe_id = '" + dto.getRECIPE_ID() + "'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getIRDNT_SN());
			pstmt.setString(2, dto.getIMPORTANCE());
			pstmt.setString(3, dto.getIRDNT_NM());
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
	
	public void stepUpdateMethod(StepDTO dto) {
		try {
			conn = init();
			String sql = "update step set cooking_no = ?, cooking_dc = ?, step_tip = ?";
			sql += "where recipe_id = '" + dto.getRECIPE_ID() + "'";
			pstmt = conn.prepareStatement(sql);
			
			
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
	}// end stepUpdateMethod()
	// -------------------------------------------------------------------수정하기 끝

	
	// -------------------------------------------------------------------삭제하기 시작
	public void primDeleteMethod(int self_num) {
		try {
			conn = init();
			String sql = "delete from primary where recipe_id in ";
			sql += "(select recipe_id from selfrecipe where self_num = " + self_num + ")";
			pstmt = conn.prepareStatement(sql);
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
	
	public void selfRecipeDeleteMethod(int self_num) {
		try {
			conn = init();
			String sql = "delete from selfrecipe where self_num = " + self_num + "";
			pstmt = conn.prepareStatement(sql);
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
	
	public void irdntDeleteMethod(int self_num) {
		try {
			conn = init();
			String sql = "delete from irdnt where recipe_id in ";
			sql += "(select recipe_id from selfrecipe where self_num = " + self_num + ")";
			pstmt = conn.prepareStatement(sql);
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
	
	public void stepDeleteMethod(int self_num) {
		try {
			conn = init();
			String sql = "delete from step where recipe_id in ";
			sql += "(select recipe_id from selfrecipe where self_num = " + self_num + ")";
			pstmt = conn.prepareStatement(sql);
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
			String sql = "select img_url from primary where recipe_id = " + recipe_id +"";
			pstmt = conn.prepareStatement(sql);
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
	public void readCountMethod(int num) {
		try {
			conn = init();
			String sql = "update board set readcount = readcount + 1 where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
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
