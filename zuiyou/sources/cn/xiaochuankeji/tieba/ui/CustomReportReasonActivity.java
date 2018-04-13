package cn.xiaochuankeji.tieba.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.LinearLayout.LayoutParams;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.b.b;
import cn.xiaochuankeji.tieba.background.net.XCError;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.my.a;
import cn.xiaochuankeji.tieba.ui.utils.e;
import org.json.JSONObject;

public class CustomReportReasonActivity extends a {
    private long l;
    private long m;
    private int n;
    private String o;

    public static void a(Context context, long j, int i, String str) {
        a(context, 0, j, i, str);
    }

    public static void a(Context context, long j, long j2, int i, String str) {
        Intent intent = new Intent(context, CustomReportReasonActivity.class);
        intent.putExtra("key_id", j2);
        if (0 != j) {
            intent.putExtra("key_parent_id", j);
        }
        intent.putExtra("key_other_key_id", i);
        intent.putExtra("key_type", str);
        context.startActivity(intent);
    }

    protected boolean a(Bundle bundle) {
        this.d = "举报";
        this.f = "举报";
        this.l = getIntent().getLongExtra("key_parent_id", 0);
        this.m = getIntent().getLongExtra("key_id", 0);
        this.o = getIntent().getStringExtra("key_type");
        this.n = getIntent().getIntExtra("key_other_key_id", 0);
        if (this.n == 0) {
            return false;
        }
        return true;
    }

    protected void d_() {
        this.i.setSingleLine(false);
        this.i.setHint("请填写举报原因");
        this.i.setGravity(51);
        this.i.setBackgroundResource(R.drawable.edit_round_rect_bg);
        LayoutParams layoutParams = (LayoutParams) this.i.getLayoutParams();
        layoutParams.topMargin = e.a(17.0f);
        layoutParams.height = e.a(73.0f);
        this.i.setLayoutParams(layoutParams);
    }

    protected void a(String str) {
        Object trim = str.trim();
        if (TextUtils.isEmpty(trim)) {
            g.a("内容不能为空");
        } else {
            new b(this.l, this.m, this.o, this.n, trim, new cn.xiaochuankeji.tieba.background.net.a.b<JSONObject>(this) {
                final /* synthetic */ CustomReportReasonActivity a;

                {
                    this.a = r1;
                }

                public /* synthetic */ void onResponse(Object obj, Object obj2) {
                    a((JSONObject) obj, obj2);
                }

                public void a(JSONObject jSONObject, Object obj) {
                    g.a("举报成功");
                    cn.htjyb.c.a.a(this.a, this.a.i);
                    this.a.finish();
                }
            }, new cn.xiaochuankeji.tieba.background.net.a.a(this) {
                final /* synthetic */ CustomReportReasonActivity a;

                {
                    this.a = r1;
                }

                public void onErrorResponse(XCError xCError, Object obj) {
                    g.a(xCError.getMessage());
                }
            }).execute();
        }
    }
}
