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
			String sql = "select rownum as rn, a.* from (select review_subject, review_url, review_num from review order by review_num desc)a";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setReview_subject(rs.getString("review_subject"));
				dto.setReview_url(rs.getString("review_url"));
				dto.setReview_num(rs.getInt("rn"));
				aList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();			
		}
		return aList;
	}//end listmethod
	
	public void reviewWriteMethod(ReviewDTO dto) {
		String sql = "insert into review(review_num, review_subject, review_content, values() ";
	}
}//end class
