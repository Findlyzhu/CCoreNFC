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
 * @DATE 2018/7/13 10:28
 * @Description This Class for SKF TYPE
 **/
public class SKFType {

    public static final int MAX_RSA_MODULUS_LEN              = 256;
    public static final int MAX_RSA_EXPONENT_LEN             = 4;

    public static final int ECC_MAX_XCOORDINATE_BITS_LEN	 =	512;
    public static final int ECC_MAX_YCOORDINATE_BITS_LEN	 =	ECC_MAX_XCOORDINATE_BITS_LEN;
    public static final int ECC_MAX_MODULUS_BITS_LEN		 =	ECC_MAX_XCOORDINATE_BITS_LEN;

    public static final int MAX_IV_LEN                       = 32;

    public static final int ADMIN_TYPE			             = 0;				//管理员 PIN 类型
    public static final int USER_TYPE			             = 1;				//用户 PIN 类型


    public static final int SECURE_NEVER_ACCOUNT	         = 0x00;		//不允许
    public static final int SECURE_ADM_ACCOUNT		         = 0x01;		//管理员权限
    public static final int SECURE_USER_ACCOUNT		         = 0x10;		//用户权限
    public static final int SECURE_ANYONE_ACCOUNT	         = 0xFF;		//任何人

    public static final int MIN_PIN_LEN				         = 0x06;
    public static final int MAX_PIN_LEN			             = 0x10;


    public static final int DEV_ABSENT_STATE		         = 0x00000000;	  //设备不存在
    public static final int DEV_PRESENT_STATE		         = 0x00000001;    //设备存在
    public static final int DEV_UNKNOW_STATE		         = 0x00000002;    //设备状态未知

    public static final int PKCS5_PADDING			         = 1;
    public static final int NO_PADDING				         = 0;

 
    /**
     * @Function: 版本结构体
    **/
    //@StructClass
    public class VERSION{
    //    @StructField(order = 0)
        public byte     major;
    //    @StructField(order = 1)
        public byte     minor;
    }

    /**
     * @Function: 设备信息结构体
    **/
    //@StructClass
    public class DEVINFO{
        public VERSION  Version;
        public String   Manufacturer;
        public String   Issuer;
        public String   Label;
        public String   SerialNumber;
        public VERSION  HWVersion;
        public VERSION  FWVersion;
        public int      AlgSymCap;
        public int      AlgASymCap;
        public int      AlgHashCap;
        public int      DevAuthAlgId;
        public int      TotalSpace;
        public int      FreeSpace;
        public int      MaxEccBufferSize;
        public int      MaxBufferSize;
        public byte[]   Reserved = new byte[56];
        public void set_version(int type,byte major,byte minor){
            Log.e("SKFTYPE","set_version");
            switch(type){
                case 1:
                    Log.e("SKFTYPE","Version set_version");
                    this.Version = new VERSION();
                    this.Version.major = major;
                    this.Version.major = minor;
                    break;
                case 2:
                    Log.e("SKFTYPE","HWVersion set_version");
                    this.HWVersion = new VERSION();
                    this.HWVersion.major = major;
                    this.HWVersion.major = minor;
                    break;
                case 3:
                    Log.e("SKFTYPE","FWVersion set_version");
                    this.FWVersion = new VERSION();
                    this.FWVersion.major = major;
                    this.FWVersion.major = minor;
                    break;
            }
        }

    }

    /**
     * @Function: RSA公钥结构体
    **/
    //@StructClass
    public class RSAPUBLICKEYBLOB{
    //    @StructField(order = 0)
        public int      AlgID;
    //    @StructField(order = 1)
        public int      BitLen;
    //    @StructField(order = 2)
        public byte[]   Modulus = new byte[MAX_RSA_MODULUS_LEN];
    //    @StructField(order = 3)
        public byte[]   PubExp  = new byte[MAX_RSA_EXPONENT_LEN];
    }

