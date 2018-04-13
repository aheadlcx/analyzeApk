package qsbk.app.activity.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import qsbk.app.R;
import qsbk.app.compat.ThemeCompat;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.UIHelper;

public class CommDialogActivity extends Activity {
    public static String KEY_ACTIONS = "actions";
    public static String KEY_ITEMS = "items";
    public static String KEY_TITLE = "titel";
    protected ListView a;
    protected AlertDialog b;
    protected String[] c;
    protected int[] d;
    protected String e;
    protected TextView f;
    protected View g;
    private boolean h = false;
    public OnItemClickListener itemClickListener = new ac(this);

    public String[] getMenuTitles() {
        return this.c;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.AppTheme.share.new.Dark);
        }
        bindEvent();
    }

    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        this.c = intent.getStringArrayExtra(KEY_ITEMS);
        this.d = intent.getIntArrayExtra(KEY_ACTIONS);
        if (this.c == null || this.d == null || this.c.length != this.d.length) {
            LogUtil.e("intent param is not correct");
            finish();
            return;
        }
        this.e = intent.getStringExtra(KEY_TITLE);
        if (TextUtils.isEmpty(this.e)) {
            this.g.setVisibility(8);
        } else {
            this.f.setText(this.e);
            this.g.setVisibility(0);
        }
        this.a.setAdapter(new ArrayAdapter(this, ThemeCompat.getSimpleListItem(), getMenuTitles()));
        this.a.setOnItemClickListener(this.itemClickListener);
    }

    @SuppressLint({"NewApi"})
    public void bindEvent() {
        if (ThemeCompat.preHoneycomb()) {
            setContentView(R.layout.activity_shareactivity_new);
            this.a = (ListView) findViewById(R.id.listview);
            this.a.setBackgroundResource(R.color.transparent);
            this.a.setPadding(20, 20, 20, 20);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(20, 1, 20, 1);
            this.a.setLayoutParams(layoutParams);
            this.f = (TextView) findViewById(R.id.id_title);
            this.g = findViewById(R.id.id_top_layout);
            return;
        }
        Builder builder = new Builder(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.activity_shareactivity_new, null);
        this.a = (ListView) inflate.findViewById(R.id.listview);
        builder.setView(inflate);
        this.b = builder.show();
        this.b.setCanceledOnTouchOutside(true);
        this.b.setOnCancelListener(new ad(this));
        this.f = (TextView) inflate.findViewById(R.id.id_title);
        this.g = inflate.findViewById(R.id.id_top_layout);
    }

    public void finish() {
        if (!this.h) {
            this.h = true;
            if (this.b != null && this.b.isShowing()) {
                this.b.dismiss();
            }
            super.finish();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        finish();
        return super.onTouchEvent(motionEvent);
    }

    public void confirmOption(int i) {
        setResult(this.d[i], new Intent());
        finish();
    }
}
