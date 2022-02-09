package com.ssafy.vue.dto;

import java.util.Date;

public class QnA {
	private int qnaNo;
	private String qnaTitle;
	private String qnaContent;
	private String qnaUserid;
	private Date qnaDatetime;
	private String replyContent;
	private Date replyDatetime;
	private String replyUserid;

	public QnA() {
		super();
	}

	public QnA(int qnaNo, String qnaTitle, String qnaContent, String qnaUserid, Date qnaDatetime, String replyContent,
			Date replyDatetime, String replyUserid) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.qnaUserid = qnaUserid;
		this.qnaDatetime = qnaDatetime;
		this.replyContent = replyContent;
		this.replyDatetime = replyDatetime;
		this.replyUserid = replyUserid;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	public String getQnaUserid() {
		return qnaUserid;
	}

	public void setQnaUserid(String qnaUserid) {
		this.qnaUserid = qnaUserid;
	}

	public Date getQnaDatetime() {
		return qnaDatetime;
	}

	public void setQnaDatetime(Date qnaDatetime) {
		this.qnaDatetime = qnaDatetime;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Date getReplyDatetime() {
		return replyDatetime;
	}

	public void setReplyDatetime(Date replyDatetime) {
		this.replyDatetime = replyDatetime;
	}

	public String getReplyUserid() {
		return replyUserid;
	}

	public void setReplyUserid(String replyUserid) {
		this.replyUserid = replyUserid;
	}

	@Override
	public String toString() {
		return "QnA [qnaNo=" + qnaNo + ", qnaTitle=" + qnaTitle + ", qnaContent=" + qnaContent + ", qnaUserid="
				+ qnaUserid + ", qnaDatetime=" + qnaDatetime + ", replyContent=" + replyContent + ", replyDatetime="
				+ replyDatetime + ", replyUserid=" + replyUserid + "]";
	}

}
