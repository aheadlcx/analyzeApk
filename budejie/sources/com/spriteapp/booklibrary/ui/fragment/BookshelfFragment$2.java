package com.spriteapp.booklibrary.ui.fragment;

import android.os.Handler;
import android.os.Message;
import com.spriteapp.booklibrary.enumeration.UpdaterShelfEnum;
import de.greenrobot.event.EventBus;

class BookshelfFragment$2 extends Handler {
    BookshelfFragment$2() {
    }

    public void handleMessage(Message message) {
        EventBus.getDefault().post(UpdaterShelfEnum.UPDATE_SHELF);
    }
}
