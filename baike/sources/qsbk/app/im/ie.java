package qsbk.app.im;

import qsbk.app.utils.GroupMsgUtils;

class ie implements Runnable {
    final /* synthetic */ MessageCountManager a;

    ie(MessageCountManager messageCountManager) {
        this.a = messageCountManager;
    }

    public void run() {
        int totalUnReadCount = this.a.b.getTotalUnReadCount();
        int totalUnReadCount2 = this.a.d.getTotalUnReadCount();
        int i = 0;
        int i2 = 0;
        for (int[] iArr : this.a.c.getUnReadCounts()) {
            if (GroupMsgUtils.has(String.valueOf(iArr[0]))) {
                int i3;
                if (GroupMsgUtils.isOpen(String.valueOf(iArr[0]), false)) {
                    i2 += iArr[1];
                    i3 = i;
                    i = i2;
                } else {
                    i3 = iArr[1] + i;
                    i = i2;
                }
                i2 = i;
                i = i3;
            }
        }
        int[] iArr2 = new int[]{(i2 + totalUnReadCount) + totalUnReadCount2, i};
        this.a.f = Integer.valueOf(iArr2[0]);
        this.a.g = iArr2[1];
        if (this.a.j) {
            this.a.notifyListener(this.a.f.intValue(), this.a.g);
        }
        a();
    }

    private void a() {
        synchronized (this.a.k) {
            this.a.k.notify();
        }
    }
}
