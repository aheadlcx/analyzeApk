package com.facebook.stetho.inspector.protocol.module;

import android.content.Context;

public abstract class DatabaseDriver2<DESC extends DatabaseDescriptor> extends BaseDatabaseDriver<DESC> {
    public DatabaseDriver2(Context context) {
        super(context);
    }
}
