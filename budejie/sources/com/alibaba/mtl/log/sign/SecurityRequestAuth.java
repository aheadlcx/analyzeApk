package com.alibaba.mtl.log.sign;

import android.content.Context;
import com.alibaba.mtl.log.a;
import com.alibaba.mtl.log.e.i;
import com.alibaba.wireless.security.open.securesignature.SecureSignatureDefine;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class SecurityRequestAuth implements IRequestAuth {
    private boolean D = false;
    private String Z;
    private Class a = null;
    /* renamed from: a */
    private Field f42a = null;
    /* renamed from: a */
    private Method f43a = null;
    private Object b = null;
    /* renamed from: b */
    private String f44b = null;
    /* renamed from: b */
    private Field f45b = null;
    private Object c = null;
    /* renamed from: c */
    private Field f46c = null;
    private int z = 1;

    public String getAppkey() {
        return this.f44b;
    }

    public SecurityRequestAuth(String str, String str2) {
        this.f44b = str;
        this.Z = str2;
    }

    private synchronized void F() {
        Class cls;
        Throwable th;
        Method method;
        Class cls2 = null;
        synchronized (this) {
            if (!this.D) {
                boolean booleanValue;
                try {
                    cls = Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager");
                    try {
                        this.b = cls.getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{a.getContext()});
                        this.c = cls.getMethod("getSecureSignatureComp", new Class[0]).invoke(this.b, new Object[0]);
                    } catch (Throwable th2) {
                        th = th2;
                        i.a("SecurityRequestAuth", "initSecurityCheck", th);
                        if (cls != null) {
                            try {
                                this.a = Class.forName("com.alibaba.wireless.security.open.SecurityGuardParamContext");
                                this.f42a = this.a.getDeclaredField("appKey");
                                this.f45b = this.a.getDeclaredField("paramMap");
                                this.f46c = this.a.getDeclaredField("requestType");
                                method = cls.getMethod("isOpen", new Class[0]);
                            } catch (Throwable th3) {
                                i.a("SecurityRequestAuth", "initSecurityCheck", th3);
                            }
                            if (method != null) {
                                booleanValue = ((Boolean) method.invoke(this.b, new Object[0])).booleanValue();
                            } else {
                                try {
                                    cls2 = Class.forName("com.taobao.wireless.security.sdk.securitybody.ISecurityBodyComponent");
                                } catch (Throwable th32) {
                                    i.a("SecurityRequestAuth", "initSecurityCheck", th32);
                                }
                                if (cls2 == null) {
                                    booleanValue = true;
                                } else {
                                    booleanValue = false;
                                }
                            }
                            this.z = booleanValue ? 1 : 12;
                            this.f43a = Class.forName("com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent").getMethod("signRequest", new Class[]{this.a, String.class});
                        }
                        this.D = true;
                    }
                } catch (Throwable th4) {
                    th32 = th4;
                    cls = cls2;
                    i.a("SecurityRequestAuth", "initSecurityCheck", th32);
                    if (cls != null) {
                        this.a = Class.forName("com.alibaba.wireless.security.open.SecurityGuardParamContext");
                        this.f42a = this.a.getDeclaredField("appKey");
                        this.f45b = this.a.getDeclaredField("paramMap");
                        this.f46c = this.a.getDeclaredField("requestType");
                        method = cls.getMethod("isOpen", new Class[0]);
                        if (method != null) {
                            cls2 = Class.forName("com.taobao.wireless.security.sdk.securitybody.ISecurityBodyComponent");
                            if (cls2 == null) {
                                booleanValue = false;
                            } else {
                                booleanValue = true;
                            }
                        } else {
                            booleanValue = ((Boolean) method.invoke(this.b, new Object[0])).booleanValue();
                        }
                        if (booleanValue) {
                        }
                        this.z = booleanValue ? 1 : 12;
                        this.f43a = Class.forName("com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent").getMethod("signRequest", new Class[]{this.a, String.class});
                    }
                    this.D = true;
                }
                if (cls != null) {
                    this.a = Class.forName("com.alibaba.wireless.security.open.SecurityGuardParamContext");
                    this.f42a = this.a.getDeclaredField("appKey");
                    this.f45b = this.a.getDeclaredField("paramMap");
                    this.f46c = this.a.getDeclaredField("requestType");
                    method = cls.getMethod("isOpen", new Class[0]);
                    if (method != null) {
                        booleanValue = ((Boolean) method.invoke(this.b, new Object[0])).booleanValue();
                    } else {
                        cls2 = Class.forName("com.taobao.wireless.security.sdk.securitybody.ISecurityBodyComponent");
                        if (cls2 == null) {
                            booleanValue = true;
                        } else {
                            booleanValue = false;
                        }
                    }
                    if (booleanValue) {
                    }
                    this.z = booleanValue ? 1 : 12;
                    this.f43a = Class.forName("com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent").getMethod("signRequest", new Class[]{this.a, String.class});
                }
                this.D = true;
            }
        }
    }

    public String getSign(String str) {
        if (!this.D) {
            F();
        }
        if (this.f44b == null) {
            i.a("SecurityRequestAuth", "There is no appkey,please check it!");
            return null;
        } else if (str == null) {
            return null;
        } else {
            String str2;
            if (!(this.b == null || this.a == null || this.f42a == null || this.f45b == null || this.f46c == null || this.f43a == null || this.c == null)) {
                try {
                    Object newInstance = this.a.newInstance();
                    this.f42a.set(newInstance, this.f44b);
                    ((Map) this.f45b.get(newInstance)).put(SecureSignatureDefine.OPEN_KEY_SIGN_INPUT, str);
                    this.f46c.set(newInstance, Integer.valueOf(this.z));
                    str2 = (String) this.f43a.invoke(this.c, new Object[]{newInstance, this.Z});
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return str2;
            }
            str2 = null;
            return str2;
        }
    }
}
