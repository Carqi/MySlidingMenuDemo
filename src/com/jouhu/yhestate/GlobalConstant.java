package com.jouhu.yhestate;

/**
 * 
 *  Class Name: GlobalConstant.java
 *  
 *  @author Gao XuYang  DateTime 2014-1-26 上午9:09:05    
 *  @version 1.0
 *  @company 长春优狐科技开发有限公司
 */
public class GlobalConstant {
    public static final String KEY_DEBUG_ENABLE = "debug.enable";
    
    public interface StatusCodeConstant {
        public static final int UNKNOWN_EXCEPTION = 0;
        public static final int SUCCESS = 200;	//成功
        public static final int SUCCESS_PARTIAL_CONTENT = 206;	//特殊请求成功
        public static final int BAD_REQUEST = 400;	//请求出现语法错误
        public static final int UNAUTHORIZED = 401;	//用户登录过期
        public static final int REQUEST_FORB = 403;	//请求被拒绝
        public static final int NOT_FOUND = 404;	//没有找到指定的文件或目录
        public static final int SERVER_ERROR = 500;	//服务器异常
        public static final int SQL_EXCEPTION = 600;//SQL语句异常
        public static final int DB_CONNECTION_EXCEPTION = 603;//数据库连接异常
    	public static final int NO_SDCARD = 667;	//没有SDCARD异常

        public static final int NETWORK_NOT_ACCESS = 900; //Network can not access the result failed to upload
        public static final int PARSE_ERROR = 903;  //can't parse the response result
        public static final int SOCKET_EXCEPTION = 904; //Network can not access cause SocketException
        public static final int SOCKET_TIMEOUT_EXCEPTION = 905; //Network can not access cause SocketTimeoutException
        public static final int IO_EXCEPTION = 906; //Network can not access cause IOException
        public static final int UNKNOWN_HOST = 907; //Unknown Host Exception
        public static final int ILLEGAL_STATE = 908; //IllegalStateException
        public static final int ILLEGAL_ARGUMENT = 909; //IllegalArgumentException
        public static final int OFFLINE = 1000;
        public static final int NO_ROLE = 408; //当前用户没有权限访问系统
        public static final int UNKNOWUSER = 407; //当前用户没有权限访问系统
    }
  
    
}
