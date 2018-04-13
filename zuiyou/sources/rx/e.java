package rx;

public interface e<T> {
    void onCompleted();

    void onError(Throwable th);

    void onNext(T t);
}
