package qsbk.app.widget.qiuyoucircle;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.ViewGroup.LayoutParams;
import com.baidu.mobstat.StatService;
import com.qiushibaike.statsdk.StatSDK;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.PicUrl;
import qsbk.app.share.ShareUtils$OnCircleShareStartListener;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.UIHelper;

public class WebAdCell extends ShareCell {
    public WebAdCell(ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener) {
        super(shareUtils$OnCircleShareStartListener);
    }

    public WebAdCell(ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener, boolean z) {
        super(shareUtils$OnCircleShareStartListener, z);
    }

    public WebAdCell(ShareUtils$OnCircleShareStartListener shareUtils$OnCircleShareStartListener, boolean z, boolean z2) {
        super(shareUtils$OnCircleShareStartListener, z, z2);
    }

    public void onUpdate() {
        int indexOf;
        int length;
        super.onUpdate();
        CircleArticle circleArticle = (CircleArticle) getItem();
        boolean equals = TextUtils.equals(this.lastArticleId, circleArticle.id);
        if (!TextUtils.isEmpty(circleArticle.adPic)) {
            displayImage(this.articleImageView, circleArticle.adPic);
        } else if (circleArticle.hasImage()) {
            displayImage(this.articleImageView, ((PicUrl) circleArticle.picUrls.get(0)).url);
        } else {
            displayImage(this.articleImageView, FrescoImageloader.getFrescoResUrl(UIHelper.getShare2IMIcon()));
        }
        this.articleTitleView.setText(circleArticle.shareContent);
        this.articlePlayView.setVisibility(8);
        this.gifTag.setVisibility(8);
        if (circleArticle.type == 13) {
            this.gifTag.setVisibility(0);
        } else if (circleArticle.type == 8) {
            this.articlePlayView.setVisibility(0);
            this.articlePlayView.setImageResource(R.drawable.video_play_normal);
        } else if (circleArticle.type == 11) {
            this.articlePlayView.setVisibility(0);
            this.articlePlayView.setImageResource(R.drawable.live_begin);
        } else {
            this.articlePlayView.setVisibility(8);
        }
        if (this.clockedView != null) {
            if (!circleArticle.isClocked() || circleArticle.clockedInfo == null || circleArticle.clockedInfo.length <= 0) {
                this.clockedView.setVisibility(8);
            } else {
                this.clockedView.setVisibility(0);
                this.clockedView.setClockedTimes(circleArticle.clockedInfo, "最近" + circleArticle.clockedInfo.length + "次打卡");
            }
        }
        if (!equals) {
            if (circleArticle.topic != null || (circleArticle.atInfoTexts != null && circleArticle.atInfoTexts.size() > 0)) {
                CharSequence spannableString = new SpannableString(circleArticle.content);
                if (circleArticle.topic != null) {
                    indexOf = circleArticle.content.indexOf(circleArticle.topic.content);
                    if (indexOf != -1) {
                        length = circleArticle.topic.content.length() + indexOf;
                        spannableString.setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), indexOf, length, 33);
                        if (!(getContext() instanceof CircleTopicActivity)) {
                            spannableString.setSpan(new bt(this, circleArticle), indexOf, length, 33);
                            this.contentView.setMovementMethod(LinkMovementMethod.getInstance());
                        }
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
                                    spannableString.setSpan(new bv(this, atInfo, circleArticle), start, end, 33);
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
        }
        this.contentView.setOnClickListener(new bw(this));
        this.contentView.setOnLongClickListener(new bx(this));
        if (this.isDetail) {
            this.contentView.setMaxLines(Integer.MAX_VALUE);
        } else {
            this.moreView.setVisibility(8);
            this.contentView.setMaxLines(getContentMaxLine());
            this.contentView.setOnTextMoreListener(new by(this));
        }
        this.shareCount.setOnClickListener(new LoginPermissionClickDelegate(new bz(this), null));
        getCellView().setOnLongClickListener(new ca(this));
        if (this.hotComment != null) {
            if (circleArticle.hotComment == null || this.isDetail) {
                this.hotCommentLabel.setVisibility(8);
                this.hotComment.setVisibility(8);
                this.hotCommentImage.setVisibility(8);
            } else {
                this.hotComment.setOnClickListener(new cb(this, circleArticle));
                this.hotCommentLabel.setVisibility(0);
                this.hotCommentLabel.setImageResource(UIHelper.isNightTheme() ? R.drawable.hot_comment_label_night : R.drawable.hot_comment_label);
                this.hotComment.setVisibility(0);
                this.hotComment.setTextColor(UIHelper.isNightTheme() ? -9802626 : -9474193);
                this.hotComment.setText(String.format("      %s：%s", new Object[]{circleArticle.hotComment.userName, circleArticle.hotComment.content}));
                if (circleArticle.hotComment.smallImage != null) {
                    this.hotCommentImage.setVisibility(0);
                    LayoutParams layoutParams = this.hotCommentImage.getLayoutParams();
                    length = UIHelper.dip2px(getContext(), 180.0f);
                    int dip2px = UIHelper.dip2px(getContext(), 100.0f);
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
                    } else if (circleArticle.hotComment.smallImage.width > dip2px) {
                        layoutParams.width = dip2px;
                        layoutParams.height = (int) (((float) dip2px) / circleArticle.hotComment.smallImage.getRatio());
                    }
                    this.hotCommentImage.setLayoutParams(layoutParams);
                    FrescoImageloader.displayImage(this.hotCommentImage, circleArticle.hotComment.smallImage.getImageUrl(), UIHelper.getDefaultImageTileBackground());
                    this.hotCommentImage.setOnClickListener(new LoginPermissionClickDelegate(new cc(this, circleArticle), null));
                } else {
                    this.hotCommentImage.setVisibility(8);
                }
            }
        }
        this.lastArticleId = circleArticle.id;
        this.shareLayout.setOnClickListener(new bu(this, circleArticle));
        indexOf = circleArticle.isTop ? UIHelper.isNightTheme() ? R.drawable.ic_circle_top_night : R.drawable.ic_circle_top : circleArticle.isRecommend ? UIHelper.isNightTheme() ? R.drawable.ic_circle_recommend_night : R.drawable.ic_circle_recommend : 0;
        this.additionView.setImageResource(indexOf);
        StatService.onEvent(QsbkApp.mContext, "circle_ad", "show_" + circleArticle.id);
        StatSDK.onEvent(QsbkApp.mContext, "circle_ad", "show_" + circleArticle.id);
    }
}
