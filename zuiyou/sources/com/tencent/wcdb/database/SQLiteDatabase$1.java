package com.tencent.wcdb.database;

class SQLiteDatabase$1 extends ThreadLocal<SQLiteSession> {
    final /* synthetic */ SQLiteDatabase a;

    SQLiteDatabase$1(SQLiteDatabase sQLiteDatabase) {
        this.a = sQLiteDatabase;
    }

    protected /* synthetic */ Object initialValue() {
        return a();
    }

    protected SQLiteSession a() {
        return this.a.createSession();
    }
}
