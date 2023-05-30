package com.study.common;

public class PageCreator {

	private PageVO paging;
	private int articleTotalCount;
	private int beginPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int finalPage;

	private final int displayPageNum = 10;

	private void calcDataOfPage() {
		endPage = (int)Math.ceil((double)paging.getPage() /displayPageNum) * displayPageNum;
		beginPage = endPage - displayPageNum + 1;

		prev = beginPage == 1 ? false : true;
		next = articleTotalCount <= paging.getCountPerPage() * endPage ? false : true;

		if(!next)
			endPage = (int)Math.ceil(articleTotalCount / (double)paging.getCountPerPage());

		finalPage = (int)Math.ceil(articleTotalCount / 10.0);
	}



	public PageVO getPaging() {
		return paging;
	}

	public void setPaging(PageVO paging) {
		this.paging = paging;
	}

	public int getArticleTotalCount() {
		return articleTotalCount;
	}

	public void setArticleTotalCount(int articleTotalCount) {
		this.articleTotalCount = articleTotalCount;
		calcDataOfPage();
	}

	public int getBeginPage() {
		return beginPage;
	}

	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getFinalPage() { return finalPage;}

	public void setFinalPage(int finalPage) { this.finalPage = finalPage;	}
}
