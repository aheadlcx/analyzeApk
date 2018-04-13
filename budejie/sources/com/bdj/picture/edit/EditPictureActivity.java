package com.bdj.picture.edit;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.HorizontalScrollView;
import android.widget.RelativeLayout;
import com.ali.auth.third.core.model.Constants;
import com.bdj.picture.edit.a.c;
import com.bdj.picture.edit.a.d;
import com.bdj.picture.edit.a.e;
import com.bdj.picture.edit.a.h;
import com.bdj.picture.edit.bean.BVType;
import com.bdj.picture.edit.e.b;
import com.bdj.picture.edit.util.f;
import com.bdj.picture.edit.widget.TabsLayout;
import com.bdj.picture.edit.widget.d.a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.ab;

public class EditPictureActivity extends TitleBarFragmentActivity implements b, a {
    public static Bitmap c;
    public boolean a = false;
    public boolean b = true;
    OnClickListener d = new OnClickListener(this) {
        final /* synthetic */ EditPictureActivity a;

        {
            this.a = r1;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case -2:
                    this.a.r.dismiss();
                    return;
                case -1:
                    this.a.r.dismiss();
                    this.a.setResult(7202);
                    this.a.finish();
                    return;
                default:
                    return;
            }
        }
    };
    private com.bdj.picture.edit.c.a e;
    private com.bdj.picture.edit.e.a f;
    private ab g;
    private int h;
    private int i;
    private int j;
    private String k;
    private ArrayList<String> l;
    private GPUImage m;
    private ProgressDialog n;
    private RelativeLayout o;
    private View p;
    private View q;
    private AlertDialog r;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        this.i = getWindowManager().getDefaultDisplay().getHeight();
        this.j = getWindowManager().getDefaultDisplay().getWidth();
        getWindow().setFlags(1024, 1024);
        setContentView(e.activity_edit_picture);
        System.gc();
        this.l = (ArrayList) getIntent().getSerializableExtra("MultipleImgPath");
        this.q = findViewById(d.title_bar);
        this.p = findViewById(d.ll_bottom);
        a(h.edit_picture_title_back, h.edit_picture_title_edit, h.edit_picture_title_next, c.edit_title_left_btn, true);
        TabsLayout tabsLayout = (TabsLayout) findViewById(d.tabsLayout);
        this.f = new com.bdj.picture.edit.d.a(this, (HorizontalScrollView) findViewById(d.contentLayout));
        tabsLayout.setEditBarChangeListener(this.f);
        tabsLayout.a();
        f();
        this.k = getIntent().getStringExtra("picture_path_key");
        b(this.k);
    }

    public void a() {
        if (this.b) {
            this.b = false;
            if (this.a) {
                a(this.q, this.p, this.o, 0);
            } else {
                a(this.q, this.p, this.o, 1);
            }
        }
    }

    private void a(View view, View view2, View view3, int i) {
        try {
            Animation aVar = new com.bdj.picture.edit.a.a(view, view2, view3, i);
            aVar.setAnimationListener(new AnimationListener(this) {
                final /* synthetic */ EditPictureActivity a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    this.a.a = !this.a.a;
                    this.a.b = true;
                }
            });
            view.startAnimation(aVar);
        } catch (Exception e) {
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 1:
                if (i2 == -1) {
                    Intent intent2 = new Intent();
                    intent2.putExtra("imagePath", intent.getStringExtra("imagePath"));
                    this.l = (ArrayList) intent.getSerializableExtra("MultipleImgPath");
                    if (this.l != null) {
                        intent2.putExtra("MultipleImgPath", this.l);
                    }
                    setResult(7203, intent2);
                    finish();
                    return;
                }
                return;
            case 2:
                if (i2 == 21) {
                    a(intent.getStringExtra(f.c), intent.getStringExtra(f.d));
                    return;
                }
                return;
            case 3:
                if (i2 == 31) {
                    if (this.m == null) {
                        this.m = new GPUImage(this);
                    }
                    this.m.a(c);
                    if (this.g == null) {
                        this.g = new ab();
                    }
                    this.m.a(this.g);
                    this.e.a(this.m.c());
                    return;
                }
                return;
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
    }

    private void b(final String str) {
        new AsyncTask<Void, Void, Bitmap>(this) {
            final /* synthetic */ EditPictureActivity b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                a((Bitmap) obj);
            }

            protected Bitmap a(Void... voidArr) {
                Bitmap bitmap = null;
                try {
                    File file = new File(str);
                    if (file.exists()) {
                        bitmap = this.b.b(new FileInputStream(file));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return bitmap;
            }

            protected void a(Bitmap bitmap) {
                if (bitmap != null) {
                    this.b.m = new GPUImage(this.b);
                    this.b.m.a(bitmap);
                    this.b.m.a(new ab());
                    EditPictureActivity.c = bitmap;
                    this.b.e.a(this.b.m.c());
                    if (this.b.n != null) {
                        this.b.n.dismiss();
                    }
                    if (EditPictureActivity.c.getHeight() <= this.b.j) {
                        this.b.h = ((this.b.i / 2) - (this.b.o.getHeight() / 2)) - this.b.o.getTop();
                    } else if (EditPictureActivity.c.getHeight() > this.b.j && EditPictureActivity.c.getHeight() <= this.b.i) {
                        this.b.h = ((this.b.i / 2) - (EditPictureActivity.c.getHeight() / 2)) - this.b.o.getTop();
                    } else if (EditPictureActivity.c.getHeight() > this.b.i) {
                        this.b.h = -this.b.o.getTop();
                    }
                }
            }

            protected void onPreExecute() {
                super.onPreExecute();
                if (this.b.n == null) {
                    this.b.n = new ProgressDialog(this.b);
                }
                this.b.n.show();
            }
        }.execute(new Void[0]);
    }

    private int e() {
        return getWindowManager().getDefaultDisplay().getWidth();
    }

    private Bitmap a(Bitmap bitmap) {
        Bitmap bitmap2 = null;
        if (bitmap != null) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
            if (byteArrayOutputStream.toByteArray().length / 1024 > 1000) {
                byteArrayOutputStream.reset();
                bitmap.compress(CompressFormat.JPEG, 100, byteArrayOutputStream);
            }
            bitmap.recycle();
            System.gc();
            InputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            bitmap2 = BitmapFactory.decodeStream(byteArrayInputStream, null, null);
            try {
                byteArrayInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Throwable th) {
            }
        }
        return bitmap2;
    }

    private ByteArrayOutputStream a(InputStream inputStream) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.close();
                inputStream.close();
                return byteArrayOutputStream;
            }
        }
    }

    private Bitmap b(InputStream inputStream) {
        ByteArrayOutputStream a;
        int i = 1;
        Bitmap bitmap = null;
        try {
            a = a(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            Object obj = bitmap;
        }
        InputStream byteArrayInputStream = new ByteArrayInputStream(a.toByteArray());
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(byteArrayInputStream, bitmap, options);
        options.inJustDecodeBounds = false;
        int i2 = options.outWidth;
        float e2 = (float) e();
        float f = (((float) options.outHeight) * e2) / ((float) i2);
        i2 = (int) (((float) options.outWidth) / e2);
        if (i2 > 0) {
            i = i2;
        }
        options.inSampleSize = i;
        try {
            byteArrayInputStream.close();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
        InputStream byteArrayInputStream2 = new ByteArrayInputStream(a.toByteArray());
        try {
            bitmap = BitmapFactory.decodeStream(byteArrayInputStream2, null, options);
        } catch (OutOfMemoryError e4) {
        }
        try {
            a.close();
            byteArrayInputStream2.close();
            return a(bitmap);
        } catch (IOException e5) {
            e5.printStackTrace();
            return a(bitmap);
        } catch (Throwable th) {
            return a(bitmap);
        }
    }

    private void f() {
        this.o = (RelativeLayout) findViewById(d.edit_picture_edit_layout);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        this.e = new com.bdj.picture.edit.c.a();
        this.e.a((b) this);
        beginTransaction.add(d.edit_picture_edit_layout, this.e);
        beginTransaction.commit();
        this.f.a(this.e);
    }

    public void a(com.bdj.picture.edit.bean.f fVar, String str) {
    }

    public void a(String str) {
    }

    public void a(ab abVar) {
        b(abVar);
    }

    private void b(ab abVar) {
        if (this.g == null || !(abVar == null || this.g.getClass().equals(abVar.getClass()))) {
            this.g = abVar;
            this.m.a(new ab());
            this.m.a(abVar);
            this.e.a(this.m.c());
        }
    }

    public void b() {
        Intent intent = new Intent();
        intent.setClassName(this, "com.budejie.www.activity.EditImageActivity");
        intent.putExtra("source", "TougaoActivity");
        intent.putExtra("picture_path_key", this.k);
        intent.putExtra("show_clear_previous_data_prompt", Constants.SERVICE_SCOPE_FLAG_VALUE);
        if (this.l != null) {
            intent.putExtra("MultipleImgPath", this.l);
        }
        startActivityForResult(intent, 1);
    }

    private void a(String str, String str2) {
        com.bdj.picture.edit.bean.a aVar = new com.bdj.picture.edit.bean.a();
        aVar.a(BVType.IE_PASTER);
        com.bdj.picture.edit.bean.d dVar = new com.bdj.picture.edit.bean.d();
        dVar.g(str);
        dVar.a(str2);
        aVar.a(dVar);
        this.e.b(aVar);
    }

    private void g() {
        this.e.b();
    }

    protected void c() {
        h();
    }

    protected void d() {
        g();
    }

    private void h() {
        this.r = new Builder(this).create();
        this.r.setTitle("提示");
        this.r.setMessage("确定放弃当前操作图片?");
        this.r.setButton("放弃", this.d);
        this.r.setButton2("取消", this.d);
        this.r.show();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            c();
        }
        return super.onKeyDown(i, keyEvent);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.e != null) {
            this.e.c();
        }
        if (c != null && !c.isRecycled()) {
            c.recycle();
        }
    }
}
