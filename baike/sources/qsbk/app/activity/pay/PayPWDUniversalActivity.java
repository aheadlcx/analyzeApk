package qsbk.app.activity.pay;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.open.SocialConstants;
import java.util.LinkedList;
import qsbk.app.R;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.utils.Util;
import qsbk.app.widget.VirtualKeyboardView;
import qsbk.app.widget.VirtualKeyboardView.Key;

public class PayPWDUniversalActivity extends BaseActivity {
    public static final String KEY = "key";
    public static final String MONEY = "money";
    public static final String PAY_FOR = "payfor";
    public static final String TITLE = "title";
    LinkedList<Key> a = new LinkedList();
    ImageView[] b = new ImageView[6];
    private String c;
    private String d;
    private double e;
    private String f;
    private View g;
    private View h;
    private VirtualKeyboardView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private View n;

    public static void launch(Activity activity, int i, String str, String str2, String str3, double d) {
        Intent intent = new Intent(activity, PayPWDUniversalActivity.class);
        intent.putExtra("title", str);
        intent.putExtra(PAY_FOR, str3);
        intent.putExtra(MONEY, d);
        intent.putExtra(SocialConstants.PARAM_APP_DESC, str2);
        activity.startActivityForResult(intent, i);
        activity.overridePendingTransition(0, 0);
    }

    protected int getLayoutId() {
        return R.layout.activity_pay_pwd_universal;
    }

    protected void initView() {
        this.g = findViewById(R.id.desc_window);
        this.j = (TextView) findViewById(R.id.title);
        this.m = (TextView) findViewById(R.id.desc);
        this.k = (TextView) findViewById(R.id.pay_for);
        this.l = (TextView) findViewById(R.id.amount);
        this.h = findViewById(R.id.pwd_input);
        this.i = (VirtualKeyboardView) findViewById(R.id.keyboard);
        this.n = findViewById(R.id.close);
        this.n.setOnClickListener(new bg(this));
        this.i.setOnKeypressListener(new bh(this));
        this.i.getLayoutBack().setOnClickListener(new bi(this));
        this.i.getViewTreeObserver().addOnGlobalLayoutListener(new bj(this));
        this.h.setOnClickListener(new bk(this));
        this.b[0] = (ImageView) findViewById(R.id.pwd_1);
        this.b[1] = (ImageView) findViewById(R.id.pwd_2);
        this.b[2] = (ImageView) findViewById(R.id.pwd_3);
        this.b[3] = (ImageView) findViewById(R.id.pwd_4);
        this.b[4] = (ImageView) findViewById(R.id.pwd_5);
        this.b[5] = (ImageView) findViewById(R.id.pwd_6);
    }

    protected void initData() {
        int i = 8;
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        int i2;
        this.c = intent.getStringExtra("title");
        this.d = intent.getStringExtra(PAY_FOR);
        this.e = intent.getDoubleExtra(MONEY, 0.0d);
        this.f = intent.getStringExtra(SocialConstants.PARAM_APP_DESC);
        this.j.setText(this.c);
        this.k.setText(this.d);
        this.m.setText(this.f);
        this.l.setText(String.format("¥%s", new Object[]{Util.formatMoney(this.e)}));
        TextView textView = this.m;
        if (TextUtils.isEmpty(this.f)) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        textView.setVisibility(i2);
        textView = this.k;
        if (TextUtils.isEmpty(this.d)) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        textView.setVisibility(i2);
        TextView textView2 = this.l;
        if (this.e != 0.0d) {
            i = 0;
        }
        textView2.setVisibility(i);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    private void a(String str) {
        Intent intent = new Intent();
        intent.putExtra(KEY, str);
        setResult(-1, intent);
        finish();
    }

    private void a() {
        if (this.b.length == 6) {
            for (int i = 0; i < this.b.length; i++) {
                if (i < this.a.size()) {
                    this.b[i].setVisibility(0);
                } else {
                    this.b[i].setVisibility(4);
                }
            }
        }
    }
}
