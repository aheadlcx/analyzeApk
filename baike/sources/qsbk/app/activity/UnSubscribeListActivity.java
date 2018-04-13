package qsbk.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.UnSubscribeAdapter;
import qsbk.app.http.HttpTask;
import qsbk.app.model.BaseUserInfo;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;

public class UnSubscribeListActivity extends BaseActionBarActivity implements OnItemClickListener, PtrListener {
    private static final String h = UnSubscribeListActivity.class.getSimpleName();
    protected boolean a = true;
    protected PtrLayout b;
    protected ListView c;
    protected UnSubscribeAdapter d;
    protected List<BaseUserInfo> e = new ArrayList();
    protected int f = 1;
    protected boolean g = true;
    private LinearLayout i;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return getString(R.string.unsubscribelist);
    }

    protected int a() {
        return R.layout.layout_ptr_listview;
    }

    public void onResume() {
        super.onResume();
        if (this.a) {
            this.b.refresh();
            this.a = false;
        }
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        i();
    }

    private void i() {
        this.b = (PtrLayout) findViewById(R.id.ptr);
        this.c = (ListView) findViewById(R.id.xListView);
        this.i = (LinearLayout) findViewById(R.id.empty_tips);
        this.b.setRefreshEnable(true);
        this.b.setLoadMoreEnable(false);
        this.b.setPtrListener(this);
        this.c.setOnItemClickListener(this);
        this.d = new UnSubscribeAdapter(this, this.e);
        this.c.setAdapter(this.d);
    }

    public void onRefresh() {
        if (this.i != null) {
            this.i.setVisibility(8);
        }
        this.f = 1;
        this.g = true;
        j();
    }

    public void onLoadMore() {
        j();
    }

    private void j() {
        if (this.g) {
            a(Constants.UNSUBSCRIBE_TA + "List", Constants.UNSUBSCRIBE_TA + "?count=" + 15 + "&page=" + this.f, null);
        }
    }

    private void a(String str, String str2, Map<String, Object> map) {
        HttpTask httpTask = new HttpTask(str, str2, new ady(this));
        if (map != null) {
            httpTask.setMapParams(map);
        }
        httpTask.execute(new Void[0]);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        MyInfoActivity.launch((Context) this, ((BaseUserInfo) this.d.getItem(i - this.c.getHeaderViewsCount())).userId, MyInfoActivity.FANS_ORIGINS[0]);
    }
}
