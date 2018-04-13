package cn.xiaochuankeji.tieba.widget;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class PushNotifyPermissionDialog_ViewBinding implements Unbinder {
    private PushNotifyPermissionDialog b;
    private View c;
    private View d;
    private View e;

    @UiThread
    public PushNotifyPermissionDialog_ViewBinding(final PushNotifyPermissionDialog pushNotifyPermissionDialog, View view) {
        this.b = pushNotifyPermissionDialog;
        pushNotifyPermissionDialog.diaologTitle = (TextView) b.b(view, R.id.title, "field 'diaologTitle'", TextView.class);
        pushNotifyPermissionDialog.dialogContent = (TextView) b.b(view, R.id.content, "field 'dialogContent'", TextView.class);
        pushNotifyPermissionDialog.checkBox = (ImageView) b.b(view, R.id.checkbox, "field 'checkBox'", ImageView.class);
        View a = b.a(view, R.id.btn_ok, "field 'confirm' and method 'clickConfirmed'");
        pushNotifyPermissionDialog.confirm = (TextView) b.c(a, R.id.btn_ok, "field 'confirm'", TextView.class);
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ PushNotifyPermissionDialog_ViewBinding c;

            public void a(View view) {
                pushNotifyPermissionDialog.clickConfirmed();
            }
        });
        a = b.a(view, R.id.checkbox_panel, "field 'checkboxPanel' and method 'clickCheckBox'");
        pushNotifyPermissionDialog.checkboxPanel = (LinearLayout) b.c(a, R.id.checkbox_panel, "field 'checkboxPanel'", LinearLayout.class);
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ PushNotifyPermissionDialog_ViewBinding c;

            public void a(View view) {
                pushNotifyPermissionDialog.clickCheckBox();
            }
        });
        View a2 = b.a(view, R.id.close, "method 'clickClose'");
        this.e = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ PushNotifyPermissionDialog_ViewBinding c;

            public void a(View view) {
                pushNotifyPermissionDialog.clickClose();
            }
        });
    }

    @CallSuper
    public void a() {
        PushNotifyPermissionDialog pushNotifyPermissionDialog = this.b;
        if (pushNotifyPermissionDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        pushNotifyPermissionDialog.diaologTitle = null;
        pushNotifyPermissionDialog.dialogContent = null;
        pushNotifyPermissionDialog.checkBox = null;
        pushNotifyPermissionDialog.confirm = null;
        pushNotifyPermissionDialog.checkboxPanel = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
    }
}
