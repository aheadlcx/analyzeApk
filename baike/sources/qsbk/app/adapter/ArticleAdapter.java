package qsbk.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.util.Pair;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.ad.feedsad.FeedsAd;
import qsbk.app.ad.feedsad.baiduad.BaiduAdItemData;
import qsbk.app.ad.feedsad.gdtad.GdtAdItemData;
import qsbk.app.ad.feedsad.qbad.QbAdItem;
import qsbk.app.ad.feedsad.qhad.QhAdItemData;
import qsbk.app.adapter.BaseImageAdapter.ProgressDisplayer;
import qsbk.app.adapter.QiushiTopicNormalAdapter.OnTabSelectListener;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.HttpTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.image.ImageSizeHelper;
import qsbk.app.model.AcrossTips;
import qsbk.app.model.Article;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.CircleArticle;
import qsbk.app.model.CircleTopic;
import qsbk.app.model.CircleTopicBanner;
import qsbk.app.model.CircleTopicPackage;
import qsbk.app.model.EditorMsg;
import qsbk.app.model.EvaluateCard;
import qsbk.app.model.GroupRecommend;
import qsbk.app.model.ImageSize;
import qsbk.app.model.LivePackage;
import qsbk.app.model.News;
import qsbk.app.model.QiushiEmpty;
import qsbk.app.model.QiushiTopic;
import qsbk.app.model.QiushiTopicBanner;
import qsbk.app.model.QiushiTopicTab;
import qsbk.app.model.Qsjx;
import qsbk.app.model.ReadLine;
import qsbk.app.model.ReportCallCard;
import qsbk.app.model.UserLoginGuideCard;
import qsbk.app.model.Vote;
import qsbk.app.model.WelcomeCard;
import qsbk.app.service.VoteManager;
import qsbk.app.share.ShareUtils;
import qsbk.app.share.ShareUtils$OnShareListener;
import qsbk.app.slide.SlideActivity;
import qsbk.app.ticker.TickerView;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.LoginPermissionClickDelegate;
import qsbk.app.utils.RemarkManager;
import qsbk.app.utils.ReportAdForMedalUtil.AD_PROVIDER;
import qsbk.app.utils.TimeDelta;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserClickDelegate;
import qsbk.app.utils.UserInfoClickListener;
import qsbk.app.utils.Util;
import qsbk.app.video.SimpleVideoPlayer;
import qsbk.app.video.SimpleVideoPlayer.OnVideoEventListener;
import qsbk.app.video.SimpleVideoPlayerView;
import qsbk.app.video.VideoLoopStatistics;
import qsbk.app.video.VideoPlayerView;
import qsbk.app.widget.CircleTopicMoreCell;
import qsbk.app.widget.CircleTopicRecommendCell;
import qsbk.app.widget.CircleTopicTextBgCell;
import qsbk.app.widget.CircleTopicThreeImageCell;
import qsbk.app.widget.CircleVideoCell;
import qsbk.app.widget.EditorLinkView;
import qsbk.app.widget.GroupRecommendQiushiCell;
import qsbk.app.widget.LiveRecommendCell;
import qsbk.app.widget.ObservableTextView;
import qsbk.app.widget.QiushiImageLayout;
import qsbk.app.widget.QiushiTopicImageSpan;
import qsbk.app.widget.QiushiTopicPagerBannerCell;
import qsbk.app.widget.QiushiTopicRecommendCell;
import qsbk.app.widget.QsjxCell;
import qsbk.app.widget.RoundedDrawable;
import qsbk.app.widget.SupportOrNotView;
import qsbk.app.widget.TopicCell;
import qsbk.app.widget.qbnews.recommend.OneImageNewsRecommendCell;
import qsbk.app.widget.qbnews.recommend.ThreeImageNewsRecommendCell;

