package qsbk.app.widget.qiuyoucircle;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleArticle.VoteInfo;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.UIHelper;
import qsbk.app.video.QiuyouCircleVideoLoopStatistics;
import qsbk.app.video.SimpleVideoPlayerView;
import qsbk.app.video.VideoPlayerView;
import qsbk.app.widget.AspectRatioImageView;
import qsbk.app.widget.CircleImageLayout;
import qsbk.app.widget.ClockedView;
import qsbk.app.widget.ObservableTextView;

public class NormalCell extends BaseUserCell {
    private int a;
    public ImageView additionView;
    private int b;
    public CircleArticle circleArticle;
    public ClockedView clockedView;
    public ObservableTextView contentView;
    public boolean hasTopic;
    public CircleImageLayout imageLayout;
    public String lastArticleId;
    public Drawable loadingDrawalbe;
    public View moreView;
    public VideoPlayerView playerView;
    public TextView stateMsg;
    public View voteLayout;
    public TextView voteLeftView;
    public TextView voteRightView;
    public ImageView voteVsView;

    public NormalCell(ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener) {
        this(shareUtils$OnCircleShareStartListener, false, false);
    }

    public NormalCell(ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener, boolean z) {
        this(shareUtils$OnCircleShareStartListener, z, false);
    }

    public NormalCell(ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener, boolean z, boolean z2) {
        super(shareUtils$OnCircleShareStartListener);
        this.a = 6;
        this.b = 6;
        this.fromCircleTopic = z;
        this.isDetail = z2;
    }

    public void onCreate() {
        setCellView(getLayoutId());
        this.circleArticle = (CircleArticle) getItem();
        this.contentView = (ObservableTextView) findViewById(R.id.content);
        this.contentView.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
        this.moreView = findViewById(R.id.more);
        this.playerView = (VideoPlayerView) findViewById(R.id.video_player);
        this.imageLayout = (CircleImageLayout) findViewById(R.id.images_layout);
        this.imageLayout.fillCount(this.isDetail ? this.a : this.b);
        this.voteLayout = findViewById(R.id.vote_layout);
        this.voteLeftView = (TextView) findViewById(R.id.vote_left);
        this.voteRightView = (TextView) findViewById(R.id.vote_right);
        this.voteVsView = (ImageView) findViewById(R.id.vote_vs);
        this.stateMsg = (TextView) findViewById(R.id.state_msg);
        this.voteLeftView.setOnClickListener(new LoginPermissionClickDelegate(new ap(this), null));
        this.voteRightView.setOnClickListener(new LoginPermissionClickDelegate(new au(this), null));
        this.loadingDrawalbe = UIHelper.isNightTheme() ? new ColorDrawable(-13158587) : new ColorDrawable(-1381648);
        this.clockedView = (ClockedView) findViewById(R.id.clocked_view);
        this.additionView = (ImageView) findViewById(R.id.addition);
    }

    public int getLayoutId() {
        return R.layout.cell_qiuyoucircle_normal;
    }

