package io.peter.domain;

public class Criteria {
	private int page;
	private int perPageNum;
	
	public Criteria() {
		System.out.println("Criteria() is called");
		this.page = 1;
		this.perPageNum = 10; // 한 페이지에서 보여줄 게시물의 갯수.
	}
	
	public void setPage(int page) {
		System.out.println("setPage() is called");
		if(page <= 0){
			this.page = 1;
			return;
		}
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum) {
		System.out.println("setPerPageNum() is called");
		if(perPageNum <= 0 || perPageNum > 100){
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}

	public int getPage() {
		return page;
	}

	// method for MyBatis SQL Mapper -
	public int getPageStart(){
		return (page - 1) * perPageNum;  // record의 시작 index 계산을 아예 메소드 안에 넣어 버림. 
	}
	// method for MyBatis SQL Mapper -	
	public int getPerPageNum() {
		return perPageNum;
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
}
