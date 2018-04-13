package com.budejie.www.activity.auditpost;

import android.app.Activity;
import com.budejie.www.bean.TouGaoItem;

public class e {
    public static d<TouGaoItem> a(Activity activity, HeadViewType headViewType, TouGaoItem touGaoItem) {
        switch (headViewType) {
            case TEXT_VIEW:
                return new g(activity, touGaoItem);
            case IMAGE_VIEW:
            case GIF_VIEW:
                return new f(activity, touGaoItem);
            case VOICE_VIEW:
                return new i(activity, touGaoItem);
            case VIDEO_VIEW:
                return new h(activity, touGaoItem);
            default:
                return null;
        }
    }
}
