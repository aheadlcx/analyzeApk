package com.budejie.www.http;

import android.content.Context;
import android.os.Handler;
import cn.v6.sixrooms.socket.common.SocketUtil;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.budejie.www.util.aa;
import com.budejie.www.util.an;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.MIME;

class m$1 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ List c;
    final /* synthetic */ boolean d;
    final /* synthetic */ String e;
    final /* synthetic */ String f;
    final /* synthetic */ Handler g;
    final /* synthetic */ int h;
    final /* synthetic */ m i;

    m$1(m mVar, String str, Context context, List list, boolean z, String str2, String str3, Handler handler, int i) {
        this.i = mVar;
        this.a = str;
        this.b = context;
        this.c = list;
        this.d = z;
        this.e = str2;
        this.f = str3;
        this.g = handler;
        this.h = i;
    }

    public void run() {
        InputStream inputStream;
        InputStream inputStream2;
        DataOutputStream dataOutputStream;
        HttpURLConnection httpURLConnection;
        OutOfMemoryError outOfMemoryError;
        Throwable th;
        HttpURLConnection httpURLConnection2;
        Exception exception;
        Exception exception2;
        Throwable th2;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        String str = "";
        DataOutputStream dataOutputStream2;
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(this.a).openConnection();
            try {
                httpURLConnection3.setDoInput(true);
                httpURLConnection3.setDoOutput(true);
                httpURLConnection3.setUseCaches(false);
                httpURLConnection3.setRequestMethod("POST");
                httpURLConnection3.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection3.setRequestProperty("Charset", "UTF-8");
                httpURLConnection3.setRequestProperty(MIME.CONTENT_TYPE, "multipart/form-data; boundary=---------7d4a6d158c9");
                m.a(this.i, this.b, httpURLConnection3);
                dataOutputStream2 = new DataOutputStream(httpURLConnection3.getOutputStream());
                try {
                    String name;
                    StringBuffer stringBuffer = new StringBuffer();
                    for (int i = 0; i < this.c.size(); i++) {
                        stringBuffer.append("--");
                        stringBuffer.append("---------7d4a6d158c9");
                        stringBuffer.append(SocketUtil.CRLF);
                        stringBuffer.append("Content-Disposition: form-data; name=\"" + ((NameValuePair) this.c.get(i)).getName() + "\"\r\n\r\n");
                        stringBuffer.append(((NameValuePair) this.c.get(i)).getValue());
                        stringBuffer.append(SocketUtil.CRLF);
                    }
                    dataOutputStream2.write(stringBuffer.toString().getBytes("utf-8"));
                    if (this.d) {
                        File file = new File(this.e);
                        byte[] c = an.c(this.e);
                        name = file.getName();
                        name = AlibcConstants.PF_ANDROID + System.currentTimeMillis() + "" + name.substring(name.lastIndexOf("."));
                        stringBuffer = new StringBuffer();
                        stringBuffer.append("--");
                        stringBuffer.append("---------7d4a6d158c9");
                        stringBuffer.append(SocketUtil.CRLF);
                        stringBuffer.append("Content-Disposition: form-data; name=\"image\"; filename=\"" + name + "\"\r\n");
                        stringBuffer.append("Content-Type: " + this.f + "\r\n\r\n");
                        dataOutputStream2.write(stringBuffer.toString().getBytes("utf-8"));
                        dataOutputStream2.write(c);
                        dataOutputStream2.write(SocketUtil.CRLF.getBytes("utf-8"));
                        dataOutputStream2.write("-----------7d4a6d158c9--\r\n".getBytes("utf-8"));
                    }
                    dataOutputStream2.flush();
                    if (httpURLConnection3.getResponseCode() != 200) {
                        throw new RuntimeException("request failed");
                    }
                    inputStream = httpURLConnection3.getInputStream();
                    try {
                        byte[] bArr = new byte[1024];
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        name = new String(byteArrayOutputStream.toByteArray(), "utf-8");
                        if (null != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e) {
                            }
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (dataOutputStream2 != null) {
                            dataOutputStream2.close();
                        }
                        if (null != null) {
                            fileOutputStream.close();
                        }
                        httpURLConnection3.disconnect();
                        this.g.sendMessage(this.g.obtainMessage(this.h, name));
                    } catch (OutOfMemoryError e2) {
                        OutOfMemoryError outOfMemoryError2 = e2;
                        inputStream2 = inputStream;
                        dataOutputStream = dataOutputStream2;
                        httpURLConnection = httpURLConnection3;
                        outOfMemoryError = outOfMemoryError2;
                        try {
                            aa.e("TougaoTool", "OutOfMemoryError ," + outOfMemoryError.toString());
                            if (null != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e3) {
                                    httpURLConnection.disconnect();
                                    this.g.sendMessage(this.g.obtainMessage(this.h, str));
                                }
                            }
                            if (inputStream2 != null) {
                                inputStream2.close();
                            }
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                            if (null != null) {
                                fileOutputStream.close();
                            }
                            httpURLConnection.disconnect();
                            this.g.sendMessage(this.g.obtainMessage(this.h, str));
                        } catch (Throwable th3) {
                            th = th3;
                            InputStream inputStream3 = inputStream2;
                            httpURLConnection2 = httpURLConnection;
                            dataOutputStream2 = dataOutputStream;
                            inputStream = inputStream3;
                            if (null != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e4) {
                                    httpURLConnection2.disconnect();
                                    this.g.sendMessage(this.g.obtainMessage(this.h, str));
                                    throw th;
                                }
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (dataOutputStream2 != null) {
                                dataOutputStream2.close();
                            }
                            if (null != null) {
                                fileOutputStream.close();
                            }
                            httpURLConnection2.disconnect();
                            this.g.sendMessage(this.g.obtainMessage(this.h, str));
                            throw th;
                        }
                    } catch (Exception e5) {
                        exception = e5;
                        httpURLConnection2 = httpURLConnection3;
                        exception2 = exception;
                        try {
                            exception2.printStackTrace();
                            if (null != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Exception e6) {
                                    httpURLConnection2.disconnect();
                                    this.g.sendMessage(this.g.obtainMessage(this.h, str));
                                }
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (dataOutputStream2 != null) {
                                dataOutputStream2.close();
                            }
                            if (null != null) {
                                fileOutputStream.close();
                            }
                            httpURLConnection2.disconnect();
                            this.g.sendMessage(this.g.obtainMessage(this.h, str));
                        } catch (Throwable th4) {
                            th = th4;
                            if (null != null) {
                                fileInputStream.close();
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            if (dataOutputStream2 != null) {
                                dataOutputStream2.close();
                            }
                            if (null != null) {
                                fileOutputStream.close();
                            }
                            httpURLConnection2.disconnect();
                            this.g.sendMessage(this.g.obtainMessage(this.h, str));
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th2 = th5;
                        httpURLConnection2 = httpURLConnection3;
                        th = th2;
                        if (null != null) {
                            fileInputStream.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (dataOutputStream2 != null) {
                            dataOutputStream2.close();
                        }
                        if (null != null) {
                            fileOutputStream.close();
                        }
                        httpURLConnection2.disconnect();
                        this.g.sendMessage(this.g.obtainMessage(this.h, str));
                        throw th;
                    }
                } catch (OutOfMemoryError e22) {
                    dataOutputStream = dataOutputStream2;
                    httpURLConnection = httpURLConnection3;
                    outOfMemoryError = e22;
                    inputStream2 = null;
                    aa.e("TougaoTool", "OutOfMemoryError ," + outOfMemoryError.toString());
                    if (null != null) {
                        fileInputStream.close();
                    }
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    if (null != null) {
                        fileOutputStream.close();
                    }
                    httpURLConnection.disconnect();
                    this.g.sendMessage(this.g.obtainMessage(this.h, str));
                } catch (Exception e52) {
                    inputStream = null;
                    exception = e52;
                    httpURLConnection2 = httpURLConnection3;
                    exception2 = exception;
                    exception2.printStackTrace();
                    if (null != null) {
                        fileInputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (dataOutputStream2 != null) {
                        dataOutputStream2.close();
                    }
                    if (null != null) {
                        fileOutputStream.close();
                    }
                    httpURLConnection2.disconnect();
                    this.g.sendMessage(this.g.obtainMessage(this.h, str));
                } catch (Throwable th52) {
                    inputStream = null;
                    th2 = th52;
                    httpURLConnection2 = httpURLConnection3;
                    th = th2;
                    if (null != null) {
                        fileInputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (dataOutputStream2 != null) {
                        dataOutputStream2.close();
                    }
                    if (null != null) {
                        fileOutputStream.close();
                    }
                    httpURLConnection2.disconnect();
                    this.g.sendMessage(this.g.obtainMessage(this.h, str));
                    throw th;
                }
            } catch (OutOfMemoryError e222) {
                dataOutputStream = null;
                httpURLConnection = httpURLConnection3;
                outOfMemoryError = e222;
                inputStream2 = null;
                aa.e("TougaoTool", "OutOfMemoryError ," + outOfMemoryError.toString());
                if (null != null) {
                    fileInputStream.close();
                }
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (null != null) {
                    fileOutputStream.close();
                }
                httpURLConnection.disconnect();
                this.g.sendMessage(this.g.obtainMessage(this.h, str));
            } catch (Exception e522) {
                inputStream = null;
                dataOutputStream2 = null;
                exception = e522;
                httpURLConnection2 = httpURLConnection3;
                exception2 = exception;
                exception2.printStackTrace();
                if (null != null) {
                    fileInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (dataOutputStream2 != null) {
                    dataOutputStream2.close();
                }
                if (null != null) {
                    fileOutputStream.close();
                }
                httpURLConnection2.disconnect();
                this.g.sendMessage(this.g.obtainMessage(this.h, str));
            } catch (Throwable th522) {
                inputStream = null;
                dataOutputStream2 = null;
                th2 = th522;
                httpURLConnection2 = httpURLConnection3;
                th = th2;
                if (null != null) {
                    fileInputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (dataOutputStream2 != null) {
                    dataOutputStream2.close();
                }
                if (null != null) {
                    fileOutputStream.close();
                }
                httpURLConnection2.disconnect();
                this.g.sendMessage(this.g.obtainMessage(this.h, str));
                throw th;
            }
        } catch (OutOfMemoryError e7) {
            outOfMemoryError = e7;
            inputStream2 = null;
            dataOutputStream = null;
            httpURLConnection = null;
            aa.e("TougaoTool", "OutOfMemoryError ," + outOfMemoryError.toString());
            if (null != null) {
                fileInputStream.close();
            }
            if (inputStream2 != null) {
                inputStream2.close();
            }
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            if (null != null) {
                fileOutputStream.close();
            }
            httpURLConnection.disconnect();
            this.g.sendMessage(this.g.obtainMessage(this.h, str));
        } catch (Exception e8) {
            exception2 = e8;
            inputStream = null;
            dataOutputStream2 = null;
            httpURLConnection2 = null;
            exception2.printStackTrace();
            if (null != null) {
                fileInputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
            }
            if (null != null) {
                fileOutputStream.close();
            }
            httpURLConnection2.disconnect();
            this.g.sendMessage(this.g.obtainMessage(this.h, str));
        } catch (Throwable th6) {
            th = th6;
            inputStream = null;
            dataOutputStream2 = null;
            httpURLConnection2 = null;
            if (null != null) {
                fileInputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (dataOutputStream2 != null) {
                dataOutputStream2.close();
            }
            if (null != null) {
                fileOutputStream.close();
            }
            httpURLConnection2.disconnect();
            this.g.sendMessage(this.g.obtainMessage(this.h, str));
            throw th;
        }
    }
}
