package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.d.c;
import com.liulishuo.filedownloader.download.e;
import com.liulishuo.filedownloader.message.d.a;
import com.liulishuo.filedownloader.message.d.b;
import com.liulishuo.filedownloader.message.d.d;
import com.liulishuo.filedownloader.message.d.g;
import com.liulishuo.filedownloader.message.d.h;
import com.liulishuo.filedownloader.message.d.i;
import java.io.File;

public class f {
    public static MessageSnapshot a(int i, File file, boolean z) {
        long length = file.length();
        if (length > 2147483647L) {
            if (z) {
                return new a(i, true, length);
            }
            return new b(i, true, length);
        } else if (z) {
            return new h.a(i, true, (int) length);
        } else {
            return new h.b(i, true, (int) length);
        }
    }

    public static MessageSnapshot a(int i, long j, long j2, boolean z) {
        if (j2 > 2147483647L) {
            if (z) {
                return new h(i, j, j2);
            }
            return new i(i, j, j2);
        } else if (z) {
            return new h.h(i, (int) j, (int) j2);
        } else {
            return new h.i(i, (int) j, (int) j2);
        }
    }

    public static MessageSnapshot a(int i, long j, Throwable th) {
        if (j > 2147483647L) {
            return new d(i, j, th);
        }
        return new h.d(i, (int) j, th);
    }

    public static MessageSnapshot a(MessageSnapshot messageSnapshot) {
        if (messageSnapshot.b() == (byte) -3) {
            return new a.a(messageSnapshot);
        }
        throw new IllegalStateException(com.liulishuo.filedownloader.g.f.a("take block completed snapshot, must has already be completed. %d %d", Integer.valueOf(messageSnapshot.m()), Byte.valueOf(messageSnapshot.b())));
    }

    public static MessageSnapshot a(byte b, c cVar, e.a aVar) {
        int a = cVar.a();
        if (b == (byte) -4) {
            throw new IllegalStateException(com.liulishuo.filedownloader.g.f.a("please use #catchWarn instead %d", Integer.valueOf(a)));
        }
        switch (b) {
            case (byte) -3:
                if (cVar.q()) {
                    return new b(a, false, cVar.h());
                }
                return new h.b(a, false, (int) cVar.h());
            case (byte) -1:
                if (cVar.q()) {
                    return new d(a, cVar.g(), aVar.b());
                }
                return new h.d(a, (int) cVar.g(), aVar.b());
            case (byte) 1:
                if (cVar.q()) {
                    return new d.e(a, cVar.g(), cVar.h());
                }
                return new h.e(a, (int) cVar.g(), (int) cVar.h());
            case (byte) 2:
                String m = cVar.l() ? cVar.m() : null;
                if (cVar.q()) {
                    return new d.c(a, aVar.a(), cVar.h(), cVar.j(), m);
                }
                return new h.c(a, aVar.a(), (int) cVar.h(), cVar.j(), m);
            case (byte) 3:
                if (cVar.q()) {
                    return new com.liulishuo.filedownloader.message.d.f(a, cVar.g());
                }
                return new com.liulishuo.filedownloader.message.h.f(a, (int) cVar.g());
            case (byte) 5:
                if (cVar.q()) {
                    return new g(a, cVar.g(), aVar.b(), aVar.c());
                }
                return new h.g(a, (int) cVar.g(), aVar.b(), aVar.c());
            case (byte) 6:
                return new MessageSnapshot.b(a);
            default:
                Throwable illegalStateException;
                String a2 = com.liulishuo.filedownloader.g.f.a("it can't takes a snapshot for the task(%s) when its status is %d,", cVar, Byte.valueOf(b));
                com.liulishuo.filedownloader.g.d.d(f.class, "it can't takes a snapshot for the task(%s) when its status is %d,", cVar, Byte.valueOf(b));
                if (aVar.b() != null) {
                    illegalStateException = new IllegalStateException(a2, aVar.b());
                } else {
                    illegalStateException = new IllegalStateException(a2);
                }
                if (cVar.q()) {
                    return new d(a, cVar.g(), illegalStateException);
                }
                return new h.d(a, (int) cVar.g(), illegalStateException);
        }
    }
}
