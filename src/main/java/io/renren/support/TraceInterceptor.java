package io.renren.support;

import cn.hutool.core.util.IdUtil;
import io.renren.constant.Constant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangxj
 * @date 2020-08-31 10:23
 * <p>
 * traceID
 */
public class TraceInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = StringUtils.defaultIfBlank(request.getHeader(Constant.TRACE_ID), IdUtil.fastUUID());
        MDC.put(Constant.TRACE_ID, traceId);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.remove(MDC.get(Constant.TRACE_ID));
    }
}