    /**
     * @Function: RSA密钥对结构体
    **/
    //@StructClass
    public class RSAPRIVATEKEYBLOB{
    //    @StructField(order = 0)
        public int		AlgoID;
    //    @StructField(order = 1)
        public int		BitLen;
    //    @StructField(order = 2)
        public byte[]   Modulus = new byte[MAX_RSA_MODULUS_LEN];
    //    @StructField(order = 3)
        public byte[]   PubExp  = new byte[MAX_RSA_EXPONENT_LEN];
    //    @StructField(order = 4)
        public byte[]   PriExp  = new byte[MAX_RSA_MODULUS_LEN];
    //    @StructField(order = 5)
        public byte[]   Prime1 = new byte[MAX_RSA_MODULUS_LEN/2];
    //    @StructField(order = 6)
        public byte[]   Prime2 = new byte[MAX_RSA_MODULUS_LEN/2];
    //    @StructField(order = 7)
        public byte[]   Prime1Exp = new byte[MAX_RSA_MODULUS_LEN/2];
    //    @StructField(order = 8)
        public byte[]   Prime2Exp = new byte[MAX_RSA_MODULUS_LEN/2];
    //    @StructField(order = 9)
        public byte[]   Coef = new byte[MAX_RSA_MODULUS_LEN/2];
    }

    /**
     * @Function: ECC公钥结构体
    **/
    //@StructClass
    public class ECCPUBLICKEYBLOB{
    //    @StructField(order = 0)
        public int		BitLen;
    //    @StructField(order = 1)
        public byte[]   XCoordinate = new byte[ECC_MAX_XCOORDINATE_BITS_LEN/8];
    //    @StructField(order = 2)
        public byte[]   YCoordinate = new byte[ECC_MAX_YCOORDINATE_BITS_LEN/8];
    }

    /**
     * @Function: ECC密钥对结构体
     **/
    //@StructClass
    public class ECCPRIVATEKEYBLOB{
    //    @StructField(order = 0)
        public int		BitLen;
    //    @StructField(order = 1)
        public byte[]   PrivateKey = new byte[ECC_MAX_MODULUS_BITS_LEN/8];
    }

    /**
     * @Function: ECC加密数据结构体
     **/
    //@StructClass
    public class ECCCIPHERBLOB{
    //    @StructField(order = 0)
        public byte[]   XCoordinate = new byte[ECC_MAX_XCOORDINATE_BITS_LEN/8];
    //    @StructField(order = 1)
        public byte[]   YCoordinate = new byte[ECC_MAX_YCOORDINATE_BITS_LEN/8];
    //    @StructField(order = 2)
        public byte[]   HASH        = new byte[32];
    //    @StructField(order = 3)
        public int		CipherLen;
    //    @StructField(order = 4)
        public byte[]   Cipher      = new byte[1];
    }

    /**
     * @Function: ECC签名值结构体
     **/
    //@StructClass
    public class ECCSIGNATUREBLOB{
    //    @StructField(order = 0)
        public byte[]   r = new byte[ECC_MAX_XCOORDINATE_BITS_LEN/8];
    //    @StructField(order = 1)
        public byte[]   s = new byte[ECC_MAX_YCOORDINATE_BITS_LEN/8];
    }

    /**
     * @Function: ECC签名值结构体
     **/
    //@StructClass
    public class ENVELOPEDKEYBLOB{
    //    @StructField(order = 0)
        public int		Version;					            // 当前版本为 1
    //    @StructField(order = 1)
        public int		ulSymmAlgID;				            // 对称算法标识，限定ECB模式
    //    @StructField(order = 2)
        public int		ulBits;						            // 加密密钥对的密钥位长度
    //    @StructField(order = 3)
        public byte[]   cbEncryptedPriKey = new byte[64];		// 加密密钥对私钥的密文
    //    @StructField(order = 4)
        ECCPUBLICKEYBLOB PubKey;        // 加密密钥对的公钥
    //    @StructField(order = 5)
        ECCCIPHERBLOB ECCCipherBlob;    // 用保护公钥加密的对称密钥密文。
    }

