package qsbk.app.share;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import com.tencent.connect.common.Constants;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.core.Arrays;
import qsbk.app.model.Article;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleTopic;
import qsbk.app.thirdparty.ThirdPartyConstants;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.ArrowRectangleView;
import qsbk.app.widget.ShareItem;

public class NewShareActivity extends Activity {
    public static final String FROM_CIRCLE_ARTICLE = "from_circle_article";
    public static final String FROM_CIRCLE_ARTICLE_TYPE = "from_circle_article_type";
    public static final String FROM_CIRCLE_TOPIC = "from_circle_topic";
    public static final String FROM_CIRCLE_TOPIC_ITEM = "from_circle_topic_item";
    public static final String FROM_MANAGEQIUSHI = "from_manage_qiushi";
    public static final String FROM_QIUSHI_HIGHLIGHT = "from_qiushi_hight";
    public static final String FROM_QIUSHI_TOPIC = "from_qiushi_topic";
    public static final String KEY_COLLECTED = "key_collected";
    public static final String KEY_SHARE_BTN_LOCATION = "share_btn_location";
    private static final String c = NewShareActivity.class.getSimpleName();
    private static int d;
    private boolean A;
    private boolean B;
    private boolean C;
    private CircleTopic D;
    private CircleArticle E;
    ArrowRectangleView a;
    Rect b;
    private String e;
    private a[] f;
    private List<a> g = new ArrayList();
    private List<a> h = new ArrayList();
    private GridView i;
    private a j;
    private a k;
    private a l;
    private a m;
    private a n;
    private a o;
    private a p;
    private a q;
    private a r;
    private a s;
    private a t;
    private a u;
    private boolean v;
    private Article w;
    private boolean x;
    private boolean y;
    private boolean z;

    private static class a {
        final int a;
        final String b;
        final int c;

        a(int i, String str, int i2) {
            this.a = i;
            this.b = str;
            this.c = i2;
        }
    }

    private class b extends BaseAdapter {
        final /* synthetic */ NewShareActivity a;

        private b(NewShareActivity newShareActivity) {
            this.a = newShareActivity;
        }

        public int getCount() {
            return this.a.h.size();
        }

        public Object getItem(int i) {
            return this.a.h.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View shareItem;
            if (view == null) {
                shareItem = new ShareItem(this.a);
                shareItem.measure(NewShareActivity.d, NewShareActivity.d);
            } else {
                shareItem = view;
            }
            a aVar = (a) this.a.h.get(i);
            ShareItem shareItem2 = (ShareItem) shareItem;
            shareItem2.setText(aVar.b);
            shareItem2.setImageResource(aVar.a);
            shareItem.setOnClickListener(new l(this, i));
            return shareItem;
        }
    }

