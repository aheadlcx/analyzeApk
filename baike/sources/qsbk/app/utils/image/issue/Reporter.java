package qsbk.app.utils.image.issue;

import android.content.Context;

public class Reporter {
    private static final TaskExecutor a = TaskExecutor.getInstance();
    private static Reporter b = null;

    private Reporter() {
    }

    public static synchronized Reporter getInstance() {
        Reporter reporter;
        synchronized (Reporter.class) {
            if (b == null) {
                b = new Reporter();
            }
            reporter = b;
        }
        return reporter;
    }

    private void a(String str, String str2, Context context) {
        a.addTask(new c(this, context, str, str2));
    }

    public void reportMsg(Context context, String str) {
        a("rpt_" + (System.currentTimeMillis() / 1000) + ".sent", str, context);
    }
}
