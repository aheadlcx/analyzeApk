package cn.xiaochuankeji.tieba.ui.my;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.base.h;
import cn.xiaochuankeji.tieba.ui.widget.NavigationBar;

public abstract class a extends h implements OnClickListener {
    protected String d;
    protected String e;
    protected String f;
    protected String g;
    protected NavigationBar h;
    protected EditText i;
    protected TextView j;
    protected Button k;

    protected abstract void a(String str);

    protected abstract void d_();

    protected int a() {
        return R.layout.activity_edit_text;
    }

    protected void c() {
        this.h = (NavigationBar) findViewById(R.id.navBar);
        this.i = (EditText) findViewById(R.id.etInput);
        this.j = (TextView) findViewById(R.id.tvDesc);
        this.k = (Button) findViewById(R.id.bnNext);
    }

    protected void i_() {
        this.h.setTitle(this.d);
        this.i.setHint(this.g);
        this.j.setText(this.e);
        this.k.setText(this.f);
        d_();
        this.i.setSelection(this.i.getText().length());
    }

    protected void j_() {
        this.k.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnNext:
                String j = j();
                cn.htjyb.c.a.a((Activity) this);
                a(j);
                return;
            default:
                return;
        }
    }

    protected String j() {
        return this.i.getText().toString();
    }
}