    /**
     * @Function: 对称算法参数结构体
     **/
    //@StructClass
    public class BLOCKCIPHERPARAM{
    //    @StructField(order = 0)
        public byte[]   IV = new byte[MAX_IV_LEN];
    //    @StructField(order = 1)
        public int		IVLen;
    //    @StructField(order = 2)
        public int		PaddingType;
    //    @StructField(order = 3)
        public int		FeedBitLen;
    }

    /**
     * @Function: 文件属性结构体
    **/
    //@StructClass
    public class FILEATTRIBUTE {
    //    @StructField(order = 0)
        public String   fileName;
        //public byte[]   fileName = new byte[MAX_IV_LEN];
    //    @StructField(order = 1)
        public int      FileSize;
    //    @StructField(order = 2)
        public int      ReadRights;
    //    @StructField(order = 3)
        public int      WriteRights;
    }

    /**
     * @Function: algorithm ID
    **/
    public static final int SGD_SM1_ECB			         = 0x00000101;       //SM1 算法 ECB 加密模式
    public static final int SGD_SM1_CBC			         = 0x00000102;       //SM1 算法 CBC 加密模式
    public static final int SGD_SM1_CFB			         = 0x00000104;       //SM1 算法 CFB 加密模式
    public static final int SGD_SM1_OFB			         = 0x00000108;       //SM1 算法 OFB 加密模式
    public static final int SGD_SM1_MAC			         = 0x00000110;       //SM1 算法 MAC 运算
    public static final int SGD_SSF33_ECB                   = 0x00000201;       //SSF33 算法 ECB 加密模式
    public static final int SGD_SSF33_CBC                   = 0x00000202;       //SSF33 算法 CBC 加密模式
    public static final int SGD_SSF33_CFB                   = 0x00000204;       //SSF33 算法 CFB 加密模式
    public static final int SGD_SSF33_OFB                   = 0x00000208;       //SSF33 算法 OFB 加密模式
    public static final int SGD_SSF33_MAC                   = 0x00000210;       //SSF33 算法 MAC 运算
    public static final int SGD_SMS4_ECB		             = 0x00000401;       //SMS4 算法 ECB 加密模式
    public static final int SGD_SMS4_CBC		             = 0x00000402;       //SMS4 算法 CBC 加密模式
    public static final int SGD_SMS4_CFB		             = 0x00000404;       //SMS4 算法 CFB 加密模式
    public static final int SGD_SMS4_OFB		             = 0x00000408;       //SMS4 算法 OFB 加密模式
    public static final int SGD_SMS4_MAC		             = 0x00000410;       //SMS4 算法 MAC 运算

    public static final int SGD_RSA				         = 0x00010000;       //RSA 算法
    public static final int SGD_SM2				         = 0x00020000;       //SM2 算法
    public static final int SGD_SM2_1			             = 0x00020100;       //椭圆曲线签名算法
    public static final int SGD_SM2_2			             = 0x00020200;       //椭圆曲线密钥交换协议
    public static final int SGD_SM2_3			             = 0x00020400;       //椭圆曲线加密算法
    public static final int SGD_SM3				         = 0x00000001;       //SM3 杂凑算法
    public static final int SGD_SHA1			             = 0x00000002;       //SHA1 杂凑算法
    public static final int SGD_SHA256			             = 0x00000004;       //SHA256 杂凑算法


