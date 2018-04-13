package qsbk.app.im;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.readystatesoftware.systembartint.SystemBarTintHelper;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.HttpTask;
import qsbk.app.image.FrescoImageloader;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class OfficialInfoActivity extends Activity {
    public static final String AVATAR = "avatar";
    public static final String NAME = "name";
    public static final String UID = "uid";
    private static final String a = OfficialInfoActivity.class.getSimpleName();
    private ProgressBar b;
    private TextView c;
    private TextView d;
    private TextView e;
    private ImageView f;
    private ImageView g;
    private View h;
    private String i;
    private String j;
    private String k;
    private boolean l = true;
    private boolean m = false;

    public static void launch(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, OfficialInfoActivity.class);
        intent.putExtra("uid", str);
        intent.putExtra("name", str2);
        intent.putExtra("avatar", str3);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        if (QsbkApp.currentUser == null) {
            ToastAndDialog.makeNegativeToast(this, "登录后才能查看官号信息。", Integer.valueOf(0)).show();
            finish();
            return;
        }
        new SystemBarTintHelper(this).setView(R.layout.official_info_activity).enableSystembarTint(UIHelper.isNightTheme() ? Color.parseColor("#444f75") : Color.parseColor("#91a7ff"));
        Intent intent = getIntent();
        this.i = intent.getStringExtra("uid");
        this.j = intent.getStringExtra("name");
        this.k = intent.getStringExtra("avatar");
        b();
        String str = Constants.OFFICIAL_INFO;
        Object[] objArr = new Object[2];
        objArr[0] = this.i;
        QsbkApp.getInstance();
        objArr[1] = QsbkApp.currentUser.userId;
        a(Constants.OFFICIAL_INFO, String.format(str, objArr), null);
    }

    private void b() {
        this.h = findViewById(R.id.layerMask);
        if (UIHelper.isNightTheme()) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(8);
        }
        this.b = (ProgressBar) findViewById(R.id.info_loading);
        this.c = (TextView) findViewById(R.id.title);
        this.d = (TextView) findViewById(R.id.name);
        this.e = (TextView) findViewById(R.id.description);
        this.f = (ImageView) findViewById(R.id.avatar);
        this.g = (ImageView) findViewById(R.id.relation);
        this.c.setText(this.j);
        this.d.setText(this.j);
        FrescoImageloader.displayAvatar(this.f, QsbkApp.absoluteUrlOfLargeUserIcon(this.k, this.i));
        this.c.setOnClickListener(new ig(this));
        this.g.setOnClickListener(new ih(this));
        this.g.setVisibility(8);
    }

    private void a(String str, String str2, Map<String, Object> map) {
        if (Constants.OFFICIAL_INFO.equalsIgnoreCase(str)) {
            this.b.setVisibility(0);
        }
        HttpTask httpTask = new HttpTask(str, str2, new ik(this));
        if (map != null) {
            httpTask.setMapParams(map);
        }
        httpTask.execute(new Void[0]);
    }

    private void c() {
        if (!this.l) {
            this.g.setImageResource(R.drawable.rel_fan_it_selector);
        } else if (this.m) {
            this.g.setImageResource(R.drawable.rel_fan_selector);
        } else {
            this.g.setImageResource(R.drawable.rel_fan_pressed);
        }
    }
}
