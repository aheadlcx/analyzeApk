package qsbk.app.adapter;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.facebook.imagepipeline.request.Postprocessor;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeMediaADData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.VideoFullScreenActivity;
import qsbk.app.activity.VideoFullScreenActivity$SimpleVideoInfo;
import qsbk.app.adapter.BaseImageAdapter.ProgressDisplayer;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.image.ImageSizeHelper;
import qsbk.app.model.Article;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.ImageSize;
import qsbk.app.model.Vote;
import qsbk.app.service.VoteManager;
import qsbk.app.ticker.TickerView;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.utils.TimeDelta;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;
import qsbk.app.utils.UserInfoClickListener;
import qsbk.app.utils.Util;
import qsbk.app.video.CircleVideoPlayerView;
import qsbk.app.video.LightActuator;
import qsbk.app.video.VideoLoopStatistics;
import qsbk.app.video.VideoPlayerView;
import qsbk.app.widget.BaseCell;
import qsbk.app.widget.QiushiTopicImageSpan;
import qsbk.app.widget.RatingView;
import qsbk.app.widget.RoundedDrawable;
import qsbk.app.widget.SupportOrNotView;

public class VideoImmersionAdapter extends BaseImageAdapter implements OnScrollListener {
    public static final String STATE_ACTIVE = "active";
    public static final String STATE_ENABLE = "enable";
    public static VideoImmersionCell lastSelect;
    public static GdtVideoImmersionCell lastSelectGdt;
    private HashMap<String, Integer> a = new HashMap();
    private int b;
    private String c;
    private int e;
    private Drawable f;
    private int g;

    public class GdtVideoImmersionCell extends BaseCell {
        ProgressBar a;
        ImageView b;
        MediaView c;
        TextView d;
        TextView e;
        TextView f;
        ImageView g;
        ImageView h;
        RatingView i;
        View j;
        TextView k;
        View l;
        View m;
        View n;
        View o;
        final /* synthetic */ VideoImmersionAdapter p;
        private LightActuator t;
        private LightActuator u;
        private NativeMediaADData v;

        public GdtVideoImmersionCell(VideoImmersionAdapter videoImmersionAdapter) {
            this.p = videoImmersionAdapter;
        }

        public MediaView getMediaView() {
            return this.c;
        }

        public NativeMediaADData getRef() {
            return this.v;
        }

        public void onCreate() {
            setCellView((int) R.layout.feeds_gdt_immersion);
            this.a = (ProgressBar) findViewById(R.id.progress);
            this.b = (ImageView) findViewById(R.id.image);
            this.c = (MediaView) findViewById(R.id.video);
            this.d = (TextView) findViewById(R.id.userName);
            this.f = (TextView) findViewById(R.id.content);
            this.e = (TextView) findViewById(R.id.players);
            this.i = (RatingView) findViewById(R.id.ratting);
            this.g = (ImageView) findViewById(R.id.feedsAdSpread);
            this.j = findViewById(R.id.mainLayout);
            this.k = (TextView) findViewById(R.id.downbt);
            this.h = (ImageView) findViewById(R.id.userIcon);
            this.l = findViewById(R.id.immersion_mask1);
            this.m = findViewById(R.id.immersion_mask2);
            this.n = findViewById(R.id.immersion_mask3);
            this.o = findViewById(R.id.layerMask);
            this.s = TileBackground.getBackgroud(this.b.getContext(), BgImageType.AD);
            this.t = new ds(this, 10, 50);
            this.u = new dt(this, 10, 100);
        }

        public void onUpdate() {
            this.v = (NativeMediaADData) getItem();
            VideoImmersionAdapter.lastSelectGdt = this;
            FrescoImageloader.displayAvatar(this.h, this.v.getIconUrl());
            this.d.setText(this.v.getTitle());
            this.f.setText(this.v.getDesc());
            FrescoImageloader.displayImage(this.b, this.v.getImgUrl(), this.s);
            if (this.v.isAPP()) {
                this.e.setText("本广告由腾讯广点通提供");
                this.i.setVisibility(8);
                a(this.v.getAPPStatus());
            } else {
                this.e.setText("本广告由腾讯广点通提供");
                this.i.setVisibility(8);
                a("查看");
            }
            this.j.setOnClickListener(new du(this));
            this.v.onExposured(getCellView());
            if (this.v.isVideoAD() && this.v.isVideoLoaded()) {
                this.b.setVisibility(8);
                this.c.setVisibility(0);
                this.v.bindView(this.c, false);
                this.v.setVolumeOn(true);
                this.v.setMediaListener(new dx(this));
                return;
            }
            this.b.setVisibility(0);
            this.c.setVisibility(8);
        }