    ////////////////////////////VENDOR DEFINED/////////////////////////////////////
    public static final int SGD_DES_ECB			         = 0x80000101;       //DES 算法 ECB 加密模式
    public static final int SGD_DES_CBC			         = 0x80000102;       //DES 算法 CBC 加密模式
    public static final int SGD_DES_CFB			         = 0x80000104;       //DES 算法 CFB 加密模式
    public static final int SGD_DES_OFB			         = 0x80000108;       //DES 算法 OFB 加密模式
    public static final int SGD_DES_MAC			         = 0x80000110;       //DES 算法 MAC 运算
    public static final int SGD_AES_ECB			         = 0x80000201;       //AES-128 算法 ECB 加密模式
    public static final int SGD_AES_CBC			         = 0x80000202;       //AES-128 算法 CBC 加密模式
    public static final int SGD_AES_CFB			         = 0x80000204;       //AES-128 算法 CFB 加密模式
    public static final int SGD_AES_OFB			         = 0x80000208;       //AES-128 算法 OFB 加密模式
    public static final int SGD_AES_MAC			         = 0x80000210;       //AES-128 算法 MAC 运算
    public static final int SGD_SM6_ECB			         = 0x80000301;       //SM6 算法 ECB 加密模式
    public static final int SGD_SM6_CBC			         = 0x80000302;       //SM6 算法 CBC 加密模式
    public static final int SGD_SM6_CFB			         = 0x80000304;       //SM6 算法 CFB 加密模式
    public static final int SGD_SM6_OFB			         = 0x80000308;       //SM6 算法 OFB 加密模式
    public static final int SGD_SM6_MAC			         = 0x80000310;       //SM6 算法 MAC 运算

    /**
     * @Function: 以下宏用于V_GenerateKey函数ulAlgId参数
    **/
    public static final int GENERATE_KEY_USAGE_SIGN		 = 0x00001000;
    public static final int GENERATE_KEY_USAGE_ENCRYPT	     = 0x00002000;
    public static final int GENERATE_KEY_USAGE_MASK		 = 0x00003000;
    public static final int GENERATE_KEY_ALGO_RSA		     = 0x00000100;
    public static final int GENERATE_KEY_ALGO_SM2		     = 0x00000200;
    public static final int GENERATE_KEY_ALGO_SM9		     = 0x00000300;
    public static final int GENERATE_KEY_ASYM_ALGO_MASK     = 0x00000300;
    public static final int GENERATE_KEY_SYM_MODE_ECB       = 0x00000001;
    public static final int GENERATE_KEY_SYM_MODE_CBC       = 0x00000002;
    public static final int GENERATE_KEY_SYM_MODE_CFB       = 0x00000003;
    public static final int GENERATE_KEY_SYM_MODE_OFB       = 0x00000004;
    public static final int GENERATE_KEY_SYM_MODE_MAC       = 0x00000005;
    public static final int GENERATE_KEY_SYM_MODE_MASK      = 0x00000007;
    public static final int GENERATE_KEY_ALGO_DES		     = 0x00000010;
    public static final int GENERATE_KEY_ALGO_AES		     = 0x00000020;
    public static final int GENERATE_KEY_ALGO_SM1		     = 0x00000030;
    public static final int GENERATE_KEY_ALGO_SM4		     = 0x00000040;
    public static final int GENERATE_KEY_ALGO_SM6		     = 0x00000050;
    public static final int GENERATE_KEY_ALGO_SSF33		 = 0x00000060;
    public static final int GENERATE_KEY_SYM_ALGO_MASK      = 0x00000070;
    public static final int GENERATE_KEY_BIT_64			 = 0x00400000;
    public static final int GENERATE_KEY_BIT_128		     = 0x00800000;
    public static final int GENERATE_KEY_BIT_256		     = 0x01000000;
    public static final int GENERATE_KEY_BIT_512		     = 0x02000000;
    public static final int GENERATE_KEY_BIT_1024		     = 0x04000000;
    public static final int GENERATE_KEY_BIT_2048		     = 0x08000000;
    public static final int GENERATE_KEY_BIT_MASK		     = 0xFFFF0000;

