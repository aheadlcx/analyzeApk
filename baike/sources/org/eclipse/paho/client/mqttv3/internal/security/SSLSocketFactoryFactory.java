package org.eclipse.paho.client.mqttv3.internal.security;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.msgpack.core.MessagePack.Code;

public class SSLSocketFactoryFactory {
    public static final String CIPHERSUITES = "com.ibm.ssl.enabledCipherSuites";
    public static final String CLIENTAUTH = "com.ibm.ssl.clientAuthentication";
    public static final String DEFAULT_PROTOCOL = "TLS";
    public static final String JSSEPROVIDER = "com.ibm.ssl.contextProvider";
    public static final String KEYSTORE = "com.ibm.ssl.keyStore";
    public static final String KEYSTOREMGR = "com.ibm.ssl.keyManager";
    public static final String KEYSTOREPROVIDER = "com.ibm.ssl.keyStoreProvider";
    public static final String KEYSTOREPWD = "com.ibm.ssl.keyStorePassword";
    public static final String KEYSTORETYPE = "com.ibm.ssl.keyStoreType";
    public static final String SSLPROTOCOL = "com.ibm.ssl.protocol";
    public static final String SYSKEYMGRALGO = "ssl.KeyManagerFactory.algorithm";
    public static final String SYSKEYSTORE = "javax.net.ssl.keyStore";
    public static final String SYSKEYSTOREPWD = "javax.net.ssl.keyStorePassword";
    public static final String SYSKEYSTORETYPE = "javax.net.ssl.keyStoreType";
    public static final String SYSTRUSTMGRALGO = "ssl.TrustManagerFactory.algorithm";
    public static final String SYSTRUSTSTORE = "javax.net.ssl.trustStore";
    public static final String SYSTRUSTSTOREPWD = "javax.net.ssl.trustStorePassword";
    public static final String SYSTRUSTSTORETYPE = "javax.net.ssl.trustStoreType";
    public static final String TRUSTSTORE = "com.ibm.ssl.trustStore";
    public static final String TRUSTSTOREMGR = "com.ibm.ssl.trustManager";
    public static final String TRUSTSTOREPROVIDER = "com.ibm.ssl.trustStoreProvider";
    public static final String TRUSTSTOREPWD = "com.ibm.ssl.trustStorePassword";
    public static final String TRUSTSTORETYPE = "com.ibm.ssl.trustStoreType";
    private static final String[] a = new String[]{SSLPROTOCOL, JSSEPROVIDER, KEYSTORE, KEYSTOREPWD, KEYSTORETYPE, KEYSTOREPROVIDER, KEYSTOREMGR, TRUSTSTORE, TRUSTSTOREPWD, TRUSTSTORETYPE, TRUSTSTOREPROVIDER, TRUSTSTOREMGR, CIPHERSUITES, CLIENTAUTH};
    private static final byte[] d = new byte[]{(byte) -99, (byte) -89, Code.STR8, Byte.MIN_VALUE, (byte) 5, (byte) -72, (byte) -119, (byte) -100};
    private Hashtable b;
    private Properties c;
    private Logger e;

