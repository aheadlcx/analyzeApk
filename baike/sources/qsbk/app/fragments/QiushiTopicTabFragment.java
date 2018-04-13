package qsbk.app.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.base.BaseArticleListViewFragment;
import qsbk.app.cache.FileCache;
import qsbk.app.cache.MemoryCache;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.http.HttpTask;
import qsbk.app.im.datastore.DatabaseHelper$SyncMsgRow;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.model.ArticleListConfig;
import qsbk.app.model.QiushiTopic;
import qsbk.app.model.QiushiTopicBanner;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.Md5;
import qsbk.app.utils.QiushiTopicBus;
import qsbk.app.utils.QiushiTopicBus.QiushiTopicUpdateListener;
import qsbk.app.utils.ReadQiushi;
import qsbk.app.utils.SplashAdManager.SplashAdItem;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.qiushitopic.QiushiTopicBannerController;
import qsbk.app.widget.qiushitopic.QiushiTopicBannerController.OnTopicBannerLoadListener;
import qsbk.app.widget.qiushitopic.QiushiTopicRecommendController;

public class QiushiTopicTabFragment extends BaseArticleListViewFragment implements QiushiTopicUpdateListener, OnTopicBannerLoadListener {
    private static final String R = QiushiTopicTabFragment.class.getSimpleName();
    List<QiushiTopic> Q = new ArrayList();
    private List<QiushiTopicBanner> S = new ArrayList();

    class a implements OnAsyncLoadListener {
        final /* synthetic */ QiushiTopicTabFragment a;
        private String b = "";
        private String c = "";

        a(QiushiTopicTabFragment qiushiTopicTabFragment) {
            this.a = qiushiTopicTabFragment;
        }

        public void onPrepareListener() {
            this.a.y = true;
            StringBuffer stringBuffer = new StringBuffer(this.a.u);
            if (this.a.u.contains("?")) {
                stringBuffer.append("&page=").append(this.a.o);
            } else {
                stringBuffer.append("?page=").append(this.a.o);
            }
            if (UIHelper.isNightTheme()) {
                stringBuffer.append("&theme=night");
            }
            stringBuffer.append("&count=").append(30);
            String string = ReadQiushi.getString();
            if (string != null && string.length() > 2) {
                stringBuffer.append("&readarticles=").append(string);
            }
            this.b = stringBuffer.indexOf("?") == -1 ? this.a.u : stringBuffer.toString();
            this.a.a(1);
            QiushiTopicBannerController.load(this.a, 1);
        }

        public String onStartListener() {
            String str = "";
            try {
                str = MemoryCache.findOrCreateMemoryCache().get(Md5.MD5(this.b));
                if (TextUtils.isEmpty(str)) {
                    DebugUtil.debug(QiushiTopicTabFragment.R, "没有预加载，获取网络数据");
                    str = HttpClient.getIntentce().get(this.b);
                } else {
                    MemoryCache.findOrCreateMemoryCache().clear();
                }
            } catch (QiushibaikeException e) {
                this.a.a(0, false);
            } catch (Exception e2) {
                this.a.a(0, false);
            }
            DebugUtil.debug(QiushiTopicTabFragment.R, "loadContent:" + str);
            return str;
        }

        public void onFinishListener(String str) {
            if (!TextUtils.isEmpty(str) && !this.c.equals(DatabaseHelper$SyncMsgRow._PRE)) {
                boolean a = this.a.a(str);
                if (this.a.o == 2 && a) {
                    FileCache.cacheTextToDisk(this.a.B, this.a.v, str);
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
                if (this.a.B instanceof MainActivity) {
                    ((MainActivity) this.a.B).requestHideSmallTips(this.a);
                }
                this.a.a(0, true);
            } else if (TextUtils.isEmpty(str)) {
                if (this.a.B != null) {
                    ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "加载失败，请稍后再试！", Integer.valueOf(0)).show();
                }
                this.a.a(0, false);
            }
            if (this.c.equals(DatabaseHelper$SyncMsgRow._PRE) && DebugUtil.DEBUG) {
                Log.d(QiushiTopicTabFragment.R, "缓存预加载数据");
            }
        }
    }

