package qsbk.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.MedalAdapter;
import qsbk.app.core.AsyncTask;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;

public class MedalListActivity extends BaseActionBarActivity implements PtrListener {
    public static final String MEDAL_FROM = "my_profile";
    public static final String NO_LOGIN_USER_ID = "-1";
    private boolean a = false;
    private String b;
    private String c;
    private PtrLayout d;
    private ListView e;
    private TextView f;
    private ArrayList<Object> g = new ArrayList();
    private MedalAdapter h;
    private a i = a.TOP_REFRESH;
    private int j = 1;

    private enum a {
        TOP_REFRESH,
        NORMAL_LOAD_MORE
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    public static void launch(Activity activity, String str, boolean z) {
        launch(activity, str, z, null);
    }

    public static void launch(Activity activity, String str, boolean z, String str2) {
        if (!TextUtils.isEmpty(str)) {
            Intent intent = new Intent(activity, MedalListActivity.class);
            intent.putExtra("isMe", z);
            intent.putExtra("uid", str);
            intent.putExtra("from", str2);
            activity.startActivity(intent);
        }
    }

    protected int a() {
        return R.layout.layout_ptr_listview;
    }

    protected String f() {
        return this.a ? "我的勋章" : "TA的勋章";
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        Intent intent = getIntent();
        this.a = intent.getBooleanExtra("isMe", false);
        this.b = intent.getStringExtra("uid");
        this.c = intent.getStringExtra("from");
        g();
    }

    private void g() {
        boolean z;
        this.d = (PtrLayout) findViewById(R.id.ptr);
        this.e = (ListView) findViewById(R.id.listview);
        this.d.setLoadMoreEnable(false);
        this.d.setPtrListener(this);
        this.f = new TextView(this);
        this.f.setHeight(i());
        this.f.setWidth(DeviceUtils.getScreenWidth(this));
        this.f.setGravity(81);
        this.f.setPadding(0, 0, 0, 15);
        this.f.setTextSize(12.0f);
        this.f.setTextColor(UIHelper.isNightTheme() ? -12171438 : -4671304);
        this.f.setText(getResources().getString(R.string.medal_empty_tip));
        ArrayList arrayList = this.g;
        ListView listView = this.e;
        if (MEDAL_FROM.equals(this.c) || "-1".equals(this.b)) {
            z = true;
        } else {
            z = false;
        }
        this.h = new MedalAdapter(this, arrayList, listView, z);
        this.e.setAdapter(this.h);
        this.e.setClickable(false);
        this.e.addFooterView(this.f);
        this.e.postDelayed(new tf(this), 0);
    }

    private int i() {
        int round = Math.round(50.0f * getResources().getDisplayMetrics().density);
        if (this.g == null || this.g.size() == 0) {
            return round;
        }
        int screenHeight = DeviceUtils.getScreenHeight(this);
        if (screenHeight < round) {
            return round;
        }
        screenHeight -= Math.round(((float) ((this.g.size() + 1) * 90)) * getResources().getDisplayMetrics().density);
        return round <= screenHeight ? screenHeight : round;
    }

    public void onRefresh() {
        this.i = a.TOP_REFRESH;
        this.j = 1;
        j();
    }

    public void onLoadMore() {
        this.i = a.NORMAL_LOAD_MORE;
        this.d.loadMoreDone(false);
        j();
    }

    private void j() {
        String str;
        if (MEDAL_FROM.equals(this.c) || "-1".equals(this.b)) {
            str = Constants.PERSONAL_MEDAL_URL;
        } else {
            str = String.format(Constants.PERSONAL_MEDAL_OTHER_URL, new Object[]{this.b});
        }
        new SimpleHttpTask(str + "?page=" + this.j + "&count=" + 30, new tg(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }
}
