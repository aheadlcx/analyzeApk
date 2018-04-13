package mtopsdk.mtop.a;

import mtopsdk.mtop.domain.EnvModeEnum;

/* synthetic */ class e {
    static final /* synthetic */ int[] a = new int[EnvModeEnum.values().length];

    static {
        try {
            a[EnvModeEnum.ONLINE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            a[EnvModeEnum.PREPARE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            a[EnvModeEnum.TEST.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            a[EnvModeEnum.TEST_SANDBOX.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
