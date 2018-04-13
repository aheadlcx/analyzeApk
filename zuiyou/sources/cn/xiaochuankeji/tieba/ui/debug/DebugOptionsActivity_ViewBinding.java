package cn.xiaochuankeji.tieba.ui.debug;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import cn.xiaochuankeji.tieba.widget.AchievementView;

public class DebugOptionsActivity_ViewBinding implements Unbinder {
    private DebugOptionsActivity b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;
    private View i;
    private View j;
    private View k;
    private View l;
    private View m;
    private View n;

    @UiThread
    public DebugOptionsActivity_ViewBinding(final DebugOptionsActivity debugOptionsActivity, View view) {
        this.b = debugOptionsActivity;
        View a = b.a(view, R.id.debug_show_layout, "field 'debug_show_layout' and method 'showLayout'");
        debugOptionsActivity.debug_show_layout = (Switch) b.c(a, R.id.debug_show_layout, "field 'debug_show_layout'", Switch.class);
        this.c = a;
        ((CompoundButton) a).setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ DebugOptionsActivity_ViewBinding b;

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                debugOptionsActivity.showLayout(compoundButton, z);
            }
        });
        a = b.a(view, R.id.https_switch, "field 'https_switch' and method 'https'");
        debugOptionsActivity.https_switch = (Switch) b.c(a, R.id.https_switch, "field 'https_switch'", Switch.class);
        this.d = a;
        ((CompoundButton) a).setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ DebugOptionsActivity_ViewBinding b;

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                debugOptionsActivity.https(compoundButton, z);
            }
        });
        a = b.a(view, R.id.enable_leak_canary, "field 'enable_leak_canary' and method 'enable_leak_canary'");
        debugOptionsActivity.enable_leak_canary = (Switch) b.c(a, R.id.enable_leak_canary, "field 'enable_leak_canary'", Switch.class);
        this.e = a;
        ((CompoundButton) a).setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ DebugOptionsActivity_ViewBinding b;

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                debugOptionsActivity.enable_leak_canary(compoundButton, z);
            }
        });
        debugOptionsActivity.navBar = (NavigationBar) b.b(view, R.id.navBar, "field 'navBar'", NavigationBar.class);
        debugOptionsActivity.achievementView = (AchievementView) b.b(view, R.id.achievementView, "field 'achievementView'", AchievementView.class);
        debugOptionsActivity.status = (TextView) b.b(view, R.id.status, "field 'status'", TextView.class);
        View a2 = b.a(view, R.id.release_api, "method 'event'");
        this.f = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ DebugOptionsActivity_ViewBinding c;

            public void a(View view) {
                debugOptionsActivity.event(view);
            }
        });
        a2 = b.a(view, R.id.debug_api, "method 'event'");
        this.g = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ DebugOptionsActivity_ViewBinding c;

            public void a(View view) {
                debugOptionsActivity.event(view);
            }
        });
        a2 = b.a(view, R.id.js_bridge, "method 'event'");
        this.h = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ DebugOptionsActivity_ViewBinding c;

            public void a(View view) {
                debugOptionsActivity.event(view);
            }
        });
        a2 = b.a(view, R.id.update_did, "method 'event'");
        this.i = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ DebugOptionsActivity_ViewBinding c;

            public void a(View view) {
                debugOptionsActivity.event(view);
            }
        });
        a2 = b.a(view, R.id.clear_message_db, "method 'event'");
        this.j = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ DebugOptionsActivity_ViewBinding c;

            public void a(View view) {
                debugOptionsActivity.event(view);
            }
        });
        a2 = b.a(view, R.id.clear_history_cache, "method 'event'");
        this.k = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ DebugOptionsActivity_ViewBinding c;

            public void a(View view) {
                debugOptionsActivity.event(view);
            }
        });
        a2 = b.a(view, R.id.setting, "method 'event'");
        this.l = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ DebugOptionsActivity_ViewBinding c;

            public void a(View view) {
                debugOptionsActivity.event(view);
            }
        });
        a2 = b.a(view, R.id.dev_setting, "method 'event'");
        this.m = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ DebugOptionsActivity_ViewBinding c;

            public void a(View view) {
                debugOptionsActivity.event(view);
            }
        });
        a2 = b.a(view, R.id.net_setting, "method 'event'");
        this.n = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ DebugOptionsActivity_ViewBinding c;

            public void a(View view) {
                debugOptionsActivity.event(view);
            }
        });
    }

    @CallSuper
    public void a() {
        DebugOptionsActivity debugOptionsActivity = this.b;
        if (debugOptionsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        debugOptionsActivity.debug_show_layout = null;
        debugOptionsActivity.https_switch = null;
        debugOptionsActivity.enable_leak_canary = null;
        debugOptionsActivity.navBar = null;
        debugOptionsActivity.achievementView = null;
        debugOptionsActivity.status = null;
        ((CompoundButton) this.c).setOnCheckedChangeListener(null);
        this.c = null;
        ((CompoundButton) this.d).setOnCheckedChangeListener(null);
        this.d = null;
        ((CompoundButton) this.e).setOnCheckedChangeListener(null);
        this.e = null;
        this.f.setOnClickListener(null);
        this.f = null;
        this.g.setOnClickListener(null);
        this.g = null;
        this.h.setOnClickListener(null);
        this.h = null;
        this.i.setOnClickListener(null);
        this.i = null;
        this.j.setOnClickListener(null);
        this.j = null;
        this.k.setOnClickListener(null);
        this.k = null;
        this.l.setOnClickListener(null);
        this.l = null;
        this.m.setOnClickListener(null);
        this.m = null;
        this.n.setOnClickListener(null);
        this.n = null;
    }
}
