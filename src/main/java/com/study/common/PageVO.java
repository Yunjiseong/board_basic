package com.study.common;

public class PageVO {
	
	private int page;
	private int countPerPage = 10;
	
	public PageVO() {}
		
	public PageVO(int page) {
		super();
		this.page = page;
	}

	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	

}
