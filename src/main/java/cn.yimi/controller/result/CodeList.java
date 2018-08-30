package cn.yimi.controller.result;

/**
 * 错误码列表
 * @author huangzs
 */
public class CodeList {
    private Integer code;
    private String msg;

    public static String getMsg(Integer code) {
        String msg = "";
        switch (code) {
            case -1:msg = "请求失败";
            break;
            case 0:msg = "请求成功";
            break;
            case 401:msg = "请登录后再进行操作";
            break;
            default:msg = "系统异常";
            break;
        }

        return msg + "!";
    }
}
