package mtopsdk.mtop.d.a;

import java.util.Map;
import mtopsdk.common.util.l;
import mtopsdk.common.util.m;
import mtopsdk.mtop.a.f;
import mtopsdk.mtop.a.i;
import mtopsdk.mtop.common.MtopNetworkProp;
import mtopsdk.mtop.common.d;
import mtopsdk.mtop.unit.ApiInfo;
import mtopsdk.mtop.unit.ApiUnit;
import mtopsdk.mtop.unit.UserUnit;
import mtopsdk.mtop.unit.UserUnit.UnitType;

public abstract class a implements d {
    protected String a(String str, String str2, mtopsdk.mtop.a aVar) {
        if (!i.a().d() || l.b(str) || l.b(str2)) {
            return null;
        }
        UserUnit userUnit = aVar.e.userUnit;
        if (userUnit == null || !UnitType.UNIT.getUnitType().equalsIgnoreCase(userUnit.unitType.getUnitType()) || !l.a(userUnit.unitPrefix)) {
            return null;
        }
        ApiUnit k = f.a().k();
        return (k == null || k.apilist == null || !k.apilist.contains(new ApiInfo(str, str2))) ? null : userUnit.unitPrefix;
    }

    protected void a(Map map, mtopsdk.mtop.a aVar) {
        if (!a(aVar)) {
            map.put("cache-control", "no-cache");
        }
    }

    protected boolean a(mtopsdk.mtop.a aVar) {
        MtopNetworkProp e = aVar.e();
        return aVar.f() instanceof d ? true : e != null && e.useCache;
    }

    protected byte[] a(Map map, String str) {
        byte[] bArr = null;
        if (map != null) {
            if (l.b(str)) {
                str = "utf-8";
            }
            String a = e.a(map, str);
            if (a != null) {
                try {
                    bArr = a.getBytes(str);
                } catch (Exception e) {
                    m.d("mtopsdk.NetworkConverter", "[createParamPostData]getPostData error");
                }
            }
        }
        return bArr;
    }
}
