package cn.xiaochuankeji.tieba.ui.member;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.b.b;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.net.a;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.base.h;
import org.json.JSONObject;

public class UserAllegeActivity extends h {
    private EditText d;
    private Button e;
    private long f;
    private long g;

    public static void a(Context context, long j, long j2) {
        Intent intent = new Intent(context, UserAllegeActivity.class);
        intent.putExtra("mid", j);
        intent.putExtra("tid", j2);
        context.startActivity(intent);
    }

    protected int a() {
        return R.layout.user_allege_activity;
    }

    protected boolean a(Bundle bundle) {
        this.f = getIntent().getLongExtra("mid", 0);
        this.g = getIntent().getLongExtra("tid", 0);
        return true;
    }

    protected void i_() {
        this.d = (EditText) findViewById(R.id.text_allege_content);
        this.e = (Button) findViewById(R.id.btn_submit_allege);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserAllegeActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String obj = this.a.d.getText().toString();
                if (TextUtils.isEmpty(obj.trim())) {
                    g.a("请填写申诉理由");
                    return;
                }
                new b(this.a.g, this.a.f, "black_complain", 0, obj.trim(), new a.b<JSONObject>(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onResponse(Object obj, Object obj2) {
                        a((JSONObject) obj, obj2);
                    }

                    public void a(JSONObject jSONObject, Object obj) {
                        g.a("提交申诉成功");
                        cn.htjyb.c.a.a(this.a.a, this.a.a.d);
                        this.a.a.finish();
                    }
                }, new a.a(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onErrorResponse(XCError xCError, Object obj) {
                        g.a(xCError.getMessage());
                    }
                }).execute();
            }
        });
    }
}
