package com.budejie.www.activity.video;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.budejie.www.util.ai;
import com.budejie.www.util.ak;

class f$8 extends Handler {
    final /* synthetic */ f a;

    f$8(f fVar) {
        this.a = fVar;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                try {
                    if (f.d(this.a).c()) {
                        Log.d("MicroMediaController", "player.getCurrentPosition()=" + f.d(this.a).getCurrentPosition());
                        Log.d("MicroMediaController", "position=" + f.w(this.a));
                        if (f.d(this.a).getCurrentPosition() != f.w(this.a)) {
                            if (f.x(this.a)) {
                                f.c(this.a, false);
                                if (f.y(this.a) != null) {
                                    f.y(this.a).setVisibility(8);
                                }
                            }
                            this.a.x();
                        } else if (f.a(this.a) > 0) {
                            this.a.y();
                        }
                        f.a(this.a, f.d(this.a).getCurrentPosition());
                        f.z(this.a);
                        return;
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 1:
                this.a.m();
                return;
            case 2:
                try {
                    f.r(this.a);
                    if (!f.s(this.a) && f.t(this.a) && f.d(this.a).c() && !f.u(this.a).hasMessages(2)) {
                        sendMessageDelayed(obtainMessage(2), (long) f.v(this.a));
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            case 3:
                if (f.a(this.a) != 0) {
                    try {
                        if (f.y(this.a) != null && ai.c(f.b(this.a))) {
                            f.c(this.a, true);
                            f.y(this.a).setVisibility(0);
                            return;
                        }
                        return;
                    } catch (Exception e3) {
                        return;
                    }
                }
                return;
            case 4:
                try {
                    if (f.A(this.a) != null) {
                        f.A(this.a).setVisibility(8);
                        return;
                    }
                    return;
                } catch (Exception e4) {
                    return;
                }
            case 5:
                try {
                    if (f.B(this.a) != null) {
                        f.B(this.a).setText((String) message.obj);
                        return;
                    }
                    return;
                } catch (Exception e5) {
                    return;
                }
            case 6:
                try {
                    if (f.C(this.a) != null) {
                        f.C(this.a).setMax(message.arg1);
                        f.C(this.a).setProgress(message.arg2);
                    }
                    if (f.j(this.a) != null) {
                        f.j(this.a).setMax(message.arg1);
                        f.j(this.a).setProgress(message.arg2);
                        return;
                    }
                    return;
                } catch (Exception e6) {
                    return;
                }
            case 7:
                try {
                    if (f.C(this.a) != null) {
                        f.C(this.a).setSecondaryProgress(message.arg1);
                    }
                    if (f.j(this.a) != null) {
                        f.j(this.a).setSecondaryProgress(message.arg1);
                        return;
                    }
                    return;
                } catch (Exception e7) {
                    return;
                }
            case 8:
                if (!this.a.a && f.D(this.a) && !ak.a(this.a.getContext())) {
                    f.E(this.a).setVisibility(0);
                    f.E(this.a).a();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
