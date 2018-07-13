package com.ccore.jni;


import android.util.Log;

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
 * @DATE 2018/7/12 8:53
 * @Description This Class for SKF JNI
 **/
public class SKFJni {
    private String TAG = "SKF JAVA:";
    public SKFJni() {
        Log.e(TAG,"SKF JAVA IN...");
    }

    /**
     * @Function: 枚举设备
     * @param bPresent
     * @param pbDevNameList
     * @return
     */
    public native long SKF_EnumDev(boolean bPresent,byte[] pbDevNameList);

    /**
     * @Function: 连接设备
     * @return
     */
    public native long SKF_ConnectDev();

    /**
     * @Function: 获取设备信息
     * @param info
     * @return
     */
    public native long SKF_GetDevInfo(SKFType.DEVINFO info);

    /**
     * @Function: 获取文件属性
     * @param szFileName
     * @param FileInfo
     * @return
     */
    public native long SKF_GetFileInfo(String szFileName, SKFType.FILEATTRIBUTE FileInfo);

    /**
     * @Function: 断开设备
     * @return*/
    public native long SKF_DisconnectDev();

    /**
     * @Function: 设置应用路径
     * @param szAppPath
     * @return
     */
    public native long SKF_SetAppPath(String szAppPath);

    static {
        System.loadLibrary("SKFJni");
    }
}
