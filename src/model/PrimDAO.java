package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class PrimDAO extends RecipeDAO {
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool)이다.
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public PrimDAO() {
		super();
	}
	
	public List<PrimDTO> listView(){
		List<PrimDTO> aList = new ArrayList<PrimDTO>();
		String sql = "select recipe_nm_ko, img_url from primary where recipe_type = 'p'";
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				PrimDTO dto = new PrimDTO();
				dto.setRECIPE_NM_KO(rs.getString(1).replaceAll("\"", ""));
				String url = rs.getString(2);
				if(url!=null && !url.isEmpty()) {
					url = url.replaceAll("\"", "");
				}
				dto.setIMG_URL(url);
				aList.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			exit();
		}
		return aList;
	}
	
	public List<PrimDTO> sortView(String column, String order){
		List<PrimDTO> aList = new ArrayList<PrimDTO>();
		String sql = "select recipe_nm_ko, img_url from primary where recipe_type = 'p' order by "+column+" "+order;
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				PrimDTO dto = new PrimDTO();
				dto.setRECIPE_NM_KO(rs.getString(1).replaceAll("\"", ""));
				String url = rs.getString(2);
				if(url!=null && !url.isEmpty()) {
					url = url.replaceAll("\"", "");
				}
				dto.setIMG_URL(url);
				aList.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			exit();
		}
		return aList;
	}

	public List<PrimDTO> searchRecipe(String irdnt_nm) {
		List<PrimDTO> aList = new ArrayList<PrimDTO>();
		String sql = "select * from primary where recipe_type = 'p' and recipe_id in(";
		JsonParser pas = new JsonParser();
		JsonArray jarr = (JsonArray) pas.parse(irdnt_nm);
		for (int i = 0; i < jarr.size(); i++) {
			if (i == jarr.size() - 1) {
				sql += "select recipe_id from irdnt where irdnt_nm = '" + jarr.get(i) + "')";
				break;
			}
			sql += "select recipe_id from irdnt where irdnt_nm = '" + jarr.get(i) + "' intersect ";
		}

		try {
			rs = queryStmt(sql);
			if (!rs.next()) {
				sql = "select * from primary where recipe_id in " + "(select recipe_id from irdnt where irdnt_nm in (";
				for (int i = 0; i < jarr.size(); i++) {
					if (i == jarr.size() - 1) {
						sql += "'" + jarr.get(i) + "') and irdnt_ty_nm = '\"주재료\"')";
						break;
					}
					sql += "'" + jarr.get(i) + "',";
				}
				rs = queryStmt(sql);
			}
			rs.previous();
			while (rs.next()) {
				PrimDTO dto = new PrimDTO();
				dto.setRECIPE_ID(rs.getInt("RECIPE_ID"));
				dto.setRECIPE_NM_KO(rs.getString("RECIPE_NM_KO").replaceAll("\"", ""));
				dto.setSUMRY(rs.getString("SUMRY").toString().replaceAll("\"", ""));
				dto.setNATION_CODE(rs.getString("NATION_CODE").replaceAll("\"", ""));
				dto.setNATION_NM(rs.getString("NATION_NM").replaceAll("\"", ""));
				dto.setTY_CODE(rs.getString("TY_CODE").replaceAll("\"", ""));
				dto.setTY_NM(rs.getString("TY_NM").replaceAll("\"", ""));
				dto.setCOOKING_TIME(rs.getString("COOKING_TIME").replaceAll("\"", ""));
				dto.setCALORIE(rs.getString("CALORIE").replaceAll("\"", ""));
				dto.setQNT(rs.getString("QNT").replaceAll("\"", ""));
				dto.setLEVEL_NM(rs.getString("LEVEL_NM").replaceAll("\"", ""));
				dto.setIRDNT_CODE(rs.getString("IRDNT_CODE").replaceAll("\"", ""));
				dto.setPC_NM(rs.getString("PC_NM").replaceAll("\"", ""));
				dto.setIMG_URL(rs.getString("IMG_URL").replaceAll("\"", ""));
				dto.setDET_URL(rs.getString("DET_URL").replaceAll("\"", ""));
				aList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}
}// end searchView()