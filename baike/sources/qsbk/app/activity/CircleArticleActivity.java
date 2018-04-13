package qsbk.app.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.umeng.commonsdk.proguard.g;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseArticleEmotionActivity;
import qsbk.app.activity.dialog.QiushiBuddleDialog;
import qsbk.app.activity.publish.QiniuUploader;
import qsbk.app.adapter.CircleCommentAdapter;
import qsbk.app.database.QsbkDatabase;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.im.ChatMsgEmotionData;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.loader.AsyncDataLoader;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleComment;
import qsbk.app.model.Image;
import qsbk.app.model.ImageInfo;
import qsbk.app.nearby.api.LocationHelper;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.CircleArticleBus;
import qsbk.app.utils.CircleArticleBus.OnArticleUpdateListener;
import qsbk.app.utils.CircleTopicManager;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;
import qsbk.app.widget.ArticleMoreOperationbar;
import qsbk.app.widget.AutoLoadMoreListView;
import qsbk.app.widget.LoaderLayout;
import qsbk.app.widget.QiushiEmotionHandler$EmotionData;
import qsbk.app.widget.TextBlockSpan;
import qsbk.app.widget.TipsHelper;
import qsbk.app.widget.qiuyoucircle.BaseUserCell;
import qsbk.app.widget.qiuyoucircle.ForwardCell;
import qsbk.app.widget.qiuyoucircle.NormalCell;
import qsbk.app.widget.qiuyoucircle.ShareCell;
import qsbk.app.widget.qiuyoucircle.WebAdCell;

public class CircleArticleActivity extends BaseArticleEmotionActivity implements OnGestureListener, OnTouchListener, ShareUtils$OnCircleShareStartListener, OnArticleUpdateListener {
    public static final String DRAFT_COMMENT = "draftCircleCommentContent";
    public static final int FIRST_PAGE = 1;
    public static final String KEY_ARTICLE_ID = "circleArticleId";
    public static final String KEY_AUTO_SCROLL_TO_COMMENT = "scrollToComment";
    public static final String KEY_CIRCLE_ARTICLE = "circleArticle";
    public static final String KEY_FROM_TOPIC = "fromTopic";
    public static final String KEY_REPLY_INFO = "replyInfo";
    public static final String KEY_SHOW_KEYBOARD = "showKeyboard";
    public static final String LOCAL_COMMENT_ID = "-1";
    int A = 180;
    int B = 0;
    protected final Runnable C = new gb(this);
    OnClickListener D = new gc(this);
    private final BroadcastReceiver Q = new ga(this);
    private boolean R = true;
    private boolean S = true;
    private ToReplyInfo T;
    private View U;
    private TextView V;
    private View W;
    private TipsHelper X;
    private boolean Y = true;
    private TipsHelper Z;
    protected final Runnable a = new ff(this);
    private ArrayList<AtInfo> aa = new ArrayList();
    private int ab;
    private ImageButton ac;
    private ImageInfo ad;
    private View ae;
    private ImageView af;
    private View ag;
    protected final Runnable b = new fv(this);
    protected CircleCommentAdapter c;
    protected ArrayList<CircleComment> d;
    protected ArrayList<CircleComment> e;
    protected ArrayList<CircleComment> f;
    protected AutoLoadMoreListView g;
    protected View h;
    protected View i;
    protected TextView j;
    protected ViewGroup k;
    protected CircleArticle l;
    protected LoaderLayout m;
    protected View n;
    protected int o = 1;
    protected int p = 1;
    protected boolean q = false;
    protected boolean r = true;
    protected String s = null;
    protected AsyncDataLoader t = null;
    protected boolean u = false;
    protected boolean v;
    protected QiushiBuddleDialog w;
    protected Window x;
    protected GestureDetector y;
    BaseUserCell z;

    protected class OnItemClick implements OnItemClickListener {
        final /* synthetic */ CircleArticleActivity a;

        public OnItemClick(CircleArticleActivity circleArticleActivity) {
            this.a = circleArticleActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Object item = adapterView.getAdapter().getItem(i);
            if (item != null && (item instanceof CircleComment)) {
                if (QsbkApp.currentUser == null) {
                    Context context = view.getContext();
                    context.startActivity(new Intent(context, ActionBarLoginActivity.class));
                    return;
                }
                this.a.reply((CircleComment) item);
            }
        }
    }

