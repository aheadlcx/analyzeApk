package qsbk.app.activity.base;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import qsbk.app.R;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.SharePreferenceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.Util;
import qsbk.app.widget.ISizeNotifier;
import qsbk.app.widget.SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate;

public abstract class BaseEmotionActivity extends BaseActionBarActivity implements SizeNotifierRelativeLayout$SizeNotifierRelativeLayoutDelegate {
    public static final String KEYBOARD_HEIGHT = "_keyboard_height";
    protected EditText G;
    protected View H;
    protected View I;
    protected InputMethodManager J;
    protected ImageButton K;
    protected ImageButton O;
    protected int P;
    private boolean a = false;
    private OnClickListener b = new y(this);

    protected void m() {
        r();
        this.H.setVisibility(8);
        this.O.setVisibility(0);
        this.K.setVisibility(8);
    }

    protected void n() {
        q();
        a(this.P);
        s();
        this.H.setVisibility(0);
        this.K.setVisibility(0);
        this.O.setVisibility(8);
    }

    protected void l() {
        this.J = (InputMethodManager) getSystemService("input_method");
        this.P = SharePreferenceUtils.getSharePreferencesIntValue(KEYBOARD_HEIGHT);
        this.H = findViewById(R.id.emotions);
        this.K = (ImageButton) findViewById(R.id.sendKeyboard);
        this.K.setOnClickListener(this.b);
        this.K.setImageResource(UIHelper.getSendTextResource());
        this.O = (ImageButton) findViewById(R.id.sendEmotion);
        this.O.setOnClickListener(this.b);
        this.O.setImageResource(UIHelper.getSendEmotionResource());
        this.G.setOnClickListener(this.b);
        this.G.setOnTouchListener(new z(this));
        this.I = findViewById(R.id.root);
        if (this.I instanceof ISizeNotifier) {
            ((ISizeNotifier) this.I).setSizeNotifierRelativeLayoutDelegate(this);
        } else if (DebugUtil.DEBUG) {
            throw new IllegalArgumentException("View with R.id.root must implements NotifierLayout, See SizeNotifierFrameLayout for example.");
        }
    }

    public void showKeyboard() {
        this.G.requestFocus();
        this.J.showSoftInput(this.G, 1);
    }

    protected void q() {
        getWindow().setSoftInputMode(32);
    }

    protected void r() {
        getWindow().setSoftInputMode(16);
    }

    protected void s() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            this.J.hideSoftInputFromWindow(currentFocus.getWindowToken(), 2);
        }
    }

    protected boolean t() {
        return this.H.getVisibility() == 0 && this.H.getHeight() > 0;
    }

    protected void a(int i) {
        LayoutParams layoutParams = this.H.getLayoutParams();
        if (i < 0) {
            i = 0;
        }
        layoutParams.height = i;
        this.H.setLayoutParams(layoutParams);
    }

    public void onSizeChanged(int i) {
        if (i > Util.dp(50.0f) && this.P != i) {
            this.P = i;
            SharePreferenceUtils.setSharePreferencesValue(KEYBOARD_HEIGHT, this.P);
        }
        if (t() && i > 0 && !this.a) {
            this.H.post(new aa(this));
        }
    }

    public void onBackPressed() {
        if (this.H.isShown()) {
            m();
        } else {
            super.onBackPressed();
        }
    }
}
