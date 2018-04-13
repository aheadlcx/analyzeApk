package qsbk.app.utils.image.issue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import qsbk.app.exception.QiushibaikeException;

public class TaskExecutor {
    private static TaskExecutor a = null;
    private ExecutorService b = Executors.newSingleThreadExecutor();

    public interface Task {
        void fail(Throwable th);

        Object proccess() throws QiushibaikeException;

        void success(Object obj);
    }

    public static class SimpleTask implements Task {
        public Object proccess() throws QiushibaikeException {
            return null;
        }

        public void success(Object obj) {
        }

        public void fail(Throwable th) {
        }
    }

    private final class a implements Runnable {
        Task a = null;
        final /* synthetic */ TaskExecutor b;

        a(TaskExecutor taskExecutor, Task task) {
            this.b = taskExecutor;
            this.a = task;
        }

        Task a() {
            return this.a;
        }

        public void run() {
            try {
                this.a.success(this.a.proccess());
            } catch (Throwable e) {
                this.a.fail(e);
            }
        }
    }

    private TaskExecutor() {
    }

    public static synchronized TaskExecutor getInstance() {
        TaskExecutor taskExecutor;
        synchronized (TaskExecutor.class) {
            if (a == null) {
                a = new TaskExecutor();
            }
            taskExecutor = a;
        }
        return taskExecutor;
    }

    public boolean addTask(Task task) {
        if (task == null) {
            throw new NullPointerException("task must not be null");
        } else if (this.b.isShutdown() || this.b.isTerminated()) {
            return false;
        } else {
            this.b.submit(new a(this, task));
            return true;
        }
    }

    public synchronized List<Task> shutdownAll() {
        List<Task> list;
        List shutdownNow = this.b.shutdownNow();
        list = null;
        if (!(shutdownNow == null || shutdownNow.isEmpty())) {
            int size = shutdownNow.size();
            List<Task> arrayList = new ArrayList();
            for (int i = 0; i < size; i++) {
                Runnable runnable = (Runnable) shutdownNow.get(i);
                if (runnable != null && (runnable instanceof a)) {
                    arrayList.add(((a) runnable).a());
                }
            }
            list = arrayList;
        }
        return list;
    }
}
