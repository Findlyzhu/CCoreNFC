//
// Created by Findly on 2018/7/13.
//


#include"until.h"

//将char类型转换成jstring类型
jstring CStr2Jstring( JNIEnv* env,const char* str )
{

    jsize len = strlen(str);
    // 定义java String类 strClass
    jclass strClass = (*env)->FindClass(env, "java/lang/String");
    LOGE("CStr2Jstring Start");
    LOGE("strClass %p",strClass);
    //设置String, 保存语言类型,用于byte数组转换至String时的参数
    jstring encoding = (*env)->NewStringUTF(env, "UTF-8");
    LOGE("encoding %p",encoding);
    // 获取java String类方法String(byte[],String)的构造器,用于将本地byte[]数组转换为一个新String
    jmethodID ctorID = (*env)->GetMethodID(env, strClass, "<init>", "([BLjava/lang/String;)V");
     LOGE("ctorID %p",ctorID);
    // 建立byte数组
    jbyteArray bytes = (*env)->NewByteArray(env, len);
    LOGE("bytes %p",ctorID);
    // 将char* 转换为byte数组
    (*env)->SetByteArrayRegion(env, bytes, 0, len, (jbyte*)str);
    LOGE("SetByteArrayRegion,End");
    //将byte数组转换为java String,并输出
    return (jstring)(*env)->NewObject(env, strClass, ctorID, bytes, encoding);
}

