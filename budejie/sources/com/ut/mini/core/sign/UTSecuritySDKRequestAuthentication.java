package com.ut.mini.core.sign;

import android.content.Context;
import com.alibaba.mtl.log.b;
import com.alibaba.mtl.log.e.i;
import com.alibaba.wireless.security.open.securesignature.SecureSignatureDefine;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class UTSecuritySDKRequestAuthentication implements IUTRequestAuthentication {
    private boolean D = false;
    private String Z;
    private Class a = null;
    /* renamed from: a */
    private Field f71a = null;
    /* renamed from: a */
    private Method f72a = null;
    private Object b = null;
    /* renamed from: b */
    private String f73b = null;
    /* renamed from: b */
    private Field f74b = null;
    private Object c = null;
    /* renamed from: c */
    private Field f75c = null;
    private int z = 1;

    public String getAppkey() {
        return this.f73b;
    }

    public UTSecuritySDKRequestAuthentication(String str, String str2) {
        this.f73b = str;
        this.Z = str2;
    }

    private synchronized void F() {
        Throwable th;
        Method method;
        boolean booleanValue;
        Class cls = null;
        synchronized (this) {
            if (!this.D) {
                Class cls2;
                try {
                    cls2 = Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager");
                    try {
                        this.b = cls2.getMethod("getInstance", new Class[]{Context.class}).invoke(null, new Object[]{b.a().getContext()});
                        this.c = cls2.getMethod("getSecureSignatureComp", new Class[0]).invoke(this.b, new Object[0]);
                    } catch (Throwable th2) {
                        th = th2;
                        i.a("initSecurityCheck", th.getMessage());
                        if (cls2 != null) {
                            try {
                                this.a = Class.forName("com.alibaba.wireless.security.open.SecurityGuardParamContext");
                                this.f71a = this.a.getDeclaredField("appKey");
                                this.f74b = this.a.getDeclaredField("paramMap");
                                this.f75c = this.a.getDeclaredField("requestType");
                                method = cls2.getMethod("isOpen", new Class[0]);
                            } catch (Throwable th3) {
                                i.a("initSecurityCheck", th3.getMessage());
                            }
                            if (method != null) {
                                booleanValue = ((Boolean) method.invoke(this.b, new Object[0])).booleanValue();
                            } else {
                                try {
                                    cls = Class.forName("com.taobao.wireless.security.sdk.securitybody.ISecurityBodyComponent");
                                } catch (Throwable th32) {
                                    i.a("initSecurityCheck", th32.getMessage());
                                }
                                if (cls == null) {
                                    booleanValue = true;
                                } else {
                                    booleanValue = false;
                                }
                            }
                            this.z = booleanValue ? 1 : 12;
                            this.f72a = Class.forName("com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent").getMethod("signRequest", new Class[]{this.a, String.class});
                        }
                        this.D = true;
                    }
                } catch (Throwable th4) {
                    th32 = th4;
                    cls2 = cls;
                    i.a("initSecurityCheck", th32.getMessage());
                    if (cls2 != null) {
                        this.a = Class.forName("com.alibaba.wireless.security.open.SecurityGuardParamContext");
                        this.f71a = this.a.getDeclaredField("appKey");
                        this.f74b = this.a.getDeclaredField("paramMap");
                        this.f75c = this.a.getDeclaredField("requestType");
                        method = cls2.getMethod("isOpen", new Class[0]);
                        if (method != null) {
                            cls = Class.forName("com.taobao.wireless.security.sdk.securitybody.ISecurityBodyComponent");
                            if (cls == null) {
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
                        this.f72a = Class.forName("com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent").getMethod("signRequest", new Class[]{this.a, String.class});
                    }
                    this.D = true;
                }
                if (cls2 != null) {
                    this.a = Class.forName("com.alibaba.wireless.security.open.SecurityGuardParamContext");
                    this.f71a = this.a.getDeclaredField("appKey");
                    this.f74b = this.a.getDeclaredField("paramMap");
                    this.f75c = this.a.getDeclaredField("requestType");
                    method = cls2.getMethod("isOpen", new Class[0]);
                    if (method != null) {
                        booleanValue = ((Boolean) method.invoke(this.b, new Object[0])).booleanValue();
                    } else {
                        cls = Class.forName("com.taobao.wireless.security.sdk.securitybody.ISecurityBodyComponent");
                        if (cls == null) {
                            booleanValue = true;
                        } else {
                            booleanValue = false;
                        }
                    }
                    if (booleanValue) {
                    }
                    this.z = booleanValue ? 1 : 12;
                    this.f72a = Class.forName("com.alibaba.wireless.security.open.securesignature.ISecureSignatureComponent").getMethod("signRequest", new Class[]{this.a, String.class});
                }
                this.D = true;
            }
        }
    }

    public String getSign(String str) {
        if (!this.D) {
            F();
        }
        if (this.f73b == null) {
            i.a("UTSecuritySDKRequestAuthentication:getSign", (Object) "There is no appkey,please check it!");
            return null;
        } else if (str == null) {
            return null;
        } else {
            String str2;
            if (!(this.b == null || this.a == null || this.f71a == null || this.f74b == null || this.f75c == null || this.f72a == null || this.c == null)) {
                try {
                    Object newInstance = this.a.newInstance();
                    this.f71a.set(newInstance, this.f73b);
                    ((Map) this.f74b.get(newInstance)).put(SecureSignatureDefine.OPEN_KEY_SIGN_INPUT, str);
                    this.f75c.set(newInstance, Integer.valueOf(this.z));
                    str2 = (String) this.f72a.invoke(this.c, new Object[]{newInstance, this.Z});
                } catch (InstantiationException e) {
                    e.printStackTrace();
                    str2 = null;
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                    str2 = null;
                } catch (IllegalArgumentException e3) {
                    e3.printStackTrace();
                    str2 = null;
                } catch (InvocationTargetException e4) {
                    e4.printStackTrace();
                }
                return str2;
            }
            str2 = null;
            return str2;
        }
    }

    public String getAuthCode() {
        return this.Z;
    }
}
