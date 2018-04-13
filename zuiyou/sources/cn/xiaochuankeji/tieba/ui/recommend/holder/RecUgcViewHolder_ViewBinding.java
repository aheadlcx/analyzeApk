package cn.xiaochuankeji.tieba.ui.recommend.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;

public class RecUgcViewHolder_ViewBinding implements Unbinder {
    private RecUgcViewHolder b;

    @UiThread
    public RecUgcViewHolder_ViewBinding(RecUgcViewHolder recUgcViewHolder, View view) {
        this.b = recUgcViewHolder;
        recUgcViewHolder.pvAvatar = (WebImageView) b.b(view, R.id.pvAvatar, "field 'pvAvatar'", WebImageView.class);
        recUgcViewHolder.ivUserLevel = (ImageView) b.b(view, R.id.iv_user_level, "field 'ivUserLevel'", ImageView.class);
        recUgcViewHolder.tvUserName = (TextView) b.b(view, R.id.tvWriterName, "field 'tvUserName'", TextView.class);
        recUgcViewHolder.ivTediumPost = (ImageView) b.b(view, R.id.ivTediumPost, "field 'ivTediumPost'", ImageView.class);
        recUgcViewHolder.ivMore = (ImageView) b.b(view, R.id.iv_more, "field 'ivMore'", ImageView.class);
        recUgcViewHolder.postContentView = (MultipleLineEllipsisTextView) b.b(view, R.id.tvPostContent, "field 'postContentView'", MultipleLineEllipsisTextView.class);
        recUgcViewHolder.ugcCoverBg = (WebImageView) b.b(view, R.id.ugcvideo_cover_bg, "field 'ugcCoverBg'", WebImageView.class);
        recUgcViewHolder.ugcCover = (WebImageView) b.b(view, R.id.ugcvideo_cover, "field 'ugcCover'", WebImageView.class);
        recUgcViewHolder.tvPlayCount = (TextView) b.b(view, R.id.tvPlayCount, "field 'tvPlayCount'", TextView.class);
        recUgcViewHolder.tvDanmuCount = (TextView) b.b(view, R.id.tvDanmuCount, "field 'tvDanmuCount'", TextView.class);
        recUgcViewHolder.tvDur = (TextView) b.b(view, R.id.tvVDur, "field 'tvDur'", TextView.class);
        recUgcViewHolder.tvTopicName = (TextView) b.b(view, R.id.tvTopicName, "field 'tvTopicName'", TextView.class);
        recUgcViewHolder.tvShare = (TextView) b.b(view, R.id.tvShare, "field 'tvShare'", TextView.class);
        recUgcViewHolder.tvFollowCount = (TextView) b.b(view, R.id.tvFollowCount, "field 'tvFollowCount'", TextView.class);
        recUgcViewHolder.postItemUpDownView = (PostItemUpDownView) b.b(view, R.id.postItemUpDownView, "field 'postItemUpDownView'", PostItemUpDownView.class);
        recUgcViewHolder.vTopicContainer = (FrameLayout) b.b(view, R.id.topicContainer, "field 'vTopicContainer'", FrameLayout.class);
        recUgcViewHolder.videoContainer = (FrameLayout) b.b(view, R.id.layout_ugcvideo_cover, "field 'videoContainer'", FrameLayout.class);
    }

    @CallSuper
    public void a() {
        RecUgcViewHolder recUgcViewHolder = this.b;
        if (recUgcViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        recUgcViewHolder.pvAvatar = null;
        recUgcViewHolder.ivUserLevel = null;
        recUgcViewHolder.tvUserName = null;
        recUgcViewHolder.ivTediumPost = null;
        recUgcViewHolder.ivMore = null;
        recUgcViewHolder.postContentView = null;
        recUgcViewHolder.ugcCoverBg = null;
        recUgcViewHolder.ugcCover = null;
        recUgcViewHolder.tvPlayCount = null;
        recUgcViewHolder.tvDanmuCount = null;
        recUgcViewHolder.tvDur = null;
        recUgcViewHolder.tvTopicName = null;
        recUgcViewHolder.tvShare = null;
        recUgcViewHolder.tvFollowCount = null;
        recUgcViewHolder.postItemUpDownView = null;
        recUgcViewHolder.vTopicContainer = null;
        recUgcViewHolder.videoContainer = null;
    }
}
