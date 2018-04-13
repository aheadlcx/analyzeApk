package com.budejie.www.activity.textcomment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.internal.view.SupportMenu;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ali.auth.third.core.model.Constants;
import com.bdj.picture.edit.EditPictureActivity;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.OauthWeiboBaseAct;
import com.budejie.www.activity.base.BaseActvityWithLoadDailog;
import com.budejie.www.activity.image.BitmapCache;
import com.budejie.www.activity.image.SelectImageActivity;
import com.budejie.www.bean.NewCommentItem;
import com.budejie.www.bean.UserItem;
import com.budejie.www.c.e;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.j;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.au;
import com.budejie.www.util.n;
import com.budejie.www.util.p;
import com.budejie.www.util.v;
import com.budejie.www.util.z;
import com.budejie.www.widget.f;
import com.elves.update.a;
import com.tencent.open.SocialConstants;
import com.tencent.smtt.sdk.WebView;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import net.tsz.afinal.a.b;

public class MakeTextCommentsActivity extends BaseActvityWithLoadDailog {
    private a A;
    private e B;
    private j C;
    private f D;
    private BitmapCache E;
    private int F = 5;
    private int G;
    private OnClickListener H = new OnClickListener(this) {
        final /* synthetic */ MakeTextCommentsActivity a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            if (view == this.a.b) {
                this.a.m = this.a.x.getString("id", "");
                if (TextUtils.isEmpty(this.a.m)) {
                    an.a(this.a.u, 0, null, null, 0);
                } else {
                    this.a.a(false);
                }
            } else if (view == this.a.f || view == this.a.h) {
                this.a.m = this.a.x.getString("id", "");
                if (TextUtils.isEmpty(this.a.m)) {
                    an.a(this.a.u, 0, null, null, 0);
                    return;
                }
                UserItem e = this.a.z.e(this.a.m);
                if (TextUtils.isEmpty(e.getLevel()) || Integer.parseInt(e.getLevel()) < this.a.F) {
                    p.a(this.a.u, this.a.u.getString(R.string.send_media_comment_level_message, new Object[]{Integer.valueOf(this.a.F)}), this.a.getString(R.string.send_media_comment_level_ok), null);
                    return;
                }
                Intent intent = new Intent(this.a, SelectImageActivity.class);
                intent.putExtra("source", "CommendDetail");
                this.a.startActivityForResult(intent, 100);
            } else if (view == this.a.k) {
                this.a.p = null;
                this.a.r = null;
                this.a.h();
                this.a.a();
            } else if (view != this.a.j || this.a.p != null || this.a.r == null) {
            }
        }
    };
    private TextWatcher I = new TextWatcher(this) {
        final /* synthetic */ MakeTextCommentsActivity a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            this.a.h();
            if (TextUtils.isEmpty(this.a.c.getText())) {
                this.a.d.setTextColor(-1);
                this.a.d.setText("" + this.a.n);
                return;
            }
            int s = this.a.n - this.a.c.getText().toString().trim().length();
            if (s < 0) {
                this.a.d.setTextColor(SupportMenu.CATEGORY_MASK);
            } else {
                this.a.d.setTextColor(this.a.getResources().getColor(R.color.write_comment_content_len_color));
            }
            this.a.d.setText("" + s);
        }
    };
    private Handler J = new Handler(this) {
        final /* synthetic */ MakeTextCommentsActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = 0;
            int i2 = message.what;
            String str;
            Map c;
            String str2;
            if (i2 == 4) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.v = an.a(this.a.u, this.a.getString(R.string.bind_failed), -1);
                    this.a.v.show();
                    MobclickAgent.onEvent(this.a.u, "weibo_bind", "sina_faild");
                    return;
                }
                try {
                    i = Integer.parseInt(str);
                } catch (NumberFormatException e) {
                }
                if (i < 0) {
                    this.a.v = an.a(this.a.u, this.a.u.getString(R.string.bind_failed), -1);
                    this.a.v.show();
                    MobclickAgent.onEvent(this.a.u, "weibo_bind", "sina_faild");
                    return;
                }
                c = z.c(str);
                if (c == null || c.isEmpty()) {
                    MobclickAgent.onEvent(this.a.u, "weibo_bind", "sina_faild");
                    return;
                }
                str2 = (String) c.get("result_msg");
                if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                    MobclickAgent.onEvent(this.a.u, "weibo_bind", "sina_success");
                    this.a.m = (String) c.get("id");
                    this.a.z.a(this.a.m, c);
                    ai.a(this.a.u, this.a.m, Constants.SERVICE_SCOPE_FLAG_VALUE);
                    if (OauthWeiboBaseAct.mAccessToken != null) {
                        this.a.z.a(this.a.m, OauthWeiboBaseAct.mAccessToken.e());
                        return;
                    }
                    return;
                }
                an.a(this.a.u, str2, -1).show();
            } else if (i2 == 5) {
                str = (String) message.obj;
                if (TextUtils.isEmpty(str)) {
                    this.a.v = an.a(this.a.u, this.a.u.getString(R.string.bind_failed), -1);
                    this.a.v.show();
                    MobclickAgent.onEvent(this.a.u, "weibo_bind", "tencent_faild");
                    return;
                }
                try {
                    i = Integer.parseInt(str);
                } catch (NumberFormatException e2) {
                }
                if (i < 0) {
                    this.a.v = an.a(this.a.u, this.a.u.getString(R.string.bind_failed), -1);
                    this.a.v.show();
                    MobclickAgent.onEvent(this.a.u, "weibo_bind", "tencent_faild");
                    return;
                }
                c = z.c(str);
                if (c == null || c.isEmpty()) {
                    MobclickAgent.onEvent(this.a.u, "weibo_bind", "tencent_faild");
                    return;
                }
                str2 = (String) c.get("result_msg");
                if ("0".equals((String) c.get(com.alipay.sdk.util.j.c))) {
                    MobclickAgent.onEvent(this.a.u, "weibo_bind", "tencent_success");
                    this.a.m = (String) c.get("id");
                    this.a.z.a(this.a.m, c);
                    ai.a(this.a.u, this.a.m, Constants.SERVICE_SCOPE_FLAG_VALUE);
                    return;
                }
                an.a(this.a.u, str2, -1).show();
            } else if (i2 == 12) {
                c = z.u((String) message.obj);
                if (c != null) {
                    if ("1".equals((String) c.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY))) {
                        if (this.a.D != null) {
                            this.a.D.a(true, (String) c.get(SocialConstants.PARAM_APP_DESC));
                        }
                        this.a.J.sendEmptyMessageDelayed(13, 1000);
                    } else if (this.a.D != null) {
                        this.a.D.a(i, (String) c.get(SocialConstants.PARAM_APP_DESC));
                    }
                } else if (this.a.D != null) {
                    this.a.D.a(i, "");
                }
            } else if (i2 == 13) {
                this.a.i();
            } else if (i2 == 14) {
                an.a(this.a.u, this.a.c);
            }
        }
    };
    BitmapCache.a a = new BitmapCache.a(this) {
        final /* synthetic */ MakeTextCommentsActivity a;

        {
            this.a = r1;
        }

        public void a(ImageView imageView, Bitmap bitmap, Object... objArr) {
            if (imageView == null || bitmap == null) {
                Log.e("MakeTextCommentsActivity", "callback, bmp null");
                return;
            }
            String str = (String) objArr[0];
            if (str == null || !str.equals((String) imageView.getTag())) {
                Log.e("MakeTextCommentsActivity", "callback, bmp not match");
            } else {
                imageView.setImageBitmap(bitmap);
            }
        }
    };
    private TextView b;
    private EditText c;
    private TextView d;
    private TextView e;
    private ImageView f;
    private TextView h;
    private RelativeLayout i;
    private ImageView j;
    private ImageView k;
    private ImageView l;
    private String m;
    private int n = R$styleable.Theme_Custom_send_btn_text_color;
    private String o;
    private File p;
    private File q;
    private File r;
    private String s;
    private NewCommentItem t;
    private Activity u;
    private Toast v;
    private n w;
    private SharedPreferences x;
    private com.budejie.www.http.n y;
    private m z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_textcomments_layout);
        com.budejie.www.widget.a.a((Activity) this);
        this.u = this;
        d(R.id.navigation_bar);
        this.G = ai.a(this);
        setTitle((int) R.string.send_commend);
        c(null, getResources().getString(R.string.goback));
        d(this.H, getResources().getString(R.string.person_edit_name_complete));
        this.s = getIntent().getStringExtra("posts_id");
        if (getIntent().getSerializableExtra("reply_comment") != null) {
            this.t = (NewCommentItem) getIntent().getSerializableExtra("reply_comment");
        }
        b();
        g();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == 711) {
            bindTencent();
        } else if (i2 == -1) {
            if (i == 100) {
                String stringExtra = intent.getStringExtra("type");
                String stringExtra2 = intent.getStringExtra("imgPath");
                String stringExtra3 = intent.getStringExtra("thumbnail");
                ArrayList arrayList = (ArrayList) intent.getSerializableExtra("MultipleImgPath");
                if ("video".equals(stringExtra)) {
                    this.r = new File(stringExtra2);
                    h();
                    a(stringExtra3, stringExtra2);
                    this.J.sendEmptyMessageDelayed(14, 100);
                } else if (!CheckCodeDO.CHECKCODE_IMAGE_URL_KEY.equals(stringExtra)) {
                } else {
                    if (stringExtra2.endsWith("gif")) {
                        this.p = new File(stringExtra2);
                        h();
                        a(stringExtra3, stringExtra2);
                        this.J.sendEmptyMessageDelayed(14, 100);
                        return;
                    }
                    a(stringExtra2, arrayList);
                }
            } else if (i == 7201) {
                a(intent.getStringExtra("imagePath"));
            }
        } else if (i2 == 7203 && i == 7201) {
            a(intent.getStringExtra("imagePath"), (ArrayList) intent.getSerializableExtra("MultipleImgPath"));
        }
    }

    private void a(String str, ArrayList<String> arrayList) {
        Intent intent = new Intent(this.u, EditPictureActivity.class);
        intent.putExtra("picture_path_key", str);
        intent.putExtra("source", "TougaoActivity");
        if (arrayList != null) {
            intent.putExtra("MultipleImgPath", arrayList);
        }
        startActivityForResult(intent, 7201);
    }

    public void a(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.p = new File(str);
                h();
                a("", str);
                this.J.sendEmptyMessageDelayed(14, 100);
            }
        } catch (OutOfMemoryError e) {
            this.v = an.a((Activity) this, getString(R.string.tougao_pic_too_big), -1);
            this.v.show();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(String str, String str2) {
        this.i.setVisibility(0);
        this.j.setTag(str2);
        this.E.a(this.j, str, str2, this.a);
        if (this.r != null) {
            this.l.setVisibility(0);
        } else {
            this.l.setVisibility(8);
        }
    }

    private void a() {
        if (this.i != null) {
            this.j.setImageBitmap(null);
            this.i.setVisibility(8);
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        i();
        return true;
    }

    private void c(final OnClickListener onClickListener, String str) {
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.navigation_bar_title, null);
        textView.setText(str);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MakeTextCommentsActivity b;

            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                } else {
                    this.b.i();
                }
            }
        });
        if (this.g != null) {
            this.g.setLeftView(textView);
        }
    }

    private void d(final OnClickListener onClickListener, String str) {
        this.b = (TextView) getLayoutInflater().inflate(R.layout.navigation_bar_title, null);
        this.b.setText("");
        this.b.setBackgroundResource(this.G == 0 ? R.drawable.edit_title_bar_send_btn_defult : R.drawable.edit_title_bar_send_btn_night_defult);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ MakeTextCommentsActivity b;

            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                } else {
                    this.b.i();
                }
            }
        });
        if (this.g != null) {
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            this.b.setLayoutParams(layoutParams);
            this.g.setRightView(this.b);
        }
    }

    private void b() {
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this.u, "send_comment_level");
        if (!TextUtils.isEmpty(configParams)) {
            this.F = Integer.parseInt(configParams);
        }
        this.E = new BitmapCache();
        this.x = getSharedPreferences("weiboprefer", 0);
        this.w = new n();
        this.y = new com.budejie.www.http.n(this);
        this.z = new m(this);
        this.A = new a(this);
        this.B = new e(this);
        this.C = new j(this.u);
    }

    private void g() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.D = new f(this.u, R.style.dialogTheme);
        this.c = (EditText) findViewById(R.id.comment_editText);
        this.d = (TextView) findViewById(R.id.comment_wordsLength);
        this.e = (TextView) findViewById(R.id.comment_replyTV);
        this.f = (ImageView) findViewById(R.id.select_image_video);
        this.h = (TextView) findViewById(R.id.select_image_video_text);
        this.f.setOnClickListener(this.H);
        this.h.setOnClickListener(this.H);
        this.c.addTextChangedListener(this.I);
        this.i = (RelativeLayout) findViewById(R.id.select_image_video_mark);
        this.j = (ImageView) findViewById(R.id.select_image_video_mark_iv);
        this.k = (ImageView) findViewById(R.id.select_image_video_mark_cancel);
        this.l = (ImageView) findViewById(R.id.video_mark);
        this.k.setOnClickListener(this.H);
        this.j.setOnClickListener(this.H);
        if (this.t != null) {
            this.c.setHint("回复：" + this.t.user.username);
        }
    }

    private void a(boolean z) {
        String str;
        if (z) {
            str = "31";
            this.p = null;
            this.r = null;
            this.o = "";
        } else if (this.r != null) {
            str = "41";
            this.p = null;
            this.q = null;
            this.o = this.c.getText().toString().trim();
        } else if (this.p != null) {
            str = com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
            this.q = null;
            this.r = null;
            this.o = this.c.getText().toString().trim();
        } else {
            str = "29";
            this.o = this.c.getText().toString().trim();
            if (TextUtils.isEmpty(this.o)) {
                Toast.makeText(this.u, R.string.none_comment_content, 0).show();
                return;
            }
            int length = this.o.length();
            Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "comment_size");
            int i = 2;
            if (!TextUtils.isEmpty(configParams)) {
                i = Integer.parseInt(configParams);
            }
            if (length < i) {
                this.v = an.a((Activity) this, getString(R.string.commend_limit, new Object[]{Integer.valueOf(i)}), -1);
                this.v.show();
                return;
            } else if (length > R$styleable.Theme_Custom_send_btn_text_color) {
                this.v = an.a((Activity) this, getString(R.string.text_beyond) + (length - 140) + getString(R.string.again_input), -1);
                this.v.show();
                return;
            } else if (this.B.b(this.s, this.o)) {
                this.v = an.a((Activity) this, getString(R.string.commentExist), -1);
                this.v.show();
                return;
            } else {
                this.q = null;
            }
        }
        if (this.D != null) {
            this.D.show();
        }
        this.c.setText("");
        a();
        String str2 = "";
        if (this.t != null) {
            str2 = this.t.id;
        }
        a(this.C.a(this.u, this.s, this.m, str2, this.o, str, 0, this.p, this.q, this.r, "", 0));
    }

    public void a(b bVar) {
        net.tsz.afinal.b bVar2 = new net.tsz.afinal.b(this.u.getApplicationContext(), new v(this.u));
        bVar2.a("User-Agent", new WebView(this.u).getSettings().getUserAgentString() + NetWorkUtil.a());
        bVar2.a("cookie", NetWorkUtil.b(this.u));
        bVar2.a(NetWorkUtil.a(getApplicationContext()));
        StringBuilder append = new StringBuilder().append("http://d.api.budejie.com");
        j jVar = this.C;
        bVar2.b(com.lt.a.a(this.u).a(append.append(j.d(this.s)).toString()), bVar, new net.tsz.afinal.a.a<String>(this) {
            final /* synthetic */ MakeTextCommentsActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void onSuccess(Object obj) {
                a((String) obj);
            }

            public void a(String str) {
                this.a.p = null;
                this.a.q = null;
                this.a.r = null;
                if (!TextUtils.isEmpty(str)) {
                    Log.i("CommendDetail", "-->" + str);
                    this.a.J.sendMessage(this.a.J.obtainMessage(12, str));
                } else if (this.a.D != null) {
                    this.a.D.a(false, "");
                }
            }

            public void onFailure(Throwable th, int i, String str) {
                Log.i("CommendDetail", "onCommitVoice error : " + str);
                this.a.p = null;
                this.a.q = null;
                this.a.r = null;
                if (this.a.D != null) {
                    this.a.D.a(false, "");
                }
            }
        });
    }

    private void h() {
        if (TextUtils.isEmpty(this.c.getText()) && this.p == null && this.r == null) {
            this.b.setBackgroundResource(this.G == 0 ? R.drawable.edit_title_bar_send_btn_defult : R.drawable.edit_title_bar_send_btn_night_defult);
        } else {
            this.b.setBackgroundResource(this.G == 0 ? R.drawable.edit_send_btn_selector : R.drawable.edit_send_btn_night_selector);
        }
    }

    private void i() {
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        try {
            this.m = this.x.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.y.a(mAccessToken, this.m, 4, this.J);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    public void bindTencent() {
        this.m = this.x.getString("id", "");
        String sharePersistent = Util.getSharePersistent(this, "ACCESS_TOKEN");
        this.y.a(Util.getSharePersistent(this, "NAME"), sharePersistent, Util.getSharePersistent(this, "OPEN_ID"), this.m, 5, this.J);
    }
}
