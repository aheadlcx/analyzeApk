package com.budejie.www.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.ali.auth.third.core.model.Constants;
import com.bdj.picture.edit.EditPictureActivity;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.htmlpage.HtmlFeatureActivity;
import com.budejie.www.activity.image.CaptureActivity;
import com.budejie.www.activity.image.SelectImageActivity;
import com.budejie.www.activity.labelsearch.SearchLabelActivity;
import com.budejie.www.activity.plate.bean.PlateBean;
import com.budejie.www.activity.video.UploadInfo;
import com.budejie.www.activity.video.k;
import com.budejie.www.activity.video.q;
import com.budejie.www.bean.DraftBean;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.ShenHeItem;
import com.budejie.www.bean.VideoParseResultInfo;
import com.budejie.www.bean.Vote;
import com.budejie.www.bean.VoteData;
import com.budejie.www.bean.sendLinkResultInfo;
import com.budejie.www.c.b;
import com.budejie.www.c.f;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.e;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.http.n;
import com.budejie.www.util.aa;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.au;
import com.budejie.www.util.h;
import com.budejie.www.util.p;
import com.budejie.www.util.s;
import com.budejie.www.util.x;
import com.budejie.www.util.z;
import com.budejie.www.widget.ScrollBottomScrollView;
import com.budejie.www.widget.SelectLabelLayout;
import com.budejie.www.widget.VoteView;
import com.budejie.www.widget.VoteView.c;
import com.budejie.www.widget.parsetagview.ParseTagEditText;
import com.google.gson.Gson;
import com.tencent.tauth.UiError;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import net.tsz.afinal.a.a;
import org.json.JSONObject;

public class TougaoActivity extends OauthWeiboBaseAct implements OnClickListener {
    private TougaoActivity A;
    private Dialog B;
    private String C;
    private m D;
    private int E;
    private int F;
    private boolean G = false;
    private boolean H = false;
    private String I = "";
    private RelativeLayout J;
    private LinearLayout K;
    private LinearLayout L;
    private TextView M;
    private TextView N;
    private TextView O;
    private LinearLayout P;
    private ImageView Q;
    private ImageView R;
    private ParseTagEditText S;
    private RelativeLayout T;
    private ScrollBottomScrollView U;
    private EditText V;
    private ImageView W;
    private View X;
    private View Y;
    private RelativeLayout Z;
    Toast a;
    private a<String> aA = new a<String>(this) {
        final /* synthetic */ TougaoActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
        }

