package cn.xiaochuankeji.tieba.ui.my.licence;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;

public class LicenceActivity extends a implements NavigationBar.a {
    @BindView
    NavigationBar navBar;

    public static void a(Context context) {
        context.startActivity(new Intent(context, LicenceActivity.class));
    }

    protected int a() {
        return R.layout.activity_licence;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        this.navBar.setTitle("开放源代码许可");
        this.navBar.setListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.container, a.b(), "licence").commit();
    }

    public void r() {
        onBackPressed();
    }

    public void s() {
    }

    public void t() {
    }

    public void u() {
    }
}
