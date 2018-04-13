package com.budejie.www.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bdj.picture.edit.EditPictureActivity;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.image.BitmapCache;
import com.budejie.www.activity.image.BitmapCache.a;
import com.budejie.www.activity.image.SelectImageActivity;
import com.budejie.www.bean.CommentItem;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.UserItem;
import com.budejie.www.bean.Vote;
import com.budejie.www.bean.VoteData;
import com.budejie.www.busevent.UpdateCommentAction;
import com.budejie.www.c.e;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil;
import com.budejie.www.http.i;
import com.budejie.www.http.j;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import com.budejie.www.util.aq;
import com.budejie.www.util.p;
import com.budejie.www.util.s;
import com.budejie.www.util.v;
import com.budejie.www.util.z;
import com.budejie.www.widget.VoteView;
import com.budejie.www.widget.VoteView.b;
import com.budejie.www.widget.VoteView.c;
import com.budejie.www.widget.f;
import com.budejie.www.widget.parsetagview.ParseTagEditText;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.smtt.sdk.WebView;
import com.umeng.onlineconfig.OnlineConfigAgent;
import de.greenrobot.event.EventBus;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;

public class PublishCommentActivity extends OauthWeiboBaseAct implements OnClickListener {
    private VoteView A;
    private boolean B;
    private int C;
    private TextWatcher D = new TextWatcher(this) {
        final /* synthetic */ PublishCommentActivity a;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.a.u = charSequence.toString();
            if (!TextUtils.isEmpty(this.a.u)) {
                this.a.u = this.a.u.trim();
            }
            this.a.m();
        }

