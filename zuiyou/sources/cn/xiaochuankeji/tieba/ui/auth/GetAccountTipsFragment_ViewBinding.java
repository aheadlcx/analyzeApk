package cn.xiaochuankeji.tieba.ui.auth;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.a.a;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;

public class GetAccountTipsFragment_ViewBinding implements Unbinder {
    private GetAccountTipsFragment b;
    private View c;
    private View d;
    private View e;

    @UiThread
    public GetAccountTipsFragment_ViewBinding(final GetAccountTipsFragment getAccountTipsFragment, View view) {
        this.b = getAccountTipsFragment;
        getAccountTipsFragment.content = (LinearLayout) b.b(view, R.id.content, "field 'content'", LinearLayout.class);
        View a = b.a(view, R.id.find_pwd, "field 'find_pwd' and method 'findPW'");
        getAccountTipsFragment.find_pwd = (AppCompatTextView) b.c(a, R.id.find_pwd, "field 'find_pwd'", AppCompatTextView.class);
        this.c = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ GetAccountTipsFragment_ViewBinding c;

            public void a(View view) {
                getAccountTipsFragment.findPW();
            }
        });
        a = b.a(view, R.id.find_account, "field 'find_account' and method 'findAccount'");
        getAccountTipsFragment.find_account = (AppCompatTextView) b.c(a, R.id.find_account, "field 'find_account'", AppCompatTextView.class);
        this.d = a;
        a.setOnClickListener(new a(this) {
            final /* synthetic */ GetAccountTipsFragment_ViewBinding c;

            public void a(View view) {
                getAccountTipsFragment.findAccount();
            }
        });
        View a2 = b.a(view, R.id.container, "method 'close'");
        this.e = a2;
        a2.setOnClickListener(new a(this) {
            final /* synthetic */ GetAccountTipsFragment_ViewBinding c;

            public void a(View view) {
                getAccountTipsFragment.close();
            }
        });
    }

    @CallSuper
    public void a() {
        GetAccountTipsFragment getAccountTipsFragment = this.b;
        if (getAccountTipsFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        getAccountTipsFragment.content = null;
        getAccountTipsFragment.find_pwd = null;
        getAccountTipsFragment.find_account = null;
        this.c.setOnClickListener(null);
        this.c = null;
        this.d.setOnClickListener(null);
        this.d = null;
        this.e.setOnClickListener(null);
        this.e = null;
    }
}
