package qsbk.app.activity.base;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.android.phone.mrpc.core.Headers;
import com.baidu.mobstat.StatService;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.json.JSONArray;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.MainActivity;
import qsbk.app.activity.ManageQiuShiActivity;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.activity.OthersQiuShiActivity;
import qsbk.app.activity.ReAuthActivity;
import qsbk.app.ad.feedsad.FeedsAd;
import qsbk.app.ad.feedsad.IFeedsAdLoaded;
import qsbk.app.ad.feedsad.qbad.QbAdItem;
import qsbk.app.adapter.ArticleAdapter.AcrossChangeDate;
import qsbk.app.adapter.ArticleAdapter.ViewHolder;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.adapter.SubscribeAdapter;
import qsbk.app.cache.FileCache;
import qsbk.app.cache.MemoryCache;
import qsbk.app.core.AsyncTask;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.fragments.IArticleList;
import qsbk.app.fragments.IPageableFragment;
import qsbk.app.fragments.QiushiListFragment;
import qsbk.app.fragments.QiushiListFragment$QiushiCallback;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.datastore.DatabaseHelper$SyncMsgRow;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.model.AdBean;
import qsbk.app.model.Article;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.GroupRecommend;
import qsbk.app.model.GroupRecommend.GroupRecommendObserver;
import qsbk.app.model.LivePackage;
import qsbk.app.model.News;
import qsbk.app.model.QiushiEmpty;
import qsbk.app.model.Qsjx;
import qsbk.app.model.ReadLine;
import qsbk.app.model.RssArticle;
import qsbk.app.model.UserLoginGuideCard;
import qsbk.app.model.WelcomeCard;
import qsbk.app.share.ShareUtils;
import qsbk.app.slide.SlideActivity;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.CircleTopicManager.OnTopicUpdate;
import qsbk.app.utils.CollectionUtils;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.ListViewHelper;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.Md5;
import qsbk.app.utils.QiushiArticleBus;
import qsbk.app.utils.QiushiArticleBus.OnArticleActionListener;
import qsbk.app.utils.QiushiArticleBus.OnArticleUpdateListener;
import qsbk.app.utils.ReadQiushi;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.image.issue.Logger;
import qsbk.app.utils.image.issue.TaskExecutor;
import qsbk.app.utils.timer.ITimerProcessor;
import qsbk.app.utils.timer.TimerHelper;
import qsbk.app.video.VideoInListHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.RefreshTipView;
import qsbk.app.widget.TipsHelper;
import qsbk.app.widget.qbnews.recommend.NewsRecommendManager;

public abstract class BaseArticleListViewFragment extends Fragment implements OnScrollListener, IVotePoint, IFeedsAdLoaded, AcrossChangeDate, IArticleList, IPageableFragment, GroupRecommendObserver, OnTopicUpdate, OnArticleActionListener, OnArticleUpdateListener, ITimerProcessor, PtrListener {
    private static final String Q = BaseArticleListViewFragment.class.getSimpleName();
    private static boolean R = (!SharePreferenceUtils.getSharePreferencesBoolValue("user_remind_first_in_"));
    protected static final Handler a = new Handler(Looper.getMainLooper());
    protected static int b = 5000;
    protected static int c = -1;
    protected static int d = -1;
    protected boolean A = false;
    protected Activity B;
    protected GroupRecommend C;
    protected int D = 0;
    protected boolean E = false;
    protected VideoInListHelper F;
    protected RelativeLayout G;
    protected TextView H;
    protected TextView I;
    protected View J;
    protected QiushiEmpty K = new QiushiEmpty();
    protected boolean L = false;
    protected Article M = null;
    protected View N = null;
    protected View O;
    protected boolean P = false;
    private final BroadcastReceiver S = new a(this);
    private final BroadcastReceiver T = new n(this);
    private WeakReference<NativeMediaADData> U = null;
    private int[] V = new int[]{5, 7, 8};
    private int W = -1;
    private TimerHelper X;
    private boolean Y = false;
    private int Z = -1;
    private boolean aa = false;
    private int ab = 0;
    private int ac = 0;
    private int ad = 0;
    private boolean ae = false;
    protected String e = "";
    protected Article f;
    public int firstCount = this.V[0];
    public int firstPosition = (this.V[0] - 1);
    public View footView;
    protected Article g;
    protected boolean h;
    protected BaseImageAdapter i;
    protected ArrayList<Object> j = new ArrayList();
    protected int k = 0;
    protected PtrLayout l;
    public Long lastRefreshFirstPageTime = null;
    protected ListView m;
    protected TipsHelper n;
    protected int o = 1;
    protected int p = -1;
    protected boolean q = false;
    protected boolean r = false;
    protected boolean s = false;
    public int secondCount = (this.V[0] + this.V[1]);
    public int secondPosition = ((this.V[0] + this.V[1]) - 1);
    protected boolean t = false;
    public int thirdCount = ((this.V[0] + this.V[1]) + this.V[2]);
    public int thirdPosition = (((this.V[0] + this.V[1]) + this.V[2]) - 1);
    protected String u;
    protected String v;
    protected boolean w = false;
    protected AsyncDataLoader x = null;
    protected boolean y = false;
    protected boolean z = false;

    class a implements OnAsyncLoadListener {
        final /* synthetic */ BaseArticleListViewFragment a;
        private String b = "";
        private String c = "";

        public a(BaseArticleListViewFragment baseArticleListViewFragment, String str) {
            this.a = baseArticleListViewFragment;
            this.c = str;
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
        }

        public String onStartListener() {
            String str = "";
            try {
                str = MemoryCache.findOrCreateMemoryCache().get(Md5.MD5(this.b));
                if (TextUtils.isEmpty(str)) {
                    DebugUtil.debug(BaseArticleListViewFragment.Q, "没有预加载，获取网络数据");
                    str = HttpClient.getIntentce().get(this.b);
                } else {
                    MemoryCache.findOrCreateMemoryCache().clear();
                }
            } catch (QiushibaikeException e) {
                this.a.a(0, false);
            } catch (Exception e2) {
                this.a.a(0, false);
            }
            DebugUtil.debug(BaseArticleListViewFragment.Q, "loadContent:" + str);
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
                this.a.s = false;
                this.a.a(true);
                if (this.a.B instanceof MainActivity) {
                    ((MainActivity) this.a.B).requestHideSmallTips(this.a);
                }
            } else if (TextUtils.isEmpty(str)) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "加载失败，请稍后再试！", Integer.valueOf(0)).show();
                this.a.a(0, false);
            } else {
                this.a.a(0, false);
            }
            if (this.c.equals(DatabaseHelper$SyncMsgRow._PRE) && DebugUtil.DEBUG) {
                Log.d(BaseArticleListViewFragment.Q, "缓存预加载数据");
            }
        }
    }

    public void insertRecommendTopics() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x00c6 in list [B:26:0x00c1]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
