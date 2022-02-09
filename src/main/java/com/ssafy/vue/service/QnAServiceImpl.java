package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dao.QnADAO;
import com.ssafy.vue.dto.QnA;

@Service
public class QnAServiceImpl implements QnAService {

	@Autowired
	private QnADAO dao;
	
	@Override
	public List<QnA> retrieveQnA() {
		return dao.selectQnA();
	}

	@Override
	public QnA detailQnA(int no) {
		return dao.selectQnAByNo(no);
	}

	@Override
	public boolean writeQnA(QnA board) {
		return dao.insertQnA(board) == 1;
	}

	@Override
	public boolean updateQnA(QnA board) {
		return dao.updateQnA(board) == 1;
	}

	@Override
	public boolean deleteQnA(int no) {
		return dao.deleteQnA(no) == 1;
	}

}
