package com.iflytek.aiui;

import android.content.Context;
import android.util.Log;
import com.iflytek.cloud.thirdparty.cb;
import com.iflytek.cloud.thirdparty.p;

public final class AIUIAgent {
    private static AIUIAgent a;
    private p b;

    private AIUIAgent(Context context, String str, AIUIListener aIUIListener) {
        cb.a("AIUIAgent", "AIUI params=" + str);
        this.b = new p(context);
        this.b.a(str, aIUIListener);
    }

    public static AIUIAgent createAgent(Context context, String str, AIUIListener aIUIListener) {
        AIUIAgent aIUIAgent;
        synchronized (AIUIAgent.class) {
            if (context == null) {
                try {
                    Log.e("AIUIAgent", "parameter context is null.");
                    aIUIAgent = null;
                } catch (Throwable th) {
                    Class cls = AIUIAgent.class;
                }
            } else {
                if (a == null) {
                    a = new AIUIAgent(context, str, aIUIListener);
                }
                aIUIAgent = a;
            }
        }
        return aIUIAgent;
    }

    public void destroy() {
        synchronized (this) {
            if (this.b != null) {
                this.b.a();
                this.b = null;
                a = null;
                cb.a("AIUIAgent", "AIUIAgent destroyed.");
            }
        }
    }

    public void sendMessage(AIUIMessage aIUIMessage) {
        if (aIUIMessage == null) {
            Log.e("AIUIAgent", "message is null.");
            return;
        }
        synchronized (this) {
            if (this.b != null) {
                this.b.a(aIUIMessage);
            } else {
                Log.e("AIUIAgent", "AIUIAgent has been destroyed.");
            }
        }
    }
}
