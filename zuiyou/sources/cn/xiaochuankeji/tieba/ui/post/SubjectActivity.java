package cn.xiaochuankeji.tieba.ui.post;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import c.a.c;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuan.jsbridge.data.JSShare;
import cn.xiaochuan.jsbridge.f;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.api.subject.SubjectJson;
import cn.xiaochuankeji.tieba.background.data.post.Subject;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.background.utils.h;
import cn.xiaochuankeji.tieba.background.utils.share.WebPageShare;
import cn.xiaochuankeji.tieba.network.custom.exception.ClientErrorException;
import cn.xiaochuankeji.tieba.ui.utils.e;
import cn.xiaochuankeji.tieba.webview.AbstractWebActivity;
import com.alibaba.fastjson.JSON;
import java.net.URL;
import java.nio.charset.Charset;
import org.aspectj.a.b.b;
import org.aspectj.lang.a.a;

public class SubjectActivity extends AbstractWebActivity {
    private static final float g = TypedValue.applyDimension(1, 160.0f, BaseApplication.getAppContext().getResources().getDisplayMetrics());
    private static final a i = null;
    f.a a = new f.a(this) {
        final /* synthetic */ SubjectActivity a;

        {
            this.a = r1;
        }

        public void a(View view, int i, int i2, int i3, int i4) {
            int a = c.a.d.a.a.a().a((int) R.color.CB);
            float a2 = this.a.p() / SubjectActivity.g;
            this.a.a(a2, c.e().c());
            if (a2 > 0.9f) {
                this.a.action_bar.setBackgroundColor(a);
            } else {
                this.a.action_bar.setBackgroundColor(Color.argb((int) (a2 * ((float) Color.alpha(a))), Color.red(a), Color.green(a), Color.blue(a)));
            }
        }
    };
    AppCompatImageView b;
    AppCompatImageView c;
    AppCompatImageView d;
    private String h;

    private static void u() {
        b bVar = new b("SubjectActivity.java", SubjectActivity.class);
        i = bVar.a("method-execution", bVar.a("4", "onCreate", "cn.xiaochuankeji.tieba.ui.post.SubjectActivity", "android.os.Bundle", "savedInstanceState", "", "void"), 92);
    }

    static {
        u();
    }

