package qsbk.app.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import qsbk.app.R;
import qsbk.app.im.image.IMImageSize;
import qsbk.app.im.image.IMImageSizeHelper;
import qsbk.app.im.image.IMImageSizeHelper.Size;
import qsbk.app.utils.LogUtil;
import qsbk.app.widget.ButtonBar;
import qsbk.app.widget.imageview.TouchImageView;

public class EditIMImageActivity extends FragmentActivity {
    public static final String EXTRA_IMAGE_URI = "image_uri";
    public static final String EXTRA_OUTPUT_SIZE_BIG = "size_big";
    public static final String EXTRA_OUTPUT_SIZE_INFO_BIG = "size_info_big";
    public static final String EXTRA_OUTPUT_SIZE_INFO_MEDIUM = "size_info_medium";
    public static final String EXTRA_OUTPUT_SIZE_INFO_ORIGIN = "size_info_origin";
    public static final String EXTRA_OUTPUT_SIZE_INFO_SMALL = "size_info_small";
    public static final String EXTRA_OUTPUT_SIZE_MEDIUM = "size_medium";
    public static final String EXTRA_OUTPUT_SIZE_SMALL = "size_small";
    public static final int RESULT_CANCELED_THEN_SELECT_OR_TAKEPHOTO_AGAIN = 2;
    private static final String a = EditIMImageActivity.class.getSimpleName();
    private Uri b;
    private Uri c;
    private Uri d;
    private Uri e;
    private TouchImageView f;
    private ButtonBar g;
    private ProgressBar h;
    private ProgressDialog i;

