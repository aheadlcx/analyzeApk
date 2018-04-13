package com.flyco.dialog.c.a;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.flyco.dialog.b.b;

public abstract class a<T extends a<T>> extends Dialog {
    private com.flyco.a.a a;
    protected String b;
    protected Context c;
    protected DisplayMetrics d;
    protected boolean e;
    protected float f;
    protected float g;
    protected LinearLayout h;
    protected LinearLayout i;
    protected View j;
    protected float k;
    private com.flyco.a.a l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private long q;
    private Handler r;

    public abstract View a();

    public abstract void b();

    public a(Context context) {
        super(context);
        this.f = 1.0f;
        this.q = 1500;
        this.r = new Handler(Looper.getMainLooper());
        d();
        this.c = context;
        this.b = getClass().getSimpleName();
        setCanceledOnTouchOutside(true);
        Log.d(this.b, "constructor");
    }

    public a(Context context, boolean z) {
        this(context);
        this.o = z;
    }

    private void d() {
        requestWindowFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().addFlags(2);
    }

    public void d(View view) {
    }

    protected void onCreate(Bundle bundle) {
        Log.d(this.b, "onCreate");
        this.d = this.c.getResources().getDisplayMetrics();
        this.k = (float) (this.d.heightPixels - b.a(this.c));
        this.h = new LinearLayout(this.c);
        this.h.setGravity(17);
        this.i = new LinearLayout(this.c);
        this.i.setOrientation(1);
        this.j = a();
        this.i.addView(this.j);
        this.h.addView(this.i);
        d(this.j);
        if (this.o) {
            setContentView(this.h, new LayoutParams(-2, -2));
        } else {
            setContentView(this.h, new LayoutParams(this.d.widthPixels, (int) this.k));
        }
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.e) {
                    this.a.dismiss();
                }
            }
        });
        this.j.setClickable(true);
    }

    public void onAttachedToWindow() {
        int i;
        int i2 = -2;
        super.onAttachedToWindow();
        Log.d(this.b, "onAttachedToWindow");
        b();
        if (this.f == 0.0f) {
            i = -2;
        } else {
            i = (int) (((float) this.d.widthPixels) * this.f);
        }
        if (this.g != 0.0f) {
            if (this.g == 1.0f) {
                i2 = (int) this.k;
            } else {
                i2 = (int) (this.k * this.g);
            }
        }
        this.i.setLayoutParams(new LinearLayout.LayoutParams(i, i2));
        if (this.a != null) {
            this.a.a(new com.flyco.a.a.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(Animator animator) {
                    this.a.m = true;
                }

                public void b(Animator animator) {
                }

                public void c(Animator animator) {
                    this.a.m = false;
                    this.a.e();
                }

                public void d(Animator animator) {
                    this.a.m = false;
                }
            }).d(this.i);
            return;
        }
        com.flyco.a.a.c(this.i);
        e();
    }

    public void setCanceledOnTouchOutside(boolean z) {
        this.e = z;
        super.setCanceledOnTouchOutside(z);
    }

    public void show() {
        Log.d(this.b, "show");
        super.show();
    }

    protected void onStart() {
        super.onStart();
        Log.d(this.b, "onStart");
    }

    protected void onStop() {
        super.onStop();
        Log.d(this.b, "onStop");
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(this.b, "onDetachedFromWindow");
    }

    public void dismiss() {
        Log.d(this.b, "dismiss");
        if (this.l != null) {
            this.l.a(new com.flyco.a.a.a(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(Animator animator) {
                    this.a.n = true;
                }

                public void b(Animator animator) {
                }

                public void c(Animator animator) {
                    this.a.n = false;
                    this.a.c();
                }

                public void d(Animator animator) {
                    this.a.n = false;
                    this.a.c();
                }
            }).d(this.i);
        } else {
            c();
        }
    }

    public void c() {
        super.dismiss();
    }

    public T a(boolean z) {
        if (z) {
            getWindow().addFlags(2);
        } else {
            getWindow().clearFlags(2);
        }
        return this;
    }

    public T a(float f) {
        this.f = f;
        return this;
    }

    public T b(float f) {
        this.g = f;
        return this;
    }

    public T a(com.flyco.a.a aVar) {
        this.a = aVar;
        return this;
    }

    public T b(com.flyco.a.a aVar) {
        this.l = aVar;
        return this;
    }

    private void e() {
        if (this.p && this.q > 0) {
            this.r.postDelayed(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.dismiss();
                }
            }, this.q);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.n || this.m || this.p) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onBackPressed() {
        if (!this.n && !this.m && !this.p) {
            super.onBackPressed();
        }
    }

    protected int c(float f) {
        return (int) ((this.c.getResources().getDisplayMetrics().density * f) + 0.5f);
    }
}
