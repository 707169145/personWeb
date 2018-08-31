package cn.yimi.filter;

import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 请求跨域处理
 */
public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        // 后续可修改为配置文件或者白名单的形式
        response.setHeader("Access-Control-Allow-Origin", "http://112.74.52.249");
//        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8848");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type,empid,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");


        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.getWriter().println(HttpStatus.OK);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