    public static int[] getWidthAndHeight(Context context, Uri uri) {
        Closeable stream;
        Object obj;
        try {
            stream = getStream(context, uri);
            obj = null;
        } catch (FileNotFoundException e) {
            obj = e;
            obj.printStackTrace();
            stream = null;
        }
        if (obj != null) {
            Log.e(a, "Get stream of " + uri + " failed. " + obj);
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(stream, null, options);
        a(stream);
        return new int[]{options.outWidth, options.outHeight};
    }

    public static IMImageSize cropImage(Context context, Uri uri, Uri uri2, Config config, Size size) {
        Closeable stream;
        Object obj;
        Closeable openOutputStream;
        IOException e;
        Throwable th;
        OutOfMemoryError e2;
        try {
            stream = getStream(context, uri);
            obj = null;
        } catch (FileNotFoundException e3) {
            obj = e3;
            obj.printStackTrace();
            stream = null;
        }
        if (obj != null) {
            Log.e(a, "Get stream of " + uri + " failed. " + obj);
            return null;
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(stream, null, options);
        IMImageSize imageSize = IMImageSizeHelper.getImageSize(size, options.outWidth, options.outHeight, context);
        LogUtil.d(size + " IMImageSize " + imageSize);
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        if (config == null) {
            config = Config.RGB_565;
        }
        options.inPreferredConfig = config;
        try {
            stream = getStream(context, uri);
            Bitmap decodeStream = BitmapFactory.decodeStream(stream, null, options);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeStream, imageSize.getDstWidth(), imageSize.getDstHeight(), true);
            if (createScaledBitmap != decodeStream) {
                decodeStream.recycle();
            }
            openOutputStream = context.getContentResolver().openOutputStream(uri2);
            try {
                if (createScaledBitmap.compress(CompressFormat.JPEG, 100, openOutputStream)) {
                    openOutputStream.flush();
                }
                a(stream);
                a(openOutputStream);
            } catch (IOException e4) {
                e = e4;
                try {
                    e.printStackTrace();
                    a(stream);
                    a(openOutputStream);
                    return imageSize;
                } catch (Throwable th2) {
                    th = th2;
                    a(stream);
                    a(openOutputStream);
                    throw th;
                }
            } catch (OutOfMemoryError e5) {
                e2 = e5;
                e2.printStackTrace();
                a(stream);
                a(openOutputStream);
                return imageSize;
            }
        } catch (IOException e6) {
            IOException iOException = e6;
            openOutputStream = null;
            e = iOException;
            e.printStackTrace();
            a(stream);
            a(openOutputStream);
            return imageSize;
        } catch (OutOfMemoryError e7) {
            OutOfMemoryError outOfMemoryError = e7;
            openOutputStream = null;
            e2 = outOfMemoryError;
            e2.printStackTrace();
            a(stream);
            a(openOutputStream);
            return imageSize;
        } catch (Throwable th3) {
            openOutputStream = null;
            th = th3;
            a(stream);
            a(openOutputStream);
            throw th;
        }
        return imageSize;
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static InputStream getStream(Context context, Uri uri) throws FileNotFoundException {
        Object scheme = uri.getScheme();
        if (TextUtils.isEmpty(scheme)) {
            throw new RuntimeException("origin image uri cannot be null.");
        } else if ("content".equals(scheme) || UriUtil.LOCAL_FILE_SCHEME.equals(scheme)) {
            return context.getContentResolver().openInputStream(uri);
        } else {
            return new FileInputStream(scheme);
        }
    }

    public static final Intent getEditImageIntent(Context context, Uri uri, Uri uri2, Uri uri3, Uri uri4) {
        Intent intent = new Intent(context, EditIMImageActivity.class);
        intent.putExtra(EXTRA_IMAGE_URI, uri);
        intent.putExtra(EXTRA_OUTPUT_SIZE_BIG, uri2);
        intent.putExtra(EXTRA_OUTPUT_SIZE_MEDIUM, uri3);
        intent.putExtra(EXTRA_OUTPUT_SIZE_SMALL, uri4);
        return intent;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.activity_edit_im_image);
        Intent intent = getIntent();
        this.b = (Uri) intent.getParcelableExtra(EXTRA_OUTPUT_SIZE_SMALL);
        this.c = (Uri) intent.getParcelableExtra(EXTRA_OUTPUT_SIZE_MEDIUM);
        this.d = (Uri) intent.getParcelableExtra(EXTRA_OUTPUT_SIZE_BIG);
        this.e = (Uri) intent.getParcelableExtra(EXTRA_IMAGE_URI);
        if (this.e == null) {
            Log.e(a, "Extra Image Uri not found.");
            setResult(0);
            finish();
        }
        b();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        setResult(2);
        finish();
        return true;
    }

    public void finish() {
        super.finish();
        this.f.setImageDrawable(null);
    }

    private void a() {
        if (this.i == null) {
            this.i = ProgressDialog.show(this, null, "请稍候...", true, false);
        }
        this.i.show();
    }

    private void b() {
        this.g = (ButtonBar) findViewById(R.id.id_top_bar);
        this.h = (ProgressBar) findViewById(R.id.progressbar);
        this.f = (TouchImageView) findViewById(R.id.multitouchimageview);
        this.g.setOnClickListener(new jx(this), new jy(this));
        a();
        this.f.setVisibility(0);
        c();
        this.i.dismiss();
        this.h.setVisibility(8);
    }

    private void c() {
        if (this.e != null) {
            int[] widthAndHeight = getWidthAndHeight(this, this.e);
            if (widthAndHeight != null && widthAndHeight.length == 2 && widthAndHeight[1] > (widthAndHeight[0] << 1)) {
                this.f.setScaleType(ScaleType.CENTER_CROP);
            }
        }
        Fresco.getImagePipeline().fetchDecodedImage(ImageRequest.fromUri(this.e.toString()), getApplicationContext()).subscribe(new ka(this), UiThreadImmediateExecutorService.getInstance());
    }

    private Intent d() {
        Parcelable cropImage;
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IMAGE_URI, this.e);
        int[] widthAndHeight = getWidthAndHeight(this, this.e);
        if (widthAndHeight != null) {
            intent.putExtra(EXTRA_OUTPUT_SIZE_INFO_ORIGIN, new IMImageSize(widthAndHeight[0], widthAndHeight[1]));
        }
        if (this.d != null) {
            cropImage = cropImage(this, this.e, this.d, null, Size.Big);
            if (cropImage != null) {
                intent.putExtra(EXTRA_OUTPUT_SIZE_BIG, this.d);
                intent.putExtra(EXTRA_OUTPUT_SIZE_INFO_BIG, cropImage);
            }
        }
        if (this.c != null) {
            cropImage = cropImage(this, this.e, this.c, null, Size.Medium);
            if (cropImage != null) {
                intent.putExtra(EXTRA_OUTPUT_SIZE_MEDIUM, this.c);
                intent.putExtra(EXTRA_OUTPUT_SIZE_INFO_MEDIUM, cropImage);
            }
        }
        if (this.b != null) {
            cropImage = cropImage(this, this.e, this.b, null, Size.Small);
            if (cropImage != null) {
                intent.putExtra(EXTRA_OUTPUT_SIZE_SMALL, this.b);
                intent.putExtra(EXTRA_OUTPUT_SIZE_INFO_SMALL, cropImage);
            }
        }
        return intent;
    }
}
