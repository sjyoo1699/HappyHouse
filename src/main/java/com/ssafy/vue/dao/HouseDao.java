package com.ssafy.vue.dao;

import java.util.List;

import com.ssafy.vue.dto.House;
import com.ssafy.vue.dto.HousePageBean;

public interface HouseDao {

	public List<House> selectHouse(HousePageBean bean);

	public House selectHouseByNo(int no);

	public int getTotalCount(HousePageBean bean);

	public List<String> selectDong();

	public List<House> selectByPrice(HousePageBean bean);
	
	public int getTotalCountByPrice(HousePageBean bean);

}
