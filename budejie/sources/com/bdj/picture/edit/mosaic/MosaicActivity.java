package com.bdj.picture.edit.mosaic;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.bdj.picture.edit.EditPictureActivity;
import com.bdj.picture.edit.TitleBarFragmentActivity;
import com.bdj.picture.edit.a.d;
import com.bdj.picture.edit.a.e;
import com.bdj.picture.edit.a.h;

public class MosaicActivity extends TitleBarFragmentActivity implements OnClickListener {
    public LinearLayout a;
    public LinearLayout b;
    public boolean c = false;
    public Context d;
    DialogInterface.OnClickListener e = new DialogInterface.OnClickListener(this) {
        final /* synthetic */ MosaicActivity a;

        {
            this.a = r1;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case -2:
                    this.a.t.dismiss();
                    return;
                case -1:
                    this.a.t.dismiss();
                    this.a.g.a.recycle();
                    this.a.g.b.recycle();
                    this.a.g.destroyDrawingCache();
                    this.a.finish();
                    return;
                default:
                    return;
            }
        }
    };
    private String f = "";
    private b g;
    private LinearLayout h;
    private TextView i;
    private ImageView j;
    private LinearLayout k;
    private TextView l;
    private ImageView m;
    private int[] n = new int[]{com.bdj.picture.edit.a.c.mosaic_1, com.bdj.picture.edit.a.c.mosaic_2, com.bdj.picture.edit.a.c.mosaic_3, com.bdj.picture.edit.a.c.mosaic_4, com.bdj.picture.edit.a.c.mosaic_5, com.bdj.picture.edit.a.c.mosaic_6, com.bdj.picture.edit.a.c.mosaic_7, com.bdj.picture.edit.a.c.mosaic_8, com.bdj.picture.edit.a.c.mosaic_9, com.bdj.picture.edit.a.c.mosaic_10};
    private a o;
    private ProgressDialog p = null;
    private SeekBar q;
    private Bitmap r;
    private LinearLayout s;
    private AlertDialog t;
    private ProgressDialog u;

    @SuppressLint({"HandlerLeak"})
    private class a extends Handler {
        final /* synthetic */ MosaicActivity a;

        private a(MosaicActivity mosaicActivity) {
            this.a = mosaicActivity;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (this.a.p != null && this.a.p.isShowing()) {
                        this.a.p.dismiss();
                    }
                    this.a.p = ProgressDialog.show(this.a, this.a.d.getString(h.drawPhoto_actionName), this.a.d.getString(h.drawPhoto_actioning));
                    break;
                case 1:
                    if (this.a.g != null) {
                        this.a.a.removeView(this.a.g);
                    }
                    this.a.g = (b) message.obj;
                    this.a.g.destroyDrawingCache();
                    this.a.g.setLayoutParams(new LayoutParams(this.a.f(), this.a.f()));
                    this.a.a.addView(this.a.g);
                    break;
                case 2:
                    this.a.f = (String) message.obj;
                    new b().start();
                    break;
                case 3:
                    if (this.a.p != null) {
                        this.a.p.dismiss();
                        break;
                    }
                    break;
            }
            super.handleMessage(message);
        }
    }

    private class b extends Thread {
        final /* synthetic */ MosaicActivity a;

        private b(MosaicActivity mosaicActivity) {
            this.a = mosaicActivity;
        }

        public void run() {
            Message message = new Message();
            message.what = 0;
            this.a.o.sendMessage(message);
            int f = this.a.f();
            this.a.g = new b(this.a, null, this.a.r, this.a.n[0], f, f);
            message = new Message();
            message.what = 1;
            message.obj = this.a.g;
            this.a.o.sendMessage(message);
        }
    }

    class c extends AsyncTask<Void, Void, Bitmap> {
        final /* synthetic */ MosaicActivity a;

        c(MosaicActivity mosaicActivity) {
            this.a = mosaicActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Bitmap) obj);
        }

        protected Bitmap a(Void... voidArr) {
            Bitmap a = this.a.g.a(this.a.g.b, this.a.g.a);
            this.a.g.destroyDrawingCache();
            return a;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            if (this.a.u == null) {
                this.a.u = new ProgressDialog(this.a);
            }
            this.a.u.show();
        }

        protected void a(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            EditPictureActivity.c.recycle();
            EditPictureActivity.c = bitmap;
            if (this.a.u != null) {
                this.a.u.dismiss();
            }
            this.a.setResult(31);
            this.a.finish();
        }
    }

    @SuppressLint({"NewApi"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(e.activity_mosaic);
        b();
        this.d = this;
        this.r = EditPictureActivity.c;
        if (this.r != null) {
            new b().start();
        }
    }

    public void a() {
        if (this.p != null && this.p.isShowing()) {
            this.p.dismiss();
        }
    }

    private void b() {
        int i = 0;
        a(h.title_cancle, h.edit_picture_function_mosaic, h.title_confirm, 0, false);
        this.a = (LinearLayout) findViewById(d.draw_photo_view);
        this.s = (LinearLayout) findViewById(d.draw_photo_view_parent);
        this.s.setLayoutParams(new LayoutParams(f(), f()));
        this.o = new a();
        this.b = (LinearLayout) findViewById(d.ll_mosaic_imgs);
        this.h = (LinearLayout) findViewById(d.ll_paint);
        this.i = (TextView) findViewById(d.txt_paint);
        this.j = (ImageView) findViewById(d.img_paint);
        this.k = (LinearLayout) findViewById(d.ll_eraser);
        this.l = (TextView) findViewById(d.txt_eraser);
        this.m = (ImageView) findViewById(d.img_eraser);
        this.q = (SeekBar) findViewById(d.seekBar);
        this.i.setSelected(true);
        this.j.setSelected(true);
        if (this.b.getChildAt(0) != null) {
            this.b.getChildAt(0).setSelected(true);
        }
        int childCount = this.b.getChildCount();
        while (i < childCount) {
            this.b.getChildAt(i).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ MosaicActivity b;

                public void onClick(View view) {
                    this.b.a(true);
                    view.setSelected(true);
                    this.b.g.a(this.b.n[i], false);
                    int childCount = this.b.b.getChildCount();
                    int i = 0;
                    while (i < childCount) {
                        if (i != i && this.b.b.getChildAt(i).isSelected()) {
                            this.b.b.getChildAt(i).setSelected(false);
                        }
                        i++;
                    }
                }
            });
            i++;
        }
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.q.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
            int a = 0;
            final /* synthetic */ MosaicActivity b;

            {
                this.b = r2;
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (this.a > 0 && ((double) this.a) < 12.5d) {
                    seekBar.setProgress(0);
                } else if (((double) this.a) > 12.5d && this.a < 25) {
                    seekBar.setProgress(25);
                } else if (this.a > 25 && ((double) this.a) < 37.5d) {
                    seekBar.setProgress(25);
                } else if (((double) this.a) > 37.5d && this.a < 50) {
                    seekBar.setProgress(50);
                } else if (this.a > 50 && ((double) this.a) < 62.5d) {
                    seekBar.setProgress(50);
                } else if (((double) this.a) > 62.5d && this.a < 75) {
                    seekBar.setProgress(75);
                } else if (this.a > 75 && ((double) this.a) < 87.5d) {
                    seekBar.setProgress(75);
                } else if (((double) this.a) > 87.5d && this.a < 100) {
                    seekBar.setProgress(100);
                }
                this.b.g.setStrokeMultiples(1.0f + (((float) (((double) this.a) / 100.0d)) * 4.0f));
                this.b.g.a();
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                this.a = i;
                this.b.g.setStrokeMultiples(1.0f + (((float) (((double) i) / 100.0d)) * 4.0f));
            }
        });
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            c();
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void e() {
        this.t = new Builder(this).create();
        this.t.setTitle("提示");
        this.t.setMessage("确定放弃当前操作图片?");
        this.t.setButton("放弃", this.e);
        this.t.setButton2("取消", this.e);
        this.t.show();
    }

    protected void c() {
        if (this.g.getIsEdited()) {
            e();
            return;
        }
        this.g.a.recycle();
        this.g.b.recycle();
        this.g.destroyDrawingCache();
        finish();
    }

    protected void d() {
        new c(this).execute(new Void[0]);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == d.ll_paint || id == d.txt_paint || id == d.img_paint) {
            a(true);
            this.g.a(true, false);
        } else if (id == d.ll_eraser || id == d.txt_eraser || id == d.img_eraser) {
            a(false);
            this.g.a(false, false);
        }
    }

    private void a(boolean z) {
        boolean z2;
        boolean z3 = true;
        this.i.setSelected(z);
        this.j.setSelected(z);
        TextView textView = this.l;
        if (z) {
            z2 = false;
        } else {
            z2 = true;
        }
        textView.setSelected(z2);
        ImageView imageView = this.m;
        if (z) {
            z3 = false;
        }
        imageView.setSelected(z3);
    }

    private int f() {
        WindowManager windowManager = getWindowManager();
        int width = windowManager.getDefaultDisplay().getWidth();
        windowManager.getDefaultDisplay().getHeight();
        return width;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!(this.g.b == null || this.g.b.isRecycled())) {
            this.g.b.recycle();
        }
        if (this.g.a != null && !this.g.a.isRecycled()) {
            this.g.a.recycle();
        }
    }
}