    public void onUpdate() {
        CharSequence spannableString;
        int indexOf;
        int length;
        super.onUpdate();
        CircleArticle circleArticle = (CircleArticle) getItem();
        this.circleArticle = circleArticle;
        if (circleArticle.isVideoArticle()) {
            QiuyouCircleVideoLoopStatistics.getInstance().loopBatch(circleArticle.id, 1, 0);
        }
        if (this.stateMsg != null) {
            if (TextUtils.isEmpty(this.circleArticle.report_reason)) {
                this.stateMsg.setVisibility(8);
            } else {
                this.stateMsg.setVisibility(0);
                this.stateMsg.setText(this.circleArticle.report_reason);
            }
        }
        boolean equals = TextUtils.equals(this.lastArticleId, circleArticle.id);
        this.playerView.setOnClickListener(new av(this, circleArticle));
        this.hasTopic = false;
        if ((circleArticle.topic != null || (circleArticle.atInfoTexts != null && circleArticle.atInfoTexts.size() > 0)) && !equals) {
            spannableString = new SpannableString(circleArticle.content);
            if (circleArticle.topic != null) {
                indexOf = circleArticle.content.indexOf(circleArticle.topic.content);
                if (indexOf != -1) {
                    length = circleArticle.topic.content.length() + indexOf;
                    spannableString.setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), indexOf, length, 33);
                    if (!(getContext() instanceof CircleTopicActivity)) {
                        spannableString.setSpan(new aw(this, circleArticle), indexOf, length, 33);
                        this.contentView.setMovementMethod(LinkMovementMethod.getInstance());
                    }
                    this.hasTopic = true;
                }
            }
            if (circleArticle.atInfoTexts != null && circleArticle.atInfoTexts.size() > 0) {
                for (length = 0; length < circleArticle.atInfoTexts.size(); length++) {
                    AtInfo atInfo = (AtInfo) circleArticle.atInfoTexts.get(length);
                    try {
                        Matcher matcher = Pattern.compile("@" + atInfo.name).matcher(circleArticle.content);
                        while (matcher.find()) {
                            int start = matcher.start(0);
                            int end = matcher.end(0);
                            spannableString.setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), start, end, 33);
                            if (!(getContext() instanceof CircleTopicActivity)) {
                                spannableString.setSpan(new ax(this, atInfo, circleArticle), start, end, 33);
                                this.contentView.setMovementMethod(LinkMovementMethod.getInstance());
                            }
                        }
                    } catch (PatternSyntaxException e) {
                    }
                }
            }
            this.contentView.setText(spannableString);
        } else if (!equals) {
            this.contentView.setMovementMethod(null);
            this.contentView.setText(circleArticle.content);
        }
        this.contentView.setOnClickListener(new ay(this));
        this.contentView.setOnLongClickListener(new az(this));
        this.moreView.setOnClickListener(new ba(this));
        if (this.isDetail) {
            this.contentView.setMaxLines(Integer.MAX_VALUE);
        } else {
            this.moreView.setVisibility(8);
            this.contentView.setMaxLines(getContentMaxLine());
            this.contentView.setOnTextMoreListener(new bb(this));
        }
        this.lastArticleId = circleArticle.id;
        indexOf = circleArticle.picUrls.size();
        this.imageLayout.setCircleArticle(circleArticle);
        if (indexOf > 0) {
            this.imageLayout.setVisibility(0);
        } else {
            this.imageLayout.setVisibility(8);
        }
        VoteInfo voteInfo = circleArticle.voteInfo;
        if (voteInfo != null) {
            this.voteLayout.setVisibility(0);
            this.voteVsView.setVisibility(0);
            if (voteInfo.vote == -1 || QsbkApp.currentUser == null) {
                this.voteLeftView.setText(circleArticle.voteInfo.a);
                this.voteRightView.setText(circleArticle.voteInfo.b);
                this.voteLeftView.setBackgroundResource(R.color.transparent);
                this.voteRightView.setBackgroundResource(R.color.transparent);
                this.voteLeftView.setTextColor(UIHelper.getCircleVoteTextColor());
                this.voteRightView.setTextColor(UIHelper.getCircleVoteTextColor());
                this.voteVsView.setImageResource(UIHelper.getCircleVoteVs());
            } else {
                length = circleArticle.voteInfo.aCount;
                int i = circleArticle.voteInfo.bCount;
                CharSequence voteDescription = VoteInfo.getVoteDescription(circleArticle.voteInfo.a, length);
                spannableString = VoteInfo.getVoteDescription(circleArticle.voteInfo.b, i);
                this.voteLeftView.setText(voteDescription);
                this.voteRightView.setText(spannableString);
                this.voteVsView.setImageResource(UIHelper.getCircleVoteVsOn());
                if (voteInfo.vote == 0) {
                    this.voteLeftView.setTextColor(UIHelper.getCircleVoteOnTextColor());
                    this.voteRightView.setTextColor(UIHelper.getCircleVoteTextColor());
                    this.voteLeftView.setBackgroundResource(UIHelper.getCircleVoteLeftOn());
                    this.voteRightView.setBackgroundResource(R.color.transparent);
                } else {
                    this.voteLeftView.setTextColor(UIHelper.getCircleVoteTextColor());
                    this.voteRightView.setTextColor(UIHelper.getCircleVoteOnTextColor());
                    this.voteLeftView.setBackgroundResource(R.color.transparent);
                    this.voteRightView.setBackgroundResource(UIHelper.getCircleVoteRightOn());
                }
            }
        } else {
            this.voteLayout.setVisibility(8);
            this.voteVsView.setVisibility(8);
        }
        if (circleArticle.video != null) {
            this.playerView.setVisibility(0);
            AspectRatioImageView aspectRatioImageView = (AspectRatioImageView) this.playerView.getPreviewView();
            aspectRatioImageView.setAspectRatio(circleArticle.video.getAspectRatio());
            displayImage(aspectRatioImageView, circleArticle.video.picUrl, this.loadingDrawalbe);
            this.playerView.setVideo(circleArticle.video.highUrl);
            this.playerView.setAspectRatio(circleArticle.video.width, circleArticle.video.height);
            if (this.playerView instanceof SimpleVideoPlayerView) {
                indexOf = circleArticle.video.duration;
                ((SimpleVideoPlayerView) this.playerView).setStrTotalTime(String.format("%d:%02d", new Object[]{Integer.valueOf(indexOf / 60), Integer.valueOf(indexOf % 60)}));
            }
        } else {
            this.playerView.setVisibility(8);
        }
        if (this.clockedView != null) {
            if (!circleArticle.isClocked() || circleArticle.clockedInfo == null || circleArticle.clockedInfo.length <= 0) {
                this.clockedView.setVisibility(8);
            } else {
                this.clockedView.setVisibility(0);
                this.clockedView.setClockedTimes(circleArticle.clockedInfo, "最近" + circleArticle.clockedInfo.length + "次打卡");
            }
        }
        this.shareCount.setOnClickListener(new LoginPermissionClickDelegate(new aq(this, circleArticle), null));
        getCellView().setOnLongClickListener(new ar(this, circleArticle));
        if (this.hotComment != null) {
            if (circleArticle.hotComment == null || this.isDetail) {
                this.hotCommentLabel.setVisibility(8);
                this.hotComment.setVisibility(8);
                this.hotCommentImage.setVisibility(8);
            } else {
                this.hotComment.setOnClickListener(new as(this));
                this.hotCommentLabel.setVisibility(0);
                this.hotCommentLabel.setImageResource(UIHelper.isNightTheme() ? R.drawable.hot_comment_label_night : R.drawable.hot_comment_label);
                this.hotComment.setVisibility(0);
                this.hotComment.setTextColor(UIHelper.isNightTheme() ? -9802626 : -9474193);
                this.hotComment.setText(String.format("      %s：%s", new Object[]{circleArticle.hotComment.userName, circleArticle.hotComment.content}));
                if (circleArticle.hotComment.smallImage != null) {
                    this.hotCommentImage.setVisibility(0);
                    LayoutParams layoutParams = this.hotCommentImage.getLayoutParams();
                    length = UIHelper.dip2px(getContext(), 180.0f);
                    i = UIHelper.dip2px(getContext(), 100.0f);
                    if (layoutParams == null) {
                        layoutParams = new LayoutParams(0, 0);
                    }
                    layoutParams.height = circleArticle.hotComment.smallImage.height;
                    layoutParams.width = circleArticle.hotComment.smallImage.width;
                    if (circleArticle.hotComment.smallImage.isVertical()) {
                        if (circleArticle.hotComment.smallImage.height > length) {
                            layoutParams.height = length;
                            layoutParams.width = (int) (((float) length) * circleArticle.hotComment.smallImage.getRatio());
                        }
                    } else if (circleArticle.hotComment.smallImage.width > i) {
                        layoutParams.width = i;
                        layoutParams.height = (int) (((float) i) / circleArticle.hotComment.smallImage.getRatio());
                    }
                    this.hotCommentImage.setLayoutParams(layoutParams);
                    FrescoImageloader.displayImage(this.hotCommentImage, circleArticle.hotComment.smallImage.getImageUrl(), UIHelper.getDefaultImageTileBackground());
                    this.hotCommentImage.setOnClickListener(new LoginPermissionClickDelegate(new at(this, circleArticle), null));
                } else {
                    this.hotCommentImage.setVisibility(8);
                }
            }
        }
        int i2 = this.circleArticle.isTop ? UIHelper.isNightTheme() ? R.drawable.ic_circle_top_night : R.drawable.ic_circle_top : this.circleArticle.isRecommend ? UIHelper.isNightTheme() ? R.drawable.ic_circle_recommend_night : R.drawable.ic_circle_recommend : 0;
        this.additionView.setImageResource(i2);
    }

    public void onClick() {
        CircleArticleActivity.launch(getContext(), (CircleArticle) getItem(), false, false, this.fromCircleTopic);
    }
}
