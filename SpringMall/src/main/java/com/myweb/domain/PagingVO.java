package com.myweb.domain;

public class PagingVO {
	private int totalCnt; // 총 글의 갯수
	private Criteria cri; // DB로부터 글의 범위를 가져오기 위한 기준을 담는 객체
	private int startPage; // 10개의 페이징 중 1번, 11번, 21번... 같은 페이징 시작 번호
	private int endPage; // 10개의 페이징 중 10번, 20번, 30번... 같은 페이징 끝 번호
	private boolean prev, next;
	
	public PagingVO() {}
	public PagingVO(int totalCnt, Criteria cri) {
		this.totalCnt = totalCnt;
		this.cri = cri;
		// 14(찍은 페이징번호) / 10.0 -> 1.4 -> (정수높임) -> 2 * 10 -> 20 (14번 페이징이 갖는 범위의 끝 번호가 20이 됨)
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0))*10;
		// 끝 번호에서 9를 빼면 무조건 시작 번호
		this.startPage = endPage-9;
		
		// DB에서 실제 상품의 총 갯수를 가져와서 한 페이지에 보여줄 상품 수(10개)로 나눈 후 정수 높임 = 실제 상품 총 갯수의 마지막 페이지 번호
		int realEndPage = (int)(Math.ceil((totalCnt*1.0)/cri.getAmount()));
		
		// 실제 마지막 페이지 번호가 계산된 페이징 마지막 번호(10의 배수 번)보다 같거나 작으면 마지막 페이지 번호는 실제 마지막 번호로 맞춘다
		if(realEndPage <= this.endPage) {
			this.endPage = realEndPage;
		}
		
		// 이전 버튼 = 페이지 시작 번호가 1보다 크면 무조건 나옴
		this.prev = this.startPage > 1;
		// 다음 버튼 = 현재 마지막 페이지 번호보다 실제 마지막 페이지 번호가 더 크면 무조건 나옴
		this.next = this.endPage < realEndPage;
	}
	
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
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
}
