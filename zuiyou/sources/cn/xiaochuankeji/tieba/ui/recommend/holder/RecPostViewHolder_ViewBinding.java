package cn.xiaochuankeji.tieba.ui.recommend.holder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.a.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.post.postitem.ViewGodReviewIndicators;
import cn.xiaochuankeji.tieba.ui.recommend.widget.ResizeMultiDraweeView;
import cn.xiaochuankeji.tieba.ui.widget.MultipleLineEllipsisTextView;
import cn.xiaochuankeji.tieba.ui.widget.image.WebImageView;
import cn.xiaochuankeji.tieba.ui.widget.updown.PostItemUpDownView;

public class RecPostViewHolder_ViewBinding implements Unbinder {
    private RecPostViewHolder b;

    @UiThread
    public RecPostViewHolder_ViewBinding(RecPostViewHolder recPostViewHolder, View view) {
        this.b = recPostViewHolder;
        recPostViewHolder.hotFlag = b.a(view, R.id.ivEditorRecommend, "field 'hotFlag'");
        recPostViewHolder.avatarView = (WebImageView) b.b(view, R.id.pvAvatar, "field 'avatarView'", WebImageView.class);
        recPostViewHolder.userLevelFlag = (ImageView) b.b(view, R.id.iv_user_level, "field 'userLevelFlag'", ImageView.class);
        recPostViewHolder.tvNickname = (TextView) b.b(view, R.id.tvWriterName, "field 'tvNickname'", TextView.class);
        recPostViewHolder.iv_official = (ImageView) b.b(view, R.id.iv_official, "field 'iv_official'", ImageView.class);
        recPostViewHolder.memberContainer = (ViewGroup) b.b(view, R.id.llMemberInfo, "field 'memberContainer'", ViewGroup.class);
        recPostViewHolder.telentFlag = (ImageView) b.b(view, R.id.iv_talent, "field 'telentFlag'", ImageView.class);
        recPostViewHolder.tvTime = (TextView) b.b(view, R.id.tvCreateTime, "field 'tvTime'", TextView.class);
        recPostViewHolder.tediumView = (ImageView) b.b(view, R.id.ivTediumPost, "field 'tediumView'", ImageView.class);
        recPostViewHolder.tvFollow = (TextView) b.b(view, R.id.ivFollow, "field 'tvFollow'", TextView.class);
        recPostViewHolder.ivMore = (ImageView) b.b(view, R.id.iv_more, "field 'ivMore'", ImageView.class);
        recPostViewHolder.tvCancelFavor = (TextView) b.b(view, R.id.tvCancelFavor, "field 'tvCancelFavor'", TextView.class);
        recPostViewHolder.tvPost = (MultipleLineEllipsisTextView) b.b(view, R.id.tvPostContent, "field 'tvPost'", MultipleLineEllipsisTextView.class);
        recPostViewHolder.ivPostImages = (ResizeMultiDraweeView) b.b(view, R.id.postImages, "field 'ivPostImages'", ResizeMultiDraweeView.class);
        recPostViewHolder.linkLayout = (RelativeLayout) b.b(view, R.id.rl_link_container, "field 'linkLayout'", RelativeLayout.class);
        recPostViewHolder.commonLinkLayout = (RelativeLayout) b.b(view, R.id.rlLinkArea, "field 'commonLinkLayout'", RelativeLayout.class);
        recPostViewHolder.wechatLinkLayout = (RelativeLayout) b.b(view, R.id.rl_link_wechat, "field 'wechatLinkLayout'", RelativeLayout.class);
        recPostViewHolder.wyLinkLayout = (RelativeLayout) b.b(view, R.id.rl_link_net163, "field 'wyLinkLayout'", RelativeLayout.class);
        recPostViewHolder.ivCommonLink = (WebImageView) b.b(view, R.id.pvLinkHolder, "field 'ivCommonLink'", WebImageView.class);
        recPostViewHolder.tvCommonLink = (TextView) b.b(view, R.id.tvUrl, "field 'tvCommonLink'", TextView.class);
        recPostViewHolder.ivWechatLink = (WebImageView) b.b(view, R.id.pv_wechat_link, "field 'ivWechatLink'", WebImageView.class);
        recPostViewHolder.tvWechatTitle = (TextView) b.b(view, R.id.tv_wechat_title, "field 'tvWechatTitle'", TextView.class);
        recPostViewHolder.tvWechatDesc = (TextView) b.b(view, R.id.tv_wechat_describe, "field 'tvWechatDesc'", TextView.class);
        recPostViewHolder.ivWyLink = (WebImageView) b.b(view, R.id.pvLink_163net_Holder, "field 'ivWyLink'", WebImageView.class);
        recPostViewHolder.tvWyTitle = (TextView) b.b(view, R.id.tv_net163_title, "field 'tvWyTitle'", TextView.class);
        recPostViewHolder.tvWyAuthor = (TextView) b.b(view, R.id.tv_net163_author, "field 'tvWyAuthor'", TextView.class);
        recPostViewHolder.voteLayout = (ViewGroup) b.b(view, R.id.voteWidget, "field 'voteLayout'", ViewGroup.class);
        recPostViewHolder.godReviewIndicators = (ViewGodReviewIndicators) b.b(view, R.id.vGodReviewIndicators, "field 'godReviewIndicators'", ViewGodReviewIndicators.class);
        recPostViewHolder.godReviewContainer = (RelativeLayout) b.b(view, R.id.llGodReview, "field 'godReviewContainer'", RelativeLayout.class);
        recPostViewHolder.tvTopicName = (TextView) b.b(view, R.id.tvTopicName, "field 'tvTopicName'", TextView.class);
        recPostViewHolder.tvShare = (TextView) b.b(view, R.id.tvShare, "field 'tvShare'", TextView.class);
        recPostViewHolder.tvComment = (TextView) b.b(view, R.id.tvCommentCount, "field 'tvComment'", TextView.class);
        recPostViewHolder.postItemUpDownView = (PostItemUpDownView) b.b(view, R.id.postItemUpDownView, "field 'postItemUpDownView'", PostItemUpDownView.class);
        recPostViewHolder.topicFrameLayout = (FrameLayout) b.b(view, R.id.flTopicNameClickContainer, "field 'topicFrameLayout'", FrameLayout.class);
    }

