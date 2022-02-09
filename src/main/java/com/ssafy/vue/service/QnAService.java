package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.QnA;

public interface QnAService {
	List<QnA> retrieveQnA();
	public QnA detailQnA(int no);
	public boolean writeQnA(QnA board);
	public boolean updateQnA(QnA board);
	public boolean deleteQnA(int no);
}
