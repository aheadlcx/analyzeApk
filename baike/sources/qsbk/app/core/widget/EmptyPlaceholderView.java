package qsbk.app.core.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import qsbk.app.core.R;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.NetworkUtils;
import qsbk.app.core.utils.ToastUtil;

public class EmptyPlaceholderView extends LinearLayout {
    private LinearLayout a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private LinearLayout e;
    private LinearLayout f;
    private ImageView g;
    private ProgressBar h;

    public interface OnEmptyClickListener {
        void onEmptyClick(View view);
    }

    public EmptyPlaceholderView(Context context) {
        this(context, null);
    }

    public EmptyPlaceholderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public EmptyPlaceholderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = View.inflate(context, R.layout.item_empty, this);
        this.a = (LinearLayout) inflate.findViewById(R.id.ll_empty);
        this.b = (ImageView) inflate.findViewById(R.id.iv_placeholder);
        this.c = (TextView) inflate.findViewById(R.id.tv_placeholder);
        this.d = (TextView) inflate.findViewById(R.id.tv_placeholder_click);
        this.b.setVisibility(8);
        this.e = (LinearLayout) findViewById(R.id.multilineText);
        this.f = (LinearLayout) findViewById(R.id.ll_action);
        this.g = (ImageView) findViewById(R.id.iv_icon);
        this.h = (ProgressBar) findViewById(R.id.progress_bar);
    }

    public void setEmptyContent(boolean z) {
        setEmptyContent(z, null);
    }

    public void setEmptyContent(OnEmptyClickListener onEmptyClickListener) {
        setEmptyContent(false, onEmptyClickListener);
    }

    public void setEmptyContent(boolean z, OnEmptyClickListener onEmptyClickListener) {
        setEmptyContent(z, 0, R.string.empty, onEmptyClickListener);
    }

    public void setEmptyContent(int i, int i2) {
        setEmptyContent(i, i2, null);
    }

    public void setEmptyContent(boolean z, int i, int i2) {
        setEmptyContent(z, i, i2, null);
    }

    public void setEmptyContent(int i, int i2, OnEmptyClickListener onEmptyClickListener) {
        setEmptyContent(false, i, i2, onEmptyClickListener);
    }

    public void setEmptyContent(boolean z, int i, int i2, OnEmptyClickListener onEmptyClickListener) {
        setEmptyContent(z, i, i2, 0, R.string.click_to_refresh, onEmptyClickListener);
    }

    public void setEmptyContent(int i, int i2, int i3, int i4, OnEmptyClickListener onEmptyClickListener) {
        setEmptyContent(false, i, i2, i3, i4, onEmptyClickListener);
    }

    public void setEmptyContent(boolean z, int i, int i2, int i3, int i4, OnEmptyClickListener onEmptyClickListener) {
        if (z) {
            hide();
            return;
        }
        show();
        if (i != 0) {
            this.b.setImageResource(i);
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
        }
        this.c.setText(i2);
        this.c.setVisibility(0);
        this.e.setVisibility(8);
        this.h.setVisibility(8);
        if (onEmptyClickListener == null) {
            this.f.setVisibility(8);
            this.f.setOnClickListener(null);
            return;
        }
        this.f.setVisibility(0);
        this.d.setText(i4);
        if (i3 != 0) {
            this.g.setImageResource(i3);
            this.g.setVisibility(0);
        } else {
            this.g.setVisibility(8);
        }
        this.f.setOnClickListener(new c(this, onEmptyClickListener));
    }

    public void setEmptyListener(OnEmptyClickListener onEmptyClickListener) {
        this.f.setOnClickListener(new d(this, onEmptyClickListener));
        this.c.setVisibility(0);
        this.f.setVisibility(0);
        this.b.setVisibility(8);
        this.e.setVisibility(8);
        this.h.setVisibility(8);
    }

    public void setTextOnly(int i) {
        setTextOnly(AppUtils.getInstance().getAppContext().getString(i));
    }

    public void setTextOnly(String str, int i) {
        setTextOnly(str);
        this.c.setTextColor(i);
    }

    public void setTextOnly(String str) {
        this.c.setText(str);
        this.c.setVisibility(0);
        this.f.setVisibility(8);
        this.b.setVisibility(8);
        this.e.setVisibility(8);
        this.h.setVisibility(8);
        show();
    }

    public void showActionOnly(OnEmptyClickListener onEmptyClickListener) {
        this.c.setVisibility(8);
        this.b.setVisibility(8);
        this.e.setVisibility(8);
        this.h.setVisibility(8);
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        this.f.setOnClickListener(new e(this, onEmptyClickListener));
    }

    public void setEmptyTextAndAction(int i, OnEmptyClickListener onEmptyClickListener) {
        setEmptyTextAndAction(AppUtils.getInstance().getAppContext().getString(i), onEmptyClickListener);
    }

    public void setEmptyTextAndAction(String str, OnEmptyClickListener onEmptyClickListener) {
        this.c.setVisibility(0);
        this.c.setText(str);
        this.b.setVisibility(8);
        this.e.setVisibility(8);
        this.h.setVisibility(8);
        this.f.setVisibility(0);
        this.g.setVisibility(8);
        this.f.setOnClickListener(new f(this, onEmptyClickListener));
        show();
    }

    public void setNetworkErrorContent(Activity activity, int i, String str, OnEmptyClickListener onEmptyClickListener, boolean z) {
        this.h.setVisibility(8);
        boolean a = a(i);
        if (TextUtils.isEmpty(str)) {
            str = activity.getString(R.string.request_failed);
        }
        if (a) {
            show();
            this.e.setVisibility(8);
            if (onEmptyClickListener == null) {
                this.f.setVisibility(8);
                this.f.setOnClickListener(null);
                return;
            }
            if (z) {
                this.b.setVisibility(0);
                if (this.b.getDrawable() == null) {
                    this.b.setImageResource(R.drawable.ic_microphone);
                }
            } else {
                this.b.setVisibility(8);
            }
            this.f.setVisibility(0);
            this.g.setImageResource(R.drawable.ic_refresh);
            this.g.setVisibility(0);
            this.c.setVisibility(0);
            this.d.setVisibility(0);
            this.c.setText(R.string.network_not_well);
            this.d.setText(R.string.click_to_refresh);
            this.f.setOnClickListener(new g(this, onEmptyClickListener));
            return;
        }
        ToastUtil.Short(str);
    }

    private boolean a(int i) {
        return i == -101;
    }

    public void setMarginTop(int i) {
        this.a.setGravity(1);
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        layoutParams.topMargin = i;
        layoutParams.bottomMargin = i;
        this.a.setLayoutParams(layoutParams);
    }

    public void show() {
        setVisibility(0);
    }

    public void hide() {
        setVisibility(8);
    }

    public boolean isShowingProgressBar() {
        return getVisibility() == 0 && this.h.getVisibility() == 0;
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    public void setMultilineText(int i) {
        setMultilineText(i, 0);
    }

    public void setMultilineText(int i, int i2) {
        this.c.setVisibility(8);
        this.f.setVisibility(8);
        this.b.setVisibility(8);
        this.h.setVisibility(8);
        if (i2 == 0) {
            i2 = R.string.message_author;
        }
        TextView textView = (TextView) findViewById(R.id.multilineText1);
        TextView textView2 = (TextView) findViewById(R.id.multilineText2);
        textView.setText(i);
        textView.setVisibility(0);
        textView.setTypeface(Typeface.MONOSPACE, 2);
        textView2.setText(i2);
        textView2.setVisibility(0);
        textView2.setTypeface(Typeface.MONOSPACE, 2);
        this.e.setVisibility(0);
    }

    public boolean showIfNetworkNotWell(Activity activity, OnEmptyClickListener onEmptyClickListener) {
        if (activity == null || activity.isFinishing()) {
            return true;
        }
        if (NetworkUtils.getInstance().isNetworkAvailable()) {
            return false;
        }
        setNetworkErrorContent(activity, -101, null, onEmptyClickListener, true);
        show();
        return true;
    }

    public boolean showIfUserNotLogin(int i, Activity activity) {
        if (AppUtils.getInstance().getUserInfoProvider().isLogin()) {
            return false;
        }
        setEmptyContent(false, 0, i, 0, R.string.require_login, new h(this, activity));
        show();
        return true;
    }

    public void showServerCrash(OnEmptyClickListener onEmptyClickListener) {
        setEmptyContent(false, R.drawable.ic_warningboard, R.string.server_crashed, R.drawable.ic_refresh, R.string.click_to_refresh, onEmptyClickListener);
        show();
    }

    public void showError(Activity activity, int i, OnEmptyClickListener onEmptyClickListener) {
        showError(activity, i, onEmptyClickListener, true);
    }

    public void showError(Activity activity, int i, String str, OnEmptyClickListener onEmptyClickListener) {
        showError(activity, i, str, onEmptyClickListener, true);
    }

    public void showError(Activity activity, int i, OnEmptyClickListener onEmptyClickListener, boolean z) {
        showError(activity, i, "", onEmptyClickListener, z);
    }

    public void showError(Activity activity, int i, String str, OnEmptyClickListener onEmptyClickListener, boolean z) {
        if (activity != null && !activity.isFinishing()) {
            if (i == 0) {
                setNetworkErrorContent(activity, -101, null, onEmptyClickListener, z);
            } else if (i == 1) {
                setEmptyContent(false, z ? R.drawable.ic_warningboard : 0, R.string.server_crashed, R.drawable.ic_refresh, R.string.click_to_refresh, onEmptyClickListener);
            } else if (TextUtils.isEmpty(str)) {
                showActionOnly(onEmptyClickListener);
            } else {
                setEmptyTextAndAction(str, onEmptyClickListener);
            }
            show();
        }
    }

    public void showProgressBar() {
        show();
        this.c.setVisibility(8);
        this.f.setVisibility(8);
        this.b.setVisibility(8);
        this.e.setVisibility(8);
        this.h.setVisibility(0);
    }
}
