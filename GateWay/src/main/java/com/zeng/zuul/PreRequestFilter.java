package com.zeng.zuul;

import com.netflix.discovery.converters.Auto;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zeng.config.FilterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author：Zeng-Jin
 * @Motto：不积跬步、无以至千里。
 * @Date：2020-08-18
 * @ZullFilter过滤器
 **/
@Component
public class PreRequestFilter extends ZuulFilter{

    @Autowired
    private FilterProperties filterProperties;

    /**
     * @过滤器类型  pre前置 routing路由 post后置  error错误过滤器
     * @Params:
     * @Return:
    */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    /**
     * @执行顺序
     * @Params:
     * @Return:
    */
    @Override
    public int filterOrder() {
        return FilterConstants.FORM_BODY_WRAPPER_FILTER_ORDER+1;
    }

    /**
     * @过滤器开关--true过滤---false不过滤、此处可以进行一些鉴权行为
     * @Params:
     * @Return:
    */
    @Override
    public boolean shouldFilter() {
        //静态方法获取RequestContext、拿到请求上下文后得到请求（zuul中操作数据的对象）
        RequestContext requestContext =RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //获取白名单列表、在白名单内不进行过滤验证
        List<String> whitePaths = filterProperties.getWhitePaths();
        for (String path : whitePaths) {
            String requestURI = request.getRequestURI();
            if (requestURI.startsWith(path)){
                return false;
            }
        }
        return true;
    }

    /**
     * @自定义执行方法
     * @Params:
     * @Return:
    */
    @Override
    public Object run() throws ZuulException {
        System.out.println("进行登录验证.......");
        //获取当前请求上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        return null;
    }
}
