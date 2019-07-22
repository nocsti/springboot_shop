package com.tfotu.shop.service.impl;

import com.tfotu.shop.Utils;
import com.tfotu.shop.dao.SmsMapper;
import com.tfotu.shop.dao.UserMapper;
import com.tfotu.shop.domin.PageInfo;
import com.tfotu.shop.dao.CategoryMapper;
import com.tfotu.shop.dao.GoodsMapper;
import com.tfotu.shop.domin.ResultEnum;
import com.tfotu.shop.domin.ValidationImpl;
import com.tfotu.shop.domin.ValidationResult;
import com.tfotu.shop.entity.*;
import com.tfotu.shop.entity.where.GoodsWhere;
import com.tfotu.shop.exception.UnifyExeption;
import com.tfotu.shop.service.ShopService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    private static Logger Log = Logger.getLogger(ShopServiceImpl.class);

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SmsMapper smsMapper;
    @Autowired
    private ValidationImpl validation;

    /**
     * 商品分类
     * @param cid 分类id
     * @param word 分类中搜索商品的名称
     * @param page 分类结果分页
     * @param sort 分类结果排序方式
     * @return 分类结果分页后的商品列表
     */
    public PageInfo getCategoryGoods(Integer cid, Integer cgall, String word, Integer page, String sort){
        GoodsWhere goodsSearch = new GoodsWhere();
        goodsSearch.setCid(cid);
        goodsSearch.setCgall(cgall);
        if( !StringUtils.isEmpty(word) )
            goodsSearch.setWord("%"+word+"%");
        goodsSearch.setPage(page, 10);
        goodsSearch.setSort(sort);

        System.out.println(goodsSearch);
        Object items = goodsMapper.getSearch(goodsSearch);
        Integer total = goodsMapper.getSearchNumber(goodsSearch);
        return PageInfo.build( items, total, goodsSearch.getPage(), goodsSearch.getLimit() );
    }

    @Override
    public List<Category> getCategory() {
        return categoryMapper.getAll();
    }

    @Override
    public List<Category> getCategoryFormPid(Integer pid) {
        return categoryMapper.getByPid(pid);
    }

    @Override
    public Goods goods(Integer gsid) {
        Goods goods = goodsMapper.getById(gsid);
        return goods;
    }

    @Override
    public boolean login(UserIDO userIdo) {
        if( userIdo == null ){
            throw new UnifyExeption(ResultEnum.PARAM_ERROR);
        }
        if( !Utils.isMobile( userIdo.getMobile() ) ){
            throw new UnifyExeption(ResultEnum.MOBILE_ERROR);
        }
        //  获取手机号用户
        User user = userMapper.getByMobile( userIdo.getMobile() );
        if( user == null ){
            throw new UnifyExeption(ResultEnum.USER_LOGIN_ERROR);
        }
        String loginPassword = Utils.md5( ( userIdo.getPassword() + user.getPasswordRandstr() ) );
        if( !StringUtils.equals( loginPassword, user.getPassword() )){
            throw new UnifyExeption(ResultEnum.USER_LOGIN_ERROR);
        }
        //  写入session
        user.setPassword("");
        user.setPasswordRandstr("");
        Utils.getSession().setAttribute("user", user);
        return true;
    }

    @Override
    public User userPostReg(UserIDO userIdo) {
        if( userIdo == null ){
            throw new UnifyExeption(ResultEnum.PARAM_ERROR);
        }
        //  验证数据
        ValidationResult valiResult = validation.validate(userIdo);
        if( valiResult.isHasError() ){
            throw new UnifyExeption( ResultEnum.PARAM_ERROR, valiResult.getErrMsg() );
        }

        //  插入dao
        String pwRandstr = Utils.md5Short( String.valueOf((new Date()).getTime()) );
        String pwMd5 = Utils.md5( ( userIdo.getPassword() + pwRandstr ) );
        User user = new User();
        user.setName( userIdo.getName() );
        user.setPassword(pwMd5);
        user.setPasswordRandstr(pwRandstr);
        user.setMobile( userIdo.getMobile() );
        user.setRegTime(Utils.getTime());
        System.out.println(user);
        userMapper.insertSelective(user);
        return user;
    }

    @Override
    public Integer userPostRegSms(String mobile) {
        if( !Utils.isMobile(mobile) ){
            throw new UnifyExeption(ResultEnum.MOBILE_ERROR);
        }
        User userMobileExist = userMapper.getByMobile( mobile );
        if( userMobileExist != null ){
            throw new UnifyExeption(ResultEnum.MOBILE_BEEN_USE);
        }
        //  生成数据
        String smsCode = "1231";//String.valueOf(Utils.randNum(1000,9999));
        Utils.getSession().setAttribute("regMobile", mobile);
        Utils.getSession().setAttribute("regSmsCode", smsCode);

        //  写入数据库记录
        Sms sms = new Sms();
        sms.setUtype(0);
        sms.setUid(0);
        sms.setCode( smsCode );
        sms.setCtime( Utils.getTime() );
        sms.setMobile( mobile );
        smsMapper.insert(sms);
        if( sms.getId() == null ){
            throw new UnifyExeption(ResultEnum.SMS_SEND_ERROR);
        }
        return sms.getId();
    }

    @Override
    public List<Goods> getRecomGoods(int num) {
        GoodsWhere goodsWhere = new GoodsWhere();
        goodsWhere.setSort( "def" );
        goodsWhere.setIndex( 0 );
        goodsWhere.setLimit( num );
        List<Goods> goods = goodsMapper.getSearch(goodsWhere);
        return goods;
    }
}