    private void a(float f, boolean z) {
        if ((this.e == null ? 0 : this.e.getProgress()) <= 70 || f <= 0.5f) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
            this.b.setImageResource(z ? R.drawable.night_icon_web_title_selected : R.drawable.icon_web_title_selected);
        }
        AppCompatImageView appCompatImageView = this.d;
        int i = z ? f > 0.5f ? R.drawable.night_icon_web_share_selected : R.drawable.night_icon_web_share : f > 0.5f ? R.drawable.icon_web_share_selected : R.drawable.icon_web_share;
        appCompatImageView.setImageResource(i);
        appCompatImageView = this.c;
        i = z ? f > 0.5f ? R.drawable.night_icon_web_back_selected : R.drawable.night_icon_web_back : f > 0.5f ? R.drawable.icon_web_back_selected : R.drawable.icon_web_back;
        appCompatImageView.setImageResource(i);
        View view = this.divider;
        if (f > 0.9f) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
    }

    static final void a(SubjectActivity subjectActivity, Bundle bundle, org.aspectj.lang.a aVar) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= 23) {
            subjectActivity.getWindow().setFlags(67108864, 67108864);
            subjectActivity.webContainer.setPaddingRelative(0, 0, 0, 0);
            subjectActivity.action_bar.setPadding(0, e.a(24.0f), 0, 0);
        }
    }

    protected void onCreate(Bundle bundle) {
        org.aspectj.lang.a a = b.a(i, this, this, bundle);
        cn.xiaochuankeji.aop.permission.c.c().a(new j(new Object[]{this, bundle, a}).linkClosureAndJoinPoint(69648));
    }

    protected void e() {
        LayoutInflater.from(getApplicationContext()).inflate(R.layout.menu_web_subject_title, this.action_bar, true);
        this.b = (AppCompatImageView) findViewById(R.id.icon_title);
        this.c = (AppCompatImageView) findViewById(R.id.back);
        this.d = (AppCompatImageView) findViewById(R.id.share);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SubjectActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.q();
            }
        });
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SubjectActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.e != null) {
                    this.a.e.a("getShareInfo", null, new com.a.a.a.e(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void a(String str) {
                            final JSShare jSShare = (JSShare) JSON.parseObject(str, JSShare.class);
                            new cn.xiaochuankeji.tieba.ui.utils.a(this.a.a, new cn.xiaochuankeji.tieba.ui.utils.a.a(this) {
                                final /* synthetic */ AnonymousClass1 b;

                                public void a(int i) {
                                    this.b.a.a.a(i, jSShare);
                                    h.a(this.b.a.a.getApplicationContext(), "zy_event_theme", "tag5");
                                }
                            }).a();
                        }
                    });
                }
            }
        });
    }

    protected void a(final cn.xiaochuan.jsbridge.b bVar) {
        r();
        Subject.fetchSubject(bVar.a, new rx.e<SubjectJson>(this) {
            final /* synthetic */ SubjectActivity b;

            public /* synthetic */ void onNext(Object obj) {
                a((SubjectJson) obj);
            }

            public void onCompleted() {
            }

            public void onError(Throwable th) {
                this.b.s();
                if (th instanceof ClientErrorException) {
                    g.a(((ClientErrorException) th).errCode() == -70 ? "专题不存在" : th.getMessage());
                }
            }

            public void a(SubjectJson subjectJson) {
                if (!this.b.isFinishing()) {
                    Subject subject = subjectJson.subject;
                    bVar.a = subject.id;
                    bVar.b = subject.title;
                    bVar.d = subject.cover_id;
                    bVar.c = subject.url;
                    this.b.getIntent().putExtra("web_data", bVar);
                    this.b.b(bVar);
                    if (this.b.e.getOnScrollChangeListener() == null) {
                        this.b.e.a(this.b.a);
                    }
                    this.b.b.setVisibility(0);
                    h.a(this.b.getApplicationContext(), "zy_event_theme", "tag4");
                }
            }
        });
    }

    protected void onResume() {
        if (this.e != null) {
            this.e.a(this.a);
        }
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
        if (this.e != null) {
            this.e.b(this.a);
        }
    }

    protected WebPageShare a(int i, String str, String str2, String str3, String str4) {
        String str5 = null;
        switch (i) {
            case 1:
                str5 = "最右小报";
                break;
            case 2:
                str5 = "最右小报";
                break;
            case 3:
                break;
            case 4:
                str = "【最右小报】" + str;
                break;
            case 5:
                str5 = "最右小报";
                break;
            default:
                str = null;
                break;
        }
        if (!TextUtils.isEmpty(str2)) {
            str5 = str2;
        }
        return new WebPageShare(str, str5, str3, str4);
    }

    protected void j() {
        super.j();
        if (this.e != null) {
            a(p() / g, c.e().c());
            t();
        }
    }

    private void t() {
        if (TextUtils.isEmpty(this.h)) {
            new AsyncTask<Void, Void, String>(this) {
                final /* synthetic */ SubjectActivity a;

                {
                    this.a = r1;
                }

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return a((Void[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    a((String) obj);
                }

                protected String a(Void... voidArr) {
                    try {
                        return "javascript:" + cn.htjyb.c.b.a(new URL(cn.xiaochuankeji.tieba.background.utils.c.a.c().B()).openStream(), Charset.forName("utf-8"));
                    } catch (Exception e) {
                        return "javascript:!function (w) {\n    function wangyiPlay() {\n        document.getElementsByClassName('footer')[0].style.display = \"none\";\n        document.getElementsByClassName('g-talk')[0].style.display = \"none\";\n        var playBtn = document.getElementsByClassName(\"disc\")[0].getElementsByTagName(\"a\")[0];\n        playBtn.className = \"btn f-pa f-vhc j-flag\";\n        playBtn.style.display = \"block\";\n    }\n\n    try {\n        switch (w.location.hostname) {\n            case \"music.163.com\":\n                wangyiPlay();\n                break;\n            default:\n                break;\n        }\n    } catch (e) {\n        console.log(e)\n    }\n}(window);";
                    }
                }

                protected void a(String str) {
                    this.a.h = str;
                    if (this.a.e != null) {
                        this.a.e.loadUrl(str);
                    }
                }
            }.execute(new Void[0]);
        } else if (this.e != null) {
            this.e.loadUrl(this.h);
        }
    }
}
