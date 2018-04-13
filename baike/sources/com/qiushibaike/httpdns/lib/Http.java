package com.qiushibaike.httpdns.lib;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Http {
    private static HttpURLConnection b(String str, String str2) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(8000);
        httpURLConnection.setReadTimeout(8000);
        httpURLConnection.setRequestMethod("GET");
        if (!TextUtils.isEmpty(str2)) {
            httpURLConnection.setRequestProperty("Host", str2);
        }
        return httpURLConnection;
    }

    static String a(String str, String str2) throws IOException {
        HttpURLConnection b;
        IOException e;
        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        String str3;
        Object obj;
        Throwable th;
        InputStream inputStream = null;
        String str4 = "";
        BufferedReader bufferedReader2;
        try {
            b = b(str, str2);
            try {
                if (b.getResponseCode() != 200) {
                    throw new IOException(String.format("HttpStatusCode [%s]", new Object[]{Integer.valueOf(b.getResponseCode())}));
                }
                InputStream inputStream2 = b.getInputStream();
                try {
                    bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2, "utf-8"));
                } catch (IOException e2) {
                    e = e2;
                    httpURLConnection = b;
                    inputStream = inputStream2;
                    Object obj2 = null;
                    httpURLConnection.disconnect();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                    if (bufferedReader == null) {
                        try {
                            bufferedReader.close();
                            inputStream = e;
                            str3 = str4;
                        } catch (IOException e4) {
                            obj = e;
                            str3 = str4;
                        }
                    } else {
                        obj = e;
                        str3 = str4;
                    }
                    if (inputStream == null) {
                        return str3;
                    }
                    throw inputStream;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader2 = null;
                    inputStream = inputStream2;
                    b.disconnect();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e5) {
                        }
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e6) {
                        }
                    }
                    throw th;
                }
                try {
                    StringBuffer stringBuffer = new StringBuffer();
                    while (true) {
                        String readLine = bufferedReader2.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuffer.append(readLine);
                    }
                    str3 = stringBuffer.toString();
                    b.disconnect();
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (IOException e7) {
                        }
                    }
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e8) {
                        }
                    }
                } catch (IOException e9) {
                    e = e9;
                    inputStream = inputStream2;
                    bufferedReader = bufferedReader2;
                    httpURLConnection = b;
                    httpURLConnection.disconnect();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (bufferedReader == null) {
                        obj = e;
                        str3 = str4;
                    } else {
                        bufferedReader.close();
                        inputStream = e;
                        str3 = str4;
                    }
                    if (inputStream == null) {
                        return str3;
                    }
                    throw inputStream;
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = inputStream2;
                    b.disconnect();
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    throw th;
                }
                if (inputStream == null) {
                    return str3;
                }
                throw inputStream;
            } catch (IOException e10) {
                e = e10;
                bufferedReader = null;
                httpURLConnection = b;
                httpURLConnection.disconnect();
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedReader == null) {
                    bufferedReader.close();
                    inputStream = e;
                    str3 = str4;
                } else {
                    obj = e;
                    str3 = str4;
                }
                if (inputStream == null) {
                    return str3;
                }
                throw inputStream;
            } catch (Throwable th4) {
                th = th4;
                bufferedReader2 = null;
                b.disconnect();
                if (inputStream != null) {
                    inputStream.close();
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                throw th;
            }
        } catch (IOException e11) {
            e = e11;
            bufferedReader = null;
            httpURLConnection = null;
            httpURLConnection.disconnect();
            if (inputStream != null) {
                inputStream.close();
            }
            if (bufferedReader == null) {
                obj = e;
                str3 = str4;
            } else {
                bufferedReader.close();
                inputStream = e;
                str3 = str4;
            }
            if (inputStream == null) {
                return str3;
            }
            throw inputStream;
        } catch (Throwable th5) {
            th = th5;
            bufferedReader2 = null;
            b = null;
            b.disconnect();
            if (inputStream != null) {
                inputStream.close();
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            throw th;
        }
    }

    static String a(String str) throws IOException {
        return a(str, null);
    }
}
