package com.sprite.ads.third;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.ali.auth.third.login.LoginConstants;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.ThirdApiItem2;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.internal.utils.c;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.reporter.Reporter;
import com.sprite.ads.third.ThirdApiAdData.ActionType;
import com.sprite.ads.third.ThirdApiAdData.ResType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.f;
import okhttp3.p.a;
import okhttp3.z;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ThirdApiAdLoader2 extends ThirdAdLoader {
    List<NativeAdData> nativeAdDataList = new ArrayList();

    public ThirdApiAdLoader2(Context context, AdItem adItem) {
        super(context, adItem);
    }

    private void errorProcess() {
        setIsInit(true);
        adLoadFailed();
    }

    private List<String> getJsonStringList(JSONObject jSONObject, String str) {
        int i = 0;
        if (!str.contains(".")) {
            return getListString(jSONObject, str);
        }
        String[] split = str.split("\\.");
        if (split == null || split.length == 0) {
            return null;
        }
        String str2 = split[0];
        JSONArray names = jSONObject.names();
        while (i < names.length()) {
            String string = names.getString(i);
            if (str2.equals(string)) {
                return getJsonStringList(jSONObject.getJSONObject(string), str.substring(str.indexOf(".") + 1, str.length()));
            }
            i++;
        }
        return null;
    }

    private List<String> getListString(JSONObject jSONObject, String str) {
        if (str.contains("[]")) {
            String replace = str.replace("[]", "");
            if (!jSONObject.has(replace)) {
                return null;
            }
            List<String> arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray(replace);
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
            return arrayList;
        }
        CharSequence optString = jSONObject.optString(str);
        if (TextUtils.isEmpty(optString)) {
            return null;
        }
        List<String> arrayList2 = new ArrayList();
        arrayList2.add(optString);
        return arrayList2;
    }

    private z getRequestBody(String str) {
        a aVar = new a();
        String[] split = str.split("&");
        for (String split2 : split) {
            try {
                String[] split3 = split2.split(LoginConstants.EQUAL);
                aVar.a(split3[0], split3[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return aVar.a();
    }

    public Reporter getAdReporter(NativeAdData nativeAdData, int i) {
        Reporter thirdApiReporter2 = new ThirdApiReporter2((ThirdApiAdData) nativeAdData);
        thirdApiReporter2.setDownwarn(i);
        return thirdApiReporter2;
    }

    public String getString(JSONObject jSONObject, String str) {
        return c.a(jSONObject, str);
    }

    public void loadAd(Context context, AdItem adItem) {
        final ThirdApiItem2 thirdApiItem2 = (ThirdApiItem2) adItem;
        f anonymousClass1 = new f() {
            public void onFailure(e eVar, IOException iOException) {
                ADLog.d("loadGdtApiAd onFailure");
                ThirdApiAdLoader2.this.errorProcess();
            }

            public void onResponse(e eVar, aa aaVar) {
                if (aaVar.d()) {
                    Log.d("jihongwen", "response Success");
                }
                try {
                    try {
                        String a = c.a(thirdApiItem2.get_match_root.replace("[]", ""), new JSONObject(aaVar.h().f()));
                        if (a == null) {
                            ThirdApiAdLoader2.this.errorProcess();
                            return;
                        }
                        JSONArray jSONArray = new JSONArray(a);
                        if (jSONArray == null || jSONArray.length() == 0) {
                            ThirdApiAdLoader2.this.errorProcess();
                            return;
                        }
                        ThirdApiAdLoader2.this.nativeAdDataList.clear();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            ThirdApiAdData thirdApiAdData = new ThirdApiAdData();
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            thirdApiAdData.title = ThirdApiAdLoader2.this.getString(jSONObject, thirdApiItem2.get_match_title);
                            thirdApiAdData.desc = ThirdApiAdLoader2.this.getString(jSONObject, thirdApiItem2.get_match_desc);
                            thirdApiAdData.pic = ThirdApiAdLoader2.this.getString(jSONObject, thirdApiItem2.get_match_img);
                            thirdApiAdData.reportTrackingUrl = ThirdApiAdLoader2.this.getJsonStringList(jSONObject, thirdApiItem2.report_tracking_url);
                            thirdApiAdData.clickTrackingUrl = ThirdApiAdLoader2.this.getJsonStringList(jSONObject, thirdApiItem2.click_tracking_url);
                            thirdApiAdData.clickThroughUrl = ThirdApiAdLoader2.this.getJsonStringList(jSONObject, thirdApiItem2.click_through_url);
                            thirdApiAdData.screenRatio = thirdApiItem2.screen_ratio;
                            thirdApiAdData.downloadStartUrl = ThirdApiAdLoader2.this.getJsonStringList(jSONObject, thirdApiItem2.download_start_url);
                            thirdApiAdData.downloadCompleteUrl = ThirdApiAdLoader2.this.getJsonStringList(jSONObject, thirdApiItem2.download_complete_url);
                            thirdApiAdData.downloadInstalledUrl = ThirdApiAdLoader2.this.getJsonStringList(jSONObject, thirdApiItem2.download_installed_url);
                            if (!TextUtils.isEmpty(thirdApiItem2.res_type_property)) {
                                String string = ThirdApiAdLoader2.this.getString(jSONObject, thirdApiItem2.res_type_property);
                                if (string == null || !string.equals(thirdApiItem2.res_type_video_value)) {
                                    thirdApiAdData.res_type = ResType.IMAGE;
                                } else {
                                    thirdApiAdData.res_type = ResType.VIDEO;
                                }
                            }
                            if (!TextUtils.isEmpty(thirdApiItem2.action_type_property)) {
                                String string2 = ThirdApiAdLoader2.this.getString(jSONObject, thirdApiItem2.action_type_property);
                                if (string2 == null || !string2.equals(thirdApiItem2.action_type_download_value)) {
                                    thirdApiAdData.action_type = ActionType.WEB;
                                } else {
                                    thirdApiAdData.action_type = ActionType.DOWNLOAD;
                                }
                            }
                            ThirdApiAdLoader2.this.nativeAdDataList.add(thirdApiAdData);
                        }
                        ThirdApiAdLoader2.this.addThirdToPool(ThirdApiAdLoader2.this.nativeAdDataList);
                    } catch (Exception e) {
                        e.printStackTrace();
                        ThirdApiAdLoader2.this.errorProcess();
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    ADLog.d("load thirdApi2 onFailure");
                    ThirdApiAdLoader2.this.errorProcess();
                }
            }
        };
        if ("get".equals(thirdApiItem2.action)) {
            com.sprite.ads.internal.net.a.a(thirdApiItem2.get_url, anonymousClass1);
        } else if ("post".equals(thirdApiItem2.action)) {
            com.sprite.ads.internal.net.a.a(thirdApiItem2.get_url, getRequestBody(thirdApiItem2.request_post_param), anonymousClass1);
        }
    }

    public void release() {
    }
}