public class ArticleAdapter extends BaseImageAdapter implements ShareUtils$OnShareListener {
    public static final float[] BT_SELECTED = new float[]{1.0f, 0.0f, 0.0f, 0.0f, -35.0f, 0.0f, 1.0f, 0.0f, 0.0f, -35.0f, 0.0f, 0.0f, 1.0f, 0.0f, -35.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    public static final String STATE_ACTIVE = "active";
    public static final String STATE_ENABLE = "enable";
    public static final int TYPE_ACROSS_TIPS = 15;
    public static final int TYPE_BAIDU_AD = 3;
    public static final int TYPE_CIRCLE_TOPIC = 10;
    public static final int TYPE_CIRCLE_TOPIC_BANNER = 26;
    public static final int TYPE_CIRCLE_TOPIC_TEXT_BACKGROUD = 13;
    public static final int TYPE_CIRCLE_TOPIC_TEXT_IMAGE = 11;
    public static final int TYPE_CIRCLE_TOPIC_TEXT_THREE_IMAGE = 12;
    public static final int TYPE_CIRCLE_VIDEO = 17;
    public static final int TYPE_CONTENT = 0;
    public static final int TYPE_CONTENT_GIF = 19;
    public static final int TYPE_EDIT_MESSAGE = 9;
    public static final int TYPE_EVALUATE = 2;
    public static final int TYPE_GDT_AD = 1;
    public static final int TYPE_GROUP_RECOMMEND = 8;
    public static final int TYPE_LIVE_RECOMMEND = 16;
    public static final int TYPE_LOGIN_GUIDE = 4;
    public static final int TYPE_ONE_IMAGE_NEWS_RECOMMEND = 21;
    public static final int TYPE_QB_AD = 6;
    public static final int TYPE_QH_360_AD = 20;
    public static final int TYPE_QIUSHITPIC_BANNER = 27;
    public static final int TYPE_QIUSHI_EMPTY = 14;
    public static final int TYPE_QIUSHI_TOPIC_RECOMMEND = 24;
    public static final int TYPE_QIUSHI_TOPIC_TAB = 23;
    public static final int TYPE_QSJX = 18;
    public static final int TYPE_READ_LINE = 7;
    public static final int TYPE_REPORT_CALL_CARD = 25;
    public static final int TYPE_THREE_IMAGE_NEWS_RECOMMEND = 22;
    public static final int TYPE_WELCOME = 5;
    protected static String a;
    private static final String e = ArticleAdapter.class.getSimpleName();
    protected AcrossChangeDate b;
    protected OnTabSelectListener c;
    private String f;
    private ProgressDisplayer g;
    private OnTouchListener h;
    public String mScenario;

    public interface AcrossChangeDate {
        void onChangeDate();
    }

    public class AcrossTipsViewHolder {
        TextView a;
        TextView b;
        final /* synthetic */ ArticleAdapter c;

        public AcrossTipsViewHolder(ArticleAdapter articleAdapter) {
            this.c = articleAdapter;
        }
    }

    public class ViewHolder implements OnVideoEventListener {
        final /* synthetic */ ArticleAdapter a;
        public Runnable addLoopTask = new ad(this);
        public Article article;
        private FrameLayout b;
        public ImageButton collection_icon;
        public View comment;
        public TextView commentCount;
        public ObservableTextView content;
        public ImageView currentAvatarView;
        public View divider;
        public TextView durationView;
        public ImageView eventView;
        public View gifTag;
        public TextView hotComment;
        public View hotCommentContainer;
        public ImageView hotCommentImage;
        public ImageView hotCommentLabel;
        public View imageLoading;
        public View layerMask;
        public TextView loop;
        public ImageView playFalgView;
        public ProgressBar progress;
        public QiushiImageLayout qiushiImageLayout;
        public TickerView supportAndCommentsCount;
        public SupportOrNotView supportOrNotView;
        public TextView tagContent;
        public ImageView unlikeView;
        public View userInfoLayout;
        public TextView userName;
        public View videoLayout;
        public VideoPlayerView videoPlayer;
        public ImageView videoPreView;

        public ViewHolder(ArticleAdapter articleAdapter, View view) {
            this.a = articleAdapter;
            this.userName = (TextView) view.findViewById(R.id.userName);
            this.b = (FrameLayout) view.findViewById(R.id.contentLayout);
            this.content = (ObservableTextView) view.findViewById(R.id.content);
            this.content.setMaxLines(10);
            this.comment = view.findViewById(R.id.comment);
            this.videoPreView = (ImageView) view.findViewById(R.id.image);
            this.supportAndCommentsCount = (TickerView) view.findViewById(R.id.points_and_comments_count);
            this.commentCount = (TextView) view.findViewById(R.id.comment_count);
            this.supportOrNotView = (SupportOrNotView) view.findViewById(R.id.support_or_not);
            this.collection_icon = (ImageButton) view.findViewById(R.id.collection_icon);
            this.userInfoLayout = view.findViewById(R.id.userInfo);
            this.currentAvatarView = (ImageView) view.findViewById(R.id.userIcon);
            this.imageLoading = view.findViewById(R.id.imageLoading);
            this.videoLayout = view.findViewById(R.id.imageLayout);
            this.qiushiImageLayout = (QiushiImageLayout) view.findViewById(R.id.qiushi_image_layout);
            this.hotCommentContainer = view.findViewById(R.id.hot_comment_container);
            this.hotComment = (TextView) view.findViewById(R.id.hot_comment);
            this.hotCommentLabel = (ImageView) view.findViewById(R.id.hot_comment_label);
            this.hotCommentImage = (ImageView) view.findViewById(R.id.hot_comment_img);
            this.progress = (ProgressBar) view.findViewById(R.id.progress);
            this.divider = view.findViewById(R.id.divider);
            ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.video_progress);
            this.layerMask = view.findViewById(R.id.layerMask);
            View findViewById = view.findViewById(R.id.play_video);
            this.durationView = (TextView) view.findViewById(R.id.duration);
            this.playFalgView = (ImageView) view.findViewById(R.id.video_play_flag);
            this.loop = (TextView) view.findViewById(R.id.loop);
            this.videoPlayer = (VideoPlayerView) view.findViewById(R.id.videoView);
            if (this.videoPlayer != null) {
                ((SimpleVideoPlayerView) this.videoPlayer).setWidget(progressBar, findViewById, this.videoPreView, this.durationView, this.playFalgView);
                this.videoPlayer.setOnVideoEventListener(this);
            }
            this.gifTag = view.findViewById(R.id.gif_tag);
            this.eventView = (ImageView) view.findViewById(R.id.event);
            this.unlikeView = (ImageView) view.findViewById(R.id.unlike);
        }

        public ImageView createReadMoreImage() {
            if (this.content == null || this.b == null) {
                return null;
            }
            Context context = this.content.getContext();
            if (context == null) {
                return null;
            }
            ImageView imageView = (ImageView) this.b.getTag();
            if (imageView != null) {
                return imageView;
            }
            imageView = new ImageView(this.content.getContext());
            LayoutParams layoutParams = new FrameLayout.LayoutParams(Math.round(context.getResources().getDisplayMetrics().density * 50.0f), this.content.getLineHeight());
            layoutParams.gravity = 85;
            layoutParams.setMargins(0, 0, UIHelper.dip2px(this.a.k, 2.0f), 0);
            this.b.addView(imageView, layoutParams);
            this.b.setTag(imageView);
            return imageView;
        }

        private void a(int i) {
            this.loop.postDelayed(this.addLoopTask, (long) (Math.random() * 3000.0d));
        }

        private void a() {
            if (this.article != null) {
                int generateLoopRandom = this.article.generateLoopRandom();
                Article article = this.article;
                article.loop += (long) generateLoopRandom;
                a(generateLoopRandom);
                VideoLoopStatistics.getInstance().loopBatch(this.article.id, generateLoopRandom);
            }
        }

        public void start() {
            this.videoPlayer.play();
        }

        public void stop() {
            this.videoPlayer.reset();
        }

        public void onVideoCompletion(SimpleVideoPlayer simpleVideoPlayer) {
            a();
        }

        public void onVideoPrepared(SimpleVideoPlayer simpleVideoPlayer) {
            if (!TextUtils.isEmpty(this.article.id)) {
                a();
            }
        }

        public void onVideoError(SimpleVideoPlayer simpleVideoPlayer, int i, int i2) {
        }

        public void onVideoBuffering(SimpleVideoPlayer simpleVideoPlayer, int i) {
        }
    }

