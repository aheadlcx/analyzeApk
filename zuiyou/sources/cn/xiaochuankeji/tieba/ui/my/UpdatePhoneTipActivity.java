package cn.xiaochuankeji.tieba.ui.my;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.modules.a.a;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.my.account.InputPhoneNumberActivity;

public class UpdatePhoneTipActivity extends h {
    private TextView d;
    private Button e;

    public static void a(Context context) {
        context.startActivity(new Intent(context, UpdatePhoneTipActivity.class));
    }

    protected int a() {
        return R.layout.activity_update_bind_phone;
    }

    protected void i_() {
        this.d = (TextView) findViewById(R.id.tv_phone_number);
        this.e = (Button) findViewById(R.id.btn_update_phone);
        a g = cn.xiaochuankeji.tieba.background.a.g();
        if (!TextUtils.isEmpty(g.q().getPhone())) {
            this.d.setText(g.q().getDisplayPhone());
        }
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UpdatePhoneTipActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                InputPhoneNumberActivity.a(this.a, 44, "bind");
            }
        });
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        setResult(i2);
        finish();
    }
}
