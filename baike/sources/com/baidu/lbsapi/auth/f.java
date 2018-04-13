package com.baidu.lbsapi.auth;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

class f {
    private Context a;
    private List<HashMap<String, String>> b = null;
    private a<String> c = null;

    interface a<Result> {
        void a(Result result);
    }

    protected f(Context context) {
        this.a = context;
    }

    private List<HashMap<String, String>> a(HashMap<String, String> hashMap, String[] strArr) {
        List<HashMap<String, String>> arrayList = new ArrayList();
        String str;
        if (strArr == null || strArr.length <= 0) {
            HashMap hashMap2 = new HashMap();
            for (String str2 : hashMap.keySet()) {
                str2 = str2.toString();
                hashMap2.put(str2, hashMap.get(str2));
            }
            arrayList.add(hashMap2);
        } else {
            for (Object put : strArr) {
                HashMap hashMap3 = new HashMap();
                for (String str22 : hashMap.keySet()) {
                    str22 = str22.toString();
                    hashMap3.put(str22, hashMap.get(str22));
                }
                hashMap3.put("mcode", put);
                arrayList.add(hashMap3);
            }
        }
        return arrayList;
    }

    private void a(String str) {
        JSONObject jSONObject;
        if (str == null) {
            str = "";
        }
        try {
            jSONObject = new JSONObject(str);
            if (!jSONObject.has("status")) {
                jSONObject.put("status", -1);
            }
        } catch (JSONException e) {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("status", -1);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        if (this.c != null) {
            this.c.a(jSONObject != null ? jSONObject.toString() : new JSONObject().toString());
        }
    }

    private void a(List<HashMap<String, String>> list) {
        b.a("syncConnect start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        if (list == null || list.size() == 0) {
            b.b("syncConnect failed,params list is null or size is 0");
            return;
        }
        List arrayList = new ArrayList();
        int i = 0;
        while (i < list.size()) {
            JSONObject jSONObject;
            b.a("syncConnect resuest " + i + "  start!!!");
            HashMap hashMap = (HashMap) list.get(i);
            g gVar = new g(this.a);
            if (gVar.a()) {
                String a = gVar.a(hashMap);
                if (a == null) {
                    a = "";
                }
                b.a("syncConnect resuest " + i + "  result:" + a);
                arrayList.add(a);
                try {
                    jSONObject = new JSONObject(a);
                    if (jSONObject.has("status") && jSONObject.getInt("status") == 0) {
                        b.a("auth end and break");
                        a(a);
                        return;
                    }
                } catch (JSONException e) {
                    b.a("continue-------------------------------");
                }
            } else {
                b.a("Current network is not available.");
                arrayList.add(a.a("Current network is not available."));
            }
            b.a("syncConnect end");
            i++;
        }
        b.a("--iiiiii:" + i + "<><>paramList.size():" + list.size() + "<><>authResults.size():" + arrayList.size());
        if (list.size() > 0 && i == list.size() && arrayList.size() > 0 && i == arrayList.size() && i - 1 > 0) {
            try {
                jSONObject = new JSONObject((String) arrayList.get(i - 1));
                if (jSONObject.has("status") && jSONObject.getInt("status") != 0) {
                    b.a("i-1 result is not 0,return first result");
                    a((String) arrayList.get(0));
                }
            } catch (JSONException e2) {
                a(a.a("JSONException:" + e2.getMessage()));
            }
        }
    }

    protected void a(HashMap<String, String> hashMap, String[] strArr, a<String> aVar) {
        this.b = a((HashMap) hashMap, strArr);
        this.c = aVar;
        new Thread(new h(this)).start();
    }
}
