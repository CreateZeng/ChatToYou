package com.zeng.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zeng.propertyEntry.WhitePathProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger= LoggerFactory.getLogger(PreRequestFilter.class);

    @Autowired
    private WhitePathProperties whitePathProperties;

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
        logger.info("into PreRequestFilter.shouldFilter()......");
        //静态方法获取RequestContext、拿到请求上下文后得到请求（zuul中操作数据的对象）
        RequestContext requestContext =RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //获取白名单列表、在白名单内不进行过滤验证
        List<String> whitePaths = whitePathProperties.getWhitePaths();
        for (String path : whitePaths) {
            String requestURI = request.getRequestURI();
            if (requestURI.startsWith(path)){
                logger.info("Present path is in whitepaths,shouldn't filter......,path is --"+requestURI);
                return false;
            }
        }
        logger.info("out of PreRequestFilter.shouldFilter()......,next excute run().....,path is --"+request.getRequestURI());
        return true;
    }

    /**
     * @自定义执行方法
     * @Params:
     * @Return:
    */
    @Override
    public Object run() throws ZuulException {
        logger.info("excute run()...,and filter user information.....");
        //获取当前请求上下文RequestContext(zuulFilter中操作请求的门户)
        RequestContext requestContext = RequestContext.getCurrentContext();
        logger.info("out of run().......");
        return null;
    }
}
