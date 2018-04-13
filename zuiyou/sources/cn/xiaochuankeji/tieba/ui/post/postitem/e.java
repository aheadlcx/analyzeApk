package cn.xiaochuankeji.tieba.ui.post.postitem;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import c.a.i.u;
import cn.htjyb.ui.a;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.beans.Medal;
import cn.xiaochuankeji.tieba.background.data.Comment;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.data.post.Post.PostVote;
import cn.xiaochuankeji.tieba.background.favorite.Favorite;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.post.d;
import cn.xiaochuankeji.tieba.background.post.k;
import cn.xiaochuankeji.tieba.background.post.m;
import cn.xiaochuankeji.tieba.background.post.n;
import cn.xiaochuankeji.tieba.background.post.r;
import cn.xiaochuankeji.tieba.background.topic.BlockTopicActionRequest;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.newshare.PostShareDataModel;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.d.h;
import cn.xiaochuankeji.tieba.ui.homepage.HomePageActivity;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.my.MyPostActivity;
import cn.xiaochuankeji.tieba.ui.my.assessor.UserAssessActivity;
import cn.xiaochuankeji.tieba.ui.post.LikedUsersActivity;
import cn.xiaochuankeji.tieba.ui.post.PostAllegeActivity;
import cn.xiaochuankeji.tieba.ui.post.PostVoteDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.ReportedPostActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.topic.TopicPostTopActivity;
import cn.xiaochuankeji.tieba.ui.topic.holder.HolderCreator;
import cn.xiaochuankeji.tieba.ui.topic.weight.FollowView;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostMemberView.ViewType;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.b;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import com.iflytek.aiui.AIUIConstant;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.c;
import org.greenrobot.eventbus.l;
import org.json.JSONObject;

public abstract class e implements OnClickListener, u, a, b, PostItemUpDownView.a {
    private WebImageView A;
    private ImageView B;
    private ImageView C;
    private TextView D;
    private TextView E;
    private TextView F;
    private WebImageView G;
    private RelativeLayout H;
    private ViewStub I;
    private WebView J;
    private WebImageView K;
    private TextView L;
    private TextView M;
    private ViewGodReviewIndicators N;
    private LinearLayout O;
    private ImageView P;
    private TextView Q;
    private boolean R;
    private TextView S;
    private FrameLayout T;
    private TextView U;
    private FollowView V;
    private d W;
    private HashMap<Long, Boolean> X;
    private k Y;
    private TextView Z;
    protected cn.xiaochuankeji.tieba.background.picture.a a;
    private TextView aa;
    private TextView ab;
    private ImageView ac;
    private ImageView ad;
    private int ae;
    private ImageView af;
    private ImageView ag;
    private String ah;
    private ImageView ai;
    private a aj;
    private ImageView ak;
    private boolean al;
    protected FrameLayout b;
    protected ViewGroup c;
    protected Context d;
    protected Resources e;
    protected String f;
    protected Post g;
    protected m h;
    protected int i;
    protected cn.xiaochuankeji.tieba.background.member.d j;
    protected cn.xiaochuankeji.tieba.ui.comment.b k;
    protected EntranceType l;
    WebViewClient m;
    WebChromeClient n;
    private WebImageView o;
    private View p;
    private FrameLayout q;
    private cn.xiaochuankeji.tieba.ui.post.postdetail.b r;
    private TextView s;
    private TextView t;
    private MultipleLineEllipsisTextView u;
    private PostItemUpDownView v;
    private RelativeLayout w;
    private RelativeLayout x;
    private RelativeLayout y;
    private RelativeLayout z;

    public abstract void a(EntranceType entranceType);

    protected abstract void c(int i);

    protected abstract void g();

    public e(Context context) {
        this(context, 0);
    }

    public e(Context context, int i) {
        this.R = true;
        this.l = EntranceType.PostItem;
        this.al = false;
        this.m = new WebViewClient(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith("http") && !str.contains("download")) {
                    return false;
                }
                return true;
            }

