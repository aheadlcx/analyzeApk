package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.data.c;
import com.alipay.sdk.packet.impl.d;
import com.alipay.sdk.sys.b;
import com.alipay.sdk.util.H5PayResultModel;
import com.alipay.sdk.util.e;
import com.alipay.sdk.util.h;
import com.alipay.sdk.util.i;
import com.alipay.sdk.util.j;
import com.alipay.sdk.util.l;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobstat.Config;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class PayTask {
    static final Object a = e.class;
    private Activity b;
    private com.alipay.sdk.widget.a c;
    private String d = "wappaygw.alipay.com/service/rest.htm";
    private String e = "mclient.alipay.com/service/rest.htm";
    private String f = "mclient.alipay.com/home/exterfaceAssign.htm";
    private Map<String, a> g = new HashMap();

    private class a {
        String a;
        String b;
        final /* synthetic */ PayTask c;

        private a(PayTask payTask) {
            this.c = payTask;
            this.a = "";
            this.b = "";
        }
    }

    public PayTask(Activity activity) {
        this.b = activity;
        b a = b.a();
        Context context = this.b;
        c.a();
        a.a(context);
        com.alipay.sdk.app.statistic.a.a(activity);
        this.c = new com.alipay.sdk.widget.a(activity, com.alipay.sdk.widget.a.b);
    }

    public synchronized String pay(String str, boolean z) {
        String str2;
        Object obj = null;
        synchronized (this) {
            String a;
            if (z) {
                a();
            }
            try {
                Context applicationContext;
                String[] split;
                int i;
                String[] split2;
                int i2;
                String a2 = new com.alipay.sdk.sys.a(this.b).a(str);
                if (!a2.contains("paymethod=\"expressGateway\"") && l.c(this.b)) {
                    e eVar = new e(this.b, new g(this));
                    a = eVar.a(a2);
                    eVar.a = null;
                    if (!TextUtils.equals(a, e.b)) {
                        if (TextUtils.isEmpty(a)) {
                            a = h.a();
                        }
                        applicationContext = this.b.getApplicationContext();
                        if (!TextUtils.isEmpty(a)) {
                            split = a.split(h.b);
                            i = 0;
                            while (i < split.length) {
                                if (split[i].startsWith(h.c) && split[i].endsWith(h.d)) {
                                    split2 = split[i].substring(8, split[i].length() - 1).split(com.alipay.sdk.sys.a.b);
                                    i2 = 0;
                                    while (i2 < split2.length) {
                                        if (!split2[i2].startsWith(h.e) && split2[i2].endsWith("\"")) {
                                            obj = split2[i2].substring(13, split2[i2].length() - 1);
                                            break;
                                        } else if (split2[i2].startsWith(h.g)) {
                                            obj = split2[i2].substring(12);
                                            break;
                                        } else {
                                            i2++;
                                        }
                                    }
                                }
                                i++;
                            }
                        }
                        if (!TextUtils.isEmpty(obj)) {
                            i.a(applicationContext, h.a, obj);
                        }
                        com.alipay.sdk.data.a.b().a(this.b.getApplicationContext());
                        b();
                        com.alipay.sdk.app.statistic.a.a(this.b.getApplicationContext(), str);
                        str2 = a;
                    }
                }
                a = a(a2);
                applicationContext = this.b.getApplicationContext();
                if (TextUtils.isEmpty(a)) {
                    split = a.split(h.b);
                    i = 0;
                    while (i < split.length) {
                        split2 = split[i].substring(8, split[i].length() - 1).split(com.alipay.sdk.sys.a.b);
                        i2 = 0;
                        while (i2 < split2.length) {
                            if (!split2[i2].startsWith(h.e)) {
                            }
                            if (split2[i2].startsWith(h.g)) {
                                obj = split2[i2].substring(12);
                                break;
                            }
                            i2++;
                        }
                        i++;
                    }
                }
                if (TextUtils.isEmpty(obj)) {
                    i.a(applicationContext, h.a, obj);
                }
            } catch (Throwable th) {
                try {
                    str2 = h.a();
                    com.alipay.sdk.data.a.b().a(this.b.getApplicationContext());
                    b();
                    com.alipay.sdk.app.statistic.a.a(this.b.getApplicationContext(), str);
                } catch (Throwable th2) {
                    com.alipay.sdk.data.a.b().a(this.b.getApplicationContext());
                    b();
                    com.alipay.sdk.app.statistic.a.a(this.b.getApplicationContext(), str);
                }
            }
            com.alipay.sdk.data.a.b().a(this.b.getApplicationContext());
            b();
            com.alipay.sdk.app.statistic.a.a(this.b.getApplicationContext(), str);
            str2 = a;
        }
        return str2;
    }

    public synchronized Map<String, String> payV2(String str, boolean z) {
        return j.a(pay(str, z));
    }

    public synchronized String fetchTradeToken() {
        return i.b(this.b.getApplicationContext(), h.a, "");
    }

    public synchronized String fetchOrderInfoFromH5PayUrl(String str) {
        String trim;
        if (!TextUtils.isEmpty(str)) {
            String trim2;
            trim = str.trim();
            if (trim.startsWith("https://" + this.d) || trim.startsWith("http://" + this.d)) {
                trim2 = trim.replaceFirst("(http|https)://" + this.d + "\\?", "").trim();
                if (!TextUtils.isEmpty(trim2)) {
                    trim = "_input_charset=\"utf-8\"&ordertoken=\"" + l.a("<request_token>", "</request_token>", (String) l.a(trim2).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new com.alipay.sdk.sys.a(this.b).a(Config.STAT_SDK_CHANNEL, "h5tonative") + "\"";
                }
            }
            try {
                if (trim.startsWith("https://" + this.e) || trim.startsWith("http://" + this.e)) {
                    trim2 = trim.replaceFirst("(http|https)://" + this.e + "\\?", "").trim();
                    if (!TextUtils.isEmpty(trim2)) {
                        trim = "_input_charset=\"utf-8\"&ordertoken=\"" + l.a("<request_token>", "</request_token>", (String) l.a(trim2).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new com.alipay.sdk.sys.a(this.b).a(Config.STAT_SDK_CHANNEL, "h5tonative") + "\"";
                    }
                }
                if ((trim.startsWith("https://" + this.f) || trim.startsWith("http://" + this.f)) && trim.contains("alipay.wap.create.direct.pay.by.user") && !TextUtils.isEmpty(trim.replaceFirst("(http|https)://" + this.f + "\\?", "").trim())) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("url", str);
                        jSONObject.put("bizcontext", new com.alipay.sdk.sys.a(this.b).a(Config.STAT_SDK_CHANNEL, "h5tonative"));
                        trim = "new_external_info==" + jSONObject.toString();
                    } catch (Throwable th) {
                    }
                }
                if (Pattern.compile("^(http|https)://(maliprod\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mali\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mclient\\.alipay\\.com\\/w\\/trade_pay\\.do.?)").matcher(str).find()) {
                    trim = l.a("?", "", str);
                    if (!TextUtils.isEmpty(trim)) {
                        Map a = l.a(trim);
                        StringBuilder stringBuilder = new StringBuilder();
                        if (a(false, true, com.alipay.sdk.app.statistic.c.H, stringBuilder, a, com.alipay.sdk.app.statistic.c.H, "alipay_trade_no")) {
                            a(true, false, "pay_phase_id", stringBuilder, a, "payPhaseId", "pay_phase_id", "out_relation_id");
                            stringBuilder.append("&biz_sub_type=\"TRADE\"");
                            stringBuilder.append("&biz_type=\"trade\"");
                            trim = (String) a.get("app_name");
                            if (TextUtils.isEmpty(trim) && !TextUtils.isEmpty((CharSequence) a.get(IXAdRequestInfo.CELL_ID))) {
                                trim = "ali1688";
                            } else if (TextUtils.isEmpty(trim) && !(TextUtils.isEmpty((CharSequence) a.get("sid")) && TextUtils.isEmpty((CharSequence) a.get("s_id")))) {
                                trim = "tb";
                            }
                            stringBuilder.append("&app_name=\"" + trim + "\"");
                            if (a(true, true, "extern_token", stringBuilder, a, "extern_token", IXAdRequestInfo.CELL_ID, "sid", "s_id")) {
                                a(true, false, "appenv", stringBuilder, a, "appenv");
                                stringBuilder.append("&pay_channel_id=\"alipay_sdk\"");
                                a aVar = new a();
                                aVar.a = (String) a.get("return_url");
                                aVar.b = (String) a.get("pay_order_id");
                                trim = stringBuilder.toString() + "&bizcontext=\"" + new com.alipay.sdk.sys.a(this.b).a(Config.STAT_SDK_CHANNEL, "h5tonative") + "\"";
                                this.g.put(trim, aVar);
                            } else {
                                trim = "";
                            }
                        }
                    }
                }
            } catch (Throwable th2) {
            }
        }
        trim = "";
        return trim;
    }

    private static boolean a(boolean z, boolean z2, String str, StringBuilder stringBuilder, Map<String, String> map, String... strArr) {
        String str2;
        String str3 = "";
        for (Object obj : strArr) {
            if (!TextUtils.isEmpty((CharSequence) map.get(obj))) {
                str2 = (String) map.get(obj);
                break;
            }
        }
        str2 = str3;
        if (TextUtils.isEmpty(str2)) {
            if (z2) {
                return false;
            }
        } else if (z) {
            stringBuilder.append(com.alipay.sdk.sys.a.b).append(str).append("=\"").append(str2).append("\"");
        } else {
            stringBuilder.append(str).append("=\"").append(str2).append("\"");
        }
        return true;
    }

    public synchronized H5PayResultModel h5Pay(String str, boolean z) {
        H5PayResultModel h5PayResultModel;
        synchronized (this) {
            H5PayResultModel h5PayResultModel2 = new H5PayResultModel();
            try {
                str.trim();
                String[] split = pay(str, z).split(h.b);
                Map hashMap = new HashMap();
                for (String str2 : split) {
                    String substring = str2.substring(0, str2.indexOf("={"));
                    String str3 = substring + "={";
                    hashMap.put(substring, str2.substring(str3.length() + str2.indexOf(str3), str2.lastIndexOf(h.d)));
                }
                if (hashMap.containsKey(j.a)) {
                    h5PayResultModel2.setResultCode((String) hashMap.get(j.a));
                }
                if (hashMap.containsKey("callBackUrl")) {
                    h5PayResultModel2.setReturnUrl((String) hashMap.get("callBackUrl"));
                } else if (hashMap.containsKey(j.c)) {
                    try {
                        String str4 = (String) hashMap.get(j.c);
                        if (str4.length() > 15) {
                            a aVar = (a) this.g.get(str);
                            if (aVar != null) {
                                if (TextUtils.isEmpty(aVar.b)) {
                                    h5PayResultModel2.setReturnUrl(aVar.a);
                                } else {
                                    h5PayResultModel2.setReturnUrl(com.alipay.sdk.data.a.b().j.replace("$OrderId$", aVar.b));
                                }
                                this.g.remove(str);
                                h5PayResultModel = h5PayResultModel2;
                            } else {
                                CharSequence a = l.a("&callBackUrl=\"", "\"", str4);
                                if (TextUtils.isEmpty(a)) {
                                    a = l.a("&call_back_url=\"", "\"", str4);
                                    if (TextUtils.isEmpty(a)) {
                                        a = l.a(com.alipay.sdk.cons.a.o, "\"", str4);
                                        if (TextUtils.isEmpty(a)) {
                                            a = URLDecoder.decode(l.a(com.alipay.sdk.cons.a.p, com.alipay.sdk.sys.a.b, str4), "utf-8");
                                            if (TextUtils.isEmpty(a)) {
                                                str4 = URLDecoder.decode(l.a("&callBackUrl=", com.alipay.sdk.sys.a.b, str4), "utf-8");
                                                if (TextUtils.isEmpty(str4)) {
                                                    str4 = com.alipay.sdk.data.a.b().j;
                                                }
                                                h5PayResultModel2.setReturnUrl(URLDecoder.decode(str4, "utf-8"));
                                            }
                                        }
                                    }
                                }
                                CharSequence charSequence = a;
                                if (TextUtils.isEmpty(str4)) {
                                    str4 = com.alipay.sdk.data.a.b().j;
                                }
                                h5PayResultModel2.setReturnUrl(URLDecoder.decode(str4, "utf-8"));
                            }
                        } else {
                            a aVar2 = (a) this.g.get(str);
                            if (aVar2 != null) {
                                h5PayResultModel2.setReturnUrl(aVar2.a);
                                this.g.remove(str);
                                h5PayResultModel = h5PayResultModel2;
                            }
                        }
                    } catch (Throwable th) {
                    }
                }
            } catch (Throwable th2) {
            }
            h5PayResultModel = h5PayResultModel2;
        }
        return h5PayResultModel;
    }

    private void a() {
        if (this.c != null) {
            this.c.a();
        }
    }

    private void b() {
        if (this.c != null) {
            this.c.b();
            this.c = null;
        }
    }

    public String getVersion() {
        return com.alipay.sdk.cons.a.f;
    }

    private String a(String str) {
        i iVar;
        int i = 0;
        a();
        com.alipay.sdk.tid.a aVar;
        try {
            List a = com.alipay.sdk.protocol.b.a(new d().a(this.b.getApplicationContext(), str).a().optJSONObject(com.alipay.sdk.cons.c.c).optJSONObject(com.alipay.sdk.cons.c.d));
            for (int i2 = 0; i2 < a.size(); i2++) {
                if (((com.alipay.sdk.protocol.b) a.get(i2)).a == com.alipay.sdk.protocol.a.Update) {
                    String[] strArr = ((com.alipay.sdk.protocol.b) a.get(i2)).b;
                    if (strArr.length == 3 && TextUtils.equals(com.alipay.sdk.cons.b.c, strArr[0])) {
                        Context context = b.a().a;
                        com.alipay.sdk.tid.b a2 = com.alipay.sdk.tid.b.a();
                        if (!(TextUtils.isEmpty(strArr[1]) || TextUtils.isEmpty(strArr[2]))) {
                            a2.a = strArr[1];
                            a2.b = strArr[2];
                            aVar = new com.alipay.sdk.tid.a(context);
                            aVar.a(com.alipay.sdk.util.a.a(context).a(), com.alipay.sdk.util.a.a(context).b(), a2.a, a2.b);
                            aVar.close();
                        }
                    }
                }
            }
            b();
            while (i < a.size()) {
                if (((com.alipay.sdk.protocol.b) a.get(i)).a == com.alipay.sdk.protocol.a.WapPay) {
                    String a3 = a((com.alipay.sdk.protocol.b) a.get(i));
                    b();
                    return a3;
                }
                i++;
            }
            b();
            iVar = null;
        } catch (Exception e) {
            aVar.close();
        } catch (Throwable e2) {
            i a4 = i.a(i.NETWORK_ERROR.h);
            com.alipay.sdk.app.statistic.a.a("net", e2);
            b();
            iVar = a4;
        } catch (Throwable th) {
            b();
        }
        if (iVar == null) {
            iVar = i.a(i.FAILED.h);
        }
        return h.a(iVar.h, iVar.i, "");
    }

    private String a(com.alipay.sdk.protocol.b bVar) {
        String[] strArr = bVar.b;
        Intent intent = new Intent(this.b, H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", strArr[0]);
        if (strArr.length == 2) {
            bundle.putString("cookie", strArr[1]);
        }
        intent.putExtras(bundle);
        this.b.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException e) {
                return h.a();
            }
        }
        String str = h.a;
        if (TextUtils.isEmpty(str)) {
            return h.a();
        }
        return str;
    }
}
