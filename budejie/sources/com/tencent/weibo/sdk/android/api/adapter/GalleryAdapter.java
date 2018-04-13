package com.tencent.weibo.sdk.android.api.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.PopupWindow;
import com.tencent.weibo.sdk.android.api.util.ImageLoaderAsync;
import com.tencent.weibo.sdk.android.api.util.ImageLoaderAsync.callBackImage;
import com.tencent.weibo.sdk.android.model.ImageInfo;
import java.util.ArrayList;

public class GalleryAdapter extends BaseAdapter {
    private ArrayList<ImageInfo> imageList;
    private ImageLoaderAsync imageLoader = new ImageLoaderAsync();
    private Context myContext;
    private PopupWindow popView;

    public GalleryAdapter(Context context, PopupWindow popupWindow, ArrayList<ImageInfo> arrayList) {
        this.myContext = context;
        this.imageList = arrayList;
        this.popView = popupWindow;
    }

    public int getCount() {
        return this.imageList.size();
    }

    public Object getItem(int i) {
        return this.imageList.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        final View imageView = new ImageView(this.myContext);
        this.imageLoader.loadImage(((ImageInfo) this.imageList.get(i)).getImagePath(), new callBackImage() {
            public void callback(Drawable drawable, String str) {
                if (drawable != null) {
                    imageView.setImageDrawable(drawable);
                }
            }
        });
        imageView.setScaleType(ScaleType.FIT_XY);
        imageView.setLayoutParams(new LayoutParams(-1, -1));
        if (this.popView != null && this.popView.isShowing()) {
            this.popView.dismiss();
        }
        return imageView;
    }
}
