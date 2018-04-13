package qsbk.app.core.utils;

import qsbk.app.core.R;

final class w implements Runnable {
    w() {
    }

    public void run() {
        ToastUtil.Long(R.string.screenshot_save_fail);
    }
}
