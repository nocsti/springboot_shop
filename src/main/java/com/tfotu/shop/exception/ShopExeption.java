package com.tfotu.shop.exception;

import com.tfotu.shop.domin.ResultEnum;
import com.tfotu.shop.domin.ResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理
 */
//@ControllerAdvice
public class ShopExeption extends RuntimeException {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object defaultExeptionHandler(HttpServletRequest req,Exception e){
        e.printStackTrace();
        if( req.getMethod().equals("POST") ){   //  post返回obj（result）对象
            if( e instanceof UnifyExeption ) {
                UnifyExeption unifyExeption = (UnifyExeption)e;
                return ResultUtils.build( unifyExeption.getCode(), unifyExeption.getMessage() );
            }

            //  mybatis的错误信息不能打印出来
            //return ResultUtils.build( ResultEnum.UNKNOWN_ERROR);
            return ResultUtils.build( 0, e.getMessage() );
        }

        //  get方式时出现异常,返回错误信息页面
        ModelAndView view = new ModelAndView();
        view.setViewName("error");
        if( e instanceof UnifyExeption ) {
            UnifyExeption unifyExeption = (UnifyExeption)e;
            view.addObject("message", unifyExeption.getMessage() );
        }else{
            view.addObject("message", ResultEnum.UNKNOWN_ERROR.getMsg() );
        }
        return view;
    }

}
