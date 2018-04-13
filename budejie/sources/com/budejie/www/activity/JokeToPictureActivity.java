package com.budejie.www.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Environment;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.androidex.widget.asyncimage.AsyncImageView;
import com.budejie.www.R;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.busevent.ShareEvent;
import com.budejie.www.busevent.ShareEvent.ShareAction;
import com.budejie.www.f.a;
import com.budejie.www.http.c;
import com.budejie.www.util.an;
import com.budejie.www.util.h;
import com.budejie.www.util.z;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class JokeToPictureActivity extends QiHooActivity implements OnClickListener, a {
    private ArrayList<CommentItem> A = new ArrayList();
    private int B;
    private TextView C;
    private ListItemObject D;
    private Bitmap E = null;
    private int[] F = new int[]{R.color.joke_to_pic_color_1, R.drawable.joke_to_pic_1, R.drawable.joke_to_pic_2, R.drawable.joke_to_pic_3, R.drawable.joke_to_pic_4, R.drawable.joke_to_pic_5, R.drawable.joke_to_pic_6, R.color.joke_to_pic_color_2, R.color.joke_to_pic_color_4, R.color.joke_to_pic_color_6};
    private LinearLayout a;
    private LinearLayout b;
    private c d;
    private LinearLayout e;
    private TextView f;
    private TextView g;
    private LinearLayout h;
    private LinearLayout i;
    private RelativeLayout j;
    private LinearLayout k;
    private TextView l;
    private ImageView m;
    private RelativeLayout n;
    private TextView o;
    private RelativeLayout p;
    private AsyncImageView q;
    private TextView r;
    private TextView s;
    private LinearLayout t;
    private RelativeLayout u;
    private ImageView v;
    private AsyncImageView w;
    private TextView x;
    private TextView y;
    private LinearLayout z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        MobclickAgent.onEvent((Context) this, "E02_A13", "段子转图片-打开页面");
        setContentView(R.layout.activity_joke_to_picture);
        a();
        e();
        b();
        d();
    }

    private void a() {
        this.d = new c(this, this);
        this.B = getIntent().getIntExtra("joke_to_pic_position", 0);
        this.D = (ListItemObject) getIntent().getSerializableExtra("joke_to_pic_post");
        if (this.B == 0 || this.D == null) {
            finish();
        }
        this.d.a(this.D.getWid(), "20", "", "1", 1111113, "");
    }

    private void b() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.h = (LinearLayout) findViewById(R.id.joke_to_pic_layout);
        this.h.setDrawingCacheEnabled(true);
        this.h.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        this.h.layout(0, 0, this.h.getMeasuredWidth(), this.h.getMeasuredHeight());
        this.i = (LinearLayout) findViewById(R.id.joke_to_pic_content_layout);
        this.C = (TextView) findViewById(R.id.joke_to_pic_content);
        this.C.setText(this.D.getContent());
        this.j = (RelativeLayout) findViewById(R.id.joke_to_pic_book_layout);
        this.l = (TextView) findViewById(R.id.joke_to_pic_book_content);
        this.k = (LinearLayout) findViewById(R.id.joke_to_pic_book_content_layout);
        this.k.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ JokeToPictureActivity a;

            {
                this.a = r1;
            }

            public void onGlobalLayout() {
                this.a.m.setImageBitmap(h.a(this.a.k.getHeight(), BitmapFactory.decodeResource(this.a.getResources(), R.drawable.book_left)));
            }
        });
        this.m = (ImageView) findViewById(R.id.joke_to_pic_book_left);
        this.l.setText(this.D.getContent());
        this.n = (RelativeLayout) findViewById(R.id.joke_to_pic_lace_layout);
        this.o = (TextView) findViewById(R.id.joke_to_pic_lace_content);
        this.o.setText(this.D.getContent());
        this.p = (RelativeLayout) findViewById(R.id.joke_to_pic_wx_layout);
        this.q = (AsyncImageView) findViewById(R.id.joke_to_pic_wx_header);
        this.r = (TextView) findViewById(R.id.joke_to_pic_wx_name);
        this.s = (TextView) findViewById(R.id.joke_to_pic_wx_content);
        this.t = (LinearLayout) findViewById(R.id.joke_to_pic_wx_comment);
        this.s.setText(this.D.getContent());
        this.u = (RelativeLayout) findViewById(R.id.joke_to_pic_wx_chat_layout);
        this.v = (ImageView) findViewById(R.id.joke_to_pic_wx_chat_top);
        this.w = (AsyncImageView) findViewById(R.id.joke_to_pic_wx_chat_header);
        this.x = (TextView) findViewById(R.id.joke_to_pic_wx_chat_name);
        this.y = (TextView) findViewById(R.id.joke_to_pic_wx_chat_content);
        this.z = (LinearLayout) findViewById(R.id.joke_to_pic_wx_chat_comment);
        this.y.setText(this.D.getContent());
        c();
    }

    private void c() {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.joke_to_pic_wx_name_color));
        if (this.A == null || this.A.size() <= 0) {
            this.t.setVisibility(8);
            this.z.setVisibility(8);
            return;
        }
        this.t.setVisibility(0);
        this.z.setVisibility(0);
        Iterator it = this.A.iterator();
        while (it.hasNext()) {
            CommentItem commentItem = (CommentItem) it.next();
            View textView = new TextView(this);
            textView.setTextColor(getResources().getColor(R.color.black));
            textView.setTextSize(13.0f);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 3, 0, 0);
            textView.setLayoutParams(layoutParams);
            CharSequence spannableStringBuilder = new SpannableStringBuilder(commentItem.getUname() + "：" + commentItem.getContent());
            spannableStringBuilder.setSpan(foregroundColorSpan, 0, commentItem.getUname().length(), 33);
            textView.setText(spannableStringBuilder);
            this.t.addView(textView);
            LinearLayout linearLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.item_joke_to_picture_chat, null);
            TextView textView2 = (TextView) linearLayout.findViewById(R.id.joke_to_pic_wx_chat_other_name);
            TextView textView3 = (TextView) linearLayout.findViewById(R.id.joke_to_pic_wx_chat_other_content);
            ((AsyncImageView) linearLayout.findViewById(R.id.joke_to_pic_wx_chat_other_header)).setAsyncCacheImage(commentItem.getProfile(), R.drawable.label_default_icon);
            textView2.setText(commentItem.getUname());
            textView3.setText(commentItem.getContent());
            this.z.addView(linearLayout);
        }
    }

    private void d() {
        this.e = (LinearLayout) findViewById(R.id.bottom_item_layout);
        if (this.e.getChildAt(0) != null) {
            b(this.F[0]);
            this.e.getChildAt(0).setSelected(true);
        }
        int childCount = this.e.getChildCount();
        for (int i = 0; i < childCount; i++) {
            this.e.getChildAt(i).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ JokeToPictureActivity b;

                public void onClick(View view) {
                    MobclickAgent.onEvent(this.b, "E02_A13", "段子转图片-" + (i + 1) + "_背景");
                    this.b.b(this.b.F[i]);
                    view.setSelected(true);
                    int childCount = this.b.e.getChildCount();
                    int i = 0;
                    while (i < childCount) {
                        if (i != i && this.b.e.getChildAt(i).isSelected()) {
                            this.b.e.getChildAt(i).setSelected(false);
                        }
                        i++;
                    }
                }
            });
        }
    }

    private void b(int i) {
        if (R.drawable.joke_to_pic_1 == i) {
            this.i.setVisibility(8);
            this.u.setVisibility(8);
            this.j.setVisibility(8);
            this.n.setVisibility(8);
            this.p.setVisibility(0);
            this.q.setImageDrawable(getResources().getDrawable(R.drawable.joke_to_pic_wx_header));
            this.r.setText(getResources().getString(R.string.joke_to_pic_wx_name));
        } else if (R.drawable.joke_to_pic_3 == i) {
            this.i.setVisibility(8);
            this.u.setVisibility(8);
            this.j.setVisibility(8);
            this.n.setVisibility(8);
            this.p.setVisibility(0);
            this.q.setImageDrawable(getResources().getDrawable(R.drawable.joke_to_pic_wx_header_2));
            this.r.setText(getResources().getString(R.string.joke_to_pic_wx_name_2));
        } else if (R.drawable.joke_to_pic_2 == i) {
            this.i.setVisibility(8);
            this.p.setVisibility(8);
            this.j.setVisibility(8);
            this.n.setVisibility(8);
            this.u.setVisibility(0);
            this.v.setImageDrawable(getResources().getDrawable(R.drawable.joke_to_pic_wx_chat_top));
            this.w.setImageDrawable(getResources().getDrawable(R.drawable.joke_to_pic_wx_header));
            this.x.setText(getResources().getString(R.string.joke_to_pic_wx_name));
        } else if (R.drawable.joke_to_pic_4 == i) {
            this.i.setVisibility(8);
            this.p.setVisibility(8);
            this.j.setVisibility(8);
            this.n.setVisibility(8);
            this.u.setVisibility(0);
            this.v.setImageDrawable(getResources().getDrawable(R.drawable.joke_to_pic_wx_chat_top_2));
            this.w.setImageDrawable(getResources().getDrawable(R.drawable.joke_to_pic_wx_header_2));
            this.x.setText(getResources().getString(R.string.joke_to_pic_wx_name_2));
        } else if (R.drawable.joke_to_pic_5 == i) {
            this.i.setVisibility(8);
            this.u.setVisibility(8);
            this.n.setVisibility(8);
            this.p.setVisibility(8);
            this.j.setVisibility(0);
        } else if (R.drawable.joke_to_pic_6 == i) {
            this.i.setVisibility(8);
            this.u.setVisibility(8);
            this.p.setVisibility(8);
            this.j.setVisibility(8);
            this.n.setVisibility(0);
        } else {
            this.i.setVisibility(0);
            this.u.setVisibility(8);
            this.p.setVisibility(8);
            this.j.setVisibility(8);
            this.n.setVisibility(8);
            if (R.color.joke_to_pic_color_2 == i) {
                this.C.setTextColor(getResources().getColor(R.color.joke_to_picture_text_color));
            } else {
                this.C.setTextColor(getResources().getColor(R.color.send_post_text));
            }
            this.i.setBackgroundColor(getResources().getColor(i));
            this.i.setPadding(an.a((Context) this, 35), an.a((Context) this, 45), an.a((Context) this, 35), an.a((Context) this, 20));
        }
    }

    private void e() {
        this.a = (LinearLayout) findViewById(R.id.left_layout);
        this.b = (LinearLayout) findViewById(R.id.right_layout);
        this.f = (TextView) findViewById(R.id.title_left_btn);
        this.g = (TextView) findViewById(R.id.title_right_btn);
        this.f.setText(R.string.cancel);
        this.g.setBackground(null);
        this.g.setText(R.string.share);
        this.a.setVisibility(0);
        this.b.setVisibility(0);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
    }

    public void onrefreshTheme() {
        super.onrefreshTheme();
        onRefreshTitleFontTheme(this.f, false);
        onRefreshTitleFontTheme(this.g, false);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_layout:
                EventBus.getDefault().post(new ShareEvent(ShareAction.CANCLE, ""));
                finish();
                MobclickAgent.onEvent((Context) this, "E02_A13", "段子转图片-取消");
                return;
            case R.id.right_layout:
                String f = f();
                Log.d("JokeToPictureActivity", "onClick: picPath=" + f);
                if (!TextUtils.isEmpty(f)) {
                    switch (this.B) {
                        case 3:
                            EventBus.getDefault().post(new ShareEvent(ShareAction.WXFRIEND, this.D, f, true));
                            break;
                        case 4:
                            EventBus.getDefault().post(new ShareEvent(ShareAction.WXP, this.D, f, true));
                            break;
                        case 7:
                            EventBus.getDefault().post(new ShareEvent(ShareAction.SMS, f));
                            break;
                        case 8:
                            EventBus.getDefault().post(new ShareEvent(ShareAction.QQ, f));
                            break;
                    }
                }
                MobclickAgent.onEvent((Context) this, "E02_A13", "段子转图片-分享");
                finish();
                return;
            default:
                return;
        }
    }

    private String f() {
        this.E = a(this.h);
        String str = System.currentTimeMillis() + ".jpg";
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/budejie");
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(Environment.getExternalStorageDirectory().toString() + "/budejie", str);
        if (h.a(this.E, file, CompressFormat.JPEG, 80)) {
            return file.getAbsolutePath();
        }
        return "";
    }

    public Bitmap a(View view) {
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        EventBus.getDefault().post(new ShareEvent(ShareAction.CANCLE, ""));
        finish();
        return true;
    }

    public void a(int i, String str) {
        if (i == 1111113) {
            Iterator it = z.a(str, true).iterator();
            boolean z = false;
            while (it.hasNext()) {
                boolean z2;
                CommentItem commentItem = (CommentItem) it.next();
                if (!TextUtils.isEmpty(commentItem.getContent())) {
                    this.A.add(commentItem);
                }
                if (commentItem.isIsnew()) {
                    z2 = true;
                } else {
                    z2 = z;
                }
                if (z2 && this.A.size() >= 5) {
                    break;
                }
                z = z2;
            }
            c();
        }
    }

    public void a(int i) {
    }
}
