package cn.xiaochuankeji.tieba.ui.post.a;

import cn.xiaochuankeji.tieba.background.ad.AdvertismentBean;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentBean.AdMultiMedia;
import cn.xiaochuankeji.tieba.background.ad.AdvertismentSoftBean;
import cn.xiaochuankeji.tieba.background.ad.PostAdExtraInfo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.izuiyou.a.a.b;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.tencent.tauth.AuthActivity;
import java.util.HashMap;
import java.util.Map;
import rx.j;

public class a {
    private static a b;
    private Map<NativeMediaADData, JSONObject> a = new HashMap();

    private a() {
    }

    public static a a() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public void a(NativeMediaADData nativeMediaADData, PostAdExtraInfo postAdExtraInfo) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", postAdExtraInfo.source.type);
            jSONObject.put("appid", postAdExtraInfo.source.appId);
            jSONObject.put("adslot", postAdExtraInfo.source.slotId);
            jSONObject.put("title", nativeMediaADData.getTitle());
            jSONObject.put("description", nativeMediaADData.getDesc());
            jSONObject.put("image_url", nativeMediaADData.getImgUrl());
            jSONObject.put("image_list", nativeMediaADData.getImgList());
            JSONObject jSONObject2 = new JSONObject();
            Object obj = nativeMediaADData.getAdPatternType() == 2 ? "video" : "img";
            jSONObject2.put(AuthActivity.ACTION_KEY, "ad");
            jSONObject2.put("otype", obj);
            jSONObject2.put("src", "index");
            jSONObject2.put("data", jSONObject);
            synchronized (this.a) {
                this.a.put(nativeMediaADData, jSONObject2);
            }
            a(1002, nativeMediaADData, postAdExtraInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(PostAdExtraInfo postAdExtraInfo) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", postAdExtraInfo.source.type);
        jSONObject.put("appid", postAdExtraInfo.source.appId);
        jSONObject.put("adslot", postAdExtraInfo.source.slotId);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(AuthActivity.ACTION_KEY, "ad");
        jSONObject2.put("otype", "null");
        jSONObject2.put("src", "index");
        JSONArray jSONArray = new JSONArray();
        jSONArray.add(Integer.valueOf(1001));
        jSONObject.put("ad_actions", jSONArray);
        jSONObject2.put("data", jSONObject);
        cn.xiaochuankeji.tieba.api.ad.a aVar = new cn.xiaochuankeji.tieba.api.ad.a();
        jSONArray = new JSONArray();
        jSONArray.add(jSONObject2);
        aVar.a(jSONArray).b(new j<Void>(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((Void) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
            }

            public void a(Void voidR) {
            }
        });
    }

