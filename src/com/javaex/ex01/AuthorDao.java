package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	
	//필드
	//생성자 - 디폴트
	//메소드 gs
	//메소드 - 일반
	//작가 리스트
	public List<AuthorVo> authorList() {
		//리스트 만들기
		//db에서 데이터 가져오기
		//리스트에 추가
		//리스트 주소 전달하기
		
		//리스트 준비
		List<AuthorVo> authorList=new ArrayList<AuthorVo>();
		
		//db에서 데이터 가져오기
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += " select   author_id, ";
			query += " 			author_name, ";
			query += " 			author_desc ";
			query += " from author ";
			
			pstmt=conn.prepareStatement(query);
			
			rs=pstmt.executeQuery();
			
		// 4.결과처리
			while (rs.next()) {
				int no=rs.getInt("author_id");
				String name=rs.getString("author_name");
				String desc=rs.getString("author_desc");
				
				AuthorVo authorVo=new AuthorVo(no, name, desc);
				
				authorList.add(authorVo);
			}
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
		try {
		if (rs != null) {
		rs.close();
		}
		if (pstmt != null) {
		pstmt.close();
		}
		if (conn != null) {
		conn.close();
		}
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		}
		
		return authorList;
		
	}//authorList()
	
	//작가 삭제
	public void authorDelete(int no) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query+=" delete from author ";
			query+=" where author_id=? ";
			
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			int count=pstmt.executeUpdate();
			
		// 4.결과처리
			System.out.println(count+"건 삭제 되었습니다.");
			
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
		try {
		if (rs != null) {
		rs.close();
		}
		if (pstmt != null) {
		pstmt.close();
		}
		if (conn != null) {
		conn.close();
		}
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		}
	}//authorDelete()
	
	//작가 등록
	public void authorInsert(String name, String desc) {
		//book_db 데이터 베이스에 접속
		//아이디 : book, 비밀번호 : book
		//author 테이블에 작가를 추가(=insert 인서트) 이름/설명
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			
		// 3. SQL문 준비 / 바인딩 / 실행
			String query="";
			query+=" insert into author ";
			query+=" values (null, ?, ?) ";
			
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			
			int count=pstmt.executeUpdate();
			
		// 4.결과처리
			System.out.println(count+"건 입력 되었습니다.");
			
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
		// 5. 자원정리
		try {
		if (rs != null) {
		rs.close();
		}
		if (pstmt != null) {
		pstmt.close();
		}
		if (conn != null) {
		conn.close();
		}
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		}
	}//authorInsert()

}
