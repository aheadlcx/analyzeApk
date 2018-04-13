package qsbk.app.slide;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.baidu.mobstat.StatService;
import com.facebook.common.util.UriUtil;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.qiushibaike.statsdk.StatSDK;
import cz.msebera.android.httpclient.message.TokenParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.About;
import qsbk.app.activity.ImagesPickerActivity;
import qsbk.app.activity.MyInfoActivity;
import qsbk.app.activity.NewImageViewer;
import qsbk.app.activity.SingleArticleLevel;
import qsbk.app.activity.VideoImmersionActivity;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.activity.dialog.QiushiBuddleDialog;
import qsbk.app.adapter.ManageMyContentsAdapter;
import qsbk.app.adapter.SingleArticleAdapter;
import qsbk.app.adapter.SingleArticleAdapter.OnMoreClickListener;
import qsbk.app.adapter.SingleArticleAdapter.OnTabSelectListener;
import qsbk.app.adapter.SubscribeAdapter;
import qsbk.app.core.AsyncTask;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.fragments.IPageableFragment;
import qsbk.app.http.HttpTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.ChatMsgEmotionData;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.image.ImageSizeHelper;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.loader.OnAsyncLoadListener;
import qsbk.app.model.Article;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.Comment;
import qsbk.app.model.Image;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.ImageSize;
import qsbk.app.model.RssArticle;
import qsbk.app.model.Vote;
import qsbk.app.service.VoteManager;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.ShareUtils$OnShareListener;
import qsbk.app.ticker.TickerView;
import qsbk.app.utils.AppContext;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.QiushiArticleBus;
import qsbk.app.utils.QiushiArticleBus.OnArticleUpdateListener;
import qsbk.app.utils.ReadQiushi;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.ReportCommon;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;
import qsbk.app.utils.UserInfoClickListener;
import qsbk.app.utils.Util;
import qsbk.app.video.SimpleVideoPlayer.OnVideoEventListener;
import qsbk.app.video.SimpleVideoPlayerView;
import qsbk.app.video.VideoLoopStatistics;
import qsbk.app.video.VideoPlayerView;
import qsbk.app.widget.AutoLoadMoreListView;
import qsbk.app.widget.DiggerBar;
import qsbk.app.widget.LoaderLayout;
import qsbk.app.widget.QiushiEmotionHandler$EmotionData;
import qsbk.app.widget.QiushiImageLayout;
import qsbk.app.widget.QiushiTopicImageSpan;
import qsbk.app.widget.QiuyouCircleNotificationItemView.Type;
import qsbk.app.widget.RoundedDrawable;
import qsbk.app.widget.SupportOrNotView;
import qsbk.app.widget.TextBlockSpan;
import qsbk.app.widget.TipsHelper;

public class SingleArticleFragment extends BaseEmotionFragment implements OnGestureListener, OnTouchListener, OnMoreClickListener, IPageableFragment, ShareUtils$OnShareListener, OnBackPressListener, OnArticleUpdateListener {
    public static final String DRAFT_COMMENT = "draftCommentContent";
    public static final String EXTRA_ARTICLE = "ARTICLEJSON";
    public static final String FROM_MSG = "FROM_MSG";
    public static final String FROM_PROMOTE = "_FROM_PROMOTE_";
    public static final String KEY_AUTO_SCROLL_TO_COMMENT = "scroll_to_comment";
    public static final String KEY_FROM_PUSH = "from_push";
    public static final String KEY_ONLY_ARTICLE_ID = "only_article_id";
    public static final String KEY_SHOW_USERINFO = "show_userinfo";
    public static final String LOCAL_COMMENT_ID = "-1";
    public static final String QIUSHI_NEED_SHOW_BUDDLE = "_qiushi_need_show_buddle";
    public static final String QIUSHI_PUSH_ID = "qiushi_push";
    protected ImageView A;
    protected SupportOrNotView B;
    protected TickerView C;
    protected TextView D;
    protected ImageView E;
    protected ProgressBar F;
    protected TextView G;
    protected DiggerBar H;
    protected TextView I;
    protected TextView J;
    protected VideoPlayerView K;
    protected View L;
    protected LoaderLayout M;
    protected View N;
    protected boolean O = false;
    protected JSONObject P = null;
    protected Comment Q = null;
    protected boolean R = false;
    protected String S = null;
    protected ProgressBar T;
    protected TextView U = null;
    protected AsyncDataLoader V = null;
    protected boolean W = false;
    protected boolean X = true;
    protected boolean Y;
    protected QiushiBuddleDialog Z;
    private View aA;
    private ImageView aB;
    private View aC;
    private short aD = (short) 4098;
    private String aE;
    private int aF = 0;
    private String aG = null;
    private String aH = null;
    private String aI = null;
    private int aJ = -1;
    private QiushiImageLayout aK;
    private View aL;
    private boolean aM;
    private boolean aN;
    private boolean aO;
    private boolean aP;
    private ArrayList<AtInfo> aQ = new ArrayList();
    private int aR;
    private OnVideoEventListener aS = new ax(this);
    private OnTabSelectListener aT = new ay(this);
    protected Window aa;
    protected boolean ab;
    protected long ac;
    protected GestureDetector ad;
    public Runnable addLoopTask = new p(this);
    boolean ae = false;
    Handler af = new ad(this);
    int ag = 180;
    int ah = 0;
    OnFragmentCreatedListener ai;
    protected final Runnable aj = new ao(this);
    protected final Runnable ak = new au(this);
    OnClickListener al = new av(this);
    protected final Runnable am = new aw(this);
    private final BroadcastReceiver an = new d(this);
    private boolean ao = false;
    private ImageView ap;
    private View aq;
    private TextView ar;
    private View as;
    private View at;
    private ImageView au;
    private a av;
    private BaseActionBarActivity aw;
    private TipsHelper ax;
    private TipsHelper ay;
    private ImageButton az;
    protected String j = SingleArticleFragment.class.getName();
    protected StringBuffer k = new StringBuffer("糗事");
    protected Article l = null;
    protected SingleArticleAdapter m;
    protected NormalCommentsDataProvider n;
    protected HotCommentsDataProvider o;
    protected OwnerCommentsDataProvider p;
    protected AutoLoadMoreListView q;
    protected String r;
    protected int s = -1;
    protected View t;
    protected View u;
    protected TextView v;
    protected View w;
    protected TextView x;
    protected TextView y;
    protected View z;

    public interface OnFragmentCreatedListener {
        void onFragmentCreated();
    }

    protected abstract class CommentsDataProvider {
        protected ArrayList<Comment> a = new ArrayList();
        protected int b = 1;
        protected int c = 0;
        final /* synthetic */ SingleArticleFragment d;

        protected class OnLoadListener implements OnAsyncLoadListener {
            final /* synthetic */ CommentsDataProvider a;
            private String b;

            protected OnLoadListener(CommentsDataProvider commentsDataProvider) {
                this.a = commentsDataProvider;
            }

            public void onPrepareListener() {
                if (this.a.d.aw != null) {
                    this.a.d.aw.getMainUIHandler().post(this.a.d.aj);
                }
                this.b = this.a.a(this);
                if (this.a.d.R) {
                    this.b += "&article=1";
                }
            }

            public String onStartListener() {
                try {
                    return HttpClient.getIntentce().get(this.b);
                } catch (QiushibaikeException e) {
                    return null;
                }
            }

            public void onFinishListener(String str) {
                int i = 0;
                if (this.a.d.aw != null && this.a.d.ab) {
                    this.a.d.aw.getMainUIHandler().post(this.a.d.ak);
                    this.a.d.aw.getMainUIHandler().removeCallbacks(this.a.d.am);
                    this.a.d.aw.getMainUIHandler().postDelayed(this.a.d.am, 60000);
                }
                if (!TextUtils.isEmpty(str)) {
                    this.a.a(this.a.b, str);
                    CommentsDataProvider commentsDataProvider = this.a;
                    commentsDataProvider.b++;
                    this.a.d.m.notifyDataSetChanged();
                    if (this.a.d.W && !(this.a instanceof OwnerCommentsDataProvider)) {
                        this.a.d.q.post(new bd(this));
                        this.a.d.W = false;
                    }
                } else if (this.a instanceof NormalCommentsDataProvider) {
                    SingleArticleFragment singleArticleFragment = this.a.d;
                    if (this.a.d.l != null) {
                        i = this.a.d.l.comment_count;
                    }
                    singleArticleFragment.s = i;
                    if (this.a.d.s == 0) {
                        if (TextUtils.isEmpty(str) && this.a.d.R) {
                            this.a.c = 2;
                        } else {
                            this.a.c = 3;
                        }
                    }
                } else {
                    this.a.c = 3;
                }
                if (!(this.a instanceof HotCommentsDataProvider)) {
                    this.a.d.b(this.a.c);
                }
                this.a.d.A();
            }
        }

