package qsbk.app.push;

import java.util.TimerTask;
import qsbk.app.utils.image.issue.TaskExecutor;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

class f extends TimerTask {
    final /* synthetic */ Task a;
    final /* synthetic */ e b;

    f(e eVar, Task task) {
        this.b = eVar;
        this.a = task;
    }

    public void run() {
        TaskExecutor.getInstance().addTask(this.a);
    }
}
