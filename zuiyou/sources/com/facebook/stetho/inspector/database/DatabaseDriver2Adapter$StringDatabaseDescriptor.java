package com.facebook.stetho.inspector.database;

import com.facebook.stetho.inspector.protocol.module.DatabaseDescriptor;

class DatabaseDriver2Adapter$StringDatabaseDescriptor implements DatabaseDescriptor {
    public final String name;

    public DatabaseDriver2Adapter$StringDatabaseDescriptor(String str) {
        this.name = str;
    }

    public String name() {
        return this.name;
    }
}
