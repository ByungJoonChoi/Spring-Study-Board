package io.peter.domain;

import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	
	private int totalCount; // DB에 저장된 게시물의 총 갯수. 
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10; // 화면 하단 page navigation에서 보여줄 페이지 번호의 갯수 
	
	private Criteria cri; // page, perPageNum 사용 
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		calcData();
	}

	/*
	 * endPage, startPage, prev, next 계산 로직
	 */
	private void calcData() {
		endPage = (int) (Math.ceil(cri.getPage()/(double) displayPageNum) * displayPageNum);
		startPage = endPage - displayPageNum + 1;
		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		if(endPage > tempEndPage){
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() < totalCount ? true : false; 
	}
	
	public String makeQuery(int page){
		
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build();
		
		return uriComponents.toUriString();
	}
	
	public String makeSearch(int page){
		
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.queryParam("searchType", ((SearchCriteria)cri).getSearchType())
				.queryParam("keyword", encoding(((SearchCriteria)cri).getKeyword()))
				.build();
		
		return uriComponents.toUriString();
	}
	
	public String encoding(String keyword){
		
		if(keyword == null || keyword.trim().length() == 0){
			return "";
		}
		
		try {
			return URLEncoder.encode(keyword, "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
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

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public Criteria getCri() {
		return cri;
	}
}
