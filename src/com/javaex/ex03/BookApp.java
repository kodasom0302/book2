package com.javaex.ex03;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao=new AuthorDao();
		
		authorDao.authorInsert("서장훈", "농구선수");
		authorDao.authorInsert("안정환", "축구선수");
		
		AuthorVo authorVo=new AuthorVo("고다솜", "개발원생");
		authorDao.authorInsert(authorVo);
		
		authorDao.authorDelete(15);
		authorDao.authorDelete(16);
		authorDao.authorDelete(17);
		
		List<AuthorVo> authorList=authorDao.authorList();
		for (AuthorVo vo:authorList) {
			System.out.println(vo.getAuthorId()+","
							  +vo.getAuthorName()+","
							  +vo.getAuthorDesc());
		}
		
		authorDao.authorUpdate("강태현", "막내", 18);
		BookDao bookDao=new BookDao();
		bookDao.bookInsert(10, "책제목", "출판사", "출판일", 10);
		bookDao.bookInsert(11, "제목", "출판사", "출판일", 5);
		bookDao.bookDelete(10);
		bookDao.bookUpdate("아기 돼지 삼형제", "미상", "2024-01-31", 1, 11);
		bookDao.bookList();
		bookDao.bookAllList();
		bookDao.bookSelectOne(2);
		/*
		authorDao.getConnection();
		authorDao.close();
		*/
	}

}
