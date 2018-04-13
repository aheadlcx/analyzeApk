package cn.xiaochuankeji.tieba.ui.my;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class SettingPushActivity_ViewBinding implements Unbinder {
    private SettingPushActivity b;
    private View c;
    private View d;
    private View e;

    @UiThread
    public SettingPushActivity_ViewBinding(final SettingPushActivity settingPushActivity, View view) {
        this.b = settingPushActivity;
        View a = b.a(view, R.id.push_recommend, "field 'pushRecommend' and method 'push_recommend'");
        settingPushActivity.pushRecommend = (ImageView) b.c(a, R.id.push_recommend, "field 'pushRecommend'", ImageView.class);
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ SettingPushActivity_ViewBinding c;

            public void a(View view) {
                settingPushActivity.push_recommend();
            }
        });
        a = b.a(view, R.id.push_comment, "field 'pushComment' and method 'push_comment'");
        settingPushActivity.pushComment = (ImageView) b.c(a, R.id.push_comment, "field 'pushComment'", ImageView.class);
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ SettingPushActivity_ViewBinding c;

            public void a(View view) {
                settingPushActivity.push_comment();
            }
        });
        a = b.a(view, R.id.push_chat, "field 'pushChat' and method 'push_chat'");
        settingPushActivity.pushChat = (ImageView) b.c(a, R.id.push_chat, "field 'pushChat'", ImageView.class);
        this.e = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ SettingPushActivity_ViewBinding c;

            public void a(View view) {
                settingPushActivity.push_chat();
            }
        });
    }

    @CallSuper
    public void a() {
        SettingPushActivity settingPushActivity = this.b;
        if (settingPushActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        settingPushActivity.pushRecommend = null;
        settingPushActivity.pushComment = null;
        settingPushActivity.pushChat = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
    }
}
