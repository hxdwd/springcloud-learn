package com.hxd.utils.httputil;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class LogMaputil {

    public static void builMap(Map<String,String> map, HttpServletRequest request, String action){
        //用户名从传入的request的回话中获取用户信息，
        //  (User)request.getSession().getAttribute("user");
        //如果使用的是Shiro 也可以中Shiro中的会话中获取
        //  (User)SecurityUtils.getSubject().getPrincipal();
        map.put("username","lisi");
        map.put("ip",IPUtil.getIpAddress(request));
        map.put("deviceType",DeviceUtil.getDeviceType(request));
    }

}
