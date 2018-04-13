package com.budejie.www.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import bdj.uk.co.senab.photoview.PhotoView;
import com.budejie.www.R;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.budejie.www.util.h;

public class EditPicActivity extends Activity {
    private EditPicActivity a;
    private TextView b;
    private TextView c;
    private PhotoView d;
    private LinearLayout e;
    private LinearLayout f;
    private LinearLayout g;
    private LinearLayout h;
    private ImageView i;
    private String j = "";
    private String k = "";
    private AsyncTask<?, ?, ?> l = null;
    private ProgressDialog m = null;
    private int n = 0;
    private Bitmap o = null;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private OnClickListener u = new OnClickListener(this) {
        final /* synthetic */ EditPicActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.b) {
                this.a.f();
            } else if (view == this.a.c) {
                this.a.setResult(0);
                this.a.a.finish();
            } else if (view == this.a.i) {
                this.a.a(1);
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.a = this;
        this.p = getWindowManager().getDefaultDisplay().getWidth();
        this.q = getWindowManager().getDefaultDisplay().getHeight();
        this.q -= an.t(this.a);
        setContentView(R.layout.edit_pic_layout);
        a();
        c();
    }

    private void a() {
        this.b = (TextView) findViewById(R.id.edit_pic_sure_text);
        this.c = (TextView) findViewById(R.id.edit_pic_cancel_text);
        this.d = (PhotoView) findViewById(R.id.edit_pic_imageview);
        this.e = (LinearLayout) findViewById(R.id.edit_pic_top_linearlayout);
        this.f = (LinearLayout) findViewById(R.id.edit_pic_buttom_linearlayout);
        this.g = (LinearLayout) findViewById(R.id.edit_pic_left_linearlayout);
        this.h = (LinearLayout) findViewById(R.id.edit_pic_right_linearlayout);
        this.f.getBackground().setAlpha(100);
        this.e.getBackground().setAlpha(100);
        this.g.getBackground().setAlpha(100);
        this.h.getBackground().setAlpha(100);
        this.i = (ImageView) findViewById(R.id.edit_pic_rote_imageview);
        this.d.setMaxScale(10.0f);
        this.b.setOnClickListener(this.u);
        this.c.setOnClickListener(this.u);
        this.i.setOnClickListener(this.u);
        b();
    }

    private void b() {
        System.out.println("屏幕高度：" + this.q);
        this.r = 10;
        this.s = this.q / 9;
        this.t = ((this.q - (this.q / 9)) - (this.p - 20)) - an.a(this.a, 50);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(this.p, this.s);
        layoutParams.gravity = 48;
        this.e.setLayoutParams(layoutParams);
        layoutParams = new FrameLayout.LayoutParams(this.p, this.t);
        layoutParams.gravity = 80;
        this.f.setLayoutParams(layoutParams);
        layoutParams = new FrameLayout.LayoutParams(this.r, -1);
        layoutParams.topMargin = this.s;
        layoutParams.bottomMargin = this.t;
        layoutParams.gravity = 3;
        this.g.setLayoutParams(layoutParams);
        layoutParams = new FrameLayout.LayoutParams(this.r, -1);
        layoutParams.topMargin = this.s;
        layoutParams.bottomMargin = this.t;
        layoutParams.gravity = 5;
        this.h.setLayoutParams(layoutParams);
    }

    private void c() {
        Intent intent = getIntent();
        if (intent != null) {
            this.j = intent.getStringExtra("picture_path_key");
        }
        if (this.j == null) {
            aa.e("EditPicActivity", "error ,origin image path is empty !");
            this.j = "";
        }
        this.k = this.j;
        if (this.k.endsWith(".jpg")) {
            this.k = this.k.substring(0, this.k.length() - 4) + "-rotate.jpg";
        } else if (this.k.endsWith(".png")) {
            this.k = this.k.substring(0, this.k.length() - 4) + "-rotate.png";
        } else if (this.k.endsWith(".gif")) {
            this.k = this.k.substring(0, this.k.length() - 4) + "-rotate.gif";
        }
        d();
    }

