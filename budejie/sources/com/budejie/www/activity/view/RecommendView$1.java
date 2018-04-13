package com.budejie.www.activity.view;

import android.os.Handler;
import android.os.Message;
import com.budejie.www.bean.SuggestedFollowsListItem;

class RecommendView$1 extends Handler {
    final /* synthetic */ RecommendView a;

    RecommendView$1(RecommendView recommendView) {
        this.a = recommendView;
    }

    public void handleMessage(Message message) {
        SuggestedFollowsListItem suggestedFollowsListItem;
        if (message.what == 100) {
            if (message.obj != null) {
                suggestedFollowsListItem = (SuggestedFollowsListItem) message.obj;
                if (suggestedFollowsListItem != null && RecommendView.a(this.a) != null && suggestedFollowsListItem.uid.equals(RecommendView.a(this.a).uid)) {
                    if (RecommendView.a.isEmpty()) {
                        this.a.a(suggestedFollowsListItem.is_follow);
                    } else {
                        this.a.a();
                    }
                }
            }
        } else if (message.what == 101 && message.obj != null) {
            suggestedFollowsListItem = (SuggestedFollowsListItem) message.obj;
            if (suggestedFollowsListItem != null && RecommendView.a(this.a) != null && suggestedFollowsListItem.uid.equals(RecommendView.a(this.a).uid)) {
                RecommendView.a(this.a, suggestedFollowsListItem.is_follow);
                if (!RecommendView.a.isEmpty() && suggestedFollowsListItem.is_follow == 1) {
                    this.a.a();
                }
            }
        }
    }
}
