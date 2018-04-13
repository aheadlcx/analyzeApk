package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.message.e.b;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class g {
    private final List<a> a = new ArrayList();
    private final b b;

    public class a {
        final /* synthetic */ g a;
        private final List<Integer> b = new ArrayList();
        private final Executor c;

        public a(g gVar, int i) {
            this.a = gVar;
            this.c = com.liulishuo.filedownloader.g.b.a(1, "Flow-" + i);
        }

        public void a(int i) {
            this.b.add(Integer.valueOf(i));
        }

        public void a(final MessageSnapshot messageSnapshot) {
            this.c.execute(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    this.b.a.b.a(messageSnapshot);
                    this.b.b.remove(Integer.valueOf(messageSnapshot.m()));
                }
            });
        }
    }

    g(int i, b bVar) {
        this.b = bVar;
        for (int i2 = 0; i2 < i; i2++) {
            this.a.add(new a(this, i2));
        }
    }

    public void a(MessageSnapshot messageSnapshot) {
        a aVar = null;
        try {
            synchronized (this.a) {
                int m = messageSnapshot.m();
                for (a aVar2 : this.a) {
                    if (aVar2.b.contains(Integer.valueOf(m))) {
                        aVar = aVar2;
                        break;
                    }
                }
                if (aVar == null) {
                    int i = 0;
                    for (a aVar22 : this.a) {
                        if (aVar22.b.size() <= 0) {
                            aVar = aVar22;
                            break;
                        }
                        int i2;
                        a aVar3;
                        if (i != 0) {
                            if (aVar22.b.size() >= i) {
                                i2 = i;
                                aVar3 = aVar;
                                aVar = aVar3;
                                i = i2;
                            }
                        }
                        aVar3 = aVar22;
                        i2 = aVar22.b.size();
                        aVar = aVar3;
                        i = i2;
                    }
                }
                aVar.a(m);
            }
        } finally {
            aVar.a(messageSnapshot);
        }
    }
}
