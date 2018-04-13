package com.budejie.www.activity.image;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import android.widget.Toast;
import com.budejie.www.R;
import com.budejie.www.activity.TougaoActivity;
import com.budejie.www.h.c;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.h;
import com.budejie.www.util.i;
import com.budejie.www.widget.f;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class SelectImageActivity extends Activity implements OnClickListener, OnItemClickListener {
    public static Bitmap a;
    public static int b = 0;
    boolean c = false;
    private List<d> d;
    private a e;
    private GridView f;
    private f g;
    private TextView h;
    private TextView i;
    private LinearLayout j;
    private TextView k;
    private ImageView l;
    private boolean m = true;
    private SlidingDrawer n;
    private ListView o;
    private e p;
    private FrameLayout q;
    private String r;
    private Toast s;
    private List<ImageItem> t;
    private int u = 0;
    private String v;
    private int w = 9;
    private List<ImageItem> x = new ArrayList();
    private f y;

    class a extends AsyncTask {
        final /* synthetic */ SelectImageActivity a;

        a(SelectImageActivity selectImageActivity) {
            this.a = selectImageActivity;
        }

        protected void onPreExecute() {
            if (this.a.y == null) {
                this.a.y = new f(this.a, R.style.dialogTheme);
                this.a.y.a("");
            }
            this.a.y.show();
        }

        protected Object doInBackground(Object[] objArr) {
            List arrayList = new ArrayList();
            try {
                for (ImageItem imageItem : this.a.x) {
                    arrayList.add(h.a(imageItem.imagePath, false));
                }
                if (h.a(this.a, arrayList)) {
                    return "outride_height_range";
                }
                try {
                    return h.a(this.a, arrayList, false);
                } catch (OutOfMemoryError e) {
                    return null;
                } catch (Exception e2) {
                    return null;
                }
            } catch (OutOfMemoryError e3) {
                arrayList.clear();
                return null;
            }
        }

        protected void onPostExecute(Object obj) {
            if (this.a.y != null) {
                this.a.y.dismiss();
            }
            if (obj != null) {
                String str = (String) obj;
                if (!TextUtils.isEmpty(str)) {
                    if ("outride_height_range".equals(str)) {
                        this.a.s = an.a(this.a, this.a.getString(R.string.select_image_outstrip_size), -1);
                        this.a.s.show();
                        return;
                    }
                    ImageItem imageItem = new ImageItem();
                    imageItem.imagePath = str;
                    imageItem.type = CheckCodeDO.CHECKCODE_IMAGE_URL_KEY;
                    this.a.a(imageItem);
                    return;
                }
                return;
            }
            this.a.s = an.a(this.a, "图片过大，请选择较小图片或减少图片合成数量", -1);
            this.a.s.show();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b = ai.a(this);
        setTheme(c.a().a(b));
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_image_grid);
        this.v = getIntent().getStringExtra("source");
        if ("CommendDetail".equals(this.v)) {
            this.c = true;
        }
        Log.d("SelectImageActivity", "onCreate isCommendDetail =" + this.c);
        if (com.budejie.www.activity.video.a.a()) {
            c();
            b();
            return;
        }
        Toast.makeText(this, "亲，你没有安装SD卡哦！", 0).show();
        finish();
    }

    private void b() {
        int i;
        boolean z = false;
        this.e = a.a();
        this.e.a(getApplicationContext(), this.c);
        this.d = this.e.c(true);
        int i2 = 0;
        while (i2 < this.d.size()) {
            List list = ((d) this.d.get(i2)).c;
            if (list != null) {
                int i3 = 0;
                int i4 = 0;
                while (i3 < list.size()) {
                    try {
                        if (!(list.get(i3) == null || ((ImageItem) list.get(i3)).imagePath == null || ((ImageItem) list.get(i3)).imagePath.equals("") || new File(((ImageItem) list.get(i3)).imagePath).exists())) {
                            ((d) this.d.get(i2)).c.remove(i3);
                            ((d) this.d.get(i2)).a--;
                            i4++;
                        }
                        i = i4;
                    } catch (Exception e) {
                        e.printStackTrace();
                        i = i4;
                    }
                    i3++;
                    i4 = i;
                }
                if (i4 == list.size()) {
                    this.d.remove(i2);
                }
            } else {
                this.d.remove(i2);
            }
            i2++;
        }
        this.p = new e(this, this.d);
        this.o.setAdapter(this.p);
        LayoutParams layoutParams = (LayoutParams) this.n.getLayoutParams();
        i = getWindowManager().getDefaultDisplay().getHeight();
        i2 = (this.d.size() * an.a((Context) this, 80)) + an.a((Context) this, 50);
        if (((double) i2) > ((double) i) * 0.75d) {
            layoutParams.height = (int) (((double) i) * 0.75d);
        } else {
            layoutParams.height = i2;
        }
        this.n.setLayoutParams(layoutParams);
        for (i = 0; i < this.d.size(); i++) {
            if (getString(R.string.select_image_all_pic).equals(((d) this.d.get(i)).b)) {
                this.u = i;
                this.u = getIntent().getIntExtra("albumIndex", i);
                break;
            }
        }
        if (!this.c) {
            z = true;
        }
        this.g = new f(this, z);
        if (this.d != null && this.d.size() > this.u) {
            this.g.a(((d) this.d.get(this.u)).c);
        }
        this.f.setAdapter(this.g);
    }

    private void c() {
        this.h = (TextView) findViewById(R.id.tv_cancel);
        this.h.setOnClickListener(this);
        this.i = (TextView) findViewById(R.id.confirmBtn);
        this.i.setOnClickListener(this);
        this.k = (TextView) findViewById(R.id.photo_text);
        this.l = (ImageView) findViewById(R.id.photo_arrows);
        this.j = (LinearLayout) findViewById(R.id.ll_select_image);
        this.o = (ListView) findViewById(R.id.lv_content);
        this.q = (FrameLayout) findViewById(R.id.fl_shade);
        this.o.setOnItemClickListener(this);
        this.j.setOnClickListener(this);
        this.f = (GridView) findViewById(R.id.gridview);
        this.n = (SlidingDrawer) findViewById(R.id.drawer_layout);
        this.k.setText("相册照片");
        this.l.setImageResource(R.drawable.up_arrows);
        this.n.setOnDrawerCloseListener(new OnDrawerCloseListener(this) {
            final /* synthetic */ SelectImageActivity a;

            {
                this.a = r1;
            }

            public void onDrawerClosed() {
                Log.i("LOG", "抽屉关闭");
                this.a.q.setVisibility(8);
                this.a.k.setText("相册照片");
                this.a.l.setImageResource(R.drawable.up_arrows);
            }
        });
        this.n.setOnDrawerOpenListener(new OnDrawerOpenListener(this) {
            final /* synthetic */ SelectImageActivity a;

            {
                this.a = r1;
            }

            public void onDrawerOpened() {
                Log.i("LOG", "抽屉打开");
                this.a.q.setVisibility(0);
                this.a.k.setText("本地照片");
                this.a.l.setImageResource(R.drawable.down_arrows);
            }
        });
        this.f.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ SelectImageActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.a.c || i != 0) {
                    RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.select_image_num_layout);
                    TextView textView = (TextView) view.findViewById(R.id.select_image_num);
                    ImageItem imageItem = (ImageItem) relativeLayout.getTag();
                    if (imageItem != null) {
                        Log.d("SelectImageActivity", "imageitem.mimeType =" + imageItem.mimeType);
                        if ("image/gif".equals(imageItem.mimeType) || "video/mp4".equals(imageItem.mimeType)) {
                            if (this.a.x.size() == 0) {
                                this.a.a(imageItem);
                                return;
                            }
                            this.a.s = an.a(this.a, this.a.getString(R.string.select_image_video_gif), -1);
                            this.a.s.show();
                            return;
                        } else if (imageItem.isSelected) {
                            imageItem.isSelected = false;
                            imageItem.selectedNum = "";
                            this.a.x.remove(imageItem);
                            for (int i2 = 0; i2 < this.a.x.size(); i2++) {
                                ((ImageItem) this.a.x.get(i2)).selectedNum = (i2 + 1) + "";
                            }
                            this.a.d();
                            this.a.g.notifyDataSetChanged();
                            return;
                        } else if (this.a.x.size() >= this.a.w) {
                            this.a.s = an.a(this.a, this.a.getString(R.string.select_image_outstrip_num), -1);
                            this.a.s.show();
                            return;
                        } else {
                            this.a.x.add(imageItem);
                            imageItem.selectedNum = this.a.x.size() + "";
                            imageItem.isSelected = true;
                            relativeLayout.setSelected(true);
                            textView.setText(imageItem.selectedNum);
                            this.a.d();
                            return;
                        }
                    }
                    return;
                }
                this.a.a();
            }
        });
    }

    private void a(ImageItem imageItem) {
        ArrayList arrayList = null;
        if (this.x.size() > 1) {
            ArrayList arrayList2 = new ArrayList();
            for (ImageItem imageItem2 : this.x) {
                arrayList2.add(imageItem2.imagePath);
            }
            arrayList = arrayList2;
        }
        if (this.c) {
            String str = imageItem.imagePath;
            String str2 = imageItem.type;
            String str3 = imageItem.thumbnailPath;
            Intent intent = new Intent();
            intent.putExtra("imgPath", str);
            intent.putExtra("thumbnail", str3);
            intent.putExtra("type", str2);
            if (arrayList != null) {
                intent.putExtra("MultipleImgPath", arrayList);
            }
            setResult(-1, intent);
            finish();
            return;
        }
        str = imageItem.imagePath;
        if ("TougaoActivity".equals(this.v)) {
            Intent intent2 = new Intent();
            intent2.putExtra("imgPath", str);
            if (arrayList != null) {
                intent2.putExtra("MultipleImgPath", arrayList);
            }
            setResult(-1, intent2);
            finish();
            return;
        }
        a(str, arrayList);
    }

    private void d() {
        if (this.x.size() > 0) {
            this.i.setVisibility(0);
            if (this.c) {
                this.i.setBackgroundResource(R.drawable.select_image_confirm_btn_selector);
                return;
            } else {
                this.i.setBackgroundResource(R.drawable.select_image_next_btn_selector);
                return;
            }
        }
        this.i.setVisibility(8);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                return;
            case R.id.confirmBtn:
                if (this.x.size() > 1) {
                    new a(this).execute(new Object[0]);
                    return;
                } else if (this.x.size() == 1) {
                    a((ImageItem) this.x.get(0));
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.n.toggle();
        this.t = ((d) this.d.get(i)).c;
        this.u = i;
        for (ImageItem imageItem : this.x) {
            imageItem.selectedNum = "";
            imageItem.isSelected = false;
        }
        this.x.clear();
        if (this.g != null) {
            this.g.a(this.t);
            this.g.notifyDataSetChanged();
            return;
        }
        boolean z;
        if (this.c) {
            z = false;
        } else {
            z = true;
        }
        this.g = new f(this, z);
        this.g.a(this.t);
        this.f.setAdapter(this.g);
    }

    public void a() {
        if (com.budejie.www.activity.video.a.a()) {
            Intent intent = new Intent(this, CaptureActivity.class);
            this.r = new File(Environment.getExternalStorageDirectory(), i.a().d() + ".jpg").getAbsolutePath();
            intent.putExtra("output", this.r);
            try {
                startActivityForResult(intent, 716);
                return;
            } catch (Exception e) {
                this.s = an.a((Activity) this, getString(R.string.no_camera), -1);
                this.s.show();
                return;
            }
        }
        this.s = an.a((Activity) this, getString(R.string.no_sdcard), -1);
        this.s.show();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            if (i != 716) {
                return;
            }
            if ("TougaoActivity".equals(this.v)) {
                Intent intent2 = new Intent();
                Object stringExtra = intent.getStringExtra("filepath");
                if (TextUtils.isEmpty(stringExtra)) {
                    intent2.putExtra("imgPath", this.r);
                } else {
                    intent2.putExtra("imgPath", stringExtra);
                }
                setResult(-1, intent2);
                finish();
                return;
            }
            String stringExtra2 = intent.getStringExtra("filepath");
            String str = this.r;
            if (stringExtra2 == null || stringExtra2.equals("")) {
                stringExtra2 = str;
            }
            a(stringExtra2, null);
        } else if (i2 == 0) {
            finish();
        }
    }

    private void a(String str, ArrayList<String> arrayList) {
        Intent intent = new Intent(this, TougaoActivity.class);
        intent.putExtra("imagePath", str);
        if (arrayList != null) {
            intent.putExtra("MultipleImgPath", arrayList);
        }
        startActivity(intent);
        finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.d != null) {
            this.d.clear();
        }
    }
}
