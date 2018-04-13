package cn.xiaochuankeji.tieba.ui.utils;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class TipsDialog_ViewBinding implements Unbinder {
    private TipsDialog b;
    private View c;
    private View d;
    private View e;

    @UiThread
    public TipsDialog_ViewBinding(final TipsDialog tipsDialog, View view) {
        this.b = tipsDialog;
        tipsDialog.content_container = b.a(view, R.id.content_container, "field 'content_container'");
        tipsDialog.title = (AppCompatTextView) b.b(view, R.id.title, "field 'title'", AppCompatTextView.class);
        tipsDialog.content = (AppCompatTextView) b.b(view, R.id.content, "field 'content'", AppCompatTextView.class);
        View a = b.a(view, R.id.left, "field 'left' and method 'left'");
        tipsDialog.left = (AppCompatTextView) b.c(a, R.id.left, "field 'left'", AppCompatTextView.class);
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ TipsDialog_ViewBinding c;

            public void a(View view) {
                tipsDialog.left();
            }
        });
        a = b.a(view, R.id.right, "field 'right' and method 'right'");
        tipsDialog.right = (AppCompatTextView) b.c(a, R.id.right, "field 'right'", AppCompatTextView.class);
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ TipsDialog_ViewBinding c;

            public void a(View view) {
                tipsDialog.right(view);
            }
        });
        View a2 = b.a(view, R.id.container, "method 'close'");
        this.e = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ TipsDialog_ViewBinding c;

            public void a(View view) {
                tipsDialog.close();
            }
        });
    }

    @CallSuper
    public void a() {
        TipsDialog tipsDialog = this.b;
        if (tipsDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        tipsDialog.content_container = null;
        tipsDialog.title = null;
        tipsDialog.content = null;
        tipsDialog.left = null;
        tipsDialog.right = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
    }
}
