package com.ccore.ConstDef;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　 ┏┓     ┏┓
 * 　　┏┛┻━━━━━┛┻┓
 * 　　┃　　　　　 ┃
 * 　　┃　　━　　　┃
 * 　　┃　┳┛　┗┳  ┃
 * 　　┃　　　　　 ┃
 * 　　┃　　┻　　　┃
 * 　　┃　　　　　 ┃
 * 　　┗━┓　　　┏━┛　Code is far away from bug with the animal protecting
 * 　　　 ┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　┣┓
 * 　　　　┃　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　 ┃┫┫ ┃┫┫
 * 　　　　 ┗┻┛ ┗┻┛
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @Author Findly_zhu
 * @DATE 2018/7/10 18:35
 * @Description This Class for Opreation Flow
 **/
public class MsgConstDef {
    public static final int MSG_DEF = 0xff;
    /*************************** 操作MSG消息 *******************************/
    public static final int MSG_GETRANDOM   = 0;
    public static final int MSG_GETSIGN     = 1;
    public static final int MSG_GETCERT     = 2;
    public static final int MSG_VERIFY      = 3;
    public static final int MSG_GETINFO     = 4;
    /*************************** 操作服务器返回后的消息 *******************************/
    public static final int MSG_HTTP_OK     = 5;
    public static final int MSG_HTTP_FAIL   = 6;
    public static final int MSG_GETRAND_OK  = 7;
    public static final int MSG_GETRAND_FAIL= 8;
    public static final int MSG_VERIFY_OK   = 3;
    public static final int MSG_VERIFY_FAIL   = 3;
    public static final int MSG_GETINFO_OK  = 4;
    public static final int MSG_GETINFO_FAIL   = 3;
    /*************************** 操作设备返回后的消息 *******************************/
    public static final int MSG_SIGN_OK   = 5;
    public static final int MSG_SIGN_FAIL   = 5;
    public static final int MSG_GETCERT_OK= 8;
    public static final int MSG_GETCERT_FAIL= 8;
}
