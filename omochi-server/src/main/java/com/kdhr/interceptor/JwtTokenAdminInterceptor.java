package com.kdhr.interceptor;

import com.kdhr.constant.JwtClaimsConstant;
import com.kdhr.context.BaseContext;
import com.kdhr.properties.JwtProperties;
import com.kdhr.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt令牌校驗的攔截器
 */
@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校驗jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //TODO 預設不檢驗Token
        if (true) {
            return true;
        }
        //判斷目前攔截到的是Controller的方法還是其他資源
        if (!(handler instanceof HandlerMethod)) {
            //目前攔截到的不是動態方法，直接放行
            return true;
        }

        //1、從請求頭中取得令牌
        String token = request.getHeader(jwtProperties.getAdminTokenName());

        //2、校驗令牌
        try {
            log.info("jwt校驗:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(), token);
            Long empId = Long.valueOf(claims.get(JwtClaimsConstant.EMP_ID).toString());
            BaseContext.setCurrentId(empId);
            log.info("現任員工id：", empId);
            //3、通過，放行
            return true;
        } catch (Exception ex) {
            //4、不通過，回應401狀態碼
            response.setStatus(401);
            return false;
        }
    }
}