        public void onItemChange(Object obj) {
            this.t.reset();
            this.u.reset();
        }

        private void a(int i) {
            String str = "";
            switch (i) {
                case 1:
                    str = "点击启动";
                    break;
                case 4:
                    str = "下载中";
                    break;
                case 8:
                    str = "点击安装";
                    break;
                case 16:
                    str = "点击重试";
                    break;
                default:
                    str = "下载";
                    break;
            }
            a(str);
        }

        private void a(String str) {
            ((TextView) findViewById(R.id.downbt)).setText(str);
        }
    }

    public class VideoImmersionCell extends BaseCell {
        final /* synthetic */ VideoImmersionAdapter a;
        public Runnable addLoopTask = new dy(this);
        public Article article;
        private LightActuator b;
        private LightActuator c;
        public ImageButton collection_icon;
        public ImageButton comment;
        public TextView commentsCount;
        public TextView content;
        public ImageView currentAvatarView;
        private View d;
        public View divider;
        private View e;
        private View f;
        private ProgressDisplayer g = new ProgressDisplayer();
        public View gifTag;
        private ImageView h;
        public TextView hotComment;
        public ImageView hotCommentLabel;
        public View imageLayout;
        public View imageLoading;
        public ImageView imageView;
        public View layerMask;
        public TextView loop;
        public VideoPlayerView player;
        public ProgressBar progress;
        public TickerView supportCount;
        public SupportOrNotView supportOrNotView;
        public TextView tagContent;
        public View userInfoLayout;
        public TextView userName;

        class a implements OnClickListener {
            int a;
            String b;
            ImageView c;
            View d;
            final /* synthetic */ VideoImmersionCell e;

            public a(VideoImmersionCell videoImmersionCell, ImageView imageView, String str, int i, View view) {
                this.e = videoImmersionCell;
                this.b = str;
                this.c = imageView;
                this.a = i;
                this.d = view;
            }

            public void onClick(View view) {
                this.e.a.l.getOnItemLongClickListener().onItemLongClick(this.e.a.l, this.d, this.a + this.e.a.l.getHeaderViewsCount(), (long) (this.a + this.e.a.l.getHeaderViewsCount()));
            }
        }

        class b implements OnClickListener {
            int a;
            String b;
            ImageView c;
            View d;
            final /* synthetic */ VideoImmersionCell e;

            public b(VideoImmersionCell videoImmersionCell, ImageView imageView, String str, int i, View view) {
                this.e = videoImmersionCell;
                this.b = str;
                this.c = imageView;
                this.a = i;
                this.d = view;
            }

            public void onClick(View view) {
                this.e.a.l.getOnItemClickListener().onItemClick(this.e.a.l, this.d, this.a + this.e.a.l.getHeaderViewsCount(), (long) (this.a + this.e.a.l.getHeaderViewsCount()));
            }
        }

        public VideoImmersionCell(VideoImmersionAdapter videoImmersionAdapter) {
            this.a = videoImmersionAdapter;
        }

