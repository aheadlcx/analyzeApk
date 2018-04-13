package qsbk.app.activity.publish;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.facebook.common.util.UriUtil;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.ConfigManager;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ImagesPickerActivity;
import qsbk.app.activity.ManageQiuShiNewActivity;
import qsbk.app.activity.base.BaseEmotionActivity;
import qsbk.app.activity.base.ResultFragmentActivity;
import qsbk.app.adapter.ManageMyContentsAdapter;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.HttpTask;
import qsbk.app.im.ChatMsgEmotionData;
import qsbk.app.im.emotion.EmotionGridView.OnEmotionItemClickListener;
import qsbk.app.im.emotion.EmotionViewPager;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.live.ui.LivePushActivity;
import qsbk.app.model.Article;
import qsbk.app.model.ArticleImage;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.EventWindow;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.QiushiTopic;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.nearby.api.ILocationCallback;
import qsbk.app.nearby.api.ILocationManager;
import qsbk.app.nearby.api.LocationCache;
import qsbk.app.nearby.api.LocationCache.Location;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.utils.AnimateDotRunnable;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ResultActivityListener;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;
import qsbk.app.video.VideoPickerActivity;
import qsbk.app.video.VideoPlayerView;
import qsbk.app.video.VideoUploader.TokenResp;
import qsbk.app.widget.DotView;
import qsbk.app.widget.QiushiDeleteViewFactory;
import qsbk.app.widget.QiushiEmotionHandler;
import qsbk.app.widget.QiushiEmotionHandler$EmotionData;
import qsbk.app.widget.QiushiImageLayout;
import qsbk.app.widget.RoundedDrawable;
import qsbk.app.widget.SizeNotifierRelativeLayout;
import qsbk.app.widget.SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate;
import qsbk.app.widget.qiushitopic.MultiLayoutEditText;

