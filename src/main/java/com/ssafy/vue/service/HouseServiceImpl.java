package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dao.HouseDao;
import com.ssafy.vue.dto.House;
import com.ssafy.vue.dto.HousePageBean;
import com.ssafy.vue.dto.PageNavigation;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseDao houseDao;
	
	@Override
	public List<House> selectHouse(HousePageBean bean) {
		return houseDao.selectHouse(bean);
	}

	@Override
	public House selectHouseByNo(int no) {
		return houseDao.selectHouseByNo(no);
	}

	@Override
	public PageNavigation makePageNavigation(HousePageBean bean) {
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10;
		pageNavigation.setCurrentPage(bean.getCurrentPage());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = houseDao.getTotalCount(bean);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = ((totalCount - 1) / bean.getSizePerPage()) + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = bean.getCurrentPage() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < bean.getCurrentPage();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	public List<String> selectDong() {
		return houseDao.selectDong();
	}

	@Override
	public List<House> selectByPrice(HousePageBean bean) {
		return houseDao.selectByPrice(bean);
	}

	@Override
	public PageNavigation makePageNavigationByPrice(HousePageBean bean) {
		PageNavigation pageNavigation = new PageNavigation();
		int naviSize = 10;
		pageNavigation.setCurrentPage(bean.getCurrentPage());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = houseDao.getTotalCountByPrice(bean);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = ((totalCount - 1) / bean.getSizePerPage()) + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = bean.getCurrentPage() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < bean.getCurrentPage();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

}
