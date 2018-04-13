package com.alipay.sdk.app;

public class EnvUtils {
    private static EnvEnum a = EnvEnum.ONLINE;

    public enum EnvEnum {
        ONLINE,
        SANDBOX
    }

    public static void setEnv(EnvEnum envEnum) {
        a = envEnum;
    }

    public static EnvEnum geEnv() {
        return a;
    }

    public static boolean isSandBox() {
        return a == EnvEnum.SANDBOX;
    }
}
