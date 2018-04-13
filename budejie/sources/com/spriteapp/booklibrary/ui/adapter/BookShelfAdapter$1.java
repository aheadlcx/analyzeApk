package com.spriteapp.booklibrary.ui.adapter;

import android.os.Handler;
import android.os.Message;
import com.spriteapp.booklibrary.enumeration.UpdaterShelfEnum;
import de.greenrobot.event.EventBus;

class BookShelfAdapter$1 extends Handler {
    BookShelfAdapter$1() {
    }

    public void handleMessage(Message message) {
        EventBus.getDefault().post(UpdaterShelfEnum.UPDATE_SHELF);
    }
}
