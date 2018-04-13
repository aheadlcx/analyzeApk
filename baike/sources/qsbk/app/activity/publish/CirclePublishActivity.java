package qsbk.app.activity.publish;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.alipay.sdk.util.j;
import com.baidu.mobstat.Config;
import com.crashlytics.android.Crashlytics;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.CircleTopicsActivity;
import qsbk.app.activity.ImagesPickerActivity;
import qsbk.app.activity.base.BaseEmotionActivity;
import qsbk.app.activity.base.ResultFragmentActivity;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.ChatMsgEmotionData;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.im.emotion.EmotionGridView.OnEmotionItemClickListener;
import qsbk.app.im.emotion.EmotionViewPager;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.Article;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.ImageInfo;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.nearby.api.LocationHelper.LocationCallBack;
import qsbk.app.share.QYQShareInfo;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ResultActivityListener;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;
import qsbk.app.video.VideoPickerActivity;
import qsbk.app.video.VideoPlayerView;
import qsbk.app.widget.CheckableLinearLayout;
import qsbk.app.widget.CircleEditText;
import qsbk.app.widget.CircleEditText.CircleText;
import qsbk.app.widget.DotView;
import qsbk.app.widget.GroupDialog;
import qsbk.app.widget.QBImageView;
import qsbk.app.widget.QiushiEmotionHandler;
import qsbk.app.widget.QiushiEmotionHandler$EmotionData;
import qsbk.app.widget.SizeNotifierRelativeLayout;
import qsbk.app.widget.SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate;
import qsbk.app.widget.TextBlockSpan;

public class CirclePublishActivity extends ResultFragmentActivity implements OnEmotionItemClickListener, LocationCallBack, SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate {
    public static final String[] ACCESS_PERMISSION = new String[]{"所有人都可见", "只有自己可见"};
    public static final int CHOOSE_AT_USER = 87;
    private int A = 0;
    public final String ARTICLE_CONTENT = "circle_draftContent";
    private String B = null;
    private LocationHelper C;
    private Article D;
    private QYQShareInfo E;
    private boolean F;
    private SimpleHttpTask G;
    private int H = 6;
    private boolean I;
    private boolean J;
    private int K;
    private boolean O;
    private ArrayList<AtInfo> P = new ArrayList();
    private int Q;
    private ProgressDialog R = null;
    private boolean S = false;
    private ResultActivityListener T = new o(this);
    private boolean U;
    private boolean V = false;
    private InputMethodManager W;
    private String X;
    SizeNotifierRelativeLayout a;
    CircleEditText b;
    Handler c = new Handler();
    public ImageView circleAt;
    public ImageView circleEmotion;
    public TextView circlePermisson;
    public ScrollView circleScroll;
    public ImageView circleTopicIv;
    public ImageView circleVote;
    Runnable d = new a(this);
    private View e;
    private QBImageView[] f = new QBImageView[6];
    private View[] g = new View[6];
    private View h;
    private SimpleDraweeView i;
    private TextView j;
    private ImageView k;
    private View l;
    public TextView locationTextView;
    private View m;
    public ImageView mCloseVidwoView;
    public RelativeLayout mVideoLayout;
    private EditText n;
    private EditText o;
    private CheckableLinearLayout p;
    public VideoPlayerView player;
    private TextView q;
    private ImageView r;
    private View s;
    public View send;
    private EmotionViewPager t;
    private DotView u;
    private ImageView v;
    public ImageView voteDelete;
    private View w;
    private View x;
    private ArrayList<ImageInfo> y = new ArrayList();
    private ImageInfo z;

    private static class a extends CircleText {
        private CircleTopic a;

        private a() {
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Context context) {
        context.startActivity(a(context));
    }

    public static void launch(Context context, CircleTopic circleTopic) {
        Intent a = a(context);
        a.putExtra("topic", circleTopic);
        context.startActivity(a);
    }

    public static void launch(Context context, QYQShareInfo qYQShareInfo) {
        Intent a = a(context);
        a.putExtra("qyqShareInfo", qYQShareInfo);
        a.putExtra("fromShare", true);
        context.startActivity(a);
    }

