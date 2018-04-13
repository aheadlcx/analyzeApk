package qsbk.app.ad.feedsad.qbad;

import android.support.v4.app.NotificationCompat;
import android.util.Pair;
import java.util.Collection;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;

class a extends AsyncTask<String, Void, Pair<Integer, String>> {
    final /* synthetic */ QbAd a;

    a(QbAd qbAd) {
        this.a = qbAd;
    }

    protected Pair<Integer, String> a(String... strArr) {
        try {
            String str = strArr[0];
            LogUtil.d("url:" + str);
            JSONObject jSONObject = new JSONObject(HttpClient.getIntentce().get(str));
            int i = jSONObject.getInt(NotificationCompat.CATEGORY_ERROR);
            if (i == 0) {
                JSONArray optJSONArray = jSONObject.optJSONArray("rows");
                Collection linkedList = new LinkedList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    QbAdItem qbAdItem = new QbAdItem();
                    qbAdItem.parseFromJSONObject(optJSONObject);
                    linkedList.add(qbAdItem);
                }
                synchronized (QbAd.access$000(this.a)) {
                    QbAd.access$000(this.a).clear();
                    QbAd.access$000(this.a).addAll(linkedList);
                }
                LogUtil.d("fetch qb ad:");
                this.a.findValidAdsAndTopAds();
            }
            return new Pair(Integer.valueOf(i), jSONObject.optString("err_msg"));
        } catch (Exception e) {
            e.printStackTrace();
            return new Pair(Integer.valueOf(9999), HttpClient.getLocalErrorStr());
        }
    }

    protected void a(Pair<Integer, String> pair) {
        super.a(pair);
        QbAd.access$102(this.a, false);
        if (((Integer) pair.first).intValue() == 0) {
            QbAd.access$202(this.a, System.currentTimeMillis());
            QbAd.access$302(this.a, true);
        } else {
            QbAd.access$302(this.a, false);
        }
        if (QbAd.access$400(this.a) != null) {
            synchronized (QbAd.access$000(this.a)) {
                if (!QbAd.access$000(this.a).isEmpty()) {
                    QbAd.access$400(this.a).onFeedsAdLoaded();
                }
            }
        }
    }
}
