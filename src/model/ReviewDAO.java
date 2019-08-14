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
	
	//글 목록 리뷰 첫 페이지
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
	
	//글 쓰기
	public void insertMethod(ReviewDTO dto) {
		String sql = "insert into review(review_num, review_content, review_rate, review_date, review_views, review_url, user_id, recipe_id,review_subject, user_nickname, recipe_nm_ko) "
				+ "values(review_num_sequ.nextval, ?, ?, sysdate, 0, 'http://localhost:8090/semiRecipe/review/images/basicImage.png', ?, ?, ?, ?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getReview_content());
			pstmt.setString(2, dto.getReview_rate());
			pstmt.setString(3, dto.getUser_id());
			pstmt.setInt(4, dto.getRecipe_id());
			pstmt.setString(5, dto.getReview_subject());
			pstmt.setString(6, dto.getUser_nickname());
			pstmt.setString(7, dto.getRecipe_nm_ko());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();
		}
	}//end insert
	
	//조회수 증가
	public void readCountMethod(int review_num) {
		
		try {
			String sql = "update review set review_views=review_views+1 "
					+ "where review_num=(select review_num from (select rownum as rn, a.* from " 
					+ "(select * from review order by review_num desc)a)b "
					+" where rn=?)";		
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review_num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	
	}//end readCountMethod
	
	//view 페이지(글 상세페이지) 
	public ReviewDTO viewMethod(int review_num){
		
		System.out.println("view 페이지(글 상세페이지) ");
		ReviewDTO dto=new ReviewDTO();
//		String sql = "select review_subject, review_rate, review_date, review_views, review_url, user_nickname, RECIPE_ID from review where review_num=?";
		String sql = "select * from (select rownum as rn, a.* from " + 
				"(select review_num,review_subject, review_content, review_rate, review_date, review_views, review_url, user_nickname, RECIPE_ID, recipe_nm_ko " + 
				"from review " + 
				"order by review_num desc)a)b " + 
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
				dto.setRecipe_nm_ko(rs.getString("recipe_nm_ko"));
				dto.setReview_num(rs.getInt("review_num"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			exit();
		}		
		return dto;
	}//end viewMethod
	
}//end class
