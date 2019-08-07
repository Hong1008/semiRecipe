package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;


public class IrdntDAO extends RecipeDAO {
	private DataSource ds; // DataSource ds  는  아파치톰캣이  제공하는 DBCP(DB Connection Pool)이다.
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public IrdntDAO() {
		super();
	}
	
	public List<IrdntDTO> mainList(int recipe_id){
		List<IrdntDTO> aList = new ArrayList<IrdntDTO>();
		String sql = "select irdnt_nm, irdnt_cpcty, importance, recipe_id from irdnt where recipe_id = "+recipe_id+" and irdnt_ty_nm != '양념'";
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				IrdntDTO dto = new IrdntDTO();
				dto.setIRDNT_NM(rs.getString(1));
				dto.setIRDNT_CPCTY(rs.getString(2));
				dto.setIMPORTANCE(rs.getString(3));
				dto.setRECIPE_ID(rs.getInt(4));
				aList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}

	public List<IrdntDTO> subList(int recipe_id) {
		List<IrdntDTO> aList = new ArrayList<IrdntDTO>();
		String sql = "select irdnt_nm, irdnt_cpcty, importance, recipe_id from irdnt where recipe_id = "+recipe_id+"and irdnt_ty_nm = '양념'";
		try {
			rs =queryStmt(sql);
			while(rs.next()) {
				IrdntDTO dto = new IrdntDTO();
				dto.setIRDNT_NM(rs.getString(1));
				dto.setIRDNT_CPCTY(rs.getString(2));
				dto.setIMPORTANCE(rs.getString(3));
				dto.setRECIPE_ID(rs.getInt(4));
				aList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}

}
