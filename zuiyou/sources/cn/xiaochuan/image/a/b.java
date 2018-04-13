package cn.xiaochuan.image.a;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCrop.Options;

public class b {
    public static void a(@NonNull Activity activity, @NonNull Uri uri, @NonNull Uri uri2, @Nullable String str) {
        a(activity, uri, uri2, activity.getResources().getDisplayMetrics().widthPixels, str);
    }

    public static void a(@NonNull Activity activity, @NonNull Uri uri, @NonNull Uri uri2, int i, @Nullable String str) {
        a(activity, uri, uri2, i, str, 1, 1);
    }

    public static void a(@NonNull Activity activity, @NonNull Uri uri, @NonNull Uri uri2, int i, @Nullable String str, int i2, int i3) {
        UCrop withAspectRatio = UCrop.of(uri, uri2).withMaxResultSize(i, i).withAspectRatio((float) i2, (float) i3);
        Options options = new Options();
        if (!TextUtils.isEmpty(str)) {
            options.setToolbarTitle("裁剪");
        }
        withAspectRatio.withOptions(options);
        withAspectRatio.start(activity);
    }

    public static Uri a(@NonNull Intent intent) {
        return (Uri) intent.getParcelableExtra(UCrop.EXTRA_OUTPUT_URI);
    }

    public static Uri a(@NonNull Activity activity, Uri uri, Uri uri2, int i) {
        return a(activity, uri, uri2, i, 1, 1);
    }

    public static Uri a(@NonNull Activity activity, Uri uri, Uri uri2, int i, int i2, int i3) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("scale", true);
        intent.putExtra("output", uri2);
        intent.putExtra("outputFormat", CompressFormat.JPEG.toString());
        intent.putExtra("return-data", false);
        intent.putExtra("noFaceDetection", true);
        activity.startActivityForResult(intent, i);
        return uri2;
    }
}
