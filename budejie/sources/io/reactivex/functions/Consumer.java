package io.reactivex.functions;

import io.reactivex.annotations.NonNull;

public interface Consumer<T> {
    void accept(@NonNull T t) throws Exception;
}
