package com.hxd.utils.logutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

public class LogUtil {

    //log4j2会根据这个去找到配置的日志格式
    public static final String DEMOCONN_LOGGER_NAME = "DemoConnLog";

    //日志分割符 (日志的分割符，日志记录时的各个参数之间的分割，方便后期日志分析，以及查看)
    public static final String LOGGER_SEPERATOR = " - ";


//    public static final Logger loggerIotConn = LoggerFactory.getLogger(DEMOCONN_LOGGER_NAME);

    public static void printLog(Map<String, String> map) {
        try {
            StringBuffer sb = new StringBuffer();
            //设备类型
            sb.append(LOGGER_SEPERATOR + ((null == map.get("deviceType") || map.get("deviceType").equalsIgnoreCase("")) ? "-1" : map.get("deviceType")));
            //用户名
            sb.append(LOGGER_SEPERATOR + ((null == map.get("username") || map.get("username").equalsIgnoreCase("")) ? "-1" : map.get("username")));
            //请求映射
            sb.append(LOGGER_SEPERATOR + ((null == map.get("action") || map.get("action").equalsIgnoreCase("")) ? "-1" : map.get("action")));
            //其余参数
            sb.append(LOGGER_SEPERATOR + ((null == map.get("para1") || map.get("para1").equalsIgnoreCase("")) ? "-1" : map.get("para1")));
            sb.append(LOGGER_SEPERATOR + ((null == map.get("para2") || map.get("para2").equalsIgnoreCase("")) ? "-1" : map.get("para2")));
            sb.append(LOGGER_SEPERATOR + ((null == map.get("para3") || map.get("para3").equalsIgnoreCase("")) ? "-1" : map.get("para3")));
            sb.append(LOGGER_SEPERATOR + ((null == map.get("para4") || map.get("para4").equalsIgnoreCase("")) ? "-1" : map.get("para4")));
//            loggerIotConn.info(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
