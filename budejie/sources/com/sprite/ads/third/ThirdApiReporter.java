package com.sprite.ads.third;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.sprite.ads.internal.a.b;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.SelfItem;
import com.sprite.ads.internal.net.a;
import com.sprite.ads.internal.net.c;
import com.sprite.ads.nati.reporter.ConfirmDownloadReporter;
import java.io.IOException;
import java.util.List;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.f;
import org.json.JSONException;
import org.json.JSONObject;

public class ThirdApiReporter extends ConfirmDownloadReporter {
    public static final String GDT_EXTRA_CLICKID = "gdt_extra_clickid";
    public static final String GDT_EXTRA_TARGETID = "gdt_extra_targetid";
    public static String GDT_REPORT_URL = ("http://c.gdt.qq.com/gdt_trace_a.fcg?targettype=" + GDT_TARGET_TYPE);
    public static final String GDT_REPORT_URL_PARAM_CLICKID = "clickid";
    public static final String GDT_REPORT_URL_PARAM_TARGETID = "targetid";
    public static String GDT_TARGET_TYPE = "6";
    private String gdt_actionid = "5";
    private String gdt_clickid = "";
    private String gdt_targetid = "";
    Handler mHandler = new Handler(Looper.getMainLooper());
    ThirdApiAdData nativeAdData;

    public ThirdApiReporter(ThirdApiAdData thirdApiAdData) {
        this.nativeAdData = thirdApiAdData;
    }

    public static void downloadEndReport(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            a.a(GDT_REPORT_URL + "&actionid=7&targetid=" + str + "&clickid=" + str2, new c());
        }
    }

    public static void downloadStartReport(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            a.a(GDT_REPORT_URL + "&actionid=5&targetid=" + str + "&clickid=" + str2, new c());
        }
    }

    public static void installReport(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            a.a(GDT_REPORT_URL + "&actionid=6&targetid=" + str + "&clickid=" + str2, new c());
        }
    }

    public void onClicked(final View view) {
        String str = "";
        List clickTrackingUrl = this.nativeAdData.getClickTrackingUrl();
        if (clickTrackingUrl != null && clickTrackingUrl.size() > 0) {
            str = (String) clickTrackingUrl.get(0);
            if ("1".equals(this.nativeAdData.getGdt_act_type())) {
                a.a(str, new f() {
                    public void onFailure(e eVar, IOException iOException) {
                    }

                    public void onResponse(e eVar, aa aaVar) {
                        try {
                            JSONObject jSONObject = new JSONObject(aaVar.h().f()).getJSONObject("data");
                            final AdItem adItem = ThirdApiReporter.this.nativeAdData.getAdItem(jSONObject.get(ThirdApiReporter.GDT_REPORT_URL_PARAM_CLICKID).toString(), jSONObject.get("dstlink").toString());
                            ThirdApiReporter.this.mHandler.post(new Runnable() {
                                public void run() {
                                    com.sprite.ads.internal.a.c.a().a((Activity) view.getContext());
                                    com.sprite.ads.internal.a.c.a().b(null, adItem, ThirdApiReporter.this.downwarn);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return;
            }
            b bVar = new b();
            AdItem selfItem = new SelfItem();
            selfItem.setUrl(str);
            bVar.b(view.getContext(), selfItem);
        }
    }

    public void onExposured(View view) {
        List reportTrackingUrl = this.nativeAdData.getReportTrackingUrl();
        if (reportTrackingUrl != null && reportTrackingUrl.size() > 0) {
            a.a((String) reportTrackingUrl.get(0), new c());
        }
    }

    public void onPlay(View view) {
    }
}
