package qsbk.app.widget.qiuyoucircle;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleArticle.VoteInfo;
import qsbk.app.model.PicUrl;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.UIHelper;
import qsbk.app.video.SimpleVideoPlayerView;
import qsbk.app.video.VideoPlayerView;
import qsbk.app.widget.AspectRatioImageView;
import qsbk.app.widget.CircleImageLayout;
import qsbk.app.widget.ClockedView;
import qsbk.app.widget.ObservableTextView;

public class ForwardCell extends BaseUserCell {
    public ImageView additionView;
    public ObservableTextView contentView;
    public String lastArticleId;
    public TextView moreView;
    public ClockedView originalClockedView;
    public TextView originalContentView;
    public CircleImageLayout originalImageLayout;
    public View originalLayout;
    public ImageView originalShareGifTag;
    public ImageView originalShareImageView;
    public View originalShareLayout;
    public ImageView originalSharePlayView;
    public TextView originalShareTitleView;
    public VideoPlayerView originalVideoPlayer;
    public View originalVoteLayout;
    public TextView originalVoteLeft;
    public TextView originalVoteRight;
    public ImageView originalVoteVS;

    public ForwardCell(ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener) {
        super(shareUtils$OnCircleShareStartListener);
    }

    public ForwardCell(ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener, boolean z) {
        super(shareUtils$OnCircleShareStartListener);
        this.fromCircleTopic = z;
    }

    public ForwardCell(ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener, boolean z, boolean z2) {
        super(shareUtils$OnCircleShareStartListener, z2);
        this.fromCircleTopic = z;
    }

    public void onCreate() {
        setCellView(getLayoutId());
        this.contentView = (ObservableTextView) findViewById(R.id.content);
        this.contentView.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
        this.moreView = (TextView) findViewById(R.id.more);
        this.originalLayout = findViewById(R.id.original_layout);
        this.originalLayout.setOnClickListener(new ae(this));
        this.originalContentView = (TextView) findViewById(R.id.original_content);
        this.originalImageLayout = (CircleImageLayout) findViewById(R.id.original_images_layout);
        this.originalVideoPlayer = (VideoPlayerView) findViewById(R.id.original_video_player);
        this.originalVoteLayout = findViewById(R.id.original_vote_layout);
        this.originalVoteLeft = (TextView) findViewById(R.id.original_vote_left);
        this.originalVoteRight = (TextView) findViewById(R.id.original_vote_right);
        this.originalVoteVS = (ImageView) findViewById(R.id.original_vote_vs);
        this.originalShareLayout = findViewById(R.id.original_share_layout);
        this.originalShareImageView = (ImageView) findViewById(R.id.original_share_image);
        this.originalShareTitleView = (TextView) findViewById(R.id.original_share_article_title);
        this.originalSharePlayView = (ImageView) findViewById(R.id.original_share_article_play);
        this.originalShareGifTag = (ImageView) findViewById(R.id.original_share_gif_tag);
        this.originalClockedView = (ClockedView) findViewById(R.id.original_clocked_view);
        this.additionView = (ImageView) findViewById(R.id.addition);
    }

    public int getLayoutId() {
        return R.layout.cell_qiuyoucircle_forward;
    }

    public void onClick() {
        CircleArticleActivity.launch(getContext(), (CircleArticle) getItem(), false, false, this.fromCircleTopic);
    }

