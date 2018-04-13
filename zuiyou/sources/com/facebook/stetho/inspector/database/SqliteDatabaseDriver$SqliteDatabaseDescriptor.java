package com.facebook.stetho.inspector.database;

import com.facebook.stetho.inspector.protocol.module.DatabaseDescriptor;
import java.io.File;

class SqliteDatabaseDriver$SqliteDatabaseDescriptor implements DatabaseDescriptor {
    public final File file;

    public SqliteDatabaseDriver$SqliteDatabaseDescriptor(File file) {
        this.file = file;
    }

    public String name() {
        return this.file.getName();
    }
}
