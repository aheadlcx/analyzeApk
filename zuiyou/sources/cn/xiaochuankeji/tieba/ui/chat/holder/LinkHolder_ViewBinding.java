package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class LinkHolder_ViewBinding implements Unbinder {
    private LinkHolder b;

    @UiThread
    public LinkHolder_ViewBinding(LinkHolder linkHolder, View view) {
        this.b = linkHolder;
        linkHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        linkHolder.thumb = (WebImageView) b.b(view, R.id.thumb, "field 'thumb'", WebImageView.class);
        linkHolder.title = (TextView) b.b(view, R.id.title, "field 'title'", TextView.class);
        linkHolder.content = (TextView) b.b(view, R.id.content, "field 'content'", TextView.class);
        linkHolder.click_area = b.a(view, R.id.click_area, "field 'click_area'");
    }

    @CallSuper
    public void a() {
        LinkHolder linkHolder = this.b;
        if (linkHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        linkHolder.avatar = null;
        linkHolder.thumb = null;
        linkHolder.title = null;
        linkHolder.content = null;
        linkHolder.click_area = null;
    }
}
