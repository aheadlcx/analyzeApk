package com.alibaba.mtl.log.e;

import android.text.TextUtils;
import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import cn.v6.sixrooms.socket.common.SocketUtil;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.login.LoginConstants;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.entity.mime.MIME;

public final class e {

    public static class a {
        public int E = -1;
        public byte[] e = null;
    }

    static {
        System.setProperty("http.keepAlive", Constants.SERVICE_SCOPE_FLAG_VALUE);
    }

    public static a a(int i, String str, Map<String, Object> map, boolean z) {
        Exception e;
        DataOutputStream dataOutputStream;
        Throwable th;
        a aVar = new a();
        if (TextUtils.isEmpty(str)) {
            return aVar;
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            if (httpURLConnection != null) {
                if (i == 2 || i == 3) {
                    httpURLConnection.setDoOutput(true);
                }
                httpURLConnection.setDoInput(true);
                if (i == 2 || i == 3) {
                    try {
                        httpURLConnection.setRequestMethod("POST");
                    } catch (ProtocolException e2) {
                        e2.printStackTrace();
                        return aVar;
                    }
                }
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(60000);
                httpURLConnection.setRequestProperty("Connection", BoxingVoteBean.BOXING_VOTE_STATE_CLOSE);
                if (z) {
                    httpURLConnection.setRequestProperty("Accept-Encoding", "gzip,deflate");
                }
                httpURLConnection.setInstanceFollowRedirects(true);
                byte[] bArr = null;
                if (i == 2 || i == 3) {
                    byte[] bArr2;
                    int length;
                    if (i == 2) {
                        httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, "multipart/form-data; boundary=GJircTeP");
                    } else if (i == 3) {
                        httpURLConnection.setRequestProperty(MIME.CONTENT_TYPE, PostMethod.FORM_URL_ENCODED_CONTENT_TYPE);
                    }
                    if (map == null || map.size() <= 0) {
                        bArr2 = null;
                    } else {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        Set keySet = map.keySet();
                        String[] strArr = new String[keySet.size()];
                        keySet.toArray(strArr);
                        for (String str2 : g.a().a(strArr, true)) {
                            if (i == 2) {
                                bArr = (byte[]) map.get(str2);
                                if (bArr != null) {
                                    try {
                                        byteArrayOutputStream.write(String.format("--GJircTeP\r\nContent-Disposition: form-data; name=\"%s\"; filename=\"%s\"\r\nContent-Type: application/octet-stream \r\n\r\n", new Object[]{str2, str2}).getBytes());
                                        byteArrayOutputStream.write(bArr);
                                        byteArrayOutputStream.write(SocketUtil.CRLF.getBytes());
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                            } else if (i == 3) {
                                String str3 = (String) map.get(str2);
                                if (byteArrayOutputStream.size() > 0) {
                                    try {
                                        byteArrayOutputStream.write(("&" + str2 + LoginConstants.EQUAL + str3).getBytes());
                                    } catch (IOException e32) {
                                        e32.printStackTrace();
                                    }
                                } else {
                                    try {
                                        byteArrayOutputStream.write((str2 + LoginConstants.EQUAL + str3).getBytes());
                                    } catch (IOException e322) {
                                        e322.printStackTrace();
                                    }
                                }
                            }
                        }
                        if (i == 2) {
                            try {
                                byteArrayOutputStream.write("--GJircTeP--\r\n".getBytes());
                            } catch (IOException e3222) {
                                e3222.printStackTrace();
                            }
                        }
                        bArr2 = byteArrayOutputStream.toByteArray();
                    }
                    if (bArr2 != null) {
                        length = bArr2.length;
                    } else {
                        length = 0;
                    }
                    httpURLConnection.setRequestProperty("Content-Length", String.valueOf(length));
                    bArr = bArr2;
                }
                DataOutputStream dataOutputStream2 = null;
                try {
                    byte[] bArr3;
                    int read;
                    httpURLConnection.connect();
                    if ((i == 2 || i == 3) && bArr != null && bArr.length > 0) {
                        DataOutputStream dataOutputStream3 = new DataOutputStream(httpURLConnection.getOutputStream());
                        try {
                            dataOutputStream3.write(bArr);
                            dataOutputStream3.flush();
                            dataOutputStream2 = dataOutputStream3;
                        } catch (Exception e4) {
                            e = e4;
                            dataOutputStream = dataOutputStream3;
                            try {
                                e.printStackTrace();
                                if (dataOutputStream != null) {
                                    try {
                                        dataOutputStream.close();
                                    } catch (IOException e5) {
                                        e5.printStackTrace();
                                    }
                                }
                                return aVar;
                            } catch (Throwable th2) {
                                th = th2;
                                dataOutputStream2 = dataOutputStream;
                                if (dataOutputStream2 != null) {
                                    try {
                                        dataOutputStream2.close();
                                    } catch (IOException e32222) {
                                        e32222.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            dataOutputStream2 = dataOutputStream3;
                            if (dataOutputStream2 != null) {
                                dataOutputStream2.close();
                            }
                            throw th;
                        }
                    }
                    if (dataOutputStream2 != null) {
                        try {
                            dataOutputStream2.close();
                        } catch (IOException e322222) {
                            e322222.printStackTrace();
                        }
                    }
                    try {
                        aVar.E = httpURLConnection.getResponseCode();
                    } catch (IOException e3222222) {
                        e3222222.printStackTrace();
                    }
                    InputStream inputStream = null;
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    if (z) {
                        try {
                            if ("gzip".equals(httpURLConnection.getContentEncoding())) {
                                inputStream = new GZIPInputStream(httpURLConnection.getInputStream());
                                System.currentTimeMillis();
                                bArr3 = new byte[2048];
                                while (true) {
                                    read = inputStream.read(bArr3, 0, 2048);
                                    if (read != -1) {
                                        break;
                                    }
                                    byteArrayOutputStream2.write(bArr3, 0, read);
                                }
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (Exception e6) {
                                        e6.printStackTrace();
                                    }
                                }
                                if (byteArrayOutputStream2.size() > 0) {
                                    aVar.e = byteArrayOutputStream2.toByteArray();
                                }
                            }
                        } catch (IOException e52) {
                            e52.printStackTrace();
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception e62) {
                                    e62.printStackTrace();
                                }
                            }
                            return aVar;
                        } catch (Throwable th4) {
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception e7) {
                                    e7.printStackTrace();
                                }
                            }
                        }
                    }
                    inputStream = new DataInputStream(httpURLConnection.getInputStream());
                    System.currentTimeMillis();
                    bArr3 = new byte[2048];
                    while (true) {
                        read = inputStream.read(bArr3, 0, 2048);
                        if (read != -1) {
                            break;
                        }
                        byteArrayOutputStream2.write(bArr3, 0, read);
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (byteArrayOutputStream2.size() > 0) {
                        aVar.e = byteArrayOutputStream2.toByteArray();
                    }
                } catch (Exception e8) {
                    e62 = e8;
                    dataOutputStream = null;
                    e62.printStackTrace();
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    return aVar;
                } catch (Throwable th5) {
                    th = th5;
                    if (dataOutputStream2 != null) {
                        dataOutputStream2.close();
                    }
                    throw th;
                }
            }
            return aVar;
        } catch (MalformedURLException e9) {
            e9.printStackTrace();
            return aVar;
        } catch (IOException e522) {
            e522.printStackTrace();
            return aVar;
        }
    }
}
