package qsbk.app.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import com.baidu.mobstat.Config;
import java.util.Calendar;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.ReAuthActivity;
import qsbk.app.activity.base.BaseArticleListViewFragment;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.adapter.SubscribeAdapter;
import qsbk.app.cache.FileCache;
import qsbk.app.cache.MemoryCache;
import qsbk.app.core.utils.NetworkUtils;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.live.ui.LivePushActivity;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.model.Article;
import qsbk.app.model.EvaluateCard;
import qsbk.app.model.LivePackage;
import qsbk.app.model.Qsjx;
import qsbk.app.model.ReportCallCard;
import qsbk.app.model.RssArticle;
import qsbk.app.model.UserLoginGuideCard;
import qsbk.app.model.WelcomeCard;
import qsbk.app.nearby.api.ILocationCallback;
import qsbk.app.nearby.api.ILocationManager;
import qsbk.app.nearby.api.LocationCache;
import qsbk.app.nearby.api.LocationCache.Location;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.LiveRecommendManager;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.Md5;
import qsbk.app.utils.QiushiCircleVideoManager;
import qsbk.app.utils.ReadQiushi;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.SubscribeReportHelper;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UserLogoutHelper;
import qsbk.app.widget.qbnews.recommend.NewsRecommendManager;

public class SubscribeFragment extends BaseArticleListViewFragment implements ILocationCallback {
    public static final String ACTION_WELCOME_CARD_LOGIN = "action_welcome_card_login";
    private static final String Q = SubscribeFragment.class.getSimpleName();
    public static boolean hasUserLoginGuideCard = false;
    private final Runnable R = new kx(this);
    private Location S;
    private int T = 0;
    private String U;
    private ILocationManager V;
    private final Runnable W = new ky(this);
    private LocalBroadcastManager X;
    private boolean Y = true;
    private BroadcastReceiver Z = new kz(this);
    private BroadcastReceiver aa = new lb(this);

    class a implements OnAsyncLoadListener {
        final /* synthetic */ SubscribeFragment a;
        private String b = "";
        private String c = "";

        public a(SubscribeFragment subscribeFragment, String str) {
            this.a = subscribeFragment;
            this.c = str;
        }

        public void onPrepareListener() {
            this.a.y = true;
            StringBuffer append = new StringBuffer(this.a.u).append("?").append("new=").append(this.c);
            if (this.a.S != null) {
                append.append("&longitude=").append(this.a.S.longitude);
                append.append("&latitude=").append(this.a.S.latitude);
            }
            String string = ReadQiushi.getString();
            if (string != null && string.length() > 2) {
                append.append("&readarticles=").append(string);
            }
            this.b = append.toString();
            DebugUtil.debug(SubscribeFragment.Q, "target url:" + this.b);
        }

        public String onStartListener() {
            if (this.a.o == 1) {
                Context activity = this.a.getActivity();
                if (activity != null) {
                    EvaluateCard.syncLoadIfNeed(activity, false);
                }
            }
            String str = "";
            try {
                str = MemoryCache.findOrCreateMemoryCache().get(Md5.MD5(this.b));
                if (TextUtils.isEmpty(str)) {
                    LogUtil.e("没有预加载，获取网络数据");
                    str = HttpClient.getIntentce().get(this.b);
                } else {
                    MemoryCache.findOrCreateMemoryCache().clear();
                }
            } catch (QiushibaikeException e) {
                this.a.a(0, false);
            } catch (Exception e2) {
                this.a.a(0, false);
            }
            DebugUtil.debug(SubscribeFragment.Q, "loadContent:" + str);
            return str;
        }