            public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
                super.onPageStarted(webView, str, bitmap);
            }

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                if (this.a.J != null) {
                    this.a.H();
                }
            }

            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
            }

            public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
                super.doUpdateVisitedHistory(webView, str, z);
            }

            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();
            }
        };
        this.n = new WebChromeClient(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onProgressChanged(WebView webView, int i) {
                if (i > 0 && i % 10 == 0) {
                    this.a.H();
                }
            }

            public void onReceivedTitle(WebView webView, String str) {
                super.onReceivedTitle(webView, str);
            }

            public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
                super.onReceivedTouchIconUrl(webView, str, z);
            }

            public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
                return super.onCreateWindow(webView, z, z2, message);
            }
        };
        this.d = context;
        this.e = context.getResources();
        this.j = cn.xiaochuankeji.tieba.background.member.d.a();
        q();
        a(context);
        p();
        b(i);
        r();
    }

    private void p() {
        if (MemberDetailActivity.class.isInstance(this.d)) {
            this.f = AIUIConstant.USER;
        } else if (PostDetailActivity.class.isInstance(this.d)) {
            this.f = "postdetail";
        } else if (TopicDetailActivity.class.isInstance(this.d)) {
            this.f = "topicdetail";
        } else if (HomePageActivity.class.isInstance(this.d)) {
            this.f = "index";
            this.R = false;
        } else {
            this.f = "other";
        }
    }

    private void q() {
        this.a = cn.xiaochuankeji.tieba.background.a.f();
        this.W = cn.xiaochuankeji.tieba.background.a.j();
    }

    private void a(Context context) {
        this.p = LayoutInflater.from(context).inflate(R.layout.view_item_post, null);
    }

    protected void b(int i) {
        this.q = (FrameLayout) this.p.findViewById(R.id.link_content_container);
        this.o = (WebImageView) this.p.findViewById(R.id.pvAvatar);
        this.t = (TextView) this.p.findViewById(R.id.tvCommentCount);
        this.T = (FrameLayout) this.p.findViewById(R.id.flTopicNameClickContainer);
        this.S = (TextView) this.p.findViewById(R.id.tvTopicName);
        this.s = (TextView) this.p.findViewById(R.id.tvShare);
        this.U = (TextView) this.p.findViewById(R.id.tvRankNumber);
        this.b = (FrameLayout) this.p.findViewById(R.id.picContainer);
        this.c = (ViewGroup) this.p.findViewById(R.id.voteWidget);
        this.c.setVisibility(8);
        this.v = (PostItemUpDownView) this.p.findViewById(R.id.postItemUpDownView);
        this.w = (RelativeLayout) this.p.findViewById(R.id.rl_link_container);
        this.x = (RelativeLayout) this.p.findViewById(R.id.rlLinkArea);
        this.y = (RelativeLayout) this.p.findViewById(R.id.rl_link_wechat);
        this.z = (RelativeLayout) this.p.findViewById(R.id.rl_link_net163);
        this.H = (RelativeLayout) this.p.findViewById(R.id.music_link_common);
        this.I = (ViewStub) this.p.findViewById(R.id.music_link_detail);
        this.K = (WebImageView) this.p.findViewById(R.id.pvLink_163net_Holder);
        this.L = (TextView) this.p.findViewById(R.id.tv_net163_title);
        this.M = (TextView) this.p.findViewById(R.id.tv_net163_author);
        this.A = (WebImageView) this.p.findViewById(R.id.pvLinkHolder);
        this.D = (TextView) this.p.findViewById(R.id.tvUrl);
        this.E = (TextView) this.p.findViewById(R.id.tv_wechat_title);
        this.F = (TextView) this.p.findViewById(R.id.tv_wechat_describe);
        this.G = (WebImageView) this.p.findViewById(R.id.pv_wechat_link);
        this.C = (ImageView) this.p.findViewById(R.id.ivEditorRecommend);
        this.B = (ImageView) this.p.findViewById(R.id.ivPostGone);
        this.x.setVisibility(8);
        this.O = (LinearLayout) this.p.findViewById(R.id.llGodReview);
        this.k = new cn.xiaochuankeji.tieba.ui.comment.b(this.d);
        this.O.addView(this.k.f_());
        this.k.f_().setTag(this.k);
        this.P = (ImageView) this.p.findViewById(R.id.ivTediumPost);
        this.V = (FollowView) this.p.findViewById(R.id.post_item_follow_view);
        if (this.d instanceof HomePageActivity) {
            this.P.setVisibility(0);
        } else {
            this.P.setVisibility(8);
        }
        this.Q = (TextView) this.p.findViewById(R.id.ivFollow);
        if (this.d instanceof PostDetailActivity) {
            this.Q.setVisibility(0);
        } else {
            this.Q.setVisibility(8);
        }
        this.af = (ImageView) this.p.findViewById(R.id.iv_more);
        this.ag = (ImageView) this.p.findViewById(R.id.iv_user_level);
        this.Z = (TextView) this.p.findViewById(R.id.tvWriterName);
        this.ac = (ImageView) this.p.findViewById(R.id.iv_talent);
        this.ad = (ImageView) this.p.findViewById(R.id.iv_official);
        this.ab = (TextView) this.p.findViewById(R.id.tvCancelFavor);
        this.aa = (TextView) this.p.findViewById(R.id.tvCreateTime);
        if (this.R) {
            this.aa.setVisibility(0);
        } else {
            this.aa.setVisibility(8);
        }
        this.u = (MultipleLineEllipsisTextView) this.p.findViewById(R.id.tvPostContent);
        this.u.setOnToggleCollapseListener(new MultipleLineEllipsisTextView.d(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                if (z) {
                    MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_TEXT_VIEW_COLLAPSE);
                    messageEvent.setData(this.a.d);
                    messageEvent.setExtraData(Integer.valueOf(this.a.ae));
                    c.a().d(messageEvent);
                    this.a.j.a(this.a.g);
                }
            }
        });
        this.u.setOnExpandableTextViewListener(new MultipleLineEllipsisTextView.c(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick() {
                this.a.a("post");
            }

            public void a() {
                this.a.b(false);
            }
        });
        this.p.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                this.a.b(false);
                return true;
            }
        });
        this.p.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a("post");
            }
        });
        this.ak = (ImageView) this.p.findViewById(R.id.iv_tip_delete_in_topic);
        this.N = (ViewGodReviewIndicators) this.p.findViewById(R.id.vGodReviewIndicators);
        c(i);
    }

    private void a(ArrayList<String> arrayList, String str) {
        long j = this.g._ID;
        final long j2 = this.g._topic != null ? this.g._topic._topicID : 0;
        String str2 = "";
        if (this.l == EntranceType.Post_RecommendImgTxt) {
            str2 = "index-imgtxt";
        } else if (this.l == EntranceType.Post_RecommendIndex) {
            str2 = "index";
        } else if (this.l == EntranceType.Post_RecommendVideo) {
            str2 = "index-video";
        }
        new n(j, j2, 0, arrayList, str, str2, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ e b;

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                g.a("将减少类似内容推荐");
                if (jSONObject != null && 1 == jSONObject.optInt("block_topic")) {
                    this.b.a(j2, this.b.g._topic._topicName);
                }
                this.b.a();
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    public void a() {
        MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_DELETE);
        messageEvent.setData(this.g);
        c.a().d(messageEvent);
    }

    private void a(final long j, String str) {
        StringBuilder stringBuilder = new StringBuilder("小右看你总是删除");
        stringBuilder.append(str);
        stringBuilder.append("的帖子，需不需要屏蔽该话题呀？");
        f a = f.a("提示", stringBuilder.toString(), (Activity) this.d, new f.a(this) {
            final /* synthetic */ e b;

            public void a(boolean z) {
                if (z) {
                    this.b.a(j);
                }
            }
        });
        a.setConfirmTip("屏蔽它");
        a.b();
    }

    public void b(boolean z) {
        a(z, true);
    }

    public void a(boolean z, boolean z2) {
        if (!UserAssessActivity.class.isInstance(this.d)) {
            this.al = z;
            SDBottomSheet sDBottomSheet = new SDBottomSheet((Activity) this.d, this);
            sDBottomSheet.a(sDBottomSheet.c(), a(sDBottomSheet, z2));
            sDBottomSheet.b();
            this.j.a(this.g);
        }
    }

    private ArrayList<SDBottomSheet.c> a(SDBottomSheet sDBottomSheet, boolean z) {
        Object cVar;
        ArrayList<SDBottomSheet.c> arrayList = new ArrayList();
        if (this.u.getText().length() > 0 && z) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_copy, "复制文字", 6));
        }
        Object obj = cn.xiaochuankeji.tieba.background.a.g().c() == this.g._member.getId() ? 1 : null;
        if (this.g.isFavored()) {
            cVar = new SDBottomSheet.c(R.drawable.icon_option_favorite, "取消收藏", 8);
        } else {
            cVar = new SDBottomSheet.c(R.drawable.icon_option_favorite, "收藏", 7);
        }
        arrayList.add(cVar);
        if (this.d instanceof HomePageActivity) {
            arrayList.add(new SDBottomSheet.c(R.drawable.toast_shield, "屏蔽该话题", 11));
        }
        if (obj != null) {
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "删除", 9));
        }
        if (obj == null || (((this.d instanceof MyPostActivity) || (this.d instanceof PostDetailActivity)) && this.g != null && this.g.status == -1)) {
            String str;
            String str2 = "举报";
            if (((this.d instanceof MyPostActivity) || (this.d instanceof PostDetailActivity)) && obj != null) {
                str = "申诉";
            } else {
                str = str2;
            }
            arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_report, str, 12));
        }
        if (this.d instanceof TopicDetailActivity) {
            int a = ((TopicDetailActivity) this.d).a(this.g._topic._topicID);
            if (a >= 2) {
                arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "移除帖子", 10));
                if ((a == 2 || a == 4) && this.g._topic.enable_black == 1) {
                    arrayList.add(new SDBottomSheet.c(R.drawable.toast_limit_post, "限制发帖", 17));
                }
                if (a == 4) {
                    arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_topic_top, "置顶帖子", 13));
                }
            }
        }
        return arrayList;
    }

    private void r() {
        this.s.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.Z.setOnClickListener(this);
        this.ab.setOnClickListener(this);
        this.T.setOnClickListener(this);
        this.x.setOnClickListener(this);
        this.P.setOnClickListener(this);
        this.Q.setOnClickListener(this);
        this.af.setOnClickListener(this);
    }

    public void b() {
        this.ab.setVisibility(0);
    }

    public void a(Post post, m mVar, int i, boolean z, HashMap<Long, Boolean> hashMap, HashMap<Long, ExpandableTextView.f> hashMap2, b bVar) {
        c();
        this.g = post;
        if (hashMap != null) {
            this.X = hashMap;
        }
        this.h = mVar;
        this.i = i;
        u();
        if (cn.xiaochuankeji.tieba.background.a.g() != null && this.g._member.getId() == cn.xiaochuankeji.tieba.background.a.g().c()) {
            this.Q.setVisibility(4);
        }
        if (cn.xiaochuankeji.tieba.background.a.g().d()) {
            this.Q.setVisibility(4);
        }
        if (this.g._member != null) {
            this.o.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(this.g._member.getId(), this.g._member.getAvatarID()));
            this.Z.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(post._member.getName()));
            final Medal medal = this.g._member.getMedal();
            if (this.g._member.isOfficial() || medal == null) {
                this.ac.setVisibility(8);
            } else {
                this.ac.setVisibility(0);
                if (medal.original == 1) {
                    this.ac.setImageResource(c.a.d.a.a.a().d(R.drawable.talent_original));
                } else if (medal.original == 2) {
                    this.ac.setImageResource(c.a.d.a.a.a().d(R.drawable.talent));
                } else if (medal.original == 3) {
                    this.ac.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_talent_small_icon));
                } else {
                    this.ac.setVisibility(8);
                }
                this.ac.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ e b;

                    public void onClick(View view) {
                        new cn.xiaochuankeji.tieba.ui.widget.d(this.b.d, medal).a(this.b.ac).show();
                    }
                });
            }
            this.ad.setVisibility(this.g._member.isOfficial() ? 0 : 8);
            this.ad.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    cn.xiaochuankeji.tieba.ui.utils.e.d(this.a.d);
                }
            });
            this.Q.setText(post._member.atted() != 0 ? "取消关注" : "关注");
        }
        this.p.findViewById(R.id.vPostDivide).setVisibility(z ? 8 : 0);
        this.aa.setText(h.a(post._createTime * 1000));
        Object obj = ((this.d instanceof HomePageActivity) && this.g.status == 3) ? 1 : null;
        this.C.setVisibility(obj != null ? 0 : 8);
        g();
        this.v.a(this.g._liked, this.g._likeCount, this);
        v();
        x();
        y();
        if (hashMap2 != null) {
            a((HashMap) hashMap2, bVar);
        }
        if (this.g.gray == 1) {
            s();
        } else {
            s();
        }
        this.Y = new k(this.g, this.g._ID, this.g._topic != null ? this.g._topic._topicID : 0, this.g._member != null ? this.g._member.getId() : 0, this.d);
    }

    public void a(Post post, int i) {
        int i2;
        int i3 = 0;
        c();
        this.g = post;
        this.i = i;
        if (this.g._member != null) {
            this.o.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(this.g._member.getId(), this.g._member.getAvatarID()));
            this.Q.setText(post._member.atted() != 0 ? "取消关注" : "关注");
            this.Z.setText(post._member.getName());
            final Medal medal = this.g._member.getMedal();
            if (this.g._member.isOfficial() || medal == null) {
                this.ac.setVisibility(8);
            } else {
                this.ac.setVisibility(0);
                if (medal.original == 1) {
                    this.ac.setImageResource(c.a.d.a.a.a().d(R.drawable.talent_original));
                } else if (medal.original == 2) {
                    this.ac.setImageResource(c.a.d.a.a.a().d(R.drawable.talent));
                } else if (medal.original == 3) {
                    this.ac.setImageResource(c.a.d.a.a.a().d(R.drawable.topic_talent_small_icon));
                } else {
                    this.ac.setVisibility(8);
                }
                this.ac.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ e b;

                    public void onClick(View view) {
                        new cn.xiaochuankeji.tieba.ui.widget.d(this.b.d, medal).a(this.b.ac).show();
                    }
                });
            }
            ImageView imageView = this.ad;
            if (this.g._member.isOfficial()) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            imageView.setVisibility(i2);
            this.ad.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    cn.xiaochuankeji.tieba.ui.utils.e.d(this.a.d);
                }
            });
            if (cn.xiaochuankeji.tieba.background.a.g() != null && this.g._member.getId() == cn.xiaochuankeji.tieba.background.a.g().c()) {
                this.Q.setVisibility(4);
            }
            if (cn.xiaochuankeji.tieba.background.a.g().d()) {
                this.Q.setVisibility(4);
            }
        }
        u();
        this.aa.setText(h.a(post._createTime * 1000));
        i2 = ((this.d instanceof HomePageActivity) && this.g.status == 3) ? 1 : 0;
        ImageView imageView2 = this.C;
        if (i2 == 0) {
            i3 = 8;
        }
        imageView2.setVisibility(i3);
        g();
        this.v.a(this.g._liked, this.g._likeCount, this);
        v();
        x();
        y();
        s();
        this.Y = new k(this.g, this.g._ID, this.g._topic != null ? this.g._topic._topicID : 0, this.g._member != null ? this.g._member.getId() : 0, this.d);
    }

    private void s() {
        int x;
        ViewType viewType = null;
        int topicRole = this.g._member.getTopicRole();
        this.P.setVisibility(8);
        this.V.setVisibility(8);
        if (this.d instanceof HomePageActivity) {
            FollowView followView;
            FollowView.a t;
            ViewType[] viewTypeArr;
            if (this.g._member.atted() == 0) {
                this.V.setVisibility(0);
                followView = this.V;
                t = t();
                viewTypeArr = new ViewType[2];
                if (!HolderCreator.a(this.g._member.getId())) {
                    viewType = ViewType.FOLLOW;
                }
                viewTypeArr[0] = viewType;
                viewTypeArr[1] = ViewType.DELETE;
                followView.a(t, viewTypeArr);
            } else if (this.g.hasUpdate) {
                this.V.setVisibility(0);
                followView = this.V;
                t = t();
                viewTypeArr = new ViewType[2];
                if (!HolderCreator.a(this.g._member.getId())) {
                    viewType = ViewType.CANCEL_FOLLOW;
                }
                viewTypeArr[0] = viewType;
                viewTypeArr[1] = ViewType.DELETE;
                followView.a(t, viewTypeArr);
            } else {
                this.P.setVisibility(0);
            }
        }
        if (this.d instanceof TopicDetailActivity) {
            try {
                if (((TopicDetailActivity) this.d).a(this.g._topic._topicID) >= 0) {
                    this.af.setVisibility(0);
                    this.af.setOnClickListener(this);
                } else {
                    this.af.setVisibility(8);
                }
            } catch (IllegalStateException e) {
            }
        }
        if (this.d instanceof ReportedPostActivity) {
            this.af.setVisibility(0);
            this.af.setOnClickListener(this);
        }
        if (this.d instanceof PostDetailActivity) {
            x = ((PostDetailActivity) this.d).x();
        } else {
            x = topicRole;
        }
        if ((this.d instanceof PostDetailActivity) || (this.d instanceof TopicDetailActivity)) {
            switch (x) {
                case 1:
                    this.ag.setVisibility(0);
                    this.ag.setImageResource(R.drawable.topic_talent_small_icon);
                    break;
                case 2:
                    this.ag.setVisibility(0);
                    this.ag.setImageResource(R.drawable.topic_admin_small_icon);
                    break;
                case 4:
                    this.ag.setVisibility(0);
                    this.ag.setImageResource(R.drawable.topic_holder_small_icon);
                    break;
                case 8:
                    this.ag.setVisibility(0);
                    this.ag.setImageResource(R.drawable.topic_guard_small_icon);
                    break;
                default:
                    this.ag.setVisibility(8);
                    break;
            }
        }
        this.ak.setVisibility(8);
        if (((this.d instanceof MyPostActivity) || (this.d instanceof PostAllegeActivity) || (this.d instanceof PostDetailActivity)) && this.g.status == -1 && this.g._member.getId() == cn.xiaochuankeji.tieba.background.a.i().q().getId()) {
            this.ak.setVisibility(0);
        }
    }

    private FollowView.a t() {
        return new FollowView.a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onClick(ViewType viewType) {
                switch (viewType) {
                    case CANCEL_FOLLOW:
                        if (cn.xiaochuankeji.tieba.ui.auth.a.a((HomePageActivity) this.a.i().getContext(), "home_tab", -10)) {
                            this.a.A();
                            return;
                        }
                        return;
                    case FOLLOW:
                        if (cn.xiaochuankeji.tieba.ui.auth.a.a((HomePageActivity) this.a.i().getContext(), "home_tab", 10)) {
                            this.a.z();
                            return;
                        }
                        return;
                    case DELETE:
                        this.a.k();
                        return;
                    default:
                        return;
                }
            }
        };
    }

    public void a(Post post, cn.xiaochuankeji.tieba.ui.post.postdetail.b.b bVar) {
        a(post, 0);
        if (post.webpage != null && (post.webpage.linkType == 1 || post.postType == 1)) {
            if (this.r == null) {
                this.r = new cn.xiaochuankeji.tieba.ui.post.postdetail.b(this.d);
                this.q.addView(this.r, 0, new LayoutParams(-1, -2));
                this.q.setVisibility(0);
                this.r.a(post.webpage.url, post, bVar);
            }
            w();
        }
        if (TextUtils.isEmpty(post._postContent)) {
            this.u.setVisibility(8);
        }
        if (post._imgList == null || post._imgList.size() == 0) {
        }
        if (post.webpage != null && post.webpage.linkType == 1) {
            this.y.setVisibility(8);
        }
    }

    public void e() {
        if (this.r != null) {
            this.r.a();
            this.r = null;
        }
        if (this.J != null) {
            this.J.destroy();
        }
    }

    private void u() {
        if (this.g._commentCount == 0) {
            this.t.setText("评论");
        } else {
            this.t.setText(cn.xiaochuankeji.tieba.ui.utils.d.b(this.g._commentCount));
        }
        if (this.g._share == -1) {
            return;
        }
        if (this.g._share == 0) {
            this.s.setText("分享");
        } else {
            this.s.setText("" + this.g._share);
        }
    }

    public void a(Post post, HashMap<Long, Boolean> hashMap) {
        c();
        this.B.setVisibility(8);
        this.O.setVisibility(8);
        this.p.findViewById(R.id.llMemberInfo).setVisibility(8);
        this.p.findViewById(R.id.vTopDivide).setVisibility(0);
        this.p.findViewById(R.id.llShareCommentCountUpDownContainer).setVisibility(8);
        this.p.findViewById(R.id.vPostDivide).setVisibility(8);
        this.g = post;
        this.i = 0;
        g();
        if (hashMap != null) {
            this.X = hashMap;
        }
        v();
        x();
        y();
    }

    public void d(int i) {
        this.ae = i;
    }

    private void a(HashMap<Long, ExpandableTextView.f> hashMap, b bVar) {
        int size = this.g.comments.size();
        if (size > 0) {
            if (this.k == null) {
                this.k = (cn.xiaochuankeji.tieba.ui.comment.b) this.O.getChildAt(0).getTag();
            }
            if (this.O.getChildCount() > 0) {
                this.O.getChildAt(0).setVisibility(0);
            }
            int i = (bVar.a < 0 || bVar.a >= this.g.comments.size()) ? 0 : bVar.a;
            Comment comment = (Comment) this.g.comments.get(i);
            ExpandableTextView.f fVar = (ExpandableTextView.f) hashMap.get(Long.valueOf(comment._id));
            if (fVar == null) {
                fVar = new ExpandableTextView.f();
                hashMap.put(Long.valueOf(comment._id), fVar);
            }
            if (this.k != null) {
                this.k.a(comment, this.g, fVar);
            }
            this.O.setVisibility(0);
            if (this.d != null) {
                cn.xiaochuankeji.tieba.background.utils.h.a(this.d, "zy_event_god_or_fine_Comments", "多评论卡片展示");
            }
        } else {
            this.O.setVisibility(8);
        }
        a(size, bVar);
    }

    private void a(int i, b bVar) {
        if (i > 1) {
            this.N.setVisibility(0);
            this.N.a(i, bVar);
            return;
        }
        this.N.setVisibility(8);
    }

    public boolean f() {
        if (this.g.postVote == null || this.g.postVote.getVoteItems().size() <= 0) {
            return false;
        }
        return true;
    }

    private void v() {
        if (TextUtils.isEmpty(this.g._postContent)) {
            this.u.setVisibility(8);
        } else {
            int i;
            this.u.setVisibility(0);
            if (this.g.postType == 1) {
                i = 3;
            } else {
                i = 2;
            }
            int a = c.a.d.a.a.a().a((int) R.color.CT_4);
            if (this.p.getContext() instanceof PostDetailActivity) {
                this.u.setExpandable(false);
            }
            this.u.a(this.g._postContent, this.X, this.g._ID, a, i);
        }
        if (this.g._topic != null && this.g._topic._topicID > 0) {
            w();
        }
    }

    private void w() {
        this.T.setVisibility(0);
        if (TextUtils.isEmpty(this.g._topic._topicName)) {
            this.S.setVisibility(8);
            return;
        }
        this.S.setText(this.g._topic._topicName);
        this.S.setVisibility(0);
    }

    private void x() {
        if (f()) {
            this.c.setVisibility(0);
            i iVar = new i(this.d, this.c, this.g, this.f);
            iVar.a(new i.b(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void a(PostVote postVote) {
                    this.a.g.postVote = postVote;
                    this.a.D();
                    this.a.j.a(this.a.g);
                }
            });
            iVar.a(new i.a(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.B();
                }
            });
            iVar.a();
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.c.getLayoutParams();
            if (this.g.hasImage()) {
                layoutParams.topMargin = cn.htjyb.c.a.a(15.0f, this.d);
            } else {
                layoutParams.topMargin = cn.htjyb.c.a.a(15.0f, this.d);
            }
            layoutParams.height = this.g.postVote.getVoteItems().size() * this.e.getDimensionPixelOffset(R.dimen.divide_item_height_44);
            this.c.setLayoutParams(layoutParams);
            return;
        }
        this.c.setVisibility(8);
    }

    private void y() {
        if (this.g.webpage == null || TextUtils.isEmpty(this.g.webpage.url)) {
            this.w.setVisibility(8);
            return;
        }
        this.w.setOnClickListener(this);
        this.x.setVisibility(8);
        this.z.setVisibility(8);
        this.y.setVisibility(8);
        switch (this.g.webpage.linkType) {
            case 0:
                this.w.setVisibility(0);
                if (this.g.webpage != null && !TextUtils.isEmpty(this.g.webpage.url)) {
                    this.x.setVisibility(0);
                    this.x.setOnClickListener(this);
                    if (TextUtils.isEmpty(this.g.webpage.title)) {
                        this.D.setText(this.g.webpage.url);
                    } else {
                        this.D.setText(this.g.webpage.title);
                    }
                    this.A.setImageResource(c.a.c.e().c() ? R.drawable.image_link_placeholder_night : R.drawable.image_link_placeholder);
                    this.A.setImageURI(this.g.webpage.thumbUrl);
                    return;
                }
                return;
            case 1:
                if (!TextUtils.isEmpty(this.g.webpage.url) && !TextUtils.isEmpty(this.g.webpage.title)) {
                    this.w.setVisibility(0);
                    if (this.g.webpage != null) {
                        this.y.setVisibility(0);
                        this.y.setOnClickListener(this);
                        this.E.setText(TextUtils.isEmpty(this.g.webpage.title) ? "" : this.g.webpage.title);
                        this.F.setText(TextUtils.isEmpty(this.g.webpage.desc) ? "" : this.g.webpage.desc);
                        this.G.setImageURI(this.g.webpage.thumbUrl);
                        return;
                    }
                    return;
                }
                return;
            case 3:
                if (this.g.webpage != null && !TextUtils.isEmpty(this.g.webpage.url)) {
                    this.w.setVisibility(0);
                    this.z.setVisibility(0);
                    this.z.setOnClickListener(this);
                    if (this.d instanceof PostDetailActivity) {
                        this.I.setVisibility(0);
                        this.H.setVisibility(8);
                        try {
                            this.J = (WebView) this.p.findViewById(R.id.music_web);
                            this.ai = (ImageView) this.p.findViewById(R.id.music_bg);
                        } catch (InflateException e) {
                        }
                        if (this.J != null) {
                            WebSettings settings = this.J.getSettings();
                            settings.setJavaScriptEnabled(true);
                            settings.setDefaultTextEncodingName("UTF-8");
                            settings.setDomStorageEnabled(true);
                            settings.setAllowFileAccess(true);
                            settings.setAppCacheEnabled(true);
                            settings.setCacheMode(2);
                            settings.setUseWideViewPort(true);
                            settings.setLoadWithOverviewMode(true);
                            if (VERSION.SDK_INT >= 21) {
                                settings.setMixedContentMode(0);
                            }
                            if (VERSION.SDK_INT >= 17) {
                                settings.setMediaPlaybackRequiresUserGesture(false);
                            }
                            this.J.setWebViewClient(this.m);
                            this.J.setWebChromeClient(this.n);
                            this.J.loadUrl(this.g.webpage.url);
                            return;
                        }
                        return;
                    }
                    this.H.setVisibility(0);
                    this.I.setVisibility(8);
                    this.K.setImageURI(this.g.webpage.thumbUrl);
                    this.L.setText(this.g.webpage.title);
                    this.M.setText(this.g.webpage.author);
                    return;
                }
                return;
            default:
                this.w.setVisibility(8);
                return;
        }
    }

    public Post h() {
        return this.g;
    }

    public View i() {
        return this.p;
    }

    public void j() {
        this.O.setVisibility(8);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivFollow:
                if (this.g._member.atted() != 0) {
                    A();
                    return;
                } else {
                    z();
                    return;
                }
            case R.id.rlLinkArea:
            case R.id.rl_link_container:
            case R.id.rl_link_wechat:
            case R.id.rl_link_net163:
                String str = this.g.webpage.thumbUrl;
                str = this.g.webpage.url;
                str = this.g._topic._topicName;
                if (this.g.webpage.linkType != 0) {
                    a("post");
                } else {
                    str = this.g.webpage.url;
                    try {
                        if (Uri.parse(str).isHierarchical()) {
                            WebActivity.a(this.d, cn.xiaochuan.jsbridge.b.a(null, str));
                        } else {
                            g.a("不是一个有效的url");
                            return;
                        }
                    } catch (Exception e) {
                        g.a("不是一个有效的url");
                        return;
                    }
                }
                this.j.a(this.g);
                if (this.d instanceof UserAssessActivity) {
                    str = this.g.webpage.url;
                    try {
                        if (Uri.parse(str).isHierarchical()) {
                            WebActivity.a(this.d, cn.xiaochuan.jsbridge.b.a(null, str));
                            return;
                        } else {
                            g.a("不是一个有效的url");
                            return;
                        }
                    } catch (Exception e2) {
                        g.a("不是一个有效的url");
                        return;
                    }
                }
                return;
            case R.id.pvAvatar:
            case R.id.tvWriterName:
                if (!(this.d instanceof MemberDetailActivity)) {
                    this.j.a(this.g);
                    MemberDetailActivity.a(this.d, this.g._member.getId(), this.g._ID, 1, 0);
                    return;
                }
                return;
            case R.id.tvShare:
                C();
                this.j.a(this.g);
                return;
            case R.id.tvCommentCount:
                if (!PostDetailActivity.class.isInstance(this.d)) {
                    a("review");
                    return;
                }
                return;
            case R.id.ivTediumPost:
                k();
                return;
            case R.id.iv_more:
                if (this.d instanceof ReportedPostActivity) {
                    this.Y.b();
                    return;
                } else {
                    a(false, false);
                    return;
                }
            case R.id.tvCancelFavor:
                m();
                return;
            case R.id.flTopicNameClickContainer:
                if (!UserAssessActivity.class.isInstance(this.d)) {
                    E();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void z() {
        new cn.xiaochuankeji.tieba.background.e.b(this.g._member.getId(), null, null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                this.a.Q.setText("取消关注");
                this.a.g._member.setAtted(1);
                this.a.g.hasUpdate = true;
                c.a().d(new cn.xiaochuankeji.tieba.ui.topic.data.b(this.a.g._member.getId(), true));
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
        cn.xiaochuankeji.tieba.background.utils.h.a(this.d, "zy_event_postdetail_page", "关注用户");
    }

    private void A() {
        new cn.xiaochuankeji.tieba.background.e.a(this.g._member.getId(), null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                this.a.Q.setText("关注");
                this.a.g._member.setAtted(0);
                this.a.g.hasUpdate = true;
                c.a().d(new cn.xiaochuankeji.tieba.ui.topic.data.b(this.a.g._member.getId(), false));
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
        cn.xiaochuankeji.tieba.background.utils.h.a(this.d, "zy_event_postdetail_page", "取消关注用户");
    }

    public void k() {
        cn.xiaochuankeji.tieba.ui.widget.k kVar = new cn.xiaochuankeji.tieba.ui.widget.k(this.d);
        kVar.a(this.g, cn.xiaochuankeji.tieba.background.utils.c.a.c().n(), new cn.xiaochuankeji.tieba.ui.widget.k.b(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void a(ArrayList<String> arrayList, String str) {
                this.a.a((ArrayList) arrayList, str);
            }
        });
        kVar.show();
        if (HomePageActivity.class.isInstance(this.d) && HomePageActivity.e()) {
            cn.xiaochuankeji.tieba.background.utils.h.a(this.d, "zy_event_homepage_tab_recommend", "X 点击次数");
        }
    }

    private void B() {
        if (f()) {
            this.j.a(this.g);
            PostVoteDetailActivity.a(this.d, this.g._ID, (long) this.g.postVote.getId(), this.i);
        }
    }

    private void C() {
        SDBottomSheet sDBottomSheet = new SDBottomSheet((Activity) this.d, this);
        sDBottomSheet.a(sDBottomSheet.c(), null);
        sDBottomSheet.b();
    }

    private void D() {
        if (this.h != null) {
            this.h.notifyListUpdate();
        }
        r.a().a(this.g._ID, this.g.postVote);
    }

    private void E() {
        if (!(this.d instanceof TopicDetailActivity)) {
            F();
            String str = "";
            if (HomePageActivity.class.isInstance(this.d)) {
                str = "index";
            } else if (PostDetailActivity.class.isInstance(this.d)) {
                str = "postdetail";
            }
            TopicDetailActivity.a(this.d, this.g._topic, str, this.g._ID);
            this.j.a(this.g);
        }
    }

    private void F() {
        if (HomePageActivity.class.isInstance(this.d) && HomePageActivity.e()) {
            cn.xiaochuankeji.tieba.background.utils.h.a(this.d, "zy_event_homepage_tab_recommend", "话题点击次数");
        }
    }

    public void a(int i) {
        if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
            e(i);
            G();
        } else if (i == 6) {
            cn.xiaochuankeji.tieba.ui.utils.d.a(this.u.getFullText());
            g.a("已复制");
        } else if (i == 7) {
            l();
        } else if (i == 8) {
            m();
        } else if (i == 9) {
            this.Y.b(0);
        } else if (i == 10) {
            this.Y.a(true);
        } else if (i == 11) {
            a(this.g._topic._topicID);
        } else if (i == 12) {
            this.Y.a();
        } else if (i == 13) {
            TopicPostTopActivity.a(this.d, this.g, -1, "");
        } else if (i == 17) {
            this.Y.a(false);
        } else if (i == 18) {
            this.g.copyLink();
        }
    }

    private void G() {
        if ((this.d instanceof HomePageActivity) && HomePageActivity.e()) {
            cn.xiaochuankeji.tieba.background.utils.h.a(this.d, "zy_event_homepage_tab_recommend", "分享点击次数");
        } else if (!(this.d instanceof PostDetailActivity)) {
        } else {
            if (this.al) {
                this.al = false;
                cn.xiaochuankeji.tieba.background.utils.h.a(this.d, "zy_event_postdetail_page", "更多_分享");
                return;
            }
            cn.xiaochuankeji.tieba.background.utils.h.a(this.d, "zy_event_postdetail_page", "普通_分享");
        }
    }

    private void a(long j) {
        new BlockTopicActionRequest(j, cn.xiaochuankeji.tieba.background.a.g().a(), null, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                g.a("屏蔽成功,可在\"我的\"中取消");
                this.a.a();
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a(xCError.getMessage());
            }
        }).execute();
    }

    public void l() {
        if (cn.xiaochuankeji.tieba.ui.auth.a.a((AppCompatActivity) this.d, "post_list", 11, 0)) {
            if (this.aj == null) {
                this.aj = new a((Activity) this.d, new a.a(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        if (z) {
                            g.a("收藏成功");
                            this.a.c(true);
                            return;
                        }
                        g.a("收藏失败");
                    }
                });
            }
            this.aj.a(this.g._ID);
            this.aj.e();
        }
    }

    public void m() {
        new cn.xiaochuankeji.tieba.background.favorite.g(System.currentTimeMillis(), this.g._ID, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onResponse(Object obj, Object obj2) {
                a((JSONObject) obj, obj2);
            }

            public void a(JSONObject jSONObject, Object obj) {
                if (jSONObject != null) {
                    Favorite favorite = new Favorite(jSONObject);
                    cn.xiaochuankeji.tieba.background.favorite.f.a().a(favorite.getId(), favorite.getPostCount());
                    g.a("取消收藏成功");
                    this.a.c(false);
                    MessageEvent messageEvent = new MessageEvent(MessageEventType.MESSAGE_POST_CANCEL_FAVORED);
                    messageEvent.setData(this.a.g);
                    c.a().d(messageEvent);
                }
            }
        }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onErrorResponse(XCError xCError, Object obj) {
                g.a("取消收藏失败");
            }
        }).execute();
    }

    private void c(boolean z) {
        if (HomePageActivity.class.isInstance(this.d)) {
            r.a().a(this.g._ID, z);
            return;
        }
        this.g.setFavored(z);
        r.a().a(this.g._ID, z);
        r.b().a(this.g._ID, z);
    }

    @l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        if (MessageEventType.MESSAGE_POST_SHARE == messageEvent.getEventType()) {
            Post post = (Post) messageEvent.getData();
            if (this.g._ID == post._ID) {
                this.g._share = post._share;
                this.s.setText("" + post._share);
            }
        } else if (messageEvent.getEventType() == MessageEventType.MESSAGE_POST_CANCEL_LIKE && ((Long) messageEvent.getData()).longValue() == this.g._ID) {
            this.v.b();
        }
    }

    @l(a = ThreadMode.MAIN)
    public void cancelLikeState(cn.xiaochuankeji.tieba.background.d.b bVar) {
        this.k.a(bVar);
    }

    protected void a(String str) {
        if (!PostDetailActivity.class.isInstance(this.d) && !UserAssessActivity.class.isInstance(this.d)) {
            this.j.a(this.g);
            if (this.d instanceof TopicDetailActivity) {
            }
            if (this.g.webpage != null) {
                com.izuiyou.a.a.b.b(this.g.webpage.toString());
            }
            cn.xiaochuankeji.tieba.background.utils.a.g.a().a = false;
            PostDetailActivity.a(this.d, new Post(this.g._ID), str);
        }
    }

    private void e(final int i) {
        final ShareDataModel postShareDataModel = new PostShareDataModel(this.g, this.g.comments.size() > 0 ? (Comment) this.g.comments.get(0) : null, i);
        postShareDataModel.prepareData(new ShareDataModel.a(this) {
            final /* synthetic */ e c;

            public void a() {
                cn.xiaochuankeji.tieba.background.utils.share.b.a().a((Activity) this.c.d, postShareDataModel);
                String str = (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(i));
                String str2 = "other";
                if (this.c.d instanceof HomePageActivity) {
                    str2 = "index";
                } else if (this.c.d instanceof TopicDetailActivity) {
                    str2 = "topicdetail";
                } else if (this.c.d instanceof PostDetailActivity) {
                    str2 = "postdetail";
                }
                cn.xiaochuankeji.tieba.background.i.a.a(this.c.g._ID, str2, str, postShareDataModel.getABTestId());
                if (this.c.g._share != -1) {
                    Post post = this.c.g;
                    post._share++;
                    this.c.s.setText("" + this.c.g._share);
                }
            }
        });
    }

    public void a(boolean z) {
        LikedUsersActivity.a(this.d, this.g._ID, z, 1);
    }

    public void a(int i, int i2, boolean z) {
        if (z) {
            if (1 == i) {
                this.W.a(this.g._ID, this.f, new d.b(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z, String str) {
                        if (!z) {
                            g.a(str);
                        }
                    }
                });
            } else if (-1 == i) {
                this.W.a(this.g._ID, 0, this.f, new d.a(this) {
                    final /* synthetic */ e a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z, boolean z2, String str) {
                        if (!z) {
                            g.a(str);
                        }
                    }
                });
            }
        }
        this.g._liked = i;
        this.g._likeCount = i2;
        if (PostDetailActivity.class.isInstance(this.d)) {
            r.a().a(this.g._ID, i, i2);
        }
        if (1 == i) {
            if (HomePageActivity.class.isInstance(this.d) && HomePageActivity.e()) {
                cn.xiaochuankeji.tieba.background.utils.h.a(this.d, "zy_event_homepage_tab_recommend", "顶点击次数");
            } else if (PostDetailActivity.class.isInstance(this.d)) {
                cn.xiaochuankeji.tieba.background.utils.h.a(this.d, "zy_event_postdetail_page", "帖子顶");
            }
        } else if (HomePageActivity.class.isInstance(this.d) && HomePageActivity.e()) {
            cn.xiaochuankeji.tieba.background.utils.h.a(this.d, "zy_event_homepage_tab_recommend", "踩点击次数");
        } else if (PostDetailActivity.class.isInstance(this.d)) {
            cn.xiaochuankeji.tieba.background.utils.h.a(this.d, "zy_event_postdetail_page", "帖子踩");
        }
        this.j.a(this.g);
    }

    public void c() {
        o();
    }

    private void H() {
        if (TextUtils.isEmpty(this.ah)) {
            new AsyncTask<Void, Void, String>(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((Void[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((String) obj);
                }

                protected String a(Void... voidArr) {
                    try {
                        return "javascript:" + cn.htjyb.c.b.a(new URL(cn.xiaochuankeji.tieba.background.utils.c.a.c().B()).openStream(), Charset.forName("utf-8"));
                    } catch (Exception e) {
                        return "javascript:!function (w) {\n    function wangyiPlay() {\n        document.getElementsByClassName('footer')[0].style.display = \"none\";\n        document.getElementsByClassName('g-talk')[0].style.display = \"none\";\n        var playBtn = document.getElementsByClassName(\"disc\")[0].getElementsByTagName(\"a\")[0];\n        playBtn.className = \"btn f-pa f-vhc j-flag\";\n        playBtn.style.display = \"block\";\n    }\n\n    try {\n        switch (w.location.hostname) {\n            case \"music.163.com\":\n                wangyiPlay();\n                break;\n            default:\n                break;\n        }\n    } catch (e) {\n        console.log(e)\n    }\n}(window);";
                    }
                }

                protected void a(String str) {
                    this.a.ah = str;
                    this.a.J.loadUrl(str);
                }
            }.execute(new Void[0]);
        } else {
            this.J.loadUrl(this.ah);
        }
    }

    public void d() {
    }

    public void n() {
        if (!c.a().b(this)) {
            c.a().a(this);
        }
    }

    public void o() {
        if (c.a().b(this)) {
            c.a().c(this);
        }
        if (this.k != null) {
            this.k.c();
        }
    }

    public boolean equals(Object obj) {
        if ((obj instanceof e) && ((e) obj).h()._ID == h()._ID) {
            return true;
        }
        return false;
    }
}
