package com.qiniu.rs;

import java.util.HashMap;

public class PutExtra {
    public static final int AUTO_CRC32 = 1;
    public static final int SPECIFY_CRC32 = 2;
    public static final int UNUSE_CRC32 = 0;
    public int checkCrc = 0;
    public long crc32;
    public String mimeType;
    public HashMap<String, String> params = new HashMap();
}