    class a implements OnClickListener {
        int a;
        String b;
        ImageView c;
        View d;
        final /* synthetic */ ArticleAdapter e;

        public a(ArticleAdapter articleAdapter, ImageView imageView, String str, int i, View view) {
            this.e = articleAdapter;
            this.b = str;
            this.c = imageView;
            this.a = i;
            this.d = view;
        }

        public void onClick(View view) {
            if (this.e.l != null) {
                this.e.l.getOnItemLongClickListener().onItemLongClick(this.e.l, this.d, this.a + this.e.l.getHeaderViewsCount(), (long) (this.a + this.e.l.getHeaderViewsCount()));
            }
        }
    }

    class b implements OnClickListener {
        int a;
        String b;
        View c;
        View d;
        final /* synthetic */ ArticleAdapter e;

        public b(ArticleAdapter articleAdapter, View view, String str, int i, View view2) {
            this.e = articleAdapter;
            this.b = str;
            this.c = view;
            this.a = i;
            this.d = view2;
        }

        public void onClick(View view) {
            if (this.e.l != null) {
                SlideActivity.showKeyboardArticleId = this.b;
                this.e.l.getOnItemClickListener().onItemClick(this.e.l, this.d, this.a + this.e.l.getHeaderViewsCount(), (long) (this.a + this.e.l.getHeaderViewsCount()));
            }
        }
    }

    private static class c {
        TextView a;
        TextView b;
        View c;

        public c(View view) {
            this.a = (TextView) view.findViewById(R.id.title);
            this.b = (TextView) view.findViewById(R.id.sub_title);
            this.c = view.findViewById(R.id.divider);
        }
    }

    public ArticleAdapter(Activity activity, ListView listView, ArrayList<Object> arrayList, String str, String str2) {
        super(arrayList, activity);
        this.f = "UNKOWN";
        this.h = new a(this);
        this.l = listView;
        this.f = str;
        this.g = new ProgressDisplayer();
        if (a == null) {
            a = this.k.getResources().getString(R.string.points_and_count);
        }
        ShareUtils.registerShareListener(this);
        this.mScenario = str2;
    }

    public ArticleAdapter(Activity activity, ListView listView, ArrayList<Object> arrayList, String str, String str2, AcrossChangeDate acrossChangeDate) {
        this(activity, listView, arrayList, str, str2);
        if (acrossChangeDate != null) {
            this.b = acrossChangeDate;
        }
    }

    public OnTabSelectListener getOnTabSelectListener() {
        return this.c;
    }

    public void setOnTabSelectListener(OnTabSelectListener onTabSelectListener) {
        this.c = onTabSelectListener;
    }

    public int getItemViewType(int i) {
        Object item = getItem(i);
        if (item instanceof Article) {
            if (((Article) item).isWordsOnly()) {
                return 0;
            }
            return ((Article) item).isGIFArticle() ? 19 : 0;
        } else if (item instanceof GdtAdItemData) {
            return 1;
        } else {
            if (item instanceof BaiduAdItemData) {
                return 3;
            }
            if (item instanceof EvaluateCard) {
                return 2;
            }
            if (item instanceof UserLoginGuideCard) {
                return 4;
            }
            if (item instanceof WelcomeCard) {
                return 5;
            }
            if (item instanceof QbAdItem) {
                return 6;
            }
            if (item instanceof ReadLine) {
                return 7;
            }
            if (item instanceof GroupRecommend) {
                return 8;
            }
            if (item instanceof EditorMsg) {
                return 9;
            }
            if (item instanceof CircleTopicPackage) {
                return 10;
            }
            if (item instanceof CircleTopic) {
                CircleTopic circleTopic = (CircleTopic) item;
                if (circleTopic.type == 1) {
                    return 13;
                }
                if (circleTopic.type == 2) {
                    return 11;
                }
                if (circleTopic.type == 3) {
                    return 12;
                }
            } else if (item instanceof QiushiEmpty) {
                return 14;
            } else {
                if (item instanceof AcrossTips) {
                    return 15;
                }
                if (item instanceof LivePackage) {
                    return 16;
                }
                if (item instanceof CircleArticle) {
                    return 17;
                }
                if (item instanceof Qsjx) {
                    return 18;
                }
                if (item instanceof QhAdItemData) {
                    return 20;
                }
                if (item instanceof News) {
                    if (((News) item).isOneImageNews()) {
                        return 21;
                    }
                    return 22;
                } else if (item instanceof QiushiTopicTab) {
                    return 23;
                } else {
                    if (!(item instanceof List) || ((List) item).size() <= 0) {
                        if (item instanceof ReportCallCard) {
                            return 25;
                        }
                        if (item instanceof CircleTopicBanner) {
                            return 26;
                        }
                    } else if (((List) item).get(0) instanceof QiushiTopic) {
                        return 24;
                    } else {
                        if (((List) item).get(0) instanceof QiushiTopicBanner) {
                            return 27;
                        }
                        return 28;
                    }
                }
            }
            return 29;
        }
    }

    public int getViewTypeCount() {
        return 29;
    }

    protected ViewHolder a(View view) {
        return new ViewHolder(this, view);
    }

