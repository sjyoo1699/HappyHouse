package com.ssafy.vue.dao;

import java.util.List;

import com.ssafy.vue.dto.QnA;

public interface QnADAO {
	List<QnA> selectQnA();
	public QnA selectQnAByNo(int no);
	public int insertQnA(QnA board);
	public int updateQnA(QnA board);
	public int deleteQnA(int no);
}
