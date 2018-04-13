package qsbk.app.im;

public interface Callback<T> {
    void onFailure(Throwable th);

    void onFinished(T t);
}
