package com.example.authorizationwithdb.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 决定某些角色是否能访问给定的资源
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {


    /**
     *
     * @param authentication 存有当前用户的角色（权限）
     * @param object 资源
     * @param configAttributes 访问object资源所需的权限
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        //从authentication中获取当前用户具有的角色(权限)
        Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();
        //权限匹配，configAttributes是资源object所需权限，这里是有metaDataSource从数据库取出的
        for (ConfigAttribute configAttribute : configAttributes) {
            String role = configAttribute.getAttribute();
            if("ROLE_NONE".equals(role)){
                if(authentication instanceof AnonymousAuthenticationToken){
                    throw new BadCredentialsException("用户未登录");
                }
                else return;
            }
            for (GrantedAuthority userAuthority : userAuthorities) {
                if(userAuthority.getAuthority().equals("ROLE_ADMIN")) return;
                if(userAuthority.getAuthority().equals(role)) return;
            }
        }
        throw new AccessDeniedException("你没有访问"+object+"的权限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
