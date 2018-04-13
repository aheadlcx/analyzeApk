package com.iflytek.aiui;

import java.util.Locale;

public class Version {
    private static VersionType a = VersionType.MOBILE_PHONE;

    public enum VersionType {
        MOBILE_PHONE,
        INTELLIGENT_HDW
    }

    public static String getVersion() {
        return "4.5.1045." + com.iflytek.cloud.Version.getVersion().replaceAll("^.+\\.(\\d+)$", "$1");
    }

    public static String getVersionType() {
        return a.name().toLowerCase(Locale.ENGLISH);
    }

    public static boolean isMobileVersion() {
        return a == VersionType.MOBILE_PHONE;
    }

    public static void setVersionType(VersionType versionType) {
        a = versionType;
    }
}
