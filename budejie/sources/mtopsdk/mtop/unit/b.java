package mtopsdk.mtop.unit;

import android.content.Context;
import android.support.v4.app.FragmentTransaction;
import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.io.Serializable;
import mtopsdk.a.b.c;
import mtopsdk.a.b.e;
import mtopsdk.a.b.g;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a.f;
import mtopsdk.mtop.domain.ProtocolEnum;

final class b implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;

    b(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final void run() {
        try {
            String str = !this.a.startsWith(ProtocolEnum.HTTP.getProtocol()) ? ProtocolEnum.HTTP.getProtocol() + this.a : this.a;
            Context b = f.a().b();
            try {
                e b2 = f.a().l().a(new c().a(str).d((int) FragmentTransaction.TRANSIT_FRAGMENT_FADE).a()).b();
                if (b2 != null && b2.a() == 200) {
                    g c = b2.c();
                    if (c != null) {
                        try {
                            ApiUnit apiUnit;
                            try {
                                apiUnit = (ApiUnit) JSON.parseObject(new String(c.c(), "utf-8"), ApiUnit.class);
                            } catch (Exception e) {
                                m.d("mtopsdk.UnitConfigManager", this.b, "[updateAndStoreApiUnitInfo]parse apiUnit json from cdn error ---" + e.toString());
                                Serializable serializable = null;
                            }
                            if (apiUnit != null && l.a(apiUnit.version)) {
                                ApiUnit k = f.a().k();
                                if (k == null || !apiUnit.version.equals(k.version)) {
                                    f.a().a(apiUnit);
                                    mtopsdk.common.util.g.a(apiUnit, b.getFilesDir(), "UNIT_SETTING_STORE.API_UNIT_ITEM");
                                    m.b("mtopsdk.UnitConfigManager", this.b, "[updateAndStoreApiUnitInfo] update apiUnit succeed.apiUnit=" + apiUnit);
                                }
                            }
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        } catch (Exception e32) {
            m.d("mtopsdk.UnitConfigManager", this.b, "[updateAndStoreApiUnitInfo] generate apiUnit Config URL error.---" + e32.toString());
        }
    }
}
