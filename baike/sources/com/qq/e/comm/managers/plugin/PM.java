package com.qq.e.comm.managers.plugin;

import android.content.Context;
import com.qq.e.comm.constants.Constants.PLUGIN;
import com.qq.e.comm.pi.POFactory;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;

public class PM {
    private static final Map<Class<?>, String> k = new HashMap<Class<?>, String>() {
        {
            put(POFactory.class, "com.qq.e.comm.plugin.POFactoryImpl");
        }
    };
    private final Context a;
    private String b;
    private File c;
    private int d;
    private DexClassLoader e;
    private RandomAccessFile f;
    private FileLock g;
    private boolean h;
    private a i;
    private a j = new a(this) {
        private /* synthetic */ PM a;

        {
            this.a = r1;
        }

        static File a(Context context) {
            return new File(context.getDir("e_qq_com_plugin", 0), "gdt_plugin.jar");
        }

        static File b(Context context) {
            return new File(context.getDir("e_qq_com_plugin", 0), "gdt_plugin.next");
        }

        static File c(Context context) {
            return new File(context.getDir("e_qq_com_plugin", 0), "gdt_plugin.jar.sig");
        }

        static File d(Context context) {
            return new File(context.getDir("e_qq_com_plugin", 0), "gdt_plugin.next.sig");
        }

        public final void a() {
            PM.a(this.a);
        }

        public final void b() {
            this.a.e();
        }
    };

    public interface a {

        public interface a {
            void a();

            void b();
        }

        void onLoadFail();

        void onLoadSuccess();
    }

    public PM(Context context, a aVar) {
        this.a = context.getApplicationContext();
        this.i = aVar;
        this.h = d();
        if (b()) {
            a();
        }
    }

    private void a() {
        GDTLogger.d("PluginFile:\t" + (this.c == null ? "null" : this.c.getAbsolutePath()));
        if (this.b != null) {
            try {
                this.e = new DexClassLoader(this.c.getAbsolutePath(), this.a.getDir("e_qq_com_dex", 0).getAbsolutePath(), null, getClass().getClassLoader());
                if (this.i != null) {
                    this.i.onLoadSuccess();
                    return;
                }
                return;
            } catch (Throwable th) {
                GDTLogger.e("exception while init plugin class loader", th);
                e();
                return;
            }
        }
        this.e = null;
    }

