package com.ssafy.vue.dto;

import java.io.Serializable;
import java.util.Arrays;

public class HousePageBean implements Serializable {
	private String dong;
	private String aptName;
	private boolean[] searchType;
	private int currentPage;
	private int sizePerPage;
	private String type;
	private int dealAmount;
	

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = "%" + dong + "%";
	}

	public String getAptName() {
		return aptName;
	}

	public void setAptName(String aptName) {
		this.aptName = "%" + aptName + "%";
	}

	public boolean[] getSearchType() {
		return searchType;
	}

	public void setSearchType(boolean[] searchType) {
		this.searchType = searchType;
		StringBuffer sb = new StringBuffer();
		sb.append("(");
		for (int i = 0, size = searchType.length; i < size; i++) {
			if (searchType[i]) {
				sb.append(i + 1);
				sb.append(",");
			}
		}
		sb.deleteCharAt(sb.length() - 1);

		sb.append(") ");
		this.type = sb.toString();
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getSizePerPage() {
		return sizePerPage;
	}

	public void setSizePerPage(int sizePerPage) {
		this.sizePerPage = sizePerPage;
	}

	public int getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(int dealAmount) {
		this.dealAmount = dealAmount;
	}

	@Override
	public String toString() {
		return "HousePageBean [dong=" + dong + ", aptName=" + aptName + ", searchType=" + Arrays.toString(searchType)
				+ ", currentPage=" + currentPage + ", sizePerPage=" + sizePerPage + ", type=" + type + ", dealAmount="
				+ dealAmount + "]";
	}

}
