package qsbk.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import java.util.Iterator;
import java.util.Stack;

public class AppManager {
    private static Stack<Activity> a;
    private static AppManager b;

    private AppManager() {
    }

    public static AppManager getAppManager() {
        if (b == null) {
            b = new AppManager();
        }
        return b;
    }

    public void addActivity(Activity activity) {
        if (a == null) {
            a = new Stack();
        }
        a.add(activity);
    }

    public Activity currentActivity() {
        return (Activity) a.lastElement();
    }

    public void finishActivity() {
        finishActivity((Activity) a.lastElement());
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            a.remove(activity);
            activity.finish();
        }
    }

    public void finishActivity(Class<?> cls) {
        Iterator it = a.iterator();
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    public void finishAllActivity() {
        int size = a.size();
        for (int i = 0; i < size; i++) {
            if (a.get(i) != null) {
                ((Activity) a.get(i)).finish();
            }
        }
        a.clear();
    }

    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ((ActivityManager) context.getSystemService("activity")).restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