    static /* synthetic */ void a(PM pm) {
        try {
            if (pm.e == null && pm.c()) {
                pm.a();
            }
        } catch (Throwable e) {
            GDTLogger.report("Exception while init online plugin: ", e);
            pm.e();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b() {
        /*
        r6 = this;
        r1 = 1;
        r0 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x006a }
        r3 = "TimeStap_BEFORE_PLUGIN_INIT:";
        r2.<init>(r3);	 Catch:{ Throwable -> 0x006a }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x006a }
        r2 = r2.append(r4);	 Catch:{ Throwable -> 0x006a }
        r2 = r2.toString();	 Catch:{ Throwable -> 0x006a }
        com.qq.e.comm.util.GDTLogger.d(r2);	 Catch:{ Throwable -> 0x006a }
        r2 = r6.c();	 Catch:{ Throwable -> 0x006a }
        if (r2 != 0) goto L_0x0025;
    L_0x001e:
        r2 = r6.h;	 Catch:{ Throwable -> 0x006a }
        if (r2 != 0) goto L_0x003d;
    L_0x0022:
        r2 = r0;
    L_0x0023:
        if (r2 == 0) goto L_0x0026;
    L_0x0025:
        r0 = r1;
    L_0x0026:
        r1 = new java.lang.StringBuilder;
        r2 = "TimeStap_AFTER_PLUGIN_INIT:";
        r1.<init>(r2);
        r2 = java.lang.System.currentTimeMillis();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.qq.e.comm.util.GDTLogger.d(r1);
    L_0x003c:
        return r0;
    L_0x003d:
        r2 = r6.a;	 Catch:{ Throwable -> 0x006a }
        r3 = r6.a;	 Catch:{ Throwable -> 0x006a }
        r3 = com.qq.e.comm.managers.plugin.PM.AnonymousClass1.a(r3);	 Catch:{ Throwable -> 0x006a }
        r4 = r6.a;	 Catch:{ Throwable -> 0x006a }
        r4 = com.qq.e.comm.managers.plugin.PM.AnonymousClass1.c(r4);	 Catch:{ Throwable -> 0x006a }
        r2 = com.qq.e.comm.a.a(r2, r3, r4);	 Catch:{ Throwable -> 0x006a }
        if (r2 == 0) goto L_0x0063;
    L_0x0051:
        r2 = "CxtwMjFSDeIM9V9RdHPgs06rf5mi90DuWhNl/Cv6X689+tdOLsD7Wqd7aEkvXONpKf9Zg9QXQl+kBlD9Kr6M2aSHKtRMguc2Qo3ST5vhcLKN2bjVwJ7oKI1W0LAyv9BoOluzl7AvrsojvwuG6Kn0G7g4kRy3RNIp/1IxteEBPuc=";
        r6.b = r2;	 Catch:{ Throwable -> 0x006a }
        r2 = r6.a;	 Catch:{ Throwable -> 0x006a }
        r2 = com.qq.e.comm.managers.plugin.PM.AnonymousClass1.a(r2);	 Catch:{ Throwable -> 0x006a }
        r6.c = r2;	 Catch:{ Throwable -> 0x006a }
        r2 = 552; // 0x228 float:7.74E-43 double:2.727E-321;
        r6.d = r2;	 Catch:{ Throwable -> 0x006a }
        r2 = r1;
        goto L_0x0023;
    L_0x0063:
        r2 = "Fail to prepair Defult plugin ";
        com.qq.e.comm.util.GDTLogger.e(r2);	 Catch:{ Throwable -> 0x006a }
        r2 = r0;
        goto L_0x0023;
    L_0x006a:
        r1 = move-exception;
        r2 = "Exception while init plugin manager";
        com.qq.e.comm.util.GDTLogger.report(r2, r1);	 Catch:{ all -> 0x0087 }
        r1 = new java.lang.StringBuilder;
        r2 = "TimeStap_AFTER_PLUGIN_INIT:";
        r1.<init>(r2);
        r2 = java.lang.System.currentTimeMillis();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.qq.e.comm.util.GDTLogger.d(r1);
        goto L_0x003c;
    L_0x0087:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;
        r2 = "TimeStap_AFTER_PLUGIN_INIT:";
        r1.<init>(r2);
        r2 = java.lang.System.currentTimeMillis();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.qq.e.comm.util.GDTLogger.d(r1);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.managers.plugin.PM.b():boolean");
    }

    private boolean c() {
        c cVar;
        if (this.h) {
            cVar = new c(AnonymousClass1.b(this.a), AnonymousClass1.d(this.a));
            if (cVar.a()) {
                GDTLogger.d("NextExist,Updated=" + cVar.a(AnonymousClass1.a(this.a), AnonymousClass1.c(this.a)));
            }
        }
        cVar = new c(AnonymousClass1.a(this.a), AnonymousClass1.c(this.a));
        if (!cVar.a()) {
            return false;
        }
        if (cVar.b() < PLUGIN.ASSET_PLUGIN_VERSION) {
            GDTLogger.d("last updated plugin version =" + this.d + ";asset plugin version=552");
            return false;
        }
        this.b = cVar.c();
        this.d = cVar.b();
        this.c = AnonymousClass1.a(this.a);
        return true;
    }

    private boolean d() {
        try {
            File file = new File(this.a.getDir("e_qq_com_plugin", 0), "update_lc");
            if (!file.exists()) {
                file.createNewFile();
                StringUtil.writeTo("lock", file);
            }
            if (!file.exists()) {
                return false;
            }
            this.f = new RandomAccessFile(file, "rw");
            this.g = this.f.getChannel().tryLock();
            if (this.g == null) {
                return false;
            }
            this.f.writeByte(37);
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private void e() {
        if (this.i != null) {
            this.i.onLoadFail();
        }
    }

    public <T> T getFactory(Class<T> cls) throws b {
        boolean z = false;
        GDTLogger.d("GetFactoryInstaceforInterface:" + cls);
        ClassLoader classLoader = this.e;
        StringBuilder stringBuilder = new StringBuilder("PluginClassLoader is parent");
        if (getClass().getClassLoader() == classLoader) {
            z = true;
        }
        GDTLogger.d(stringBuilder.append(z).toString());
        if (classLoader == null) {
            throw new b("Fail to init GDTADPLugin,PluginClassLoader == null;while loading factory impl for:" + cls);
        }
        try {
            String str = (String) k.get(cls);
            if (StringUtil.isEmpty(str)) {
                throw new b("factory  implemention name is not specified for interface:" + cls.getName());
            }
            Class loadClass = classLoader.loadClass(str);
            T cast = cls.cast(loadClass.getDeclaredMethod("getInstance", new Class[0]).invoke(loadClass, new Object[0]));
            GDTLogger.d("ServiceDelegateFactory =" + cast);
            return cast;
        } catch (Throwable th) {
            b bVar = new b("Fail to getfactory implement instance for interface:" + cls.getName(), th);
        }
    }

    public String getLocalSig() {
        return this.b;
    }

    public POFactory getPOFactory() throws b {
        return (POFactory) getFactory(POFactory.class);
    }

    public int getPluginVersion() {
        return this.d;
    }

    public void update(String str, String str2) {
        if (this.h) {
            a aVar = new a(this.a);
            aVar.a(this.j);
            aVar.a(str, str2);
        }
    }
}
