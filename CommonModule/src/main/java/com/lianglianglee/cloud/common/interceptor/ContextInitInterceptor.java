package com.lianglianglee.cloud.common.interceptor;

import com.lianglianglee.cloud.common.model.EditJwtModel;
import com.lianglianglee.cloud.common.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 将从传来的值，存储在线程上下文中。供service, controller调用.
 */
public class ContextInitInterceptor implements HandlerInterceptor {
  @Autowired
  private JwtService jwtAuthenticationService = new JwtService();

  private static Logger logger = LoggerFactory.getLogger(ContextInitInterceptor.class);

  @Override
  public void afterCompletion(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    logger.info("请求结束：" + httpServletRequest.getRequestURI());
    HttpContext.removeContext();
  }

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response, Object o) throws Exception {
    String requestUri = request.getRequestURI();
    logger.info("请求开始：" + request.getMethod() + "   " + requestUri);
    if (requestUri.contentEquals
      ("/api/user/login")) {
      return true;
    }
    if ("options".equals(request.getMethod().toLowerCase())) {
      return true;
    }
    if ("post".equals(request.getMethod().toLowerCase()) && requestUri.equals("/api/user")) {
      return true;
    }
    try {
      String token = request.getHeader("authorization");
      EditJwtModel model = jwtAuthenticationService.customizedValidation(token);
      HttpContext.getContext().setDeviceType(model.getDeviceType());
      HttpContext.getContext().setToken(token);
      HttpContext.getContext().setUserId(model.getId());
      HttpContext.getContext().setUserName(model.getUserName());
      return true;
    } catch (Exception e) {
      logger.info("登录失败：", e);
      response.setHeader("Access-Control-Allow-Origin", "*");
      response.setStatus(401);
      response.flushBuffer();
      return false;
    }
  }

  @Override
  public void postHandle(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView)
    throws Exception {

  }
}
