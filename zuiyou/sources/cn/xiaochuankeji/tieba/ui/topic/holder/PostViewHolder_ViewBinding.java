package cn.xiaochuankeji.tieba.ui.topic.holder;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.recommend.widget.ResizeMultiDraweeView;
import cn.xiaochuankeji.tieba.ui.topic.weight.PostVoteView;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;

public class PostViewHolder_ViewBinding extends BaseViewHolder_ViewBinding {
    private PostViewHolder b;

    @UiThread
    public PostViewHolder_ViewBinding(PostViewHolder postViewHolder, View view) {
        super(postViewHolder, view);
        this.b = postViewHolder;
        postViewHolder.postContent = (MultipleLineEllipsisTextView) b.b(view, R.id.tvPostContent, "field 'postContent'", MultipleLineEllipsisTextView.class);
        postViewHolder.topicName = (TextView) b.b(view, R.id.tvTopicName, "field 'topicName'", TextView.class);
        postViewHolder.linkContainer = b.a(view, R.id.rl_link_container, "field 'linkContainer'");
        postViewHolder.netLinkView = b.a(view, R.id.rlLinkArea, "field 'netLinkView'");
        postViewHolder.netLinkImage = (WebImageView) b.b(view, R.id.pvLinkHolder, "field 'netLinkImage'", WebImageView.class);
        postViewHolder.netLinkText = (TextView) b.b(view, R.id.tvUrl, "field 'netLinkText'", TextView.class);
        postViewHolder.weChatLinkView = b.a(view, R.id.rl_link_wechat, "field 'weChatLinkView'");
        postViewHolder.weChatLintTitle = (TextView) b.b(view, R.id.tv_wechat_title, "field 'weChatLintTitle'", TextView.class);
        postViewHolder.weChatLintInfo = (TextView) b.b(view, R.id.tv_wechat_describe, "field 'weChatLintInfo'", TextView.class);
        postViewHolder.weChatLinkImage = (WebImageView) b.b(view, R.id.pv_wechat_link, "field 'weChatLinkImage'", WebImageView.class);
        postViewHolder.netsLinkView = b.a(view, R.id.rl_link_net163, "field 'netsLinkView'");
        postViewHolder.netsLinkImage = (WebImageView) b.b(view, R.id.pvLink_163net_Holder, "field 'netsLinkImage'", WebImageView.class);
        postViewHolder.netsLinkTitle = (TextView) b.b(view, R.id.tv_net163_title, "field 'netsLinkTitle'", TextView.class);
        postViewHolder.netsLinkAuthor = (TextView) b.b(view, R.id.tv_net163_author, "field 'netsLinkAuthor'", TextView.class);
        postViewHolder.images = (ResizeMultiDraweeView) b.b(view, R.id.postImages, "field 'images'", ResizeMultiDraweeView.class);
        postViewHolder.voteView = (PostVoteView) b.b(view, R.id.voteWidget, "field 'voteView'", PostVoteView.class);
    }

    public void a() {
        PostViewHolder postViewHolder = this.b;
        if (postViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        postViewHolder.postContent = null;
        postViewHolder.topicName = null;
        postViewHolder.linkContainer = null;
        postViewHolder.netLinkView = null;
        postViewHolder.netLinkImage = null;
        postViewHolder.netLinkText = null;
        postViewHolder.weChatLinkView = null;
        postViewHolder.weChatLintTitle = null;
        postViewHolder.weChatLintInfo = null;
        postViewHolder.weChatLinkImage = null;
        postViewHolder.netsLinkView = null;
        postViewHolder.netsLinkImage = null;
        postViewHolder.netsLinkTitle = null;
        postViewHolder.netsLinkAuthor = null;
        postViewHolder.images = null;
        postViewHolder.voteView = null;
        super.a();
    }
}
