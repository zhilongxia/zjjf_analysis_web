package com.zjjf.analysis.beans.vo;

public class BaseVo {

	private Integer pageNo;

	private Integer offset;

	private Integer limit;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public BaseVo() {

		if (pageNo != null && limit != null) {
			offset = (pageNo - 1) * limit;
		}
	}
}