    protected int a() {
        return R.layout.layout_article_item;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        TimeDelta timeDelta = new TimeDelta();
        DebugUtil.debug(e, "getView...");
        int itemViewType = getItemViewType(i);
        Log.d(e, "viewType:" + itemViewType);
        int dip2px;
        switch (itemViewType) {
            case 0:
            case 19:
                View inflate;
                ViewHolder viewHolder;
                if (view == null || !(view.getTag() instanceof ViewHolder)) {
                    inflate = this.n.inflate(a(), null);
                    Log.d(e, "getView inflat view");
                    ViewHolder a = a(inflate);
                    inflate.setTag(a);
                    viewHolder = a;
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                    inflate = view;
                }
                viewHolder.videoPreView.setColorFilter(null);
                viewHolder.gifTag.setVisibility(8);
                viewHolder.currentAvatarView.setColorFilter(null);
                Article article = (Article) getItem(i);
                viewHolder.article = article;
                viewHolder.videoPlayer.setOnClickListener(new n(this, article, viewHolder.videoPlayer));
                TimeDelta timeDelta2 = new TimeDelta();
                a(article, viewHolder, i, inflate);
                Log.d(getClass().getSimpleName(), "article initArticleContent use time:" + timeDelta2.getDelta());
                viewHolder.supportOrNotView.setOnSupportListener(new u(this, article, i, viewHolder.supportAndCommentsCount, viewHolder.commentCount));
                b bVar = new b(this, viewHolder.comment, article.id, i, inflate);
                viewHolder.comment.setSelected(false);
                viewHolder.comment.setOnClickListener(new v(this, article, bVar));
                viewHolder.comment.setOnTouchListener(this.h);
                viewHolder.collection_icon.setSelected(false);
                viewHolder.collection_icon.setOnClickListener(new a(this, viewHolder.collection_icon, article.id, i, inflate));
                viewHolder.collection_icon.setOnTouchListener(this.h);
                viewHolder.layerMask.setVisibility(8);
                if (viewHolder.divider != null) {
                    viewHolder.divider.setVisibility(i == 0 ? 8 : 0);
                }
                if (viewHolder.hotComment != null) {
                    if (article.hotComment != null) {
                        viewHolder.hotCommentContainer.setVisibility(0);
                        viewHolder.hotComment.setOnClickListener(new w(this, i));
                        viewHolder.hotComment.setVisibility(0);
                        viewHolder.hotComment.setTextColor(UIHelper.isNightTheme() ? -9802626 : -9474193);
                        viewHolder.hotCommentLabel.setVisibility(0);
                        viewHolder.hotCommentLabel.setImageResource(UIHelper.isNightTheme() ? R.drawable.hot_comment_label_night : R.drawable.hot_comment_label);
                        viewHolder.hotComment.setText(String.format("      %s：%s", new Object[]{article.hotComment.userName, article.hotComment.content}));
                        if (article.hotComment.smallImage != null) {
                            viewHolder.hotCommentImage.setVisibility(0);
                            LayoutParams layoutParams = viewHolder.hotCommentImage.getLayoutParams();
                            int dip2px2 = UIHelper.dip2px(this.k, 180.0f);
                            dip2px = UIHelper.dip2px(this.k, 100.0f);
                            if (layoutParams == null) {
                                layoutParams = new LayoutParams(0, 0);
                            }
                            layoutParams.height = article.hotComment.smallImage.height;
                            layoutParams.width = article.hotComment.smallImage.width;
                            if (article.hotComment.smallImage.isVertical()) {
                                if (article.hotComment.smallImage.height > dip2px2) {
                                    layoutParams.height = dip2px2;
                                    layoutParams.width = (int) (((float) dip2px2) * article.hotComment.smallImage.getRatio());
                                }
                            } else if (article.hotComment.smallImage.width > dip2px) {
                                layoutParams.width = dip2px;
                                layoutParams.height = (int) (((float) dip2px) / article.hotComment.smallImage.getRatio());
                            }
                            viewHolder.hotCommentImage.setLayoutParams(layoutParams);
                            FrescoImageloader.displayImage(viewHolder.hotCommentImage, article.hotComment.smallImage.getImageUrl(), UIHelper.getDefaultImageTileBackground());
                            viewHolder.hotCommentImage.setOnClickListener(new LoginPermissionClickDelegate(new x(this, article, viewHolder), null));
                        } else {
                            viewHolder.hotCommentImage.setVisibility(8);
                        }
                    } else {
                        viewHolder.hotCommentLabel.setVisibility(8);
                        viewHolder.hotComment.setVisibility(8);
                        viewHolder.hotCommentImage.setVisibility(8);
                        viewHolder.hotCommentContainer.setVisibility(8);
                    }
                }
                viewHolder.unlikeView.setOnClickListener(new LoginPermissionClickDelegate(new y(this, article, i), null));
                if (article.qiushiTopic != null && article.qiushiTopic.hasEvent()) {
                    viewHolder.eventView.setVisibility(0);
                    FrescoImageloader.displayImage(viewHolder.eventView, article.qiushiTopic.eventWindow.iconUrl, 0, R.drawable.ic_get_laisee);
                    viewHolder.eventView.setOnClickListener(new ab(this, article));
                    view = inflate;
                    break;
                }
                viewHolder.eventView.setVisibility(4);
                view = inflate;
                break;
                break;
            case 1:
                view = ((GdtAdItemData) getItem(i)).getView().getView(view);
                break;
            case 2:
                EvaluateCard evaluateCard = (EvaluateCard) getItem(i);
                view = EvaluateCard.getView(this.n, view, viewGroup);
                break;
            case 3:
                DebugUtil.debug("BaiduAd", "ArticleAdapter getView");
                view = ((BaiduAdItemData) getItem(i)).getView().getView(view);
                break;
            case 4:
                UserLoginGuideCard userLoginGuideCard = (UserLoginGuideCard) getItem(i);
                view = UserLoginGuideCard.getView(this.n, view, viewGroup);
                break;
            case 5:
                WelcomeCard welcomeCard = (WelcomeCard) getItem(i);
                view = WelcomeCard.getView(this.n, view, viewGroup, i);
                break;
            case 6:
                view = ((QbAdItem) getItem(i)).getView(this.n, view, viewGroup, i);
                break;
            case 7:
                view = this.n.inflate(R.layout.layout_read_line, viewGroup, false);
                RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.read_rel);
                ImageView imageView = (ImageView) view.findViewById(R.id.read_refresh);
                Animation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                rotateAnimation.setDuration(1000);
                rotateAnimation.setAnimationListener(new ac(this));
                relativeLayout.setOnClickListener(new b(this, imageView, rotateAnimation));
                break;
            case 8:
                GroupRecommendQiushiCell groupRecommendQiushiCell;
                if (view == null || !(view.getTag() instanceof GroupRecommendQiushiCell)) {
                    groupRecommendQiushiCell = new GroupRecommendQiushiCell();
                    groupRecommendQiushiCell.performCreate(i, viewGroup, getItem(i));
                    view = groupRecommendQiushiCell.getCellView();
                    view.setTag(groupRecommendQiushiCell);
                } else {
                    groupRecommendQiushiCell = (GroupRecommendQiushiCell) view.getTag();
                }
                groupRecommendQiushiCell.performUpdate(i, viewGroup, getItem(i));
                break;
            case 9:
                TextView textView;
                if (view == null) {
                    view = this.n.inflate(R.layout.highligh_qiushit_footer, viewGroup, false);
                }
                EditorMsg editorMsg = (EditorMsg) getItem(i);
                TextView textView2 = (TextView) view.findViewById(R.id.brief);
                ImageView imageView2 = (ImageView) view.findViewById(R.id.avatar_icon);
                EditorLinkView editorLinkView = (EditorLinkView) view.findViewById(R.id.links);
                ((TextView) view.findViewById(R.id.editor)).setText("本期小编 : " + editorMsg.login);
                textView2.setText(editorMsg.brief);
                b(imageView2, editorMsg.icon);
                if (editorMsg.links == null || editorMsg.links.size() == 0) {
                    editorLinkView.setVisibility(8);
                } else {
                    editorLinkView.setLink(editorMsg.links);
                }
                View findViewById = view.findViewById(R.id.vote_layout);
                if (editorMsg.vote != null) {
                    findViewById.setVisibility(0);
                    textView = (TextView) view.findViewById(R.id.vote_left);
                    textView2 = (TextView) view.findViewById(R.id.vote_right);
                    imageView2 = (ImageView) view.findViewById(R.id.vote_vs);
                    if (editorMsg.vote.isVoted()) {
                        textView.setText(editorMsg.vote.upDesc + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + editorMsg.vote.up);
                        textView2.setText(editorMsg.vote.downDesc + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + editorMsg.vote.down);
                        imageView2.setImageResource(UIHelper.getCircleVoteVsOn());
                        if (editorMsg.vote.isVoteUp()) {
                            textView.setTextColor(UIHelper.getCircleVoteOnTextColor());
                            textView2.setTextColor(UIHelper.getCircleVoteTextColor());
                            textView.setBackgroundResource(UIHelper.getCircleVoteLeftOn());
                            textView2.setBackgroundResource(R.color.transparent);
                        } else {
                            textView.setTextColor(UIHelper.getCircleVoteTextColor());
                            textView2.setTextColor(UIHelper.getCircleVoteOnTextColor());
                            textView.setBackgroundResource(R.color.transparent);
                            textView2.setBackgroundResource(UIHelper.getCircleVoteRightOn());
                        }
                    } else {
                        imageView2.setImageResource(UIHelper.getCircleVoteVs());
                        textView.setText(editorMsg.vote.upDesc);
                        textView2.setText(editorMsg.vote.downDesc);
                        textView.setOnClickListener(new LoginPermissionClickDelegate(new c(this, editorMsg), null));
                        textView2.setOnClickListener(new LoginPermissionClickDelegate(new e(this, editorMsg), null));
                    }
                } else {
                    findViewById.setVisibility(8);
                }
                textView = (TextView) view.findViewById(R.id.bottom);
                if (!TextUtils.isEmpty(editorMsg.bottomDesc) && !TextUtils.isEmpty(editorMsg.highlight)) {
                    textView.setVisibility(0);
                    CharSequence spannableStringBuilder = new SpannableStringBuilder(editorMsg.bottomDesc);
                    dip2px = editorMsg.bottomDesc.indexOf(editorMsg.highlight);
                    int length = editorMsg.highlight.length() + dip2px;
                    spannableStringBuilder.setSpan(new g(this, editorMsg), dip2px, length, 33);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#ff639ce0")), dip2px, length, 33);
                    textView.setText(spannableStringBuilder);
                    textView.setMovementMethod(LinkMovementMethod.getInstance());
                    break;
                }
                textView.setVisibility(8);
                break;
            case 10:
                TopicCell topicCell;
                if (view == null || !(view.getTag() instanceof TopicCell)) {
                    topicCell = new TopicCell(true);
                    topicCell.performCreate(i, viewGroup, getItem(i));
                    view = topicCell.getCellView();
                    view.setTag(topicCell);
                } else {
                    topicCell = (TopicCell) view.getTag();
                }
                topicCell.performUpdate(i, viewGroup, getItem(i));
                break;
            case 11:
                CircleTopicMoreCell circleTopicMoreCell;
                if (view == null) {
                    circleTopicMoreCell = new CircleTopicMoreCell();
                    circleTopicMoreCell.performCreate(i, viewGroup, getItem(i));
                    view = circleTopicMoreCell.getCellView();
                    view.setTag(circleTopicMoreCell);
                } else {
                    circleTopicMoreCell = (CircleTopicMoreCell) view.getTag();
                }
                circleTopicMoreCell.performUpdate(i, viewGroup, getItem(i));
                break;
            case 12:
                CircleTopicThreeImageCell circleTopicThreeImageCell;
                if (view == null) {
                    circleTopicThreeImageCell = new CircleTopicThreeImageCell();
                    circleTopicThreeImageCell.performCreate(i, viewGroup, getItem(i));
                    view = circleTopicThreeImageCell.getCellView();
                    view.setTag(view);
                } else {
                    circleTopicThreeImageCell = (CircleTopicThreeImageCell) view.getTag();
                }
                circleTopicThreeImageCell.performUpdate(i, viewGroup, getItem(i));
                break;
            case 13:
                CircleTopicTextBgCell circleTopicTextBgCell;
                if (view == null) {
                    circleTopicTextBgCell = new CircleTopicTextBgCell();
                    circleTopicTextBgCell.performCreate(i, viewGroup, getItem(i));
                    view = circleTopicTextBgCell.getCellView();
                    view.setTag(circleTopicTextBgCell);
                } else {
                    circleTopicTextBgCell = (CircleTopicTextBgCell) view.getTag();
                }
                circleTopicTextBgCell.performUpdate(i, viewGroup, getItem(i));
                break;
            case 14:
                if (view == null) {
                    view = this.n.inflate(R.layout.layout_qiushi_empty, viewGroup, false);
                    break;
                }
                break;
            case 15:
                AcrossTipsViewHolder acrossTipsViewHolder;
                if (view == null || !(view.getTag() instanceof AcrossTipsViewHolder)) {
                    view = this.n.inflate(R.layout.across_tips, viewGroup, false);
                    acrossTipsViewHolder = new AcrossTipsViewHolder(this);
                    acrossTipsViewHolder.b = (TextView) view.findViewById(R.id.change_date);
                    acrossTipsViewHolder.a = (TextView) view.findViewById(R.id.across_date);
                    view.setTag(acrossTipsViewHolder);
                } else {
                    acrossTipsViewHolder = (AcrossTipsViewHolder) view.getTag();
                }
                acrossTipsViewHolder.a.setText(((AcrossTips) getItem(i)).date);
                acrossTipsViewHolder.b.setOnClickListener(new h(this));
                break;
            case 16:
                LiveRecommendCell liveRecommendCell;
                if (view == null || !(view.getTag() instanceof LiveRecommendCell)) {
                    liveRecommendCell = new LiveRecommendCell(this.mScenario, true);
                    liveRecommendCell.performCreate(i, viewGroup, getItem(i));
                    view = liveRecommendCell.getCellView();
                    view.setTag(liveRecommendCell);
                } else {
                    liveRecommendCell = (LiveRecommendCell) view.getTag();
                }
                liveRecommendCell.performUpdate(i, viewGroup, getItem(i));
                break;
            case 17:
                CircleVideoCell circleVideoCell;
                if (view == null || !(view.getTag() instanceof CircleVideoCell)) {
                    circleVideoCell = new CircleVideoCell();
                    circleVideoCell.performCreate(i, viewGroup, getItem(i));
                    view = circleVideoCell.getCellView();
                    view.setTag(circleVideoCell);
                } else {
                    circleVideoCell = (CircleVideoCell) view.getTag();
                }
                circleVideoCell.performUpdate(i, viewGroup, getItem(i));
                break;
            case 18:
                QsjxCell qsjxCell;
                if (view == null || !(view.getTag() instanceof Qsjx)) {
                    qsjxCell = new QsjxCell();
                    qsjxCell.performCreate(i, viewGroup, getItem(i));
                    view = qsjxCell.getCellView();
                    view.setTag(qsjxCell);
                } else {
                    qsjxCell = (QsjxCell) view.getTag();
                }
                qsjxCell.performUpdate(i, viewGroup, getItem(i));
                break;
            case 20:
                QhAdItemData qhAdItemData = (QhAdItemData) getItem(i);
                view = qhAdItemData.getAdView().getView(view);
                view.setOnClickListener(new i(this, qhAdItemData.getAdView().getmNativeAd()));
                FeedsAd.getInstance().setAdShowed(qhAdItemData, AD_PROVIDER.QH);
                break;
            case 21:
                OneImageNewsRecommendCell oneImageNewsRecommendCell;
                if (view == null || !(view.getTag() instanceof OneImageNewsRecommendCell)) {
                    oneImageNewsRecommendCell = new OneImageNewsRecommendCell();
                    oneImageNewsRecommendCell.performCreate(i, viewGroup, getItem(i));
                    view = oneImageNewsRecommendCell.getCellView();
                    view.setTag(oneImageNewsRecommendCell);
                } else {
                    oneImageNewsRecommendCell = (OneImageNewsRecommendCell) view.getTag();
                }
                oneImageNewsRecommendCell.performUpdate(i, viewGroup, getItem(i));
                break;
            case 22:
                ThreeImageNewsRecommendCell threeImageNewsRecommendCell;
                if (view == null || !(view.getTag() instanceof ThreeImageNewsRecommendCell)) {
                    threeImageNewsRecommendCell = new ThreeImageNewsRecommendCell();
                    threeImageNewsRecommendCell.performCreate(i, viewGroup, getItem(i));
                    view = threeImageNewsRecommendCell.getCellView();
                    view.setTag(threeImageNewsRecommendCell);
                } else {
                    threeImageNewsRecommendCell = (ThreeImageNewsRecommendCell) view.getTag();
                }
                threeImageNewsRecommendCell.performUpdate(i, viewGroup, getItem(i));
                break;
            case 23:
                c cVar;
                if (view != null && (view.getTag() instanceof c)) {
                    cVar = (c) view.getTag();
                    break;
                }
                view = this.n.inflate(R.layout.qiushi_topic_listitem_section, viewGroup, false);
                cVar = new c(view);
                cVar.a.setOnClickListener(new j(this, cVar));
                cVar.b.setOnClickListener(new k(this, cVar));
                view.setTag(cVar);
                break;
                break;
            case 24:
                QiushiTopicRecommendCell qiushiTopicRecommendCell;
                if (view == null || (view.getTag() instanceof QiushiTopicRecommendCell)) {
                    qiushiTopicRecommendCell = new QiushiTopicRecommendCell();
                    qiushiTopicRecommendCell.performCreate(i, viewGroup, getItem(i));
                    view = qiushiTopicRecommendCell.getCellView();
                    view.setTag(qiushiTopicRecommendCell);
                } else {
                    qiushiTopicRecommendCell = (QiushiTopicRecommendCell) view.getTag();
                }
                qiushiTopicRecommendCell.performUpdate(i, viewGroup, getItem(i));
                break;
            case 25:
                if (view == null) {
                    view = ReportCallCard.getView(this.n, view, viewGroup);
                    break;
                }
                break;
            case 26:
                CircleTopicRecommendCell circleTopicRecommendCell;
                if (view == null) {
                    circleTopicRecommendCell = new CircleTopicRecommendCell();
                    circleTopicRecommendCell.performCreate(i, viewGroup, getItem(i));
                    view = circleTopicRecommendCell.getCellView();
                    view.setTag(circleTopicRecommendCell);
                } else {
                    circleTopicRecommendCell = (CircleTopicRecommendCell) view.getTag();
                }
                circleTopicRecommendCell.performUpdate(i, viewGroup, getItem(i));
                break;
            case 27:
                QiushiTopicPagerBannerCell qiushiTopicPagerBannerCell;
                List list = (List) getItem(i);
                if (view == null) {
                    qiushiTopicPagerBannerCell = new QiushiTopicPagerBannerCell(list);
                    qiushiTopicPagerBannerCell.performCreate(i, viewGroup, list);
                    view = qiushiTopicPagerBannerCell.getCellView();
                    view.setTag(qiushiTopicPagerBannerCell);
                } else {
                    qiushiTopicPagerBannerCell = (QiushiTopicPagerBannerCell) view.getTag();
                }
                qiushiTopicPagerBannerCell.performUpdate(i, viewGroup, list);
                break;
        }
        Log.d(e, "getView type:" + itemViewType + " use time" + timeDelta.getDelta());
        View findViewById2 = view.findViewById(R.id.divider);
        if (findViewById2 != null) {
            if (i == 0) {
                findViewById2.setVisibility(8);
            } else {
                findViewById2.setVisibility(0);
            }
        }
        return view;
    }

    protected void a(String str, String str2, ImageView imageView) {
        b(imageView, QsbkApp.absoluteUrlOfMediumUserIcon(str2, str));
    }

    protected void a(Article article, ViewHolder viewHolder, int i, View view) {
        a(article.id, viewHolder);
        b(article, viewHolder);
        b(article, viewHolder, i, view);
        a(article, viewHolder);
        viewHolder.progress.setVisibility(8);
        c(article, viewHolder);
        d(article, viewHolder);
        e(article, viewHolder);
    }

    protected void a(Article article, ViewHolder viewHolder) {
        if (article.isVideoArticle()) {
            viewHolder.videoLayout.setVisibility(0);
            int[] requestWidthAndMaxPixcel = ImageSizeHelper.getRequestWidthAndMaxPixcel();
            int i = requestWidthAndMaxPixcel[0];
            int i2 = requestWidthAndMaxPixcel[1];
            if (article.image_size != null) {
                setImageLayoutParams(viewHolder.videoPreView, article.image_size.smallSize(), i, i2);
                return;
            }
            setImageLayoutParams(viewHolder.videoPreView, new ImageSize(i, (i2 * 4) / 9), i, i2);
            return;
        }
        viewHolder.videoLayout.setVisibility(8);
    }

    private void a(String str, ViewHolder viewHolder) {
        viewHolder.supportOrNotView.reset();
        if (VoteManager.getInstance().containsVote(str, "up")) {
            viewHolder.supportOrNotView.setSupport();
        }
        if (VoteManager.getInstance().containsVote(str, Config.DEVICE_NAME) && !VoteManager.getInstance().containsVote(str, "_up")) {
            viewHolder.supportOrNotView.setUnSupport();
        }
        if (QsbkApp.allCollection.contains(str)) {
            viewHolder.collection_icon.setTag("active");
        } else {
            viewHolder.collection_icon.setTag("enable");
        }
    }

    protected boolean b() {
        return false;
    }

    protected void b(Article article, ViewHolder viewHolder) {
        int i = R.color.uesr_night;
        if (b()) {
            viewHolder.userInfoLayout.setVisibility(8);
            return;
        }
        viewHolder.userInfoLayout.setVisibility(0);
        if (TextUtils.isEmpty(article.login) || "Guest".equals(article.login)) {
            viewHolder.userName.setText(BaseUserInfo.ANONYMOUS_USER_NAME);
            TextView textView = viewHolder.userName;
            Resources resources = viewHolder.userName.getResources();
            if (!UIHelper.isNightTheme()) {
                i = R.color.g_txt_small;
            }
            textView.setTextColor(resources.getColor(i));
            viewHolder.currentAvatarView.setImageDrawable(RoundedDrawable.fromDrawable(viewHolder.currentAvatarView.getResources().getDrawable(UIHelper.getIconRssAnonymousUser())));
            viewHolder.userInfoLayout.setOnClickListener(null);
            return;
        }
        CharSequence remark = RemarkManager.getRemark(article.user_id);
        TextView textView2 = viewHolder.userName;
        if (TextUtils.isEmpty(remark)) {
            remark = article.login;
        }
        textView2.setText(remark);
        viewHolder.userName.setTextColor(viewHolder.userName.getResources().getColor(UIHelper.isNightTheme() ? R.color.uesr_night : R.color.username));
        a(article.user_id, article.user_icon, viewHolder.currentAvatarView);
        viewHolder.userInfoLayout.setOnClickListener(new UserClickDelegate(article.user_id, new UserInfoClickListener(article.user_id, article.login, article.user_icon, article.id)));
    }

    private SpannableStringBuilder a(TextView textView, CharSequence charSequence, Article article, boolean z) {
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = textView.getText();
        }
        Object spannableStringBuilder = new SpannableStringBuilder(charSequence);
        QiushiTopicImageSpan qiushiTopicImageSpan = new QiushiTopicImageSpan(this.k.getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.ic_topic_prefix_night : R.drawable.ic_topic_prefix));
        qiushiTopicImageSpan.setSubSize(UIHelper.dip2px(this.k, 5.0f));
        qiushiTopicImageSpan.setmPaint(textView.getPaint());
        spannableStringBuilder.setSpan(qiushiTopicImageSpan, 0, 1, 33);
        int length = article.qiushiTopic.content.length();
        if (spannableStringBuilder.length() > length + 2) {
            if (!(this.k instanceof QiushiTopicActivity)) {
                spannableStringBuilder.setSpan(new l(this, article), 0, length + 2, 33);
            }
            spannableStringBuilder.setSpan(new ForegroundColorSpan(UIHelper.isNightTheme() ? -4424933 : -17664), 1, length + 2, 33);
        }
        if (z) {
            textView.setText(spannableStringBuilder);
        }
        return spannableStringBuilder;
    }

    private void a(ViewHolder viewHolder, Article article, boolean z) {
        CharSequence charSequence;
        CharSequence a;
        CharSequence text = viewHolder.content.getText();
        Pair ellipseText = Util.ellipseText(text.toString(), viewHolder.content.getPaint(), viewHolder.content.getLayout(), (viewHolder.content.getMeasuredWidth() - viewHolder.content.getPaddingLeft()) - viewHolder.content.getPaddingRight(), 10, "... 查看全文");
        if (((Boolean) ellipseText.first).booleanValue()) {
            charSequence = (CharSequence) ellipseText.second;
        } else {
            charSequence = text;
        }
        if (z) {
            a = a(viewHolder.content, charSequence, article, false);
        } else {
            a = new SpannableStringBuilder(charSequence);
        }
        a.setSpan(new ForegroundColorSpan(UIHelper.isNightTheme() ? -4424933 : -17664), charSequence.length() - 4, charSequence.length(), 33);
        viewHolder.content.setText(a);
        viewHolder.content.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void b(Article article, ViewHolder viewHolder, int i, View view) {
        if (TextUtils.isEmpty(article.getContent()) || "null".equals(article.getContent().trim())) {
            viewHolder.content.setVisibility(8);
            return;
        }
        viewHolder.content.setVisibility(0);
        viewHolder.content.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
        viewHolder.content.setEllipsize(TruncateAt.END);
        viewHolder.content.setOnClickListener(new m(this, i));
        viewHolder.content.setOnLongClickListener(new o(this, view, i));
        if (article.qiushiTopic != null) {
            viewHolder.content.setOnTextMoreListener(new p(this, viewHolder, article));
            a(viewHolder.content, "搜 " + article.qiushiTopic.content + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + article.content, article, true);
            return;
        }
        viewHolder.content.setOnTextMoreListener(new q(this, viewHolder, article));
        viewHolder.content.setText(article.getContent());
    }

    protected void c(Article article, ViewHolder viewHolder) {
        if (TextUtils.isEmpty(article.image) || "null".equalsIgnoreCase(article.image)) {
            viewHolder.videoPreView.setTag(null);
            viewHolder.progress.setTag(null);
            return;
        }
        viewHolder.progress.setTag(article.image);
        viewHolder.videoPreView.setTag(viewHolder.progress);
    }

    protected void d(Article article, ViewHolder viewHolder) {
        if (article.isImageArticle()) {
            viewHolder.videoLayout.setVisibility(8);
            viewHolder.qiushiImageLayout.setVisibility(0);
            viewHolder.qiushiImageLayout.setImages(article.imageInfos);
            viewHolder.qiushiImageLayout.setOnChildClickListener(new r(this, viewHolder, article));
            return;
        }
        viewHolder.qiushiImageLayout.setVisibility(8);
    }

    protected void e(Article article, ViewHolder viewHolder) {
        int i = article.comment_count;
        viewHolder.loop.removeCallbacks(viewHolder.addLoopTask);
        viewHolder.loop.setText("");
        if (viewHolder.supportOrNotView.isSupport()) {
            UIHelper.setSupportAndCommentTextHighlight(viewHolder.supportAndCommentsCount, viewHolder.commentCount, article.getDisplayLaugth(), i, article.shareCount, false);
        } else {
            UIHelper.setSupportAndCommentText(viewHolder.supportAndCommentsCount, viewHolder.commentCount, article.getDisplayLaugth(), i, article.shareCount, false);
        }
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

    public void onShared(Article article) {
        for (int i = 0; i < getCount(); i++) {
            Object item = getItem(i);
            if (item instanceof Article) {
                Article article2 = (Article) item;
                if (TextUtils.equals(article2.id, article.id)) {
                    article2.shareCount = article.shareCount;
                    notifyDataSetChanged();
                    return;
                }
            }
        }
    }

    private boolean a(int i, String str, String str2, String str3) {
        Vote vote = new Vote(this.f + (i + 1), str, str2, "1");
        DebugUtil.debug(e, "投票:" + vote.toString());
        return VoteManager.getInstance().vote(vote, str, str2);
    }

    private void a(Article article) {
        HttpTask httpTask = new HttpTask(String.format(Constants.ARTICLE_UNLIKE, new Object[]{article.id}), new s(this));
        Map hashMap = new HashMap();
        hashMap.put("type", "topic");
        hashMap.put("toid", Integer.valueOf(article.qiushiTopic.id));
        httpTask.setMapParams(hashMap);
        httpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void b(Article article) {
        HttpTask httpTask = new HttpTask(String.format(Constants.ARTICLE_UNLIKE, new Object[]{article.id}), new t(this));
        Map hashMap = new HashMap();
        hashMap.put("type", "user");
        hashMap.put("toid", article.user_id);
        httpTask.setMapParams(hashMap);
        httpTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.g != null) {
            this.g.recycle();
        }
    }

    public void onStop() {
        super.onStop();
    }

    public void onPause() {
        super.onPause();
    }
}
