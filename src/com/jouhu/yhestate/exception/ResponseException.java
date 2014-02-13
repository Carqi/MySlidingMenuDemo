package com.jouhu.yhestate.exception;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;

import com.jouhu.yhestate.GlobalConstant.StatusCodeConstant;
import com.jouhu.yhestate.R;

import android.content.Context;
import android.text.TextUtils;

/**
 * 
 *  Class Name: ResponseException.java
 *  异常类
 *  @author Yu Liu  DateTime 2013-10-26 上午11:23:23    
 *  @version 1.0
 *  @company 长春优狐科技开发有限公司
 */
public class ResponseException extends Exception {
	private String message;//异常信息
    private int statusCode;//异常代码
    private static final Map<String, String> mHttpStatusCodeMap = new HashMap<String, String>();

	private static final long serialVersionUID = 1L;
	
	public ResponseException(){}
	
	public ResponseException(String message) {
		this.message = message;
	}

    public ResponseException(int statusCode) {
        this(statusCode, getMessage(statusCode));
    }

    public ResponseException(Exception e) {
        super(e);
        statusCode = exceptionToStatusCode(e);
        this.message = getMessage(statusCode);
    }
    
    public ResponseException(int statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }
    
    public int getStatusCode() {
		return statusCode;
	}
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessage() {
    	return this.message;
    }
    
    protected static void loadData(Context context, int resId, Map<String, String> map) {
        String[] lines = context.getResources().getStringArray(resId);
        for (String line : lines) {
            String[] fields = TextUtils.split(line, "=");
            if (fields == null || fields.length != 2) {
                continue;
            }

            map.put(fields[0], fields[1]);
        }
    }
    
    /**
     * 
     * <pre>
     * 加载异常信息
     * </pre>
     *
     * @param context 上下文
     */
    public static void loadMessageData(Context context) {
        loadData(context, R.array.status_codes, mHttpStatusCodeMap);
    }
    
    public synchronized static String getMessage(int statusCode) {
        String value = null;
        
        String key = String.valueOf(statusCode);
        if (mHttpStatusCodeMap.containsKey(key)) {
            value = mHttpStatusCodeMap.get(key);
        } else {
            value = mHttpStatusCodeMap.get(String.valueOf(StatusCodeConstant.UNKNOWN_EXCEPTION));
        }

        return value;
    }
    
    protected static int exceptionToStatusCode(Exception e) {
        int statusCode = StatusCodeConstant.UNKNOWN_EXCEPTION;
        if (e instanceof UnknownHostException) {
            statusCode = StatusCodeConstant.UNKNOWN_HOST;
        } else if (e instanceof IllegalArgumentException) {
            statusCode = StatusCodeConstant.ILLEGAL_ARGUMENT;
        } else if (e instanceof IllegalStateException) {
            statusCode = StatusCodeConstant.ILLEGAL_STATE;
        } else if (e instanceof SocketTimeoutException) {
            statusCode = StatusCodeConstant.SOCKET_TIMEOUT_EXCEPTION;
        } else if (e instanceof ConnectTimeoutException) {
            statusCode = StatusCodeConstant.SOCKET_TIMEOUT_EXCEPTION;
        } else if (e instanceof SocketException) {
            statusCode = StatusCodeConstant.SOCKET_EXCEPTION;
        } else if (e instanceof JSONException) {
            statusCode = StatusCodeConstant.PARSE_ERROR;
        } else if (e instanceof XmlPullParserException) {
            statusCode = StatusCodeConstant.PARSE_ERROR;
        } else if (e instanceof UnsupportedEncodingException) {
            statusCode = StatusCodeConstant.PARSE_ERROR;
        } else if (e instanceof IOException) {
            statusCode = StatusCodeConstant.IO_EXCEPTION;
        } else if (e instanceof ResponseException) { 
        	statusCode  = ((ResponseException) e).getStatusCode();
        }

        return statusCode;
    }
}
