package com.alibaba.baichuan.android.auth;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.alibaba.baichuan.android.trade.AlibcContext;
import com.alibaba.baichuan.android.trade.AlibcContext.Environment;
import com.alibaba.baichuan.android.trade.utils.StringUtils;
import com.alibaba.baichuan.android.trade.utils.http.HttpHelper;
import com.alibaba.baichuan.android.trade.utils.log.AlibcLogger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

final class c extends AsyncTask {
    final /* synthetic */ String a;
    final /* synthetic */ e b;
    final /* synthetic */ boolean c;
    final /* synthetic */ boolean d;

    c(String str, e eVar, boolean z, boolean z2) {
        this.a = str;
        this.b = eVar;
        this.c = z;
        this.d = z2;
    }

    protected String a(String... strArr) {
        String str = "";
        if (AlibcContext.environment == Environment.TEST) {
            str = String.format("http://100.69.205.47/authHint.htm?apiList=[\"%s\"]", new Object[]{this.a.replace("$", "_")});
        } else if (AlibcContext.environment == Environment.PRE) {
            str = String.format("http://pre.nbsdk-baichuan.taobao.com/authHint.htm?apiList=[\"%s\"]", new Object[]{this.a.replace("$", "_")});
        } else {
            str = String.format("https://nbsdk-baichuan.alicdn.com/authHint.htm?apiList=[\"%s\"]", new Object[]{this.a.replace("$", "_")});
        }
        String str2 = "";
        try {
            AlibcLogger.e("alibc", "getHint : url  " + str);
            str2 = HttpHelper.get(str, null);
            AlibcLogger.e("alibc", "getHint : url  " + str + " " + str2);
            return str2;
        } catch (Exception e) {
            Exception exception = e;
            str = str2;
            exception.printStackTrace();
            return str;
        }
    }

    protected void a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                this.b.a(a.HINTLIST_NULL.b, a.HINTLIST_NULL.c);
                return;
            }
            JSONObject parseObject = JSON.parseObject(str);
            Set set = null;
            if (StringUtils.obj2Boolean(parseObject.get("success"))) {
                Map obj2MapObject = StringUtils.obj2MapObject(parseObject.get("authHintMap"));
                if (obj2MapObject != null && obj2MapObject.size() > 0) {
                    Set hashSet = new HashSet(obj2MapObject.size());
                    for (String str2 : obj2MapObject.keySet()) {
                        Map obj2MapString = StringUtils.obj2MapString(obj2MapObject.get(str2));
                        if (obj2MapString != null) {
                            AlibcAuthHint.putExpandList((String) obj2MapString.get("hintId"), (String) obj2MapString.get("hintName"));
                            hashSet.add(obj2MapString.get("hintId"));
                        }
                    }
                    AlibcAuthHint.putApiAndHint(this.a, hashSet);
                    set = hashSet;
                }
            }
            if (set == null || set.size() <= 0) {
                this.b.a(a.HINTLIST_NULL.b, a.HINTLIST_NULL.c);
            } else if (this.c) {
                AlibcAuth.a(set, this.b, this.d);
            } else {
                this.b.a();
            }
        } catch (Exception e) {
            this.b.a(a.HINTLIST_NULL.b, a.HINTLIST_NULL.c);
            e.printStackTrace();
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((String) obj);
    }
}
