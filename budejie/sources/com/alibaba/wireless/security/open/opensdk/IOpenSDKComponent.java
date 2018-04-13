package com.alibaba.wireless.security.open.opensdk;

import com.alibaba.wireless.security.open.IComponent;
import com.alibaba.wireless.security.open.SecException;

public interface IOpenSDKComponent extends IComponent {
    public static final byte[] OPEN_BIZ_IID = new byte[]{(byte) 0, (byte) 2};
    public static final byte[] OPEN_BIZ_TID = new byte[]{(byte) 0, (byte) 3};
    public static final byte[] OPEN_BIZ_UID = new byte[]{(byte) 0, (byte) 1};

    Long analyzeOpenId(String str, String str2, String str3, byte[] bArr, String str4) throws SecException;
}
