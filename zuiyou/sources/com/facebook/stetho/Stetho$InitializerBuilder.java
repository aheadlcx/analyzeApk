package com.facebook.stetho;

import android.content.Context;
import com.facebook.stetho.common.Util;
import javax.annotation.Nullable;

public class Stetho$InitializerBuilder {
    final Context mContext;
    @Nullable
    DumperPluginsProvider mDumperPlugins;
    @Nullable
    InspectorModulesProvider mInspectorModules;

    private Stetho$InitializerBuilder(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public Stetho$InitializerBuilder enableDumpapp(DumperPluginsProvider dumperPluginsProvider) {
        this.mDumperPlugins = (DumperPluginsProvider) Util.throwIfNull(dumperPluginsProvider);
        return this;
    }

    public Stetho$InitializerBuilder enableWebKitInspector(InspectorModulesProvider inspectorModulesProvider) {
        this.mInspectorModules = inspectorModulesProvider;
        return this;
    }

    public Stetho$Initializer build() {
        return new Stetho$BuilderBasedInitializer();
    }
}
