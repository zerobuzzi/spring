package com.study.board;

import lombok.Data;

@Data
public class Criteria {

    private int page;
    private int perPageNum;

    private String id;
    
    private String searchType; // 검색타입
    private String searchName; // 검색이름

    public Criteria() {
        page = 1;
        perPageNum = 5;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if(page <= 0)
            page = 1;

        this.page = page;
    }

    public int getPerPageNum() {
        return perPageNum;
    }
    
    public int getPageStart() {
        return (this.page - 1) * this.perPageNum;
    }

    public void setPerPageNum(int perPageNum) {
        if(perPageNum <= 0 || perPageNum > 100) {
            perPageNum = 10;
        }else {
        	this.perPageNum = perPageNum;
        }
        
    }
    
    

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", searchType=" + searchType + ", searchName="
				+ searchName + ", getPage()=" + getPage() + ", getPerPageNum()=" + getPerPageNum() + ", getPageStart()="
				+ getPageStart() + ", getSearchType()=" + getSearchType() + ", getSearchName()=" + getSearchName()
				+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}
    
    
}
