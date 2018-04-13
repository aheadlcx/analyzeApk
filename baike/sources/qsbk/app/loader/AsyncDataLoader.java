package qsbk.app.loader;

import qsbk.app.core.AsyncTask;

public class AsyncDataLoader extends AsyncTask<Void, Long, String> {
    private OnAsyncLoadListener a = null;
    private String b = null;

    public AsyncDataLoader(String str) {
        this.b = str;
    }

    public AsyncDataLoader(OnAsyncLoadListener onAsyncLoadListener) {
        this.a = onAsyncLoadListener;
    }

    public AsyncDataLoader(OnAsyncLoadListener onAsyncLoadListener, String str) {
        this.b = str;
        this.a = onAsyncLoadListener;
    }

    public void setOnAsyncLoadListener(OnAsyncLoadListener onAsyncLoadListener) {
        this.a = onAsyncLoadListener;
    }

    protected void a() {
        super.a();
        if (this.a != null) {
            this.a.onPrepareListener();
        }
    }

    protected String a(Void... voidArr) {
        try {
            Thread.currentThread().setName(this.b);
        } catch (SecurityException e) {
        }
        if (this.a != null) {
            return this.a.onStartListener();
        }
        return null;
    }

    protected void a(String str) {
        super.a(str);
        if (this.a != null) {
            this.a.onFinishListener(str);
        }
    }
}
