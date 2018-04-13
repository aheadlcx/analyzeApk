package com.baidu.mobstat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttTopic;

class bf {
    private static final bf b = new bf();
    private HashMap<String, Set<String>> a = new HashMap();
    private boolean c;
    private boolean d;

    bf() {
    }

    public static bf a() {
        return b;
    }

    public void a(Context context) {
        a(context, false);
    }

    @TargetApi(14)
    private void a(Context context, boolean z) {
        if (!this.d) {
            if (VERSION.SDK_INT >= 14) {
                b(context);
                this.d = true;
            } else if (z) {
                db.a("module autotrace only support android os version bigger than 4.0");
            }
        }
    }

    private void a(Activity activity, boolean z) {
        if (!(activity instanceof IIgnoreAutoTrace)) {
            if (z) {
                bv.a().a((Context) activity);
            }
            if (z) {
                ch.a().a((Context) activity, System.currentTimeMillis(), true);
                return;
            }
            ch.a().a(activity, System.currentTimeMillis(), true, null);
        }
    }

    private void b(Context context) {
        try {
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new bg(this));
        } catch (Exception e) {
            db.a("registerActivityLifecycleCallbacks encounter exception");
        }
    }

    private void a(Activity activity) {
        Window window = activity.getWindow();
        if (window != null) {
            View decorView = window.getDecorView();
            if (decorView != null) {
                ViewGroup viewGroup;
                try {
                    viewGroup = (ViewGroup) ((ViewGroup) decorView.findViewById(16908290)).getChildAt(0);
                } catch (Exception e) {
                    viewGroup = null;
                }
                if (viewGroup != null) {
                    a(activity, viewGroup);
                }
            }
        }
    }

    private void a(Activity activity, ViewGroup viewGroup) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt instanceof ViewGroup) {
                a(activity, (ViewGroup) childAt);
            }
            a(activity, childAt);
        }
    }

    private void a(Activity activity, View view) {
        if (view instanceof Button) {
            String charSequence = ((Button) view).getText().toString();
            if (!TextUtils.isEmpty(charSequence)) {
                a(activity, view, charSequence);
            }
        }
    }

    private void a(Activity activity, View view, String str) {
        AccessibilityDelegate a = a(view);
        if (!(a instanceof bh)) {
            view.setAccessibilityDelegate(new bh(this, activity, view, str, a));
        }
    }

    private AccessibilityDelegate a(View view) {
        try {
            return (AccessibilityDelegate) view.getClass().getMethod("getAccessibilityDelegate", new Class[0]).invoke(view, new Object[0]);
        } catch (Exception e) {
            db.b("getAccessibilityDelegate threw an exception when called");
            return null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(android.app.Activity r13, android.view.View r14, java.lang.String r15) {
        /*
        r12 = this;
        r5 = 1;
        r0 = com.baidu.mobstat.bv.a();
        r0.a(r13);
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = r14.hashCode();
        r0 = r0.append(r1);
        r1 = "_";
        r0 = r0.append(r1);
        r1 = r14.getId();
        r0 = r0.append(r1);
        r1 = r0.toString();
        r0 = r13.getClass();
        r9 = r0.getName();
        r2 = r12.a;
        monitor-enter(r2);
        r0 = r12.a;	 Catch:{ all -> 0x0063 }
        r0 = r0.get(r9);	 Catch:{ all -> 0x0063 }
        r0 = (java.util.Set) r0;	 Catch:{ all -> 0x0063 }
        if (r0 == 0) goto L_0x0044;
    L_0x003c:
        r0 = r0.contains(r1);	 Catch:{ all -> 0x0063 }
        if (r0 == 0) goto L_0x0044;
    L_0x0042:
        monitor-exit(r2);	 Catch:{ all -> 0x0063 }
    L_0x0043:
        return;
    L_0x0044:
        monitor-exit(r2);	 Catch:{ all -> 0x0063 }
        r8 = r12.a(r14, r13);
        r0 = com.baidu.mobstat.Config.EventViewType.BUTTON;
        r10 = r0.getValue();
        r1 = com.baidu.mobstat.bm.a();
        r2 = r13.getApplicationContext();
        r4 = "";
        r6 = java.lang.System.currentTimeMillis();
        r3 = r15;
        r11 = r5;
        r1.a(r2, r3, r4, r5, r6, r8, r9, r10, r11);
        goto L_0x0043;
    L_0x0063:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0063 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.bf.b(android.app.Activity, android.view.View, java.lang.String):void");
    }

    private String a(View view, Activity activity) {
        String str = "";
        if (view == null) {
            return str;
        }
        View view2 = null;
        try {
            view2 = (ViewGroup) ((ViewGroup) activity.getWindow().getDecorView().findViewById(16908290)).getChildAt(0);
        } catch (Exception e) {
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(view.getClass().getName());
        while (view != null && view != r1) {
            View view3 = (View) view.getParent();
            arrayList.add(view3.getClass().getName());
            view = view3;
        }
        int size = arrayList.size() - 1;
        String str2 = str;
        while (size >= 0) {
            str = str2 + ((String) arrayList.get(size)) + MqttTopic.TOPIC_LEVEL_SEPARATOR;
            size--;
            str2 = str;
        }
        if (str2.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR)) {
            return str2.substring(0, str2.length() - 1);
        }
        return str2;
    }
}
