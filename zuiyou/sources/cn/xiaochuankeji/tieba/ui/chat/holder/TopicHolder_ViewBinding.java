package cn.xiaochuankeji.tieba.ui.chat.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class TopicHolder_ViewBinding implements Unbinder {
    private TopicHolder b;

    @UiThread
    public TopicHolder_ViewBinding(TopicHolder topicHolder, View view) {
        this.b = topicHolder;
        topicHolder.avatar = (WebImageView) b.b(view, R.id.avatar, "field 'avatar'", WebImageView.class);
        topicHolder.content = (TextView) b.b(view, R.id.content, "field 'content'", TextView.class);
        topicHolder.thumb = (WebImageView) b.b(view, R.id.thumb, "field 'thumb'", WebImageView.class);
        topicHolder.title = (TextView) b.b(view, R.id.title, "field 'title'", TextView.class);
        topicHolder.count = (TextView) b.b(view, R.id.count, "field 'count'", TextView.class);
        topicHolder.click_area = b.a(view, R.id.click_area, "field 'click_area'");
    }

    @CallSuper
    public void a() {
        TopicHolder topicHolder = this.b;
        if (topicHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        topicHolder.avatar = null;
        topicHolder.content = null;
        topicHolder.thumb = null;
        topicHolder.title = null;
        topicHolder.count = null;
        topicHolder.click_area = null;
    }
}
