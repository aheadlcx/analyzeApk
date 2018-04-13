package mtopsdk.common.util;

public class k {
    private static SdkSetting$ENV a = SdkSetting$ENV.release;

    public static void a(SdkSetting$ENV sdkSetting$ENV) {
        if (sdkSetting$ENV != null) {
            a = sdkSetting$ENV;
        }
    }
}
