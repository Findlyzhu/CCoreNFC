package com.ccore.Until;

/**
 * Created by hxzhang on 2017/10/25.
 */
public class DCUtil {
    public static String bytes2HexStr4Disp(byte[] src, int len){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || len <= 0) {
            return null;
        }
        for (int i = 0; i < len; i++) {
            stringBuilder.append(" 0x");
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

    public static String bytes2HexStr(byte[] src, int len){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || len <= 0) {
            return null;
        }
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

    public static boolean isHexStr(String hexStr){
        if(hexStr.length() % 2 != 0 || hexStr.length() == 0){
            return false;
        }

        hexStr = hexStr.toUpperCase();
        for(int i = 0; i < hexStr.length(); i++){
            if("0123456789ABCDEF".indexOf(hexStr.charAt(i)) < 0)
                return false;
        }
        return true;
    }

    public static byte[] hexStr2Bytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));

        }
        return d;
    }
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static byte[] short2Bytes(short s) {
        byte[] shortBuf = new byte[2];
        for(int i = 0; i < 2; i++) {
            int offset = (shortBuf.length - 1 - i) * 8;
            shortBuf[i] = (byte)((s >>> offset) & 0xff);
        }
        return shortBuf;
    }

    public static byte[] ushort2Bytes(int s) {
        byte[] shortBuf = new byte[2];
        byte[] intBuf = intToBytes(s);

        System.arraycopy(intBuf, 2, shortBuf, 0, shortBuf.length);

        return shortBuf;
    }

    /*
    //小端序
    public static int bytesToIntS(byte[] src, int offset) {
    int value;
    value = (int) ((src[offset] & 0xFF)
            | ((src[offset+1] & 0xFF)<<8)
            | ((src[offset+2] & 0xFF)<<16)
            | ((src[offset+3] & 0xFF)<<24));
        return value;
    }
    */

    //大端序
    public static int bytes2Int(byte[] src, int offset) {
        int value;
        value = (int) ( ((src[offset] & 0xFF) << 24)
                |((src[offset + 1] & 0xFF) << 16)
                |((src[offset + 2] & 0xFF) << 8)
                |(src[offset + 3] & 0xFF));
        return value;
    }

    //大端序
    public static long bytes2Long(byte[] src, int offset) {
        long value;
        value = (long) ( ((src[offset] & 0xFF) << 24)
                |((src[offset + 1] & 0xFF) << 16)
                |((src[offset + 2] & 0xFF) << 8)
                |(src[offset + 3] & 0xFF));
        return value;
    }

    //大端序
    public static int bytes2uShort(byte[] src, int offset) {
        int value;
        value = ((src[offset] & 0xFF) << 8) |(src[offset + 1] & 0xFF);
        return value;
    }


    //小端序
    public static byte[] intToBytesS( int value )
    {
        byte[] src = new byte[4];
        src[3] =  (byte) ((value>>24) & 0xFF);
        src[2] =  (byte) ((value>>16) & 0xFF);
        src[1] =  (byte) ((value>>8) & 0xFF);
        src[0] =  (byte) (value & 0xFF);
        return src;
    }


    //大端序
    public static byte[] intToBytes(int value)
    {
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }

    /**
     * 将整数表示的ip地址转换为字符串表示.
     *
     * @param ip 32位整数表示的ip地址
     * @return 点分式表示的ip地址
     */
    public static final String int2Ip(final int ip) {
        final long[] mask = { 0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000 };
        final StringBuilder ipAddress = new StringBuilder();
        for (int i = 0; i < mask.length; i++) {
            long l = (ip & mask[i]) >> (i * 8);
            l = l >= 0 ? l : l + 256;
            ipAddress.insert(0, l);
            if (i < mask.length - 1) {
                ipAddress.insert(0, ".");
            }
        }
        return ipAddress.toString();
    }
}
