package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

public class CommentDAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private CommentDAO() {

	}

	private static CommentDAO dao = new CommentDAO();

	public static CommentDAO getInstance() {
		return dao;
	}
	
	private Connection init() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin://@127.0.0.1:1521:xe";
		String user = "hr";
		String password = "a1234";
		return DriverManager.getConnection(url, user, password);
	}// end init()

	private void exit() throws SQLException {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}// end exit()
	
	public List<CommentDTO> listMethod(HashMap<String, String> map){
		List<CommentDTO> aList = new ArrayList<CommentDTO>();
		
		try {
			String sql = "select user_id,com_time,com_content "
					+ "from recipe_comment "
					+ "where com_board='"+map.get("BoardName")+"'and recipe_id=? "
					+ "order by com_num";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(map.get("recipe_id")));
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setUser_id(rs.getString("USER_ID"));
				dto.setCom_num(rs.getInt("COM_NUM"));
				aList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return aList;
	}//end listmethod
	
	
}
