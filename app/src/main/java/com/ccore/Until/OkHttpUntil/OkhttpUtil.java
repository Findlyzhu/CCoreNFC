package com.ccore.Until.OkHttpUntil;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by findly on 2018/7/10.
 */

public class OkhttpUtil {

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST = "POST";
    public static final String METHOD_PUT = "PUT";
    public static final String METHOD_DELETE = "DELETE";
    public static final String METHOD_CANCEL = "CANCEL";

    public static final String FILE_TYPE_FILE = "file/*";
    public static final String FILE_TYPE_IMAGE = "image/*";
    public static final String FILE_TYPE_AUDIO = "audio/*";
    public static final String FILE_TYPE_VIDEO = "video/*";

    public static void okHttpCancel(int tag){
        new RequestUtil(METHOD_CANCEL,tag).execute();
    }

    /**
     * @Function:get请求
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpGet(String url,int tag, CallBackUtil callBack) {
        okHttpGet(url,tag,null, null, callBack);
    }

    /**
     * get请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpGet(String url,int tag, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpGet(url,tag, paramsMap, null, callBack);
    }

    /**
     * get请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpGet(String url,int tag, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_GET,tag, url, paramsMap, headerMap, callBack).execute();
    }

    /**
     * post请求
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPost(String url,int tag, CallBackUtil callBack) {
        okHttpPost(url,tag, null, callBack);
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPost(String url,int tag, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpPost(url,tag, paramsMap, null, callBack);
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPost(String url,int tag, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST,tag, url, paramsMap, headerMap, callBack).execute();
    }
/**
     * post请求
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPut(String url,int tag, CallBackUtil callBack) {
        okHttpPut(url,tag, null, callBack);
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPut(String url,int tag, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpPut(url,tag, paramsMap, null, callBack);
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPut(String url,int tag, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_PUT,tag, url, paramsMap, headerMap, callBack).execute();
    }
/**
     * post请求
     * @param url：url
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpDelete(String url,int tag, CallBackUtil callBack) {
        okHttpDelete(url,tag, null, callBack);
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpDelete(String url,int tag, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpDelete(url,tag, paramsMap, null, callBack);
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpDelete(String url,int tag, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_DELETE,tag, url, paramsMap, headerMap, callBack).execute();
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param jsonStr：json格式的键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPostJson(String url,int tag, String jsonStr, CallBackUtil callBack) {
        okHttpPostJson(url,tag, jsonStr, null, callBack);
    }

    /**
     * post请求，可以传递参数
     * @param url：url
     * @param jsonStr：json格式的键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpPostJson(String url,int tag, String jsonStr, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST,tag, url, jsonStr, headerMap, callBack).execute();
    }

    /**
     * post请求，上传单个文件
     * @param url：url
     * @param file：File对象
     * @param fileKey：上传参数时file对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。还可以重写onProgress方法，得到上传进度
     */
    public static void okHttpUploadFile(String url,int tag, File file,String fileKey, String fileType, CallBackUtil callBack) {
        okHttpUploadFile(url,tag, file, fileKey,fileType, null, callBack);
    }

    /**
     * post请求，上传单个文件
     * @param url：url
     * @param file：File对象
     * @param fileKey：上传参数时file对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。还可以重写onProgress方法，得到上传进度
     */
    public static void okHttpUploadFile(String url,int tag, File file, String fileKey,String fileType, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpUploadFile(url,tag, file,fileKey, fileType, paramsMap, null, callBack);
    }

    /**
     * post请求，上传单个文件
     * @param url：url
     * @param file：File对象
     * @param fileKey：上传参数时file对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。还可以重写onProgress方法，得到上传进度
     */
    public static void okHttpUploadFile(String url,int tag, File file, String fileKey,String fileType, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST,tag, url,paramsMap, file, fileKey,fileType,  headerMap, callBack).execute();
    }

    /**
     * post请求，上传多个文件，以list集合的形式
     * @param url：url
     * @param fileList：集合元素是File对象
     * @param fileKey：上传参数时fileList对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpUploadListFile(String url,int tag, List<File> fileList, String fileKey, String fileType, CallBackUtil callBack) {
        okHttpUploadListFile(url,tag,null, fileList, fileKey, fileType, callBack);
    }

    /**
     * post请求，上传多个文件，以list集合的形式
     * @param url：url
     * @param fileList：集合元素是File对象
     * @param fileKey：上传参数时fileList对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpUploadListFile(String url,int tag, Map<String, String> paramsMap,List<File> fileList, String fileKey, String fileType,  CallBackUtil callBack) {
        okHttpUploadListFile(url,tag, paramsMap,fileList, fileKey, fileType, null, callBack);
    }

    /**
     * post请求，上传多个文件，以list集合的形式
     * @param url：url
     * @param fileList：集合元素是File对象
     * @param fileKey：上传参数时fileList对应的键
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpUploadListFile(String url,int tag, Map<String, String> paramsMap, List<File> fileList, String fileKey, String fileType, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST,tag, url,  paramsMap,fileList, fileKey, fileType, headerMap, callBack).execute();
    }

    /**
     * post请求，上传多个文件，以map集合的形式
     * @param url：url
     * @param fileMap：集合key是File对象对应的键，集合value是File对象
     * @param fileType：File类型，是image，video，audio，file
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpUploadMapFile(String url,int tag, Map<String, File> fileMap, String fileType, CallBackUtil callBack) {
        okHttpUploadMapFile(url,tag, fileMap, fileType, null, callBack);
    }

    /**
     * post请求，上传多个文件，以map集合的形式
     * @param url：url
     * @param fileMap：集合key是File对象对应的键，集合value是File对象
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpUploadMapFile(String url,int tag, Map<String, File> fileMap, String fileType, Map<String, String> paramsMap, CallBackUtil callBack) {
        okHttpUploadMapFile(url,tag, fileMap, fileType, paramsMap, null, callBack);
    }

    /**
     * post请求，上传多个文件，以map集合的形式
     * @param url：url
     * @param fileMap：集合key是File对象对应的键，集合value是File对象
     * @param fileType：File类型，是image，video，audio，file
     * @param paramsMap：map集合，封装键值对参数
     * @param headerMap：map集合，封装请求头键值对
     * @param callBack：回调接口，onFailure方法在请求失败时调用，onResponse方法在请求成功后调用，这两个方法都执行在UI线程。
     */
    public static void okHttpUploadMapFile(String url,int tag, Map<String, File> fileMap, String fileType, Map<String, String> paramsMap, Map<String, String> headerMap, CallBackUtil callBack) {
        new RequestUtil(METHOD_POST,tag, url,paramsMap, fileMap, fileType,  headerMap, callBack).execute();
    }

    /**
     * 下载文件,不带参数
     */
    public static void okHttpDownloadFile(String url,int tag, CallBackUtil.CallBackFile callBack) {
        okHttpDownloadFile(url,tag,null,callBack);
    }

    /**
     * 下载文件,带参数
     */
    public static void okHttpDownloadFile(String url,int tag,Map<String, String> paramsMap, CallBackUtil.CallBackFile callBack) {
        okHttpGet(url,tag, paramsMap, null, callBack);
    }
    /**
     * 加载图片
     */
    public static void okHttpGetBitmap(String url,int tag, CallBackUtil.CallBackBitmap callBack) {
        okHttpGetBitmap(url,tag, null, callBack);
    }
    /**
     * 加载图片，带参数
     */
    public static void okHttpGetBitmap(String url,int tag,Map<String, String> paramsMap,  CallBackUtil.CallBackBitmap callBack) {
        okHttpGet(url,tag,paramsMap, null, callBack);
    }


}
