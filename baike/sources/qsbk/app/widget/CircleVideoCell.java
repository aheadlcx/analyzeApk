package qsbk.app.widget;

import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.CircleArticleActivity;
import qsbk.app.activity.CircleTopicActivity;
import qsbk.app.im.GroupConversationActivity.AtInfo;
import qsbk.app.image.ImageSizeHelper;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.ImageSize;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.UIHelper;
import qsbk.app.video.QiuyouCircleVideoLoopStatistics;
import qsbk.app.video.SimpleVideoPlayer;
import qsbk.app.video.SimpleVideoPlayer.OnVideoEventListener;
import qsbk.app.video.SimpleVideoPlayerView;
import qsbk.app.video.VideoPlayerView;

public class CircleVideoCell extends BaseCell implements OnVideoEventListener {
    private SimpleDraweeView a;
    public CircleArticle article;
    private TextView b;
    private TextView c;
    public RelativeLayout contentLayout;
    private TextView d;
    private TextView e;
    private ImageView f;
    private SimpleDraweeView g;
    private TextView h;
    private Drawable i;
    public VideoPlayerView player;

    public CircleArticle getItem() {
        return (CircleArticle) super.getItem();
    }

    public void onCreate() {
        setCellView((int) R.layout.qiushi_circle_video);
        this.article = getItem();
        this.a = (SimpleDraweeView) findViewById(R.id.userIcon);
        this.b = (TextView) findViewById(R.id.userName);
        this.c = (TextView) findViewById(R.id.type);
        this.d = (TextView) findViewById(R.id.content);
        this.player = (VideoPlayerView) findViewById(R.id.videoView);
        this.h = (TextView) findViewById(R.id.more_video);
        this.i = TileBackground.getBackgroud(getContext(), BgImageType.ARTICLE);
        this.contentLayout = (RelativeLayout) findViewById(R.id.imageLayout);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.video_progress);
        findViewById(R.id.layerMask).setVisibility(8);
        View findViewById = findViewById(R.id.play_video);
        this.g = (SimpleDraweeView) findViewById(R.id.image);
        this.e = (TextView) findViewById(R.id.duration);
        this.f = (ImageView) findViewById(R.id.video_play_flag);
        ((SimpleVideoPlayerView) this.player).setWidget(progressBar, findViewById, this.g, this.e, this.f);
        this.player.setOnVideoEventListener(this);
        this.player.setOnClickListener(new bc(this));
    }

    public void onUpdate() {
        int indexOf;
        this.article = getItem();
        if (this.article.isVideoArticle()) {
            QiuyouCircleVideoLoopStatistics.getInstance().loopBatch(this.article.id, 1, 0);
        }
        if (this.article.user.isAnonymous()) {
            this.a.setImageDrawable(RoundedDrawable.fromDrawable(this.a.getResources().getDrawable(UIHelper.getIconRssAnonymousUser())));
            this.b.setText(BaseUserInfo.ANONYMOUS_USER_NAME);
            this.a.setOnClickListener(null);
            this.b.setOnClickListener(null);
        } else {
            Object absoluteUrlOfMediumUserIcon = QsbkApp.absoluteUrlOfMediumUserIcon(this.article.user.userIcon, this.article.user.userId);
            if (TextUtils.isEmpty(absoluteUrlOfMediumUserIcon)) {
                this.a.setImageResource(UIHelper.getDefaultAvatar());
            } else {
                displayAvatar(this.a, absoluteUrlOfMediumUserIcon);
            }
            this.b.setText(this.article.user.userName);
            this.a.setOnClickListener(new bd(this));
            this.b.setOnClickListener(new be(this));
        }
        this.c.setText("糗友圈");
        this.c.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.qiushi_circle_video_night : R.drawable.qiushi_circle_video), null, null, null);
        this.c.setOnClickListener(new bf(this));
        this.h.setText("查看更多视频");
        this.h.setCompoundDrawablesWithIntrinsicBounds(getContext().getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.live_arrow_night : R.drawable.live_arrow), null, null, null);
        this.h.setOnClickListener(new bg(this));
        if (this.article.topic != null || (this.article.atInfoTexts != null && this.article.atInfoTexts.size() > 0)) {
            int length;
            CharSequence spannableString = new SpannableString(this.article.content);
            if (this.article.topic != null) {
                indexOf = this.article.content.indexOf(this.article.topic.content);
                if (indexOf != -1) {
                    length = this.article.topic.content.length() + indexOf;
                    spannableString.setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), indexOf, length, 33);
                    if (!(getContext() instanceof CircleTopicActivity)) {
                        spannableString.setSpan(new bi(this), indexOf, length, 33);
                        this.d.setMovementMethod(LinkMovementMethod.getInstance());
                    }
                }
            }
            if (this.article.atInfoTexts != null && this.article.atInfoTexts.size() > 0) {
                for (length = 0; length < this.article.atInfoTexts.size(); length++) {
                    AtInfo atInfo = (AtInfo) this.article.atInfoTexts.get(length);
                    try {
                        Matcher matcher = Pattern.compile("@" + atInfo.name).matcher(this.article.content);
                        while (matcher.find()) {
                            int start = matcher.start(0);
                            int end = matcher.end(0);
                            spannableString.setSpan(new ForegroundColorSpan(UIHelper.getTopicLinkColor()), start, end, 33);
                            if (!(getContext() instanceof CircleTopicActivity)) {
                                spannableString.setSpan(new bj(this, atInfo), start, end, 33);
                                this.d.setMovementMethod(LinkMovementMethod.getInstance());
                            }
                        }
                    } catch (PatternSyntaxException e) {
                    }
                }
            }
            this.d.setText(spannableString);
        } else {
            this.d.setMovementMethod(null);
            this.d.setText(this.article.content);
        }
        this.d.setOnClickListener(new bk(this));
        if (this.article.video != null) {
            this.player.setVisibility(0);
            displayImage((ImageView) this.player.getPreviewView(), this.article.video.picUrl, this.i);
            this.player.setVideo(this.article.video.highUrl);
            a(getItem(), this.g, this.contentLayout);
            this.player.setAspectRatio(this.article.video.width, this.article.video.height);
            if (this.player instanceof SimpleVideoPlayerView) {
                indexOf = this.article.video.duration;
                ((SimpleVideoPlayerView) this.player).setStrTotalTime(String.format("%d:%02d", new Object[]{Integer.valueOf(indexOf / 60), Integer.valueOf(indexOf % 60)}));
            }
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
            this.f.setVisibility(8);
            this.player.getPlayBtn().setVisibility(8);
            this.player.getProgressBar().setVisibility(8);
            this.player.setVisibility(8);
            this.player.reset();
        }
        getCellView().setOnClickListener(new bl(this));
    }

    public void onClick() {
        CircleArticleActivity.launch(getContext(), getItem(), false);
    }

    public void start() {
        this.player.play();
    }

    public void stop() {
        this.player.reset();
    }

    public void onVideoCompletion(SimpleVideoPlayer simpleVideoPlayer) {
    }

    public void onVideoPrepared(SimpleVideoPlayer simpleVideoPlayer) {
    }

    public void onVideoError(SimpleVideoPlayer simpleVideoPlayer, int i, int i2) {
    }

    public void onVideoBuffering(SimpleVideoPlayer simpleVideoPlayer, int i) {
    }

    protected void a(CircleArticle circleArticle, ImageView imageView, View view) {
        if (circleArticle.video.height <= 0 || circleArticle.video.width <= 0) {
            view.setVisibility(8);
            imageView.setVisibility(8);
            return;
        }
        imageView.setVisibility(0);
        view.setVisibility(0);
        int[] requestWidthAndMaxPixcel = ImageSizeHelper.getRequestWidthAndMaxPixcel();
        setImageLayoutParams(imageView, new ImageSize(circleArticle.video.width, circleArticle.video.height), requestWidthAndMaxPixcel[0], requestWidthAndMaxPixcel[1]);
    }

    public void setImageLayoutParams(ImageView imageView, ImageSize imageSize, int i, int i2) {
        LayoutParams layoutParams = imageView.getLayoutParams();
        int[] iArr = new int[2];
        ImageSizeHelper.calWidthAndHeight(i, i2, iArr, imageSize);
        if (layoutParams != null) {
            layoutParams.width = iArr[0];
            layoutParams.height = iArr[1];
        } else {
            layoutParams = new LayoutParams(iArr[0], iArr[1]);
        }
        imageView.setLayoutParams(layoutParams);
    }
}