    /**
     * @Function: 返回值错误码
    **/
    public static final int SAR_OK							 = 0x00000000;
    public static final int SAR_FAIL						 = 0x0A000001;
    public static final int SAR_UNKOWNERR					 = 0x0A000002;
    public static final int SAR_NOTSUPPORTYETERR			 = 0x0A000003;
    public static final int SAR_FILEERR					     = 0x0A000004;
    public static final int SAR_INVALIDHANDLEERR			 = 0x0A000005;
    public static final int SAR_INVALIDPARAMERR			 = 0x0A000006;
    public static final int SAR_READFILEERR				 = 0x0A000007;
    public static final int SAR_WRITEFILEERR				 = 0x0A000008;
    public static final int SAR_NAMELENERR					 = 0x0A000009;
    public static final int SAR_KEYUSAGEERR				 = 0x0A00000A;
    public static final int SAR_MODULUSLENERR				 = 0x0A00000B;
    public static final int SAR_NOTINITIALIZEERR			 = 0x0A00000C;
    public static final int SAR_OBJERR						 = 0x0A00000D;
    public static final int SAR_MEMORYERR					 = 0x0A00000E;
    public static final int SAR_TIMEOUTERR					 = 0x0A00000F;
    public static final int SAR_INDATALENERR				 = 0x0A000010;
    public static final int SAR_INDATAERR					 = 0x0A000011;
    public static final int SAR_GENRANDERR					 = 0x0A000012;
    public static final int SAR_HASHOBJERR					 = 0x0A000013;
    public static final int SAR_HASHERR					 = 0x0A000014;
    public static final int SAR_GENRSAKEYERR				 = 0x0A000015;
    public static final int SAR_RSAMODULUSLENERR			 = 0x0A000016;
    public static final int SAR_CSPIMPRTPUBKEYERR			 = 0x0A000017;
    public static final int SAR_RSAENCERR					 = 0x0A000018;
    public static final int SAR_RSADECERR					 = 0x0A000019;
    public static final int SAR_HASHNOTEQUALERR			 = 0x0A00001A;
    public static final int SAR_KEYNOTFOUNTERR				 = 0x0A00001B;
    public static final int SAR_CERTNOTFOUNTERR			 = 0x0A00001C;
    public static final int SAR_NOTEXPORTERR				 = 0x0A00001D;
    public static final int SAR_DECRYPTPADERR				 = 0x0A00001E;
    public static final int SAR_MACLENERR					 = 0x0A00001F;
    public static final int SAR_BUFFER_TOO_SMALL			 = 0x0A000020;
    public static final int SAR_KEYINFOTYPEERR				 = 0x0A000021;
    public static final int SAR_NOT_EVENTERR				 = 0x0A000022;
    public static final int SAR_DEVICE_REMOVED				 = 0x0A000023;
    public static final int SAR_PIN_INCORRECT				 = 0x0A000024;
    public static final int SAR_PIN_LOCKED					 = 0x0A000025;
    public static final int SAR_PIN_INVALID				 = 0x0A000026;
    public static final int SAR_PIN_LEN_RANGE				 = 0x0A000027;
    public static final int SAR_USER_ALREADY_LOGGED_IN		 = 0x0A000028;
    public static final int SAR_USER_PIN_NOT_INITIALIZED	 = 0x0A000029;
    public static final int SAR_USER_TYPE_INVALID			 = 0x0A00002A;
    public static final int SAR_APPLICATION_NAME_INVALID	 = 0x0A00002B;
    public static final int SAR_APPLICATION_EXISTS			 = 0x0A00002C;
    public static final int SAR_USER_NOT_LOGGED_IN			 = 0x0A00002D;
    public static final int SAR_APPLICATION_NOT_EXISTS		 = 0x0A00002E;
    public static final int SAR_FILE_ALREADY_EXIST			 = 0x0A00002F;
    public static final int SAR_NO_ROOM					 = 0x0A000030;
    public static final int SAR_FILE_NOT_EXIST				 = 0x0A000031;
    public static final int SAR_REACH_MAX_CONTAINER_COUNT	 = 0x0A000032;

    public static final int VR_FP_ID_INVALIED               = 0x0B000001;
    public static final int VR_FP_IMAGE_ERROR               = 0x0B000002;
    public static final int VR_FP_MATCH_ERROR               = 0x0B000003;
    public static final int VR_FP_NOT_LEAVE                 = 0x0B000004;
    public static final int VR_FP_NOT_TOUCH                 = 0x0B000005;
    public static final int VR_OP_NOT_FOUND				 = 0x0B000005;
}