        public void a(String str) {
            super.onSuccess(str);
            new AsyncTask<String, String, String>(this) {
                final /* synthetic */ AnonymousClass10 a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((String) obj);
                }

                protected void a(String str) {
                }

                protected String a(String... strArr) {
                    try {
                        VideoParseResultInfo videoParseResultInfo = (VideoParseResultInfo) z.a(strArr[0], VideoParseResultInfo.class);
                        if (videoParseResultInfo == null || videoParseResultInfo.code != 200) {
                            if (videoParseResultInfo != null) {
                                Message message = new Message();
                                message.what = 3;
                                message.obj = videoParseResultInfo.message;
                                this.a.a.z.sendMessage(message);
                            }
                            return null;
                        }
                        this.a.a.h = videoParseResultInfo.url;
                        this.a.a.z.sendEmptyMessage(0);
                        return null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.execute(new String[]{str});
        }
    };
    private a<String> aB = new a<String>(this) {
        final /* synthetic */ TougaoActivity a;

        {
            this.a = r1;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            a((String) obj);
        }

        public void onStart() {
            aa.b("TougaoActivity", "onSendLinkRequestCallBack onStart ");
        }

        public void onFailure(Throwable th, int i, String str) {
            super.onFailure(th, i, str);
            aa.b("TougaoActivity", "onSendLinkRequestCallBack onFailure errorNo = " + i + " strMsg=" + str);
            this.a.ai = true;
            Toast.makeText(this.a, "发表失败", 0).show();
        }

        public void a(String str) {
            super.onSuccess(str);
            aa.b("TougaoActivity", "onSendLinkRequestCallBack onSuccess ");
            new AsyncTask<String, String, String>(this) {
                final /* synthetic */ AnonymousClass11 a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((String[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((String) obj);
                }

                protected void a(String str) {
                    aa.b("TougaoActivity", "onPostExecute ");
                }

                protected String a(String... strArr) {
                    String str = strArr[0];
                    try {
                        sendLinkResultInfo sendlinkresultinfo = (sendLinkResultInfo) z.a(str, sendLinkResultInfo.class);
                        aa.b("TougaoActivity", "onSendLinkRequestCallBack = " + str);
                        if (sendlinkresultinfo == null || !"200".equals(sendlinkresultinfo.code)) {
                            if (sendlinkresultinfo != null) {
                                this.a.a.ai = true;
                                Message message = new Message();
                                message.what = 3;
                                message.obj = sendlinkresultinfo.message;
                                this.a.a.z.sendMessage(message);
                            }
                            return null;
                        }
                        Intent intent = new Intent(this.a.a, MyPostsActivity.class);
                        intent.putExtra("from_tougao_tag", true);
                        this.a.a.startActivity(intent);
                        this.a.a.finish();
                        i.a(this.a.a.A.getString(R.string.track_action_send_link), this.a.a.A.getString(R.string.track_event_send_post_success));
                        return null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.execute(new String[]{str});
        }
    };
    private ImageView aa;
    private RelativeLayout ab;
    private LinearLayout ac;
    private TextView ad;
    private ImageView ae;
    private String af = "0";
    private AlertDialog ag;
    private InputMethodManager ah;
    private boolean ai = true;
    private String aj;
    private j ak;
    private TextView al;
    private BudejieApplication am;
    private ArrayList<String> an;
    private VoteView ao;
    private String ap;
    private SelectLabelLayout aq;
    private SelectLabelLayout ar;
    private String as;
    private String at;
    private int au;
    private boolean av;
    private DialogInterface.OnClickListener aw = new DialogInterface.OnClickListener(this) {
        final /* synthetic */ TougaoActivity a;

        {
            this.a = r1;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case -2:
                    this.a.ag.dismiss();
                    return;
                case -1:
                    this.a.ag.dismiss();
                    this.a.V.setText("");
                    this.a.V.requestFocus();
                    return;
                default:
                    return;
            }
        }
    };
    private String ax;
    private UploadInfo ay;
    private int az;
    int b = 0;
    int c;
    int d = R$styleable.Theme_Custom_send_btn_text_color;
    String e;
    String f;
    String g = "";
    String h;
    String i = "5";
    String j = "这里添加文字，请勿发布色情、政治等违反国家法律的内容，违者封号处理。";
    String k = "这里添加文字，请勿发布色情、政治等违反国家法律的内容，违者封号处理。";
    String l = "描述";
    String m;
    com.budejie.www.http.m n;
    n o;
    SharedPreferences p;
    com.elves.update.a q;
    Timer r;
    b s;
    f t;
    String u;
    String v;
    TimerTask w = new TimerTask(this) {
        final /* synthetic */ TougaoActivity a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.z.sendEmptyMessage(931);
        }
    };
    TextWatcher x = new TextWatcher(this) {
        final /* synthetic */ TougaoActivity a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(this.a.S.getText())) {
                this.a.N.setBackgroundResource(this.a.au == 0 ? R.drawable.edit_title_bar_send_btn_defult : R.drawable.edit_title_bar_send_btn_night_defult);
            } else {
                this.a.N.setBackgroundResource(this.a.au == 0 ? R.drawable.edit_send_btn_selector : R.drawable.edit_send_btn_night_selector);
            }
        }
    };
    TextWatcher y = new TextWatcher(this) {
        final /* synthetic */ TougaoActivity a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(editable.toString())) {
                this.a.W.setVisibility(8);
            } else {
                this.a.W.setVisibility(0);
            }
        }
    };
    Handler z = new Handler(this) {
        final /* synthetic */ TougaoActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int i = false;
            int i2 = message.what;
            if (i2 == 932) {
                this.a.m();
                this.a.q.a(this.a.c, (boolean) i, (int) R.string.tougao_failed);
                this.a.q();
            } else if (i2 == 933) {
                this.a.n();
                this.a.q.a(this.a.c, true, (int) R.string.tougao_successed);
                this.a.q();
            } else if (i2 == 934) {
                r0 = (String) message.obj;
                if (TextUtils.isEmpty(r0)) {
                    this.a.z.sendEmptyMessage(932);
                } else if ("0".equals(r0)) {
                    this.a.z.sendEmptyMessage(933);
                } else {
                    this.a.z.sendEmptyMessage(932);
                }
            } else if (i2 == 931) {
                this.a.q.a(this.a.c);
            } else if (i2 == 945) {
                an.a(this.a, this.a.S);
            } else if (i2 == 4) {
                r0 = (String) message.obj;
                if (TextUtils.isEmpty(r0)) {
                    this.a.a = an.a(this.a.A, this.a.A.getString(R.string.bind_failed), -1);
                    this.a.a.show();
                    MobclickAgent.onEvent(this.a.A, "weibo_bind", "sina_faild");
                } else {
                    try {
                        i = Integer.parseInt(r0);
                    } catch (NumberFormatException e) {
                    }
                    if (i < 0) {
                        this.a.a = an.a(this.a.A, this.a.A.getString(R.string.bind_failed), -1);
                        this.a.a.show();
                        MobclickAgent.onEvent(this.a.A, "weibo_bind", "sina_faild");
                    } else {
                        r2 = z.c(r0);
                        if (r2 == null || r2.isEmpty()) {
                            MobclickAgent.onEvent(this.a.A, "weibo_bind", "sina_faild");
                        } else {
                            r1 = (String) r2.get("result_msg");
                            if ("0".equals((String) r2.get(com.alipay.sdk.util.j.c))) {
                                MobclickAgent.onEvent(this.a.A, "weibo_bind", "sina_success");
                                this.a.C = (String) r2.get("id");
                                this.a.D.a(this.a.C, r2);
                                ai.a(this.a.A, this.a.C, Constants.SERVICE_SCOPE_FLAG_VALUE);
                                if (OauthWeiboBaseAct.mAccessToken != null) {
                                    this.a.D.a(this.a.C, OauthWeiboBaseAct.mAccessToken.e());
                                }
                            } else {
                                an.a(this.a.A, r1, -1).show();
                            }
                        }
                    }
                }
                this.a.B.dismiss();
            } else if (i2 == 5) {
                r0 = (String) message.obj;
                if (TextUtils.isEmpty(r0)) {
                    this.a.a = an.a(this.a.A, this.a.A.getString(R.string.bind_failed), -1);
                    this.a.a.show();
                    MobclickAgent.onEvent(this.a.A, "weibo_bind", "tencent_faild");
                } else {
                    try {
                        i = Integer.parseInt(r0);
                    } catch (NumberFormatException e2) {
                    }
                    if (i < 0) {
                        this.a.a = an.a(this.a.A, this.a.A.getString(R.string.bind_failed), -1);
                        this.a.a.show();
                        MobclickAgent.onEvent(this.a.A, "weibo_bind", "tencent_faild");
                    } else {
                        r2 = z.c(r0);
                        if (r2 == null || r2.isEmpty()) {
                            MobclickAgent.onEvent(this.a.A, "weibo_bind", "tencent_faild");
                        } else {
                            r1 = (String) r2.get("result_msg");
                            if ("0".equals((String) r2.get(com.alipay.sdk.util.j.c))) {
                                MobclickAgent.onEvent(this.a.A, "weibo_bind", "tencent_success");
                                this.a.C = (String) r2.get("id");
                                this.a.D.a(this.a.C, r2);
                                ai.a(this.a.A, this.a.C, Constants.SERVICE_SCOPE_FLAG_VALUE);
                            } else {
                                an.a(this.a.A, r1, -1).show();
                            }
                        }
                    }
                }
                this.a.B.dismiss();
            } else if (i2 == 10) {
                r0 = (String) message.obj;
                if (TextUtils.isEmpty(r0)) {
                    this.a.a = an.a(this.a.A, this.a.A.getString(R.string.bind_failed), -1);
                    this.a.a.show();
                    MobclickAgent.onEvent(this.a.A, "weibo_bind", "qzone_faild");
                } else {
                    try {
                        i = Integer.parseInt(r0);
                    } catch (NumberFormatException e3) {
                    }
                    if (i < 0) {
                        this.a.a = an.a(this.a.A, this.a.A.getString(R.string.bind_failed), -1);
                        this.a.a.show();
                        MobclickAgent.onEvent(this.a.A, "weibo_bind", "qzone_faild");
                    } else {
                        r2 = z.c(r0);
                        if (r2 == null || r2.isEmpty()) {
                            MobclickAgent.onEvent(this.a.A, "weibo_bind", "qzone_faild");
                        } else {
                            r1 = (String) r2.get("result_msg");
                            if ("0".equals((String) r2.get(com.alipay.sdk.util.j.c))) {
                                MobclickAgent.onEvent(this.a.A, "weibo_bind", "qzone_success");
                                this.a.C = (String) r2.get("id");
                                this.a.D.a(this.a.C, r2);
                                ai.a(this.a.A, this.a.C, Constants.SERVICE_SCOPE_FLAG_VALUE);
                            } else {
                                an.a(this.a.A, r1, -1).show();
                            }
                        }
                    }
                }
                this.a.B.dismiss();
            } else if (i2 == 946) {
                an.a(this.a.A, 1, "mytougao", "mytougao", 124);
            } else if (i2 == 937) {
                r0 = (String) message.obj;
                this.a.s.d("1");
                ShenHeItem d = com.budejie.www.j.a.d(this.a.A, r0);
                this.a.s.a(d);
                if (this.a.H) {
                    this.a.t.a(-1, d.getDataList());
                } else {
                    this.a.t.a(1, d.getDataList());
                }
            } else if (i2 == 0) {
                this.a.c();
            } else if (i2 == 1) {
                this.a.d();
            } else if (i2 == 2) {
                this.a.S.setText((String) message.obj);
                this.a.S.setHint(this.a.l);
            } else if (i2 == 3) {
                Toast.makeText(this.a.A, (String) message.obj, i).show();
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.tougao_layout);
        com.budejie.www.widget.a.a((Activity) this);
        this.am = (BudejieApplication) getApplication();
        this.ak = new j();
        this.au = ai.a(this);
        e();
        f();
        g();
        s();
        this.an = (ArrayList) getIntent().getSerializableExtra("MultipleImgPath");
        String stringExtra = getIntent().getStringExtra("imagePath");
        if (!TextUtils.isEmpty(stringExtra)) {
            if (stringExtra.endsWith("gif")) {
                a(stringExtra);
            } else if (getIntent().getBooleanExtra("islarge_length_image", false)) {
                a(stringExtra);
            } else {
                b(stringExtra);
            }
        }
    }

