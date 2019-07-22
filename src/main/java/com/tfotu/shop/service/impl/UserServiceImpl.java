package com.tfotu.shop.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.tfotu.shop.Utils;
import com.tfotu.shop.dao.*;
import com.tfotu.shop.domin.PageInfo;
import com.tfotu.shop.dto.*;
import com.tfotu.shop.entity.*;
import com.tfotu.shop.domin.ValidationImpl;
import com.tfotu.shop.domin.ValidationResult;
import com.tfotu.shop.domin.ResultEnum;
import com.tfotu.shop.entity.where.CartWhere;
import com.tfotu.shop.entity.where.GoodsWhere;
import com.tfotu.shop.entity.where.OrderWhere;
import com.tfotu.shop.exception.ShopExeption;
import com.tfotu.shop.exception.UnifyExeption;
import com.tfotu.shop.service.UserService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Service
@Scope(value = "singleton")
public class UserServiceImpl implements UserService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ValidationImpl validation;
    @Autowired
    private UserAddrMapper userAddrMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private OrderAddrMapper orderAddrMapper;
    @Autowired
    private CartMapper cartMapper;

    public User getUserInfo(){
        return null;
    }
    public String uploadIcon(){
        return null;
    }
    public User saveInfo(){
        return null;
    }

    @Override
    public List<UserAddr> userAddress(Integer uid) {
        return userAddrMapper.getAll( uid );
    }

    @Override
    public UserAddr getDefAddress(Integer uid) {
        return userAddrMapper.getDef(uid);
    }

    @Override
    public void userAddAddress(UserAddr userAddr) {
        if( userAddr == null ){
            throw new UnifyExeption(ResultEnum.PARAM_ERROR);
        }
        //  验证数据
        ValidationResult valiResult = validation.validate(userAddr);
        if( valiResult.isHasError() ){
            throw new UnifyExeption( ResultEnum.PARAM_ERROR, valiResult.getErrMsg() );
        }
        userAddrMapper.insert(userAddr);
    }

    @Override
    public void userDelAddress(UserAddr userAddr) {
        if( userAddr == null ){
            throw new UnifyExeption(ResultEnum.PARAM_ERROR);
        }
        userAddrMapper.delete(userAddr);
    }

    @Override
    public void userEditAddress(UserAddr userAddr) {
        //  验证数据
        ValidationResult valiResult = validation.validate(userAddr);
        if( valiResult.isHasError() ){
            throw new UnifyExeption( ResultEnum.PARAM_ERROR, valiResult.getErrMsg() );
        }
        userAddrMapper.update(userAddr);
    }

    @Override
    public void userAddressSetDef(UserAddr userAddr) {
        userAddrMapper.updateSetDef(userAddr);
        //  设置非默认地址
        userAddr.setDef( 0 );
        userAddrMapper.updateSetUndef(userAddr);
    }

    @Override
    @Transactional
    public Integer orderCreate( Integer uid, String message, String userAddrId) {
        if( userAddrId == null ){
            throw new UnifyExeption( ResultEnum.ORDER_USER_ADDR_NULL );
        }
        BigDecimal orderTotal = new BigDecimal(0);
        //  商品入库前计算总价
        List<BuyGoodsDto> buyGoodsDtos = (List<BuyGoodsDto>) Utils.getSession().getAttribute("buyGoodsDtos");
        for ( int i=0;i<buyGoodsDtos.size();i++ ){
            BuyGoodsDto buyGoodsDto = buyGoodsDtos.get(i);
            BigDecimal goodsTotal = buyGoodsDto.getPrice().multiply( new BigDecimal(buyGoodsDto.getNumber()) );
            buyGoodsDto.setTotal( goodsTotal );
            orderTotal = orderTotal.add( goodsTotal );
        }
        //  创建订单
        Order order = new Order();
        order.setUid( uid );
        order.setNumber( Utils.createOrderNumber( Integer.toString(Utils.randNum(1000,9999)) ) ); //  订单号
        order.setMessage( message );
        order.setCtime( Utils.getTime() );
        order.setStatus( 0 );
        order.setPayType( 1 );  //  1默认支付宝支付
        order.setPay( 0 );
        order.setDelete( 0 );
        order.setTotal( orderTotal );
        System.out.println( order );
        orderMapper.insert( order );
        if( order.getId() == null ){
            throw new UnifyExeption( ResultEnum.ORDER_CREATE_ERROR );
        }
        //  写入商品
        Cart cart = new Cart();
        for ( int i=0;i<buyGoodsDtos.size();i++ ) {
            BuyGoodsDto buyGoodsDto = buyGoodsDtos.get(i);
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOid( order.getId() );
            orderGoods.setUid(uid);
            orderGoods.setGoodsId( buyGoodsDto.getId() );
            orderGoods.setGoodsThumb( buyGoodsDto.getThumb() );
            orderGoods.setGoodsName( buyGoodsDto.getName() );
            orderGoods.setGoodsPrice( buyGoodsDto.getPrice() );
            orderGoods.setGoodsNumber( buyGoodsDto.getNumber() );
            orderGoods.setTotal( buyGoodsDto.getTotal() );
            orderGoodsMapper.insert( orderGoods );
            if( orderGoods.getId() == null ){
                throw new UnifyExeption( ResultEnum.ORDER_GOODS_CREATE_ERROR );
            }
            //  购物车条目删除
            if( buyGoodsDto.getCartItem() ){
                cart.setUid( uid );
                cart.setGoodsId( buyGoodsDto.getId() );
                cartMapper.delete( cart );
            }
        }
        //  写入收货地址
        UserAddr uaWhere = new UserAddr();
        uaWhere.setId( StringUtils.stringToInteger(userAddrId) );
        uaWhere.setUid( uid );
        UserAddr userAddr = userAddrMapper.getById( uaWhere );
        if( userAddr == null ){
            throw new UnifyExeption( ResultEnum.USER_ADDR_NOT_EXIST );
        }
        OrderAddr orderAddr = new OrderAddr();
        orderAddr.setOid( order.getId() );
        orderAddr.setUid( userAddr.getUid() );
        orderAddr.setUaid( userAddr.getId() );
        orderAddr.setConsignee( userAddr.getConsignee() );
        orderAddr.setMobile( userAddr.getMobile() );
        orderAddr.setAddress( userAddr.getAddress() );
        orderAddrMapper.insert( orderAddr );

        return order.getId();
    }

    @Override
    public PageInfo getOrder(OrderWhere orderWhere) {
        List<Order> orders = orderMapper.getSearch( orderWhere );
        List<OrderDto> items = new LinkedList<>();
        for(int i=0; i<orders.size();i++){
            Order order = orders.get(i);
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties( order, orderDto );
            List<OrderGoods> orderGoodsList = orderGoodsMapper.getGroupOid( order.getId() );
            List<OrderGoodsDto> goodsDtos = new LinkedList<>();
            for(OrderGoods orderGoods: orderGoodsList){
                OrderGoodsDto goodsDto = new OrderGoodsDto();
                BeanUtils.copyProperties( orderGoods, goodsDto );
                goodsDtos.add( goodsDto );
            }
            orderDto.setGoods( goodsDtos );
            items.add(orderDto);
        }
        Integer total = orderMapper.getSearchCount( orderWhere );
        return PageInfo.build( items, total, orderWhere.getPage(), orderWhere.getLimit() );
    }

    @Override
    public Order getOrderFrist(OrderWhere orderWhere) {
        Order order = orderMapper.getById( orderWhere );
        return order;
    }

    @Override
    public Boolean orderPay(OrderWhere orderWhere) {
        Order order = new Order();
        order.setId( orderWhere.getId() );
        order.setUid( orderWhere.getUid() );
        order.setPayType( orderWhere.getPayType() );
        order.setStatus( 10 );
        orderMapper.update( order );
        return true;
    }

    @Override
    public void cartAdd(Cart cart) {
        CartWhere cartWhere = new CartWhere();
        cartWhere.setGoodsId( cart.getGoodsId() );
        cartWhere.setUid( cart.getUid() );
        List<Cart> cartList = cartMapper.getSearch( cartWhere );
        if( cartList == null || cartList.size() == 0 ){
            cartMapper.insert( cart );
        }else{
            //  存在则更新数量
            Cart cartExist = cartList.get(0);
            cartExist.setNumber( cart.getNumber() );
            cartExist.setUptime(Utils.getTime());
            cartMapper.update( cartExist );
        }
    }

    @Override
    public List<BuyGoodsDto> tureorder(Integer uid, String[] idArr) {
        List<BuyGoodsDto> buyGoodsDtos = new LinkedList<>();
        if( idArr != null ) {
            String buyGoodsFormType = Utils.getRequest().getParameter("buyGoodsFormType");
            boolean isFormCartItem = (buyGoodsFormType.equals("cart"))?true:false;
            for (int i = 0; i < idArr.length; i++) {
                Integer id = StringUtils.stringToInteger(idArr[i]);
                Integer num = StringUtils.stringToInteger( Utils.getRequest().getParameter("goodsNumber["+id+"]") );
                BuyGoodsDto buyGoodsDto = new BuyGoodsDto();
                buyGoodsDto.setId( id );
                buyGoodsDto.setNumber( num );
                buyGoodsDto.setCartItem( isFormCartItem );
                buyGoodsDtos.add(buyGoodsDto);

                //  来自购物车条目进行删除
                if( isFormCartItem ){
                    Cart cart = new Cart();
                    cart.setId( id );
                    cart.setUid( uid );
                    //cartMapper.delete( cart );
                }
            }
            Utils.getSession().setAttribute("buyGoodsDtos", buyGoodsDtos);
        }else{
            buyGoodsDtos = (List<BuyGoodsDto>) Utils.getSession().getAttribute("buyGoodsDtos");
        }
        if( buyGoodsDtos == null){
            throw new UnifyExeption( ResultEnum.TURE_ORDER_GOODS_NULL );
        }

        for(int bgdi=0; bgdi < buyGoodsDtos.size(); bgdi++){
            BuyGoodsDto buyGoodsDto = buyGoodsDtos.get(bgdi);
            Goods goods = goodsMapper.getById( buyGoodsDto.getId() );
            if( !ObjectUtils.isEmpty(buyGoodsDto) && !ObjectUtils.isEmpty( goods) ){
                BeanUtils.copyProperties(goods, buyGoodsDto);
            }
        }

        return buyGoodsDtos;
    }

    @Override
    public PageInfo getCart(CartWhere cartWhere) {
        List<Cart> cartList = cartMapper.getSearch( cartWhere );
        Integer cartListCount = cartMapper.getSearchCount( cartWhere );
        List<CartDto> cartDtoList = carts2cartDtos( cartList );
        return PageInfo.build( cartDtoList, cartListCount, cartWhere.getPage(), cartWhere.getLimit() );
    }

    private List<CartDto> carts2cartDtos(List<Cart> cartList){
        List<CartDto> cartDtoList = new LinkedList<CartDto>();
        for(int i=0; i < cartList.size(); i++){
            Cart cartItem = cartList.get(i);
            CartDto cartDto = new CartDto();
            //  查询商品信息
            Goods goods = goodsMapper.getById( cartItem.getGoodsId() );
            if( goods != null ){
                BeanUtils.copyProperties( goods, cartDto);
            }
            BeanUtils.copyProperties( cartItem, cartDto);
            cartDtoList.add( cartDto );
        }
        return cartDtoList;
    }
}
