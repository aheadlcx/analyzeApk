package qsbk.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.MediaVariationsIndexDatabase.IndexEntry;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.File;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.ManageQiuShiNewActivity;
import qsbk.app.activity.QiushiTopicActivity;
import qsbk.app.adapter.BaseImageAdapter.ProgressDisplayer;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.image.ImageSizeHelper;
import qsbk.app.model.Article;
import qsbk.app.model.ArticleImage;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.model.ImageSize;
import qsbk.app.model.PostedArticle;
import qsbk.app.model.QiushiEmpty;
import qsbk.app.model.RssArticle;
import qsbk.app.model.Vote;
import qsbk.app.service.VoteManager;
import qsbk.app.ticker.TickerView;
import qsbk.app.utils.DateUtil;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.MobileTransformationMethod;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.UserInfoClickListener;
import qsbk.app.video.SimpleVideoPlayer;
import qsbk.app.video.SimpleVideoPlayer.OnVideoEventListener;
import qsbk.app.video.VideoLoopStatistics;
import qsbk.app.video.VideoPlayerView;
import qsbk.app.widget.DiggerBar;
import qsbk.app.widget.QiushiImageLayout;
import qsbk.app.widget.QiushiTopicImageSpan;
import qsbk.app.widget.RoundedDrawable;
import qsbk.app.widget.SupportOrNotView;
import qsbk.app.widget.SupportOrNotView.OnSupportListener;

public class ManageMyContentsAdapter extends BaseImageAdapter {
    public static final String APPEALING = "appealing";
    public static final String APPEALING_FAIL = "appeal_fail";
    public static final String FAKE = "fake";
    public static final String NEW_PUBLISH = "new_publish";
    public static final String PENDING = "pending";
    public static final String PRIVATE = "private";
    public static final String PUBLISH = "publish";
    public static final String REPORTED = "reported";
    public static final String REVOKE = "revoke";
    public static final String SPAM = "spam";
    public static final String WAITING = "waiting";
    private static int a;
    private static int b;
    public static ArrayList<String> revokeList = new ArrayList();
    private int c;
    private ProgressDisplayer e;
    private IViewClickListener f;
    private String g = "MYPUBLISH";
    private OnTouchListener h = new bv(this);

    public interface IViewClickListener {
        void delete(Article article);
    }

    public class ViewHolder implements OnVideoEventListener {
        DiggerBar a;
        public Runnable addLoopTask = new cj(this);
        public Article article;
        final /* synthetic */ ManageMyContentsAdapter b;
        public ImageButton collection_icon;
        public TextView commentCount;
        public TextView content;
        public FrameLayout contentLayout;
        public ImageView delete;
        public View divider;
        public ImageView imageTip;
        public TextView imageloading;
        public RelativeLayout layerMask;
        public TextView loop;
        public View playBtn;
        public VideoPlayerView player;
        public ProgressBar progress;
        public QiushiImageLayout qiushiImageLayout;
        public TextView sendDate;
        public ImageView sendImage;
        public TextView state;
        public TextView stateMsg;
        public TickerView supportCount;
        public View supportLayout;
        public SupportOrNotView supportOrNotView;
        public TextView type;
        public ImageButton userComment;
        public ImageView userIcon;
        public RelativeLayout userInfo;
        public TextView userName;
        public View videoLayout;

        public ViewHolder(ManageMyContentsAdapter manageMyContentsAdapter) {
            this.b = manageMyContentsAdapter;
        }

        public ImageView createReadMoreImage() {
            if (this.content == null || this.contentLayout == null) {
                return null;
            }
            Context context = this.content.getContext();
            if (context == null) {
                return null;
            }
            ImageView imageView = (ImageView) this.contentLayout.getTag();
            if (imageView != null) {
                return imageView;
            }
            Layout layout = this.content.getLayout();
            if (layout == null || layout.getLineCount() < 10) {
                return imageView;
            }
            imageView = new ImageView(this.content.getContext());
            float f = context.getResources().getDisplayMetrics().density;
            LayoutParams layoutParams = new FrameLayout.LayoutParams(Math.round(50.0f * f), Math.round(25.0f * f));
            layoutParams.gravity = 5;
            layoutParams.topMargin = layout.getLineTop(9) - Math.round(f * 2.0f);
            this.contentLayout.addView(imageView, layoutParams);
            this.contentLayout.setTag(imageView);
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
        final /* synthetic */ ManageMyContentsAdapter e;

        public a(ManageMyContentsAdapter manageMyContentsAdapter, ImageView imageView, String str, int i, View view) {
            this.e = manageMyContentsAdapter;
            this.b = str;
            this.c = imageView;
            this.a = i;
            this.d = view;
        }

        public void onClick(View view) {
            this.e.l.getOnItemLongClickListener().onItemLongClick(this.e.l, this.d, this.a + this.e.l.getHeaderViewsCount(), (long) (this.a + this.e.l.getHeaderViewsCount()));
        }
    }