        protected abstract String a(OnLoadListener onLoadListener);

        protected CommentsDataProvider(SingleArticleFragment singleArticleFragment) {
            this.d = singleArticleFragment;
        }

        public void load() {
            this.c = 1;
            this.d.V = new AsyncDataLoader(new OnLoadListener(this), "qsbk-AT-SA-01");
            this.d.V.execute(new Void[0]);
        }

        protected void a(int i, String str) {
            int i2 = 0;
            DebugUtil.debug(this.d.j, str);
            try {
                if (!"".equals(str)) {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 0) {
                        int i3;
                        String str2;
                        JSONArray jSONArray = jSONObject.getJSONArray("items");
                        int length = jSONArray.length();
                        for (i3 = 0; i3 < length; i3++) {
                            JSONObject optJSONObject = jSONArray.optJSONObject(i3);
                            if (optJSONObject != null) {
                                Comment newInstance = Comment.newInstance(optJSONObject);
                                if (!(newInstance == null || this.a.contains(newInstance) || ReportCommon.mReportCommon.keySet().contains(newInstance.id))) {
                                    this.a.add(newInstance);
                                }
                            }
                        }
                        i3 = jSONObject.optInt("total");
                        b(i3);
                        if (this instanceof NormalCommentsDataProvider) {
                            this.d.s = i3 - (i * 50);
                            SingleArticleFragment singleArticleFragment = this.d;
                            if (this.d.s > 0) {
                                i2 = this.d.s;
                            }
                            singleArticleFragment.s = i2;
                        }
                        if (jSONObject.isNull("article")) {
                            str2 = null;
                        } else {
                            str2 = jSONObject.getString("article");
                        }
                        if (str2 != null) {
                            this.d.l = new RssArticle(new JSONObject(str2));
                            this.d.m.setArticle(this.d.l);
                            this.d.initVoteState();
                            this.d.l();
                            this.d.b(this.d.l);
                            this.d.N.setVisibility(0);
                        }
                        if (i3 > 0) {
                            a(i3);
                        }
                        if (this.a.size() >= i3) {
                            this.c = 3;
                        } else {
                            this.c = 0;
                        }
                        this.d.m.notifyDataSetChanged();
                    } else if (jSONObject.optInt(NotificationCompat.CATEGORY_ERROR) == 30001) {
                        this.c = 4;
                        this.d.b(this.c);
                    } else {
                        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, jSONObject.optString("err_msg"), Integer.valueOf(0)).show();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        protected void a(int i) {
        }

        protected void b(int i) {
        }
    }

    protected class HotCommentsDataProvider extends CommentsDataProvider {
        final /* synthetic */ SingleArticleFragment e;

        protected HotCommentsDataProvider(SingleArticleFragment singleArticleFragment) {
            this.e = singleArticleFragment;
            super(singleArticleFragment);
        }

        protected String a(OnLoadListener onLoadListener) {
            return String.format(Constants.HOT_COMMENT, new Object[]{this.e.S}) + "?page=" + this.b + "&count=" + 10;
        }

        protected void a(int i, String str) {
            super.a(i, str);
            this.e.aO = true;
            if (this.e.aN && i == 1) {
                this.e.H();
            }
        }
    }

    protected static final class ImageOnClickListener implements OnClickListener {
        String a = null;
        String b = null;
        Article c = null;
        String d = null;

        public ImageOnClickListener(String str, String str2, Article article, String str3) {
            this.a = str;
            this.b = QsbkApp.absoluteUrlOfMediumContentImage(str, str2);
            this.c = article;
            this.d = str3;
        }

        public void onClick(View view) {
            NewImageViewer.launch(view.getContext(), null, new Rect[]{UIHelper.getViewVisibleRect(view)}, this.c, 0);
        }
    }

    protected class NormalCommentsDataProvider extends CommentsDataProvider {
        final /* synthetic */ SingleArticleFragment e;

        protected NormalCommentsDataProvider(SingleArticleFragment singleArticleFragment) {
            this.e = singleArticleFragment;
            super(singleArticleFragment);
        }

        protected String a(OnLoadListener onLoadListener) {
            return String.format(Constants.COMMENT, new Object[]{this.e.S}) + "?page=" + this.b + "&count=" + 50;
        }

        protected void b(int i) {
            this.e.m.setAllCount(i);
        }

        protected void a(int i, String str) {
            super.a(i, str);
            this.e.H();
        }
    }

    protected class OnItemClick implements OnItemClickListener {
        final /* synthetic */ SingleArticleFragment a;

        protected OnItemClick(SingleArticleFragment singleArticleFragment) {
            this.a = singleArticleFragment;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Object item = adapterView.getAdapter().getItem(i);
            if (item != null && (item instanceof Comment)) {
                this.a.reply((Comment) item);
            }
        }
    }

    protected class OnItemLongClick implements OnItemLongClickListener {
        final /* synthetic */ SingleArticleFragment a;

        protected OnItemLongClick(SingleArticleFragment singleArticleFragment) {
            this.a = singleArticleFragment;
        }

        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            Object item = adapterView.getAdapter().getItem(i);
            if (item == null || !(item instanceof Comment)) {
                return false;
            }
            Comment comment = (Comment) item;
            CharSequence charSequence = comment.uid;
            if ((QsbkApp.currentUser != null && TextUtils.equals("admin", QsbkApp.currentUser.adminRole)) || (QsbkApp.currentUser != null && "1".equalsIgnoreCase(QsbkApp.currentUser.userId))) {
                new Builder(this.a.getContext()).setTitle("").setItems(new String[]{"删除", "封禁"}, new bf(this, comment, i)).setNegativeButton("取消", new be(this)).show();
            } else if (this.a.ae || ((QsbkApp.currentUser != null && this.a.l.isMine(QsbkApp.currentUser.userId)) || !this.a.X)) {
                new Builder(this.a.getContext()).setTitle("").setItems(new String[]{"举报", "删除"}, new bh(this, comment, i)).setNegativeButton("取消", new bg(this)).show();
            } else if (QsbkApp.currentUser == null || !(TextUtils.equals(charSequence, QsbkApp.currentUser.userId) || this.a.l.isMine(QsbkApp.currentUser.userId))) {
                new Builder(this.a.getActivity()).setTitle("").setItems(new String[]{"复制", "举报"}, new bk(this, comment)).setNegativeButton("取消", null).show();
            } else {
                new Builder(this.a.getContext()).setTitle("").setItems(new String[]{"删除"}, new bj(this, comment, i)).setNegativeButton("取消", new bi(this)).show();
            }
            return true;
        }
    }

    protected class OwnerCommentsDataProvider extends CommentsDataProvider {
        final /* synthetic */ SingleArticleFragment e;

        protected OwnerCommentsDataProvider(SingleArticleFragment singleArticleFragment) {
            this.e = singleArticleFragment;
            super(singleArticleFragment);
        }

        protected String a(OnLoadListener onLoadListener) {
            return String.format(Constants.OWNER_COMMENT, new Object[]{this.e.S}) + "?page=" + this.b + "&count=" + 50;
        }

        protected void b(int i) {
            this.e.m.setOwnerCount(i);
        }
    }

    private class a {
        final /* synthetic */ SingleArticleFragment a;
        private Comment b;
        private View c;
        private View d;
        private View e = this.c.findViewById(R.id.leftContainer);
        private View f;
        private View g;
        private int h;

        public a(SingleArticleFragment singleArticleFragment, Context context) {
            this.a = singleArticleFragment;
            this.c = LayoutInflater.from(context).inflate(R.layout.layout_comment_more, null);
            this.e.setOnClickListener(new az(this, singleArticleFragment));
            this.f = this.c.findViewById(R.id.reportContainer);
            this.f.setOnClickListener(new ba(this, singleArticleFragment));
            this.g = this.c.findViewById(R.id.deleteContainer);
            this.g.setOnClickListener(new bb(this, singleArticleFragment));
            this.d = this.c.findViewById(R.id.container);
            this.c.setOnTouchListener(new bc(this, singleArticleFragment));
        }

        void a(int i, Comment comment, View view, View view2) {
            if (this.c != null && view2 != null && view != null && this.d != null && !a()) {
                if (QsbkApp.currentUser == null || !comment.uid.equals(QsbkApp.currentUser.userId)) {
                    this.f.setVisibility(0);
                    this.g.setVisibility(8);
                } else {
                    this.f.setVisibility(8);
                    this.g.setVisibility(0);
                }
                ((ViewGroup) view2.getRootView()).addView(this.c, new LayoutParams(-1, -1));
                this.b = comment;
                this.h = i;
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.d.getLayoutParams();
                layoutParams.gravity = 53;
                layoutParams.rightMargin = (view2.getWidth() - iArr[0]) + Util.dp(4.0f);
                layoutParams.topMargin = (iArr[1] - (view.getHeight() / 2)) + Util.dp(4.0f);
                this.d.setLayoutParams(layoutParams);
            }
        }

