package com.alibaba.sdk.android.httpdns;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import javax.net.ssl.HttpsURLConnection;

public class n implements Callable {
    private int d;

    public n(int i) {
        this.d = i;
    }

    public String[] b() {
        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        HttpURLConnection httpURLConnection2;
        Throwable th;
        BufferedReader bufferedReader2;
        InputStream inputStream;
        BufferedReader bufferedReader3 = null;
        InputStream inputStream2;
        try {
            String f = q.a().f();
            if (f != null) {
                httpURLConnection = (HttpURLConnection) new URL(f).openConnection();
                try {
                    httpURLConnection.setConnectTimeout(15000);
                    httpURLConnection.setReadTimeout(15000);
                    if (httpURLConnection instanceof HttpsURLConnection) {
                        ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new o(this));
                    }
                    if (httpURLConnection.getResponseCode() != 200) {
                        f.e("response code is " + httpURLConnection.getResponseCode() + " expect 200");
                        q.a().b(new e(httpURLConnection.getResponseCode(), ""));
                        inputStream2 = null;
                    } else {
                        inputStream2 = httpURLConnection.getInputStream();
                        try {
                            bufferedReader = new BufferedReader(new InputStreamReader(inputStream2, "UTF-8"));
                        } catch (Throwable e) {
                            httpURLConnection2 = httpURLConnection;
                            th = e;
                            bufferedReader2 = null;
                            inputStream = inputStream2;
                            try {
                                f.a(th);
                                q.a().b(th);
                                if (this.d > 0) {
                                    this.d--;
                                    b();
                                }
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (Throwable th2) {
                                        f.a(th2);
                                    }
                                }
                                if (bufferedReader2 != null) {
                                    bufferedReader2.close();
                                }
                                return new String[0];
                            } catch (Throwable th3) {
                                th2 = th3;
                                inputStream2 = inputStream;
                                bufferedReader3 = bufferedReader2;
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                }
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (Throwable e2) {
                                        f.a(e2);
                                        throw th2;
                                    }
                                }
                                if (bufferedReader3 != null) {
                                    bufferedReader3.close();
                                }
                                throw th2;
                            }
                        } catch (Throwable e22) {
                            httpURLConnection2 = httpURLConnection;
                            th2 = e22;
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            if (inputStream2 != null) {
                                inputStream2.close();
                            }
                            if (bufferedReader3 != null) {
                                bufferedReader3.close();
                            }
                            throw th2;
                        }
                        try {
                            StringBuilder stringBuilder = new StringBuilder();
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                stringBuilder.append(readLine);
                            }
                            q.a().a(new s(stringBuilder.toString()));
                            bufferedReader3 = bufferedReader;
                        } catch (Throwable e222) {
                            inputStream = inputStream2;
                            BufferedReader bufferedReader4 = bufferedReader;
                            httpURLConnection2 = httpURLConnection;
                            th2 = e222;
                            bufferedReader2 = bufferedReader4;
                            f.a(th2);
                            q.a().b(th2);
                            if (this.d > 0) {
                                this.d--;
                                b();
                            }
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (bufferedReader2 != null) {
                                bufferedReader2.close();
                            }
                            return new String[0];
                        } catch (Throwable e2222) {
                            bufferedReader3 = bufferedReader;
                            httpURLConnection2 = httpURLConnection;
                            th2 = e2222;
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            if (inputStream2 != null) {
                                inputStream2.close();
                            }
                            if (bufferedReader3 != null) {
                                bufferedReader3.close();
                            }
                            throw th2;
                        }
                    }
                } catch (Throwable e22222) {
                    httpURLConnection2 = httpURLConnection;
                    th2 = e22222;
                    bufferedReader2 = null;
                    f.a(th2);
                    q.a().b(th2);
                    if (this.d > 0) {
                        this.d--;
                        b();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    return new String[0];
                } catch (Throwable e222222) {
                    inputStream2 = null;
                    httpURLConnection2 = httpURLConnection;
                    th2 = e222222;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (bufferedReader3 != null) {
                        bufferedReader3.close();
                    }
                    throw th2;
                }
            }
            inputStream2 = null;
            httpURLConnection = null;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream2 != null) {
                try {
                    inputStream2.close();
                } catch (Throwable th22) {
                    f.a(th22);
                }
            }
            if (bufferedReader3 != null) {
                bufferedReader3.close();
            }
        } catch (Exception e3) {
            th22 = e3;
            bufferedReader2 = null;
            httpURLConnection2 = null;
            f.a(th22);
            q.a().b(th22);
            if (this.d > 0) {
                this.d--;
                b();
            }
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            return new String[0];
        } catch (Throwable th4) {
            th22 = th4;
            inputStream2 = null;
            httpURLConnection2 = null;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            if (inputStream2 != null) {
                inputStream2.close();
            }
            if (bufferedReader3 != null) {
                bufferedReader3.close();
            }
            throw th22;
        }
        return new String[0];
    }

    public /* synthetic */ Object call() {
        return b();
    }
}
