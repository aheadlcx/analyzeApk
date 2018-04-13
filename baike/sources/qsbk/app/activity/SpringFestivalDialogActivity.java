package qsbk.app.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.utils.SpringFestivalUtil;
import qsbk.app.utils.UIHelper;

public class SpringFestivalDialogActivity extends Activity {
    public static final String KEY_BTN_TEXT = "btn_text";
    public static final String KEY_COLOR_NUM = "color_num";
    public static final String KEY_CONTENT = "content";
    public static final String KEY_GOLD_NUM = "gold_num";
    public static final String KEY_LINK = "link";
    public static final String KEY_SUBTITLE = "subtitle";
    public static final String KEY_TITLE = "title";
    private TextView a;
    private TextView b;
    private View c;
    private View d;
    private TextView e;
    private TextView f;
    private TextView g;
    private Button h;
    private CharSequence i = "";
    private CharSequence j = "";
    private CharSequence k = "去砸蛋";
    private CharSequence l = "";
    private int m = 0;
    private int n = 0;
    private String o = "";
    private OnClickListener p;

    public static class SpringFestivalIntentBuilder {
        private CharSequence a;
        private CharSequence b;
        private CharSequence c = "去砸蛋";
        private CharSequence d = "活动期间, 每天登录都送砸彩蛋锤子哦";
        private String e;
        private int f;
        private int g;

        public SpringFestivalIntentBuilder title(CharSequence charSequence) {
            this.a = charSequence;
            return this;
        }

        public SpringFestivalIntentBuilder subTitle(CharSequence charSequence) {
            this.b = charSequence;
            return this;
        }

        public SpringFestivalIntentBuilder buttonText(CharSequence charSequence) {
            if (!TextUtils.isEmpty(charSequence)) {
                this.c = charSequence;
            }
            return this;
        }

        public SpringFestivalIntentBuilder content(CharSequence charSequence) {
            if (!TextUtils.isEmpty(charSequence)) {
                this.d = charSequence;
            }
            return this;
        }

        public SpringFestivalIntentBuilder goldNum(int i) {
            this.f = i;
            return this;
        }

        public SpringFestivalIntentBuilder colorNum(int i) {
            this.g = i;
            return this;
        }

        public SpringFestivalIntentBuilder link(String str) {
            this.e = str;
            return this;
        }

        public Intent build(Context context) {
            Intent intent = new Intent(context, SpringFestivalDialogActivity.class);
            intent.putExtra("title", this.a);
            intent.putExtra(SpringFestivalDialogActivity.KEY_SUBTITLE, this.b);
            intent.putExtra("content", this.d);
            intent.putExtra(SpringFestivalDialogActivity.KEY_BTN_TEXT, this.c);
            intent.putExtra(SpringFestivalDialogActivity.KEY_GOLD_NUM, this.f);
            intent.putExtra(SpringFestivalDialogActivity.KEY_COLOR_NUM, this.g);
            intent.putExtra("link", this.e);
            return intent;
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.p = onClickListener;
    }

    public void setTitle(CharSequence charSequence) {
        this.i = charSequence;
    }

    public void setSubTitle(CharSequence charSequence) {
        this.j = charSequence;
    }

    public void setButtonText(CharSequence charSequence) {
        this.k = charSequence;
    }

    public void setContent(CharSequence charSequence) {
        this.l = charSequence;
    }

    public void setGoldNum(int i) {
        this.m = i;
    }

    public void setColorNum(int i) {
        this.n = i;
    }

    protected void onCreate(Bundle bundle) {
        int i;
        int i2 = 0;
        a();
        super.onCreate(bundle);
        b();
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.transparent));
        if (this.m == 0 || this.n == 0) {
            getWindow().setLayout(UIHelper.dip2px(this, 270.0f), -2);
        }
        setContentView(R.layout.dialog_spring_festival_remind);
        this.a = (TextView) findViewById(R.id.title);
        this.b = (TextView) findViewById(R.id.sub_title);
        this.c = findViewById(R.id.gold_container);
        this.d = findViewById(R.id.color_container);
        this.e = (TextView) findViewById(R.id.gold_num);
        this.f = (TextView) findViewById(R.id.color_num);
        this.g = (TextView) findViewById(R.id.content);
        this.h = (Button) findViewById(R.id.button);
        this.a.setText(this.i);
        this.g.setText(this.l);
        this.b.setText(this.j);
        this.b.setVisibility(TextUtils.isEmpty(this.j) ? 8 : 0);
        this.e.setText(this.m > 99 ? "99+" : "" + this.m);
        this.f.setText(this.n > 99 ? "99+" : "" + this.n);
        View view = this.c;
        if (this.m > 0) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        View view2 = this.d;
        if (this.n <= 0) {
            i2 = 8;
        }
        view2.setVisibility(i2);
        this.h.setText(this.k);
        this.h.setOnClickListener(new ade(this));
        SpringFestivalUtil.neverRemind();
        if (TextUtils.isEmpty(this.i) && TextUtils.isEmpty(this.l) && TextUtils.isEmpty(this.o)) {
            finish();
        }
    }

    private void b() {
        Intent intent = getIntent();
        this.i = intent.getStringExtra("title");
        this.j = intent.getStringExtra(KEY_SUBTITLE);
        this.k = intent.getStringExtra(KEY_BTN_TEXT);
        this.l = intent.getStringExtra("content");
        this.m = intent.getIntExtra(KEY_GOLD_NUM, 0);
        this.n = intent.getIntExtra(KEY_COLOR_NUM, 0);
        this.o = intent.getStringExtra("link");
    }

    protected void a() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Theme.Dialog.Night);
        } else {
            setTheme(R.style.Theme.Dialog.Day);
        }
    }
}
