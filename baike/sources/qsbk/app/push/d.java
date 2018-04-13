package qsbk.app.push;

import java.util.TimerTask;
import qsbk.app.utils.image.issue.TaskExecutor;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

class d extends TimerTask {
    final /* synthetic */ Task a;
    final /* synthetic */ c b;

    d(c cVar, Task task) {
        this.b = cVar;
        this.a = task;
    }

    public void run() {
        TaskExecutor.getInstance().addTask(this.a);
    }
}
