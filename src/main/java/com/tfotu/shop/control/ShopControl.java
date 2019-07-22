package com.tfotu.shop.control;

import com.alibaba.druid.util.StringUtils;
import com.tfotu.shop.Utils;
import com.tfotu.shop.domin.Result;
import com.tfotu.shop.domin.ResultEnum;
import com.tfotu.shop.domin.ResultUtils;
import com.tfotu.shop.entity.Goods;
import com.tfotu.shop.entity.User;
import com.tfotu.shop.entity.UserIDO;
import com.tfotu.shop.exception.UnifyExeption;
import com.tfotu.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 页面访问控制器
 */
@Controller
public class ShopControl extends BaseControl {

    @Autowired
    private ShopService shopService;

    @RequestMapping({"/","/index"})
    public ModelAndView index(){
        Date date = new Date();
        date.setTime( date.getTime() + 86400000 );
        String msEndTime = Utils.getFormatDateTime( date );

        List<Goods> recomGoods = shopService.getRecomGoods( 3 );


        this.setObjs("msEndTime", msEndTime);
        this.setObjs("recomGoods", recomGoods);
        return view("/shop/index");
    }

    @RequestMapping("/category")
    public ModelAndView category(){
        this.setObjs("categorys", shopService.getCategoryFormPid(0) );
        return view("/shop/category");
    }

    @RequestMapping("/search")
    public ModelAndView search(){
        return view("/shop/search");
    }

    @RequestMapping({"/goods/{id}","/goods"})
    public ModelAndView goods(@PathVariable(required = false) String id){
        Integer gsid = Utils.String2Integer(id,0);
        Goods goods = shopService.goods(gsid);
        if( goods == null ){
            return view("/shop/goods_not");
        }
        //  图片
        String[] imgs = org.apache.commons.lang3.StringUtils.split(goods.getImgs(),",");
        if( imgs.length == 0 ){
            String[] imgsNew = {goods.getThumb()};
            imgs = imgsNew;
        }
        this.setObjs("goods", goods);
        this.setObjs("imgs", imgs);
        return view("/shop/goods");
    }

    @RequestMapping("/userLogin")
    public ModelAndView userLogin(){
        return view("/shop/login");
    }

    @RequestMapping("/userRegister")
    public ModelAndView userRegister(){
        return view("/shop/register");
    }

    @RequestMapping("/userResetPassword")
    public ModelAndView userResetPassword(){
        return view("/shop/resetpassword");
    }

    @RequestMapping("/post")
    @ResponseBody
    public Result shopPost(@RequestParam(name = "action",defaultValue = "") String action, HttpServletRequest req){
        switch (action){
            case "getcategorybypid": return this.getCategoryByPid(req);
            case "getcategorygoods": return this.getCategoryGoods(req);

            case "userlogin": return this.userPostLogin(req);
            case "userregister": return this.userPostReg(req);
            case "userregistersms": return this.userPostRegSms(req);
        }
        return ResultUtils.build(ResultEnum.PARAM_ERROR);
    }
    private Result getCategoryByPid(HttpServletRequest req){
        Integer pid = Utils.String2Integer( req.getParameter("pid"), null );
        return ResultUtils.suc( shopService.getCategoryFormPid(pid) );
    }
    private Result getCategoryGoods(HttpServletRequest req){
        Integer cid = Utils.String2Integer( req.getParameter("cid"), null );
        Integer cgall = Utils.String2Integer( req.getParameter("cgall"), null );
        String word = req.getParameter("word");
        Integer page = Utils.String2Integer( req.getParameter("page"), 0 );
        String sort = req.getParameter("sort");
        return ResultUtils.suc( shopService.getCategoryGoods(cid, cgall, word, page, sort) );
    }
    private Result userPostLogin(HttpServletRequest req){
        UserIDO userReg = new UserIDO();
        userReg.setMobile( req.getParameter("mobile") );
        userReg.setPassword( req.getParameter("password") );
        shopService.login(userReg);
        return ResultUtils.suc( null );
    }
    private Result userPostReg(HttpServletRequest req) {
        String mobile = req.getParameter("mobile");
        String smscode = req.getParameter("smscode");
        //  数据判断(短信验证码)
        String sessionRegMobile = (String)Utils.getSession().getAttribute("regMobile");
        String sessionRegSmsCode = (String)Utils.getSession().getAttribute("regSmsCode");
        if( !StringUtils.equals(sessionRegMobile, mobile ) || !StringUtils.equals(sessionRegSmsCode, smscode ) ){
            throw new UnifyExeption(ResultEnum.SMS_CHECK_ERROR);
        }

        UserIDO userReg = new UserIDO();
        userReg.setName( req.getParameter("name") );
        userReg.setPassword( req.getParameter("password") );
        userReg.setMobile( mobile );
        userReg.setSmscode( smscode );
        User user = shopService.userPostReg( userReg );
        Utils.getSession().setAttribute("user", user);
        return ResultUtils.suc( null );
    }
    private Result userPostRegSms(HttpServletRequest req){
        String mobile = req.getParameter("mobile");
        return ResultUtils.suc( shopService.userPostRegSms( mobile ) );
    }

}