        private void a() {
            int size = this.a.m.size() - this.q;
            List arrayList = new ArrayList();
            for (int i = 0; i < size; i++) {
                Object obj = this.a.m.get(this.q + i);
                if (obj instanceof Article) {
                    Article article = (Article) obj;
                    VideoFullScreenActivity$SimpleVideoInfo videoFullScreenActivity$SimpleVideoInfo = new VideoFullScreenActivity$SimpleVideoInfo();
                    videoFullScreenActivity$SimpleVideoInfo.url = article.getVideoUrl();
                    videoFullScreenActivity$SimpleVideoInfo.width = article.absPicWidth;
                    videoFullScreenActivity$SimpleVideoInfo.height = article.absPicHeight;
                    videoFullScreenActivity$SimpleVideoInfo.extraType = VideoFullScreenActivity$SimpleVideoInfo.TYPE_QiushiArticle;
                    try {
                        videoFullScreenActivity$SimpleVideoInfo.extra = article.toJSONObject().toString();
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    if (i == 0) {
                        videoFullScreenActivity$SimpleVideoInfo.currentTime = this.player.getCurrentTime();
                    }
                    arrayList.add(videoFullScreenActivity$SimpleVideoInfo);
                }
            }
            if (arrayList.size() != 0) {
                LogUtil.d("video start immersion activity position = " + this.q);
                VideoFullScreenActivity.launch(this.a.k, (VideoFullScreenActivity$SimpleVideoInfo[]) arrayList.toArray(new VideoFullScreenActivity$SimpleVideoInfo[arrayList.size()]), new eg(this));
            }
        }

        public void onCreate() {
            setCellView((int) R.layout.cell_video_immersion);
            this.userName = (TextView) findViewById(R.id.userName);
            this.content = (TextView) findViewById(R.id.content);
            this.content.setTextColor(-1);
            this.comment = (ImageButton) findViewById(R.id.comment);
            this.imageView = (ImageView) findViewById(R.id.image);
            this.supportCount = (TickerView) findViewById(R.id.points_and_comments_count);
            this.commentsCount = (TextView) findViewById(R.id.comment_count);
            this.supportOrNotView = (SupportOrNotView) findViewById(R.id.support_or_not);
            this.collection_icon = (ImageButton) findViewById(R.id.collection_icon);
            this.userInfoLayout = findViewById(R.id.userInfo);
            this.currentAvatarView = (ImageView) findViewById(R.id.userIcon);
            this.imageLoading = findViewById(R.id.imageLoading);
            this.imageLayout = findViewById(R.id.imageLayout);
            this.gifTag = findViewById(R.id.gif_tag);
            this.hotComment = (TextView) findViewById(R.id.hot_comment);
            this.hotCommentLabel = (ImageView) findViewById(R.id.hot_comment_label);
            this.progress = (ProgressBar) findViewById(R.id.progress);
            this.divider = findViewById(R.id.divider);
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.video_progress);
            this.layerMask = findViewById(R.id.layerMask);
            this.h = (ImageView) findViewById(R.id.video_preview);
            View findViewById = findViewById(R.id.play_video);
            this.loop = (TextView) findViewById(R.id.loop);
            this.player = (VideoPlayerView) findViewById(R.id.videoView);
            this.player.setWidget(progressBar, findViewById, this.h);
            this.player.setLoop(false);
            ((CircleVideoPlayerView) this.player).setOnControlBarListener(new eh(this));
            ((CircleVideoPlayerView) this.player).setOnFullScreenClick(new ei(this));
            this.player.setOnVideoStateListener(new ej(this));
            this.player.setOnVideoEventListener(new ek(this));
            this.d = findViewById(R.id.immersion_mask1);
            this.e = findViewById(R.id.immersion_mask2);
            this.f = findViewById(R.id.immersion_mask3);
            OnTouchListener elVar = new el(this);
            this.d.setOnTouchListener(elVar);
            this.e.setOnTouchListener(elVar);
            this.f.setOnTouchListener(elVar);
            this.b = new em(this, 10, 50);
            this.c = new en(this, 10, 100);
            this.s = TileBackground.getBackgroud(getContext(), BgImageType.ARTICLE);
        }

