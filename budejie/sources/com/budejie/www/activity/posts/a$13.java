package com.budejie.www.activity.posts;

import android.text.TextUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import com.budejie.www.R;
import com.budejie.www.activity.video.k;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.util.aa;

class a$13 implements OnScrollListener {
    final /* synthetic */ a a;

    a$13(a aVar) {
        this.a = aVar;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 0) {
            if (absListView.getFirstVisiblePosition() == 0) {
                aa.a("XListView", "onscroll监听到滚到第一位");
                if (a.b(this.a).m.getVisibility() == 8) {
                    a.b(this.a).m.b();
                }
            }
            if (!k.a(a.b(this.a)).g && this.a.getUserVisibleHint()) {
                for (int firstVisiblePosition = absListView.getFirstVisiblePosition(); firstVisiblePosition <= absListView.getLastVisiblePosition(); firstVisiblePosition++) {
                    Object itemAtPosition = absListView.getItemAtPosition(firstVisiblePosition);
                    if (itemAtPosition instanceof ListItemObject) {
                        ListItemObject listItemObject = (ListItemObject) itemAtPosition;
                        if (listItemObject.isIs_ad() && "41".equals(listItemObject.getType()) && !TextUtils.isEmpty(listItemObject.getVideouri())) {
                            absListView.getChildAt(firstVisiblePosition - absListView.getFirstVisiblePosition()).findViewById(R.id.video_play_btn).performClick();
                            return;
                        }
                    }
                }
            }
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (k.a(a.b(this.a)).b() && this.a.getUserVisibleHint() && this.a.isResumed()) {
            k.a(a.b(this.a)).a(absListView.getFirstVisiblePosition(), absListView.getLastVisiblePosition(), ((ListView) absListView).getHeaderViewsCount());
        }
    }
}
