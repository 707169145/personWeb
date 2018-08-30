package cn.yimi.controller.result;

/**
 * 数据返回模型
 * @author huangzs
 */
public class ResultModal<T> {
    // 请求信息
    private String msg;
    // 错误代码
    private Integer code;
    // 请求数据
    private T data;
    // 详细信息
    private String detailMsg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDetailMsg() {
        return detailMsg;
    }

    public void setDetailMsg(String detailMsg) {
        this.detailMsg = detailMsg;
    }
}