        public void onUpdate() {
            int i = 0;
            this.imageView.setColorFilter(null);
            this.imageView.setImageDrawable(null);
            this.currentAvatarView.setColorFilter(null);
            this.article = (Article) getItem();
            TimeDelta timeDelta = new TimeDelta();
            initDefaultVoteState();
            initUserInfo();
            initContent();
            ensureImageSize();
            this.progress.setVisibility(8);
            initProgressBar();
            initImage();
            initVote();
            this.supportOrNotView.setOnSupportListener(new dz(this));
            OnTouchListener eaVar = new ea(this);
            b bVar = new b(this, this.comment, this.article.id, this.q, getCellView());
            this.comment.setSelected(false);
            this.comment.setOnClickListener(new eb(this, bVar));
            this.comment.setOnTouchListener(eaVar);
            this.collection_icon.setSelected(false);
            this.collection_icon.setOnClickListener(new a(this, this.collection_icon, this.article.id, this.q, getCellView()));
            this.collection_icon.setOnTouchListener(eaVar);
            this.layerMask.setVisibility(8);
            if (this.divider != null) {
                this.divider.setVisibility(this.q == 0 ? 8 : 0);
            }
            if (this.hotComment != null) {
                LayoutParams layoutParams;
                RelativeLayout.LayoutParams layoutParams2;
                if (this.article.hotComment != null) {
                    this.hotComment.setOnClickListener(new ec(this));
                    this.hotComment.setVisibility(0);
                    this.hotCommentLabel.setVisibility(0);
                    this.hotCommentLabel.setImageResource(R.drawable.hot_comment_label_night);
                    this.hotComment.setText(String.format("      %s：%s", new Object[]{this.article.hotComment.userName, this.article.hotComment.content}));
                    layoutParams = this.f.getLayoutParams();
                    if (layoutParams != null || (layoutParams instanceof RelativeLayout.LayoutParams)) {
                        layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                    } else {
                        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                    }
                    layoutParams.addRule(8, R.id.hot_comment_layout);
                    this.f.setLayoutParams(layoutParams);
                } else {
                    this.hotComment.setVisibility(8);
                    this.hotCommentLabel.setVisibility(8);
                    layoutParams = this.f.getLayoutParams();
                    if (layoutParams != null || (layoutParams instanceof RelativeLayout.LayoutParams)) {
                        layoutParams2 = (RelativeLayout.LayoutParams) layoutParams;
                    } else {
                        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                    }
                    layoutParams.addRule(8, R.id.operation_bar);
                    this.f.setLayoutParams(layoutParams);
                }
            }
            if (this.article.isVideoArticle()) {
                this.player.setVisibility(0);
                ImageView imageView = (ImageView) this.player.getPreviewView();
                imageView.setImageDrawable(null);
                this.player.setVideo(this.article.getVideoUrl());
                if (this.article.absPicWidth != 0 && this.article.absPicHeight != 0) {
                    this.player.setAspectRatio(this.article.absPicWidth, this.article.absPicHeight);
                } else if (this.article.image_size != null) {
                    if (this.article.image_size.mediumSize() != null) {
                        this.player.setAspectRatio(this.article.image_size.mediumSize().getWidth(), this.article.image_size.mediumSize().getHeight());
                    } else if (this.article.image_size.smallSize() != null) {
                        this.player.setAspectRatio(this.article.image_size.smallSize().getWidth(), this.article.image_size.smallSize().getHeight());
                    }
                }
                displayImage(imageView, this.article.absPicPath, this.a.f);
            } else {
                this.player.getPlayBtn().setVisibility(8);
                this.player.getProgressBar().setVisibility(8);
                this.player.setVisibility(8);
                this.player.reset();
            }
            View view = this.gifTag;
            if (!this.article.isGIFArticle()) {
                i = 8;
            }
            view.setVisibility(i);
        }

        public void onItemChange(Object obj) {
            this.c.reset();
            this.b.reset();
        }

        public void initDefaultVoteState() {
            Article article = (Article) getItem();
            this.supportOrNotView.reset();
            if (VoteManager.getInstance().containsVote(article.id, "up")) {
                this.supportOrNotView.setSupport();
            }
            if (VoteManager.getInstance().containsVote(article.id, Config.DEVICE_NAME) && !VoteManager.getInstance().containsVote(article.id, "_up")) {
                this.supportOrNotView.setSupport();
            }
            if (QsbkApp.allCollection.contains(article.id)) {
                this.collection_icon.setTag("active");
            } else {
                this.collection_icon.setTag("enable");
            }
        }

