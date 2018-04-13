package qsbk.app.activity;

import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import qsbk.app.utils.JoinedGroupGetter;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.TemporaryNoteUtils;
import qsbk.app.utils.UIHelper;
import qsbk.app.widget.PtrLayout;
import qsbk.app.widget.PtrLayout.PtrListener;
import qsbk.app.widget.Switch;

public class TemporaryNoteActivity extends BaseActionBarActivity implements PtrListener {
    private static int b = 1;
    private static int c = 5;
    SparseArray<GroupInfo> a = new SparseArray();
    private HttpTask d;
    private SparseIntArray e = new SparseIntArray();
    private Switch f;
    private RelativeLayout g;
    private TextView h;
    private View i;
    private TextView j;
    private TextView k;
    private View l;
    private int m = 1;
    private GroupNoteAdapter n;
    private PtrLayout o;
    private ListView p;
    private ArrayList<Object> q;
    private Pair<Integer, Integer> r;

    public class GroupNoteAdapter extends BaseImageAdapter {
        final /* synthetic */ TemporaryNoteActivity a;

        public GroupNoteAdapter(TemporaryNoteActivity temporaryNoteActivity, ArrayList<Object> arrayList) {
            this.a = temporaryNoteActivity;
            super(arrayList, temporaryNoteActivity);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            GroupInfo groupInfo;
            if (view == null) {
                a aVar2 = new a(this.a);
                view = LayoutInflater.from(this.a).inflate(R.layout.temporaty_note_item, null);
                aVar2.joinGroupImg = (ImageView) view.findViewById(R.id.join_group_img);
                aVar2.joinGroupName = (TextView) view.findViewById(R.id.join_group_name);
                aVar2.joinGroupSwitch = (Switch) view.findViewById(R.id.join_group_switch);
                aVar2.tem_note_item_lin = (LinearLayout) view.findViewById(R.id.tem_note_item_lin);
                aVar2.temporaty_note_view = view.findViewById(R.id.temporaty_note_view);
                view.setTag(aVar2);
                aVar = aVar2;
            } else {
                aVar = (a) view.getTag();
            }
            aVar.tem_note_item_lin.setBackgroundColor(UIHelper.isNightTheme() ? -14803421 : -1184275);
            aVar.temporaty_note_view.setBackgroundColor(UIHelper.isNightTheme() ? -15198180 : -3355444);
            aVar.joinGroupName.setTextColor(UIHelper.isNightTheme() ? -9802626 : -12894910);
            Pair pair = (Pair) getItem(i);
            LogUtil.d("groupId===================" + pair.first);
            if (this.a.a.get(((Integer) pair.first).intValue()) != null) {
                groupInfo = (GroupInfo) this.a.a.get(((Integer) pair.first).intValue());
            } else {
                groupInfo = null;
            }
            if (groupInfo != null) {
                if (TextUtils.isEmpty(groupInfo.icon)) {
                    aVar.joinGroupImg.setImageResource(UIHelper.getDefaultAvatar());
                } else {
                    b(aVar.joinGroupImg, groupInfo.icon);
                }
                aVar.joinGroupName.setText(groupInfo.name);
                if (((Integer) pair.second).intValue() == 1) {
                    aVar.joinGroupSwitch.setChecked(true);
                } else {
                    aVar.joinGroupSwitch.setChecked(false);
                }
                aVar.joinGroupSwitch.setOnClickListener(new adm(this, aVar, i, pair));
            }
            return view;
        }
    }

    class a {
        final /* synthetic */ TemporaryNoteActivity a;
        public ImageView joinGroupImg;
        public TextView joinGroupName;
        public Switch joinGroupSwitch;
        public LinearLayout tem_note_item_lin;
        public View temporaty_note_view;

        a(TemporaryNoteActivity temporaryNoteActivity) {
            this.a = temporaryNoteActivity;
        }
    }

    protected /* synthetic */ CharSequence getCustomTitle() {
        return f();
    }

