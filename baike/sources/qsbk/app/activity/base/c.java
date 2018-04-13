package qsbk.app.activity.base;

import qsbk.app.exception.QiushibaikeException;
import qsbk.app.utils.image.issue.TaskExecutor.SimpleTask;

class c extends SimpleTask {
    final /* synthetic */ BaseArticleListViewFragment a;

    c(BaseArticleListViewFragment baseArticleListViewFragment) {
        this.a = baseArticleListViewFragment;
    }

    public Object proccess() throws QiushibaikeException {
        return this.a.w();
    }

    public void success(Object obj) {
        super.success(obj);
        BaseArticleListViewFragment.a.post(new d(this, obj instanceof String ? (String) obj : null));
    }

    public void fail(Throwable th) {
        super.fail(th);
        BaseArticleListViewFragment.a.post(new e(this));
    }
}
