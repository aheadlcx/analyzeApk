package bolts;

import bolts.Task.UnobservedExceptionHandler;

class t {
    private Task<?> a;

    public t(Task<?> task) {
        this.a = task;
    }

    protected void finalize() throws Throwable {
        try {
            Task task = this.a;
            if (task != null) {
                UnobservedExceptionHandler unobservedExceptionHandler = Task.getUnobservedExceptionHandler();
                if (unobservedExceptionHandler != null) {
                    unobservedExceptionHandler.unobservedException(task, new UnobservedTaskException(task.getError()));
                }
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
        }
    }

    public void setObserved() {
        this.a = null;
    }
}
