package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.PageNavigation;
import com.ssafy.vue.dto.House;
import com.ssafy.vue.dto.HousePageBean;

public interface HouseService {

	public List<House> selectHouse(HousePageBean bean);

	public House selectHouseByNo(int no);

	public PageNavigation makePageNavigation(HousePageBean bean);

	public List<String> selectDong();

	public List<House> selectByPrice(HousePageBean bean);
	
	public PageNavigation makePageNavigationByPrice(HousePageBean bean);
	
}
