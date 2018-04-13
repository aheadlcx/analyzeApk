package qsbk.app.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.HashMap;
import qsbk.app.R;
import qsbk.app.adapter.ArticleAdapter.AcrossChangeDate;
import qsbk.app.adapter.ArticleAdapter.ViewHolder;
import qsbk.app.image.ImageSizeHelper;
import qsbk.app.model.Article;
import qsbk.app.model.ImageSize;
import qsbk.app.utils.LogUtil;
import qsbk.app.video.SimpleVideoPlayerView;

public class BaseVideoAdapter extends ArticleAdapter {
    private HashMap<String, Integer> e;

    public BaseVideoAdapter(Activity activity, ListView listView, ArrayList<Object> arrayList, String str, String str2) {
        this(activity, listView, arrayList, str, str2, null);
    }

    public BaseVideoAdapter(Activity activity, ListView listView, ArrayList<Object> arrayList, String str, String str2, AcrossChangeDate acrossChangeDate) {
        super(activity, listView, arrayList, str, str2, acrossChangeDate);
        this.e = new HashMap();
    }

    protected int a() {
        return R.layout.layout_article_item;
    }

    protected void a(Article article, ViewHolder viewHolder, int i, View view) {
        super.a(article, viewHolder, i, view);
        if (viewHolder.videoPlayer instanceof SimpleVideoPlayerView) {
            if (article.isGIFArticle()) {
                ((SimpleVideoPlayerView) viewHolder.videoPlayer).setDisplayMode(1);
            } else {
                ((SimpleVideoPlayerView) viewHolder.videoPlayer).setDisplayMode(0);
            }
        }
        if (article.isVideoArticle() || article.isGIFArticle()) {
            viewHolder.videoPlayer.setVisibility(0);
            viewHolder.videoPlayer.setVideo(article.getVideoUrl());
            int[] videoWidthAndHeight = article.getVideoWidthAndHeight();
            int i2 = videoWidthAndHeight[0];
            int i3 = videoWidthAndHeight[1];
            if (!(i2 == 0 || i3 == 0)) {
                viewHolder.videoPlayer.setAspectRatio(i2, i3);
            }
            if (article.isGIFArticle()) {
                viewHolder.durationView.setVisibility(8);
            } else {
                viewHolder.durationView.setText(article.getVideoDurationText());
                ((SimpleVideoPlayerView) viewHolder.videoPlayer).setStrTotalTime(article.getVideoDurationText());
                viewHolder.durationView.setVisibility(0);
            }
            View playBtn = viewHolder.videoPlayer.getPlayBtn();
            if (article.isGIFArticle()) {
                i3 = 8;
            } else {
                i3 = 0;
            }
            playBtn.setVisibility(i3);
            viewHolder.loop.setVisibility(0);
        } else {
            viewHolder.durationView.setVisibility(8);
            viewHolder.videoPlayer.getPlayBtn().setVisibility(4);
            viewHolder.videoPlayer.getProgressBar().setVisibility(8);
            viewHolder.videoPlayer.setVisibility(8);
            viewHolder.videoPlayer.reset();
        }
        viewHolder.playFalgView.setVisibility(8);
    }

    protected void a(Article article, ViewHolder viewHolder) {
        if (article.isVideoArticle() || article.isGIFArticle()) {
            ImageSize imageSize;
            int[] videoWidthHeightMaxPix = ImageSizeHelper.getVideoWidthHeightMaxPix();
            int i = videoWidthHeightMaxPix[0];
            int i2 = article.isVideoArticle() ? videoWidthHeightMaxPix[0] : videoWidthHeightMaxPix[1];
            int[] videoWidthAndHeight = article.getVideoWidthAndHeight();
            if (videoWidthAndHeight[0] == 0 || videoWidthAndHeight[1] == 0) {
                imageSize = new ImageSize(i, (i2 * 4) / 9);
            } else {
                imageSize = new ImageSize(videoWidthAndHeight[0], videoWidthAndHeight[1]);
            }
            i2 = setVideoLayoutParams(viewHolder.videoPreView, imageSize, i, i2);
            if (videoWidthAndHeight[0] < videoWidthAndHeight[1]) {
                this.e.put(article.absPicPath, Integer.valueOf(i2));
                return;
            }
            return;
        }
        super.a(article, viewHolder);
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

    protected void c(Article article, ViewHolder viewHolder) {
        if (!article.isVideoArticle() && !article.isGIFArticle()) {
            super.c(article, viewHolder);
        } else if (TextUtils.isEmpty(article.absPicPath) || "null".equalsIgnoreCase(article.absPicPath)) {
            viewHolder.videoPreView.setTag(null);
            viewHolder.progress.setTag(null);
        } else {
            viewHolder.progress.setTag(article.absPicPath);
            viewHolder.videoPreView.setTag(viewHolder.progress);
        }
    }

    protected void d(Article article, ViewHolder viewHolder) {
        super.d(article, viewHolder);
        viewHolder.gifTag.setVisibility(article.isGIFArticle() ? 0 : 8);
        if (article.isVideoArticle()) {
            String str = article.absPicPath;
            if (TextUtils.isEmpty(str) || "null".equalsIgnoreCase(str)) {
                viewHolder.videoPreView.setVisibility(8);
                viewHolder.videoLayout.setVisibility(8);
            } else {
                BasePostprocessor aeVar;
                viewHolder.videoLayout.setVisibility(0);
                viewHolder.videoPreView.setVisibility(0);
                if (BaseImageAdapter.doNotLoadImageDirectly()) {
                    viewHolder.imageLoading.setVisibility(0);
                    ((TextView) viewHolder.imageLoading).setText("点击加载");
                } else {
                    viewHolder.imageLoading.setVisibility(8);
                }
                Integer num = (Integer) this.e.get(str);
                int i = viewHolder.videoPreView.getLayoutParams().width;
                if (num != null) {
                    aeVar = new ae(this, i, num, str);
                } else {
                    aeVar = null;
                }
                a(str, viewHolder.videoPreView, aeVar);
            }
            viewHolder.videoPreView.setOnClickListener(new af(this, article, viewHolder));
        }
    }

    protected void a(String str, ImageView imageView, BasePostprocessor basePostprocessor) {
        if (!TextUtils.isEmpty(str) && !"null".equalsIgnoreCase(str) && imageView != null) {
            displayImage(imageView, str, basePostprocessor);
        } else if (imageView != null) {
            imageView.setVisibility(4);
        }
    }

    public void onStop() {
        super.onStop();
        LogUtil.d("on stop in baseVideo adapter");
        int childCount = this.l.getChildCount();
        for (int i = 0; i < childCount; i++) {
            Object tag = this.l.getChildAt(i).getTag();
            if (tag != null && (tag instanceof ViewHolder)) {
                ((ViewHolder) tag).videoPlayer.reset();
            }
        }
    }

    protected void e(Article article, ViewHolder viewHolder) {
        super.e(article, viewHolder);
        if (article.isVideoArticle()) {
            CharSequence loopString = article.getLoopString();
            if (!loopString.startsWith(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)) {
                loopString = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + loopString;
            }
            viewHolder.loop.setText(loopString);
            return;
        }
        viewHolder.loop.setText("");
    }

    public void onDestroy() {
        this.e.clear();
        super.onDestroy();
    }
}
