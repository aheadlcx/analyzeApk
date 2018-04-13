package cn.xiaochuankeji.tieba.ui.topic.holder;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class UgcViewHolder_ViewBinding extends BaseViewHolder_ViewBinding {
    private UgcViewHolder b;

    @UiThread
    public UgcViewHolder_ViewBinding(UgcViewHolder ugcViewHolder, View view) {
        super(ugcViewHolder, view);
        this.b = ugcViewHolder;
        ugcViewHolder.ugcDescription = (MultipleLineEllipsisTextView) b.b(view, R.id.tvPostContent, "field 'ugcDescription'", MultipleLineEllipsisTextView.class);
        ugcViewHolder.videoCoverBackground = (WebImageView) b.b(view, R.id.video_cover_bg, "field 'videoCoverBackground'", WebImageView.class);
        ugcViewHolder.videoCover = (WebImageView) b.b(view, R.id.video_cover, "field 'videoCover'", WebImageView.class);
        ugcViewHolder.topicName = (TextView) b.b(view, R.id.post_topic_name, "field 'topicName'", TextView.class);
        ugcViewHolder.playNumber = (TextView) b.b(view, R.id.video_play_num, "field 'playNumber'", TextView.class);
        ugcViewHolder.coverView = b.a(view, R.id.layout_video_cover, "field 'coverView'");
    }

    public void a() {
        UgcViewHolder ugcViewHolder = this.b;
        if (ugcViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        ugcViewHolder.ugcDescription = null;
        ugcViewHolder.videoCoverBackground = null;
        ugcViewHolder.videoCover = null;
        ugcViewHolder.topicName = null;
        ugcViewHolder.playNumber = null;
        ugcViewHolder.coverView = null;
        super.a();
    }
}
