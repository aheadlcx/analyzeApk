package com.budejie.www.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.budejie.www.R;
import com.budejie.www.activity.TipPopUp.TypeControl;

public class LTCountTipDialog extends Dialog {
    private Context a;
    private ViewGroup b;
    private int c = 1;
    private Animation d;
    private Handler e = new Handler(this) {
        final /* synthetic */ LTCountTipDialog a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == this.a.c) {
                this.a.b.startAnimation(this.a.d);
            }
        }
    };

    /* renamed from: com.budejie.www.activity.LTCountTipDialog$3 */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] a = new int[TypeControl.values().length];

        static {
            try {
                a[TypeControl.share.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[TypeControl.post.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[TypeControl.commit.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[TypeControl.jubao.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[TypeControl.modify.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    public enum ViewType {
        SHARE,
        POST,
        COMMIT,
        MODIFY
    }

    public LTCountTipDialog(Context context, TypeControl typeControl, int i, boolean z) {
        super(context, R.style.tipDialog_style);
        this.a = context;
        a(typeControl, i, z);
    }

    private void a(TypeControl typeControl, int i, boolean z) {
        this.b = (ViewGroup) LayoutInflater.from(this.a).inflate(R.layout.count_tip_dialog, null);
        setContentView(this.b);
        TextView textView = (TextView) this.b.findViewById(R.id.content);
        ((TextView) this.b.findViewById(R.id.addGold)).setText(" + " + i + "积分");
        if (z) {
            ((ViewGroup) this.b.findViewById(R.id.addView)).setVisibility(8);
        }
        switch (AnonymousClass3.a[typeControl.ordinal()]) {
            case 1:
                textView.setText(R.string.count_tip_share);
                break;
            case 2:
                textView.setText(R.string.count_tip_post);
                break;
            case 3:
                textView.setText(R.string.count_tip_commit);
                break;
            case 4:
                textView.setText(R.string.count_tip_report);
                break;
            case 5:
                textView.setText(R.string.count_tip_modify);
                ((ViewGroup) this.b.findViewById(R.id.addView)).setVisibility(4);
                break;
        }
        this.d = AnimationUtils.loadAnimation(getContext(), R.anim.tip_dialog_exit);
        this.d.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ LTCountTipDialog a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.dismiss();
            }
        });
    }

    public void show() {
        super.show();
        Message obtainMessage = this.e.obtainMessage();
        obtainMessage.what = this.c;
        this.e.sendMessageDelayed(obtainMessage, 500);
    }
}
