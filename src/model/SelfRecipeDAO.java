package model;

import java.sql.Connection;
import java.sql.Date;
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

	
	// 
	public List<SelfRecipeDTO> listMethod(String recipe_nm) {
		List<SelfRecipeDTO> aList = new ArrayList<SelfRecipeDTO>();
		try {
			conn = init();
			String sql = "select recipe_nm_ko, sumry, img_url, ty_nm, cooking_time, calorie, level_nm, importance, cooking_no ";
			sql += "from primary pm, selfrecipe sf, step sp, irdnt ir ";
			sql += "where pm.recipe_id = sf.recipe_id and pm.recipe_id = sp.recipe_id and pm.recipe_id = ir.recipe_id and recipe_nm_ko = '%" + recipe_nm + "%'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				SelfRecipeDTO dto = new SelfRecipeDTO();
				dto.setRecipe_nm_ko(rs.getString("recipe_nm_ko"));
				dto.setSumry(rs.getString("sumry"));
				dto.setImg_url(rs.getString("img_url"));
				dto.setTy_nm(rs.getString("ty_nm"));
				dto.setCooking_time(rs.getString("cooking_time"));
				dto.setCalorie(rs.getString("calorie"));
				dto.setLevel_nm(rs.getString("level_nm"));
				dto.setImportance(rs.getString("importance"));
				dto.setCooking_no(rs.getInt("cooking_no"));
				aList.add(dto);
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
		return aList;
	}// end listMethod()
	
	public void viewMethod() {
		
	}// end viewMethod()
	
	
	//글쓰기 
	public void insertMethod(SelfRecipeDTO dto) {
		try {
			conn = init();
			String sql = "insert all ";
			sql += "into selfrecipe(recipe_id, user_id, self_date) values (?, ?, ?) ";
			sql += "into primary(recipe_nm_ko, sumry, img_url, ty_nm, cooking_time, calorie, level_nm) ";
			sql += "values(?, ?, ?, ?, ?, ?, ?, ?) ";
			sql += "into irdnt(importance) values (?) ";
			sql += "into step(step) values(?) ";
			sql += "from primary p, irdnt i, step s, selfrecipe sr ";
			sql += "where p.recipe = i.recipe and p.recipe = i.recipe and p.recipe = se.recipe and recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getRecipe_nm_ko());
			pstmt.setString(2, dto.getSumry());
			pstmt.setString(3, dto.getImg_url());
			pstmt.setString(4, dto.getTy_nm());
			pstmt.setString(5, dto.getCooking_time());
			pstmt.setString(6, dto.getCalorie());
			pstmt.setString(7, dto.getLevel_nm());
			pstmt.setString(8, dto.getImportance());
			pstmt.setInt(9, dto.getCooking_no());
			pstmt.setString(10,dto.getUser_id());
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
	}// end insertMethod()
	
	
	// 수정하기
	public void updateMethod(SelfRecipeDTO dto) {
		try {
			conn = init();
			String sql = "update selfrecipe set recipe_nm_ko = ?, sumry = ?, img_url = ?', ty_nm = ?, ";
			sql += "cooking_time = ?, calorie = ?, level_nm = ?, importance = ?, cooking_no = ? ";
			sql += "where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			;
			pstmt.setString(1, dto.getRecipe_nm_ko());
			pstmt.setString(2, dto.getSumry());
			pstmt.setString(3, dto.getImg_url());
			pstmt.setString(4, dto.getTy_nm());
			pstmt.setString(5, dto.getCooking_time());
			pstmt.setString(6, dto.getCalorie());
			pstmt.setString(7, dto.getLevel_nm());
			pstmt.setString(8, dto.getImportance());
			pstmt.setInt(9, dto.getCooking_no());
			pstmt.setInt(10, dto.getRecipe_id());
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
	
	
	// 삭제하기
	public void deleteMethod(SelfRecipeDTO dto) {
		try {
			conn = init();
			String sql = "delete from selfreceipe where where recipe_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getRecipe_id());
			pstmt.executeQuery();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}// end deleteMethod()

	// 조회수 증가
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
	}// end readCountMethod() 조회수 올리기

}// end class