    public void a(int i, NativeMediaADData nativeMediaADData, PostAdExtraInfo postAdExtraInfo) {
        if (this.a != null) {
            JSONObject jSONObject = (JSONObject) this.a.get(nativeMediaADData);
            if (jSONObject == null) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("type", postAdExtraInfo.source.type);
                    jSONObject2.put("appid", postAdExtraInfo.source.appId);
                    jSONObject2.put("adslot", postAdExtraInfo.source.slotId);
                    jSONObject2.put("title", nativeMediaADData.getTitle());
                    jSONObject2.put("description", nativeMediaADData.getDesc());
                    jSONObject2.put("image_url", nativeMediaADData.getImgUrl());
                    jSONObject2.put("image_list", nativeMediaADData.getImgList());
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.add(Integer.valueOf(i));
                    jSONObject2.put("actions", jSONArray);
                    JSONObject jSONObject3 = new JSONObject();
                    Object obj = nativeMediaADData.getAdPatternType() == 2 ? "video" : "img";
                    jSONObject3.put(AuthActivity.ACTION_KEY, "ad");
                    jSONObject3.put("otype", obj);
                    jSONObject3.put("src", "index");
                    jSONObject3.put("data", jSONObject2);
                    jSONArray = new JSONArray();
                    jSONArray.add(jSONObject3);
                    new cn.xiaochuankeji.tieba.api.ad.a().a(jSONArray).g();
                    return;
                } catch (Exception e) {
                    return;
                }
            }
            try {
                if (jSONObject.containsKey("data")) {
                    jSONObject = jSONObject.getJSONObject("data");
                    if (!jSONObject.containsKey("actions")) {
                        jSONObject.put("actions", new JSONArray());
                    }
                    jSONObject.getJSONArray("actions").add(Integer.valueOf(i));
                }
            } catch (Exception e2) {
            }
        }
    }

    public void b() {
        b.c("reportAds reportInfo:" + this.a.size());
        if (this.a != null && this.a.size() != 0) {
            JSONArray jSONArray = new JSONArray();
            for (JSONObject add : this.a.values()) {
                jSONArray.add(add);
            }
            cn.xiaochuankeji.tieba.api.ad.a aVar = new cn.xiaochuankeji.tieba.api.ad.a();
            b.c("report api");
            aVar.a(jSONArray).b(new j<Void>(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onNext(Object obj) {
                    a((Void) obj);
                }

                public void onCompleted() {
                    synchronized (this.a.a) {
                        this.a.a.clear();
                    }
                }

                public void onError(Throwable th) {
                }

                public void a(Void voidR) {
                }
            });
        }
    }

    public static void a(AdvertismentBean advertismentBean, int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", Integer.valueOf(advertismentBean.c_type));
            jSONObject.put("id", Long.valueOf(advertismentBean.id));
            JSONArray jSONArray = new JSONArray();
            jSONArray.add(Integer.valueOf(i));
            jSONObject.put("actions", jSONArray);
            jSONObject.put(PushConstants.EXTRA, advertismentBean.extraCallback);
            JSONObject jSONObject2 = new JSONObject();
            Object obj = (advertismentBean.media == null || advertismentBean.media.size() <= 0 || ((AdMultiMedia) advertismentBean.media.get(0)).videoUrls == null || ((AdMultiMedia) advertismentBean.media.get(0)).videoUrls.size() <= 0) ? null : 1;
            jSONObject2.put(AuthActivity.ACTION_KEY, "ad");
            jSONObject2.put("otype", obj != null ? "video" : "img");
            jSONObject2.put("src", "index");
            jSONObject2.put("data", jSONObject);
            cn.xiaochuankeji.tieba.api.ad.a aVar = new cn.xiaochuankeji.tieba.api.ad.a();
            b.c("report api");
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.add(jSONObject2);
            aVar.a(jSONArray2).b(new j<Void>() {
                public /* synthetic */ void onNext(Object obj) {
                    a((Void) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(Void voidR) {
                }
            });
        } catch (Exception e) {
            b.e(e);
        }
    }

    public static void a(AdvertismentSoftBean advertismentSoftBean, int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", Integer.valueOf(advertismentSoftBean.cType));
            if (!(advertismentSoftBean == null || advertismentSoftBean.advert == null)) {
                jSONObject.put("id", Long.valueOf(advertismentSoftBean.advert.adid));
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.add(Integer.valueOf(i));
            jSONObject.put("actions", jSONArray);
            JSONObject jSONObject2 = new JSONObject();
            boolean videoPost = advertismentSoftBean.videoPost();
            jSONObject2.put(AuthActivity.ACTION_KEY, "ad");
            jSONObject2.put("otype", videoPost ? "video" : "img");
            jSONObject2.put("src", "index");
            jSONObject2.put("data", jSONObject);
            cn.xiaochuankeji.tieba.api.ad.a aVar = new cn.xiaochuankeji.tieba.api.ad.a();
            b.c("report api");
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.add(jSONObject2);
            aVar.a(jSONArray2).b(new j<Void>() {
                public /* synthetic */ void onNext(Object obj) {
                    a((Void) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                }

                public void a(Void voidR) {
                }
            });
        } catch (Exception e) {
            b.e(e);
        }
    }
}
