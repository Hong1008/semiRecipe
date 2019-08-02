package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class IrdntTYDAO extends RecipeDAO{
	private ResultSet rs;
	private PreparedStatement pstmt;

	public IrdntTYDAO() {
		super();
	}
	
	public List<IrdntTYDTO> list(){
		List<IrdntTYDTO> aList = new ArrayList<IrdntTYDTO>();
		String sql = "select * from irdnt_type";
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				IrdntTYDTO dto = new IrdntTYDTO();
				dto.setIrdnt_nm(rs.getString(1).replaceAll("/", "").replaceAll("\\(", "")
						.replaceAll("\\)", "").replaceAll("\\[", "").replaceAll("\\]", ""));
				dto.setTy_nm(rs.getString(2));
				aList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}
	
	public List<String> tyList(){
		List<String> tList = new ArrayList<String>();
		String sql = "select distinct(type_nm) from irdnt_type order by type_nm desc";
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				tList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tList;
	}
}
