package com.flyco.dialog.c.b;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class a<T extends a<T>> extends com.flyco.dialog.c.a.a<T> {
    protected String A = "取消";
    protected String B = "确定";
    protected String C = "继续";
    protected int D;
    protected int E;
    protected int F;
    protected float G = 15.0f;
    protected float H = 15.0f;
    protected float I = 15.0f;
    protected int J = Color.parseColor("#E3E3E3");
    protected com.flyco.dialog.a.a K;
    protected com.flyco.dialog.a.a L;
    protected com.flyco.dialog.a.a M;
    protected float N = 3.0f;
    protected int O = Color.parseColor("#ffffff");
    protected LinearLayout a;
    protected TextView l;
    protected String m;
    protected int n;
    protected float o;
    protected boolean p = true;
    protected TextView q;
    protected String r;
    protected int s = 16;
    protected int t;
    protected float u;
    protected int v = 2;
    protected LinearLayout w;
    protected TextView x;
    protected TextView y;
    protected TextView z;

    public a(Context context) {
        super(context);
        a(0.88f);
        this.a = new LinearLayout(context);
        this.a.setOrientation(1);
        this.l = new TextView(context);
        this.q = new TextView(context);
        this.w = new LinearLayout(context);
        this.w.setOrientation(0);
        this.x = new TextView(context);
        this.x.setGravity(17);
        this.z = new TextView(context);
        this.z.setGravity(17);
        this.y = new TextView(context);
        this.y.setGravity(17);
    }

    public void b() {
        this.l.setVisibility(this.p ? 0 : 8);
        this.l.setText(TextUtils.isEmpty(this.m) ? "温馨提示" : this.m);
        this.l.setTextColor(this.n);
        this.l.setTextSize(2, this.o);
        this.q.setGravity(this.s);
        this.q.setText(this.r);
        this.q.setTextColor(this.t);
        this.q.setTextSize(2, this.u);
        this.q.setLineSpacing(0.0f, 1.3f);
        this.x.setText(this.A);
        this.y.setText(this.B);
        this.z.setText(this.C);
        this.x.setTextColor(this.D);
        this.y.setTextColor(this.E);
        this.z.setTextColor(this.F);
        this.x.setTextSize(2, this.G);
        this.y.setTextSize(2, this.H);
        this.z.setTextSize(2, this.I);
        if (this.v == 1) {
            this.x.setVisibility(8);
            this.y.setVisibility(8);
        } else if (this.v == 2) {
            this.z.setVisibility(8);
        }
        this.x.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.K != null) {
                    this.a.K.a();
                } else {
                    this.a.dismiss();
                }
            }
        });
        this.y.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.L != null) {
                    this.a.L.a();
                } else {
                    this.a.dismiss();
                }
            }
        });
        this.z.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.M != null) {
                    this.a.M.a();
                } else {
                    this.a.dismiss();
                }
            }
        });
    }

    public T a(String str) {
        this.m = str;
        return this;
    }

    public T b(boolean z) {
        this.p = z;
        return this;
    }

    public T b(String str) {
        this.r = str;
        return this;
    }

    public T b(int i) {
        this.s = i;
        return this;
    }

    public T c(int i) {
        if (i < 1 || i > 3) {
            throw new IllegalStateException("btnNum is [1,3]!");
        }
        this.v = i;
        return this;
    }

    public T a(String... strArr) {
        if (strArr.length < 1 || strArr.length > 3) {
            throw new IllegalStateException(" range of param btnTexts length is [1,3]!");
        }
        if (strArr.length == 1) {
            this.C = strArr[0];
        } else if (strArr.length == 2) {
            this.A = strArr[0];
            this.B = strArr[1];
        } else if (strArr.length == 3) {
            this.A = strArr[0];
            this.B = strArr[1];
            this.C = strArr[2];
        }
        return this;
    }

    public void a(com.flyco.dialog.a.a... aVarArr) {
        if (aVarArr.length < 1 || aVarArr.length > 3) {
            throw new IllegalStateException(" range of param onBtnClickLs length is [1,3]!");
        } else if (aVarArr.length == 1) {
            this.M = aVarArr[0];
        } else if (aVarArr.length == 2) {
            this.K = aVarArr[0];
            this.L = aVarArr[1];
        } else if (aVarArr.length == 3) {
            this.K = aVarArr[0];
            this.L = aVarArr[1];
            this.M = aVarArr[2];
        }
    }
}
