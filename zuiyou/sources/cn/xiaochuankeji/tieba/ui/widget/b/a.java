package cn.xiaochuankeji.tieba.ui.widget.b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import cn.xiaochuankeji.tieba.R;

public class a {
    static final /* synthetic */ boolean a = (!a.class.desiredAssertionStatus());
    private AlertDialog b;
    private View c;

    public static class a {
        private a a;

        public a(Context context) {
            this.a = new a(context);
        }

        public a(Context context, String str, String str2) {
            this(context);
            a(str);
            b(str2);
        }

        public a a(String str) {
            this.a.c(str);
            return this;
        }

        public a b(String str) {
            this.a.d(str);
            return this;
        }

        public a a(String str, final OnClickListener onClickListener) {
            this.a.a(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.a.b();
                    if (onClickListener != null) {
                        onClickListener.onClick(view);
                    }
                }
            });
            this.a.a(str);
            return this;
        }

        public a b(String str, final OnClickListener onClickListener) {
            this.a.b(new OnClickListener(this) {
                final /* synthetic */ a b;

                public void onClick(View view) {
                    this.b.a.b();
                    if (onClickListener != null) {
                        onClickListener.onClick(view);
                    }
                }
            });
            this.a.b(str);
            return this;
        }

        public a a(View view) {
            this.a.a(view);
            return this;
        }

        public void a() {
            this.a.a();
        }

        public void b() {
            this.a.b();
        }
    }

    @SuppressLint({"InflateParams"})
    private a(Context context) {
        this.c = LayoutInflater.from(context).inflate(R.layout.layout_dialog_content_view, null);
        this.b = new Builder(context).create();
        Window window = this.b.getWindow();
        if (a || window != null) {
            window.setBackgroundDrawable(new ColorDrawable(0));
            this.b.setView(this.c);
            return;
        }
        throw new AssertionError();
    }

    private void a(String str) {
        ((TextView) this.c.findViewById(R.id.dialog_cancel)).setText(str);
    }

    private void b(String str) {
        ((TextView) this.c.findViewById(R.id.dialog_confirm)).setText(str);
    }

    private void a(OnClickListener onClickListener) {
        this.c.findViewById(R.id.dialog_cancel).setOnClickListener(onClickListener);
    }

    private void b(OnClickListener onClickListener) {
        this.c.findViewById(R.id.dialog_confirm).setOnClickListener(onClickListener);
    }

    private void c(String str) {
        ((TextView) this.c.findViewById(R.id.dialog_title)).setText(str);
    }

    private void d(String str) {
        ((TextView) this.c.findViewById(R.id.dialog_msg)).setText(str);
    }

    private void a(View view) {
        this.b.setView(view);
    }

    private void a() {
        if (this.b.isShowing()) {
            this.b.dismiss();
        }
        this.b.show();
    }

    private void b() {
        this.b.dismiss();
    }
}
