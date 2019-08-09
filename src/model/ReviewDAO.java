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
			String sql = "select rownum as rn, a.* from (select review_subject, review_url, review_num, user_nickname from review order by review_num desc)a";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setReview_subject(rs.getString("review_subject"));
				dto.setReview_url(rs.getString("review_url"));
				dto.setReview_num(rs.getInt("rn"));
				dto.setUser_nickname(rs.getString("user_nickname"));
				aList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();			
		}
		return aList;
	}//end listmethod
	
	public void insertMethod(ReviewDTO dto) {
		String sql = "insert into review(review_num, review_content, review_rate, review_date, review_views, review_url, user_id, recipe_id,review_subject, user_nickname) "
				+ "values(review_num_sequ.nextval, ?, ?, sysdate, 809, 'http://localhost:8090/semiRecipe/review/images/thumbs/04.jpg', ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getReview_content());
			pstmt.setString(2, dto.getReview_rate());
			pstmt.setString(3, dto.getUser_id());
			pstmt.setInt(4, dto.getRecipe_id());
			pstmt.setString(5, dto.getReview_subject());
			pstmt.setString(6, dto.getUser_nickname());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();
		}
	}//end insert
	
	public ReviewDTO viewMethod(int review_num){
		ReviewDTO dto=new ReviewDTO();
//		String sql = "select review_subject, review_rate, review_date, review_views, review_url, user_nickname, RECIPE_ID from review where review_num=?";
		String sql = "select * from (select rownum as rn, a.* from " + 
				"(select review_subject, review_content, review_rate, review_date, review_views, review_url, user_nickname, RECIPE_ID " + 
				"from review " + 
				"order by review_num desc)a)b\n" + 
				"where rn=?";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setReview_subject(rs.getString("review_subject"));
				dto.setReview_content(rs.getString("review_content"));
				dto.setReview_rate(rs.getString("review_rate"));
				dto.setReview_date(rs.getDate("review_date"));
				dto.setReview_views(rs.getInt("review_views"));
				dto.setReview_url(rs.getString("review_url"));
				dto.setUser_nickname(rs.getString("user_nickname"));
				dto.setRecipe_id(rs.getInt("recipe_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();
		}		
		return dto;
	}//end viewMethod
}//end class
