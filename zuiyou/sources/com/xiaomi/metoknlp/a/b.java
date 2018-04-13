package com.xiaomi.metoknlp.a;

import android.text.TextUtils;
import com.tencent.bugly.BuglyStrategy$a;
import com.tencent.connect.common.Constants;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class b {
    public static String a(String str, Map map) {
        String str2;
        BufferedReader bufferedReader;
        HttpURLConnection httpURLConnection;
        Throwable th;
        BufferedReader bufferedReader2 = null;
        String str3 = "";
        if (TextUtils.isEmpty(str)) {
            return str3;
        }
        try {
            URL url = new URL(str);
            HttpURLConnection httpURLConnection2;
            try {
                httpURLConnection2 = url.getProtocol().toLowerCase().equals("https") ? (HttpsURLConnection) url.openConnection() : (HttpURLConnection) url.openConnection();
                try {
                    httpURLConnection2.setConnectTimeout(BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH);
                    httpURLConnection2.setReadTimeout(BuglyStrategy$a.MAX_USERDATA_VALUE_LENGTH);
                    httpURLConnection2.setRequestMethod(Constants.HTTP_GET);
                    httpURLConnection2.setDoOutput(false);
                    if (map != null && map.size() > 0) {
                        for (String str22 : map.keySet()) {
                            httpURLConnection2.addRequestProperty(str22, (String) map.get(str22));
                        }
                    }
                    httpURLConnection2.connect();
                    if (httpURLConnection2.getResponseCode() == 200) {
                        BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(httpURLConnection2.getInputStream()));
                        try {
                            StringBuffer stringBuffer = new StringBuffer();
                            while (true) {
                                String readLine = bufferedReader3.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                stringBuffer.append(readLine);
                            }
                            str3 = stringBuffer.toString();
                            bufferedReader3.close();
                            bufferedReader2 = bufferedReader3;
                            str22 = str3;
                        } catch (Exception e) {
                            bufferedReader = bufferedReader3;
                            httpURLConnection = httpURLConnection2;
                            str22 = str3;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception e2) {
                                    return str22;
                                }
                            }
                            if (httpURLConnection != null) {
                                return str22;
                            }
                            httpURLConnection.disconnect();
                            return str22;
                        } catch (Throwable th2) {
                            bufferedReader2 = bufferedReader3;
                            th = th2;
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (Exception e3) {
                                    throw th;
                                }
                            }
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            throw th;
                        }
                    }
                    str22 = str3;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception e4) {
                            return str22;
                        }
                    }
                    if (httpURLConnection2 == null) {
                        return str22;
                    }
                    httpURLConnection2.disconnect();
                    return str22;
                } catch (Exception e5) {
                    bufferedReader = null;
                    str22 = str3;
                    httpURLConnection = httpURLConnection2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (httpURLConnection != null) {
                        return str22;
                    }
                    httpURLConnection.disconnect();
                    return str22;
                } catch (Throwable th3) {
                    th = th3;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e6) {
                bufferedReader = null;
                str22 = str3;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (httpURLConnection != null) {
                    return str22;
                }
                httpURLConnection.disconnect();
                return str22;
            } catch (Throwable th4) {
                th = th4;
                httpURLConnection2 = null;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        } catch (MalformedURLException e7) {
            return str3;
        }
    }
}