        public void initUserInfo() {
            this.userInfoLayout.setVisibility(0);
            if (TextUtils.isEmpty(this.article.login) || "Guest".equals(this.article.login)) {
                this.userName.setText(BaseUserInfo.ANONYMOUS_USER_NAME);
                this.userName.setTextColor(-1);
                this.currentAvatarView.setImageDrawable(RoundedDrawable.fromDrawable(this.currentAvatarView.getResources().getDrawable(UIHelper.getIconRssAnonymousUser())));
                this.userInfoLayout.setOnClickListener(null);
                return;
            }
            CharSequence remark = RemarkManager.getRemark(this.article.user_id);
            TextView textView = this.userName;
            if (TextUtils.isEmpty(remark)) {
                remark = this.article.login;
            }
            textView.setText(remark);
            this.userName.setTextColor(-1);
            a(this.article.user_id, this.article.user_icon, this.currentAvatarView);
            this.userInfoLayout.setOnClickListener(new UserClickDelegate(this.article.user_id, new UserInfoClickListener(this.article.user_id, this.article.login, this.article.user_icon, this.article.id)));
        }

        protected void a(String str, String str2, ImageView imageView) {
            displayAvatar(imageView, QsbkApp.absoluteUrlOfMediumUserIcon(str2, str));
        }

