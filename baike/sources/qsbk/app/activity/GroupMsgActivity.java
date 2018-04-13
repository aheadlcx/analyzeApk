package qsbk.app.activity;

import android.os.Bundle;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.baidu.mobstat.Config;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.Constants;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.http.HttpTask;
import qsbk.app.model.GroupInfo;
import qsbk.app.utils.GroupMsgUtils;
import qsbk.app.utils.JoinedGroupGetter;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.Switch;

public class GroupMsgActivity extends BaseActionBarActivity implements PtrListener {
    private static int a = 1;
    private static int b = 5;
    private HttpTask c;
    private SparseIntArray d = new SparseIntArray();
    private Switch e;
    private int f = 1;
    private GroupNoteAdapter g;
    private PtrLayout h;
    private ListView i;
    private ArrayList<Object> j;
    private Pair<Integer, Integer> k;
    private SparseArray<GroupInfo> l = new SparseArray();

    public class GroupNoteAdapter extends BaseImageAdapter {
        final /* synthetic */ GroupMsgActivity a;

        public GroupNoteAdapter(GroupMsgActivity groupMsgActivity, ArrayList<Object> arrayList) {
            this.a = groupMsgActivity;
            super(arrayList, groupMsgActivity);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            GroupInfo groupInfo;
            if (view == null) {
                a aVar2 = new a(this.a);
                view = LayoutInflater.from(this.a).inflate(R.layout.group_msg_item, null);
                aVar2.joinGroupImg = (ImageView) view.findViewById(R.id.msg_join_group_img);
                aVar2.joinGroupName = (TextView) view.findViewById(R.id.msg_join_group_name);
                aVar2.joinGroupSwitch = (Switch) view.findViewById(R.id.msg_join_group_switch);
                view.setTag(aVar2);
                aVar = aVar2;
            } else {
                aVar = (a) view.getTag();
            }
            Pair pair = (Pair) getItem(i);
            if (pair == null || this.a.l.get(((Integer) pair.first).intValue()) == null) {
                groupInfo = null;
            } else {
                groupInfo = (GroupInfo) this.a.l.get(((Integer) pair.first).intValue());
            }
            if (groupInfo != null) {
                b(aVar.joinGroupImg, groupInfo.icon);
                aVar.joinGroupName.setText(groupInfo.name);
                if (((Integer) pair.second).intValue() == 1) {
                    aVar.joinGroupSwitch.setChecked(true);
                } else {
                    aVar.joinGroupSwitch.setChecked(false);
                }
                aVar.joinGroupSwitch.setOnClickListener(new nh(this, aVar, i, pair));
            }
            return view;
        }
    }

    class a {
        final /* synthetic */ GroupMsgActivity a;
        public ImageView joinGroupImg;
        public TextView joinGroupName;
        public Switch joinGroupSwitch;

        a(GroupMsgActivity groupMsgActivity) {
            this.a = groupMsgActivity;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return e();
    }

    protected String e() {
        return "群消息提醒";
    }

    protected int a() {
        return R.layout.activity_group_msg;
    }

    protected boolean d() {
        return true;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        f();
        g();
    }

    private void f() {
        i();
        this.e = (Switch) findViewById(R.id.msg_master_switch);
        this.h = (PtrLayout) findViewById(R.id.ptr);
        this.i = (ListView) findViewById(R.id.listview);
        this.h.setLoadMoreEnable(true);
        this.h.setPtrListener(this);
        this.j = new ArrayList();
        this.g = new GroupNoteAdapter(this, this.j);
        this.i.setAdapter(this.g);
        this.h.loadMore();
        this.e.setOnClickListener(new nd(this));
    }

    private void g() {
    }

    private void i() {
        showLoading();
        JoinedGroupGetter.getJoinedGroups(new ne(this));
    }

    private void j() {
        String format = String.format(Constants.URL_SET_GROUP_MSG_SWITCH + "?page=%d", new Object[]{Integer.valueOf(this.f)});
        this.c = new HttpTask(format, format, new nf(this));
        this.c.execute(new Void[0]);
    }

    public void onRefresh() {
        if (this.c != null) {
            this.c.cancel(true);
            this.c = null;
        }
        this.f = 1;
        j();
    }

    public void onLoadMore() {
        if (this.c != null) {
            this.c.cancel(true);
            this.c = null;
        }
        this.f = 1;
        j();
    }

    protected void onStop() {
        String str = Constants.URL_SET_GROUP_MSG_SWITCH;
        Map hashMap = new HashMap();
        GroupMsgUtils.setOpenable(this.j);
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < this.j.size(); i++) {
            Pair pair = (Pair) this.j.get(i);
            if (this.d.get(((Integer) pair.first).intValue()) != ((Integer) pair.second).intValue()) {
                stringBuffer.append(pair.first + Config.TRACE_TODAY_VISIT_SPLIT + pair.second + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            hashMap.put("sws", stringBuffer.toString().toLowerCase());
            this.c = new HttpTask(str, str, new ng(this));
            this.c.setMapParams(hashMap);
            this.c.execute(new Void[0]);
        }
        finish();
        super.onStop();
    }
}
