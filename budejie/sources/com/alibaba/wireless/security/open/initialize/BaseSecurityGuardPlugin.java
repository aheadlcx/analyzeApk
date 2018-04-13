package com.alibaba.wireless.security.open.initialize;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.alibaba.wireless.security.open.IRouterComponent;
import com.alibaba.wireless.security.open.SecException;

public abstract class BaseSecurityGuardPlugin implements ISecurityGuardPlugin {
    private Context a = null;
    private IRouterComponent b = null;
    private PackageInfo c = null;

    public Context getContext() {
        return this.a;
    }

    public synchronized <T> T getInterface(Class<T> cls) {
        T t = null;
        synchronized (this) {
            if (cls != null) {
                T t2 = getInterfaceToImplMaps().get(cls);
                if (t2 == null || !cls.isAssignableFrom(t2.getClass())) {
                    t2 = null;
                }
                t = t2;
            }
        }
        return t;
    }

    public String getMetaData(String str) {
        PackageInfo packageInfo = getPackageInfo();
        return packageInfo != null ? packageInfo.applicationInfo.metaData.getString(str) : "";
    }

    public PackageInfo getPackageInfo() {
        return this.c;
    }

    public IRouterComponent getRouter() {
        return this.b;
    }

    public String getVersion() {
        return getPackageInfo().versionName;
    }

    public IRouterComponent onPluginLoaded(Context context, IRouterComponent iRouterComponent, PackageInfo packageInfo, String str) throws SecException {
        setContext(context);
        setRouter(iRouterComponent);
        setPackageInfo(packageInfo);
        return iRouterComponent;
    }

    public void setContext(Context context) {
        this.a = context;
    }

    public void setPackageInfo(PackageInfo packageInfo) {
        this.c = packageInfo;
    }

    public void setRouter(IRouterComponent iRouterComponent) {
        this.b = iRouterComponent;
    }
}
