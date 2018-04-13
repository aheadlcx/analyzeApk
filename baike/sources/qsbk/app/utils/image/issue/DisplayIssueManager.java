package qsbk.app.utils.image.issue;

import android.content.Context;
import java.io.IOException;

public class DisplayIssueManager {
    private static final TaskExecutor a = TaskExecutor.getInstance();
    private static DisplayIssueManager b = null;

    private DisplayIssueManager() {
    }

    public static synchronized DisplayIssueManager getInstance() {
        DisplayIssueManager displayIssueManager;
        synchronized (DisplayIssueManager.class) {
            if (b == null) {
                b = new DisplayIssueManager();
            }
            displayIssueManager = b;
        }
        return displayIssueManager;
    }

    public void reportNewIssue(Context context, String str, IOException iOException, String str2, int i) {
        if (iOException instanceof IOExceptionWrapper) {
            IOExceptionWrapper iOExceptionWrapper = (IOExceptionWrapper) iOException;
            reportNewIssue(context, str, iOExceptionWrapper.getResponseCode(), iOExceptionWrapper.getOriginException(), str2, i);
            return;
        }
        reportNewIssue(context, str, -1, iOException, str2, i);
    }

    public void reportNewIssue(Context context, String str, IOException iOException, String str2) {
        reportNewIssue(context, str, iOException, str2, 2);
    }

    public void reportNewIssue(Context context, String str, int i, IOException iOException, String str2, int i2) {
        a.addTask(new a(this, str, i, iOException, str2, i2, context));
    }
}
