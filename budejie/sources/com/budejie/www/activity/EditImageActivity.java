package com.budejie.www.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import bdj.uk.co.senab.photoview.PhotoView;
import com.ali.auth.third.core.model.Constants;
import com.bdj.picture.edit.TitleBarFragmentActivity;
import com.budejie.www.R;
import com.budejie.www.h.c;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.h;
import com.edmodo.cropper.CropImageView;
import com.nostra13.universalimageloader.core.d;
import com.tencent.smtt.sdk.WebView;
import com.umeng.analytics.MobclickAgent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EditImageActivity extends TitleBarFragmentActivity implements OnClickListener {
    public static int b = 0;
    private Bitmap A = null;
    private Bitmap B = null;
    private Bitmap C = null;
    private int D = 1;
    private String E = "";
    private String F = "";
    private ProgressDialog G = null;
    private AsyncTask<?, ?, ?> H = null;
    private LinearLayout I;
    private ImageView J;
    private ImageView K;
    private List<Bitmap> L;
    private List<Bitmap> M;
    private List<Bitmap> N;
    private List<Integer> O;
    private List<Boolean> P;
    private HashMap<String, Bitmap> Q;
    private int R;
    private int S;
    private ArrayList<String> T;
    private ArrayList<String> U;
    public int a = 0;
    private Activity c;
    private int d = 30;
    private WebView e;
    private PhotoView f;
    private CropImageView g;
    private LinearLayout h;
    private Button i;
    private Button j;
    private Button k;
    private Button l;
    private Button m;
    private int n = 0;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;
    private boolean t = false;
    private boolean u = false;
    private boolean v = false;
    private Bitmap w;
    private Bitmap x = null;
    private Bitmap y;
    private Bitmap z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        b = ai.a(this);
        setTheme(c.a().a(b));
        this.c = this;
        setContentView(R.layout.edit_image);
        d.a().c();
        d.a().d();
        System.gc();
        a();
        b();
        this.R = 0;
        this.S = 2;
        e();
    }

    private void a() {
        a(R.string.title_cancle, R.string.edit_picture_function_clipping, R.string.title_confirm, 0, false);
        this.e = (WebView) findViewById(R.id.wv_gif);
        this.f = (PhotoView) findViewById(R.id.imgView);
        this.I = (LinearLayout) findViewById(R.id.TrunPageLayout);
        this.J = (ImageView) findViewById(R.id.EditImageFrontImageView);
        this.K = (ImageView) findViewById(R.id.EditImageNextImageView);
        this.J.setImageResource(R.drawable.edit_picture_front_prohibit);
        this.J.setOnClickListener(this);
        this.K.setOnClickListener(this);
        this.g = (CropImageView) findViewById(R.id.cropper_view);
        this.h = (LinearLayout) findViewById(R.id.bottom_bar_rl);
        this.i = (Button) findViewById(R.id.bottom_rdobtn_1);
        this.j = (Button) findViewById(R.id.bottom_rdobtn_2);
        this.k = (Button) findViewById(R.id.bottom_rdobtn_3);
        this.l = (Button) findViewById(R.id.bottom_rdobtn_4);
        this.m = (Button) findViewById(R.id.bottom_rdobtn_5);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.m.setOnClickListener(this);
    }

    private void b() {
        this.Q = new HashMap();
        this.L = new ArrayList();
        this.M = new ArrayList();
        this.N = new ArrayList();
        this.O = new ArrayList();
        this.P = new ArrayList();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService("window");
        this.o = windowManager.getDefaultDisplay().getWidth();
        this.p = windowManager.getDefaultDisplay().getHeight();
        Intent intent = getIntent();
        this.s = intent.getIntExtra("albumIndex", 0);
        if (intent != null) {
            this.E = intent.getStringExtra("picture_path_key");
            aa.b("sendImage", "initData() mOriginImagePath=" + this.E);
        }
        if (this.E == null) {
            aa.e("EditImageActivity", "error ,origin image path is empty !");
            this.E = "";
        }
        this.T = (ArrayList) getIntent().getSerializableExtra("MultipleImgPath");
        if (this.T == null) {
            this.T = new ArrayList();
            this.T.add(this.E);
            this.I.setVisibility(8);
        } else if (this.T.size() == 1) {
            this.I.setVisibility(8);
        }
    }

    private void e() {
        if (this.T != null && this.T.size() > 0) {
            this.U = new ArrayList();
            File file = new File(Environment.getExternalStorageDirectory().toString() + "/budejie/report");
            if (!file.exists()) {
                file.mkdirs();
            }
            int size = this.T.size();
            String str = "";
            for (int i = 0; i < size; i++) {
                str = (String) this.T.get(i);
                str = str.substring(str.lastIndexOf("/"));
                this.U.add(file.toString() + str.substring(0, str.lastIndexOf(".")) + "-change" + str.substring(str.lastIndexOf(".")));
            }
            j();
        }
    }

    protected void d() {
        if (!this.u) {
            try {
                a(this.R, this.g.getCroppedImage());
            } catch (OutOfMemoryError e) {
            } catch (Exception e2) {
            }
        }
        if (Constants.SERVICE_SCOPE_FLAG_VALUE.equals(getIntent().getStringExtra("show_clear_previous_data_prompt"))) {
            final AlertDialog create = new Builder(this).create();
            create.setTitle("提示");
            create.setMessage("之前操作全部无效，确定裁剪？");
            DialogInterface.OnClickListener anonymousClass1 = new DialogInterface.OnClickListener(this) {
                final /* synthetic */ EditImageActivity b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    switch (i) {
                        case -2:
                            create.dismiss();
                            return;
                        case -1:
                            create.dismiss();
                            this.b.i();
                            return;
                        default:
                            return;
                    }
                }
            };
            create.setButton("确定", anonymousClass1);
            create.setButton2("取消", anonymousClass1);
            create.show();
            return;
        }
        i();
    }

    public void onClick(View view) {
        Bitmap bitmap = null;
        if (view == this.i) {
            this.S = 1;
            if (((Boolean) this.P.get(this.R)).booleanValue()) {
                this.g.a(90);
            } else if (!this.t && !this.u) {
                g();
            }
        } else if (view == this.j) {
            this.S = 2;
            this.g.a(false, false);
        } else if (view == this.k) {
            this.S = 3;
            this.g.a(true, true);
            this.g.a(3, 4);
        } else if (view == this.l) {
            this.S = 4;
            this.g.a(true, true);
            this.g.a(1, 1);
        } else if (view == this.m) {
            this.S = 5;
            this.g.a(true, true);
            this.g.a(4, 3);
        } else if (view == this.J) {
            this.R--;
            if (this.R < 0) {
                this.R = 0;
                return;
            }
            this.K.setImageResource(R.drawable.edit_picture_next_selector);
            if (this.R == 0) {
                this.J.setImageResource(R.drawable.edit_picture_front_prohibit);
            }
            try {
                bitmap = this.g.getCroppedImage();
            } catch (OutOfMemoryError e) {
            } catch (Exception e2) {
            }
            a(this.R + 1, bitmap);
            a((Bitmap) this.L.get(this.R));
            f();
        } else if (view == this.K) {
            this.R++;
            if (this.R < this.L.size()) {
                this.J.setImageResource(R.drawable.edit_picture_front_selector);
                try {
                    bitmap = this.g.getCroppedImage();
                } catch (OutOfMemoryError e3) {
                } catch (Exception e4) {
                }
                a(this.R - 1, bitmap);
                a((Bitmap) this.L.get(this.R));
                f();
                if (this.R == this.L.size() - 1) {
                    this.K.setImageResource(R.drawable.edit_picture_complete_selector);
                    return;
                }
                return;
            }
            this.R--;
            d();
        }
    }

    private void f() {
        switch (this.S) {
            case 2:
                this.g.a(false, false);
                return;
            case 3:
                this.g.a(true, true);
                this.g.a(3, 4);
                return;
            case 4:
                this.g.a(true, true);
                this.g.a(1, 1);
                return;
            case 5:
                this.g.a(true, true);
                this.g.a(4, 3);
                return;
            default:
                return;
        }
    }

    private void a(int i, Bitmap bitmap) {
        if (this.L != null && this.L.size() > 0 && i < this.L.size() && bitmap != null) {
            System.gc();
            this.L.remove(i);
            this.L.add(i, bitmap);
        }
    }

    private void a(int i, int i2) {
        if (this.O != null && this.O.size() > 0 && i < this.O.size()) {
            this.O.remove(i);
            this.O.add(i, Integer.valueOf(i2));
        }
    }

    private void g() {
        try {
            this.H = new AsyncTask<Void, Void, Boolean>(this) {
                final /* synthetic */ EditImageActivity a;

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
                }

                protected Boolean a(Void... voidArr) {
                    if (isCancelled()) {
                        return null;
                    }
                    try {
                        Bitmap bitmap;
                        switch (((Integer) this.a.O.get(this.a.R)).intValue() % 4) {
                            case 0:
                                this.a.x = null;
                                this.a.a(this.a.R, (Bitmap) this.a.M.get(this.a.R));
                                break;
                            case 1:
                                bitmap = (Bitmap) this.a.Q.get("1:" + this.a.R);
                                if (bitmap != null) {
                                    this.a.a(this.a.R, bitmap);
                                    break;
                                }
                                bitmap = h.b((Bitmap) this.a.N.get(this.a.R), ((Integer) this.a.O.get(this.a.R)).intValue());
                                this.a.Q.put("1:" + this.a.R, bitmap);
                                this.a.a(this.a.R, bitmap);
                                break;
                            case 2:
                                bitmap = (Bitmap) this.a.Q.get("2:" + this.a.R);
                                if (bitmap != null) {
                                    this.a.x = null;
                                    this.a.a(this.a.R, bitmap);
                                    break;
                                }
                                bitmap = h.b((Bitmap) this.a.N.get(this.a.R), ((Integer) this.a.O.get(this.a.R)).intValue());
                                this.a.Q.put("2:" + this.a.R, bitmap);
                                this.a.a(this.a.R, bitmap);
                                break;
                            case 3:
                                bitmap = (Bitmap) this.a.Q.get("3:" + this.a.R);
                                if (bitmap != null) {
                                    this.a.x = null;
                                    this.a.a(this.a.R, bitmap);
                                    break;
                                }
                                this.a.a(this.a.R, h.b((Bitmap) this.a.N.get(this.a.R), ((Integer) this.a.O.get(this.a.R)).intValue()));
                                break;
                        }
                        return Boolean.valueOf(true);
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        return Boolean.valueOf(false);
                    }
                }

                protected void onCancelled() {
                    super.onCancelled();
                    this.a.H = null;
                    this.a.h();
                }

                protected void a(Boolean bool) {
                    super.onPostExecute(bool);
                    if (bool.booleanValue()) {
                        this.a.a((Bitmap) this.a.L.get(this.a.R));
                        this.a.a(this.a.R, ((Integer) this.a.O.get(this.a.R)).intValue() + 1);
                    }
                    this.a.H = null;
                }
            }.execute(new Void[0]);
        } catch (Exception e) {
            aa.e("EditImageActivity", "asyncRotate Exception , " + e.toString());
        }
    }

    private void a(boolean z) {
        if (this.G == null) {
            this.G = new ProgressDialog(this);
            this.G.setMessage(getString(R.string.operating));
            this.G.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ EditImageActivity a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    if (this.a.H != null) {
                        this.a.H.cancel(false);
                        this.a.H = null;
                    }
                }
            });
        }
        this.G.setCancelable(z);
        this.G.show();
    }

    private void h() {
        if (this.G != null) {
            this.G.cancel();
        }
    }

    private void a(Bitmap bitmap) {
        if (this.g != null) {
            this.g.a(bitmap, false);
        }
    }

    private static void a(HashMap<String, Bitmap> hashMap) {
        if (hashMap != null && hashMap.size() > 0) {
            for (Bitmap b : hashMap.values()) {
                b(b);
            }
        }
    }

    private static void a(List<Bitmap> list) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                b((Bitmap) list.get(i));
            }
        }
    }

    private static void b(Bitmap bitmap) {
        if (bitmap != null) {
            synchronized (bitmap) {
                h.a(bitmap);
            }
        }
    }

    private void i() {
        aa.c("EditImageActivity", "saveBitmapAndFinish ");
        try {
            this.H = new AsyncTask<Void, Void, Boolean>(this) {
                final /* synthetic */ EditImageActivity a;

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
                    if (this.a.u) {
                        return Boolean.valueOf(true);
                    }
                    try {
                        this.a.F = h.a(this.a, this.a.L, false);
                        if (this.a.F == null || this.a.F.length() <= 0) {
                            return Boolean.valueOf(false);
                        }
                        try {
                            if (this.a.U != null && this.a.U.size() > 0) {
                                for (int i = 0; i < this.a.U.size(); i++) {
                                    this.a.a((String) this.a.U.get(i), (Bitmap) this.a.L.get(i));
                                }
                            }
                            return Boolean.valueOf(true);
                        } catch (OutOfMemoryError e) {
                            return Boolean.valueOf(true);
                        }
                    } catch (OutOfMemoryError e2) {
                        return Boolean.valueOf(false);
                    }
                }

                protected void onCancelled() {
                    super.onCancelled();
                    this.a.H = null;
                    this.a.h();
                }

                protected void a(Boolean bool) {
                    super.onPostExecute(bool);
                    this.a.H = null;
                    if (bool.booleanValue()) {
                        Intent intent = new Intent();
                        if (this.a.U != null) {
                            intent.putExtra("MultipleImgPath", this.a.U);
                        }
                        if (this.a.U.size() == 1) {
                            this.a.F = (String) this.a.U.get(0);
                        }
                        intent.putExtra("imagePath", this.a.F);
                        intent.putExtra("islarge_length_image", this.a.v);
                        this.a.setResult(-1, intent);
                        this.a.finish();
                    } else {
                        Toast.makeText(this.a.c, "图片过大，请选择较小图片或减少图片合成数量", 0).show();
                        this.a.finish();
                    }
                    this.a.h();
                }
            }.execute(new Void[0]);
        } catch (Exception e) {
            aa.e("EditImageActivity", "Exception,  msg = " + e.toString());
        }
    }

    private void j() {
        try {
            this.H = new AsyncTask<Void, Void, Boolean>(this) {
                final /* synthetic */ EditImageActivity a;

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
                    if (this.a.T != null && this.a.T.size() > 0) {
                        for (int i = 0; i < this.a.T.size(); i++) {
                            if (!this.a.a((String) this.a.T.get(i))) {
                                return Boolean.valueOf(false);
                            }
                        }
                    }
                    return Boolean.valueOf(true);
                }

                protected void onCancelled() {
                    super.onCancelled();
                    this.a.H = null;
                    this.a.h();
                }

                protected void a(Boolean bool) {
                    super.onPostExecute(bool);
                    if (bool.booleanValue()) {
                        this.a.a((Bitmap) this.a.L.get(this.a.R));
                        this.a.f();
                    } else {
                        an.a(this.a, this.a.getString(R.string.tougao_pic_too_big), -1).show();
                    }
                    this.a.H = null;
                    this.a.h();
                }
            }.execute(new Void[0]);
        } catch (Exception e) {
            aa.e("EditImageActivity", "Exception,  msg = " + e.toString());
        }
    }

    private boolean a(String str) {
        Options options;
        Bitmap decodeFile;
        try {
            options = new Options();
            options.inPreferredConfig = Config.RGB_565;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            this.q = options.outWidth;
            this.r = options.outHeight;
            if (((float) this.r) / ((float) this.q) > ((float) this.d)) {
                this.v = true;
            }
            this.t = true;
            options.inJustDecodeBounds = false;
            decodeFile = BitmapFactory.decodeFile(str, options);
            this.L.add(decodeFile);
            this.P.add(Boolean.valueOf(true));
            this.M.add(decodeFile);
            this.N.add(decodeFile);
            this.O.add(Integer.valueOf(1));
            return true;
        } catch (OutOfMemoryError e) {
            options = new Options();
            options.inPreferredConfig = Config.RGB_565;
            options.inPurgeable = true;
            options.inInputShareable = true;
            try {
                decodeFile = BitmapFactory.decodeFile(str, options);
                this.L.add(decodeFile);
                this.M.add(decodeFile);
                this.N.add(decodeFile);
                this.O.add(Integer.valueOf(1));
                this.P.add(Boolean.valueOf(false));
                return true;
            } catch (OutOfMemoryError e2) {
                return false;
            }
        }
    }

    private boolean a(String str, Bitmap bitmap) {
        return a(c(bitmap), str);
    }

    private Bitmap c(Bitmap bitmap) {
        aa.b("createBitmap", "create a new bitmap");
        if (bitmap == null) {
            return null;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
            canvas.save(31);
            canvas.restore();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private boolean a(Bitmap bitmap, String str) {
        boolean z;
        FileNotFoundException e;
        IOException e2;
        try {
            OutputStream fileOutputStream = new FileOutputStream(new File(str));
            if (bitmap.compress(CompressFormat.JPEG, 80, fileOutputStream)) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            z = true;
            try {
                bitmap.recycle();
            } catch (FileNotFoundException e3) {
                e = e3;
                e.printStackTrace();
                return z;
            } catch (IOException e4) {
                e2 = e4;
                e2.printStackTrace();
                return z;
            }
        } catch (FileNotFoundException e5) {
            FileNotFoundException fileNotFoundException = e5;
            z = false;
            e = fileNotFoundException;
            e.printStackTrace();
            return z;
        } catch (IOException e6) {
            IOException iOException = e6;
            z = false;
            e2 = iOException;
            e2.printStackTrace();
            return z;
        }
        return z;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        aa.c("EditImageActivity", "onKeyDown , back");
        finish();
        return true;
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        if (this.H != null) {
            this.H.cancel(false);
            this.H = null;
        }
        b(this.z);
        b(this.x);
        b(this.y);
        b(this.w);
        b(this.A);
        b(this.B);
        b(this.C);
        a(this.L);
        a(this.M);
        a(this.N);
        a(this.Q);
        h();
        System.gc();
        super.onDestroy();
    }
}
