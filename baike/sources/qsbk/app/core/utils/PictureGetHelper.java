package qsbk.app.core.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import qsbk.app.core.model.User;

public class PictureGetHelper {
    public static final int CAMERA_WITH_DATA = 0;
    public static final int IMAGE_SIZE = 640;
    public static final String KEY_CAPTURE_FILE_PATH = "CAPTURE_FILE_PATH";
    public static final int PHOTO_CROPPED_WITH_DATA = 2;
    public static final int PHOTO_PICKED_WITH_DATA = 1;
    private static final File b = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");
    public final String KEY_TEMP_FILE = "temp_camera_file_name";
    private Activity a;
    private Bitmap c = null;
    public File mCapturedFile;

    public PictureGetHelper(Activity activity, Bundle bundle) {
        this.a = activity;
        if (bundle != null) {
            Object string = bundle.getString("CAPTURE_FILE_PATH");
            if (!TextUtils.isEmpty(string)) {
                this.mCapturedFile = new File(string);
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        Object capturedFilePath = getCapturedFilePath();
        if (!TextUtils.isEmpty(capturedFilePath)) {
            bundle.putString("CAPTURE_FILE_PATH", capturedFilePath);
        }
    }

    private String a() {
        Date date = new java.sql.Date(System.currentTimeMillis());
        return new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss").format(date) + ".jpg";
    }

    public static Intent getPhotoPickIntent() {
        Intent intent = new Intent("android.intent.action.PICK", null);
        intent.setType("image/*");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", IMAGE_SIZE);
        intent.putExtra("outputY", IMAGE_SIZE);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        return intent;
    }

    private Bitmap a(Uri uri) {
        try {
            return BitmapFactory.decodeStream(this.a.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void doCropPhoto(Uri uri) {
        try {
            this.a.startActivityForResult(getCropImageIntent(uri), 2);
        } catch (Exception e) {
            ToastAndDialog.makeText(this.a, "拍照出错了", Integer.valueOf(1)).show();
        }
    }

    public static Intent getCropImageIntent(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("com/soundcloud/android/crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", IMAGE_SIZE);
        intent.putExtra("outputY", IMAGE_SIZE);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        return intent;
    }

    public void getPicFromContent() {
        try {
            this.a.startActivityForResult(getPhotoPickIntent(), 1);
        } catch (Exception e) {
            ToastAndDialog.makeText(this.a, "错误", Integer.valueOf(1)).show();
        }
    }

    public void getPicFromCapture() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            b.mkdir();
            String a = a();
            this.mCapturedFile = new File(b, a);
            PreferenceUtils.instance().putString("photoname", a);
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.putExtra("output", Uri.fromFile(this.mCapturedFile));
            try {
                this.a.startActivityForResult(intent, 0);
                return;
            } catch (ActivityNotFoundException e) {
                ToastUtil.Long("没有摄像头");
                return;
            }
        }
        ToastAndDialog.makeText(this.a, "没有sd卡", Integer.valueOf(1));
    }

    public Bitmap getPickedBitmap() {
        return this.c;
    }

    public void doCropPhotoWithCaptured() {
        doCropPhoto(Uri.fromFile(this.mCapturedFile));
    }

    public String savePickedBitmap(Intent intent) {
        Bitmap a;
        Bundle extras = intent.getExtras();
        if (extras == null || extras.get("data") == null) {
            LogUtils.d("pickedBitmapData:" + intent.getData());
            a = a(intent.getData());
            if (a == null) {
                ToastUtil.Short("选择的图片为空");
                return null;
            }
        }
        a = (Bitmap) extras.get("data");
        this.c = a;
        String str = this.a.getCacheDir() + "/medium";
        User user = AppUtils.getInstance().getUserInfoProvider().getUser();
        if (user != null) {
            return FileUtils.saveDrawable(a, user.id + "_" + System.currentTimeMillis(), str, null, true);
        }
        return FileUtils.saveDrawable(a, "image" + System.currentTimeMillis(), str, null, true);
    }

    public String getCapturedFilePath() {
        return this.mCapturedFile != null ? this.mCapturedFile.getAbsolutePath() : null;
    }

    public Uri getCapturedUri() {
        if (this.mCapturedFile == null) {
            this.mCapturedFile = new File(b, PreferenceUtils.instance().getString("photoname", ""));
        }
        return Uri.fromFile(this.mCapturedFile);
    }
}
