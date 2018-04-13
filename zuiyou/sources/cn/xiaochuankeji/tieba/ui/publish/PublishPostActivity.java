package cn.xiaochuankeji.tieba.ui.publish;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.aop.permission.PermissionItem;
import cn.xiaochuankeji.aop.permission.e;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.post.c;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent;
import cn.xiaochuankeji.tieba.background.modules.chat.models.beans.MessageEvent.MessageEventType;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl;
import cn.xiaochuankeji.tieba.background.post.l;
import cn.xiaochuankeji.tieba.background.topic.Topic;
import cn.xiaochuankeji.tieba.background.topic.TopicHistoryRecordManager;
import cn.xiaochuankeji.tieba.background.topic.TopicHistoryRecordManager.Type;
import cn.xiaochuankeji.tieba.background.upload.j;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.CheckUrlJson;
import cn.xiaochuankeji.tieba.json.CheckUrlJson.PageRes;
import cn.xiaochuankeji.tieba.json.SubmitUrlJson;
import cn.xiaochuankeji.tieba.json.topic.QueryFobiddenJson;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.homepage.HomePageActivity;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseWhenSelectActivity;
import cn.xiaochuankeji.tieba.ui.publish.PublishPostPicturesView.b;
import cn.xiaochuankeji.tieba.ui.publish.d.a;
import cn.xiaochuankeji.tieba.ui.publish.selecttopic.SelectTopicActivity;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.widget.PictureView;
import cn.xiaochuankeji.tieba.ui.widget.f;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import com.alibaba.fastjson.JSON;
import com.linkedin.urls.detection.UrlDetector;
import com.linkedin.urls.detection.UrlDetectorOptions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

public class PublishPostActivity extends h implements OnClickListener, b, a, e.a {
    private static final org.aspectj.lang.a.a af = null;
    public static Topic d;
    private PictureView A;
    private TextView B;
    private TextView C;
    private ImageView D;
    private PictureView E;
    private ImageView F;
    private boolean G;
    private l H;
    private long I;
    private int J;
    private d K;
    private ArrayList<String> L = new ArrayList();
    private ArrayList<LocalMedia> M = new ArrayList();
    private Handler N = new Handler();
    private Runnable O;
    private int P;
    private String Q;
    private int R;
    private c S;
    private int T = -1;
    private PageRes U;
    private boolean V;
    private LinearLayout W;
    private RelativeLayout X;
    private String Y;
    private ImageView Z;
    private ImageView aa;
    private ImageView ab;
    private b ac;
    private boolean ad = false;
    private j ae;
    long e = 0;
    private EditText f;
    private PublishPostPicturesView g;
    private TextView h;
    private View i;
    private Button j;
    private Button k;
    private View l;
    private TextView m;
    private TextView n;
    private ImageView o;
    private Button p;
    private e q;
    private RelativeLayout r;
    private RelativeLayout s;
    private RelativeLayout t;
    private RelativeLayout u;
    private PictureView v;
    private TextView w;
    private PictureView x;
    private TextView y;
    private TextView z;

    static {
        I();
    }