    private void e() {
        this.E = getIntent().getIntExtra("TOUGAO_TYPE", 10);
        this.as = getIntent().getStringExtra("label_name");
        this.F = this.E;
        int intExtra = getIntent().getIntExtra("label_id", -1);
        this.at = intExtra == -1 ? "" : String.valueOf(intExtra);
        this.H = getIntent().getBooleanExtra("local_file", false);
        this.aj = getIntent().getStringExtra("h5_reserve");
        Object stringExtra = getIntent().getStringExtra("inner_result");
        if (this.E == 41 || !TextUtils.isEmpty(stringExtra)) {
            this.E = 41;
            this.F = this.E;
            this.ax = stringExtra;
            this.ay = q.a(this, this.ax);
            i.a(getString(R.string.track_action_send_video), getString(R.string.track_event_video_ready));
            if (TextUtils.isEmpty(this.ay.getImage())) {
                Toast.makeText(this, R.string.send_video_error, 0).show();
                MobclickAgent.onEvent(getApplicationContext(), "E03-A12", "视频无缩略图，无法发表");
                finish();
            }
        }
    }

    private void f() {
        this.ah = (InputMethodManager) getSystemService("input_method");
        this.A = this;
        this.t = new f(this);
        this.s = new b(this.A);
        this.D = new m(this.A);
        this.n = new com.budejie.www.http.m();
        this.o = new n(this.A);
        this.p = getSharedPreferences("weiboprefer", 0);
        this.C = this.p.getString("id", "");
        this.q = new com.elves.update.a(this);
        this.r = new Timer();
    }

    private void b(String str) {
        Intent intent = new Intent(this.A, EditPictureActivity.class);
        intent.putExtra("albumIndex", this.az);
        intent.putExtra("picture_path_key", str);
        intent.putExtra("source", "TougaoActivity");
        if (this.an != null) {
            intent.putExtra("MultipleImgPath", this.an);
        }
        startActivityForResult(intent, 7201);
    }

    protected void onResume() {
        super.onResume();
        if (this.B != null) {
            this.B.dismiss();
        }
        if (this.p != null) {
            this.C = this.p.getString("id", "");
        }
        j();
        new Timer().schedule(new TimerTask(this) {
            final /* synthetic */ TougaoActivity a;

            {
                this.a = r1;
            }

            public void run() {
                an.a(this.a, this.a.S);
            }
        }, 300);
    }

    protected void onPause() {
        super.onPause();
        k.a(this.A).o();
        this.ab.setVisibility(8);
    }

    protected void onDestroy() {
        super.onDestroy();
        k.a((Context) this).p();
    }

