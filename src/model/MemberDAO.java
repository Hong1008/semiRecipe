package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MemberDAO {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private MemberDAO() {

	}

	private static MemberDAO dao = new MemberDAO();

	public static MemberDAO getInstance() {
		return dao;
	}

	private Connection init() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.OracleDriver");
		String url = "jdbc:oracle:thin://@127.0.0.1:1521:xe";
		String user = "hr";
		String password = "a1234";
		return DriverManager.getConnection(url, user, password);
	}// end init()

	private void exit() throws SQLException {
		if (rs != null)
			rs.close();
		if (stmt != null)
			stmt.close();
		if (pstmt != null)
			pstmt.close();
		if (conn != null)
			conn.close();
	}// end exit()
	
	public void registerMethod(MemberDTO dto) {
		try {
			conn = init();
			String sql = "insert into semimember values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getNickname());
			pstmt.setDate(4, dto.getBirthday());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String checkID(String id) {
		String chk = "";
		try {
			conn = init();
			String sql = "select id from semimember where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // true이면
				chk = "이미 사용중인 아이디 입니다.";
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return chk;
	}
	
	public String checkNickname(String nickname) {
		String chk = "";
		try {
			conn = init();
			String sql = "select id from semimember where nickname = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // true이면
				chk = "이미 사용중인 닉네임 입니다.";
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return chk;
	}
	
	public int login(MemberDTO dto) {
		int cnt=0;
		try {
			conn = init();
			String sql = "select count(id) from semimember where id=? and pw=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	
	public MemberDTO myPage(String sessionChkId) {
		MemberDTO dto = new MemberDTO();
		
		try {
			conn=init();
			String sql = "select * from semimember where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, sessionChkId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setNickname(rs.getString("nickname"));
				dto.setBirthday(rs.getDate("birthday"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				exit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}
	
	public void update(MemberDTO dto) {
		try {
			conn = init();
			String sql = "update semimember set "+dto.getUpdateName()+"= ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			if(dto.getUpdateName().equals("pw")) {
				pstmt.setString(1, dto.getPw());
			} else if(dto.getUpdateName().equals("nickname")) {
				pstmt.setString(1, dto.getNickname());
			} else if(dto.getUpdateName().equals("birthday")) {
				pstmt.setDate(1, dto.getBirthday());
			}
			pstmt.setString(2, dto.getId());
			rs = pstmt.executeQuery();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
