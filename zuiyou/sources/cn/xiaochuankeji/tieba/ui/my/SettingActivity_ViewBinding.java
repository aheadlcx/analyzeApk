package cn.xiaochuankeji.tieba.ui.my;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class SettingActivity_ViewBinding implements Unbinder {
    private SettingActivity b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;

    @UiThread
    public SettingActivity_ViewBinding(final SettingActivity settingActivity, View view) {
        this.b = settingActivity;
        settingActivity.ivNew = (ImageView) b.b(view, R.id.ivNew, "field 'ivNew'", ImageView.class);
        settingActivity.betaSwitcher = (ImageView) b.b(view, R.id.laboratory_toggle, "field 'betaSwitcher'", ImageView.class);
        View a = b.a(view, R.id.flmemberBlock, "field 'flmemberBlock' and method 'memberBlock'");
        settingActivity.flmemberBlock = a;
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ SettingActivity_ViewBinding c;

            public void a(View view) {
                settingActivity.memberBlock();
            }
        });
        a = b.a(view, R.id.ivBack, "method 'back'");
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ SettingActivity_ViewBinding c;

            public void a(View view) {
                settingActivity.back();
            }
        });
        a = b.a(view, R.id.flBlock, "method 'flBlock'");
        this.e = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ SettingActivity_ViewBinding c;

            public void a(View view) {
                settingActivity.flBlock();
            }
        });
        a = b.a(view, R.id.tvPushSetting, "method 'tvPushSetting'");
        this.f = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ SettingActivity_ViewBinding c;

            public void a(View view) {
                settingActivity.tvPushSetting();
            }
        });
        a = b.a(view, R.id.tvPgc, "method 'tvPgc'");
        this.g = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ SettingActivity_ViewBinding c;

            public void a(View view) {
                settingActivity.tvPgc();
            }
        });
    }

    @CallSuper
    public void a() {
        SettingActivity settingActivity = this.b;
        if (settingActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        settingActivity.ivNew = null;
        settingActivity.betaSwitcher = null;
        settingActivity.flmemberBlock = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
        this.f.setOnClickListener(null);
        this.f = null;
        this.g.setOnClickListener(null);
        this.g = null;
    }
}