    public void onUpdate() {
        super.onUpdate();
        CircleArticle circleArticle = (CircleArticle) getItem();
        if (!TextUtils.equals(this.lastArticleId, circleArticle.id)) {
            CharSequence content = BaseUserCell.getContent(getContext(), circleArticle, this.fromCircleTopic);
            this.contentView.setText(content);
            this.contentView.setMaxLines(this.isDetail ? Integer.MAX_VALUE : 4);
            this.contentView.setMovementMethod(content instanceof SpannableString ? LinkMovementMethod.getInstance() : null);
            CircleArticle circleArticle2 = circleArticle.originalCircleArticle;
            this.originalContentView.setText(TextUtils.concat(new CharSequence[]{getUserSpan(getContext(), circleArticle2), new SpannableString(": "), BaseUserCell.getContent(getContext(), circleArticle2, this.fromCircleTopic)}));
            this.originalImageLayout.setVisibility(8);
            this.originalVoteLayout.setVisibility(8);
            this.originalVoteVS.setVisibility(8);
            this.originalVideoPlayer.setVisibility(8);
            this.originalShareLayout.setVisibility(8);
            this.originalClockedView.setVisibility(8);
            if (circleArticle2.isShare()) {
                this.originalShareLayout.setVisibility(0);
                if (circleArticle2.hasImage()) {
                    displayImage(this.originalShareImageView, ((PicUrl) circleArticle2.picUrls.get(0)).url);
                } else {
                    displayImage(this.originalShareImageView, FrescoImageloader.getFrescoResUrl(UIHelper.getShare2IMIcon()));
                }
                this.originalShareTitleView.setText(circleArticle2.shareContent);
                this.originalSharePlayView.setVisibility(8);
                this.originalShareGifTag.setVisibility(8);
                if (circleArticle2.type == 13) {
                    this.originalShareGifTag.setVisibility(0);
                } else if (circleArticle2.type == 8) {
                    this.originalSharePlayView.setVisibility(0);
                    this.originalSharePlayView.setImageResource(R.drawable.video_play_normal);
                } else if (circleArticle2.type == 11) {
                    this.originalSharePlayView.setVisibility(0);
                    this.originalSharePlayView.setImageResource(R.drawable.live_begin);
                } else {
                    this.originalSharePlayView.setVisibility(8);
                }
                if (!circleArticle2.hasVideo()) {
                    this.originalVideoPlayer.setVisibility(8);
                }
                this.originalShareLayout.setOnClickListener(new ah(this, circleArticle2));
            } else {
                int size = circleArticle2.picUrls == null ? 0 : circleArticle2.picUrls.size();
                this.originalImageLayout.setCircleArticle(circleArticle2);
                CircleImageLayout circleImageLayout = this.originalImageLayout;
                if (size > 0) {
                    size = 0;
                } else {
                    size = 8;
                }
                circleImageLayout.setVisibility(size);
                VoteInfo voteInfo = circleArticle2.voteInfo;
                if (voteInfo != null) {
                    this.originalVoteLayout.setVisibility(0);
                    this.originalVoteVS.setVisibility(0);
                    if (voteInfo.vote == -1 || QsbkApp.currentUser == null) {
                        this.originalVoteLeft.setText(circleArticle2.voteInfo.a);
                        this.originalVoteRight.setText(circleArticle2.voteInfo.b);
                        this.originalVoteLeft.setBackgroundResource(R.color.transparent);
                        this.originalVoteRight.setBackgroundResource(R.color.transparent);
                        this.originalVoteLeft.setTextColor(UIHelper.getCircleVoteTextColor());
                        this.originalVoteRight.setTextColor(UIHelper.getCircleVoteTextColor());
                        this.originalVoteVS.setImageResource(UIHelper.getCircleVoteVs());
                    } else {
                        int i = voteInfo.aCount;
                        int i2 = voteInfo.bCount;
                        content = VoteInfo.getVoteDescription(voteInfo.a, i);
                        CharSequence voteDescription = VoteInfo.getVoteDescription(voteInfo.b, i2);
                        this.originalVoteLeft.setText(content);
                        this.originalVoteRight.setText(voteDescription);
                        this.originalVoteVS.setImageResource(UIHelper.getCircleVoteVsOn());
                        if (voteInfo.vote == 0) {
                            this.originalVoteLeft.setTextColor(UIHelper.getCircleVoteOnTextColor());
                            this.originalVoteRight.setTextColor(UIHelper.getCircleVoteTextColor());
                            this.originalVoteLeft.setBackgroundResource(UIHelper.getCircleVoteLeftOn());
                            this.originalVoteRight.setBackgroundResource(R.color.transparent);
                        } else {
                            this.originalVoteLeft.setTextColor(UIHelper.getCircleVoteTextColor());
                            this.originalVoteRight.setTextColor(UIHelper.getCircleVoteOnTextColor());
                            this.originalVoteLeft.setBackgroundResource(R.color.transparent);
                            this.originalVoteRight.setBackgroundResource(UIHelper.getCircleVoteRightOn());
                        }
                    }
                } else {
                    this.originalVoteLayout.setVisibility(8);
                    this.originalVoteVS.setVisibility(8);
                }
                if (circleArticle2.video != null) {
                    Drawable colorDrawable;
                    this.originalVideoPlayer.setVisibility(0);
                    AspectRatioImageView aspectRatioImageView = (AspectRatioImageView) this.originalVideoPlayer.getPreviewView();
                    aspectRatioImageView.setAspectRatio(circleArticle2.video.getAspectRatio());
                    if (UIHelper.isNightTheme()) {
                        colorDrawable = new ColorDrawable(-13158587);
                    } else {
                        colorDrawable = new ColorDrawable(-1381648);
                    }
                    displayImage(aspectRatioImageView, circleArticle2.video.picUrl, colorDrawable);
                    this.originalVideoPlayer.setVideo(circleArticle2.video.highUrl);
                    this.originalVideoPlayer.setAspectRatio(circleArticle2.video.width, circleArticle2.video.height);
                    if (this.originalVideoPlayer instanceof SimpleVideoPlayerView) {
                        size = circleArticle2.video.duration;
                        ((SimpleVideoPlayerView) this.originalVideoPlayer).setStrTotalTime(String.format("%d:%02d", new Object[]{Integer.valueOf(size / 60), Integer.valueOf(size % 60)}));
                    }
                    this.originalVideoPlayer.setOnClickListener(new ai(this, circleArticle2));
                } else {
                    this.originalVideoPlayer.setVisibility(8);
                }
                if (!circleArticle2.hasVideo()) {
                    this.originalVideoPlayer.setVisibility(8);
                }
                if (this.originalClockedView != null) {
                    if (!circleArticle2.isClocked() || circleArticle2.clockedInfo == null || circleArticle2.clockedInfo.length <= 0) {
                        this.originalClockedView.setVisibility(8);
                    } else {
                        this.originalClockedView.setVisibility(0);
                        this.originalClockedView.setClockedTimes(circleArticle2.clockedInfo, "最近" + circleArticle2.clockedInfo.length + "次打卡");
                    }
                }
            }
        }
        this.contentView.setOnClickListener(new aj(this));
        this.contentView.setOnLongClickListener(new ak(this));
        if (this.isDetail) {
            this.contentView.setMaxLines(Integer.MAX_VALUE);
        } else {
            this.moreView.setVisibility(8);
            this.contentView.setMaxLines(getContentMaxLine());
            this.contentView.setOnTextMoreListener(new al(this));
        }
        this.shareCount.setOnClickListener(new LoginPermissionClickDelegate(new am(this), null));
        getCellView().setOnLongClickListener(new an(this));
        if (this.hotComment != null) {
            if (circleArticle.hotComment == null || this.isDetail) {
                this.hotCommentLabel.setVisibility(8);
                this.hotComment.setVisibility(8);
                this.hotCommentImage.setVisibility(8);
            } else {
                this.hotComment.setOnClickListener(new ao(this, circleArticle));
                this.hotCommentLabel.setVisibility(0);
                this.hotCommentLabel.setImageResource(UIHelper.isNightTheme() ? R.drawable.hot_comment_label_night : R.drawable.hot_comment_label);
                this.hotComment.setVisibility(0);
                this.hotComment.setTextColor(UIHelper.isNightTheme() ? -9802626 : -9474193);
                this.hotComment.setText(String.format("      %s：%s", new Object[]{circleArticle.hotComment.userName, circleArticle.hotComment.content}));
                if (circleArticle.hotComment.smallImage != null) {
                    this.hotCommentImage.setVisibility(0);
                    LayoutParams layoutParams = this.hotCommentImage.getLayoutParams();
                    int dip2px = UIHelper.dip2px(getContext(), 180.0f);
                    i = UIHelper.dip2px(getContext(), 100.0f);
                    if (layoutParams == null) {
                        layoutParams = new LayoutParams(0, 0);
                    }
                    layoutParams.height = circleArticle.hotComment.smallImage.height;
                    layoutParams.width = circleArticle.hotComment.smallImage.width;
                    if (circleArticle.hotComment.smallImage.isVertical()) {
                        if (circleArticle.hotComment.smallImage.height > dip2px) {
                            layoutParams.height = dip2px;
                            layoutParams.width = (int) (((float) dip2px) * circleArticle.hotComment.smallImage.getRatio());
                        }
                    } else if (circleArticle.hotComment.smallImage.width > i) {
                        layoutParams.width = i;
                        layoutParams.height = (int) (((float) i) / circleArticle.hotComment.smallImage.getRatio());
                    }
                    this.hotCommentImage.setLayoutParams(layoutParams);
                    FrescoImageloader.displayImage(this.hotCommentImage, circleArticle.hotComment.smallImage.getImageUrl(), UIHelper.getDefaultImageTileBackground());
                    this.hotCommentImage.setOnClickListener(new LoginPermissionClickDelegate(new af(this, circleArticle), null));
                } else {
                    this.hotCommentImage.setVisibility(8);
                }
            }
        }
        this.lastArticleId = circleArticle.id;
        int i3 = circleArticle.isTop ? UIHelper.isNightTheme() ? R.drawable.ic_circle_top_night : R.drawable.ic_circle_top : circleArticle.isRecommend ? UIHelper.isNightTheme() ? R.drawable.ic_circle_recommend_night : R.drawable.ic_circle_recommend : 0;
        this.additionView.setImageResource(i3);
    }

    public SpannableString getUserSpan(Context context, CircleArticle circleArticle) {
        SpannableString spannableString = new SpannableString("@" + circleArticle.user.userName);
        spannableString.setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), 0, spannableString.length(), 33);
        if (!(context instanceof CircleTopicActivity)) {
            spannableString.setSpan(new ag(this, circleArticle), 0, spannableString.length(), 33);
        }
        return spannableString;
    }
}
