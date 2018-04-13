package qsbk.app.im;

import android.view.View.OnTouchListener;

class jt implements OnTouchListener {
    final /* synthetic */ VoiceUIHelper a;
    private long b;

    jt(VoiceUIHelper voiceUIHelper) {
        this.a = voiceUIHelper;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r12, android.view.MotionEvent r13) {
        /*
        r11 = this;
        r8 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r1 = 3;
        r2 = 0;
        r10 = 1;
        r0 = r11.a;
        r0 = r0.l;
        r0.a(r13);
        r0 = r13.getAction();
        switch(r0) {
            case 0: goto L_0x0016;
            case 1: goto L_0x016e;
            case 2: goto L_0x02aa;
            case 3: goto L_0x016e;
            default: goto L_0x0015;
        };
    L_0x0015:
        return r10;
    L_0x0016:
        r0 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = 1;
        r0.a(r3);	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = android.os.SystemClock.uptimeMillis();	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r11.b = r4;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3.<init>();	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = r11.b;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = ".up";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r3.toString();	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0.h = r3;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = new java.io.File;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = qsbk.app.im.voice.VoiceManager.getDir();	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = r4.h;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0.<init>(r3, r4);	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r0.exists();	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        if (r3 != 0) goto L_0x0054;
    L_0x0051:
        r0.createNewFile();	 Catch:{ IOException -> 0x012a, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
    L_0x0054:
        r3 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = r0.exists();	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        if (r4 == 0) goto L_0x0147;
    L_0x005c:
        r4 = r0.length();	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
    L_0x0060:
        r3.p = r4;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = 0;
        r3.o = r4;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r3.q;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = "00″";
        r3.setText(r4);	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r3.q;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = "#63625e";
        r4 = android.graphics.Color.parseColor(r4);	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3.setTextColor(r4);	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = new android.media.MediaRecorder;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4.<init>();	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3.f = r4;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r3.f;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = 1;
        r3.setAudioSource(r4);	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r3.f;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = 3;
        r3.setOutputFormat(r4);	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r3.f;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = 1;
        r3.setAudioEncoder(r4);	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r3.f;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r0.getPath();	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3.setOutputFile(r0);	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r0.f;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0.prepare();	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r11.a;	 Catch:{ RuntimeException -> 0x014b }
        r0 = r0.f;	 Catch:{ RuntimeException -> 0x014b }
        r0.start();	 Catch:{ RuntimeException -> 0x014b }
    L_0x00ca:
        r4 = android.os.SystemClock.uptimeMillis();	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r11.b = r4;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r0.e;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r0.y;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r3.z;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0.postDelayed(r3, r4);	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r0.e;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r0.y;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r3.x;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0.postDelayed(r3, r4);	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r0.e;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r0.y;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r3.y;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0.post(r3);	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = r13.getX();	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r3 = (int) r3;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0.m = r3;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        goto L_0x0015;
    L_0x0113:
        r0 = move-exception;
        r0 = r11.a;
        r0.a(r1);
        r0 = qsbk.app.QsbkApp.mContext;
        r1 = "录音失败";
        r2 = java.lang.Integer.valueOf(r2);
        r0 = qsbk.app.utils.ToastAndDialog.makeNegativeToast(r0, r1, r2);
        r0.show();
        goto L_0x0015;
    L_0x012a:
        r3 = move-exception;
        r3.printStackTrace();	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        goto L_0x0054;
    L_0x0130:
        r0 = move-exception;
        r0 = r11.a;
        r0.a(r1);
        r0 = qsbk.app.QsbkApp.mContext;
        r1 = "录音失败";
        r2 = java.lang.Integer.valueOf(r2);
        r0 = qsbk.app.utils.ToastAndDialog.makeNegativeToast(r0, r1, r2);
        r0.show();
        goto L_0x0015;
    L_0x0147:
        r4 = 0;
        goto L_0x0060;
    L_0x014b:
        r0 = move-exception;
        r3 = r11.a;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r4 = 0;
        r3.w = r4;	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        r0.printStackTrace();	 Catch:{ IOException -> 0x0113, IllegalStateException -> 0x0130, Throwable -> 0x0157 }
        goto L_0x00ca;
    L_0x0157:
        r0 = move-exception;
        r0 = r11.a;
        r0.a(r1);
        r0 = qsbk.app.QsbkApp.mContext;
        r1 = "录音失败";
        r2 = java.lang.Integer.valueOf(r2);
        r0 = qsbk.app.utils.ToastAndDialog.makeNegativeToast(r0, r1, r2);
        r0.show();
        goto L_0x0015;
    L_0x016e:
        r0 = r11.a;
        r0 = r0.e;
        r0 = r0.y;
        r3 = r11.a;
        r3 = r3.x;
        r0.removeCallbacks(r3);
        r3 = new java.io.File;
        r0 = qsbk.app.im.voice.VoiceManager.getDir();
        r4 = r11.a;
        r4 = r4.h;
        r3.<init>(r0, r4);
        r0 = r11.a;	 Catch:{ IllegalStateException -> 0x01b0 }
        r0.d();	 Catch:{ IllegalStateException -> 0x01b0 }
    L_0x0193:
        r0 = r11.a;
        r0 = r0.d;
        if (r0 != r10) goto L_0x0015;
    L_0x019b:
        r4 = android.os.SystemClock.uptimeMillis();
        r6 = r11.b;
        r4 = r4 - r6;
        r4 = r4 / r8;
        r6 = 1;
        r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r0 >= 0) goto L_0x01be;
    L_0x01a9:
        r0 = r11.a;
        r0.a(r1);
        goto L_0x0015;
    L_0x01b0:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r3.exists();
        if (r0 == 0) goto L_0x0193;
    L_0x01ba:
        r3.deleteOnExit();
        goto L_0x0193;
    L_0x01be:
        r0 = r11.a;
        r0 = r0.w;
        if (r0 == 0) goto L_0x01e2;
    L_0x01c6:
        r0 = r3.exists();
        if (r0 == 0) goto L_0x01e2;
    L_0x01cc:
        r0 = r11.a;
        r0 = r0.o;
        if (r0 < r10) goto L_0x01f8;
    L_0x01d4:
        r6 = r3.length();
        r0 = r11.a;
        r8 = r0.p;
        r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r0 != 0) goto L_0x01f8;
    L_0x01e2:
        r0 = r11.a;
        r0.a(r1);
        r0 = qsbk.app.QsbkApp.mContext;
        r1 = "请检查录音权限是否禁用";
        r2 = java.lang.Integer.valueOf(r2);
        r0 = qsbk.app.utils.ToastAndDialog.makeNegativeToast(r0, r1, r2);
        r0.show();
        goto L_0x0015;
    L_0x01f8:
        r0 = r11.a;
        r0 = r0.e;
        r0 = r0 instanceof qsbk.app.im.ConversationActivity;
        if (r0 == 0) goto L_0x022a;
    L_0x0202:
        r0 = r11.a;
        r0 = r0.e;
        r0 = (qsbk.app.im.ConversationActivity) r0;
        r3 = r0.isTemporary;
        if (r3 != 0) goto L_0x022a;
    L_0x020e:
        r0 = r0.mRelationship;
        r3 = qsbk.app.model.relationship.Relationship.FOLLOW_UNREPLIED;
        if (r0 != r3) goto L_0x022a;
    L_0x0214:
        r0 = qsbk.app.QsbkApp.mContext;
        r1 = "对方回应后才可发送...";
        r2 = java.lang.Integer.valueOf(r2);
        r0 = qsbk.app.utils.ToastAndDialog.makeNegativeToast(r0, r1, r2);
        r0.show();
        r0 = r11.a;
        r0.a(r10);
        goto L_0x0015;
    L_0x022a:
        r3 = new qsbk.app.im.ChatMsgVoiceData;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r6 = qsbk.app.im.voice.VoiceManager.getDir();
        r0 = r0.append(r6);
        r6 = java.io.File.separator;
        r0 = r0.append(r6);
        r6 = r11.a;
        r6 = r6.h;
        r0 = r0.append(r6);
        r0 = r0.toString();
        r4 = (int) r4;
        r3.<init>(r0, r4);
        r0 = r11.a;
        r0 = r0.e;
        r4 = r0.newContactItem();
        r0 = r11.a;
        r0 = r0.e;
        r5 = r0.z;
        r0 = r11.a;
        r0 = r0.e;
        r6 = r0.s;
        r0 = r11.a;
        r0 = r0.c;
        if (r0 == 0) goto L_0x02a8;
    L_0x0273:
        r0 = r1;
    L_0x0274:
        r0 = r5.newVoiceChatMsg(r4, r3, r6, r0);
        r1 = r11.a;
        r1 = r1.e;
        r1 = r1.g;
        r1.appendItem(r0);
        r0 = r11.a;
        r0 = r0.e;
        r0 = r0.g;
        r0.notifyDataSetChanged();
        r0 = r11.a;
        r0 = r0.e;
        r0 = r0.y;
        r1 = new qsbk.app.im.ju;
        r1.<init>(r11);
        r2 = 100;
        r0.postDelayed(r1, r2);
        r0 = r11.a;
        r1 = 2;
        r0.a(r1);
        goto L_0x0015;
    L_0x02a8:
        r0 = r2;
        goto L_0x0274;
    L_0x02aa:
        r0 = r11.a;
        r0 = r0.d;
        if (r0 != r10) goto L_0x0015;
    L_0x02b2:
        r0 = r13.getX();
        r0 = (int) r0;
        r2 = r11.a;
        r2 = r2.m;
        r0 = r2 - r0;
        r2 = r11.a;
        r2 = r2.n;
        if (r0 <= r2) goto L_0x02d3;
    L_0x02c7:
        r0 = r11.a;
        r0.a(r1);
        r0 = r11.a;
        r0.b();
        goto L_0x0015;
    L_0x02d3:
        r0 = r11.a;
        r0.a(r10);
        goto L_0x0015;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.im.jt.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
