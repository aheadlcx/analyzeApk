package com.sprite.ads.third;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.ali.auth.third.login.LoginConstants;
import com.qq.e.comm.constants.Constants.KEYS;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.ThirdApiItem;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.internal.utils.c;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.reporter.Reporter;
import com.sprite.ads.third.ThirdApiAdData.ActionType;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.f;
import okhttp3.p.a;
import okhttp3.z;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThirdApiAdLoader extends ThirdAdLoader {
    List<NativeAdData> nativeAdDataList = new ArrayList();

    public ThirdApiAdLoader(Context context, AdItem adItem) {
        super(context, adItem);
    }

    private void errorProcess() {
        setIsInit(true);
        adLoadFailed();
    }

    private List<String> getListString(String str) {
        List<String> arrayList = new ArrayList();
        arrayList.add(str);
        return arrayList;
    }

    private String getReportClickUrl(String str, String str2, HashMap<String, String> hashMap) {
        StringBuffer stringBuffer = new StringBuffer();
        String parserParams = parserParams(str2, hashMap);
        if (str != null && str.indexOf("?") != -1) {
            stringBuffer.append(str).append(parserParams);
        } else if (str != null) {
            stringBuffer.append(str).append("?").append(parserParams);
        }
        return stringBuffer.toString();
    }

    private String getReportUrl(String str, String str2, HashMap<String, String> hashMap) {
        StringBuffer stringBuffer = new StringBuffer();
        String parserParams = parserParams(str2, hashMap);
        if (str != null && str.indexOf("?") != -1) {
            stringBuffer.append(str).append(parserParams);
        } else if (str != null) {
            stringBuffer.append(str).append("?").append(parserParams);
        }
        return stringBuffer.toString();
    }

    private z getRequestBody(String str) {
        a aVar = new a();
        String str2 = "";
        try {
            String[] split = str.split("\\?")[1].split("&");
            for (String str22 : split) {
                try {
                    String[] split2 = str22.split(LoginConstants.EQUAL);
                    aVar.a(split2[0], split2[1]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return aVar.a();
        } catch (Exception e2) {
            e2.printStackTrace();
            return aVar.a();
        }
    }

    private String getUrl(String str) {
        try {
            return str.split("\\?")[0];
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String parserParams(String str, HashMap<String, String> hashMap) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            String[] split = str.split("\\[&]");
            if (split != null) {
                for (int i = 0; i < split.length; i++) {
                    String[] split2 = split[i].split(LoginConstants.EQUAL);
                    if (TextUtils.isEmpty((CharSequence) hashMap.get(split2[1]))) {
                        stringBuffer.append(split2[0]);
                        stringBuffer.append(LoginConstants.EQUAL);
                        stringBuffer.append(URLEncoder.encode(split2[1], "utf-8"));
                    } else {
                        stringBuffer.append(split2[0]);
                        stringBuffer.append(LoginConstants.EQUAL);
                        stringBuffer.append(URLEncoder.encode((String) hashMap.get(split2[1]), "utf-8"));
                    }
                    if (i != split.length - 1) {
                        stringBuffer.append("&");
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public Reporter getAdReporter(NativeAdData nativeAdData, int i) {
        Reporter thirdApiReporter = new ThirdApiReporter((ThirdApiAdData) nativeAdData);
        thirdApiReporter.setDownwarn(i);
        return thirdApiReporter;
    }

    public String getString(JSONObject jSONObject, String str) {
        return c.a(jSONObject, str);
    }

    public void loadAd(Context context, AdItem adItem) {
        final ThirdApiItem thirdApiItem = (ThirdApiItem) adItem;
        f anonymousClass1 = new f() {
            public void onFailure(e eVar, IOException iOException) {
                ADLog.d("loadGdtApiAd onFailure");
                ThirdApiAdLoader.this.errorProcess();
            }

            public void onResponse(e eVar, aa aaVar) {
                if (aaVar.d()) {
                    Log.d("jihongwen", "response Success");
                }
                String f = aaVar.h().f();
                try {
                    Map a = c.a(f);
                    String str = (String) a.get(KEYS.RET);
                    if (a == null || !"0".equals(str)) {
                        ADLog.d("loadGdtApiAd onFailure");
                        ADLog.d("ret 不等于 0  ret:" + str);
                        ThirdApiAdLoader.this.errorProcess();
                        return;
                    }
                    try {
                        str = c.a(thirdApiItem.get_match_root.replace("[]", ""), new JSONObject(f));
                        if (str == null) {
                            ThirdApiAdLoader.this.errorProcess();
                            return;
                        }
                        JSONArray jSONArray = new JSONArray(str);
                        ThirdApiAdLoader.this.nativeAdDataList.clear();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            ThirdApiAdData thirdApiAdData = new ThirdApiAdData();
                            HashMap a2 = c.a(jSONArray.getJSONObject(i));
                            thirdApiAdData.title = (String) a2.get(thirdApiItem.get_match_title);
                            thirdApiAdData.desc = (String) a2.get(thirdApiItem.get_match_desc);
                            thirdApiAdData.pic = (String) a2.get(thirdApiItem.get_match_img);
                            thirdApiAdData.gdt_targetid = (String) a2.get(ThirdApiReporter.GDT_REPORT_URL_PARAM_TARGETID);
                            thirdApiAdData.gdt_crt_type = (String) a2.get("crt_type");
                            thirdApiAdData.gdt_act_type = (String) a2.get("acttype");
                            thirdApiAdData.reportTrackingUrl = ThirdApiAdLoader.this.getListString(ThirdApiAdLoader.this.getReportUrl(thirdApiItem.report_url, thirdApiItem.report_param, a2));
                            thirdApiAdData.clickTrackingUrl = ThirdApiAdLoader.this.getListString(ThirdApiAdLoader.this.getReportClickUrl(thirdApiItem.report_click, thirdApiItem.report_click_param, a2));
                            thirdApiAdData.screenRatio = thirdApiItem.screen_ratio;
                            thirdApiAdData.action_type = ActionType.DOWNLOAD;
                            ThirdApiAdLoader.this.nativeAdDataList.add(thirdApiAdData);
                        }
                        ThirdApiAdLoader.this.addThirdToPool(ThirdApiAdLoader.this.nativeAdDataList);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ThirdApiAdLoader.this.errorProcess();
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    ADLog.d("loadGdtApiAd onFailure");
                    ADLog.d("third api json 解析错误");
                    ThirdApiAdLoader.this.errorProcess();
                }
            }
        };
        if ("get".equals(thirdApiItem.action)) {
            com.sprite.ads.internal.net.a.a(thirdApiItem.get_url, anonymousClass1);
        } else if ("post".equals(thirdApiItem.action)) {
            com.sprite.ads.internal.net.a.a(getUrl(thirdApiItem.get_url), getRequestBody(thirdApiItem.get_url), anonymousClass1);
        }
    }

    public void release() {
    }
}