    private void d() {
        aa.c("EditPicActivity", "initPreviewBitmap ");
        try {
            this.l = new AsyncTask<Void, Void, Bitmap>(this) {
                final /* synthetic */ EditPicActivity a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((Void[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((Bitmap) obj);
                }

                protected void onPreExecute() {
                    super.onPreExecute();
                    this.a.a(false);
                }

                protected Bitmap a(Void... voidArr) {
                    Bitmap bitmap = null;
                    if (!isCancelled()) {
                        float f = 500.0f;
                        int i = 0;
                        while (i < 5) {
                            aa.c("EditPicActivity", "initPreviewBitmap , getCompressBitmap, times = " + i);
                            if (f <= 0.0f) {
                                break;
                            }
                            try {
                                bitmap = h.a(this.a.j, 640.0f, f);
                                break;
                            } catch (OutOfMemoryError e) {
                                e.printStackTrace();
                                EditPicActivity.c(bitmap);
                                f -= 100.0f;
                                i++;
                            }
                        }
                    }
                    return bitmap;
                }

                protected void onCancelled() {
                    super.onCancelled();
                    this.a.l = null;
                    this.a.e();
                }

                protected void a(Bitmap bitmap) {
                    super.onPostExecute(bitmap);
                    if (bitmap != null) {
                        this.a.b(bitmap);
                    } else {
                        an.a(this.a.a, this.a.getString(R.string.tougao_pic_too_big), -1).show();
                    }
                    this.a.l = null;
                    this.a.e();
                }
            }.execute(new Void[0]);
        } catch (Exception e) {
            aa.e("EditPicActivity", "Exception,  msg = " + e.toString());
        }
    }

    private void a(boolean z) {
        if (this.m == null) {
            this.m = new ProgressDialog(this.a);
            this.m.setMessage(getString(R.string.operating));
            this.m.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ EditPicActivity a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    if (this.a.l != null) {
                        this.a.l.cancel(false);
                        this.a.l = null;
                    }
                }
            });
        }
        this.m.setCancelable(z);
        this.m.show();
    }

    private void e() {
        if (this.m != null) {
            this.m.cancel();
        }
    }

    private void b(Bitmap bitmap) {
        if (!(this.o == null || this.o == bitmap)) {
            c(this.o);
        }
        this.o = bitmap;
        if (this.d != null) {
            this.d.setImageBitmap(bitmap);
        }
    }

    private static void c(Bitmap bitmap) {
        if (bitmap != null) {
            synchronized (bitmap) {
                h.a(bitmap);
            }
        }
    }

    private void a(final int i) {
        aa.c("EditPicActivity", "---rotatePicture, type = " + i);
        try {
            this.l = new AsyncTask<Void, Void, Bitmap>(this) {
                final /* synthetic */ EditPicActivity b;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((Void[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((Bitmap) obj);
                }

                protected void onPreExecute() {
                    super.onPreExecute();
                }

                protected Bitmap a(Void... voidArr) {
                    Bitmap bitmap = null;
                    if (!isCancelled()) {
                        try {
                            bitmap = EditPicActivity.b(this.b.o, i);
                        } catch (OutOfMemoryError e) {
                            e.printStackTrace();
                        }
                    }
                    return bitmap;
                }

                protected void onCancelled() {
                    super.onCancelled();
                    this.b.l = null;
                    this.b.e();
                }

                protected void a(Bitmap bitmap) {
                    super.onPostExecute(bitmap);
                    if (bitmap != null) {
                        this.b.b(bitmap);
                        this.b.b(i);
                    }
                    this.b.l = null;
                }
            }.execute(new Void[0]);
        } catch (Exception e) {
            aa.e("EditPicActivity", "asyncRotate Exception , " + e.toString());
        }
    }

    private static Bitmap b(Bitmap bitmap, int i) {
        if (bitmap == null) {
            return null;
        }
        Bitmap a;
        synchronized (bitmap) {
            a = h.a(bitmap, i);
        }
        return a;
    }

    private void b(int i) {
        switch (i) {
            case 1:
                this.n--;
                break;
            case 2:
                this.n++;
                break;
        }
        aa.c("EditPicActivity", "changeRotateStatus, rotateType = " + i + ", mCurrentRotateType = " + this.n);
    }

    protected void onDestroy() {
        if (this.l != null) {
            this.l.cancel(false);
            this.l = null;
        }
        e();
        this.d.setImageBitmap(null);
        c(this.o);
        System.gc();
        super.onDestroy();
    }

    private void f() {
        aa.c("EditPicActivity", "saveBitmapAndFinish ");
        try {
            this.l = new AsyncTask<Void, Void, Boolean>(this) {
                final /* synthetic */ EditPicActivity a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((Void[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((Boolean) obj);
                }

                protected void onPreExecute() {
                    super.onPreExecute();
                    this.a.a(false);
                }

                protected Boolean a(Void... voidArr) {
                    if (isCancelled()) {
                        return Boolean.valueOf(false);
                    }
                    return Boolean.valueOf(this.a.g());
                }

                protected void onCancelled() {
                    super.onCancelled();
                    this.a.l = null;
                    this.a.e();
                }

                protected void a(Boolean bool) {
                    super.onPostExecute(bool);
                    this.a.l = null;
                    if (bool.booleanValue()) {
                        Intent intent = new Intent();
                        intent.putExtra("picture_path_key", this.a.k);
                        this.a.setResult(720, intent);
                    } else {
                        this.a.setResult(0);
                    }
                    this.a.a.finish();
                    this.a.e();
                }
            }.execute(new Void[0]);
        } catch (Exception e) {
            aa.e("EditPicActivity", "Exception,  msg = " + e.toString());
        }
    }

    private boolean g() {
        aa.c("EditPicActivity", "saveBitmapToFile ");
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(this.j, options);
        CompressFormat compressFormat = CompressFormat.JPEG;
        if (!(options == null || options.outMimeType == null || !options.outMimeType.contains("png"))) {
            compressFormat = CompressFormat.PNG;
        }
        return h.a(h.a(this.a, this.r, this.s + an.t(this.a), this.p - 20, this.p - 20), this.k, compressFormat);
    }
}
