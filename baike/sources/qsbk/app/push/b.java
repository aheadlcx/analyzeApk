package qsbk.app.push;

import java.util.TimerTask;
import qsbk.app.utils.image.issue.TaskExecutor;
import qsbk.app.utils.image.issue.TaskExecutor.Task;

class b extends TimerTask {
    final /* synthetic */ Task a;
    final /* synthetic */ a b;

    b(a aVar, Task task) {
        this.b = aVar;
        this.a = task;
    }

    public void run() {
        TaskExecutor.getInstance().addTask(this.a);
    }
}