        public void afterTextChanged(Editable editable) {
        }
    };
    a a = new a(this) {
        final /* synthetic */ PublishCommentActivity a;

        {
            this.a = r1;
        }

        public void a(ImageView imageView, Bitmap bitmap, Object... objArr) {
            if (imageView != null && bitmap != null) {
                String str = (String) objArr[0];
                if (str != null && str.equals(imageView.getTag())) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    };
    private TextView b;
    private ImageView c;
    private ImageView d;
    private ImageView e;
    private ImageView f;
    private ParseTagEditText g;
    private RelativeLayout h;
    private ListItemObject i;
    private String j;
    private SharedPreferences k;
    private PublishCommentActivity l;
    private ImageView m;
    private m n;
    private File o;
    private File p;
    private File q;
    private String r;
    private BitmapCache s;
    private int t;
    private String u;
    private e v;
    private f w;
    private CommentItem x;
    private j y;
    private RelativeLayout z;

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_publish_comment);
        aq.a(this, R.color.black);
        this.l = this;
        c();
        a();
    }

    private void a() {
        b();
        this.k = getSharedPreferences("weiboprefer", 0);
        this.n = new m(this.l);
        this.v = new e(this.l);
        this.y = new j(this.l);
        Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this.l, "send_comment_level");
        if (!TextUtils.isEmpty(configParams)) {
            try {
                this.C = Integer.parseInt(configParams);
            } catch (NumberFormatException e) {
                this.C = 1;
            }
        }
    }

    private void b() {
        Intent intent = getIntent();
        if (intent != null) {
            CharSequence charSequence;
            this.i = (ListItemObject) intent.getSerializableExtra("PublishTag");
            this.x = (CommentItem) intent.getSerializableExtra("ReplyCommentTag");
            ParseTagEditText parseTagEditText = this.g;
            if (this.x == null) {
                charSequence = "写评论...";
            } else {
                charSequence = "回复: " + this.x.getUname();
            }
            parseTagEditText.setHint(charSequence);
            this.f.setVisibility(o() ? 0 : 8);
        }
    }

    private void c() {
        e();
        d();
    }

    private void d() {
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.g.setListener(new com.budejie.www.widget.parsetagview.a(this));
        this.g.setTextChangedListener(this.D);
        this.h.setOnClickListener(this);
        this.A.setCancelListener(new b(this) {
            final /* synthetic */ PublishCommentActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.r = "";
                this.a.B = false;
                this.a.m();
            }
        });
        this.A.setItemClickListener(new c(this) {
            final /* synthetic */ PublishCommentActivity a;

            {
                this.a = r1;
            }

            public void a(VoteData voteData, Vote vote) {
                this.a.l();
            }
        });
    }

    private void e() {
        this.b = (TextView) findViewById(R.id.cancel_text_view);
        this.c = (ImageView) findViewById(R.id.send_image_view);
        this.d = (ImageView) findViewById(R.id.album_image_view);
        this.e = (ImageView) findViewById(R.id.vote_image_view);
        this.f = (ImageView) findViewById(R.id.dub_image_view);
        this.m = (ImageView) findViewById(R.id.preview_image_view);
        this.g = (ParseTagEditText) findViewById(R.id.comment_edit_text);
        this.g.setFilters(new InputFilter[]{new s()});
        this.h = (RelativeLayout) findViewById(R.id.edit_layout);
        this.z = (RelativeLayout) findViewById(R.id.preview_layout);
        findViewById(R.id.delete_image_view).setOnClickListener(this);
        this.A = (VoteView) findViewById(R.id.vote_view);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_text_view:
                an.b(this.l);
                finish();
                return;
            case R.id.send_image_view:
                this.j = this.k.getString("id", "");
                if (TextUtils.isEmpty(this.j)) {
                    an.a(this.l, 0, null, null, 0);
                    return;
                } else {
                    i();
                    return;
                }
            case R.id.album_image_view:
                g();
                return;
            case R.id.vote_image_view:
                l();
                return;
            case R.id.dub_image_view:
                k();
                return;
            case R.id.edit_layout:
                an.a((Activity) this, this.g);
                return;
            case R.id.delete_image_view:
                f();
                return;
            default:
                return;
        }
    }

    private void f() {
        this.p = null;
        this.o = null;
        this.q = null;
        this.z.setVisibility(8);
        m();
    }

    private void g() {
        if (h()) {
            Intent intent = new Intent(this.l, SelectImageActivity.class);
            intent.putExtra("source", "CommendDetail");
            startActivityForResult(intent, 100);
        }
    }

    private boolean h() {
        this.j = this.k.getString("id", "");
        if (TextUtils.isEmpty(this.j)) {
            an.a(this.l, 0, null, null, 0);
            return false;
        }
        UserItem e = this.n.e(this.j);
        if (e == null) {
            return false;
        }
        Object level = e.getLevel();
        if (TextUtils.isEmpty(level)) {
            return false;
        }
        try {
            if (Integer.parseInt(level) >= this.C) {
                return true;
            }
            p.a(this.l, this.l.getString(R.string.send_media_comment_level_message, new Object[]{Integer.valueOf(this.C)}), getString(R.string.send_media_comment_level_ok), null);
            return false;
        } catch (NumberFormatException e2) {
            if (TextUtils.isEmpty(e2.getMessage())) {
                return false;
            }
            aa.e("PublishCommentActivity", e2.getMessage());
            return false;
        }
    }

    private void i() {
        String str;
        if (this.o != null) {
            str = "41";
            this.p = null;
            this.q = null;
            this.t = 0;
        } else if (this.q != null) {
            str = "71";
            this.p = null;
            this.o = this.q;
        } else if (this.p != null) {
            str = Constants.VIA_REPORT_TYPE_SHARE_TO_QQ;
            this.t = 0;
            this.o = null;
            this.q = null;
        } else {
            str = "29";
            if (!this.B) {
                if (TextUtils.isEmpty(this.u)) {
                    Toast.makeText(this.l, R.string.none_comment_content, 0).show();
                    return;
                }
                int length = this.u.length();
                Object configParams = OnlineConfigAgent.getInstance().getConfigParams(this, "comment_size");
                int i = 2;
                if (!TextUtils.isEmpty(configParams)) {
                    i = Integer.parseInt(configParams);
                }
                if (length < i) {
                    b(getString(R.string.commend_limit, new Object[]{Integer.valueOf(i)}));
                    return;
                } else if (length > R$styleable.Theme_Custom_send_btn_text_color) {
                    b(getString(R.string.text_beyond) + (length - 140) + getString(R.string.again_input));
                    return;
                } else {
                    Object obj = "";
                    if (this.i != null) {
                        obj = this.i.getWid();
                    }
                    if (!TextUtils.isEmpty(obj) && this.v.b(obj, this.u)) {
                        b(getString(R.string.commentExist));
                        return;
                    }
                }
            }
            this.t = 0;
        }
        if (this.w == null) {
            this.w = new f(this.l, R.style.dialogTheme);
        }
        this.w.show();
        String str2 = "";
        if (this.x != null) {
            str2 = this.x.getId();
        }
        if (this.i != null) {
            a(this.y.a(this.l, this.i.getWid(), this.j, str2, this.u, str, 0, this.p, null, this.o, this.r, this.t));
            j();
        }
    }

    public void a(net.tsz.afinal.a.b bVar) {
        if (this.i != null) {
            net.tsz.afinal.b bVar2 = new net.tsz.afinal.b(this.l.getApplicationContext(), new v(this.l));
            bVar2.a("User-Agent", new WebView(this.l).getSettings().getUserAgentString() + NetWorkUtil.a());
            bVar2.a("cookie", NetWorkUtil.b(this.l));
            bVar2.a(NetWorkUtil.a(getApplicationContext()));
            StringBuilder append = new StringBuilder().append("http://d.api.budejie.com");
            j jVar = this.y;
            bVar2.b(com.lt.a.a(this.l).a(append.append(j.d(this.i.getWid())).toString()), bVar, new net.tsz.afinal.a.a<String>(this) {
                final /* synthetic */ PublishCommentActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onSuccess(Object obj) {
                    a((String) obj);
                }

                public void a(String str) {
                    this.a.p = null;
                    this.a.t = 0;
                    this.a.o = null;
                    this.a.q = null;
                    this.a.r = "";
                    if (!TextUtils.isEmpty(str)) {
                        Map u = z.u(str);
                        if (u == null) {
                            this.a.a(false, "");
                            return;
                        }
                        if ("1".equals((String) u.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY))) {
                            if (this.a.w != null) {
                                this.a.w.a(true, (String) u.get(SocialConstants.PARAM_APP_DESC));
                            }
                            this.a.a(str);
                            return;
                        }
                        this.a.a(false, (String) u.get(SocialConstants.PARAM_APP_DESC));
                    } else if (this.a.w != null) {
                        this.a.w.a(false, "");
                    }
                }

                public void onFailure(Throwable th, int i, String str) {
                    if (this.a.w != null) {
                        this.a.w.a(false, "");
                    }
                }
            });
        }
    }

    private void a(boolean z, String str) {
        if (this.w != null) {
            this.w.a(z, str);
        }
    }

    private void a(String str) {
        Observable.just(Integer.valueOf(1)).map(new Function<Integer, Boolean>(this) {
            final /* synthetic */ PublishCommentActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ Object apply(@NonNull Object obj) throws Exception {
                return a((Integer) obj);
            }

            public Boolean a(@NonNull Integer num) throws Exception {
                Thread.sleep(500);
                return Boolean.valueOf(true);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Boolean>(this) {
            Disposable a;
            final /* synthetic */ PublishCommentActivity b;

            {
                this.b = r1;
            }

            public /* synthetic */ void onNext(@NonNull Object obj) {
                a((Boolean) obj);
            }

            public void onSubscribe(@NonNull Disposable disposable) {
                this.a = disposable;
            }

            public void a(@NonNull Boolean bool) {
                if (this.b.w != null) {
                    this.b.w.dismiss();
                }
                EventBus.getDefault().post(UpdateCommentAction.UPDATE_COMMENT);
                this.b.finish();
            }

            public void onError(@NonNull Throwable th) {
                this.a.dispose();
            }

            public void onComplete() {
                this.a.dispose();
            }
        });
    }

    private void j() {
        i.a(getString(R.string.track_event_replay_post), j.a(this.i), j.b((Context) this, this.i));
    }

    private void a(StringBuilder stringBuilder) {
        int selectionStart = this.g.getSelectionStart();
        if (selectionStart < 0) {
            selectionStart = 0;
        }
        this.g.getEditableText().insert(selectionStart, stringBuilder);
    }

    private void k() {
        if (h()) {
            Intent intent = new Intent(this.l, GodDubbingActivity.class);
            intent.putExtra("dubbing_key", this.i);
            startActivityForResult(intent, 20);
        }
    }

    private void l() {
        if (h()) {
            Intent intent = new Intent(this.l, AddVoteActivity.class);
            intent.putExtra("vote_data_key", this.r);
            startActivityForResult(intent, 10);
        }
    }

    private void b(String str) {
        an.a(this.l, str, -1).show();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        int i3 = 1;
        if (intent != null) {
            if (i2 == -1) {
                Object stringExtra;
                if (i == 435 || i == 436) {
                    stringExtra = intent.getStringExtra(getString(R.string.RESPONE_RESULT_CONTACT_NAME));
                    if (!TextUtils.isEmpty(stringExtra)) {
                        if (i != 436) {
                            i3 = 0;
                        }
                        a(new StringBuilder(i3 != 0 ? "" : "@").append(stringExtra).append(" "));
                    }
                } else if (i == 475 || i == 476) {
                    stringExtra = intent.getStringExtra(getString(R.string.RESPONE_RESULT_TOPIC_NAME));
                    if (!TextUtils.isEmpty(stringExtra)) {
                        if (i != 476) {
                            i3 = 0;
                        }
                        a(new StringBuilder(i3 != 0 ? "" : "#").append(stringExtra).append("#"));
                    }
                } else if (i == 100) {
                    String stringExtra2 = intent.getStringExtra("type");
                    String stringExtra3 = intent.getStringExtra("imgPath");
                    String stringExtra4 = intent.getStringExtra("thumbnail");
                    ArrayList arrayList = (ArrayList) intent.getSerializableExtra("MultipleImgPath");
                    if ("video".equals(stringExtra2)) {
                        this.o = new File(stringExtra3);
                        a(stringExtra4, stringExtra3);
                    } else if (CheckCodeDO.CHECKCODE_IMAGE_URL_KEY.equals(stringExtra2)) {
                        if (stringExtra3.endsWith("gif")) {
                            this.p = new File(stringExtra3);
                            a(stringExtra4, stringExtra3);
                        } else {
                            a(stringExtra3, arrayList);
                        }
                    }
                } else if (i == 7201) {
                    c(intent.getStringExtra("imagePath"));
                }
            } else if (i2 == 7203 && i == 7201) {
                a(intent.getStringExtra("imagePath"), (ArrayList) intent.getSerializableExtra("MultipleImgPath"));
            } else if (i2 == 3) {
                String stringExtra5 = intent.getStringExtra("VideoPathTag");
                this.t = intent.getIntExtra("VideoTimeTag", 0);
                if (!TextUtils.isEmpty(stringExtra5)) {
                    this.o = null;
                    this.q = new File(stringExtra5);
                    a("", stringExtra5);
                }
            } else if (i2 == 10) {
                this.r = intent.getStringExtra("vote_data_key");
                if (!TextUtils.isEmpty(this.r)) {
                    n();
                }
            }
            m();
        }
    }

    private void m() {
        boolean z = (TextUtils.isEmpty(this.u) && this.p == null && this.o == null && this.q == null && TextUtils.isEmpty(this.r)) ? false : true;
        this.c.setImageResource(z ? R.drawable.publish_comment_send_comment_selector : R.drawable.publish_comment_send_gray_image);
        this.c.setClickable(z);
    }

    private void n() {
        this.B = true;
        this.A.a();
        String[] split = this.r.split(",");
        ArrayList arrayList = new ArrayList();
        for (String str : split) {
            Vote vote = new Vote();
            vote.vid = "0";
            vote.name = str;
            arrayList.add(vote);
        }
        VoteData voteData = new VoteData();
        voteData.votes = arrayList;
        this.A.a(voteData, true);
        this.A.setVisibility(0);
    }

    private boolean o() {
        if (this.i == null) {
            return false;
        }
        String type = this.i.getType();
        if ("41".equals(type)) {
            try {
                if (Integer.parseInt(this.i.getVideotime()) < 60) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                if (!TextUtils.isEmpty(e.getMessage())) {
                    aa.e("PublishCommentActivity", e.getMessage());
                }
                return false;
            }
        }
        int width = this.i.getWidth();
        if (width <= 0 || !Constants.VIA_REPORT_TYPE_SHARE_TO_QQ.equals(type) || this.i.getHeight() / width >= 2) {
            return false;
        }
        return true;
    }

    private void c(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.p = new File(str);
                a("", str);
            }
        } catch (OutOfMemoryError e) {
            b(getString(R.string.tougao_pic_too_big));
        } catch (Exception e2) {
            if (!TextUtils.isEmpty(e2.getMessage())) {
                aa.e("PublishCommentActivity", e2.getMessage());
            }
        }
    }

    private void a(String str, ArrayList<String> arrayList) {
        Intent intent = new Intent(this.l, EditPictureActivity.class);
        intent.putExtra("picture_path_key", str);
        intent.putExtra("source", "TougaoActivity");
        if (arrayList != null) {
            intent.putExtra("MultipleImgPath", arrayList);
        }
        startActivityForResult(intent, 7201);
    }

    private void a(String str, String str2) {
        if (this.s == null) {
            this.s = new BitmapCache();
        }
        this.z.setVisibility(0);
        this.m.setTag(str2);
        this.s.a(this.m, str, str2, this.a);
    }
}
