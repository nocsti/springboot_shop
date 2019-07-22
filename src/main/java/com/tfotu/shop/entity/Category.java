package com.tfotu.shop.entity;

import java.math.BigInteger;

public class Category {
    private Integer id;
    private Integer pid;
    private Integer sort;
    private String title;
    private String thumb;
    private String descr;
    private Integer status;
    private BigInteger ctime;

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", pid=" + pid +
                ", sort=" + sort +
                ", title='" + title + '\'' +
                ", thumb='" + thumb + '\'' +
                ", descr='" + descr + '\'' +
                ", status=" + status +
                ", ctime=" + ctime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigInteger getCtime() {
        return ctime;
    }

    public void setCtime(BigInteger ctime) {
        this.ctime = ctime;
    }
}
