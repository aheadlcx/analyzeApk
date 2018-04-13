package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class TaleHolder_ViewBinding implements Unbinder {
    private TaleHolder b;

    @UiThread
    public TaleHolder_ViewBinding(TaleHolder taleHolder, View view) {
        this.b = taleHolder;
        taleHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        taleHolder.content = (TextView) b.b(view, R.id.content, "field 'content'", TextView.class);
        taleHolder.thumb = (WebImageView) b.b(view, R.id.thumb, "field 'thumb'", WebImageView.class);
        taleHolder.title = (TextView) b.b(view, R.id.title, "field 'title'", TextView.class);
        taleHolder.click_area = b.a(view, R.id.click_area, "field 'click_area'");
    }

    @CallSuper
    public void a() {
        TaleHolder taleHolder = this.b;
        if (taleHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        taleHolder.avatar = null;
        taleHolder.content = null;
        taleHolder.thumb = null;
        taleHolder.title = null;
        taleHolder.click_area = null;
    }
}
