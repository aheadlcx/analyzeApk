package qsbk.app.activity.publish;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.qiniu.auth.Authorizer;
import com.qiniu.io.IO;
import com.qiniu.rs.UploadTaskExecutor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.media.MediaFormat;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.ImageUtils;
import qsbk.app.utils.Util;

public class QiniuUploader {
    private OnUploadListener a;
    private String b;
    private Uri c;
    private String d;

    public interface OnUploadListener {
        void onUploadFail(Uri uri);

        void onUploadSuccess(Uri uri, String str);
    }

    private static class a extends AsyncTask<Uri, Void, String> {
        final a a;

        interface a {
            void onDone(String str);
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

        static void a(String str, String str2) {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            if (new File(str).exists()) {
                BitmapFactory.decodeFile(str, options);
                int i = options.outWidth;
                int i2 = options.outHeight;
                int[] a = Util.isLongImage(i, i2) ? new int[]{i, i2} : a(i, i2);
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
                    float f;
                    switch (new ExifInterface(str).getAttributeInt("Orientation", 1)) {
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
                        createScaledBitmap = ImageUtils.rotateBitmap(createScaledBitmap, f);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    OutputStream fileOutputStream = new FileOutputStream(file);
                    if (createScaledBitmap.compress(CompressFormat.JPEG, 90, fileOutputStream)) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        protected String a(Uri... uriArr) {
            String str = DeviceUtils.getSDPath() + "/qsbk/send/" + System.currentTimeMillis() + ".jpg";
            Uri uri = uriArr[0];
            String mimetype = QiniuUploader.getMimetype(uri);
            Options options = new Options();
            options.inJustDecodeBounds = true;
            if (!new File(uri.getPath()).exists()) {
                return uri.getPath();
            }
            BitmapFactory.decodeFile(uri.getPath(), options);
            int i = options.outWidth;
            int i2 = options.outHeight;
            if (!mimetype.startsWith("image") || MediaFormat.IMAGE_GIF.mimeType.equals(mimetype) || Util.isLongImage(i, i2)) {
                return uri.getPath();
            }
            a(uri.getPath(), str);
            return str;
        }

        protected void a(String str) {
            if (this.a != null) {
                this.a.onDone(str);
            }
        }
    }

    public QiniuUploader(String str, String str2, OnUploadListener onUploadListener) {
        this(str, Uri.fromFile(new File(str2)), onUploadListener);
    }

    public QiniuUploader(String str, Uri uri, OnUploadListener onUploadListener) {
        this.d = IO.UNDEFINED_KEY;
        this.b = str;
        this.c = uri;
        this.a = onUploadListener;
    }

    public QiniuUploader(String str, String str2, Uri uri, OnUploadListener onUploadListener) {
        this.d = IO.UNDEFINED_KEY;
        this.b = str;
        this.d = str2;
        this.c = uri;
        this.a = onUploadListener;
    }

    public static void uploadImage(String str, OnUploadListener onUploadListener) {
        new SimpleHttpTask(Constants.CIRCLE_IMAGE_TOKEN, new cb(str, onUploadListener)).execute();
    }

    private static void b(String str, String str2, OnUploadListener onUploadListener) {
        new QiniuUploader(str2, str, onUploadListener).startUpload();
    }

    public static String getMimetype(Uri uri) {
        String type;
        if (uri.getScheme().equals("content")) {
            type = QsbkApp.getInstance().getContentResolver().getType(uri);
        } else {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase());
        }
        return TextUtils.isEmpty(type) ? "unknow" : type;
    }

    public void startUpload() {
        a();
    }

    private UploadTaskExecutor a() {
        Authorizer authorizer = new Authorizer();
        authorizer.setUploadToken(this.b);
        new a(new cc(this, authorizer)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Uri[]{this.c});
        return null;
    }
}
