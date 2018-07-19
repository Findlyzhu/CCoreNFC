//
// Created by Findly on 2018/7/16.
//

#include "until.h"
#ifndef ANDROID
#define ANDROID
#endif
typedef struct __android_nfc_dev {
	JNIEnv*			env;
	jobject 		obj;
} NFC_DEV;
NFC_DEV nfc_res = {0};
DEVHANDLE hDev = NULL_PTR;
HANDLE * hKey = NULL_PTR;
HAPPLICATION * hAppHandle = NULL_PTR;
HCONTAINER * hContainerHandle = NULL_PTR;
/*
 * 连接设备
 */
JNIEXPORT jlong JNICALL Java_com_ccore_nfcjni_NFCJni_SKF_1ConnectDev(
		JNIEnv *env, jobject obj) {
	ULONG uiRet = 0;
	LPSTR szNameList = (LPSTR)malloc(1024);
	ULONG pulSize = 1024;

    nfc_res.env = env;
    LOGE("env:%p",nfc_res.env);
    nfc_res.obj = obj;
    LOGE("obj:0x%08x",nfc_res.obj);
    uiRet = V_SetDevRes(&nfc_res);
   	if(uiRet) return uiRet;
    LOGE("SKF_SO CALL:SKF_ConnectDev");
	uiRet = SKF_ConnectDev((LPSTR)szNameList, &hDev);
	free(szNameList);
	LOGE("SKF_SO CALL:SKF_ConnectDev----return:%d", uiRet);
	if(uiRet) return uiRet;
	return uiRet;
}
/*
 * 获取随机数
 */
JNIEXPORT jlong JNICALL Java_com_ccore_nfcjni_NFCJni_NFC_1GenRandom
        (JNIEnv * env, jobject obj, jbyteArray pbRandomData, jint randomLen){
    unsigned long uiRet = 0;
	LPSTR szNameList = (LPSTR)malloc(1024);
    jbyte * pRandom = (*env)->GetByteArrayElements(env, pbRandomData, NULL);
    LOGE("NFC_GenRandom_JNI----START");
    nfc_res.env = env;
    //LOGE("env:%p",nfc_res.env);
    nfc_res.obj = obj;
    //LOGE("obj:0x%08x",nfc_res.obj);
    uiRet = V_SetDevRes(&nfc_res);
   	if(uiRet) return uiRet;
    LOGE("SKF_ConnectDev----Enter");
	uiRet = SKF_ConnectDev((LPSTR)szNameList, &hDev);
	free(szNameList);
	if(uiRet) return uiRet;
    LOGE("SKF_ConnectDev----Out");
    while(TRUE){
        if(NULL_PTR == hDev){
            uiRet = SAR_DEVICE_REMOVED;
            goto err;
        }
        LOGE("SKF_GenRandom----Enter");
        uiRet = SKF_GenRandom(hDev,(u8*)pRandom,randomLen);
        if(uiRet) {
            LOGE("SKF_GenRandom End,uiRet = %d",uiRet);
            goto err;
        }
        LOGE("SKF_GenRandom  pRandom = %p,ulSize = %d",pRandom,randomLen);
        //有可能只能返回第一个设备路径
        break;
    }
    LOGE("NFC_GenRandom_JNI----End,uiRet = %d",uiRet);
err:
    (*env)->ReleaseByteArrayElements(env, pbRandomData, pRandom, 0);
	SKF_DisConnectDev(hDev);
    return uiRet;
}

