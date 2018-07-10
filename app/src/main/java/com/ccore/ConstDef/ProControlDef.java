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
 *   @DATE 2018/7/10 17:31
 *   @Description This Class for http struct
 **/
import android.util.Log;

import struct.JavaStruct;
import struct.StructClass;
import struct.StructException;
import struct.StructField;


public class ProControlDef {
    private static final String TAG = ProControlDef.class.getSimpleName();

    /**
     * @Function: Request Data(发送数据结构体)
    **/
    @StructClass
    private static class Req_Data{
        @StructField(order = 0)
        public short Hdr;

        @StructField(order = 1)
        public short Type;

        @StructField(order = 2)
        public byte Cmd;

        @StructField(order = 3)
        public byte BitDef;

        @StructField(order = 4)
        public short	Rsv;

        @StructField(order = 5)
        public int PayloadLength;

    }

    /**
     * @Function: Response Data(回收数据结构体)
    **/
    @StructClass
    public static class Resp_Data
    {
        @StructField(order = 0)
        public short Hdr;

        @StructField(order = 1)
        public short Type;

        @StructField(order = 2)
        public byte Cmd;

        @StructField(order = 3)
        public byte BitDef;

        @StructField(order = 4)
        public byte Rsv;

        @StructField(order = 5)
        public byte RespStatus;

        @StructField(order = 6)
        public int PayloadLength;
    }

    /**
     * @Function: convert an object to byte array
     * @param obj the object to be converted
     * @return
     */
    public static byte[] ObjectToBytes(Object obj)
    {
        byte[] bytes = null;

        try
        {
            bytes = JavaStruct.pack(obj);
        }
        catch ( StructException e )
        {
            Log.d(TAG, "ObjectToBytes fail " + e.getMessage());
        }

        return bytes;
    }

    /**
     * @Function: generate an object from a byte array
     * @param bytes	the byte array
     * @param obj	the object
     * @return
     */
    public static Object BytesToObject(byte[] bytes, Object obj)
    {
        try
        {
            JavaStruct.unpack(obj, bytes);
        }
        catch ( StructException e )
        {
            Log.d(TAG, "BytesToObject fail " + e.getMessage());
        }

        return obj;
    }
}