        public void initContent() {
            if (TextUtils.isEmpty(this.article.getContent()) || "null".equals(this.article.getContent().trim())) {
                this.content.setVisibility(8);
                return;
            }
            this.content.setVisibility(0);
            this.content.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
            this.content.setEllipsize(TruncateAt.END);
            this.content.setMaxLines(10);
            this.content.setOnClickListener(new ed(this));
            if (this.article.qiushiTopic != null) {
                CharSequence spannableStringBuilder = new SpannableStringBuilder();
                spannableStringBuilder.append("搜");
                QiushiTopicImageSpan qiushiTopicImageSpan = new QiushiTopicImageSpan(this.a.k.getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.ic_topic_prefix_night : R.drawable.ic_topic_prefix));
                qiushiTopicImageSpan.setSubSize(UIHelper.dip2px(this.a.k, 5.0f));
                qiushiTopicImageSpan.setmPaint(this.content.getPaint());
                spannableStringBuilder.setSpan(qiushiTopicImageSpan, 0, 1, 33);
                String str = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.article.qiushiTopic.content;
                spannableStringBuilder.append(str);
                spannableStringBuilder.setSpan(new ee(this), 0, str.length() + 1, 33);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(UIHelper.isNightTheme() ? -4424933 : -17664), 1, str.length() + 1, 33);
                spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.article.getContent());
                this.content.setText(spannableStringBuilder);
                this.content.setMovementMethod(LinkMovementMethod.getInstance());
                return;
            }
            this.content.setText(this.article.getContent());
        }

        public void ensureImageSize() {
            int[] fullWidthVideoWidthHeightMax;
            int i;
            int videoLayoutParams;
            if (this.article.isVideoArticle()) {
                ImageSize imageSize;
                fullWidthVideoWidthHeightMax = ImageSizeHelper.getFullWidthVideoWidthHeightMax();
                i = fullWidthVideoWidthHeightMax[0];
                int i2 = fullWidthVideoWidthHeightMax[1];
                int[] videoWidthAndHeight = this.article.getVideoWidthAndHeight();
                if (videoWidthAndHeight[0] == 0 || videoWidthAndHeight[1] == 0) {
                    imageSize = new ImageSize(i, (i2 * 4) / 9);
                } else {
                    imageSize = new ImageSize(videoWidthAndHeight[0], videoWidthAndHeight[1]);
                }
                videoLayoutParams = setVideoLayoutParams(this.imageView, imageSize, Util.displaySize.x, i2);
                if (videoWidthAndHeight[0] < videoWidthAndHeight[1]) {
                    this.a.a.put(this.article.absPicPath, Integer.valueOf(videoLayoutParams));
                }
            } else if (!TextUtils.isEmpty(this.article.image) && !this.article.image.equalsIgnoreCase("null")) {
                fullWidthVideoWidthHeightMax = ImageSizeHelper.getRequestWidthAndMaxPixcel();
                i = fullWidthVideoWidthHeightMax[0];
                videoLayoutParams = fullWidthVideoWidthHeightMax[1];
                if (this.article.image_size != null) {
                    setImageLayoutParams(this.imageView, this.article.image_size.smallSize(), i, videoLayoutParams);
                    return;
                }
                setImageLayoutParams(this.imageView, new ImageSize(i, (videoLayoutParams * 4) / 9), i, videoLayoutParams);
            }
        }

        public void setImageLayoutParams(ImageView imageView, ImageSize imageSize, int i, int i2) {
            LayoutParams layoutParams = imageView.getLayoutParams();
            int[] iArr = new int[2];
            ImageSizeHelper.calWidthAndHeight(i, i2, iArr, imageSize, true);
            if (layoutParams != null) {
                layoutParams.width = iArr[0];
                layoutParams.height = iArr[1];
            } else {
                layoutParams = new LayoutParams(iArr[0], iArr[1]);
            }
            imageView.setLayoutParams(layoutParams);
        }

        public int setVideoLayoutParams(ImageView imageView, ImageSize imageSize, int i, int i2) {
            LayoutParams layoutParams;
            LayoutParams layoutParams2 = imageView.getLayoutParams();
            int[] iArr = new int[2];
            int calWidthAndHeight = ImageSizeHelper.calWidthAndHeight(i, i2, iArr, imageSize, imageSize.getWidth() >= imageSize.getHeight());
            if (layoutParams2 != null) {
                layoutParams2.width = iArr[0];
                layoutParams2.height = iArr[1];
                layoutParams = layoutParams2;
            } else {
                layoutParams = new LayoutParams(iArr[0], iArr[1]);
            }
            imageView.setLayoutParams(layoutParams);
            return calWidthAndHeight;
        }

        public void initProgressBar() {
            Object obj = this.article.isVideoArticle() ? this.article.absPicPath : this.article.image;
            if (TextUtils.isEmpty(obj) || "null".equalsIgnoreCase(obj)) {
                this.imageView.setTag(null);
                this.progress.setTag(null);
                return;
            }
            this.progress.setTag(obj);
            this.imageView.setTag(this.progress);
        }

        public void initImage() {
            if (this.article.isVideoArticle()) {
                String str = this.article.absPicPath;
                if (TextUtils.isEmpty(str) || "null".equalsIgnoreCase(str)) {
                    this.imageView.setVisibility(8);
                    this.imageLayout.setVisibility(8);
                    return;
                }
                this.imageLayout.setVisibility(0);
                this.imageView.setVisibility(0);
                if (BaseImageAdapter.doNotLoadImageDirectly()) {
                    this.imageLoading.setVisibility(0);
                    ((TextView) this.imageLoading).setText("点击加载");
                } else {
                    this.imageLoading.setVisibility(8);
                }
                a(this.article.id, str, this.imageView, this.imageLoading);
            } else if (TextUtils.isEmpty(this.article.image) || "null".equalsIgnoreCase(this.article.image)) {
                this.imageView.setVisibility(8);
                this.imageLayout.setVisibility(8);
            } else {
                this.imageLayout.setVisibility(0);
                this.imageView.setVisibility(0);
                if (BaseImageAdapter.doNotLoadImageDirectly()) {
                    this.imageLoading.setVisibility(0);
                    ((TextView) this.imageLoading).setText("点击加载图片");
                } else {
                    this.imageLoading.setVisibility(8);
                }
                a(this.article.id, this.a.imageNameToUrl(this.article.id, this.article.image), this.imageView, this.imageLoading);
            }
        }

        protected void a(String str, String str2, ImageView imageView, View view) {
            if (TextUtils.isEmpty(str2) || "null".equalsIgnoreCase(str2) || imageView == null) {
                if (imageView != null) {
                    imageView.setVisibility(4);
                }
                view.setVisibility(8);
                return;
            }
            Postprocessor postprocessor = null;
            if (((Integer) this.a.a.get(str2)) != null) {
                postprocessor = new ef(this, 32, str2);
            }
            displayImage(imageView, str2, this.a.f, this.a.f, postprocessor);
        }

        public void initVote() {
            int i = this.article.comment_count;
            this.loop.removeCallbacks(this.addLoopTask);
            if (this.article.isVideoArticle()) {
                CharSequence loopString = this.article.getLoopString();
                if (!loopString.startsWith(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
                    loopString = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + loopString;
                }
                this.loop.setText(loopString);
            } else {
                this.loop.setText("");
            }
            if (this.supportOrNotView.isSupport()) {
                UIHelper.setSupportAndCommentTextHighlight(this.supportCount, this.commentsCount, this.article.getDisplayLaugth(), i, this.article.shareCount, false);
            } else {
                UIHelper.setSupportAndCommentText(this.supportCount, this.commentsCount, this.article.getDisplayLaugth(), i, this.article.shareCount, false);
            }
        }

        public void toLight() {
            this.c.light();
            if (!(VideoImmersionAdapter.lastSelect == this || VideoImmersionAdapter.lastSelect == null)) {
                VideoImmersionAdapter.lastSelect.toOffLight(true);
            }
            VideoImmersionAdapter.lastSelect = this;
            this.b.light();
        }

        public void toOffLight(boolean z) {
            this.b.offLight();
            if (z) {
                this.c.offLight();
            }
        }

        private void a(int i) {
            this.loop.postDelayed(this.addLoopTask, (long) (Math.random() * 3000.0d));
        }

        private void b() {
            if (this.article != null) {
                int generateLoopRandom = this.article.generateLoopRandom();
                Article article = this.article;
                article.loop += (long) generateLoopRandom;
                a(generateLoopRandom);
                VideoLoopStatistics.getInstance().loopBatch(this.article.id, generateLoopRandom);
            }
        }

        private boolean a(int i, String str, String str2, String str3) {
            return VoteManager.getInstance().vote(new Vote(this.a.c + (i + 1), str, str2, "1"), str, str2);
        }

        public void download() {
            ((CircleVideoPlayerView) this.player).download();
        }
    }

    public VideoImmersionAdapter(ArrayList<Object> arrayList, Activity activity, ListView listView, int i, String str) {
        super(arrayList, activity);
        this.l = listView;
        this.b = i;
        this.c = str;
        this.f = new ColorDrawable(1315864);
    }

    public void setOffset(int i) {
        this.e = i;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getItemViewType(int i) {
        Object item = getItem(i);
        if (!(item instanceof Article) && (item instanceof NativeMediaADData)) {
            return 1;
        }
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        BaseCell videoImmersionCell;
        switch (getItemViewType(i)) {
            case 0:
                if (view == null || !(view.getTag() instanceof VideoImmersionCell)) {
                    videoImmersionCell = new VideoImmersionCell(this);
                    videoImmersionCell.performCreate(i, viewGroup, getItem(i));
                    view = videoImmersionCell.getCellView();
                    view.setTag(videoImmersionCell);
                } else {
                    videoImmersionCell = (BaseCell) view.getTag();
                }
                videoImmersionCell.performUpdate(i, viewGroup, getItem(i));
                break;
            case 1:
                if (view == null || !(view.getTag() instanceof GdtVideoImmersionCell)) {
                    videoImmersionCell = new GdtVideoImmersionCell(this);
                    videoImmersionCell.performCreate(i, viewGroup, getItem(i));
                    view = videoImmersionCell.getCellView();
                    view.setTag(videoImmersionCell);
                } else {
                    videoImmersionCell = (BaseCell) view.getTag();
                }
                videoImmersionCell.performUpdate(i, viewGroup, getItem(i));
                break;
        }
        return view;
    }

    public void onDestroy() {
        this.a.clear();
        super.onDestroy();
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (lastSelect != null) {
            ((CircleVideoPlayerView) lastSelect.player).showControlBar(false, true);
        }
        this.g = i;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (lastSelect != null && this.g == 1) {
            ((CircleVideoPlayerView) lastSelect.player).showControlBar(false, true);
        }
    }
}