    public static int getShareItemDownload() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_download_dark : R.drawable.share_item_download;
    }

    public static int getShareItemDelete() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_delete_dark : R.drawable.share_item_delete;
    }

    public static int getShareItemAnonymous() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_anonymous_dark : R.drawable.share_item_anonymous;
    }

    public static int getShareItemCopy() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_copy_dark : R.drawable.share_item_copy;
    }

    public static int getShareItemCollect() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_collect_dark : R.drawable.share_item_collect;
    }

    public static int getShareItemCollected() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_collected_dark : R.drawable.share_item_collected;
    }

    public static int getShareItemResend() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_resend_dark : R.drawable.share_item_resend;
    }

    public static int getShareItemWXPYQ() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_wx_pyq_dark : R.drawable.share_item_wx_pyq;
    }

    public static int getShareItemWXPY() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_wx_py_dark : R.drawable.share_item_wx_py;
    }

    public static int getShareItemQQ() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_qq_dark : R.drawable.share_item_qq;
    }

    public static int getShareItemQZone() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_qzone_dark : R.drawable.share_item_qzone;
    }

    public static int getShareItemReport() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_report_dark : R.drawable.share_item_report;
    }

    public static int getShareItemSina() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_sina_dark : R.drawable.share_item_sina;
    }

    public static int getShareItemChat() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_chat_dark : R.drawable.share_item_chat;
    }

    public static int getShareItemQyq() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_qyq_dark : R.drawable.share_item_qyq;
    }

    public static int getNoInterest() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_not_interest_dark : R.drawable.share_item_not_interest;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTheme(UIHelper.isNightTheme() ? R.style.AppTheme.share.FullScreen.Dark : R.style.AppTheme.share.FullScreen);
        setContentView(R.layout.activity_new_share);
        this.b = (Rect) getIntent().getParcelableExtra(KEY_SHARE_BTN_LOCATION);
        this.v = getIntent().getBooleanExtra(KEY_COLLECTED, false);
        this.w = (Article) getIntent().getSerializableExtra("article");
        if (this.w != null) {
            boolean z;
            if (QsbkApp.allCollection == null || !QsbkApp.allCollection.contains(this.w.id)) {
                z = false;
            } else {
                z = true;
            }
            this.v = z;
        }
        this.E = (CircleArticle) getIntent().getSerializableExtra("circleArticle");
        this.D = (CircleTopic) getIntent().getSerializableExtra("circleTopic");
        this.x = getIntent().getBooleanExtra(FROM_MANAGEQIUSHI, false);
        this.A = getIntent().getBooleanExtra(FROM_CIRCLE_TOPIC, false);
        this.B = getIntent().getBooleanExtra(FROM_CIRCLE_ARTICLE, false);
        this.y = getIntent().getBooleanExtra(FROM_CIRCLE_TOPIC_ITEM, false);
        this.C = getIntent().getBooleanExtra(FROM_QIUSHI_HIGHLIGHT, false);
        this.z = getIntent().getBooleanExtra(FROM_QIUSHI_TOPIC, false);
        this.e = getIntent().getStringExtra(FROM_CIRCLE_ARTICLE_TYPE);
        this.i = (GridView) findViewById(R.id.grid);
        this.a = (ArrowRectangleView) findViewById(R.id.share_container);
        if (this.x) {
            this.l = new a(getShareItemResend(), "重新投稿", 103);
        } else if (this.v) {
            this.l = new a(getShareItemCollected(), "取消收藏", 6);
        } else {
            this.l = new a(getShareItemCollect(), "收藏", 6);
        }
        this.j = new a(getShareItemReport(), this.B ? "举报帖子" : "举报", 7);
        this.k = new a(getShareItemReport(), "举报话题", 20);
        this.m = new a(getShareItemCopy(), "复制", 5);
        this.o = new a(getShareItemDelete(), "删除", 11);
        this.p = new a(getShareItemDownload(), "下载", 12);
        this.n = new a(getShareItemAnonymous(), "匿名", 13);
        this.q = new a(getNoInterest(), "不感兴趣", 16);
        this.r = new a(b(), "封禁", 14);
        this.t = new a(c(), "移除", 18);
        this.u = new a(e(), "屏蔽", 19);
        if (this.E == null || !this.E.isTop) {
            this.s = new a(d(), "置顶", 17);
        } else {
            this.s = new a(d(), "取消置顶", 17);
        }
        if (this.C) {
            this.f = new a[]{new a(getShareItemWXPYQ(), "微信朋友圈", 8), new a(getShareItemWXPY(), "微信好友", 4), new a(getShareItemQQ(), "QQ好友", 3), new a(getShareItemQZone(), "QQ空间", 10), new a(getShareItemSina(), "新浪微博", 1), new a(getShareItemChat(), "糗友/群", 9)};
        }
        if (this.A) {
            this.f = new a[]{new a(getShareItemWXPYQ(), "微信朋友圈", 8), new a(getShareItemWXPY(), "微信好友", 4), new a(getShareItemQQ(), "QQ好友", 3), new a(getShareItemQZone(), "QQ空间", 10), new a(getShareItemSina(), "新浪微博", 1), new a(getShareItemChat(), "糗友/群", 9)};
        } else if (this.B) {
            if (this.E == null) {
                finish();
                return;
            }
            if (TextUtils.isEmpty(this.e)) {
                if (this.E.isVideoArticle()) {
                    this.f = new a[]{new a(getShareItemQyq(), "糗友圈", 15), new a(getShareItemChat(), "糗友/群", 9), new a(getShareItemWXPY(), "微信好友", 4), new a(getShareItemWXPYQ(), "微信朋友圈", 8), new a(getShareItemQQ(), "QQ好友", 3), new a(getShareItemQZone(), "QQ空间", 10), new a(getShareItemSina(), "新浪微博", 1)};
                } else {
                    this.f = new a[]{new a(getShareItemQyq(), "糗友圈", 15), new a(getShareItemChat(), "糗友/群", 9)};
                }
                if (this.E.isVideoArticle()) {
                    this.g.add(this.p);
                }
                this.g.add(this.m);
            } else if (TextUtils.equals(this.e, ShareUtils$OnCircleShareStartListener.TYPE_REPORT_OR_COPY)) {
                this.f = new a[0];
            } else if (TextUtils.equals(this.e, ShareUtils$OnCircleShareStartListener.TYPE_SHARE)) {
                this.i.setVisibility(0);
                this.f = new a[]{new a(getShareItemQyq(), "糗友圈", 15), new a(getShareItemChat(), "糗友/群", 9), new a(getShareItemWXPY(), "微信好友", 4), new a(getShareItemWXPYQ(), "微信朋友圈", 8), new a(getShareItemQQ(), "QQ好友", 3), new a(getShareItemQZone(), "QQ空间", 10), new a(getShareItemSina(), "新浪微博", 1)};
                if (this.E.isVideoArticle()) {
                    this.g.add(this.p);
                }
                this.g.add(this.m);
            }
            if ((QsbkApp.currentUser == null || !TextUtils.equals(this.E.user.userId, QsbkApp.currentUser.userId)) && !(this.E.user.isAnonymous() && this.E.user.isMe)) {
                if (TextUtils.equals(this.e, ShareUtils$OnCircleShareStartListener.TYPE_REPORT_OR_COPY)) {
                    this.g.add(this.q);
                }
            } else if (TextUtils.equals(this.e, ShareUtils$OnCircleShareStartListener.TYPE_REPORT_OR_COPY)) {
                this.g.add(this.o);
            }
            if (QsbkApp.currentUser != null && QsbkApp.currentUser.isBackEndAdmin()) {
                if (!this.g.contains(this.o)) {
                    this.g.add(this.o);
                }
                this.g.add(this.r);
                if (this.y) {
                    if (!this.E.isClocked()) {
                        this.g.add(this.s);
                    }
                    this.g.add(this.t);
                    this.g.add(this.u);
                }
            } else if (this.y && this.D != null && this.D.user != null && QsbkApp.currentUser != null && TextUtils.equals(QsbkApp.currentUser.userId, this.D.user.userId)) {
                if (!this.E.isClocked()) {
                    this.g.add(this.s);
                }
                this.g.add(this.t);
                this.g.add(this.u);
            } else if ((this.E == null || this.E.topic == null || !this.E.topic.isChicken()) && ((this.D == null || !this.D.isChicken()) && TextUtils.equals(this.e, ShareUtils$OnCircleShareStartListener.TYPE_REPORT_OR_COPY))) {
                this.g.add(this.j);
                if (!(this.E == null || this.E.topic == null)) {
                    this.g.add(this.k);
                }
            }
        } else if (this.z) {
            this.f = new a[]{new a(getShareItemWXPYQ(), "微信朋友圈", 8), new a(getShareItemWXPY(), "微信好友", 4), new a(getShareItemQQ(), "QQ好友", 3), new a(getShareItemQZone(), "QQ空间", 10), new a(getShareItemSina(), "新浪微博", 1), new a(getShareItemChat(), "糗友/群", 9), new a(getShareItemQyq(), "糗友圈", 15)};
        } else {
            this.f = new a[]{new a(getShareItemWXPYQ(), "微信朋友圈", 8), new a(getShareItemWXPY(), "微信好友", 4), new a(getShareItemQQ(), "QQ好友", 3), new a(getShareItemQZone(), "QQ空间", 10), new a(getShareItemSina(), "新浪微博", 1), new a(getShareItemChat(), "糗友/群", 9), new a(getShareItemQyq(), "糗友圈", 15)};
            if (this.w != null && QsbkApp.currentUser != null && QsbkApp.currentUser.isBackEndAdmin()) {
                this.g.add(this.l);
                this.g.add(this.o);
                this.g.add(this.j);
                this.g.add(this.n);
            } else if (QsbkApp.currentUser != null && !QsbkApp.currentUser.isBackEndAdmin() && this.w != null && !this.w.anonymous && QsbkApp.currentUser.userId.equals(this.w.user_id)) {
                this.g.add(this.m);
                this.g.add(this.l);
                this.g.add(this.o);
            } else if (QsbkApp.currentUser == null || QsbkApp.currentUser.isBackEndAdmin() || this.w == null || !this.w.anonymous || !this.w.is_mine) {
                this.g.add(this.m);
                this.g.add(this.l);
                this.g.add(this.j);
            } else {
                this.g.add(this.m);
                this.g.add(this.l);
                this.g.add(this.o);
            }
            if (this.w != null && this.w.isVideoArticle()) {
                this.g.add(new a(getShareItemDownload(), "下载", 12));
            }
        }
        d = getResources().getDimensionPixelSize(R.dimen.new_share_item_size);
        this.h.addAll(Arrays.asList(this.f));
        this.h.addAll(this.g);
        this.i.setAdapter(new b());
        this.i.setOnItemClickListener(new i(this));
        findViewById(R.id.content_container).setOnClickListener(new j(this));
        this.i.getViewTreeObserver().addOnGlobalLayoutListener(new k(this));
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        DebugUtil.debug("QiushiShare", "NewShareActivity, onActivityResult, requestCode=" + i + ",requestCode=" + i + ",data=" + intent);
        super.onActivityResult(i, i2, intent);
    }

    private int b() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_report_dark : R.drawable.share_item_report;
    }

    private int c() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_remove_dark : R.drawable.share_item_remove;
    }

    private int d() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_top_dark : R.drawable.share_item_top;
    }

    private int e() {
        return UIHelper.isNightTheme() ? R.drawable.share_item_block_dark : R.drawable.share_item_block;
    }

    private void a(a aVar) {
        Intent intent = new Intent();
        if (this.B && this.E != null) {
            intent.putExtra("circleArticle", this.E);
        }
        int i = aVar.c;
        a(i);
        setResult(i, intent);
        finish();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    private void a(int i) {
        String str = "";
        switch (i) {
            case 1:
                str = "sina";
                break;
            case 3:
                str = ThirdPartyConstants.THIRDPARTY_TYLE_QQ;
                break;
            case 4:
                str = "py";
                break;
            case 5:
                str = "copy";
                break;
            case 8:
                str = "pyq";
                break;
            case 9:
                str = "chat";
                break;
            case 10:
                str = Constants.SOURCE_QZONE;
                break;
            case 11:
                str = "delete";
                break;
            case 12:
                str = "download";
                break;
            case 13:
                str = "anonymous";
                break;
            case 15:
                str = "qiuyouquan";
                break;
            case 20:
                str = "report_topic";
                break;
        }
        StatService.onEvent(this, "share", str);
        StatSDK.onEvent(this, "share", str);
    }
}
