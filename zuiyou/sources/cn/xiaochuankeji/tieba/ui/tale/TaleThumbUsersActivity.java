package cn.xiaochuankeji.tieba.ui.tale;

import android.app.Activity;
import android.arch.lifecycle.q;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.tale.TaleService;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.EmptyJson;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.tale.viewmodel.TaleUserModel;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import com.alibaba.fastjson.JSONObject;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.a.h;
import com.scwang.smartrefresh.layout.e.c;
import rx.e;

public class TaleThumbUsersActivity extends a implements cn.xiaochuankeji.tieba.ui.tale.viewmodel.a, NavigationBar.a {
    private TaleThumbAdapter a = new TaleThumbAdapter();
    private TaleUserModel b;
    @BindView
    NavigationBar navBar;
    @BindView
    RecyclerView recycler;
    @BindView
    SmartRefreshLayout refresh;

    public static void a(Context context, long j, boolean z, String str) {
        a(context, j, 1, z, str);
    }

    public static void a(Context context, long j, int i, boolean z, String str) {
        Intent intent = new Intent(context, TaleThumbUsersActivity.class);
        intent.putExtra("_src", str);
        intent.putExtra("_id", j);
        intent.putExtra("_state", z);
        intent.putExtra("_action", i);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.activity_tale_users;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("_src");
        long longExtra = intent.getLongExtra("_id", 0);
        boolean booleanExtra = intent.getBooleanExtra("_state", true);
        final int intExtra = intent.getIntExtra("_action", 0);
        this.navBar.setTitle(booleanExtra ? "顶过的人" : "踩过的人");
        this.navBar.setOptionText(booleanExtra ? "取消顶" : "取消踩");
        this.navBar.setListener(this);
        LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.recycler.setLayoutManager(linearLayoutManager);
        this.recycler.setAdapter(this.a);
        this.b = (TaleUserModel) q.a((FragmentActivity) this).a(TaleUserModel.class);
        this.b.a((cn.xiaochuankeji.tieba.ui.tale.viewmodel.a) this);
        this.b.a(this.a, stringExtra, longExtra, booleanExtra);
        this.b.a(intExtra);
        this.refresh.a(new c(this) {
            final /* synthetic */ TaleThumbUsersActivity b;

            public void a_(h hVar) {
                this.b.b.a(intExtra);
            }
        });
        this.refresh.a(new com.scwang.smartrefresh.layout.e.a(this) {
            final /* synthetic */ TaleThumbUsersActivity b;

            public void a(h hVar) {
                this.b.b.b(intExtra);
            }
        });
    }

    public void a(boolean z, String str, int i, boolean z2) {
        if (!(this.refresh == null || isFinishing())) {
            this.refresh.m();
            this.refresh.a(z2);
        }
        if (!z) {
            g.a(str);
        }
    }

    public void a(boolean z, boolean z2, String str) {
        if (!(this.refresh == null || isFinishing())) {
            if (z2) {
                this.refresh.n();
            } else {
                this.refresh.o();
            }
        }
        if (!z) {
            g.a(str);
        }
    }

    public void r() {
        onBackPressed();
    }

    public void s() {
        this.navBar.getOptionText().setEnabled(false);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("_src");
        final long longExtra = intent.getLongExtra("_id", 0);
        final boolean booleanExtra = intent.getBooleanExtra("_state", true);
        final int intExtra = intent.getIntExtra("_action", 0);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("from", stringExtra);
        jSONObject.put("id", Long.valueOf(longExtra));
        jSONObject.put("op", booleanExtra ? "up" : "down");
        jSONObject.put("value", Integer.valueOf(-1));
        if (intExtra == 1) {
            ((TaleService) cn.xiaochuankeji.tieba.network.c.b(TaleService.class)).taleThumb(jSONObject).a(rx.a.b.a.a()).a(new e<EmptyJson>(this) {
                final /* synthetic */ TaleThumbUsersActivity d;

                public /* synthetic */ void onNext(Object obj) {
                    a((EmptyJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    if (th instanceof ClientErrorException) {
                        g.b(th.getMessage());
                    } else {
                        g.b("取消失败");
                    }
                    this.d.navBar.getOptionText().setEnabled(true);
                }

                public void a(EmptyJson emptyJson) {
                    g.b("取消成功");
                    org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.b.e(booleanExtra ? 2 : 3, longExtra, intExtra));
                    this.d.finish();
                }
            });
        } else {
            ((TaleService) cn.xiaochuankeji.tieba.network.c.b(TaleService.class)).taleCommentThumb(jSONObject).a(rx.a.b.a.a()).a(new e<EmptyJson>(this) {
                final /* synthetic */ TaleThumbUsersActivity d;

                public /* synthetic */ void onNext(Object obj) {
                    a((EmptyJson) obj);
                }

                public void onCompleted() {
                }

                public void onError(Throwable th) {
                    if (th instanceof ClientErrorException) {
                        g.b(th.getMessage());
                    } else {
                        g.b("取消失败");
                    }
                    this.d.navBar.getOptionText().setEnabled(true);
                }

                public void a(EmptyJson emptyJson) {
                    g.b("取消成功");
                    org.greenrobot.eventbus.c.a().d(new cn.xiaochuankeji.tieba.b.e(booleanExtra ? 2 : 3, longExtra, intExtra));
                    this.d.finish();
                }
            });
        }
    }

    public void t() {
    }

    public void u() {
    }
}
