package com.tfotu.shop.control;

import com.tfotu.shop.Utils;
import com.tfotu.shop.dao.UserMapper;
import com.tfotu.shop.domin.ResultEnum;
import com.tfotu.shop.entity.User;
import com.tfotu.shop.exception.UnifyExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BaseControl {

    private Map<String,Object> objs = null;
    private String path = null;

    public BaseControl(){
        objs = new HashMap<>();
        objs.put("webName", "TF微商城");
    }
    //  模版数据
    protected void setObjs(String key,Object value) {
        this.objs.put(key,value);
    }
    protected Object getObjs(String key) {
        return this.objs.get(key);
    }
    //  显示模版
    protected ModelAndView view(String viewName){
        return this.view(viewName, null );
    }
    protected ModelAndView view(String viewName,String objName,Object obj){
        Map<String,Object> objs = new HashMap<String,Object>();
        objs.put( objName, obj);
        return this.view(viewName, objs );
    }
    protected ModelAndView view(String viewName, Map<String,Object> objs){
        ModelAndView mv = new ModelAndView();
        mv.setViewName(viewName);
        if( this.objs != null && !this.objs.isEmpty() ){
            mv.addAllObjects(this.objs);
        }
        if( objs != null && !objs.isEmpty() ){
            mv.addAllObjects(objs);
        }
        return mv;
    }

    public User getUser(){
        User user = (User)Utils.getSession().getAttribute("user");
        if(ObjectUtils.isEmpty(user)){
            throw new UnifyExeption(ResultEnum.USER_NOT_LOGIN);
        }
        return user;
    }

}
