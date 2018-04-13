package cn.v6.sixrooms.room.statistic;

import android.os.Handler;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.utils.AppInfoUtils;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.UrlUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class StatisticManager {
    public static String DECODE_STATISTIC_PADAPI = "coop-mobile-videoStatisCount.php";
    public static String STATISTIC_URL = "http://sclick.6rooms.com/s.html";
    private static final String a = StatisticManager.class.getSimpleName();
    public static volatile StatisticManager instance = null;
    private long b;
    private long c;
    private Handler d = new Handler();
    private String e;
    private Runnable f = new e(this);

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        if (instance == null) {
            synchronized (StatisticManager.class) {
                if (instance == null) {
                    instance = new StatisticManager();
                }
            }
        }
        return instance;
    }

    public void pageStatistic(String str) {
        try {
            String str2 = "";
            if (GlobleValue.getUserBean() != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(HistoryOpenHelper.COLUMN_UID, GlobleValue.getUserBean().getId());
                jSONObject.put("coinrank", GlobleValue.getUserBean().getCoin6rank());
                jSONObject.put("wealthrank", GlobleValue.getUserBean().getWealthrank());
                str2 = URLEncoder.encode(jSONObject.toString());
            }
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("uuid", AppInfoUtils.getUUID()));
            arrayList.add(new BasicNameValuePair("page", str));
            arrayList.add(new BasicNameValuePair("event", StatiscEvent.LOAD));
            arrayList.add(new BasicNameValuePair("data", str2));
            arrayList.add(new BasicNameValuePair("watchid", StatisticValue.getInstance().getWatchid()));
            NetworkServiceSingleton.createInstance().sendAsyncRequest(new a(this), UrlUtils.getUrl(STATISTIC_URL, arrayList), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickStatistic(String str, String str2) {
        try {
            String str3 = "";
            if (GlobleValue.getUserBean() != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(HistoryOpenHelper.COLUMN_UID, GlobleValue.getUserBean().getId());
                jSONObject.put("coinrank", GlobleValue.getUserBean().getCoin6rank());
                jSONObject.put("wealthrank", GlobleValue.getUserBean().getWealthrank());
                str3 = URLEncoder.encode(jSONObject.toString());
            }
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("uuid", AppInfoUtils.getUUID()));
            arrayList.add(new BasicNameValuePair("page", str));
            arrayList.add(new BasicNameValuePair("event", StatiscEvent.CLICK));
            arrayList.add(new BasicNameValuePair("data", str3));
            arrayList.add(new BasicNameValuePair("module", str2));
            NetworkServiceSingleton.createInstance().sendAsyncRequest(new b(this), UrlUtils.getUrl(STATISTIC_URL, arrayList), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickStatistic(String str, String str2, String str3, String str4) {
        try {
            String str5 = "";
            if (GlobleValue.getUserBean() != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(HistoryOpenHelper.COLUMN_UID, GlobleValue.getUserBean().getId());
                jSONObject.put("coinrank", GlobleValue.getUserBean().getCoin6rank());
                jSONObject.put("wealthrank", GlobleValue.getUserBean().getWealthrank());
                str5 = URLEncoder.encode(jSONObject.toString());
            }
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("uuid", AppInfoUtils.getUUID()));
            arrayList.add(new BasicNameValuePair("from_module", str));
            arrayList.add(new BasicNameValuePair("event", str3));
            arrayList.add(new BasicNameValuePair("data", str5));
            arrayList.add(new BasicNameValuePair("rid", str4));
            arrayList.add(new BasicNameValuePair("watchid", StatisticValue.getInstance().getWatchid(str2)));
            NetworkServiceSingleton.createInstance().sendAsyncRequest(new c(this), UrlUtils.getUrl(STATISTIC_URL, arrayList), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void intoStatistic(String str, String str2, String str3, String str4, String str5) {
        try {
            String str6 = "";
            if (GlobleValue.getUserBean() != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(HistoryOpenHelper.COLUMN_UID, GlobleValue.getUserBean().getId());
                jSONObject.put("coinrank", GlobleValue.getUserBean().getCoin6rank());
                jSONObject.put("wealthrank", GlobleValue.getUserBean().getWealthrank());
                str6 = URLEncoder.encode(jSONObject.toString());
            }
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("uuid", AppInfoUtils.getUUID()));
            arrayList.add(new BasicNameValuePair("page", str));
            arrayList.add(new BasicNameValuePair("event", StatiscEvent.IN));
            arrayList.add(new BasicNameValuePair("data", str6));
            arrayList.add(new BasicNameValuePair("module", str2));
            arrayList.add(new BasicNameValuePair("url", str3));
            arrayList.add(new BasicNameValuePair("pos", str4));
            arrayList.add(new BasicNameValuePair("recid", str5));
            arrayList.add(new BasicNameValuePair("watchid", StatisticValue.getInstance().getWatchid(str)));
            NetworkServiceSingleton.createInstance().sendAsyncRequest(new d(this), UrlUtils.getUrl(STATISTIC_URL, arrayList), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startWatchTime(String str) {
        this.e = str;
        startTiming();
        this.d.post(this.f);
    }

    public void stopWatchTime() {
        stopTiming(this.e);
        this.d.removeCallbacks(this.f);
    }

    public void destroyWatchTime() {
        this.b = 0;
        this.c = 0;
    }

    public void startTiming() {
        if (this.b == 0) {
            this.b = System.currentTimeMillis() / 1000;
        }
    }

    public void pollSendWatchTime(String str) {
        long abs;
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (this.c == 0) {
            abs = Math.abs(currentTimeMillis - this.b);
        } else {
            abs = Math.abs(currentTimeMillis - this.c);
        }
        this.c = currentTimeMillis;
        getInstance().watchTimeStatistic(StatisticValue.getInstance().getFromFoomPageModule(), str, abs, Math.abs(currentTimeMillis - this.b));
    }

    public void stopTiming(String str) {
        long abs;
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (this.c == 0) {
            abs = Math.abs(currentTimeMillis - this.b);
        } else {
            abs = Math.abs(currentTimeMillis - this.c);
        }
        this.c = currentTimeMillis;
        getInstance().watchTimeStatistic(StatisticValue.getInstance().getFromFoomPageModule(), str, abs, Math.abs(currentTimeMillis - this.b));
    }

    public void watchTimeStatistic(String str, String str2, long j, long j2) {
        try {
            String str3 = "";
            if (GlobleValue.getUserBean() != null) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(HistoryOpenHelper.COLUMN_UID, GlobleValue.getUserBean().getId());
                jSONObject.put("coinrank", GlobleValue.getUserBean().getCoin6rank());
                jSONObject.put("wealthrank", GlobleValue.getUserBean().getWealthrank());
                str3 = URLEncoder.encode(jSONObject.toString());
            }
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("uuid", AppInfoUtils.getUUID()));
            arrayList.add(new BasicNameValuePair("event", StatiscEvent.LOOP));
            arrayList.add(new BasicNameValuePair("data", str3));
            arrayList.add(new BasicNameValuePair("from_module", str));
            arrayList.add(new BasicNameValuePair("rid", str2));
            arrayList.add(new BasicNameValuePair(IXAdRequestInfo.MAX_TITLE_LENGTH, String.valueOf(j)));
            arrayList.add(new BasicNameValuePair("looktm", String.valueOf(j2)));
            arrayList.add(new BasicNameValuePair("watchid", StatisticValue.getInstance().getWatchid(StatisticCodeTable.ROOM)));
            NetworkServiceSingleton.createInstance().sendAsyncRequest(new f(this), UrlUtils.getUrl(STATISTIC_URL, arrayList), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decodeStatistic(int i) {
        try {
            List arrayList = new ArrayList();
            arrayList.add(new BasicNameValuePair("padapi", DECODE_STATISTIC_PADAPI));
            arrayList.add(new BasicNameValuePair("type", String.valueOf(i)));
            arrayList.add(new BasicNameValuePair("processor", AppInfoUtils.getCpuAbil()));
            arrayList.add(new BasicNameValuePair("androidversion", AppInfoUtils.getDeviceVersion()));
            NetworkServiceSingleton.createInstance().sendAsyncRequest(new g(this), UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList), "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