/*实现获取设备信息接口*/
JNIEXPORT jlong JNICALL Java_com_ccore_nfcjni_NFCJni_NFC_1GetDevInfo
        (JNIEnv * env, jobject obj, jobject objDevInfo){
    unsigned long uiRet = 0;
    LPSTR szNameList = (LPSTR)malloc(1024);
    DEVINFO devinfo;
    LOGE("SKF_GetDevInfo_JNI----START");
    memset(&devinfo,0,sizeof(DEVINFO));
    nfc_res.env = env;
     //LOGE("env:%p",nfc_res.env);
    nfc_res.obj = obj;
     //LOGE("obj:0x%08x",nfc_res.obj);
    uiRet = V_SetDevRes(&nfc_res);
   	if(uiRet) return uiRet;
    LOGE("SKF_ConnectDev----Enter");
	uiRet = SKF_ConnectDev((LPSTR)szNameList, &hDev);
	free(szNameList);
	if(uiRet) return uiRet;
    LOGE("SKF_ConnectDev----Out");
    /*
    devinfo.Version.major = 0x01;
    devinfo.Version.minor = 0x02;
    devinfo.HWVersion.major = 0x01;
    devinfo.HWVersion.minor = 0x02;
    devinfo.FirmwareVersion.major = 0x01;
    devinfo.FirmwareVersion.minor = 0x02;
    devinfo.AlgSymCap = 0x01;
    devinfo.AlgAsymCap = 0x02;
    devinfo.AlgHashCap = 0x01;
    devinfo.DevAuthAlgId = 0x02;
    devinfo.TotalSpace = 0x01;
    devinfo.FreeSpace = 0x02;
    devinfo.MaxEccBufferSize = 0x01;
    devinfo.MaxBufferSize = 0x02;
    strcpy(devinfo.Manufacturer,"123123123");
    strcpy(devinfo.Issuer,"123123123");
    strcpy(devinfo.Label,"123123123");
    strcpy(devinfo.SerialNumber,"123123123");*/

    while(TRUE) {
        if(NULL_PTR == hDev){
            uiRet = SAR_DEVICE_REMOVED;
            goto err;
        }
        uiRet = SKF_GetDevInfo(hDev, &devinfo);
        if (0 != uiRet) {
            LOGE("SKF_GetDevInfo----End,Fail to Get DevInfo,ErrorRet = %d", uiRet);
            goto err;
        }
        uiRet = SetDevInfo(env,objDevInfo,&devinfo);
        if(0 != uiRet) goto err;

        //Do Anything There
        break;
    }
    //uiRet = SetDevInfo(env,objDevInfo,&devinfo);
    //if(uiRet) return -1;
    LOGE("SKF_GetDevInfo_JNI----return = %d", uiRet);
err:
    SKF_DisConnectDev(hDev);
    return uiRet;
}

