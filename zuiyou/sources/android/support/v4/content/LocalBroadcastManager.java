package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;

public final class LocalBroadcastManager {
    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private static LocalBroadcastManager mInstance;
    private static final Object mLock = new Object();
    private final HashMap<String, ArrayList<ReceiverRecord>> mActions = new HashMap();
    private final Context mAppContext;
    private final Handler mHandler;
    private final ArrayList<BroadcastRecord> mPendingBroadcasts = new ArrayList();
    private final HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> mReceivers = new HashMap();

    private static final class BroadcastRecord {
        final Intent intent;
        final ArrayList<ReceiverRecord> receivers;

        BroadcastRecord(Intent intent, ArrayList<ReceiverRecord> arrayList) {
            this.intent = intent;
            this.receivers = arrayList;
        }
    }

    private static final class ReceiverRecord {
        boolean broadcasting;
        boolean dead;
        final IntentFilter filter;
        final BroadcastReceiver receiver;

        ReceiverRecord(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.filter = intentFilter;
            this.receiver = broadcastReceiver;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder(128);
            stringBuilder.append("Receiver{");
            stringBuilder.append(this.receiver);
            stringBuilder.append(" filter=");
            stringBuilder.append(this.filter);
            if (this.dead) {
                stringBuilder.append(" DEAD");
            }
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    @NonNull
    public static LocalBroadcastManager getInstance(@NonNull Context context) {
        LocalBroadcastManager localBroadcastManager;
        synchronized (mLock) {
            if (mInstance == null) {
                mInstance = new LocalBroadcastManager(context.getApplicationContext());
            }
            localBroadcastManager = mInstance;
        }
        return localBroadcastManager;
    }

    private LocalBroadcastManager(Context context) {
        this.mAppContext = context;
        this.mHandler = new Handler(context.getMainLooper()) {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        LocalBroadcastManager.this.executePendingBroadcasts();
                        return;
                    default:
                        super.handleMessage(message);
                        return;
                }
            }
        };
    }

    public void registerReceiver(@NonNull BroadcastReceiver broadcastReceiver, @NonNull IntentFilter intentFilter) {
        synchronized (this.mReceivers) {
            ReceiverRecord receiverRecord = new ReceiverRecord(intentFilter, broadcastReceiver);
            ArrayList arrayList = (ArrayList) this.mReceivers.get(broadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList(1);
                this.mReceivers.put(broadcastReceiver, arrayList);
            }
            arrayList.add(receiverRecord);
            for (int i = 0; i < intentFilter.countActions(); i++) {
                String action = intentFilter.getAction(i);
                arrayList = (ArrayList) this.mActions.get(action);
                if (arrayList == null) {
                    arrayList = new ArrayList(1);
                    this.mActions.put(action, arrayList);
                }
                arrayList.add(receiverRecord);
            }
        }
    }

