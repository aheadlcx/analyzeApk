package com.budejie.www.activity.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.OauthWeiboBaseAct;
import com.budejie.www.h.c;
import com.budejie.www.util.ai;
import com.budejie.www.widget.NavigationBar;

public class BaseActvityWithLoadDailog extends OauthWeiboBaseAct {
    private Dialog a;
    private TextView b;
    protected NavigationBar g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTheme(c.a().a(ai.a(this)));
    }

    protected Dialog onCreateDialog(int i) {
        switch (i) {
            case 1:
                this.a = new Dialog(this, R.style.dialogTheme);
                this.a.setContentView(R.layout.loaddialog);
                this.a.setCanceledOnTouchOutside(true);
                this.a.setCancelable(true);
                return this.a;
            default:
                return super.onCreateDialog(i);
        }
    }

    public void d(int i) {
        this.g = (NavigationBar) findViewById(i);
        if (this.g == null) {
            throw new RuntimeException("R.id.navigation_bar_ex resouce not found!!");
        }
    }

    public NavigationBar c() {
        if (this.g != null) {
            return this.g;
        }
        throw new RuntimeException("you may have forgotten to call setupNavigationBar!!");
    }

    public void setTitle(int i) {
        super.setTitle(i);
        setTitle(getResources().getString(i));
    }

    public void a(Context context, int i) {
        View imageView = new ImageView(context);
        imageView.setImageResource(i);
        this.g.setMiddleView(imageView);
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.navigation_bar_title, null);
        textView.setText(charSequence);
        textView.setTextSize(19.0f);
        this.g.setMiddleView(textView);
    }

    public void a(final OnClickListener onClickListener, String str) {
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.navigation_bar_btn_left, null);
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BaseActvityWithLoadDailog b;

            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                } else {
                    this.b.finish();
                }
            }
        });
        if (this.g != null) {
            this.g.setLeftView(textView);
        }
    }

    public void b(final OnClickListener onClickListener, String str) {
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.navigation_bar_btn_left, null);
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BaseActvityWithLoadDailog b;

            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                } else {
                    this.b.finish();
                }
            }
        });
        if (this.g != null) {
            this.g.setRightView(textView);
        }
    }

    protected void a(final OnClickListener onClickListener) {
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.navigation_bar_btn, null);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BaseActvityWithLoadDailog b;

            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                } else {
                    this.b.finish();
                }
            }
        });
        if (this.g != null) {
            this.g.setLeftView(textView);
        }
    }

    protected void b(final OnClickListener onClickListener) {
        this.b = (TextView) getLayoutInflater().inflate(R.layout.navigation_bar_close_btn, null);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(15, 0, 10, 0);
        this.b.setLayoutParams(layoutParams);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ BaseActvityWithLoadDailog b;

            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                } else {
                    this.b.finish();
                }
            }
        });
        if (this.g != null) {
            this.g.setLeftViewTwo(this.b);
        }
    }

    protected void d() {
        if (this.g != null) {
            this.g.a(this.b);
        }
    }

    public void e() {
        if (this.a != null && this.a.isShowing()) {
            dismissDialog(1);
        }
    }

    public void f() {
        try {
            showDialog(1);
        } catch (RuntimeException e) {
        }
    }
}
