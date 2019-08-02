package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import common.JdbcTemplate;

public class IrdntDAO extends RecipeDAO {
	private DataSource ds; // DataSource ds  는  아파치톰캣이  제공하는 DBCP(DB Connection Pool)이다.
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public IrdntDAO() {
		super();
	}
	
	public List<IrdntDTO> list(){
		List<IrdntDTO> aList = new ArrayList<IrdntDTO>();
		String sql = "select distinct(irdnt_nm), irdnt_ty_code from irdnt";
		try {
			queryStmt(sql);
			rs =stmt.executeQuery(sql);
			while(rs.next()) {
				IrdntDTO dto = new IrdntDTO();
				dto.setIRDNT_NM(rs.getString(1));
				dto.setIRDNT_TY_CODE(rs.getString(2));
				aList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}

}
