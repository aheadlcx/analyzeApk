package com.budejie.www.activity.clip;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClipPictureActivity extends MonitoredActivity implements OnTouchListener {
    private int A = 255;
    private int B;
    private int C;
    private ScaleGestureDetector D;
    private com.budejie.www.activity.clip.a.c E;
    private com.budejie.www.activity.clip.a.b F;
    private com.budejie.www.activity.clip.a.d G;
    final int a = 1024;
    boolean b;
    private CompressFormat c = CompressFormat.JPEG;
    private ContentResolver d;
    private final Handler e = new Handler();
    private ImageView f;
    private ClipView g;
    private TextView h;
    private TextView i;
    private int j;
    private int k;
    private int l;
    private int m;
    private String n;
    private Uri o = null;
    private String p;
    private Bitmap q;
    private Rect r;
    private boolean s;
    private int t;
    private int u;
    private Matrix v = new Matrix();
    private float w = 0.4f;
    private float x = 0.0f;
    private float y = 0.0f;
    private float z = 0.0f;

    private class a extends com.budejie.www.activity.clip.a.b.b {
        final /* synthetic */ ClipPictureActivity a;

        private a(ClipPictureActivity clipPictureActivity) {
            this.a = clipPictureActivity;
        }

        public boolean a(com.budejie.www.activity.clip.a.b bVar) {
            PointF b = bVar.b();
            this.a.y = this.a.y + b.x;
            this.a.z = b.y + this.a.z;
            return true;
        }
    }

    private class b extends com.budejie.www.activity.clip.a.c.b {
        final /* synthetic */ ClipPictureActivity a;

        private b(ClipPictureActivity clipPictureActivity) {
            this.a = clipPictureActivity;
        }

        public boolean a(com.budejie.www.activity.clip.a.c cVar) {
            this.a.x = this.a.x - cVar.b();
            return true;
        }
    }

    private class c extends SimpleOnScaleGestureListener {
        final /* synthetic */ ClipPictureActivity a;

        private c(ClipPictureActivity clipPictureActivity) {
            this.a = clipPictureActivity;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            this.a.w = this.a.w * scaleGestureDetector.getScaleFactor();
            this.a.w = Math.max(0.1f, Math.min(this.a.w, 10.0f));
            return true;
        }
    }

    private class d extends com.budejie.www.activity.clip.a.d.b {
        final /* synthetic */ ClipPictureActivity a;

        private d(ClipPictureActivity clipPictureActivity) {
            this.a = clipPictureActivity;
        }

        public boolean a(com.budejie.www.activity.clip.a.d dVar) {
            this.a.A = (int) (((float) this.a.A) + dVar.b());
            if (this.a.A > 255) {
                this.a.A = 255;
            } else if (this.a.A < 0) {
                this.a.A = 0;
            }
            return true;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.clip_layout);
        this.d = getContentResolver();
        a();
        b();
        c();
    }

    private void a() {
        this.D = new ScaleGestureDetector(getApplicationContext(), new c());
        this.E = new com.budejie.www.activity.clip.a.c(getApplicationContext(), new b());
        this.F = new com.budejie.www.activity.clip.a.b(getApplicationContext(), new a());
        this.G = new com.budejie.www.activity.clip.a.d(getApplicationContext(), new d());
    }

    private void b() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.l = extras.getInt("outputX", 512);
            this.m = extras.getInt("outputY", 512);
            this.n = extras.getString("image-path");
            if (extras.containsKey("aspectX") && (extras.get("aspectX") instanceof Integer)) {
                this.j = extras.getInt("aspectX");
                if (extras.containsKey("aspectY") && (extras.get("aspectY") instanceof Integer)) {
                    this.k = extras.getInt("aspectY");
                    this.q = c(this.n);
                    this.p = a(this.n);
                    this.o = b(this.p);
                } else {
                    throw new IllegalArgumentException("aspect_y must be integer");
                }
            }
            throw new IllegalArgumentException("aspect_x must be integer");
        }
        if (this.q == null) {
            Log.d("ClipPictureActivity", "finish!!!");
            Toast.makeText(getApplicationContext(), R.string.clip_bitmap_faild, 1).show();
            finish();
        }
    }

    private String a(String str) {
        String path = Environment.getExternalStorageDirectory().getPath();
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf("/");
        String substring = str.substring(lastIndexOf + 1);
        String[] split = substring.split("\\.");
        if (split == null || split.length < 2) {
            substring = substring + "_clip";
        } else {
            substring = split[0] + "_clip." + split[split.length - 1];
        }
        if (!str.startsWith(path) || str.startsWith("/system")) {
            return path + "/" + substring;
        }
        return str.substring(0, lastIndexOf + 1) + substring;
    }

    private void c() {
        this.g = (ClipView) findViewById(R.id.clipview);
        this.f = (ImageView) findViewById(R.id.src_pic);
        this.f.setOnTouchListener(this);
        this.f.setScaleType(ScaleType.MATRIX);
        this.f.setImageBitmap(this.q);
        this.s = true;
        this.g.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener(this) {
            final /* synthetic */ ClipPictureActivity a;

            {
                this.a = r1;
            }

            public boolean onPreDraw() {
                if (this.a.s) {
                    this.a.s = false;
                    this.a.d();
                }
                return true;
            }
        });
        this.h = (TextView) findViewById(R.id.edit_pic_sure_text);
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ClipPictureActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    this.a.e();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.i = (TextView) findViewById(R.id.edit_pic_cancel_text);
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ClipPictureActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        });
        ((ImageView) findViewById(R.id.edit_pic_rote_imageview)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ClipPictureActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.x <= 0.0f) {
                    this.a.x = 270.0f;
                } else {
                    this.a.x = this.a.x - 90.0f;
                }
                this.a.a(this.a.f);
            }
        });
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.D.onTouchEvent(motionEvent);
        this.F.a(motionEvent);
        ImageView imageView = (ImageView) view;
        a(imageView);
        imageView.setAlpha(this.A);
        return true;
    }

    private void a(ImageView imageView) {
        float f = (((float) this.C) * this.w) / 2.0f;
        float f2 = (((float) this.B) * this.w) / 2.0f;
        this.v.reset();
        this.v.postScale(this.w, this.w);
        this.v.postRotate(this.x, f, f2);
        this.v.postTranslate(this.y - f, this.z - f2);
        imageView.setImageMatrix(this.v);
    }

    private void d() {
        this.t = this.g.getWidth();
        this.u = this.g.getHeight();
        int i = this.t / 2;
        int i2 = this.u / 2;
        int i3 = this.t - 100;
        int i4 = (this.m * i3) / this.l;
        this.r = new Rect(i - (i3 / 2), i2 - (i4 / 2), i + (i3 / 2), i2 + (i4 / 2));
        this.g.setClipRect(this.r);
        this.C = this.q.getWidth();
        this.B = this.q.getHeight();
        this.w = ((float) (this.r.width() > this.r.height() ? this.r.width() : this.r.height())) / ((float) (this.C > this.B ? this.C : this.B));
        this.y = (float) (this.t / 2);
        this.z = (float) (this.u / 2);
        a(this.f);
    }

    private Bitmap a(View view) {
        view.clearFocus();
        view.setPressed(false);
        boolean willNotCacheDrawing = view.willNotCacheDrawing();
        view.setWillNotCacheDrawing(false);
        int drawingCacheBackgroundColor = view.getDrawingCacheBackgroundColor();
        view.setDrawingCacheBackgroundColor(0);
        if (drawingCacheBackgroundColor != 0) {
            view.destroyDrawingCache();
        }
        view.buildDrawingCache();
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(this.l, this.m, Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(drawingCache, this.g.getClipRect(), new Rect(0, 0, this.l, this.m), new Paint());
        view.destroyDrawingCache();
        view.setWillNotCacheDrawing(willNotCacheDrawing);
        view.setDrawingCacheBackgroundColor(drawingCacheBackgroundColor);
        return createBitmap;
    }

    private Uri b(String str) {
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            if (file != null) {
                return Uri.fromFile(file);
            }
        }
        return null;
    }

    private Bitmap c(String str) {
        int i = 1;
        Uri b = b(str);
        if (b != null) {
            try {
                InputStream openInputStream = this.d.openInputStream(b);
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(openInputStream, null, options);
                openInputStream.close();
                if (options.outHeight > 1024 || options.outWidth > 1024) {
                    i = (int) Math.pow(2.0d, (double) ((int) Math.round(Math.log(1024.0d / ((double) Math.max(options.outHeight, options.outWidth))) / Math.log(0.5d))));
                }
                Options options2 = new Options();
                options2.inSampleSize = i;
                InputStream openInputStream2 = this.d.openInputStream(b);
                Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream2, null, options2);
                openInputStream2.close();
                return decodeStream;
            } catch (FileNotFoundException e) {
                Log.e("ClipPictureActivity", "file " + str + " not found");
            } catch (IOException e2) {
                Log.e("ClipPictureActivity", "file " + str + " not found");
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }

    private void e() throws Exception {
        if (!this.b) {
            this.b = true;
            final Object a = a(this.f);
            Bundle extras = getIntent().getExtras();
            if (extras == null || !extras.getBoolean("return-data")) {
                a.a(this, null, "正在保存图片。。。", new Runnable(this) {
                    final /* synthetic */ ClipPictureActivity b;

                    public void run() {
                        this.b.a(a);
                    }
                }, this.e);
                return;
            }
            extras = new Bundle();
            extras.putParcelable("data", a);
            setResult(-1, new Intent().setAction("inline-data").putExtras(extras));
            finish();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.graphics.Bitmap r6) {
        /*
        r5 = this;
        r0 = r5.o;
        if (r0 == 0) goto L_0x0069;
    L_0x0004:
        r1 = 0;
        r0 = r5.d;	 Catch:{ IOException -> 0x003e }
        r2 = r5.o;	 Catch:{ IOException -> 0x003e }
        r1 = r0.openOutputStream(r2);	 Catch:{ IOException -> 0x003e }
        if (r1 == 0) goto L_0x0016;
    L_0x000f:
        r0 = r5.c;	 Catch:{ IOException -> 0x003e }
        r2 = 90;
        r6.compress(r0, r2, r1);	 Catch:{ IOException -> 0x003e }
    L_0x0016:
        com.budejie.www.activity.clip.a.a(r1);
        r0 = new android.os.Bundle;
        r0.<init>();
        r1 = new android.content.Intent;
        r2 = r5.o;
        r2 = r2.toString();
        r1.<init>(r2);
        r1.putExtras(r0);
        r0 = "image-path";
        r2 = r5.p;
        r1.putExtra(r0, r2);
        r0 = -1;
        r5.setResult(r0, r1);
    L_0x0037:
        r6.recycle();
        r5.finish();
    L_0x003d:
        return;
    L_0x003e:
        r0 = move-exception;
        r2 = "ClipPictureActivity";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0064 }
        r3.<init>();	 Catch:{ all -> 0x0064 }
        r4 = "Cannot open file: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x0064 }
        r4 = r5.o;	 Catch:{ all -> 0x0064 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x0064 }
        r3 = r3.toString();	 Catch:{ all -> 0x0064 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ all -> 0x0064 }
        r0 = 0;
        r5.setResult(r0);	 Catch:{ all -> 0x0064 }
        r5.finish();	 Catch:{ all -> 0x0064 }
        com.budejie.www.activity.clip.a.a(r1);
        goto L_0x003d;
    L_0x0064:
        r0 = move-exception;
        com.budejie.www.activity.clip.a.a(r1);
        throw r0;
    L_0x0069:
        r0 = "ClipPictureActivity";
        r1 = "not defined image url";
        android.util.Log.e(r0, r1);
        goto L_0x0037;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.budejie.www.activity.clip.ClipPictureActivity.a(android.graphics.Bitmap):void");
    }
}
