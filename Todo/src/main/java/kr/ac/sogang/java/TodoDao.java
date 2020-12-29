package kr.ac.sogang.java;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
	private static String dburl = "jdbc:mysql://localhost:3306/firstdb?serverTimezone=Asia/Seoul&useSSL=false";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123";
	
	public int addTodo(TodoDto dto) {
		Connection conn;
		PreparedStatement ps=null;
		//int rs;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
			String sql="insert into todo (title,name,sequence,type) values (?,?,?,?)"; 
			ps=conn.prepareStatement(sql);
			ps.setString(1,dto.getTitle());
			ps.setString(2, dto.getName());
			ps.setInt(3, dto.getSequence());
			ps.setString(4, dto.getType());
			
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return 1;
	}
	public List<TodoDto> getTodos(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<TodoDto> list=new ArrayList<>();
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
			
			String sql="select id,title,name,sequence,type,regdate from todo order by sequence"; 
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				TodoDto dto=new TodoDto();
				dto.setId(rs.getInt("id"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setRegDate(rs.getString("regdate"));
				dto.setSequence(rs.getInt("sequence"));
				dto.setType(rs.getString("type"));
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			else {
				return null;
			}
		}
		return list;
		
	}
	public int updateTodo(TodoDto dto) {
		Connection conn;
		PreparedStatement ps=null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(dburl,dbUser,dbpasswd);
			
			String sql="update todo set type = ? where id = ?"; 
			ps=conn.prepareStatement(sql);
			ps.setString(1, dto.getType());
			ps.setLong(2, dto.getId());
			ps.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
}