        boolean a() {
            if (this.c == null || this.c.getParent() == null) {
                return false;
            }
            ((ViewGroup) this.c.getParent()).removeView(this.c);
            return true;
        }
    }

    private class b implements OnClickListener {
        final /* synthetic */ SingleArticleFragment a;

        private b(SingleArticleFragment singleArticleFragment) {
            this.a = singleArticleFragment;
        }

        public void onClick(View view) {
            this.a.D();
        }
    }

    public static SingleArticleFragment newInstance(Article article) {
        SingleArticleFragment singleArticleFragment = new SingleArticleFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(bo.ARTICLE.getTypeValue(), article);
        singleArticleFragment.setArguments(bundle);
        return singleArticleFragment;
    }

    protected static final boolean a(Article article) {
        return article instanceof RssArticle;
    }

    public static void reportEvent(boolean z) {
        String str = "delete_comment";
        String str2 = z ? "long_click" : "click";
        StatService.onEvent(AppContext.getContext(), str, str2);
        StatSDK.onEvent(AppContext.getContext(), str, str2);
    }

    public static final String emotionTrim(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length() - 1;
        char[] toCharArray = str.toCharArray();
        int i = 0;
        while (i <= length && toCharArray[i] <= TokenParser.SP && toCharArray[i] != '\u0001') {
            i++;
        }
        int i2 = length;
        while (i2 >= i && toCharArray[i2] <= TokenParser.SP) {
            i2--;
        }
        return (i == 0 && i2 == length) ? str : new String(toCharArray, i, (i2 - i) + 1);
    }

    public void onAttach(Activity activity) {
        if (activity instanceof BaseActionBarActivity) {
            this.aw = (BaseActionBarActivity) activity;
        }
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.at = layoutInflater.inflate(v(), viewGroup, false);
        this.l = (Article) getArguments().getSerializable(bo.ARTICLE.getTypeValue());
        if (this.l != null) {
            this.S = this.l.id;
        }
        a(bundle);
        return this.at;
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.ai != null) {
            this.ai.onFragmentCreated();
        }
    }

    public void onStart() {
        super.onStart();
        QiushiArticleBus.register(this);
    }

