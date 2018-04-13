package mtopsdk.mtop.util;

import com.alibaba.fastjson.JSON;
import com.alipay.sdk.util.h;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import mtopsdk.common.util.m;
import mtopsdk.mtop.domain.MtopRequest;
import mtopsdk.mtop.domain.b;

public class k {
    public static String a(Map map) {
        StringBuilder stringBuilder = new StringBuilder(64);
        stringBuilder.append("{");
        if (!(map == null || map.isEmpty())) {
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                if (!(str == null || str2 == null)) {
                    try {
                        stringBuilder.append(JSON.toJSONString(str));
                        stringBuilder.append(":");
                        stringBuilder.append(JSON.toJSONString(str2));
                        stringBuilder.append(",");
                    } catch (Throwable th) {
                        m.d("mtopsdk.ReflectUtil", "[converMapToDataStr] convert key=" + str + ",value=" + str2 + " to dataStr error ---" + th.toString());
                    }
                }
            }
            int length = stringBuilder.length();
            if (length > 1) {
                stringBuilder.deleteCharAt(length - 1);
            }
        }
        stringBuilder.append(h.d);
        return stringBuilder.toString();
    }

    public static MtopRequest a(Object obj) {
        MtopRequest mtopRequest = new MtopRequest();
        if (obj != null) {
            a(mtopRequest, obj);
        }
        return mtopRequest;
    }

    public static MtopRequest a(b bVar) {
        MtopRequest mtopRequest = new MtopRequest();
        if (bVar != null) {
            a(mtopRequest, bVar);
        }
        return mtopRequest;
    }

    private static void a(MtopRequest mtopRequest, Object obj) {
        try {
            Map hashMap = new HashMap();
            Class cls = obj.getClass();
            HashSet hashSet = new HashSet();
            hashSet.addAll(Arrays.asList(cls.getFields()));
            hashSet.addAll(Arrays.asList(cls.getDeclaredFields()));
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                Field field = (Field) it.next();
                String name = field.getName();
                if (!(name.indexOf("$") != -1 || name.equals("serialVersionUID") || name.equals("ORIGINALJSON"))) {
                    field.setAccessible(true);
                    Object obj2;
                    if (name.equals("API_NAME")) {
                        obj2 = field.get(obj);
                        if (obj2 != null) {
                            mtopRequest.setApiName(obj2.toString());
                        }
                    } else if (name.equals("VERSION")) {
                        obj2 = field.get(obj);
                        if (obj2 != null) {
                            mtopRequest.setVersion(obj2.toString());
                        }
                    } else if (name.equals("NEED_ECODE")) {
                        r0 = Boolean.valueOf(field.getBoolean(obj));
                        r0 = r0 != null && r0.booleanValue();
                        mtopRequest.setNeedEcode(r0);
                    } else if (name.equals("NEED_SESSION")) {
                        r0 = Boolean.valueOf(field.getBoolean(obj));
                        r0 = r0 != null && r0.booleanValue();
                        mtopRequest.setNeedSession(r0);
                    } else {
                        obj2 = field.get(obj);
                        if (obj2 != null) {
                            if (obj2 instanceof String) {
                                hashMap.put(name, obj2.toString());
                            } else {
                                hashMap.put(name, JSON.toJSONString(obj2));
                            }
                        }
                    }
                }
            }
            mtopRequest.dataParams = hashMap;
            mtopRequest.setData(a(hashMap));
        } catch (Throwable e) {
            m.b("mtopsdk.ReflectUtil", "parseParams failed.", e);
        }
    }
}
