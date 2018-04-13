package com.huawei.hms.update.a.a;

import com.meizu.cloud.pushsdk.constants.PushConstants;

public final class d {
    public static String a(int i) {
        switch (i) {
            case 1000:
                return "CHECK_OK";
            case 1101:
                return "CHECK_CANCELED";
            case 1201:
                return "CHECK_FAILURE";
            case 1202:
                return "CHECK_NO_UPDATE";
            case 1203:
                return "CHECK_NO_SUPPORTED";
            case 2000:
                return "DOWNLOAD_SUCCESS";
            case PushConstants.BROADCAST_MESSAGE_ARRIVE /*2100*/:
                return "DOWNLOADING";
            case 2101:
                return "DOWNLOAD_CANCELED";
            case PushConstants.ONTIME_NOTIFICATION /*2201*/:
                return "DOWNLOAD_FAILURE";
            case PushConstants.DELAY_NOTIFICATION /*2202*/:
                return "DOWNLOAD_HASH_ERROR";
            case 2203:
                return "DOWNLOAD_NO_SPACE";
            case 2204:
                return "DOWNLOAD_NO_STORAGE";
            default:
                return "UNKNOWN - " + Integer.toString(i);
        }
    }
}
