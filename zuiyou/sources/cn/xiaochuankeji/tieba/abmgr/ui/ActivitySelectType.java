package cn.xiaochuankeji.tieba.abmgr.ui;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.abmgr.a.b;
import cn.xiaochuankeji.tieba.abmgr.data.RequireStyle;
import cn.xiaochuankeji.tieba.abmgr.data.Requirement;
import cn.xiaochuankeji.tieba.abmgr.ui.a.c;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;

public class ActivitySelectType extends h {
    private a d;
    private Unbinder e;
    @BindView
    NavigationBar navigationBar;
    @BindView
    RecyclerView typeList;

    protected int a() {
        return R.layout.activity_select_type;
    }

    protected void i_() {
        this.e = ButterKnife.a((Activity) this);
        this.navigationBar.setTitle("选择需求风格");
        j();
        k();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.e.a();
    }

    private void j() {
        this.d = new a(this);
        this.d.a(new c(this) {
            final /* synthetic */ ActivitySelectType a;

            {
                this.a = r1;
            }

            public void onClick(Requirement requirement, RequireStyle requireStyle) {
                b.a().a(requirement, requireStyle);
                this.a.k();
            }
        });
        this.typeList.setLayoutManager(new LinearLayoutManager(this));
        this.typeList.setAdapter(this.d);
        this.typeList.setAnimation(null);
    }

    private void k() {
        this.d.a(c.a());
    }
}
