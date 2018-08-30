package cn.yimi.dto;

/**
 * 拜访记录
 * @author huangzs
 */
public class VisitInfo {
    // 访问时间
    private String visit_time;
    // 访问者
    private String visiter;
    // 访问ip
    private String visit_ip;
    // 访问物理地址
    private String visit_address;

    public String getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(String visit_time) {
        this.visit_time = visit_time;
    }

    public String getVisiter() {
        return visiter;
    }

    public void setVisiter(String visiter) {
        this.visiter = visiter;
    }

    public String getVisit_ip() {
        return visit_ip;
    }

    public void setVisit_ip(String visit_ip) {
        this.visit_ip = visit_ip;
    }

    public String getVisit_address() {
        return visit_address;
    }

    public void setVisit_address(String visit_address) {
        this.visit_address = visit_address;
    }
}
