package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.g.d;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.e.b;
import java.util.List;

public class z implements b {
    private boolean a(List<a.b> list, MessageSnapshot messageSnapshot) {
        a.b bVar;
        if (list.size() > 1 && messageSnapshot.b() == (byte) -3) {
            for (a.b bVar2 : list) {
                synchronized (bVar2.H()) {
                    if (bVar2.z().c(messageSnapshot)) {
                        d.c(this, "updateMoreLikelyCompleted", new Object[0]);
                        return true;
                    }
                }
            }
        }
        for (a.b bVar22 : list) {
            synchronized (bVar22.H()) {
                if (bVar22.z().b(messageSnapshot)) {
                    d.c(this, "updateKeepFlow", new Object[0]);
                    return true;
                }
            }
        }
        if ((byte) -4 == messageSnapshot.b()) {
            for (a.b bVar222 : list) {
                synchronized (bVar222.H()) {
                    if (bVar222.z().d(messageSnapshot)) {
                        d.c(this, "updateSampleFilePathTaskRunning", new Object[0]);
                        return true;
                    }
                }
            }
        }
        if (list.size() != 1) {
            return false;
        }
        boolean a;
        bVar222 = (a.b) list.get(0);
        synchronized (bVar222.H()) {
            d.c(this, "updateKeepAhead", new Object[0]);
            a = bVar222.z().a(messageSnapshot);
        }
        return a;
    }

    public void a(MessageSnapshot messageSnapshot) {
        synchronized (Integer.toString(messageSnapshot.m()).intern()) {
            List<a.b> c = h.a().c(messageSnapshot.m());
            if (c.size() > 0) {
                a y = ((a.b) c.get(0)).y();
                if (d.a) {
                    d.c(this, "~~~callback %s old[%s] new[%s] %d", Integer.valueOf(messageSnapshot.m()), Byte.valueOf(y.q()), Byte.valueOf(messageSnapshot.b()), Integer.valueOf(c.size()));
                }
                if (!a(c, messageSnapshot)) {
                    StringBuilder stringBuilder = new StringBuilder("The event isn't consumed, id:" + messageSnapshot.m() + " status:" + messageSnapshot.b() + " task-count:" + c.size());
                    for (a.b y2 : c) {
                        stringBuilder.append(" | ").append(y2.y().q());
                    }
                    d.b(this, stringBuilder.toString(), new Object[0]);
                }
            } else {
                d.b(this, "Receive the event %d, but there isn't any running task in the upper layer", Byte.valueOf(messageSnapshot.b()));
            }
        }
    }
}
