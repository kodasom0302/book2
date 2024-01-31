package com.javaex.ex02;

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
		
		/*
		authorDao.getConnection();
		authorDao.close();
		*/

	}

}
