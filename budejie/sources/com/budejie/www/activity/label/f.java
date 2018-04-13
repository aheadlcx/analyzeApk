package com.budejie.www.activity.label;

import android.text.TextUtils;
import com.alipay.sdk.util.j;
import com.budejie.www.bean.SearchHotItem;
import com.budejie.www.bean.Topics;
import com.umeng.analytics.pro.x;
import java.util.ArrayList;
import java.util.List;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class f {
    public static LabelBean a(String str) {
        LabelBean labelBean = null;
        if (!(TextUtils.isEmpty(str) || "[]".equals(str))) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("info")) {
                    labelBean = a(jSONObject.getJSONObject("info"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return labelBean;
    }

    public static b b(String str) {
        JSONException e;
        if (TextUtils.isEmpty(str) || "[]".equals(str)) {
            return null;
        }
        b bVar;
        try {
            JSONObject jSONObject = new JSONObject(str);
            bVar = new b();
            try {
                if (jSONObject.has("next_page")) {
                    bVar.b = jSONObject.getInt("next_page");
                }
                if (jSONObject.has("has_more")) {
                    bVar.c = jSONObject.getInt("has_more");
                }
                if (!jSONObject.has("list") || "[]".equals(jSONObject.getString("list"))) {
                    return bVar;
                }
                List arrayList = new ArrayList();
                JSONArray jSONArray = jSONObject.getJSONArray("list");
                for (int i = 0; i < jSONArray.length(); i++) {
                    LabelBean a = a(jSONArray.getJSONObject(i));
                    if (a != null) {
                        arrayList.add(a);
                    }
                }
                bVar.a = arrayList;
                return bVar;
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                return bVar;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            bVar = null;
            e = jSONException;
            e.printStackTrace();
            return bVar;
        }
    }

    public static List<LabelBean> c(String str) {
        JSONException e;
        int i = 0;
        if (TextUtils.isEmpty(str) || "[]".equals(str)) {
            return null;
        }
        List<LabelBean> arrayList;
        try {
            arrayList = new ArrayList();
            try {
                if (str.startsWith("{")) {
                    JSONObject jSONObject = new JSONObject(str);
                    if (!jSONObject.has("list") || "[]".equals(jSONObject.getString("list"))) {
                        return arrayList;
                    }
                    JSONArray jSONArray = jSONObject.getJSONArray("list");
                    while (i < jSONArray.length()) {
                        LabelBean a = a(jSONArray.getJSONObject(i));
                        if (a != null) {
                            arrayList.add(a);
                        }
                        i++;
                    }
                    return arrayList;
                }
                JSONArray jSONArray2 = new JSONArray(str);
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    LabelBean a2 = a(jSONArray2.getJSONObject(i2));
                    if (a2 != null) {
                        arrayList.add(a2);
                    }
                }
                return arrayList;
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                return arrayList;
            }
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            arrayList = null;
            e = jSONException;
            e.printStackTrace();
            return arrayList;
        }
    }

    private static LabelBean a(JSONObject jSONObject) {
        LabelBean labelBean = null;
        if (jSONObject != null) {
            labelBean = new LabelBean();
            try {
                if (jSONObject.has("theme_id")) {
                    labelBean.setTheme_id(jSONObject.getInt("theme_id"));
                }
                if (jSONObject.has("theme_name")) {
                    labelBean.setTheme_name(jSONObject.getString("theme_name"));
                }
                if (jSONObject.has("theme_type")) {
                    labelBean.setTheme_type(jSONObject.getString("theme_type"));
                }
                if (jSONObject.has("content_type")) {
                    labelBean.setContent_type(jSONObject.getString("content_type"));
                }
                if (jSONObject.has("image_detail")) {
                    labelBean.setImage_detail(jSONObject.getString("image_detail"));
                }
                if (jSONObject.has("image_list")) {
                    labelBean.setImage_list(jSONObject.getString("image_list"));
                }
                if (jSONObject.has("info")) {
                    labelBean.setInfo(jSONObject.getString("info"));
                }
                if (jSONObject.has("info_url")) {
                    labelBean.setInfo_url(jSONObject.getString("info_url"));
                }
                if (jSONObject.has("info_more")) {
                    labelBean.setInfo_more(jSONObject.getString("info_more"));
                }
                if (jSONObject.has("rule")) {
                    labelBean.setRule(jSONObject.getString("rule"));
                }
                if (jSONObject.has("prize")) {
                    labelBean.setPrize(jSONObject.getString("prize"));
                }
                if (jSONObject.has("hot_post")) {
                    labelBean.setHot_post(jSONObject.getString("hot_post"));
                }
                if (jSONObject.has(x.W)) {
                    labelBean.setStart_time(jSONObject.getString(x.W));
                }
                if (jSONObject.has(x.X)) {
                    labelBean.setEnd_time(jSONObject.getString(x.X));
                }
                if (jSONObject.has("total_users")) {
                    labelBean.setTotal_users(jSONObject.getString("total_users"));
                }
                if (jSONObject.has("square_show")) {
                    labelBean.setSquare_show(jSONObject.getString("square_show"));
                }
                if (jSONObject.has("square_number")) {
                    labelBean.setSquare_number(jSONObject.getString("square_number"));
                }
                if (jSONObject.has("ctime")) {
                    labelBean.setCtime(jSONObject.getString("ctime"));
                }
                if (jSONObject.has(j.c)) {
                    labelBean.setResult(jSONObject.getString(j.c));
                }
                if (jSONObject.has("status")) {
                    labelBean.setStatus(jSONObject.getInt("status"));
                }
                if (jSONObject.has("share")) {
                    labelBean.setShare(jSONObject.getString("share"));
                }
                if (jSONObject.has("sub_number")) {
                    labelBean.setSub_number(jSONObject.getString("sub_number"));
                }
                if (jSONObject.has("is_sub")) {
                    labelBean.setIs_sub(jSONObject.getString("is_sub"));
                }
                if (jSONObject.has("is_default")) {
                    labelBean.setIs_default(jSONObject.getString("is_default"));
                }
                if (jSONObject.has("post_number")) {
                    labelBean.setPost_number(jSONObject.getString("post_number"));
                }
                if (jSONObject.has("topics")) {
                    JSONArray jSONArray = jSONObject.getJSONArray("topics");
                    List arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        Topics topics = new Topics();
                        topics.setBody(jSONObject2.getString("text"));
                        topics.setImage(jSONObject2.getString(CheckCodeDO.CHECKCODE_IMAGE_URL_KEY));
                        topics.setType(jSONObject2.getString("type"));
                        arrayList.add(topics);
                    }
                    labelBean.setTopics(arrayList);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return labelBean;
    }

    public static List<SearchHotItem> d(String str) {
        JSONException e;
        int i = 0;
        if (TextUtils.isEmpty(str) || "[]".equals(str)) {
            return null;
        }
        List<SearchHotItem> arrayList;
        try {
            arrayList = new ArrayList();
            try {
                JSONObject jSONObject;
                JSONArray jSONArray;
                SearchHotItem searchHotItem;
                if (str.startsWith("{")) {
                    jSONObject = new JSONObject(str);
                    if (!jSONObject.has("hot_search") || "[]".equals(jSONObject.getString("hot_search"))) {
                        return arrayList;
                    }
                    jSONArray = jSONObject.getJSONArray("hot_search");
                    while (i < jSONArray.length()) {
                        jSONObject = jSONArray.getJSONObject(i);
                        if (jSONObject != null) {
                            searchHotItem = new SearchHotItem();
                            try {
                                if (jSONObject.has("name")) {
                                    searchHotItem.setSearchKey(jSONObject.getString("name"));
                                }
                                if (jSONObject.has("count")) {
                                    searchHotItem.setCount(jSONObject.getString("count"));
                                }
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                            arrayList.add(searchHotItem);
                        }
                        i++;
                    }
                    return arrayList;
                }
                jSONArray = new JSONArray(str);
                while (i < jSONArray.length()) {
                    jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject != null) {
                        searchHotItem = new SearchHotItem();
                        try {
                            if (jSONObject.has("name")) {
                                searchHotItem.setSearchKey(jSONObject.getString("name"));
                            }
                            if (jSONObject.has("count")) {
                                searchHotItem.setCount(jSONObject.getString("count"));
                            }
                        } catch (JSONException e22) {
                            e22.printStackTrace();
                        }
                        arrayList.add(searchHotItem);
                    }
                    i++;
                }
                return arrayList;
            } catch (JSONException e3) {
                e22 = e3;
            }
        } catch (JSONException e4) {
            JSONException jSONException = e4;
            arrayList = null;
            e22 = jSONException;
            e22.printStackTrace();
            return arrayList;
        }
    }
}
