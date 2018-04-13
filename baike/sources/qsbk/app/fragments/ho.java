package qsbk.app.fragments;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class ho extends SimpleTask {
    final /* synthetic */ NewsFragment a;

    ho(NewsFragment newsFragment) {
        this.a = newsFragment;
    }

    public Object proccess() throws QiushibaikeException {
        return NewsFragment.a(this.a);
    }

    public void success(Object obj) {
        super.success(obj);
        NewsFragment.a.post(new hp(this, obj instanceof String ? (String) obj : null));
    }

    public void fail(Throwable th) {
        super.fail(th);
        NewsFragment.a.post(new hq(this));
    }
}
