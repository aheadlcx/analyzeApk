package cn.xiaochuankeji.tieba.ui.tale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.AppBarLayout.OnOffsetChangedListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.dreamtobe.kpswitch.widget.KPSwitchPanelFrameLayout;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.tale.TaleService;
import cn.xiaochuankeji.tieba.api.user.UserService;
import cn.xiaochuankeji.tieba.background.tale.TaleDetail;
import cn.xiaochuankeji.tieba.background.upload.j;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.json.tale.TaleArticleIdsJson;
import cn.xiaochuankeji.tieba.json.tale.TaleCommentJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.member.MemberDetailActivity;
import cn.xiaochuankeji.tieba.ui.post.b.d;
import cn.xiaochuankeji.tieba.ui.publish.e;
import cn.xiaochuankeji.tieba.ui.selectlocalmedia.LocalMedia;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.b;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import cn.xiaochuankeji.tieba.ui.widget.customtv.ExpandableTextView.f;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.c.a.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.l;

public class TaleDetailActivity extends cn.xiaochuankeji.tieba.ui.base.a implements cn.xiaochuankeji.tieba.ui.post.b.d.a, cn.xiaochuankeji.tieba.ui.publish.e.a, b {
    private d a;
    @BindView
    AppBarLayout appbar;
    private String b;
    @BindView
    View back;
    private j c;
    private boolean d = false;
    private TaleDetail e;
    private c f;
    private e g;
    private int h;
    private Handler i = new Handler();
    @BindView
    FrameLayout input_container;
    @BindView
    WebImageView iv_avatar;
    private Runnable j;
    private long k;
    private long l;
    @BindView
    View ll_title;
    private TaleArticleIdsJson m;
    private cn.xiaochuankeji.tieba.api.tale.a n;
    private int o;
    private IndicatorLayoutManager p;
    @BindView
    KPSwitchPanelFrameLayout panel_root;
    private IndicatorLayoutManager q;
    private int r;
    @BindView
    RecyclerView recycler_view;
    @BindView
    RecyclerView recycler_view_title;
    private float s;
    private int t = 1;
    @BindView
    TextView theme_count;
    @BindView
    TextView theme_title;
    @BindView
    TextView tv_title;
    private a u;
    private int v;
    @BindView
    ViewPager viewpager;
    private HashMap<Long, b> w = new HashMap();
    private HashMap<Long, TaleDetail> x = new HashMap();
    private cn.xiaochuankeji.tieba.background.utils.a.b y;
    private HashMap<Long, f> z = new HashMap();

    class a extends FragmentPagerAdapter {
        final /* synthetic */ TaleDetailActivity a;

        public a(TaleDetailActivity taleDetailActivity, FragmentManager fragmentManager) {
            this.a = taleDetailActivity;
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            Fragment b = TaleDetailFragment.b();
            if (i == 0) {
                b.a(this.a.l, this.a.v, this.a.b);
            } else {
                b.a(this.a.m.articleIds[i], 0, "scroll");
            }
            return b;
        }

        public int getCount() {
            return this.a.t;
        }
    }

    public static void a(Context context, String str, long j) {
        Intent intent = new Intent(context, TaleDetailActivity.class);
        intent.putExtra("_src", str);
        intent.putExtra("_id", j);
        context.startActivity(intent);
    }

    public static void a(Context context, String str, long j, boolean z) {
        Intent intent = new Intent(context, TaleDetailActivity.class);
        intent.putExtra("_src", str);
        intent.putExtra("_id", j);
        intent.putExtra("scroll", z);
        context.startActivity(intent);
    }

