package com.tfotu.shop.domin;

public class ResultUtils {

    public static Result build(ResultEnum resultEnum){
        Result res = new Result();
        res.setCode(resultEnum.getCode());
        res.setMsg(resultEnum.getMsg());
        return res;
    }
    public static Result build(Integer code, String msg){
        Result res = new Result();
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }
    public static Result build(ResultEnum resultEnum, String msg){
        Result res = new Result();
        res.setCode(resultEnum.getCode());
        res.setMsg(msg);
        return res;
    }
    public static Result suc(Object data){
        Result res = new Result();
        res.setCode(ResultEnum.SUCCESS.getCode());
        res.setData(data);
        return res;
    }

}
