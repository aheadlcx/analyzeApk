package com.qiniu.android.utils;

import com.alipay.sdk.util.h;
import java.net.InetAddress;
import java.net.UnknownHostException;

public final class Dns {
    public static String[] getAddresses(String str) {
        int i = 0;
        try {
            InetAddress[] allByName = InetAddress.getAllByName(str);
            String[] strArr = new String[allByName.length];
            while (i < strArr.length) {
                strArr[i] = allByName[i].getHostAddress();
                i++;
            }
            return strArr;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    public static String getAddress(String str) {
        String[] addresses = getAddresses(str);
        if (addresses == null || addresses.length == 0) {
            return null;
        }
        return addresses[0];
    }

    public static String getAddressesString(String str) {
        return StringUtils.join(getAddresses(str), h.b);
    }
}
