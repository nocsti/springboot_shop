package com.tfotu.shop.control;

import com.alibaba.druid.util.StringUtils;
import com.tfotu.shop.Utils;
import com.tfotu.shop.dto.BuyGoodsDto;
import com.tfotu.shop.dto.CartDto;
import com.tfotu.shop.dto.GoodsDto;
import com.tfotu.shop.entity.*;
import com.tfotu.shop.domin.Result;
import com.tfotu.shop.domin.ResultEnum;
import com.tfotu.shop.domin.ResultUtils;
import com.tfotu.shop.entity.where.CartWhere;
import com.tfotu.shop.entity.where.OrderWhere;
import com.tfotu.shop.service.UserService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户独特相关页面,和需要登陆的操作
 */
@Controller
public class UserControl extends BaseControl {

    @Autowired
    private UserService userService;

    @RequestMapping({"/user","/user/home"})
    public ModelAndView userHome(){
        return view("/shop/user/home");
    }

    @RequestMapping("/user/info")
    public ModelAndView userInfo(){
        return view("/shop/user/info");
    }

    @RequestMapping("/user/gladdress")
    public ModelAndView userGladdress(){
        return view("/shop/user/gladdress");
    }

    @RequestMapping("/user/secset")
    public ModelAndView userSecset(){
        return view("/shop/user/secset");
    }

    @RequestMapping("/user/shopcart")
    public ModelAndView shopcart(){
        return view("/shop/user/shopcart");
    }

    @RequestMapping("/user/order")
    public ModelAndView userOrder(){
        return view("/shop/user/order");
    }

    @RequestMapping("/user/tureorder")
    public ModelAndView tureorder(){
        //  获取收货地址
        UserAddr userAddr = userService.getDefAddress( getUser().getId() );
        //  购物车商品输出
        String[] idArr = Utils.getRequest().getParameterValues("goodsId");
        List<BuyGoodsDto> goodsArr = userService.tureorder( getUser().getId(), idArr);
        System.out.println(goodsArr);
        //  返回视图
        this.setObjs("userAddr", userAddr);
        this.setObjs("goodsArr", goodsArr);
        return view("/shop/tureorder");
    }

    @RequestMapping("/user/orderPay/{id}")
    public ModelAndView pay(@PathVariable(required = false) String id){
        OrderWhere orderWhere = new OrderWhere();
        orderWhere.setId( StringUtils.stringToInteger(id) );
        orderWhere.setUid( getUser().getId() );
        Order order = userService.getOrderFrist(orderWhere);

        this.setObjs("order", order);
        return view("/shop/pay");
    }

    /**
     * user统一 post接口
     */
    @RequestMapping("/user/post")
    @ResponseBody
    public Result userPost(@RequestParam(name = "action",defaultValue = "") String action,
                           HttpServletRequest req){
        switch (action){
            case "cartadd": return cartAdd(req);
            case "cartupdatenumber": return cartUpdateNumber(req);
            case "cartdelete": return cartDelete(req);
            case "cart": return userCart(req);
            case "order": return userOrder(req);
            case "orderadd": return userOrderAdd(req);
            case "orderpay": return userOrderPay(req);
            case "address": return userAddress(req);
            case "addaddress": return userAddAddress(req);
            case "deladdress": return userDelAddress(req);
            case "editaddress": return userEditAddress(req);
            case "addresssetdef": return userAddressSetDef(req);
        }
        return ResultUtils.build(ResultEnum.PARAM_ERROR);
    }
    private Result cartAdd(HttpServletRequest req){
        Cart cart = new Cart();
        cart.setGoodsId( StringUtils.stringToInteger(req.getParameter("id")) );
        cart.setNumber( StringUtils.stringToInteger(req.getParameter("number")) );
        cart.setUid( getUser().getId() );
        cart.setUptime( Utils.getTime() );
        cart.setCtime( cart.getUptime() );
        userService.cartAdd( cart );
        return ResultUtils.suc(null);
    }
    private Result cartUpdateNumber(HttpServletRequest req){
        return ResultUtils.suc(null);
    }
    private Result cartDelete(HttpServletRequest req){
        return ResultUtils.suc(null);
    }
    private Result userCart(HttpServletRequest req){
        CartWhere cartWhere = new CartWhere();
        cartWhere.setUid( getUser().getId() );
        cartWhere.setPage( StringUtils.stringToInteger(req.getParameter("page")), 10 );
        return ResultUtils.suc(userService.getCart(cartWhere));
    }
    private Result userOrder(HttpServletRequest req){
        OrderWhere where = new OrderWhere();
        where.setUid( getUser().getId() );
        where.setWord("%"+req.getParameter("word")+"%");
        where.setStatus( StringUtils.stringToInteger(req.getParameter("status")) );
        where.setPage( StringUtils.stringToInteger(req.getParameter("page")), 10 );
        where.setDelete( 0 );   //  用户没有删除的订单
        return ResultUtils.suc(userService.getOrder(where));
    }
    private Result userOrderAdd(HttpServletRequest req){
        String message = req.getParameter("message");
        String userAddrId = req.getParameter("userAddrId");
        Integer oid = userService.orderCreate( getUser().getId(), message, userAddrId);
        return ResultUtils.suc( oid );
    }
    private Result userOrderPay(HttpServletRequest req){
        String id = req.getParameter("id");
        String payType = req.getParameter("id");
        OrderWhere orderWhere = new OrderWhere();
        orderWhere.setId( StringUtils.stringToInteger(id) );
        orderWhere.setUid( getUser().getId() );
        orderWhere.setPayType( StringUtils.stringToInteger(payType) );
        userService.orderPay( orderWhere );
        return ResultUtils.suc( id );
    }
    private Result userAddress(HttpServletRequest req){
        return ResultUtils.suc(userService.userAddress( getUser().getId() ));
    }
    private Result userAddAddress(HttpServletRequest req){
        UserAddr userAddr = new UserAddr();
        userAddr.setConsignee( req.getParameter("consignee") );
        userAddr.setMobile( req.getParameter("mobile") );
        userAddr.setAddress( req.getParameter("address") );
        userAddr.setUid( getUser().getId() );
        userAddr.setCtime( Utils.getTime() );
        userService.userAddAddress(userAddr);
        return ResultUtils.suc(null);
    }
    private Result userDelAddress(HttpServletRequest req){
        UserAddr userAddr = new UserAddr();
        userAddr.setId( StringUtils.stringToInteger(req.getParameter("id")) );
        userAddr.setUid( getUser().getId() );
        userService.userDelAddress( userAddr );
        return ResultUtils.suc(null);
    }
    private Result userEditAddress(HttpServletRequest req){
        UserAddr userAddr = new UserAddr();
        userAddr.setConsignee( req.getParameter("consignee") );
        userAddr.setMobile( req.getParameter("mobile") );
        userAddr.setAddress( req.getParameter("address") );
        userAddr.setId( StringUtils.stringToInteger(req.getParameter("id")) );
        userService.userEditAddress( userAddr );
        return ResultUtils.suc(null);
    }
    private Result userAddressSetDef(HttpServletRequest req){
        UserAddr userAddr = new UserAddr();
        userAddr.setId( StringUtils.stringToInteger(req.getParameter("id")) );
        userAddr.setUid( getUser().getId() );
        userAddr.setDef(1);
        userService.userAddressSetDef( userAddr );
        return ResultUtils.build(ResultEnum.SUCCESS, "设置成功！");
    }

}
