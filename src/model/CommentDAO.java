package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO extends RecipeDAO {
	private ResultSet rs;
	private PreparedStatement pstmt;
	
	public CommentDAO() {
		super();
	}

	
	
	public List<CommentDTO> listMethod(String com_board, int key){
		List<CommentDTO> aList = new ArrayList<CommentDTO>();
		String sql = "select c.user_id,com_time,com_content,rating,user_icon,user_nickname "
				+ "from recipe_comment c, user_table u "
				+ "where com_board='"+com_board+"'";
		if(com_board.equalsIgnoreCase("primary")||com_board.equalsIgnoreCase("selfrecipe")) {
				sql += " and recipe_id='"+key+"' ";
		}
		if(com_board.equalsIgnoreCase("review")) {
			sql += " and review_num='"+key+"' ";
		}
		sql += "and c.user_id = u.user_id order by com_num";
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				CommentDTO dto = new CommentDTO();
				dto.setUser_id(rs.getString(1));
				dto.setCom_time(rs.getString(2));
				dto.setCom_content(rs.getString(3));
				dto.setRating(rs.getInt(4));
				dto.setUser_icon(rs.getString(5));
				dto.setUser_nickname(rs.getString(6));
				aList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return aList;
	}//end listmethod
	
	public void insertCom(CommentDTO dto, int key) {
		String com_board = dto.getCom_board();
		String column = "";
		if(com_board.equalsIgnoreCase("primary")||com_board.equalsIgnoreCase("selfrecipe")) {
			column = "recipe_id";
		}
		if(com_board.equalsIgnoreCase("review")) {
			column = "review_num";
		}	
		String sql = "insert into recipe_comment(com_num,com_content,user_id,com_board,rating,"+column+") "
				+ "values (com_num_seq.nextval,?,?,?,?,?)";
		try {
			pstmt = updatePstmt(sql);
			pstmt.setString(1, dto.getCom_content());
			pstmt.setString(2, dto.getUser_id());
			pstmt.setString(3, com_board);
			pstmt.setInt(4, dto.getRating());
			pstmt.setInt(5, key);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
