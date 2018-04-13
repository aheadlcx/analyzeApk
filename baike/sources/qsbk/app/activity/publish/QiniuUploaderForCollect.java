package qsbk.app.activity.publish;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import com.facebook.common.util.UriUtil;
import com.qiniu.auth.Authorizer;
import com.qiniu.rs.PutExtra;
import com.qiniu.rs.UploadTaskExecutor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.ImageUtils;
import qsbk.app.utils.Util;

public class QiniuUploaderForCollect {
    private OnUploadListener a;
    private String b;
    private Uri c;

    public interface OnUploadListener {
        void onUploadFail(Uri uri, String str);

        void onUploadSuccess(Uri uri, String str, String str2, int i, int i2);
    }

    private static class a extends AsyncTask<String, Void, Object[]> {
        final a a;

        interface a {
            void onDone(String str, int i, int i2);
        }

        protected /* synthetic */ void a(Object obj) {
            c((Object[]) obj);
        }

        a(a aVar) {
            this.a = aVar;
        }

        static int[] a(int i, int i2) {
            int[] iArr = new int[2];
            if (i > Publish_Image.MIN_IMG_HEIGHT || i2 > Publish_Image.MIN_IMG_HEIGHT) {
                float f = ((float) i) / ((float) i2);
                if (f > 1.0f) {
                    iArr[0] = Publish_Image.MIN_IMG_HEIGHT;
                    iArr[1] = Math.round(960.0f / f);
                } else {
                    iArr[1] = Publish_Image.MIN_IMG_HEIGHT;
                    iArr[0] = Math.round(f * 960.0f);
                }
            } else {
                iArr[0] = i;
                iArr[1] = i2;
            }
            return iArr;
        }

        static int[] a(String str, String str2) {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            int[] a = a(options.outWidth, options.outHeight);
            options.inJustDecodeBounds = false;
            options.inSampleSize = ImageUtils.calculateInSampleSize(options, Util.displaySize.x, Util.displaySize.y);
            Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeFile, a[0], a[1], true);
            if (decodeFile != createScaledBitmap) {
                decodeFile.recycle();
            }
            File file = new File(str2);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            try {
                OutputStream fileOutputStream = new FileOutputStream(file);
                if (createScaledBitmap.compress(CompressFormat.JPEG, 100, fileOutputStream)) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return a;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new int[]{0, 0};
        }

        protected Object[] a(String... strArr) {
            int[] a = a(strArr[0], DeviceUtils.getCollectSDPath() + File.separator + System.currentTimeMillis() + ".jpg");
            return new Object[]{r0, Integer.valueOf(a[0]), Integer.valueOf(a[1])};
        }

        protected void c(Object[] objArr) {
            if (this.a != null) {
                this.a.onDone((String) objArr[0], ((Integer) objArr[1]).intValue(), ((Integer) objArr[2]).intValue());
            }
        }
    }

    public QiniuUploaderForCollect(String str, Uri uri, OnUploadListener onUploadListener) {
        this.b = str;
        this.c = uri;
        this.a = onUploadListener;
    }

    public void startUpload() {
        a();
    }

    private UploadTaskExecutor a() {
        String str = System.currentTimeMillis() + ".jpg";
        PutExtra putExtra = new PutExtra();
        putExtra.params = new HashMap();
        Authorizer authorizer = new Authorizer();
        authorizer.setUploadToken(this.b);
        new a(new ce(this, authorizer, str, putExtra)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{UriUtil.getRealPathFromUri(QsbkApp.mContext.getContentResolver(), this.c)});
        return null;
    }
}
