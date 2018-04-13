package cn.xiaochuankeji.aop.permission;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import junit.framework.Assert;

public class a {
    private static a a;
    private final Context b;
    private a c;
    private Queue<a> d = new ConcurrentLinkedQueue();
    private cn.xiaochuankeji.aop.permission.ShadowPermissionActivity.a e = new cn.xiaochuankeji.aop.permission.ShadowPermissionActivity.a(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public boolean a(String[] strArr) {
            this.a.c = (a) this.a.d.poll();
            if (this.a.c != null) {
                this.a.a(this.a.c);
            }
            return this.a.c != null;
        }
    };
    private Handler f = new Handler(Looper.getMainLooper());

    class a {
        PermissionItem a;
        e b;
        final /* synthetic */ a c;

        public a(a aVar, PermissionItem permissionItem, e eVar) {
            this.c = aVar;
            this.a = permissionItem;
            this.b = eVar;
        }
    }

    public static a a(Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    private a(Context context) {
        this.b = context.getApplicationContext();
        ShadowPermissionActivity.a(this.e);
    }

    public void a(PermissionItem permissionItem, e eVar) {
        if (permissionItem != null && eVar != null) {
            if (f.a()) {
                this.d.add(new a(this, permissionItem, eVar));
                if (this.c == null) {
                    this.c = (a) this.d.poll();
                    a(this.c);
                    return;
                }
                return;
            }
            b(permissionItem, eVar);
        }
    }

    private void a(a aVar) {
        PermissionItem permissionItem = aVar.a;
        e eVar = aVar.b;
        if (f.a(this.b, permissionItem.permissions)) {
            b(permissionItem, eVar);
        } else {
            ShadowPermissionActivity.a(this.b, permissionItem.permissions, permissionItem.rationalMessage, permissionItem.rationalButton, permissionItem.needGotoSetting, permissionItem.settingText, permissionItem.deniedMessage, permissionItem.deniedButton, eVar);
        }
    }

    private void b(PermissionItem permissionItem, e eVar) {
        Assert.assertNotNull(permissionItem);
        if (eVar != null) {
            eVar.permissionGranted();
        }
        this.e.a(permissionItem.permissions);
    }
}
