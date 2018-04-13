package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class UnSupportHolder_ViewBinding implements Unbinder {
    private UnSupportHolder b;

    @UiThread
    public UnSupportHolder_ViewBinding(UnSupportHolder unSupportHolder, View view) {
        this.b = unSupportHolder;
        unSupportHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        unSupportHolder.content = (TextView) b.b(view, R.id.content, "field 'content'", TextView.class);
    }

    @CallSuper
    public void a() {
        UnSupportHolder unSupportHolder = this.b;
        if (unSupportHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        unSupportHolder.avatar = null;
        unSupportHolder.content = null;
    }
}