//将jstring类型转换成char类型
char * Jstring2CStr( JNIEnv * env, jstring jstr )
{
    char * rtn = NULL;
    jclass clsstring = (*env)->FindClass(env, "java/lang/String");
    jstring strencode = (*env)->NewStringUTF(env, "GB2312");
    jmethodID mid = (*env)->GetMethodID(env, clsstring, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr= (jbyteArray)(*env)->CallObjectMethod(env, jstr,mid,strencode);
    jsize alen = (*env)->GetArrayLength(env, barr);
    jbyte * ba = (*env)->GetByteArrayElements(env, barr,JNI_FALSE);
    if(alen > 0)
    {
        rtn = (char*)malloc(alen+1); //new char[alen+1];
        memcpy(rtn,ba,alen);
        rtn[alen]=0;
    }
    (*env)->ReleaseByteArrayElements(env, barr,ba,0);

    return rtn;
}

long SetStringValue(JNIEnv*  env,jobject jobj,char *paramchar){
    long uiRet = 0;
    LOGE("SetStringValue----Begin");

    jstring p = CStr2Jstring(env,paramchar);

    jclass jclaz = (*env)->FindClass(env,"java/lang/String");
    if (NULL == jclaz)
    {
        LOGE("FindClass----Fail");
        return SAR_UNKOWNERR;
    }
    jfieldID valueId = (*env)->GetFieldID(env,jclaz, "name", "Ljava/lang/String");
    if (NULL == valueId)
    {
        LOGE("GetFiledID failed");
        return SAR_UNKOWNERR;
    }
    (*env)->SetObjectField(env, jobj, valueId,p);
    LOGE("SetIntegerValue----End");
    return uiRet;
}

long SetIntegerValue(JNIEnv*  env,jobject jobj,jint paramInt){
    long uiRet = 0;
    LOGE("SetIntegerValue----Begin");
    jclass jclaz = (*env)->FindClass(env,"java/lang/Integer");
    if (NULL == jclaz)
    {
        LOGE("FindClass----Fail");
        return SAR_UNKOWNERR;
    }
    jfieldID valueId = (*env)->GetFieldID(env,jclaz, "value", "I");
    if (NULL == valueId)
    {
        LOGE("GetFiledID failed");
        return SAR_UNKOWNERR;
    }

    (*env)->SetIntField(env,jobj,valueId, paramInt);
    LOGE("SetIntegerValue----End");
    return uiRet;
}


long SetByteArryField(JNIEnv* env,jobject paramObj,jfieldID jfied,char* pSrcParam){
    LOGE("SetByteArryField,Begin,pSrcParam = %s",pSrcParam);
    jbyteArray tempArry = (*env)->NewByteArray(env,strlen(pSrcParam));
    (*env)->SetByteArrayRegion(env,tempArry, 0, strlen(pSrcParam), (jbyte*)pSrcParam);
    (*env)->SetObjectField(env, paramObj, jfied,tempArry);
    LOGE("SetByteArryField,End");
    return 0;
}

long SetDevInfo(JNIEnv* env ,jobject paramObj,PDEVINFO pdevinfo){
    long uiRet = 0;
    jfieldID fid;
    jstring manufacturer = CStr2Jstring(env,pdevinfo->Manufacturer);
    jstring issuer = CStr2Jstring(env,pdevinfo->Issuer);
    jstring label = CStr2Jstring(env,pdevinfo->Label);
    jstring serialNumber = CStr2Jstring(env,pdevinfo->SerialNumber);
    LOGE("SetDevInfo----Begin");
    if(NULL == env || NULL == paramObj){
        LOGE("SetDevInfo----JNIEnv = Null OR jobj = Null");
        return SAR_UNKOWNERR;
    }
    jclass Class = (*env)->GetObjectClass(env,paramObj);
    LOGE("SetDevInfo----Class:%p",Class);

    fid = (*env)->GetFieldID(env, Class, "Manufacturer", "Ljava/lang/String;");
    LOGE("SetDevInfo----fid:%p",fid);
    (*env)->SetObjectField(env, paramObj, fid,manufacturer);
    fid = (*env)->GetFieldID(env, Class, "Issuer", "Ljava/lang/String;");
    LOGE("SetDevInfo----fid:%p",fid);
    (*env)->SetObjectField(env, paramObj, fid,issuer);
    fid = (*env)->GetFieldID(env, Class, "Label", "Ljava/lang/String;");
    LOGE("SetDevInfo----fid:%p",fid);
    (*env)->SetObjectField(env, paramObj, fid,label);
    fid = (*env)->GetFieldID(env, Class, "SerialNumber", "Ljava/lang/String;");
    LOGE("SetDevInfo----fid:%p",fid);
    (*env)->SetObjectField(env, paramObj, fid,serialNumber);
    LOGE("SetDevinfo---Set Type Int,Begin");
    fid = (*env)->GetFieldID(env, Class, "AlgSymCap", "I");
    LOGE("SetDevInfo----fid:%p",fid);
    (*env)->SetIntField(env, paramObj, fid,pdevinfo->AlgSymCap);
    fid = (*env)->GetFieldID(env, Class, "AlgASymCap", "I");
    LOGE("SetDevInfo----fid:%p",fid);
    (*env)->SetIntField(env, paramObj, fid,pdevinfo->AlgAsymCap);
    fid = (*env)->GetFieldID(env, Class, "AlgHashCap", "I");
    LOGE("SetDevInfo----fid:%p",fid);
    (*env)->SetIntField(env, paramObj, fid,pdevinfo->AlgHashCap);
    fid = (*env)->GetFieldID(env, Class, "DevAuthAlgId", "I");
    LOGE("SetDevInfo----fid:%p",fid);
    (*env)->SetIntField(env, paramObj, fid,pdevinfo->DevAuthAlgId);
    fid = (*env)->GetFieldID(env, Class, "TotalSpace", "I");
    LOGE("SetDevInfo----fid:%p",fid);
    (*env)->SetIntField(env, paramObj, fid,pdevinfo->TotalSpace);
    fid= (*env)->GetFieldID(env, Class, "FreeSpace", "I");
    LOGE("SetDevInfo----fid:%p",fid);
    (*env)->SetIntField(env, paramObj, fid,pdevinfo->FreeSpace);
    fid = (*env)->GetFieldID(env, Class, "MaxEccBufferSize", "I");
    LOGE("SetDevInfo----fid:%p",fid);
    (*env)->SetIntField(env, paramObj, fid,pdevinfo->MaxEccBufferSize);
    fid = (*env)->GetFieldID(env, Class, "MaxBufferSize", "I");
    LOGE("SetDevInfo----fid:%p",fid);
    (*env)->SetIntField(env, paramObj, fid,pdevinfo->MaxBufferSize);

    jmethodID setversion =  (*env)->GetMethodID(env,Class,"set_version","(IBB)V");
    LOGE("SetDevInfo----setversion:%p",setversion);
    (*env)->CallVoidMethod(env,paramObj,setversion,1,(jbyte)(pdevinfo->Version.major),(jbyte)(pdevinfo->Version.minor));
    (*env)->CallVoidMethod(env,paramObj,setversion,2,(jbyte)(pdevinfo->HWVersion.major),(jbyte)(pdevinfo->HWVersion.minor));
    (*env)->CallVoidMethod(env,paramObj,setversion,3,(jbyte)(pdevinfo->FirmwareVersion.major),(jbyte)(pdevinfo->FirmwareVersion.minor));
    LOGE("CallVoidMethod----ok");

    LOGE("SetDevInfo----GetFieldID End");

    (*env)->DeleteLocalRef(env,Class);
    return uiRet;
}

long SetFileAttribute(JNIEnv* env ,jobject paramObj,PFILEATTRIBUTE pfileattribute){
    long uiRet = 0;
    jfieldID fid;
    jstring filename = CStr2Jstring(env,pfileattribute->FileName);

    LOGE("SetFileAttribute----Begin");
    if(NULL == env || NULL == paramObj){
        LOGE("SetFileAttribute----JNIEnv = Null OR jobj = Null");
        return SAR_UNKOWNERR;
    }
    jclass Class = (*env)->GetObjectClass(env,paramObj);


    fid = (*env)->GetFieldID(env, Class, "FileSize", "I");
    (*env)->SetIntField(env, paramObj,fid,pfileattribute->FileSize);
    LOGE("SetFileAttribute----fid:%p",fid);
    fid = (*env)->GetFieldID(env, Class, "ReadRights", "I");
    (*env)->SetIntField(env, paramObj,fid,pfileattribute->ReadRights);
    LOGE("SetFileAttribute----fid:%p",fid);
    fid = (*env)->GetFieldID(env, Class, "WriteRights", "I");
    (*env)->SetIntField(env, paramObj,fid,pfileattribute->WriteRights);
    LOGE("SetFileAttribute----fid:%p",fid);
    fid = (*env)->GetFieldID(env, Class, "fileName", "Ljava/lang/String;");
    LOGE("SetFileAttribute----fid:%p",fid);
    (*env)->SetObjectField(env, paramObj, fid,filename);

    //uiRet =SetByteArryField(env,paramObj,fid,pfileattribute->FileName);
    LOGE("SetFileAttribute----GetFieldID End");

    (*env)->DeleteLocalRef(env,Class);
    return uiRet;
}