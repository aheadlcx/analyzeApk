package qsbk.app.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.baidu.mobstat.StatService;
import com.readystatesoftware.systembartint.SystemBarTintHelper;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.http.HttpTask;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

public class AddQiuYouActivity extends StatFragmentActivity {
    private ProgressBar a;
    private TextView b;
    private EditText c;
    private ImageView d;
    private ImageView e;

    private void a() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day);
        }
    }

    protected void onCreate(Bundle bundle) {
        a();
        requestWindowFeature(1);
        super.onCreate(bundle);
        new SystemBarTintHelper(this).setView(R.layout.activity_add_qiuyou).enableSystembarTint();
        b();
    }

    private void b() {
        this.a = (ProgressBar) findViewById(R.id.loadingbar);
        this.b = (TextView) findViewById(R.id.title);
        this.c = (EditText) findViewById(R.id.input_name);
        this.d = (ImageView) findViewById(R.id.clear_input);
        this.e = (ImageView) findViewById(R.id.search);
        this.b.setOnClickListener(new as(this));
        this.c.setOnKeyListener(new at(this));
        this.c.addTextChangedListener(new au(this));
        this.d.setOnClickListener(new av(this));
        this.e.setOnClickListener(new aw(this));
    }

    private void d() {
        this.a.setVisibility(0);
        String obj = this.c.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            ToastAndDialog.makeNegativeToast(this, "请输入糗友昵称", Integer.valueOf(0)).show();
            this.a.setVisibility(8);
        } else if (obj.equals(QsbkApp.currentUser.userName)) {
            ToastAndDialog.makeNegativeToast(this, "您输入的是自己的昵称", Integer.valueOf(1)).show();
            this.a.setVisibility(8);
        } else {
            try {
                obj = Constants.URL_SEARCH_QIUYOU + "?name=" + URLEncoder.encode(obj, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                obj = null;
            }
            a(Constants.URL_SEARCH_QIUYOU, obj, null);
            StatService.onEvent(this, "search_qiuyou", "pass");
        }
    }

    private void a(String str, String str2, Map<String, Object> map) {
        HttpTask httpTask = new HttpTask(str, str2, new ax(this));
        if (map != null) {
            httpTask.setMapParams(map);
        }
        httpTask.execute(new Void[0]);
    }
}