    public void unregisterReceiver(@NonNull BroadcastReceiver broadcastReceiver) {
        synchronized (this.mReceivers) {
            ArrayList arrayList = (ArrayList) this.mReceivers.remove(broadcastReceiver);
            if (arrayList == null) {
                return;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ReceiverRecord receiverRecord = (ReceiverRecord) arrayList.get(size);
                receiverRecord.dead = true;
                for (int i = 0; i < receiverRecord.filter.countActions(); i++) {
                    String action = receiverRecord.filter.getAction(i);
                    ArrayList arrayList2 = (ArrayList) this.mActions.get(action);
                    if (arrayList2 != null) {
                        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
                            ReceiverRecord receiverRecord2 = (ReceiverRecord) arrayList2.get(size2);
                            if (receiverRecord2.receiver == broadcastReceiver) {
                                receiverRecord2.dead = true;
                                arrayList2.remove(size2);
                            }
                        }
                        if (arrayList2.size() <= 0) {
                            this.mActions.remove(action);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean sendBroadcast(@android.support.annotation.NonNull android.content.Intent r17) {
        /*
        r16 = this;
        r0 = r16;
        r13 = r0.mReceivers;
        monitor-enter(r13);
        r2 = r17.getAction();	 Catch:{ all -> 0x0110 }
        r0 = r16;
        r1 = r0.mAppContext;	 Catch:{ all -> 0x0110 }
        r1 = r1.getContentResolver();	 Catch:{ all -> 0x0110 }
        r0 = r17;
        r3 = r0.resolveTypeIfNeeded(r1);	 Catch:{ all -> 0x0110 }
        r5 = r17.getData();	 Catch:{ all -> 0x0110 }
        r4 = r17.getScheme();	 Catch:{ all -> 0x0110 }
        r6 = r17.getCategories();	 Catch:{ all -> 0x0110 }
        r1 = r17.getFlags();	 Catch:{ all -> 0x0110 }
        r1 = r1 & 8;
        if (r1 == 0) goto L_0x00d3;
    L_0x002b:
        r1 = 1;
        r12 = r1;
    L_0x002d:
        if (r12 == 0) goto L_0x0061;
    L_0x002f:
        r1 = "LocalBroadcastManager";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0110 }
        r7.<init>();	 Catch:{ all -> 0x0110 }
        r8 = "Resolving type ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0110 }
        r7 = r7.append(r3);	 Catch:{ all -> 0x0110 }
        r8 = " scheme ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0110 }
        r7 = r7.append(r4);	 Catch:{ all -> 0x0110 }
        r8 = " of intent ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0110 }
        r0 = r17;
        r7 = r7.append(r0);	 Catch:{ all -> 0x0110 }
        r7 = r7.toString();	 Catch:{ all -> 0x0110 }
        android.util.Log.v(r1, r7);	 Catch:{ all -> 0x0110 }
    L_0x0061:
        r0 = r16;
        r1 = r0.mActions;	 Catch:{ all -> 0x0110 }
        r7 = r17.getAction();	 Catch:{ all -> 0x0110 }
        r1 = r1.get(r7);	 Catch:{ all -> 0x0110 }
        r0 = r1;
        r0 = (java.util.ArrayList) r0;	 Catch:{ all -> 0x0110 }
        r8 = r0;
        if (r8 == 0) goto L_0x0182;
    L_0x0073:
        if (r12 == 0) goto L_0x008f;
    L_0x0075:
        r1 = "LocalBroadcastManager";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0110 }
        r7.<init>();	 Catch:{ all -> 0x0110 }
        r9 = "Action list: ";
        r7 = r7.append(r9);	 Catch:{ all -> 0x0110 }
        r7 = r7.append(r8);	 Catch:{ all -> 0x0110 }
        r7 = r7.toString();	 Catch:{ all -> 0x0110 }
        android.util.Log.v(r1, r7);	 Catch:{ all -> 0x0110 }
    L_0x008f:
        r10 = 0;
        r1 = 0;
        r11 = r1;
    L_0x0092:
        r1 = r8.size();	 Catch:{ all -> 0x0110 }
        if (r11 >= r1) goto L_0x0147;
    L_0x0098:
        r1 = r8.get(r11);	 Catch:{ all -> 0x0110 }
        r0 = r1;
        r0 = (android.support.v4.content.LocalBroadcastManager.ReceiverRecord) r0;	 Catch:{ all -> 0x0110 }
        r9 = r0;
        if (r12 == 0) goto L_0x00be;
    L_0x00a2:
        r1 = "LocalBroadcastManager";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0110 }
        r7.<init>();	 Catch:{ all -> 0x0110 }
        r14 = "Matching against filter ";
        r7 = r7.append(r14);	 Catch:{ all -> 0x0110 }
        r14 = r9.filter;	 Catch:{ all -> 0x0110 }
        r7 = r7.append(r14);	 Catch:{ all -> 0x0110 }
        r7 = r7.toString();	 Catch:{ all -> 0x0110 }
        android.util.Log.v(r1, r7);	 Catch:{ all -> 0x0110 }
    L_0x00be:
        r1 = r9.broadcasting;	 Catch:{ all -> 0x0110 }
        if (r1 == 0) goto L_0x00d7;
    L_0x00c2:
        if (r12 == 0) goto L_0x0135;
    L_0x00c4:
        r1 = "LocalBroadcastManager";
        r7 = "  Filter's target already added";
        android.util.Log.v(r1, r7);	 Catch:{ all -> 0x0110 }
        r1 = r10;
    L_0x00ce:
        r7 = r11 + 1;
        r11 = r7;
        r10 = r1;
        goto L_0x0092;
    L_0x00d3:
        r1 = 0;
        r12 = r1;
        goto L_0x002d;
    L_0x00d7:
        r1 = r9.filter;	 Catch:{ all -> 0x0110 }
        r7 = "LocalBroadcastManager";
        r1 = r1.match(r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x0110 }
        if (r1 < 0) goto L_0x0113;
    L_0x00e2:
        if (r12 == 0) goto L_0x0102;
    L_0x00e4:
        r7 = "LocalBroadcastManager";
        r14 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0110 }
        r14.<init>();	 Catch:{ all -> 0x0110 }
        r15 = "  Filter matched!  match=0x";
        r14 = r14.append(r15);	 Catch:{ all -> 0x0110 }
        r1 = java.lang.Integer.toHexString(r1);	 Catch:{ all -> 0x0110 }
        r1 = r14.append(r1);	 Catch:{ all -> 0x0110 }
        r1 = r1.toString();	 Catch:{ all -> 0x0110 }
        android.util.Log.v(r7, r1);	 Catch:{ all -> 0x0110 }
    L_0x0102:
        if (r10 != 0) goto L_0x0185;
    L_0x0104:
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x0110 }
        r1.<init>();	 Catch:{ all -> 0x0110 }
    L_0x0109:
        r1.add(r9);	 Catch:{ all -> 0x0110 }
        r7 = 1;
        r9.broadcasting = r7;	 Catch:{ all -> 0x0110 }
        goto L_0x00ce;
    L_0x0110:
        r1 = move-exception;
        monitor-exit(r13);	 Catch:{ all -> 0x0110 }
        throw r1;
    L_0x0113:
        if (r12 == 0) goto L_0x0135;
    L_0x0115:
        switch(r1) {
            case -4: goto L_0x013b;
            case -3: goto L_0x0137;
            case -2: goto L_0x013f;
            case -1: goto L_0x0143;
            default: goto L_0x0118;
        };
    L_0x0118:
        r1 = "unknown reason";
    L_0x011b:
        r7 = "LocalBroadcastManager";
        r9 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0110 }
        r9.<init>();	 Catch:{ all -> 0x0110 }
        r14 = "  Filter did not match: ";
        r9 = r9.append(r14);	 Catch:{ all -> 0x0110 }
        r1 = r9.append(r1);	 Catch:{ all -> 0x0110 }
        r1 = r1.toString();	 Catch:{ all -> 0x0110 }
        android.util.Log.v(r7, r1);	 Catch:{ all -> 0x0110 }
    L_0x0135:
        r1 = r10;
        goto L_0x00ce;
    L_0x0137:
        r1 = "action";
        goto L_0x011b;
    L_0x013b:
        r1 = "category";
        goto L_0x011b;
    L_0x013f:
        r1 = "data";
        goto L_0x011b;
    L_0x0143:
        r1 = "type";
        goto L_0x011b;
    L_0x0147:
        if (r10 == 0) goto L_0x0182;
    L_0x0149:
        r1 = 0;
        r2 = r1;
    L_0x014b:
        r1 = r10.size();	 Catch:{ all -> 0x0110 }
        if (r2 >= r1) goto L_0x015e;
    L_0x0151:
        r1 = r10.get(r2);	 Catch:{ all -> 0x0110 }
        r1 = (android.support.v4.content.LocalBroadcastManager.ReceiverRecord) r1;	 Catch:{ all -> 0x0110 }
        r3 = 0;
        r1.broadcasting = r3;	 Catch:{ all -> 0x0110 }
        r1 = r2 + 1;
        r2 = r1;
        goto L_0x014b;
    L_0x015e:
        r0 = r16;
        r1 = r0.mPendingBroadcasts;	 Catch:{ all -> 0x0110 }
        r2 = new android.support.v4.content.LocalBroadcastManager$BroadcastRecord;	 Catch:{ all -> 0x0110 }
        r0 = r17;
        r2.<init>(r0, r10);	 Catch:{ all -> 0x0110 }
        r1.add(r2);	 Catch:{ all -> 0x0110 }
        r0 = r16;
        r1 = r0.mHandler;	 Catch:{ all -> 0x0110 }
        r2 = 1;
        r1 = r1.hasMessages(r2);	 Catch:{ all -> 0x0110 }
        if (r1 != 0) goto L_0x017f;
    L_0x0177:
        r0 = r16;
        r1 = r0.mHandler;	 Catch:{ all -> 0x0110 }
        r2 = 1;
        r1.sendEmptyMessage(r2);	 Catch:{ all -> 0x0110 }
    L_0x017f:
        r1 = 1;
        monitor-exit(r13);	 Catch:{ all -> 0x0110 }
    L_0x0181:
        return r1;
    L_0x0182:
        monitor-exit(r13);	 Catch:{ all -> 0x0110 }
        r1 = 0;
        goto L_0x0181;
    L_0x0185:
        r1 = r10;
        goto L_0x0109;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.LocalBroadcastManager.sendBroadcast(android.content.Intent):boolean");
    }

    public void sendBroadcastSync(@NonNull Intent intent) {
        if (sendBroadcast(intent)) {
            executePendingBroadcasts();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void executePendingBroadcasts() {
        /*
        r9 = this;
        r2 = 0;
    L_0x0001:
        r1 = r9.mReceivers;
        monitor-enter(r1);
        r0 = r9.mPendingBroadcasts;	 Catch:{ all -> 0x0043 }
        r0 = r0.size();	 Catch:{ all -> 0x0043 }
        if (r0 > 0) goto L_0x000e;
    L_0x000c:
        monitor-exit(r1);	 Catch:{ all -> 0x0043 }
        return;
    L_0x000e:
        r4 = new android.support.v4.content.LocalBroadcastManager.BroadcastRecord[r0];	 Catch:{ all -> 0x0043 }
        r0 = r9.mPendingBroadcasts;	 Catch:{ all -> 0x0043 }
        r0.toArray(r4);	 Catch:{ all -> 0x0043 }
        r0 = r9.mPendingBroadcasts;	 Catch:{ all -> 0x0043 }
        r0.clear();	 Catch:{ all -> 0x0043 }
        monitor-exit(r1);	 Catch:{ all -> 0x0043 }
        r1 = r2;
    L_0x001c:
        r0 = r4.length;
        if (r1 >= r0) goto L_0x0001;
    L_0x001f:
        r5 = r4[r1];
        r0 = r5.receivers;
        r6 = r0.size();
        r3 = r2;
    L_0x0028:
        if (r3 >= r6) goto L_0x0046;
    L_0x002a:
        r0 = r5.receivers;
        r0 = r0.get(r3);
        r0 = (android.support.v4.content.LocalBroadcastManager.ReceiverRecord) r0;
        r7 = r0.dead;
        if (r7 != 0) goto L_0x003f;
    L_0x0036:
        r0 = r0.receiver;
        r7 = r9.mAppContext;
        r8 = r5.intent;
        r0.onReceive(r7, r8);
    L_0x003f:
        r0 = r3 + 1;
        r3 = r0;
        goto L_0x0028;
    L_0x0043:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0043 }
        throw r0;
    L_0x0046:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.LocalBroadcastManager.executePendingBroadcasts():void");
    }
}
