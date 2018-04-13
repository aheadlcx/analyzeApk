package mtopsdk.mtop.unit;

import com.taobao.tao.remotebusiness.listener.c;
import java.util.HashMap;
import java.util.Map;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.g;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a.f;
import mtopsdk.mtop.a.i;
import mtopsdk.mtop.unit.UserUnit.UnitType;
import mtopsdk.mtop.util.e;

public final class a {
    private static UserUnit a(String str, String str2) {
        String str3 = null;
        String[] split = str.split(",");
        if (split == null || split.length <= 0) {
            return null;
        }
        UnitType unitType = null;
        for (String str4 : split) {
            try {
                if (str4.contains("type=")) {
                    unitType = UnitType.UNIT.getUnitType().equalsIgnoreCase(str4.substring(5)) ? UnitType.UNIT : UnitType.CENTER;
                } else if (str4.contains("prefix=")) {
                    str3 = str4.substring(7);
                }
            } catch (Throwable e) {
                m.b("mtopsdk.UnitConfigManager", str2, "[parseUserUnitInfo] parse x-m-update-unitinfo  header error,userUnitInfo=" + str, e);
            }
        }
        return new UserUnit(mtopsdk.xstate.a.b(), unitType, str3);
    }

    public static void a() {
        if (!i.a().d()) {
            return;
        }
        if (g.b()) {
            e.a(new c());
        } else {
            c();
        }
    }

    private static void a(String str, String str2, String str3) {
        if (l.b(str) || l.b(str2)) {
            m.c("mtopsdk.UnitConfigManager", str3, "[updateAndStoreApiUnitInfo] invalid apiUnitInfo,version=" + str + ",url=" + str2);
            return;
        }
        ApiUnit k = f.a().k();
        if (k == null || !str.equals(k.version)) {
            e.a(new b(str2, str3));
        } else if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
            m.a("mtopsdk.UnitConfigManager", str3, "[updateAndStoreApiUnitInfo] current apiUnit version is up-to-date,version=" + str);
        }
    }

    public static void a(Map map, String str) {
        if (!i.a().d()) {
            m.b("mtopsdk.UnitConfigManager", str, "[parseUnitSettingHeader]unitSwitchOpen is false");
        } else if (map != null && !map.isEmpty()) {
            String a = c.a(map, "x-m-update-unitinfo");
            if (l.a(a)) {
                a(a(a, str), str);
            }
            a = c.a(map, "x-m-update-unitapi");
            if (l.a(a)) {
                Map b = b(a, str);
                a((String) b.get("v="), (String) b.get("url="), str);
            }
        }
    }

    private static void a(UserUnit userUnit, String str) {
        if (userUnit == null) {
            m.c("mtopsdk.UnitConfigManager", str, "[updateAndStoreUserUnitInfo]  invalid userUnit,userUnit=" + userUnit);
        } else if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
            m.b("mtopsdk.UnitConfigManager", str, String.format("[updateAndStoreUserUnitInfo] update userUnitinfo succeed.userid=%s ;utdid=%s ;unitPrefix=%s", new Object[]{userUnit.userId, f.a().g(), userUnit.unitPrefix}));
        }
    }

    private static Map b(String str, String str2) {
        Map hashMap = new HashMap(2);
        String[] split = str.split(",");
        if (split != null && split.length > 0) {
            for (String str3 : split) {
                try {
                    if (str3.contains("v=")) {
                        hashMap.put("v=", str3.substring(2));
                    } else if (str3.contains("url=")) {
                        hashMap.put("url=", str3.substring(4));
                    }
                } catch (Exception e) {
                    m.d("mtopsdk.UnitConfigManager", str2, "[parseApiUnitInfoParams] parse x-m-update-unitapi  header error---" + e.toString());
                }
            }
        }
        return hashMap;
    }

    private static void c() {
        f a = f.a();
        if (a.k() == null) {
            try {
                ApiUnit apiUnit = (ApiUnit) g.a(a.b().getFilesDir(), "UNIT_SETTING_STORE.API_UNIT_ITEM");
                if (apiUnit != null) {
                    a.a(apiUnit);
                    if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                        m.b("mtopsdk.UnitConfigManager", "[loadUnitInfoFromLocalStore] load ApiUnit info from local Storage succeed.");
                    }
                }
            } catch (Exception e) {
                m.d("mtopsdk.UnitConfigManager", "[loadUnitInfoFromLocalStore] parse apiUnit from local Storage error ---" + e.toString());
            }
        }
    }
}
