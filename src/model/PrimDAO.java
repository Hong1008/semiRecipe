package model;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import common.JdbcTemplate;

public class PrimDAO extends RecipeDAO {
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool)이다.
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public PrimDAO() {
		super();
	}
	
	public List<String> getNation_nms() {
		List<String> nms = new ArrayList<String>();
		String sql = "select distinct(nation_nm) from primary where recipe_type='p'";
		
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				nms.add(rs.getString(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nms;
	}
	
	public List<PrimDTO> listView(){
		List<PrimDTO> aList = new ArrayList<PrimDTO>();
		String sql = "select recipe_nm_ko, img_url, prim_views, rating, recipe_id,nation_nm from primary where recipe_type = 'p'";
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				PrimDTO dto = new PrimDTO();
				dto.setRECIPE_NM_KO(rs.getString(1));
				String url = rs.getString(2);
				dto.setIMG_URL(url);
				dto.setPRIM_VIEWS(rs.getInt(3));
				dto.setRATING(rs.getString(4));
				dto.setRECIPE_ID(rs.getInt(5));
				dto.setNATION_NM(rs.getString(6));
				aList.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aList;
	}
	
	public PrimDTO listView(int recipe_id){
		PrimDTO dto = new PrimDTO();
		String sql = "select recipe_nm_ko, img_url, prim_views, rating, nation_nm, ty_nm, cooking_time, calorie, level_nm from primary where recipe_type = 'p' and recipe_id = "+recipe_id;
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				dto.setRECIPE_NM_KO(rs.getString(1));
				String url = rs.getString(2);
				dto.setIMG_URL(url);
				dto.setPRIM_VIEWS(rs.getInt(3));
				dto.setRATING(rs.getString(4));
				dto.setNATION_NM(rs.getString(5));
				dto.setTY_NM(rs.getString(6));
				dto.setCOOKING_TIME(rs.getString(7));
				dto.setCALORIE(rs.getString(8));
				dto.setLEVEL_NM(rs.getString(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			exit();
		}
		return dto;
	}
	
	public List<PrimDTO> sortView(String column, String order, String nation_nm){
		List<PrimDTO> aList = new ArrayList<PrimDTO>();
		String sql = "select recipe_nm_ko, img_url, prim_views, rating, recipe_id, nation_nm from primary where recipe_type = 'p' ";
		if(nation_nm!= null && !nation_nm.isEmpty()) {
			sql += "and nation_nm = '"+nation_nm+"' ";
		}
		if(column!=null && !column.isEmpty() && order!=null && !order.isEmpty()) {
			sql += "order by "+column+" "+order;
		}
		try {
			rs = queryStmt(sql);
			while(rs.next()) {
				PrimDTO dto = new PrimDTO();
				dto.setRECIPE_NM_KO(rs.getString(1));
				String url = rs.getString(2);
				dto.setIMG_URL(url);
				dto.setPRIM_VIEWS(rs.getInt(3));
				dto.setRATING(rs.getString(4));
				dto.setRECIPE_ID(rs.getInt(5));
				dto.setNATION_NM(rs.getString(6));
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

	public List<PrimDTO> revSearch(String recipe_nm_ko) {
		List<PrimDTO> aList = new ArrayList<PrimDTO>();
		String sql = "select recipe_id, recipe_nm_ko from primary where recipe_type='p' and recipe_nm_ko like '%' || ? || '%'";
		try {
			conn = JdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, recipe_nm_ko);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				PrimDTO dto = new PrimDTO();
				dto.setRECIPE_ID(rs.getInt("RECIPE_ID"));
				dto.setRECIPE_NM_KO(rs.getString("RECIPE_NM_KO"));
				aList.add(dto);
				System.out.println(rs.getString("RECIPE_NM_KO")+"   dto");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
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
				sql += "select recipe_id from irdnt where recipe_type = 'p' and irdnt_nm = '" + jarr.get(i).toString().replace("\"", "") + "')";
				break;
			}
			sql += "select recipe_id from irdnt where recipe_type = 'p' and irdnt_nm = '" + jarr.get(i).toString().replace("\"", "") + "' intersect ";
		}

		try {
			rs = queryStmt(sql);
			if (!rs.next()) {
				sql = "select * from primary where recipe_type = 'p' and recipe_id in " + "(select recipe_id from irdnt where irdnt_nm in (";
				for (int i = 0; i < jarr.size(); i++) {
					if (i == jarr.size() - 1) {
						sql += "'" + jarr.get(i).toString().replace("\"", "") + "') and irdnt_ty_nm = '주재료')";
						break;
					}
					sql += "'" + jarr.get(i).toString().replace("\"", "") + "',";
				}
				rs = queryStmt(sql);
			}
			rs.previous();
			while (rs.next()) {
				PrimDTO dto = new PrimDTO();
				dto.setRECIPE_ID(rs.getInt("RECIPE_ID"));
				dto.setRECIPE_NM_KO(rs.getString("RECIPE_NM_KO"));
				dto.setIMG_URL(rs.getString("IMG_URL"));
				aList.add(dto);
			}
			System.out.println(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aList;
	}
	
}// end searchView()