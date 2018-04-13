package mtopsdk.mtop.a;

import mtopsdk.common.util.SdkSetting$ENV;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.k;
import mtopsdk.common.util.m;
import mtopsdk.mtop.b;
import mtopsdk.mtop.domain.EnvModeEnum;

final class d implements Runnable {
    final /* synthetic */ EnvModeEnum a;

    d(EnvModeEnum envModeEnum) {
        this.a = envModeEnum;
    }

    public final void run() {
        a.a();
        if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
            m.b("mtopsdk.MtopSDK", "[switchEnvMode]MtopSDK switchEnvMode start");
        }
        switch (e.a[this.a.ordinal()]) {
            case 1:
                a.a.a(EnvModeEnum.ONLINE);
                b.a = EnvModeEnum.ONLINE;
                k.a(SdkSetting$ENV.release);
                a.c(this.a);
                if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                    m.b("mtopsdk.MtopSDK", "[switchEnvMode]switch envMode to ONLINE!");
                }
                a.a(false);
                break;
            case 2:
                a.a.a(EnvModeEnum.PREPARE);
                b.a = EnvModeEnum.PREPARE;
                k.a(SdkSetting$ENV.develop);
                a.a(true);
                a.c(this.a);
                if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                    m.b("mtopsdk.MtopSDK", "[switchEnvMode]switch envMode to PRE!");
                    break;
                }
                break;
            case 3:
                a.a.a(EnvModeEnum.TEST);
                b.a = EnvModeEnum.TEST;
                k.a(SdkSetting$ENV.debug);
                a.a(true);
                a.c(this.a);
                if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                    m.b("mtopsdk.MtopSDK", "[switchEnvMode]switch envMode to DAILY!");
                    break;
                }
                break;
            case 4:
                a.a.a(EnvModeEnum.TEST_SANDBOX);
                b.a = EnvModeEnum.TEST_SANDBOX;
                k.a(SdkSetting$ENV.debug);
                a.a(true);
                a.c(this.a);
                if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                    m.b("mtopsdk.MtopSDK", "[switchEnvMode]switch envMode to DAILY SandBox!");
                    break;
                }
                break;
        }
        a.b(a.a.b());
        if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
            m.b("mtopsdk.MtopSDK", "[switchEnvMode]MtopSDK switchEnvMode end");
        }
    }
}
