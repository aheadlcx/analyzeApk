package cn.xiaochuankeji.tieba.ui.hollow.edit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.q;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.e;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.json.hollow.NickNameListJson.NickName;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer;
import cn.xiaochuankeji.tieba.ui.hollow.util.IAudioPlayer.PlayerStatus;
import cn.xiaochuankeji.tieba.ui.hollow.widget.AudioPlayView;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;
import com.jakewharton.a.b.a;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.Orientation;
import java.util.concurrent.TimeUnit;
import rx.b.b;

public class EditHollowActivity extends h implements b {
    private a d;
    private a e;
    private AudioPublishModel f;
    private int g;
    private PlayerStatus h;
    private IAudioPlayer i;
    private String j;
    private long k;
    private AudioPlayView l;
    private TextView m;
    private EditText n;
    private View o;
    private View p;
    private NickName q;
    private String r;
    private boolean s;

    public static void a(Activity activity, String str, long j) {
        Intent intent = new Intent(activity, EditHollowActivity.class);
        intent.putExtra("audioDuration", j);
        intent.putExtra("audioPath", str);
        activity.startActivityForResult(intent, 0);
    }

    protected int a() {
        return R.layout.activity_edit_publish;
    }

    protected void i_() {
        j();
        k();
        v();
        w();
        x();
        C();
        y();
        z();
        B();
    }

    private void j() {
        this.k = getIntent().getLongExtra("audioDuration", 0);
        this.j = getIntent().getStringExtra("audioPath");
        this.s = this.j != null;
    }

