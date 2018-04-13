package com.meizu.cloud.pushsdk.notification.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import com.meizu.cloud.a.a;
import java.io.IOException;

public class ResourceReader {
    private static final String TAG = "ResourceReader";
    private static ResourceReader mInstance;
    private AssetManager mAssetManager;
    private Context mContext;

    private ResourceReader(Context context) {
        this.mContext = context;
        init();
    }

    public static ResourceReader getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new ResourceReader(context);
        }
        return mInstance;
    }

    private void init() {
        this.mAssetManager = this.mContext.getAssets();
    }

    public Drawable getDrawable(String str) {
        Drawable drawable = null;
        try {
            drawable = Drawable.createFromStream(this.mAssetManager.open(str), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawable;
    }

    public Bitmap getBitmap(String str) {
        try {
            return BitmapFactory.decodeStream(this.mAssetManager.open(str));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getResId(String str, String str2) {
        a.a(TAG, "Get resource type " + str2 + " " + str);
        return this.mContext.getResources().getIdentifier(str, str2, this.mContext.getApplicationInfo().packageName);
    }
}
