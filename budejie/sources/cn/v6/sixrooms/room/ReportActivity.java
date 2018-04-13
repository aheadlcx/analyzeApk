package cn.v6.sixrooms.room;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.room.engine.ReportUserEngine;
import cn.v6.sixrooms.ui.phone.BaseFragmentActivity;
import cn.v6.sixrooms.ui.phone.EventActivity;
import cn.v6.sixrooms.utils.LoginUtils;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.ToastUtils;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;

public class ReportActivity extends BaseFragmentActivity implements OnClickListener {
    private final String a = "http://jb.ccm.gov.cn/";
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private Drawable g;
    private Drawable h;
    private ReportUserEngine i;
    private String j = "";
    private String k;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.phone_activity_report);
        initDefaultTitleBar(null, getResources().getDrawable(R.drawable.default_titlebar_back_selector), "提交", null, "举报", this, this);
        this.b = (TextView) findViewById(R.id.report_type_1);
        this.c = (TextView) findViewById(R.id.report_type_2);
        this.d = (TextView) findViewById(R.id.report_type_3);
        this.e = (TextView) findViewById(R.id.report_type_4);
        this.f = (TextView) findViewById(R.id.report_type_5);
        this.k = getIntent().getStringExtra(HistoryOpenHelper.COLUMN_UID);
        this.g = getResources().getDrawable(R.drawable.report_checked);
        this.g.setBounds(0, 0, this.g.getMinimumWidth(), this.g.getMinimumHeight());
        this.h = getResources().getDrawable(R.drawable.transparent_line);
        this.h.setBounds(0, 0, this.h.getMinimumWidth(), this.h.getMinimumHeight());
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.titlebar_left_frame) {
            finish();
        } else if (id == R.id.titlebar_right_frame) {
            if (TextUtils.isEmpty(this.j)) {
                ToastUtils.showToast("请先选择举报类型");
                return;
            }
            if (this.i == null) {
                this.i = new ReportUserEngine(new t(this));
            }
            this.i.reportUser(this.k, this.j, LoginUtils.getLoginUID(), SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
        } else if (id == R.id.report_type_1) {
            selectType(true, false, false, false);
            this.j = "1";
        } else if (id == R.id.report_type_2) {
            selectType(false, true, false, false);
            this.j = "2";
        } else if (id == R.id.report_type_3) {
            selectType(false, false, true, false);
            this.j = "3";
        } else if (id == R.id.report_type_4) {
            selectType(false, false, false, true);
            this.j = "4";
        } else if (id == R.id.report_type_5) {
            Bundle bundle = new Bundle();
            bundle.putString("eventurl", "http://jb.ccm.gov.cn/");
            bundle.putString("eventUrlFrom", EventActivity.GOV_REPORT_EVENT);
            Intent intent = new Intent(this, EventActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public void selectType(boolean z, boolean z2, boolean z3, boolean z4) {
        this.b.setCompoundDrawables(null, null, z ? this.g : this.h, null);
        this.c.setCompoundDrawables(null, null, z2 ? this.g : this.h, null);
        this.d.setCompoundDrawables(null, null, z3 ? this.g : this.h, null);
        this.e.setCompoundDrawables(null, null, z4 ? this.g : this.h, null);
    }
}
