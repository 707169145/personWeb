package cn.yimi.filter;


import cn.yimi.util.IpUtil;
import cn.yimi.util.SessionUtil;
import cn.yimi.util.TokenUtil;
import com.sun.jndi.cosnaming.IiopUrl;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录拦截
 * 对不允许未登录用户进行请求的接口进行保护
 */
public class LoginFilter implements Filter {

    // 访问白名单
    List<String> passList =  new ArrayList<String>();
    // 访问黑名单
    List<String> blackList = new ArrayList<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        blackList.add("/system/");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        // 从请求头中取出请求信息
        String token = request.getHeader("token");
        String empid = request.getHeader("empid");

        // 设置访问用户id,ip
        SessionUtil.setSession("empid", StringUtils.isEmpty(empid) || empid.equals("touries")? IpUtil.getIpAddress(request): empid);
        SessionUtil.setSession("ip", IpUtil.getIpAddress(request));

        // 不在黑名单中的请求地址直接跳过
        for (String url : blackList) {
            if (-1 ==  request.getRequestURL().indexOf(url)) {
                filterChain.doFilter(request,response);
                return;
            }
        }

        if (TokenUtil.verifyToken(token, empid)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 拦截请求
        request.getRequestDispatcher("/OAuth/tokenError.action").forward(request, response);
        return;
    }

    @Override
    public void destroy() {

    }
}
