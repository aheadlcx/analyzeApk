package io.reactivex.subjects;

interface ReplaySubject$ReplayBuffer<T> {
    void add(T t);

    void addFinal(Object obj);

    boolean compareAndSet(Object obj, Object obj2);

    Object get();

    T getValue();

    T[] getValues(T[] tArr);

    void replay(ReplaySubject$ReplayDisposable<T> replaySubject$ReplayDisposable);

    int size();
}
