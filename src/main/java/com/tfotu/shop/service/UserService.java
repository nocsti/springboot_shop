package com.tfotu.shop.service;

import com.tfotu.shop.domin.PageInfo;
import com.tfotu.shop.dto.BuyGoodsDto;
import com.tfotu.shop.dto.CartDto;
import com.tfotu.shop.dto.GoodsDto;
import com.tfotu.shop.entity.*;
import com.tfotu.shop.entity.where.CartWhere;
import com.tfotu.shop.entity.where.OrderWhere;

import java.util.List;

public interface UserService {
    User getUserInfo();
    String uploadIcon();
    User saveInfo();

    List<UserAddr> userAddress(Integer uid);

    void userAddAddress(UserAddr userAddr);

    void userDelAddress(UserAddr userAddr);

    void userEditAddress(UserAddr userAddr);

    void userAddressSetDef(UserAddr userAddr);

    UserAddr getDefAddress(Integer uid);

    List<BuyGoodsDto> tureorder(Integer uid, String[] idArr);

    Integer orderCreate(Integer uid,String message,String userAddrId);
    PageInfo getOrder(OrderWhere orderWhere);
    Order getOrderFrist(OrderWhere orderWhere);
    Boolean orderPay(OrderWhere orderWhere);

    void cartAdd(Cart cart);
    PageInfo getCart(CartWhere cartWhere);
}
