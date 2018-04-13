package qsbk.app.activity;

import java.util.Map;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

class kd implements Task {
    final /* synthetic */ String a;
    final /* synthetic */ Map b;
    final /* synthetic */ EditInfoBaseActivity c;

    kd(EditInfoBaseActivity editInfoBaseActivity, String str, Map map) {
        this.c = editInfoBaseActivity;
        this.a = str;
        this.b = map;
    }

    public void success(Object obj) {
        this.c.a("Submit success. " + obj);
    }

    public Object proccess() throws QiushibaikeException {
        return HttpClient.getIntentce().post(this.a, this.b);
    }

    public void fail(Throwable th) {
        this.c.a("Exception happens on submit." + this.a);
    }
}