    public void onResume() {
        super.onResume();
        this.ab = true;
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && this.aO) {
            H();
        }
    }

    public void doResume() {
        this.aN = true;
        if (this.l != null && !this.l.isGIFArticle() && this.l.isVideoArticle() && QsbkApp.getInstance().isAutoPlayConfiged()) {
            this.K.setVisibility(8);
        }
        if (this.aO) {
            H();
        }
    }

    public void doPause() {
        if (this.l != null && this.K.isPlaying()) {
            this.K.stop();
        }
        this.aN = false;
    }

    public void onStop() {
        super.onStop();
        this.ab = false;
        ReportCommon.save2Local();
        QiushiArticleBus.unregister(this);
    }

    public void doStop() {
        if (this.l == null || this.K != null) {
            this.aN = false;
        } else {
            this.aN = false;
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.K != null) {
            this.K.reset();
        }
    }

    public void onDestroy() {
        ShareUtils.unregisterShareListener(this);
        if (this.aw != null) {
            this.aw.getMainUIHandler().removeCallbacks(this.am);
            this.aw.getMainUIHandler().removeCallbacks(this.ak);
            this.aw.getMainUIHandler().removeCallbacks(this.aj);
        }
        if (this.V != null) {
            this.V.cancel(true);
        }
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.an);
        super.onDestroy();
        if (this.m != null) {
            this.m.onDestroy();
        }
    }

    protected void h() {
        if (this.T == null) {
            this.T = new ProgressBar(getContext());
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            if (this.aw != null) {
                this.aw.addContentView(this.T, layoutParams);
            }
        }
        this.T.setVisibility(0);
        this.N.setVisibility(4);
    }

    protected void i() {
        if (this.T != null) {
            this.T.setVisibility(8);
        }
        if (this.U != null) {
            this.U.setVisibility(0);
        } else if (this.aw != null) {
            this.U = new TextView(this.aw);
            this.U.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
            this.U.setText(Html.fromHtml("加载失败，请稍后<font color=#000000>点我</font>重试..."));
            this.U.setOnClickListener(new e(this));
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            if (this.aw != null) {
                this.aw.addContentView(this.U, layoutParams);
            }
        }
    }

    void j() {
        if (this.T != null) {
            this.T.setVisibility(8);
        }
        if (this.N != null) {
            this.N.setVisibility(0);
        }
    }

    public void handleIntent(Intent intent, Bundle bundle) {
        this.W = intent.getBooleanExtra("scroll_to_comment", this.W);
        this.X = intent.getBooleanExtra("show_userinfo", this.X);
        this.r = intent.getStringExtra("source");
        this.ae = intent.getBooleanExtra("fromManageMyQiuShi", false);
        this.R = "only_article_id".equals(this.r);
        if (this.l == null) {
            this.l = (Article) intent.getSerializableExtra("article");
        }
        a(true);
        if (intent.getBooleanExtra("FROM_MSG", false)) {
            this.O = true;
            try {
                this.l = new Article(new JSONObject(getActivity().getIntent().getStringExtra("ARTICLEJSON")));
                this.ao = getActivity().getIntent().getBooleanExtra("_FROM_PROMOTE_", false);
            } catch (Exception e) {
                ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "消息携带的帖子内容出错，查看失败!", Integer.valueOf(0)).show();
                finish();
            }
        } else if (!this.R) {
            if (t() && this.l == null) {
                Object obj = QsbkApp.currentDataSource.get(QsbkApp.currentSelectItem);
                if (obj instanceof Article) {
                    this.l = (Article) obj;
                } else {
                    finish();
                    return;
                }
            }
            if (bundle != null) {
                this.l = (Article) bundle.getSerializable("currentArticle");
            }
        }
        a(intent, this.l);
        if (intent.getBooleanExtra("from_push", false)) {
            ((NotificationManager) getActivity().getSystemService("notification")).cancel(1);
            ReadQiushi.setRead(this.l);
        }
        this.aF = intent.getIntExtra(SingleArticleLevel.COMMENT_FLOOR, 0);
        this.aG = intent.getStringExtra(SingleArticleLevel.COMMENT_USER_NAME);
        this.aH = intent.getStringExtra(SingleArticleLevel.COMMENT_CONTENT);
        this.aI = intent.getStringExtra(SingleArticleLevel.COMMENT_UID);
        if (!(TextUtils.isEmpty(this.aH) || TextUtils.isEmpty(this.aG))) {
            a(this.aG, this.aH, this.aF, this.aI);
            this.a.postDelayed(new f(this), 200);
        }
        if (this.aw != null) {
            this.aw.statPushLabel();
        }
        h();
        if (this.l != null) {
            this.m.setArticle(this.l);
            l();
            b(this.l);
        } else if (!TextUtils.isEmpty(this.S)) {
        }
        if (this.l == null && TextUtils.isEmpty(this.S)) {
            finish();
            return;
        }
        k();
        if (intent.getBooleanExtra("showKeyboard", false) && this.a != null) {
            this.a.requestFocus();
            UIHelper.showKeyboard(getActivity());
        }
    }

    protected void a(Intent intent, Article article) {
        Object stringExtra = intent.getStringExtra("article_id");
        if (TextUtils.isEmpty(this.S) && article != null) {
            this.S = article.id;
        } else if (!TextUtils.isEmpty(stringExtra)) {
            this.S = stringExtra;
        }
        Log.e(this.j, "article id is : " + this.S);
        if (TextUtils.isEmpty(this.S)) {
            finish();
        }
    }

    private void c(int i) {
        this.J.postDelayed(this.addLoopTask, (long) (Math.random() * 3000.0d));
    }

    private void B() {
        if (this.l != null) {
            int generateLoopRandom = this.l.generateLoopRandom();
            Article article = this.l;
            article.loop += (long) generateLoopRandom;
            c(generateLoopRandom);
            VideoLoopStatistics.getInstance().loopBatch(this.l.id, generateLoopRandom);
        }
    }

    public void onShared(Article article) {
        this.l.shareCount = article.shareCount;
        if (TextUtils.equals(this.S, article.id)) {
            UIHelper.setSupportAndCommentTextHighlight(this.C, this.D, this.l.getDisplayLaugth(), this.l.comment_count, this.l.shareCount, false);
            QiushiArticleBus.updateArticle(this.l, null);
        }
    }

    protected void a(boolean z) {
        if (!this.R) {
            return;
        }
        if (z) {
            this.ac = System.currentTimeMillis();
        } else {
            StatSDK.onEventDuration(getContext(), "qiushi_push", "spent", System.currentTimeMillis() - this.ac);
        }
    }

    public void onViewStateRestored(@Nullable Bundle bundle) {
        super.onViewStateRestored(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    protected void k() {
        if (!this.aM) {
            this.aM = true;
            String str = Constants.ARTICLE_DETAIL + (this.l != null ? this.l.id : this.S);
            new HttpTask(str, str, new g(this)).execute(new Void[0]);
        }
    }

    protected void l() {
        this.q.setOnLoadMoreListener(new i(this));
        this.q.setOnItemClickListener(new OnItemClick(this));
        this.q.setOnItemLongClickListener(new OnItemLongClick(this));
        this.q.setOnScrollListener(new j(this));
        this.u.setOnClickListener(new k(this));
        this.w.setOnLongClickListener(new l(this));
        if (!(this.l == null || TextUtils.isEmpty(this.l.image) || "null".equalsIgnoreCase(this.l.image))) {
            if (this.l.isVideoArticle()) {
                this.A.setOnClickListener(null);
            } else {
                this.A.setOnClickListener(new ImageOnClickListener(this.S, this.l.image, this.l, this.r));
            }
        }
        this.B.setOnSupportListener(new m(this));
        this.E.setOnClickListener(new n(this));
    }

    protected void m() {
        boolean z = false;
        if (this.E != null && this.E.getTag().equals("active")) {
            z = true;
        }
        ShareUtils.openShareDialog(getParentFragment() == null ? this : getParentFragment(), this.aw, 1, z, this.l, this.E);
    }

    protected boolean a(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 2) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "再多写点内容吧!", Integer.valueOf(0)).show();
            return true;
        } else if (TextUtils.isEmpty(str) || str.length() <= 140) {
            return false;
        } else {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "评论内容不能超过140个字哦!", Integer.valueOf(0)).show();
            return true;
        }
    }

    protected void n() {
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能评论哦！", Integer.valueOf(0)).show();
            LoginPermissionClickDelegate.startLoginActivity(getContext());
        } else if (!this.l.allow_comment) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "该文章关闭评论", Integer.valueOf(0)).show();
        } else if (HttpUtils.netIsAvailable()) {
            F();
            if (!a(this.a.getText().toString())) {
                this.P = null;
                this.u.setClickable(false);
                p();
            }
        } else {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, getString(R.string.network_not_connected), Integer.valueOf(0)).show();
        }
    }

    protected HashMap<String, Object> o() {
        HashMap hashMap = new HashMap();
        hashMap.put("content", a(hashMap));
        hashMap.put("anonymous", Boolean.valueOf(false));
        if (!(this.Q == null || TextUtils.equals("-1", this.Q.id))) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(Integer.valueOf(this.Q.uid));
            hashMap.put(Type.QIUSHI_COMMENT_AT, jSONArray);
        }
        return hashMap;
    }

    protected void p() {
        if (this.R) {
            StatSDK.onEvent(getActivity(), "qiushi_push", "comment");
        }
        f();
        E();
        this.aA.setVisibility(8);
        String str = this.aE;
        Map o = o();
        this.a.setText("");
        this.aQ.clear();
        this.a.setHint(R.string.comment_input_hint);
        this.aq.setVisibility(8);
        this.aE = null;
        SharePreferenceUtils.remove(y());
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.COMMENT_CREATE, new Object[]{this.S}), new o(this));
        simpleHttpTask.setIsSubmit(true);
        simpleHttpTask.setMapParams(o);
        simpleHttpTask.setFilePath(str);
        simpleHttpTask.executeOnExecutor(SimpleHttpTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void q() {
        this.ax = new TipsHelper(this.at.findViewById(R.id.tips));
        this.ax.hide();
        this.q = (AutoLoadMoreListView) this.at.findViewById(R.id.xListView);
        this.o = new HotCommentsDataProvider(this);
        this.n = new NormalCommentsDataProvider(this);
        this.p = new OwnerCommentsDataProvider(this);
        this.m = new SingleArticleAdapter(this.o.a, this.n.a, this.p.a, getActivity(), this.S);
        this.m.setOnTabSelectListener(this.aT);
        this.m.setMoreClickListener(this);
        if (this.w == null) {
            this.w = LayoutInflater.from(getContext()).inflate(w(), null);
        }
        this.x = (TextView) this.w.findViewById(R.id.userName);
        this.y = (TextView) this.w.findViewById(R.id.content);
        this.z = this.w.findViewById(R.id.imageLayout);
        this.aK = (QiushiImageLayout) this.w.findViewById(R.id.qiushi_image_layout);
        this.A = (ImageView) this.w.findViewById(R.id.image);
        this.F = (ProgressBar) this.w.findViewById(R.id.progress);
        this.G = (TextView) this.w.findViewById(R.id.duration);
        this.ap = (ImageView) this.w.findViewById(R.id.video_play_flag);
        this.w.findViewById(R.id.comment).setVisibility(8);
        this.B = (SupportOrNotView) this.w.findViewById(R.id.support_or_not);
        this.E = (ImageView) this.w.findViewById(R.id.collection_icon);
        this.C = (TickerView) this.w.findViewById(R.id.points_and_comments_count);
        this.D = (TextView) this.w.findViewById(R.id.comment_count);
        this.I = (TextView) this.w.findViewById(R.id.type);
        this.au = (ImageView) this.w.findViewById(R.id.event);
        this.aL = this.w.findViewById(R.id.unlike);
        this.H = (DiggerBar) this.w.findViewById(R.id.diggerbar);
        View findViewById = this.w.findViewById(R.id.play_video);
        this.J = (TextView) this.w.findViewById(R.id.loop);
        this.K = (VideoPlayerView) this.w.findViewById(R.id.videoView);
        this.K.setOnClickListener(new b());
        this.K.setLoop(true);
        ((SimpleVideoPlayerView) this.K).setWidget(this.F, findViewById, this.A, this.G, this.ap);
        this.A.setOnClickListener(new b());
        this.K.getPlayBtn().setOnClickListener(new b());
        this.L = this.w.findViewById(R.id.gif_tag);
        this.aq = this.at.findViewById(R.id.to_reply_layout);
        this.ar = (TextView) this.at.findViewById(R.id.to_reply_info);
        this.as = this.at.findViewById(R.id.to_reply_close);
        this.as.setOnClickListener(new q(this));
        this.az = (ImageButton) this.at.findViewById(R.id.send_img);
        this.az.setOnClickListener(new LoginPermissionClickDelegate(new r(this), "请先登录"));
        this.aB = (ImageView) this.at.findViewById(R.id.comment_img);
        this.aA = this.at.findViewById(R.id.comment_img_container);
        this.aC = this.at.findViewById(R.id.comment_img_clear);
        this.aA.setOnClickListener(this.al);
        this.aC.setOnClickListener(this.al);
        this.q.addHeaderView(this.w);
        if (this.M == null) {
            this.M = (LoaderLayout) LayoutInflater.from(getContext()).inflate(R.layout.loader_layout, null);
        }
        this.ay = new TipsHelper(this.M.findViewById(R.id.cmt_empty_tips), true);
        this.ay.hide();
        this.q.addFooterView(this.M);
        this.q.setAdapter(this.m);
        this.N = this.at.findViewById(R.id.root);
        this.t = this.at.findViewById(R.id.addCmtLayout);
        this.v = (TextView) this.at.findViewById(R.id.exceed_words);
        this.v.setVisibility(8);
        this.a = (EditText) this.at.findViewById(R.id.addCmtEditText);
        this.a.addTextChangedListener(new t(this));
        this.a.setOnEditorActionListener(new u(this));
        this.u = this.at.findViewById(R.id.send);
        this.u.setEnabled(false);
        CharSequence sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(y());
        if (!TextUtils.isEmpty(sharePreferencesValue)) {
            this.u.setEnabled(true);
            this.a.setText(sharePreferencesValue);
        }
        Object sharePreferencesValue2 = SharePreferenceUtils.getSharePreferencesValue(z());
        this.Q = null;
        if (!TextUtils.isEmpty(sharePreferencesValue2)) {
            try {
                this.Q = Comment.newInstance(new JSONObject(sharePreferencesValue2));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (this.l != null) {
            initVoteState();
        }
        this.av = new a(this, getContext());
        a(this.N);
        if (!TextUtils.isEmpty(this.aH) && !TextUtils.isEmpty(this.aG)) {
            a(this.aG, this.aH, this.aF, this.aI);
            this.a.postDelayed(new v(this), 200);
        }
    }

    private void C() {
        startActivityForResult(ImagesPickerActivity.prepareIntent(getActivity(), 1), this.aD);
    }

    private void D() {
        if (this.l == null) {
            return;
        }
        if (this.l.isGIFArticle()) {
            NewImageViewer.launch(getActivity(), new Rect[]{UIHelper.getRectOnScreen(this.K)}, new Rect[]{UIHelper.getViewVisibleRect(this.K)}, this.l, 0);
            return;
        }
        VideoImmersionActivity.launch(getContext(), this.l, this.K.getCurrentTime());
    }

    protected void r() {
        View findViewById = this.w.findViewById(R.id.userInfo);
        if (this.X) {
            findViewById.setVisibility(0);
            if (TextUtils.isEmpty(this.l.login)) {
                this.x.setText(BaseUserInfo.ANONYMOUS_USER_NAME);
                this.x.setTextColor(getResources().getColor(UIHelper.isNightTheme() ? R.color.uesr_night : R.color.g_txt_small));
                ImageView imageView = (ImageView) this.w.findViewById(R.id.userIcon);
                imageView.setImageDrawable(RoundedDrawable.fromDrawable(imageView.getResources().getDrawable(UIHelper.getIconRssAnonymousUser())));
                return;
            }
            CharSequence remark = RemarkManager.getRemark(this.l.user_id);
            TextView textView = this.x;
            if (TextUtils.isEmpty(remark)) {
                remark = this.l.login;
            }
            textView.setText(remark);
            a(this.l.user_id, this.l.user_icon, (ImageView) this.w.findViewById(R.id.userIcon));
            findViewById.setOnClickListener(new UserClickDelegate(this.l.user_id, new UserInfoClickListener(this.l.user_id, this.l.login, this.l.user_icon, this.l.id)));
            return;
        }
        findViewById.setVisibility(8);
    }

    protected void b(Article article) {
        int videoLayoutParams;
        this.z.setVisibility(8);
        r();
        if (TextUtils.isEmpty(article.getContent()) || "null".equals(article.getContent().trim())) {
            this.y.setVisibility(8);
        } else {
            this.y.setVisibility(0);
            this.y.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
            if (article.qiushiTopic != null) {
                CharSequence spannableStringBuilder = new SpannableStringBuilder();
                spannableStringBuilder.append("搜");
                QiushiTopicImageSpan qiushiTopicImageSpan = new QiushiTopicImageSpan(getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.ic_topic_prefix_night : R.drawable.ic_topic_prefix));
                qiushiTopicImageSpan.setSubSize(UIHelper.dip2px(getActivity(), 5.0f));
                qiushiTopicImageSpan.setmPaint(this.y.getPaint());
                spannableStringBuilder.setSpan(qiushiTopicImageSpan, 0, 1, 33);
                String str = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + article.qiushiTopic.content;
                spannableStringBuilder.append(str);
                spannableStringBuilder.setSpan(new w(this, article), 0, str.length() + 1, 33);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(UIHelper.isNightTheme() ? -4424933 : -17664), 1, str.length() + 1, 33);
                spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + article.getContent());
                this.y.setText(spannableStringBuilder);
                this.y.setMovementMethod(LinkMovementMethod.getInstance());
            } else {
                this.y.setText(article.getContent());
            }
        }
        if (article.isWordsOnly()) {
            this.z.setVisibility(8);
            this.aK.setVisibility(8);
        } else {
            this.z.setVisibility(0);
            this.A.setVisibility(0);
            int[] videoWidthHeightMaxPix = (article.isVideoArticle() || article.isGIFArticle()) ? ImageSizeHelper.getVideoWidthHeightMaxPix() : ImageSizeHelper.getRequestWidthAndMaxPixcel();
            int i = videoWidthHeightMaxPix[0];
            int i2 = videoWidthHeightMaxPix[1];
            if (article.isVideoArticle()) {
                ImageSize imageSize;
                this.aK.setVisibility(8);
                this.z.setVisibility(0);
                i2 = videoWidthHeightMaxPix[0];
                int[] videoWidthAndHeight = article.getVideoWidthAndHeight();
                if (videoWidthAndHeight[0] == 0 || videoWidthAndHeight[1] == 0) {
                    imageSize = new ImageSize(i, (i2 * 4) / 9);
                } else {
                    imageSize = new ImageSize(videoWidthAndHeight[0], videoWidthAndHeight[1]);
                }
                videoLayoutParams = setVideoLayoutParams(this.A, imageSize, i, i2);
                if (videoWidthAndHeight[0] < videoWidthAndHeight[1]) {
                    this.aJ = videoLayoutParams;
                }
            } else {
                this.aK.setVisibility(0);
                this.z.setVisibility(8);
            }
            if ("publish".equals(article.state) || ManageMyContentsAdapter.FAKE.equals(article.state)) {
                String str2;
                if (article.isVideoArticle()) {
                    str2 = article.absPicPath;
                } else {
                    str2 = QsbkApp.absoluteUrlOfMediumContentImage(this.S, article.image);
                }
                b(this.S, str2, this.A);
            } else if ("pending".equals(article.state) || ManageMyContentsAdapter.WAITING.equals(article.state)) {
                this.F.setVisibility(8);
                this.A.setBackgroundResource(R.drawable.thumb_pic);
            } else {
                this.F.setVisibility(8);
                this.A.setBackgroundDrawable(TileBackground.getBackgroud(getContext(), BgImageType.ARTICLE));
            }
        }
        initVoteInfo();
        if (a(article)) {
            a((RssArticle) article);
        }
        if (article.isImageArticle()) {
            this.z.setVisibility(8);
            this.aK.setVisibility(0);
            this.aK.setImages(article.imageInfos);
            this.aK.setOnChildClickListener(new x(this, article));
        } else {
            this.aK.setVisibility(8);
        }
        if (article.isVideoArticle()) {
            this.G.setVisibility(0);
            this.z.setVisibility(0);
            this.K.getProgressBar().setVisibility(8);
            c(article);
        } else {
            this.z.setVisibility(8);
            this.J.setVisibility(8);
            this.G.setVisibility(8);
            this.K.setVisibility(8);
            this.K.getPlayBtn().setVisibility(4);
            this.K.getProgressBar().setVisibility(8);
        }
        View view = this.L;
        if (article.isGIFArticle()) {
            videoLayoutParams = 0;
        } else {
            videoLayoutParams = 8;
        }
        view.setVisibility(videoLayoutParams);
        if (article.qiushiTopic == null || !article.qiushiTopic.hasEvent()) {
            this.au.setVisibility(4);
        } else {
            this.au.setVisibility(0);
            FrescoImageloader.displayImage(this.au, article.qiushiTopic.eventWindow.iconUrl, 0, R.drawable.ic_get_laisee);
            this.au.setOnClickListener(new y(this, article));
        }
        this.aL.setOnClickListener(new z(this, article));
        if (this.ao && a(article)) {
            RssArticle rssArticle = (RssArticle) article;
            rssArticle.type = "promote";
            if (!TextUtils.isEmpty(rssArticle.type)) {
                this.au.setVisibility(4);
            }
            a(rssArticle, this.I);
        }
        this.m.setArticleId(article.id);
    }

    public void setImageLayoutParams(ImageView imageView, ImageSize imageSize, int i, int i2) {
        LayoutParams layoutParams = imageView.getLayoutParams();
        int[] iArr = new int[2];
        ImageSizeHelper.calWidthAndHeight(i, i2, iArr, imageSize);
        if (layoutParams != null) {
            layoutParams.width = iArr[0];
            layoutParams.height = iArr[1];
        } else {
            layoutParams = new LayoutParams(iArr[0], iArr[1]);
        }
        imageView.setLayoutParams(layoutParams);
    }

    public int setVideoLayoutParams(ImageView imageView, ImageSize imageSize, int i, int i2) {
        LayoutParams layoutParams = imageView.getLayoutParams();
        int[] iArr = new int[2];
        int calWidthAndHeight = ImageSizeHelper.calWidthAndHeight(i, i2, iArr, imageSize, false);
        if (layoutParams != null) {
            layoutParams.width = iArr[0];
            layoutParams.height = iArr[1];
        } else {
            layoutParams = new LayoutParams(iArr[0], iArr[1]);
        }
        imageView.setLayoutParams(layoutParams);
        return calWidthAndHeight;
    }

    protected void a(RssArticle rssArticle, TextView textView) {
        SubscribeAdapter.initType(rssArticle, textView);
    }

    protected void a(RssArticle rssArticle) {
        this.H.belongTo(this.l.id);
        this.H.setDiggers((RssArticle) this.l, false);
        a(rssArticle, this.I);
    }

    protected void c(Article article) {
        CharSequence charSequence;
        int i;
        this.K.getPlayBtn().setVisibility(0);
        this.K.setVideo(this.l.getVideoUrl());
        if (article.absPicWidth != 0 && article.absPicHeight != 0) {
            this.K.setAspectRatio(article.absPicWidth, article.absPicHeight);
        } else if (article.image_size != null) {
            if (article.image_size.mediumSize() != null) {
                this.K.setAspectRatio(article.image_size.mediumSize().getWidth(), article.image_size.mediumSize().getHeight());
            } else if (article.image_size.smallSize() != null) {
                this.K.setAspectRatio(article.image_size.smallSize().getWidth(), article.image_size.smallSize().getHeight());
            }
        }
        ((SimpleVideoPlayerView) this.K).setStrTotalTime(article.getVideoDurationText());
        this.K.setOnVideoEventListener(this.aS);
        String loopString = article.getLoopString();
        if (loopString.startsWith(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
            Object obj = loopString;
        } else {
            charSequence = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + loopString;
        }
        SimpleVideoPlayerView simpleVideoPlayerView = (SimpleVideoPlayerView) this.K;
        if (article.isGIFArticle()) {
            i = 1;
        } else {
            i = 0;
        }
        simpleVideoPlayerView.setDisplayMode(i);
        this.J.setVisibility(0);
        this.J.setText(charSequence);
        this.J.setVisibility(article.isVideoArticle() ? 0 : 8);
        if (this.l.isGIFArticle()) {
            this.K.setVisibility(0);
            if (!this.K.isPlaying()) {
                this.K.play();
            }
        } else if (this.l.isVideoArticle() && QsbkApp.getInstance().isAutoPlayConfiged()) {
            this.K.setVisibility(4);
        }
    }

    public void initVoteState() {
        if (this.B != null) {
            this.B.reset();
            boolean containsVote = VoteManager.getInstance().containsVote(this.S, "up");
            if (containsVote) {
                this.B.setSupport();
            }
            if (VoteManager.getInstance().containsVote(this.S, Config.DEVICE_NAME) && !containsVote) {
                this.B.setUnSupport();
            }
            Object obj = (QsbkApp.allCollection == null || !QsbkApp.allCollection.contains(this.S)) ? null : 1;
            if (obj != null) {
                this.E.setTag("active");
            } else {
                this.E.setTag("enable");
            }
        }
    }

    public void initVoteInfo() {
        if (this.C != null && this.D != null && isShowCommentLayout()) {
            UIHelper.setSupportAndCommentTextHighlight(this.C, this.D, this.l.getDisplayLaugth(), this.l.comment_count, this.l.shareCount, false);
        }
    }

    protected void a(String str, String str2, ImageView imageView) {
        Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(str2, str);
        if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
            imageView.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            FrescoImageloader.displayAvatar(imageView, absoluteUrlOfMediumUserIcon);
        }
    }

    protected void b(String str, String str2, ImageView imageView) {
        if (TextUtils.isEmpty(str2) || imageView == null) {
            this.F.setVisibility(8);
            imageView.setVisibility(8);
            return;
        }
        imageView.setVisibility(0);
        if (!this.l.isVideoArticle() || this.aJ == -1) {
            FrescoImageloader.displayImage(imageView, str2, TileBackground.getBackgroud(getContext(), BgImageType.ARTICLE));
            return;
        }
        Drawable backgroud = TileBackground.getBackgroud(getContext(), BgImageType.ARTICLE);
        FrescoImageloader.displayImage(imageView, str2, backgroud, backgroud, new ac(this, imageView, str2));
    }

    public boolean isShowCommentLayout() {
        if (this.l == null || (!"publish".equals(this.l.state) && !ManageMyContentsAdapter.FAKE.equals(this.l.state))) {
            return false;
        }
        return true;
    }

    private void E() {
        if (QsbkApp.currentUser != null) {
            Comment comment = new Comment();
            comment.id = "-1";
            HashMap hashMap = new HashMap();
            comment.content = a(hashMap);
            comment.liked = false;
            comment.uid = QsbkApp.currentUser.userId;
            comment.userName = QsbkApp.currentUser.userName;
            comment.icon = QsbkApp.currentUser.userIcon;
            comment.createAt = (int) (System.currentTimeMillis() / 1000);
            if (!TextUtils.isEmpty(this.aE)) {
                comment.smallImage = new Image(this.aE);
            }
            hashMap.put("content", a(hashMap));
            hashMap.put("anonymous", Boolean.valueOf(false));
            if (!(this.Q == null || TextUtils.equals("-1", this.Q.id))) {
                comment.reply = this.Q;
            }
            this.q.removeFooterView(this.M);
            initVoteInfo();
            this.n.a.add(0, comment);
            if (QsbkApp.currentUser != null && this.l.isMine(QsbkApp.currentUser.userId)) {
                this.p.a.add(0, comment);
                this.m.addOwnerCount(1);
            }
            this.m.notifyDataSetChanged();
        }
    }

    private void a(List<Comment> list) {
        if (list != null && list.size() > 0 && TextUtils.equals("-1", ((Comment) list.get(0)).id)) {
            list.remove(0);
        }
    }

    protected void s() {
        try {
            if (this.P != null) {
                Comment newInstance = Comment.newInstance(this.P);
                if (newInstance != null) {
                    if (this.Q != null) {
                        newInstance.reply = this.Q;
                    }
                    Iterator it = this.n.a.iterator();
                    while (it.hasNext()) {
                        if (((Comment) it.next()).equals(newInstance)) {
                            return;
                        }
                    }
                    Article article = this.l;
                    article.comment_count++;
                    initVoteInfo();
                    this.m.setAllCount(this.l.comment_count);
                    a(this.n.a);
                    this.n.a.add(0, newInstance);
                    if (QsbkApp.currentUser != null && this.l.isMine(QsbkApp.currentUser.userId)) {
                        a(this.p.a);
                        this.p.a.add(0, newInstance);
                    }
                    this.m.notifyDataSetChanged();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean a(String str, String str2, String str3) {
        Vote vote = new Vote(this.r, str, str2, "1");
        if (DebugUtil.DEBUG) {
            Log.i(getClass().getSimpleName(), "投票:" + vote.toString());
        }
        return VoteManager.getInstance().vote(vote, str, str2);
    }

    protected boolean t() {
        return QsbkApp.currentDataSource != null && QsbkApp.currentDataSource.size() > QsbkApp.currentSelectItem && QsbkApp.currentSelectItem > -1;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        FileInputStream fileInputStream;
        FileNotFoundException e;
        Throwable th;
        ShareUtils shareUtils = new ShareUtils();
        DebugUtil.debug(this.j, "onActivityResult, requestCode=" + i + ",resultCode=" + i2 + ",data=" + intent);
        if (i == 87 && i2 == -1) {
            BaseUserInfo baseUserInfo = (BaseUserInfo) intent.getSerializableExtra(QsbkDatabase.USER_TABLE_NAME);
            this.a.getText().insert(this.aR + 1, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            AtInfo atInfo = new AtInfo();
            atInfo.uid = baseUserInfo.userId;
            atInfo.name = baseUserInfo.userName;
            atInfo.span = new TextBlockSpan("@" + atInfo.name, this.a, UIHelper.getTopicLinkColor());
            this.a.getText().setSpan(atInfo.span, this.aR, this.aR + 1, 33);
            this.aQ.add(atInfo);
            this.a.requestFocus();
            UIHelper.showKeyboard(getActivity());
            return;
        }
        if (i == this.aD && intent != null) {
            List list = (List) intent.getSerializableExtra("paths");
            if (list != null && list.size() != 0) {
                ImageInfo imageInfo = (ImageInfo) list.get(0);
                if (imageInfo != null) {
                    this.aE = UriUtil.getRealPathFromUri(getContext().getContentResolver(), Uri.parse(imageInfo.getImageUrl()));
                    try {
                        if (this.aE != null) {
                            fileInputStream = new FileInputStream(new File(this.aE));
                            try {
                                if (!(TextUtils.isEmpty(this.aE) || fileInputStream == null)) {
                                    this.aA.setVisibility(0);
                                    FrescoImageloader.displayImage(this.aB, imageInfo.getImageUrl(), UIHelper.getDefaultImageTileBackground());
                                }
                            } catch (FileNotFoundException e2) {
                                e = e2;
                                try {
                                    e.printStackTrace();
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e3) {
                                            e3.printStackTrace();
                                        }
                                    }
                                    this.a.requestFocus();
                                    return;
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (fileInputStream != null) {
                                        try {
                                            fileInputStream.close();
                                        } catch (IOException e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                    throw th;
                                }
                            }
                        }
                        fileInputStream = null;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                            }
                        }
                    } catch (FileNotFoundException e5) {
                        e = e5;
                        fileInputStream = null;
                        e.printStackTrace();
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        this.a.requestFocus();
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                        fileInputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                    this.a.requestFocus();
                    return;
                }
            }
            return;
        }
        if (i2 >= 1) {
            if (i == 1) {
                ShareUtils.doShare(i2, getActivity(), this, this.l, this.E);
                if (i2 == 11) {
                    delete(this.l);
                } else if (i2 == 13) {
                    anonymous(this.l);
                } else if (i2 == 14) {
                    forbid(this.l);
                } else if (i2 == 15) {
                    ShareUtils.shareArticle2QiuyouCircle(getContext(), this.l);
                } else if (i2 == 12 && this.K != null) {
                    if (QsbkApp.getInstance().isAutoPlayVideo()) {
                        this.K.download();
                    } else {
                        this.K.downloadWithoutPlay();
                    }
                }
            } else if (i == 2) {
                ShareUtils.Share(getContext(), this.l.id, i2);
            } else if (i == 3) {
                ReportArticle.setReportArticle(this.l, i2);
                ReportArticle.reportHandler(true);
            } else if (i == 9) {
                ToastAndDialog.makePositiveToast(QsbkApp.mContext, "已成功分享！", Integer.valueOf(0)).show();
            }
            super.onActivityResult(i, i2, intent);
        }
    }

    protected void u() {
        this.ad = new GestureDetector(this);
        if (this.N == null) {
            this.N = this.at.findViewById(R.id.root);
        }
        this.N.setOnTouchListener(this);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.ad.onTouchEvent(motionEvent);
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (motionEvent != null && motionEvent2 != null && motionEvent.getX() < 70.0f && motionEvent2.getX() - motionEvent.getX() > ((float) this.ag) && Math.abs(f) > ((float) this.ah)) {
            finish();
        }
        return false;
    }

    public void finish() {
        a(false);
        f();
    }

    public void onLongPress(MotionEvent motionEvent) {
    }

    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        return false;
    }

    public void onShowPress(MotionEvent motionEvent) {
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    public void onArticleUpdate(Article article) {
        if (this.l != null && this.l.equals(article)) {
            this.l.updateWith(article);
            b(this.l);
            initVoteState();
            initVoteInfo();
            B();
        }
    }

    public boolean onBackPressed() {
        if (!g()) {
            return false;
        }
        a();
        return true;
    }

    public void forbidComment(Comment comment, int i) {
        if (comment != null) {
            new Builder(getContext()).setTitle("删除该评论并封禁该用户？").setItems(new String[]{"删除并封禁", "取消"}, new ae(this, comment)).show();
        }
    }

    public void forbidComment(Comment comment) {
        String format = String.format(Constants.ADMIN_FORBID_QIUSHI_COMMENT, new Object[]{Integer.valueOf(Integer.parseInt(comment.id))});
        Map hashMap = new HashMap();
        hashMap.put("comment_id", comment.id);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new af(this));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void deleteComment(Comment comment, int i) {
        reportEvent(true);
        Map hashMap = new HashMap();
        hashMap.put("comment_id", comment.id);
        hashMap.put("user_id", QsbkApp.currentUser.userId);
        hashMap.put("article_id", this.l.id);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.DELETE_OTHER_COMMENT, new ag(this, comment));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.executeOnExecutor(SimpleHttpTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void a(Comment comment, int i) {
        reportEvent(false);
        Map hashMap = new HashMap();
        hashMap.put("comment_id", comment.id);
        hashMap.put("user_id", QsbkApp.currentUser.userId);
        hashMap.put("article_id", this.S);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(Constants.DELETE_MY_COMMENT, new ah(this, comment));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.executeOnExecutor(SimpleHttpTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void a(Comment comment) {
        if (QsbkApp.currentUser == null) {
            LoginPermissionClickDelegate.startLoginActivity(getActivity());
            return;
        }
        String str;
        CharSequence substring;
        String str2 = comment.content;
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        if (comment.reply == null || !str2.startsWith("回复 ")) {
            str = str2;
        } else {
            str = str2;
            int i = 3;
            while (i < str.length()) {
                char charAt = str.charAt(i);
                if (charAt < '0' || charAt > '9') {
                    if (i <= 3 || charAt != '楼' || i + 1 >= str.length() || str.charAt(i + 1) != '：') {
                        break;
                    }
                    str = str.substring(i + 2);
                }
                i++;
            }
        }
        StringBuffer append = new StringBuffer("举报 ").append(comment.userName).append("：").append(str);
        if (append.length() > 30) {
            substring = append.substring(0, 29);
        } else {
            substring = append.toString();
        }
        String[] strArr = new String[]{"abusive", "porn", ManageMyContentsAdapter.SPAM, "waste"};
        new Builder(getContext()).setTitle(substring).setItems(new String[]{"辱骂", "色情", "广告/欺诈", "浪费楼层"}, new aj(this, comment)).setNegativeButton("取消", new ai(this)).show();
    }

    protected int v() {
        return R.layout.activity_singlearticle;
    }

    protected int w() {
        return R.layout.layout_single_article_head;
    }

    public void onPause() {
        super.onPause();
        x();
        if (this.K != null && this.K.isPlaying()) {
            this.K.pause();
        }
    }

    private String F() {
        String stringBuilder;
        JSONArray jSONArray = new JSONArray();
        if (this.aQ.size() > 0) {
            int i;
            CharSequence text = this.a.getText();
            int[] iArr = new int[this.aQ.size()];
            StringBuilder stringBuilder2 = new StringBuilder();
            int i2 = 1;
            for (i = 0; i < this.aQ.size(); i++) {
                JSONObject jSONObject = new JSONObject();
                AtInfo atInfo = (AtInfo) this.aQ.get(i);
                try {
                    jSONObject.put(atInfo.name, Integer.parseInt(atInfo.uid));
                    jSONArray.put(jSONObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                iArr[i] = text.getSpanStart(atInfo.span);
                if (i2 != 0) {
                    i2 = 0;
                } else {
                    stringBuilder2.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
                stringBuilder2.append(atInfo.uid);
            }
            StringBuilder stringBuilder3 = new StringBuilder(text);
            for (i = 0; i < iArr.length; i++) {
                int i3 = iArr[i];
                String str = ((AtInfo) this.aQ.get(i)).name;
                stringBuilder3.insert(i3 + 1, str);
                for (int i4 = 0; i4 < iArr.length; i4++) {
                    if (iArr[i4] > i3) {
                        iArr[i4] = iArr[i4] + str.length();
                    }
                }
            }
            stringBuilder = stringBuilder3.toString();
        } else {
            stringBuilder = this.a.getText().toString();
        }
        stringBuilder = emotionTrim(stringBuilder);
        if (this.Q == null) {
            return stringBuilder;
        }
        return String.format("回复 %d楼：", new Object[]{Integer.valueOf(this.Q.floor)}) + stringBuilder;
    }

    private String a(HashMap<String, Object> hashMap) {
        String stringBuilder;
        JSONObject jSONObject = new JSONObject();
        LogUtil.e("at:" + this.aQ.size());
        if (this.aQ.size() > 0) {
            int i;
            CharSequence text = this.a.getText();
            int[] iArr = new int[this.aQ.size()];
            StringBuilder stringBuilder2 = new StringBuilder();
            int i2 = 1;
            for (i = 0; i < this.aQ.size(); i++) {
                AtInfo atInfo = (AtInfo) this.aQ.get(i);
                try {
                    jSONObject.put(atInfo.name, Integer.parseInt(atInfo.uid));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                iArr[i] = text.getSpanStart(atInfo.span);
                if (i2 != 0) {
                    i2 = 0;
                } else {
                    stringBuilder2.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
                stringBuilder2.append(atInfo.uid);
            }
            StringBuilder stringBuilder3 = new StringBuilder(text);
            for (i = 0; i < iArr.length; i++) {
                int i3 = iArr[i];
                String str = ((AtInfo) this.aQ.get(i)).name;
                stringBuilder3.insert(i3 + 1, str);
                for (int i4 = 0; i4 < iArr.length; i4++) {
                    if (iArr[i4] > i3) {
                        iArr[i4] = iArr[i4] + str.length();
                    }
                }
            }
            stringBuilder = stringBuilder3.toString();
        } else {
            stringBuilder = this.a.getText().toString();
        }
        if (jSONObject != null) {
            hashMap.put("at_infos", jSONObject);
        }
        stringBuilder = emotionTrim(stringBuilder);
        if (this.Q == null) {
            return stringBuilder;
        }
        return String.format("回复 %d楼：", new Object[]{Integer.valueOf(this.Q.floor)}) + stringBuilder;
    }

    protected void x() {
        if (this.a != null && !TextUtils.isEmpty(this.S)) {
            String trim;
            if (this.aQ.size() > 0) {
                int i;
                CharSequence text = this.a.getText();
                int[] iArr = new int[this.aQ.size()];
                StringBuilder stringBuilder = new StringBuilder();
                Object obj = 1;
                for (i = 0; i < this.aQ.size(); i++) {
                    AtInfo atInfo = (AtInfo) this.aQ.get(i);
                    iArr[i] = text.getSpanStart(atInfo.span);
                    if (obj != null) {
                        obj = null;
                    } else {
                        stringBuilder.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
                    }
                    stringBuilder.append(atInfo.uid);
                }
                StringBuilder stringBuilder2 = new StringBuilder(text);
                for (i = 0; i < iArr.length; i++) {
                    int i2 = iArr[i];
                    String str = ((AtInfo) this.aQ.get(i)).name;
                    stringBuilder2.insert(i2 + 1, str);
                    for (int i3 = 0; i3 < iArr.length; i3++) {
                        if (iArr[i3] > i2) {
                            iArr[i3] = iArr[i3] + str.length();
                        }
                    }
                }
                trim = stringBuilder2.toString().trim();
            } else {
                trim = this.a.getText().toString();
            }
            trim = emotionTrim(trim);
            LogUtil.e(trim);
            String y = y();
            if (!trim.equalsIgnoreCase(SharePreferenceUtils.getSharePreferencesValue(y))) {
                SharePreferenceUtils.setSharePreferencesValue(y, trim);
            }
            SharePreferenceUtils.setSharePreferencesValue(z(), this.Q != null ? this.Q.toJson().toString() : "");
        }
    }

    protected String y() {
        return "draftCommentContent_" + this.S;
    }

    protected String z() {
        return "draftCommentContent_r_" + this.S;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                break;
            case R.id.action_about:
                Intent intent = new Intent(getActivity(), About.class);
                intent.putExtra("targetPage", "about");
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void a(Bundle bundle) {
        ShareUtils.registerShareListener(this);
        u();
        q();
        if (this.l != null) {
            this.m.setArticle(this.l);
            l();
            b(this.l);
            k();
        }
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.an, new IntentFilter(MyInfoActivity.CHANGE_REMARK));
    }

    public void showBuddleDialog() {
        if (this.ab) {
            this.Z = new QiushiBuddleDialog(getContext(), R.style.user_info_dialog);
            this.aa = this.Z.getWindow();
            WindowManager.LayoutParams attributes = this.aa.getAttributes();
            this.aa.setGravity(51);
            int i = (int) (48.0f * Util.density);
            attributes.x = (int) (10.0f * Util.density);
            attributes.y = (int) (((float) i) + (65.0f * Util.density));
            this.aa.setAttributes(attributes);
            this.Z.setCanceledOnTouchOutside(false);
            this.Z.show();
            ((TextView) this.Z.findViewById(R.id.user_info_textView)).setOnClickListener(new ak(this));
        }
    }

    public SingleArticleAdapter getAdapter() {
        return this.m;
    }

    public void onMoreClick(View view, Comment comment, int i) {
        if (view != null && comment != null) {
            this.av.a(i, comment, view, this.at);
        }
    }

    public void reply(Comment comment) {
        if (QsbkApp.currentUser == null) {
            LoginPermissionClickDelegate.startLoginActivity(getActivity());
        } else {
            b(comment);
        }
    }

    protected void a(String str, String str2, int i, String str3) {
        Comment comment = new Comment();
        comment.userName = str;
        comment.content = str2;
        comment.floor = i;
        comment.uid = str3;
        b(comment);
    }

    protected void b(Comment comment) {
        String str;
        String str2 = comment.content;
        if (TextUtils.isEmpty(str2)) {
            str2 = "";
        }
        if (str2.startsWith("回复 ")) {
            str = str2;
            int i = 3;
            while (i < str.length()) {
                char charAt = str.charAt(i);
                if (charAt < '0' || charAt > '9') {
                    if (i <= 3 || charAt != '楼' || i + 1 >= str.length() || str.charAt(i + 1) != '：') {
                        break;
                    }
                    str = str.substring(i + 2);
                }
                i++;
            }
        } else {
            str = str2;
        }
        if (comment.hasImage()) {
            str = str + "[图片]";
        }
        str2 = RemarkManager.getRemark(comment.uid);
        if (TextUtils.isEmpty(str2)) {
            str2 = comment.userName;
        }
        this.ar.setText(String.format("回复 %s：%s", new Object[]{str2, str}));
        this.aq.setVisibility(0);
        this.Q = comment;
        this.a.setHint("回复 " + str2 + "：");
        this.a.requestFocus();
        this.a.performClick();
    }

    public void delete(Article article) {
        if (article != null) {
            new Builder(getActivity()).setTitle("刪除此条糗事？").setItems(new String[]{"立即删除", "取消"}, new al(this, article)).show();
        }
    }

    private void d(Article article) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.DELETE_CREATE, new Object[]{article.id}), new am(this));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void anonymous(Article article) {
        if (article != null) {
            new Builder(getActivity()).setTitle("将此条糗事匿名？").setItems(new String[]{"匿名", "取消"}, new an(this, article)).show();
        }
    }

    private void e(Article article) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.ANONYMOUS_CREATE, new Object[]{article.id}), new ap(this));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void forbid(Article article) {
        if (article != null) {
            new Builder(getActivity()).setTitle("删除该糗事并封禁该用户？").setItems(new String[]{"删除并封禁", "取消"}, new aq(this, article)).show();
        }
    }

    private void f(Article article) {
        new SimpleHttpTask(String.format(Constants.FORBID_CREATE, new Object[]{article.id}), new ar(this)).setMapParams(new HashMap());
    }

    protected void b(int i) {
        switch (i) {
            case 0:
                this.q.setLoadMoreFinished();
                if (this.l != null && !"publish".equals(this.l.state) && !ManageMyContentsAdapter.FAKE.equals(this.l.state)) {
                    G();
                    return;
                }
                return;
            case 1:
                this.q.setLoadingMore(true);
                return;
            case 2:
                i();
                return;
            case 3:
                this.M.setImgAndTextViewGone();
                if (this.m.isNormalPage()) {
                    if (this.n.a.size() == 0) {
                        this.ay.set(UIHelper.getCommentEmptyImg(), "暂无评论，快来抢地主吧~");
                        this.ay.show();
                        return;
                    }
                    return;
                } else if (this.p.a.size() == 0) {
                    this.ay.set(UIHelper.getCommentEmptyImg(), "暂无楼主评论~");
                    this.ay.show();
                    return;
                } else {
                    return;
                }
            case 4:
                G();
                return;
            default:
                return;
        }
    }

    private void G() {
        this.q.setVisibility(8);
        this.ax.set(UIHelper.getEmptyImg(), "糗事不存在");
        this.ax.show();
        this.t.setVisibility(8);
        if (this.Z != null) {
            this.Z.cancel();
        }
        if (this.T != null) {
            this.T.setVisibility(8);
            this.N.setVisibility(0);
        }
        this.aq.setVisibility(8);
        f();
    }

    protected void A() {
    }

    public void onEmotionItemClick(int i, ChatMsgEmotionData chatMsgEmotionData) {
        if (QiushiEmotionHandler$EmotionData.DELETE.getName().equals(chatMsgEmotionData.name)) {
            this.a.dispatchKeyEvent(new KeyEvent(0, 67));
            return;
        }
        int max = Math.max(this.a.getSelectionStart(), 0);
        int max2 = Math.max(this.a.getSelectionEnd(), 0);
        this.a.getText().replace(Math.min(max, max2), Math.max(max, max2), chatMsgEmotionData.name, 0, chatMsgEmotionData.name.length());
    }

    private void g(Article article) {
        HttpTask httpTask = new HttpTask(String.format(Constants.ARTICLE_UNLIKE, new Object[]{article.id}), new as(this));
        Map hashMap = new HashMap();
        hashMap.put("type", "topic");
        hashMap.put("toid", Integer.valueOf(article.qiushiTopic.id));
        httpTask.setMapParams(hashMap);
        httpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void h(Article article) {
        HttpTask httpTask = new HttpTask(String.format(Constants.ARTICLE_UNLIKE, new Object[]{article.id}), new at(this));
        Map hashMap = new HashMap();
        hashMap.put("type", "user");
        hashMap.put("toid", article.user_id);
        httpTask.setMapParams(hashMap);
        httpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void H() {
        if ((this.m.hotComments == null || (this.m.hotComments.size() == 0 && this.aO)) && isResumed() && getView() != null && getView().isShown() && getUserVisibleHint() && !this.aP && QsbkApp.currentUser != null && QsbkApp.currentUser.isNewUser() && !SharePreferenceUtils.getSharePreferencesBoolValue("new_hot_comment_showcase")) {
            this.aP = true;
            ToastAndDialog.makeText(getActivity(), "帖子还没有热评哦，开始你的表演吧！").show();
        }
    }

    public void setOnFragmentCreatedListener(OnFragmentCreatedListener onFragmentCreatedListener) {
        this.ai = onFragmentCreatedListener;
    }

    public String getArticleId() {
        return this.S;
    }
}
