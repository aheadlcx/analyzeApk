package com.budejie.www.activity.image;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.Size;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.wireless.security.SecExceptionCode;
import com.budejie.www.R;
import com.budejie.www.activity.DetailContentActivity;
import com.budejie.www.activity.RecordActivity;
import com.budejie.www.http.i;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.budejie.www.util.h;
import com.budejie.www.util.p;
import java.io.File;
import java.util.List;

@SuppressLint({"NewApi"})
public class CaptureActivity extends Activity implements OnClickListener, com.budejie.www.activity.image.b.a {
    static final String b = CaptureActivity.class.getSimpleName();
    private TextView A;
    b a;
    private ImageView c;
    private ImageView d;
    private CheckBox e;
    private ImageView f;
    private ImageView g;
    private FrameLayout h;
    private a i;
    private CameraCropBorderView j;
    private Camera k;
    private PictureCallback l;
    private AutoFocusCallback m;
    private b n;
    private View o;
    private int p;
    private int q;
    private boolean r;
    private int s;
    private List<d> t;
    private a u;
    private int v;
    private String w;
    private String x;
    private String y;
    private TextView z;

    public class a extends SurfaceView implements Callback {
        final /* synthetic */ CaptureActivity a;
        private SurfaceHolder b = getHolder();
        private Camera c;

