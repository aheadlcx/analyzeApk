package com.androidex.widget.asyncimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.budejie.www.R;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.video.a;
import com.budejie.www.e.e;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.c.b;
import com.nostra13.universalimageloader.core.d.c;
import com.nostra13.universalimageloader.core.d.d;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import pl.droidsonroids.gif.GifDrawable;

public class AsyncImageView extends ImageView {
    public static int CACHESIZE = 37748736;
    private static final String TAG = "AsyncImageView";
    static final List<String> displayedImages = Collections.synchronizedList(new LinkedList());
    private static final String filePath = (BudejieApplication.g.getPackageName().equals("com.budejie.www") ? "budejie" : BudejieApplication.g.getString(R.string.app_name));
    public static File mImageDir = new File(a.a() ? Environment.getExternalStorageDirectory() : BudejieApplication.g.getCacheDir(), filePath + File.separator + "ImageCache");
    private Context mContext;
    ImageListener mImageListener;
    private c mImageLoadingListener;
    private d mImageLoadingProgressListener;
    private com.nostra13.universalimageloader.core.assist.c mImageSize;
    private String[] mImageUri;

    public interface ImageListener {
        void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable);

        void onLoadingFailed(String str, View view, FailReason failReason);

        void onLoadingStarted(String str, View view);

