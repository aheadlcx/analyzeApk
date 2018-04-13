package com.umeng.onlineconfig.proguard;

import com.umeng.onlineconfig.OnlineConfigLog;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONObject;

public class c {
    private static final String a = c.class.getName();
    private Map<String, String> b;

    public <T extends e> T a(d dVar, Class<T> cls) {
        JSONObject a;
        String trim = dVar.c().trim();
        b(trim);
        if (d.c.equals(trim)) {
            a = a(dVar.b());
        } else if (d.b.equals(trim)) {
            a = a(dVar.d, dVar.a());
        } else {
            a = null;
        }
        if (a == null) {
            return null;
        }
        try {
            return (e) cls.getConstructor(new Class[]{JSONObject.class}).newInstance(new Object[]{a});
        } catch (Exception e) {
            OnlineConfigLog.e(a, "SecurityException", e);
            return null;
        } catch (Exception e2) {
            OnlineConfigLog.e(a, "NoSuchMethodException", e2);
            return null;
        } catch (Exception e22) {
            OnlineConfigLog.e(a, "IllegalArgumentException", e22);
            return null;
        } catch (Exception e222) {
            OnlineConfigLog.e(a, "InstantiationException", e222);
            return null;
        } catch (Exception e2222) {
            OnlineConfigLog.e(a, "IllegalAccessException", e2222);
            return null;
        } catch (Exception e22222) {
            OnlineConfigLog.e(a, "InvocationTargetException", e22222);
            return null;
        }
    }

    private JSONObject a(String str, JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        int nextInt = new Random().nextInt(1000);
        OnlineConfigLog.d(a, nextInt + ":\trequest: " + str + h.a + jSONObject2);
        Object httpPost = new HttpPost(str);
        HttpClient defaultHttpClient = new DefaultHttpClient(b());
        try {
            if (a()) {
                byte[] a = f.a(jSONObject2, Charset.defaultCharset().name());
                httpPost.addHeader("Content-Encoding", "deflate");
                httpPost.setEntity(new InputStreamEntity(new ByteArrayInputStream(a), (long) a.length));
            } else {
                List arrayList = new ArrayList(1);
                arrayList.add(new BasicNameValuePair("content", jSONObject2));
                httpPost.setEntity(new UrlEncodedFormEntity(arrayList, "UTF-8"));
            }
            HttpResponse execute = defaultHttpClient.execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = execute.getEntity();
                if (entity == null) {
                    return null;
                }
                InputStream inputStream;
                InputStream content = entity.getContent();
                Header firstHeader = execute.getFirstHeader("Content-Encoding");
                if (firstHeader == null || !firstHeader.getValue().equalsIgnoreCase("deflate")) {
                    inputStream = content;
                } else {
                    inputStream = new InflaterInputStream(content);
                }
                String a2 = a(inputStream);
                OnlineConfigLog.i(a, nextInt + ":\tresponse: " + h.a + a2);
                if (a2 == null) {
                    return null;
                }
                return new JSONObject(a2);
            }
            OnlineConfigLog.d(a, nextInt + ":\tFailed to send message. StatusCode = " + execute.getStatusLine().getStatusCode() + h.a + str);
            return null;
        } catch (Exception e) {
            OnlineConfigLog.d(a, nextInt + ":\tClientProtocolException,Failed to send message." + str, e);
            return null;
        } catch (Exception e2) {
            OnlineConfigLog.d(a, nextInt + ":\tIOException,Failed to send message." + str, e2);
            return null;
        } catch (Exception e22) {
            OnlineConfigLog.d(a, nextInt + ":\tIOException,Failed to send message." + str, e22);
            return null;
        }
    }

    public boolean a() {
        return false;
    }

    private static String a(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 8192);
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuilder.append(readLine + "\n");
            } catch (Exception e) {
                stringBuilder = a;
                OnlineConfigLog.e(stringBuilder, "Caught IOException in convertStreamToString()", e);
                return null;
            } finally {
                try {
                    inputStream.close();
                } catch (Exception e2) {
                    OnlineConfigLog.e(a, "Caught IOException in convertStreamToString()", e2);
                    return null;
                }
            }
        }
        return stringBuilder.toString();
    }

    private JSONObject a(String str) {
        int nextInt = new Random().nextInt(1000);
        try {
            String property = System.getProperty("line.separator");
            if (str.length() <= 1) {
                OnlineConfigLog.e(a, nextInt + ":\tInvalid baseUrl.");
                return null;
            }
            OnlineConfigLog.i(a, nextInt + ":\tget: " + str);
            HttpUriRequest httpGet = new HttpGet(str);
            if (this.b != null && this.b.size() > 0) {
                for (String str2 : this.b.keySet()) {
                    httpGet.addHeader(str2, (String) this.b.get(str2));
                }
            }
            HttpResponse execute = new DefaultHttpClient(b()).execute(httpGet);
            if (execute.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = execute.getEntity();
                if (entity != null) {
                    InputStream gZIPInputStream;
                    InputStream content = entity.getContent();
                    Header firstHeader = execute.getFirstHeader("Content-Encoding");
                    if (firstHeader != null && firstHeader.getValue().equalsIgnoreCase("gzip")) {
                        OnlineConfigLog.i(a, nextInt + "  Use GZIPInputStream get data....");
                        gZIPInputStream = new GZIPInputStream(content);
                    } else if (firstHeader == null || !firstHeader.getValue().equalsIgnoreCase("deflate")) {
                        gZIPInputStream = content;
                    } else {
                        OnlineConfigLog.i(a, nextInt + "  Use InflaterInputStream get data....");
                        gZIPInputStream = new InflaterInputStream(content);
                    }
                    String a = a(gZIPInputStream);
                    OnlineConfigLog.i(a, nextInt + ":\tresponse: " + property + a);
                    if (a == null) {
                        return null;
                    }
                    return new JSONObject(a);
                }
            }
            OnlineConfigLog.d(a, nextInt + ":\tFailed to send message. StatusCode = " + execute.getStatusLine().getStatusCode() + h.a + str);
            return null;
        } catch (Exception e) {
            OnlineConfigLog.d(a, nextInt + ":\tClientProtocolException,Failed to send message." + str, e);
            return null;
        } catch (Exception e2) {
            OnlineConfigLog.d(a, nextInt + ":\tIOException,Failed to send message." + str, e2);
            return null;
        }
    }

    private HttpParams b() {
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
        HttpProtocolParams.setUserAgent(basicHttpParams, System.getProperty("http.agent"));
        return basicHttpParams;
    }

    public c a(Map<String, String> map) {
        this.b = map;
        return this;
    }

    private void b(String str) {
        if (h.a(str) || (d.c.equals(str.trim()) ^ d.b.equals(str.trim())) == 0) {
            throw new RuntimeException("验证请求方式失败[" + str + "]");
        }
    }
}
