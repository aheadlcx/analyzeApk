package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class TextHolder_ViewBinding implements Unbinder {
    private TextHolder b;

    @UiThread
    public TextHolder_ViewBinding(TextHolder textHolder, View view) {
        this.b = textHolder;
        textHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        textHolder.content = (TextView) b.b(view, R.id.content, "field 'content'", TextView.class);
    }

    @CallSuper
    public void a() {
        TextHolder textHolder = this.b;
        if (textHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        textHolder.avatar = null;
        textHolder.content = null;
    }
}
