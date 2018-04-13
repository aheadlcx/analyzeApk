package com.qiniu.conf;

public class Conf {
    public static final int BLOCK_SIZE = 4194304;
    public static int BLOCK_TRY_TIMES = 2;
    public static final int CANCEL_CODE = -1;
    public static final String CHARSET = "utf-8";
    public static int CHUNK_SIZE = 262144;
    public static int CHUNK_TRY_TIMES = 3;
    public static int CONNECTION_TIMEOUT = 30000;
    public static final int ERROR_CODE = 0;
    public static int FIRST_CHUNK = 262144;
    public static int ONCE_WRITE_SIZE = 32768;
    public static String PROCESS_MSG = "upload alread in process or procssed or canceled.";
    public static int SO_TIMEOUT = 30000;
    public static String UP_HOST = "http://upload.qiniu.com";
    public static String UP_HOST2 = "http://up.qiniu.com";
    public static String USER_AGENT = null;
    public static final String VERSION = "6.1.0";
}
