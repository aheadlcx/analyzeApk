package qsbk.app.im;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.http.HttpTask;
import qsbk.app.utils.UIHelper;

public class OfficialSubscribeListActivity extends BaseActionBarActivity {
    public static final String INIT_ADAPTER = "init_adapter";
    String a;
    private ListView b;
    private ProgressBar c;
    private OfficialSubscribeListAdapter d;
    private boolean e = false;
    private final BroadcastReceiver f = new im(this);
    private LocalBroadcastManager g;

    public OfficialSubscribeListActivity() {
        String str = Constants.OFFICIAL_SUBSCRIBE_LIST;
        Object[] objArr = new Object[1];
        QsbkApp.getInstance();
        objArr[0] = QsbkApp.currentUser.userId;
        this.a = String.format(str, objArr);
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return getResources().getString(R.string.official_number_list);
    }

    protected int a() {
        return R.layout.official_subscribe_list_activity;
    }

    protected void onResume() {
        super.onResume();
        if (this.e) {
            g();
            this.e = false;
        }
    }

    private void g() {
        a(Constants.OFFICIAL_SUBSCRIBE_LIST, this.a, null);
        this.c.setVisibility(0);
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        View findViewById = findViewById(R.id.official_background);
        if (findViewById != null) {
            findViewById.setBackgroundColor(UIHelper.isNightTheme() ? UIHelper.getColor(R.color.main_bg_night) : UIHelper.getColor(R.color.white));
        }
        this.c = (ProgressBar) findViewById(R.id.loadingbar);
        this.b = (ListView) findViewById(R.id.official_list);
        this.b.addHeaderView(LayoutInflater.from(this).inflate(R.layout.listview_header_tips, null), null, false);
        this.d = new OfficialSubscribeListAdapter(this);
        this.b.setAdapter(this.d);
        g();
        this.b.setOnItemClickListener(new in(this));
        this.g = LocalBroadcastManager.getInstance(this);
        this.g.registerReceiver(this.f, new IntentFilter(INIT_ADAPTER));
    }

    protected void onDestroy() {
        super.onDestroy();
        this.g.unregisterReceiver(this.f);
    }

    private void a(String str, String str2, Map<String, Object> map) {
        HttpTask httpTask = new HttpTask(str, str2, new io(this));
        if (map != null) {
            httpTask.setMapParams(map);
        }
        httpTask.execute(new Void[0]);
    }
}