    private static void I() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("PublishPostActivity.java", PublishPostActivity.class);
        af = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.publish.PublishPostActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 190);
    }

    public static void a(Activity activity, Topic topic, int i) {
        int i2 = 0;
        Intent intent = new Intent(activity, PublishPostActivity.class);
        if (!HomePageActivity.class.isInstance(activity) && TopicDetailActivity.class.isInstance(activity)) {
            i2 = 1;
        }
        intent.putExtra("sourcePage", i2);
        if (topic != null) {
            topic.fillToIntent(intent);
        }
        activity.startActivityForResult(intent, i);
    }

    public boolean h() {
        return false;
    }

    static final void a(PublishPostActivity publishPostActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        publishPostActivity.overridePendingTransition(R.anim.bottom_in, 0);
        com.android.a.a.c.a(publishPostActivity, c.a.d.a.a.a().a((int) R.color.CB), c.a.c.e().d());
        super.onCreate(bundle);
        cn.xiaochuankeji.tieba.background.utils.h.a(publishPostActivity, "zy_event_publishpost_page", "页面进入");
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(af, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new c(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_publish_post;
    }

    protected boolean a(Bundle bundle) {
        Topic topic = new Topic(getIntent());
        if (0 != topic._topicID) {
            d = topic;
        }
        this.H = new l();
        this.J = getIntent().getExtras().getInt("sourcePage");
        return true;
    }

    protected void c() {
        this.f = (EditText) findViewById(R.id.etContent);
        this.g = (PublishPostPicturesView) findViewById(R.id.viewPictures);
        this.h = (TextView) findViewById(R.id.textSelectTopic);
        this.j = (Button) findViewById(R.id.ivAddVote);
        this.k = (Button) findViewById(R.id.ivAddLink);
        this.n = (TextView) findViewById(R.id.tvRefreshTitle);
        this.i = findViewById(R.id.viewVoteArea);
        this.i.setVisibility(8);
        this.K = new d(this, this.i, new OnItemClickListener(this) {
            final /* synthetic */ PublishPostActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.a.ac != null) {
                    this.a.ac.a(this.a.L);
                    this.a.ac.show();
                }
            }
        });
        this.K.a((a) this);
        this.l = findViewById(R.id.rlLinkArea);
        this.m = (TextView) findViewById(R.id.tvLinkHelp);
        this.o = (ImageView) findViewById(R.id.ivDelLink);
        this.p = (Button) findViewById(R.id.bnAddPic);
        this.q = new e(this, this);
        this.r = (RelativeLayout) findViewById(R.id.link_type_url);
        this.s = (RelativeLayout) findViewById(R.id.link_type_article);
        this.t = (RelativeLayout) findViewById(R.id.link_type_video);
        this.u = (RelativeLayout) findViewById(R.id.link_type_music);
        this.w = (TextView) findViewById(R.id.tvUrl);
        this.v = (PictureView) findViewById(R.id.pvLinkHolder);
        this.x = (PictureView) findViewById(R.id.pv_wechat_link);
        this.y = (TextView) findViewById(R.id.tv_wechat_title);
        this.z = (TextView) findViewById(R.id.tv_wechat_describe);
        this.A = (PictureView) findViewById(R.id.pvLink_163net_Holder);
        this.D = (ImageView) findViewById(R.id.btn_play_music);
        this.B = (TextView) findViewById(R.id.tv_net163_title);
        this.C = (TextView) findViewById(R.id.tv_net163_author);
        this.E = (PictureView) findViewById(R.id.pvLink_video_Holder);
        this.F = (ImageView) findViewById(R.id.iv_video_play);
        this.W = (LinearLayout) findViewById(R.id.toolBar);
        this.X = (RelativeLayout) findViewById(R.id.toolBarSmall);
        this.Z = (ImageView) findViewById(R.id.iv_add_photo_small);
        this.ab = (ImageView) findViewById(R.id.iv_add_url_small);
        this.aa = (ImageView) findViewById(R.id.iv_add_vote_small);
    }

    protected void i_() {
        this.g.a(9, (b) this);
        this.m.setText(cn.xiaochuankeji.tieba.background.utils.c.a.c().A());
        z();
        v();
    }

    public void b(boolean z) {
        if (z) {
            this.W.setVisibility(8);
            this.X.setVisibility(0);
            return;
        }
        this.W.setVisibility(0);
        this.X.setVisibility(8);
    }

    private void v() {
        Object x = x();
        if (!TextUtils.isEmpty(x) && !cn.xiaochuankeji.tieba.background.post.c.a().b(x)) {
            onClick(this.k);
        }
    }

    protected void j_() {
        this.h.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.o.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.Z.setOnClickListener(this);
        this.ab.setOnClickListener(this);
        this.aa.setOnClickListener(this);
        this.f.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ PublishPostActivity a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                this.a.A();
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textSelectTopic:
                SelectTopicActivity.a((Activity) this, 1, 0);
                return;
            case R.id.bnAddPic:
            case R.id.iv_add_photo_small:
                PermissionItem permissionItem = new PermissionItem("android.permission.READ_EXTERNAL_STORAGE");
                permissionItem.deniedMessage = "拒绝该权限后无法正常选择大图和视频";
                permissionItem.deniedButton = "仍然拒绝";
                permissionItem.needGotoSetting = true;
                permissionItem.rationalButton = "确认";
                permissionItem.rationalMessage = "打开存储权限后才可以正常选择大图和视频";
                permissionItem.runIgnorePermission = false;
                permissionItem.settingText = "前往设置";
                permissionItem.runIgnorePermission = false;
                cn.xiaochuankeji.aop.permission.a.a((Context) this).a(permissionItem, new e(this) {
                    final /* synthetic */ PublishPostActivity a;

                    {
                        this.a = r1;
                    }

                    public void permissionGranted() {
                        ArrayList arrayList = new ArrayList();
                        Iterator it = this.a.g.getSelectMedias().iterator();
                        while (it.hasNext()) {
                            arrayList.add(((LocalMedia) it.next()).path);
                        }
                        List selectedItems = this.a.g.getSelectedItems();
                        if (selectedItems.size() == 0) {
                            cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.a(this.a, 2);
                        } else {
                            cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.b(this.a, 2, selectedItems);
                        }
                    }

                    public void permissionDenied() {
                        g.a("拒绝该权限后，无法选择大图和视频");
                    }
                });
                return;
            case R.id.ivAddLink:
            case R.id.iv_add_url_small:
            case R.id.rlLinkArea:
            case R.id.tvUrl:
                String x = x();
                if (!(this.l.getVisibility() != 8 || TextUtils.isEmpty(x) || cn.xiaochuankeji.tieba.background.post.c.a().b(x))) {
                    cn.xiaochuankeji.tieba.background.post.c.a().a(x);
                }
                a aVar = new a(this, new a.a(this) {
                    final /* synthetic */ PublishPostActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(String str) {
                        if (TextUtils.isEmpty(str)) {
                            g.a("链接不符合规则，请重新输入");
                            return;
                        }
                        this.a.Y = str;
                        this.a.w.setText(this.a.Y);
                        this.a.Q = null;
                        if (this.a.y()) {
                            this.a.a(false, str);
                            return;
                        }
                        this.a.S = new c();
                        this.a.R = 0;
                        this.a.o.setEnabled(false);
                        this.a.b(this.a.Y);
                        this.a.a(true, this.a.Y);
                    }
                });
                aVar.a(x);
                aVar.show();
                return;
            case R.id.ivAddVote:
            case R.id.iv_add_vote_small:
                this.ac = new b(this, new b.a(this) {
                    final /* synthetic */ PublishPostActivity a;

                    {
                        this.a = r1;
                    }

                    public void a(List<String> list) {
                        this.a.L = (ArrayList) list;
                        if (this.a.L != null && this.a.L.size() > 0) {
                            this.a.K.a(this.a.L);
                            this.a.K.a();
                            LayoutParams layoutParams = (LayoutParams) this.a.i.getLayoutParams();
                            layoutParams.height = (this.a.getResources().getDimensionPixelSize(R.dimen.item_height) * this.a.L.size()) + this.a.getResources().getDimensionPixelOffset(R.dimen.divide_space_10);
                            this.a.i.setLayoutParams(layoutParams);
                        }
                        this.a.B();
                    }
                });
                this.ac.show();
                return;
            case R.id.etContent:
                cn.htjyb.c.a.a(this.f, (Context) this);
                return;
            case R.id.ivDelLink:
                w();
                return;
            case R.id.tvLinkHelp:
                WebActivity.a(this, cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/external_link.html")));
                return;
            default:
                return;
        }
    }

    private void w() {
        this.T = -1;
        this.Y = null;
        this.l.setVisibility(8);
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.r.setVisibility(8);
        this.s.setVisibility(8);
        this.u.setVisibility(8);
        this.t.setVisibility(8);
        this.f.setHint("发表帖子");
        B();
        switch (this.T) {
            case 0:
                this.w.setText("");
                return;
            case 1:
                this.y.setText("");
                this.z.setText("");
                return;
            case 2:
                this.F.setOnClickListener(null);
                return;
            case 3:
                this.B.setText("");
                this.C.setText("");
                this.D.setOnClickListener(null);
                return;
            default:
                return;
        }
    }

    public void l_() {
        this.H.a();
        if (this.q.c()) {
            this.q.b();
        }
    }

    private String x() {
        try {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
            if (clipboardManager != null) {
                ClipData primaryClip = clipboardManager.getPrimaryClip();
                if (primaryClip != null && primaryClip.getItemCount() > 0) {
                    return a(primaryClip.getItemAt(0).getText().toString().trim());
                }
            }
        } catch (Throwable e) {
            cn.xiaochuankeji.tieba.analyse.a.a(e);
        }
        return null;
    }

    private String a(String str) {
        List a = new UrlDetector(str, UrlDetectorOptions.BRACKET_MATCH).a();
        if (a.isEmpty()) {
            return null;
        }
        return ((com.linkedin.urls.a) a.get(0)).toString();
    }

    public void s() {
        if (!this.ad || d == null) {
            D();
        } else {
            g.a("您在该话题内被禁止发帖，请尝试其他话题");
        }
    }

    public void onBackPressed() {
        C();
    }

    protected void onResume() {
        super.onResume();
        if (d != null) {
            this.G = true;
            z();
            A();
        }
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.ae != null) {
            this.ae.a();
        }
        d = null;
        if (this.g != null) {
            this.g.c();
        }
        cn.xiaochuankeji.tieba.ui.videomaker.j.d();
    }

    private boolean y() {
        boolean z = this.g.getSelectMedias().size() > 0;
        boolean z2;
        if (this.L == null || this.L.size() <= 0) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z || r3) {
            return true;
        }
        return false;
    }

    private void a(boolean z, String str) {
        this.l.setVisibility(0);
        this.m.setVisibility(0);
        if (z) {
            B();
            this.n.setVisibility(8);
            return;
        }
        this.Q = null;
        this.T = 0;
        this.U = new PageRes();
        this.U.linkType = 0;
        this.U.url = str;
        this.U.title = str;
        G();
    }

    private void z() {
        if (d != null) {
            this.h.setText(d._topicName);
            this.h.setSelected(true);
        }
    }

    private void A() {
        this.I = System.currentTimeMillis();
    }

    private void B() {
        boolean z;
        if (!(!TextUtils.isEmpty(this.Y)) || (this.T <= 0 && !this.V)) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            this.p.setEnabled(false);
            this.Z.setEnabled(false);
            this.j.setEnabled(false);
            this.aa.setEnabled(false);
        } else {
            this.p.setEnabled(true);
            this.Z.setEnabled(true);
            this.j.setEnabled(true);
            this.aa.setEnabled(true);
        }
        this.k.setEnabled(true);
        this.ab.setEnabled(true);
    }

    private void C() {
        if (!f.a(this) && !this.q.c()) {
            if (TextUtils.isEmpty(this.f.getText().toString().trim()) && this.g.getSelectMedias().isEmpty() && ((this.L == null || this.L.size() <= 0) && !this.G && TextUtils.isEmpty(this.Y))) {
                overridePendingTransition(0, R.anim.bottom_out);
                finish();
                return;
            }
            f.a("提示", "确定放弃发表？", this, new f.a(this) {
                final /* synthetic */ PublishPostActivity a;

                {
                    this.a = r1;
                }

                public void a(boolean z) {
                    if (z) {
                        this.a.overridePendingTransition(0, R.anim.bottom_out);
                        this.a.finish();
                    }
                }
            });
        }
    }

    private void D() {
        if (this.V) {
            g.a("链接解析中，请稍后~");
            return;
        }
        Object trim = this.f.getText().toString().trim();
        int i = !TextUtils.isEmpty(this.Y) ? 1 : 0;
        if (TextUtils.isEmpty(trim) && this.g.getSelectMedias().isEmpty() && ((this.L == null || this.L.size() <= 0) && i == 0)) {
            g.a("请输入帖子内容");
        } else if (d == null) {
            g.a("请添加话题");
            SelectTopicActivity.a((Activity) this, 1, 1);
        } else {
            JSONObject jSONObject;
            this.N.postDelayed(new Runnable(this) {
                final /* synthetic */ PublishPostActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    cn.htjyb.c.a.a(this.a);
                }
            }, 500);
            a(d);
            this.M = this.g.getSelectMedias();
            E();
            try {
                jSONObject = new JSONObject(JSON.toJSONString(this.U));
            } catch (JSONException e) {
                e.printStackTrace();
                jSONObject = null;
            }
            String str = "other";
            if (this.J == 0) {
                str = "index";
            } else if (this.J == 1) {
                str = "topic";
            }
            this.ae = new j();
            this.H.a(trim, this.M, this.L, d, String.valueOf(this.I), this.Y, this.T, jSONObject, str, new l.a(this) {
                final /* synthetic */ PublishPostActivity a;

                {
                    this.a = r1;
                }

                public void a(boolean z, final String str, final Post post) {
                    if (z) {
                        if (this.a.T >= 0 && post.isSingleImage()) {
                            post.imageLoadState = 1;
                        }
                        this.a.P = 0;
                        this.a.O = new Runnable(this) {
                            final /* synthetic */ AnonymousClass12 b;

                            public void run() {
                                this.b.a.P = this.b.a.P + 1;
                                if (this.b.a.P <= 30) {
                                    this.b.a.a(30, (long) this.b.a.P, -1);
                                    this.b.a.N.post(this.b.a.O);
                                    return;
                                }
                                this.b.a.q.b();
                                Intent intent = new Intent();
                                intent.putExtra("publishedPost", post);
                                this.b.a.setResult(-1, intent);
                                this.b.a.overridePendingTransition(0, R.anim.bottom_out);
                                org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.topic.data.a());
                                this.b.a.finish();
                                if (this.b.a.J == 0) {
                                    cn.xiaochuankeji.tieba.background.utils.h.a(this.b.a, "zy_event_publishpost_page", "发帖成功_推荐流");
                                } else if (this.b.a.J == 1) {
                                    cn.xiaochuankeji.tieba.background.utils.h.a(this.b.a, "zy_event_publishpost_page", "发帖成功_话题内");
                                }
                            }
                        };
                        this.a.N.post(this.a.O);
                        return;
                    }
                    this.a.N.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass12 b;

                        public void run() {
                            this.b.a.q.b();
                            g.a(str);
                        }
                    });
                }
            }, new cn.xiaochuankeji.tieba.background.upload.b(this) {
                final /* synthetic */ PublishPostActivity a;

                {
                    this.a = r1;
                }

                public void a(long j, long j2, int i) {
                    this.a.a(j, j2, i);
                }
            }, this.ae);
        }
    }

    private void a(Topic topic) {
        TopicHistoryRecordManager.getInstance(Type.kSelect).insert(topic);
    }

    private void E() {
        String str = "";
        if (this.M.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder("正在上传");
            if (1 == ((LocalMedia) this.M.get(0)).type) {
                stringBuilder.append("视频");
            } else {
                stringBuilder.append("图片");
            }
            stringBuilder.append("1/" + this.M.size());
            str = stringBuilder.toString();
        }
        this.q.a(str, 10, 0);
        this.q.a();
    }

    protected void onActivityResult(int i, int i2, final Intent intent) {
        if (-1 == i2) {
            if (1 == i) {
                d = new Topic(intent);
                this.G = true;
                z();
                A();
                this.ad = false;
                cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
                new cn.xiaochuankeji.tieba.api.topic.b().b(d._topicID).a(rx.a.b.a.a()).b(new rx.j<QueryFobiddenJson>(this) {
                    final /* synthetic */ PublishPostActivity b;

                    public /* synthetic */ void onNext(Object obj) {
                        a((QueryFobiddenJson) obj);
                    }

                    public void onCompleted() {
                    }

                    public void onError(Throwable th) {
                        cn.xiaochuankeji.tieba.ui.widget.g.c(this.b);
                    }

                    public void a(QueryFobiddenJson queryFobiddenJson) {
                        cn.xiaochuankeji.tieba.ui.widget.g.c(this.b);
                        if (queryFobiddenJson != null && queryFobiddenJson.isFobidden) {
                            g.a("您在该话题内被禁止发帖，请尝试其他话题");
                            this.b.ad = true;
                        } else if (intent.getIntExtra("PARAM_ACTION_TYPE", 0) == 1) {
                            this.b.D();
                        }
                    }
                });
            } else if (2 == i) {
                Collection a = cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.a(intent);
                if (a != null) {
                    this.g.setSelectMedias(a);
                }
                A();
                B();
            }
        }
    }

    public void j() {
        this.L.clear();
        this.L = null;
        B();
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void message(MessageEvent messageEvent) {
        cn.xiaochuankeji.tieba.background.upload.g.b bVar;
        if (messageEvent.getEventType() == MessageEventType.MESSAGE_UPLOAD_PROGRESS) {
            if (System.currentTimeMillis() - this.e >= 1000) {
                com.izuiyou.a.a.b.e("正常更新进度");
                this.e = System.currentTimeMillis();
            }
            bVar = (cn.xiaochuankeji.tieba.background.upload.g.b) messageEvent.getData();
            this.q.a(null, bVar.b, bVar.c);
        } else if (messageEvent.getEventType() == MessageEventType.MESSAGE_SHOW_DUMMY_PROGRESS_AND_SHOW_NEXT) {
            com.izuiyou.a.a.b.e("走一个假进度并将title更新为下一个");
            bVar = (cn.xiaochuankeji.tieba.background.upload.g.b) messageEvent.getData();
            if (bVar != null) {
                this.P = 0;
                int i = bVar.a;
                this.O = new Runnable(this) {
                    final /* synthetic */ PublishPostActivity a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.P = this.a.P + 1;
                        if (this.a.P <= 30) {
                            this.a.q.a(null, 30, this.a.P);
                            this.a.N.post(this.a.O);
                            return;
                        }
                        org.greenrobot.eventbus.c.a().d(new MessageEvent(MessageEventType.MESSAGE_DUMMY_PROGRESS_OVER));
                    }
                };
                this.N.post(this.O);
            }
        } else if (messageEvent.getEventType() == MessageEventType.MESSAGE_SHOW_NEXT) {
            com.izuiyou.a.a.b.e("将ProgressBar title更新为下一个");
            if (((cn.xiaochuankeji.tieba.background.upload.g.b) messageEvent.getData()) != null) {
            }
        }
    }

    @org.greenrobot.eventbus.l(a = ThreadMode.MAIN)
    public void onEventMainThread(cn.xiaochuankeji.tieba.ui.videomaker.c cVar) {
        int i = cVar.a == 2 ? (int) (((float) cVar.b) * 0.8f) : cVar.a == 3 ? (int) (80.0f + (((float) cVar.b) * 0.2f)) : 0;
        this.q.a(null, 100, i);
        if (cVar.a == 3 && cVar.b == 100) {
            this.q.a("正在发帖", 10, 0);
        }
    }

    private void a(long j, long j2, int i) {
        if (i < this.M.size() && i >= 0) {
            int i2 = ((LocalMedia) this.M.get(i)).type;
            StringBuilder stringBuilder = new StringBuilder("正在上传");
            if (1 == i2) {
                stringBuilder.append("视频");
            } else {
                stringBuilder.append("图片");
            }
            stringBuilder.append((i + 1) + "/" + this.M.size());
            this.q.a(stringBuilder.toString(), (int) j, (int) j2);
        } else if (i == -1) {
            this.q.a("正在发帖", (int) j, (int) j2);
        }
    }

    public void a(String str, boolean z) {
        boolean z2 = true;
        this.o.setEnabled(true);
        this.f.setHint("发表帖子");
        if (this.f.getText().toString().trim().equals("")) {
            z2 = false;
        }
        if (z2 || !TextUtils.isEmpty(str)) {
            return;
        }
        if (z) {
            this.n.setVisibility(8);
        } else {
            this.n.setVisibility(8);
        }
    }

    private void F() {
        this.V = false;
        a("", false);
        B();
    }

    private void G() {
        this.V = false;
        this.r.setVisibility(0);
        this.s.setVisibility(8);
        this.t.setVisibility(8);
        this.u.setVisibility(8);
        this.w.setText(TextUtils.isEmpty(this.U.title) ? this.U.url : this.U.title);
        this.o.setEnabled(true);
        B();
        a("", false);
    }

    private void H() {
        if (this.T == 0) {
            B();
        }
    }

    private void b(final String str) {
        this.V = true;
        this.r.setVisibility(0);
        this.l.setVisibility(0);
        this.m.setVisibility(0);
        this.w.setText("链接解析中");
        this.S.a(str).a(rx.a.b.a.a()).b(new rx.j<SubmitUrlJson>(this) {
            final /* synthetic */ PublishPostActivity b;

            public /* synthetic */ void onNext(Object obj) {
                a((SubmitUrlJson) obj);
            }

            public void onCompleted() {
                this.b.H();
            }

            public void onError(Throwable th) {
                com.izuiyou.a.a.b.e(th);
                this.b.T = -1;
                this.b.U = new PageRes();
                this.b.U.title = str;
                this.b.U.url = str;
                this.b.U.linkType = 0;
                g.c("链接解析错误");
                this.b.G();
            }

            public void a(final SubmitUrlJson submitUrlJson) {
                this.b.l.setVisibility(0);
                this.b.m.setVisibility(0);
                this.b.r.setVisibility(8);
                this.b.s.setVisibility(8);
                this.b.t.setVisibility(8);
                this.b.u.setVisibility(8);
                this.b.T = submitUrlJson.linkType;
                switch (submitUrlJson.linkType) {
                    case 0:
                        this.b.r.setVisibility(0);
                        this.b.w.setText("链接解析中");
                        break;
                    case 1:
                        this.b.s.setVisibility(0);
                        this.b.y.setText("链接解析中");
                        break;
                    case 2:
                        this.b.t.setVisibility(0);
                        break;
                    case 3:
                        this.b.u.setVisibility(0);
                        this.b.B.setText("链接解析中");
                        break;
                    default:
                        this.b.r.setVisibility(0);
                        this.b.w.setText("链接解析中");
                        break;
                }
                this.b.N.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 b;

                    public void run() {
                        this.b.b.a(str, submitUrlJson.resId);
                    }
                }, 2000);
            }
        });
    }

    private void a(final String str, final String str2) {
        this.V = true;
        this.R++;
        this.S.a(str, str2).a(rx.a.b.a.a()).b(new rx.j<CheckUrlJson>(this) {
            final /* synthetic */ PublishPostActivity c;

            public /* synthetic */ void onNext(Object obj) {
                a((CheckUrlJson) obj);
            }

            public void onCompleted() {
                this.c.F();
            }

            public void onError(Throwable th) {
                this.c.T = 0;
                this.c.U = new PageRes();
                this.c.U.url = str;
                this.c.U.linkType = 0;
                this.c.U.title = str;
                this.c.G();
            }

            public void a(final CheckUrlJson checkUrlJson) {
                if (checkUrlJson.status == 0) {
                    if (this.c.R <= 5) {
                        this.c.N.postDelayed(new Runnable(this) {
                            final /* synthetic */ AnonymousClass5 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.c.a(str, str2);
                            }
                        }, 2000);
                    } else {
                        this.c.U = checkUrlJson.webPage;
                        if (this.c.U == null) {
                            this.c.U = new PageRes();
                        }
                        if (TextUtils.isEmpty(this.c.U.url)) {
                            this.c.U.url = str;
                        }
                    }
                } else if (checkUrlJson.status == -1) {
                    this.c.r.setVisibility(0);
                    this.c.s.setVisibility(8);
                    this.c.t.setVisibility(8);
                    this.c.u.setVisibility(8);
                    this.c.T = 0;
                    this.c.w.setText(str);
                    this.c.U = checkUrlJson.webPage;
                    if (this.c.U == null) {
                        this.c.U = new PageRes();
                    }
                    this.c.U.linkType = 0;
                    if (TextUtils.isEmpty(this.c.U.url)) {
                        this.c.U.url = str;
                    }
                    this.c.G();
                    return;
                } else if (checkUrlJson.status == 1) {
                    this.c.T = checkUrlJson.webPage.linkType;
                    this.c.U = checkUrlJson.webPage;
                    com.izuiyou.a.a.b.b("check result:" + checkUrlJson.toString());
                    this.c.U = checkUrlJson.webPage;
                    this.c.o.setEnabled(true);
                }
                switch (checkUrlJson.webPage.linkType) {
                    case 0:
                        this.c.r.setVisibility(0);
                        this.c.s.setVisibility(8);
                        this.c.t.setVisibility(8);
                        this.c.u.setVisibility(8);
                        this.c.w.setText(TextUtils.isEmpty(checkUrlJson.webPage.title) ? checkUrlJson.webPage.url : checkUrlJson.webPage.title);
                        this.c.v.setData(cn.xiaochuankeji.tieba.background.a.f().a(PictureImpl.Type.kLinkPic228White, checkUrlJson.webPage.thumbId));
                        return;
                    case 1:
                        this.c.s.setVisibility(0);
                        this.c.y.setText(checkUrlJson.webPage.title);
                        this.c.z.setText(checkUrlJson.webPage.describe);
                        this.c.x.setData(cn.xiaochuankeji.tieba.background.a.f().a(PictureImpl.Type.kLinkPic228White, checkUrlJson.webPage.thumbId));
                        return;
                    case 2:
                        this.c.t.setVisibility(0);
                        this.c.E.setData(cn.xiaochuankeji.tieba.background.a.f().a(PictureImpl.Type.kLinkPic228White, checkUrlJson.webPage.thumbId));
                        this.c.F.setOnClickListener(new OnClickListener(this) {
                            final /* synthetic */ AnonymousClass5 b;

                            public void onClick(View view) {
                                this.b.c.c(checkUrlJson.webPage.videoUrl);
                            }
                        });
                        return;
                    case 3:
                        this.c.u.setVisibility(0);
                        this.c.B.setText(checkUrlJson.webPage.title);
                        this.c.C.setText(checkUrlJson.webPage.author);
                        this.c.A.setData(cn.xiaochuankeji.tieba.background.a.f().a(PictureImpl.Type.kLinkPic228White, checkUrlJson.webPage.thumbId));
                        this.c.u.setOnClickListener(this.c);
                        return;
                    default:
                        this.c.G();
                        return;
                }
            }
        });
    }

    private void c(String str) {
        if (!TextUtils.isEmpty(str)) {
            PictureImpl a = cn.xiaochuankeji.tieba.background.a.f().a(str, PictureImpl.Type.kVideo, 0);
            ArrayList arrayList = new ArrayList();
            arrayList.add(a);
            MediaBrowseWhenSelectActivity.a(this, arrayList, null, 0);
        }
    }

    public void k() {
        B();
    }
}
