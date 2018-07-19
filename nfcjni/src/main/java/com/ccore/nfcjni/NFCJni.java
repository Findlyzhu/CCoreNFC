package com.ccore.nfcjni;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.util.Log;

import java.io.IOException;

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
 * @DATE 2018/7/16 10:24
 * @Description This Class for NFC Write and Read
 **/
public class NFCJni extends Object {
    private final String TAG = "NFC JAVA";
    private Context       context = null;
    private String        nfcAction      = "";
    private NfcAdapter nfcAdapter = null;
    private PendingIntent pendingIntent = null;
    private Tag           tag           = null;
    private IsoDep        currentisodep = null;
    public  boolean       recvIsoDep    = false;
    private IntentFilter[]  intentFilters;
    private byte[] 		    mRecvData;
    public NFCJni(final Context context_new){
        super();
        Log.e(TAG,"NFC JAVA IN:");
        context = context_new;
        nfcAdapter = NfcAdapter.getDefaultAdapter(context);
        if(nfcAdapter==null){
            Log.e(TAG, "device is not support NFC!");
            return;
        }

        intentFilters = new IntentFilter[] { new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED),};
        //check NFC function is open
        if (!nfcAdapter.isEnabled()){
            Log.e(TAG, "Not open function NFC!");
            return;
        }
        //create PendingIntent, when tag received, get a intent
        pendingIntent=PendingIntent.getActivity(context,0,new Intent(context,context.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),0);
    }

    public PendingIntent getPendingInt(){
        return pendingIntent;
    }

    public NfcAdapter getNfcAdapter(){
        return nfcAdapter;
    }

    public Tag        getNfcTag(){
        return tag;
    }

    public IsoDep     getNfcIsoDep(){
        return currentisodep;
    }

    public void recvNewTag(Intent intent)
    {
        String[] 	  techList;
        tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        recvIsoDep = false;
        techList = tag.getTechList();
        for (String tech:techList){
            if(tech.indexOf("IsoDep")>0){
                recvIsoDep = true;
                Log.v(TAG, "recv new IsoDep tag");
            }
        }
        //get intent nfc action
        nfcAction = intent.getAction();
    }

    public void disableDispatch() {
        if(nfcAdapter!=null){
            nfcAdapter.disableForegroundDispatch((Activity) context);//关闭前台发布系统
        }
    }

    public void enableDispatch() {
        if (nfcAdapter!=null){
            //            mNfcAdapter.enableForegroundDispatch(this,mPendingIntent,null,null);
            nfcAdapter.enableForegroundDispatch((Activity) context,pendingIntent,null,null);//打开前台发布系统，使页面优于其它nfc处理.当检测到一个Tag标签就会执行mPendingItent
        }
    }

    public int opendev(){
        try{
            Log.e(TAG,"opendev ...");
            if (tag==null){
                return -1;
            }
            if(recvIsoDep == false)
            {
                return -1;
            }
            if(nfcAdapter.ACTION_TAG_DISCOVERED.equals(nfcAction)){
                currentisodep = IsoDep.get(tag);
                currentisodep.connect();
            }
            Log.e(TAG,"opendev ok");
            return 0;
        }
        catch (IOException e){
            Log.e(TAG, "Ccore Tag is not discovered!");
            e.printStackTrace();
        }
        return -1;
    }

    public int closedev(){
        try{
            Log.e(TAG,"closedev ...");
            currentisodep.close();
            Log.e(TAG,"closedev ok");
            return 0;
        }
        catch (IOException e){
            Log.e(TAG, "Ccore Tag is not discovered!");
            e.printStackTrace();
        }
        return -1;
    }

    public static String bytes2HexStr(byte[] src){
        if (src == null || src.length <= 0) {
            return null;
        }
        int len = src.length;
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < len; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            hv = hv.toUpperCase();
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public int senddata(byte[] sendData,int sendlen){
        if(sendData != null && sendData.length > 4&&sendData.length==sendlen)
        {
            try {
                if (tag==null){
                    return -1;
                }
                if(recvIsoDep == false)
                {
                    return -1;
                }
                String s = bytes2HexStr(sendData);
                Log.e(TAG,"senddata :"+s);
                mRecvData = currentisodep.transceive(sendData);

                if(mRecvData.length>=2)
                {
                    Log.e(TAG,"senddata ok");
                    return 0;
                }
                return -1;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.e(TAG, "Ccore Tag send data error!");
                e.printStackTrace();
                return -1;
            }
        } else {
            return -1;
        }
    }

    public int readlen(){
        if (tag==null){
            return -1;
        }
        if(recvIsoDep == false)
        {
            return -1;
        }
        if(mRecvData.length<2){
            return -1;
        }
        return mRecvData.length;
    }

    public byte[] readdata(){
        if (tag==null){
            return null;
        }
        if(recvIsoDep == false)
        {
            return null;
        }
        if(mRecvData.length<2){
            return null;
        }
        String r = bytes2HexStr(mRecvData);
        Log.e(TAG,"readdata :"+r);
        return mRecvData;
    }

    //nfc write data, return read data
    public int transmit(byte[] sendData,int sendlen,byte[] recvData,int[] recvlen)
    {
        if(sendData != null && sendData.length > 4&&sendData.length==sendlen)
        {
            try {
                Log.e(TAG,"transmit ...");
                mRecvData = currentisodep.transceive(sendData);
                for (int i = 0; i < mRecvData.length; i++) {

                    recvData[i] = mRecvData[i];
                }
                recvlen[0] = mRecvData.length;
                return 0;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Log.e(TAG, "Ccore Tag send data error!");
                e.printStackTrace();
                return -1;
            }
        } else {
            return -1;
        }
    }

    /**
     * @Function: 枚举设备
     * @param bPresent
     * @param pbDevNameList
     * @return
     */
    public native long SKF_EnumDev(boolean bPresent,char[] pbDevNameList);

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


    /**
     * @Function: 设置密钥
     * @param key
     * @param algID
     * @return
     */
    public native long SKF_SetSymmKey(byte[] key, int algID);

    /**
     * @Function: 对称算法初始化
     * @param param
     * @return
     */
    public native long SKF_EncryptInit(SKFType.BLOCKCIPHERPARAM param);

    /**
     * @Function: 单组对称加密
     * @param pbData
     * @param ulDataLen
     * @param pbEncryptedData
     * @param pulEncryptedLen
     * @return
     */
    public native long SKF_Encrypt(byte[] pbData, long ulDataLen,
                                   byte[] pbEncryptedData, long[] pulEncryptedLen);


    /**
     * @Function: 对称加密Update
     * @param pbData
     * @param ulDataLen
     * @param pbEncryptedData
     * @param pulEncryptedLen
     * @return
     */
    public native long SKF_EncryptUpdate(byte[] pbData, long ulDataLen,
                                         byte[] pbEncryptedData, long[] pulEncryptedLen);

    /**
     * @Function: 对称加密Final
     * @param pbEncryptedData
     * @param pulEncryptedLen
     * @return
     */
    public native long SKF_EncryptFinal(byte[] pbEncryptedData,
                                        long[] pulEncryptedLen);

    /**
     * @Function: 对称解密初始化
     * @param param
     * @return
     */
    public native long SKF_DecryptInit(SKFType.BLOCKCIPHERPARAM param);

    /**
     * @Function: 单组解密
     * @param pbData
     * @param ulDataLen
     * @param pbEncryptedData
     * @param pulEncryptedLen
     * @return
     */
    public native long SKF_Decrypt(byte[] pbData, long ulDataLen,
                                   byte[] pbEncryptedData, long[] pulEncryptedLen);

    /**
     * @Function: 对称解密Update
     * @param pbData
     * @param ulDataLen
     * @param pbEncryptedData
     * @param pulEncryptedLen
     * @return
     */
    public native long SKF_DecryptUpdate(byte[] pbData, long ulDataLen,
                                         byte[] pbEncryptedData, long[] pulEncryptedLen);

    /**
     * @Function: 对称解密Final
     * @param pbEncryptedData
     * @param pulEncryptedLen
     * @return
     */
    public native long SKF_DecryptFinal(byte[] pbEncryptedData,
                                        long[] pulEncryptedLen);

    /**
     * @Function: 获取设备状态
     * @param szDevName
     * @param ulDevState
     * @return
     */
    public native long SKF_GetDevState(String szDevName,Integer ulDevState);

    /**
     * @Function: 设置标签
     * @param szLabelName
     * @return
     */
    public native long SKF_SetLabel(String szLabelName);

    /**
     * @Function: 锁定设备
     * @param ulTimeOut
     * @return
     */
    public native long SKF_LockDev(int ulTimeOut);

    /**
     * @Function: 解锁设备
     * @return
     */
    public native long SKF_UnlockDev();

    /**
     * @Function: 透传指令
     * @param pbCommand
     * @param ulCommandLen
     * @param pbData
     * @param pulDataLen
     * @return
     */
    public native long SKF_Transmit(byte[] pbCommand,long ulCommandLen,byte[] pbData,long pulDataLen);

    /**
     * @Function: 改变设备认证密钥
     * @param pbKeyValue
     * @param ulKeyLen
     * @return
     */
    public native long SKF_ChangeDevAuthKey(byte[] pbKeyValue,long ulKeyLen);

    /**
     * @Function: 设备认证
     * @param pbAuthData
     * @param ulLen
     * @return
     */
    public native long SKF_DevAuth(byte[] pbAuthData,long ulLen);

    /**
     * @Function: 改变PIN码
     * @param ulPinType
     * @param szOldPin
     * @param szNewPin
     * @param pulRetry
     * @return
     */
    public native long SKF_ChangePIN(int ulPinType,String szOldPin,String szNewPin,Integer pulRetry);

    /**
     * @Function: 获取PIN码信息
     * @param ulPinType
     * @param pulMaxRetryCount
     * @param pulRemainRetryCount
     * @param pbDefaultPin
     * @return
     */
    public native long SKF_GetPINInfo(int ulPinType,Integer pulMaxRetryCount,
                                      Integer pulRemainRetryCount, boolean[] pbDefaultPin);

    /**
     * @Function: 验证PIN码
     * @param ulPinType
     * @param szOldPin
     * @param pulRetry
     * @return
     */
    public native long SKF_VerifyPIN(int ulPinType,String szOldPin,Integer pulRetry);

    /**
     * @Function: 复原PIN 码
     * @param szAdminPin
     * @param szNewUserPin
     * @param pulRetry
     * @return
     */
    public native long SKF_UnblockPIN(byte[] szAdminPin,String szNewUserPin,Integer pulRetry);

    /**
     * @Function: 清楚安全状态
     * @return
     */
    public native long SKF_ClearSecureState();

    /**
     * @Function: 创建应用
     * @param szAppName
     * @param szNewUserPin
     * @param ulAdminPinRetry
     * @param szAdminPin
     * @param ulUserPinRetry
     * @param ulCreateFileRights
     * @return
     */
    public native long SKF_CreateApplication(byte[] szAppName,byte[] szNewUserPin,long ulAdminPinRetry,byte[] szAdminPin,long ulUserPinRetry,long ulCreateFileRights);

    /**
     * @Function: 枚举应用
     * @param szAppName
     * @param pulSize
     * @return
     */
    public native long SKF_EnumApplication(byte[] szAppName,Integer pulSize);

    /**
     * @Function: 删除应用
     * @param szAppName
     * @return
     */
    public native long SKF_DeleteApplication(byte[] szAppName);

    /**
     * @Function: 打开应用
     * @param szAppName
     * @return
     */
    public native long SKF_OpenApplication(byte[] szAppName);

    /**
     * @Function: 关闭应用
     * @return
     */
    public native long SKF_CloseApplication();

    /**
     * @Function: 创建文件
     * @param szFileName
     * @param ulFileSize
     * @param ulReadRights
     * @param ulWriteRights
     * @return
     */
    public native long SKF_CreateFile(byte[] szFileName, long ulFileSize,long ulReadRights,long ulWriteRights);

    /**
     * @Function: 删除文件
     * @param szFileName
     * @return
     */
    public native long SKF_DeleteFile(byte[] szFileName);

    /**
     * @Function: 枚举文件
     * @param szFileList
     * @param FileListSize
     * @return
     */
    public native long SKF_EnumFiles(byte[] szFileList,Integer FileListSize);

    /**
     * @Function: 读取文件内容
     * @param szFileName
     * @param offSet
     * @param Size
     * @param pbOutData
     * @param OutDataLen
     * @return
     */
    public native long SKF_ReadFile(byte[] szFileName,long offSet,long Size,byte[] pbOutData,Integer OutDataLen);

    /**
     * @Function: 写入文件内容
     * @param szFileName
     * @param ulOffset
     * @param pbData
     * @param ulSize
     * @return
     */
    public native long SKF_WriteFile(byte[] szFileName, long ulOffset, byte[] pbData, long ulSize);

    /**
     * @Function: 创建容器
     * @param szContainerName
     * @return
     */
    public native long SKF_CreateContainer(byte[] szContainerName);

    /**
     * @Function: 删除容器
     * @param szContainerName
     * @return
     */
    public native long SKF_DeleteContainer(byte[] szContainerName);

    /**
     * @Function: 打开容器
     * @param szContainerName
     * @return
     */
    public native long SKF_OpenContainer(byte[] szContainerName);

    /**
     * @Function: 关闭容器
     * @return
     */
    public native long SKF_CloseContainer();

    /**
     * @Function: 枚举容器
     * @param szContainerName
     * @param CountainerSize
     * @return
     */
    public native long SKF_EnumContainer(byte[] szContainerName,Integer CountainerSize);

    /**
     * @Function: 获取容器类型
     * @param ContainerType
     * @return
     */
    public native long SKF_GetContainerType(Integer ContainerType);

    /**
     * @Function: 获取随机数
     * @param pbRandom
     * @param ContainerType
     * @return
     */
    public native long SKF_GenRandom(byte[] pbRandom,int ContainerType);

    /**
     * @Function: 获取外部密钥
     * @param ulBitlen
     * @param Blob
     * @return
     */
    public native long SKF_GenExtRSAKey(int ulBitlen, SKFType.RSAPRIVATEKEYBLOB Blob);

    /**
     * @Function: 生成RSA密钥对
     * @param ulBitlen
     * @param Blob
     * @return
     */
    public native long SKF_GenRSAKeyPair(int ulBitlen, SKFType.RSAPRIVATEKEYBLOB Blob);

    /**
     * @Function: 导入RSA密钥对
     * @param ulSymAlgId
     * @param pbWrappedKey
     * @param ulWrappedKeyLen
     * @param pbEncryptedData
     * @param ulEncryptedDataLen
     * @return
     */
    public native long SKF_ImportRSAKeyPair(int ulSymAlgId, byte[] pbWrappedKey, int ulWrappedKeyLen,byte[] pbEncryptedData,int ulEncryptedDataLen);

    /**
     * @Function: 生成RSA签名值
     * @param pbData
     * @param ulDataLen
     * @param pbSignature
     * @param pulSignLen
     * @return
     */
    public native long SKF_RSASignData(byte[] pbData, int ulDataLen,byte[] pbSignature,Integer pulSignLen);

    /**
     * @Function: RSA验签
     * @param Blob
     * @param pbData
     * @param ulDatalen
     * @param pbSignature
     * @param ulSignLen
     * @return
     */
    public native long SKF_RSAVerify(SKFType.RSAPUBLICKEYBLOB Blob, byte[] pbData, int ulDatalen, byte[] pbSignature, int ulSignLen);

    public native long NFC_GenRandom(byte[] pbRandom,int cbRandom);

    public native long NFC_GetDevInfo(SKFType.DEVINFO info);

    public native long transmitJni(byte[] InData,int InLen,byte[] OutData,int []OutLen);

    public native long V_SetNFC_Class();

    static {
        System.loadLibrary("NFCJni");
    }


}
