package qsbk.app.utils.image.issue;

import android.content.Context;
import android.util.Log;
import com.qiushibaike.statsdk.StatSDK;
import java.io.IOException;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

class a implements Task {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ IOException c;
    final /* synthetic */ String d;
    final /* synthetic */ int e;
    final /* synthetic */ Context f;
    final /* synthetic */ DisplayIssueManager g;

    a(DisplayIssueManager displayIssueManager, String str, int i, IOException iOException, String str2, int i2, Context context) {
        this.g = displayIssueManager;
        this.a = str;
        this.b = i;
        this.c = iOException;
        this.d = str2;
        this.e = i2;
        this.f = context;
    }

    public void success(Object obj) {
        if (DebugUtil.DEBUG) {
            Log.e(DisplayIssueManager.class.getSimpleName(), "succeed in reporting issue.");
        }
    }

    public Object proccess() throws QiushibaikeException {
        IssueBean issueBean = new IssueBean(this.a, this.b, this.c, this.d, this.e);
        StatSDK.onEvent(this.f, "img_issue", String.format("fail_%s_%s", new Object[]{Integer.valueOf(this.b), Integer.valueOf(this.e)}), this.a, this.c.toString(), this.d + "");
        if (DebugUtil.DEBUG) {
            Log.e(DisplayIssueManager.class.getSimpleName(), issueBean.toString());
        }
        Reporter.getInstance().reportMsg(this.f, issueBean.toString());
        return null;
    }

    public void fail(Throwable th) {
        if (DebugUtil.DEBUG) {
            Log.e(DisplayIssueManager.class.getSimpleName(), "failed to report issue.");
        }
    }
}
