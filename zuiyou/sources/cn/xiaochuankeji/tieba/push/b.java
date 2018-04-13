package cn.xiaochuankeji.tieba.push;

import cn.xiaochuankeji.tieba.push.data.c.a;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iflytek.cloud.SpeechConstant;

public class b {
    public static boolean a(int i) {
        return i == 131 || i == 130;
    }

    public static void a(JSONObject jSONObject) {
        JSONArray jSONArray = jSONObject.getJSONArray("counters");
        JSONObject jSONObject2 = jSONObject.getJSONObject("new");
        JSONObject jSONObject3 = jSONObject.getJSONObject("src");
        long longValue = jSONObject.getLongValue("ct");
        long longValue2 = jSONObject.getLongValue("to_mid");
        int intValue = jSONObject.getIntValue("subtype");
        int intValue2 = jSONObject.getIntValue("type");
        a aVar = new a();
        switch (intValue2) {
            case 130:
                a(jSONObject3, jSONObject2, intValue, aVar);
                break;
            case 131:
                b(jSONObject3, aVar);
                break;
        }
        a(jSONArray, aVar);
        cn.xiaochuankeji.tieba.push.b.b.a(aVar.e(longValue2).f(longValue).a());
    }

    private static void a(JSONObject jSONObject, JSONObject jSONObject2, int i, a aVar) {
        switch (i) {
            case 40:
                a(jSONObject, jSONObject2, aVar);
                break;
            case 70:
                a(jSONObject, aVar);
                break;
        }
        aVar.a(130);
    }

    private static void a(JSONObject jSONObject, a aVar) {
        if (jSONObject != null) {
            String string = jSONObject.getString(SpeechConstant.SUBJECT);
            long longValue = jSONObject.getLongValue("id");
            aVar.a(string).a(longValue).b(longValue);
        }
    }

    private static void a(JSONObject jSONObject, JSONObject jSONObject2, a aVar) {
        if (jSONObject != null && jSONObject2 != null) {
            JSONObject jSONObject3 = jSONObject2.getJSONObject("xmember");
            JSONObject jSONObject4 = new JSONObject();
            if (jSONObject3 != null) {
                jSONObject4.put("name", jSONObject3.getString("name"));
                jSONObject4.put("mid", Integer.valueOf(-1));
            }
            boolean z = jSONObject2.getJSONObject("audio") != null;
            String string = jSONObject2.getString("text");
            String string2 = jSONObject.getString(SpeechConstant.SUBJECT);
            long longValue = jSONObject.getLongValue("id");
            aVar.a(longValue).b(longValue).c(jSONObject2.getLongValue("id")).a(jSONObject4).b(string).b(z).a(string2);
        }
    }

    private static void b(JSONObject jSONObject, a aVar) {
        if (jSONObject != null) {
            long longValue = jSONObject.getLongValue("xroom_id");
            long longValue2 = jSONObject.getLongValue("id");
            aVar.a(longValue2).b(longValue).a(jSONObject.getString("text")).a(131);
        }
    }

    private static void a(JSONArray jSONArray, a aVar) {
        for (int i = 0; i < jSONArray.size(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            int intValue = jSONObject.getIntValue("count");
            switch (jSONObject.getIntValue("type")) {
                case 10:
                    aVar.c(intValue);
                    break;
                case 20:
                    aVar.d(intValue);
                    break;
                case 30:
                    aVar.e(intValue);
                    break;
                case 40:
                    aVar.f(intValue);
                    break;
                case 50:
                    aVar.g(intValue);
                    break;
                case 60:
                    aVar.h(intValue);
                    break;
                case 70:
                    aVar.c(intValue);
                    break;
                default:
                    break;
            }
        }
    }
}
