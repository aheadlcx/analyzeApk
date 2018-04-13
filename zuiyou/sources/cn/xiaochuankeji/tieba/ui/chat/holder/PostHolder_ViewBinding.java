package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class PostHolder_ViewBinding implements Unbinder {
    private PostHolder b;

    @UiThread
    public PostHolder_ViewBinding(PostHolder postHolder, View view) {
        this.b = postHolder;
        postHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        postHolder.content = (TextView) b.b(view, R.id.content, "field 'content'", TextView.class);
        postHolder.thumb = (WebImageView) b.b(view, R.id.thumb, "field 'thumb'", WebImageView.class);
        postHolder.title = (TextView) b.b(view, R.id.title, "field 'title'", TextView.class);
        postHolder.click_area = b.a(view, R.id.click_area, "field 'click_area'");
    }

    @CallSuper
    public void a() {
        PostHolder postHolder = this.b;
        if (postHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        postHolder.avatar = null;
        postHolder.content = null;
        postHolder.thumb = null;
        postHolder.title = null;
        postHolder.click_area = null;
    }
}
