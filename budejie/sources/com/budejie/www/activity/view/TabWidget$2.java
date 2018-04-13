package com.budejie.www.activity.view;

import android.content.Intent;
import android.net.Uri;
import android.widget.RelativeLayout;
import com.budejie.www.activity.htmlpage.NoViewActivity;
import com.budejie.www.activity.view.MyViewPager.a;
import com.budejie.www.bean.Topic;
import com.umeng.analytics.MobclickAgent;

class TabWidget$2 implements a {
    final /* synthetic */ TabWidget a;

    TabWidget$2(TabWidget tabWidget) {
        this.a = tabWidget;
    }

    public void a(int i) {
        RelativeLayout relativeLayout = (RelativeLayout) TabWidget.f(this.a).get(i % TabWidget.a(this.a));
        if (relativeLayout != null) {
            Topic topic = (Topic) relativeLayout.getTag();
            MobclickAgent.onEvent(TabWidget.g(this.a), "帖子流推广位跳转", topic.id + "");
            TabWidget.g(this.a).startActivity(new Intent(TabWidget.g(this.a), NoViewActivity.class).setData(Uri.parse(topic.url)).addFlags(268435456));
        }
    }
}
