package cn.xiaochuankeji.tieba.ui.tale;

import android.app.Activity;
import android.arch.lifecycle.q;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.tale.FollowPostThemeJson;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.recommend.b;
import cn.xiaochuankeji.tieba.ui.tale.viewmodel.TaleInvisibleModel;
import cn.xiaochuankeji.tieba.webview.WebActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;

public class TaleInvisibleActivity extends a implements b, TaleInvisibleModel.a {
    private TaleInvisibleModel a;
    private String b;
    private long c;
    private TaleListAdapter d;
    @BindView
    RecyclerView recycler_view;
    @BindView
    SmartRefreshLayout refreshLayout;

    public static void a(Context context, String str, long j) {
        Intent intent = new Intent(context, TaleInvisibleActivity.class);
        intent.putExtra("id", j);
        intent.putExtra("from", str);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_tale_invisible;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.recycler_view.setLayoutManager(linearLayoutManager);
        this.d = new TaleListAdapter(this);
        this.recycler_view.setAdapter(this.d);
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_tale_invisible_header, this.recycler_view, false);
        inflate.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ TaleInvisibleActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                WebActivity.a(this.a, cn.xiaochuan.jsbridge.b.a(null, cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/theme/describe")));
            }
        });
        this.d.a(inflate);
        this.a = (TaleInvisibleModel) q.a((FragmentActivity) this).a(TaleInvisibleModel.class);
        this.a.a(this.d);
        this.b = getIntent().getStringExtra("from");
        this.c = getIntent().getLongExtra("id", 0);
        this.a.a(this.b, this.c, null, 1);
        this.a.a((b) this, (TaleInvisibleModel.a) this);
        this.refreshLayout.b(false);
        this.refreshLayout.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ TaleInvisibleActivity a;

            {
                this.a = r1;
            }

            public void a(h hVar) {
                this.a.a.a(this.a.b, this.a.c, 1);
            }
        });
    }

    @OnClick
    public void onClick(View view) {
        finish();
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
        this.d.a(followPostThemeJson);
    }
}
