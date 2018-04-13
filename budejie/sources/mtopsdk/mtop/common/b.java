package mtopsdk.mtop.common;

import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.m;

public class b implements e, f, g {
    public void onFinished(i iVar, Object obj) {
        if (iVar != null && iVar.a() != null && m.a(TBSdkLog$LogEnable.DebugEnable)) {
            m.a("mtopsdk.DefaultMtopCallback", "[onFinished]" + iVar.a().toString());
        }
    }

    public void onHeader(j jVar, Object obj) {
        if (jVar != null && m.a(TBSdkLog$LogEnable.DebugEnable)) {
            m.a("mtopsdk.DefaultMtopCallback", "[onHeader]" + jVar.toString());
        }
    }
}
