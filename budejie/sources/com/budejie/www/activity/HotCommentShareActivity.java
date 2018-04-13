package com.budejie.www.activity;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.g.b;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.budejie.www.util.h;
import com.budejie.www.util.p;
import com.budejie.www.util.p.a;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import java.io.File;
import java.util.ArrayList;

public class HotCommentShareActivity extends QiHooActivity implements OnClickListener {
    private LinearLayout a;
    private LinearLayout b;
    private TextView d;
    private TextView e;
    private LinearLayout f;
    private LinearLayout g;
    private ArrayList<ImageView> h = new ArrayList();
    private HotCommentShareActivity i;
    private boolean j;
    private b k;
    private SharedPreferences l;
    private IWXAPI m;
    private String n;
    private ListItemObject o;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        aa.b("HotCommentShareActivity", "onCreate");
        setContentView(R.layout.activity_hot_comment_share);
        a();
        b();
        c();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!this.j) {
            try {
                new File(this.n).delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void a() {
        this.i = this;
        this.l = getSharedPreferences("weiboprefer", 0);
        this.k = new b(this, this.mSsoHandler, this.mTencent, this);
        this.m = WXAPIFactory.createWXAPI(this, "wx592fdc48acfbe290", true);
        this.m.registerApp("wx592fdc48acfbe290");
        this.o = (ListItemObject) getIntent().getSerializableExtra("hot_comment_share_post");
        this.n = getIntent().getStringExtra("hot_comment_share_image");
        aa.b("HotCommentShareActivity", "imagePath=" + this.n);
    }

    private void b() {
        this.a = (LinearLayout) findViewById(R.id.left_layout);
        this.b = (LinearLayout) findViewById(R.id.right_layout);
        this.d = (TextView) findViewById(R.id.title_left_btn);
        this.e = (TextView) findViewById(R.id.title_center_txt);
        this.d.setText(R.string.cancel);
        this.e.setText(R.string.hot_comment_share_title);
        this.e.setTextColor(getResources().getColor(R.color.white));
        this.a.setVisibility(0);
        this.b.setVisibility(8);
        this.a.setOnClickListener(this);
    }

    private void c() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.f = (LinearLayout) findViewById(R.id.hot_comment_save_ll);
        this.g = (LinearLayout) findViewById(R.id.hot_comment_share_ll);
        this.h.add((ImageView) findViewById(R.id.image_view_1));
        this.h.add((ImageView) findViewById(R.id.image_view_2));
        this.h.add((ImageView) findViewById(R.id.image_view_3));
        this.h.add((ImageView) findViewById(R.id.image_view_4));
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        d();
    }

    private void d() {
        Options options = new Options();
        options.inSampleSize = 1;
        Bitmap decodeFile = BitmapFactory.decodeFile(this.n, options);
        if (decodeFile != null) {
            ArrayList a = h.a(decodeFile, this.h.size(), 1);
            int i = 0;
            while (i < this.h.size() && i < a.size()) {
                decodeFile = (Bitmap) a.get(i);
                int width = decodeFile.getWidth();
                int height = decodeFile.getHeight();
                LayoutParams layoutParams = (LayoutParams) ((ImageView) this.h.get(i)).getLayoutParams();
                layoutParams.width = an.x(this.i) - an.a(this.i, 20);
                layoutParams.height = (height * layoutParams.width) / width;
                ((ImageView) this.h.get(i)).setLayoutParams(layoutParams);
                ((ImageView) this.h.get(i)).setImageBitmap(decodeFile);
                i++;
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        this.i.finish();
        return true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hot_comment_save_ll:
                this.j = true;
                Builder builder = new Builder(this);
                builder.setTitle(R.string.system_tip);
                builder.setMessage(this.i.getString(R.string.save_successed));
                builder.setPositiveButton(R.string.sure, null);
                if (!this.i.isFinishing()) {
                    builder.create().show();
                    return;
                }
                return;
            case R.id.hot_comment_share_ll:
                this.j = true;
                e();
                return;
            case R.id.left_layout:
                this.i.finish();
                return;
            default:
                return;
        }
    }

    private void e() {
        p.a(null, this.i, this.m.isWXAppInstalled(), this.m.getWXAppSupportAPI() >= Build.TIMELINE_SUPPORTED_SDK_INT, this.l, new a(this) {
            final /* synthetic */ HotCommentShareActivity a;

            {
                this.a = r1;
            }

            public void a(int i, Dialog dialog) {
                if (i == 3) {
                    this.a.k.b(this.a.o, this.a.m, new Handler(), this.a.n);
                    dialog.dismiss();
                } else if (i == 4) {
                    this.a.k.a(this.a.o, this.a.m, new Handler(), this.a.n);
                    dialog.dismiss();
                } else if (i == 8) {
                    this.a.k.b(this.a.o, null, this.a.n);
                    dialog.dismiss();
                }
            }
        }, false, true, false);
    }
}
