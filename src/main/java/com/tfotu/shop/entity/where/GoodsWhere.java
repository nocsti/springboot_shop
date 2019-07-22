package com.tfotu.shop.entity.where;

import org.apache.ibatis.annotations.Param;

public class GoodsWhere extends BaseWhere {
    private Integer cid;
    private Integer cgall;
    private String word;
    private String sort;
    private String idStr;

    public String getSort() {
        return sort;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCgall() {
        return cgall;
    }

    public void setCgall(Integer cgall) {
        this.cgall = cgall;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }
}
