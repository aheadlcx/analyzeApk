package com.sprite.ads.internal.bean;

import android.text.TextUtils;
import com.sprite.ads.DataSourceType;
import com.sprite.ads.internal.bean.data.ADConfig;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.BookItem;
import com.sprite.ads.internal.bean.data.LinkActiveItem;
import com.sprite.ads.internal.bean.data.SelfItem;
import com.sprite.ads.internal.bean.data.SixRoomItem;
import com.sprite.ads.internal.bean.data.ThirdApiItem;
import com.sprite.ads.internal.bean.data.ThirdApiItem2;
import com.sprite.ads.internal.bean.data.ThirdSdkItem;
import com.sprite.ads.internal.imageCache.AdImageLoader;
import com.umeng.analytics.pro.x;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class ResponseBody implements JsonInterface {
    public ADConfig config;
    public Map<String, AdItem> data;
    public List<String> preload;
    public String type = "";

    public ResponseBody(JSONObject jSONObject) {
        jsonToObject(jSONObject);
    }

    private void parsePreload(JSONObject jSONObject) {
        if (jSONObject.has("preload")) {
            JSONArray jSONArray = jSONObject.getJSONArray("preload");
            this.preload = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                this.preload.add(jSONArray.get(i).toString());
                AdImageLoader.getInstance().preCacheImage((String) this.preload.get(i));
            }
        }
    }

    public void jsonToObject(JSONObject jSONObject) {
        parsePreload(jSONObject);
        parseConfig(jSONObject);
        parseType(jSONObject);
        parseData(jSONObject.getJSONObject("data"));
    }

    public ADConfig parseConfig(JSONObject jSONObject) {
        if (!jSONObject.has("config")) {
            return this.config;
        }
        this.config = new ADConfig(jSONObject.getJSONObject("config"));
        return this.config;
    }

    public Map<String, AdItem> parseData(JSONObject jSONObject) {
        this.data = new HashMap();
        JSONArray names = jSONObject.names();
        if (names == null) {
            return this.data;
        }
        for (int i = 0; i < names.length(); i++) {
            String string = names.getString(i);
            if (string.startsWith("self.sixroomslive")) {
                SixRoomItem sixRoomItem = new SixRoomItem(string, jSONObject.getJSONObject(string));
                sixRoomItem.setDataSourceType(DataSourceType.getDataSourceType(string));
                this.data.put(string, sixRoomItem);
            } else if (string.startsWith("self.huaxibook")) {
                BookItem bookItem = new BookItem(string, jSONObject.getJSONObject(string));
                bookItem.setDataSourceType(DataSourceType.getDataSourceType(string));
                this.data.put(string, bookItem);
            } else if (string.startsWith("self")) {
                AdItem selfItem = new SelfItem(string, jSONObject.getJSONObject(string));
                selfItem.setDataSourceType(DataSourceType.getDataSourceType(string));
                this.data.put(string, selfItem);
            } else if (string.startsWith("api.linkactive")) {
                LinkActiveItem linkActiveItem = new LinkActiveItem(string, jSONObject.getJSONObject(string));
                linkActiveItem.setDataSourceType(DataSourceType.getDataSourceType(string));
                this.data.put(string, linkActiveItem);
            } else if (string.startsWith("api")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(string);
                if (TextUtils.isEmpty(jSONObject2.getJSONObject("extra").optString(x.k))) {
                    ThirdApiItem thirdApiItem = new ThirdApiItem(string, jSONObject2);
                    thirdApiItem.setDataSourceType(DataSourceType.getDataSourceType(string));
                    this.data.put(string, thirdApiItem);
                } else {
                    ThirdApiItem2 thirdApiItem2 = new ThirdApiItem2(string, jSONObject2);
                    thirdApiItem2.setDataSourceType(DataSourceType.getDataSourceType(string));
                    this.data.put(string, thirdApiItem2);
                }
            } else if (string.startsWith("sdk")) {
                ThirdSdkItem thirdSdkItem = new ThirdSdkItem(string, jSONObject.getJSONObject(string));
                thirdSdkItem.postId = string;
                thirdSdkItem.setDataSourceType(DataSourceType.getDataSourceType(string));
                this.data.put(string, thirdSdkItem);
            }
        }
        return this.data;
    }

    public String parseType(JSONObject jSONObject) {
        if (!jSONObject.has("type")) {
            return this.type;
        }
        this.type = jSONObject.getString("type");
        return this.type;
    }
}
