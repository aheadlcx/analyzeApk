package cn.xiaochuankeji.tieba.ui.my.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.LinearLayout.LayoutParams;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.background.modules.a.h;
import cn.xiaochuankeji.tieba.background.utils.g;
import cn.xiaochuankeji.tieba.ui.my.a;
import cn.xiaochuankeji.tieba.ui.utils.e;

public class ModifySignActivity extends a implements TextWatcher, h.a {
    String l;

    public static void a(Activity activity, int i) {
        activity.startActivityForResult(new Intent(activity, ModifySignActivity.class), i);
    }

    protected boolean a(Bundle bundle) {
        this.d = "个人签名";
        this.e = "最长可以输入24个字的个人签名。";
        this.f = "保存";
        return true;
    }

    protected void d_() {
        this.i.setSingleLine(false);
        this.i.setHint("请输入个人签名");
        this.l = cn.xiaochuankeji.tieba.background.a.g().q().getSign();
        if (this.l == null) {
            this.l = "";
        }
        this.i.setText(this.l);
        this.i.setSelection(this.i.length());
        this.i.setGravity(51);
        this.i.setBackgroundResource(R.drawable.edit_round_rect_bg);
        LayoutParams layoutParams = (LayoutParams) this.i.getLayoutParams();
        layoutParams.topMargin = e.a(17.0f);
        layoutParams.height = e.a(73.0f);
        this.i.setLayoutParams(layoutParams);
    }

    protected void onResume() {
        super.onResume();
        this.i.addTextChangedListener(this);
    }

    protected void onPause() {
        super.onPause();
        this.i.removeTextChangedListener(this);
    }

    protected void a(String str) {
        Object trim = str.trim();
        if (b(trim) > 48) {
            g.a("昵称长度最大不能超过20个字符");
        } else if (TextUtils.isEmpty(trim)) {
            g.a("签名不能为空");
        } else {
            cn.xiaochuankeji.tieba.ui.widget.g.a((Activity) this);
            cn.xiaochuankeji.tieba.background.a.h().a(cn.xiaochuankeji.tieba.background.a.g().q().getGender(), trim, 0, this);
        }
    }

    private int b(String str) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        if (str.length() <= 24) {
            return str.length();
        }
        for (char c : str.toCharArray()) {
            if (c <= 'ÿ') {
                i++;
            } else {
                i += 2;
            }
        }
        return i;
    }

    public void a(boolean z, String str) {
        cn.xiaochuankeji.tieba.ui.widget.g.c(this);
        if (z) {
            g.a("签名修改成功");
            setResult(-1);
            finish();
            return;
        }
        g.a(str);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        String obj = editable.toString();
        if (TextUtils.isEmpty(obj) || obj.length() <= 24) {
            this.l = editable.toString();
        } else if (!this.l.equalsIgnoreCase(obj)) {
            if (b(obj) <= 48) {
                this.l = editable.toString();
            } else if (obj.length() < this.l.length()) {
                this.l = editable.toString();
            } else {
                this.i.setText(this.l);
                this.i.setSelection(this.l.length());
            }
        }
    }
}
