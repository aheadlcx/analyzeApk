package cn.xiaochuankeji.tieba.ui.topic.model;

import android.arch.lifecycle.o;
import android.text.TextUtils;
import cn.xiaochuankeji.tieba.api.topic.TopicService;
import cn.xiaochuankeji.tieba.background.beans.TopicBanner;
import cn.xiaochuankeji.tieba.network.c;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import rx.a.b.a;
import rx.b.g;
import rx.e;

public class TopicBannerModel extends o {
    public void a(final a aVar) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pos", Integer.valueOf(1));
        ((TopicService) c.b(TopicService.class)).getBanners(jSONObject).d(new g<JSONObject, ArrayList<TopicBanner>>(this) {
            final /* synthetic */ TopicBannerModel a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object call(Object obj) {
                return a((JSONObject) obj);
            }

            public ArrayList<TopicBanner> a(JSONObject jSONObject) {
                JSONArray jSONArray = jSONObject.getJSONArray("list");
                ArrayList<TopicBanner> arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.size(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    TopicBanner topicBanner = new TopicBanner();
                    topicBanner.type = jSONObject2.getIntValue("type");
                    if (TopicBanner.isSupport(topicBanner.type)) {
                        topicBanner.imageUrl = jSONObject2.getString("url");
                        jSONObject2 = jSONObject2.getJSONObject("data");
                        if (jSONObject2 != null) {
                            topicBanner.id = jSONObject2.getLongValue("tid");
                            if (topicBanner.id == 0) {
                                topicBanner.id = jSONObject2.getLongValue("pid");
                            }
                            topicBanner.activityUrl = jSONObject2.getString("url");
                        }
                        if (!TextUtils.isEmpty(topicBanner.imageUrl)) {
                            arrayList.add(topicBanner);
                        }
                    }
                }
                return arrayList;
            }
        }).a(a.a()).a(new e<ArrayList<TopicBanner>>(this) {
            final /* synthetic */ TopicBannerModel b;

            public /* synthetic */ void onNext(Object obj) {
                a((ArrayList) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                aVar.a(th);
            }

            public void a(ArrayList<TopicBanner> arrayList) {
                aVar.a((List) arrayList);
            }
        });
    }
}
