package qsbk.app.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.facebook.imagepipeline.request.Postprocessor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import qsbk.app.QsbkApp;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.model.Article;
import qsbk.app.model.ImageInfo;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.TileBackground;
import qsbk.app.utils.TileBackground.BgImageType;
import qsbk.app.widget.Recyclable;

public abstract class BaseImageAdapter<T> extends DefaultAdapter<T> {
    public static final String IMAGELOADWAY_TEXTONLY = "textonly";
    public static final String WIFI = "wifi";
    protected static String d = null;

    public static class ImageLazyLoadListener implements OnClickListener {
        private String a;
        private String b;
        private ImageView c;
        private View d;

        public ImageLazyLoadListener(String str, ImageView imageView, View view, String str2) {
            this.a = str2;
            this.b = str;
            this.c = imageView;
            this.d = view;
        }

        public void onClick(View view) {
            FrescoImageloader.displayImage(this.c, this.b, TileBackground.getBackgroud(view.getContext(), BgImageType.ARTICLE));
            this.d.setVisibility(8);
        }
    }

    public static class ProgressDisplayer implements Recyclable {
        static final List<String> a = Collections.synchronizedList(new LinkedList());
        static final Map<String, Integer> b = Collections.synchronizedMap(new HashMap());
        static final List<String> c = Collections.synchronizedList(new ArrayList());

        private void a(View view, String str, boolean z) {
            if (view != null && str.equalsIgnoreCase((String) view.getTag())) {
                view.setVisibility(0);
                ((ProgressBar) view).setIndeterminate(z);
            }
        }

        private void a(View view, String str) {
            if (view != null && str.equalsIgnoreCase((String) view.getTag())) {
                view.setVisibility(8);
            }
        }

        public boolean isDisplaying(String str) {
            return c.contains(str);
        }

        public boolean displaying(String str) {
            return c.add(str);
        }

        public boolean removeDisplaying(String str) {
            return c.remove(str);
        }

        public void onLoadingStarted(String str, View view) {
            if (!isDisplaying(str)) {
                displaying(str);
            }
            ProgressBar progressBar = (ProgressBar) view.getTag();
            progressBar.setTag(str);
            Integer num = (Integer) b.get(str);
            if (num == null || num.intValue() < 10) {
                a(progressBar, str, true);
                return;
            }
            progressBar.setProgress(num.intValue());
            a(progressBar, str, false);
        }

        public void onLoadingFailed(String str, View view) {
            removeDisplaying(str);
            if (view != null) {
                a((ProgressBar) view.getTag(), str);
            }
        }

        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            removeDisplaying(str);
            if (view != null) {
                a((ProgressBar) view.getTag(), str);
                if (bitmap != null) {
                    if ((!a.contains(str) ? 1 : null) != null) {
                        a.add(str);
                    }
                }
            }
        }

        public void onLoadingCancelled(String str, View view) {
            removeDisplaying(str);
            if (view != null) {
                a((ProgressBar) view.getTag(), str);
            }
        }

        public void onProgressUpdate(String str, View view, int i, int i2) {
            if (view != null) {
                ProgressBar progressBar = (ProgressBar) view.getTag();
                if (a.contains(str)) {
                    a(progressBar, str);
                } else if (i == i2) {
                    a(progressBar, str);
                } else {
                    a(progressBar, str, false);
                    int i3 = (i * 100) / i2;
                    b.put(str, Integer.valueOf(i3));
                    if (progressBar != null) {
                        progressBar.setProgress(i3);
                    }
                }
            }
        }

        public void recycle() {
            a.clear();
            b.clear();
            c.clear();
        }
    }

    public BaseImageAdapter(ArrayList<T> arrayList, Activity activity) {
        super(arrayList, activity);
        c();
    }

    public static boolean doNotLoadImageDirectly() {
        if (TextUtils.isEmpty(d)) {
            d = SharePreferenceUtils.getSharePreferencesValue("imageLoadWay");
            if (TextUtils.isEmpty(d)) {
                d = "auto";
            }
        }
        return d.equals(IMAGELOADWAY_TEXTONLY) || (d.equals("wifi") && !HttpUtils.isWifi(QsbkApp.getInstance()));
    }

    protected void c() {
    }

    protected Drawable d() {
        return TileBackground.getBackgroud(this.k, BgImageType.ARTICLE);
    }

    protected void a(ImageView imageView, String str) {
        a(imageView, str, d(), null);
    }

    public void displayImage(ImageView imageView, String str, Postprocessor postprocessor) {
        FrescoImageloader.displayImage(imageView, str, d(), d(), postprocessor);
    }

    protected void a(ImageView imageView, String str, Drawable drawable, Drawable drawable2) {
        FrescoImageloader.displayImage(imageView, str, drawable, drawable2);
    }

    protected void a(ImageView imageView, String str, boolean z) {
        FrescoImageloader.displayAvatar(imageView, str, z);
    }

    protected void a(ImageView imageView, String str, boolean z, int i) {
        FrescoImageloader.displayAvatar(imageView, str, 0, z, i);
    }

    protected void b(ImageView imageView, String str) {
        FrescoImageloader.displayAvatar(imageView, str);
    }

    public String imageNameToUrl(String str, String str2) {
        return QsbkApp.absoluteUrlOfMediumContentImage(str, str2);
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onStop() {
    }

    public String getImageLoadWay() {
        return d;
    }

    public void setImageLoadWay(String str) {
        d = str;
    }

    public void clearImageCache() {
        super.clearImageCache();
        if (this.m == null || this.m.isEmpty()) {
            LogUtil.d("dataSource is empty.");
            return;
        }
        List arrayList = new ArrayList();
        int size = this.m.size();
        for (int i = 0; i < size; i++) {
            CharSequence charSequence;
            Object obj = this.m.get(i);
            if (obj instanceof Article) {
                Article article = (Article) obj;
                if (article.isVideoArticle()) {
                    charSequence = article.absPicPath;
                } else {
                    charSequence = imageNameToUrl(article.id, article.image);
                }
            } else if (obj instanceof ImageInfo) {
                charSequence = ((ImageInfo) obj).url;
            } else {
                charSequence = null;
            }
            if (!TextUtils.isEmpty(charSequence)) {
                arrayList.add(charSequence);
            }
        }
        FrescoImageloader.evictFromMemoryCache(arrayList);
    }

    public void onDestroy() {
        super.onDestroy();
    }
}
