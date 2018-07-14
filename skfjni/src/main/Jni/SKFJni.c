
#include "until.h"
#ifndef ANDROID
#define ANDROID
#endif

DEVHANDLE hDev = NULL_PTR;
HANDLE * hKey = NULL_PTR;
HAPPLICATION * hAppHandle = NULL_PTR;
HCONTAINER * hContainerHandle = NULL_PTR;

JNIEXPORT jlong JNICALL Java_com_ccore_jni_SKFJni_SKF_1EnumDev
        (JNIEnv * env, jobject thiz, jboolean bPresent,jcharArray pbDevNameList){
    unsigned long uiRet = 0;
    unsigned int ulSize = 128;
	LPSTR szNameList = "sdcard1";
    //jbyte * plist = (*env)->GetByteArrayElements(env, pbDevNameList, NULL);
    LOGE("SKF_EnumDev_JNI----Enter");
    while(FALSE){
        uiRet = SKF_EnumDev(bPresent,NULL,&ulSize);
        if(uiRet) {
            LOGE("SKF_EnumDev End,uiRet = %d",uiRet);
            break;
        }
        szNameList = (LPSTR)malloc(ulSize);
        uiRet = SKF_EnumDev(bPresent,szNameList,&ulSize);
        if(uiRet) {
            LOGE("SKF_EnumDev End,uiRet = %d",uiRet);
            break;
        }
        LOGE("jbyteDevnameList = %s,ulSize = %d",plist,ulSize);
        jchar* devlist = (jchar*)szNameList;
        //(*env)->SetCharArrayRegion(env,pbDevNameList, 0, ulSize, devlist);
        //有可能只能返回第一个设备路径
        break;

    }
    ulSize = strlen(szNameList);
    jchar* devlist = (jchar*)szNameList;
    (*env)->SetCharArrayRegion(env,pbDevNameList, 0, ulSize, devlist);
    LOGE("SKF_EnumDev_JNI----End,uiRet = %d",uiRet);
    //(*env)->ReleaseByteArrayElements(env, pbDevNameList, plist, 0);
    //if(szNameList) free(szNameList);
    return uiRet;
}

/*
 * 枚举并连接设备
 */
JNIEXPORT jlong JNICALL Java_com_ccore_jni_SKFJni_SKF_1ConnectDev(
		JNIEnv *env, jobject obj) {
	ULONG uiRet = 0;
	LPSTR szNameList = (LPSTR)malloc(1024);
	ULONG pulSize = 1024;

    LOGE("SKF_SO CALL:SKF_EnumDev");

	uiRet = SKF_EnumDev(1, szNameList, &pulSize);
	LOGE("SKF_EnumDev----return:%ld,szNameList = %s,pulSize = %ld", uiRet,uiRet,szNameList, pulSize);
	if(uiRet){
		free(szNameList);
		return uiRet;
	}
    LOGE("SKF_SO CALL:SKF_ConnectDev");
	uiRet = SKF_ConnectDev((LPSTR)szNameList, &hDev);
	LOGE("SKF_ConnectDev----return:%d , szNameList:=%s", uiRet,szNameList);
	free(szNameList);
	return uiRet;
}

/*实现获取设备信息接口*/
JNIEXPORT jlong JNICALL Java_com_ccore_jni_SKFJni_SKF_1GetDevInfo
        (JNIEnv * env, jobject obj, jobject objDevInfo){
    unsigned long uiRet = 0;

    DEVINFO devinfo;
    memset(&devinfo,0,sizeof(DEVINFO));
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
    strcpy(devinfo.SerialNumber,"123123123");
    LOGE("SKF_GetDevInfo_JNI----Enter");
    while(FALSE) {
        if(NULL_PTR == hDev){
            uiRet = SAR_DEVICE_REMOVED;
            break;
        }
        uiRet = SKF_GetDevInfo(hDev, &devinfo);
        if (0 != uiRet) {
            LOGE("SKF_GetDevInfo----End,Fail to Get DevInfo,ErrorRet = %d", uiRet);
            break;
        }
        uiRet = SetDevInfo(env,objDevInfo,&devinfo);
        if(0 != uiRet) break;

        //Do Anything There
        break;
    }
    uiRet = SetDevInfo(env,objDevInfo,&devinfo);
    if(uiRet) return -1;
    LOGE("SKF_GetDevInfo_JNI----return = %d", uiRet);
    return uiRet;
}


JNIEXPORT jlong JNICALL Java_com_ccore_jni_SKFJni_SKF_1GetFileInfo
         (JNIEnv * env, jobject thiz, jstring fileName, jobject objFileInfo){
     unsigned long uiRet = 0;
     FILEATTRIBUTE fileAttribute;
     char* file=(char*)(*env)->GetStringUTFChars(env,fileName,JNI_FALSE);
     LOGE("SKF_GetFileInfo_JNI----Enter");

     memset(&fileAttribute,0,sizeof(FILEATTRIBUTE));
     strcpy(fileAttribute.FileName,file);
     fileAttribute.FileSize = 10;
     fileAttribute.ReadRights = 10;
     fileAttribute.WriteRights = 10;

     while(FALSE){
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
     uiRet = SetFileAttribute(env,objFileInfo,&fileAttribute);
     if(uiRet) goto err;
     LOGE("SKF_GetFileInfo_JNI----End,uiRet = %d",uiRet);
err:
     (*env)->ReleaseStringUTFChars(env,fileName,file);
     return uiRet;
 }

/*
 * 断开设备连接
 */

JNIEXPORT jlong JNICALL Java_com_ccore_jni_SKFJni_SKF_1DisconnectDev(
		JNIEnv *env, jobject obj) {

	ULONG uiRet = 0;
	uiRet = SKF_DisconnectDev(hDev);
    hDev = NULL_PTR;
	return uiRet;
}

JNIEXPORT jlong JNICALL Java_com_ccore_jni_SKFJni_SKF_1SetAppPath(
		JNIEnv *env, jobject obj, jstring szAppPath) {
	unsigned long uiRet = 0;
    char* appPath=(char*)(*env)->GetStringUTFChars(env,szAppPath,JNI_FALSE);
    LOGE("SKF_SetAppPath_JNI----Enter");
    /*
    jsize len = (*env)->GetArrayLength(env,szAppPath);
    char* appPath = (char*)malloc(len+1);
    if(NULL == appPath){
        uiRet = SAR_MEMORYERR;
        return uiRet;
    }
    memset(appPath,0,len+1);
    memcpy(appPath,(char*)(*env)->GetByteArrayElements(env,szAppPath,0),len);*/
    LOGE("V_SetAppPath:%s",appPath);
	uiRet = V_SetAppPath(appPath);
    if(appPath != NULL) free(appPath);
    LOGE("SKF_SetAppPath_JNI----return = %d",uiRet);
    (*env)->ReleaseStringUTFChars(env,szAppPath,appPath);
	return uiRet;
}