    private void g() {
        an.a((LinearLayout) findViewById(R.id.TitleGapLayout));
        this.B = new Dialog(this, R.style.dialogTheme);
        this.B.setContentView(R.layout.loaddialog);
        this.B.setCanceledOnTouchOutside(true);
        this.J = (RelativeLayout) findViewById(R.id.titleLayout);
        this.K = (LinearLayout) findViewById(R.id.left_layout);
        this.L = (LinearLayout) findViewById(R.id.right_layout);
        this.M = (TextView) findViewById(R.id.title_left_btn);
        this.N = (TextView) findViewById(R.id.title_right_btn);
        this.O = (TextView) findViewById(R.id.title_center_txt);
        this.M.setText(R.string.cancel);
        this.N.setText("");
        this.N.setBackgroundResource(this.au == 0 ? R.drawable.edit_title_bar_send_btn_defult : R.drawable.edit_title_bar_send_btn_night_defult);
        this.K.setVisibility(0);
        this.L.setVisibility(0);
        this.K.setOnClickListener(this);
        this.L.setOnClickListener(this);
        this.R = (ImageView) findViewById(R.id.picUploadBtn);
        this.P = (LinearLayout) findViewById(R.id.video_layout);
        this.Q = (ImageView) findViewById(R.id.video_image);
        findViewById(R.id.selector_label).setOnClickListener(this);
        findViewById(R.id.add_vote).setOnClickListener(this);
        findViewById(R.id.add_vote_name).setOnClickListener(this);
        this.al = (TextView) findViewById(R.id.selector_label_name);
        this.al.setOnClickListener(this);
        this.S = (ParseTagEditText) findViewById(R.id.editTougao);
        this.S.setTextChangedListener(this.x);
        this.S.setListener(new com.budejie.www.widget.parsetagview.a(this.A));
        this.T = (RelativeLayout) findViewById(R.id.tougao_edit_layout);
        this.U = (ScrollBottomScrollView) findViewById(R.id.tougao_scroll_view);
        this.V = (EditText) findViewById(R.id.tougao_link);
        this.W = (ImageView) findViewById(R.id.tougao_link_clear);
        this.X = findViewById(R.id.divider1);
        this.Y = findViewById(R.id.divider2);
        this.Z = (RelativeLayout) findViewById(R.id.preview_layout);
        this.aa = (ImageView) findViewById(R.id.preview_button);
        this.ab = (RelativeLayout) findViewById(R.id.preview_container);
        this.ac = (LinearLayout) findViewById(R.id.preview_text_layout);
        this.ad = (TextView) findViewById(R.id.preview_text);
        this.ae = (ImageView) findViewById(R.id.help_iv);
        h();
        this.aq.setVisibility(this.E == 0 ? 8 : 0);
        if (an.a((Context) this)) {
            Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "tougao_hint");
            if (!TextUtils.isEmpty(configParams)) {
                this.j = configParams;
            }
            configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "tougao_video_hint");
            if (!TextUtils.isEmpty(configParams)) {
                this.k = configParams;
            }
            configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "tougao_size");
            if (!TextUtils.isEmpty(configParams)) {
                this.i = configParams;
            }
        }
        l();
        if (this.E == 0) {
            this.F = 41;
            CharSequence configParams2 = OnlineConfigAgent.getInstance().getConfigParams(this, "tougao_link_preview_tip");
            if (!TextUtils.isEmpty(configParams2)) {
                this.ad.setText(configParams2);
            }
            this.V.setVisibility(0);
            this.X.setVisibility(0);
            this.Y.setVisibility(0);
            this.Z.setVisibility(0);
            this.O.setText(R.string.link_tougao);
            this.S.getPaint().setTypeface(Typeface.DEFAULT_BOLD);
            this.S.setHint(this.l);
            this.U.setScrollBottomListener(new ScrollBottomScrollView.a(this) {
                final /* synthetic */ TougaoActivity a;

                {
                    this.a = r1;
                }

                public void a() {
                    aa.b("TougaoActivity", "scrollBottom " + this.a.getWindow().getAttributes().softInputMode);
                    boolean hideSoftInputFromWindow = this.a.ah.hideSoftInputFromWindow(this.a.S.getWindowToken(), 0);
                    if (hideSoftInputFromWindow) {
                        aa.b("TougaoActivity", "softInputMode is show hiden=" + hideSoftInputFromWindow);
                        this.a.aa.requestFocus();
                        this.a.V.clearFocus();
                        this.a.S.clearFocus();
                    }
                }
            });
            this.aa.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ TougaoActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (!k.a(this.a.A).c || !this.a.ab.isShown()) {
                        MobclickAgent.onEvent(this.a.A, "E02-A12", "预览");
                        if (an.a(this.a.p)) {
                            this.a.ab.setVisibility(0);
                            LayoutParams layoutParams = this.a.ab.getLayoutParams();
                            layoutParams.height = 0;
                            layoutParams.width = 0;
                            this.a.ab.setLayoutParams(layoutParams);
                            this.a.d(this.a.V.getText().toString());
                            return;
                        }
                        an.a(this.a.A, 0, "", "", 0);
                    }
                }
            });
            this.W.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ TougaoActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.V.setText("");
                    this.a.V.requestFocus();
                }
            });
            this.V.setOnFocusChangeListener(new OnFocusChangeListener(this) {
                final /* synthetic */ TougaoActivity a;

                {
                    this.a = r1;
                }

                public void onFocusChange(View view, boolean z) {
                    if (!z) {
                        this.a.W.setVisibility(8);
                    } else if (!TextUtils.isEmpty(this.a.V.getText().toString())) {
                        this.a.W.setVisibility(0);
                    }
                }
            });
            this.V.addTextChangedListener(this.y);
            this.ae.setOnClickListener(this);
            this.ac.setOnClickListener(this);
            j();
        } else if (this.E == 10) {
            this.O.setText(R.string.picture_tougao);
            this.S.setHint(this.j);
            this.R.setVisibility(0);
        } else if (this.E == 41) {
            this.O.setText(R.string.video_tougao);
            this.S.setHint(this.k);
            this.P.setVisibility(0);
            this.Q.setImageBitmap(BitmapFactory.decodeFile(this.ay.getImage()));
            this.Q.setOnClickListener(this);
        } else {
            this.O.setText(R.string.duanzi_tougao);
            this.S.setHint(this.j);
        }
        if (this.E != 0) {
            aa.b("TougaoActivity", "tougaoEditLayout.setOnClickListener");
            this.T.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ TougaoActivity a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    aa.b("TougaoActivity", "tougao_layout.onClick()");
                    an.a(this.a, this.a.S);
                }
            });
        }
        k();
        this.ao = (VoteView) findViewById(R.id.vote_view);
        this.ao.setCancelListener(new VoteView.b(this) {
            final /* synthetic */ TougaoActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.ap = "";
            }
        });
        this.ao.setItemClickListener(new c(this) {
            final /* synthetic */ TougaoActivity a;

            {
                this.a = r1;
            }

            public void a(VoteData voteData, Vote vote) {
                Intent intent = new Intent(this.a, AddVoteActivity.class);
                intent.putExtra("vote_data_key", this.a.ap);
                this.a.startActivityForResult(intent, 10);
            }
        });
        this.ao.setVisibility(8);
    }

    private void h() {
        this.aq = (SelectLabelLayout) findViewById(R.id.label_layout);
        this.ar = (SelectLabelLayout) findViewById(R.id.link_label_layout);
        this.aq.setListener(new OnClickListener(this) {
            final /* synthetic */ TougaoActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.i();
            }
        });
        this.av = !TextUtils.isEmpty(this.as);
        this.aq.setLabelName(this.as);
        this.ar.setLabelName(this.as);
        this.ar.setListener(new OnClickListener(this) {
            final /* synthetic */ TougaoActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.i();
            }
        });
    }

    private void i() {
        Intent intent = new Intent(this, SearchLabelActivity.class);
        intent.putExtra("topicType", this.F);
        startActivityForResult(intent, 10);
    }

    private void j() {
        if (this.E == 0) {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
            if (clipboardManager.getText() != null && !this.g.equals(clipboardManager.getText().toString()) && x.a(this.A).a(clipboardManager.getText().toString())) {
                k.a(this.A).p();
                this.g = clipboardManager.getText().toString();
                this.V.setText(this.g);
                this.S.requestFocus();
                this.S.setHint(R.string.tougao_link_title_parse);
                c(this.g);
            }
        }
    }

    private void c(final String str) {
        new Thread(new Runnable(this) {
            final /* synthetic */ TougaoActivity b;

            public void run() {
                String a = x.a(this.b.A).a(this.b.A, str);
                if (TextUtils.isEmpty(this.b.S.getText().toString())) {
                    Message message = new Message();
                    message.what = 2;
                    message.obj = a;
                    this.b.z.sendMessage(message);
                }
            }
        }).start();
    }

    private void d(final String str) {
        if (x.a(this.A).a(str)) {
            new Thread(new Runnable(this) {
                final /* synthetic */ TougaoActivity b;

                public void run() {
                    ArrayList b = x.a(this.b.A).b(this.b.A, str);
                    if (b.size() >= 1) {
                        this.b.g((String) b.get(0));
                        return;
                    }
                    this.b.h = str;
                    this.b.z.sendEmptyMessage(1);
                }
            }).start();
        } else {
            this.ag = p.a(this.A, getResources().getString(R.string.sd_title), getResources().getString(R.string.tougao_link_dialog_content), getResources().getString(R.string.tougao_link_dialog_rewrite), getResources().getString(R.string.cancel), this.aw);
        }
    }

    private void k() {
        Object obj = "0";
        if (this.E == 29 || this.E == 0) {
            obj = OnlineConfigAgent.getInstance().getConfigParams(this, "tougao_duanzi_text_limit");
        } else if (this.E == 10) {
            obj = OnlineConfigAgent.getInstance().getConfigParams(this, "tougao_image_text_limit");
        } else if (this.E == 41) {
            obj = OnlineConfigAgent.getInstance().getConfigParams(this, "tougao_video_text_limit");
        }
        if (!TextUtils.isEmpty(obj) && !"0".equals(obj)) {
            this.d = Integer.parseInt(obj);
            this.S.setFilters(new InputFilter[]{new LengthFilter(this.d), new s()});
        }
    }

    private void l() {
        this.e = this.p.getString("tougaoCache", "");
        if (!TextUtils.isEmpty(this.e)) {
            this.S.setText(this.e);
        }
        this.m = this.p.getString("tougaoImageCache", "");
        if (!TextUtils.isEmpty(this.m)) {
            try {
                f(this.m);
            } catch (OutOfMemoryError e) {
                aa.e("TougaoActivity", "initTougaoCacheData, OutOfMemoryError");
            }
        }
    }

    private void m() {
        Editor edit = this.p.edit();
        edit.putString("tougaoCache", this.e);
        edit.putString("tougaoImageCache", this.m);
        edit.commit();
    }

    private void n() {
        Editor edit = this.p.edit();
        edit.putString("tougaoCache", "");
        edit.putString("tougaoImageCache", "");
        edit.commit();
    }

    public void onClick(View view) {
        boolean z = false;
        Intent intent;
        switch (view.getId()) {
            case R.id.add_vote:
            case R.id.add_vote_name:
                intent = new Intent(this, AddVoteActivity.class);
                intent.putExtra("vote_data_key", this.ap);
                startActivityForResult(intent, 10);
                break;
            case R.id.selector_contacts:
                startActivityForResult(new Intent(this, SelectorContactsActivity.class), 435);
                break;
            case R.id.selector_label:
            case R.id.selector_label_name:
                intent = new Intent(this, SelectLabelsActivity.class);
                intent.putExtra("TOUGAO_TYPE", this.E);
                startActivityForResult(intent, 20);
                break;
        }
        if (view == this.K || view == this.M) {
            this.S.clearFocus();
            this.S.setFocusableInTouchMode(z);
            an.b((Activity) this);
            o();
            finish();
        } else if (view == this.L || view == this.N) {
            if (an.a(this.p)) {
                int parseInt;
                this.e = this.S.getText().toString().trim();
                this.f = this.V.getText().toString();
                try {
                    parseInt = Integer.parseInt(this.i);
                } catch (Exception e) {
                    aa.e("TougaoActivity", "parse tougao size error");
                }
                if (this.e.length() < parseInt) {
                    this.a = an.a((Activity) this, "投稿内容不能少于" + parseInt + "个字", -1);
                    this.a.show();
                    return;
                } else if (this.E == 10 && TextUtils.isEmpty(this.m)) {
                    this.a = an.a((Activity) this, "请选择一张图片", -1);
                    this.a.show();
                    return;
                } else if (this.E == 0 && TextUtils.isEmpty(this.f)) {
                    this.a = an.a((Activity) this, "连接不能为空", -1);
                    this.a.show();
                    return;
                } else {
                    if (this.m != null) {
                        if (!an.a(this.p)) {
                            an.a(this.A, 1, "tougao", "publish", (int) R$styleable.Theme_Custom_new_item_login_tencent_bg);
                        } else if (this.av) {
                            p();
                        } else {
                            i();
                            return;
                        }
                    }
                    MobclickAgent.onEvent(this, "tougaoSendCount");
                    return;
                }
            }
            an.a(this.A, (int) z, "", "", (int) z);
        } else if (view == this.ae || view == this.ac) {
            MobclickAgent.onEvent((Context) this, "E02-A12", "帮助");
            com.budejie.www.util.a.a(this.A, null, "16529619", z);
        }
    }

    private void o() {
        if (!this.H && this.E == 41 && this.ay != null) {
            try {
                new File(this.ay.getImage()).delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                new File(this.ay.getVideo()).delete();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            o();
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void p() {
        switch (this.E) {
            case 0:
                MobclickAgent.onEvent((Context) this, "E02-A12", "发表");
                break;
            case 10:
                MobclickAgent.onEvent((Context) this, "E02-A10", "发表");
                break;
            case 29:
                MobclickAgent.onEvent((Context) this, "E02-A11", "发表");
                break;
            case 41:
                MobclickAgent.onEvent((Context) this, "E02-A09", "发表");
                break;
        }
        String str = "";
        this.c = ((int) System.currentTimeMillis()) / 100;
        Intent intent;
        if (this.E == 41) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(this.ay.getDuration());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                parseInt = 0;
            }
            com.budejie.www.util.b.a(this.A, a(Integer.parseInt(this.C), new File(this.ay.getImage()), new File(this.ay.getVideo()), 0, parseInt == 0 ? 0 : (int) Math.round(((double) parseInt) / 1000.0d), this.e, str, this.aj, ((BudejieApplication) getApplication()).b, this.ap), this.u, 0);
            intent = new Intent(this, MyPostsActivity.class);
            intent.putExtra("local_file", this.H);
            intent.putExtra("from_tougao_tag", true);
            startActivity(intent);
            this.m = null;
            finish();
        } else if (this.E != 0) {
            com.budejie.www.util.b.a(this.A, a(Integer.parseInt(this.C), this.e, str, this.m, this.aj, ((BudejieApplication) getApplication()).b, this.ap), this.u, this.c);
            intent = new Intent(this, MyPostsActivity.class);
            intent.putExtra("from_tougao_tag", true);
            startActivity(intent);
            this.m = null;
            finish();
        } else if (this.ai) {
            this.ai = false;
            if (x.a(this.A).a(this.f)) {
                t();
                return;
            }
            this.ai = true;
            this.ag = p.a(this.A, getResources().getString(R.string.sd_title), getResources().getString(R.string.tougao_link_dialog_content), getResources().getString(R.string.tougao_link_dialog_rewrite), getResources().getString(R.string.cancel), this.aw);
        }
    }

    public net.tsz.afinal.a.b a(int i, String str, String str2, String str3, String str4, String[] strArr, String str5) {
        Map a = this.ak.a(this.A, new HashMap());
        a.put("format", "json");
        a.put("app", "8");
        a.put(HistoryOpenHelper.COLUMN_UID, String.valueOf(i));
        a.put("content", String.valueOf(str));
        a.put("shareType", str2);
        if (strArr != null) {
            a.put("longitude", strArr[0]);
            a.put("latitude", strArr[1]);
        }
        if (!TextUtils.isEmpty(str4)) {
            a.put("reserve", str4);
        }
        if (!TextUtils.isEmpty(this.at)) {
            a.put("theme_id", this.at);
        } else if (!TextUtils.isEmpty(this.as)) {
            a.put("theme_name", this.as);
        }
        if (this.E == 29) {
            a.put("type", "29");
        } else if (this.E == 10) {
            a.put("type", com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
            if (!(str3 == null || str3.endsWith(".gif"))) {
                if (this.G) {
                    a.put("waterprint", "1");
                } else {
                    a.put("waterprint", "0");
                }
            }
        }
        aa.b("stickerIds", "stickerIds=" + this.I);
        a.put("sticker", this.I);
        this.u = com.budejie.www.util.i.a().d();
        if (this.E == 0) {
            a(i, str, "", this.f);
        } else {
            a(i, str, str3, "");
        }
        a.put("a", "createugc");
        a.put("c", "topic");
        a.put("vote", str5);
        return e.a("/api/api_open.php", a, str3);
    }

    private void a(int i, String str, String str2, String str3) {
        int i2 = 0;
        if (!TextUtils.isEmpty(str3)) {
            i2 = 1;
        }
        DraftBean draftBean = new DraftBean(0, i, i2, str2 == null ? null : str2, null, str, 0, 0, this.u, this.aj, null, 0, this.G);
        if (!TextUtils.isEmpty(str2)) {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str2, options);
            options.inJustDecodeBounds = false;
            draftBean.setWidth(String.valueOf(options.outWidth));
            draftBean.setHeight(String.valueOf(options.outHeight));
        }
        draftBean.setLinkurl(str3);
        draftBean.voteDataStr = this.ap;
        Object arrayList = new ArrayList();
        PlateBean plateBean = new PlateBean();
        plateBean.theme_id = this.at;
        plateBean.theme_name = this.as;
        arrayList.add(plateBean);
        draftBean.plateDataStr = new Gson().toJson(arrayList);
        this.t.a(draftBean);
    }

    public net.tsz.afinal.a.b a(int i, File file, File file2, int i2, int i3, String str, String str2, String str3, String[] strArr, String str4) {
        Map a = this.ak.a(this.A, new HashMap());
        a.put("format", "json");
        a.put(HistoryOpenHelper.COLUMN_UID, String.valueOf(i));
        a.put("bvoiceid", String.valueOf(i2));
        a.put("videotime", String.valueOf(i3));
        a.put("content", String.valueOf(str));
        a.put("shareType", str2);
        if (strArr != null) {
            a.put("longitude", strArr[0]);
            a.put("latitude", strArr[1]);
        }
        if (!TextUtils.isEmpty(str3)) {
            a.put("reserve", str3);
        }
        Map map;
        if (!TextUtils.isEmpty(this.at)) {
            map = a;
            map.put("theme_id", this.at);
        } else if (!TextUtils.isEmpty(this.as)) {
            map = a;
            map.put("theme_name", this.as);
        }
        a.put("type", "41");
        this.u = com.budejie.www.util.i.a().d();
        DraftBean draftBean = new DraftBean(0, i, 0, file.getAbsolutePath(), null, str, i2, 0, this.u, this.aj, file2.getAbsolutePath(), i3, false);
        draftBean.setWidth(this.ay.getWidth());
        draftBean.setHeight(this.ay.getHeight());
        draftBean.voteDataStr = this.ap;
        Object arrayList = new ArrayList();
        PlateBean plateBean = new PlateBean();
        plateBean.theme_id = this.at;
        plateBean.theme_name = this.as;
        arrayList.add(plateBean);
        draftBean.plateDataStr = new Gson().toJson(arrayList);
        this.t.a(draftBean);
        a.put("a", "createugc");
        a.put("c", "topic");
        a.put("vote", str4);
        return e.b("/api/api_open.php", a, file, file2);
    }

    private void q() {
        this.r.schedule(this.w, 3000);
    }

    public void picUploadBtn$Click(View view) {
        MobclickAgent.onEvent(this, "tougaoPicCount");
        registerForContextMenu(this.R);
        openContextMenu(this.R);
    }

    public void a() {
        try {
            Intent intent = new Intent(this, SelectImageActivity.class);
            intent.putExtra("source", "TougaoActivity");
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            an.a(this.A, getString(R.string.no_available_album), -1).show();
        }
    }

    public void b() {
        if (com.bdj.picture.edit.util.a.a()) {
            Intent intent = new Intent(this, CaptureActivity.class);
            this.v = new File(Environment.getExternalStorageDirectory(), com.budejie.www.util.i.a().d() + ".jpg").getAbsolutePath();
            intent.putExtra("output", this.v);
            try {
                startActivityForResult(intent, 716);
                return;
            } catch (Exception e) {
                this.a = an.a(this.A, this.A.getString(R.string.no_camera), -1);
                this.a.show();
                return;
            }
        }
        this.a = an.a(this.A, this.A.getString(R.string.no_sdcard), -1);
        this.a.show();
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        if (view == this.R) {
            if (TextUtils.isEmpty(this.m)) {
                contextMenu.setHeaderTitle(getString(R.string.tougao_upload_pic));
            } else {
                contextMenu.setHeaderTitle(getString(R.string.tougao_modify_pic));
                contextMenu.add(0, 3, 0, getString(R.string.tougao_delete_pic));
            }
            contextMenu.add(0, 1, 0, getString(R.string.tougao_from_camera));
            contextMenu.add(0, 2, 0, getString(R.string.tougao_from_album));
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 3) {
            this.R.setImageResource(R.drawable.publishposts_pic_selector);
            this.m = "";
        } else if (itemId == 1 || itemId == 4) {
            b();
        } else if (itemId == 2 || itemId == 5) {
            a();
        }
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.C = this.p.getString("id", "");
        if (i2 == -1) {
            if (i == 1) {
                if (TextUtils.isEmpty(intent.getStringExtra("filepath"))) {
                    this.an = (ArrayList) intent.getSerializableExtra("MultipleImgPath");
                    e(intent.getStringExtra("imgPath"));
                    return;
                }
                e(intent.getStringExtra("filepath"));
            } else if (i == 716) {
                if (!TextUtils.isEmpty(intent.getStringExtra("filepath"))) {
                    this.v = intent.getStringExtra("filepath");
                }
                this.an = (ArrayList) intent.getSerializableExtra("MultipleImgPath");
                e(this.v);
            } else if (i == 720) {
                r0 = intent.getStringExtra("imagePath");
                if (r0 != null && r0.endsWith("gif")) {
                    a(r0);
                } else if (intent.getBooleanExtra("islarge_length_image", false)) {
                    a(r0);
                } else {
                    b(r0);
                }
            } else if (i == 7201) {
                r0 = intent.getStringExtra("imagePath");
                this.I = intent.getStringExtra("stickerIds");
                a(r0);
            } else if (i == 435 || i == 436) {
                r0 = intent.getStringExtra(getString(R.string.RESPONE_RESULT_CONTACT_NAME));
                if (!TextUtils.isEmpty(r0)) {
                    CharSequence append;
                    if (i == 436) {
                        append = new StringBuilder("").append(r0).append(" ");
                    } else {
                        append = new StringBuilder("@").append(r0).append(" ");
                    }
                    this.S.getEditableText().insert(this.S.getSelectionStart(), append);
                }
            }
        } else if (i2 == 7203 && i == 7201) {
            this.an = (ArrayList) intent.getSerializableExtra("MultipleImgPath");
            b(intent.getStringExtra("imagePath"));
        } else if (i2 == 7202) {
            finish();
        } else if (i2 == 711) {
            bindTencent();
        } else if (i2 == 0) {
            if (this.B.isShowing()) {
                this.B.dismiss();
            }
        } else if (i2 == R$styleable.Theme_Custom_new_item_login_tencent_bg) {
        } else {
            if (i2 == 10) {
                this.ap = intent.getStringExtra("vote_data_key");
                if (!TextUtils.isEmpty(this.ap)) {
                    r();
                }
            } else if (i == 10 && i2 == 11) {
                this.as = intent.getStringExtra("selectLabelNameTag");
                this.at = intent.getStringExtra("selectLabelThemeIdTag");
                this.aq.setLabelName(this.as);
                this.ar.setLabelName(this.as);
                this.av = true;
            }
        }
    }

    private void r() {
        this.ao.a();
        String[] split = this.ap.split(",");
        ArrayList arrayList = new ArrayList();
        for (String str : split) {
            Vote vote = new Vote();
            vote.vid = "0";
            vote.name = str;
            arrayList.add(vote);
        }
        VoteData voteData = new VoteData();
        voteData.votes = arrayList;
        this.ao.a(voteData, true);
        this.ao.setVisibility(0);
    }

    public void a(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                f(str);
                i.a(getString(R.string.track_action_send_picture), getString(R.string.track_event_picture_ready));
            }
        } catch (OutOfMemoryError e) {
            this.a = an.a((Activity) this, getString(R.string.tougao_pic_too_big), -1);
            this.a.show();
            aa.e("TougaoActivity", "OutOfMemoryError");
        } catch (Exception e2) {
            aa.e("TougaoActivity", "Exception ," + e2.toString());
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onComplete(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        super.onComplete(jSONObject);
        HashMap a = z.a(jSONObject);
        if (a != null && a.size() != 0) {
            this.p.edit().putString("openid", (String) a.get("qzone_uid")).putString("qzone_token", (String) a.get("qzone_token")).putString(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN, (System.currentTimeMillis() + (Long.parseLong((String) a.get(com.tencent.connect.common.Constants.PARAM_EXPIRES_IN)) * 1000)) + "").commit();
            this.C = this.p.getString("id", "");
            this.o.a((String) a.get("qzone_uid"), this.C, (String) a.get("qzone_token"), 10, this.z);
        }
    }

    public void onError(UiError uiError) {
        super.onError(uiError);
        Toast.makeText(this.A, "code:" + uiError.errorCode + ", msg:" + uiError.errorMessage + ", detail:" + uiError.errorDetail, 0).show();
    }

    private void e(String str) {
        if (str.endsWith("gif")) {
            a(str);
        } else if (getIntent().getBooleanExtra("islarge_length_image", false)) {
            a(str);
        } else {
            b(str);
        }
    }

    private void f(String str) {
        if (this.E == 10) {
            this.m = str;
        }
        if (new File(str).exists()) {
            Bitmap b = h.b(str, 100.0f, 50.0f);
            if (b != null) {
                this.m = str;
                aa.a("TougaoActivity", "uploadFilePath = " + this.m);
                this.R.setImageBitmap(b);
                an.a((Activity) this, this.S);
                if (this.b == 0) {
                    this.b++;
                }
            }
        }
    }

    public void onSuccess(com.sina.weibo.sdk.auth.b bVar) {
        try {
            this.C = this.p.getString("id", "");
            mAccessToken = bVar;
            if (mAccessToken.a()) {
                com.sina.weibo.sdk.auth.a.a(this.A, mAccessToken);
                au.a((int) R.string.oauthSuccess);
                this.o.a(mAccessToken, this.C, 4, this.z);
            }
        } catch (Exception e) {
            au.a((int) R.string.sina_shouquan_failed);
        }
    }

    private void s() {
        super.onrefreshTheme();
        this.O.setTextColor(getResources().getColor(com.budejie.www.util.j.b));
        onRefreshTitleFontTheme(this.M, false);
        onRefreshTitleFontTheme(this.N, false);
        this.R.setImageResource(com.budejie.www.util.j.aq);
    }

    public void onCancel() {
        super.onCancel();
        this.B.dismiss();
    }

    public void bindTencent() {
        this.C = this.p.getString("id", "");
        String sharePersistent = Util.getSharePersistent(this.A, "ACCESS_TOKEN");
        this.o.a(Util.getSharePersistent(this.A, "NAME"), sharePersistent, Util.getSharePersistent(this.A, "OPEN_ID"), this.C, 5, this.z);
    }

    private void g(String str) {
        aa.b("TougaoActivity", "parseVideoUrl url = " + str);
        if (!TextUtils.isEmpty(str)) {
            BudejieApplication.a.a(RequstMethod.GET, j.c(), new j().a(this.A, str), this.aA);
        }
    }

    private void t() {
        BudejieApplication.a.a(RequstMethod.POST, j.d(), new j().a(this.A, this.f, this.e, this.at, this.as, this.ap), this.aB);
    }

    public void c() {
        aa.b("TougaoActivity", "showPreview = previewUrl =" + this.h);
        com.budejie.www.adapter.b.a.a(this.ab, com.bdj.picture.edit.util.a.a(this.A), com.bdj.picture.edit.util.a.a(this.A));
        k a = k.a(this.A);
        a.getClass();
        k.a aVar = new k.a(a, 0, this.ab);
        ListItemObject listItemObject = new ListItemObject();
        listItemObject.setVideouri(this.h);
        listItemObject.setVideouriBackup(this.h);
        k.a(this.A).a(listItemObject, aVar, null, 1);
    }

    public void d() {
        aa.b("TougaoActivity", "showPreview = previewUrl =" + this.h);
        if (!TextUtils.isEmpty(this.h)) {
            Intent intent = new Intent(this.A, HtmlFeatureActivity.class);
            intent.setData(Uri.parse(this.h));
            this.A.startActivity(intent);
        }
    }
}
