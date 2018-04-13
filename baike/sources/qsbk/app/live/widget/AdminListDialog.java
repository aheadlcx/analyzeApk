package qsbk.app.live.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.ui.base.BaseActivity;
import qsbk.app.core.widget.BaseDialog;
import qsbk.app.core.widget.EmptyPlaceholderView;
import qsbk.app.live.R;
import qsbk.app.live.adapter.AdminAdapter;
import qsbk.app.live.model.LiveAdmin;

public class AdminListDialog extends BaseDialog {
    private TextView c;
    private EmptyPlaceholderView d;
    private RecyclerView e;
    private LinearLayoutManager f;
    private AdminAdapter g;
    private long h;
    private ArrayList<LiveAdmin> i = new ArrayList();
    private int j = 2;

    public AdminListDialog(Context context, long j) {
        super(context);
        this.h = j;
    }

    protected int c() {
        return R.layout.live_admin_list_view;
    }

    protected void d() {
        this.c = (TextView) findViewById(R.id.tv_title);
        this.d = (EmptyPlaceholderView) findViewById(R.id.empty);
        this.f = new LinearLayoutManager(this.a);
        this.e = (RecyclerView) findViewById(R.id.recyclerview);
        this.e.setLayoutManager(this.f);
    }

    protected void e() {
        refreshTitle();
        this.g = new AdminAdapter(this, (BaseActivity) this.a, this.i, this.h);
        this.e.setAdapter(this.g);
        loadData();
    }

    public void loadData() {
        NetRequest.getInstance().get(UrlConstants.LIVE_ADMIN_LIST, new a(this));
    }

    public void refreshEmptyView() {
        this.d.setEmptyContent(size() > 0);
    }

    public void refreshTitle() {
        this.c.setText("我的管理员 " + size() + MqttTopic.TOPIC_LEVEL_SEPARATOR + this.j);
    }

    public void refreshData(List<LiveAdmin> list) {
        this.i.clear();
        if (list != null) {
            this.i.addAll(list);
        }
        this.g.notifyDataSetChanged();
    }

    public int size() {
        return this.i != null ? this.i.size() : 0;
    }
}
