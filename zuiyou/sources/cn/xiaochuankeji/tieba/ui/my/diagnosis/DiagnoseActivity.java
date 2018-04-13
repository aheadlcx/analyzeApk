package cn.xiaochuankeji.tieba.ui.my.diagnosis;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.monitor.netmonitor.b;
import cn.xiaochuankeji.tieba.background.utils.monitor.netmonitor.g;
import cn.xiaochuankeji.tieba.ui.base.a;
import org.json.JSONObject;

public class DiagnoseActivity extends a implements OnClickListener {
    private String a = "file.izuiyou.com";
    private String b;
    private b c;
    private b d;
    private int e = 1;
    private int f = 0;
    private int g = 0;
    private StringBuilder h;
    private int i;
    private g j;
    private RelativeLayout k;
    private LinearLayout l;
    private ProgressBar m;
    private Button n;
    private Button o;
    private TextView p;
    private TextView q;
    private TextView r;
    private ImageView s;
    private cn.xiaochuankeji.tieba.background.utils.monitor.netmonitor.a t = new cn.xiaochuankeji.tieba.background.utils.monitor.netmonitor.a(this) {
        final /* synthetic */ DiagnoseActivity a;

        {
            this.a = r1;
        }

        public void a(boolean z, String str) {
            if (z) {
                com.izuiyou.a.a.b.e("这次诊断成功:\n" + str);
                this.a.f = this.a.f + 1;
                this.a.h.append(str);
                this.a.h.append("==========\n");
            } else {
                com.izuiyou.a.a.b.e("这次诊断失败");
                this.a.g = this.a.g + 1;
            }
            if (this.a.f + this.a.g != this.a.e) {
                return;
            }
            if (this.a.f > 0) {
                this.a.p();
                return;
            }
            this.a.k();
            this.a.b(false);
        }

        public void a(String str, String str2) {
            if (str2.equals("true")) {
                this.a.u.sendEmptyMessage(1001);
            }
        }
    };
    private Handler u = new Handler(this) {
        final /* synthetic */ DiagnoseActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            int progress;
            if (message.what == 1001) {
                progress = this.a.m.getProgress();
                if (progress < this.a.i - 1) {
                    this.a.m.setProgress(progress + 1);
                }
            } else if (message.what == 1002) {
                progress = this.a.m.getProgress();
                if (progress < this.a.i) {
                    this.a.m.setProgress(progress + 1);
                    this.a.u.sendEmptyMessageDelayed(1002, 50);
                    return;
                }
                this.a.b(true);
            }
        }
    };

    protected boolean a(Bundle bundle) {
        this.j = g.a(this);
        this.h = new StringBuilder();
        this.i = 16;
        this.e = 1;
        return true;
    }

    protected int a() {
        return R.layout.activity_diagnose;
    }

    protected void c() {
        super.c();
        this.k = (RelativeLayout) findViewById(R.id.rlDiagnose);
        this.l = (LinearLayout) findViewById(R.id.llFinish);
        this.m = (ProgressBar) findViewById(R.id.pBar);
        this.n = (Button) findViewById(R.id.bnDiagnose);
        this.o = (Button) findViewById(R.id.bnRetryOrFinish);
        this.p = (TextView) findViewById(R.id.tvDescriptionResult);
        this.q = (TextView) findViewById(R.id.tvDescriptionTop);
        this.r = (TextView) findViewById(R.id.tvDescriptionBottom);
        this.s = (ImageView) findViewById(R.id.ivDiagnoseResult);
    }

    protected void i_() {
        this.m.setMax(this.i);
        this.q.setText("可能由于服务器故障,导致暂时看不了图片");
        this.r.setText("进行网络诊断有助于为你解决问题,诊断时长大约1分钟");
    }

    protected void j_() {
        super.j_();
        this.n.setOnClickListener(this);
        this.o.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnDiagnose:
                e();
                j();
                return;
            case R.id.bnRetryOrFinish:
                if (this.o.getText().equals("重试")) {
                    e();
                    j();
                    return;
                }
                finish();
                return;
            default:
                return;
        }
    }

    private void e() {
        this.l.setVisibility(8);
        this.n.setVisibility(8);
        this.m.setProgress(0);
        this.m.setVisibility(0);
        this.k.setVisibility(0);
        this.q.setText("机器人小右正在玩命检测中,这1分钟你可以想想下一顿吃啥,谢谢");
        this.r.setText("正在诊断网络~~");
    }

    private void j() {
        if (this.e == 1) {
            this.c = new b(this, this.a, this.t);
            this.c.c(new String[0]);
            return;
        }
        this.c = new b(this, this.a, this.t);
        this.c.c(new String[0]);
        this.d = new b(this, this.b, this.t);
        this.d.c(new String[0]);
    }

    private void b(boolean z) {
        this.k.setVisibility(8);
        this.l.setVisibility(0);
        if (z) {
            this.o.setText("完成");
            this.s.setImageResource(R.drawable.img_diagnose_success);
            this.p.setText("检测成功,感谢协助");
            return;
        }
        this.o.setText("重试");
        this.s.setImageResource(R.drawable.img_diagnose_failure);
        this.p.setText("检测失败,请重试");
    }

    public void onBackPressed() {
        super.onBackPressed();
        k();
        com.izuiyou.a.a.b.e("手动返回取消");
    }

    private void k() {
        this.f = 0;
        this.g = 0;
        this.h.delete(0, this.h.length());
        this.j.a();
        if (this.c != null) {
            this.c.a(true);
            this.c = null;
        }
        if (this.d != null) {
            this.d.a(true);
            this.d = null;
        }
    }

    private void p() {
        JSONObject jSONObject = new JSONObject();
        cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
        this.j.a(this.h.toString(), jSONObject, new g.a(this) {
            final /* synthetic */ DiagnoseActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z, String str) {
                if (z) {
                    com.izuiyou.a.a.b.e("文件上传成功");
                    this.a.u.sendEmptyMessage(1002);
                    return;
                }
                com.izuiyou.a.a.b.e("文件上传失败");
                cn.xiaochuankeji.tieba.background.utils.g.a(str);
                this.a.k();
                this.a.b(false);
            }
        });
    }
}
