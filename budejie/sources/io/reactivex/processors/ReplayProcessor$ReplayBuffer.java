package io.reactivex.processors;

interface ReplayProcessor$ReplayBuffer<T> {
    void add(T t);

    void addFinal(Object obj);

    Object get();

    T getValue();

    T[] getValues(T[] tArr);

    void replay(ReplayProcessor$ReplaySubscription<T> replayProcessor$ReplaySubscription);

    int size();
}
