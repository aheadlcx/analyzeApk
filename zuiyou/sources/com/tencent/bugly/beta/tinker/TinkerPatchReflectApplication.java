package com.tencent.bugly.beta.tinker;

import android.annotation.TargetApi;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application.OnProvideAssistDataListener;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import com.tencent.tinker.loader.TinkerLoader;
import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareReflectUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TinkerPatchReflectApplication extends TinkerApplication {
    private static final String TAG = "Tinker.ReflectApp";
    private boolean isReflectFailure = false;
    private String rawApplicationName = null;
    private Application realApplication;

    public TinkerPatchReflectApplication() {
        super(7, "com.tencent.bugly.beta.tinker.TinkerApplicationLike", TinkerLoader.class.getName(), false);
    }

    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        try {
            String rawApplicationName = getRawApplicationName(context);
            if (rawApplicationName == null) {
                throw new RuntimeException("can get real realApplication from manifest!");
            }
            this.realApplication = (Application) Class.forName(rawApplicationName, false, getClassLoader()).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (this.realApplication != null) {
                try {
                    Method declaredMethod = ContextWrapper.class.getDeclaredMethod("attachBaseContext", new Class[]{Context.class});
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(this.realApplication, new Object[]{context});
                } catch (Throwable e) {
                    throw new IllegalStateException(e);
                }
            }
        } catch (Throwable e2) {
            throw new IllegalStateException(e2);
        }
    }

    public void onCreate() {
        int i = 0;
        if (this.realApplication != null) {
            Class cls;
            Field declaredField;
            Field declaredField2;
            Class cls2;
            try {
                cls = Class.forName("android.app.ActivityThread");
                Object activityThread = ShareReflectUtil.getActivityThread(this, cls);
                declaredField = cls.getDeclaredField("mInitialApplication");
                declaredField.setAccessible(true);
                Application application = (Application) declaredField.get(activityThread);
                if (this.realApplication != null && application == this) {
                    declaredField.set(activityThread, this.realApplication);
                }
                if (this.realApplication != null) {
                    declaredField2 = cls.getDeclaredField("mAllApplications");
                    declaredField2.setAccessible(true);
                    List list = (List) declaredField2.get(activityThread);
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        if (list.get(i2) == this) {
                            list.set(i2, this.realApplication);
                        }
                    }
                }
                cls2 = Class.forName("android.app.LoadedApk");
            } catch (ClassNotFoundException e) {
                cls2 = Class.forName("android.app.ActivityThread$PackageInfo");
            } catch (Throwable th) {
                Log.e(TAG, "Error, reflect Application fail, result:" + th);
                this.isReflectFailure = true;
            }
            Field declaredField3 = cls2.getDeclaredField("mApplication");
            declaredField3.setAccessible(true);
            declaredField = null;
            try {
                declaredField = Application.class.getDeclaredField("mLoadedApk");
            } catch (NoSuchFieldException e2) {
                e2.printStackTrace();
            }
            String[] strArr = new String[]{"mPackages", "mResourcePackages"};
            while (i < 2) {
                declaredField2 = cls.getDeclaredField(strArr[i]);
                declaredField2.setAccessible(true);
                for (Entry value : ((Map) declaredField2.get(activityThread)).entrySet()) {
                    Object obj = ((WeakReference) value.getValue()).get();
                    if (obj != null && declaredField3.get(obj) == this) {
                        if (this.realApplication != null) {
                            declaredField3.set(obj, this.realApplication);
                        }
                        if (!(this.realApplication == null || declaredField == null)) {
                            declaredField.set(this.realApplication, obj);
                        }
                    }
                }
                i++;
            }
            if (!this.isReflectFailure) {
                try {
                    cls2 = Class.forName("com.tencent.bugly.beta.tinker.TinkerApplicationLike", false, getClassLoader());
                    Log.e(TAG, "replaceApplicationLike delegateClass:" + cls2);
                    ShareReflectUtil.findField(cls2, "application").set(cls2.getDeclaredMethod("getTinkerPatchApplicationLike", new Class[0]).invoke(cls2, new Object[0]), this.realApplication);
                } catch (Throwable th2) {
                    Log.e(TAG, "replaceApplicationLike exception:" + th2.getMessage());
                }
            }
        }
        super.onCreate();
        if (this.realApplication != null) {
            this.realApplication.onCreate();
        }
    }

    public String getRawApplicationName(Context context) {
        if (this.rawApplicationName != null) {
            return this.rawApplicationName;
        }
        try {
            Object obj = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get("TINKER_PATCH_APPLICATION");
            if (obj != null) {
                this.rawApplicationName = String.valueOf(obj);
            } else {
                this.rawApplicationName = null;
            }
            Log.i(TAG, "with app realApplication from manifest applicationName:" + this.rawApplicationName);
            return this.rawApplicationName;
        } catch (Exception e) {
            Log.e(TAG, "getManifestApplication exception:" + e.getMessage());
            return null;
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (!this.isReflectFailure || this.realApplication == null) {
            super.onConfigurationChanged(configuration);
        } else {
            this.realApplication.onConfigurationChanged(configuration);
        }
    }

    public void onLowMemory() {
        if (!this.isReflectFailure || this.realApplication == null) {
            super.onLowMemory();
        } else {
            this.realApplication.onLowMemory();
        }
    }

    @TargetApi(14)
    public void onTrimMemory(int i) {
        if (!this.isReflectFailure || this.realApplication == null) {
            super.onTrimMemory(i);
        } else {
            this.realApplication.onTrimMemory(i);
        }
    }

    public void onTerminate() {
        if (!this.isReflectFailure || this.realApplication == null) {
            super.onTerminate();
        } else {
            this.realApplication.onTerminate();
        }
    }

    public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (!this.isReflectFailure || this.realApplication == null) {
            return super.registerReceiver(broadcastReceiver, intentFilter);
        }
        return this.realApplication.registerReceiver(broadcastReceiver, intentFilter);
    }

    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        if (!this.isReflectFailure || this.realApplication == null) {
            super.unregisterReceiver(broadcastReceiver);
        } else {
            this.realApplication.unregisterReceiver(broadcastReceiver);
        }
    }

    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        if (!this.isReflectFailure || this.realApplication == null) {
            return super.bindService(intent, serviceConnection, i);
        }
        return this.realApplication.bindService(intent, serviceConnection, i);
    }

    public void unbindService(ServiceConnection serviceConnection) {
        if (!this.isReflectFailure || this.realApplication == null) {
            super.unbindService(serviceConnection);
        } else {
            this.realApplication.unbindService(serviceConnection);
        }
    }

    @TargetApi(14)
    public void registerComponentCallbacks(ComponentCallbacks componentCallbacks) {
        if (!this.isReflectFailure || this.realApplication == null) {
            super.registerComponentCallbacks(componentCallbacks);
        } else {
            this.realApplication.registerComponentCallbacks(componentCallbacks);
        }
    }

    @TargetApi(14)
    public void unregisterComponentCallbacks(ComponentCallbacks componentCallbacks) {
        if (!this.isReflectFailure || this.realApplication == null) {
            super.unregisterComponentCallbacks(componentCallbacks);
        } else {
            this.realApplication.unregisterComponentCallbacks(componentCallbacks);
        }
    }

    @TargetApi(14)
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        if (!this.isReflectFailure || this.realApplication == null) {
            super.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        } else {
            this.realApplication.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }

    @TargetApi(14)
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        if (!this.isReflectFailure || this.realApplication == null) {
            super.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        } else {
            this.realApplication.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }
    }

    @TargetApi(18)
    public void registerOnProvideAssistDataListener(OnProvideAssistDataListener onProvideAssistDataListener) {
        if (!this.isReflectFailure || this.realApplication == null) {
            super.registerOnProvideAssistDataListener(onProvideAssistDataListener);
        } else {
            this.realApplication.registerOnProvideAssistDataListener(onProvideAssistDataListener);
        }
    }

    @TargetApi(18)
    public void unregisterOnProvideAssistDataListener(OnProvideAssistDataListener onProvideAssistDataListener) {
        if (!this.isReflectFailure || this.realApplication == null) {
            super.unregisterOnProvideAssistDataListener(onProvideAssistDataListener);
        } else {
            this.realApplication.unregisterOnProvideAssistDataListener(onProvideAssistDataListener);
        }
    }

    public Resources getResources() {
        if (!this.isReflectFailure || this.realApplication == null) {
            return super.getResources();
        }
        return this.realApplication.getResources();
    }

    public ClassLoader getClassLoader() {
        if (!this.isReflectFailure || this.realApplication == null) {
            return super.getClassLoader();
        }
        return this.realApplication.getClassLoader();
    }

    public AssetManager getAssets() {
        return (!this.isReflectFailure || this.realApplication == null) ? super.getAssets() : this.realApplication.getAssets();
    }

    public ContentResolver getContentResolver() {
        if (!this.isReflectFailure || this.realApplication == null) {
            return super.getContentResolver();
        }
        return this.realApplication.getContentResolver();
    }
}
