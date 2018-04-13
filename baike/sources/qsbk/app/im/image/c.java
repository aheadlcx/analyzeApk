package qsbk.app.im.image;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.media.ExifInterface;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.common.util.UriUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.im.image.ImageUploader.ImageUploadTask;
import qsbk.app.im.image.ImageUploader.UploadImageCallback;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.ImageUtils;
import qsbk.app.utils.Md5;
import qsbk.app.utils.Util;

class c extends AsyncTask<Void, Void, String> {
    final /* synthetic */ UploadImageCallback a;
    final /* synthetic */ Uri b;
    final /* synthetic */ Object c;
    final /* synthetic */ ImageUploadTask d;
    final /* synthetic */ ImageUploader e;

    c(ImageUploader imageUploader, UploadImageCallback uploadImageCallback, Uri uri, Object obj, ImageUploadTask imageUploadTask) {
        this.e = imageUploader;
        this.a = uploadImageCallback;
        this.b = uri;
        this.c = obj;
        this.d = imageUploadTask;
    }

    private String b(String str) {
        String stringBuffer = new StringBuffer(DeviceUtils.isExternalStorageAvailable() ? DeviceUtils.getSDPath() : QsbkApp.mContext.getFilesDir().getAbsolutePath()).append(File.separator).append(QsbkApp.mContext.getPackageName()).append(File.separator).append("im_tmp_dir").append(File.separator).toString();
        File file = new File(stringBuffer);
        if (!file.exists()) {
            file.mkdirs();
        }
        return stringBuffer + Md5.MD5(str);
    }

    private boolean a(Uri uri) {
        String b = b(uri.toString());
        Bitmap decodeBitmap = ImageUtils.decodeBitmap(QsbkApp.mContext, uri.toString(), IMImageSizeHelper.MAX, IMImageSizeHelper.MAX, Config.ARGB_8888);
        if (decodeBitmap == null) {
            return false;
        }
        if (!Util.isLongImage(decodeBitmap.getWidth(), decodeBitmap.getHeight())) {
            decodeBitmap = ImageUtils.scaleBitmapIfNecessary(decodeBitmap, IMImageSizeHelper.MAX, IMImageSizeHelper.MAX, true);
        }
        try {
            float f;
            switch (new ExifInterface(UriUtil.getRealPathFromUri(QsbkApp.getInstance().getContentResolver(), uri)).getAttributeInt("Orientation", 1)) {
                case 3:
                    f = 180.0f;
                    break;
                case 6:
                    f = 90.0f;
                    break;
                case 8:
                    f = 270.0f;
                    break;
                default:
                    f = 0.0f;
                    break;
            }
            if (f > 0.0f) {
                decodeBitmap = ImageUtils.rotateBitmap(decodeBitmap, f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            OutputStream fileOutputStream = new FileOutputStream(b);
            if (decodeBitmap.compress(CompressFormat.JPEG, 90, fileOutputStream)) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    protected void a() {
        this.a.onStart(this.b, this.c);
    }

    protected String a(Void... voidArr) {
        if (a(this.b)) {
            return this.e.a(this.e.a);
        }
        return null;
    }

    protected void a(String str) {
        super.a(str);
        if (TextUtils.isEmpty(str)) {
            this.a.onFaiure(this.b, "", this.c);
            return;
        }
        this.d.setUploadTaskExecutor(this.e.a(str, this.b, b(this.b.toString()), this.a, this.c));
    }
}
