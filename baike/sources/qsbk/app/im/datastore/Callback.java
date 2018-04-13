package qsbk.app.im.datastore;

public interface Callback<T> {
    void onFailure(Throwable th);

    void onFinished(T t);
}
