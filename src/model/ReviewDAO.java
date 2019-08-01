package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import common.JdbcTemplate;

public class ReviewDAO {
	private static Connection conn;
	private static Statement stmt;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	
	public ReviewDAO() {
		try {
			conn = JdbcTemplate.getConnection();
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void exit() {
		JdbcTemplate.close(rs);
		JdbcTemplate.close(pstmt);
		JdbcTemplate.close(stmt);
		JdbcTemplate.close(conn);
	}
	
	public List<ReviewDTO> listMethod(){
		List<ReviewDTO> aList = new ArrayList<ReviewDTO>();
		
		try {
			String sql = "select review_subject, review_url from review order by review_num";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setReview_subject(rs.getString("review_subject"));
				dto.setReview_url(rs.getString("review_url"));
				aList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();			
		}
		return aList;
	}//end listmethod
}//end class
