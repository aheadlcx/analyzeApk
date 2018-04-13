package com.budejie.www.busevent;

import android.view.View;
import com.budejie.www.bean.RecommendSubscription;

public final class LabelEvent {
    public LabelAction a;
    public RecommendSubscription b;
    public View c;

    public enum LabelAction {
        SUBSCRIBE,
        UNSUBSCRIBE
    }

    public LabelEvent(LabelAction labelAction, RecommendSubscription recommendSubscription) {
        this(labelAction, recommendSubscription, null);
    }

    public LabelEvent(LabelAction labelAction, RecommendSubscription recommendSubscription, View view) {
        this.a = labelAction;
        this.b = recommendSubscription;
        this.c = view;
    }
}
