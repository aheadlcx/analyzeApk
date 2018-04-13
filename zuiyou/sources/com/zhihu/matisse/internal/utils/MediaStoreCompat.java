package com.zhihu.matisse.internal.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.os.EnvironmentCompat;
import com.zhihu.matisse.BuildConfig;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MediaStoreCompat {
    private final CaptureStrategy mCaptureStrategy = new CaptureStrategy(true, BuildConfig.PROVIDER);
    private final WeakReference<Activity> mContext;
    private String mCurrentPhotoPath;
    private Uri mCurrentPhotoUri;
    private final WeakReference<Fragment> mFragment;

    public MediaStoreCompat(Activity activity) {
        this.mContext = new WeakReference(activity);
        this.mFragment = null;
    }

    public MediaStoreCompat(Activity activity, Fragment fragment) {
        this.mContext = new WeakReference(activity);
        this.mFragment = new WeakReference(fragment);
    }

    public static boolean hasCameraFeature(Context context) {
        return context.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    public void dispatchCaptureIntent(Context context, int i) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            File createImageFile;
            File file = null;
            try {
                createImageFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
                createImageFile = file;
            }
            if (createImageFile != null) {
                this.mCurrentPhotoPath = createImageFile.getAbsolutePath();
                this.mCurrentPhotoUri = FileProvider.getUriForFile((Context) this.mContext.get(), this.mCaptureStrategy.authority, createImageFile);
                intent.putExtra("output", this.mCurrentPhotoUri);
                intent.addFlags(2);
                if (VERSION.SDK_INT < 21) {
                    for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 65536)) {
                        context.grantUriPermission(resolveInfo.activityInfo.packageName, this.mCurrentPhotoUri, 3);
                    }
                }
                if (this.mFragment != null) {
                    ((Fragment) this.mFragment.get()).startActivityForResult(intent, i);
                } else {
                    ((Activity) this.mContext.get()).startActivityForResult(intent, i);
                }
            }
        }
    }

    private File createImageFile() throws IOException {
        File externalStoragePublicDirectory;
        String format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String format2 = String.format("JPEG_%s.jpg", new Object[]{format});
        if (this.mCaptureStrategy.isPublic) {
            externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        } else {
            externalStoragePublicDirectory = ((Activity) this.mContext.get()).getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        }
        File file = new File(externalStoragePublicDirectory, format2);
        if ("mounted".equals(EnvironmentCompat.getStorageState(file))) {
            return file;
        }
        return null;
    }

    public Uri getCurrentPhotoUri() {
        return this.mCurrentPhotoUri;
    }

    public String getCurrentPhotoPath() {
        return this.mCurrentPhotoPath;
    }
}