    public static QiushiTopicTabFragment newInstance(ArticleListConfig articleListConfig) {
        QiushiTopicTabFragment qiushiTopicTabFragment = new QiushiTopicTabFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("config", articleListConfig);
        qiushiTopicTabFragment.setArguments(bundle);
        return qiushiTopicTabFragment;
    }

    protected void a() {
        super.a();
        ArticleListConfig articleListConfig = (ArticleListConfig) getArguments().getSerializable("config");
        if (articleListConfig == null || !Constants.CONTENT_DOMAINS.equalsIgnoreCase(Constants.QIUBAI_DOMAINS)) {
            this.u = Constants.QIUSHI_TOPIC_TAB;
            this.v = SplashAdItem.AD_QIUSHI_TOPIC;
            this.w = false;
        } else {
            this.u = articleListConfig.mUrl;
            this.v = articleListConfig.mUniqueName;
            this.w = articleListConfig.mIsShuffle;
        }
        DebugUtil.debug(R, "mUrl:" + this.u + " mUniqueName:" + this.v + " mIsShuffle:" + this.w);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        QiushiTopicBus.register(this);
        G();
    }

    public void onDestroy() {
        super.onDestroy();
        QiushiTopicBus.unregister(this);
    }

    protected boolean a(String str) {
        boolean a = super.a(str);
        c();
        return a;
    }

    void c() {
        if (this.Q != null && this.Q.size() > 0) {
            if (this.j.contains(this.Q)) {
                this.j.remove(this.Q);
            }
            this.j.add(0, this.Q);
        }
        if (this.S != null && this.S.size() > 0) {
            if (this.j.contains(this.S)) {
                this.j.remove(this.S);
            }
            this.j.add(0, this.S);
        }
        this.i.notifyDataSetChanged();
    }

    protected boolean u() {
        return false;
    }

    protected boolean o() {
        return false;
    }

    protected boolean r() {
        return false;
    }

    protected boolean p() {
        return false;
    }

    protected boolean s() {
        return false;
    }

    protected boolean m() {
        return false;
    }

    protected OnAsyncLoadListener b(String str) {
        return new a(this);
    }

    protected String w() {
        QiushiTopicRecommendController.load(new jg(this), 3);
        QiushiTopicBannerController.load(new jh(this), 3);
        return super.w();
    }

    public void initHistoryData() {
        super.initHistoryData();
    }

    private void a(int i) {
        new ji(this, i).execute(new Object[]{AsyncTask.THREAD_POOL_EXECUTOR, new Object[0]});
    }

    public String getFootViewTip() {
        return "订阅多了，内容就多了，去看看  >";
    }

    public OnClickListener getfootViewClickListener() {
        return new jj(this);
    }

    public void onQiushiTopicUpdate(QiushiTopic qiushiTopic, Object obj) {
        if (this.Q != null) {
            Object obj2 = null;
            for (int i = 0; i < this.Q.size(); i++) {
                QiushiTopic qiushiTopic2 = (QiushiTopic) this.Q.get(i);
                if (qiushiTopic2 != null && qiushiTopic2.id == qiushiTopic.id) {
                    qiushiTopic2.cloneFrom(qiushiTopic);
                    obj2 = 1;
                }
            }
            if (obj2 != null) {
                a((Object) this.Q);
            }
        }
    }

    public void onTopicBannerLoad(List<QiushiTopicBanner> list) {
        this.S.clear();
        this.S.addAll(list);
        c();
    }

    private void G() {
        new HttpTask(null, Constants.QIUSHI_TOPIC_BANNER, new jk(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void H() {
        a((Object) this.Q);
    }
}
