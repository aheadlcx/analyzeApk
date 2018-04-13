package qsbk.app.core.utils;

import qsbk.app.core.R;

final class v implements Runnable {
    v() {
    }

    public void run() {
        ToastUtil.Long(R.string.screenshot_save_success);
    }
}
