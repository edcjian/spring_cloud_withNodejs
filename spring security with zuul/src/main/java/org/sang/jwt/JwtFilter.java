package org.sang.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

/**
 * @Author 江南一点雨
 * @Date 2019-03-30 17:01
 */
public class JwtFilter extends GenericFilterBean {
    /**
     * 所有的其他请求都会来到这个方法中
     * 在这个方法中，提取出客户端传来的 JWT Token，并进行解析，并查看用户角色等信息
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String jwtToken = req.getHeader("authorization");
//       String opt =Optional.ofNullable(jwtToken).orElseThrow(()->new SignatureException("unkown"));
        if (StringUtils.isNotEmpty(jwtToken)) {


                System.out.println(jwtToken);
                Claims claims = Jwts.parser().setSigningKey("sang@123").parseClaimsJws(jwtToken.replace("Bearer", ""))
                        .getBody();
                String username = claims.getSubject();//获取当前登录用户名
                List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(token);
                filterChain.doFilter(req, servletResponse);


        }
        else{
                            HttpServletResponse resp =(HttpServletResponse)servletResponse;
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.write("没有token");
                out.flush();
                out.close();
        }
    }
}