    class b implements OnClickListener {
        int a;
        String b;
        ImageView c;
        View d;
        final /* synthetic */ ManageMyContentsAdapter e;

        public b(ManageMyContentsAdapter manageMyContentsAdapter, ImageView imageView, String str, int i, View view) {
            this.e = manageMyContentsAdapter;
            this.b = str;
            this.c = imageView;
            this.a = i;
            this.d = view;
        }

        public void onClick(View view) {
            this.e.l.getOnItemClickListener().onItemClick(this.e.l, this.d, this.a + this.e.l.getHeaderViewsCount(), (long) (this.a + this.e.l.getHeaderViewsCount()));
        }
    }

    public ManageMyContentsAdapter(Activity activity, ListView listView, ArrayList<Object> arrayList, IViewClickListener iViewClickListener) {
        super(arrayList, activity);
        this.f = iViewClickListener;
        this.e = new ProgressDisplayer();
        this.l = listView;
        Resources resources = this.k.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        this.c = displayMetrics.widthPixels - (resources.getDimensionPixelSize(R.dimen.profile_item_margin) * 2);
        b = (int) (((double) displayMetrics.heightPixels) * 1.5d);
        a = (int) (((double) displayMetrics.heightPixels) * 0.7d);
        SharePreferenceUtils.getRevokes();
    }

    public static boolean doNotLoadImageDirectly() {
        if (TextUtils.isEmpty(d)) {
            d = SharePreferenceUtils.getSharePreferencesValue("imageLoadWay");
            if (TextUtils.isEmpty(d)) {
                d = "auto";
            }
        }
        return d.equals(BaseImageAdapter.IMAGELOADWAY_TEXTONLY) || (d.equals("wifi") && !HttpUtils.isWifi(QsbkApp.getInstance()));
    }

    protected void c() {
    }

    protected Drawable d() {
        return UIHelper.getDrawable(UIHelper.getDefaultImageTileBackground());
    }

    private String a(int i) {
        if (i > 1000000) {
            return (i / 10000) + "万";
        }
        return String.valueOf(i);
    }

    public int getItemViewType(int i) {
        Object item = getItem(i);
        if (item instanceof Article) {
            return 0;
        }
        if (item instanceof QiushiEmpty) {
            return 1;
        }
        return super.getItemViewType(i);
    }