public class PublishActivity extends ResultFragmentActivity implements OnEmotionItemClickListener, ILocationCallback, SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate {
    public static final String ARTICLE_ARTICLE = "draftQiushiArticle";
    public static final String ARTICLE_CONTENT = "draftContent";
    public static final String ARTICLE_PIC = "draftPic";
    public static final String ARTICLE_VIDEO = "draftVideo";
    public static final float[] BT_SELECTED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, -35.0f, 0.0f, 1.0f, 0.0f, 0.0f, -35.0f, 0.0f, 0.0f, 1.0f, 0.0f, -35.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    public static final String FIRST_PUBLISH = "qiushi_first_publish";
    public static final String KEY_CAMERY_REQUEST_CODE = "CAMERY_REQUEST_CODE";
    public static final String KEY_PUBLISH_ARTICLE = "_publis_article_";
    public static final String KEY_VIDEO_REQUEST_CODE = "VIDEO_REQUEST_CODE";
    public static final String PREF_KEY_SHOW_PUBLISH_ARTICLE_TIPS = "show_publish_article_tips";
    public static HashMap<String, Boolean> isPublishingArticle = new HashMap();
    public static boolean mIsFromLocal = false;
    private static final String o = PublishActivity.class.getSimpleName();
    private AnimateDotRunnable A;
    private boolean B = false;
    private ImageView C;
    private TextView D;
    private CheckBox E;
    private Article F;
    private QiushiTopic G = QiushiTopic.EMPTY;
    private Article H;
    private String I;
    private View J;
    private OnGlobalLayoutListener K;
    private PopupWindow O;
    private EventWindow P;
    private ImageView Q;
    private View R;
    private EmotionViewPager S;
    private DotView T;
    private int U;
    private boolean V;
    private boolean W;
    private boolean X;
    private InputMethodManager Y;
    private ProgressDialog Z = null;
    protected ImageView a;
    private boolean aa = false;
    private int ab = 0;
    private int ac = 0;
    private String ad = "";
    private ResultActivityListener ae = new an(this);
    private ImageView af;
    private View ag;
    private View ah;
    private String ai = null;
    private ResultActivityListener aj = new ba(this);
    private int ak;
    private int al;
    private TokenResp am = null;
    private int an = 0;
    private int ao = 0;
    private ResultActivityListener ap = new bo(this);
    MultiLayoutEditText b;
    TextView c;
    QiushiImageLayout d = null;
    SizeNotifierRelativeLayout e;
    ImageButton f;
    ImageButton g;
    ImageButton h;
    ImageButton i;
    ArrayList<ImageInfo> j = new ArrayList();
    String k;
    Handler l = new Handler();
    boolean m = false;
    public ImageView mCloseVidwoView;
    public RelativeLayout mVideoLayout;
    Runnable n = new bl(this);
    private String p;
    public VideoPlayerView player;
    private int q;
    private boolean r = false;
    private Boolean s = Boolean.valueOf(true);
    private int t = -1;
    private View u;
    private View v;
    private TextView w;
    private Location x;
    private ILocationManager y;
    private int z = 0;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return g();
    }

    public static void removeFromLocal(Article article) {
        if (article != null) {
            if (!TextUtils.isEmpty(article.editUuid)) {
                File file = new File(DeviceUtils.getSDPath() + File.separator + "qsbk/send/" + article.editUuid + ".jpg");
                if (file.exists() && !file.isDirectory()) {
                    file.delete();
                }
            }
            Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(KEY_PUBLISH_ARTICLE);
            if (!TextUtils.isEmpty(sharePreferencesValue)) {
                JSONArray jSONArray = new JSONArray();
                try {
                    JSONArray jSONArray2 = new JSONArray(sharePreferencesValue);
                    for (int i = 0; i < jSONArray2.length(); i++) {
                        Article parseJsonFromLocal = Article.parseJsonFromLocal(jSONArray2.getJSONObject(i));
                        if (TextUtils.isEmpty(parseJsonFromLocal.uuid) || TextUtils.isEmpty(article.uuid) || !parseJsonFromLocal.uuid.equals(article.uuid)) {
                            jSONArray.put(jSONArray2.get(i));
                        }
                    }
                    SharePreferenceUtils.setSharePreferencesValue(KEY_PUBLISH_ARTICLE, jSONArray.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void changeFromLocal(Article article) {
        if (article != null) {
            Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(KEY_PUBLISH_ARTICLE);
            LogUtil.e("publish localStr+ " + sharePreferencesValue);
            LogUtil.e("publish str " + article.toString());
            if (!TextUtils.isEmpty(sharePreferencesValue)) {
                JSONArray jSONArray = new JSONArray();
                try {
                    JSONArray jSONArray2 = new JSONArray(sharePreferencesValue);
                    for (int i = 0; i < jSONArray2.length(); i++) {
                        Article parseJsonFromLocal = Article.parseJsonFromLocal(jSONArray2.getJSONObject(i));
                        if (TextUtils.isEmpty(parseJsonFromLocal.uuid) || TextUtils.isEmpty(article.uuid) || !parseJsonFromLocal.uuid.equals(article.uuid)) {
                            jSONArray.put(jSONArray2.get(i));
                        } else {
                            parseJsonFromLocal.stateExtra = 1;
                            jSONArray.put(Article.toJSONObjectToSaveLocal(parseJsonFromLocal));
                        }
                    }
                    SharePreferenceUtils.setSharePreferencesValue(KEY_PUBLISH_ARTICLE, jSONArray.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean allowLocation() {
        return QsbkApp.getInstance().getSharedPreferences("publish_art", 0).getBoolean("pa_location_access", false);
    }

    public static void clearDraft() {
        a("");
    }

    private static boolean n() {
        return SharePreferenceUtils.getSharePreferencesBoolValue(PREF_KEY_SHOW_PUBLISH_ARTICLE_TIPS);
    }

    public static void hidePublishTips() {
        SharePreferenceUtils.setSharePreferencesValue(PREF_KEY_SHOW_PUBLISH_ARTICLE_TIPS, true);
    }

    public void onBackPressed() {
        LogUtil.d("back pressed");
        if (this.O != null && this.O.isShowing()) {
            this.O.dismiss();
        } else if (this.R.isShown()) {
            m();
        } else {
            v();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 82) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private Article q() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(ARTICLE_ARTICLE);
        try {
            if (!TextUtils.isEmpty(sharePreferencesValue)) {
                return Article.parseJsonFromLocal(new JSONObject(sharePreferencesValue));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void a(String str) {
        SharePreferenceUtils.setSharePreferencesValue(ARTICLE_ARTICLE, str);
    }

    private boolean r() {
        try {
            a(Article.toJSONObjectToSaveLocal(getWillPublishArticle()).toString());
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    protected void f() {
        this.a = (ImageView) findViewById(R.id.send);
        this.a.setImageLevel(0);
        this.U = SharePreferenceUtils.getSharePreferencesIntValue(BaseEmotionActivity.KEYBOARD_HEIGHT);
        this.R = findViewById(R.id.emotions);
        this.S = (EmotionViewPager) findViewById(R.id.emotion_viewpager);
        this.T = (DotView) findViewById(R.id.emotion_dotview);
        this.S.setOnEmotionClickListener(this);
        this.S.setEmotionType(1);
        this.S.setPerPageCount(28);
        this.S.setRowCount(4);
        EmotionViewPager emotionViewPager = this.S;
        QiushiEmotionHandler.getInstance();
        emotionViewPager.setDatas(QiushiEmotionHandler.convert2ChatMsgEmotionData());
        this.S.setDotView(this.T);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.m = true;
        this.ai = intent.getStringExtra("video_path");
        this.ak = intent.getIntExtra("video_width", 0);
        this.al = intent.getIntExtra("video_height", 0);
        this.aa = true;
        mIsFromLocal = true;
        a(this.ai, this.ak, this.al);
        DebugUtil.debug(o, "onNewIntent:" + this.ai + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.ak + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.al);
    }

    protected void onCreate(Bundle bundle) {
        mIsFromLocal = false;
        requestWindowFeature(1);
        supportRequestWindowFeature(1);
        super.onCreate(bundle);
        DebugUtil.debug(o, "onCreate");
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.hide();
        }
        if (bundle != null) {
            int i = bundle.getInt(KEY_VIDEO_REQUEST_CODE, 0);
            if (i != 0) {
                this.M.addReqeustCodeListener(i, this.ap);
            }
        }
        s();
    }

    private void s() {
        new HttpTask(null, Constants.ACTIVITY_WINDOW_NEW, new bp(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    protected void onResume() {
        super.onResume();
        boolean booleanExtra = getIntent().getBooleanExtra("success", false);
        if (this.aa) {
            this.aa = false;
            if (booleanExtra) {
                a(this.ai, this.ak, this.al);
            } else {
                ToastAndDialog.makeNegativeToast(this, "该视频裁剪失败，我们工程师正在努力加油。").show();
            }
        }
    }

    private void t() {
        QsbkApp qsbkApp = (QsbkApp) getApplication();
        if (qsbkApp.getWaitSendBitmap() != null && !qsbkApp.getWaitSendBitmap().isRecycled()) {
            d(true);
            Bitmap waitSendBitmap = qsbkApp.getWaitSendBitmap();
            if (waitSendBitmap != null) {
                int width = waitSendBitmap.getWidth();
                int height = waitSendBitmap.getHeight();
                LogUtil.d("bm width:" + width);
                LogUtil.d("bm height:" + height);
                int i = UIHelper.getDisplayMetrics(this).widthPixels;
                height = (int) (((float) (height * (i - (UIHelper.dip2px(this, 20.0f) * 2)))) / ((float) width));
                LogUtil.d("imgView width:" + this.d.getWidth());
                LogUtil.d("deviceWidth width:" + i);
                LogUtil.d("deviceWidth width:" + height);
                this.d.requestLayout();
            }
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        QsbkApp qsbkApp = (QsbkApp) getApplication();
        if (!(qsbkApp.getWaitSendBitmap() == null || qsbkApp.getWaitSendBitmap().isRecycled())) {
            qsbkApp.getWaitSendBitmap().recycle();
            qsbkApp.setWaitSendBitmap(null);
        }
        if (this.y != null) {
            this.y.remove(this);
        }
        if (this.A != null) {
            this.A.stop();
        }
        if (VERSION.SDK_INT >= 16) {
            this.J.getViewTreeObserver().removeOnGlobalLayoutListener(this.K);
        } else {
            this.J.getViewTreeObserver().removeGlobalOnLayoutListener(this.K);
        }
    }

    protected void onPause() {
        this.l.removeCallbacks(this.n);
        super.onPause();
    }

    private boolean u() {
        return this.b.getQiushiContent().length() > 0 || this.j.size() > 0 || this.m || !QiushiTopic.EMPTY.equals(this.G);
    }

    private void v() {
        if (getCurrentFocus() != null) {
            this.Y.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
        if (u()) {
            new Builder(this).setItems(new String[]{"保存草稿", "不保存", "取消"}, new bq(this)).show();
            return;
        }
        finish();
    }

    private Builder a(int i) {
        Builder builder = new Builder(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.update_dialog_message, null);
        ((TextView) inflate.findViewById(R.id.updateMessage)).setText("只能添加一种附件,如果继续添加将删除当前附件");
        builder.setView(inflate);
        builder.setPositiveButton("确定", new bs(this, i)).setNegativeButton("取消", new br(this));
        return builder;
    }

    private void a(String str, int i, int i2) {
        C();
        LogUtil.d("set add ImageLayout to gone");
        this.mVideoLayout.setVisibility(0);
        LogUtil.d("mVidwoLayout.width:" + this.mVideoLayout.getWidth());
        this.mVideoLayout.getViewTreeObserver().addOnGlobalLayoutListener(new bt(this, i2, i, str));
        this.mVideoLayout.requestLayout();
        LogUtil.d("mVidwoLayout.width:" + this.mVideoLayout.getWidth());
    }

    private void w() {
        if (this.j.size() == 1) {
            ImageInfo imageInfo = (ImageInfo) this.j.get(0);
            Object realPathFromUri = UriUtil.getRealPathFromUri(getContentResolver(), Uri.parse(imageInfo.getImageUrl()));
            if (!TextUtils.isEmpty(realPathFromUri) && imageInfo.width == 0) {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(realPathFromUri, options);
                imageInfo.width = options.outWidth;
                imageInfo.height = options.outHeight;
            }
        }
        this.d.setImages(this.j);
    }

    private void a(boolean z) {
        this.r = z;
        if (z) {
            this.C.setColorFilter(null);
            this.C.setImageDrawable(RoundedDrawable.fromDrawable(this.C.getResources().getDrawable(UIHelper.getIconRssAnonymousUser())));
            this.D.setText(BaseUserInfo.ANONYMOUS_USER_NAME);
            return;
        }
        Object obj = QsbkApp.currentUser.userIcon;
        String str = QsbkApp.currentUser.userId;
        this.D.setText(QsbkApp.currentUser.userName);
        if (TextUtils.isEmpty(obj)) {
            this.C.setImageResource(UIHelper.getDefaultAvatar());
        } else {
            FrescoImageloader.displayAvatar(this.C, QsbkApp.absoluteUrlOfMediumUserIcon(obj, str));
        }
        if (UIHelper.isNightTheme()) {
            this.C.setColorFilter(new ColorMatrixColorFilter(BT_SELECTED));
        } else {
            this.C.setColorFilter(null);
        }
    }

    public void initView() {
        this.F = (Article) getIntent().getSerializableExtra("article");
        this.e = (SizeNotifierRelativeLayout) findViewById(R.id.size_nofify_layout);
        this.e.setSizeNotifierRelativeLayoutDelegate(this);
        this.J = findViewById(R.id.publish_content);
        this.C = (ImageView) findViewById(R.id.avatar);
        this.D = (TextView) findViewById(R.id.name);
        this.E = (CheckBox) findViewById(R.id.anonymous);
        this.E.setChecked(false);
        this.E.setOnCheckedChangeListener(new ao(this));
        a(this.r);
        this.ag = findViewById(R.id.play);
        this.player = (VideoPlayerView) findViewById(R.id.video_view);
        this.player.setDisplayMode(1);
        this.af = (ImageView) findViewById(R.id.video_one_frame);
        this.af.setEnabled(false);
        this.af.setFocusable(false);
        this.af.setFocusableInTouchMode(false);
        this.mVideoLayout = (RelativeLayout) findViewById(R.id.id_add_video_layout);
        this.b = (MultiLayoutEditText) findViewById(R.id.content);
        this.b.setOnClickListener(new ap(this));
        this.b.setOnTopicClickListener(new aq(this));
        this.c = (TextView) findViewById(R.id.hint);
        this.ah = findViewById(R.id.video_cover);
        this.player.setWidget(null, this.ag, this.ah);
        this.mCloseVidwoView = (ImageView) findViewById(R.id.delete_video);
        this.mCloseVidwoView.setOnClickListener(new ar(this));
        this.d = (QiushiImageLayout) findViewById(R.id.id_add_img_layout);
        this.d.setViewfactory(new QiushiDeleteViewFactory(new as(this)));
        this.d.setSpaceSize(0);
        this.f = (ImageButton) findViewById(R.id.id_add_topic);
        this.f.setOnTouchListener(QsbkApp.TouchDark);
        this.f.setOnClickListener(new at(this));
        this.f.postDelayed(new au(this), 200);
        this.K = new ax(this);
        this.J.getViewTreeObserver().addOnGlobalLayoutListener(this.K);
        this.g = (ImageButton) findViewById(R.id.id_from_albumn);
        this.g.setOnTouchListener(QsbkApp.TouchDark);
        this.g.setOnClickListener(new ay(this));
        this.h = (ImageButton) findViewById(R.id.id_from_video);
        this.h.setOnTouchListener(QsbkApp.TouchDark);
        this.h.setOnClickListener(new az(this));
        if (VERSION.SDK_INT < 11) {
            this.h.setVisibility(8);
        }
        if (ConfigManager.getInstance().isVideoPublishDisabled()) {
            this.h.setVisibility(8);
        }
        QiushiTopic qiushiTopic = (QiushiTopic) getIntent().getSerializableExtra("topic");
        if (this.F != null) {
            this.b.setText(this.F.content);
            this.G = this.F.qiushiTopic == null ? QiushiTopic.EMPTY : this.F.qiushiTopic;
        } else {
            y();
            if (!(QiushiTopic.EMPTY.equals(qiushiTopic) || qiushiTopic == null)) {
                this.G = qiushiTopic;
            }
        }
        this.b.setQiushiTopic(this.G);
        this.g.setVisibility(0);
        this.k = getString(R.string.content_hint);
        if (QsbkApp.indexConfig != null) {
            try {
                DebugUtil.debug(o, "postPageRemind:" + QsbkApp.indexConfig.optJSONObject("post_page_remind"));
                JSONObject optJSONObject = QsbkApp.indexConfig.optJSONObject("post_page_remind");
                if (optJSONObject.has("prompt")) {
                    this.k = optJSONObject.getString("prompt");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.i = (ImageButton) findViewById(R.id.id_add_emotion);
        this.i.setOnClickListener(new bb(this));
        this.b.post(new bc(this));
        this.b.addTextChangedListener(new bd(this));
        this.a.setOnClickListener(new be(this));
        this.u = findViewById(R.id.locationContainer);
        this.u.setBackgroundResource(UIHelper.getPublishLocationDefaultBackground());
        this.u.setOnClickListener(new bf(this));
        this.w = (TextView) findViewById(R.id.location);
        this.A = new AnimateDotRunnable(this.w, 3, 600);
        this.v = findViewById(R.id.close_location);
        this.v.setOnClickListener(new bg(this));
        this.b.setFocusable(true);
        this.b.requestFocus();
        this.Q = (ImageView) findViewById(R.id.event);
        this.Q.setOnClickListener(new bh(this));
    }

    private void b(String str) {
        StatService.onEvent(this, "publish_location", str);
    }

    private void x() {
        if (allowLocation() && this.x != null) {
            G();
        }
    }

    private void c(boolean z) {
        getSharedPreferences("publish_art", 0).edit().putBoolean("pa_location_access", z).apply();
    }

    private void y() {
        int i = 0;
        Article q = q();
        if (q != null) {
            String str = q.content;
            if (!TextUtils.isEmpty(str)) {
                this.b.setText(str);
                this.b.setSelection(str.length());
                this.a.setImageLevel(str.length() >= 5 ? 1 : 0);
            }
            if (q.isVideoArticle()) {
                this.m = true;
                this.ai = q.filePath;
                this.ak = q.mVideoWidth;
                this.al = q.mVideoHeight;
                if (new File(this.ai).exists()) {
                    a(this.ai, this.ak, this.al);
                }
            } else if (q.isImageArticle()) {
                this.j.clear();
                while (i < q.imageInfos.size()) {
                    ArticleImage articleImage = (ArticleImage) q.imageInfos.get(i);
                    if (ImageInfo.isUrlFile(articleImage.url)) {
                        this.j.add(articleImage);
                    }
                    i++;
                }
                w();
            }
            if (q.qiushiTopic != null) {
                setQiushiTopic(q.qiushiTopic);
            }
            clearDraft();
        }
    }

    public String getSendHint() {
        return getResources().getString(R.string.send_succ);
    }

    public String getUrl() {
        return Constants.ARTICLE_CREATE + "?imgsrc=" + this.t + "&from_topic=" + this.q;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.an != 0) {
            bundle.putInt(KEY_CAMERY_REQUEST_CODE, this.an);
        }
        if (this.ao != 0) {
            bundle.putInt(KEY_VIDEO_REQUEST_CODE, this.ao);
        }
    }

    private boolean z() {
        return "article".equals(this.p);
    }

    private void A() {
        if (this.B && this.x == null) {
            new Builder(this).setTitle(R.string.nearby_pop_title).setMessage("地理位置还没有获取成功，等一会儿再发表吧？").setPositiveButton(R.string.nearby_pop_btn_ok, new bj(this)).setNegativeButton("不管了", new bi(this)).show();
        } else {
            submitContent();
        }
    }

    public void submitContent() {
        if (this.Z == null || !this.Z.isShowing()) {
            saveTolocal();
        } else {
            Log.e(PublishActivity.class.getSimpleName(), "ProgressDialog is showing , resubmit?");
        }
    }

    public String getCustomerTitle() {
        if (z()) {
            return "糗事投稿";
        }
        return "添加评论";
    }

    public void startAlbum() {
        a(ImagesPickerActivity.prepareIntent(this, 9, this.j), this.aj);
    }

    public void startVideo() {
        VideoPickerActivity.launchForResult(this);
        j();
    }

    private void B() {
        this.m = false;
        this.ai = null;
        D();
        this.player.reset();
    }

    private void C() {
        d(false);
        E();
    }

    public void finish() {
        super.finish();
    }

    private void D() {
        this.mVideoLayout.setVisibility(8);
    }

    private void d(boolean z) {
        if (z) {
            this.mVideoLayout.setVisibility(8);
            this.d.setVisibility(0);
            return;
        }
        this.d.setVisibility(8);
    }

    private void E() {
        QsbkApp qsbkApp = (QsbkApp) getApplication();
        if (qsbkApp.getWaitSendBitmap() != null && !qsbkApp.getWaitSendBitmap().isRecycled()) {
            qsbkApp.getWaitSendBitmap().recycle();
            qsbkApp.setWaitSendBitmap(null);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 3 && intent != null) {
            QiushiTopic qiushiTopic = (QiushiTopic) intent.getSerializableExtra("topic");
            if (qiushiTopic != null) {
                setQiushiTopic(qiushiTopic);
            }
        } else if (i == 273) {
            if (i2 == -1 && this.P != null && this.P.getQiushiTopic() != null) {
                setQiushiTopic(this.P.getQiushiTopic());
            }
        } else if (i == 10010) {
            if (i2 == 999 && intent != null) {
                ArrayList arrayList = (ArrayList) intent.getSerializableExtra("paths");
                this.j.clear();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    r0 = (ImageInfo) it.next();
                    if (new File(r0.getFilePath(this)).exists()) {
                        this.j.add(r0);
                    }
                }
                w();
            }
        } else if (i == 4097 && i2 == -1) {
            r0 = (ImageInfo) intent.getSerializableExtra("video");
            this.ai = r0.getFilePath(this);
            this.ak = r0.width;
            this.al = r0.height;
            a(this.ai, this.ak, this.al);
        }
    }

    public void setQiushiTopic(QiushiTopic qiushiTopic) {
        if (qiushiTopic != null) {
            this.b.setQiushiTopic(qiushiTopic);
            this.G = qiushiTopic;
            this.b.setSelection(this.b.getText().length());
            m();
            i();
        }
    }

    protected String g() {
        return null;
    }

    protected int a() {
        return R.layout.activity_publish_content_fragment;
    }

    private void F() {
        if (this.x == null || (this.x.city == null && this.x.district == null)) {
            this.u.setEnabled(false);
            this.y.getLocation(this);
            this.w.setText("定位中");
            this.v.setVisibility(8);
            this.A.setBaseText("定位中");
            this.A.start();
            return;
        }
        G();
    }

    private void G() {
        if (this.x.city == null && this.x.district == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "加载地理位置失败，请稍后重试。", Integer.valueOf(0)).show();
            H();
            return;
        }
        this.u.setEnabled(false);
        this.u.setBackgroundResource(UIHelper.getPublishLocationSuccessBackground());
        this.v.setVisibility(0);
        this.A.stop();
        this.w.setText(String.format("%s·%s", new Object[]{this.x.city, this.x.district}));
    }

    private void H() {
        this.u.setEnabled(true);
        this.u.setBackgroundResource(UIHelper.getPublishLocationDefaultBackground());
        this.v.setVisibility(8);
        this.A.stop();
        this.w.setText(R.string.location_add);
    }

    private void a(Context context) {
        if (!n()) {
            b(context);
        }
    }

    private void b(Context context) {
        Builder builder = new Builder(context);
        View inflate = LayoutInflater.from(context).inflate(R.layout.publish_qiushi_tips_dialog, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_content);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(R.id.dialog);
        if (relativeLayout != null) {
            if (UIHelper.isNightTheme()) {
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.main_bg_night));
            } else {
                relativeLayout.setBackgroundColor(getResources().getColor(R.color.main_bg));
            }
        }
        textView.setText(R.string.tips_publish_qiushi);
        builder.setView(inflate);
        builder.setPositiveButton(R.string.i_know, new bk(this)).create();
        builder.show();
        hidePublishTips();
    }

    protected void a(Bundle bundle) {
        a((Context) this);
        this.Y = (InputMethodManager) getSystemService("input_method");
        f();
        this.p = getIntent().getStringExtra("flag");
        this.G = (QiushiTopic) getIntent().getSerializableExtra("topic");
        if (this.G == null) {
            this.G = QiushiTopic.EMPTY;
        }
        this.x = LocationCache.getInstance().getLocation(LivePushActivity.INNER);
        initView();
        x();
        if ((this.x == null || (this.x.city == null && this.x.district == null)) && this.y == null) {
            this.y = NearbyEngine.instance().getLastLocationManager();
            this.y.getLocation(this);
        }
        if (z()) {
            this.l.postDelayed(this.n, 10000);
        }
    }

    public void onLocation(int i, double d, double d2, String str, String str2) {
        if (i == 161 || i == 0) {
            NearbyEngine.saveLastLocationToLocal(d, d2);
        }
        if (i == 61 || i == 65 || i == 66 || i == 68 || i == 161 || i == 0) {
            if (this.x == null) {
                this.x = new Location();
            }
            this.x.latitude = d;
            this.x.longitude = d2;
            this.x.city = str2;
            this.x.code = i;
            this.x.district = str;
            this.y.remove(this);
            if (this.B || allowLocation()) {
                G();
            }
            LocationCache.getInstance().setLocation(this.x);
            return;
        }
        this.z++;
        this.y = NearbyEngine.instance().changeLocationMgr(this.y);
        if (this.z >= 2) {
            this.z = 0;
            Pair lastSavedPosition = NearbyEngine.getLastSavedPosition(true);
            if (this.x == null) {
                this.x = new Location();
            }
            if (lastSavedPosition != null) {
                this.x.latitude = ((Double) lastSavedPosition.first).doubleValue();
                this.x.longitude = ((Double) lastSavedPosition.second).doubleValue();
                return;
            }
            return;
        }
        int location = this.y.getLocation(this);
        LogUtil.d("ret:" + location);
        if (location == 6) {
            this.v.postDelayed(new bm(this), 2000);
        }
    }

    public Article getWillPublishArticle() {
        Article article = new Article();
        article.uuid = UUID.randomUUID().toString().replaceAll(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SERVER, "");
        article.content = this.b.getQiushiContent().toString();
        article.anonymous = this.r;
        article.created_at = (long) ((int) (System.currentTimeMillis() / 1000));
        if (!this.r) {
            article.login = QsbkApp.currentUser.userName;
            article.user_icon = QsbkApp.currentUser.userIcon;
            article.user_id = QsbkApp.currentUser.userId;
        }
        if (this.j == null || this.j.size() <= 0) {
            article.format = Article.FORMAT_WORD;
        } else {
            article.format = Article.FORMAT_MULTI;
            Iterator it = this.j.iterator();
            while (it.hasNext()) {
                article.imageInfos.add(new ArticleImage((ImageInfo) it.next()));
            }
        }
        article.imgsrc = this.t;
        article.from_topic = this.q;
        if (!TextUtils.isEmpty(this.I)) {
            article.editUuid = this.I;
        }
        article.state = ManageMyContentsAdapter.NEW_PUBLISH;
        if (this.x != null) {
            Location location = new Location();
            location.longitude = this.x.longitude;
            location.latitude = this.x.latitude;
            location.city = this.x.city;
            location.district = this.x.district;
            article.publish_location = location;
        }
        int i = (this.B || allowLocation()) ? 1 : 0;
        article.display = i;
        article.isPublishArticle = z();
        if (z()) {
            article.allow_comment = this.s.booleanValue();
        }
        if (!TextUtils.isEmpty(this.ai)) {
            File file = new File(this.ai);
            if (file.exists()) {
                ImageInfo imageInfo = new ImageInfo(UriUtil.getUriForFile(file).toString(), MediaFormat.VIDEO);
                imageInfo.width = this.ak;
                imageInfo.height = this.al;
                this.j.add(imageInfo);
                article.format = "video";
                article.filePath = this.ai;
                article.mVideoWidth = this.ak;
                article.mVideoHeight = this.al;
            }
        }
        if (!(this.G == null || QiushiTopic.EMPTY.equals(this.G))) {
            article.qiushiTopic = this.G;
        }
        return article;
    }

    public void saveTolocal() {
        Intent intent = new Intent();
        intent.setClass(this, ManageQiuShiNewActivity.class);
        intent.putExtra("isFromPublishQiushi", true);
        startActivity(intent);
        clearDraft();
        finish();
        Article willPublishArticle = getWillPublishArticle();
        try {
            JSONObject toJSONObjectToSaveLocal = Article.toJSONObjectToSaveLocal(willPublishArticle);
            Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(KEY_PUBLISH_ARTICLE);
            if (TextUtils.isEmpty(sharePreferencesValue)) {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(toJSONObjectToSaveLocal);
                LogUtil.e(jSONArray.toString());
                SharePreferenceUtils.setSharePreferencesValue(KEY_PUBLISH_ARTICLE, jSONArray.toString());
            } else {
                JSONArray jSONArray2 = new JSONArray(sharePreferencesValue);
                jSONArray2.put(toJSONObjectToSaveLocal);
                LogUtil.e(jSONArray2.toString());
                SharePreferenceUtils.setSharePreferencesValue(KEY_PUBLISH_ARTICLE, jSONArray2.toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.H = willPublishArticle;
        isPublishingArticle.put(willPublishArticle.uuid, Boolean.valueOf(true));
        new QiushiPublishTask(this.H).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void I() {
        int i;
        int i2 = 0;
        CharSequence qiushiContent = this.b.getQiushiContent();
        if (QiushiTopic.EMPTY.equals(this.b.getTopic()) && TextUtils.isEmpty(qiushiContent)) {
            i = 0;
        } else {
            i = 1;
        }
        TextView textView = this.c;
        if (i != 0) {
            i2 = 8;
        }
        textView.setVisibility(i2);
        this.c.setText(this.k);
    }

    public void onSizeChanged(int i) {
        if (i > Util.dp(50.0f) && this.U != i) {
            this.U = i;
            SharePreferenceUtils.setSharePreferencesValue(BaseEmotionActivity.KEYBOARD_HEIGHT, this.U);
        }
        if (i <= 0) {
            this.V = false;
        } else {
            this.V = true;
        }
        if (!this.W) {
            this.R.post(new bn(this));
        }
    }

    protected void i() {
        if (!isFinishing()) {
            View currentFocus = getCurrentFocus();
            if (currentFocus != null) {
                this.Y.showSoftInput(currentFocus, 0);
            }
        }
    }

    protected void j() {
        if (!isFinishing()) {
            View currentFocus = getCurrentFocus();
            if (currentFocus != null) {
                this.Y.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
            }
        }
    }

    private void b(int i) {
        LayoutParams layoutParams = this.R.getLayoutParams();
        if (i < 0) {
            i = 0;
        }
        layoutParams.height = i;
        this.R.setLayoutParams(layoutParams);
    }

    protected void k() {
        getWindow().setSoftInputMode(32);
    }

    private void J() {
        getWindow().setSoftInputMode(16);
    }

    private boolean K() {
        return this.R.getVisibility() == 0 && this.R.getHeight() > 0;
    }

    protected void l() {
        if (this.V) {
            k();
            j();
            return;
        }
        b(this.U);
        this.R.setVisibility(0);
    }

    protected void m() {
        J();
        this.R.setVisibility(8);
        i();
    }

    public void onEmotionItemClick(int i, ChatMsgEmotionData chatMsgEmotionData) {
        if (QiushiEmotionHandler$EmotionData.DELETE.getName().equals(chatMsgEmotionData.name)) {
            this.b.dispatchKeyEvent(new KeyEvent(0, 67));
            return;
        }
        int max = Math.max(this.b.getSelectionStart(), 0);
        int max2 = Math.max(this.b.getSelectionEnd(), 0);
        this.b.getText().replace(Math.min(max, max2), Math.max(max, max2), chatMsgEmotionData.name, 0, chatMsgEmotionData.name.length());
    }
}
