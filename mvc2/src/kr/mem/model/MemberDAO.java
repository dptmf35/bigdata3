package kr.mem.model;

import java.sql.*;
import java.util.ArrayList;

//JDBC ---> myBatis
public class MemberDAO {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	// 초기화 블럭
	static {
		try { // DriverManager
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public Connection getConn() { // 연결 객체
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "hr";
		String password = "hr";

		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return conn;

	}

	public int memberInsert(MemberVO vo) {
		conn = getConn();
		int cnt = 0;
		String sql = "insert into tblMem values(seq_num.nextval,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, vo.getName());
			pst.setString(2, vo.getPhone());
			pst.setString(3, vo.getAddr());
			pst.setDouble(4, vo.getLat());
			pst.setDouble(5, vo.getLng());
			cnt = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}

		return cnt;

	}

	public ArrayList<MemberVO> memberAllList() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		conn = getConn();
		String sql = "select * from tblMem order by num desc";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				int num = rs.getInt(1);
				String name = rs.getString(2);
				String phone = rs.getString(3);
				String addr = rs.getString(4);
				double lat = rs.getDouble(5);
				double lng = rs.getDouble(6);
				MemberVO vo = new MemberVO(num, name, phone, addr, lat, lng);
				list.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}

		return list;
	}

	public int memberDelete(int num) {
		conn = getConn();
		int cnt = 0;
		String sql = "delete from tblMem where num = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, num);
			cnt = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return cnt;
	}
	
	public MemberVO memberContent(int num) {
		MemberVO vo = null;
		conn = getConn();
		String sql = "select * from tblMem where num = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, num);
			rs = pst.executeQuery();
			if(rs.next()) {
			num = rs.getInt(1);
			String name = rs.getString(2);
			String phone = rs.getString(3);
			String addr = rs.getString(4);
			double lat = rs.getDouble(5);
			double lng = rs.getDouble(6);
			vo = new MemberVO(num, name, phone, addr, lat, lng);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}
	
	public int memberUpdate(MemberVO vo) {
		conn = getConn();
		int cnt = 0;
		String sql = "update tblMem set phone = ?, addr = ? where num = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, vo.getPhone());
			pst.setString(2, vo.getAddr());
			pst.setInt(3, vo.getNum());
			cnt = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return cnt;
		
	}
	

	public void close() {

		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
