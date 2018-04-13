package mtopsdk.common.util;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IInterface;
import java.lang.reflect.Method;

public abstract class a {
    protected volatile IInterface a = null;
    private Class b;
    private Class c;
    private Object d = new Object();
    private ServiceConnection e = new b(this);

    public a(Class cls, Class cls2) {
        this.b = cls;
        this.c = cls2;
    }

    private static Object a(String str, String str2, Class[] clsArr, Object... objArr) {
        if (str == null || str2 == null) {
            return null;
        }
        Class cls = Class.forName(str);
        Method declaredMethod = clsArr != null ? cls.getDeclaredMethod(str2, clsArr) : cls.getDeclaredMethod(str2, new Class[0]);
        if (declaredMethod == null) {
            return null;
        }
        declaredMethod.setAccessible(true);
        return objArr != null ? declaredMethod.invoke(cls, objArr) : declaredMethod.invoke(cls, new Object[0]);
    }

    private String c() {
        String str = null;
        try {
            str = this.b.getSimpleName();
        } catch (Throwable th) {
            m.b("mtopsdk.AsyncServiceBinder", "[getInterfaceName]getInterfaceName error.interfaceName =" + this.b, th);
        }
        return str;
    }

    protected abstract void a();

    public void a(Context context) {
        Intent intent;
        boolean bindService;
        if (this.a == null) {
            if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
                m.a("mtopsdk.AsyncServiceBinder", "[asyncBind]try to bind service for " + c());
            }
            try {
                a("com.taobao.android.service.Services", "bind", new Class[]{Context.class, Class.class, ServiceConnection.class}, context.getApplicationContext(), this.b, this.e);
                if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
                    m.a("mtopsdk.AsyncServiceBinder", "[asyncBind]bind service by service framework");
                }
            } catch (ClassNotFoundException e) {
                if (m.a(TBSdkLog$LogEnable.WarnEnable)) {
                    m.c("mtopsdk.AsyncServiceBinder", "[asyncBind]service framework not exist. use intent to bind service.");
                }
                intent = new Intent(context.getApplicationContext(), this.c);
                intent.setAction(this.b.getName());
                intent.setPackage(context.getPackageName());
                intent.addCategory("android.intent.category.DEFAULT");
                bindService = context.bindService(intent, this.e, 1);
                if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
                    m.a("mtopsdk.AsyncServiceBinder", "[asyncBind]bindService ret=" + bindService);
                }
            } catch (NoSuchMethodException e2) {
                if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
                    m.a("mtopsdk.AsyncServiceBinder", "[asyncBind]service framework not exist. use intent to bind service.");
                }
                intent = new Intent(context.getApplicationContext(), this.c);
                intent.setAction(this.b.getName());
                intent.setPackage(context.getPackageName());
                intent.addCategory("android.intent.category.DEFAULT");
                bindService = context.bindService(intent, this.e, 1);
                if (m.a(TBSdkLog$LogEnable.DebugEnable)) {
                    m.a("mtopsdk.AsyncServiceBinder", "[asyncBind]bindService ret=" + bindService);
                }
            } catch (Throwable th) {
                if (m.a(TBSdkLog$LogEnable.WarnEnable)) {
                    m.c("mtopsdk.AsyncServiceBinder", "[asyncBind]Service bind failed. interfaceName =" + c());
                }
            }
        }
    }

    public IInterface b() {
        return this.a;
    }
}