    protected class OnItemLongClick implements OnItemLongClickListener {
        final /* synthetic */ CircleArticleActivity a;

        protected OnItemLongClick(CircleArticleActivity circleArticleActivity) {
            this.a = circleArticleActivity;
        }

        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (QsbkApp.currentUser == null) {
                Context context = view.getContext();
                context.startActivity(new Intent(context, ActionBarLoginActivity.class));
                return true;
            }
            Object item = adapterView.getAdapter().getItem(i);
            if (item == null || !(item instanceof CircleComment)) {
                return false;
            }
            CircleComment circleComment = (CircleComment) item;
            if ((QsbkApp.currentUser != null && TextUtils.equals("admin", QsbkApp.currentUser.adminRole)) || (QsbkApp.currentUser != null && "1".equalsIgnoreCase(QsbkApp.currentUser.userId))) {
                new Builder(this.a).setTitle("").setItems(new String[]{"复制", "删除", "封禁"}, new gi(this, circleComment)).setNegativeButton("取消", new gh(this)).show();
            } else if (QsbkApp.currentUser != null && TextUtils.equals(circleComment.uid, QsbkApp.currentUser.userId)) {
                new Builder(this.a).setTitle("").setItems(new String[]{"删除"}, new gk(this, circleComment)).setNegativeButton("取消", new gj(this)).show();
            } else if (QsbkApp.currentUser == null || !TextUtils.equals(this.a.l.user.userId, QsbkApp.currentUser.userId)) {
                new Builder(this.a).setTitle("").setItems(new String[]{"复制", "举报"}, new go(this, circleComment)).setNegativeButton("取消", new gn(this)).show();
            } else {
                new Builder(this.a).setTitle("").setItems(new String[]{"举报", "删除"}, new gm(this, circleComment)).setNegativeButton("取消", new gl(this)).show();
            }
            return true;
        }
    }

    public static class ToReplyInfo implements Serializable {
        public CircleComment comment;
        public String commentContent;
        public String commentId;
        public boolean hasImage;
        public int nickStatus;
        public String uid;
        public String userName;

        public static ToReplyInfo parse(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            ToReplyInfo toReplyInfo = new ToReplyInfo();
            try {
                JSONArray jSONArray = new JSONArray(str);
                toReplyInfo.commentId = jSONArray.getString(0);
                toReplyInfo.uid = jSONArray.getString(1);
                toReplyInfo.userName = jSONArray.getString(2);
                toReplyInfo.commentContent = jSONArray.getString(3);
                toReplyInfo.nickStatus = jSONArray.optInt(4);
                return toReplyInfo;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        public String toString() {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(this.commentId);
            jSONArray.put(this.uid);
            jSONArray.put(this.userName);
            jSONArray.put(this.commentContent);
            jSONArray.put(this.nickStatus);
            return jSONArray.toString();
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return g_();
    }

    public static void launch(Context context, String str, boolean z) {
        Intent intent = new Intent(context, CircleArticleActivity.class);
        intent.putExtra(KEY_ARTICLE_ID, str);
        intent.putExtra("showKeyboard", z);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public static void launch(Context context, CircleArticle circleArticle, boolean z) {
        Intent intent = new Intent(context, CircleArticleActivity.class);
        intent.putExtra("circleArticle", circleArticle);
        intent.putExtra("showKeyboard", z);
        intent.addFlags(67108864);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public static void launch(Context context, CircleArticle circleArticle, ToReplyInfo toReplyInfo) {
        Intent intent = new Intent(context, CircleArticleActivity.class);
        intent.putExtra(KEY_ARTICLE_ID, circleArticle.id);
        intent.putExtra(KEY_REPLY_INFO, toReplyInfo);
        intent.putExtra("showKeyboard", true);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    public static void launch(Context context, CircleArticle circleArticle, boolean z, boolean z2) {
        launch(context, circleArticle, z, z2, false);
    }

    public static void launch(Context context, CircleArticle circleArticle, boolean z, boolean z2, boolean z3) {
        Intent intent = new Intent(context, CircleArticleActivity.class);
        intent.putExtra("circleArticle", circleArticle);
        intent.putExtra(KEY_AUTO_SCROLL_TO_COMMENT, z2);
        intent.putExtra("showKeyboard", z);
        intent.putExtra(KEY_FROM_TOPIC, z3);
        if (!(context instanceof Activity)) {
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
        }
        context.startActivity(intent);
    }

    protected void c() {
        this.g.setOnLoadMoreListener(new gd(this));
        this.g.setOnItemClickListener(new OnItemClick(this));
        this.g.setOnItemLongClickListener(new OnItemLongClick(this));
        this.i.setOnClickListener(new ge(this));
    }

    protected void e() {
        this.X = new TipsHelper(findViewById(R.id.tips));
        this.X.hide();
        this.g = (AutoLoadMoreListView) findViewById(R.id.xListView);
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.c = new CircleCommentAdapter(this.d, this.e, this.f, this, this.s, this.g);
        this.c.setArticle(this.l);
        this.c.setOnTabSelectListener(new gf(this));
        this.k = new FrameLayout(this);
        this.g.addHeaderView(this.k);
        this.m = (LoaderLayout) LayoutInflater.from(this).inflate(R.layout.loader_layout, null);
        this.Z = new TipsHelper(this.m.findViewById(R.id.cmt_empty_tips), true);
        this.Z.hide();
        this.g.setAdapter(this.c);
        this.n = findViewById(R.id.root);
        this.h = findViewById(R.id.addCmtLayout);
        this.j = (TextView) findViewById(R.id.exceed_words);
        this.j.setVisibility(8);
        this.U = findViewById(R.id.to_reply_layout);
        this.V = (TextView) findViewById(R.id.to_reply_info);
        this.W = findViewById(R.id.to_reply_close);
        this.W.setOnClickListener(new gg(this));
        this.G = (EditText) findViewById(R.id.addCmtEditText);
        this.af = (ImageView) findViewById(R.id.comment_img);
        this.ae = findViewById(R.id.comment_img_container);
        this.ag = findViewById(R.id.comment_img_clear);
        this.ae.setOnClickListener(this.D);
        this.ag.setOnClickListener(this.D);
        this.ac = (ImageButton) findViewById(R.id.send_img);
        this.ac.setOnClickListener(new LoginPermissionClickDelegate(new fg(this), "请先登录"));
        String j = j();
        CharSequence sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(j);
        String sharePreferencesValue2 = SharePreferenceUtils.getSharePreferencesValue(k());
        if (TextUtils.isEmpty(sharePreferencesValue)) {
            SharePreferenceUtils.remove(j);
        } else {
            this.G.setText(sharePreferencesValue);
        }
        if (this.T == null) {
            this.T = ToReplyInfo.parse(sharePreferencesValue2);
        }
        setReplyUI();
        this.G.setSelection(this.G.length());
        this.G.addTextChangedListener(new fi(this));
        this.G.setOnEditorActionListener(new fj(this));
        this.i = findViewById(R.id.send);
        l();
    }

    private void u() {
        startActivityForResult(ImagesPickerActivity.prepareIntent(this, 1), 101);
    }

    private void v() {
        if (this.l.topic != null) {
            CircleTopicManager.getInstance().insertTopicToLRU(this.l.topic);
        }
    }

    private void w() {
        if (this.l != null) {
            if (this.z == null) {
                if (this.l.isForward()) {
                    this.z = new ForwardCell(this, this.r, true);
                } else if (this.l.isShare()) {
                    this.z = new ShareCell(this, this.r, true);
                } else if (this.l.isAd()) {
                    this.z = new WebAdCell(this, this.r, true);
                } else {
                    this.z = new NormalCell(this, this.r, true);
                }
                this.z.performCreate(0, this.g, this.l);
            }
            if (this.k.getChildCount() == 0) {
                this.k.addView(this.z.getCellView());
            }
            this.z.performUpdate(0, this.g, this.l);
        }
    }

    private void b(int i) {
        new SimpleHttpTask(String.format(Constants.CIRCLE_OWNER_COMMENT, new Object[]{this.s, Integer.valueOf(i)}), new fk(this, i)).execute();
    }

    private void c(int i) {
        this.X.hide();
        LocationHelper.loadCache();
        new SimpleHttpTask(String.format(Constants.CIRCLE_ARTICLE_INFO, new Object[]{this.s, Integer.valueOf(i), Double.valueOf(LocationHelper.getLatitude()), Double.valueOf(LocationHelper.getLongitude())}), new fl(this, i)).execute();
    }

    protected void f() {
        String str = null;
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "登录后才能评论哦！", Integer.valueOf(0)).show();
            LoginPermissionClickDelegate.startLoginActivity(this);
            return;
        }
        v();
        if (HttpUtils.netIsAvailable()) {
            String stringBuilder;
            int i;
            String str2;
            String str3;
            String str4;
            JSONArray jSONArray = new JSONArray();
            if (this.aa.size() > 0) {
                int i2;
                CharSequence text = this.G.getText();
                int[] iArr = new int[this.aa.size()];
                StringBuilder stringBuilder2 = new StringBuilder();
                int i3 = 1;
                for (i2 = 0; i2 < this.aa.size(); i2++) {
                    JSONObject jSONObject = new JSONObject();
                    AtInfo atInfo = (AtInfo) this.aa.get(i2);
                    JSONObject toJson = AtInfo.toJson(atInfo);
                    if (toJson != null) {
                        jSONArray.put(toJson);
                    }
                    iArr[i2] = text.getSpanStart(atInfo.span);
                    if (i3 != 0) {
                        i3 = false;
                    } else {
                        stringBuilder2.append(com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
                    }
                    stringBuilder2.append(atInfo.uid);
                }
                StringBuilder stringBuilder3 = new StringBuilder(text);
                for (i2 = 0; i2 < iArr.length; i2++) {
                    int i4 = iArr[i2];
                    String str5 = ((AtInfo) this.aa.get(i2)).name;
                    stringBuilder3.insert(i4 + 1, str5);
                    for (int i5 = 0; i5 < iArr.length; i5++) {
                        if (iArr[i5] > i4) {
                            iArr[i5] = iArr[i5] + str5.length();
                        }
                    }
                }
                stringBuilder = stringBuilder3.toString();
            } else {
                stringBuilder = this.G.getText().toString();
            }
            String emotionTrim = Util.emotionTrim(stringBuilder);
            if (this.T == null || this.T.commentId.equals("-1")) {
                i = 0;
                str2 = null;
                str3 = null;
                str4 = null;
            } else {
                str4 = this.T.commentId;
                str3 = this.T.userName;
                str2 = this.T.uid;
                str = this.T.commentContent;
                i = this.T.nickStatus;
            }
            if (!a(emotionTrim)) {
                this.i.setClickable(false);
                a(str4, str2, str3, str, emotionTrim, i, jSONArray);
                return;
            }
            return;
        }
        ToastAndDialog.makeNegativeToast(this, getString(R.string.network_not_connected), Integer.valueOf(1)).show();
    }

    private CircleComment b(String str, String str2, String str3, String str4, String str5, int i, JSONArray jSONArray) {
        CircleComment circleComment = new CircleComment();
        circleComment.createTime = (int) (System.currentTimeMillis() / 1000);
        circleComment.id = "-1";
        circleComment.content = str5;
        if (this.l.user.isAnonymous() && this.l.user.isMe) {
            circleComment.uid = this.l.user.userId;
            circleComment.icon = this.l.user.userIcon;
            circleComment.userName = this.l.user.userName;
            circleComment.nickStatus = this.l.user.nickStatus;
        } else {
            circleComment.uid = QsbkApp.currentUser.userId;
            circleComment.icon = QsbkApp.currentUser.userIcon;
            circleComment.userName = QsbkApp.currentUser.userName;
            circleComment.nickStatus = QsbkApp.currentUser.nickStatus;
        }
        if (!TextUtils.isEmpty(str)) {
            CircleComment circleComment2 = new CircleComment();
            circleComment2.id = str;
            circleComment2.uid = str2;
            circleComment2.userName = str3;
            circleComment2.content = str4;
            circleComment2.nickStatus = i;
            circleComment.reply = circleComment2;
        }
        if (jSONArray != null && jSONArray.length() > 0) {
            circleComment.atInfoTexts = AtInfo.constructJson(jSONArray);
        }
        if (!(this.ad == null || TextUtils.isEmpty(this.ad.getImageUrl()))) {
            circleComment.smallImage = new Image(this.ad, this);
        }
        this.g.removeFooterView(this.m);
        CircleArticleBus.updateArticle(this.l, this);
        this.e.add(0, circleComment);
        if (this.l.user.isAnonymous()) {
            if (this.l.user.isMe) {
                this.f.add(0, circleComment);
            }
        } else if (QsbkApp.currentUser != null && this.l.user.userId.equals(QsbkApp.currentUser.userId)) {
            this.f.add(0, circleComment);
            this.c.addOwnerCount(1);
        }
        this.c.notifyDataSetChanged();
        return circleComment;
    }

    protected boolean a(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 2) {
            ToastAndDialog.makeNegativeToast(this, "再多写点内容吧!").show();
            return true;
        } else if (TextUtils.isEmpty(str) || str.length() <= 140) {
            return false;
        } else {
            ToastAndDialog.makeNegativeToast(this, "评论内容不能超过140个字哦").show();
            return true;
        }
    }

    protected void a(String str, String str2, String str3, String str4, String str5, int i, JSONArray jSONArray) {
        CircleComment b = b(str, str2, str3, str4, str5, i, jSONArray);
        String format = String.format(Constants.CIRCLE_ADD_COMMENT, new Object[]{this.s});
        Map hashMap = new HashMap();
        hashMap.put("content", str5);
        if (jSONArray != null && jSONArray.length() > 0) {
            hashMap.put("at_users", jSONArray);
        }
        if (str != null) {
            hashMap.put("comment_id", str);
        }
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new fo(this, b));
        this.G.setText("");
        this.i.setClickable(true);
        this.T = null;
        this.G.setHint(R.string.comment_input_hint);
        this.U.setVisibility(8);
        if (this.ad == null || TextUtils.isEmpty(this.ad.getImageUrl())) {
            simpleHttpTask.setMapParams(hashMap);
            simpleHttpTask.execute();
            return;
        }
        QiniuUploader.uploadImage(this.ad.getFilePath(this), new fr(this, hashMap, simpleHttpTask));
        x();
    }

    public void onEmotionItemClick(int i, ChatMsgEmotionData chatMsgEmotionData) {
        if (QiushiEmotionHandler$EmotionData.DELETE.getName().equals(chatMsgEmotionData.name)) {
            this.G.dispatchKeyEvent(new KeyEvent(0, 67));
            return;
        }
        int max = Math.max(this.G.getSelectionStart(), 0);
        int max2 = Math.max(this.G.getSelectionEnd(), 0);
        this.G.getText().replace(Math.min(max, max2), Math.max(max, max2), chatMsgEmotionData.name, 0, chatMsgEmotionData.name.length());
    }

    protected void g() {
        this.y = new GestureDetector(this);
        if (this.n == null) {
            this.n = findViewById(R.id.root);
        }
        this.n.setOnTouchListener(this);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.y.onTouchEvent(motionEvent);
    }

    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (motionEvent != null && motionEvent2 != null && motionEvent.getX() < 70.0f && motionEvent2.getX() - motionEvent.getX() > ((float) this.A) && Math.abs(f) > ((float) this.B)) {
            finish();
        }
        return false;
    }

    public void finish() {
        s();
        if (isTaskRoot()) {
            startActivity(new Intent(this, MainActivity.class));
        }
        super.finish();
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

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        this.y.onTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    protected void onDestroy() {
        getMainUIHandler().removeCallbacks(this.C);
        getMainUIHandler().removeCallbacks(this.b);
        getMainUIHandler().removeCallbacks(this.a);
        if (this.t != null) {
            this.t.cancel(true);
        }
        CircleArticleBus.unregister(this);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.Q);
        super.onDestroy();
        if (this.c != null) {
            this.c.onDestroy();
        }
    }

    public void onCircleShareStart(CircleArticle circleArticle) {
        ShareUtils.openShareDialog(this, 1, circleArticle);
    }

    public void onCircleShareStart(CircleArticle circleArticle, String str, View view) {
        ShareUtils.openShareDialog(this, 1, circleArticle, str);
    }

    protected void a(CircleComment circleComment) {
        CharSequence substring;
        StringBuffer append = new StringBuffer("举报 : ").append(circleComment.content);
        if (append.length() > 30) {
            substring = append.substring(0, 29);
        } else {
            substring = append.toString();
        }
        String[] strArr = new String[]{g.an, "dirty", "abuse", "politics", "others"};
        new Builder(this).setTitle(substring).setItems(new String[]{"广告/欺诈", "淫秽色情", "骚扰谩骂", "反动政治", "其他"}, new ft(this, strArr, circleComment)).setNegativeButton("取消", new fs(this)).show();
    }

    public void reportComment(CircleComment circleComment, String str) {
        String format = String.format(Constants.CIRCLE_COMMENT_REPORT, new Object[]{circleComment.id});
        Map hashMap = new HashMap();
        hashMap.put("reason", str);
        hashMap.put("toid", circleComment.uid);
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(format, new fu(this, circleComment));
        simpleHttpTask.setMapParams(hashMap);
        simpleHttpTask.execute();
    }

    public void deleteComment(CircleComment circleComment) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.CIRCLE_COMMENT_DELETE, new Object[]{circleComment.id}), new fw(this, circleComment));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.execute();
    }

    public void forbidComment(CircleComment circleComment) {
        SimpleHttpTask simpleHttpTask = new SimpleHttpTask(String.format(Constants.ADMIN_FORBID_CIRCLE_COMMENT, new Object[]{circleComment.id}), new fx(this, circleComment));
        simpleHttpTask.setMapParams(new HashMap());
        simpleHttpTask.execute();
    }

    protected String g_() {
        return null;
    }

    protected int a() {
        return R.layout.activity_singlecircle;
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
        i();
    }

    protected void i() {
        if (this.G != null && !TextUtils.isEmpty(this.s)) {
            Object trim;
            if (this.aa.size() > 0) {
                int i;
                CharSequence text = this.G.getText();
                int[] iArr = new int[this.aa.size()];
                StringBuilder stringBuilder = new StringBuilder();
                Object obj = 1;
                for (i = 0; i < this.aa.size(); i++) {
                    AtInfo atInfo = (AtInfo) this.aa.get(i);
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
                    String str = ((AtInfo) this.aa.get(i)).name;
                    stringBuilder2.insert(i2 + 1, str);
                    for (int i3 = 0; i3 < iArr.length; i3++) {
                        if (iArr[i3] > i2) {
                            iArr[i3] = iArr[i3] + str.length();
                        }
                    }
                }
                trim = stringBuilder2.toString().trim();
            } else {
                trim = this.G.getText().toString();
            }
            String j = j();
            if (TextUtils.isEmpty(trim)) {
                SharePreferenceUtils.remove(j);
            } else {
                SharePreferenceUtils.setSharePreferencesValue(j, trim);
            }
            j = k();
            trim = this.T == null ? "" : this.T.toString();
            if (TextUtils.isEmpty(trim)) {
                SharePreferenceUtils.remove(j);
            } else {
                SharePreferenceUtils.setSharePreferencesValue(j, trim);
            }
        }
    }

    protected String j() {
        return "draftCircleCommentContent_" + this.s;
    }

    protected String k() {
        return "draftCircleCommentContent@_" + this.s;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        Intent intent = getIntent();
        this.l = (CircleArticle) intent.getSerializableExtra("circleArticle");
        if (this.l != null) {
            this.s = this.l.id;
        } else {
            this.s = intent.getStringExtra(KEY_ARTICLE_ID);
        }
        this.u = intent.getBooleanExtra(KEY_AUTO_SCROLL_TO_COMMENT, false);
        this.T = (ToReplyInfo) intent.getSerializableExtra(KEY_REPLY_INFO);
        this.r = intent.getBooleanExtra(KEY_FROM_TOPIC, false);
        g();
        e();
        c();
        setTitle("详情");
        this.o = 1;
        this.p = 1;
        c(1);
        b(1);
        w();
        this.G.postDelayed(new fy(this), 200);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.Q, new IntentFilter(MyInfoActivity.CHANGE_REMARK));
    }

    public void showBuddleDialog() {
        if (!isFinishing()) {
            this.w = new QiushiBuddleDialog(this, R.style.user_info_dialog);
            this.x = this.w.getWindow();
            LayoutParams attributes = this.x.getAttributes();
            this.x.setGravity(51);
            float f = getResources().getDisplayMetrics().density;
            int i = (int) (48.0f * f);
            attributes.x = (int) (10.0f * f);
            attributes.y = (int) ((f * 73.0f) + ((float) i));
            this.x.setAttributes(attributes);
            this.w.setCanceledOnTouchOutside(false);
            this.w.show();
            ((TextView) this.w.findViewById(R.id.user_info_textView)).setOnClickListener(new fz(this));
        }
    }

    protected boolean d() {
        return true;
    }

    public CircleCommentAdapter getAdapter() {
        return this.c;
    }

    public void setReplyUI() {
        if (this.T != null) {
            String remark = RemarkManager.getRemark(this.T.uid);
            if (TextUtils.isEmpty(remark)) {
                remark = this.T.userName;
            }
            String str = this.T.commentContent;
            if (this.T.hasImage) {
                str = str + "[图片]";
            }
            this.V.setText(String.format("回复 %s：%s", new Object[]{remark, str}));
            this.U.setVisibility(0);
            this.G.setHint("回复 " + remark + "：");
            return;
        }
        this.G.setHint(R.string.comment_input_hint);
        this.U.setVisibility(8);
    }

    public void reply(CircleComment circleComment) {
        this.T = new ToReplyInfo();
        this.T.commentId = circleComment.id;
        this.T.uid = circleComment.uid;
        this.T.userName = circleComment.userName;
        this.T.commentContent = circleComment.content;
        this.T.nickStatus = circleComment.nickStatus;
        this.T.hasImage = circleComment.hasImage();
        this.T.comment = circleComment;
        setReplyUI();
        this.G.setSelection(this.G.length());
        this.G.requestFocus();
        this.G.performClick();
    }

    protected void onStop() {
        s();
        super.onStop();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CircleArticleBus.register(this);
    }

    public void onArticleCreate(CircleArticle circleArticle) {
    }

    public void onArticleUpdate(CircleArticle circleArticle) {
        if (this.l != null && TextUtils.equals(this.l.id, circleArticle.id)) {
            this.l.updateWith(circleArticle);
            this.z.performUpdate(0, this.g, this.l);
        }
    }

    public void onArticleDelete(CircleArticle circleArticle) {
        if (this.l != null && TextUtils.equals(this.l.id, circleArticle.id)) {
            finish();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 87 && i2 == -1) {
            BaseUserInfo baseUserInfo = (BaseUserInfo) intent.getSerializableExtra(QsbkDatabase.USER_TABLE_NAME);
            this.G.getText().insert(this.ab + 1, MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            AtInfo atInfo = new AtInfo();
            atInfo.uid = baseUserInfo.userId;
            atInfo.name = baseUserInfo.userName;
            atInfo.span = new TextBlockSpan("@" + atInfo.name, this.G, UIHelper.getTopicLinkColor());
            this.G.getText().setSpan(atInfo.span, this.ab, this.ab + 1, 33);
            this.aa.add(atInfo);
            this.G.requestFocus();
            UIHelper.showKeyboard(this);
        } else if (i == 1 && intent != null) {
            CircleArticle circleArticle = (CircleArticle) intent.getSerializableExtra("circleArticle");
            if (circleArticle != null) {
                ArticleMoreOperationbar.handleShare(i2, (Activity) this, circleArticle);
            }
        } else if (i == 101 && intent != null) {
            ArrayList arrayList = (ArrayList) intent.getSerializableExtra("paths");
            if (arrayList != null && arrayList.size() != 0) {
                this.ad = (ImageInfo) arrayList.get(0);
                if (this.ad != null) {
                    this.ae.setVisibility(0);
                    FrescoImageloader.displayImage(this.af, this.ad.getImageUrl(), UIHelper.getDefaultImageTileBackground());
                    this.G.requestFocus();
                }
            }
        }
    }

    private void x() {
        this.ad = null;
        this.ae.setVisibility(8);
        this.G.requestFocus();
        UIHelper.hideKeyboard(this);
    }
}
