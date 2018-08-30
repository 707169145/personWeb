package cn.yimi.controller.result;

/**
 * 统一格式化返回
 * @author huangzs
 */
public class ResultBuilder {

    private static final String SUCCESS_MSG = "请求成功";
    private static final String FAIL_MSG = "请求失败";

    /**
     * 成功请求
     * @return
     */
    public static <T>ResultModal success(){
        return builder(0, SUCCESS_MSG,null);
    };
    public static <T>ResultModal success(T data){
        return builder(0, SUCCESS_MSG,data);
    };

    /**
     * 失败请求
     * @return
     */
    public static ResultModal fail() {
        return builder(-1, FAIL_MSG,null);
    }

    public static ResultModal fail(Integer code) {
        return builder(code, FAIL_MSG, null);
    }

    public static ResultModal fail(String msg) {
        return builder(-2, msg, null);
    }

    public static ResultModal fail(Integer code, String detailMsg) {
        return builder(code, detailMsg, null);
    }

    private static <T>ResultModal builder(Integer code, String detailMsg, T data) {
        ResultModal<T> resultModal = new ResultModal<T>();
        resultModal.setCode(code);
        resultModal.setData(data);
        resultModal.setDetailMsg(detailMsg);
        resultModal.setMsg(CodeList.getMsg(code));
        return resultModal;
    }
}
