package com.sprite.ads.third.linkactive;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.ali.auth.third.login.LoginConstants;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.LinkActiveAdItem;
import com.sprite.ads.internal.bean.data.LinkActiveItem;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.internal.utils.c;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.reporter.Reporter;
import com.sprite.ads.third.ThirdAdLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.f;
import okhttp3.p.a;
import okhttp3.z;
import org.json.JSONArray;
import org.json.JSONObject;

public class LinkActiveAdLoader extends ThirdAdLoader {
    List<NativeAdData> nativeAdDataList = new ArrayList();

    public LinkActiveAdLoader(Context context, AdItem adItem) {
        super(context, adItem);
    }

    private void errorProcess() {
        setIsInit(true);
        adLoadFailed();
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

    public void filterAdItem(Context context, List<LinkActiveAdItem> list) {
        int i = 0;
        List installedPackages = context.getPackageManager().getInstalledPackages(0);
        List arrayList = new ArrayList();
        if (installedPackages != null) {
            for (int i2 = 0; i2 < installedPackages.size(); i2++) {
                arrayList.add(((PackageInfo) installedPackages.get(i2)).packageName);
            }
        }
        while (i < list.size()) {
            LinkActiveAdItem linkActiveAdItem = (LinkActiveAdItem) list.get(i);
            if (linkActiveAdItem.check_install_status == 1 && !arrayList.contains(linkActiveAdItem.pkg_name)) {
                list.remove(linkActiveAdItem);
                i--;
                ADLog.d("LinkActiveLoader 应用未安装：" + linkActiveAdItem.title + " 包名：" + linkActiveAdItem.pkg_name);
            }
            i++;
        }
    }

    public Reporter getAdReporter(NativeAdData nativeAdData, int i) {
        Reporter linkActiveReporter = new LinkActiveReporter((LinkActiveAdData) nativeAdData);
        linkActiveReporter.setDownwarn(i);
        return linkActiveReporter;
    }

    public void loadAd(final Context context, AdItem adItem) {
        final LinkActiveItem linkActiveItem = (LinkActiveItem) adItem;
        f anonymousClass1 = new f() {
            public void onFailure(e eVar, IOException iOException) {
                LinkActiveAdLoader.this.errorProcess();
            }

            public void onResponse(e eVar, aa aaVar) {
                Object f = aaVar.h().f();
                if (TextUtils.isEmpty(f)) {
                    LinkActiveAdLoader.this.errorProcess();
                    return;
                }
                try {
                    JSONArray jSONArray = new JSONArray(f);
                    if (jSONArray == null || jSONArray.length() == 0) {
                        LinkActiveAdLoader.this.errorProcess();
                        return;
                    }
                    LinkActiveAdLoader.this.nativeAdDataList.clear();
                    List<LinkActiveAdItem> arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i);
                        LinkActiveAdItem linkActiveAdItem = new LinkActiveAdItem();
                        linkActiveAdItem.active_device_type = optJSONObject.optString("active_device_type");
                        linkActiveAdItem.ad_code = optJSONObject.optString("ad_code");
                        linkActiveAdItem.title = c.a("ad_content.title", optJSONObject);
                        linkActiveAdItem.ad_content_id = optJSONObject.optInt("ad_content_id");
                        linkActiveAdItem.ad_position = optJSONObject.optString("ad_position");
                        linkActiveAdItem.apk_url = optJSONObject.optString("apk_url");
                        linkActiveAdItem.check_install_status = optJSONObject.optInt("check_install_status");
                        linkActiveAdItem.h5_url = optJSONObject.optString("h5_url");
                        linkActiveAdItem.img_url = optJSONObject.optString("img_url");
                        linkActiveAdItem.pkg_name = optJSONObject.optString("pkg_name");
                        linkActiveAdItem.request_id = optJSONObject.optString("request_id");
                        linkActiveAdItem.uri_scheme = optJSONObject.optString("uri_scheme");
                        linkActiveAdItem.record_status_url = linkActiveItem.record_status_url + "&ad_code=" + linkActiveAdItem.ad_code + "&ad_content_id=" + linkActiveAdItem.ad_content_id + "&active_device_type=" + linkActiveAdItem.active_device_type + "&request_id=" + linkActiveAdItem.request_id;
                        arrayList.add(linkActiveAdItem);
                    }
                    LinkActiveAdLoader.this.filterAdItem(context, arrayList);
                    for (LinkActiveAdItem linkActiveAdData : arrayList) {
                        LinkActiveAdLoader.this.nativeAdDataList.add(new LinkActiveAdData(linkActiveAdData));
                    }
                    LinkActiveAdLoader.this.addThirdToPool(LinkActiveAdLoader.this.nativeAdDataList);
                } catch (Exception e) {
                    e.printStackTrace();
                    LinkActiveAdLoader.this.errorProcess();
                }
            }
        };
        if ("get".equals(linkActiveItem.action)) {
            com.sprite.ads.internal.net.a.a(linkActiveItem.get_url, anonymousClass1);
        } else if ("post".equals(linkActiveItem.action)) {
            com.sprite.ads.internal.net.a.a(linkActiveItem.get_url, getRequestBody(linkActiveItem.request_post_param), anonymousClass1);
        }
    }

    public void release() {
    }
}