    private static Intent a(Context context) {
        Intent intent = new Intent(context, CirclePublishActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        return intent;
    }

    private static Map<String, List<ChatMsgEmotionData>> m() {
        Collection<QiushiEmotionHandler$EmotionData> values = QiushiEmotionHandler.getInstance().getAll().values();
        Map<String, List<ChatMsgEmotionData>> linkedHashMap = new LinkedHashMap(1);
        List arrayList = new ArrayList();
        int i = 0;
        for (QiushiEmotionHandler$EmotionData qiushiEmotionHandler$EmotionData : values) {
            int i2 = i + 1;
            ChatMsgEmotionData chatMsgEmotionData = new ChatMsgEmotionData();
            if (i2 % 28 == 0) {
                chatMsgEmotionData.name = QiushiEmotionHandler$EmotionData.DELETE.getName();
                chatMsgEmotionData.key = QiushiEmotionHandler$EmotionData.DELETE.getName();
                chatMsgEmotionData.localResource = QiushiEmotionHandler$EmotionData.DELETE.getResId();
            } else {
                chatMsgEmotionData.name = qiushiEmotionHandler$EmotionData.getName();
                chatMsgEmotionData.key = qiushiEmotionHandler$EmotionData.getName();
                chatMsgEmotionData.localResource = qiushiEmotionHandler$EmotionData.getResId();
            }
            arrayList.add(chatMsgEmotionData);
            i = i2;
        }
        ChatMsgEmotionData chatMsgEmotionData2 = new ChatMsgEmotionData();
        chatMsgEmotionData2.name = QiushiEmotionHandler$EmotionData.DELETE.getName();
        chatMsgEmotionData2.key = QiushiEmotionHandler$EmotionData.DELETE.getName();
        chatMsgEmotionData2.localResource = QiushiEmotionHandler$EmotionData.DELETE.getResId();
        arrayList.add(chatMsgEmotionData2);
        linkedHashMap.put("emotion_small", arrayList);
        return linkedHashMap;
    }

    public void onBackPressed() {
        LogUtil.d("back pressed");
        if (this.s.isShown()) {
            l();
        } else {
            w();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 82) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private String n() {
        return SharePreferenceUtils.getSharePreferencesValue("circle_draftContent");
    }

    private void a(String str) {
        SharePreferenceUtils.setSharePreferencesValue("circle_draftContent", str);
    }

    private boolean q() {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            if (this.P.size() > 0) {
                for (int i = 0; i < this.P.size(); i++) {
                    try {
                        jSONArray.put(AtInfo.toJson((AtInfo) this.P.get(i)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (jSONArray != null) {
                if (jSONArray.length() > 0) {
                    jSONObject.put("at_users", jSONArray);
                }
            }
            CharSequence obj = this.b.getText().toString();
            if (!TextUtils.isEmpty(obj)) {
                jSONObject.put("text", obj);
            }
            a aVar = (a) this.b.getFirstText();
            if (aVar != null) {
                jSONObject.put("topic", CircleTopic.toJson(aVar.a));
                jSONObject.put(ParamKey.OFFSET, aVar.index);
            }
            if (this.I && this.E != null) {
                jSONObject.put("qyq_share_info", this.E.toJson());
                jSONObject.put("is_share", this.I);
            }
            if (this.y != null && this.y.size() > 0) {
                JSONArray jSONArray2 = new JSONArray();
                Iterator it = this.y.iterator();
                while (it.hasNext()) {
                    jSONArray2.put(((ImageInfo) it.next()).toJson());
                }
                jSONObject.put("pic_urls", jSONArray2);
            }
            if (this.z != null) {
                jSONObject.put("video_info", this.z.toJson());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        String jSONObject2 = jSONObject.toString();
        String n = n();
        if (TextUtils.isEmpty(jSONObject2) || jSONObject2.equals(n)) {
            return false;
        }
        a(jSONObject2);
        return true;
    }

    private boolean r() {
        return this.R == null || !this.R.isShowing();
    }

    private void s() {
        this.send.setEnabled(false);
        if (this.R != null) {
            this.R.dismiss();
        }
        this.R = ProgressDialog.show(this, null, "发布中，请稍候..", true, true);
        this.R.setCanceledOnTouchOutside(false);
        this.R.setOnCancelListener(new ac(this));
    }

    private void t() {
        this.send.setEnabled(true);
        if (this.R != null) {
            this.R.dismiss();
            this.R = null;
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.S = true;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1000 && i2 == -1) {
            CircleTopic resultFromIntent = CircleTopicsActivity.getResultFromIntent(intent);
            CircleText aVar = new a();
            aVar.a = resultFromIntent;
            aVar.text = resultFromIntent.content;
            int selectionStart = this.b.getSelectionStart();
            if (selectionStart >= 1) {
                this.b.getText().delete(selectionStart - 1, selectionStart);
            }
            this.b.insertTextBinding(aVar);
            this.b.getText().setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), aVar.index, aVar.text.length() + aVar.index, 33);
            this.b.checked(j.c);
        } else if (i == 87 && i2 == -1) {
            BaseUserInfo baseUserInfo = (BaseUserInfo) intent.getSerializableExtra(QsbkDatabase.USER_TABLE_NAME);
            if (!this.P.contains(baseUserInfo)) {
                this.b.getText().insert(this.Q + 1, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                AtInfo atInfo = new AtInfo();
                atInfo.uid = baseUserInfo.userId;
                atInfo.name = baseUserInfo.userName;
                this.P.add(atInfo);
                atInfo.span = new TextBlockSpan("@" + atInfo.name, this.b, UIHelper.getTopicLinkColor());
                this.b.getText().setSpan(atInfo.span, this.Q, this.Q + 1, 33);
                this.b.requestFocus();
                UIHelper.showKeyboard(this);
            }
        } else if (i == 4097 && i2 == -1) {
            this.z = (ImageInfo) intent.getSerializableExtra("video");
            x();
            y();
        }
    }

    private void u() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(false);
        supportActionBar.setDisplayShowHomeEnabled(false);
        supportActionBar.setDisplayShowTitleEnabled(false);
        supportActionBar.setDisplayShowCustomEnabled(true);
        supportActionBar.setCustomView(R.layout.circle_activity_publish_bar);
        View findViewById = findViewById(R.id.cancel);
        this.circlePermisson = (TextView) findViewById(R.id.perrmission);
        this.circlePermisson.setOnClickListener(null);
        findViewById.setOnClickListener(new ah(this));
        this.send = findViewById(R.id.submit);
        if (UIHelper.isNightTheme()) {
            ((TextView) findViewById).setTextColor(Color.parseColor("#BD7C1C"));
            this.circlePermisson.setTextColor(Color.parseColor("#8F8F92"));
            ((TextView) this.send).setTextColor(Color.parseColor("#BD7C1C"));
        }
        this.send.setOnClickListener(new ai(this));
    }

    protected void onResume() {
        super.onResume();
        if (this.S) {
            this.S = false;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        QsbkApp qsbkApp = (QsbkApp) getApplication();
        if (qsbkApp.getWaitSendBitmap() != null) {
            qsbkApp.setWaitSendBitmap(null);
        }
        if (this.C != null) {
            this.C.stop();
        }
        this.b.end();
    }

    protected void onPause() {
        this.c.removeCallbacks(this.d);
        UIHelper.hideKeyboard(this);
        q();
        super.onPause();
    }

    private boolean v() {
        return this.b.getText().toString().length() > 0 || this.I || this.y.size() > 0 || this.z != null;
    }

    private void w() {
        if (getCurrentFocus() != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        }
        if (v()) {
            new Builder(this).setItems(new String[]{"保存草稿", "不保存", "取消"}, new aj(this)).show();
            return;
        }
        finish();
    }

    private void x() {
        if (this.z == null) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
        int dip2px = UIHelper.dip2px(this, 84.0f);
        int size = this.y.size();
        for (int i = 0; i < size; i++) {
            FrescoImageloader.displayImage(this.f[i], ((ImageInfo) this.y.get(i)).getImageUrl(), null, false, dip2px, dip2px);
            this.f[i].setTypeImageResouce(MediaFormat.getFormatTagImage(((ImageInfo) this.y.get(i)).mediaFormat));
            this.f[i].setVisibility(0);
            this.g[i].setVisibility(0);
        }
        if (size < this.f.length && this.z == null) {
            FrescoImageloader.displayImage(this.f[size], "", R.drawable.circle_article_publish_image_add);
            this.f[size].setTypeImageResouce(0);
            this.f[size].setVisibility(0);
            this.g[size].setVisibility(8);
        }
        for (int i2 = size + 1; i2 < this.f.length; i2++) {
            this.f[i2].setTypeImageResouce(0);
            this.f[i2].setVisibility(8);
            this.g[i2].setVisibility(8);
        }
    }

    private void y() {
        this.mVideoLayout.setVisibility(this.z == null ? 8 : 0);
        if (this.z != null) {
            this.mVideoLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ak(this));
            this.mVideoLayout.requestLayout();
            LogUtil.d("mVidwoLayout.width:" + this.mVideoLayout.getWidth());
        }
    }

    public void initView() {
        this.W = (InputMethodManager) getSystemService("input_method");
        this.K = SharePreferenceUtils.getSharePreferencesIntValue(BaseEmotionActivity.KEYBOARD_HEIGHT);
        this.b = (CircleEditText) findViewById(R.id.content);
        this.b.setOnClickListener(new al(this));
        this.b.start();
        this.e = findViewById(R.id.images_container);
        this.f[0] = (QBImageView) findViewById(R.id.image1);
        this.f[1] = (QBImageView) findViewById(R.id.image2);
        this.f[2] = (QBImageView) findViewById(R.id.image3);
        this.f[3] = (QBImageView) findViewById(R.id.image4);
        this.f[4] = (QBImageView) findViewById(R.id.image5);
        this.f[5] = (QBImageView) findViewById(R.id.image6);
        this.g[0] = findViewById(R.id.imageDelete1);
        this.g[1] = findViewById(R.id.imageDelete2);
        this.g[2] = findViewById(R.id.imageDelete3);
        this.g[3] = findViewById(R.id.imageDelete4);
        this.g[4] = findViewById(R.id.imageDelete5);
        this.g[5] = findViewById(R.id.imageDelete6);
        OnClickListener amVar = new am(this);
        for (int i = 0; i < this.f.length; i++) {
            this.f[i].setOnClickListener(amVar);
            this.g[i].setOnClickListener(new b(this, i));
        }
        x();
        this.w = findViewById(R.id.play);
        this.player = (VideoPlayerView) findViewById(R.id.video_view);
        this.player.setDisplayMode(1);
        this.v = (ImageView) findViewById(R.id.video_one_frame);
        this.v.setEnabled(false);
        this.v.setFocusable(false);
        this.v.setFocusableInTouchMode(false);
        this.mVideoLayout = (RelativeLayout) findViewById(R.id.id_add_video_layout);
        this.mCloseVidwoView = (ImageView) findViewById(R.id.delete_video);
        this.mCloseVidwoView.setOnClickListener(new c(this));
        this.a = (SizeNotifierRelativeLayout) findViewById(R.id.circle_publish);
        this.a.setSizeNotifierRelativeLayoutDelegate(this);
        this.h = findViewById(R.id.share_container);
        this.h.setVisibility(8);
        this.i = (SimpleDraweeView) findViewById(R.id.share_image);
        this.k = (ImageView) findViewById(R.id.gif_tag);
        this.j = (TextView) findViewById(R.id.share_content);
        this.r = (ImageView) findViewById(R.id.share_play);
        this.l = findViewById(R.id.add_vote);
        this.m = findViewById(R.id.vote_layout);
        this.locationTextView = (TextView) findViewById(R.id.location);
        this.circleVote = (ImageView) findViewById(R.id.circle_vote);
        this.circleTopicIv = (ImageView) findViewById(R.id.circle_topic_iv);
        this.circleAt = (ImageView) findViewById(R.id.circle_at);
        this.circleEmotion = (ImageView) findViewById(R.id.circle_emoji);
        this.circleScroll = (ScrollView) findViewById(R.id.circle_scroll);
        this.voteDelete = (ImageView) findViewById(R.id.vote_delete);
        this.n = (EditText) findViewById(R.id.vote_edit_left);
        this.o = (EditText) findViewById(R.id.vote_edit_right);
        this.p = (CheckableLinearLayout) findViewById(R.id.comment_qiushi);
        this.q = (TextView) findViewById(R.id.comment_desc);
        this.p.setChecked(true);
        this.s = findViewById(R.id.emotions);
        this.t = (EmotionViewPager) findViewById(R.id.emotion_viewpager);
        this.u = (DotView) findViewById(R.id.emotion_dotview);
        this.t.setOnEmotionClickListener(this);
        this.t.setEmotionType(1);
        this.t.setPerPageCount(28);
        this.t.setRowCount(4);
        this.t.setDatas(m());
        this.t.setDotView(this.u);
        if (UIHelper.isNightTheme()) {
            if (this.a != null) {
                this.a.setBackgroundColor(getResources().getColor(R.color.main_bg_night));
            }
            this.b.setTextColor(Color.parseColor("#8F8F92"));
            this.n.setTextColor(Color.parseColor("#66666F"));
            this.o.setTextColor(Color.parseColor("#66666F"));
            this.locationTextView.setTextColor(Color.parseColor("#66666F"));
        }
        this.l.setVisibility(8);
        this.m.setVisibility(8);
        this.circleVote.setOnClickListener(new d(this));
        OnFocusChangeListener fVar = new f(this);
        this.b.setOnFocusChangeListener(fVar);
        this.n.setOnFocusChangeListener(fVar);
        this.o.setOnFocusChangeListener(fVar);
        this.circleAt.setOnClickListener(new g(this));
        this.circleTopicIv.setOnClickListener(new h(this));
        this.circleEmotion.setOnClickListener(new i(this));
        this.voteDelete.setOnClickListener(new j(this));
        CircleTopic circleTopic = (CircleTopic) getIntent().getSerializableExtra("topic");
        if (circleTopic != null) {
            CircleText aVar = new a();
            aVar.a = circleTopic;
            aVar.text = circleTopic.content;
            this.b.insertTextBinding(aVar);
            this.b.getText().setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), aVar.index, aVar.text.length() + aVar.index, 33);
        }
        if (getIntent().getBooleanExtra("fromShare", false)) {
            this.I = true;
            this.E = (QYQShareInfo) getIntent().getSerializableExtra("qyqShareInfo");
        }
        if (!this.I) {
            B();
        }
        this.D = (Article) getIntent().getSerializableExtra("article");
        this.F = getIntent().getBooleanExtra("fromManageQiushi", false);
        if (!(this.D == null || this.I)) {
            this.b.setText(this.D.content);
        }
        if (this.I && this.E != null) {
            CharSequence charSequence;
            this.b.setHint("#话题# 这一刻你在想什么");
            this.e.setVisibility(8);
            this.h.setVisibility(0);
            this.circleVote.setVisibility(8);
            if (this.E.hasArticle()) {
                this.p.setVisibility(0);
                this.q.setText("评论给原糗事");
            } else if (this.E.circleArticle != null) {
                this.p.setVisibility(0);
                this.q.setText("评论给原动态");
                if (!this.J && (this.E.circleArticle.isForward() || this.E.circleArticle.isShare())) {
                    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("//");
                    AtInfo atInfo = new AtInfo();
                    atInfo.name = this.E.circleArticle.user.userName;
                    atInfo.uid = this.E.circleArticle.user.userId;
                    this.Q = spannableStringBuilder.length();
                    spannableStringBuilder.append("@ ");
                    this.P.add(atInfo);
                    atInfo.span = new TextBlockSpan("@" + atInfo.name, this.b, UIHelper.getTopicLinkColor());
                    spannableStringBuilder.setSpan(atInfo.span, this.Q, this.Q + 1, 33);
                    spannableStringBuilder.append(Config.TRACE_TODAY_VISIT_SPLIT);
                    SpannableStringBuilder spannableStringBuilder2 = new SpannableStringBuilder(this.E.circleArticle.content);
                    if (!TextUtils.isEmpty(this.E.circleArticle.content) && this.E.circleArticle.atInfoTexts.size() > 0) {
                        for (int i2 = 0; i2 < this.E.circleArticle.atInfoTexts.size(); i2++) {
                            atInfo = (AtInfo) this.E.circleArticle.atInfoTexts.get(i2);
                            try {
                                Matcher matcher = Pattern.compile("@" + atInfo.name).matcher(spannableStringBuilder2);
                                if (matcher.find(0)) {
                                    int start = matcher.start(0);
                                    int end = matcher.end(0);
                                    this.Q = (spannableStringBuilder.length() + start) + 1;
                                    spannableStringBuilder2.replace(start, end, "@");
                                    end = start + 1;
                                    this.P.add(atInfo);
                                    atInfo.span = new TextBlockSpan("@" + atInfo.name, this.b, UIHelper.getTopicLinkColor());
                                    spannableStringBuilder2.setSpan(atInfo.span, start, end, 33);
                                }
                            } catch (PatternSyntaxException e) {
                            }
                        }
                    }
                    this.b.setText(TextUtils.concat(new CharSequence[]{spannableStringBuilder, spannableStringBuilder2}));
                    this.b.post(new m(this));
                }
            } else if (QYQShareInfo.TYPE_QSJX.equals(this.E.type)) {
                this.b.setHint("分享了合集");
            }
            if ("video".equals(this.E.type)) {
                this.r.setVisibility(0);
                this.r.setImageResource(R.drawable.video_play_normal);
            } else if ("live".equals(this.E.type)) {
                this.r.setVisibility(0);
                this.r.setImageResource(R.drawable.live_begin);
            } else {
                this.r.setVisibility(8);
            }
            this.k.setVisibility(8);
            if (this.E.hasArticle() && this.E.article.isGIFArticle()) {
                this.k.setVisibility(0);
                this.r.setVisibility(8);
            }
            if (this.E.circleArticle != null) {
                if (this.E.circleArticle.isForward()) {
                    if (this.E.circleArticle.originalCircleArticle.hasGIF()) {
                        this.k.setVisibility(0);
                    }
                } else if (this.E.circleArticle.hasGIF()) {
                    this.k.setVisibility(0);
                }
            }
            TextView textView = this.j;
            if (this.E.content == null) {
                charSequence = "";
            } else {
                charSequence = this.E.content;
            }
            textView.setText(charSequence);
            this.B = this.E.picUrl;
            FrescoImageloader.displayImage(this.i, this.B, UIHelper.getShare2IMIcon(), UIHelper.getShare2IMIcon());
        }
        this.b.setFocusable(true);
        this.b.requestFocus();
        this.b.addTextChangedListener(new n(this));
        this.b.checked("init");
    }

    private void z() {
        new Builder(this).setItems(new String[]{"照片", "视频", "取消"}, new p(this)).create().show();
    }

    private void A() {
        if (this.y.size() > 0) {
            new Builder(this).setTitle("添加视频，将清除当前添加的图片").setPositiveButton("确定", new q(this)).setNegativeButton("取消", null).create().show();
        } else {
            VideoPickerActivity.launchForResult(this);
        }
    }

    public void startImagePicker() {
        if (this.z != null) {
            new Builder(this).setTitle("添加图片，将清除当前添加的视频").setPositiveButton("确定", new r(this)).setNegativeButton("取消", null).create().show();
        } else {
            a(ImagesPickerActivity.prepareIntent(this, 6 - this.y.size()), this.T);
        }
    }

    private void B() {
        Object n = n();
        if (!TextUtils.isEmpty(n)) {
            try {
                JSONObject jSONObject = new JSONObject(n);
                if (jSONObject.length() != 0) {
                    int i;
                    CharSequence optString = jSONObject.optString("text");
                    CircleTopic parseJson = CircleTopic.parseJson(jSONObject.optJSONObject("topic"));
                    int optInt = jSONObject.optInt(ParamKey.OFFSET);
                    if (this.b.getCircleTexts().size() != 0) {
                        if (parseJson != null && optString.startsWith(parseJson.content)) {
                            optString = optString.substring(parseJson.content.length());
                        }
                        if (TextUtils.isEmpty(optString)) {
                            this.b.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                            this.b.setSelection(this.b.getText().length());
                        } else {
                            this.b.append(optString);
                            this.b.setSelection(this.b.getText().length());
                        }
                    } else {
                        this.b.setText(optString);
                        if (parseJson != null) {
                            CircleText aVar = new a();
                            aVar.a = parseJson;
                            aVar.text = parseJson.content;
                            this.b.replaceOrInsertCircleText(optInt, parseJson.content.length() + optInt, aVar);
                            this.b.getText().setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), optInt, parseJson.content.length() + optInt, 33);
                            this.b.setSelection(optString.length());
                        }
                    }
                    JSONArray optJSONArray = jSONObject.optJSONArray("at_users");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (i = 0; i < optJSONArray.length(); i++) {
                            AtInfo atInfo = new AtInfo();
                            atInfo = AtInfo.constructJson(optJSONArray.optJSONObject(i));
                            if (atInfo != null) {
                                this.P.add(atInfo);
                            }
                        }
                        optInt = 0;
                        int i2 = 0;
                        while (optInt < this.P.size()) {
                            AtInfo atInfo2 = (AtInfo) this.P.get(optInt);
                            try {
                                Matcher matcher = Pattern.compile("@").matcher(this.b.getText());
                                if (matcher.find(i2)) {
                                    int start = matcher.start(0);
                                    i2 = matcher.end(0);
                                    atInfo2.span = new TextBlockSpan("@" + atInfo2.name, this.b, UIHelper.getTopicLinkColor());
                                    this.b.getText().setSpan(atInfo2.span, start, i2, 33);
                                }
                                i = i2;
                            } catch (PatternSyntaxException e) {
                                i = i2;
                            }
                            optInt++;
                            i2 = i;
                        }
                    }
                    optJSONArray = jSONObject.optJSONArray("pic_urls");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (i = 0; i < optJSONArray.length(); i++) {
                            JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                            ImageInfo imageInfo = new ImageInfo();
                            imageInfo.fromJson(jSONObject2);
                            if (ImageInfo.isUrlFile(imageInfo.url)) {
                                this.y.add(imageInfo);
                            }
                        }
                    }
                    JSONObject optJSONObject = jSONObject.optJSONObject("video_info");
                    if (optJSONObject != null) {
                        this.z = new ImageInfo();
                        this.z.fromJson(optJSONObject);
                    }
                    x();
                    y();
                    this.I = jSONObject.optBoolean("is_share", false);
                    try {
                        optJSONObject = jSONObject.optJSONObject("qyq_share_info");
                        if (optJSONObject != null) {
                            this.E = new QYQShareInfo(optJSONObject);
                        }
                    } catch (QiushibaikeException e2) {
                        e2.printStackTrace();
                    }
                    this.J = true;
                    C();
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
    }

    private void C() {
        a("");
    }

    public void startSubmit() {
        s();
        if (this.y.size() > 0) {
            uploadImage(0);
        } else if (this.z != null) {
            D();
        } else {
            submitContent();
        }
    }

    public void submitContent() {
        String str;
        Object stringBuilder;
        CircleTopic circleTopic = null;
        int i = 1;
        a aVar = (a) this.b.getFirstText();
        if (!this.b.checked("submit")) {
            Crashlytics.logException(new Exception(this.b.getLog()));
            aVar = null;
        }
        if (aVar != null) {
            circleTopic = aVar.a;
        }
        JSONArray jSONArray = new JSONArray();
        if (this.P.size() > 0) {
            int i2;
            CharSequence text = this.b.getText();
            int[] iArr = new int[this.P.size()];
            StringBuilder stringBuilder2 = new StringBuilder();
            int i3 = 1;
            for (i2 = 0; i2 < this.P.size(); i2++) {
                JSONObject jSONObject = new JSONObject();
                AtInfo atInfo = (AtInfo) this.P.get(i2);
                try {
                    jSONArray.put(AtInfo.toJson(atInfo));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                iArr[i2] = text.getSpanStart(atInfo.span);
                if (i3 != 0) {
                    i3 = 0;
                } else {
                    stringBuilder2.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
                }
                stringBuilder2.append(atInfo.uid);
            }
            stringBuilder2.toString();
            StringBuilder stringBuilder3 = new StringBuilder(text);
            for (i2 = 0; i2 < iArr.length; i2++) {
                int i4 = iArr[i2];
                str = ((AtInfo) this.P.get(i2)).name;
                stringBuilder3.insert(i4 + 1, str);
                for (int i5 = 0; i5 < iArr.length; i5++) {
                    if (iArr[i5] > i4) {
                        iArr[i5] = iArr[i5] + str.length();
                    }
                }
            }
            stringBuilder = stringBuilder3.toString();
        } else if (TextUtils.isEmpty(this.b.getText().toString()) && this.I && this.E != null && circleTopic == null) {
            stringBuilder = this.E.getDefaultComment();
        } else {
            stringBuilder = this.b.getText().toString();
        }
        if (TextUtils.isEmpty(stringBuilder) || stringBuilder.trim().length() == 0 || !(circleTopic == null || circleTopic.isClocked() || !TextUtils.equals(circleTopic.content, stringBuilder))) {
            ToastAndDialog.makeText(this, "请再写点内容吧").show();
            t();
            return;
        }
        Object trim = this.n.getText().toString().trim();
        str = this.o.getText().toString().trim();
        String str2 = qsbk.app.Constants.CIRCLE_PUBLISH;
        Map hashMap = new HashMap();
        hashMap.put("content", stringBuilder);
        if (!TextUtils.isEmpty(trim)) {
            hashMap.put("vote_a", trim);
            hashMap.put("vote_b", str);
        }
        if (!TextUtils.isEmpty(this.X)) {
            hashMap.put("video_name", this.X);
        }
        if (this.I && this.E != null) {
            if (this.E.circleArticle == null || this.E.circleArticle.isShare()) {
                hashMap.put("qiushi_link", this.E.link);
                hashMap.put("qiushi_content", this.E.content);
                hashMap.put("qiushi_type", this.E.type);
                hashMap.put("is_shared", Integer.valueOf(1));
                hashMap.put("live_origin", Integer.valueOf(this.E.live_origin));
            } else {
                hashMap.put("article_id", this.E.circleArticle.id);
            }
        }
        if (this.B != null) {
            hashMap.put("pic_urls", this.B);
        }
        if (circleTopic != null) {
            if (circleTopic.id.equals("0")) {
                hashMap.put("topic", circleTopic.content.substring(1, circleTopic.content.length() - 1));
            } else {
                hashMap.put("topic_id", circleTopic.id);
            }
        }
        String str3 = "status";
        if (this.A == 1) {
            i = 4;
        }
        hashMap.put(str3, Integer.valueOf(i));
        if (jSONArray != null && jSONArray.length() > 0) {
            hashMap.put("at_users", jSONArray);
        }
        double latitude = LocationHelper.getLatitude();
        double longitude = LocationHelper.getLongitude();
        hashMap.put(ParamKey.LATITUDE, Double.valueOf(latitude));
        hashMap.put(ParamKey.LONGITUDE, Double.valueOf(longitude));
        Object city = LocationHelper.getCity();
        Object district = LocationHelper.getDistrict();
        if (!(TextUtils.isEmpty(city) || TextUtils.isEmpty(district))) {
            hashMap.put("location", city + "·" + district);
        }
        t();
        this.G = new SimpleHttpTask(str2, new s(this, stringBuilder, circleTopic, jSONArray, trim, str, latitude, longitude));
        this.G.setMapParams(hashMap);
        this.G.execute();
    }

    private void a(int i) {
        t();
        new GroupDialog.Builder(this).setMessage("图片上传失败，请重试").setPositiveButton("重试", new v(this, i)).setNegativeButton("取消", null).show();
    }

    public void uploadImage(int i) {
        LogUtil.d("upload image index = " + i);
        new SimpleHttpTask(qsbk.app.Constants.CIRCLE_IMAGE_TOKEN, new w(this, i)).execute();
    }

    public void uploadImage(int i, String str) {
        new QiniuUploader(str, Uri.parse(((ImageInfo) this.y.get(i)).url), new x(this, i, str)).startUpload();
    }

    private void D() {
        new SimpleHttpTask(qsbk.app.Constants.CIRCLE_IMAGE_TOKEN, new y(this)).execute();
    }

    public void uploadVideo(String str, String str2) {
        new QiniuVideoUploader(str2, str, new z(this)).startUpload();
    }

    private void b(int i) {
        this.y.remove(i);
        x();
        E();
    }

    public void finish() {
        super.finish();
    }

    private void E() {
        QsbkApp qsbkApp = (QsbkApp) getApplication();
        if (qsbkApp.getWaitSendBitmap() != null) {
            qsbkApp.setWaitSendBitmap(null);
        }
    }

    protected String f() {
        return null;
    }

    protected int a() {
        return R.layout.circle_activity_publish;
    }

    protected void a(Bundle bundle) {
        u();
        initView();
        getRank();
        showTipsIfNeed();
        this.c.postDelayed(this.d, 10000);
        this.C = new LocationHelper((Context) this);
        this.C.startLocate(this);
    }

    public void getRank() {
        if (QsbkApp.currentUser.circleLevel > 0) {
            this.H = QsbkApp.currentUser.circleLevel;
        }
        String str = QsbkApp.currentUser.userId;
        new SimpleHttpTask(String.format(qsbk.app.Constants.PERSONAL_SCORE, new Object[]{str}), new aa(this)).execute();
    }

    public void showTipsIfNeed() {
        int sharePreferencesIntValue = SharePreferenceUtils.getSharePreferencesIntValue("circle_publish_tip_times");
        if (sharePreferencesIntValue < 3) {
            SharePreferenceUtils.setSharePreferencesValue("circle_publish_tip_times", sharePreferencesIntValue + 1);
            showTips();
        }
    }

    public void showTips() {
        new ad(this, this).show();
    }

    public void selectPermission() {
        new Builder(this).setItems(ACCESS_PERMISSION, new af(this)).show();
    }

    public void onLocateDone(double d, double d2, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            this.locationTextView.setText(str2 + str);
            this.locationTextView.setVisibility(0);
        }
    }

    public void onLocateFail(int i) {
        this.locationTextView.setVisibility(8);
    }

    public void onSizeChanged(int i) {
        if (i > Util.dp(50.0f) && this.K != i) {
            this.K = i;
            SharePreferenceUtils.setSharePreferencesValue(BaseEmotionActivity.KEYBOARD_HEIGHT, this.K);
        }
        if (i <= 0) {
            this.V = false;
        } else {
            this.V = true;
        }
        if (!this.U) {
            this.s.post(new ag(this));
        }
        if (i > 0 && G()) {
            c(0);
            this.s.setVisibility(8);
        }
    }

    protected void g() {
        if (!isFinishing()) {
            View currentFocus = getCurrentFocus();
            if (currentFocus != null) {
                this.W.showSoftInput(currentFocus, 0);
            }
        }
    }

    protected void i() {
        if (!isFinishing()) {
            View currentFocus = getCurrentFocus();
            if (currentFocus != null) {
                this.W.hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
            }
        }
    }

    private void c(int i) {
        LayoutParams layoutParams = this.s.getLayoutParams();
        if (i < 0) {
            i = 0;
        }
        layoutParams.height = i;
        this.s.setLayoutParams(layoutParams);
    }

    protected void j() {
        getWindow().setSoftInputMode(32);
    }

    private void F() {
        getWindow().setSoftInputMode(16);
    }

    private boolean G() {
        return this.s.getVisibility() == 0 && this.s.getHeight() > 0;
    }

    protected void k() {
        if (this.V) {
            j();
            i();
            return;
        }
        c(this.K);
        this.s.setVisibility(0);
    }

    protected void l() {
        F();
        this.s.setVisibility(8);
        g();
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
