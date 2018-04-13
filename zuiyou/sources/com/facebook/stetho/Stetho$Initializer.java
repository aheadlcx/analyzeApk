package com.facebook.stetho;

import android.content.Context;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.server.AddressNameHelper;
import com.facebook.stetho.server.LazySocketHandler;
import com.facebook.stetho.server.LocalSocketServer;
import com.facebook.stetho.server.ServerManager;
import javax.annotation.Nullable;

public abstract class Stetho$Initializer {
    private final Context mContext;

    @Nullable
    protected abstract Iterable<DumperPlugin> getDumperPlugins();

    @Nullable
    protected abstract Iterable<ChromeDevtoolsDomain> getInspectorModules();

    protected Stetho$Initializer(Context context) {
        this.mContext = context.getApplicationContext();
    }

    final void start() {
        new ServerManager(new LocalSocketServer("main", AddressNameHelper.createCustomAddress("_devtools_remote"), new LazySocketHandler(new Stetho$Initializer$RealSocketHandlerFactory(this, null)))).start();
    }
}
