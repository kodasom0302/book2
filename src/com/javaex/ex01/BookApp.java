package com.javaex.ex01;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao=new AuthorDao();
		
		//작가 등록
		//authorDao.authorInsert("이효리", "제주도민");
		
		//작가 삭제
		//authorDao.authorDelete(14);
		
		//작가 리스트
		List<AuthorVo> authorList=authorDao.authorList();
		for (AuthorVo authorVo:authorList) {
			int id=authorVo.getAuthorId();
			String name=authorVo.getAuthorName();
			String desc=authorVo.getAuthorDesc();
			System.out.println(id+",	"+name+",	"+desc);
		}
		/*
		for (int i=0; i<authorList.size(); i++) {
			int id=authorList.get(i).getAuthorId();
			String name=authorList.get(i).getAuthorName();
			String desc=authorList.get(i).getAuthorDesc();
			System.out.println(id+",	"+name+",	"+desc);
		}
		
		System.out.println(authorList.size()+"명의 작가가 등록되어 있습니다.");
		*/

	}

}
