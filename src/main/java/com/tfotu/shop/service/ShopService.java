package com.tfotu.shop.service;

import com.tfotu.shop.domin.PageInfo;
import com.tfotu.shop.entity.Category;
import com.tfotu.shop.entity.Goods;
import com.tfotu.shop.entity.User;
import com.tfotu.shop.entity.UserIDO;

import java.util.List;

public interface ShopService {
    /**
     * 商品分类
     * @param cid 分类id
     * @param word 分类中搜索商品的名称
     * @param page 分类结果分页
     * @param sort 分类结果排序方式
     * @return 分类结果分页后的商品列表
     */
    public PageInfo getCategoryGoods(Integer cid, Integer cgall, String word, Integer page, String sort);

    public List<Category> getCategory();

    public List<Category> getCategoryFormPid(Integer pid);

    public Goods goods(Integer gsid);

    boolean login(UserIDO userIdo);

    User userPostReg(UserIDO userIdo);

    Integer userPostRegSms(String mobile);

    public List<Goods> getRecomGoods(int num);
}