    @CallSuper
    public void a() {
        RecPostViewHolder recPostViewHolder = this.b;
        if (recPostViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.b = null;
        recPostViewHolder.hotFlag = null;
        recPostViewHolder.avatarView = null;
        recPostViewHolder.userLevelFlag = null;
        recPostViewHolder.tvNickname = null;
        recPostViewHolder.iv_official = null;
        recPostViewHolder.memberContainer = null;
        recPostViewHolder.telentFlag = null;
        recPostViewHolder.tvTime = null;
        recPostViewHolder.tediumView = null;
        recPostViewHolder.tvFollow = null;
        recPostViewHolder.ivMore = null;
        recPostViewHolder.tvCancelFavor = null;
        recPostViewHolder.tvPost = null;
        recPostViewHolder.ivPostImages = null;
        recPostViewHolder.linkLayout = null;
        recPostViewHolder.commonLinkLayout = null;
        recPostViewHolder.wechatLinkLayout = null;
        recPostViewHolder.wyLinkLayout = null;
        recPostViewHolder.ivCommonLink = null;
        recPostViewHolder.tvCommonLink = null;
        recPostViewHolder.ivWechatLink = null;
        recPostViewHolder.tvWechatTitle = null;
        recPostViewHolder.tvWechatDesc = null;
        recPostViewHolder.ivWyLink = null;
        recPostViewHolder.tvWyTitle = null;
        recPostViewHolder.tvWyAuthor = null;
        recPostViewHolder.voteLayout = null;
        recPostViewHolder.godReviewIndicators = null;
        recPostViewHolder.godReviewContainer = null;
        recPostViewHolder.tvTopicName = null;
        recPostViewHolder.tvShare = null;
        recPostViewHolder.tvComment = null;
        recPostViewHolder.postItemUpDownView = null;
        recPostViewHolder.topicFrameLayout = null;
    }
}