        void onProgressUpdate(String str, View view, int i);
    }

    public interface ProfileImageListener extends ImageListener {
        void onLoadingCancelled(String str, View view);
    }

    public AsyncImageView(Context context) {
        super(context);
        this.mContext = context;
    }

    public AsyncImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public AsyncImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void setAsyncCacheImage(String str, String str2, int i) {
        String a = com.lt.a.a(this.mContext).a(str);
        if (a == null || !(a.startsWith("https://") || a.startsWith("http://") || a.startsWith("file://"))) {
            if (this.mImageLoadingListener == null || this.mImageLoadingProgressListener == null) {
                com.nostra13.universalimageloader.core.d.a().a(str2, new b(this, false), getOptionsByRseId(i));
                return;
            }
            com.nostra13.universalimageloader.core.d.a().a(str2, new b(this, false), getOptionsByRseId(i), this.mImageLoadingListener, this.mImageLoadingProgressListener);
        } else if (this.mImageLoadingListener == null || this.mImageLoadingProgressListener == null) {
            com.nostra13.universalimageloader.core.d.a().a(a, new b(this, false), getOptionsByRseId(i));
        } else {
            com.nostra13.universalimageloader.core.d.a().a(a, new b(this, false), getOptionsByRseId(i), this.mImageLoadingListener, this.mImageLoadingProgressListener);
        }
    }

    public void setAsyncCacheImage(String str, int i) {
        String a = com.lt.a.a(this.mContext).a(str);
        if (this.mImageLoadingListener == null || this.mImageLoadingProgressListener == null) {
            com.nostra13.universalimageloader.core.d.a().a(a, new b(this, false), getOptionsByRseId(i));
        } else {
            com.nostra13.universalimageloader.core.d.a().a(a, new b(this, false), getOptionsByRseId(i), this.mImageLoadingListener, this.mImageLoadingProgressListener);
        }
    }

    public void setPostAvatarImage(String str) {
        String a = com.lt.a.a(this.mContext).a(str);
        if ("barrage_empty".equals(a)) {
            com.nostra13.universalimageloader.core.d.a().a("", (ImageView) this, e.i());
        } else {
            com.nostra13.universalimageloader.core.d.a().a(a, (ImageView) this, e.a(1));
        }
    }

    public void setRichPostLinkImage(String str) {
        com.nostra13.universalimageloader.core.d.a().a(com.lt.a.a(this.mContext).a(str), (ImageView) this, e.g());
    }

    public void setRecommendVideoImage(String str) {
        com.nostra13.universalimageloader.core.d.a().a(com.lt.a.a(this.mContext).a(str), (ImageView) this, e.h());
    }

    public void setPostImage(String str) {
        String a = com.lt.a.a(this.mContext).a(str);
        this.mImageUri = null;
        this.mImageSize = null;
        com.nostra13.universalimageloader.core.d.a().a(a, new b(this, false), e.b(), this.mImageLoadingListener, this.mImageLoadingProgressListener);
    }

    public void loadProfileImage(String str) {
        com.nostra13.universalimageloader.core.d.a().a(com.lt.a.a(this.mContext).a(str), new ImageView(getContext()), e.b(), this.mImageLoadingListener, this.mImageLoadingProgressListener);
    }

    public void setPostImage(String str, String str2) {
        this.mImageUri = new String[]{str, str2};
        this.mImageSize = null;
        if (str != null && (str.startsWith("https://") || str.startsWith("http://") || str.startsWith("file://"))) {
            str2 = str;
        }
        com.nostra13.universalimageloader.core.d.a().a(com.lt.a.a(this.mContext).a(str2), new b(this, false), e.b(), this.mImageLoadingListener, this.mImageLoadingProgressListener);
    }

    public void setFoldPostImage(String str, String str2, int i, int i2) {
        this.mImageUri = new String[]{str, str2};
        if (str != null && (str.startsWith("https://") || str.startsWith("http://") || str.startsWith("file://"))) {
            str2 = str;
        }
        com.nostra13.universalimageloader.core.assist.c cVar = new com.nostra13.universalimageloader.core.assist.c(i, i2);
        this.mImageSize = cVar;
        setFoldPostImage(str2, cVar);
    }

    private void setFoldPostImage(String str, com.nostra13.universalimageloader.core.assist.c cVar) {
        com.nostra13.universalimageloader.core.d.a().a(com.lt.a.a(this.mContext).a(str), new com.budejie.www.e.c(this, cVar), e.b(), this.mImageLoadingListener, this.mImageLoadingProgressListener);
    }

    public void setStaggeredFoldImage(String str, String str2, int i, int i2, Drawable drawable, com.nostra13.universalimageloader.core.b.a aVar, int i3) {
        String str3;
        this.mImageUri = new String[]{str, str2};
        if (str == null || !(str.startsWith("https://") || str.startsWith("http://") || str.startsWith("file://"))) {
            str3 = str2;
        } else {
            str3 = str;
        }
        com.nostra13.universalimageloader.core.assist.c cVar = new com.nostra13.universalimageloader.core.assist.c(i, i2);
        this.mImageSize = cVar;
        setStaggeredFoldImage(str3, cVar, drawable, aVar, i3);
    }

    public void setStaggeredFoldImage(String str, com.nostra13.universalimageloader.core.assist.c cVar, Drawable drawable, com.nostra13.universalimageloader.core.b.a aVar, int i) {
        com.nostra13.universalimageloader.core.c a;
        com.nostra13.universalimageloader.core.c.a cVar2 = new com.budejie.www.e.c(this, cVar);
        com.nostra13.universalimageloader.core.d a2 = com.nostra13.universalimageloader.core.d.a();
        String a3 = com.lt.a.a(this.mContext).a(str);
        if (i == 0) {
            a = e.a(drawable, aVar);
        } else {
            a = e.b(drawable, aVar);
        }
        a2.a(a3, cVar2, a);
    }

    public void setAsyncCacheImage(String str) {
        String a = com.lt.a.a(this.mContext).a(str);
        if (this.mImageLoadingListener == null || this.mImageLoadingProgressListener == null) {
            Log.d(TAG, "displayImage()");
            com.nostra13.universalimageloader.core.d.a().a(a, new b(this, false), e.b());
            return;
        }
        Log.d(TAG, "displayImage(listener)");
        com.nostra13.universalimageloader.core.d.a().a(a, new b(this, false), e.b(), this.mImageLoadingListener, this.mImageLoadingProgressListener);
    }

    private com.nostra13.universalimageloader.core.c getOptionsByRseId(int i) {
        switch (i) {
            case R.drawable.defaut_bg:
                return e.c();
            case R.drawable.head_portrait:
                return e.a(1);
            case R.drawable.label_default_icon:
                return e.a();
            case R.drawable.lable_default_bg:
                return e.f();
            case R.drawable.likelist_defaut_bg:
                return e.d();
            case R.drawable.rich_post_no_pic:
                return e.g();
            case R.drawable.search_duanzi_bg:
                return e.e();
            case R.color.apply_listview_cacahecolor:
                return e.b();
            case R.color.head_portrait_female_round:
                return e.a(2);
            case R.color.head_portrait_male_round:
                return e.a(1);
            case R.color.transparent:
                return e.b();
            default:
                return e.b();
        }
    }

    public void reloadAsyncImage(String str) {
        Log.d(TAG, "reloadAsyncImage");
        setAsyncCacheImage(str);
    }

    public static void clearCache() {
        com.nostra13.universalimageloader.core.d.a().c();
        com.nostra13.universalimageloader.core.d.a().f();
    }

    public void setImageListener(ImageListener imageListener) {
        setImageListenerSpare(imageListener);
    }

    public void setImageListenerSpare(ImageListener imageListener) {
        if (imageListener != null) {
            this.mImageListener = imageListener;
            if (this.mImageLoadingListener == null) {
                this.mImageLoadingListener = new com.nostra13.universalimageloader.core.d.b() {
                    public void onLoadingStarted(String str, View view) {
                        AsyncImageView.this.mImageListener.onLoadingStarted(str, view);
                    }

                    public void onLoadingFailed(String str, View view, FailReason failReason) {
                        if (AsyncImageView.this.mImageUri == null || !str.equals(AsyncImageView.this.mImageUri[0]) || str.equals(AsyncImageView.this.mImageUri[1]) || TextUtils.isEmpty(AsyncImageView.this.mImageUri[1])) {
                            AsyncImageView.this.mImageListener.onLoadingFailed(str, view, failReason);
                        } else if (AsyncImageView.this.mImageSize != null) {
                            AsyncImageView.this.setFoldPostImage(AsyncImageView.this.mImageUri[1], AsyncImageView.this.mImageSize);
                        } else {
                            AsyncImageView.this.setPostImage(AsyncImageView.this.mImageUri[1]);
                        }
                    }

                    public void onLoadingComplete(String str, View view, Bitmap bitmap, GifDrawable gifDrawable) {
                        if (!(bitmap == null && gifDrawable == null)) {
                            ImageView imageView = (ImageView) view;
                            if ((!AsyncImageView.displayedImages.contains(str) ? 1 : null) != null) {
                                com.nostra13.universalimageloader.core.b.c.a(imageView, 500);
                                AsyncImageView.displayedImages.add(str);
                            }
                        }
                        AsyncImageView.this.mImageListener.onLoadingComplete(str, view, bitmap, gifDrawable);
                    }

                    public void onLoadingCancelled(String str, View view) {
                        if (AsyncImageView.this.mImageListener instanceof ProfileImageListener) {
                            ((ProfileImageListener) AsyncImageView.this.mImageListener).onLoadingCancelled(str, view);
                        }
                    }
                };
                this.mImageLoadingProgressListener = new d() {
                    public void onProgressUpdate(String str, View view, int i, int i2) {
                        AsyncImageView.this.mImageListener.onProgressUpdate(str, view, (i * 100) / i2);
                    }
                };
            }
        }
    }
}
