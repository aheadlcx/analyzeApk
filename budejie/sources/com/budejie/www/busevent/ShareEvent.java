package com.budejie.www.busevent;

import com.budejie.www.bean.ListItemObject;

public final class ShareEvent {
    public String a;
    public ShareAction b;
    public ListItemObject c;
    public boolean d;

    public enum ShareAction {
        QQ,
        QZONE,
        WXFRIEND,
        WXP,
        SMS,
        CANCLE
    }

    public ShareEvent(ShareAction shareAction, String str) {
        this.b = shareAction;
        this.a = str;
    }

    public ShareEvent(ShareAction shareAction, ListItemObject listItemObject, String str, boolean z) {
        this.b = shareAction;
        this.a = str;
        this.c = listItemObject;
        this.d = z;
    }

    public ShareEvent(ShareAction shareAction, ListItemObject listItemObject) {
        this.b = shareAction;
        this.c = listItemObject;
    }
}