    public int getViewTypeCount() {
        return 2;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        switch (getItemViewType(i)) {
            case 0:
                ViewHolder viewHolder;
                String str;
                ViewHolder a;
                if (view == null || !(view.getTag() instanceof ViewHolder)) {
                    view = this.n.inflate(R.layout.listitem_manage_mycontent_row, null);
                    a = a(view, new ViewHolder(this));
                    view.setTag(a);
                    viewHolder = a;
                } else {
                    a = (ViewHolder) view.getTag();
                    a.player.setWidget(a.progress, a.playBtn, a.sendImage);
                    viewHolder = a;
                }
                viewHolder.userIcon.setColorFilter(null);
                Article article = (Article) getItem(i);
                viewHolder.article = article;
                if (TextUtils.isEmpty(article.login) || "Guest".equals(article.login)) {
                    viewHolder.userName.setText(BaseUserInfo.ANONYMOUS_USER_NAME);
                    viewHolder.userName.setTextColor(viewHolder.userName.getResources().getColor(UIHelper.isNightTheme() ? R.color.uesr_night : R.color.g_txt_small));
                    viewHolder.userIcon.setImageDrawable(RoundedDrawable.fromDrawable(viewHolder.userIcon.getResources().getDrawable(UIHelper.getIconRssAnonymousUser())));
                    viewHolder.userInfo.setOnClickListener(null);
                } else {
                    viewHolder.userName.setText(article.login);
                    viewHolder.userName.setTextColor(viewHolder.userName.getResources().getColor(UIHelper.isNightTheme() ? R.color.uesr_night : R.color.cmt_user_article_username));
                    a(article.user_id, article.user_icon, viewHolder.userIcon);
                    viewHolder.userInfo.setOnClickListener(new UserInfoClickListener(article.user_id, article.login, article.user_icon, article.id));
                }
                viewHolder.delete.setOnClickListener(new cb(this, article));
                viewHolder.player.setOnClickListener(new cc(this, article, viewHolder.player, viewHolder));
                Object trim = TextUtils.isEmpty(article.content) ? null : article.content.trim();
                if (TextUtils.isEmpty(trim) || trim.equals("null")) {
                    viewHolder.content.setVisibility(8);
                } else {
                    viewHolder.content.setVisibility(0);
                    viewHolder.content.setTransformationMethod(new MobileTransformationMethod());
                    viewHolder.content.setTextSize(QsbkApp.getInstance().getCurrentContentTextSize());
                    if (article.qiushiTopic == null || article.qiushiTopic.isEmpty()) {
                        viewHolder.content.setText(article.getContent());
                    } else {
                        CharSequence spannableStringBuilder = new SpannableStringBuilder();
                        spannableStringBuilder.append("搜");
                        QiushiTopicImageSpan qiushiTopicImageSpan = new QiushiTopicImageSpan(this.k.getResources().getDrawable(UIHelper.isNightTheme() ? R.drawable.ic_topic_prefix_night : R.drawable.ic_topic_prefix));
                        qiushiTopicImageSpan.setSubSize(UIHelper.dip2px(this.k, 5.0f));
                        qiushiTopicImageSpan.setmPaint(viewHolder.content.getPaint());
                        spannableStringBuilder.setSpan(qiushiTopicImageSpan, 0, 1, 33);
                        str = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + article.qiushiTopic.content;
                        spannableStringBuilder.append(str);
                        if (!(this.k instanceof QiushiTopicActivity)) {
                            spannableStringBuilder.setSpan(new cd(this, article), 0, str.length() + 1, 33);
                        }
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(UIHelper.isNightTheme() ? -4424933 : -17664), 1, str.length() + 1, 33);
                        spannableStringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + article.getContent());
                        viewHolder.content.setText(spannableStringBuilder);
                        viewHolder.content.setMovementMethod(LinkMovementMethod.getInstance());
                    }
                    viewHolder.content.setOnClickListener(new ce(this, i));
                }
                viewHolder.sendDate.setText(DateUtil.Get_DiffDate_Info(this.k, article.created_at, 0).toString());
                String format = String.format("好笑 %1$s", new Object[]{a(article.getDisplayLaugth())});
                str = String.format(UIHelper.getDot() + "评论 %1$s", new Object[]{a(article.comment_count)});
                String str2 = "";
                if (article.shareCount > 0) {
                    str2 = String.format(UIHelper.getDot() + "分享 %1$s", new Object[]{a(article.shareCount)});
                }
                format + str + str2;
                UIHelper.setSupportAndCommentText(viewHolder.supportCount, viewHolder.commentCount, article.getDisplayLaugth(), article.comment_count, article.shareCount, false);
                viewHolder.loop.removeCallbacks(viewHolder.addLoopTask);
                if (article.isVideoArticle()) {
                    CharSequence loopString = article.getLoopString();
                    if (!loopString.startsWith(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
                        loopString = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + loopString;
                    }
                    viewHolder.loop.setText(loopString);
                } else {
                    viewHolder.loop.setText("");
                }
                str = article.state;
                if (NEW_PUBLISH.equals(str)) {
                    viewHolder.loop.setVisibility(8);
                    viewHolder.supportLayout.setVisibility(8);
                } else {
                    viewHolder.loop.setVisibility(0);
                    viewHolder.supportLayout.setVisibility(0);
                }
                c(article, viewHolder);
                a(viewHolder, article, str);
                e(article, viewHolder);
                a(article, viewHolder);
                d(article, viewHolder);
                a(viewHolder, str, article, i, view);
                if (article instanceof RssArticle) {
                    a((RssArticle) article, viewHolder);
                } else {
                    viewHolder.type.setVisibility(8);
                }
                b(article, viewHolder);
                TextView textView = viewHolder.content;
                textView.post(new cf(this, viewHolder.createReadMoreImage(), textView));
                if (viewHolder.divider == null) {
                    return view;
                }
                int i2;
                View view2 = viewHolder.divider;
                if (i == 0) {
                    i2 = 8;
                } else {
                    i2 = 0;
                }
                view2.setVisibility(i2);
                return view;
            case 1:
                if (view == null) {
                    return this.n.inflate(R.layout.layout_qiushi_empty, viewGroup, false);
                }
                return view;
            default:
                return view;
        }
    }

