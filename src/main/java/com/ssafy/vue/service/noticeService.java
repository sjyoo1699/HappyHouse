package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.notice;



public interface noticeService {

	public List<notice> listnotice() throws Exception;
	public notice contentNotice(int content) throws Exception;
	public void writeNotice(notice nt) throws Exception;
}
