//package com.example.demo.config;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.nimbusds.openid.connect.sdk.claims.UserInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import javax.management.relation.RoleInfo;
//import java.util.List;
//
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private RoleInfoService roleInfoService;
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        log.debug("开始登陆验证，用户名为: {}",s);
//
//        // 根据用户名验证用户
//        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.lambda().eq(UserInfo::getLoginAccount,s);
//        UserInfo userInfo = userService.getOne(queryWrapper);
//        if (userInfo == null) {
//            throw new UsernameNotFoundException("用户名不存在，登陆失败。");
//        }
//
//        // 构建UserDetail对象
//        UserDetail userDetail = new UserDetail();
//        userDetail.setUserInfo(userInfo);
//        List<RoleInfo> roleInfoList = roleInfoService.listRoleByUserId(userInfo.getUserId());
//        userDetail.setRoleInfoList(roleInfoList);
//        return userDetail;
//    }
//}
