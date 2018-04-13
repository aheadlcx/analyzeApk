package com.tencent.wcdb.database;

public final class SQLiteCustomFunction {
    public final SQLiteDatabase$CustomFunction callback;
    public final String name;
    public final int numArgs;

    public SQLiteCustomFunction(String str, int i, SQLiteDatabase$CustomFunction sQLiteDatabase$CustomFunction) {
        if (str == null) {
            throw new IllegalArgumentException("name must not be null.");
        }
        this.name = str;
        this.numArgs = i;
        this.callback = sQLiteDatabase$CustomFunction;
    }

    private void dispatchCallback(String[] strArr) {
        this.callback.callback(strArr);
    }
}
