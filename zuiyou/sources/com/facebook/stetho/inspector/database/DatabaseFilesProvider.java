package com.facebook.stetho.inspector.database;

import java.io.File;
import java.util.List;

public interface DatabaseFilesProvider {
    List<File> getDatabaseFiles();
}