    public void onStop() {
        super.onStop();
        int childCount = this.l.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewHolder viewHolder = (ViewHolder) this.l.getChildAt(i).getTag();
            if (viewHolder != null) {
                viewHolder.loop.removeCallbacks(viewHolder.addLoopTask);
            }
        }
    }

    private void b(Article article, ViewHolder viewHolder) {
        if (article.isVideoArticle()) {
            ImageSize imageSize;
            viewHolder.qiushiImageLayout.setVisibility(8);
            viewHolder.videoLayout.setVisibility(0);
            viewHolder.sendImage.setVisibility(0);
            int[] videoWidthHeightMaxPix = ImageSizeHelper.getVideoWidthHeightMaxPix();
            int i = videoWidthHeightMaxPix[0];
            int i2 = article.isVideoArticle() ? videoWidthHeightMaxPix[0] : videoWidthHeightMaxPix[1];
            int[] videoWidthAndHeight = article.getVideoWidthAndHeight();
            if (videoWidthAndHeight[0] == 0 || videoWidthAndHeight[1] == 0) {
                imageSize = new ImageSize(i, (i2 * 4) / 9);
            } else {
                imageSize = new ImageSize(videoWidthAndHeight[0], videoWidthAndHeight[1]);
            }
            setVideoLayoutParams(viewHolder.imageTip, imageSize, setVideoLayoutParams(viewHolder.sendImage, imageSize, i, i2), i2);
            a(article, viewHolder.sendImage, viewHolder.imageTip, viewHolder.imageloading);
            viewHolder.imageTip.setVisibility(0);
            viewHolder.sendImage.setVisibility(0);
            viewHolder.player.setAspectRatio(videoWidthAndHeight[0], videoWidthAndHeight[1]);
            viewHolder.player.setVideo(article.getVideoUrl());
            viewHolder.player.pause();
            viewHolder.playBtn.setVisibility(0);
            viewHolder.playBtn.setOnClickListener(new cg(this, article, viewHolder));
            return;
        }
        viewHolder.videoLayout.setVisibility(8);
    }

    public int setVideoLayoutParams(ImageView imageView, ImageSize imageSize, int i, int i2) {
        LayoutParams layoutParams = imageView.getLayoutParams();
        int[] iArr = new int[2];
        int calWidthAndHeight = ImageSizeHelper.calWidthAndHeight(i, i2, iArr, imageSize, false);
        if (layoutParams != null) {
            layoutParams.width = iArr[0];
            layoutParams.height = iArr[1];
        } else {
            layoutParams = new LayoutParams(iArr[0], iArr[1]);
        }
        imageView.setLayoutParams(layoutParams);
        return calWidthAndHeight;
    }

    private void c(Article article, ViewHolder viewHolder) {
        viewHolder.progress.setVisibility(8);
        if (TextUtils.isEmpty(article.state) || !article.state.equals(NEW_PUBLISH)) {
            if (article.isWordsOnly()) {
                viewHolder.sendImage.setTag(null);
                viewHolder.progress.setTag(null);
                return;
            }
            viewHolder.progress.setTag(article.isVideoArticle() ? article.absPicPath : article.image);
            viewHolder.sendImage.setTag(viewHolder.progress);
        } else if (article.isWordsOnly()) {
            viewHolder.sendImage.setTag(null);
            viewHolder.progress.setTag(null);
        } else {
            viewHolder.progress.setTag(article.uuid);
            viewHolder.sendImage.setTag(viewHolder.progress);
        }
    }

    private void d(Article article, ViewHolder viewHolder) {
        int i = 8;
        if (article instanceof PostedArticle) {
            PostedArticle postedArticle = (PostedArticle) article;
            DiggerBar diggerBar = viewHolder.a;
            if (postedArticle.isBan()) {
                i = 0;
            }
            diggerBar.setVisibility(i);
            viewHolder.a.belongTo(article.id);
            viewHolder.a.removeAllViews();
            if (!postedArticle.isBan()) {
                return;
            }
            if (TextUtils.isEmpty(postedArticle.ban.repeatId)) {
                viewHolder.a.addText("", postedArticle.ban.reason, false);
                return;
            }
            i = postedArticle.ban.reason.length();
            CharSequence spannableStringBuilder = new SpannableStringBuilder(postedArticle.ban.reason + "查看相关帖子");
            int length = spannableStringBuilder.length();
            ch chVar = new ch(this, postedArticle);
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(-10248992);
            spannableStringBuilder.setSpan(chVar, i, length, 33);
            spannableStringBuilder.setSpan(foregroundColorSpan, i, length, 33);
            viewHolder.a.addText("", spannableStringBuilder, false);
            return;
        }
        viewHolder.a.setVisibility(8);
    }

    private void a(Article article, ImageView imageView, ImageView imageView2, View view) {
        if (!article.isVideoArticle() || TextUtils.isEmpty(article.filePath)) {
            String manageqiushiImageNameToUrl;
            if (this.k instanceof ManageQiuShiNewActivity) {
                manageqiushiImageNameToUrl = manageqiushiImageNameToUrl(article.id, article.image);
            } else {
                manageqiushiImageNameToUrl = imageNameToUrl(article.id, article.image);
            }
            if (article.imageInfos.size() > 0) {
                manageqiushiImageNameToUrl = ((ArticleImage) article.imageInfos.get(0)).getImageUrl();
            }
            view.setVisibility(8);
            if (!(imageView instanceof SimpleDraweeView) || imageView2 == null) {
                a(imageView, manageqiushiImageNameToUrl);
                return;
            }
            FrescoImageloader.displayImage(imageView, manageqiushiImageNameToUrl, d(), d(), new IterativeBoxBlurPostProcessor(5));
            FrescoImageloader.displayImage(imageView2, manageqiushiImageNameToUrl);
            return;
        }
        FrescoImageloader.displayImage(imageView, UriUtil.getUriForFile(new File(article.filePath)).toString(), d(), d(), new IterativeBoxBlurPostProcessor(32));
        FrescoImageloader.displayImage(imageView2, UriUtil.getUriForFile(new File(article.filePath)).toString());
    }

    public final ImageSize getDefualtImageSize() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue("saveVideInfo");
        try {
            if (!TextUtils.isEmpty(sharePreferencesValue)) {
                JSONObject jSONObject = new JSONObject(sharePreferencesValue);
                return new ImageSize(jSONObject.optInt(IndexEntry.COLUMN_NAME_WIDTH), jSONObject.optInt(IndexEntry.COLUMN_NAME_HEIGHT));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ImageSize(this.c, (b * 4) / 9);
    }

    private void a(ViewHolder viewHolder, Article article, String str) {
        if (article.isImageArticle()) {
            viewHolder.videoLayout.setVisibility(8);
            viewHolder.qiushiImageLayout.setVisibility(0);
            viewHolder.qiushiImageLayout.setImages(article.imageInfos);
            viewHolder.qiushiImageLayout.setOnChildClickListener(new ci(this, article, viewHolder));
            return;
        }
        viewHolder.qiushiImageLayout.setVisibility(8);
    }

    public String manageqiushiImageNameToUrl(String str, String str2) {
        return QsbkApp.ManageqiushiAbsoluteUrlOfMediumContentImage(str, str2);
    }

    private void a(RssArticle rssArticle, ViewHolder viewHolder) {
        SubscribeAdapter.initType(rssArticle, viewHolder.type);
    }

    private void a(ViewHolder viewHolder, String str, Article article, int i, View view) {
        int i2;
        LogUtil.e("state ====" + str);
        viewHolder.delete.setSelected(false);
        viewHolder.delete.setOnClickListener(new bw(this, view, i));
        viewHolder.collection_icon.setOnTouchListener(this.h);
        OnSupportListener bxVar = new bx(this, i, viewHolder);
        b bVar;
        if (str.equals("publish")) {
            viewHolder.state.setText("已通过");
            viewHolder.stateMsg.setVisibility(8);
            viewHolder.supportOrNotView.support.setEnabled(true);
            viewHolder.supportOrNotView.unSupport.setEnabled(true);
            viewHolder.userComment.setEnabled(true);
            viewHolder.collection_icon.setVisibility(0);
            viewHolder.delete.setVisibility(8);
            viewHolder.collection_icon.setSelected(false);
            viewHolder.collection_icon.setOnClickListener(new a(this, viewHolder.collection_icon, article.id, i, view));
            viewHolder.collection_icon.setOnTouchListener(this.h);
            viewHolder.supportOrNotView.setEnable(true);
            viewHolder.supportOrNotView.setClickable(true);
            viewHolder.supportOrNotView.setOnSupportListener(bxVar);
            e(article, viewHolder);
            bVar = new b(this, viewHolder.userComment, article.id, i, view);
            viewHolder.userComment.setSelected(false);
            viewHolder.userComment.setOnClickListener(new by(this, article, bVar));
            viewHolder.userComment.setOnTouchListener(this.h);
            if (revokeList.contains(article.id)) {
                revokeList.remove(article.id);
                SharePreferenceUtils.setRevokes(revokeList);
            }
            if (UIHelper.isNightTheme()) {
                i2 = R.drawable.my_content_pass_icon_night;
            } else {
                i2 = R.drawable.my_content_pass_icon;
            }
        } else if (str.equals(FAKE)) {
            viewHolder.state.setText("已通过");
            viewHolder.stateMsg.setVisibility(8);
            viewHolder.collection_icon.setVisibility(0);
            viewHolder.delete.setVisibility(8);
            viewHolder.collection_icon.setSelected(false);
            viewHolder.collection_icon.setOnClickListener(new a(this, viewHolder.collection_icon, article.id, i, view));
            viewHolder.collection_icon.setOnTouchListener(this.h);
            viewHolder.userComment.setEnabled(true);
            viewHolder.supportOrNotView.setEnable(true);
            viewHolder.supportOrNotView.setClickable(true);
            viewHolder.supportOrNotView.setOnSupportListener(bxVar);
            bVar = new b(this, viewHolder.userComment, article.id, i, view);
            viewHolder.userComment.setSelected(false);
            viewHolder.userComment.setOnClickListener(new bz(this, article, bVar));
            viewHolder.userComment.setOnTouchListener(this.h);
            if (UIHelper.isNightTheme()) {
                i2 = R.drawable.my_content_pass_icon_night;
            } else {
                i2 = R.drawable.my_content_pass_icon;
            }
        } else if (str.equals("pending") || str.equals(WAITING)) {
            viewHolder.state.setText("审核中");
            viewHolder.collection_icon.setVisibility(8);
            viewHolder.delete.setVisibility(0);
            viewHolder.collection_icon.setOnClickListener(null);
            viewHolder.userComment.setEnabled(false);
            viewHolder.supportOrNotView.setEnable(false);
            viewHolder.supportOrNotView.setClickable(false);
            viewHolder.stateMsg.setVisibility(0);
            StringBuffer Get_Last_Date = DateUtil.Get_Last_Date(this.k, article.created_at);
            if (TextUtils.isEmpty(Get_Last_Date)) {
                Get_Last_Date = new StringBuffer("36 小时");
            }
            viewHolder.stateMsg.setText("还剩" + Get_Last_Date.toString());
            if (UIHelper.isNightTheme()) {
                i2 = R.drawable.my_content_waiting_icon_night;
                viewHolder.stateMsg.setTextColor(this.k.getResources().getColor(R.color.psts_text_normal_dark));
            } else {
                i2 = R.drawable.my_content_waiting_icon;
                viewHolder.stateMsg.setTextColor(this.k.getResources().getColor(R.color.g_txt_middle));
            }
        } else if (str.equals(SPAM) || str.equals("private")) {
            viewHolder.state.setText("未通过");
            viewHolder.collection_icon.setVisibility(8);
            viewHolder.delete.setVisibility(0);
            viewHolder.collection_icon.setOnClickListener(null);
            viewHolder.userComment.setEnabled(false);
            viewHolder.supportOrNotView.setEnable(false);
            viewHolder.supportOrNotView.setClickable(false);
            viewHolder.stateMsg.setVisibility(8);
            if (UIHelper.isNightTheme()) {
                i2 = R.drawable.my_content_no_pass_night;
            } else {
                i2 = R.drawable.my_content_no_pass;
            }
        } else if (str.equals(REPORTED)) {
            viewHolder.state.setText("已通过");
            viewHolder.collection_icon.setVisibility(0);
            viewHolder.delete.setVisibility(8);
            viewHolder.collection_icon.setSelected(false);
            viewHolder.collection_icon.setOnClickListener(new a(this, viewHolder.collection_icon, article.id, i, view));
            viewHolder.collection_icon.setOnTouchListener(this.h);
            viewHolder.userComment.setEnabled(true);
            viewHolder.supportOrNotView.setEnable(true);
            viewHolder.supportOrNotView.setClickable(true);
            viewHolder.supportOrNotView.setOnSupportListener(bxVar);
            bVar = new b(this, viewHolder.userComment, article.id, i, view);
            viewHolder.userComment.setSelected(false);
            viewHolder.userComment.setOnClickListener(new ca(this, article, bVar));
            viewHolder.userComment.setOnTouchListener(this.h);
            viewHolder.stateMsg.setVisibility(8);
            if (UIHelper.isNightTheme()) {
                i2 = R.drawable.my_content_report_icon_night;
            } else {
                i2 = R.drawable.my_content_report_icon;
            }
        } else if (str.equals(REVOKE)) {
            if (revokeList.size() <= 0 || !revokeList.contains(article.id)) {
                viewHolder.state.setText("被举报");
                viewHolder.collection_icon.setVisibility(8);
                viewHolder.delete.setVisibility(0);
                viewHolder.collection_icon.setOnClickListener(null);
                viewHolder.userComment.setEnabled(false);
                viewHolder.supportOrNotView.setEnable(false);
                if (article.report_reason == null || article.report_reason.length() <= 0) {
                    viewHolder.stateMsg.setVisibility(8);
                } else {
                    viewHolder.stateMsg.setVisibility(0);
                    viewHolder.stateMsg.setText(article.report_reason);
                }
                if (UIHelper.isNightTheme()) {
                    i2 = R.drawable.my_content_waiting_icon_night;
                    viewHolder.stateMsg.setTextColor(this.k.getResources().getColor(R.color.psts_text_normal_dark));
                } else {
                    i2 = R.drawable.my_content_waiting_icon;
                    viewHolder.stateMsg.setTextColor(this.k.getResources().getColor(R.color.g_txt_middle));
                }
            } else {
                viewHolder.state.setText("申诉中");
                viewHolder.collection_icon.setVisibility(8);
                viewHolder.delete.setVisibility(0);
                viewHolder.collection_icon.setOnClickListener(null);
                viewHolder.userComment.setEnabled(false);
                viewHolder.supportOrNotView.setEnable(false);
                viewHolder.stateMsg.setVisibility(8);
                if (UIHelper.isNightTheme()) {
                    i2 = R.drawable.my_content_no_pass_night;
                } else {
                    i2 = R.drawable.my_content_no_pass;
                }
            }
        } else if (str.equals(APPEALING)) {
            viewHolder.state.setText("申诉中");
            viewHolder.collection_icon.setVisibility(8);
            viewHolder.delete.setVisibility(0);
            viewHolder.collection_icon.setOnClickListener(null);
            viewHolder.userComment.setEnabled(false);
            viewHolder.supportOrNotView.setEnable(false);
            viewHolder.stateMsg.setVisibility(8);
            if (UIHelper.isNightTheme()) {
                i2 = R.drawable.my_content_no_pass_night;
            } else {
                i2 = R.drawable.my_content_no_pass;
            }
        } else if (str.equals(APPEALING_FAIL)) {
            viewHolder.state.setText("申诉失败");
            viewHolder.collection_icon.setVisibility(8);
            viewHolder.delete.setVisibility(0);
            viewHolder.collection_icon.setOnClickListener(null);
            viewHolder.userComment.setEnabled(false);
            viewHolder.supportOrNotView.setEnable(false);
            viewHolder.stateMsg.setVisibility(8);
            if (revokeList.contains(article.id)) {
                revokeList.remove(article.id);
                SharePreferenceUtils.setRevokes(revokeList);
            }
            if (UIHelper.isNightTheme()) {
                i2 = R.drawable.my_content_no_pass_night;
            } else {
                i2 = R.drawable.my_content_no_pass;
            }
        } else if (!str.equals(NEW_PUBLISH)) {
            viewHolder.state.setText("火星资料备份中");
            viewHolder.collection_icon.setVisibility(8);
            viewHolder.delete.setVisibility(0);
            viewHolder.collection_icon.setOnClickListener(null);
            viewHolder.userComment.setEnabled(false);
            viewHolder.supportOrNotView.setEnable(false);
            viewHolder.stateMsg.setVisibility(8);
            if (UIHelper.isNightTheme()) {
                i2 = R.drawable.my_content_waiting_icon_night;
            } else {
                i2 = R.drawable.my_content_waiting_icon;
            }
        } else if (str.equals(NEW_PUBLISH) && article.stateExtra == 0) {
            viewHolder.state.setText("发送中");
            viewHolder.collection_icon.setVisibility(8);
            viewHolder.delete.setVisibility(0);
            viewHolder.collection_icon.setOnClickListener(null);
            viewHolder.userComment.setEnabled(false);
            viewHolder.supportOrNotView.setEnable(false);
            viewHolder.stateMsg.setVisibility(8);
            if (UIHelper.isNightTheme()) {
                i2 = R.drawable.my_content_no_pass_night;
            } else {
                i2 = R.drawable.my_content_no_pass;
            }
        } else {
            viewHolder.state.setText("投稿失败");
            viewHolder.collection_icon.setVisibility(8);
            viewHolder.delete.setVisibility(0);
            viewHolder.collection_icon.setOnClickListener(null);
            viewHolder.userComment.setEnabled(false);
            viewHolder.supportOrNotView.setEnable(false);
            viewHolder.stateMsg.setVisibility(8);
            if (UIHelper.isNightTheme()) {
                i2 = R.drawable.my_content_no_pass_night;
            } else {
                i2 = R.drawable.my_content_no_pass;
            }
        }
        viewHolder.state.setBackgroundResource(i2);
    }

    private ViewHolder a(View view, ViewHolder viewHolder) {
        viewHolder.contentLayout = (FrameLayout) view.findViewById(R.id.contentLayout);
        viewHolder.content = (TextView) view.findViewById(R.id.content);
        viewHolder.sendImage = (ImageView) view.findViewById(R.id.image);
        viewHolder.imageTip = (ImageView) view.findViewById(R.id.imageTip);
        viewHolder.videoLayout = view.findViewById(R.id.imageLayout);
        viewHolder.qiushiImageLayout = (QiushiImageLayout) view.findViewById(R.id.qiushi_image_layout);
        viewHolder.state = (TextView) view.findViewById(R.id.state);
        viewHolder.delete = (ImageView) view.findViewById(R.id.delete);
        viewHolder.sendDate = (TextView) view.findViewById(R.id.date);
        viewHolder.supportLayout = view.findViewById(R.id.points_and_comments_count_layout);
        viewHolder.supportCount = (TickerView) view.findViewById(R.id.points_and_comments_count);
        viewHolder.commentCount = (TextView) view.findViewById(R.id.comment_count);
        viewHolder.progress = (ProgressBar) view.findViewById(R.id.progress);
        viewHolder.imageloading = (TextView) view.findViewById(R.id.imageLoading);
        viewHolder.type = (TextView) view.findViewById(R.id.type);
        viewHolder.playBtn = view.findViewById(R.id.play_video);
        viewHolder.loop = (TextView) view.findViewById(R.id.loop);
        viewHolder.player = (VideoPlayerView) view.findViewById(R.id.videoView);
        viewHolder.player.setWidget(viewHolder.progress, viewHolder.playBtn, viewHolder.imageTip);
        viewHolder.player.setOnVideoEventListener(viewHolder);
        viewHolder.stateMsg = (TextView) view.findViewById(R.id.state_msg);
        viewHolder.userInfo = (RelativeLayout) view.findViewById(R.id.userInfo);
        viewHolder.userIcon = (ImageView) view.findViewById(R.id.userIcon);
        viewHolder.userName = (TextView) view.findViewById(R.id.userName);
        viewHolder.userComment = (ImageButton) view.findViewById(R.id.user_comment);
        viewHolder.supportOrNotView = (SupportOrNotView) view.findViewById(R.id.support_or_not);
        viewHolder.collection_icon = (ImageButton) view.findViewById(R.id.collection_icon);
        viewHolder.layerMask = (RelativeLayout) view.findViewById(R.id.layerMask);
        viewHolder.divider = view.findViewById(R.id.divider);
        viewHolder.a = (DiggerBar) view.findViewById(R.id.diggerbar);
        return viewHolder;
    }

    protected void a(String str, String str2, ImageView imageView) {
        b(imageView, QsbkApp.absoluteUrlOfMediumUserIcon(str2, str));
    }

    private boolean a(int i, String str, String str2, String str3) {
        return VoteManager.getInstance().vote(new Vote(this.g + (i + 1), str, str2, "1"), str, str2);
    }

    private void e(Article article, ViewHolder viewHolder) {
        viewHolder.supportOrNotView.reset();
        if (VoteManager.getInstance().containsVote(article.id, "up")) {
            viewHolder.supportOrNotView.setSupport();
        }
        if (VoteManager.getInstance().containsVote(article.id, Config.DEVICE_NAME) && !VoteManager.getInstance().containsVote(article.id, "_up")) {
            viewHolder.supportOrNotView.setUnSupport();
        }
        if (QsbkApp.allCollection.contains(article.id)) {
            viewHolder.collection_icon.setTag("active");
        } else {
            viewHolder.collection_icon.setTag("enable");
        }
    }

    protected void a(Article article, ViewHolder viewHolder) {
        int i = article.comment_count;
        if (viewHolder.supportOrNotView.isSupport()) {
            UIHelper.setSupportAndCommentTextHighlight(viewHolder.supportCount, viewHolder.commentCount, article.getDisplayLaugth(), i, article.shareCount, false);
        } else {
            UIHelper.setSupportAndCommentText(viewHolder.supportCount, viewHolder.commentCount, article.getDisplayLaugth(), i, article.shareCount, false);
        }
    }
}
