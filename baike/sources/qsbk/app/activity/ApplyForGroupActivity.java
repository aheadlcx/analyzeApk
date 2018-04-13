package qsbk.app.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import qsbk.app.Constants;
import qsbk.app.QsbkApp;
import qsbk.app.R;
import qsbk.app.activity.base.BaseActionBarActivity;
import qsbk.app.http.SimpleHttpTask;
import qsbk.app.model.GroupInfo;
import qsbk.app.model.GroupRecommend;
import qsbk.app.model.GroupRecommend.GroupItem;
import qsbk.app.utils.GroupActionUtil;
import qsbk.app.utils.UIHelper;

public class ApplyForGroupActivity extends BaseActionBarActivity {
    public static final String CAN_JOIN_NUM = "canJoinNum";
    public static final int INVALID_ID = -1;
    public static final String JOINED_NUM = "joinedNum";
    public static final int REQ_APPLY = 156;
    private static GroupInfo a = null;
    private GroupInfo b;
    private int c;
    private EditText d;
    private TextView e;
    private int f;
    private int g;
    private TextView h;

    protected /* synthetic */ CharSequence getCustomTitle() {
        return c();
    }

    public static void launchForResult(Fragment fragment, GroupInfo groupInfo) {
        launchIfAllowed(null, fragment, groupInfo, -1, -1);
    }

    public static void launchForResult(Fragment fragment, GroupInfo groupInfo, int i) {
        launchIfAllowed(null, fragment, groupInfo, i, -1);
    }

    public static void launchForResult(Context context, GroupInfo groupInfo, int i) {
        launchIfAllowed(context, null, groupInfo, i, -1);
    }

    public static void launchIfAllowed(Context context, Fragment fragment, GroupInfo groupInfo, int i, int i2) {
        Context activity = fragment == null ? context : fragment.getActivity();
        if (QsbkApp.checkLogin(activity, true, true)) {
            new ProgressDialog(activity).setCancelable(false);
            new SimpleHttpTask(String.format(Constants.URL_APPLY_FOR_GROUP, new Object[]{Integer.valueOf(groupInfo.id)}) + "?tid=" + groupInfo.id, new ay(context, fragment, groupInfo, i, i2)).execute();
        }
    }

    private static void b(Context context, Fragment fragment, GroupInfo groupInfo, int i, int i2, int i3) {
        Intent intent = new Intent(context, ApplyForGroupActivity.class);
        a = groupInfo;
        intent.putExtra("from_id", i);
        intent.putExtra(JOINED_NUM, i2);
        intent.putExtra(CAN_JOIN_NUM, i3);
        if (fragment != null) {
            fragment.startActivityForResult(intent, REQ_APPLY);
        } else if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, REQ_APPLY);
        } else {
            context.startActivity(intent);
        }
    }

    protected void c_() {
        if (UIHelper.isNightTheme()) {
            setTheme(R.style.Night);
        } else {
            setTheme(R.style.Day.GroupInfo);
        }
    }

    protected void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
    }

    protected String c() {
        return null;
    }

    protected int a() {
        return R.layout.activity_apply_for_group;
    }

    protected void a(Bundle bundle) {
        Intent intent = getIntent();
        this.b = a;
        a = null;
        this.c = intent.getIntExtra("from_id", -1);
        this.f = intent.getIntExtra(JOINED_NUM, 0);
        this.g = intent.getIntExtra(CAN_JOIN_NUM, 0);
        f();
    }

    private void f() {
        View findViewById = findViewById(R.id.apply_group);
        if (findViewById != null) {
            findViewById.setBackgroundColor(UIHelper.isNightTheme() ? UIHelper.getColor(R.color.main_bg_night) : UIHelper.getColor(R.color.white));
        }
        this.d = (EditText) findViewById(R.id.content);
        this.e = (TextView) findViewById(R.id.content_left);
        this.h = (TextView) findViewById(R.id.prompt);
        this.h.setVisibility(0);
        this.h.setTextColor(UIHelper.isNightTheme() ? -12171438 : -4802890);
        this.h.setText("你当前已经加入" + this.f + "个群，还能加入" + this.g + "个群");
        this.e.setText("30");
        this.d.addTextChangedListener(new bd(this));
        findViewById(R.id.send).setOnClickListener(new be(this));
    }

    public void onBackPressed() {
        setResult(-1);
        super.onBackPressed();
    }

    private void a(String str) {
        if (this.b != null) {
            GroupActionUtil.applyForGroup(str, this.b.id, this.c, new bf(this, this, "申请中..."));
        }
    }

    private void a(int i, int i2) {
        GroupItem groupItem;
        GroupRecommend instance = GroupRecommend.getInstance();
        if (instance != null && instance.groups != null && instance.groups.size() > 0) {
            for (int i3 = 0; i3 < instance.groups.size(); i3++) {
                groupItem = (GroupItem) instance.groups.get(i3);
                if (groupItem.id == i) {
                    groupItem.joinStatus = i2;
                    GroupRecommend.refresh(i3);
                    break;
                }
            }
        }
        ArrayList load = GroupRecommend.load();
        if (load != null) {
            Iterator it = load.iterator();
            while (it.hasNext()) {
                groupItem = (GroupItem) it.next();
                if (groupItem.id == i) {
                    groupItem.joinStatus = i2;
                }
            }
            GroupRecommend.save(load);
        }
    }
}
