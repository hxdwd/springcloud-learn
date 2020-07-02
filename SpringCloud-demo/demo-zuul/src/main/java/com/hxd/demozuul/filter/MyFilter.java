package com.hxd.demozuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyFilter extends ZuulFilter {

    //此方法返回不同的字符串代表了此过滤器的执行时机
    /**
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     * */
    public String filterType() {
        return "pre";
    }

    //过滤的顺序数字越大优先级越低
    public int filterOrder() {
        return 0;
    }

    //是否过滤 true为过滤 false不过滤
    public boolean shouldFilter() {
        return true;
    }


    //具体过滤内容如一些权限判断
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        String name = request.getParameter("");
        if(!"zhangsan".equals(name)){
            try {
                //注意需要设置这两项
                //不然前台不会打印name error
                //会以为是500
                currentContext.setSendZuulResponse(false);
                currentContext.setResponseStatusCode(403);
                currentContext.getResponse().getWriter().write("name error");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        return null;
    }


}
