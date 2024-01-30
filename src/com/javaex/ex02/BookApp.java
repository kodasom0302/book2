package com.javaex.ex02;

public class BookApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao=new AuthorDao();
		authorDao.authorInsert("서장훈", "농구선수");
		
		/*
		authorDao.getConnection();
		authorDao.close();
		*/

	}

}
