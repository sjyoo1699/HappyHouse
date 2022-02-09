package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.House;
import com.ssafy.vue.dto.HousePageBean;
import com.ssafy.vue.dto.PageNavigation;
import com.ssafy.vue.service.HouseService;

import io.swagger.annotations.ApiOperation;

//http://localhost:8080/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/house")
public class HouseController {

	private static final Logger logger = LoggerFactory.getLogger(HouseController.class);
//	private static final String SUCCESS = "success";
//	private static final String FAIL = "fail";

	@Autowired
	private HouseService houseService;

	@ApiOperation(value = "조건에 맞는 모든 House의 정보를 반환한다.", response = List.class)
	@PostMapping
	public ResponseEntity<List<House>> selectHouse(@RequestBody HousePageBean bean) throws Exception {
		logger.debug("selectHouse - 호출");
		bean.setCurrentPage((bean.getCurrentPage()-1)*bean.getSizePerPage());
		System.out.println(bean.getCurrentPage());
		return new ResponseEntity<List<House>>(houseService.selectHouse(bean), HttpStatus.OK);
	}

	@ApiOperation(value = "번호에 해당하는 House의 정보를 반환한다.", response = House.class)
	@GetMapping("{no}")
	public ResponseEntity<House> selectHouseByNo(@PathVariable int no) {
		logger.debug("selectHouseByNo - 호출");
		return new ResponseEntity<House>(houseService.selectHouseByNo(no), HttpStatus.OK);
	}
	
	@ApiOperation(value = "페이징 정보를 반환한다.", response = PageNavigation.class)
	@PutMapping
	public ResponseEntity<PageNavigation> makePageNavigation(@RequestBody HousePageBean bean) throws Exception {
		logger.debug("makePageNavigation - 호출");
		return new ResponseEntity<PageNavigation>(houseService.makePageNavigation(bean), HttpStatus.OK);
	}
}
