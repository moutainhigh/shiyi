
package com.baibei.shiyi.common.tool.api;

/**
 * @author 会跳舞的机器人
 * @version 1.0
 * @Description Controller层返回的统一结果对象信息码
 * @date 2015年8月31日 下午11:58:29
 */
public enum ResultEnum {
    SUCCESS(200, "成功"),// 成功
    ERROR(-999, "系统错误"),// 系统错误
    BAD_PARAM(-998, "参数错误"),// 参数错误
    NOT_FOUND(-997, "接口不存在"),// 接口不存在
    SERVIE_FAIL(-996, "服务调用失败"),// 服务调用失败
    BUSINESS_ERROE(-995, "业务异常"), // 默认服务层抛出异常的状态码,可细分下发具体服务的异常码
    ACCESS_TOKEN_ERROE(-994, "访问令牌错误或过期"),
    REPEAT_SUBMIT(-993, "请勿重复提交"), // 请勿重复提交
    BUSINESS_VALIDATE_FAIL(-992, "业务校验失败"),


    // 用户服务异常-100开始
    USER_NOT_EXIST(-101, "用户不存在"),
    MOBILE_VERIFICATION_CODE_ERROR(-102, "短信验证码错误"),
    USER_LOCK(-104, "密码错误次数超过5次，请明天再试"),
    USER_PASSWORD_ERROR(-105, "用户名或密码不正确"),
    LOGIN_VERIFICATION_CODE_ERROR(-106, "登录验证码错误"),
    USER_PASSWORD_ERRO(-107, "登录密码错误"),
    REGISTER_PARAM_NONSTANDARD(-108, "用户名或密码参数不规范"),
    INVITER_NOT_EXIST(-109, "邀请人不存在"),
    LIMIT_LOGIN(-110, "账号状态异常，请联系客服"),
    USER_EXIST(-111, "手机号已注册，请直接登录"),
    USERNAME_EXIST(-112, "用户名已存在"),
    MOBILE_NOT_EXIST(-113, "手机号未注册，请先注册"),
    // 账户服务异常-200开始
    CREATE_ACCOUNT_FAIL(-201, "开户失败"),
    BALANCE_NOT_ENOUGH(-202, "余额不足"),
    ACCOUNT_NOT_EXIST(-203, "账户不存在"),
    ACCOUNT_PASSWORD_ERRO(-204, "资金密码不正确"),
    SIGNING_AGE_ERROR(-205, "根据相关要求，个人申请者须年满18周岁，不满65周岁"),
    // 商品服务异常-300开始
    PRODUCT_TRADE_NO(-301, "商品交易编码不存在"),
    PRODUCT_QUOTE_SERVER(-302, "调用行情服务接口异常"),
    PRODUCT_PRO_TYPE_DELETE(-303, "部分有商品的类型不能进行删除"),

    // 交易服务异常-400开始
    ISNOT_TRADE_DAY(-401, "非开市时间"),
    VALIDATE_ERROR(-402, "交易参数校验异常"),
    PRODUCT_NOT_TRADING(-403, "商品非交易时间"),
    CUSTOMER_NOT_TRADE(-404, "客户不允许交易"),
    REALNAMEVERIFICATION_NOT(-405, "跳转到实名页面"),
    SIGNING_NOT(-406, "跳转到开户页面"),
    LIST_BUY_FAIL(-407, "挂牌买入失败"),
    DELIST_DEAL(-408, "挂牌信息已过期，请重新选择挂牌商"),


    // 公共服务异常-500开始
    PUBLIC_CODE_FAIL(-501, "短信验证码不存在"),
    PUBLIC_SMS_CODE(-502, "调用短信渠道失败"),
    MOBILE_NUM_CHECKOUT(-503, "手机号应为11位数"),
    MOBILE_TYPE_CHECKOUT(-504, "短信类型不能为空"),
    SMS_COUNT_CHECKOUT(-505, "一天之内的短信次数达到上限"),
    SMS_TIME_OUT(-506, "验证码已下发，请查收"),
    SMS_TIME_MAX(-507, "该短信已经过期，请重新获取"),
    // 行情服务异常-600开始

    // 出入金服务异常-700开始

    // 后台服务异常-800开始
    ADMIN_SC_UNAUTHORIZED(-800, "权限不够"),
    ADMIN_SC_DUPLICATE(-801, "数据重复"),
    ADMIN_SC_ACCOUNT_BALANCE_ERROR(-802, "数据导入失败或部分失败,是否查看"),

    // 商城订单服务-900开始
    CAN_NOT_BUY(-901, "不能购买"),
    BALANCE_NOT_ENOUGHT(-902, "余额不足"),
    PRODUCT_UNSHELF(-903, "商品已下架");

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 根据状态码获取到对应的提示信息
     *
     * @param code
     * @return
     */
    public static String getMsg(int code) {
        for (ResultEnum resultCodeMsg : values()) {
            if (resultCodeMsg.getCode() == code) {
                return resultCodeMsg.getMsg();
            }
        }
        return null;
    }
}