/*
JNIEXPORT jlong JNICALL Java_com_ccore_jni_SKFJni_SKF_1GetFileInfo
         (JNIEnv * env, jobject obj, jstring fileName, jobject objFileInfo){
     unsigned long uiRet = 0;
     LPSTR szNameList = (LPSTR)malloc(1024);
     FILEATTRIBUTE fileAttribute;
     char* file=(char*)(*env)->GetStringUTFChars(env,fileName,JNI_FALSE);
     LOGE("SKF_GetFileInfo_JNI----START");
     nfc_res.env = env;
     //LOGE("env:%p",nfc_res.env);
     nfc_res.obj = obj;
     //LOGE("obj:0x%08x",nfc_res.obj);
     uiRet = V_SetDevRes(&nfc_res);
   	 if(uiRet) return uiRet;
     LOGE("SKF_ConnectDev----Enter");
	 uiRet = SKF_ConnectDev((LPSTR)szNameList, &hDev);
	 free(szNameList);
	 if(uiRet) return uiRet;
     LOGE("SKF_ConnectDev----Out");
     memset(&fileAttribute,0,sizeof(FILEATTRIBUTE));
     //strcpy(fileAttribute.FileName,file);
     //fileAttribute.FileSize = 10;
     //fileAttribute.ReadRights = 10;
     //fileAttribute.WriteRights = 10;
     while(TRUE){
         if(NULL_PTR == hDev){
             uiRet = SAR_DEVICE_REMOVED;
             goto err;
         }
         if(NULL_PTR == hAppHandle){
             uiRet = SAR_APPLICATION_NOT_EXISTS;
             goto err;
         }
         uiRet = SKF_GetFileInfo(hAppHandle,file,&fileAttribute);
         if(uiRet) goto err;

         //设置FileAttribute对象属性
         uiRet = SetFileAttribute(env,objFileInfo,&fileAttribute);
         if(uiRet) goto err;
         //Do SomeThing Here
         break;
     }

     //设置FileAttribute对象属性
     //uiRet = SetFileAttribute(env,objFileInfo,&fileAttribute);
     //if(uiRet) goto err;
     LOGE("SKF_GetFileInfo_JNI----End,uiRet = %d",uiRet);
err:
     (*env)->ReleaseStringUTFChars(env,fileName,file);
     SKF_DisConnectDev(hDev);
     return uiRet;
 }

JNIEXPORT jlong JNICALL Java_com_ccore_nfcjni_NFCJni_V_1SetNFC_1Class(
		JNIEnv *env, jobject obj) {
    int ret = 0;
	unsigned long uiRet = 0;


    LOGE("V_SetDecRes_JNI----Enter");
    nfc_res.env = env;
    LOGE("env:%p",nfc_res.env);
    nfc_res.obj = obj;
    uiRet = V_SetDevRes(&nfc_res);
    LOGE("obj:0x%08x",nfc_res.obj);
    return uiRet;
}

int transmit(JNIEnv *env, jobject obj,unsigned char *inBuf,int inlen,unsigned char *outBuf,int *outlen){
    int ret = 0,outDLen = 0;
    unsigned char *Out = NULL;
    jbyteArray indata,outdata;
    indata =(*env)->NewByteArray(env, inlen);
    (*env)->SetByteArrayRegion(env,indata,0,inlen,inBuf);
    jclass clazz = (*env)->GetObjectClass(env,obj);//获取该对象的类
    LOGE("nfc clazz:%p",clazz);
    jmethodID fid =(*env)->GetMethodID(env,clazz, "senddata", "([BI)I");//获取JAVA方法的ID
    LOGE("senddata func:%p",fid);
    ret = (*env)->CallIntMethod(env,obj,fid,indata,inlen);
    if(ret) goto err;
    jmethodID fid1 =(*env)->GetMethodID(env,clazz, "readlen", "()I");//获取JAVA方法的ID
    LOGE("readlen func:%p",fid1);
    outDLen = (*env)->CallIntMethod(env,obj,fid1);
    if(outDLen == -1)
    {
        ret = -1;
        goto err;
    }
    jmethodID fid2 =(*env)->GetMethodID(env,clazz, "readdata", "()[B");//获取JAVA方法的ID
    LOGE("readdata func:%p",fid1);
    outdata = (jbyteArray) (*env)->CallObjectMethod(env, obj, fid2);
    if(outdata==NULL)
    {
        ret = -1;
        goto err;
    }
    (*env)->GetByteArrayRegion(env, outdata, 0, outDLen, outBuf);
    LOGE("out(%p %d) %02X %02X %02X %02X\n", outBuf,outDLen,outBuf[0],outBuf[1],outBuf[2],outBuf[3]);
    *outlen = outDLen;
err:
    if(indata)
        (*env)->DeleteLocalRef(env, indata);
    if(outdata)
        (*env)->DeleteLocalRef(env, outdata);
    return ret;
}

JNIEXPORT jlong JNICALL Java_com_ccore_nfcjni_NFCJni_transmitJni(
		JNIEnv *env, jobject obj, jbyteArray InData, jint InLen, jbyteArray OutData, jintArray OutLen) {
    int ret = 0;
	unsigned long uiRet = 0;
    unsigned char *inBuf = NULL;
    int inBufLen = InLen;
    unsigned char *outBuf= NULL;
    int outBufLen = 0;
    // get input buff
    if (inBufLen > 0) {
        inBuf = malloc(inBufLen * sizeof(unsigned char));
        memset(inBuf, 0x00, inBufLen * sizeof(unsigned char));
        (*env)->GetByteArrayRegion(env, InData, 0, inBufLen, inBuf);
        LOGE("inl(%d)\n", inBufLen);
    } else {
        LOGE(" inBuf null and inBufLen 0\n");
    }
    outBuf = (*env)->GetByteArrayElements(env, OutData, NULL);
    LOGE("Out(%p)\n", outBuf);
    ret = transmit(env,obj,inBuf,inBufLen,outBuf,&outBufLen);
    LOGE("out(%p %d) %02X %02X %02X %02X\n", outBuf,outBufLen,outBuf[0],outBuf[1],outBuf[2],outBuf[3]);
    //(*env)->ReleaseIntArrayElements(env, OutLen, outBufLen, 0);
    //(*env)->ReleaseByteArrayElements(env, OutData, outBuf, 0);
    if (inBuf)
        free(inBuf);
    return ret;
}*/