package cn.xiaochuankeji.tieba.webview;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.htjyb.netlib.NetworkMonitor;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuan.jsbridge.d;
import cn.xiaochuan.jsbridge.data.JSAssess;
import cn.xiaochuan.jsbridge.data.JSChat;
import cn.xiaochuan.jsbridge.data.JSCreateUgcVideo;
import cn.xiaochuan.jsbridge.data.JSMarket;
import cn.xiaochuan.jsbridge.data.JSOpen;
import cn.xiaochuan.jsbridge.data.JSOpenUgcVideo;
import cn.xiaochuan.jsbridge.data.JSPost;
import cn.xiaochuan.jsbridge.data.JSProfile;
import cn.xiaochuan.jsbridge.data.JSReview;
import cn.xiaochuan.jsbridge.data.JSShare;
import cn.xiaochuan.jsbridge.data.JSToast;
import cn.xiaochuan.jsbridge.data.JSTopic;
import cn.xiaochuan.jsbridge.data.JSUploadFile;
import cn.xiaochuan.jsbridge.f;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.AppController;
import cn.xiaochuankeji.tieba.background.beans.Member;
import cn.xiaochuankeji.tieba.background.data.ServerImage;
import cn.xiaochuankeji.tieba.background.data.post.Post;
import cn.xiaochuankeji.tieba.background.picture.PictureImpl.Type;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.i;
import cn.xiaochuankeji.tieba.background.utils.share.WebPageShare;
import cn.xiaochuankeji.tieba.background.utils.share.WebPageShareStruct;
import cn.xiaochuankeji.tieba.d.j;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.mediabrowse.EntranceType;
import cn.xiaochuankeji.tieba.ui.mediabrowse.MediaBrowseActivity;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.my.assessor.UserAssessActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.postdetail.PostDetailActivity.CommentFilter;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.topic.TopicDetailActivity;
import cn.xiaochuankeji.tieba.ui.ugcvideodetail.UgcVideoActivity;
import cn.xiaochuankeji.tieba.ui.utils.b;
import cn.xiaochuankeji.tieba.ui.videomaker.VideoRecordActivity;
import com.a.a.a.e;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.c.a.c;
import com.tencent.connect.common.Constants;
import com.tencent.wcdb.database.SQLiteDatabase;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractWebActivity extends a {
    private static final org.aspectj.lang.a.a g = null;
    private e a;
    @BindView
    protected FrameLayout action_bar;
    private e b;
    private e c;
    private b d;
    @BindView
    protected View divider;
    protected f e;
    cn.xiaochuan.jsbridge.e f = new cn.xiaochuan.jsbridge.e(this) {
        final /* synthetic */ AbstractWebActivity a;

        {
            this.a = r1;
        }

        public void onProgressChanged(WebView webView, int i) {
            com.izuiyou.a.a.a.a(webView, true);
            super.onProgressChanged(webView, i);
            if (i > 70) {
                this.a.s();
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
            if (!TextUtils.isEmpty(str)) {
                this.a.setTitle(str);
            }
        }
    };
    @BindView
    protected FrameLayout webContainer;

    static {
        k();
    }

    private static void k() {
        org.aspectj.a.b.b bVar = new org.aspectj.a.b.b("AbstractWebActivity.java", AbstractWebActivity.class);
        g = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.webview.AbstractWebActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 155);
    }

    protected abstract WebPageShare a(int i, String str, String str2, String str3, String str4);

    protected abstract void a(cn.xiaochuan.jsbridge.b bVar);

    protected abstract void e();

    static final void a(AbstractWebActivity abstractWebActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        if (abstractWebActivity.g()) {
            c.b(abstractWebActivity).a(0.075f);
        }
        abstractWebActivity.runOnUiThread(new Runnable(abstractWebActivity) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void run() {
                if (NetworkMonitor.a()) {
                    cn.xiaochuan.jsbridge.b bVar = (cn.xiaochuan.jsbridge.b) this.a.getIntent().getParcelableExtra("web_data");
                    if (bVar != null) {
                        this.a.a(bVar);
                        return;
                    } else {
                        g.a("无效的参数");
                        return;
                    }
                }
                g.a("没有网络，请连接~");
                this.a.finish();
            }
        });
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = org.aspectj.a.b.b.a(g, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new a(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected int a() {
        return R.layout.activity_web;
    }

    public void setTitle(CharSequence charSequence) {
    }

    protected void c() {
        super.c();
        ButterKnife.a(this);
        this.action_bar.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
            }
        });
        e();
    }

    protected void i_() {
    }

    protected float p() {
        if (this.e == null) {
            return 0.0f;
        }
        return (float) this.e.getScrollY();
    }

    protected void onResume() {
        if (this.e != null) {
            this.e.onResume();
        }
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
        if (this.e != null) {
            this.e.onPause();
        }
    }

    protected void onDestroy() {
        if (this.webContainer != null) {
            this.webContainer.removeAllViews();
        }
        if (this.d != null) {
            this.d.b();
        }
        if (this.e != null) {
            this.e.destroy();
            this.e = null;
        }
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 263) {
            this.f.a(i2, intent);
        } else if (i == 274) {
            if (i2 != -1) {
                this.a = null;
            } else if (this.a != null) {
                this.a.a("{\"ret\":1}");
            }
        } else if (i == 275) {
            if (i2 == -1 && this.b != null) {
                this.b.a("{\"ret\":1}");
            }
        } else if (i == 276 && this.c != null) {
            if (i2 == -1) {
                a(cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.b(intent));
                return;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ret", Integer.valueOf(0));
            jSONObject.put("errmsg", (Object) "取消选择媒体资源");
            this.c.a(jSONObject.toJSONString());
            this.c = null;
        }
    }

    public void onBackPressed() {
        if (this.e == null) {
            super.onBackPressed();
        } else if (this.e.canGoBack()) {
            this.e.goBack();
        } else {
            super.onBackPressed();
        }
    }

    protected void b(cn.xiaochuan.jsbridge.b bVar) {
        int i = 1;
        if (this.e != null) {
            this.e.removeAllViews();
            this.e.destroy();
            this.e = null;
        }
        this.e = new f(BaseApplication.getAppContext());
        this.webContainer.removeAllViews();
        this.webContainer.addView(this.e, new LayoutParams(-1, -1));
        cn.xiaochuan.jsbridge.a.a(this.e, null, "4.1.8.9");
        this.e.setOnLongClickListener(new cn.xiaochuan.jsbridge.c(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public boolean a(@NonNull String str) {
                Bundle bundle = new Bundle();
                bundle.putInt("HitTestResult", 5);
                bundle.putString("Data", str);
                WebViewTipsFragment.a(this.a.getSupportFragmentManager(), bundle);
                return true;
            }
        });
        this.e.setVerticalScrollBarEnabled(true);
        this.e.setWebViewClient(new d(this, this.e) {
            final /* synthetic */ AbstractWebActivity a;

            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                this.a.j();
            }
        });
        this.e.setWebChromeClient(this.f);
        i.a(this.e);
        a(this.e);
        f fVar = this.e;
        String str = bVar.c;
        String str2 = "dark_mode";
        if (!c.a.c.e().c()) {
            i = 0;
        }
        fVar.loadUrl(j.a(str, str2, String.valueOf(i)));
    }

    protected void j() {
    }

    public void q() {
        setResult(0);
        finish();
    }

    protected void a(f fVar) {
        fVar.setDefaultHandler(new com.a.a.a.f());
        fVar.a(cn.xiaochuan.jsbridge.data.f.a, cn.xiaochuan.jsbridge.data.f.b);
        fVar.a(JSToast.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                JSToast jSToast = (JSToast) JSON.parseObject(str, JSToast.class);
                if (jSToast != null && !TextUtils.isEmpty(jSToast.text)) {
                    g.a(jSToast.text);
                }
            }
        });
        fVar.a("share", new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                this.a.a((JSShare) JSON.parseObject(str, JSShare.class));
            }
        });
        fVar.a(JSPost.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                this.a.a((JSPost) JSON.parseObject(str, JSPost.class));
            }
        });
        fVar.a(cn.xiaochuan.jsbridge.data.e.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                this.a.a(new cn.xiaochuan.jsbridge.data.e(str));
            }
        });
        fVar.a(cn.xiaochuan.jsbridge.data.a.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                this.a.setResult(-1);
                this.a.finish();
            }
        });
        fVar.a(JSOpen.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                JSOpen jSOpen = (JSOpen) JSON.parseObject(str, JSOpen.class);
                if (jSOpen != null) {
                    WebActivity.a(this.a, cn.xiaochuan.jsbridge.b.a(jSOpen.title, jSOpen.url));
                    if (jSOpen.closeCurrent) {
                        this.a.finish();
                    }
                }
            }
        });
        fVar.a(cn.xiaochuan.jsbridge.data.c.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                int i = 1;
                if (eVar != null) {
                    org.json.JSONObject jSONObject = new org.json.JSONObject();
                    cn.xiaochuankeji.tieba.background.modules.a.a g = cn.xiaochuankeji.tieba.background.a.g();
                    int i2 = g.c() == 0 ? 0 : g.d() ? 1 : 2;
                    try {
                        jSONObject.put("userstatus", i2);
                        Member q = g.q();
                        if (q != null) {
                            String str2 = "isbind";
                            if (!q.isBind()) {
                                i = 0;
                            }
                            jSONObject.put(str2, i);
                            jSONObject.put("nickname", q.getRawName());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
                    eVar.a(jSONObject.toString());
                }
            }
        });
        fVar.a(cn.xiaochuan.jsbridge.data.d.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                this.a.a = eVar;
                cn.xiaochuankeji.tieba.ui.auth.a.a(this.a, "h5", 1000, 274);
            }
        });
        fVar.a(JSProfile.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                JSProfile jSProfile = (JSProfile) JSON.parseObject(str, JSProfile.class);
                if (jSProfile.mid > 0) {
                    MemberDetailActivity.a(this.a, jSProfile.mid);
                    if (jSProfile.closeCurrent) {
                        this.a.finish();
                    }
                }
            }
        });
        fVar.a(JSTopic.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                JSTopic jSTopic = (JSTopic) JSON.parseObject(str, JSTopic.class);
                if (jSTopic.tid > 0) {
                    TopicDetailActivity.a(this.a, jSTopic.tid, false, "splash", 0);
                    if (jSTopic.closeCurrent) {
                        this.a.finish();
                    }
                }
            }
        });
        fVar.a(JSReview.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                JSReview jSReview = (JSReview) JSON.parseObject(str, JSReview.class);
                if (jSReview.pid > 0) {
                    PostDetailActivity.a(this.a, new Post(jSReview.pid), 1, new CommentFilter(jSReview.rid, 1), "");
                    if (jSReview.closeCurrent) {
                        this.a.finish();
                    }
                }
            }
        });
        fVar.a(JSMarket.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                JSMarket jSMarket = (JSMarket) JSON.parseObject(str, JSMarket.class);
                if (jSMarket != null && !TextUtils.isEmpty(jSMarket.packageName)) {
                    Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + jSMarket.packageName));
                    intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                    this.a.startActivity(Intent.createChooser(intent, "分享"));
                    if (jSMarket.closeCurrent) {
                        this.a.finish();
                    }
                }
            }
        });
        fVar.a(JSAssess.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                JSAssess jSAssess = (JSAssess) JSON.parseObject(str, JSAssess.class);
                UserAssessActivity.a(this.a);
                Editor edit = cn.xiaochuankeji.tieba.background.a.c().edit();
                edit.putInt("key_first_assess_flag", 0);
                edit.putInt("key_first_assess_category_flag", 0);
                edit.apply();
                if (jSAssess != null && jSAssess.closeCurrent) {
                    this.a.finish();
                }
            }
        });
        fVar.a(JSOpenUgcVideo.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                JSOpenUgcVideo jSOpenUgcVideo = (JSOpenUgcVideo) JSON.parseObject(str, JSOpenUgcVideo.class);
                if (jSOpenUgcVideo.pid > 0) {
                    UgcVideoActivity.b(this.a, jSOpenUgcVideo.pid, "h5act");
                } else if (jSOpenUgcVideo.rid > 0) {
                    UgcVideoActivity.a(this.a, jSOpenUgcVideo.rid, "h5act");
                }
                if (jSOpenUgcVideo.closeCurrent) {
                    this.a.finish();
                }
            }
        });
        fVar.a(JSCreateUgcVideo.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                this.a.b = eVar;
                JSCreateUgcVideo jSCreateUgcVideo = (JSCreateUgcVideo) JSON.parseObject(str, JSCreateUgcVideo.class);
                VideoRecordActivity.a(this.a, 275, 0, "h5act");
                if (jSCreateUgcVideo != null && jSCreateUgcVideo.closeCurrent) {
                    this.a.finish();
                }
            }
        });
        fVar.a(JSChat.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                JSChat jSChat = (JSChat) JSON.parseObject(str, JSChat.class);
                if (jSChat != null) {
                    Member member = new Member();
                    member.setId(jSChat.mid);
                    member.setAvatarID(jSChat.avatar);
                    member.setGender(jSChat.gender);
                    member.setOfficial(jSChat.official);
                    member.setName(jSChat.name);
                    cn.xiaochuankeji.tieba.ui.chat.a.b.a(this.a, member, true);
                    if (jSChat.closeCurrent) {
                        this.a.finish();
                    }
                }
            }
        });
        fVar.a(cn.xiaochuan.jsbridge.data.b.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, final e eVar) {
                if (this.a.d != null) {
                    this.a.d.b();
                }
                this.a.d = b.a();
                try {
                    this.a.d.a(new b.b(this) {
                        final /* synthetic */ AnonymousClass15 b;

                        public void a(String str) {
                        }

                        public void b(String str) {
                            if (eVar != null) {
                                try {
                                    Object str2 = new String(Base64.encode(str.getBytes(), 2));
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put("info_text", str2);
                                    eVar.a(jSONObject.toString());
                                } catch (Exception e) {
                                    eVar.a("{\"ret\":-1}");
                                }
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    if (eVar != null) {
                        eVar.a("{\"ret\":-1}");
                    }
                }
            }
        });
        fVar.a(JSUploadFile.a, new com.a.a.a.a(this) {
            final /* synthetic */ AbstractWebActivity a;

            {
                this.a = r1;
            }

            public void a(String str, e eVar) {
                if (this.a.c == null) {
                    this.a.c = eVar;
                    JSUploadFile jSUploadFile = (JSUploadFile) JSON.parseObject(str, JSUploadFile.class);
                    boolean z = jSUploadFile.multiple;
                    int i = jSUploadFile.count;
                    if ("img".equalsIgnoreCase(jSUploadFile.file_type)) {
                        if (z) {
                            cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.b(this.a, i, 276);
                        } else {
                            cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.d(this.a, 276);
                        }
                    } else if (z) {
                        cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.a(this.a, i, 276);
                    } else {
                        cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.b(this.a, 276);
                    }
                }
            }
        });
    }

    private String a(cn.xiaochuan.jsbridge.data.e eVar) {
        if (eVar.b == null) {
            return "{\"ret\":-1}";
        }
        int intValue = eVar.b.getIntValue("index");
        eVar.b.getLongValue("rid");
        boolean booleanValue = eVar.b.getBooleanValue("closeCurrent");
        Post post = new Post(new org.json.JSONObject(eVar.b));
        if (post._ID <= 0) {
            post._ID = eVar.b.getLongValue("pid");
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = post._imgList.iterator();
        while (it.hasNext()) {
            cn.htjyb.b.a a;
            ServerImage serverImage = (ServerImage) it.next();
            arrayList.add(cn.xiaochuankeji.tieba.background.a.f().a(Type.kPostPic228, serverImage.postImageId));
            if (serverImage.isVideo()) {
                a = cn.xiaochuankeji.tieba.background.a.f().a(post.getImgVideoBy(serverImage.postImageId).getUrl(), Type.kVideo, serverImage.postImageId);
            } else if (serverImage.isMP4()) {
                a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kMP4, (long) serverImage.mp4Id);
            } else if (serverImage.isGif()) {
                a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kGif, serverImage.postImageId);
            } else {
                a = cn.xiaochuankeji.tieba.background.a.f().a(Type.kPostPicLarge, serverImage.postImageId);
            }
            a.setRotate(serverImage.rotate);
            arrayList2.add(a);
        }
        MediaBrowseActivity.a(this, intValue, post, arrayList, arrayList2, post._imgList, post.imgVideos, EntranceType.Subject);
        if (booleanValue) {
            finish();
        }
        return "{\"ret\":-1}";
    }

    private String a(JSPost jSPost) {
        int i = 1;
        if (jSPost == null) {
            return "{\"ret\":-1}";
        }
        Post post = new Post(jSPost.pid);
        if (jSPost.rid == 0) {
            if (!jSPost.isScrollToReview) {
                i = 0;
            }
            PostDetailActivity.a(this, post, i);
        } else {
            PostDetailActivity.a(this, post, 1, new CommentFilter(jSPost.rid, 1), "");
        }
        if (jSPost.closeCurrent) {
            finish();
        }
        return "{\"ret\":1}";
    }

    private String a(final JSShare jSShare) {
        int a = a(jSShare.platform);
        if (a >= 0) {
            return a(a, jSShare);
        }
        new cn.xiaochuankeji.tieba.ui.utils.a(this, new cn.xiaochuankeji.tieba.ui.utils.a.a(this) {
            final /* synthetic */ AbstractWebActivity b;

            public void a(int i) {
                this.b.a(i, jSShare);
            }
        }).a();
        return "{\"ret\":1}";
    }

    protected String a(final int i, final JSShare jSShare) {
        if ((i == 4 || i == 3 || i == 2) && !TextUtils.isEmpty(jSShare.img_url)) {
            Uri parse = Uri.parse(jSShare.img_url);
            if ("http".equalsIgnoreCase(parse.getScheme()) || "https".equalsIgnoreCase(parse.getScheme())) {
                final cn.xiaochuankeji.tieba.ui.mediabrowse.component.f fVar = new cn.xiaochuankeji.tieba.ui.mediabrowse.component.f(getWindow());
                fVar.a();
                cn.xiaochuankeji.tieba.ui.widget.bigImage.e.a(AppController.getAppContext()).a(jSShare.img_url.hashCode(), Uri.parse(jSShare.img_url), new cn.xiaochuankeji.tieba.ui.widget.bigImage.a.b(this) {
                    final /* synthetic */ AbstractWebActivity d;

                    public void a(int i) {
                        super.a(i);
                        fVar.a(i);
                    }

                    public void a(File file) {
                        super.a(file);
                        c(file);
                    }

                    private void c(File file) {
                        try {
                            File file2 = new File(cn.xiaochuankeji.tieba.background.a.e().B(), file.getName());
                            cn.htjyb.c.a.b.a(file, file2);
                            jSShare.img_url = file2.getAbsolutePath();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            c();
                        }
                    }

                    public void a(Throwable th) {
                        jSShare.img_url = null;
                        c();
                    }

                    private void c() {
                        fVar.a(100);
                        fVar.b();
                        WebPageShareStruct a = this.d.a(i, jSShare.title, jSShare.desc, jSShare.img_url, jSShare.link);
                        if (i == 3) {
                            a.suffix = " (分享自@最右APP) 查看详情请戳→_→";
                        }
                        cn.xiaochuankeji.tieba.background.utils.share.b.a().a(i, this.d, a);
                    }
                });
            }
        } else if (i == 18) {
            ClipboardManager clipboardManager = (ClipboardManager) BaseApplication.getAppContext().getSystemService("clipboard");
            ClipData newPlainText = ClipData.newPlainText("link", jSShare.link);
            if (clipboardManager != null) {
                clipboardManager.setPrimaryClip(newPlainText);
                g.a("已复制到剪贴板");
            }
        } else {
            WebPageShareStruct a = a(i, jSShare.title, jSShare.desc, jSShare.img_url, jSShare.link);
            a.suffix = " (分享自@最右APP) 查看详情请戳→_→";
            cn.xiaochuankeji.tieba.background.utils.share.b.a().a(i, this, a);
        }
        return "{\"ret\":1}";
    }

    @SuppressLint({"WrongConstant"})
    private int a(String str) {
        if ("qq".equalsIgnoreCase(str)) {
            return 1;
        }
        if ("weixin_friend".equalsIgnoreCase(str)) {
            return 2;
        }
        if ("weixin_timeline".equalsIgnoreCase(str)) {
            return 4;
        }
        if (Constants.SOURCE_QZONE.equalsIgnoreCase(str)) {
            return 5;
        }
        if ("weibo".equalsIgnoreCase(str)) {
            return 3;
        }
        return -1;
    }

    protected void r() {
        if (this.webContainer != null) {
            cn.xiaochuankeji.tieba.ui.widget.g.a(this, true);
        }
    }

    protected void s() {
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
    }

    private void a(final List<LocalMedia> list) {
        if (this.c != null) {
            if (list == null || list.isEmpty()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ret", Integer.valueOf(-1));
                jSONObject.put("errmsg", (Object) "没有选择媒体资源");
                this.c.a(jSONObject.toJSONString());
                this.c = null;
                return;
            }
            final cn.xiaochuankeji.tieba.ui.publish.e eVar = new cn.xiaochuankeji.tieba.ui.publish.e(this, new cn.xiaochuankeji.tieba.ui.publish.e.a(this) {
                final /* synthetic */ AbstractWebActivity a;

                {
                    this.a = r1;
                }

                public void l_() {
                }
            });
            eVar.a(false);
            eVar.a("正在上传", list.size(), 0);
            eVar.a();
            new cn.xiaochuankeji.tieba.background.upload.j().a(list, "h5", new cn.xiaochuankeji.tieba.background.upload.b(this) {
                final /* synthetic */ AbstractWebActivity c;

                public void a(long j, long j2, int i) {
                    if (i < list.size() && i >= 0 && list.size() > 0 && i < list.size() && i >= 0) {
                        int i2 = ((LocalMedia) list.get(i)).type;
                        StringBuilder stringBuilder = new StringBuilder("正在上传");
                        if (1 == i2) {
                            stringBuilder.append("视频");
                        } else {
                            stringBuilder.append("图片");
                        }
                        stringBuilder.append((i + 1) + "/" + list.size());
                        eVar.a(stringBuilder.toString(), (int) j, (int) j2);
                    }
                }
            }, new cn.xiaochuankeji.tieba.background.upload.f(this) {
                final /* synthetic */ AbstractWebActivity b;

                public void a(List<Long> list, List<Long> list2, HashMap<String, LocalMedia> hashMap) {
                    JSONObject jSONObject = new JSONObject();
                    Object jSONArray = new JSONArray();
                    if (!(list == null || list.isEmpty())) {
                        for (Long longValue : list) {
                            long longValue2 = longValue.longValue();
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("type", (Object) "video");
                            jSONObject2.put("id", Long.valueOf(longValue2));
                            jSONArray.add(jSONObject2);
                        }
                    }
                    if (!(list2 == null || list2.isEmpty())) {
                        for (Long longValue3 : list2) {
                            longValue2 = longValue3.longValue();
                            jSONObject2 = new JSONObject();
                            jSONObject2.put("type", (Object) "img");
                            jSONObject2.put("id", Long.valueOf(longValue2));
                            jSONArray.add(jSONObject2);
                        }
                    }
                    jSONObject.put("ret", Integer.valueOf(1));
                    jSONObject.put("list", jSONArray);
                    if (eVar != null && eVar.c()) {
                        eVar.b();
                    }
                    if (this.b.c != null) {
                        this.b.c.a(jSONObject.toJSONString());
                        this.b.c = null;
                    }
                }

                public void a(String str) {
                    if (eVar != null && eVar.c()) {
                        eVar.b();
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("ret", Integer.valueOf(-1));
                    jSONObject.put("errmsg", (Object) str);
                    if (this.b.c != null) {
                        this.b.c.a(jSONObject.toJSONString());
                        this.b.c = null;
                    }
                    g.a("上传图片失败");
                }
            });
        }
    }
}