        public a(CaptureActivity captureActivity, Context context, Camera camera) {
            this.a = captureActivity;
            super(context);
            this.c = camera;
            this.b.addCallback(this);
            this.b.setType(3);
        }

        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            try {
                this.c.setPreviewDisplay(surfaceHolder);
                this.c.startPreview();
            } catch (Exception e) {
                Log.d(CaptureActivity.b, "Error setting camera preview: " + e.getMessage());
            }
        }

        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            if (this.a.k != null) {
                this.a.k.setPreviewCallback(null);
                this.a.k.stopPreview();
                this.a.k.release();
                this.a.k = null;
            }
        }

        private Size a(List<Size> list, int i, int i2) {
            double d = ((double) i) / ((double) i2);
            if (list == null) {
                return null;
            }
            Size size;
            double abs;
            Size size2 = null;
            double d2 = Double.MAX_VALUE;
            for (Size size3 : list) {
                if (Math.abs((((double) size3.width) / ((double) size3.height)) - d) <= 0.05d) {
                    if (((double) Math.abs(size3.height - i2)) < d2) {
                        size = size3;
                        abs = (double) Math.abs(size3.height - i2);
                    } else {
                        double d3 = d2;
                        size = size2;
                        abs = d3;
                    }
                    size2 = size;
                    d2 = abs;
                }
            }
            if (size2 != null) {
                return size2;
            }
            d2 = Double.MAX_VALUE;
            for (Size size32 : list) {
                if (((double) Math.abs(size32.height - i2)) < d2) {
                    size = size32;
                    abs = (double) Math.abs(size32.height - i2);
                } else {
                    d3 = d2;
                    size = size2;
                    abs = d3;
                }
                size2 = size;
                d2 = abs;
            }
            return size2;
        }

        private Size a(List<Size> list, double d) {
            if (list == null) {
                return null;
            }
            Size size = null;
            int i = 0;
            double d2 = Double.MAX_VALUE;
            for (Size size2 : list) {
                int i2;
                Size size3;
                double abs;
                int max = Math.max(size2.width, size2.height);
                Object obj = null;
                if (max < SecExceptionCode.SEC_ERROR_SAFETOKEN) {
                    if (i == 0 || max > i) {
                        obj = 1;
                    }
                } else if (1600 > i) {
                    obj = 1;
                } else {
                    double abs2 = Math.abs((((double) size2.width) / ((double) size2.height)) - d);
                    if (0.05d + abs2 < d2) {
                        obj = 1;
                    } else if (abs2 < 0.05d + d2 && max < i) {
                        obj = 1;
                    }
                }
                if (obj != null) {
                    i2 = max;
                    size3 = size2;
                    abs = Math.abs((((double) size2.width) / ((double) size2.height)) - d);
                } else {
                    abs = d2;
                    i2 = i;
                    size3 = size;
                }
                i = i2;
                size = size3;
                d2 = abs;
            }
            return size;
        }

        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            Log.e("", "ljj-->surfaceChanged format:" + i + ", w:" + i2 + ", h:" + i3);
            if (this.b.getSurface() != null) {
                try {
                    this.c.stopPreview();
                } catch (Exception e) {
                }
                try {
                    Parameters parameters = this.c.getParameters();
                    Size a = a(parameters.getSupportedPreviewSizes(), i3, i2);
                    parameters.setPreviewSize(a.width, a.height);
                    a = a(parameters.getSupportedPictureSizes(), ((double) i3) / ((double) i2));
                    parameters.setPictureSize(a.width, a.height);
                    parameters.setRotation(0);
                    this.c.setParameters(parameters);
                } catch (Exception e2) {
                    Log.e("", "ljj-->" + e2.toString());
                }
                try {
                    this.c.setPreviewDisplay(this.b);
                    this.c.startPreview();
                } catch (Exception e22) {
                    Log.e("", "ljj-->Error starting camera preview: " + e22.getMessage());
                }
            }
        }
    }

    private class b extends OrientationEventListener {
        final /* synthetic */ CaptureActivity a;

        public b(CaptureActivity captureActivity, Context context) {
            this.a = captureActivity;
            super(context);
        }

        public void onOrientationChanged(int i) {
            if (this.a.k != null && i != -1) {
                int i2 = ((i + 45) / 90) * 90;
                if (VERSION.SDK_INT <= 8) {
                    this.a.s = (i2 + 90) % 360;
                    return;
                }
                CameraInfo cameraInfo = new CameraInfo();
                Camera.getCameraInfo(this.a.p, cameraInfo);
                if (cameraInfo.facing == 1) {
                    this.a.s = ((cameraInfo.orientation - i2) + 360) % 360;
                } else {
                    this.a.s = (i2 + cameraInfo.orientation) % 360;
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        this.n = new b(this);
        this.a = new b(this, this);
        super.onCreate(bundle);
        setContentView(R.layout.activity_capture);
        this.x = getIntent().getStringExtra("type");
        this.y = getIntent().getStringExtra("reserve");
        a();
        j();
        b();
        f();
        this.w = getIntent().getStringExtra("output");
    }

    protected void onDestroy() {
        if (this.n != null) {
            this.n.a(null);
            this.n = null;
        }
        this.a = null;
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        e();
        this.n.b();
        this.a.disable();
    }

    protected void onResume() {
        super.onResume();
        g();
    }

    private void e() {
        if (this.k != null) {
            this.k.release();
            this.k = null;
        }
        if (this.i != null) {
            this.h.removeAllViews();
            this.i = null;
        }
    }

    protected void a() {
        this.c = (ImageView) findViewById(R.id.bnToggleCamera);
        this.d = (ImageView) findViewById(R.id.bnCapture);
        this.e = (CheckBox) findViewById(R.id.bnFlash);
        this.f = (ImageView) findViewById(R.id.bnClose);
        this.g = (ImageView) findViewById(R.id.bnAlbum);
        this.h = (FrameLayout) findViewById(R.id.cameraPreview);
        this.o = findViewById(R.id.viewFocuse);
        this.z = (TextView) findViewById(R.id.tv_upload_sound_web);
        this.A = (TextView) findViewById(R.id.capture_title);
        if ("voice".equals(this.x)) {
            LayoutParams layoutParams = (LayoutParams) this.z.getLayoutParams();
            layoutParams.addRule(11);
            layoutParams.addRule(12);
            layoutParams.bottomMargin = (((h() - i()) / 3) * 2) - com.budejie.www.activity.video.a.a(this, 36);
            layoutParams.rightMargin = com.budejie.www.activity.video.a.a(this, 15);
            this.z.setLayoutParams(layoutParams);
            this.z.setVisibility(0);
            this.A.setVisibility(0);
            return;
        }
        this.z.setVisibility(8);
        this.A.setVisibility(8);
    }

    protected void b() {
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.n.a(this);
        this.z.setOnClickListener(this);
        this.l = new PictureCallback(this) {
            final /* synthetic */ CaptureActivity a;

            {
                this.a = r1;
            }

            public void onPictureTaken(byte[] bArr, Camera camera) {
                this.a.r = false;
                Bitmap bitmap = null;
                try {
                    Options options = new Options();
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                    options.inJustDecodeBounds = false;
                    options.inPreferredConfig = Config.ARGB_8888;
                    options.inSampleSize = Math.max(options.outWidth / SecExceptionCode.SEC_ERROR_SAFETOKEN, options.outHeight / SecExceptionCode.SEC_ERROR_SAFETOKEN);
                    bitmap = h.a(bArr, options);
                    if (bitmap == null) {
                        options.inSampleSize = Math.max(2, options.inSampleSize * 2);
                        bitmap = h.a(bArr, options);
                    }
                } catch (Throwable th) {
                }
                if (bitmap == null) {
                    Toast.makeText(this.a, "内存不足，保存照片失败！", 0).show();
                    return;
                }
                Bitmap a = h.a(bitmap, this.a.s, 1600.0f, true);
                Bitmap a2 = this.a.a(a);
                File file = new File(this.a.w);
                for (boolean a3 = h.a(a2, file, CompressFormat.JPEG, 100); !a3; a3 = h.a(a2, file, CompressFormat.JPEG, 100)) {
                }
                if (!(a2 == null || a2.isRecycled())) {
                    a.recycle();
                }
                if ("voice".equals(this.a.x)) {
                    p.a(this.a, this.a.w, 640, 640);
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("photo_path", file.getAbsolutePath());
                Log.e("", "ljj-->kPhotoPath: " + file.getAbsolutePath());
                this.a.setResult(-1, intent);
                this.a.finish();
            }
        };
        this.m = new AutoFocusCallback(this) {
            final /* synthetic */ CaptureActivity a;

            {
                this.a = r1;
            }

            public void onAutoFocus(boolean z, Camera camera) {
                this.a.o.setVisibility(4);
            }
        };
    }

    private Bitmap a(Bitmap bitmap) {
        int width;
        int height;
        int i;
        float f;
        Bitmap createBitmap;
        Bitmap bitmap2;
        int width2 = bitmap.getWidth();
        int height2 = bitmap.getHeight();
        if (height2 <= width2) {
            width = getWindowManager().getDefaultDisplay().getWidth();
            height = getWindowManager().getDefaultDisplay().getHeight();
            i = width;
        } else {
            height = getWindowManager().getDefaultDisplay().getWidth();
            i = getWindowManager().getDefaultDisplay().getHeight();
        }
        Rect rect = new Rect();
        int width3 = (height - this.j.getRect().width()) / 2;
        width = (i - this.j.getRect().height()) / 3;
        rect.set(width3, width, this.j.getRect().width() + width3, this.j.getRect().height() + width);
        if (width2 <= height || height2 > i) {
            f = 1.0f;
        } else {
            f = (((float) height) * 1.0f) / ((float) width2);
        }
        if (height2 > i && width2 <= height) {
            f = (((float) i) * 1.0f) / ((float) height2);
        }
        if (width2 > height && height2 > i) {
            f = Math.max((((float) height) * 1.0f) / ((float) width2), (((float) i) * 1.0f) / ((float) height2));
        }
        if (width2 < height && height2 < i) {
            f = (((float) height) * 1.0f) / ((float) width2);
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f, f);
        try {
            createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width2, height2, matrix, true);
            if (!(createBitmap == null || bitmap == createBitmap)) {
                bitmap.recycle();
                bitmap = createBitmap;
            }
            bitmap2 = bitmap;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            bitmap2 = bitmap;
        }
        try {
            createBitmap = Bitmap.createBitmap(bitmap2, rect.left, rect.top, rect.width(), rect.height());
            if (!(createBitmap == null || bitmap2 == createBitmap)) {
                bitmap2.recycle();
                Log.e("", "ljj-->width:" + height + "--height:" + i);
                width = Math.min(height, i);
                bitmap2 = Bitmap.createScaledBitmap(createBitmap, width, width, false);
                if (bitmap2 == null && createBitmap != bitmap2) {
                    createBitmap.recycle();
                    return bitmap2;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        createBitmap = bitmap2;
        Log.e("", "ljj-->width:" + height + "--height:" + i);
        try {
            width = Math.min(height, i);
            bitmap2 = Bitmap.createScaledBitmap(createBitmap, width, width, false);
            return bitmap2 == null ? createBitmap : createBitmap;
        } catch (OutOfMemoryError e3) {
            e3.printStackTrace();
            return createBitmap;
        }
    }

    private void f() {
        if (VERSION.SDK_INT > 8) {
            int numberOfCameras = Camera.getNumberOfCameras();
            if (numberOfCameras < 1) {
                Toast.makeText(this, "你的设备木有摄像头。。。", 0).show();
                finish();
                return;
            }
            if (numberOfCameras == 1) {
                this.c.setVisibility(4);
            } else {
                this.c.setVisibility(0);
            }
            this.p = 0;
            this.q = m();
            if (-1 == this.q) {
                this.c.setVisibility(4);
            }
        }
    }

    private void g() {
        if (VERSION.SDK_INT > 8) {
            try {
                this.k = Camera.open(this.p);
                if (this.k == null) {
                    throw new Exception("camera is empty!");
                }
                a(this, this.p, this.k);
            } catch (Exception e) {
                Toast.makeText(this, "摄像头打开失败", 0).show();
                finish();
                return;
            }
        }
        try {
            this.k = Camera.open();
        } catch (Exception e2) {
            Toast.makeText(this, "摄像头打开失败", 0).show();
            finish();
            return;
        }
        for (Size size : this.k.getParameters().getSupportedPreviewSizes()) {
            Log.v(b, "w:" + size.width + ",h:" + size.height);
        }
        this.i = new a(this, this, this.k);
        this.j = new CameraCropBorderView(this);
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        ViewGroup.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        this.h.addView(this.i, layoutParams);
        this.h.addView(this.j, layoutParams2);
        this.n.a();
        this.a.enable();
    }

    private int h() {
        return getWindowManager().getDefaultDisplay().getHeight();
    }

    private int i() {
        return getWindowManager().getDefaultDisplay().getWidth();
    }

    public void c() {
        try {
            Intent intent = new Intent("android.intent.action.PICK");
            intent.setDataAndType(Media.INTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, 714);
        } catch (Exception e) {
            an.a((Activity) this, getString(R.string.no_available_album), -1).show();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 714 && i2 == -1) {
            if (intent == null) {
                return;
            }
            if ("voice".equals(this.x)) {
                p.a((Activity) this, intent, 640, 640);
                return;
            }
            Intent intent2 = new Intent();
            intent2.putExtra("filepath", p.a((Activity) this, intent));
            setResult(-1, intent2);
            finish();
        } else if (i == 728 && i2 == -1 && intent != null) {
            if (this.y == null) {
                this.y = "";
            }
            p.a(intent, (Activity) this, RecordActivity.class, this.y);
            i.a(getString(R.string.track_action_send_voice), getString(R.string.track_event_picture_ready));
            finish();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnClose:
                finish();
                return;
            case R.id.bnFlash:
                if (this.k != null) {
                    Parameters parameters = this.k.getParameters();
                    if (parameters.getFlashMode().equals("on")) {
                        parameters.setFlashMode("off");
                        this.e.setChecked(true);
                    } else if (parameters.getFlashMode().equals("off")) {
                        parameters.setFlashMode("on");
                        this.e.setChecked(false);
                    }
                    this.k.setParameters(parameters);
                    return;
                }
                return;
            case R.id.bnCapture:
                l();
                return;
            case R.id.bnToggleCamera:
                k();
                return;
            case R.id.bnAlbum:
                c();
                return;
            case R.id.tv_upload_sound_web:
                String string = getString(R.string.help_page_url);
                Intent intent = new Intent(this, DetailContentActivity.class);
                intent.putExtra("operator", "help");
                intent.putExtra("url", string);
                startActivity(intent);
                return;
            default:
                return;
        }
    }

    private void j() {
        this.u = a.a();
        this.u.a(getApplicationContext());
        this.t = this.u.c(true);
        if (this.t == null || this.t.size() <= 0) {
            this.g.setImageResource(R.drawable.icon_album);
            return;
        }
        int size = this.t.size();
        for (int i = 0; i < size; i++) {
            if ("Camera".equals(((d) this.t.get(i)).b)) {
                this.v = i;
                break;
            }
        }
        try {
            ImageItem imageItem = (ImageItem) ((d) this.t.get(this.v)).c.get(0);
            aa.e(b, "imageItem.thumbnailPath-->" + imageItem.thumbnailPath);
            if (TextUtils.isEmpty(imageItem.thumbnailPath) || "null".equalsIgnoreCase(imageItem.thumbnailPath)) {
                this.g.setImageResource(R.drawable.icon_album);
            } else {
                this.g.setImageBitmap(BitmapFactory.decodeFile(imageItem.thumbnailPath));
            }
        } catch (Exception e) {
            this.g.setImageResource(R.drawable.icon_album);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    private void k() {
        if (this.p == 0) {
            this.p = this.q;
        } else {
            this.p = 0;
        }
        e();
        g();
    }

    private void l() {
        if (!this.r) {
            this.r = true;
            this.o.setVisibility(4);
            try {
                this.k.takePicture(null, null, this.l);
            } catch (RuntimeException e) {
                e.printStackTrace();
                this.r = false;
            }
        }
    }

    private int m() {
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            CameraInfo cameraInfo = new CameraInfo();
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 1) {
                return cameraInfo.facing;
            }
        }
        return -1;
    }

    private static void a(Activity activity, int i, Camera camera) {
        int i2 = 0;
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case 1:
                i2 = 90;
                break;
            case 2:
                i2 = 180;
                break;
            case 3:
                i2 = 270;
                break;
        }
        if (cameraInfo.facing == 1) {
            i2 = (360 - ((i2 + cameraInfo.orientation) % 360)) % 360;
        } else {
            i2 = ((cameraInfo.orientation - i2) + 360) % 360;
        }
        camera.setDisplayOrientation(i2);
    }

    public void d() {
        if (this.k != null && !this.r) {
            this.k.cancelAutoFocus();
            try {
                this.k.autoFocus(this.m);
                if (4 == this.o.getVisibility()) {
                    this.o.setVisibility(0);
                    this.o.getParent().requestTransparentRegion(this.i);
                }
            } catch (Exception e) {
                Log.e("", "ljj-->" + e.toString());
            }
        }
    }
}
