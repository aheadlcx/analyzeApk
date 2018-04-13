package com.bdj.picture.edit.sticker;

import android.os.Handler;
import android.os.Message;
import android.widget.RadioButton;

class StickerTabs$1 extends Handler {
    final /* synthetic */ StickerTabs a;

    StickerTabs$1(StickerTabs stickerTabs) {
        this.a = stickerTabs;
    }

    public void handleMessage(Message message) {
        RadioButton radioButton = (RadioButton) StickerTabs.a(this.a).getChildAt(message.what);
        if (radioButton != null) {
            radioButton.setChecked(true);
        }
        StickerTabs.a(this.a, message.what);
    }
}
