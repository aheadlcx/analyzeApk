package com.facebook.stetho;

import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import com.facebook.stetho.Stetho.PluginBuilder;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;
import com.facebook.stetho.inspector.database.DatabaseDriver2Adapter;
import com.facebook.stetho.inspector.database.DatabaseFilesProvider;
import com.facebook.stetho.inspector.database.DefaultDatabaseConnectionProvider;
import com.facebook.stetho.inspector.database.DefaultDatabaseFilesProvider;
import com.facebook.stetho.inspector.database.SqliteDatabaseDriver;
import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.elements.DocumentProviderFactory;
import com.facebook.stetho.inspector.elements.android.AndroidDocumentProviderFactory;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.module.CSS;
import com.facebook.stetho.inspector.protocol.module.Console;
import com.facebook.stetho.inspector.protocol.module.DOM;
import com.facebook.stetho.inspector.protocol.module.DOMStorage;
import com.facebook.stetho.inspector.protocol.module.Database;
import com.facebook.stetho.inspector.protocol.module.Database.DatabaseDriver;
import com.facebook.stetho.inspector.protocol.module.DatabaseDriver2;
import com.facebook.stetho.inspector.protocol.module.Debugger;
import com.facebook.stetho.inspector.protocol.module.HeapProfiler;
import com.facebook.stetho.inspector.protocol.module.Inspector;
import com.facebook.stetho.inspector.protocol.module.Network;
import com.facebook.stetho.inspector.protocol.module.Page;
import com.facebook.stetho.inspector.protocol.module.Profiler;
import com.facebook.stetho.inspector.protocol.module.Runtime;
import com.facebook.stetho.inspector.protocol.module.Worker;
import com.facebook.stetho.inspector.runtime.RhinoDetectingRuntimeReplFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public final class Stetho$DefaultInspectorModulesBuilder {
    private final Application mContext;
    @Nullable
    private List<DatabaseDriver2> mDatabaseDrivers;
    @Nullable
    private DatabaseFilesProvider mDatabaseFilesProvider;
    private final PluginBuilder<ChromeDevtoolsDomain> mDelegate = new PluginBuilder(null);
    @Nullable
    private DocumentProviderFactory mDocumentProvider;
    private boolean mExcludeSqliteDatabaseDriver;
    @Nullable
    private RuntimeReplFactory mRuntimeRepl;

    public Stetho$DefaultInspectorModulesBuilder(Context context) {
        this.mContext = (Application) context.getApplicationContext();
    }

    public Stetho$DefaultInspectorModulesBuilder documentProvider(DocumentProviderFactory documentProviderFactory) {
        this.mDocumentProvider = documentProviderFactory;
        return this;
    }

    public Stetho$DefaultInspectorModulesBuilder runtimeRepl(RuntimeReplFactory runtimeReplFactory) {
        this.mRuntimeRepl = runtimeReplFactory;
        return this;
    }

    @Deprecated
    public Stetho$DefaultInspectorModulesBuilder databaseFiles(DatabaseFilesProvider databaseFilesProvider) {
        this.mDatabaseFilesProvider = databaseFilesProvider;
        return this;
    }

    @Deprecated
    public Stetho$DefaultInspectorModulesBuilder provideDatabaseDriver(DatabaseDriver databaseDriver) {
        provideDatabaseDriver(new DatabaseDriver2Adapter(databaseDriver));
        return this;
    }

    public Stetho$DefaultInspectorModulesBuilder provideDatabaseDriver(DatabaseDriver2 databaseDriver2) {
        if (this.mDatabaseDrivers == null) {
            this.mDatabaseDrivers = new ArrayList();
        }
        this.mDatabaseDrivers.add(databaseDriver2);
        return this;
    }

    public Stetho$DefaultInspectorModulesBuilder excludeSqliteDatabaseDriver(boolean z) {
        this.mExcludeSqliteDatabaseDriver = z;
        return this;
    }

    @Deprecated
    public Stetho$DefaultInspectorModulesBuilder provide(ChromeDevtoolsDomain chromeDevtoolsDomain) {
        this.mDelegate.provide(chromeDevtoolsDomain.getClass().getName(), chromeDevtoolsDomain);
        return this;
    }

    private Stetho$DefaultInspectorModulesBuilder provideIfDesired(ChromeDevtoolsDomain chromeDevtoolsDomain) {
        this.mDelegate.provideIfDesired(chromeDevtoolsDomain.getClass().getName(), chromeDevtoolsDomain);
        return this;
    }

    @Deprecated
    public Stetho$DefaultInspectorModulesBuilder remove(String str) {
        this.mDelegate.remove(str);
        return this;
    }

    public Iterable<ChromeDevtoolsDomain> finish() {
        RuntimeReplFactory runtimeReplFactory;
        provideIfDesired(new Console());
        provideIfDesired(new Debugger());
        DocumentProviderFactory resolveDocumentProvider = resolveDocumentProvider();
        if (resolveDocumentProvider != null) {
            Document document = new Document(resolveDocumentProvider);
            provideIfDesired(new DOM(document));
            provideIfDesired(new CSS(document));
        }
        provideIfDesired(new DOMStorage(this.mContext));
        provideIfDesired(new HeapProfiler());
        provideIfDesired(new Inspector());
        provideIfDesired(new Network(this.mContext));
        provideIfDesired(new Page(this.mContext));
        provideIfDesired(new Profiler());
        if (this.mRuntimeRepl != null) {
            runtimeReplFactory = this.mRuntimeRepl;
        } else {
            runtimeReplFactory = new RhinoDetectingRuntimeReplFactory(this.mContext);
        }
        provideIfDesired(new Runtime(runtimeReplFactory));
        provideIfDesired(new Worker());
        if (VERSION.SDK_INT >= 11) {
            Object obj;
            ChromeDevtoolsDomain database = new Database();
            if (this.mDatabaseDrivers != null) {
                obj = null;
                for (DatabaseDriver2 databaseDriver2 : this.mDatabaseDrivers) {
                    Object obj2;
                    database.add(databaseDriver2);
                    if (databaseDriver2 instanceof SqliteDatabaseDriver) {
                        obj2 = 1;
                    } else {
                        obj2 = obj;
                    }
                    obj = obj2;
                }
            } else {
                obj = null;
            }
            if (obj == null && !this.mExcludeSqliteDatabaseDriver) {
                database.add(new SqliteDatabaseDriver(this.mContext, this.mDatabaseFilesProvider != null ? this.mDatabaseFilesProvider : new DefaultDatabaseFilesProvider(this.mContext), new DefaultDatabaseConnectionProvider()));
            }
            provideIfDesired(database);
        }
        return this.mDelegate.finish();
    }

    @Nullable
    private DocumentProviderFactory resolveDocumentProvider() {
        if (this.mDocumentProvider != null) {
            return this.mDocumentProvider;
        }
        if (VERSION.SDK_INT >= 14) {
            return new AndroidDocumentProviderFactory(this.mContext, Collections.emptyList());
        }
        return null;
    }
}