    private void k() {
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navBar);
        navigationBar.a(-1285, 0, 0);
        navigationBar.setOptionText("发表");
        navigationBar.getOptionText().setTextColor(-686198);
    }

    private void v() {
        this.m = (TextView) findViewById(R.id.tv_nickname);
        a.a(findViewById(R.id.btn_change_name)).d(200, TimeUnit.MILLISECONDS).a(rx.a.b.a.a()).a(new b<Void>(this) {
            final /* synthetic */ EditHollowActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ void call(Object obj) {
                a((Void) obj);
            }

            public void a(Void voidR) {
                NickName b = this.a.f.b();
                if (b == null) {
                    this.a.f.a(this.a);
                    g.a("正在生成花名，请等待");
                    return;
                }
                this.a.b(b);
            }
        });
    }

    @SuppressLint({"SetTextI18n"})
    private void w() {
        final int i = this.s ? 45 : 200;
        final TextView textView = (TextView) findViewById(R.id.edit_warn_info);
        this.n = (EditText) findViewById(R.id.tree_publish_edit);
        textView.setText("0/" + i);
        this.n.setFilters(new InputFilter[]{new LengthFilter(i)});
        this.n.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ EditHollowActivity c;

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() <= i) {
                    textView.setText(charSequence.length() + "/" + i);
                } else {
                    g.a("树洞主题过长");
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.n.setOnEditorActionListener(new OnEditorActionListener(this) {
            final /* synthetic */ EditHollowActivity a;

            {
                this.a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return i == 1 || i == 0;
            }
        });
        findViewById(R.id.tree_publish_edit_fun).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ EditHollowActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                cn.htjyb.c.a.a(this.a.n, this.a);
            }
        });
    }

    private void x() {
        DiscreteScrollView discreteScrollView = (DiscreteScrollView) findViewById(R.id.tree_publish_emotion);
        this.e = new a(this, discreteScrollView);
        discreteScrollView.setSlideOnFling(true);
        discreteScrollView.setAdapter(this.e);
        discreteScrollView.setOrientation(Orientation.HORIZONTAL);
        discreteScrollView.setItemTransformer(new com.yarolegovich.discretescrollview.transform.b.a().a(0.6f).a());
        discreteScrollView.setItemTransitionTimeMillis(100);
        discreteScrollView.a(new DiscreteScrollView.a<a>(this) {
            final /* synthetic */ EditHollowActivity a;

            {
                this.a = r1;
            }

            public void a(@Nullable a aVar, int i) {
                com.izuiyou.a.a.b.c("AdapterPosition -> " + i);
                if (this.a.d != null) {
                    this.a.d.a(false);
                }
                if (aVar != null) {
                    aVar.a(true);
                    this.a.d = aVar;
                    this.a.g = i;
                }
            }
        });
        discreteScrollView.scrollToPosition(2);
    }

    @SuppressLint({"SetTextI18n"})
    private void y() {
        this.l = (AudioPlayView) findViewById(R.id.tree_publish_play_view);
        if (this.j == null || this.j.isEmpty()) {
            this.l.setVisibility(8);
            return;
        }
        this.l.setPlayDuration(this.k);
        this.l.a(false, 1.0f, 1.0f);
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ EditHollowActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.A();
            }
        });
        cn.xiaochuankeji.tieba.background.a.p().d().execute(new Runnable(this) {
            final /* synthetic */ EditHollowActivity a;

            {
                this.a = r1;
            }

            public void run() {
                e.a().b(new e.a(this) {
                    final /* synthetic */ AnonymousClass9 a;

                    {
                        this.a = r1;
                    }

                    public void a(String str, String str2) {
                        this.a.a.r = str2;
                    }

                    public void a(int i, String str) {
                    }
                });
                e.a().a(this.a.j);
            }
        });
    }

    private void z() {
        this.o = findViewById(R.id.tree_publish_refresh);
        this.p = findViewById(R.id.tree_publish_refresh_fun);
        this.o.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ EditHollowActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.D();
            }
        });
    }

    private void A() {
        switch (this.h) {
            case PREPARE:
            case END:
                this.i.a();
                return;
            case LOADING:
            case PLAYING:
                this.i.b();
                return;
            case PAUSE:
                this.i.c();
                return;
            default:
                return;
        }
    }

    private void B() {
        if (this.s) {
            this.i = new cn.xiaochuankeji.tieba.ui.hollow.util.a(this);
            this.i.a(this.j, this.k, new IAudioPlayer.a(this) {
                final /* synthetic */ EditHollowActivity a;

                {
                    this.a = r1;
                }

                public void a(PlayerStatus playerStatus) {
                    com.izuiyou.a.a.b.c("playerStatus : " + playerStatus);
                    this.a.h = playerStatus;
                    switch (playerStatus) {
                        case END:
                            this.a.l.c();
                            return;
                        case LOADING:
                            this.a.l.a();
                            return;
                        case PLAYING:
                            this.a.l.b();
                            return;
                        case PAUSE:
                            this.a.l.d();
                            return;
                        default:
                            return;
                    }
                }

                public void a(final long j) {
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass11 b;

                        public void run() {
                            com.izuiyou.a.a.b.c("refreshPlayTime -> remainTime : " + j);
                            this.b.a.l.a(j);
                        }
                    });
                }
            });
        }
    }

    private void C() {
        this.f = (AudioPublishModel) q.a((FragmentActivity) this).a(AudioPublishModel.class);
        this.f.a(this.e, (Activity) this);
        this.f.a((b) this);
        D();
    }

    private void D() {
        this.f.a(new a(this) {
            final /* synthetic */ EditHollowActivity a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.o.setVisibility(8);
                this.a.p.setVisibility(0);
            }

            public void b() {
                this.a.o.setVisibility(0);
                this.a.p.setVisibility(8);
            }
        });
    }

    public void s() {
        long a = this.e.a(this.g);
        if (this.n.getText().length() < 5) {
            String str;
            if (this.s) {
                str = "主题字数过少（最少5个字）";
            } else {
                str = "据说走心的树洞倾诉都多余5个字";
            }
            g.a(str);
        } else if (a == -1) {
            g.a("表情信息获取失败");
        } else if (this.q == null || this.q.nameId <= 0) {
            g.a("未获取到花名，请重试");
        } else if (this.s) {
            this.f.a(this.n.getText().toString(), this.j, this.e.a(this.g), this.k, this.q.nameId, this.r);
            cn.htjyb.c.a.a((Activity) this);
        } else {
            this.f.a(this.n.getText().toString(), this.e.a(this.g), this.q.nameId);
        }
    }

    public boolean h() {
        return false;
    }

    protected void onPause() {
        super.onPause();
        if (this.h != null && this.h == PlayerStatus.PLAYING) {
            A();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.i != null) {
            this.i.e();
        }
    }

    @SuppressLint({"SetTextI18n"})
    public void b(NickName nickName) {
        if (nickName == null) {
            g.a("没有获取到花名，请点击重新获取");
            return;
        }
        this.q = nickName;
        this.m.setText("花名：" + nickName.name);
    }

    public void a(NickName nickName) {
        b(nickName);
    }
}
