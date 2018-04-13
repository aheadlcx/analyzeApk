package cn.xiaochuankeji.tieba.ui.tale;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.arch.lifecycle.q;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.user.UserService;
import cn.xiaochuankeji.tieba.background.tale.TaleTheme;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.newshare.ShareDataModel;
import cn.xiaochuankeji.tieba.json.tale.FollowPostArticleJson;
import cn.xiaochuankeji.tieba.json.tale.FollowPostThemeJson;
import cn.xiaochuankeji.tieba.ui.CustomReportReasonActivity;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.recommend.b;
import cn.xiaochuankeji.tieba.ui.tale.viewmodel.TaleListModel;
import cn.xiaochuankeji.tieba.ui.utils.d;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet;
import cn.xiaochuankeji.tieba.ui.widget.SDBottomSheet.c;
import cn.xiaochuankeji.tieba.ui.widget.SDCheckSheet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TaleListActivity extends a implements b, TaleListModel.a, SDBottomSheet.b {
    private TaleListAdapter a;
    private TaleListModel b;
    @BindView
    View back;
    private String c;
    private long d;
    private String e;
    private AppCompatTextView f;
    private AppCompatTextView g;
    private FollowPostThemeJson h;
    private int i;
    @BindView
    ImageView iv_create;
    private boolean j;
    private cn.xiaochuankeji.tieba.background.utils.a.b k;
    @BindView
    View ll_title;
    @BindView
    RecyclerView recycler_view;
    @BindView
    SmartRefreshLayout refreshLayout;
    @BindView
    TextView tv_count;
    @BindView
    TextView tv_title;

    public static void a(Context context, String str, long j, String str2) {
        Intent intent = new Intent(context, TaleListActivity.class);
        intent.putExtra("id", j);
        intent.putExtra("from", str);
        intent.putExtra("array", str2);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_follow_list;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        this.k = cn.xiaochuankeji.tieba.background.utils.a.a.a().a(this);
        LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.recycler_view.setLayoutManager(linearLayoutManager);
        this.a = new TaleListAdapter(this);
        this.recycler_view.setAdapter(this.a);
        this.b = (TaleListModel) q.a((FragmentActivity) this).a(TaleListModel.class);
        this.b.a(this.a);
        this.c = getIntent().getStringExtra("from");
        this.d = getIntent().getLongExtra("id", 0);
        this.e = getIntent().getStringExtra("array");
        this.b.a(this.c, this.d, JSONArray.parseArray(this.e));
        this.b.a((b) this, (TaleListModel.a) this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_tale_list_header, this.recycler_view, false);
        this.f = (AppCompatTextView) inflate.findViewById(R.id.theme_title);
        this.g = (AppCompatTextView) inflate.findViewById(R.id.theme_count);
        this.a.a(inflate);
        j();
        this.refreshLayout.b(false);
        this.refreshLayout.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ TaleListActivity a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.b.a(this.a.c, this.a.d);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        if ("index".equals(this.c)) {
            this.k.b();
        }
    }

    protected void onPause() {
        super.onPause();
        if ("index".equals(this.c)) {
            this.k.d();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if ("index".equals(this.c)) {
            Map hashMap = new HashMap();
            hashMap.put("remain_time", Integer.valueOf(this.k.e() / 1000));
            cn.xiaochuankeji.tieba.background.utils.monitor.a.b.a().a("view", "theme", this.d, 0, "index", hashMap);
        }
    }

    private void j() {
        final int applyDimension = (int) TypedValue.applyDimension(1, 60.0f, getResources().getDisplayMetrics());
        this.recycler_view.addOnScrollListener(new OnScrollListener(this) {
            final /* synthetic */ TaleListActivity b;

            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (this.b.recycler_view.computeVerticalScrollOffset() >= applyDimension) {
                    this.b.ll_title.setVisibility(0);
                } else {
                    this.b.ll_title.setVisibility(4);
                }
                int a = e.a(27.0f) + this.b.iv_create.getHeight();
                if (i2 > 0 && !this.b.j) {
                    this.b.iv_create.animate().translationY((float) a).setDuration(250).setListener(new AnimatorListener(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onAnimationStart(Animator animator) {
                        }

                        public void onAnimationEnd(Animator animator) {
                            this.a.b.j = true;
                        }

                        public void onAnimationCancel(Animator animator) {
                        }

                        public void onAnimationRepeat(Animator animator) {
                        }
                    });
                } else if (i2 < 0 && this.b.j) {
                    this.b.iv_create.animate().translationY(0.0f).setDuration(250).setListener(new AnimatorListener(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onAnimationStart(Animator animator) {
                        }

                        public void onAnimationEnd(Animator animator) {
                            this.a.b.j = false;
                        }

                        public void onAnimationCancel(Animator animator) {
                        }

                        public void onAnimationRepeat(Animator animator) {
                        }
                    });
                }
            }
        });
    }

    @OnClick
    public void onClick(View view) {
        if (view.getId() == R.id.back) {
            onBackPressed();
        }
        if (this.h != null) {
            switch (view.getId()) {
                case R.id.tv_title:
                    return;
                case R.id.iv_share:
                    SDBottomSheet sDBottomSheet = new SDBottomSheet(this, this);
                    ArrayList arrayList = new ArrayList();
                    if ((cn.xiaochuankeji.tieba.background.a.g().c() == this.h.author.id ? 1 : null) == null) {
                        arrayList.add(new c(R.drawable.icon_option_report, "举报", 12));
                    }
                    sDBottomSheet.a(sDBottomSheet.c(), arrayList);
                    sDBottomSheet.b();
                    return;
                case R.id.iv_create:
                    if (cn.xiaochuankeji.tieba.ui.auth.a.a(this, "tale_list", 9)) {
                        TaleTheme taleTheme = new TaleTheme();
                        taleTheme.id = this.h.id;
                        taleTheme.ct = this.h.createTime;
                        taleTheme.title = this.h.title;
                        taleTheme.author = this.h.author;
                        taleTheme.articleCnt = this.h.postCount;
                        TaleCreateActivity.a(this, "theme", taleTheme, 4);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void a(int i) {
        if (i == 4 || i == 2 || i == 3 || i == 5 || i == 1) {
            b(i);
            return;
        }
        if (i == 18) {
            e();
        }
        if (i == 12) {
            k();
        }
    }

    private void k() {
        LinkedHashMap m = cn.xiaochuankeji.tieba.background.utils.c.a.c().m();
        if (m.size() != 0) {
            SDCheckSheet sDCheckSheet = new SDCheckSheet(this, new SDCheckSheet.a(this) {
                final /* synthetic */ TaleListActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    if (i == -123) {
                        CustomReportReasonActivity.a(this.a, 0, this.a.d, this.a.i, "tale_theme");
                        return;
                    }
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", Long.valueOf(this.a.d));
                    jSONObject.put("type", "tale_theme");
                    jSONObject.put("reason", Integer.valueOf(i));
                    ((UserService) cn.xiaochuankeji.tieba.network.c.b(UserService.class)).reportUser(jSONObject).a(rx.a.b.a.a()).a(new rx.e<Object>(this) {
                        final /* synthetic */ AnonymousClass3 a;

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
                    this.i = parseInt;
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

    public void e() {
        d.a(this.h.title + "(分享自@最右APP)看详情戳链接→_→" + cn.xiaochuankeji.tieba.background.utils.d.a.b(this.h.id));
        g.a("复制成功");
    }

    private void b(int i) {
        ShareDataModel themeShareDataModel = new ThemeShareDataModel(this.h, i);
        cn.xiaochuankeji.tieba.background.utils.share.b.a().a(this, themeShareDataModel);
        cn.xiaochuankeji.tieba.background.i.a.a("tale_theme", this.d, "theme", (String) cn.xiaochuankeji.tieba.d.d.b.get(Integer.valueOf(i)), themeShareDataModel.getABTestId());
    }

    public void a(boolean z, String str, int i, boolean z2) {
        if (!z) {
            g.a(str);
        }
        if (z2) {
            this.refreshLayout.a(true);
        } else {
            this.refreshLayout.a(false);
        }
    }

    public void a(boolean z, String str, boolean z2) {
        if (this.refreshLayout != null) {
            if (z2) {
                this.refreshLayout.n();
            } else {
                this.refreshLayout.o();
            }
        }
        if (!z) {
            g.a(str);
        }
    }

    public void a(FollowPostThemeJson followPostThemeJson) {
        this.h = followPostThemeJson;
        this.a.a(followPostThemeJson);
        this.f.setText(followPostThemeJson.title);
        this.g.setText(getString(R.string.follow_post_count_space, new Object[]{String.valueOf(followPostThemeJson.postCount)}));
        this.tv_count.setText(getString(R.string.follow_post_count_space, new Object[]{String.valueOf(followPostThemeJson.postCount)}));
        this.tv_title.setText(followPostThemeJson.title);
        this.iv_create.setVisibility(0);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (-1 == i2 && 4 == i) {
            this.a.a((FollowPostArticleJson) intent.getParcelableExtra("create_data"));
            this.recycler_view.scrollToPosition(0);
        }
    }
}
