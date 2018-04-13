package com.facebook.stetho.inspector.database;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class DefaultDatabaseFilesProvider implements DatabaseFilesProvider {
    private final Context mContext;

    public DefaultDatabaseFilesProvider(Context context) {
        this.mContext = context;
    }

    public List<File> getDatabaseFiles() {
        List<File> arrayList = new ArrayList();
        for (String databasePath : this.mContext.databaseList()) {
            arrayList.add(this.mContext.getDatabasePath(databasePath));
        }
        return arrayList;
    }
}
