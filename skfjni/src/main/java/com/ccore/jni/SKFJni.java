package com.ccore.jni;


import android.util.Log;

/**
 * ���������������޳�û������������
 * ���� ����     ����
 * ���������ߩ������������ߩ�
 * ���������������� ��
 * ��������������������
 * ���������ש�������  ��
 * ���������������� ��
 * �����������ߡ�������
 * ���������������� ��
 * ������������������������Code is far away from bug with the animal protecting
 * ������ ����������    ���ޱ���,������bug
 * ������������������
 * ��������������������������
 * �����������������������ǩ�
 * ��������������������������
 * �������������������ש�����
 * �������� ���ϩ� ���ϩ�
 * �������� ���ߩ� ���ߩ�
 * <p>
 * �������������о������թ�����������
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
     * @Function: ö���豸
     * @param bPresent
     * @param pbDevNameList
     * @return
     */
    public native long SKF_EnumDev(boolean bPresent,byte[] pbDevNameList);

    /**
     * @Function: �����豸
     * @return
     */
    public native long SKF_ConnectDev();

    /**
     * @Function: ��ȡ�豸��Ϣ
     * @param info
     * @return
     */
    public native long SKF_GetDevInfo(SKFType.DEVINFO info);

    /**
     * @Function: ��ȡ�ļ�����
     * @param szFileName
     * @param FileInfo
     * @return
     */
    public native long SKF_GetFileInfo(String szFileName, SKFType.FILEATTRIBUTE FileInfo);

    /**
     * @Function: �Ͽ��豸
     * @return*/
    public native long SKF_DisconnectDev();

    /**
     * @Function: ����Ӧ��·��
     * @param szAppPath
     * @return
     */
    public native long SKF_SetAppPath(String szAppPath);

    static {
        System.loadLibrary("SKFJni");
    }
}