*/
        /*
        r8 = this;
        r4 = 3;
        r5 = 2;
        r1 = 1;
        r2 = 0;
        r0 = r8.j;
        if (r0 == 0) goto L_0x00c6;
    L_0x0008:
        r0 = r8.j;	 Catch:{ all -> 0x018b }
        r0 = r0.size();	 Catch:{ all -> 0x018b }
        r3 = r8.thirdCount;	 Catch:{ all -> 0x018b }
        r6 = r8.D;	 Catch:{ all -> 0x018b }
        r6 = r6 * 30;	 Catch:{ all -> 0x018b }
        r3 = r3 + r6;	 Catch:{ all -> 0x018b }
        if (r0 <= r3) goto L_0x00c7;	 Catch:{ all -> 0x018b }
    L_0x0017:
        r0 = r4;	 Catch:{ all -> 0x018b }
    L_0x0018:
        r3 = r8.D;	 Catch:{ all -> 0x018b }
        r6 = qsbk.app.utils.CircleTopicManager.getCircleTopic(r3, r0);	 Catch:{ all -> 0x018b }
        r3 = qsbk.app.utils.CircleTopicManager.getRandomBanner();	 Catch:{ all -> 0x018b }
        r0 = r8.getSecondLivePackage();	 Catch:{ all -> 0x018b }
        if (r6 == 0) goto L_0x00bd;	 Catch:{ all -> 0x018b }
    L_0x0028:
        r7 = r6.size();	 Catch:{ all -> 0x018b }
        if (r7 <= 0) goto L_0x00bd;	 Catch:{ all -> 0x018b }
    L_0x002e:
        r7 = r6.size();	 Catch:{ all -> 0x018b }
        if (r7 < r4) goto L_0x0126;	 Catch:{ all -> 0x018b }
    L_0x0034:
        r4 = r8.j;	 Catch:{ all -> 0x018b }
        r5 = r8.thirdPosition;	 Catch:{ all -> 0x018b }
        r7 = r8.D;	 Catch:{ all -> 0x018b }
        r7 = r7 * 30;	 Catch:{ all -> 0x018b }
        r5 = r5 + r7;	 Catch:{ all -> 0x018b }
        if (r0 == 0) goto L_0x0113;	 Catch:{ all -> 0x018b }
    L_0x003f:
        r4.add(r5, r0);	 Catch:{ all -> 0x018b }
        r0 = r8.j;	 Catch:{ all -> 0x018b }
        r4 = r8.secondPosition;	 Catch:{ all -> 0x018b }
        r5 = r8.D;	 Catch:{ all -> 0x018b }
        r5 = r5 * 30;	 Catch:{ all -> 0x018b }
        r4 = r4 + r5;	 Catch:{ all -> 0x018b }
        r5 = 1;	 Catch:{ all -> 0x018b }
        r5 = r6.get(r5);	 Catch:{ all -> 0x018b }
        r0.add(r4, r5);	 Catch:{ all -> 0x018b }
        r4 = r8.j;	 Catch:{ all -> 0x018b }
        r0 = r8.firstPosition;	 Catch:{ all -> 0x018b }
        r5 = r8.D;	 Catch:{ all -> 0x018b }
        r5 = r5 * 30;	 Catch:{ all -> 0x018b }
        r5 = r5 + r0;	 Catch:{ all -> 0x018b }
        r0 = qsbk.app.utils.CircleTopicManager.isShowTopic;	 Catch:{ all -> 0x018b }
        if (r0 != 0) goto L_0x011c;	 Catch:{ all -> 0x018b }
    L_0x0060:
        if (r3 == 0) goto L_0x011c;	 Catch:{ all -> 0x018b }
    L_0x0062:
        r4.add(r5, r3);	 Catch:{ all -> 0x018b }
        r0 = r8.needSubscribeReport();	 Catch:{ all -> 0x018b }
        if (r0 == 0) goto L_0x00b0;	 Catch:{ all -> 0x018b }
    L_0x006b:
        r0 = 2;	 Catch:{ all -> 0x018b }
        r0 = r6.get(r0);	 Catch:{ all -> 0x018b }
        r0 = (qsbk.app.model.CircleTopic) r0;	 Catch:{ all -> 0x018b }
        r0 = r0.hashCode();	 Catch:{ all -> 0x018b }
        r3 = "topic";	 Catch:{ all -> 0x018b }
        r4 = r8.thirdPosition;	 Catch:{ all -> 0x018b }
        r5 = r8.D;	 Catch:{ all -> 0x018b }
        r5 = r5 * 30;	 Catch:{ all -> 0x018b }
        r4 = r4 + r5;	 Catch:{ all -> 0x018b }
        qsbk.app.utils.SubscribeReportHelper.addRecord(r0, r3, r4);	 Catch:{ all -> 0x018b }
        r0 = 1;	 Catch:{ all -> 0x018b }
        r0 = r6.get(r0);	 Catch:{ all -> 0x018b }
        r0 = (qsbk.app.model.CircleTopic) r0;	 Catch:{ all -> 0x018b }
        r0 = r0.hashCode();	 Catch:{ all -> 0x018b }
        r3 = "topic";	 Catch:{ all -> 0x018b }
        r4 = r8.secondPosition;	 Catch:{ all -> 0x018b }
        r5 = r8.D;	 Catch:{ all -> 0x018b }
        r5 = r5 * 30;	 Catch:{ all -> 0x018b }
        r4 = r4 + r5;	 Catch:{ all -> 0x018b }
        qsbk.app.utils.SubscribeReportHelper.addRecord(r0, r3, r4);	 Catch:{ all -> 0x018b }
        r0 = 0;	 Catch:{ all -> 0x018b }
        r0 = r6.get(r0);	 Catch:{ all -> 0x018b }
        r0 = (qsbk.app.model.CircleTopic) r0;	 Catch:{ all -> 0x018b }
        r0 = r0.hashCode();	 Catch:{ all -> 0x018b }
        r3 = "topic";	 Catch:{ all -> 0x018b }
        r4 = r8.firstPosition;	 Catch:{ all -> 0x018b }
        r5 = r8.D;	 Catch:{ all -> 0x018b }
        r5 = r5 * 30;	 Catch:{ all -> 0x018b }
        r4 = r4 + r5;	 Catch:{ all -> 0x018b }
        qsbk.app.utils.SubscribeReportHelper.addRecord(r0, r3, r4);	 Catch:{ all -> 0x018b }
    L_0x00b0:
        r0 = r8.D;	 Catch:{ all -> 0x018b }
        r0 = r0 + 1;	 Catch:{ all -> 0x018b }
        r8.D = r0;	 Catch:{ all -> 0x018b }
        r0 = qsbk.app.utils.CircleTopicManager.isShowTopic;	 Catch:{ all -> 0x018b }
        if (r0 != 0) goto L_0x01df;	 Catch:{ all -> 0x018b }
    L_0x00ba:
        r0 = r1;	 Catch:{ all -> 0x018b }
    L_0x00bb:
        qsbk.app.utils.CircleTopicManager.isShowTopic = r0;	 Catch:{ all -> 0x018b }
    L_0x00bd:
        r0 = r8.i;
        if (r0 == 0) goto L_0x00c6;
    L_0x00c1:
        r0 = r8.i;
        r0.notifyDataSetChanged();
    L_0x00c6:
        return;
    L_0x00c7:
        r0 = r8.j;	 Catch:{ all -> 0x018b }
        r0 = r0.size();	 Catch:{ all -> 0x018b }
        r3 = r8.secondCount;	 Catch:{ all -> 0x018b }
        r6 = r8.D;	 Catch:{ all -> 0x018b }
        r6 = r6 * 30;	 Catch:{ all -> 0x018b }
        r3 = r3 + r6;	 Catch:{ all -> 0x018b }
        if (r0 <= r3) goto L_0x00e8;	 Catch:{ all -> 0x018b }
    L_0x00d6:
        r0 = r8.j;	 Catch:{ all -> 0x018b }
        r0 = r0.size();	 Catch:{ all -> 0x018b }
        r3 = r8.thirdCount;	 Catch:{ all -> 0x018b }
        r6 = r8.D;	 Catch:{ all -> 0x018b }
        r6 = r6 * 30;	 Catch:{ all -> 0x018b }
        r3 = r3 + r6;	 Catch:{ all -> 0x018b }
        if (r0 > r3) goto L_0x00e8;	 Catch:{ all -> 0x018b }
    L_0x00e5:
        r0 = r5;	 Catch:{ all -> 0x018b }
        goto L_0x0018;	 Catch:{ all -> 0x018b }
    L_0x00e8:
        r0 = r8.j;	 Catch:{ all -> 0x018b }
        r0 = r0.size();	 Catch:{ all -> 0x018b }
        r3 = r8.firstCount;	 Catch:{ all -> 0x018b }
        r6 = r8.D;	 Catch:{ all -> 0x018b }
        r6 = r6 * 30;	 Catch:{ all -> 0x018b }
        r3 = r3 + r6;	 Catch:{ all -> 0x018b }
        if (r0 <= r3) goto L_0x0109;	 Catch:{ all -> 0x018b }
    L_0x00f7:
        r0 = r8.j;	 Catch:{ all -> 0x018b }
        r0 = r0.size();	 Catch:{ all -> 0x018b }
        r3 = r8.secondCount;	 Catch:{ all -> 0x018b }
        r6 = r8.D;	 Catch:{ all -> 0x018b }
        r6 = r6 * 30;
        r3 = r3 + r6;
        if (r0 > r3) goto L_0x0109;
    L_0x0106:
        r0 = r1;
        goto L_0x0018;
    L_0x0109:
        r0 = r8.i;
        if (r0 == 0) goto L_0x00c6;
    L_0x010d:
        r0 = r8.i;
        r0.notifyDataSetChanged();
        goto L_0x00c6;
    L_0x0113:
        r0 = 2;
        r0 = r6.get(r0);	 Catch:{ all -> 0x018b }
        r0 = (qsbk.app.model.QbBean) r0;	 Catch:{ all -> 0x018b }
        goto L_0x003f;	 Catch:{ all -> 0x018b }
    L_0x011c:
        r0 = 0;	 Catch:{ all -> 0x018b }
        r0 = r6.get(r0);	 Catch:{ all -> 0x018b }
        r0 = (qsbk.app.model.QbBean) r0;	 Catch:{ all -> 0x018b }
        r3 = r0;	 Catch:{ all -> 0x018b }
        goto L_0x0062;	 Catch:{ all -> 0x018b }
    L_0x0126:
        r0 = r6.size();	 Catch:{ all -> 0x018b }
        if (r0 < r5) goto L_0x019f;	 Catch:{ all -> 0x018b }
    L_0x012c:
        r0 = r6.size();	 Catch:{ all -> 0x018b }
        if (r0 >= r4) goto L_0x019f;	 Catch:{ all -> 0x018b }
    L_0x0132:
        r0 = r8.j;	 Catch:{ all -> 0x018b }
        r4 = r8.secondPosition;	 Catch:{ all -> 0x018b }
        r5 = r8.D;	 Catch:{ all -> 0x018b }
        r5 = r5 * 30;	 Catch:{ all -> 0x018b }
        r4 = r4 + r5;	 Catch:{ all -> 0x018b }
        r5 = 1;	 Catch:{ all -> 0x018b }
        r5 = r6.get(r5);	 Catch:{ all -> 0x018b }
        r0.add(r4, r5);	 Catch:{ all -> 0x018b }
        r4 = r8.j;	 Catch:{ all -> 0x018b }
        r0 = r8.firstPosition;	 Catch:{ all -> 0x018b }
        r5 = r8.D;	 Catch:{ all -> 0x018b }
        r5 = r5 * 30;	 Catch:{ all -> 0x018b }
        r5 = r5 + r0;	 Catch:{ all -> 0x018b }
        r0 = qsbk.app.utils.CircleTopicManager.isShowTopic;	 Catch:{ all -> 0x018b }
        if (r0 != 0) goto L_0x0196;	 Catch:{ all -> 0x018b }
    L_0x0150:
        if (r3 == 0) goto L_0x0196;	 Catch:{ all -> 0x018b }
    L_0x0152:
        r4.add(r5, r3);	 Catch:{ all -> 0x018b }
        r0 = r8.needSubscribeReport();	 Catch:{ all -> 0x018b }
        if (r0 == 0) goto L_0x00b0;	 Catch:{ all -> 0x018b }
    L_0x015b:
        r0 = 1;	 Catch:{ all -> 0x018b }
        r0 = r6.get(r0);	 Catch:{ all -> 0x018b }
        r0 = (qsbk.app.model.CircleTopic) r0;	 Catch:{ all -> 0x018b }
        r0 = r0.hashCode();	 Catch:{ all -> 0x018b }
        r3 = "topic";	 Catch:{ all -> 0x018b }
        r4 = r8.secondPosition;	 Catch:{ all -> 0x018b }
        r5 = r8.D;	 Catch:{ all -> 0x018b }
        r5 = r5 * 30;	 Catch:{ all -> 0x018b }
        r4 = r4 + r5;	 Catch:{ all -> 0x018b }
        qsbk.app.utils.SubscribeReportHelper.addRecord(r0, r3, r4);	 Catch:{ all -> 0x018b }
        r0 = 0;	 Catch:{ all -> 0x018b }
        r0 = r6.get(r0);	 Catch:{ all -> 0x018b }
        r0 = (qsbk.app.model.CircleTopic) r0;	 Catch:{ all -> 0x018b }
        r0 = r0.hashCode();	 Catch:{ all -> 0x018b }
        r3 = "topic";	 Catch:{ all -> 0x018b }
        r4 = r8.firstPosition;	 Catch:{ all -> 0x018b }
        r5 = r8.D;	 Catch:{ all -> 0x018b }
        r5 = r5 * 30;	 Catch:{ all -> 0x018b }
        r4 = r4 + r5;	 Catch:{ all -> 0x018b }
        qsbk.app.utils.SubscribeReportHelper.addRecord(r0, r3, r4);	 Catch:{ all -> 0x018b }
        goto L_0x00b0;
    L_0x018b:
        r0 = move-exception;
        r1 = r8.i;
        if (r1 == 0) goto L_0x0195;
    L_0x0190:
        r1 = r8.i;
        r1.notifyDataSetChanged();
    L_0x0195:
        throw r0;
    L_0x0196:
        r0 = 0;
        r0 = r6.get(r0);	 Catch:{ all -> 0x018b }
        r0 = (qsbk.app.model.QbBean) r0;	 Catch:{ all -> 0x018b }
        r3 = r0;	 Catch:{ all -> 0x018b }
        goto L_0x0152;	 Catch:{ all -> 0x018b }
    L_0x019f:
        r0 = r6.size();	 Catch:{ all -> 0x018b }
        if (r0 != r1) goto L_0x00b0;	 Catch:{ all -> 0x018b }
    L_0x01a5:
        r4 = r8.j;	 Catch:{ all -> 0x018b }
        r0 = r8.firstPosition;	 Catch:{ all -> 0x018b }
        r5 = r8.D;	 Catch:{ all -> 0x018b }
        r5 = r5 * 30;	 Catch:{ all -> 0x018b }
        r5 = r5 + r0;	 Catch:{ all -> 0x018b }
        r0 = qsbk.app.utils.CircleTopicManager.isShowTopic;	 Catch:{ all -> 0x018b }
        if (r0 != 0) goto L_0x01d7;	 Catch:{ all -> 0x018b }
    L_0x01b2:
        if (r3 == 0) goto L_0x01d7;	 Catch:{ all -> 0x018b }
    L_0x01b4:
        r0 = r3;	 Catch:{ all -> 0x018b }
    L_0x01b5:
        r4.add(r5, r0);	 Catch:{ all -> 0x018b }
        r0 = r8.needSubscribeReport();	 Catch:{ all -> 0x018b }
        if (r0 == 0) goto L_0x00b0;	 Catch:{ all -> 0x018b }
    L_0x01be:
        r0 = 0;	 Catch:{ all -> 0x018b }
        r0 = r6.get(r0);	 Catch:{ all -> 0x018b }
        r0 = (qsbk.app.model.CircleTopic) r0;	 Catch:{ all -> 0x018b }
        r0 = r0.hashCode();	 Catch:{ all -> 0x018b }
        r3 = "topic";	 Catch:{ all -> 0x018b }
        r4 = r8.firstPosition;	 Catch:{ all -> 0x018b }
        r5 = r8.D;	 Catch:{ all -> 0x018b }
        r5 = r5 * 30;	 Catch:{ all -> 0x018b }
        r4 = r4 + r5;	 Catch:{ all -> 0x018b }
        qsbk.app.utils.SubscribeReportHelper.addRecord(r0, r3, r4);	 Catch:{ all -> 0x018b }
        goto L_0x00b0;	 Catch:{ all -> 0x018b }
    L_0x01d7:
        r0 = 0;	 Catch:{ all -> 0x018b }
        r0 = r6.get(r0);	 Catch:{ all -> 0x018b }
        r0 = (qsbk.app.model.QbBean) r0;	 Catch:{ all -> 0x018b }
        goto L_0x01b5;
    L_0x01df:
        r0 = r2;
        goto L_0x00bb;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.activity.base.BaseArticleListViewFragment.insertRecommendTopics():void");
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.B = activity;
        if (this.B == null) {
            MainActivity.mInstance.reload();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FeedsAd.getInstance().registerListener(this);
        QiushiArticleBus.register(this);
        QiushiArticleBus.registerOnArticleActionListener(this);
        LocalBroadcastManager.getInstance(this.B).registerReceiver(this.S, new IntentFilter(MainActivity.ACTION_QB_LOGIN));
        LocalBroadcastManager.getInstance(this.B).registerReceiver(this.T, new IntentFilter(MyInfoActivity.CHANGE_REMARK));
        if (m()) {
            GroupRecommend.register(this);
        }
        if (u()) {
            CircleTopicManager.register(this);
            D();
        }
    }

    protected void a() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        DebugUtil.debug(Q, "onCreateView...");
        a();
        View inflate = layoutInflater.inflate(R.layout.layout_ptr_listview, null);
        a(inflate);
        if (UIHelper.isNightTheme()) {
            this.m.setDivider(new ColorDrawable(-16777216));
            this.m.setDividerHeight((int) (getResources().getDisplayMetrics().density / 2.0f));
        } else {
            this.m.setDivider(null);
            this.m.setDividerHeight(0);
        }
        this.F = new p(this, this.m);
        this.F.setEnable(false);
        if (isSelected()) {
            this.F.autoPlay();
        } else {
            this.F.stopAll();
        }
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.j.size() == 0) {
            for (int i = 0; i < 5; i++) {
                this.j.add(this.K);
            }
            this.i.notifyDataSetChanged();
            d();
        }
        j();
    }

    protected void e() {
        if (this.X == null) {
            this.X = new TimerHelper(this, RefreshTipView.SECOND_REFRESH_INTERVAL, RefreshTipView.SECOND_REFRESH_INTERVAL);
        }
        this.X.startTimer();
    }

    protected void f() {
        this.Y = false;
        if (this.X != null) {
            this.X.stopTimer();
            this.X = null;
        }
    }

    public void doPause() {
        Logger.getInstance().debug(Q, "doPause", this + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (this.F != null) {
            this.F.stopAll();
        }
    }

    public void doResume() {
        Logger.getInstance().debug(Q, "doResume", this + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (this.i != null) {
            this.i.onResume();
            this.i.notifyDataSetChanged();
        }
        if (this.B instanceof QiushiListFragment$QiushiCallback) {
            ((QiushiListFragment$QiushiCallback) this.B).onResume(this);
        }
        e();
        ReadQiushi.setFirstArticleRead(this.j);
    }

    public boolean isSelected() {
        return this.aa;
    }

    public void setSelected(boolean z) {
        this.aa = z;
        c();
    }

    private void c() {
        if (this.F == null) {
            return;
        }
        if (this.aa) {
            this.l.post(new q(this));
        } else {
            this.F.stopAll();
        }
    }

    public void doStop() {
        Logger.getInstance().debug(Q, "doStop()", this + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        if (this.i != null) {
            this.i.onStop();
        }
        f();
    }

    public void onStart() {
        super.onStart();
    }

    public void onResume() {
        super.onResume();
        this.P = true;
        if (this.t) {
            this.t = false;
        }
        if (this.N != null) {
            View findViewById = this.N.findViewById(R.id.layerMask);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            this.N = null;
        }
        if (QsbkApp.getInstance().hasContentTextSizeChange() && this.i != null) {
            DebugUtil.debug(Q, "textSize change and change textSize~");
            this.i.notifyDataSetChanged();
        }
        if (this.i != null) {
            this.i.setImageLoadWay(SharePreferenceUtils.getSharePreferencesValue("imageLoadWay"));
        }
        if (this.i != null) {
            this.i.onResume();
        }
    }

    protected boolean g() {
        return true;
    }

    public void onPause() {
        super.onPause();
        DebugUtil.debug(Q, "onPause " + this.v);
        if (g()) {
            ListViewHelper.onSaveListViewFirstVisibleItem(this.B, this.m, this.i, this.v, true);
        }
        if (this.F != null) {
            this.F.stopAll();
        }
    }

    public void onStop() {
        boolean z = false;
        ReportArticle.save2Local();
        this.P = false;
        super.onStop();
        LogUtil.d("onStop:" + this.v);
        if (this.i != null) {
            this.i.onStop();
        }
        if (this.footView != null) {
            if (this.footView.getVisibility() == 0) {
                z = true;
            }
            this.E = z;
        }
    }

    protected void h() {
        if ((this.B instanceof QiushiListFragment$QiushiCallback) && isSelected()) {
            StatService.onEvent(this.B, "tab_refresh", "show");
            ((QiushiListFragment$QiushiCallback) this.B).onNewQiushi(this);
        }
    }

    protected void i() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            h();
        } else {
            a.post(new r(this));
        }
    }

    public void process() {
        if (isSelected() && !this.s) {
            if (this.lastRefreshFirstPageTime == null || System.currentTimeMillis() - this.lastRefreshFirstPageTime.longValue() >= RefreshTipView.SECOND_REFRESH_INTERVAL) {
                this.Y = true;
                i();
            }
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onDestroy() {
        QiushiArticleBus.unregister(this);
        QiushiArticleBus.unregisterOnArticleActionListener(this);
        GroupRecommend.unregister(this);
        CircleTopicManager.unregister(this);
        LocalBroadcastManager.getInstance(this.B).unregisterReceiver(this.S);
        LocalBroadcastManager.getInstance(this.B).unregisterReceiver(this.T);
        super.onDestroy();
        if (this.i != null) {
            this.i.clearImageCache();
        }
        if (this.j != null) {
            this.j.clear();
        }
        if (this.x != null) {
            this.x.cancel(true);
        }
        if (this.l != null) {
            this.l.setPtrListener(null);
            this.l.setOnScrollListener(null);
            this.l.setOnScrollOffsetListener(null);
        }
        FeedsAd.getInstance().unRegisterListener(this);
        this.B = null;
    }

    public void onDetach() {
        super.onDetach();
    }

    protected BaseImageAdapter b() {
        return new SubscribeAdapter(this.B, this.m, this.j, getVotePoint(), this.v, this);
    }

    protected void a(View view) {
        int i;
        int i2 = -14013903;
        this.l = (PtrLayout) view.findViewById(R.id.ptr);
        this.m = (ListView) view.findViewById(R.id.listview);
        this.l.setLoadMoreEnable(false);
        this.l.setPtrListener(this);
        this.l.setOnScrollListener(this);
        this.i = b();
        this.footView = LayoutInflater.from(getActivity()).inflate(R.layout.qiushi_listview_foot, null);
        LinearLayout linearLayout = (LinearLayout) this.footView.findViewById(R.id.foot_lin);
        View findViewById = this.footView.findViewById(R.id.foot_left_line);
        View findViewById2 = this.footView.findViewById(R.id.foot_right_line);
        TextView textView = (TextView) this.footView.findViewById(R.id.foot_remind);
        linearLayout.setBackgroundColor(UIHelper.isNightTheme() ? -15132387 : -855310);
        if (UIHelper.isNightTheme()) {
            i = -14013903;
        } else {
            i = -2236961;
        }
        findViewById.setBackgroundColor(i);
        if (!UIHelper.isNightTheme()) {
            i2 = -2236961;
        }
        findViewById2.setBackgroundColor(i2);
        textView.setTextColor(UIHelper.isNightTheme() ? -12829625 : -5197644);
        textView.setText(getFootViewTip());
        this.m.addFooterView(this.footView);
        this.footView.setOnClickListener(getfootViewClickListener());
        this.footView.setVisibility(this.E ? 0 : 8);
        this.m.setAdapter(this.i);
    }

    protected void d() {
        DebugUtil.debug(Q, "initData...");
        if (x()) {
            initHistoryData();
            if (!ListViewHelper.isOutSizeIntervalOfPage(this.B, this.v, -1)) {
                this.o = Math.max(ListViewHelper.getSaveLastPage(this.B, this.v) + 1, this.o);
                return;
            }
            return;
        }
        this.l.refresh();
    }

    protected void j() {
        l();
        k();
        F();
        ReadQiushi.trackListView(this.l);
    }

    private void F() {
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && (parentFragment instanceof QiushiListFragment)) {
            QiushiListFragment qiushiListFragment = (QiushiListFragment) parentFragment;
            this.l.setOnScrollOffsetListener(new s(this, qiushiListFragment.getQiushiNotificationView(), qiushiListFragment.getActivityNotification(), qiushiListFragment));
        }
    }

    protected void k() {
        this.m.setOnItemClickListener(new v(this));
    }

    protected void l() {
        this.m.setOnItemLongClickListener(new w(this));
    }

    protected boolean m() {
        return getParentFragment() != null && (getParentFragment() instanceof QiushiListFragment);
    }

    private void G() {
        boolean z = false;
        if (this.O != null && this.O.getTag().equals("active")) {
            z = true;
        }
        ShareUtils.openShareDialog(getParentFragment() == null ? this : getParentFragment(), this.B, 1, z, this.M, this.O);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        View findViewById;
        if (this.N != null) {
            findViewById = this.N.findViewById(R.id.layerMask);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            this.N = null;
        }
        ShareUtils shareUtils = new ShareUtils();
        if (i2 >= 1) {
            if (this.M != null) {
                if (i == 1) {
                    if (i2 == 6 && this.O.getTag().equals("enable")) {
                        a(this.M);
                    } else if (i2 == 11) {
                        if (this.j.contains(this.M)) {
                            delete(this.M);
                        }
                    } else if (i2 == 12) {
                        if (this.j.contains(this.M)) {
                            findViewById = this.m.getChildAt((this.j.indexOf(this.M) - this.m.getFirstVisiblePosition()) + this.m.getHeaderViewsCount());
                            if (findViewById != null) {
                                ViewHolder viewHolder = (ViewHolder) findViewById.getTag();
                                if (QsbkApp.getInstance().isAutoPlayVideo()) {
                                    viewHolder.videoPlayer.download();
                                } else {
                                    viewHolder.videoPlayer.downloadWithoutPlay();
                                }
                            }
                        }
                    } else if (i2 == 13) {
                        if (this.j.contains(this.M)) {
                            anonymous(this.M);
                        }
                    } else if (i2 == 14) {
                        if (this.j.contains(this.M)) {
                            forbid(this.M);
                        }
                    } else if (i2 == 15) {
                        ShareUtils.shareArticle2QiuyouCircle(this.B, this.M);
                    } else {
                        ShareUtils.doShare(i2, this.B, this, this.M, this.O);
                    }
                } else if (i == 2) {
                    ShareUtils.Share(this.B, this.M.id, i2);
                } else if (i == 3) {
                    report(this.M, i2);
                }
            }
            if (i2 == SlideActivity.SLIDE_REQUEST) {
                this.j = QsbkApp.currentDataSource;
                b().notifyDataSetChanged();
                int intExtra = intent.getIntExtra("position", 0) + this.m.getHeaderViewsCount();
                this.o = intent.getIntExtra(ParamKey.PAGE, this.o);
                this.m.post(new x(this, intExtra));
            }
            super.onActivityResult(i, i2, intent);
        }
    }

    protected void a(Article article) {
        if (QsbkApp.currentUser == null) {
            LoginPermissionClickDelegate.startLoginActivity(this.B);
        } else {
            ShareUtils.collection(article.id, !QsbkApp.allCollection.contains(article.id));
        }
    }

    protected int n() {
        return 0;
    }

    private void H() {
        if (this.m != null && o()) {
            int i = this.k > 0 ? this.k : (this.o - 1) * 30;
            if (this.lastRefreshFirstPageTime != null) {
                i -= n();
            }
            while (i + 6 >= this.j.size()) {
                i -= 30;
            }
            Log.e(getClass().getSimpleName(), "feedsad qbad anchor：" + i + "==loadPageNo：" + this.o + "==data size：" + this.j.size() + "==Thread：" + Thread.currentThread().getName());
            FeedsAd.getInstance().insertFeedAd(i, this.j, isSelected());
        }
    }

    protected boolean o() {
        return true;
    }

    protected boolean p() {
        return false;
    }

    protected void q() {
        List<News> recommendNews = NewsRecommendManager.getInstance().getRecommendNews(2);
        if (recommendNews != null) {
            for (News news : recommendNews) {
                int i;
                if (NewsRecommendManager.getLastInsertPosition(this.v) <= 0) {
                    i = 16;
                } else {
                    i = NewsRecommendManager.getLastInsertPosition(this.v) + 16;
                }
                if (this.j.size() > i) {
                    this.j.add(i, news);
                    NewsRecommendManager.setLastInsertPosition(this.v, i + 1);
                } else {
                    return;
                }
            }
        }
    }

    protected boolean r() {
        return true;
    }

    protected boolean s() {
        return true;
    }

    protected boolean t() {
        return MainActivity.needShowRemixOrChecken;
    }

    protected boolean u() {
        return true;
    }

    public void onFeedsAdLoaded() {
        if (!this.y && this.m != null && o()) {
            v();
        }
    }

    protected void v() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            if (!(this.z || this.A)) {
                z();
            }
            H();
            if (!(!(this.B instanceof MainActivity) || this.h || this.g == null)) {
                int indexOf = this.j.indexOf(this.g);
                if (indexOf >= 0 && (indexOf != 0 || this.j.size() <= 0 || (this.j.get(1) instanceof Article))) {
                    this.h = true;
                    this.j.add(indexOf, new ReadLine());
                }
            }
            this.s = false;
            a(true);
        }
    }

    protected String w() {
        return FileCache.getContentFromDisk(QsbkApp.getInstance().getApplicationContext(), this.v);
    }

    protected boolean x() {
        return ListViewHelper.canSelectionSaveable(this.B);
    }

    protected boolean y() {
        return true;
    }

    protected void z() {
        if (("text".equalsIgnoreCase(this.v) && !HttpUtils.isWifi(this.B)) || !isSelected() || !o() || !t()) {
            return;
        }
        if ((this.o == 1 || this.j.size() <= 36) && this.j.size() > 0 && !(this.j.get(0) instanceof QbAdItem)) {
            Object topItemWithAd = FeedsAd.getInstance().getTopItemWithAd(1);
            if (topItemWithAd != null) {
                a(topItemWithAd);
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
            if (optInt == 0 || !isSelected()) {
                JSONArray optJSONArray = jSONObject.optJSONArray("items");
                this.p = jSONObject.optInt("total");
                if (this.p == 0 && (optJSONArray == null || (optJSONArray != null && optJSONArray.length() == 0))) {
                    this.q = true;
                    this.l.setLoadMoreEnable(false);
                    this.footView.setVisibility(8);
                    return false;
                } else if (this.p <= 0 || (optJSONArray != null && (optJSONArray == null || optJSONArray.length() != 0))) {
                    if (this.p > ((this.o - 1) * 30) + optJSONArray.length()) {
                        this.footView.setVisibility(8);
                        this.l.setLoadMoreEnable(true);
                        this.q = false;
                    } else {
                        this.footView.setVisibility(0);
                        this.l.setLoadMoreEnable(false);
                        this.q = true;
                    }
                    if (jSONObject.optInt(Headers.REFRESH) > 0 && this.r && isSelected() && this.e.equals("top_refresh")) {
                        ToastAndDialog.makePositiveToast(QsbkApp.mContext, String.format("为您刷新了%s条糗事", new Object[]{Integer.valueOf(optInt)}), Integer.valueOf(0)).show();
                    }
                    int length = optJSONArray.length();
                    if (length == 0) {
                        this.q = true;
                    }
                    if (this.e.equals("top_refresh") || (this.o == 1 && !x())) {
                        this.j.clear();
                        this.D = 0;
                        A();
                        if (p()) {
                            NewsRecommendManager.setLastInsertPosition(this.v, -1);
                        }
                    }
                    this.k = this.j.size();
                    int i = 0;
                    while (i < length) {
                        try {
                            if (optJSONArray.optJSONObject(i) != null) {
                                Article rssArticle;
                                if (y()) {
                                    rssArticle = new RssArticle(optJSONArray.optJSONObject(i));
                                } else {
                                    rssArticle = new Article(optJSONArray.optJSONObject(i));
                                }
                                if (this.e.equals("top_refresh") && i == 0 && !(getActivity() instanceof OthersQiuShiActivity) && !(getActivity() instanceof ManageQiuShiActivity)) {
                                    this.h = false;
                                    if (this.f != null) {
                                        this.g = this.f;
                                    }
                                    this.f = rssArticle;
                                }
                                if (!(this.j.contains(rssArticle) || ReportArticle.mReportArtcicle.keySet().contains(rssArticle.id))) {
                                    this.j.add(rssArticle);
                                }
                            }
                        } catch (QiushibaikeException e) {
                        }
                        i++;
                    }
                    if (this.o == 1 && this.w) {
                        DebugUtil.debug(Q, "sort:重新排序，来源:" + this.v);
                        sort(this.j);
                    }
                    if (m() && this.o == 1 && this.C != null && this.j.size() > 15) {
                        this.j.add(15, this.C);
                    }
                    if (u()) {
                        insertRecommendTopics();
                    }
                    if (p()) {
                        q();
                    }
                    if (o()) {
                        v();
                    }
                    if (!jSONObject.isNull("ads")) {
                        a(jSONObject.getJSONArray("ads"));
                    }
                    this.j.remove(UserLoginGuideCard.instance);
                    if (UserLoginGuideCard.isNeedToShow() && UserLoginGuideCard.getPosition() < this.j.size()) {
                        this.j.add(UserLoginGuideCard.getPosition(), UserLoginGuideCard.getInstance(getActivity()));
                    }
                    if (!this.q) {
                        this.o++;
                    }
                    this.i.notifyDataSetChanged();
                    if (this.P) {
                        c();
                    }
                    return true;
                } else {
                    this.q = true;
                    this.l.setLoadMoreEnable(false);
                    this.footView.setVisibility(0);
                    return false;
                }
            } else if (optInt != SimpleHttpTask.ERROR_DOUBLE_LOGIN.intValue() || QsbkApp.currentUser == null) {
                Object optString = jSONObject.optString("err_msg");
                if (TextUtils.isEmpty(optString)) {
                    return false;
                }
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, optString, Integer.valueOf(0)).show();
                return false;
            } else {
                QsbkApp.mContext.startActivity(ReAuthActivity.getIntent(QsbkApp.mContext));
                return false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void sort(ArrayList<Object> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            for (int i2 = 1; i2 < arrayList.size(); i2++) {
                if (((Article) arrayList.get(i2 - 1)).random.compareTo(((Article) arrayList.get(i2)).random) > 0) {
                    Article article = (Article) arrayList.get(i2 - 1);
                    arrayList.set(i2 - 1, arrayList.get(i2));
                    arrayList.set(i2, article);
                }
            }
        }
    }

    protected void a(JSONArray jSONArray) {
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            try {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    AdBean adBean = new AdBean(optJSONObject);
                    if (SharePreferenceUtils.getSharePreferencesIntValue(adBean.id + "_" + format) < adBean.count) {
                        this.j.add(adBean.pos, adBean);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    protected void a(int i, boolean z) {
        this.s = false;
        a.postDelayed(new b(this, z), (long) i);
    }

    protected void a(boolean z) {
        if (this.m != null) {
            this.l.refreshDone();
            if (this.e != "bottom_button_refresh" || z) {
                this.l.loadMoreDone(true);
            } else {
                this.l.loadMoreDone(z);
            }
            if (this.j.size() <= 0 || this.q) {
                this.l.setLoadMoreEnable(false);
            } else {
                this.l.setLoadMoreEnable(true);
            }
            if (this.n != null) {
                if (this.j.size() == 0) {
                    C();
                } else {
                    this.n.hide();
                }
            }
            this.i.notifyDataSetChanged();
            c();
        }
    }

    public void scrollToTop() {
        if (this.m != null) {
            this.m.setSelection(0);
        }
    }

    public void refresh() {
        if (this.m != null && !this.s) {
            StatService.onEvent(this.B, "tab_refresh", "click");
            this.L = true;
            this.m.setSelection(0);
            this.l.refresh();
        }
    }

    public boolean hasNewArticle() {
        return this.Y || this.Z >= 21;
    }

    protected void A() {
        this.Y = false;
        this.Z = -1;
        if ((this.B instanceof MainActivity) && isSelected()) {
            ((MainActivity) this.B).setHighlightedTab(MainActivity.TAB_QIUSHI_ID, false);
        }
    }

    protected OnAsyncLoadListener b(String str) {
        return new a(this, str);
    }

    protected void c(String str) {
        StatService.onEvent(this.B, "list_refresh", str + "_" + this.v);
    }

    public void onArticleDeleted(OnArticleActionListener onArticleActionListener, Article article) {
        if (this.j != null && this.j.contains(article)) {
            this.j.remove(article);
            if (this.i != null) {
                this.i.notifyDataSetChanged();
            }
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (isSelected() && this.F != null) {
            this.F.onScroll(absListView, i, i2, i3);
        }
        this.ac = i2;
        this.ad = i3;
        if (this.ab != i && this.ab < i) {
            this.ab = i;
        }
        if (i >= 21 && this.Z != 21) {
            this.Z = 21;
            i();
        }
    }

    protected final void B() {
        if (R && this.B != null && (this.B instanceof MainActivity)) {
            R = false;
            SharePreferenceUtils.setSharePreferencesValue("user_remind_first_in_", true);
            ((MainActivity) this.B).showFirstInRemindDailog();
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.F != null) {
            this.F.onScrollStateChanged(absListView, i);
        }
        if (i == 0) {
            if (!QsbkApp.getInstance().isAutoPlayVideo()) {
            }
        } else if (i == 2 && this.W == 1) {
            B();
        }
    }

    public String getVotePoint() {
        return this.v + MqttTopic.TOPIC_LEVEL_SEPARATOR;
    }

    public void initHistoryData() {
        this.e = "top_refresh";
        TaskExecutor.getInstance().addTask(new c(this));
    }

    public void setForceLoad(boolean z) {
        this.ae = z;
    }

    public void onRefresh() {
        this.s = true;
        this.q = false;
        this.e = "top_refresh";
        a.postDelayed(new f(this), 180);
    }

    public void onLoadMore() {
        if (this.q) {
            this.l.setLoadMoreEnable(false);
            this.footView.setVisibility(0);
            return;
        }
        this.e = "bottom_button_refresh";
        this.x = new AsyncDataLoader(b("load"), "qsbk-AT-BGA-more");
        this.x.execute(new Void[0]);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 25 || this.ab + this.ac < this.ad - 2) {
            return false;
        }
        onLoadMore();
        return true;
    }

    protected void C() {
        DebugUtil.debug("luolong", "showEmptyPrompt, mUrl=" + this.u);
        if (getActivity() != null) {
            String str = "这里暂时还没有内容哦";
            if (this.u.equals(Constants.MYCONTENTS)) {
                str = getResources().getString(R.string.my_qiushi_page_empty_prompt);
            } else if (this.u.equals(Constants.COLLECT_LIST)) {
                str = getResources().getString(R.string.my_collection_page_empty_prompt);
            } else if (this.u.equals(Constants.PARTICIPATE)) {
                str = getResources().getString(R.string.my_activity_page_empty_prompt);
            }
            this.n.set(UIHelper.getEmptyImg(), str);
            this.n.show();
        }
    }

    public void delete(Article article) {
        if (article != null) {
            new Builder(this.B).setTitle("刪除此条糗事？").setItems(new String[]{"立即删除", "取消"}, new g(this, article)).show();
        }
    }

    private void b(Article article) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.DELETE_CREATE, new Object[]{article.id}), new h(this, article));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void anonymous(Article article) {
        if (article != null) {
            new Builder(this.B).setTitle("将此条糗事匿名？").setItems(new String[]{"匿名", "取消"}, new i(this, article)).show();
        }
    }

    private void c(Article article) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.ANONYMOUS_CREATE, new Object[]{article.id}), new j(this, article));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void forbid(Article article) {
        if (article != null) {
            new Builder(this.B).setTitle("删除该糗事并封禁该用户？").setItems(new String[]{"删除并封禁", "取消"}, new k(this, article)).show();
        }
    }

    private void d(Article article) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.FORBID_CREATE, new Object[]{article.id}), new l(this, article));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void D() {
        CircleTopicManager.getInstance().loadRecommendTopics(new m(this));
    }

    public boolean needSubscribeReport() {
        return false;
    }

    public LivePackage getSecondLivePackage() {
        return null;
    }

    public void report(Article article, int i) {
        if (article != null) {
            if (QsbkApp.currentUser == null) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能举报哦!", Integer.valueOf(0)).show();
                LoginPermissionClickDelegate.startLoginActivity(this.B);
                return;
            }
            ReportArticle.setReportArticle(article, i);
            this.j.remove(article);
            this.i.notifyDataSetChanged();
            FileCache.cacheTextToDiskImmediately(this.B, this.v, CollectionUtils.artilesToJsonString(this.j));
            ReportArticle.reportHandler(true);
        }
    }

    public void onArticleUpdate(Article article) {
        Object obj;
        int size = this.j.size();
        for (int i = 0; i < size; i++) {
            obj = this.j.get(i);
            if (obj instanceof Article) {
                Article article2 = (Article) obj;
                if (TextUtils.equals(article2.id, article.id)) {
                    article2.updateWith(article);
                    obj = 1;
                    break;
                }
            }
        }
        obj = null;
        if (obj != null) {
            this.i.notifyDataSetChanged();
        }
    }

    public void onNewGroupRecommend(GroupRecommend groupRecommend) {
        this.C = groupRecommend;
    }

    public void onTopicUpdate(Collection<CircleTopic> collection) {
        if (CircleTopicManager.artificialTopics != null && CircleTopicManager.artificialTopics.size() > 0) {
            Iterator it = CircleTopicManager.artificialTopics.iterator();
            Object obj = null;
            while (it.hasNext()) {
                CircleTopic circleTopic = (CircleTopic) it.next();
                Object obj2 = obj;
                for (CircleTopic circleTopic2 : collection) {
                    if (circleTopic.id.equals(circleTopic2.id)) {
                        circleTopic.updateWith(circleTopic2);
                        obj2 = 1;
                    }
                }
                obj = obj2;
            }
            if (obj != null && this.i != null) {
                this.i.notifyDataSetChanged();
            }
        }
    }

    public void onChangeDate() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).refreshCurrentTab();
        }
    }

    public void setUserVisibleHint(boolean z) {
        setSelected(z);
        super.setUserVisibleHint(z);
    }

    public String getFootViewTip() {
        return "没内容了哦，去糗友圈看看吧  >";
    }

    public OnClickListener getfootViewClickListener() {
        return new o(this);
    }

    protected void a(Object obj) {
        if (this.j != null && obj != null) {
            Object obj2;
            Object obj3;
            if ((obj instanceof Article) || c <= 0 || this.j.size() <= c || !(this.j.get(c) instanceof Article)) {
                obj2 = null;
            } else {
                obj2 = this.j.get(c);
            }
            if ((obj instanceof Qsjx) || d <= 0 || this.j.size() <= d || !(this.j.get(d) instanceof Qsjx)) {
                obj3 = null;
            } else {
                obj3 = this.j.get(d);
            }
            Object obj4 = null;
            Object obj5 = null;
            int i = 0;
            while (i < this.j.size() && i < 4) {
                if (!(this.j.get(i) instanceof ReadLine)) {
                    if (!(this.j.get(i) instanceof Qsjx)) {
                        if (!(this.j.get(i) instanceof WelcomeCard)) {
                            if (!(this.j.get(i) instanceof QbAdItem)) {
                                break;
                            }
                            obj4 = this.j.get(i);
                            this.j.remove(i);
                            i--;
                        } else {
                            obj5 = this.j.get(i);
                            this.j.remove(i);
                            i--;
                        }
                    } else {
                        obj3 = this.j.get(i);
                        this.j.remove(i);
                        i--;
                    }
                }
                i++;
            }
            if (obj instanceof Qsjx) {
                obj3 = obj;
            } else if (obj instanceof WelcomeCard) {
                obj5 = obj;
            } else if (obj instanceof QbAdItem) {
                obj4 = obj;
            } else if (obj instanceof Article) {
                obj2 = obj;
            }
            if (obj5 != null) {
                this.j.add(0, obj5);
                return;
            }
            if (obj3 != null) {
                this.j.add(0, obj3);
            }
            if (obj2 != null) {
                this.j.add(0, obj2);
            }
            if (obj4 != null) {
                this.j.add(0, obj4);
            }
            if (obj2 != null) {
                c = this.j.indexOf(obj2);
            } else {
                c = -1;
            }
            if (obj3 != null) {
                d = this.j.indexOf(obj3);
            } else {
                d = -1;
            }
        }
    }
}
