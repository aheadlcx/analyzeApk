package com.sprite.ads.third.sixroom;

import android.content.Context;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.LiveItem;
import com.sprite.ads.internal.bean.data.SixRoomItem;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.internal.net.a;
import com.sprite.ads.internal.utils.e;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.reporter.Reporter;
import com.sprite.ads.third.ThirdAdLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.aa;
import okhttp3.f;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SixRoomAdLoader extends ThirdAdLoader implements SixRoomConstants {
    private static int currPage = 1;
    private static int totalPage = 3;
    List<NativeAdData> nativeAdDataList = new ArrayList();
    private int page = 0;

    public SixRoomAdLoader(Context context, AdItem adItem) {
        super(context, adItem);
    }

    private void errorProcess() {
        this.page = 1;
        setIsInit(true);
        adLoadFailed();
    }

    private String getLiveListRequestUrl(int i, int i2) {
        String a = e.a();
        return "http://v.6.cn/coop/pub/getLiveList.php?act=livelist&coopsrc=baisi&tm=" + a + "&flag=" + e.a(SixRoomConstants.SR_CoopSrc + a + SixRoomConstants.SR_LiveKey) + "&limit=" + i + "&page=" + i2 + "&onlypc=0";
    }

    public Reporter getAdReporter(NativeAdData nativeAdData, int i) {
        return null;
    }

    public void loadAd(Context context, AdItem adItem) {
        final SixRoomItem sixRoomItem = (SixRoomItem) adItem;
        f anonymousClass1 = new f() {
            public void onFailure(okhttp3.e eVar, IOException iOException) {
                SixRoomAdLoader.this.errorProcess();
            }

            public void onResponse(okhttp3.e eVar, aa aaVar) {
                int i = 0;
                try {
                    JSONObject jSONObject = new JSONObject(aaVar.h().f());
                    JSONArray optJSONArray = jSONObject.optJSONArray(CommonStrs.TYPE_ALL_ROOMLIST);
                    if (optJSONArray == null) {
                        SixRoomAdLoader.this.errorProcess();
                        return;
                    }
                    SixRoomAdLoader.totalPage = jSONObject.optInt("totalpage");
                    if (optJSONArray.length() == 0) {
                        SixRoomAdLoader.this.errorProcess();
                        return;
                    }
                    int i2;
                    SixRoomAdLoader.this.nativeAdDataList.clear();
                    List arrayList = new ArrayList();
                    for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                        LiveItem liveItem = new LiveItem();
                        liveItem.rid = optJSONObject.optInt("rid");
                        liveItem.uid = optJSONObject.optInt(HistoryOpenHelper.COLUMN_UID);
                        liveItem.username = optJSONObject.optString(HistoryOpenHelper.COLUMN_USERNAME);
                        liveItem.sex = optJSONObject.optInt("sex");
                        liveItem.pospic = optJSONObject.optString("pospic");
                        liveItem.pic = optJSONObject.optString("pic");
                        liveItem.count = optJSONObject.optInt("count");
                        liveItem.screenRatio = sixRoomItem.screen_ratio;
                        arrayList.add(liveItem);
                    }
                    i2 = sixRoomItem.item_count;
                    while (i < arrayList.size() / i2) {
                        int i3 = i * i2;
                        List subList = arrayList.subList(i3, i3 + i2);
                        SixRoomAdData sixRoomAdData = new SixRoomAdData();
                        sixRoomAdData.setLiveItems(subList);
                        SixRoomAdLoader.this.nativeAdDataList.add(sixRoomAdData);
                        i++;
                    }
                    if (SixRoomAdLoader.this.nativeAdDataList.size() == 0) {
                        SixRoomAdLoader.this.errorProcess();
                    } else {
                        SixRoomAdLoader.this.addThirdToPool(SixRoomAdLoader.this.nativeAdDataList);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    ADLog.d("load sixRoom onFailure");
                    SixRoomAdLoader.this.errorProcess();
                }
            }
        };
        if (this.page == 0) {
            this.page = currPage;
        }
        a.a(getLiveListRequestUrl(sixRoomItem.item_limit, this.page), anonymousClass1);
        currPage++;
        if (currPage > totalPage) {
            currPage = 1;
        }
    }

    public void release() {
    }
}
