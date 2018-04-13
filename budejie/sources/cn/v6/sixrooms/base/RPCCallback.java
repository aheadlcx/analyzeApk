package cn.v6.sixrooms.base;

public interface RPCCallback<T> {
    void onError(RPCException rPCException);

    void onResult(T t);
}
