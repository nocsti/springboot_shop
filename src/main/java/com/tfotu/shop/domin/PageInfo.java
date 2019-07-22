package com.tfotu.shop.domin;

public class PageInfo {
    /** 数据集 */
    private Object items;
    /** 当前页码,1开始 */
    private Integer currentPage;
    /** 最后页码 */
    private Integer lastPage;
    /** 查询总条数 */
    private Integer total;
    /** 当前条数 */
    private Integer listRows;
    /** 是否还有下一页 */
    private Boolean hasMore;

    public PageInfo(Object items, Integer total, Integer currentPage, Integer listRows) {
        this.items = items;
        this.total = total;

        this.listRows = listRows;
        this.currentPage = currentPage;
        this.lastPage = (int)Math.ceil( Double.valueOf(total) / listRows );
        this.hasMore = (currentPage < lastPage)?true:false;
    }

    public static PageInfo build(Object items, Integer total, Integer currentPage, Integer listRows){
        return new PageInfo( items, total, currentPage, listRows );
    }

    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getLastPage() {
        return lastPage;
    }

    public void setLastPage(Integer lastPage) {
        this.lastPage = lastPage;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getListRows() {
        return listRows;
    }

    public void setListRows(Integer listRows) {
        this.listRows = listRows;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(Boolean hasMore) {
        this.hasMore = hasMore;
    }
}
