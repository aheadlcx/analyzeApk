package android.support.v4.media;

import android.media.session.MediaSession.Token;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class IMediaBrowserServiceCallbacksAdapterApi21 {
    private Method mAsBinderMethod;
    Object mCallbackObject;
    private Method mOnConnectFailedMethod;
    private Method mOnConnectMethod;
    private Method mOnLoadChildrenMethod;

    static class Stub {
        static Method sAsInterfaceMethod;

        Stub() {
        }

        static {
            ReflectiveOperationException e;
            try {
                sAsInterfaceMethod = Class.forName("android.service.media.IMediaBrowserServiceCallbacks$Stub").getMethod("asInterface", new Class[]{IBinder.class});
                return;
            } catch (ClassNotFoundException e2) {
                e = e2;
            } catch (NoSuchMethodException e3) {
                e = e3;
            }
            e.printStackTrace();
        }

        static Object asInterface(IBinder iBinder) {
            ReflectiveOperationException e;
            try {
                return sAsInterfaceMethod.invoke(null, new Object[]{iBinder});
            } catch (IllegalAccessException e2) {
                e = e2;
                e.printStackTrace();
                return null;
            } catch (InvocationTargetException e3) {
                e = e3;
                e.printStackTrace();
                return null;
            }
        }
    }

    IMediaBrowserServiceCallbacksAdapterApi21(Object obj) {
        ReflectiveOperationException e;
        this.mCallbackObject = obj;
        try {
            Class cls = Class.forName("android.service.media.IMediaBrowserServiceCallbacks");
            Class cls2 = Class.forName("android.content.pm.ParceledListSlice");
            this.mAsBinderMethod = cls.getMethod("asBinder", new Class[0]);
            this.mOnConnectMethod = cls.getMethod("onConnect", new Class[]{String.class, Token.class, Bundle.class});
            this.mOnConnectFailedMethod = cls.getMethod("onConnectFailed", new Class[0]);
            this.mOnLoadChildrenMethod = cls.getMethod("onLoadChildren", new Class[]{String.class, cls2});
            return;
        } catch (ClassNotFoundException e2) {
            e = e2;
        } catch (NoSuchMethodException e3) {
            e = e3;
        }
        e.printStackTrace();
    }

    IBinder asBinder() {
        ReflectiveOperationException e;
        try {
            return (IBinder) this.mAsBinderMethod.invoke(this.mCallbackObject, new Object[0]);
        } catch (IllegalAccessException e2) {
            e = e2;
        } catch (InvocationTargetException e3) {
            e = e3;
        }
        e.printStackTrace();
        return null;
    }

    void onConnect(String str, Object obj, Bundle bundle) throws RemoteException {
        ReflectiveOperationException e;
        try {
            this.mOnConnectMethod.invoke(this.mCallbackObject, new Object[]{str, obj, bundle});
            return;
        } catch (IllegalAccessException e2) {
            e = e2;
        } catch (InvocationTargetException e3) {
            e = e3;
        }
        e.printStackTrace();
    }

    void onConnectFailed() throws RemoteException {
        ReflectiveOperationException e;
        try {
            this.mOnConnectFailedMethod.invoke(this.mCallbackObject, new Object[0]);
            return;
        } catch (IllegalAccessException e2) {
            e = e2;
        } catch (InvocationTargetException e3) {
            e = e3;
        }
        e.printStackTrace();
    }

    void onLoadChildren(String str, Object obj) throws RemoteException {
        ReflectiveOperationException e;
        try {
            this.mOnLoadChildrenMethod.invoke(this.mCallbackObject, new Object[]{str, obj});
            return;
        } catch (IllegalAccessException e2) {
            e = e2;
        } catch (InvocationTargetException e3) {
            e = e3;
        }
        e.printStackTrace();
    }
}