        public void onFinishListener(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    if (new JSONObject(str).optInt(NotificationCompat.CATEGORY_ERROR) == SimpleHttpTask.ERROR_DOUBLE_LOGIN.intValue()) {
                        if (QsbkApp.currentUser != null) {
                            QsbkApp.mContext.startActivity(ReAuthActivity.getIntent(QsbkApp.mContext));
                        }
                        new UserLogoutHelper(this.a.B).doLogout();
                        this.a.S();
                        this.a.x = new AsyncDataLoader(this.a.b(this.c), "qsbk-AT-BGA-pre1");
                        this.a.x.execute(new Void[0]);
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                boolean a = this.a.a(str);
                if (this.a.o == 2 && a) {
                    FileCache.cacheTextToDiskImmediately(this.a.B, this.a.v, str);
                }
                this.a.lastRefreshFirstPageTime = Long.valueOf(System.currentTimeMillis());
                ListViewHelper.saveLastUpdateTime(this.a.B, this.a.v);
                if (this.a.isSelected()) {
                    ReadQiushi.markSend();
                }
                if (this.a.isSelected() && this.a.o == 2) {
                    ReadQiushi.setFirstArticleRead(this.a.j);
                }
                this.a.y = false;
                this.a.s = false;
                this.a.a(true);
                if (this.a.B instanceof MainActivity) {
                    ((MainActivity) this.a.B).requestHideSmallTips(this.a);
                }
            } else if (TextUtils.isEmpty(str)) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "加载失败，请稍后再试。", Integer.valueOf(0)).show();
                this.a.a(0, false);
            }
        }
    }

    public SubscribeFragment() {
        S();
    }

    private void S() {
        this.u = Constants.MAINPAGE_1;
        this.v = "subscribe";
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LocalBroadcastManager.getInstance(this.B).registerReceiver(this.Z, new IntentFilter(MainActivity.ACTION_NEW_INTENT));
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        setForceLoad(true);
        this.S = LocationCache.getInstance().getLocation(LivePushActivity.INNER);
        a.postDelayed(this.W, Config.BPLUS_DELAY_TIME);
    }

    protected void a(View view) {
        super.a(view);
        if (this.l != null) {
            this.l.setLeftItemWhenTrickLoadMore(3);
        }
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.S = LocationCache.getInstance().getLocation(LivePushActivity.INNER);
        S();
    }

    public String getVotePoint() {
        return this.v + MqttTopic.TOPIC_LEVEL_SEPARATOR;
    }

    protected BaseImageAdapter b() {
        return new SubscribeAdapter(this.B, this.m, this.j, getVotePoint(), this.v, this);
    }

    public void onRefresh() {
        LiveRecommendManager instance = LiveRecommendManager.getInstance();
        if (instance != null) {
            instance.refresh();
        }
        QiushiCircleVideoManager instance2 = QiushiCircleVideoManager.getInstance();
        if (instance2 != null) {
            instance2.load();
        }
        this.U = "1";
        super.onRefresh();
    }

    public void initHistoryData() {
        EvaluateCard.syncLoadIfNeed(getActivity(), true);
        super.initHistoryData();
    }

    protected boolean p() {
        return false;
    }

    private void b(JSONArray jSONArray) {
        if (jSONArray != null && jSONArray.length() != 0) {
            int length = jSONArray.length();
            int i;
            if ("top_refresh".equals(this.e)) {
                i = length - 1;
                while (i >= 0) {
                    try {
                        if (jSONArray.optJSONObject(i) != null) {
                            Article rssArticle = new RssArticle(jSONArray.optJSONObject(i));
                            if (this.e.equals("top_refresh") && i == 0 && (this.B instanceof MainActivity)) {
                                this.h = false;
                                if (this.f != null) {
                                    this.g = this.f;
                                }
                                this.f = rssArticle;
                            }
                            if (!(this.j.contains(rssArticle) || ReportArticle.mReportArtcicle.keySet().contains(rssArticle.id))) {
                                this.j.add(0, rssArticle);
                            }
                        }
                    } catch (QiushibaikeException e) {
                    }
                    i--;
                }
                return;
            }
            for (i = 0; i < length; i++) {
                try {
                    if (jSONArray.optJSONObject(i) != null) {
                        RssArticle rssArticle2 = new RssArticle(jSONArray.optJSONObject(i));
                        if (!(this.j.contains(rssArticle2) || ReportArticle.mReportArtcicle.keySet().contains(rssArticle2.id))) {
                            this.j.add(rssArticle2);
                        }
                    }
                } catch (QiushibaikeException e2) {
                }
            }
        }
    }

    protected boolean a(String str) {
        try {
            if (this.j.contains(this.K)) {
                this.j.clear();
            }
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt(NotificationCompat.CATEGORY_ERROR);
            if (optInt == SimpleHttpTask.ERROR_DOUBLE_LOGIN.intValue()) {
                return false;
            }
            if (optInt == 0 || !isSelected()) {
                JSONArray optJSONArray = jSONObject.optJSONArray("items");
                this.p = jSONObject.optInt("total");
                this.Y = jSONObject.optBoolean("hasMore");
                Object optString = jSONObject.optString("message");
                if (!TextUtils.isEmpty(optString) && this.r && isSelected() && this.e.equals("top_refresh")) {
                    ToastAndDialog.makePositiveToast(QsbkApp.mContext, optString, Integer.valueOf(0)).show();
                }
                if (this.Y) {
                    this.q = false;
                    this.l.setLoadMoreEnable(true);
                    this.footView.setVisibility(8);
                } else {
                    this.q = true;
                    this.l.setLoadMoreEnable(false);
                    this.footView.setVisibility(0);
                }
                if (this.e.equals("top_refresh") || (this.o == 1 && !x())) {
                    this.D = 0;
                    A();
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        a(n());
                    }
                    SubscribeReportHelper.clear();
                    if (p()) {
                        NewsRecommendManager.setLastInsertPosition(this.v, -1);
                    }
                }
                if (optJSONArray == null || optJSONArray.length() == 0) {
                    return false;
                }
                T();
                U();
                this.k = this.j.size();
                b(optJSONArray);
                if (this.o == 1 && this.C != null && this.j.size() > 15) {
                    this.j.add(15, this.C);
                    SubscribeReportHelper.addRecord(this.C.hashCode(), SubscribeReportHelper.TYPE_GROUP, 15);
                }
                if (LiveRecommendManager.LIVE_RECOMMEND != null) {
                    Pair liveRecommendPackages = LivePackage.getLiveRecommendPackages(LiveRecommendManager.LIVE_RECOMMEND, true);
                    LivePackage livePackage;
                    if (this.o != 1 || !NetworkUtils.getInstance().isWifiAvailable() || liveRecommendPackages == null || liveRecommendPackages.second == null) {
                        if (u()) {
                            insertRecommendTopics();
                        }
                        if (this.o == 1 && liveRecommendPackages != null) {
                            livePackage = (LivePackage) liveRecommendPackages.first;
                            if (this.j.size() > 11 && livePackage != null) {
                                this.j.add(11, livePackage);
                                SubscribeReportHelper.addRecord(livePackage.hashCode(), "live", 11);
                            }
                        }
                    } else {
                        livePackage = (LivePackage) liveRecommendPackages.first;
                        LivePackage livePackage2 = (LivePackage) liveRecommendPackages.second;
                        if (u()) {
                            insertRecommendTopics();
                        }
                        if (this.j.size() > 11 && livePackage != null) {
                            this.j.add(11, livePackage);
                            SubscribeReportHelper.addRecord(livePackage.hashCode(), "live", 11);
                        }
                    }
                } else if (u()) {
                    insertRecommendTopics();
                }
                optInt = this.j.size();
                if (QiushiListFragment.showCircleVideo) {
                    optInt -= this.k;
                    int size = QiushiCircleVideoManager.circleVideos.size();
                    int i;
                    int i2;
                    if (NetworkUtils.getInstance().isWifiAvailable() && optInt > 0) {
                        i = optInt / QiushiListFragment.circleWifiPosition;
                        i2 = 0;
                        while (true) {
                            if (i2 >= (i > size ? size : i)) {
                                break;
                            }
                            optInt = ((i2 + 1) * QiushiListFragment.circleWifiPosition) + this.k;
                            if (optInt > 1 && size > i2) {
                                this.j.add(optInt - 1, QiushiCircleVideoManager.circleVideos.get(i2));
                            }
                            i2++;
                        }
                    } else {
                        i = optInt / QiushiListFragment.circle4GPosition;
                        i2 = 0;
                        while (true) {
                            if (i2 >= (i > size ? size : i)) {
                                break;
                            }
                            optInt = ((i2 + 1) * QiushiListFragment.circle4GPosition) + this.k;
                            if (optInt > 1 && size > i2) {
                                this.j.add(optInt - 1, QiushiCircleVideoManager.circleVideos.get(i2));
                            }
                            i2++;
                        }
                    }
                }
                if (p()) {
                    q();
                }
                v();
                if (!jSONObject.isNull("ads")) {
                    a(jSONObject.getJSONArray("ads"));
                }
                this.j.remove(EvaluateCard.INSTANCE);
                this.j.remove(ReportCallCard.INSTANCE);
                if (!EvaluateCard.isNeedToShow() || EvaluateCard.getPosition() >= this.j.size()) {
                    long sharePreferencesLongValue = SharePreferenceUtils.getSharePreferencesLongValue(ReportCallCard.class.getSimpleName());
                    Calendar instance = Calendar.getInstance();
                    instance.setTimeInMillis(sharePreferencesLongValue);
                    Calendar instance2 = Calendar.getInstance();
                    if (instance2.get(6) - instance.get(6) >= 14 || instance2.get(1) > instance.get(1)) {
                        this.j.add(EvaluateCard.getPosition(), ReportCallCard.INSTANCE);
                        SharePreferenceUtils.setSharePreferencesValue(ReportCallCard.class.getSimpleName(), System.currentTimeMillis());
                    }
                } else {
                    this.j.add(EvaluateCard.getPosition(), EvaluateCard.INSTANCE);
                }
                this.j.remove(UserLoginGuideCard.instance);
                if (!UserLoginGuideCard.isNeedToShow() || UserLoginGuideCard.getPosition() >= this.j.size()) {
                    hasUserLoginGuideCard = false;
                } else {
                    hasUserLoginGuideCard = true;
                    this.X = LocalBroadcastManager.getInstance(getActivity());
                    this.X.registerReceiver(this.aa, new IntentFilter(ACTION_WELCOME_CARD_LOGIN));
                    this.j.add(UserLoginGuideCard.getPosition(), UserLoginGuideCard.getInstance(getActivity()));
                    SubscribeReportHelper.addRecord(-1, SubscribeReportHelper.TYPE_LOGIN_CARD, UserLoginGuideCard.getPosition());
                }
                this.j.remove(WelcomeCard.instance);
                if (WelcomeCard.isNeedToShow() && WelcomeCard.getPosition() < this.j.size() && QsbkApp.currentUser == null) {
                    a((Object) WelcomeCard.getInstance(getActivity()));
                }
                if (!this.q) {
                    this.o++;
                }
                return true;
            }
            Object optString2 = jSONObject.optString("err_msg");
            if (!TextUtils.isEmpty(optString2)) {
                ToastAndDialog.makeText(this.B, optString2).show();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public LivePackage getSecondLivePackage() {
        Pair liveRecommendPackages = LivePackage.getLiveRecommendPackages(LiveRecommendManager.LIVE_RECOMMEND, true);
        if (liveRecommendPackages == null || liveRecommendPackages.second == null) {
            return super.getSecondLivePackage();
        }
        return (LivePackage) liveRecommendPackages.second;
    }

    public boolean needSubscribeReport() {
        return true;
    }

    protected int n() {
        return 15;
    }

    private boolean a(int i) {
        int i2 = 0;
        if (i < 0) {
            return false;
        }
        if (this.j != null && i >= this.j.size()) {
            return false;
        }
        int i3 = 0;
        while (i2 < this.j.size()) {
            i3++;
            if (i3 > i) {
                break;
            }
            i2++;
        }
        i2 = i3;
        while (this.j.size() > i2) {
            this.j.remove(this.j.size() - 1);
        }
        return true;
    }

    public void onLoadMore() {
        LiveRecommendManager instance = LiveRecommendManager.getInstance();
        if (instance != null) {
            instance.refresh();
        }
        QiushiCircleVideoManager instance2 = QiushiCircleVideoManager.getInstance();
        if (instance2 != null) {
            instance2.load();
        }
        if (!this.q) {
            this.U = "0";
        }
        super.onLoadMore();
    }

    public void onDestroy() {
        LocalBroadcastManager.getInstance(this.B).unregisterReceiver(this.Z);
        super.onDestroy();
    }

    protected OnAsyncLoadListener b(String str) {
        return new a(this, this.U);
    }

    public void onLocation(int i, double d, double d2, String str, String str2) {
        LogUtil.d("location type:" + i);
        LogUtil.d("location latitude:" + d);
        LogUtil.d("location longtitude:" + d2);
        LogUtil.d("location district:" + str);
        LogUtil.d("location city:" + str2);
        if (i == 161 || i == 0) {
            NearbyEngine.saveLastLocationToLocal(d, d2);
        }
        if (i == 61 || i == 65 || i == 66 || i == 68 || i == 161 || i == 0) {
            if (this.S == null) {
                this.S = new Location();
            }
            this.S.latitude = d;
            this.S.longitude = d2;
            this.S.city = str2;
            this.S.code = i;
            this.S.district = str;
            this.V.remove(this);
            LocationCache.getInstance().setLocation(this.S);
            return;
        }
        this.T++;
        this.V = NearbyEngine.instance().changeLocationMgr(this.V);
        if (this.T >= 2) {
            this.T = 0;
            Pair lastSavedPosition = NearbyEngine.getLastSavedPosition(true);
            if (lastSavedPosition != null) {
                if (this.S == null) {
                    this.S = new Location();
                }
                this.S.latitude = ((Double) lastSavedPosition.first).doubleValue();
                this.S.longitude = ((Double) lastSavedPosition.second).doubleValue();
                return;
            }
            return;
        }
        int location = this.V.getLocation(this);
        LogUtil.d("ret:" + location);
        if (location == 6) {
            a.postDelayed(new lc(this), 2000);
        }
    }

    public void onDestroyView() {
        if (this.V != null) {
            this.V.stop();
        }
        super.onDestroyView();
    }

    private void T() {
        boolean sharePreferencesBoolValue = SharePreferenceUtils.getSharePreferencesBoolValue(MainActivity.SHOW_QSJX_ARTICLES);
        this.A = false;
        d = -1;
        if (!sharePreferencesBoolValue) {
            return;
        }
        if (this.j.size() <= 0 || !(this.j.get(0) instanceof Qsjx)) {
            Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("qsjx_articles");
            if (!TextUtils.isEmpty(sharePreferencesValue) && Looper.getMainLooper() == Looper.myLooper()) {
                SharePreferenceUtils.setSharePreferencesValue(MainActivity.SHOW_QSJX_ARTICLES, false);
                try {
                    Qsjx qsjx = new Qsjx();
                    qsjx.fromJsonObject(new JSONObject(sharePreferencesValue));
                    if (this.j.size() == 0 || !this.j.contains(this.K)) {
                        this.A = true;
                        a((Object) qsjx);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void U() {
        boolean sharePreferencesBoolValue = SharePreferenceUtils.getSharePreferencesBoolValue(MainActivity.SHOW_QSJX_ARTICLE);
        this.z = false;
        c = -1;
        if (!sharePreferencesBoolValue) {
            return;
        }
        if (this.j.size() <= 0 || !(this.j.get(0) instanceof Qsjx)) {
            Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("qsjx_article");
            if (!TextUtils.isEmpty(sharePreferencesValue) && Looper.getMainLooper() == Looper.myLooper()) {
                SharePreferenceUtils.setSharePreferencesValue(MainActivity.SHOW_QSJX_ARTICLE, false);
                try {
                    a((Object) new RssArticle(new JSONObject(sharePreferencesValue)));
                    this.z = true;
                    SharePreferenceUtils.remove("qsjx_article");
                    if (this.m != null && SharePreferenceUtils.getSharePreferencesBoolValue(MainActivity.SHOW_QSJX_ARTICLE_FIRST_OPEN)) {
                        b().notifyDataSetChanged();
                        this.m.postDelayed(this.R, 500);
                        SharePreferenceUtils.setSharePreferencesValue(MainActivity.SHOW_QSJX_ARTICLE_FIRST_OPEN, false);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (QiushibaikeException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
