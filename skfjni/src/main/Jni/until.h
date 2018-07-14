//
// Created by Findly on 2018/7/13.
//

#include <stdlib.h>
#include <malloc.h>
#include "com_ccore_jni_SKFJni.h"
#include "prebuilt/include/SKF.h"
#include <android/log.h>
#include <string.h>
#ifndef CCORENFC_UNTIL_H
#define CCORENFC_UNTIL_H

#define TAG "SKF_JNI"

#ifdef LOG
// 定义info信息
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)
// 定义debug信息
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
// 定义error信息
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG,__VA_ARGS__)
#else
// 定义info信息
#define LOGI(...)
// 定义debug信息
#define LOGD(...)
// 定义error信息
#define LOGE(...)
#endif
#define IF_ERROR_GOTO_END(x) if(x!=0) goto END;

long SetStringValue(JNIEnv*  env,jobject jobj,char *paramchar);
long SetIntegerValue(JNIEnv*  env,jobject jobj,jint paramInt);
long SetDevInfo(JNIEnv* env ,jobject paramObj,PDEVINFO pdevinfo);
long SetFileAttribute(JNIEnv* env ,jobject paramObj,PFILEATTRIBUTE pfileattribute);

jstring CStr2Jstring( JNIEnv* env,const char* str );

char* Jstring2CStr( JNIEnv * env, jstring jstr );

#endif //CCORENFC_UNTIL_H
