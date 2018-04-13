package qsbk.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import java.util.Map;
import qsbk.app.R;
import qsbk.app.utils.HttpUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.utils.image.issue.TaskExecutor;

public abstract class EditInfoBaseActivity extends FragmentActivity {
    public static final String URL = "http://nearby.qiushibaike.com/user/%s/detail";
    private static String c = null;
    protected View a;
    protected View b;
    private final boolean d = true;
    private boolean e = true;
    private FrameLayout f;

    public interface REQUEST_KEY {
        public static final String KEY_AUTO_SUBMIT = "auto_submit";
        public static final String KEY_DEFAULT_VALUE = "default_value";
        public static final String KEY_EDIT_TYPE = "edit_type";
        public static final String KEY_RETURN_VALUE = "return_value";
    }

    public abstract int getLayout();

    public abstract Map<String, Object> getPostParams();

    public abstract String getPostUrl();

    public abstract Intent getResultData();

    public abstract void handleIntent(Intent intent);

    public abstract void init();

    public abstract void onCancel(View view);

    public abstract boolean onSure(View view);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
        requestWindowFeature(1);
        c = getClass().getName();
        a(getIntent());
        setContentView(R.layout.activity_edit_info);
        this.f = (FrameLayout) findViewById(R.id.container);
        this.f.addView(View.inflate(this, getLayout(), null));
        a();
        init();
        handleIntent(getIntent());
    }

    private void a() {
        this.a = findViewById(R.id.cancel);
        this.b = findViewById(R.id.sure);
        this.a.setOnClickListener(new kb(this));
        this.b.setOnClickListener(new kc(this));
    }

    protected void a(Map<String, Object> map, String str) {
        if (HttpUtils.netIsAvailable()) {
            TaskExecutor.getInstance().addTask(new kd(this, str, map));
        }
    }

    protected void a(String str) {
        Log.e(c, str + "");
    }

    protected void b(String str) {
        a(str);
        setResult(0);
        finish();
    }

    private void a(Intent intent) {
        this.e = intent.getBooleanExtra(REQUEST_KEY.KEY_AUTO_SUBMIT, true);
    }
}
