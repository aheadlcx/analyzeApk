package com.sprite.ads.third.book;

import android.content.Context;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.BookItem;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.internal.net.a;
import com.sprite.ads.internal.utils.c;
import com.sprite.ads.nati.NativeAdData;
import com.sprite.ads.nati.reporter.Reporter;
import com.sprite.ads.third.ThirdAdLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import okhttp3.aa;
import okhttp3.e;
import okhttp3.f;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BookAdLoader extends ThirdAdLoader {
    List<NativeAdData> nativeAdDataList = new ArrayList();

    public BookAdLoader(Context context, AdItem adItem) {
        super(context, adItem);
    }

    private void errorProcess() {
        setIsInit(true);
        adLoadFailed();
    }

    public Reporter getAdReporter(NativeAdData nativeAdData, int i) {
        return null;
    }

    public void loadAd(Context context, AdItem adItem) {
        BookItem bookItem = (BookItem) adItem;
        f anonymousClass1 = new f() {
            public void onFailure(e eVar, IOException iOException) {
                ADLog.d("BookAdLoader onFailure");
                BookAdLoader.this.errorProcess();
            }

            public void onResponse(e eVar, aa aaVar) {
                try {
                    try {
                        String a = c.a("body.data", new JSONObject(aaVar.h().f()));
                        if (a == null) {
                            BookAdLoader.this.errorProcess();
                            return;
                        }
                        JSONArray jSONArray = new JSONArray(a);
                        if (jSONArray == null || jSONArray.length() == 0) {
                            BookAdLoader.this.errorProcess();
                            return;
                        }
                        BookAdLoader.this.nativeAdDataList.clear();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            BookAdData bookAdData = new BookAdData();
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            bookAdData.name = jSONObject.optString("name");
                            bookAdData.book_author = jSONObject.optString("book_author");
                            bookAdData.image = jSONObject.optString(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY);
                            bookAdData.intro = jSONObject.optString("intro");
                            bookAdData.book_id = jSONObject.optInt("book_id");
                            bookAdData.id = jSONObject.optInt("id");
                            BookAdLoader.this.nativeAdDataList.add(bookAdData);
                        }
                        BookAdLoader.this.addThirdToPool(BookAdLoader.this.nativeAdDataList);
                    } catch (Exception e) {
                        e.printStackTrace();
                        BookAdLoader.this.errorProcess();
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    BookAdLoader.this.errorProcess();
                }
            }
        };
        if (bookItem.isdefault == 0) {
            a.a("http://dspsdk.spriteapp.com/getbook?source=1", anonymousClass1);
        } else {
            a.a("http://dspsdk.spriteapp.com/getres?ad_id=" + bookItem.ad_id, anonymousClass1);
        }
    }

    public void release() {
    }
}