    protected String f() {
        return "接收群临时小纸条";
    }

    protected int a() {
        return R.layout.activity_temporary_note;
    }

    protected void a(Bundle bundle) {
        setActionbarBackable();
        g();
        i();
    }

    private void g() {
        int i;
        int i2 = -12171438;
        int i3 = -15198180;
        this.o = (PtrLayout) findViewById(R.id.ptr);
        this.p = (ListView) findViewById(R.id.listview);
        this.o.setLoadMoreEnable(false);
        this.o.setPtrListener(this);
        this.q = new ArrayList();
        this.n = new GroupNoteAdapter(this, this.q);
        this.p.setAdapter(this.n);
        j();
        this.g = (RelativeLayout) findViewById(R.id.temporary_note_rel);
        this.h = (TextView) findViewById(R.id.total_open);
        this.i = findViewById(R.id.head_dividing_line);
        this.j = (TextView) findViewById(R.id.group_tem_remind);
        this.k = (TextView) findViewById(R.id.group_divider);
        this.l = findViewById(R.id.body_dividing_line);
        this.h.setTextColor(UIHelper.isNightTheme() ? -12171438 : -6908266);
        this.j.setTextColor(UIHelper.isNightTheme() ? -9802626 : -10263970);
        TextView textView = this.k;
        if (!UIHelper.isNightTheme()) {
            i2 = -6908266;
        }
        textView.setTextColor(i2);
        View view = this.i;
        if (UIHelper.isNightTheme()) {
            i = -15198180;
        } else {
            i = -3355444;
        }
        view.setBackgroundColor(i);
        View view2 = this.l;
        if (!UIHelper.isNightTheme()) {
            i3 = -3355444;
        }
        view2.setBackgroundColor(i3);
        this.f = (Switch) findViewById(R.id.master_switch);
        this.f.setOnClickListener(new adi(this));
    }

    private void i() {
    }

    protected boolean d() {
        return true;
    }

    private void j() {
        showLoading();
        JoinedGroupGetter.getJoinedGroups(new adj(this));
    }

    private void k() {
        String format = String.format(Constants.URL_SET_GROUP_MSG_SWITCH_TEMP + "?page=%d", new Object[]{Integer.valueOf(this.m)});
        this.d = new HttpTask(format, format, new adk(this));
        this.d.execute(new Void[0]);
    }

    public void onRefresh() {
        if (this.d != null) {
            this.d.cancel(true);
            this.d = null;
        }
        this.m = 1;
        k();
    }

    public void onLoadMore() {
        if (this.d != null) {
            this.d.cancel(true);
            this.d = null;
        }
        this.m = 1;
        k();
    }

    protected void onStop() {
        String str = Constants.URL_SET_GROUP_MSG_SWITCH_TEMP;
        Map hashMap = new HashMap();
        StringBuffer stringBuffer = new StringBuffer("");
        Editor edit = TemporaryNoteUtils.getPreferences().edit();
        for (int i = 0; i < this.q.size(); i++) {
            boolean z;
            Pair pair = (Pair) this.q.get(i);
            String valueOf = String.valueOf(pair.first);
            if (((Integer) pair.second).intValue() != 0) {
                z = true;
            } else {
                z = false;
            }
            edit.putBoolean(valueOf, z);
            if (this.e.get(((Integer) pair.first).intValue()) != ((Integer) pair.second).intValue()) {
                stringBuffer.append(pair.first + Config.TRACE_TODAY_VISIT_SPLIT + pair.second + com.xiaomi.mipush.sdk.Constants.ACCEPT_TIME_SEPARATOR_SP);
            }
        }
        edit.apply();
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            String toLowerCase = stringBuffer.toString().toLowerCase();
            LogUtil.d("jsonData =====" + toLowerCase);
            hashMap.put("sws", toLowerCase);
            this.d = new HttpTask(str, str, new adl(this));
            this.d.setMapParams(hashMap);
            this.d.execute(new Void[0]);
        }
        finish();
        super.onStop();
    }
}
