package cn.xiaochuankeji.tieba.ui.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class j {
    private final Context a;
    private final View b;

    protected abstract View a(LayoutInflater layoutInflater);

    protected abstract void a(View view);

    protected j(Context context) {
        this.a = context;
        this.b = a(LayoutInflater.from(context));
        a(this.b);
    }

    protected Context e_() {
        return this.a;
    }

    public View f_() {
        return this.b;
    }
}
