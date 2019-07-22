package com.tfotu.shop.exception;

import com.tfotu.shop.domin.ResultEnum;

public class UnifyExeption extends RuntimeException {

    private Integer code;

    public UnifyExeption(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public UnifyExeption(ResultEnum resultEnum,String errMsg) {
        super(errMsg);
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
