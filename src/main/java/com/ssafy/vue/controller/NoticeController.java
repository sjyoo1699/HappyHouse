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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.notice;
import com.ssafy.vue.service.noticeService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/notice")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(HouseController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private noticeService ns;
	
	@ApiOperation(value = "모든 notice의 정보를 반환한다.", response = List.class)
	@GetMapping("/list")
	public ResponseEntity<List<notice>> listNotice() throws Exception {
		logger.debug("listNotice - 호출");
		return new ResponseEntity<List<notice>>(ns.listnotice(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "번호에 해당하는 notice의 정보를 반환한다.", response = notice.class)
	@GetMapping("/content/{articleNo}")
	public ResponseEntity<notice> selectNoticeByArticleNo(@PathVariable int articleNo) throws Exception {
		logger.debug("selectNoticeByArticleNo - 호출");
		return new ResponseEntity<notice>(ns.contentNotice(articleNo), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "notice를 create한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping("/create")
	public ResponseEntity<String> writeNotice(@RequestBody notice notice) throws Exception {
		logger.debug("writeNotice - 호출");
		try {
			ns.writeNotice(notice);
		} catch (Exception e) {
			logger.debug("writeNotice - error");
			return new ResponseEntity<String>(FAIL, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
}
