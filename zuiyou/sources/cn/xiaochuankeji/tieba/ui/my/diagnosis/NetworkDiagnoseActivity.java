package cn.xiaochuankeji.tieba.ui.my.diagnosis;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuan.base.BaseApplication;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.utils.monitor.netmonitor.g;
import cn.xiaochuankeji.tieba.network.e;
import cn.xiaochuankeji.tieba.ui.base.a;
import cn.xiaochuankeji.tieba.ui.utils.b;
import java.util.Iterator;
import org.json.JSONObject;

public class NetworkDiagnoseActivity extends a {
    b a;
    private TextView b;

    public static void a(Context context) {
        context.startActivity(new Intent(context, NetworkDiagnoseActivity.class));
    }

    protected int a() {
        return R.layout.activity_network_diagnose;
    }

    protected void i_() {
        ButterKnife.a((Activity) this);
        this.b = (TextView) findViewById(R.id.label_log);
        this.b.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.a = b.a();
    }

    @OnClick
    public void back() {
        onBackPressed();
    }

    @OnClick
    public void btnStart(View view) {
        view.setEnabled(false);
        this.a.a(new b.b(this) {
            final /* synthetic */ NetworkDiagnoseActivity a;

            {
                this.a = r1;
            }

            public void a(String str) {
                this.a.b.append(str);
                int lineCount = this.a.b.getLineCount() * this.a.b.getLineHeight();
                if (lineCount > this.a.b.getHeight()) {
                    this.a.b.scrollTo(0, lineCount - this.a.b.getHeight());
                }
            }

            public void b(String str) {
                a("正在上传日志...");
                JSONObject jSONObject = new JSONObject();
                cn.xiaochuankeji.tieba.background.utils.d.a.a(jSONObject);
                StringBuilder stringBuilder = new StringBuilder(this.a.b.getText());
                stringBuilder.append("\n====================");
                Iterator it = e.a().f().iterator();
                while (it.hasNext()) {
                    String str2 = (String) it.next();
                    stringBuilder.append("\n");
                    stringBuilder.append(str2);
                }
                g.a(BaseApplication.getAppContext()).a(stringBuilder.toString(), jSONObject, new g.a(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z, String str) {
                        if (z) {
                            this.a.a("上传成功.");
                        } else {
                            this.a.a("上传失败.");
                        }
                    }
                });
            }
        });
    }

    @OnClick
    public void copy() {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService("clipboard");
        ClipData newPlainText = ClipData.newPlainText("log", this.b.getText().toString());
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(newPlainText);
            cn.xiaochuankeji.tieba.background.utils.g.a("已复制到剪贴板");
        }
    }

    protected void onDestroy() {
        if (this.a != null) {
            this.a.b();
        }
        super.onDestroy();
    }
}
