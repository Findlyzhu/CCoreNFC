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
 *
 *
 *
 *   @Author Findly_zhu
 *   @DATE 2018/7/10 17:40
 *   @Description This Class for Define Http Cmd
 **/
public class NetCmdDef {
    public static final int     TRN_REQHDR      = 0x5254;
    public static final short   TRN_RESPHDR     = 0x5253;
    public static final byte    CMD_GETRANDOM   = (byte)0xA1;
    public static final byte    CMD_VERIFY      = (byte)0xA2;
    public static final byte    CMD_GETINFO     = (byte)0xA3;

    /****************************** 通用错误码 **********************************/
    public static final int NFC_OK = 0;	/* 成功 */
    public static final int CR_INVALID_CMD = 1;	/* 无效命令字 */
    public static final int CR_INVALID_DATA = 2;	/* 无效数据 */
    public static final int CR_SIGNATURE_INVALID = 3;	/* 签名无效 */
    public static final int CR_CERT_INVALID = 4;	/* 证书无效 */

    /*************************** 服务器返回错误码 *******************************/
    public static final int SVR_AUTHOR_CODE = 100; /* 注册授权码错误 */
    public static final int SVR_NOT_REGISTER = 101;	/* 终端/摄像头没有注册 */
    public static final int SVR_ALREADY_REGISTER = 102;	/* 终端/摄像头已经注册 */
    public static final int SVR_NOT_LOGON = 103;	/* 没有登录 */
    public static final int SVR_ALREADY_LOGON = 104;	/* 已经登录 */
    public static final int SVR_NOT_PERMISSION = 105;	/* 没有权限 */
    public static final int SVR_CAMERA_NOT_ONLINE = 106; /* 摄像头不在线 */
    public static final int SVR_CONNECT_MAX = 107; /* 连接已达最大数 */
    public static final int SVR_USER_NOT_ONLINE = 108; /* 用户不在线 */
    public static final int SVR_SOCKET_ERROR = 198;	/* SOCKET错误 */
    public static final int SVR_INTERNAL = 199;	/* 服务器内部错误 */

}
