package com.budejie.www.activity.search;

import android.view.View;
import android.view.View.OnClickListener;
import com.budejie.www.R;
import com.umeng.analytics.MobclickAgent;

class c$4 implements OnClickListener {
    final /* synthetic */ c a;

    c$4(c cVar) {
        this.a = cVar;
    }

    public void onClick(View view) {
        String str = "";
        switch (view.getId()) {
            case R.id.SearchDelHisLayout:
                if (this.a.g != null) {
                    this.a.g.a(1, "", false);
                    break;
                }
                break;
            case R.id.HisTextView1:
                MobclickAgent.onEvent(this.a.getActivity(), "E06_A18", "历史搜索点击");
                str = c.c(this.a).getText().toString();
                break;
            case R.id.HisTextView2:
                MobclickAgent.onEvent(this.a.getActivity(), "E06_A18", "历史搜索点击");
                str = c.d(this.a).getText().toString();
                break;
            case R.id.HisTextView3:
                MobclickAgent.onEvent(this.a.getActivity(), "E06_A18", "历史搜索点击");
                str = c.e(this.a).getText().toString();
                break;
        }
        this.a.b(str);
        if (this.a.g != null) {
            this.a.g.a(0, str, false);
        }
    }
}
