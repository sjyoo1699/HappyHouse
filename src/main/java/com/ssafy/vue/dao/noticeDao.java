package com.ssafy.vue.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.notice;


public interface noticeDao {

	List<notice> listnotice() throws SQLException;
	notice contentNotice(int content) throws SQLException;
	void noticeWrite(notice nt) throws SQLException;

}
