package com.budejie.www.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.budejie.www.R;
import com.budejie.www.activity.base.BaseTitleActivity;
import com.budejie.www.adapter.a.a;
import com.budejie.www.bean.LabelUser;
import com.budejie.www.bean.UserRanking;
import com.budejie.www.util.an;
import java.util.ArrayList;
import java.util.Iterator;

public class ContributeListActivity extends BaseTitleActivity {
    private final int a = 1;
    private ListView b;
    private a c;
    private UserRanking d;
    private Activity e;
    private OnItemClickListener i = new OnItemClickListener(this) {
        final /* synthetic */ ContributeListActivity a;

        {
            this.a = r1;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                if (this.a.b.getItemAtPosition(i) instanceof LabelUser) {
                    an.b(this.a.e, ((LabelUser) this.a.b.getItemAtPosition(i)).getUid(), 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e(R.layout.contribute_list_layout);
        this.e = this;
        a("标签贡献榜单");
        this.d = (UserRanking) getIntent().getSerializableExtra("data");
        a();
    }

    private void a() {
        this.b = (ListView) findViewById(R.id.listview);
        this.c = new a(this, this.d);
        this.b.setAdapter(this.c);
        this.b.setOnItemClickListener(this.i);
    }

    public void onLeftClick(View view) {
        setResult(5325, new Intent().putExtra("data", this.d));
        super.onLeftClick(view);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        setResult(5325, new Intent().putExtra("data", this.d));
        finish();
        return false;
    }

    protected void onResume() {
        super.onResume();
        if (this.c != null) {
            this.c.notifyDataSetChanged();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == 2) {
            CharSequence stringExtra = intent.getStringExtra(UserTrackerConstants.USER_ID);
            if (!TextUtils.isEmpty(stringExtra)) {
                LabelUser labelUser;
                ArrayList arrayList = (ArrayList) this.d.getRecomList();
                ArrayList arrayList2 = (ArrayList) this.d.getTopList();
                String stringExtra2 = intent.getStringExtra("follow_status");
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    labelUser = (LabelUser) it.next();
                    if (labelUser.getUid().equals(stringExtra)) {
                        labelUser.setIs_follow(stringExtra2);
                    }
                }
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    labelUser = (LabelUser) it2.next();
                    if (labelUser.getUid().equals(stringExtra)) {
                        labelUser.setIs_follow(stringExtra2);
                    }
                }
                if (this.c != null) {
                    this.c.notifyDataSetChanged();
                }
            }
        }
    }
}
