package com.p2mj.mall.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class PageResult<T> implements Serializable {

    private int totalCount; //总记录数

    private int pageSize; //每页记录

    private int totalPage; //总页数

    private int currPage; //当前页数

    private List<?> list;//列表数据

    public PageResult(java.util.List<?> list, int totalCount, int pageSize, int currPage) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.list = list;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
