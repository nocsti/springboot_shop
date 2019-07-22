package com.tfotu.shop.domin;

/**
 * 统一结果
 */
public enum ResultEnum {
    UNKNOWN_ERROR( 0, "未知错误！"),
    SUCCESS( 1, "成功！"),
    USER_NOT_LOGIN( 1601, "账号未登录，请登陆后操作！"),
    PARAM_ERROR( 2000, "参数错误！"),
    MOBILE_ERROR( 2001, "手机号不正确！"),
    MOBILE_BEEN_USE( 2002, "手机号已被注册！"),
    SMS_SEND_ERROR( 2003, "短信验证码发送失败！"),
    SMS_CHECK_ERROR( 2004, "短信验证码与下发的不相符！"),
    USER_LOGIN_ERROR( 2005, "手机号或密码填写错误！"),
    TURE_ORDER_GOODS_NULL( 2006, "商品信息获取失败，请返回重新下单！"),
    ORDER_USER_ADDR_NULL( 2007, "订单收货地址不能为空！"),
    ORDER_CREATE_ERROR( 2008, "订单创建失败！"),
    ORDER_GOODS_CREATE_ERROR( 2009, "订单商品创建快照失败！"),
    USER_ADDR_NOT_EXIST( 2010, "收货地址不存在！"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
