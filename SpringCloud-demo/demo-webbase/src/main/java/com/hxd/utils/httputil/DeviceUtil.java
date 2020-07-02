package com.hxd.utils.httputil;

import javax.servlet.http.HttpServletRequest;

/*用来获取设备类型*/
public class DeviceUtil {

    /**
     * 判断登陆设备(PC,IPHONE,ANDROID,APP,UNKOWN)
     *
     * @Title: deviceIdentify
     * @Description:
     *      PC：电脑浏览器,
     *      IPHONE：苹果手机浏览器,
     *      ANDROID：安卓手机浏览器,
     *      APP：APP端访问,
     *      UNKOWN：未知设备类型
     * @param request
     * @return
     */
    public static String getDeviceType(HttpServletRequest request) {
        // 默认为pc电脑
        String device = "PC";
        if (null != request) {
            String userAgent = request.getHeader("user-agent");
            if (null == userAgent)
                return "UNKOWN";

            if (userAgent.indexOf("bzzApp") != -1) {
                device = "APP";
            } else {
                if (userAgent.indexOf("iPhone") != -1) {
                    device = "IPHONE";
                } else if (userAgent.indexOf("Android") != -1) {
                    device = "ANDROID";
                }
            }
        }
        return device;
    }

}
