package com.tfotu.shop.entity.where;

public class BaseWhere {
    private Integer index;
    private Integer limit;
    private Integer page;
    private Integer sort;   //  排序方式，sort值的几种情况

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setPage(Integer page) {
        this.setPage( page, 10 );
    }

    //  从第一页开始
    public void setPage(Integer page,Integer count){
        if( page == null || page <= 0)
            page = 1;
        if( count == null || count <= 0 )
            count = 10;
        else if( count > 100 )
            count = 100;
        index = (page-1) * count;
        limit = count;
        this.page = page;
    }

    public Integer getPage(){
        return page;
    }
}
