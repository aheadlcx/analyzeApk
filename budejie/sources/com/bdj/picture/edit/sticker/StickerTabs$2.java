package com.bdj.picture.edit.sticker;

import android.view.View;
import android.view.View.OnClickListener;

class StickerTabs$2 implements OnClickListener {
    final /* synthetic */ StickerTabs a;

    StickerTabs$2(StickerTabs stickerTabs) {
        this.a = stickerTabs;
    }

    public void onClick(View view) {
        if (StickerTabs.b(this.a) != null) {
            StickerTabs.b(this.a).a(((Integer) view.getTag()).intValue());
        }
        StickerTabs.a(this.a, ((Integer) view.getTag()).intValue());
    }
}
