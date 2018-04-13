package cn.v6.sixrooms.room.IM;

public abstract class IMBaseManager {
    public abstract void clearAll();

    public <T> T filterNull(T t, T t2) {
        return t == null ? t2 : t;
    }
}
