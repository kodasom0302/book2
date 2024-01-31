package com.javaex.ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
	
	//필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver="com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/book_db";
	private String id="book";
	private String pw="book";
	//생성자 - 디폴트
	//메소드 - gs
	//메소드 - 일반
	public void getConnection() {
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
	}//getConnection()

	public void close() {
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
	}//close()
	
	public int bookInsert(int bookId, String title, String pubs, String pubsDate, int authorId) {
		
		int count=-1;
		
		this.getConnection();
		
		try {
			String query = "";
			query += " insert into book ";
			query += " values (?, ?, ?, ?, ?) ";
			
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, 10);
			pstmt.setString(2, "책 제목");
			pstmt.setString(3, "출판사");
			pstmt.setString(4, "출시일");
			pstmt.setInt(5, 7);
			
			count=pstmt.executeUpdate();
			
			System.out.println(count+"건 등록 되었습니다.");
			
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		
		this.close();
		
		return count;
	}//bookInsert()
	
	public int bookInsert(BookVo bookVo) {
		
		int count=-1;
		
		this.getConnection();
	
		try {
			String query = "";
			query += " insert into book ";
			query += " values (?, ?, ?, ?, ?) ";
			
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, bookVo.getBookId());
			pstmt.setString(2, bookVo.getTitle());
			pstmt.setString(3, bookVo.getPubs());
			pstmt.setString(4, bookVo.getPubDate());
			pstmt.setInt(5, bookVo.getAuthorId());
			
			count=pstmt.executeUpdate();
					
				System.out.println(count+"건 등록 되었습니다.");
					
		} catch (SQLException e) {
			System.out.println("error:" + e);
			}
		return count;
	}//bookInsert()
	
	public int bookDelete(int bookId) {
		int count=-1;
		this.getConnection();
		
		try {
			String query = "";
			query += " delete from book ";
			query += " where book_id=? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 9);
			
			count = pstmt.executeUpdate();
			
			System.out.println(count+"건이 삭제 되었습니다.");
			
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		this.close();
		return count;
	}//bookDelete()
	
	public int bookUpdate(String title, String pubs, String pubDate, int authorId, int bookId) {
		int count=-1;
		this.getConnection();
		try {
		String query = "";
		query += " update book ";
		query += " set title=?, ";
		query += " 	   pubs=? ";
		query += " 	   pubs_date=? ";
		query += " 	   author_id=? ";
		query += " where book_id=? ";
		
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, "책 제목");
		pstmt.setString(2, "출판사");
		pstmt.setString(3, "출판일");
		pstmt.setInt(5, 7);
		pstmt.setInt(6, 9);
		
		count=pstmt.executeUpdate();
		
		System.out.println(count+"건이 수정 되었습니다.");
		
	} catch (SQLException e) {
	System.out.println("error:" + e);
	}
		this.close();
		return count;
	}//bookUpdate()
	
	public List<BookVo> bookList() {
		this.getConnection();
		List<BookVo> bookList=new ArrayList<BookVo>();
		try {
		String query = "";
		query += " select   book_id, ";
		query += " 			title, ";
		query += " 			pubs, ";
		query += " 			pubs_date, ";
		query += " 			author_id ";
		query += " from book ";
		
		pstmt = conn.prepareStatement(query);
		
		rs=pstmt.executeQuery();
		while (rs.next()) {
			int bookId=rs.getInt("book_id");
			String title=rs.getString("title");
			String pubs=rs.getString("pubs");
			String pubsDate=rs.getString("pubs_date");
			int authorId=rs.getInt("author_id");
			
			BookVo bookVo=new BookVo(bookId, title, pubs, pubsDate, authorId);
			
			bookList.add(bookVo);
		}
		
	} catch (SQLException e) {
	System.out.println("error:" + e);
	}
	this.close();
	return bookList;
		
	}//bookSelect()
	
	public List<BookVo> bookAllList() {
		this.getConnection();
		List<BookVo> bookAllList=new ArrayList<BookVo>();
		try {
			String query="";
			query += " select book_id, ";
			query += "		  title, ";
			query += "		  pubs, ";
			query += "		  pubs_date, ";
			query += "		  author_id, ";
			query += "		  author_name, ";
			query += "		  author_desc ";
			query += " from book b, author a ";
			
			pstmt = conn.prepareStatement(query);
			
			rs=pstmt.executeQuery();
			while (rs.next()) {
				int bookId=rs.getInt("book_id");
				String title=rs.getString("title");
				String pubs=rs.getString("pubs");
				String pubsDate=rs.getString("pubs_date");
				int authorId=rs.getInt("author_id");
				String authorName=rs.getString("author_name");
				String authorDesc=rs.getString("author_desc");
				
				BookVo bookVo=new BookVo(bookId, title, pubs, pubsDate, authorId, authorName, authorDesc);
				
				bookAllList.add(bookVo);
			}
			
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		this.close();
		return bookAllList;
	}//bookSelectAll()
	
	public void bookSelectOne(int bookId) {
		this.getConnection();
		try {
			String query="";
			query += " select book_id, ";
			query += "		  title, ";
			query += "		  pubs, ";
			query += "		  pubs_date, ";
			query += "		  author_id, ";
			query += "		  author_name, ";
			query += "		  author_desc ";
			query += " from book b, author a ";
		
		pstmt=conn.prepareStatement(query);
		
		rs=pstmt.executeQuery();
		
		for (int i=0; i<bookId; i++) {
			rs.next();
		}
		
		bookId=rs.getInt(1);
		String title=rs.getString(2);
		String pubs=rs.getString(3);
		String pubsDate=rs.getString(4);
		int authorId=rs.getInt(5);
		String authorName=rs.getString(6);
		String authorDesc=rs.getString(7);
     
  } catch (SQLException e) {
  System.out.println("error:" + e);
  }
		this.close();
	}//bookSelectOne()

}