    public static boolean isSupportedOnJVM() throws LinkageError, ExceptionInInitializerError {
        try {
            Class.forName("javax.net.ssl.SSLServerSocketFactory");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public SSLSocketFactoryFactory() {
        this.e = null;
        this.b = new Hashtable();
    }

    public SSLSocketFactoryFactory(Logger logger) {
        this();
        this.e = logger;
    }

    private boolean a(String str) {
        int i = 0;
        while (i < a.length && !a[i].equals(str)) {
            i++;
        }
        if (i < a.length) {
            return true;
        }
        return false;
    }

    private void a(Properties properties) throws IllegalArgumentException {
        for (String str : properties.keySet()) {
            if (!a(str)) {
                throw new IllegalArgumentException(new StringBuffer(String.valueOf(str)).append(" is not a valid IBM SSL property key.").toString());
            }
        }
    }

    public static char[] toChar(byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            return null;
        }
        char[] cArr = new char[(bArr.length / 2)];
        int i2 = 0;
        while (i2 < bArr.length) {
            int i3 = i + 1;
            int i4 = i2 + 1;
            int i5 = bArr[i2] & 255;
            i2 = i4 + 1;
            cArr[i] = (char) (((bArr[i4] & 255) << 8) + i5);
            i = i3;
        }
        return cArr;
    }

    public static byte[] toByte(char[] cArr) {
        int i = 0;
        if (cArr == null) {
            return null;
        }
        byte[] bArr = new byte[(cArr.length * 2)];
        int i2 = 0;
        while (i < cArr.length) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) (cArr[i] & 255);
            int i4 = i3 + 1;
            i2 = i + 1;
            bArr[i3] = (byte) ((cArr[i] >> 8) & 255);
            i = i2;
            i2 = i4;
        }
        return bArr;
    }

    public static String obfuscate(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        byte[] toByte = toByte(cArr);
        for (int i = 0; i < toByte.length; i++) {
            toByte[i] = (byte) ((toByte[i] ^ d[i % d.length]) & 255);
        }
        return new StringBuffer("{xor}").append(new String(SimpleBase64Encoder.encode(toByte))).toString();
    }

    public static char[] deObfuscate(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] decode = SimpleBase64Encoder.decode(str.substring("{xor}".length()));
            for (int i = 0; i < decode.length; i++) {
                decode[i] = (byte) ((decode[i] ^ d[i % d.length]) & 255);
            }
            return toChar(decode);
        } catch (Exception e) {
            return null;
        }
    }

    public static String packCipherSuites(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < strArr.length; i++) {
            stringBuffer.append(strArr[i]);
            if (i < strArr.length - 1) {
                stringBuffer.append(',');
            }
        }
        return stringBuffer.toString();
    }

    public static String[] unpackCipherSuites(String str) {
        if (str == null) {
            return null;
        }
        Vector vector = new Vector();
        int indexOf = str.indexOf(44);
        int i = 0;
        while (indexOf > -1) {
            vector.add(str.substring(i, indexOf));
            i = indexOf + 1;
            indexOf = str.indexOf(44, i);
        }
        vector.add(str.substring(i));
        String[] strArr = new String[vector.size()];
        vector.toArray(strArr);
        return strArr;
    }

    private void b(Properties properties) {
        String property = properties.getProperty(KEYSTOREPWD);
        if (!(property == null || property.startsWith("{xor}"))) {
            properties.put(KEYSTOREPWD, obfuscate(property.toCharArray()));
        }
        property = properties.getProperty(TRUSTSTOREPWD);
        if (property != null && !property.startsWith("{xor}")) {
            properties.put(TRUSTSTOREPWD, obfuscate(property.toCharArray()));
        }
    }

    public void initialize(Properties properties, String str) throws IllegalArgumentException {
        a(properties);
        Properties properties2 = new Properties();
        properties2.putAll(properties);
        b(properties2);
        if (str != null) {
            this.b.put(str, properties2);
        } else {
            this.c = properties2;
        }
    }

    public void merge(Properties properties, String str) throws IllegalArgumentException {
        a(properties);
        Properties properties2 = this.c;
        if (str != null) {
            properties2 = (Properties) this.b.get(str);
        }
        if (properties2 == null) {
            properties2 = new Properties();
        }
        b(properties);
        properties2.putAll(properties);
        if (str != null) {
            this.b.put(str, properties2);
        } else {
            this.c = properties2;
        }
    }

    public boolean remove(String str) {
        if (str != null) {
            if (this.b.remove(str) != null) {
                return true;
            }
            return false;
        } else if (this.c == null) {
            return false;
        } else {
            this.c = null;
            return true;
        }
    }

    public Properties getConfiguration(String str) {
        Properties properties;
        if (str == null) {
            properties = this.c;
        } else {
            properties = this.b.get(str);
        }
        return properties;
    }

    private String a(String str, String str2, String str3) {
        String a = a(str, str2);
        if (a == null && str3 != null) {
            return System.getProperty(str3);
        }
        return a;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r3, java.lang.String r4) {
        /*
        r2 = this;
        r1 = 0;
        if (r3 == 0) goto L_0x0020;
    L_0x0003:
        r0 = r2.b;
        r0 = r0.get(r3);
        r0 = (java.util.Properties) r0;
    L_0x000b:
        if (r0 == 0) goto L_0x0014;
    L_0x000d:
        r0 = r0.getProperty(r4);
        if (r0 == 0) goto L_0x0015;
    L_0x0013:
        return r0;
    L_0x0014:
        r0 = r1;
    L_0x0015:
        r1 = r2.c;
        if (r1 == 0) goto L_0x0013;
    L_0x0019:
        r0 = r1.getProperty(r4);
        if (r0 == 0) goto L_0x0013;
    L_0x001f:
        goto L_0x0013;
    L_0x0020:
        r0 = r1;
        goto L_0x000b;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory.a(java.lang.String, java.lang.String):java.lang.String");
    }

    public String getSSLProtocol(String str) {
        return a(str, SSLPROTOCOL, null);
    }

    public String getJSSEProvider(String str) {
        return a(str, JSSEPROVIDER, null);
    }

    public String getKeyStore(String str) {
        String str2 = KEYSTORE;
        String str3 = SYSKEYSTORE;
        str2 = a(str, str2);
        if (str2 == null && str3 != null) {
            return System.getProperty(str3);
        }
        return str2;
    }

    public char[] getKeyStorePassword(String str) {
        String a = a(str, KEYSTOREPWD, SYSKEYSTOREPWD);
        if (a == null) {
            return null;
        }
        if (a.startsWith("{xor}")) {
            return deObfuscate(a);
        }
        return a.toCharArray();
    }

    public String getKeyStoreType(String str) {
        return a(str, KEYSTORETYPE, SYSKEYSTORETYPE);
    }

    public String getKeyStoreProvider(String str) {
        return a(str, KEYSTOREPROVIDER, null);
    }

    public String getKeyManager(String str) {
        return a(str, KEYSTOREMGR, SYSKEYMGRALGO);
    }

    public String getTrustStore(String str) {
        return a(str, TRUSTSTORE, SYSTRUSTSTORE);
    }

    public char[] getTrustStorePassword(String str) {
        String a = a(str, TRUSTSTOREPWD, SYSTRUSTSTOREPWD);
        if (a == null) {
            return null;
        }
        if (a.startsWith("{xor}")) {
            return deObfuscate(a);
        }
        return a.toCharArray();
    }

    public String getTrustStoreType(String str) {
        return a(str, TRUSTSTORETYPE, null);
    }

    public String getTrustStoreProvider(String str) {
        return a(str, TRUSTSTOREPROVIDER, null);
    }

    public String getTrustManager(String str) {
        return a(str, TRUSTSTOREMGR, SYSTRUSTMGRALGO);
    }

    public String[] getEnabledCipherSuites(String str) {
        return unpackCipherSuites(a(str, CIPHERSUITES, null));
    }

    public boolean getClientAuthentication(String str) {
        String a = a(str, CLIENTAUTH, null);
        if (a != null) {
            return Boolean.valueOf(a).booleanValue();
        }
        return false;
    }

    private SSLContext b(String str) throws MqttSecurityException {
        String str2;
        SSLContext instance;
        String str3;
        String str4;
        String str5;
        Object[] objArr;
        String str6;
        Object[] objArr2;
        String str7;
        Object[] objArr3;
        KeyManager[] keyManagers;
        char[] trustStorePassword;
        TrustManager[] trustManagerArr;
        String sSLProtocol = getSSLProtocol(str);
        if (sSLProtocol == null) {
            sSLProtocol = "TLS";
        }
        if (this.e != null) {
            Logger logger = this.e;
            str2 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
            String str8 = "getSSLContext";
            String str9 = "12000";
            Object[] objArr4 = new Object[2];
            objArr4[0] = str != null ? str : "null (broker defaults)";
            objArr4[1] = sSLProtocol;
            logger.fine(str2, str8, str9, objArr4);
        }
        String jSSEProvider = getJSSEProvider(str);
        if (jSSEProvider == null) {
            try {
                instance = SSLContext.getInstance(sSLProtocol);
            } catch (Throwable e) {
                throw new MqttSecurityException(e);
            } catch (Throwable e2) {
                throw new MqttSecurityException(e2);
            } catch (Throwable e22) {
                throw new MqttSecurityException(e22);
            } catch (Throwable e222) {
                throw new MqttSecurityException(e222);
            } catch (Throwable e2222) {
                throw new MqttSecurityException(e2222);
            } catch (Throwable e22222) {
                throw new MqttSecurityException(e22222);
            } catch (Throwable e222222) {
                throw new MqttSecurityException(e222222);
            } catch (Throwable e2222222) {
                throw new MqttSecurityException(e2222222);
            } catch (Throwable e22222222) {
                throw new MqttSecurityException(e22222222);
            } catch (Throwable e222222222) {
                throw new MqttSecurityException(e222222222);
            } catch (Throwable e2222222222) {
                throw new MqttSecurityException(e2222222222);
            } catch (Throwable e22222222222) {
                throw new MqttSecurityException(e22222222222);
            }
        }
        instance = SSLContext.getInstance(sSLProtocol, jSSEProvider);
        if (this.e != null) {
            Logger logger2 = this.e;
            str3 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
            str2 = "getSSLContext";
            str8 = "12001";
            objArr4 = new Object[2];
            objArr4[0] = str != null ? str : "null (broker defaults)";
            objArr4[1] = instance.getProvider().getName();
            logger2.fine(str3, str2, str8, objArr4);
        }
        str2 = a(str, KEYSTORE, null);
        if (null == null) {
            Object[] objArr5;
            if (str2 == null) {
                str2 = a(str, KEYSTORE, SYSKEYSTORE);
            }
            if (this.e != null) {
                logger2 = this.e;
                str3 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
                str8 = "getSSLContext";
                str4 = "12004";
                objArr5 = new Object[2];
                objArr5[0] = str != null ? str : "null (broker defaults)";
                objArr5[1] = str2 != null ? str2 : "null";
                logger2.fine(str3, str8, str4, objArr5);
            }
            char[] keyStorePassword = getKeyStorePassword(str);
            if (this.e != null) {
                logger2 = this.e;
                str3 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
                str4 = "getSSLContext";
                str5 = "12005";
                objArr = new Object[2];
                objArr[0] = str != null ? str : "null (broker defaults)";
                objArr[1] = keyStorePassword != null ? obfuscate(keyStorePassword) : "null";
                logger2.fine(str3, str4, str5, objArr);
            }
            str3 = getKeyStoreType(str);
            if (str3 == null) {
                str3 = KeyStore.getDefaultType();
            }
            if (this.e != null) {
                logger2 = this.e;
                str4 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
                str5 = "getSSLContext";
                str6 = "12006";
                objArr2 = new Object[2];
                objArr2[0] = str != null ? str : "null (broker defaults)";
                objArr2[1] = str3 != null ? str3 : "null";
                logger2.fine(str4, str5, str6, objArr2);
            }
            jSSEProvider = KeyManagerFactory.getDefaultAlgorithm();
            str4 = getKeyStoreProvider(str);
            sSLProtocol = getKeyManager(str);
            if (sSLProtocol == null) {
                sSLProtocol = jSSEProvider;
            }
            if (!(str2 == null || str3 == null || sSLProtocol == null)) {
                KeyManagerFactory instance2;
                Logger logger3;
                KeyStore instance3 = KeyStore.getInstance(str3);
                instance3.load(new FileInputStream(str2), keyStorePassword);
                if (str4 != null) {
                    instance2 = KeyManagerFactory.getInstance(sSLProtocol, str4);
                } else {
                    instance2 = KeyManagerFactory.getInstance(sSLProtocol);
                }
                if (this.e != null) {
                    logger3 = this.e;
                    str4 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
                    str6 = "getSSLContext";
                    str7 = "12010";
                    objArr3 = new Object[2];
                    objArr3[0] = str != null ? str : "null (broker defaults)";
                    if (sSLProtocol == null) {
                        sSLProtocol = "null";
                    }
                    objArr3[1] = sSLProtocol;
                    logger3.fine(str4, str6, str7, objArr3);
                    logger2 = this.e;
                    str2 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
                    str4 = "getSSLContext";
                    str6 = "12009";
                    objArr2 = new Object[2];
                    objArr2[0] = str != null ? str : "null (broker defaults)";
                    objArr2[1] = instance2.getProvider().getName();
                    logger2.fine(str2, str4, str6, objArr2);
                }
                instance2.init(instance3, keyStorePassword);
                keyManagers = instance2.getKeyManagers();
                str2 = getTrustStore(str);
                if (this.e != null) {
                    logger2 = this.e;
                    str3 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
                    str4 = "getSSLContext";
                    str5 = "12011";
                    objArr = new Object[2];
                    objArr[0] = str == null ? str : "null (broker defaults)";
                    objArr[1] = str2 == null ? str2 : "null";
                    logger2.fine(str3, str4, str5, objArr);
                }
                trustStorePassword = getTrustStorePassword(str);
                if (this.e != null) {
                    logger2 = this.e;
                    str3 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
                    str5 = "getSSLContext";
                    str6 = "12012";
                    objArr2 = new Object[2];
                    objArr2[0] = str == null ? str : "null (broker defaults)";
                    objArr2[1] = trustStorePassword == null ? obfuscate(trustStorePassword) : "null";
                    logger2.fine(str3, str5, str6, objArr2);
                }
                str3 = getTrustStoreType(str);
                if (str3 == null) {
                    str3 = KeyStore.getDefaultType();
                }
                if (this.e != null) {
                    logger2 = this.e;
                    str5 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
                    str6 = "getSSLContext";
                    str7 = "12013";
                    objArr3 = new Object[2];
                    objArr3[0] = str == null ? str : "null (broker defaults)";
                    objArr3[1] = str3 == null ? str3 : "null";
                    logger2.fine(str5, str6, str7, objArr3);
                }
                jSSEProvider = TrustManagerFactory.getDefaultAlgorithm();
                str5 = getTrustStoreProvider(str);
                sSLProtocol = getTrustManager(str);
                if (sSLProtocol == null) {
                    sSLProtocol = jSSEProvider;
                }
                if (str2 != null || str3 == null || sSLProtocol == null) {
                    trustManagerArr = null;
                } else {
                    TrustManagerFactory instance4;
                    KeyStore instance5 = KeyStore.getInstance(str3);
                    instance5.load(new FileInputStream(str2), trustStorePassword);
                    if (str5 != null) {
                        instance4 = TrustManagerFactory.getInstance(sSLProtocol, str5);
                    } else {
                        instance4 = TrustManagerFactory.getInstance(sSLProtocol);
                    }
                    if (this.e != null) {
                        logger3 = this.e;
                        str4 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
                        str5 = "getSSLContext";
                        str6 = "12017";
                        objArr2 = new Object[2];
                        objArr2[0] = str != null ? str : "null (broker defaults)";
                        if (sSLProtocol == null) {
                            sSLProtocol = "null";
                        }
                        objArr2[1] = sSLProtocol;
                        logger3.fine(str4, str5, str6, objArr2);
                        Logger logger4 = this.e;
                        jSSEProvider = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
                        str2 = "getSSLContext";
                        str4 = "12016";
                        objArr5 = new Object[2];
                        if (str == null) {
                            str = "null (broker defaults)";
                        }
                        objArr5[0] = str;
                        objArr5[1] = instance4.getProvider().getName();
                        logger4.fine(jSSEProvider, str2, str4, objArr5);
                    }
                    instance4.init(instance5);
                    trustManagerArr = instance4.getTrustManagers();
                }
                instance.init(keyManagers, trustManagerArr, null);
                return instance;
            }
        }
        keyManagers = null;
        str2 = getTrustStore(str);
        if (this.e != null) {
            logger2 = this.e;
            str3 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
            str4 = "getSSLContext";
            str5 = "12011";
            objArr = new Object[2];
            if (str == null) {
            }
            objArr[0] = str == null ? str : "null (broker defaults)";
            if (str2 == null) {
            }
            objArr[1] = str2 == null ? str2 : "null";
            logger2.fine(str3, str4, str5, objArr);
        }
        trustStorePassword = getTrustStorePassword(str);
        if (this.e != null) {
            logger2 = this.e;
            str3 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
            str5 = "getSSLContext";
            str6 = "12012";
            objArr2 = new Object[2];
            if (str == null) {
            }
            objArr2[0] = str == null ? str : "null (broker defaults)";
            if (trustStorePassword == null) {
            }
            objArr2[1] = trustStorePassword == null ? obfuscate(trustStorePassword) : "null";
            logger2.fine(str3, str5, str6, objArr2);
        }
        str3 = getTrustStoreType(str);
        if (str3 == null) {
            str3 = KeyStore.getDefaultType();
        }
        if (this.e != null) {
            logger2 = this.e;
            str5 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
            str6 = "getSSLContext";
            str7 = "12013";
            objArr3 = new Object[2];
            if (str == null) {
            }
            objArr3[0] = str == null ? str : "null (broker defaults)";
            if (str3 == null) {
            }
            objArr3[1] = str3 == null ? str3 : "null";
            logger2.fine(str5, str6, str7, objArr3);
        }
        jSSEProvider = TrustManagerFactory.getDefaultAlgorithm();
        str5 = getTrustStoreProvider(str);
        sSLProtocol = getTrustManager(str);
        if (sSLProtocol == null) {
            sSLProtocol = jSSEProvider;
        }
        if (str2 != null) {
        }
        trustManagerArr = null;
        instance.init(keyManagers, trustManagerArr, null);
        return instance;
    }

    public SSLSocketFactory createSocketFactory(String str) throws MqttSecurityException {
        SSLContext b = b(str);
        if (this.e != null) {
            Logger logger = this.e;
            String str2 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
            String str3 = "createSocketFactory";
            String str4 = "12020";
            Object[] objArr = new Object[2];
            objArr[0] = str != null ? str : "null (broker defaults)";
            objArr[1] = getEnabledCipherSuites(str) != null ? a(str, CIPHERSUITES, null) : "null (using platform-enabled cipher suites)";
            logger.fine(str2, str3, str4, objArr);
        }
        return b.getSocketFactory();
    }
}
