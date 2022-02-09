package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dao.noticeDao;
import com.ssafy.vue.dto.notice;


@Service
public class noticeServiceImpl implements noticeService{

	@Autowired
	private noticeDao nDao;
	
	@Override
	public List<notice> listnotice() throws Exception {
		return nDao.listnotice();
	}

	@Override
	public notice contentNotice(int content) throws Exception {
		
		return nDao.contentNotice(content);
	}

	@Override
	public void writeNotice(notice nt) throws Exception {
		nDao.noticeWrite(nt);		
	}

	

}