    public static void a(Context context, String str, long j, int i, long j2, long j3) {
        Intent intent = new Intent(context, TaleDetailActivity.class);
        intent.putExtra("_src", str);
        intent.putExtra("_id", j);
        intent.putExtra("type", i);
        intent.putExtra("start_id", j2);
        intent.putExtra("comment_id", j3);
        intent.putExtra("scroll", true);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_tale_detail;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        this.y = cn.xiaochuankeji.tieba.background.utils.a.a.a().a(this);
        this.n = new cn.xiaochuankeji.tieba.api.tale.a();
        Intent intent = getIntent();
        this.l = intent.getLongExtra("_id", 0);
        this.k = this.l;
        this.b = intent.getStringExtra("_src");
        this.v = intent.getIntExtra("type", 0);
        this.appbar.post(new Runnable(this) {
            final /* synthetic */ TaleDetailActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.r = this.a.appbar.getHeight();
                this.a.s = (float) (this.a.r / 3);
            }
        });
        this.appbar.addOnOffsetChangedListener(new OnOffsetChangedListener(this) {
            final /* synthetic */ TaleDetailActivity a;

            {
                this.a = r1;
            }

            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (((float) (this.a.r - Math.abs(i))) < this.a.s) {
                    this.a.iv_avatar.setVisibility(8);
                    this.a.ll_title.setAlpha(1.0f - (((float) (this.a.r - Math.abs(i))) / this.a.s));
                    this.a.ll_title.setVisibility(0);
                    return;
                }
                this.a.iv_avatar.setVisibility(0);
                this.a.ll_title.setVisibility(8);
            }
        });
        this.a = new d(this, this);
        this.a.i();
        this.input_container.addView(this.a.f_());
        this.iv_avatar.setVisibility(4);
        this.g = new e(this, this);
        j();
    }

    private void j() {
        this.n.a(this.b, this.k).a(rx.a.b.a.a()).b(new rx.j<TaleArticleIdsJson>(this) {
            final /* synthetic */ TaleDetailActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((TaleArticleIdsJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.a.tv_title.setTextSize(2, 16.0f);
                this.a.recycler_view_title.setVisibility(8);
                this.a.recycler_view.setVisibility(8);
            }

            public void a(TaleArticleIdsJson taleArticleIdsJson) {
                this.a.m = taleArticleIdsJson;
                if (taleArticleIdsJson.articleIds.length > 1 && taleArticleIdsJson.articleIds.length < 6) {
                    this.a.t = taleArticleIdsJson.articleIds.length;
                    this.a.tv_title.setTextSize(2, 13.0f);
                    this.a.b(taleArticleIdsJson.articleIds.length);
                    LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 1;
                    layoutParams.setMargins(0, cn.xiaochuankeji.tieba.ui.utils.e.a(4.0f), 0, 0);
                    this.a.recycler_view_title.setLayoutParams(layoutParams);
                    layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.gravity = 1;
                    layoutParams.setMargins(0, 0, 0, cn.xiaochuankeji.tieba.ui.utils.e.a(6.0f));
                    this.a.recycler_view.setLayoutParams(layoutParams);
                } else if (taleArticleIdsJson.articleIds.length > 5) {
                    this.a.t = taleArticleIdsJson.articleIds.length;
                    this.a.b(taleArticleIdsJson.articleIds.length);
                    this.a.tv_title.setTextSize(2, 13.0f);
                } else if (taleArticleIdsJson.articleIds.length < 2) {
                    this.a.tv_title.setTextSize(2, 16.0f);
                    this.a.recycler_view_title.setVisibility(8);
                    this.a.recycler_view.setVisibility(8);
                }
                this.a.u = new a(this.a, this.a.getSupportFragmentManager());
                this.a.viewpager.setAdapter(this.a.u);
            }
        });
    }

    private void b(int i) {
        this.p = new IndicatorLayoutManager(this);
        this.p.setOrientation(0);
        this.q = new IndicatorLayoutManager(this);
        this.q.setOrientation(0);
        this.recycler_view.setLayoutManager(this.p);
        this.f = new c(i);
        this.recycler_view.setAdapter(this.f);
        this.recycler_view_title.setLayoutManager(this.q);
        this.recycler_view_title.setAdapter(this.f);
        this.viewpager.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ TaleDetailActivity a;

            {
                this.a = r1;
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                this.a.f.a(i);
                int findFirstVisibleItemPosition = this.a.p.findFirstVisibleItemPosition();
                if (this.a.p.findLastVisibleItemPosition() <= i) {
                    this.a.recycler_view.smoothScrollToPosition(i + 1);
                    this.a.recycler_view_title.smoothScrollToPosition(i + 1);
                }
                if (findFirstVisibleItemPosition >= i && i > 0) {
                    this.a.recycler_view.smoothScrollToPosition(i - 1);
                    this.a.recycler_view_title.smoothScrollToPosition(i - 1);
                }
                if (!(this.a.m == null || this.a.m.articleIds == null)) {
                    this.a.k = this.a.m.articleIds[i];
                }
                this.a.a.h();
                if (this.a.g()) {
                    com.c.a.e b = c.b(this.a);
                    if (b == null) {
                        return;
                    }
                    if (i == 0) {
                        b.b(true);
                    } else {
                        b.b(false);
                    }
                }
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    protected void onResume() {
        super.onResume();
        if ("index".equals(this.b)) {
            this.y.b();
        }
    }

    protected void onPause() {
        super.onPause();
        if ("index".equals(this.b)) {
            this.y.d();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if ("index".equals(this.b)) {
            long j;
            Map hashMap = new HashMap();
            hashMap.put("remain_time", Integer.valueOf(this.y.e() / 1000));
            if (this.e != null) {
                j = this.e.themeId;
            } else {
                j = 0;
            }
            cn.xiaochuankeji.tieba.background.utils.monitor.a.b.a().a("view", "theme", j, 0, "index", hashMap);
        }
        if (this.a != null) {
            this.a.g();
        }
        this.w.clear();
    }

    public b a(long j) {
        return (b) this.w.get(Long.valueOf(j));
    }

    public void a(long j, b bVar) {
        if (this.w.size() <= 100) {
            this.w.put(Long.valueOf(j), bVar);
        }
    }

    public HashMap<Long, f> e() {
        return this.z;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 32 && this.a != null) {
            this.a.a((ArrayList) cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.b(intent));
        }
    }

    public void onBackPressed() {
        if (!this.a.c()) {
            super.onBackPressed();
        }
    }

    @OnClick
    public void onClick(View view) {
        TaleDetail taleDetail = (TaleDetail) this.x.get(Long.valueOf(this.k));
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                return;
            case R.id.tv_title:
            case R.id.theme_title:
            case R.id.theme_count:
                if (taleDetail == null) {
                    return;
                }
                if (TextUtils.isEmpty(this.b) || !"theme".equals(this.b)) {
                    TaleListActivity.a((Context) this, "article", taleDetail.themeId, null);
                    return;
                } else {
                    finish();
                    return;
                }
            case R.id.iv_share:
                if (taleDetail != null) {
                    Object obj = cn.xiaochuankeji.tieba.background.a.g().c() == taleDetail.author.id ? 1 : null;
                    SDBottomSheet sDBottomSheet = new SDBottomSheet(this, this);
                    ArrayList arrayList = new ArrayList();
                    String str = "举报";
                    if (obj != null) {
                        arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_delete, "删除", 9));
                    } else {
                        arrayList.add(new SDBottomSheet.c(R.drawable.icon_option_report, str, 12));
                    }
                    sDBottomSheet.a(sDBottomSheet.c(), arrayList);
                    sDBottomSheet.b();
                    return;
                }
                return;
            case R.id.iv_avatar:
                if (taleDetail != null) {
                    MemberDetailActivity.a((Context) this, taleDetail.theme.author.id);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void a(final int i) {
        final TaleDetail taleDetail = (TaleDetail) this.x.get(Long.valueOf(this.k));
        if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
            final ShareDataModel articleShareDataModel = new ArticleShareDataModel(taleDetail, i);
            articleShareDataModel.prepareData(new cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel.a(this) {
                final /* synthetic */ TaleDetailActivity d;

                public void a() {
                    cn.xiaochuankeji.tieba.background.utils.share.b.a().a(this.d, articleShareDataModel);
                    cn.xiaochuankeji.tieba.background.i.a.a("tale_article", taleDetail.id, "article", (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(i)), articleShareDataModel.getABTestId());
                }
            });
        }
        if (i == 18) {
            a(taleDetail);
        }
        if (i == 12) {
            k();
        }
        if (i == 9) {
            cn.xiaochuankeji.tieba.ui.widget.f.a("提示", "确定要删除？", this, new cn.xiaochuankeji.tieba.ui.widget.f.a(this) {
                final /* synthetic */ TaleDetailActivity b;

                public void a(boolean z) {
                    if (z) {
                        this.b.n.a(taleDetail.id).a(rx.a.b.a.a()).b(new rx.j<String>(this) {
                            final /* synthetic */ AnonymousClass7 a;

                            {
                                this.a = r1;
                            }

                            public /* synthetic */ void onNext(Object obj) {
                                a((String) obj);
                            }

                            public void onCompleted() {
                            }

                            public void onError(Throwable th) {
                            }

                            public void a(String str) {
                                this.a.b.finish();
                            }
                        });
                    }
                }
            });
        }
    }

    private void k() {
        LinkedHashMap m = cn.xiaochuankeji.tieba.background.utils.c.a.c().m();
        if (m.size() != 0) {
            SDCheckSheet sDCheckSheet = new SDCheckSheet(this, new cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet.a(this) {
                final /* synthetic */ TaleDetailActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    if (i == -123) {
                        CustomReportReasonActivity.a(this.a, 0, this.a.k, this.a.o, "tale_article");
                        return;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", Long.valueOf(this.a.k));
                    jSONObject.put("type", "tale_article");
                    jSONObject.put("reason", Integer.valueOf(i));
                    ((UserService) cn.xiaochuankeji.tieba.network.c.b(UserService.class)).reportUser(jSONObject).a(rx.a.b.a.a()).a(new rx.e<Object>(this) {
                        final /* synthetic */ AnonymousClass8 a;

                        {
                            this.a = r1;
                        }

                        public void onCompleted() {
                        }

                        public void onError(Throwable th) {
                            g.a(th == null ? "举报失败" : th.getMessage());
                        }

                        public void onNext(Object obj) {
                            g.a("已举报");
                        }
                    });
                }
            });
            int i = 0;
            for (Entry entry : m.entrySet()) {
                int i2;
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                int parseInt = Integer.parseInt(str);
                int i3 = i + 1;
                String trim = str2.trim();
                if (trim.equals("其他")) {
                    this.o = parseInt;
                    i2 = -123;
                } else {
                    i2 = parseInt;
                }
                if (i3 == m.size()) {
                    sDCheckSheet.a(trim, i2, true);
                } else {
                    sDCheckSheet.a(trim, i2, false);
                }
                i = i3;
            }
            sDCheckSheet.b();
        }
    }

    public void a(TaleDetail taleDetail) {
        cn.xiaochuankeji.tieba.ui.utils.d.a(taleDetail.theme.title + "(分享自@最右APP)看详情戳链接→_→" + cn.xiaochuankeji.tieba.background.utils.d.a.c(taleDetail.id));
        g.a("复制成功");
    }

    public void a(long j, String str, String str2, cn.xiaochuankeji.tieba.background.post.a aVar, ArrayList<LocalMedia> arrayList) {
        if (!this.d) {
            g.a("帖子未加载成功");
        } else if (TextUtils.isEmpty(str) && arrayList.isEmpty()) {
            g.a("评论不能为空");
        } else if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "tale_detail", 4, 1112)) {
            a(j, str, Long.parseLong(str2), (ArrayList) arrayList);
        }
    }

    public void a(ArrayList<LocalMedia> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.e(this, 32);
        } else {
            cn.xiaochuankeji.tieba.ui.selectlocalmedia.b.d(this, 32, arrayList);
        }
    }

    private void a(long j, String str, long j2, final ArrayList<LocalMedia> arrayList) {
        if (this.k != 0) {
            final JSONObject jSONObject = new JSONObject();
            jSONObject.put("article_id", Long.valueOf(this.k));
            jSONObject.put("localid", Long.valueOf(j2));
            if (j > 0) {
                jSONObject.put("ref_id", Long.valueOf(j));
            }
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("txt", str);
            }
            if (arrayList.isEmpty()) {
                this.g.a("正在发评论", 30, this.h);
                this.g.a();
                a(jSONObject);
                return;
            }
            this.g.a("正在上传", arrayList.size(), 0);
            this.g.a();
            this.c = new j();
            this.c.a(arrayList, "tale", new cn.xiaochuankeji.tieba.background.upload.b(this) {
                final /* synthetic */ TaleDetailActivity b;

                public void a(long j, long j2, int i) {
                    if (i < arrayList.size() && i >= 0 && arrayList.size() > 0 && i < arrayList.size() && i >= 0) {
                        int i2 = ((LocalMedia) arrayList.get(i)).type;
                        StringBuilder stringBuilder = new StringBuilder("正在上传");
                        if (1 == i2) {
                            stringBuilder.append("视频");
                        } else {
                            stringBuilder.append("图片");
                        }
                        stringBuilder.append((i + 1) + "/" + arrayList.size());
                        this.b.g.a(stringBuilder.toString(), (int) j, (int) j2);
                    }
                }
            }, new cn.xiaochuankeji.tieba.background.upload.f(this) {
                final /* synthetic */ TaleDetailActivity b;

                public void a(List<Long> list, List<Long> list2, HashMap<String, LocalMedia> hashMap) {
                    if (list2 == null || list2.isEmpty()) {
                        this.b.a(jSONObject);
                        return;
                    }
                    JSONArray jSONArray = new JSONArray();
                    for (int i = 0; i < list2.size(); i++) {
                        jSONArray.add(list2.get(i));
                    }
                    jSONObject.put("imgs", jSONArray);
                    this.b.a(jSONObject);
                }

                public void a(String str) {
                    if (this.b.g != null && this.b.g.c()) {
                        this.b.g.b();
                    }
                    g.a("上传图片失败");
                }
            });
        }
    }

    private void a(JSONObject jSONObject) {
        ((TaleService) cn.xiaochuankeji.tieba.network.c.b(TaleService.class)).createComment(jSONObject).a(rx.a.b.a.a()).a(new rx.e<TaleCommentJson>(this) {
            final /* synthetic */ TaleDetailActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onNext(Object obj) {
                a((TaleCommentJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                if (this.a.g != null && this.a.g.c()) {
                    this.a.g.b();
                }
                th.printStackTrace();
                if (th instanceof ClientErrorException) {
                    g.a("网络不给力哦~");
                } else {
                    g.a(th.getMessage());
                }
            }

            public void a(final TaleCommentJson taleCommentJson) {
                this.a.j = new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 b;

                    public void run() {
                        this.b.a.h = this.b.a.h + 1;
                        if (this.b.a.h <= 30) {
                            this.b.a.g.a("正在发评论", 30, this.b.a.h);
                            this.b.a.i.post(this.b.a.j);
                            return;
                        }
                        if (this.b.a.g != null && this.b.a.g.c()) {
                            this.b.a.g.b();
                        }
                        this.b.a.h = 0;
                        g.a("评论发送成功");
                        if (this.b.a.a != null) {
                            this.b.a.a.h();
                            org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.ui.tale.a.a(taleCommentJson, this.b.a.k));
                        }
                    }
                };
                this.a.i.post(this.a.j);
            }
        });
    }

    @l(a = ThreadMode.MAIN)
    public void replyComment(cn.xiaochuankeji.tieba.b.d dVar) {
        if (this.a != null && dVar.a != null) {
            this.a.a(dVar.a.id, "回复 " + cn.xiaochuankeji.tieba.ui.utils.d.b(dVar.a.author.name));
        }
    }

    @l(a = ThreadMode.MAIN)
    public void finishActivity(cn.xiaochuankeji.tieba.ui.tale.a.b bVar) {
        finish();
    }

    public void b(TaleDetail taleDetail) {
        this.x.put(Long.valueOf(taleDetail.id), taleDetail);
        if (this.e == null) {
            this.e = taleDetail;
            this.iv_avatar.setVisibility(0);
            this.iv_avatar.setWebImage(cn.xiaochuankeji.tieba.background.f.b.a(taleDetail.theme.author.id, taleDetail.theme.author.avatar));
            this.tv_title.setText(taleDetail.theme.title);
            this.theme_title.setText(taleDetail.theme.title);
            this.theme_count.setText(String.format(Locale.SIMPLIFIED_CHINESE, "%d 跟帖 >", new Object[]{Long.valueOf(taleDetail.theme.articleCnt)}));
            this.d = true;
        }
    }

    public TaleDetail f(long j) {
        return (TaleDetail) this.x.get(Long.valueOf(j));
    }

    public void l_() {
        if (this.g.c()) {
            this.g.b();
        }
        if (this.c != null) {
            this.c.a();
        }
    }
}
